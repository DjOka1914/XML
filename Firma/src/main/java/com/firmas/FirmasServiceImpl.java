package com.firmas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FirmasServiceImpl implements FirmasService{

	private final FirmasRepository firmasRepository;
	
	@Autowired
	public FirmasServiceImpl(final FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}
	
	@Override
	public Firmas findOneByMailAndPassword(String mail, String password) {
		return firmasRepository.findByMailAndPassword(mail, password);
	}
	
	@Override
	public Firmas findOneById(Long id) {
		return firmasRepository.findOne(id);
	}

	@Override
	public Firmas save(Firmas firmas) {
		return firmasRepository.save(firmas);
	}
}
