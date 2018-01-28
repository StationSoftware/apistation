package com.station.api.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.station.api.model.Product;
import com.station.api.repository.ProductRepository;
import com.station.api.service.ProductServiceImpl;
import com.station.api.testutil.TestUtil;

public class ProductServiceImplTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private TestUtil testUtil = new TestUtil();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUserService_getAllUserProfiles_ReturningProfiles() {
				
		List<Product> mockProducts = testUtil.getMockProducts();
		when(productRepository.findAll()).thenReturn(mockProducts);
		
		List<Product> testProducts
		= productService.getAllProducts();
		
		verify(productRepository).findAll();
		assertThat(testProducts,is(mockProducts));
	}
	
	@Test
	public void testUserService_getAllUserProfiles_EmptyProfiles() {
				
		List<Product> mockEmptyProducts = testUtil.getMockEmptyProducts();
		when(productRepository.findAll()).thenReturn(mockEmptyProducts);
		
		thrown.expect(ValidationException.class);
		thrown.expectMessage("No products");
		
		productService.getAllProducts();
	}

}
