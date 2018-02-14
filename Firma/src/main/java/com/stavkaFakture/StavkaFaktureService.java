package com.stavkaFakture;

import java.util.List;

public interface StavkaFaktureService {


	public List<StavkaFakture> findAll();

	public StavkaFakture save(StavkaFakture zaglavlje);

	public StavkaFakture findOne(Long id);
}
