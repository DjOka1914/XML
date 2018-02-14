package com.mt102;

import java.util.List;

public interface ZaglavljeMT102Service {

	public List<ZaglavljeMT102> findAll();

	public ZaglavljeMT102 save(ZaglavljeMT102 mt102);

	public ZaglavljeMT102 findOne(Long id);
}
