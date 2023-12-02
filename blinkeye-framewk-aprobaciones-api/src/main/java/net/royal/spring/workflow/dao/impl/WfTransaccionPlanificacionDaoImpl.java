package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionplanificacion;
import net.royal.spring.workflow.dominio.WfTransaccionplanificacionPk;
import net.royal.spring.workflow.dominio.dto.DtoWfPlanificacionEtapa;

@Repository
public class WfTransaccionPlanificacionDaoImpl
		extends GenericoDaoImpl<WfTransaccionplanificacion, WfTransaccionplanificacionPk> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionPlanificacionDaoImpl() {
		super("wftransaccionplanificacion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoWfPlanificacionEtapa> listarPorTransaccion(Integer transaccionid) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.transaccionId", transaccionid));
		List<WfTransaccionplanificacion> ls = criteria.list();
		List<DtoWfPlanificacionEtapa> lst = new ArrayList<DtoWfPlanificacionEtapa>();
		for (WfTransaccionplanificacion row : ls) {
			DtoWfPlanificacionEtapa d = new DtoWfPlanificacionEtapa();
			d.setPlanificacionid(row.getPk().getPlanificacionId());
			d.setNivel(row.getNivelId());
			d.setInicio(row.getFechahoraInicio());
			d.setFin(row.getFechahoraFin());
			lst.add(d);
		}
		return lst;
	}

}
