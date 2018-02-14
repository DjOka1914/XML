package com.endopoints;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.MyLogger;
import com.config.NarodnaBankaClient;
import com.mt102.GetMT102Request;
import com.mt102.MT102Service;
import com.mt103.GetMT103Request;
import com.mt103.MT103Service;
import com.mt900.GetMT900Response;
import com.mt900.MT900;
import com.mt910.MT910;

@Endpoint
@Component
public class NarodnaBankaEndpoint {
	private String logPrefix = "NarodnaBankaEndpoint";
	
	@Autowired
	MT103Service MT103Service;
	

	@Autowired
	MT102Service MT102Service;
	
	@Autowired
	NarodnaBankaClient narodnaBankaClient;
	
	@Autowired
	private Environment environment;
	
	@PostConstruct
	public void reinitLogPrefix()
	{
		logPrefix += ":" + environment.getProperty("server.port");
	}
	
	private static final String NAMESPACE_URI = "http://mt102.com";
	private static final String NAMESPACE_URI2 = "http://mt103.com";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMT102Request")
	@ResponsePayload
	public GetMT900Response getNalogZaPlacanje(@RequestPayload GetMT102Request request) {
		MyLogger.log(logPrefix, "getNalogZaPlacanje started");
		GetMT900Response response = new GetMT900Response();
		MT102Service.save(request.getMT102());
		
		MT900 mt900 = new MT900();
		mt900.setIdPoruke((UUID.randomUUID().toString()));
		
		
		mt900.setSwiftKodBankeDuznika(request.getMT102().getZaglavljeMT102().getSwiftKodBankeDuznika());
		mt900.setObracunskiRacunBankeDuznika(request.getMT102().getZaglavljeMT102().getObracunskiRacunBankeDuznika());    
		mt900.setIdPorukeNaloga(request.getMT102().getZaglavljeMT102().getIdPoruke());   
		mt900.setDatumValute(request.getMT102().getZaglavljeMT102().getDatumValute());
		mt900.setIznos(request.getMT102().getZaglavljeMT102().getUkupanIznos());
		mt900.setSifraValute(request.getMT102().getZaglavljeMT102().getSifraValute());
		
		response.setMT900(mt900);
		
		MT910 mt910 = new MT910();
		mt910.setIdPoruke((UUID.randomUUID().toString()));
		mt910.setSwiftKodBankePoverioca(request.getMT102().getZaglavljeMT102().getSwiftKodBankePoverioca());
		mt910.setObracunskiRacunBankePoverioca(request.getMT102().getZaglavljeMT102().getObracunskiRacunBankePoverioca());    
		mt910.setIdPorukeNaloga(request.getMT102().getZaglavljeMT102().getIdPoruke());    
		mt910.setDatumValute(request.getMT102().getZaglavljeMT102().getDatumValute());
		mt910.setIznos(request.getMT102().getZaglavljeMT102().getUkupanIznos());
		mt910.setSifraValute(request.getMT102().getZaglavljeMT102().getSifraValute());
	
		narodnaBankaClient.send910(mt910,null, request.getMT102());
		MyLogger.log(logPrefix, "getNalogZaPlacanje finished");
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI2, localPart = "getMT103Request")
	@ResponsePayload
	public GetMT900Response getMT103(@RequestPayload GetMT103Request request) {
		MyLogger.log(logPrefix, "getMT103 started");
		if(!ValidacijaSema.validirajSemu(request, "mt103")) {
			System.out.println("Nevalidan dokument");
			return null;
		}
		GetMT900Response response = new GetMT900Response();
		
		MT103Service.save(request.getMT103());
		
		MT900 mt900 = new MT900();
		mt900.setIdPoruke((UUID.randomUUID().toString()));
		mt900.setSwiftKodBankeDuznika(request.getMT103().getSwiftKodBankeDuznika());
		mt900.setObracunskiRacunBankeDuznika(request.getMT103().getObracunskiRacunBankeDuznika());    
		mt900.setIdPorukeNaloga(request.getMT103().getIdPoruke());   
		mt900.setDatumValute(request.getMT103().getDatumValute());
		mt900.setIznos(request.getMT103().getIznos());
		mt900.setSifraValute(request.getMT103().getSifraValute());
		
		response.setMT900(mt900);
		
		MT910 mt910 = new MT910();
		mt910.setIdPoruke((UUID.randomUUID().toString()));
		mt910.setSwiftKodBankePoverioca(request.getMT103().getSwiftKodBankePoverioca());
		mt910.setObracunskiRacunBankePoverioca(request.getMT103().getObracunskiRacunBankePoverioca());    
		mt910.setIdPorukeNaloga(request.getMT103().getIdPoruke());    
		mt910.setDatumValute(request.getMT103().getDatumValute());
		mt910.setIznos(request.getMT103().getIznos());
		mt910.setSifraValute(request.getMT103().getSifraValute());
	
		if(!narodnaBankaClient.send910(mt910, request.getMT103(),null)) {
			return null;
		}
		
		
		if(!ValidacijaSema.validirajSemu(response, "mt900")) {
			System.out.println("Nevalidan dokument");
			return null;
		}
		MyLogger.log(logPrefix, "getMT103 finished");
		return response;
	}
}