package com.station.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.station.api.model.Product;
import com.station.api.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

}
