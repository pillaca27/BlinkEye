package net.royal.spring.contabilidad.dao.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.contabilidad.dominio.BeanVoucherdetail;
import net.royal.spring.contabilidad.dominio.BeanVoucherdetailPk;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class VoucherdetailComunDaoImpl extends GenericoDaoImpl<BeanVoucherdetail, BeanVoucherdetailPk> {

	private static Logger logger = LogManager.getLogger(BeanVoucherdetail.class);

	public VoucherdetailComunDaoImpl() {
		super("voucherdetail");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanVoucherdetail coreInsertar(BeanVoucherdetail bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanVoucherdetail coreActualizar(BeanVoucherdetail bean) {
		this.actualizar(bean);
		return bean;
	}

}
