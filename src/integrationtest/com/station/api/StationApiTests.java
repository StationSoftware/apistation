package com.station.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.station.api.controller.HomeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StationApiTests {

	@Test
	public void contextLoads() {
		HomeController hc = new HomeController();
		String result = hc.home();
		assertEquals( result, "OAS, reporting for duty!" );
	}

}
