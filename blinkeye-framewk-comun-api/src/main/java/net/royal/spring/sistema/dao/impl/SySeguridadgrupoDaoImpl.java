package net.royal.spring.sistema.dao.impl;

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
import net.royal.spring.sistema.dominio.BeanSySeguridadgrupo;
import net.royal.spring.sistema.dominio.BeanSySeguridadgrupoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadgrupo;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSySeguridadgrupo;

@Repository
public class SySeguridadgrupoDaoImpl extends GenericoDaoImpl<BeanSySeguridadgrupo, BeanSySeguridadgrupoPk> {

	private static Logger logger = LogManager.getLogger(BeanSySeguridadgrupo.class);

	public SySeguridadgrupoDaoImpl() {
		super("syseguridadgrupo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSySeguridadgrupo obtenerPorId(String paplicacioncodigo,String pgrupo) {
		return obtenerPorId(new BeanSySeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

	public BeanSySeguridadgrupo coreInsertar(BeanSySeguridadgrupo bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSySeguridadgrupo coreActualizar(BeanSySeguridadgrupo bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSySeguridadgrupo obtenerDtoCore(DtoComunSySeguridadgrupo pk) throws Exception {
		DtoComunSySeguridadgrupo dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSySeguridadgrupo();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSySeguridadgrupo> listarDtoCore(DtoComunSySeguridadgrupo filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SySeguridadgrupo.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getGrupo()))
			filtro.setGrupo(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunSySeguridadgrupo.class, "syseguridadgrupo.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSySeguridadgrupo obtenerDto(DtoComunSySeguridadgrupo pk) {
		DtoComunSySeguridadgrupo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));

		List lst = listarPorQuery(DtoComunSySeguridadgrupo.class, "syseguridadgrupo.obtenerDto", parametros);
		List seguridadconceptos = listarPorQuery(DtoComunSySeguridadconcepto.class, "syseguridadgrupo.obtenerSeguridadConceptos", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunSySeguridadgrupo) lst.get(0);
			dto.setSeguridadconcepto(seguridadconceptos);
		}
			
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSySeguridadgrupo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SySeguridadgrupo.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getGrupo()))
			filtro.setGrupo(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("syseguridadgrupo.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syseguridadgrupo.listarPaginadoSentencia",DtoComunSySeguridadgrupo.class);
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
		//
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadgrupo.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
