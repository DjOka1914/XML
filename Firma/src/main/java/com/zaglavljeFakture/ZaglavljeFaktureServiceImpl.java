package com.zaglavljeFakture;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ZaglavljeFaktureServiceImpl implements ZaglavljeFaktureService{

	
	private final ZaglavljeFaktureRepository zaglavljeFaktureRepository;
	
	@Autowired
	public ZaglavljeFaktureServiceImpl(final ZaglavljeFaktureRepository zaglavljeFaktureRepository) {
		this.zaglavljeFaktureRepository = zaglavljeFaktureRepository;
	}
	
	@Override
	public List<ZaglavljeFakture> findAll() {
		return (List<ZaglavljeFakture>) zaglavljeFaktureRepository.findAll();
	}

	@Override
	public ZaglavljeFakture save(ZaglavljeFakture zaglavlje) {
		return zaglavljeFaktureRepository.save(zaglavlje);
	}

	@Override
	public ZaglavljeFakture findOne(Long id) {
		return zaglavljeFaktureRepository.findOne(id);
	}

}
