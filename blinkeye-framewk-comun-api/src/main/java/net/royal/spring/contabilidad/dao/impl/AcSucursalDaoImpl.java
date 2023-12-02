package net.royal.spring.contabilidad.dao.impl;

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
import net.royal.spring.contabilidad.dominio.BeanAcSucursal;
import net.royal.spring.contabilidad.dominio.BeanAcSucursalPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcSucursal;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcSucursal;

@Repository
public class AcSucursalDaoImpl extends GenericoDaoImpl<BeanAcSucursal, BeanAcSucursalPk> {

	private static Logger logger = LogManager.getLogger(BeanAcSucursal.class);

	public AcSucursalDaoImpl() {
		super("acsucursal");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public DtoComunAcSucursal obtenerDtoPorUuid(String uuid) {
		DtoComunAcSucursal dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunAcSucursal.class, "acsucursal.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunAcSucursal) lst.get(0); 
		}
		return dto;
	}
	
	public BeanAcSucursal obtenerPorId(String psucursal) {
		return obtenerPorId(new BeanAcSucursalPk( psucursal));
	}

	public BeanAcSucursal coreInsertar(BeanAcSucursal bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcSucursal coreActualizar(BeanAcSucursal bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcSucursal obtenerDtoCore(DtoComunAcSucursal pk) throws Exception {
		DtoComunAcSucursal dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcSucursal();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcSucursal> listarDtoCore(DtoComunAcSucursal filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcSucursal.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, filtro.getSucursal()));

		List datos = this.listarPorQuery(DtoComunAcSucursal.class, "acsucursal.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcSucursal obtenerDto(DtoComunAcSucursal pk) {
		DtoComunAcSucursal dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, pk.getSucursal()));

		List lst = listarPorQuery(DtoComunAcSucursal.class, "acsucursal.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcSucursal) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcSucursal filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getSucursal()))
			filtro.setSucursal(null);
		if(UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setSucursal(null);
		if(UString.esNuloVacio(filtro.getEstado()))
			filtro.setSucursal(null);
		// TODO AcSucursal.listarPaginado : modificar query / modificar propiedades

		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, filtro.getSucursal()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("acsucursal.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "acsucursal.listarPaginadoSentencia",DtlComunAcSucursal.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarSucursales( FiltroComunAcSucursal filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getSucursal()))
			filtro.setSucursal("");
		// TODO AcSucursal.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_sucursal", String.class, filtro.getSucursal()));
		List lst = listarPorQuery(DtlComunAcSucursal.class, "acsucursal.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
