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
import net.royal.spring.workflow.dominio.WfProcesoFlujosNiveles;
import net.royal.spring.workflow.dominio.WfProcesoFlujosNivelesPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfFlujoNivel;

@Repository
public class WfProcesoFlujosNivelesDaoImpl extends GenericoDaoImpl<WfProcesoFlujosNiveles, WfProcesoFlujosNivelesPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesoFlujosNivelesDaoImpl() {
		super("wfprocesoflujosniveles");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<WfProcesoFlujosNiveles> listar(String proceso, Integer version, Integer flujo) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.procesoid", proceso))
				.add(Restrictions.eq("pk.versionid", version)).add(Restrictions.eq("pk.flujoid", flujo));
		return criteria.list();
	}

	public List<DtoExportarWfFlujoNivel> exportar(String proceso, Integer version) {
		List<DtoExportarWfFlujoNivel> lst = new ArrayList<DtoExportarWfFlujoNivel>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).add(Restrictions.eq("pk.versionid", version)).list()) {
			WfProcesoFlujosNiveles row = (WfProcesoFlujosNiveles) r;
			DtoExportarWfFlujoNivel dto = new DtoExportarWfFlujoNivel();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setApi(row.getApi());
			dto.setCondicionAprobacionId(row.getCondicionaprobacionid());
			dto.setCorreoOtros(row.getCorreootros());
			dto.setDocumentoFlgBotonNuevo(row.getDocumentoFlgBotonNuevo());
			dto.setDocumentoFlgColumnaGrupo(row.getDocumentoFlgColumnaGrupo());

			dto.setDuracionCantidad(row.getDuracioncantidad());
			dto.setDuracionTipo(row.getDuraciontipo());
			dto.setEstadoId(row.getEstadoid());
			dto.setFlgAprobarComentarioDetallado(row.getFlgAprobarComentarioDetallado());
			dto.setFlgBotonAprobar(row.getFlgbotonaprobar());

			dto.setFlgBotonDevolver(row.getFlgbotondevolver());
			dto.setFlgBotonRechazar(row.getFlgbotonrechazar());
			dto.setFlgCorreoJefe(row.getFlgcorreojefe());
			dto.setFlgCorreoSolicitante(row.getFlgcorreosolicitante());
			dto.setFlgDevolverComentarioDetallado(row.getFlgDevolverComentarioDetallado());

			dto.setFlgNotificar(row.getFlgnotificar());
			dto.setFlgPlanificacionEditable(row.getFlgplanificacioneditable());
			dto.setFlgPlanificacionValidar(row.getFlgPlanificacionValidar());
			dto.setFlgPlanificacionVer(row.getFlgplanificacionver());
			dto.setFlgRechazarComentarioDetallado(row.getFlgRechazarComentarioDetallado());

			dto.setFlujoId(row.getPk().getFlujoid());
			dto.setNivelId(row.getPk().getNivelid());
			dto.setNombre(row.getNombre());
			dto.setOrigenDatosId(row.getOrigendatosid());
			dto.setPlanificacionTag(row.getPlanificacionTag());

			dto.setSpAprobar(row.getSpaprobar());
			dto.setSpDevolver(row.getSpdevolver());
			dto.setSpRechazar(row.getSprechazar());
			dto.setSpValidar(row.getSpvalidar());
			dto.setTipoAprobadorId(row.getTipoaprobadorid());

			dto.setVersionId(row.getPk().getVersionid());
			dto.setWebComponente(row.getWebcomponente());
			
			dto.setFlgCorreoPersonaReferencia(row.getFlgCorreoPersonaReferencia());
			dto.setFlgCorreoTransaccion(row.getFlgCorreoTransaccion());
			dto.setFlgCorreoPersona(row.getFlgCorreoPersona());
			
			dto.setFlgAprobarComentario(row.getFlgAprobarComentario());
			
			dto.setFlgCorreoAdjuntarDocumentos(row.getFlgCorreoAdjuntarDocumentos());

			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfFlujoNivel row, SeguridadUsuarioActual seguridadUsuarioActual) {
		WfProcesoFlujosNiveles bean = new WfProcesoFlujosNiveles();
		bean.getPk().setProcesoid(row.getProcesoId());
		bean.getPk().setVersionid(row.getVersionId());
		bean.getPk().setFlujoid(row.getFlujoId());
		bean.getPk().setNivelid(row.getNivelId());

		bean.setApi(row.getApi());
		bean.setCondicionaprobacionid(row.getCondicionAprobacionId());
		bean.setCorreootros(row.getCorreoOtros());
		bean.setDocumentoFlgBotonNuevo(row.getDocumentoFlgBotonNuevo());
		bean.setDocumentoFlgColumnaGrupo(row.getDocumentoFlgColumnaGrupo());

		bean.setDuracioncantidad(row.getDuracionCantidad());
		bean.setDuraciontipo(row.getDuracionTipo());
		bean.setEstadoid(row.getEstadoId());
		bean.setFlgAprobarComentarioDetallado(row.getFlgAprobarComentarioDetallado());
		bean.setFlgbotonaprobar(row.getFlgBotonAprobar());

		bean.setFlgbotondevolver(row.getFlgBotonDevolver());
		bean.setFlgbotonrechazar(row.getFlgBotonRechazar());
		bean.setFlgcorreojefe(row.getFlgCorreoJefe());
		bean.setFlgcorreosolicitante(row.getFlgCorreoSolicitante());
		bean.setFlgDevolverComentarioDetallado(row.getFlgDevolverComentarioDetallado());

		bean.setFlgnotificar(row.getFlgNotificar());
		bean.setFlgplanificacioneditable(row.getFlgPlanificacionEditable());
		bean.setFlgPlanificacionValidar(row.getFlgPlanificacionValidar());
		bean.setFlgplanificacionver(row.getFlgPlanificacionVer());
		bean.setFlgRechazarComentarioDetallado(row.getFlgRechazarComentarioDetallado());

		bean.setNombre(row.getNombre());
		bean.setOrigendatosid(row.getOrigenDatosId());
		bean.setPlanificacionTag(row.getPlanificacionTag());

		bean.setSpaprobar(row.getSpAprobar());
		bean.setSpdevolver(row.getSpDevolver());
		bean.setSprechazar(row.getSpRechazar());
		bean.setSpvalidar(row.getSpValidar());
		bean.setTipoaprobadorid(row.getTipoAprobadorId());

		bean.setWebcomponente(row.getWebComponente());
		
		bean.setFlgCorreoPersonaReferencia(row.getFlgCorreoPersonaReferencia());
		bean.setFlgCorreoTransaccion(row.getFlgCorreoTransaccion());
		bean.setFlgCorreoPersona(row.getFlgCorreoPersona());

		bean.setFlgAprobarComentario(row.getFlgAprobarComentario());
		
		bean.setFlgCorreoAdjuntarDocumentos(row.getFlgCorreoAdjuntarDocumentos());
		
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(seguridadUsuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(seguridadUsuarioActual.getUsuario());
		registrar(bean);

	}

}
