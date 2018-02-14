package com.firma;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.firmas.FirmasService;
import com.presek.GetPresekResponse;
import com.presek.Presek;
import com.zahtevzadobijanjeizvoda.ZahtevZaDobijanjeIzvoda;


@RestController
@RequestMapping("/firma")
public class FirmaController {
	
	@Autowired
	private FirmaService firmaService;

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	FirmasService firmasService;
	
	@Autowired
	FirmaClient firmaClient;
	
	
	@Autowired
	public FirmaController(final FirmaService firmaService){
		this.firmaService = firmaService;
	}
	
	
	@GetMapping("/findAllPoslovniSaradnici/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Firma> findAllPoslovniSaradnici(@PathVariable Long id) {
		List<Firma> saradnici = firmaService.findOne(id).getPoslovniSaradnici();
		
		return saradnici;
	}
	
	@PostMapping("/findPresek")
	@ResponseStatus(HttpStatus.OK)
	public Presek findPreseke(@RequestBody ZahtevZaDobijanjeIzvoda zahtev) {
		GetPresekResponse response = firmaClient.findPreseke(zahtev);
		if(response != null) {
			return response.getPresek();
		}
		else {
			return null;
		}
	}
	
	
	
	
	
}
