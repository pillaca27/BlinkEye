package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProcesoVersion;

@Repository
public class WfProcesoVersionesDaoImpl extends GenericoDaoImpl<WfProcesoVersiones, WfProcesoVersionesPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoVersionesDaoImpl() {
		super("wfprocesoversiones");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoExportarWfProcesoVersion> exportar(String proceso, Integer version) {
		List<DtoExportarWfProcesoVersion> lst = new ArrayList<DtoExportarWfProcesoVersion>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso))
				.add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoVersiones row = (WfProcesoVersiones) r;
			DtoExportarWfProcesoVersion dto = new DtoExportarWfProcesoVersion();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setNombre(row.getNombre());
			dto.setVersionId(row.getPk().getVersionid());

			dto.setAdministradorId(row.getAdministradorId());
			dto.setApi(row.getApi());
			dto.setComunicacionFlgAlerta(row.getComunicacionFlgAlerta());
			dto.setFlgCorreoNiveles(row.getFlgCorreoNiveles());
			dto.setFlgPlanificacionGenerar(row.getFlgplanificaciongenerar());
			dto.setNivelEstadoIdInicial(row.getNivelestadoidinicial());
			dto.setOrigenDatosId(row.getOrigendatosid());
			dto.setSegmentoCodigoTabla(row.getSegmentocodigotabla());
			dto.setSpVer(row.getSpver());
			dto.setWebComponente(row.getWebcomponente());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProcesoVersion row, SeguridadUsuarioActual seguridadUsuarioActual,
			boolean existeVersion) {
		WfProcesoVersiones bean = null;

		if (existeVersion) {
			bean = obtenerPorId(new WfProcesoVersionesPk(row.getProcesoId(), row.getVersionId()));
		} else {
			bean = new WfProcesoVersiones();
		}

		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.setEstado("A");
		bean.setNombre(row.getNombre());
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());

		bean.setAdministradorId(row.getAdministradorId());
		bean.setApi(row.getApi());
		bean.setComunicacionFlgAlerta(row.getComunicacionFlgAlerta());
		bean.setFlgCorreoNiveles(row.getFlgCorreoNiveles());
		bean.setFlgplanificaciongenerar(row.getFlgPlanificacionGenerar());
		bean.setNivelestadoidinicial(row.getNivelEstadoIdInicial());
		bean.setOrigendatosid(row.getOrigenDatosId());
		bean.setSegmentocodigotabla(row.getSegmentoCodigoTabla());
		bean.setSpver(row.getSpVer());
		bean.setWebcomponente(row.getWebComponente());

		if (existeVersion) {
			actualizar(bean);
		} else {
			registrar(bean);
		}

	}

	public Integer generarVersion(String proceso) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.procesoid", proceso))
				.setProjection(Projections.max("pk.versionid"));
		Integer calc = (Integer) criteria.uniqueResult();

		if (calc == null) {
			return 1;
		}
		return calc + 1;
	}

}
