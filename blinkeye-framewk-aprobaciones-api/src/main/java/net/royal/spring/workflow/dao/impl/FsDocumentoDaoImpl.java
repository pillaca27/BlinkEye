package net.royal.spring.workflow.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.FsDocumento;
import net.royal.spring.workflow.dominio.FsDocumentoPk;


@Repository
public class FsDocumentoDaoImpl extends GenericoDaoImpl<FsDocumento, FsDocumentoPk> {

	private static final long serialVersionUID = 1L;

	public FsDocumentoDaoImpl() {
		super("fsdocumento");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<FsDocumento> listarPorTransaccion(Integer transaccionId) {
		Criteria query = getCriteria();
		query = query.add(Restrictions.eq("workflowtransaccionid", new BigDecimal(transaccionId)));
		return query.list();
	}

	public List<FsDocumento> obtenerCabecera(Integer transaccionId) {
		Criteria query = getCriteria();
		BigDecimal id = new BigDecimal(transaccionId);
		query = query.add(Restrictions.eq("workflowtransaccionid", id));
		query = query.add(Restrictions.eq("codigotexto1id", "S"));
		List l = query.list();
		return l;
	}

	public Integer obtenerSecuencia() {
		Criteria c = this.getCriteria()
				.setProjection(Projections.projectionList().add(Projections.max("pk.documentoId")));
		return this.incrementarInteger(c);
	}

}
