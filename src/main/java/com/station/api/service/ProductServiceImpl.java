package com.station.api.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.station.api.model.Product;
import com.station.api.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {

		List<Product> products = productRepository.findAll();

		if (products.isEmpty()) {
			throw new ValidationException("No products");
		} else {
			return products;
		}
	}

}
