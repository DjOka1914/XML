package com.nalog;

import java.util.List;

public interface NalogService {
	public Nalog findOne(Long id);
	

	public Nalog save(Nalog nalog);


	public List<Nalog> findAll();

	
	public Nalog findByIdPoruke(String idPoruke);
}
