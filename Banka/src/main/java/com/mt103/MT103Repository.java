package com.mt103;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MT103Repository  extends PagingAndSortingRepository<MT103, Long> {

	@Query("select r from MT103 r where r.idPoruke = ?1")
	MT103 findByIdPoruke(String idPoruke);

}
