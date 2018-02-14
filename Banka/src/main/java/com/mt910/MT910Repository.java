package com.mt910;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mt103.MT103;

public interface MT910Repository extends PagingAndSortingRepository<MT910, Long> {

	@Query("select r from MT910 r where r.idPorukeNaloga = ?1")
	MT910 findByidPorukeNaloga(String idPoruke);
}
