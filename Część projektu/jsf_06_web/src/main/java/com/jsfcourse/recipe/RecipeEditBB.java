package com.jsfcourse.recipe;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RecipeDAO;
import com.jsf.entities.Recipe;

@Named
@ViewScoped
public class RecipeEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_RECIPE_LIST = "recipeList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Recipe recipe = new Recipe();
	private Recipe loaded = null;

	@EJB
	RecipeDAO recipeDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Recipe getRecipe() {
		return recipe;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Recipe) flash.get("recipe");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			recipe = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
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
