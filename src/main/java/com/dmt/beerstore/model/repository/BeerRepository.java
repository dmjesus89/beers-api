package com.dmt.beerstore.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.dmt.beerstore.model.entity.BeerEntity;

public interface BeerRepository extends JpaRepository<BeerEntity, Long> {

	BeerEntity findByName(String name);

}
