package com.firmas;

public interface FirmasService {
	public Firmas findOneByMailAndPassword(String mail, String password);

	public Firmas findOneById(Long id);

	public Firmas save(Firmas admin);
}
