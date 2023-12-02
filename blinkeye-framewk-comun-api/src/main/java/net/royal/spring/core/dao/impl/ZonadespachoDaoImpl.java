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
import net.royal.spring.core.dominio.BeanZonadespacho;
import net.royal.spring.core.dominio.BeanZonadespachoPk;
import net.royal.spring.core.dominio.dto.DtoComunZonadespacho;
import net.royal.spring.core.dominio.filtro.FiltroComunZonadespacho;
import net.royal.spring.core.dominio.lista.DtlComunZonadespacho;

@Repository
public class ZonadespachoDaoImpl extends GenericoDaoImpl<BeanZonadespacho, BeanZonadespachoPk> {

	private static Logger logger = LogManager.getLogger(BeanZonadespacho.class);

	public ZonadespachoDaoImpl() {
		super("zonadespacho");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanZonadespacho obtenerPorId(String pzonadespacho) {
		return obtenerPorId(new BeanZonadespachoPk( pzonadespacho));
	}

	public BeanZonadespacho coreInsertar(BeanZonadespacho bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanZonadespacho coreActualizar(BeanZonadespacho bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunZonadespacho obtenerDtoCore(DtoComunZonadespacho pk) throws Exception {
		DtoComunZonadespacho dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunZonadespacho();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunZonadespacho> listarDtoCore(DtoComunZonadespacho filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Zonadespacho.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_zonadespacho", String.class, filtro.getZonadespacho()));

		List datos = this.listarPorQuery(DtoComunZonadespacho.class, "zonadespacho.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunZonadespacho obtenerDto(DtoComunZonadespacho pk) {
		DtoComunZonadespacho dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_zonadespacho", String.class, pk.getZonadespacho()));

		List lst = listarPorQuery(DtoComunZonadespacho.class, "zonadespacho.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunZonadespacho) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunZonadespacho filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Zonadespacho.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_zonadespacho", String.class, filtro.getZonadespacho()));

		Integer registros = contar("zonadespacho.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "zonadespacho.listarPaginadoSentencia",DtlComunZonadespacho.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
