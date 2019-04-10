package com.dmt.beerstore;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.MatcherAssert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dmt.beerstore.exception.BeerAlreadyExistsException;
import com.dmt.beerstore.model.entity.BeerEntity;
import com.dmt.beerstore.model.entity.BeerTypeEnum;
import com.dmt.beerstore.service.BeerService;

public class BeerServiceTest {

	@Before
	public void setUp() {
		beerService = new BeerService();
	}

	private BeerService beerService;

	@Test(expected = BeerAlreadyExistsException.class)
	public void should_deny_save_beer_thant_exits() {

		BeerEntity beerEntity = new BeerEntity();
		beerEntity.setName("Heineken");
		beerEntity.setType(BeerTypeEnum.PILSEN);
		beerEntity.setVolume(new BigDecimal(10));
		beerService.save(beerEntity);
	}

	@Test()
	public void should_save_new_beer() {
		BeerEntity beerEntity = new BeerEntity();
		beerEntity.setName("Heineken");
		beerEntity.setType(BeerTypeEnum.PILSEN);
		beerEntity.setVolume(new BigDecimal(10));
		beerService.save(beerEntity);
		Assert.assertNotNull(beerEntity.getId());
		Assert.assertEquals("Heineken", beerEntity.getName());

	}

}
