package com.firmas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FirmasRepository extends PagingAndSortingRepository<Firmas, Long> {

	@Query("select r from Firmas r where r.mail = ?1 and r.password = ?2")
	public Firmas findByMailAndPassword(String mail, String password);

}
