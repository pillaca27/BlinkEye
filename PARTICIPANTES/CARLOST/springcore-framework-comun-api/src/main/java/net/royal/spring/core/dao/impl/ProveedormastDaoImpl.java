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
import net.royal.spring.core.dominio.BeanProveedormast;
import net.royal.spring.core.dominio.BeanProveedormastPk;
import net.royal.spring.core.dominio.dto.DtoComunProveedormast;
import net.royal.spring.core.dominio.filtro.FiltroComunProveedormast;
import net.royal.spring.core.dominio.lista.DtlComunProveedormast;

@Repository
public class ProveedormastDaoImpl extends GenericoDaoImpl<BeanProveedormast, BeanProveedormastPk> {

	private static Logger logger = LogManager.getLogger(BeanProveedormast.class);

	public ProveedormastDaoImpl() {
		super("proveedormast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanProveedormast obtenerPorId(Integer pproveedor) {
		return obtenerPorId(new BeanProveedormastPk( pproveedor));
	}

	public BeanProveedormast coreInsertar(BeanProveedormast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanProveedormast coreActualizar(BeanProveedormast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunProveedormast obtenerDtoCore(DtoComunProveedormast pk) throws Exception {
		DtoComunProveedormast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunProveedormast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunProveedormast> listarDtoCore(DtoComunProveedormast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Proveedormast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_proveedor", String.class, filtro.getProveedor()));
		parametros.add(new DominioParametroPersistencia("p_proveedor", String.class, filtro.getProveedor()));

		List datos = this.listarPorQuery(DtoComunProveedormast.class, "proveedormast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunProveedormast obtenerDto(DtoComunProveedormast pk) {
		DtoComunProveedormast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, pk.getProveedor()));

		List lst = listarPorQuery(DtoComunProveedormast.class, "proveedormast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunProveedormast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunProveedormast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Proveedormast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_proveedor", String.class, filtro.getProveedor()));
		parametros.add(new DominioParametroPersistencia("p_proveedor", String.class, filtro.getProveedor()));

		Integer registros = contar("proveedormast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "proveedormast.listarPaginadoSentencia",DtlComunProveedormast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
