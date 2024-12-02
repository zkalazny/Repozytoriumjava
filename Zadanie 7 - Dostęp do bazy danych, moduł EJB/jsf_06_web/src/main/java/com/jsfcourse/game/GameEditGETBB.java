package com.jsfcourse.game;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.GameDAO;
import com.jsf.entities.Game;

@Named
@ViewScoped
public class GameEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_GAME_LIST = "gameList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Game game = new Game();
	private Game loaded = null;

	@Inject
	FacesContext context;

	@EJB
	GameDAO gameDAO;

	public Game getGame() {
		return game;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && game.getIdgame() != null) {
				loaded = gameDAO.find(game.getIdgame());
			}
			if (loaded != null) {
				game = loaded;
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
