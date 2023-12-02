package net.royal.spring.workflow.dao.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionaprobador;
import net.royal.spring.workflow.dominio.WfTransaccionaprobadorPk;

@Repository
public class WfTransaccionaprobadorDaoImpl extends GenericoDaoImpl<WfTransaccionaprobador, WfTransaccionaprobadorPk> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionaprobadorDaoImpl() {
		super("wftransaccionaprobador");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Integer obtenerSecuencia(Integer transaccionGenerada) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.transaccionid", new BigDecimal(transaccionGenerada)))
				.setProjection(Projections.max("pk.detalleid"));
		BigDecimal calc = (BigDecimal) criteria.uniqueResult();

		if (calc == null) {
			return 1;
		}
		
		Integer calcFinal = calc.intValue();
		
		return calcFinal + 1;
	}

}
