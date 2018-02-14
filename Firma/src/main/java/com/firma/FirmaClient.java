package com.firma;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.MyLogger;
import com.banka.Banka;
import com.banka.BankaService;
import com.firmas.Firmas;
import com.nalog.GetNalogRequest;
import com.nalog.GetNalogResponse;
import com.nalog.Nalog;
import com.presek.GetPresekResponse;
import com.racun.Racun;
import com.zahtevZaDobijanjeNaloga.GetZahtevZaDobijanjeNalogaRequest;
import com.zahtevZaDobijanjeNaloga.GetZahtevZaDobijanjeNalogaResponse;
import com.zahtevZaDobijanjeNaloga.ZahtevZaDobijanjeNaloga;
import com.zahtevzadobijanjeizvoda.GetZahtevZaDobijanjeIzvodaRequest;
import com.zahtevzadobijanjeizvoda.ZahtevZaDobijanjeIzvoda;

@Component
public class FirmaClient {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private BankaService bankService;
	
	@Autowired
	private FirmaService firmaService;
	
	private String logPrefix = "FirmaClient";
	
	@Autowired
	private Environment environment;
	
	@PostConstruct
	public void reinitLogPrefix()
	{
		logPrefix += ":" + environment.getProperty("server.port");
	}
	
	public void sendNalogTemp(Nalog nalog2) { //ISPRAVNA 		
		MyLogger.log(logPrefix, "sendNalogTemp started");
		
		GetNalogRequest nalogRequest =  new GetNalogRequest();
		//Nalog nalog = nalogService.findOne(1l);
		nalogRequest.setNalog(nalog2);
		Firmas firmas = (Firmas) httpSession.getAttribute("user");
		List<Banka> banke = bankService.findAll();
		
		String uri = "";
		for(int i =0;i<banke.size();i++) {
			List<Racun> racuni =  banke.get(i).getRacuni();	
			for(int j =0;j<racuni.size();j++) {
				if(racuni.get(j).getBrojRacuna().equals(firmas.getFirma().getRacun().getBrojRacuna()))
					uri = banke.get(i).getUri() + "/ws";
			}
		}
		
		webServiceTemplate.setDefaultUri(uri);
		
		if(!ValidacijaSema.validirajSemu(nalogRequest, "nalog")) {
			System.out.println("Nevalidan nalog");
		}else{
			GetNalogResponse nalogResponse = (GetNalogResponse) webServiceTemplate.marshalSendAndReceive(nalogRequest);
		}		
		MyLogger.log(logPrefix, "sendNalogTemp finished");
	}
	
	public GetPresekResponse findPreseke(ZahtevZaDobijanjeIzvoda zahtev) {	
		MyLogger.log(logPrefix, "findPreseke started");
		Firmas firmas = (Firmas) httpSession.getAttribute("user");
		Firma firma = firmaService.findOne(firmas.getFirma().getId());
		zahtev.setBrojRacuna(firma.getRacun().getBrojRacuna());
		
		String uri = "";
		for(int i=0;i<bankService.findAll().size();i++) {
			for(int j=0;j<bankService.findAll().get(i).getRacuni().size();j++) {
				if(bankService.findAll().get(i).getRacuni().get(j).getBrojRacuna().equals(firma.getRacun().getBrojRacuna())) {
					uri = bankService.findAll().get(i).getUri()+ "/ws";					
				}
			}
		}	
		
		GetZahtevZaDobijanjeIzvodaRequest request = new GetZahtevZaDobijanjeIzvodaRequest();
		request.setZahtevZaDobijanjeIzvoda(zahtev);
		webServiceTemplate.setDefaultUri(uri);
 		GetPresekResponse response = (GetPresekResponse) webServiceTemplate.marshalSendAndReceive(request);	
		MyLogger.log(logPrefix, "findPreseke finished");
		return response;
	}


	public List<com.zahtevZaDobijanjeNaloga.Nalogg> getlistaNaloga() { 
		MyLogger.log(logPrefix, "getlistaNaloga started");
		List<com.zahtevZaDobijanjeNaloga.Nalogg> nalozi = new ArrayList<com.zahtevZaDobijanjeNaloga.Nalogg>();
		
		Firmas firmas = (Firmas) httpSession.getAttribute("user");
		Firma firma = firmaService.findOne(firmas.getFirma().getId());
		
		GetZahtevZaDobijanjeNalogaRequest request = new GetZahtevZaDobijanjeNalogaRequest();
		ZahtevZaDobijanjeNaloga zahtevZaNaloge = new ZahtevZaDobijanjeNaloga();
		zahtevZaNaloge.setBrojRacuna(firma.getRacun().getBrojRacuna());
		
		request.setZahtevZaDobijanjeNaloga(zahtevZaNaloge);
		
		String uri = "";
		for(int i=0;i<bankService.findAll().size();i++) {
			for(int j=0;j<bankService.findAll().get(i).getRacuni().size();j++) {
				if(bankService.findAll().get(i).getRacuni().get(j).getBrojRacuna().equals(firma.getRacun().getBrojRacuna())) {
					uri = bankService.findAll().get(i).getUri()+ "/ws";					
				}
				
			}
		}	
		webServiceTemplate.setDefaultUri(uri);
		
		if(!ValidacijaSema.validirajSemu(request, "zahtevZaDobijanjeNaloga")) {
			System.out.println("Nevalidan nalog");
		}else{
 		
			GetZahtevZaDobijanjeNalogaResponse response = (GetZahtevZaDobijanjeNalogaResponse) webServiceTemplate.marshalSendAndReceive(request);
			
			
			if(!ValidacijaSema.validirajSemu(response, "zahtevZaDobijanjeNaloga")) {
				System.out.println("Nevalidan nalog");
			}else{
				nalozi = response.getNalogg();
				
				/*for(int i = 0; i< nalozi.size(); i++){
					if(!ValidacijaSema.validirajNalog(nalozi.get(i), "nalog"+i)) {
							System.out.println("Nevalidan nalog "  + i);
					}
				}*/
			}
		}
 		

		MyLogger.log(logPrefix, "getlistaNaloga finished");
		return nalozi;
		
	}
}