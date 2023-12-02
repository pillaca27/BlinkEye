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
import net.royal.spring.workflow.dominio.WfProcesoFlujoConfiguraciones;
import net.royal.spring.workflow.dominio.WfProcesoFlujoConfiguracionesPk;
import net.royal.spring.workflow.dominio.WfProcesoFlujos;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoConfiguracion;

@Repository
public class WfProcesoFlujoConfiguracionesDaoImpl
		extends GenericoDaoImpl<WfProcesoFlujoConfiguraciones, WfProcesoFlujoConfiguracionesPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujoConfiguracionesDaoImpl() {
		super("wfprocesoflujoconfiguraciones");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfFlujoConfiguracion> exportar(String proceso, Integer version) {
		List<DtoExportarWfFlujoConfiguracion> lst = new ArrayList<DtoExportarWfFlujoConfiguracion>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujoConfiguraciones row = (WfProcesoFlujoConfiguraciones) r;
			DtoExportarWfFlujoConfiguracion dto = new DtoExportarWfFlujoConfiguracion();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setComparacion(row.getComparacion());
			dto.setConfiguracionId(row.getPk().getConfiguracionid());
			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setRelacion(row.getRelacion());
			dto.setValores(row.getValores());
			dto.setVariableId(row.getVariableid());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfFlujoConfiguracion row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujoConfiguraciones bean = new WfProcesoFlujoConfiguraciones();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.getPk().setConfiguracionid(row.getConfiguracionId());
		bean.setComparacion(row.getComparacion());
		bean.setRelacion(row.getRelacion());
		bean.setValores(row.getValores());
		bean.setVariableid(row.getVariableId());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
		
	}

}
