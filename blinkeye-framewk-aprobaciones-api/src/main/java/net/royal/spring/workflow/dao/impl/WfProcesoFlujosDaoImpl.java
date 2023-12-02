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
import net.royal.spring.workflow.dominio.WfProcesoFlujos;
import net.royal.spring.workflow.dominio.WfProcesoFlujosPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoFlujo;

@Repository
public class WfProcesoFlujosDaoImpl extends GenericoDaoImpl<WfProcesoFlujos, WfProcesoFlujosPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujosDaoImpl() {
		super("wfprocesoflujos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfProcesoFlujo> exportar(String proceso, Integer version) {
		List<DtoExportarWfProcesoFlujo> lst = new ArrayList<DtoExportarWfProcesoFlujo>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujos row = (WfProcesoFlujos) r;
			DtoExportarWfProcesoFlujo dto = new DtoExportarWfProcesoFlujo();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setNombre(row.getNombre());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoFlujo row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujos bean = new WfProcesoFlujos();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.setNombre(row.getNombre());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
		
	}

}
