package net.royal.spring.workflow.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionalerta;
import net.royal.spring.workflow.dominio.WfTransaccionalertaPk;

@Repository
public class WfTransaccionalertaDaoImpl extends GenericoDaoImpl<WfTransaccionalerta, Integer> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionalertaDaoImpl() {
		super("wfTransaccionalerta");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

//	public Integer generarId() {
//		Criteria criteria = getCriteria().setProjection(Projections.max("pk.alertaId"));
//		Integer calc = (Integer) criteria.uniqueResult();
//
//		if (calc == null) {
//			return 1;
//		}
//		return calc.intValue() + 1;
//	}
		
	public void anularActivosPorTransaccion(Integer transaccionId,String usuario) {
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("transaccionId", transaccionId));
		cri.add(Restrictions.eq("estado", "A"));
		List<WfTransaccionalerta> lst = cri.list();
		for (WfTransaccionalerta wfTransaccionalerta : lst) {
			wfTransaccionalerta.setEstado("I");
			wfTransaccionalerta.setModificacionFecha(new Date());
			wfTransaccionalerta.setModificacionUsuario(usuario);
			this.actualizar(wfTransaccionalerta);
		}
	}
	public void eliminarPorTransaccion(Integer transaccionId) {
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("transaccionId", transaccionId));
		List<WfTransaccionalerta> lst = cri.list();
		for (WfTransaccionalerta wfTransaccionalerta : lst) {
			this.delete(wfTransaccionalerta);
		}
	}
}
