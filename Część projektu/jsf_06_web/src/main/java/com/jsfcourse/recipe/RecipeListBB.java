package com.jsfcourse.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RecipeDAO;
import com.jsf.entities.Recipe;

@Named
@RequestScoped
public class RecipeListBB {
	private static final String PAGE_RECIPE_EDIT = "recipeEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RecipeDAO recipeDAO;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getFullList(){
		return recipeDAO.getFullList();
	}

	public List<Recipe> getList(){
		List<Recipe> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = recipeDAO.getList(searchParams);
		
		return list;
	}

	public String newRecipe(){
		Recipe recipe = new Recipe();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("recipe", recipe);
		
		return PAGE_RECIPE_EDIT;
	}

	public String editRecipe(Recipe recipe){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("recipe", recipe);
		
		return PAGE_RECIPE_EDIT;
	}

	public String deleteRecipe(Recipe recipe){
		recipeDAO.remove(recipe);
		return PAGE_STAY_AT_THE_SAME;
	}
}
