package com.banka;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BankaRepository extends PagingAndSortingRepository<Banka, Long> {

	@Query("select f from Banka f where f.swiftKod=?1")
	Banka findBySwiftKod(String kod);
}
