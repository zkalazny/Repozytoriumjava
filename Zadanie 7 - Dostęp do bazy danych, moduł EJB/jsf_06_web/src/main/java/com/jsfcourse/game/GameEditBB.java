package com.jsfcourse.game;

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

import com.jsf.dao.GameDAO;
import com.jsf.entities.Game;

@Named
@ViewScoped
public class GameEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_GAME_LIST = "gameList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Game game = new Game();
	private Game loaded = null;

	@EJB
	GameDAO gameDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Game getGame() {
		return game;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Game) flash.get("game");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			game = loaded;
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
			if (game.getIdgame() == null) {
				// new record
				gameDAO.create(game);
			} else {
				// existing record
				gameDAO.merge(game);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_GAME_LIST;
	}
}
