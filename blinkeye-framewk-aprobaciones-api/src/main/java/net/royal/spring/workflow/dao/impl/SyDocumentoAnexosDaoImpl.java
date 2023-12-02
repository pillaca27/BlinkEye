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
import net.royal.spring.workflow.dominio.SyDocumentoAnexos;
import net.royal.spring.workflow.dominio.SyDocumentoAnexosPk;


@Repository
public class SyDocumentoAnexosDaoImpl extends GenericoDaoImpl<SyDocumentoAnexos, SyDocumentoAnexosPk> {

	private static final long serialVersionUID = 1L;

	public SyDocumentoAnexosDaoImpl() {
		super("sydocumentoanexos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<SyDocumentoAnexos> listarPorTransaccion(Integer transaccionId) {
		Criteria query = getCriteria();
		query = query.add(Restrictions.eq("pk.linea", transaccionId));
		return query.list();
	}

	public List<SyDocumentoAnexos> obtenerCabecera(Integer transaccionId) {
		Criteria query = getCriteria();
		query = query.add(Restrictions.eq("pk.modulo", "WF"));
		query = query.add(Restrictions.eq("pk.linea", transaccionId));
		query = query.add(Restrictions.eq("concepto", "S"));
		List l = query.list();
		return l;
	}

	public Integer obtenerSecuencia(String modulo, Integer transaccionId) {
		Criteria c = this.getCriteria().add(Restrictions.eq("pk.modulo", modulo))
				.add(Restrictions.eq("pk.linea", transaccionId))
				.setProjection(Projections.projectionList().add(Projections.max("pk.secuencia")));
		return this.incrementarInteger(c);
	}

	public SyDocumentoAnexos obtenerPorTransaccionSecuencia(String modulo, Integer transaccion, Integer secuencia) {
		Criteria query = getCriteria();
		query = query.add(Restrictions.eq("pk.modulo", modulo));
		query = query.add(Restrictions.eq("pk.linea", transaccion));
		query = query.add(Restrictions.eq("pk.secuencia", secuencia));
		List l = query.list();
		return (SyDocumentoAnexos) l.get(0);
	}

}
