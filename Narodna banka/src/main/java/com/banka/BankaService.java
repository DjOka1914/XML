package com.banka;


public interface BankaService {
	public Banka findOne(Long id);

	public Banka findBySwiftKod(String swiftKod);

}
