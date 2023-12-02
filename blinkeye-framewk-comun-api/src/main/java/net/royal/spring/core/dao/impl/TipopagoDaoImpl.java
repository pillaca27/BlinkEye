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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
import net.royal.spring.core.dominio.BeanTipopago;
import net.royal.spring.core.dominio.BeanTipopagoPk;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunTipopago;
import net.royal.spring.core.dominio.lista.DtlComunTipopago;

@Repository
public class TipopagoDaoImpl extends GenericoDaoImpl<BeanTipopago, BeanTipopagoPk> {

	private static Logger logger = LogManager.getLogger(BeanTipopago.class);

	public TipopagoDaoImpl() {
		super("tipopago");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanTipopago obtenerPorId(String ptipopago) {
		return obtenerPorId(new BeanTipopagoPk( ptipopago));
	}

	public BeanTipopago coreInsertar(BeanTipopago bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanTipopago coreActualizar(BeanTipopago bean) {
		this.actualizar(bean);
		return bean;
	}
	public DtoComunTipopago obtenerDtoPorUuid(String uuid) {
		DtoComunTipopago dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunTipopago.class, "tipopago.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunTipopago) lst.get(0); 
		}
		return dto;
	}
	
	public DtoComunTipopago obtenerDtoCore(DtoComunTipopago pk) throws Exception {
		DtoComunTipopago dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunTipopago();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunTipopago> listarDtoCore(DtoComunTipopago filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tipopago.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));

		List datos = this.listarPorQuery(DtoComunTipopago.class, "tipopago.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunTipopago obtenerDto(DtoComunTipopago pk) {
		DtoComunTipopago dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, pk.getTipopago()));

		List lst = listarPorQuery(DtoComunTipopago.class, "tipopago.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunTipopago) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunTipopago filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tipopago.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getTipopago()))
			filtro.setTipopago(null);
		
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("tipopago.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "tipopago.listarPaginadoSentencia",DtlComunTipopago.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarTipoPago(FiltroComunTipopago filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Tipopago.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getTipopago()))
			filtro.setTipopago(null);
		
		parametros.add(new DominioParametroPersistencia("p_tipopago", String.class, filtro.getTipopago()));
		List lst = listarPorQuery(DtlComunTipopago.class, "tipopago.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
}
