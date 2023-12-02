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
import net.royal.spring.workflow.dominio.WfProcesoVariables;
import net.royal.spring.workflow.dominio.WfProcesoVariablesPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoVariable;

@Repository
public class WfProcesoVariablesDaoImpl extends GenericoDaoImpl<WfProcesoVariables, WfProcesoVariablesPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoVariablesDaoImpl() {
		super("wfprocesovariables");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfProcesoVariable> exportar(String proceso, Integer version) {
		List<DtoExportarWfProcesoVariable> lst = new ArrayList<DtoExportarWfProcesoVariable>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).list()) {
			WfProcesoVariables row = (WfProcesoVariables) r;
			DtoExportarWfProcesoVariable dto = new DtoExportarWfProcesoVariable();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setVariableId(row.getPk().getVariableid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoVariable row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoVariables bean = new WfProcesoVariables();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVariableid(row.getVariableId());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
	}

}
