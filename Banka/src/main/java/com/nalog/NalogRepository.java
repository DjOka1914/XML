package com.nalog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NalogRepository extends PagingAndSortingRepository<Nalog, Long> {
	@Query("select f from Nalog f where f.idPoruke=?1")
	Nalog findByIdPoruke(String idPoruke);
}
