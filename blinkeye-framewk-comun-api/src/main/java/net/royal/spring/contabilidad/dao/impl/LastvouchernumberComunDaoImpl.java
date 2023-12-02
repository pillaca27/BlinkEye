package net.royal.spring.contabilidad.dao.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.contabilidad.dominio.BeanLastvouchernumber;
import net.royal.spring.contabilidad.dominio.BeanLastvouchernumberPk;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class LastvouchernumberComunDaoImpl extends GenericoDaoImpl<BeanLastvouchernumber, BeanLastvouchernumberPk> {

	private static Logger logger = LogManager.getLogger(BeanLastvouchernumber.class);

	public LastvouchernumberComunDaoImpl() {
		super("lastvouchernumber");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanLastvouchernumber coreActualizar(BeanLastvouchernumber bean) {
		this.actualizar(bean);
		return bean;
	}

	public BeanLastvouchernumber coreInsertar(BeanLastvouchernumber bean) {
		this.registrar(bean);
		return bean;
	}
}
