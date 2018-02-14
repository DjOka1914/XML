package com.temp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.nalog.Nalog;
import com.presek.ZaglavljePreseka;

public class NaloziZaglavlje {
	public List<Nalog> lista = new ArrayList<Nalog>();
	public ZaglavljePreseka zaglavlje = new ZaglavljePreseka();
	
	//to do constructor to init all
	public NaloziZaglavlje() {
		zaglavlje.setBrojPreseka(BigInteger.valueOf(0));
		zaglavlje.setBrojPromenaNaTeret(BigInteger.valueOf(0));
		zaglavlje.setBrojPromenaUKorist(BigInteger.valueOf(0));
		zaglavlje.setNovoStanje(BigDecimal.valueOf(0));
		zaglavlje.setPrethodnoStanje(BigDecimal.valueOf(0));
		zaglavlje.setUkupnoNaTeret(BigDecimal.valueOf(0));
		zaglavlje.setUkupnoUKorist(BigDecimal.valueOf(0));
	}
}
