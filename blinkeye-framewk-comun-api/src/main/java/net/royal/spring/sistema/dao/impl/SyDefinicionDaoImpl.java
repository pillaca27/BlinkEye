package net.royal.spring.sistema.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyDefinicion;
import net.royal.spring.sistema.dominio.BeanSyDefinicionPk;

@Repository
public class SyDefinicionDaoImpl extends GenericoDaoImpl<BeanSyDefinicion, BeanSyDefinicionPk> {

	private static final long serialVersionUID = 1L;

	public SyDefinicionDaoImpl() {
		super("sydefinicion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

}
