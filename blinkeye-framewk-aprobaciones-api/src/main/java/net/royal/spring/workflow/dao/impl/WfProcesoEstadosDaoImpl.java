package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoEstados;
import net.royal.spring.workflow.dominio.WfProcesoEstadosPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoEstado;

@Repository
public class WfProcesoEstadosDaoImpl extends GenericoDaoImpl<WfProcesoEstados, WfProcesoEstadosPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoEstadosDaoImpl() {
		super("wfprocesoestados");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public String obtenerEstadoCesado(String procesoid) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.procesoid", procesoid))
				.add(Restrictions.eq("tipoId", "RECH"));
		List ls = criteria.list();

		if (ls.size() == 0) {
			return null;
		}

		return ((WfProcesoEstados) ls.get(0)).getPk().getEstadoid();
	}

	public List<DtoExportarWfProcesoEstado> exportar(String proceso, Integer version) {
		List<DtoExportarWfProcesoEstado> lst = new ArrayList<DtoExportarWfProcesoEstado>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).list()) {
			WfProcesoEstados row = (WfProcesoEstados) r;
			DtoExportarWfProcesoEstado dto = new DtoExportarWfProcesoEstado();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setEstadoId(row.getPk().getEstadoid());
			dto.setNombre(row.getNombre());
			dto.setTipoId(row.getTipoId());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoEstado row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoEstados bean = new WfProcesoEstados();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setEstadoid(row.getEstadoId());
		bean.setNombre(row.getNombre());
		bean.setTipoId(row.getTipoId());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
		
	}

}
