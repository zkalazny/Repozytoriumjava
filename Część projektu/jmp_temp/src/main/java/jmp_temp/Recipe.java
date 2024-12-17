package jmp_temp;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the recipe database table.
 * 
 */
@Entity
@NamedQuery(name="Recipe.findAll", query="SELECT r FROM Recipe r")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrecipe;

	private String calories;

	private String description;

	private String name;

	private String portion;

	private String temperature;

	public Recipe() {
	}

	public int getIdrecipe() {
		return this.idrecipe;
	}

	public void setIdrecipe(int idrecipe) {
		this.idrecipe = idrecipe;
	}

	public String getCalories() {
		return this.calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortion() {
		return this.portion;
	}

	public void setPortion(String portion) {
		this.portion = portion;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

}