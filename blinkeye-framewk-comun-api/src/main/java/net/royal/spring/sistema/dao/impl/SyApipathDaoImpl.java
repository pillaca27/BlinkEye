package net.royal.spring.sistema.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyApipath;
import net.royal.spring.sistema.dominio.BeanSyApipathPk;

@Repository
public class SyApipathDaoImpl extends GenericoDaoImpl<BeanSyApipath, BeanSyApipathPk> {

	private static final long serialVersionUID = 1L;

	public SyApipathDaoImpl() {
		super("syapipath");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

}
