package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAcciones;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAccionesPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoAccion;

@Repository
public class WfProcesoFlujoNivelAccionesDaoImpl
		extends GenericoDaoImpl<WfProcesoFlujoNivelAcciones, WfProcesoFlujoNivelAccionesPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujoNivelAccionesDaoImpl() {
		super("wfprocesoflujonivelacciones");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfFlujoAccion> exportar(String proceso, Integer version) {
		List<DtoExportarWfFlujoAccion> lst = new ArrayList<DtoExportarWfFlujoAccion>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujoNivelAcciones row = (WfProcesoFlujoNivelAcciones) r;
			DtoExportarWfFlujoAccion dto = new DtoExportarWfFlujoAccion();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setAccionId(row.getPk().getAccionid());
			dto.setAccionWf(row.getAccionwf());
			dto.setEstadoSubAccion(row.getEstadosubaccion());
			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setNivelId(row.getPk().getNivelid());
			dto.setNombre(row.getNombre());
			dto.setNivelDestinoId(row.getNivelDestinoId());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfFlujoAccion row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujoNivelAcciones bean = new WfProcesoFlujoNivelAcciones();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.getPk().setNivelid(row.getNivelId());
		bean.getPk().setAccionid(row.getAccionId());
		bean.setNivelDestinoId(row.getNivelDestinoId());
		bean.setAccionwf(row.getAccionWf());
		bean.setEstadosubaccion(row.getEstadoSubAccion());
		bean.setNombre(row.getNombre());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
	}

}
