package com.racun;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RacunServiceImpl implements RacunService{

	private final RacunRepository racunRepository;

	@Autowired
	public RacunServiceImpl(final RacunRepository racunRepository) {
		this.racunRepository = racunRepository;
	}

	
	@Override
	public Racun findOne(Long id) {
		return racunRepository.findOne(id);
	}

	@Override
	public List<Racun> findAll() {
		return (List<Racun>) racunRepository.findAll();
	}


	@Override
	public Racun findByBrojRacuna(String brojRacuna) {
		return racunRepository.findByBrojRacuna(brojRacuna);
	}


	@Override
	public Racun save(Racun racun) {
		return racunRepository.save(racun);
	}

}
