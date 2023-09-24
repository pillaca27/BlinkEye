package net.royal.spring.sy.dao.impl;

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
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacionPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyUnidadreplicacion;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyUnidadreplicacion;
import net.royal.spring.sy.dominio.lista.DtlComunSyUnidadreplicacion;

@Repository
public class SyUnidadreplicacionDaoImpl extends GenericoDaoImpl<BeanSyUnidadreplicacion, BeanSyUnidadreplicacionPk> {

	private static Logger logger = LogManager.getLogger(BeanSyUnidadreplicacion.class);

	public SyUnidadreplicacionDaoImpl() {
		super("syunidadreplicacion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyUnidadreplicacion obtenerPorId(String punidadreplicacion) {
		return obtenerPorId(new BeanSyUnidadreplicacionPk( punidadreplicacion));
	}

	public BeanSyUnidadreplicacion coreInsertar(BeanSyUnidadreplicacion bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyUnidadreplicacion coreActualizar(BeanSyUnidadreplicacion bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyUnidadreplicacion obtenerDtoCore(DtoComunSyUnidadreplicacion pk) throws Exception {
		DtoComunSyUnidadreplicacion dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSyUnidadreplicacion();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSyUnidadreplicacion> listarDtoCore(DtoComunSyUnidadreplicacion filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyUnidadreplicacion.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadreplicacion", String.class, filtro.getUnidadreplicacion()));

		List datos = this.listarPorQuery(DtoComunSyUnidadreplicacion.class, "syunidadreplicacion.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSyUnidadreplicacion obtenerDto(DtoComunSyUnidadreplicacion pk) {
		DtoComunSyUnidadreplicacion dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_unidadreplicacion", String.class, pk.getUnidadreplicacion()));

		List lst = listarPorQuery(DtoComunSyUnidadreplicacion.class, "syunidadreplicacion.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyUnidadreplicacion) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyUnidadreplicacion filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getUnidadreplicacion()))
			filtro.setUnidadreplicacion("");
		
		// TODO SyUnidadreplicacion.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadreplicacion", String.class, filtro.getUnidadreplicacion()));

		Integer registros = contar("syunidadreplicacion.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syunidadreplicacion.listarPaginadoSentencia",DtlComunSyUnidadreplicacion.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarUnidadReplicacion(FiltroComunSyUnidadreplicacion filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getUnidadreplicacion()))
			filtro.setUnidadreplicacion("");
		
		// TODO SyUnidadreplicacion.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadreplicacion", String.class, filtro.getUnidadreplicacion()));

		List lst = listarPorQuery(DtlComunSyUnidadreplicacion.class, "syunidadreplicacion.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
