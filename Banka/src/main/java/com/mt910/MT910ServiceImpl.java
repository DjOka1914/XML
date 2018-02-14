package com.mt910;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MT910ServiceImpl  implements MT910Service
{

	private final MT910Repository MT910Repository;
	
	@Autowired
	public MT910ServiceImpl(final MT910Repository MT910Repository) {
		this.MT910Repository = MT910Repository;
		
	}

	@Override
	public List<MT910> findAll() {
		return (List<MT910>) MT910Repository.findAll();
	}

	@Override
	public MT910 save(MT910 mt910) {
		return MT910Repository.save(mt910);
	}

	@Override
	public MT910 findOne(Long id) {
		return MT910Repository.findOne(id);
	}

	@Override
	public MT910 findByidPorukeNaloga(String idPorukeNaloga) {
		return MT910Repository.findByidPorukeNaloga(idPorukeNaloga);
	}
}
