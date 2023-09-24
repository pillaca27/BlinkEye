package com.royal.genericos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import java.sql.Timestamp;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.Connection;

import org.springframework.transaction.annotation.Transactional;

import com.royal.util.DominioMensajeUsuario;
import com.royal.util.DominioMensajeUsuario.tipo_mensaje;
import com.royal.util.DominioParametroPersistencia;
import com.royal.util.UString;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.orm.hibernate5.HibernateTemplate;

public abstract class GenericoDaoImpl<T, ID extends Serializable> extends HibernateTemplate
		implements GenericoDao<T, ID>, Serializable {

	private static final long serialVersionUID = 8704138686206423411L;
	private Class<T> persistentClass;
	private String nombreAlias;
	private Session sessionInterna;

	public GenericoDaoImpl(String nombreAlias) {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.nombreAlias = nombreAlias;
	}

	public GenericoDaoImpl(SessionFactory factory, String nombreAlias) {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		super.setSessionFactory(factory);
		this.nombreAlias = nombreAlias;
	}

	public T findId(ID key) {
		return (T) this.getSesionActual().get(persistentClass, key);
	}

	public ID registrar(T entity) {
		return (ID) this.getSesionActual().save(entity);
	}

	public GenericoDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Criteria getCriteria() {
		return this.getSesionActual().createCriteria(persistentClass);
	}

	public Criteria getCriteria(Class clazz) {
		return this.getSesionActual().createCriteria(clazz);
	}

	public void actualizar(T entity) {
		this.getSesionActual().update(entity);
	}

	public void eliminar(T entity) {
		this.getSesionActual().delete(entity);
	}

	public List<T> listarPorCriterios(Criteria criteria) {
		return criteria.list();
	}

	public void registrarOactualizar(T entity) {
		this.getSesionActual().saveOrUpdate(entity);
	}

	@Transactional(readOnly = true)
	public T obtenerPorId(ID id, boolean lock) {
		T entity = null;
		try {
			if (lock) {
				entity = (T) this.getSesionActual().get(persistentClass, id, LockMode.UPGRADE);
			} else {
				entity = (T) this.getSesionActual().get(persistentClass, id);
			}
		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
		return entity;
	}

	@Transactional(readOnly = true)
	public T obtenerPorId(ID id) {
		return this.obtenerPorId(id, true);
	}

	@Override
	public void eliminar(List<T> entitys) {
		// TODO Auto-generated method stub

	}
	
	@Transactional(readOnly = true)
	public List<Class> listarPorQuery(Class clazz, String nombreQuery) {

		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}
	
	@Transactional(readOnly = true)
	public List<Class> listarPorQuery(Class clazz, String nombreQuery, List<DominioParametroPersistencia> parameters) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parameters);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}
	
	public Session getSesionActual() {
		if (sessionInterna == null) {
			return super.getSessionFactory().getCurrentSession();
		} else {
			return sessionInterna;
		}
	}

	public void setSesionActual(Session sessionInterna) {
		this.sessionInterna = sessionInterna;
	}

	public Connection getConnection() {
		return ((SessionImpl) this.getSesionActual()).connection();
	}
	
	@Autowired
	private MessageSource messageSource;
	
	public DominioMensajeUsuario getMsjUsuarioError(String msgCode) {
		String msg = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}
	public String getMessage(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, null, locale);
	}
	private String getMessageBase(String msgCode, Object[] params, String defaultMsg, Locale locale) {
		try {
			if (locale == null)
				locale = LocaleContextHolder.getLocale();
			defaultMsg = UString.estaVacio(defaultMsg) ? msgCode : defaultMsg;
			String msg = messageSource.getMessage(msgCode, params, defaultMsg, locale);
			return UString.estaVacio(msg) ? msgCode : msg;
		} catch (Exception e) {
			logger.error(msgCode);
		}
		return msgCode;
	}
}
