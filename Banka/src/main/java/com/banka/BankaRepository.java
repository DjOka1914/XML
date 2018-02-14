package com.banka;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.racun.Racun;


public interface BankaRepository extends PagingAndSortingRepository<Banka, Long> {
	
	@Query("select f from Banka f where f.kodBanke=?1")
	Banka findByKod(String kod);
}
