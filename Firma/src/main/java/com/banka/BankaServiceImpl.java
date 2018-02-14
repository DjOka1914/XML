package com.banka;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankaServiceImpl implements BankaService{
private final BankaRepository bankaRepository;
	
	@Autowired
	public BankaServiceImpl(final BankaRepository bankaRepository) {
		this.bankaRepository = bankaRepository;
	}

	@Override
	public Banka findOne(Long id) {
		return bankaRepository.findOne(id);
	}
	
	@Override
	public List<Banka> findAll() {
		return (List<Banka>) bankaRepository.findAll();
	}

	@Override
	public Banka findByKod(String kod) {
		return bankaRepository.findByKod(kod);
	}
	
}
