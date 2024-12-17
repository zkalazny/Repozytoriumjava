package com.jsf.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name = "recipe")
@NamedQuery(name = "Recipe.findAll", query = "SELECT p FROM Recipe p")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idrecipe;

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String calories;
	
	@Column(length = 45)
	private String portion;
	
	@Column(length = 45)
	private String temperature;
	
	@Column(length = 45)
	private String description;


	public void log(String text) {
		System.out.println(text + ": [" + idrecipe + "], " + name + ", " + description + ", " + calories + ", " + portion + ", " + temperature);
	}
	
	public Recipe() {
	}

	public Integer getIdrecipe() {
		return this.idrecipe;
	}

	public void setIdrecipe(Integer idrecipe) {
		this.idrecipe = idrecipe;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPortion() {
		return this.portion;
	}

	public void setPortion(String portion) {
		this.portion = portion;
	}
	
	public String getCalories() {
		return this.calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}
	
	public String getTemperature() {
		return this.description;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}