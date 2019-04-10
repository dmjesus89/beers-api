package com.dmt.beerstore.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dmt.beerstore.error.ErrorResponse;
import com.dmt.beerstore.model.entity.BeerEntity;
import com.dmt.beerstore.model.repository.BeerRepository;

@RestController
@RequestMapping("/beers")
public class BeerResource {

	@Autowired
	private BeerRepository beerRepository;

	@GetMapping
	public List<BeerEntity> getAll() {
		List<BeerEntity> listBeer = beerRepository.findAll();
		return listBeer;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BeerEntity save(@Valid @RequestBody BeerEntity beerEntity) {
		beerRepository.save(beerEntity);
		return beerEntity;
	}

	@PutMapping
	public void update(@Valid @RequestBody BeerEntity beerEntity) {
		beerRepository.save(beerEntity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		beerRepository.deleteById(id);
	}

}
