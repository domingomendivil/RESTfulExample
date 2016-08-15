package com.airport.rest;

import java.util.List;

public interface DAO {
	
	public void begin();
	
	public void commit();
	
	public void save(Object object);
	
	public Object getbyId(Class<? extends Object> aClass,Object id);

	public List<? extends Object> getAll(Class<? extends Object> aClass);

	public List<? extends Object> getByCriteria(Class<? extends Object> aClass,String criteria,String... params);

}
