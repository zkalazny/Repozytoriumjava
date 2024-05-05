package com.jsfcourse.db;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import com.jsfcourse.entities.User;

@Named
@RequestScoped
public class UserDAO {

	// simulate finding user in DB
	public User getUserFromDatabase(String login, String pass) {
		
		User u = null;

		if (login.equals("user") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
			u.setName("Jan");
			u.setSurname("Kowalski");
		}

		if (login.equals("moderator") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
			u.setName("Anna");
			u.setSurname("Nowak");
		}

		if (login.equals("administrator") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
			u.setName("Michał");
			u.setSurname("Jaworek");
		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getLogin().equals("user")) {
			roles.add("user");
		}
		if (user.getLogin().equals("moderator")) {
			roles.add("moderator");
		}
		if (user.getLogin().equals("administrator")) {
			roles.add("admin");
		}
		
		return roles;
	}
}
