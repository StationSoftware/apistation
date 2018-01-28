package com.station.api.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.station.api.controller.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomeController() {
		HomeController hc = new HomeController();
		String result = hc.home();
		assertEquals( result, "OAS, reporting for duty!" );
	}
}
