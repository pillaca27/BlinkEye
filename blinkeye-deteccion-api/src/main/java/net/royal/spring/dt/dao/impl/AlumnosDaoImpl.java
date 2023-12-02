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
import net.royal.spring.dt.dominio.Alumnos;
import net.royal.spring.dt.dominio.AlumnosPk;
import net.royal.spring.dt.dominio.dto.DtoAlumnos;
import net.royal.spring.dt.dominio.filtro.FiltroAlumnos;

@Repository
public class AlumnosDaoImpl extends GenericoDaoImpl<Alumnos, AlumnosPk> {

	private static Logger logger = LogManager.getLogger(Alumnos.class);

	public AlumnosDaoImpl() {
		super("alumnos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Alumnos obtenerPorId(Integer pidAlumno) {
		return obtenerPorId(new AlumnosPk( pidAlumno));
	}

	public Alumnos coreInsertar(Alumnos bean) {
		this.registrar(bean);
		return bean;
	}


	public Alumnos coreActualizar(Alumnos bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoAlumnos obtenerDtoCore(DtoAlumnos pk) throws Exception {
		DtoAlumnos dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoAlumnos();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoAlumnos obtenerDto(DtoAlumnos pk) {
		DtoAlumnos dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idalumno", Integer.class, pk.getIdAlumno()));

		List lst = listarPorQuery(DtoAlumnos.class, "alumnos.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoAlumnos) lst.get(0);
		return dto;
	}

	public List<DtoAlumnos> listarDtoCore(DtoAlumnos filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Alumnos.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdAlumno()))
			filtro.setIdAlumno(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_idalumno", Integer.class, filtro.getIdAlumno()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		List datos = this.listarPorQuery(DtoAlumnos.class, "alumnos.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroAlumnos filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Alumnos.listarPaginado : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getIdAlumno()))
			filtro.setIdAlumno(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_idalumno", Integer.class, filtro.getIdAlumno()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		Integer registros = contar("alumnos.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "alumnos.listarPaginadoSentencia",DtoAlumnos.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "alumnos.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
