package com.mt102;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PojedinacnoPlacanjeMT102ServiceImpl implements PojedinacnoPlacanjeMT102Service{
	
	private final PojedinacnoPlacanjeMT102Repository pojedinacnoPlacanjeMT102Repository;
	
	
	@Autowired
	public PojedinacnoPlacanjeMT102ServiceImpl(final PojedinacnoPlacanjeMT102Repository pojedinacnoPlacanjeMT102Repository) {
		this.pojedinacnoPlacanjeMT102Repository = pojedinacnoPlacanjeMT102Repository;
		
	}


	@Override
	public List<PojedinacnoPlacanjeMT102> findAll() {
		return (List<PojedinacnoPlacanjeMT102>) pojedinacnoPlacanjeMT102Repository.findAll();
	}


	@Override
	public PojedinacnoPlacanjeMT102 save(PojedinacnoPlacanjeMT102 mt102) {
		return pojedinacnoPlacanjeMT102Repository.save(mt102);
	}


	@Override
	public PojedinacnoPlacanjeMT102 findOne(Long id) {
		return pojedinacnoPlacanjeMT102Repository.findOne(id);
	}
}
