package com.nalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.faktura.Faktura;
import com.firma.Firma;
import com.firma.FirmaClient;
import com.firma.FirmaService;
import com.firma.ValidacijaSema;
import com.itextpdf.text.DocumentException;
import com.pdfTransformer.PDFTransformer;


@RestController
@RequestMapping("/nalog")
public class NalogController {

	private final FirmaService firmaService;
	private HttpSession httpSession;
	private final NalogService nalogService;
	
	@Autowired
	FirmaClient firmClient;
	
	
	@Autowired
	public NalogController(final HttpSession httpSession,final FirmaService firmaService,final NalogService nalogService) {
		this.httpSession = httpSession;
		this.firmaService = firmaService;
		this.nalogService = nalogService;
	}
	
	
	///ovako kreirani nalog bi trebao da se posalje banci preko SOAP-a
		//ili kao xml nalog ili obicni objekat
		@PostMapping(path = "/sendNalog")
		@ResponseStatus(HttpStatus.CREATED)
		public Nalog sendNalog(@RequestBody Faktura faktura){
			
			if(!ValidacijaSema.validirajSemu(faktura, "faktura")) {
				System.out.println("Nevalidna faktura");
				return null;
			}
			
			
			Nalog nalog = new Nalog();
			String uniqueID = UUID.randomUUID().toString();
			
			nalog.setIdPoruke(uniqueID);
			nalog.setDuznik(faktura.getZaglavljeFakture().getNazivKupca());
			nalog.setSvrhaPlacanja("prodaja");
			nalog.setPrimalac(faktura.getZaglavljeFakture().getNazivDobavljaca());
			nalog.setDatumNaloga(faktura.getZaglavljeFakture().getDatumRacuna());
			nalog.setDatumValute(faktura.getZaglavljeFakture().getDatumValute());
			Firma duznik = firmaService.findByPIB(faktura.getZaglavljeFakture().getPibKupca());
			
			nalog.setRacunDuznika(duznik.getRacun().getBrojRacuna());
			nalog.setModelZaduzenja(BigInteger.valueOf(97L));
			nalog.setPozivNaBrojZaduzenja("11111111111111111111");
			nalog.setRacunPrimaoca(faktura.getZaglavljeFakture().getUplataNaRacun());
			nalog.setModelOdobrenja(BigInteger.valueOf(97L));
			nalog.setPozivNaBrojOdobrenja("22222222222222222222");
			nalog.setIznos(faktura.getZaglavljeFakture().getIznosZaUplatu());
			nalog.setOznakaValute(faktura.getZaglavljeFakture().getOznakaValute());
			//ako je vece od 250000 postavi da je nalog hitan
			if(nalog.getIznos().compareTo(BigDecimal.valueOf(250000L))>1){
				nalog.setHitno(true);
			}else{
				nalog.setHitno(false);
			}
			
			firmClient.sendNalogTemp(nalog);
			return nalogService.save(nalog);
		}
		
		@GetMapping(path = "/findAllNaloge")
		@ResponseStatus(HttpStatus.CREATED)
		public List<com.zahtevZaDobijanjeNaloga.Nalogg> findAllNaloge() {
		
			
			List<com.zahtevZaDobijanjeNaloga.Nalogg> nalozi  = firmClient.getlistaNaloga();
			if(nalozi  == null){
				return new ArrayList<com.zahtevZaDobijanjeNaloga.Nalogg>();
			}
			return nalozi;
		}
		
		@PostMapping(path = "/createHTML")
		@ResponseStatus(HttpStatus.CREATED)
		public void createHTML(@RequestBody Nalog nalog) throws IOException, DocumentException {
			System.out.println("createHTMLNalog "+nalog.getDuznik());
			File file = createNalogXML(nalog);	
			final String INPUT_FILE = file.getPath();
			System.out.println(INPUT_FILE);
			final String XSL_FILE = "gen/itext/nalog.xsl";
			final String HTML_FILE = "gen/itext/nalog.html";
			final String OUTPUT_FILE = "gen/itext/nalog.pdf";
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
		
		@GetMapping("/nalogHTML")
		@ResponseStatus(HttpStatus.OK)
		public void createHTML(HttpServletResponse response) throws IOException{
			File file = (File)httpSession.getAttribute("html");
			response.setContentType("text/html");
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
		
		public File createNalogXML(Nalog nalog){
			try {
				
				File file = new File("createNalog.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Nalog.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
				jaxbMarshaller.marshal(nalog, file);
				jaxbMarshaller.marshal(nalog, System.out);
				
				// dodaj liniju sa referencom na xsl u postojeci xml fajl
				Path path = Paths.get("createNalog.xml");
				List<String> lines;
				try {
					lines = Files.readAllLines(path, StandardCharsets.UTF_8);
					String stylesheet = "<?xml-stylesheet type=\"text/xsl\" href=\"nalog.xsl\"?>";  
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
		public void createPDF(@RequestBody Nalog nalog) throws IOException, DocumentException {
			System.out.println("createPDFNalog "+nalog.getDuznik());
			File file = createNalogXML(nalog);
			final String INPUT_FILE = file.getPath();
			System.out.println(INPUT_FILE);
			final String XSL_FILE = "gen/itext/nalog.xsl";
			final String HTML_FILE = "gen/itext/nalog.html";
			final String OUTPUT_FILE = "gen/itext/nalog.pdf";
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
		
		@GetMapping("/nalogPDF")
		@ResponseStatus(HttpStatus.OK)
		public void createFakturaPDF(HttpServletResponse response) throws IOException{
			File pdf = (File)httpSession.getAttribute("pdf");
			response.setContentType("application/pdf");
			InputStream inputStream = new FileInputStream(pdf);
			IOUtils.copy(inputStream, response.getOutputStream());
		}
}
