package com.firma;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FirmaRepository  extends PagingAndSortingRepository<Firma, Long>{
	List<Firma> findByPoslovniSaradnici(List<Firma> firma);
	
	Firma findByPoslovniSaradnici_PIB(String pib);
	
	@Query("select f from Firma f where f.PIB=?1")
	Firma findByPIB(String pib);
}
