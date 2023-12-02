package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalleFiltro;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalleListado;
import net.royal.spring.workflow.dominio.filtro.FiltroWfMacroProcesoDetalle;

@Repository
public class WfMacroProcesoDetalleDaoImpl extends GenericoDaoImpl<WfMacroProcesoDetalle, WfMacroProcesoDetallePk> {

	private static Logger logger = LogManager.getLogger(WfMacroProcesoDetalle.class);

	public WfMacroProcesoDetalleDaoImpl() {
		super("wfmacroprocesodetalle");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfMacroProcesoDetalle obtenerPorId(String pmacroProcesoId,String pprocesoOrigenId,String pprocesoDestinoId) {
		return obtenerPorId(new WfMacroProcesoDetallePk( pmacroProcesoId, pprocesoOrigenId, pprocesoDestinoId));
	}

	public WfMacroProcesoDetalle coreInsertar(WfMacroProcesoDetalle bean) {
		this.registrar(bean);
		return bean;
	}


	public WfMacroProcesoDetalle coreActualizar(WfMacroProcesoDetalle bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoWfMacroProcesoDetalle obtenerDtoPorId(DtoWfMacroProcesoDetalle pk) {
		DtoWfMacroProcesoDetalle dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, pk.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_procesoorigenid", String.class, pk.getProcesoOrigenId()));
		parametros.add(new DominioParametroPersistencia("p_procesodestinoid", String.class, pk.getProcesoDestinoId()));


		List lst = listarPorQuery(DtoWfMacroProcesoDetalle.class, "wfmacroprocesodetalle.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoWfMacroProcesoDetalle) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoWfMacroProcesoDetalleFiltro filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProcesoDetalle.Paginacion
		//parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));
		//parametros.add(new DominioParametroPersistencia("p_procesoorigenid", String.class, filtro.getProcesoOrigenId()));
		//parametros.add(new DominioParametroPersistencia("p_procesodestinoid", String.class, filtro.getProcesoDestinoId()));


		Integer registros = contar("wfmacroprocesodetalle.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wfmacroprocesodetalle.listarPaginadoSentencia",DtoWfMacroProcesoDetalleListado.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public WfMacroProcesoDetalle obtenerInicial(String procesoOrigenId) {
		Criteria criteria = getCriteria();
		criteria = criteria.add(Restrictions.eq("procesoOrigenId", procesoOrigenId));
		criteria = criteria.add(Restrictions.eq("orden", 1));
		List lst = criteria.list();
		
		if (lst.size()==1)
			return (WfMacroProcesoDetalle)lst.get(0);
		else
			return null;
	}	
	
	public DtoWfMacroProcesoDetalle obtenerDtoCore(DtoWfMacroProcesoDetalle pk) throws Exception {
		DtoWfMacroProcesoDetalle dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoWfMacroProcesoDetalle();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoWfMacroProcesoDetalle> listarDtoCore(DtoWfMacroProcesoDetalle filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProcesoDetalle.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getMacroProcesoId()))
			filtro.setMacroProcesoId(null);
		if (UString.esNuloVacio(filtro.getProcesoOrigenId()))
			filtro.setProcesoOrigenId(null);
		if (UString.esNuloVacio(filtro.getProcesoDestinoId()))
			filtro.setProcesoDestinoId(null);

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_procesoorigenid", String.class, filtro.getProcesoOrigenId()));
		parametros.add(new DominioParametroPersistencia("p_procesodestinoid", String.class, filtro.getProcesoDestinoId()));

		List datos = this.listarPorQuery(DtoWfMacroProcesoDetalle.class, "wfmacroprocesodetalle.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoWfMacroProcesoDetalle obtenerDto(DtoWfMacroProcesoDetalle pk) {
		DtoWfMacroProcesoDetalle dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, pk.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_procesoorigenid", String.class, pk.getProcesoOrigenId()));
		parametros.add(new DominioParametroPersistencia("p_procesodestinoid", String.class, pk.getProcesoDestinoId()));

		List lst = listarPorQuery(DtoWfMacroProcesoDetalle.class, "wfmacroprocesodetalle.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoWfMacroProcesoDetalle) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroWfMacroProcesoDetalle filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfMacroProcesoDetalle.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getMacroProcesoId()))
			filtro.setMacroProcesoId(null);
		if (UString.esNuloVacio(filtro.getProcesoOrigenId()))
			filtro.setProcesoOrigenId(null);
		if (UString.esNuloVacio(filtro.getProcesoDestinoId()))
			filtro.setProcesoDestinoId(null);

		parametros.add(new DominioParametroPersistencia("p_macroprocesoid", String.class, filtro.getMacroProcesoId()));
		parametros.add(new DominioParametroPersistencia("p_procesoorigenid", String.class, filtro.getProcesoOrigenId()));
		parametros.add(new DominioParametroPersistencia("p_procesodestinoid", String.class, filtro.getProcesoDestinoId()));

		Integer registros = contar("wfmacroprocesodetalle.L_listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wfmacroprocesodetalle.L_listarPaginadoSentencia",DtoWfMacroProcesoDetalle.class);
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
		//parametros.add(new DominioParametroPersistencia("p_sidid", String.class, filtro.getSidId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "wfmacroprocesodetalle.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}
}
