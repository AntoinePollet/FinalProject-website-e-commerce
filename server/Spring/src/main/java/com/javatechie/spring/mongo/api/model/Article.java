package com.javatechie.spring.mongo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "articles")
public class Article {
	@Id
	private String id;
	private String name;
	private String description;
	private List<String> pictures;
	private Boolean favorites;
	private String category;
	private String color;
	private int price;
	private Double rating;
	private Boolean inStock;

	public Article() {
	}

	public Article(String name, String description, List<String> pictures, Boolean favorites, String category, String color, int price, Double rating, Boolean inStock) {
		this.name = name;
		this.description = description;
		this.pictures = pictures;
		this.favorites = favorites;
		this.category = category;
		this.color = color;
		this.price = price;
		this.rating = rating;
		this.inStock = inStock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public Boolean getFavorites() {
		return favorites;
	}

	public void setFavorites(Boolean favorites) {
		this.favorites = favorites;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getStock() {
		return inStock;
	}

	public void setStock(Boolean inStock) {
		this.inStock = inStock;
	}



	@Override
	public String toString() {
		return "Employee{" +
				", description='" + description + '\'' +
				", pictures='" + pictures + '\'' +
				", category='" + category + '\'' +
				", color='" + color + '\'' +
				", price=" + price +
				", rating=" + rating +
				", inStock=" + inStock +
				'}';
	}
}


