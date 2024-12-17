package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Recipe;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class RecipeDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Recipe recipe) {
		em.persist(recipe);
	}

	public Recipe merge(Recipe recipe) {
		return em.merge(recipe);
	}

	public void remove(Recipe recipe) {
		em.remove(em.merge(recipe));
	}

	public Recipe find(Object id) {
		return em.find(Recipe.class, id);
	}

	public List<Recipe> getFullList() {
		List<Recipe> list = null;

		Query query = em.createQuery("select p from Recipe p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Recipe> getList(Map<String, Object> searchParams) {
		List<Recipe> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Recipe p ";
		String where = "";
		String orderby = "order by p.name asc";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
