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
import net.royal.spring.workflow.dominio.WfProcesoRol;
import net.royal.spring.workflow.dominio.WfProcesoRolPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoRol;

@Repository
public class WfProcesoRolDaoImpl extends GenericoDaoImpl<WfProcesoRol, WfProcesoRolPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoRolDaoImpl() {
		super("wfprocesorol");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfProcesoRol> exportar(String proceso, Integer versionId) {
		List<DtoExportarWfProcesoRol> lst = new ArrayList<DtoExportarWfProcesoRol>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", versionId)).list()) {
			WfProcesoRol row = (WfProcesoRol) r;
			DtoExportarWfProcesoRol dto = new DtoExportarWfProcesoRol();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setTipoAprobadorId(row.getPk().getTipoAprobadorId());
			dto.setNombre(row.getNombre());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoRol row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoRol bean = new WfProcesoRol();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setTipoAprobadorId(row.getTipoAprobadorId());
		bean.setNombre(row.getNombre());
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
		
	}

}
