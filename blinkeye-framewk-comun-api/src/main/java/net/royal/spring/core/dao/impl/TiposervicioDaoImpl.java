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
import net.royal.spring.core.dominio.BeanTiposervicio;
import net.royal.spring.core.dominio.BeanTiposervicioPk;
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;
import net.royal.spring.core.dominio.dto.DtoComunTiposervicio;
import net.royal.spring.core.dominio.filtro.FiltroComunTiposervicio;
import net.royal.spring.core.dominio.lista.DtlComunTiposervicio;

@Repository
public class TiposervicioDaoImpl extends GenericoDaoImpl<BeanTiposervicio, BeanTiposervicioPk> {

	private static Logger logger = LogManager.getLogger(BeanTiposervicio.class);

	public TiposervicioDaoImpl() {
		super("tiposervicio");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanTiposervicio obtenerPorId(String ptiposervicio) {
		return obtenerPorId(new BeanTiposervicioPk( ptiposervicio));
	}

	public DtoComunTiposervicio obtenerDtoPorUuid(String uuid) {
		DtoComunTiposervicio dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunTiposervicio) lst.get(0);
			parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, dto.getTiposervicio()));
			List lstDetalle = listarPorQuery(DtoComunServicioximpuesto.class, "tiposervicio.obtenerDtoDetalle", parametros);
			if(lstDetalle.size()>0) {
				dto.setLstDetalle(lstDetalle);
			}
		}
		return dto;
	}
	
	public BeanTiposervicio coreInsertar(BeanTiposervicio bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanTiposervicio coreActualizar(BeanTiposervicio bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunTiposervicio obtenerDtoCore(DtoComunTiposervicio pk) throws Exception {
		DtoComunTiposervicio dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunTiposervicio();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunTiposervicio> listarDtoCore(DtoComunTiposervicio filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tiposervicio.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));

		List datos = this.listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunTiposervicio obtenerDto(DtoComunTiposervicio pk) {
		DtoComunTiposervicio dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));

		List lst = listarPorQuery(DtoComunTiposervicio.class, "tiposervicio.obtenerDto", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunTiposervicio) lst.get(0);
			
			List lstDetalle = listarPorQuery(DtoComunServicioximpuesto.class, "tiposervicio.obtenerDtoDetalle", parametros);
			if(lstDetalle.size()>0) {
				dto.setLstDetalle(lstDetalle);
			}
		}

		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunTiposervicio filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tiposervicio.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getTiposervicio()))
			filtro.setTiposervicio("");
		
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class,filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class,filtro.getEstado()));

		Integer registros = contar("tiposervicio.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "tiposervicio.listarPaginadoSentencia",DtlComunTiposervicio.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarTipoServicio(FiltroComunTiposervicio filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tiposervicio.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getTiposervicio()))
			filtro.setTiposervicio("");
		
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		List lst = listarPorQuery(DtlComunTiposervicio.class, "tiposervicio.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
