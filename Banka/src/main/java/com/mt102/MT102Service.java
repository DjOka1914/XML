package com.mt102;

import java.util.List;


public interface MT102Service {

	public List<MT102> findAll();

	public MT102 save(MT102 mt102);

	public MT102 findOne(Long id);
}
