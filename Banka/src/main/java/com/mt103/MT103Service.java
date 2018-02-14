package com.mt103;

import java.util.List;


public interface MT103Service {

	public List<MT103> findAll();

	public MT103 save(MT103 mt103);

	public MT103 findOne(Long id);

	public MT103 findByIdPoruke(String idPorukeNaloga);
}
