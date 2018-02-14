package com.stavkaFakture;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class StavkaFaktureServiceImpl implements StavkaFaktureService {

	
	private final StavkaFaktureRepository stavkaFaktureRepository;
	
	@Autowired
	public StavkaFaktureServiceImpl(final StavkaFaktureRepository stavkaFaktureRepository) {
		this.stavkaFaktureRepository = stavkaFaktureRepository;
	}
	@Override
	public List<StavkaFakture> findAll() {
		return (List<StavkaFakture>) stavkaFaktureRepository.findAll();
	}

	@Override
	public StavkaFakture save(StavkaFakture stavka) {
		return stavkaFaktureRepository.save(stavka);
	}

	@Override
	public StavkaFakture findOne(Long id) {
		return stavkaFaktureRepository.findOne(id);
	}

}
