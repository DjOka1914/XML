package com.faktura;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface FakturaRepository extends PagingAndSortingRepository<Faktura, Long> {
	
	Faktura findByZaglavljeFakture_Id(Long id); 
	
	List<Faktura> findByZaglavljeFakture_PibKupca(String pibKupca);
	
	List<Faktura> findByZaglavljeFakture_PibDobavljaca(String pibDobavljaca);

}
