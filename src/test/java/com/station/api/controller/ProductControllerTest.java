package com.station.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.station.api.controller.ProductController;
import com.station.api.model.Product;
import com.station.api.service.ProductService;
import com.station.api.testutil.TestUtil;

public class ProductControllerTest {
	
	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;
	
	public TestUtil testUtil = new TestUtil();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProductControllerList() {
				
		List<Product> mockProducts = testUtil.getMockProducts();
		when(productService.getAllProducts()).thenReturn(mockProducts);
		
		ResponseEntity<List<Product>> testResponseEntity
		= productController.list();
		
		verify(productService).getAllProducts();
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat(testResponseEntity.getBody(), is(mockProducts));
	}

}
