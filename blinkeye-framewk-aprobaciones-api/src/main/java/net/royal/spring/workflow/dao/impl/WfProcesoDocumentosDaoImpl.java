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
import net.royal.spring.workflow.dominio.WfProcesoDocumentos;
import net.royal.spring.workflow.dominio.WfProcesoDocumentosPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoDocumento;

@Repository
public class WfProcesoDocumentosDaoImpl extends GenericoDaoImpl<WfProcesoDocumentos, WfProcesoDocumentosPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoDocumentosDaoImpl() {
		super("wfprocesodocumentos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfProcesoDocumento> exportar(String proceso, Integer version) {
		List<DtoExportarWfProcesoDocumento> lst = new ArrayList<DtoExportarWfProcesoDocumento>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).list()) {
			WfProcesoDocumentos row = (WfProcesoDocumentos) r;
			DtoExportarWfProcesoDocumento dto = new DtoExportarWfProcesoDocumento();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setGrupo(row.getGrupo());
			dto.setRutaPlantilla(row.getRutaplantilla());
			dto.setTipoDocumentoId(row.getPk().getTipodocumentoid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoDocumento row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoDocumentos bean = new WfProcesoDocumentos();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setTipodocumentoid(row.getTipoDocumentoId());
		bean.setGrupo(row.getGrupo());
		bean.setRutaplantilla(row.getRutaPlantilla());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
	}

}
