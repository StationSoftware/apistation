package com.station.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	int id;
	String name;
	String code;
	String releaseDate;
	String price;
	String description;
	String starRating;

	public Product(int id, String name, String code, String string, String string2, String description,
			String string3) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.releaseDate = string;
		this.price = string2;
		this.description = description;
		this.starRating = string3;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}
}
