package com.dmt.beerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmt.beerstore.exception.BeerAlreadyExistsException;
import com.dmt.beerstore.model.entity.BeerEntity;
import com.dmt.beerstore.model.repository.BeerRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;

	public BeerEntity save(final BeerEntity beerEntity) {

		if (beerRepository.findByName(beerEntity.getName()) != null) {
			throw new BeerAlreadyExistsException();
		}

		return beerRepository.save(beerEntity);
	}

}
