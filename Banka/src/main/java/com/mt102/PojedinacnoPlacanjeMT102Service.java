package com.mt102;

import java.util.List;

public interface PojedinacnoPlacanjeMT102Service {

	public List<PojedinacnoPlacanjeMT102> findAll();

	public PojedinacnoPlacanjeMT102 save(PojedinacnoPlacanjeMT102 mt102);

	public PojedinacnoPlacanjeMT102 findOne(Long id);
}
