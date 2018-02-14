package com.zaglavljeFakture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zaglavljeFakture")
public class ZaglavljeFaktureController {

	private final ZaglavljeFaktureService zaglavljeFaktureService;
	
	@Autowired
	public ZaglavljeFaktureController(final ZaglavljeFaktureService zaglavljeFaktureService){
		this.zaglavljeFaktureService = zaglavljeFaktureService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ZaglavljeFakture save(@RequestBody ZaglavljeFakture zaglavljeFakture) {
		return zaglavljeFaktureService.save(zaglavljeFakture);
	}
}
