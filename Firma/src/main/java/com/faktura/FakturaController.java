package com.faktura;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.firma.Firma;
import com.firma.FirmaClient;
import com.firma.FirmaService;
import com.firmas.Firmas;
import com.itextpdf.text.DocumentException;
import com.pdfTransformer.PDFTransformer;
import com.stavkaFakture.StavkaFakture;
import com.stavkaFakture.StavkaFaktureService;
import com.zaglavljeFakture.ZaglavljeFakture;
import com.zaglavljeFakture.ZaglavljeFaktureService;


@RestController
@RequestMapping("/faktura")
public class FakturaController {

	private final FakturaService fakturaService;
	private final ZaglavljeFaktureService zaglavljeFaktureService;
	private final StavkaFaktureService stavkaFaktureService;
	private final FirmaService firmaService;
	private final HttpSession httpSession;
	
	@Autowired
	FirmaClient firmClient;
	 
	@Autowired
	public FakturaController(final FakturaService fakturaService,final ZaglavljeFaktureService zaglavljeFaktureService,
			final StavkaFaktureService stavkaFaktureService,final FirmaService firmaService,final HttpSession httpSession){
		this.fakturaService = fakturaService;
		this.zaglavljeFaktureService = zaglavljeFaktureService;
		this.stavkaFaktureService = stavkaFaktureService;
		this.firmaService = firmaService;
		this.httpSession  = httpSession;
	}
	
	@PostMapping(path = "/{idZaglavlja}")
	@ResponseStatus(HttpStatus.CREATED)
	public Faktura save(@PathVariable Long idZaglavlja,@RequestBody StavkaFakture stavkaFakture) {
		System.out.println(idZaglavlja+"   "+stavkaFakture.getNazivRobeIliUsluge());
		ZaglavljeFakture zaglavljeFakture = zaglavljeFaktureService.findOne(idZaglavlja);
		StavkaFakture sacuvanaSF = stavkaFaktureService.save(stavkaFakture);
		Faktura postojecaFaktura = fakturaService.findByZaglavljeFakture_Id(idZaglavlja);
		Faktura savedFaktura = null;
		if(postojecaFaktura==null){
			Faktura faktura= new Faktura();
			faktura.setZaglavljeFakture(zaglavljeFakture);
			stavkaFakture.setRedniBroj(BigInteger.valueOf(100L));
			List<StavkaFakture> stavke = new ArrayList<StavkaFakture>();
			stavke.add(stavkaFakture);
			faktura.setStavkaFakture(stavke);
			savedFaktura=fakturaService.save(faktura);
			//sendFaktura(faktura,kupac);
			
		}else{
			BigInteger redniBroj = BigInteger.valueOf(postojecaFaktura.getStavkaFakture().size()+1);
			stavkaFakture.setRedniBroj(redniBroj);
			postojecaFaktura.getStavkaFakture().add(stavkaFakture);
			savedFaktura= fakturaService.save(postojecaFaktura);
			//sendFaktura(postojecaFaktura,kupac);
			//fakturaService.delete(postojecaFaktura.getId());
		}
		return savedFaktura;
	}
	
	@GetMapping(path = "/findAllUlazneFakture")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Faktura> findAllUlazneFakture() {
		Firma firma = ((Firmas) httpSession.getAttribute("user")).getFirma();		
		List<Faktura> ulazneFakture = fakturaService.findByZaglavljeFakture_PibKupca(firma.getPIB());
		if(ulazneFakture  == null){
			return new ArrayList<Faktura>();
		}
		return ulazneFakture;
	}
	
	@GetMapping(path = "/findAllIzlazneFakture")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Faktura> findAllIzlazneFakture() {
		Firma firma = ((Firmas) httpSession.getAttribute("user")).getFirma();		
		List<Faktura> ulazneFakture = fakturaService.findByZaglavljeFakture_PibDobavljaca(firma.getPIB());
		if(ulazneFakture  == null){
			return new ArrayList<Faktura>();
		}
		return ulazneFakture;
	}
	
	
	@PostMapping(path = "/sendFaktura")
	@ResponseStatus(HttpStatus.CREATED)
	public Faktura save(@RequestBody Faktura faktura) {
		Firma kupac = firmaService.findByPIB(faktura.getZaglavljeFakture().getPibKupca());
		
		Faktura result = sendFaktura(faktura,kupac);
		return result;
	}

	//REST Client Code
	private static Faktura sendFaktura(Faktura faktura,Firma kupac)
	{
		
	    final String uri = kupac.getUri()+"/RESTApi/acceptFaktura";
	    RestTemplate restTemplate = new RestTemplate();
	    Faktura result = restTemplate.postForObject( uri,faktura, Faktura.class);
	    
	    return result;
	}
	
	@PostMapping(path = "/createHTML")
	@ResponseStatus(HttpStatus.CREATED)
	public void createHTML(@RequestBody Faktura faktura) throws IOException, DocumentException {
		System.out.println("createXML "+faktura.getZaglavljeFakture().getNazivKupca());
		File file = createFakturaXML(faktura);	
		final String INPUT_FILE = file.getPath();
		System.out.println(INPUT_FILE);
		final String XSL_FILE = "gen/itext/faktura.xsl";
		final String HTML_FILE = "gen/itext/faktura.html";
		final String OUTPUT_FILE = "gen/itext/faktura.pdf";
    	// Creates parent directory if necessary
    	File pdfFile = new File(OUTPUT_FILE);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateHTML(INPUT_FILE, XSL_FILE,HTML_FILE);
		
		File html = new File(HTML_FILE);
        httpSession.setAttribute("html", html);
		System.out.println("[INFO] File \"" + HTML_FILE + "\" generated successfully.");
		System.out.println("[INFO] End.");

	}
	
	@GetMapping("/fakturaHTML")
	@ResponseStatus(HttpStatus.OK)
	public void createHTML(HttpServletResponse response) throws IOException{
		File file = (File)httpSession.getAttribute("html");
		response.setContentType("text/html");
		InputStream inputStream = new FileInputStream(file);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	public File createFakturaXML(Faktura faktura){
		try {
			
			File file = new File("createFaktura.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Faktura.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
			jaxbMarshaller.marshal(faktura, file);
			jaxbMarshaller.marshal(faktura, System.out);
			
			// dodaj liniju sa referencom na xsl u postojeci xml fajl
			Path path = Paths.get("createFaktura.xml");
			List<String> lines;
			try {
				lines = Files.readAllLines(path, StandardCharsets.UTF_8);
				String stylesheet = "<?xml-stylesheet type=\"text/xsl\" href=\"faktura.xsl\"?>";  
				lines.add(1, stylesheet);
				Files.write(path, lines, StandardCharsets.UTF_8);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return file;

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(path = "/createPDF")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPDF(@RequestBody Faktura faktura) throws IOException, DocumentException {
		
		File file = createFakturaXML(faktura);
		final String INPUT_FILE = file.getPath();
		
		final String XSL_FILE = "gen/itext/faktura.xsl";
		final String HTML_FILE = "gen/itext/faktura.html";
		final String OUTPUT_FILE = "gen/itext/faktura.pdf";
    	// Creates parent directory if necessary
    	File pdfFile = new File(OUTPUT_FILE);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		pdfTransformer.generateHTML(INPUT_FILE, XSL_FILE,HTML_FILE);
		pdfTransformer.generatePDF(OUTPUT_FILE,HTML_FILE);
		
		File pdf = new File(OUTPUT_FILE);
        httpSession.setAttribute("pdf", pdf);
		System.out.println("[INFO] File \"" + OUTPUT_FILE + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}
	
	@GetMapping("/fakturaPDF")
	@ResponseStatus(HttpStatus.OK)
	public void createFakturaPDF(HttpServletResponse response) throws IOException{
		File pdf = (File)httpSession.getAttribute("pdf");
		response.setContentType("application/pdf");
		InputStream inputStream = new FileInputStream(pdf);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
}



