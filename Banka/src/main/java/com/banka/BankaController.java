package com.banka;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyLogger;
import com.config.BankaClient;
import com.mt102.MT102;
import com.mt102.MT102Service;
import com.mt102.PojedinacnoPlacanjeMT102;
import com.mt103.MT103Service;
import com.mt900.MT900;
import com.nalog.Nalog;
import com.nalog.NalogService;
import com.racun.Racun;
import com.racun.RacunService;


@RestController
@RequestMapping("/banka")
public class BankaController {
	private String logPrefix = "BankaController";

	@Autowired
	BankaClient bankaClient;
	
	@Autowired
	Environment environment;
	
	@Autowired
	RacunService racunService;

	@Autowired
	BankaService bankaService;
	

	@Autowired
	NalogService nalogService;
	
	private final MT102Service MT102Service;
	private final MT103Service MT103Service;
	
	
	@Autowired
	public BankaController(final MT102Service MT102Service,final MT103Service MT103Service){
		this.MT102Service = MT102Service;
		this.MT103Service = MT103Service;
	}
	
	@PostConstruct
	public void reinitLogPrefix()
	{
		logPrefix += ":" + environment.getProperty("server.port");
	}
	
	@GetMapping("/posaljiMT102")
	public void obradiMt102(){	
		MyLogger.log(logPrefix, "obradiMt102 started");
		String port = environment.getProperty("server.port");
		List<Banka> banke = bankaService.findAll();
		Banka trenutnaBanka  = null;
		for (Banka banka : banke) {
			if(banka.getUri().contains(port)){
				trenutnaBanka = banka;
			}
		}
		
		
		List<MT102> sveMT102poruke = MT102Service.findAll();
		List<MT102> mt102porukeBanke = new ArrayList<MT102>();
		
		for(MT102 mt102 : sveMT102poruke){
			if(mt102.getZaglavljeMT102().getSwiftKodBankeDuznika().equals(trenutnaBanka.getSwiftKod())){
				mt102porukeBanke.add(mt102);
			}
		}		
	
		for(int j= 0 ; j<mt102porukeBanke.size() ;j++ ){
			MT102 mt102 = mt102porukeBanke.get(j);
			MT900 mt900 = bankaClient.sendMT102(mt102);
			
			//kada se obradi mt102 potrebno je sa svakog racuna duznika skinuti novac
			for(int i = 0; i< mt102.getPojedinacnoPlacanjeMT102().size(); i++){
				PojedinacnoPlacanjeMT102 placanje = mt102.getPojedinacnoPlacanjeMT102().get(i);
				trenutnaBanka.getObracunskiRacun().setTrenutnoStanje(trenutnaBanka.getObracunskiRacun().getTrenutnoStanje().subtract(placanje.getIznos()));
				List<Nalog> listaNaloga = nalogService.findAll();
				for (Nalog nalog : listaNaloga) {
					if(nalog.getIdPoruke().equals(placanje.getIdNalogaZaPlacanje())) {
						nalog.setObradjen(true);
						nalogService.save(nalog);						
					}
				}
				
				String brojRacunaDuznika = placanje.getRacunDuznika();
				Racun racunDuznika = racunService.findByBrojRacuna(brojRacunaDuznika);
				
				MathContext mc = new MathContext(2);
				racunDuznika.setTrenutnoStanje(racunDuznika.getTrenutnoStanje().subtract(racunDuznika.getRezervisano().subtract(placanje.getIznos()),mc) );
				
				racunDuznika.setRezervisano(racunDuznika.getRezervisano().subtract(placanje.getIznos()));
				racunService.save(racunDuznika);
			}
			
			mt102.setStatus(true); //mt102 postaje obradjen
			MT102Service.save(mt102);
		}
		
		System.out.println("Obradjena sva pojedinacna placanja");	
		MyLogger.log(logPrefix, "obradiMt102 finished");
	}
	
	
	
	
}
