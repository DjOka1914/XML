package com.mt102;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ZaglavljeMT102ServiceImpl implements ZaglavljeMT102Service{

	private final ZaglavljeMT102Repository zaglavljeMT102Repository;
	
	@Autowired
	public ZaglavljeMT102ServiceImpl(final ZaglavljeMT102Repository zaglavljeMT102Repository) {
		this.zaglavljeMT102Repository = zaglavljeMT102Repository;
	}
	@Override
	public List<ZaglavljeMT102> findAll() {
		return (List<ZaglavljeMT102>) zaglavljeMT102Repository.findAll();
	}

	@Override
	public ZaglavljeMT102 save(ZaglavljeMT102 mt102) {
		return zaglavljeMT102Repository.save(mt102);
	}

	@Override
	public ZaglavljeMT102 findOne(Long id) {
		return zaglavljeMT102Repository.findOne(id);
	}

}
