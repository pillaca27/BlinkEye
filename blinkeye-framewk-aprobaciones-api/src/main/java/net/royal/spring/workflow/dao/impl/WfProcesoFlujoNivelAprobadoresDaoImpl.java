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
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAprobadores;
import net.royal.spring.workflow.dominio.WfProcesoFlujoNivelAprobadoresPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoAprobador;

@Repository
public class WfProcesoFlujoNivelAprobadoresDaoImpl
		extends GenericoDaoImpl<WfProcesoFlujoNivelAprobadores, WfProcesoFlujoNivelAprobadoresPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujoNivelAprobadoresDaoImpl() {
		super("wfprocesoflujonivelaprobadores");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfFlujoAprobador> exportar(String proceso, Integer version) {
		List<DtoExportarWfFlujoAprobador> lst = new ArrayList<DtoExportarWfFlujoAprobador>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujoNivelAprobadores row = (WfProcesoFlujoNivelAprobadores) r;
			DtoExportarWfFlujoAprobador dto = new DtoExportarWfFlujoAprobador();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setNivelId(row.getPk().getNivelid());
			dto.setPersonaId(row.getPk().getPersonaid());
			dto.setSegmentoAprobar(row.getSegmentoaprobar());
			dto.setVersionId(row.getPk().getVersionid());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfFlujoAprobador row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujoNivelAprobadores bean = new WfProcesoFlujoNivelAprobadores();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.getPk().setNivelid(row.getNivelId());
		bean.getPk().setPersonaid(row.getPersonaId());
		bean.setSegmentoaprobar(row.getSegmentoAprobar());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);

	}

}
