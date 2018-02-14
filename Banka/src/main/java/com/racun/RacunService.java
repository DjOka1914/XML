package com.racun;

import java.util.List;

public interface RacunService {

	public Racun findOne(Long id);
	
	public List<Racun> findAll();
	
	public Racun findByBrojRacuna(String brojRacuna);
	
	public Racun save(Racun racun);
}
