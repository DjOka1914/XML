package com.mt103;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MT103ServiceImpl implements MT103Service {

	private final MT103Repository MT103Repository;
	
	@Autowired
	public MT103ServiceImpl(final MT103Repository MT103Repository) {
		this.MT103Repository = MT103Repository;
	}

	
	@Override
	public List<MT103> findAll() {
		return (List<MT103>) MT103Repository.findAll();
	}

	@Override
	public MT103 findOne(Long id) {
		return MT103Repository.findOne(id);
	}


	@Override
	public MT103 save(MT103 mt103) {
		return MT103Repository.save(mt103);
	}


	@Override
	public MT103 findByIdPoruke(String idPorukeNaloga) {
		return MT103Repository.findByIdPoruke(idPorukeNaloga);
	}

}
