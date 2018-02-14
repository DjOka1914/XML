package com.faktura;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavkaFakture.StavkaFakture;
import com.stavkaFakture.StavkaFaktureRepository;
import com.zaglavljeFakture.ZaglavljeFakture;
import com.zaglavljeFakture.ZaglavljeFaktureRepository;

@Service
@Transactional
public class FakturaServiceImpl implements FakturaService {

	private final FakturaRepository fakturaRepository;
	private final ZaglavljeFaktureRepository zaglavljeFaktureRepository;
	private final StavkaFaktureRepository stavkaFaktureRepository;
	
	@Autowired
	public FakturaServiceImpl(final FakturaRepository fakturaRepository, final ZaglavljeFaktureRepository zaglavljeFaktureRepository, final StavkaFaktureRepository stavkaFaktureRepository) {
		this.fakturaRepository = fakturaRepository;
		this.zaglavljeFaktureRepository = zaglavljeFaktureRepository;
		this.stavkaFaktureRepository = stavkaFaktureRepository;
	}
	
	@Override
	public List<Faktura> findAll() {
		return (List<Faktura>) fakturaRepository.findAll();
	}

	@Override
	public Faktura save(Faktura faktura) {
		ZaglavljeFakture zaglavlje = zaglavljeFaktureRepository.save(faktura.getZaglavljeFakture());
		faktura.setZaglavljeFakture(zaglavlje);
		
		
		for(int i = 0 ;i <faktura.getStavkaFakture().size(); i++){
			StavkaFakture stavka = stavkaFaktureRepository.save(faktura.getStavkaFakture().get(i));
			faktura.getStavkaFakture().set(i,stavka);			
		}
		
		return fakturaRepository.save(faktura);
	}

	@Override
	public Faktura findOne(Long id) {
		return fakturaRepository.findOne(id);
	}
	@Override
	public List<Faktura> findByZaglavljeFakture_PibKupca(String pibKupca) {
		// TODO Auto-generated method stub
		return fakturaRepository.findByZaglavljeFakture_PibKupca(pibKupca);
	}
	
	@Override
	public Faktura findByZaglavljeFakture_Id(Long id) {
		return fakturaRepository.findByZaglavljeFakture_Id(id);
	}

	@Override
	public void delete(Long id) {

		fakturaRepository.delete(id);
	}

	@Override
	public List<Faktura> findByZaglavljeFakture_PibDobavljaca(String pibDobavljaca) {
		// TODO Auto-generated method stub
		return fakturaRepository.findByZaglavljeFakture_PibDobavljaca(pibDobavljaca);
	}


}
