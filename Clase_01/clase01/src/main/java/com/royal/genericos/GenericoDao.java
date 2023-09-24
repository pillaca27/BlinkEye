package com.royal.genericos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface GenericoDao<T, ID extends Serializable> {
	public Criteria getCriteria();

	public Criteria getCriteria(Class clazz);

	public ID registrar(T entity);

	public void actualizar(T entity);

	public void eliminar(T entity);

	public void eliminar(List<T> entitys);

	public void registrarOactualizar(T entity);

	public T obtenerPorId(ID id, boolean lock);

	public T obtenerPorId(ID id);

	public T findId(ID key);
	
	public Session getSesionActual();

	public void setSesionActual(Session sessionInterna);

}
