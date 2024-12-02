package com.jsfcourse.game;

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

import com.jsf.dao.GameDAO;
import com.jsf.entities.Game;

@Named
@RequestScoped
public class GameListBB {
	private static final String PAGE_GAME_EDIT = "gameEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	GameDAO gameDAO;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getFullList(){
		return gameDAO.getFullList();
	}

	public List<Game> getList(){
		List<Game> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		list = gameDAO.getList(searchParams);
		
		return list;
	}

	public String newGame(){
		Game game = new Game();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("game", game);
		
		return PAGE_GAME_EDIT;
	}

	public String editGame(Game game){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("game", game);
		
		return PAGE_GAME_EDIT;
	}

	public String deleteGame(Game game){
		gameDAO.remove(game);
		return PAGE_STAY_AT_THE_SAME;
	}
}
