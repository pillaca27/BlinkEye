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
import net.royal.spring.core.dominio.BeanServicioximpuesto;
import net.royal.spring.core.dominio.BeanServicioximpuestoPk;
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;
import net.royal.spring.core.dominio.filtro.FiltroComunServicioximpuesto;
import net.royal.spring.core.dominio.lista.DtlComunServicioximpuesto;

@Repository
public class ServicioximpuestoDaoImpl extends GenericoDaoImpl<BeanServicioximpuesto, BeanServicioximpuestoPk> {

	private static Logger logger = LogManager.getLogger(BeanServicioximpuesto.class);

	public ServicioximpuestoDaoImpl() {
		super("servicioximpuesto");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanServicioximpuesto obtenerPorId(String ptiposervicio,String pimpuesto) {
		return obtenerPorId(new BeanServicioximpuestoPk( ptiposervicio, pimpuesto));
	}

	public BeanServicioximpuesto coreInsertar(BeanServicioximpuesto bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanServicioximpuesto coreActualizar(BeanServicioximpuesto bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunServicioximpuesto obtenerDtoCore(DtoComunServicioximpuesto pk) throws Exception {
		DtoComunServicioximpuesto dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunServicioximpuesto();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunServicioximpuesto> listarDtoCore(DtoComunServicioximpuesto filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Servicioximpuesto.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));

		List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunServicioximpuesto obtenerDto(DtoComunServicioximpuesto pk) {
		DtoComunServicioximpuesto dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, pk.getImpuesto()));
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, pk.getImpuesto()));

		List lst = listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunServicioximpuesto) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunServicioximpuesto filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Servicioximpuesto.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));

		Integer registros = contar("servicioximpuesto.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "servicioximpuesto.listarPaginadoSentencia",DtlComunServicioximpuesto.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
