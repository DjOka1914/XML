package com.banka;

import java.util.List;

public interface BankaService {
	public Banka findOne(Long id);

	public Banka findByKod(String kod);

	public List<Banka> findAll();
}
