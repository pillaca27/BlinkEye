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
import net.royal.spring.core.dominio.BeanBanco;
import net.royal.spring.core.dominio.BeanBancoPk;
import net.royal.spring.core.dominio.dto.DtoComunBanco;
import net.royal.spring.core.dominio.filtro.FiltroComunBanco;
import net.royal.spring.core.dominio.lista.DtlComunBanco;

@Repository
public class BancoDaoImpl extends GenericoDaoImpl<BeanBanco, BeanBancoPk> {

	private static Logger logger = LogManager.getLogger(BeanBanco.class);

	public BancoDaoImpl() {
		super("banco");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanBanco obtenerPorId(String pbanco) {
		return obtenerPorId(new BeanBancoPk( pbanco));
	}

	public DtoComunBanco obtenerDtoPorUuid(String uuid) {
		DtoComunBanco dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunBanco.class, "banco.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunBanco) lst.get(0); 
		}
		return dto;
	}
	
	public BeanBanco coreInsertar(BeanBanco bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanBanco coreActualizar(BeanBanco bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunBanco obtenerDtoCore(DtoComunBanco pk) throws Exception {
		DtoComunBanco dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunBanco();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunBanco> listarDtoCore(DtoComunBanco filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_banco", String.class, filtro.getBanco()));
		parametros.add(new DominioParametroPersistencia("p_banco", String.class, filtro.getBanco()));

		List datos = this.listarPorQuery(DtoComunBanco.class, "banco.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunBanco obtenerDto(DtoComunBanco pk) {
		DtoComunBanco dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_banco", String.class, pk.getBanco()));
		List lst = listarPorQuery(DtoComunBanco.class, "banco.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunBanco) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunBanco filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		if (UString.esNuloVacio(filtro.getBanco()))
			filtro.setBanco(null);
		// TODO Banco.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_banco", String.class,filtro.getBanco()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class,filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class,filtro.getEstado()));

		Integer registros = contar("banco.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "banco.listarPaginadoSentencia",DtlComunBanco.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarBancos(FiltroComunBanco filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		if (UString.esNuloVacio(filtro.getBanco()))
			filtro.setBanco("0");
		// TODO Banco.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_banco", String.class,filtro.getBanco()));

		List lst = listarPorQuery(DtlComunBanco.class, "banco.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
