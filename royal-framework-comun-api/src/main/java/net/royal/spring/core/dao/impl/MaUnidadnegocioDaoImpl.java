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
import net.royal.spring.core.dominio.BeanMaUnidadnegocio;
import net.royal.spring.core.dominio.BeanMaUnidadnegocioPk;
import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;
import net.royal.spring.core.dominio.filtro.FiltroComunMaUnidadnegocio;
import net.royal.spring.core.dominio.lista.DtlComunMaUnidadnegocio;

@Repository
public class MaUnidadnegocioDaoImpl extends GenericoDaoImpl<BeanMaUnidadnegocio, BeanMaUnidadnegocioPk> {

	private static Logger logger = LogManager.getLogger(BeanMaUnidadnegocio.class);

	public MaUnidadnegocioDaoImpl() {
		super("maunidadnegocio");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaUnidadnegocio obtenerPorId(String punidadnegocio) {
		return obtenerPorId(new BeanMaUnidadnegocioPk( punidadnegocio));
	}

	public BeanMaUnidadnegocio coreInsertar(BeanMaUnidadnegocio bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaUnidadnegocio coreActualizar(BeanMaUnidadnegocio bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaUnidadnegocio obtenerDtoCore(DtoComunMaUnidadnegocio pk) throws Exception {
		DtoComunMaUnidadnegocio dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunMaUnidadnegocio();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunMaUnidadnegocio> listarDtoCore(DtoComunMaUnidadnegocio filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaUnidadnegocio.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadnegocio()));

		List datos = this.listarPorQuery(DtoComunMaUnidadnegocio.class, "maunidadnegocio.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunMaUnidadnegocio obtenerDto(DtoComunMaUnidadnegocio pk) {
		DtoComunMaUnidadnegocio dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, pk.getUnidadnegocio()));

		List lst = listarPorQuery(DtoComunMaUnidadnegocio.class, "maunidadnegocio.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaUnidadnegocio) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunMaUnidadnegocio filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getUnidadnegocio()))
			filtro.setUnidadnegocio("");
		
		// TODO MaUnidadnegocio.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadnegocio()));

		Integer registros = contar("maunidadnegocio.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "maunidadnegocio.listarPaginadoSentencia",DtlComunMaUnidadnegocio.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarUnidadNegocio(FiltroComunMaUnidadnegocio filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getUnidadnegocio()))
			filtro.setUnidadnegocio("");
		
		// TODO MaUnidadnegocio.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_unidadnegocio", String.class, filtro.getUnidadnegocio()));

		List lst = listarPorQuery(DtlComunMaUnidadnegocio.class, "maunidadnegocio.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
