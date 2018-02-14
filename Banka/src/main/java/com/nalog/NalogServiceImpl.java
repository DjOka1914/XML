package com.nalog;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NalogServiceImpl implements NalogService {

	private final NalogRepository nalogRepository;

	@Autowired
	public NalogServiceImpl(final NalogRepository nalogRepository) {
		this.nalogRepository = nalogRepository;
	}

	@Override
	public Nalog findOne(Long id) {
		return nalogRepository.findOne(id);
	}

	@Override
	public Nalog save(Nalog nalog) {
		return nalogRepository.save(nalog);
	}
	
	@Override
	public List<Nalog> findAll() {
		return (List<Nalog>) nalogRepository.findAll();
	}

	@Override
	public Nalog findByIdPoruke(String idPoruke)
	{
		/*Nalog temp = nalogRepository.findByIdPoruke(idPoruke);
		if (temp == null)
			System.out.println("################# pronadjeni nalog je null");
		else
		{
			System.out.println("################# pronadjeni nalog: "
					+ "idPoruke- " + temp.getIdPoruke()
					+ "RacunDuznika- " + temp.getRacunDuznika()
					+ "RacunPrimaoca- " + temp.getRacunPrimaoca());
		}
		return temp;*/
		return nalogRepository.findByIdPoruke(idPoruke);
	}
}
