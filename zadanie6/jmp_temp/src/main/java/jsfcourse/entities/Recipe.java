package jsfcourse.entities;

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

	private int calories;

	private String image;

	private String name;

	private int portion;

	private String preparation;

	private String temperature;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="useriduser")
	private User user;

	public Recipe() {
	}

	public int getIdrecipe() {
		return this.idrecipe;
	}

	public void setIdrecipe(int idrecipe) {
		this.idrecipe = idrecipe;
	}

	public int getCalories() {
		return this.calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPortion() {
		return this.portion;
	}

	public void setPortion(int portion) {
		this.portion = portion;
	}

	public String getPreparation() {
		return this.preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public String getTemperature() {
		return this.temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}