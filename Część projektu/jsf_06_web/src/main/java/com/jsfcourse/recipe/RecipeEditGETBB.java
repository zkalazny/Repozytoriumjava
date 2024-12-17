package com.jsfcourse.recipe;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.RecipeDAO;
import com.jsf.entities.Recipe;

@Named
@ViewScoped
public class RecipeEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_RECIPE_LIST = "recipeList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Recipe recipe = new Recipe();
	private Recipe loaded = null;

	@Inject
	FacesContext context;

	@EJB
	RecipeDAO recipeDAO;

	public Recipe getRecipe() {
		return recipe;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && recipe.getIdrecipe() != null) {
				loaded = recipeDAO.find(recipe.getIdrecipe());
			}
			if (loaded != null) {
				recipe = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (recipe.getIdrecipe() == null) {
				// new record
				recipeDAO.create(recipe);
			} else {
				// existing record
				recipeDAO.merge(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_RECIPE_LIST;
	}
}
