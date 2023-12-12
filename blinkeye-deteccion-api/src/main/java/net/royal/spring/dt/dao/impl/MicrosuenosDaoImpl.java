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
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.dt.dominio.Microsuenos;
import net.royal.spring.dt.dominio.MicrosuenosPk;
import net.royal.spring.dt.dominio.dto.DtoMicrosuenos;
import net.royal.spring.dt.dominio.filtro.FiltroMicrosuenos;

@Repository
public class MicrosuenosDaoImpl extends GenericoDaoImpl<Microsuenos, MicrosuenosPk> {

	private static Logger logger = LogManager.getLogger(Microsuenos.class);

	public MicrosuenosDaoImpl() {
		super("microsuenos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Microsuenos obtenerPorId(Integer pidMicrosueno) {
		return obtenerPorId(new MicrosuenosPk( pidMicrosueno));
	}

	public Microsuenos coreInsertar(Microsuenos bean) {
		this.registrar(bean);
		return bean;
	}


	public Microsuenos coreActualizar(Microsuenos bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoMicrosuenos obtenerDtoCore(DtoMicrosuenos pk) throws Exception {
		DtoMicrosuenos dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoMicrosuenos();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoMicrosuenos obtenerDto(DtoMicrosuenos pk) {
		DtoMicrosuenos dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idmicrosueno", Integer.class, pk.getIdMicrosueno()));

		List lst = listarPorQuery(DtoMicrosuenos.class, "microsuenos.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoMicrosuenos) lst.get(0);
		return dto;
	}

	public List<DtoMicrosuenos> listarDtoCore(DtoMicrosuenos filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Microsuenos.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdMicrosueno()))
			filtro.setIdMicrosueno(null);

		parametros.add(new DominioParametroPersistencia("p_idmicrosueno", Integer.class, filtro.getIdMicrosueno()));

		List datos = this.listarPorQuery(DtoMicrosuenos.class, "microsuenos.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroMicrosuenos filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Microsuenos.listarPaginado : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdMicrosueno()))
			filtro.setIdMicrosueno(null);

		parametros.add(new DominioParametroPersistencia("p_idmicrosueno", Integer.class, filtro.getIdMicrosueno()));

		Integer registros = contar("microsuenos.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "microsuenos.listarPaginadoSentencia",DtoMicrosuenos.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "microsuenos.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPorClasepaginado(SeguridadUsuarioActual usuarioActual, FiltroMicrosuenos filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UInteger.esCeroOrNulo(filtro.getIdClase()))
			filtro.setIdClase(null);

		if (UInteger.esCeroOrNulo(filtro.getIdDocente()))
			filtro.setIdDocente(null);
		
		if (UFechaHora.esNuloVacio(filtro.getFechaDesde()))
			filtro.setFechaDesde(null);
		
		if (UFechaHora.esNuloVacio(filtro.getFechaHasta()))
			filtro.setFechaHasta(null);

		parametros.add(new DominioParametroPersistencia("p_iddocente", Integer.class, filtro.getIdDocente()));
		parametros.add(new DominioParametroPersistencia("p_idclase", Integer.class, filtro.getIdClase()));
		parametros.add(new DominioParametroPersistencia("p_fechaDesde", Date.class, filtro.getFechaDesde()));
		parametros.add(new DominioParametroPersistencia("p_fechaHasta", Date.class, filtro.getFechaHasta()));

		Integer registros = contar("microsuenos.listarPorClasepaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "microsuenos.listarPorClasepaginado",DtoMicrosuenos.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
