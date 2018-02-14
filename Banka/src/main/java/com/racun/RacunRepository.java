package com.racun;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.banka.Banka;

public interface RacunRepository extends PagingAndSortingRepository<Racun, Long> {

	@Query("select f from Racun f where f.brojRacuna=?1")
	Racun findByBrojRacuna(String brojRacuna);
}
