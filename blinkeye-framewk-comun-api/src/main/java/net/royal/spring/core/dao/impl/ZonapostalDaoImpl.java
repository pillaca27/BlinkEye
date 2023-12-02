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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanZonapostal;
import net.royal.spring.core.dominio.BeanZonapostalPk;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.dominio.filtro.FiltroComunZonapostal;
import net.royal.spring.core.dominio.lista.DtlComunZonapostal;

@Repository
public class ZonapostalDaoImpl extends GenericoDaoImpl<BeanZonapostal, BeanZonapostalPk> {

	private static Logger logger = LogManager.getLogger(BeanZonapostal.class);

	public ZonapostalDaoImpl() {
		super("zonapostal");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanZonapostal obtenerPorId() {
		return obtenerPorId(new BeanZonapostalPk());
	}

	public BeanZonapostal coreInsertar(BeanZonapostal bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanZonapostal coreActualizar(BeanZonapostal bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunZonapostal obtenerDtoCore(DtoComunZonapostal pk) throws Exception {
		DtoComunZonapostal dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunZonapostal();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunZonapostal> listarDtoCore(DtoComunZonapostal filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Zonapostal.listarDtoCore : modificar query / modificar propiedades

		List datos = this.listarPorQuery(DtoComunZonapostal.class, "zonapostal.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunZonapostal obtenerDto(DtoComunZonapostal pk) {
		DtoComunZonapostal dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		List lst = listarPorQuery(DtoComunZonapostal.class, "zonapostal.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunZonapostal) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunZonapostal filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Zonapostal.listarPaginado : modificar query / modificar propiedades

		Integer registros = contar("zonapostal.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "zonapostal.listarPaginadoSentencia",DtlComunZonapostal.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
