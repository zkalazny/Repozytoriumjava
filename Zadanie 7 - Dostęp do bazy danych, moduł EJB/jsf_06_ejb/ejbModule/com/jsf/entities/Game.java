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
@Table(name = "game")
@NamedQuery(name = "Game.findAll", query = "SELECT p FROM Game p")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idgame;

	@Temporal(TemporalType.DATE)
	private Date whencreated;

	@Column(length = 45)
	private String name;

	@Column(length = 45)
	private String category;

	public void log(String text) {
		System.out.println(text + ": [" + idgame + "], " + name + ", " + category + ", " + whencreated);
	}
	
	public Game() {
	}

	public Integer getIdgame() {
		return this.idgame;
	}

	public void setIdgame(Integer idgame) {
		this.idgame = idgame;
	}

	public Date getWhencreated() {
		return this.whencreated;
	}

	public void setWhencreated(Date whencreated) {
		this.whencreated = whencreated;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}