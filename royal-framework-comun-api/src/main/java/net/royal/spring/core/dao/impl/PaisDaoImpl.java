package net.royal.spring.core.dao.impl;

import java.math.BigDecimal;
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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanPais;
import net.royal.spring.core.dominio.BeanPaisPk;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.dominio.filtro.FiltroComunPais;

@Repository
public class PaisDaoImpl extends GenericoDaoImpl<BeanPais, BeanPaisPk> {

	private static Logger logger = LogManager.getLogger(BeanPais.class);

	public PaisDaoImpl() {
		super("pais");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanPais obtenerPorId(String ppais) {
		return obtenerPorId(new BeanPaisPk( ppais));
	}

	public BeanPais coreInsertar(BeanPais bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanPais coreActualizar(BeanPais bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunPais obtenerDtoCore(DtoComunPais pk) throws Exception {
		DtoComunPais dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunPais();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunPais> listarDtoCore(DtoComunPais filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Pais.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getPais()))
			filtro.setPais(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		List datos = this.listarPorQuery(DtoComunPais.class, "pais.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunPais obtenerDto(DtoComunPais pk) {
		DtoComunPais dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_pais", String.class, pk.getPais()));

		List lst = listarPorQuery(DtoComunPais.class, "pais.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunPais) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunPais filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Pais.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getPais()))
			filtro.setPais(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_pais", String.class, filtro.getPais()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		Integer registros = contar("pais.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "pais.listarPaginadoSentencia",DtoComunPais.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public List<DtoTabla> listaCortaPorNombre(DtoTabla filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		parametros.add(new DominioParametroPersistencia("p_sidid", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "pais.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
