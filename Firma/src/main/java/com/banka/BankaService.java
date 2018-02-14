package com.banka;

import java.util.List;

import com.firma.Firma;

public interface BankaService {
	public Banka findOne(Long id);

	public List<Banka> findAll();
	
	Banka findByKod(String kod);

}
