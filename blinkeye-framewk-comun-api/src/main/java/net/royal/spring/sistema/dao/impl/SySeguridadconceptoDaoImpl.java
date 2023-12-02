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
import net.royal.spring.sistema.dominio.BeanSySeguridadconcepto;
import net.royal.spring.sistema.dominio.BeanSySeguridadconceptoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSySeguridadconcepto;

@Repository
public class SySeguridadconceptoDaoImpl extends GenericoDaoImpl<BeanSySeguridadconcepto, BeanSySeguridadconceptoPk> {

	private static Logger logger = LogManager.getLogger(BeanSySeguridadconcepto.class);

	public SySeguridadconceptoDaoImpl() {
		super("syseguridadconcepto");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSySeguridadconcepto obtenerPorId(String paplicacioncodigo,String pgrupo,String pconcepto) {
		return obtenerPorId(new BeanSySeguridadconceptoPk( paplicacioncodigo, pgrupo, pconcepto));
	}

	public BeanSySeguridadconcepto coreInsertar(BeanSySeguridadconcepto bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSySeguridadconcepto coreActualizar(BeanSySeguridadconcepto bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSySeguridadconcepto obtenerDtoCore(DtoComunSySeguridadconcepto pk) throws Exception {
		DtoComunSySeguridadconcepto dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSySeguridadconcepto();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSySeguridadconcepto> listarDtoCore(DtoComunSySeguridadconcepto filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SySeguridadconcepto.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getGrupo()))
			filtro.setGrupo(null);
		if (UString.esNuloVacio(filtro.getConcepto()))
			filtro.setConcepto(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, filtro.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunSySeguridadconcepto.class, "syseguridadconcepto.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSySeguridadconcepto obtenerDto(DtoComunSySeguridadconcepto pk) {
		DtoComunSySeguridadconcepto dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, pk.getConcepto()));

		List lst = listarPorQuery(DtoComunSySeguridadconcepto.class, "syseguridadconcepto.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSySeguridadconcepto) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSySeguridadconcepto filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SySeguridadconcepto.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getGrupo()))
			filtro.setGrupo(null);
		if (UString.esNuloVacio(filtro.getConcepto()))
			filtro.setConcepto(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, filtro.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("syseguridadconcepto.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syseguridadconcepto.listarPaginadoSentencia",DtoComunSySeguridadconcepto.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "syseguridadconcepto.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
