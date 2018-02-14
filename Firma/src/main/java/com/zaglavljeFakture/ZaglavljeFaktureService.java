package com.zaglavljeFakture;

import java.util.List;

public interface ZaglavljeFaktureService {

	public List<ZaglavljeFakture> findAll();

	public ZaglavljeFakture save(ZaglavljeFakture zaglavlje);

	public ZaglavljeFakture findOne(Long id);
}
