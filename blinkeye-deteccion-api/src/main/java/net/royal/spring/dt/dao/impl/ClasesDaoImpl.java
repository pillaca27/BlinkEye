package net.royal.spring.dt.dao.impl;

import java.math.BigDecimal;
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
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.dt.dominio.Clases;
import net.royal.spring.dt.dominio.ClasesPk;
import net.royal.spring.dt.dominio.dto.DtoClases;
import net.royal.spring.dt.dominio.filtro.FiltroClases;

@Repository
public class ClasesDaoImpl extends GenericoDaoImpl<Clases, ClasesPk> {

	private static Logger logger = LogManager.getLogger(Clases.class);

	public ClasesDaoImpl() {
		super("clases");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Clases obtenerPorId(Integer pidClase) {
		return obtenerPorId(new ClasesPk( pidClase));
	}

	public Clases coreInsertar(Clases bean) {
		this.registrar(bean);
		return bean;
	}


	public Clases coreActualizar(Clases bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoClases obtenerDtoCore(DtoClases pk) throws Exception {
		DtoClases dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoClases();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoClases obtenerDto(DtoClases pk) {
		DtoClases dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idclase", Integer.class, pk.getIdClase()));

		List lst = listarPorQuery(DtoClases.class, "clases.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoClases) lst.get(0);
		return dto;
	}

	public List<DtoClases> listarDtoCore(DtoClases filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Clases.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdClase()))
			filtro.setIdClase(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_idclase", Integer.class, filtro.getIdClase()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		List datos = this.listarPorQuery(DtoClases.class, "clases.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroClases filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Clases.listarPaginado : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdClase()))
			filtro.setIdClase(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_idclase", Integer.class, filtro.getIdClase()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		Integer registros = contar("clases.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "clases.listarPaginadoSentencia",DtoClases.class);
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
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "clases.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
