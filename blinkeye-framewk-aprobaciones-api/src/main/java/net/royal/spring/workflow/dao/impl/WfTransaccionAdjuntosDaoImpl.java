package net.royal.spring.workflow.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionAdjuntos;
import net.royal.spring.workflow.dominio.WfTransaccionAdjuntosPk;


@Repository
public class WfTransaccionAdjuntosDaoImpl extends GenericoDaoImpl<WfTransaccionAdjuntos, WfTransaccionAdjuntosPk> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionAdjuntosDaoImpl() {
		super("wftransaccionadjuntos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<WfTransaccionAdjuntos> listarPorTransaccion(Integer transaccionId) {
		Criteria query = getCriteria();
		query = query.add(Restrictions.eq("pk.transaccionid", new BigDecimal(transaccionId)));
		return query.list();
	}

	public List<WfTransaccionAdjuntos> obtenerCabecera(Integer transaccionId) {
		Criteria query = getCriteria();
		BigDecimal id = new BigDecimal(transaccionId);
		query = query.add(Restrictions.eq("pk.transaccionid", id));
		query = query.add(Restrictions.eq("flagver", "S"));
		List l = query.list();
		return l;
	}

}
