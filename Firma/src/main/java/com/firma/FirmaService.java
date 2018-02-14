package com.firma;

import java.util.List;



public interface FirmaService {

	public List<Firma> findAll();
	
	public Firma save(Firma faktura);
	
	public Firma findOne(Long id);
	
	public void delete(Long id);
	
	List<Firma> findByPoslovniSaradnici(List<Firma> firma);
	
	Firma findByPoslovniSaradnici_PIB(String pib);
	
	Firma findByPIB(String pib);
}
