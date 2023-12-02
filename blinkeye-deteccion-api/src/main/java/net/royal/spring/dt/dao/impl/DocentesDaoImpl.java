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
import net.royal.spring.dt.dominio.Docentes;
import net.royal.spring.dt.dominio.DocentesPk;
import net.royal.spring.dt.dominio.dto.DtoDocentes;
import net.royal.spring.dt.dominio.filtro.FiltroDocentes;

@Repository
public class DocentesDaoImpl extends GenericoDaoImpl<Docentes, DocentesPk> {

	private static Logger logger = LogManager.getLogger(Docentes.class);

	public DocentesDaoImpl() {
		super("docentes");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Docentes obtenerPorId(Integer pidDocente) {
		return obtenerPorId(new DocentesPk( pidDocente));
	}

	public Docentes coreInsertar(Docentes bean) {
		this.registrar(bean);
		return bean;
	}


	public Docentes coreActualizar(Docentes bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoDocentes obtenerDtoCore(DtoDocentes pk) throws Exception {
		DtoDocentes dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoDocentes();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoDocentes obtenerDto(DtoDocentes pk) {
		DtoDocentes dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_iddocente", Integer.class, pk.getIdDocente()));

		List lst = listarPorQuery(DtoDocentes.class, "docentes.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoDocentes) lst.get(0);
		return dto;
	}

	public List<DtoDocentes> listarDtoCore(DtoDocentes filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Docentes.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdDocente()))
			filtro.setIdDocente(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_iddocente", Integer.class, filtro.getIdDocente()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		List datos = this.listarPorQuery(DtoDocentes.class, "docentes.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroDocentes filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Docentes.listarPaginado : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdDocente()))
			filtro.setIdDocente(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_iddocente", Integer.class, filtro.getIdDocente()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		Integer registros = contar("docentes.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "docentes.listarPaginadoSentencia",DtoDocentes.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "docentes.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
