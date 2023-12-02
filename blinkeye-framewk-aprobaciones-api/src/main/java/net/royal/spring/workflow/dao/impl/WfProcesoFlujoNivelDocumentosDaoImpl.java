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
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelDocumentos;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelDocumentosPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoNivelDocumento;

@Repository
public class WfProcesoFlujoNivelDocumentosDaoImpl
		extends GenericoDaoImpl<WfProcesoFlujoNivelDocumentos, WfProcesoFlujoNivelDocumentosPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujoNivelDocumentosDaoImpl() {
		super("wfprocesoflujoniveldocumentos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfFlujoNivelDocumento> exportar(String proceso, Integer version) {
		List<DtoExportarWfFlujoNivelDocumento> lst = new ArrayList<DtoExportarWfFlujoNivelDocumento>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujoNivelDocumentos row = (WfProcesoFlujoNivelDocumentos) r;
			DtoExportarWfFlujoNivelDocumento dto = new DtoExportarWfFlujoNivelDocumento();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setFlgEditable(row.getFlgeditable());
			dto.setFlgFirmaElectronica(row.getFlgfirmaelectronica());
			dto.setFlgFirmaImagen(row.getFlgfirmaimagen());
			dto.setFlgRequerido(row.getFlgrequerido());
			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setNivelId(row.getPk().getNivelid());
			dto.setTipoDocumentoId(row.getPk().getTipodocumentoid());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfFlujoNivelDocumento row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujoNivelDocumentos bean = new WfProcesoFlujoNivelDocumentos();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.getPk().setNivelid(row.getNivelId());
		bean.getPk().setTipodocumentoid(row.getTipoDocumentoId());
		bean.setFlgeditable(row.getFlgEditable());
		bean.setFlgfirmaelectronica(row.getFlgFirmaElectronica());
		bean.setFlgfirmaimagen(row.getFlgFirmaImagen());
		bean.setFlgrequerido(row.getFlgRequerido());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);
	}

}
