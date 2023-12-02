package net.royal.spring.core.dao.impl;

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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionApliacionesMast;
import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.dominio.BeanAplicacionesmastPk;
import net.royal.spring.core.dominio.dto.DtoComunAplicacionesmast;
import net.royal.spring.core.dominio.filtro.FiltroComunAplicacionesmast;
import net.royal.spring.core.dominio.lista.DtlComunAplicacionesmast;

@Repository
public class AplicacionesmastDaoImpl extends GenericoDaoImpl<BeanAplicacionesmast, BeanAplicacionesmastPk> {

	private static Logger logger = LogManager.getLogger(BeanAplicacionesmast.class);

	public AplicacionesmastDaoImpl() {
		super("aplicacionesmast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DtoComunAplicacionesmast obtenerDtoPorUuid(String uuid) {
		DtoComunAplicacionesmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunAplicacionesmast.class, "aplicacionesmast.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunAplicacionesmast) lst.get(0); 
		}
		return dto;
	}

	public BeanAplicacionesmast obtenerPorId(String paplicacioncodigo) {
		return obtenerPorId(new BeanAplicacionesmastPk( paplicacioncodigo));
	}

	public BeanAplicacionesmast coreInsertar(BeanAplicacionesmast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAplicacionesmast coreActualizar(BeanAplicacionesmast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAplicacionesmast obtenerDtoCore(DtoComunAplicacionesmast pk) throws Exception {
		DtoComunAplicacionesmast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAplicacionesmast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAplicacionesmast> listarDtoCore(DtoComunAplicacionesmast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Aplicacionesmast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));

		List datos = this.listarPorQuery(DtoComunAplicacionesmast.class, "aplicacionesmast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAplicacionesmast obtenerDto(DtoComunAplicacionesmast pk) {
		DtoComunAplicacionesmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));


		List lst = listarPorQuery(DtoComunAplicacionesmast.class, "aplicacionesmast.obtenerDtoCore", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAplicacionesmast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAplicacionesmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		// TODO Aplicacionesmast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));

		Integer registros = contar("aplicacionesmast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "aplicacionesmast.listarPaginadoSentencia",DtlComunAplicacionesmast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	
	public BeanAplicacionesmast coreInsertar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanAplicacionesmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	@Transactional(readOnly = true)
	public DominioPaginacion listar(FiltroComunPaginacionApliacionesMast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getEstado())) {
			filtro.setEstado(null);
		}
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List<?> datos = listarPorQuery(DtoTabla.class, "aplicacionesmast.listar", parametros);
		DominioPaginacion obj = new DominioPaginacion();
		obj.setPaginacionListaResultado(datos);
		return obj;
	}

	@Transactional(readOnly = true)
	public DominioPaginacion listarTipodet(FiltroComunPaginacionApliacionesMast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getEstado())) {
			filtro.setEstado(null);
		}
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		List<?> datos = listarPorQuery(DtoTabla.class, "aplicacionesmast.listartipodet", parametros);
		DominioPaginacion obj = new DominioPaginacion();
		obj.setPaginacionListaResultado(datos);
		return obj;
	}
	/*********/

	public DominioPaginacion exportarAplicaciones(FiltroComunAplicacionesmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		// TODO Aplicacionesmast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		List lst = listarPorQuery(DtlComunAplicacionesmast.class, "aplicacionesmast.listarPaginadoSentencia",parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	 
	/***/
}
