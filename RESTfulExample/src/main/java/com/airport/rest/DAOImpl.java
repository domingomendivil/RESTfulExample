package com.airport.rest;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOImpl implements DAO {

	private final static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("testjpa");
	private final static EntityManager em = entityManagerFactory.createEntityManager();

	

	@Override
	public Object getbyId(Class<? extends Object> aClass, Object id) {
		em.getTransaction().begin();
		Object anObject = em.find(aClass,id);
		em.getTransaction().commit();
		return anObject;
	}

	@Override
	public List<? extends Object> getByCriteria(Class<? extends Object> aClass,String criteria,String... params){
		em.getTransaction().begin();
		String sql= "SELECT e FROM "+getClass(aClass)+" e where "+criteria;
		Query query = em.createQuery(sql);
		System.out.println("SQL "+sql);
		for (int i=0;i<params.length;i++){
			query.setParameter(i+1,params[i]);	
		}
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) query.getResultList();
		em.getTransaction().commit();
		return list;
	}
	
	@Override
	public List<? extends Object> getAll(Class<? extends Object> aClass) {
		em.getTransaction().begin();
		String sql= "SELECT e FROM "+getClass(aClass)+" e";
		System.out.println("SQL "+sql);
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) query.getResultList();
		em.getTransaction().commit();
		return list;
	}

	private String getClass(Class<? extends Object> aClass) {
		return aClass.getSimpleName();
	}

	@Override
	public void save(Object object) {
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
	}

	@Override
	public void begin() {
		em.getTransaction().begin();
		
	}

	@Override
	public void commit() {
		em.getTransaction().commit();
	}


}
