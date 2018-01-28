package com.station.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.station.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
