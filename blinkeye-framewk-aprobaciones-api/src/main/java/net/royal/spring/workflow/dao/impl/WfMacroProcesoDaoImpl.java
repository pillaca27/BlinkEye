package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfMacroProceso;
import net.royal.spring.workflow.dominio.WfMacroProcesoPk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProceso;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoFiltro;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoListado;
import net.royal.spring.workflow.dominio.filtro.FiltroWfMacroProceso;

@Repository
public class WfMacroProcesoDaoImpl extends GenericoDaoImpl<WfMacroProceso, WfMacroProcesoPk> {

	private static Logger logger = LogManager.getLogger(WfMacroProceso.class);

	public WfMacroProcesoDaoImpl() {
		super("wfmacroproceso");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfMacroProceso obtenerPorId(String pmacroProcesoId) {
		return obtenerPorId(new WfMacroProcesoPk( pmacroProcesoId));
	}

	public WfMacroProceso coreInsertar(WfMacroProceso bean) {
		this.registrar(bean);
		return bean;
	}


	public WfMacroProceso coreActualizar(WfMacroProceso bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoWfMacroProceso obtenerDtoPorId(DtoWfMacroProceso pk) {
		DtoWfMacroProceso dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, pk.getMacroProcesoId()));


		List lst = listarPorQuery(DtoWfMacroProceso.class, "wfmacroproceso.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoWfMacroProceso) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoWfMacroProcesoFiltro filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProceso.Paginacion
		//parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));


		Integer registros = contar("wfmacroproceso.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wfmacroproceso.listarPaginadoSentencia",DtoWfMacroProcesoListado.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DtoWfMacroProceso obtenerDtoCore(DtoWfMacroProceso pk) throws Exception {
		DtoWfMacroProceso dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoWfMacroProceso();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
	
	public DtoWfMacroProceso obtenerDto(DtoWfMacroProceso pk) {
		DtoWfMacroProceso dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, pk.getMacroProcesoId()));

		List lst = listarPorQuery(DtoWfMacroProceso.class, "wfmacroproceso.obtenerDto", parametros);
		List detalle = listarPorQuery(DtoWfMacroProcesoDetalle.class, "wfmacroproceso.obtenerdetalle", parametros);
 
		if (lst.size() == 1)
			dto = (DtoWfMacroProceso) lst.get(0);
		dto.setDetalle(detalle);
		
		return dto;
	}
	
	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroWfMacroProceso filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProceso.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getMacroProcesoId()))
			filtro.setMacroProcesoId(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("wfmacroproceso.L_listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wfmacroproceso.L_listarPaginadoSentencia",DtoWfMacroProceso.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public List<DtoTabla> listaCortaPorNombre(DtoTabla filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
	//	parametros.add(new DominioParametroPersistencia("p_sidid", String.class, filtro.getSidId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroproceso.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	public List<DtoWfMacroProceso> listarDtoCore(DtoWfMacroProceso filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProceso.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getMacroProcesoId()))
			filtro.setMacroProcesoId(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoWfMacroProceso.class, "wfmacroproceso.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	public String generarSecuencia() {
		Criteria c = this.getCriteria().setProjection(Projections.projectionList().add(Projections.max("pk.macroProcesoId")));
		return this.incrementarStringInteger(c).toString();
	}
}
