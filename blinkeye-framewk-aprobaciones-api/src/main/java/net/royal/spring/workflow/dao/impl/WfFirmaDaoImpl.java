package net.royal.spring.workflow.dao.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfFirma;
import net.royal.spring.workflow.dominio.WfFirmaPk;

@Repository
public class WfFirmaDaoImpl extends GenericoDaoImpl<WfFirma, WfFirmaPk> {

	private static final long serialVersionUID = 1L;

	public WfFirmaDaoImpl() {
		super("wffirma");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BigDecimal generarId() {
		Criteria criteria = getCriteria().setProjection(Projections.max("pk.idFirma"));
		BigDecimal calc = (BigDecimal) criteria.uniqueResult();

		if (calc == null) {
			return new BigDecimal(1);
		}
		return new BigDecimal(calc.intValue() + 1);
	}

}
