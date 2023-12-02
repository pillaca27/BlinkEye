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
import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopico;
import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopicoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyAplicacionreportetopico;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyAplicacionreportetopico;

@Repository
public class SyAplicacionreportetopicoDaoImpl extends GenericoDaoImpl<BeanSyAplicacionreportetopico, BeanSyAplicacionreportetopicoPk> {

	private static Logger logger = LogManager.getLogger(BeanSyAplicacionreportetopico.class);

	public SyAplicacionreportetopicoDaoImpl() {
		super("syaplicacionreportetopico");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

 
	public BeanSyAplicacionreportetopico obtenerPorId(String paplicacioncodigo,String ptopico) {
		return obtenerPorId(new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	public BeanSyAplicacionreportetopico coreInsertar(BeanSyAplicacionreportetopico bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyAplicacionreportetopico coreActualizar(BeanSyAplicacionreportetopico bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyAplicacionreportetopico obtenerDtoCore(DtoComunSyAplicacionreportetopico pk) throws Exception {
		DtoComunSyAplicacionreportetopico dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSyAplicacionreportetopico();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoComunSyAplicacionreportetopico obtenerDto(DtoComunSyAplicacionreportetopico pk) {
		DtoComunSyAplicacionreportetopico dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_topico", String.class, pk.getTopico()));

		List lst = listarPorQuery(DtoComunSyAplicacionreportetopico.class, "syaplicacionreportetopico.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyAplicacionreportetopico) lst.get(0);
		return dto;
	}

	public DtoComunSyAplicacionreportetopico obtenerDtoPorUuid(String uuid) {
		DtoComunSyAplicacionreportetopico dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunSyAplicacionreportetopico.class, "syaplicacionreportetopico.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyAplicacionreportetopico) lst.get(0);
		return dto;
	}

	public List<DtoComunSyAplicacionreportetopico> listarDtoCore(DtoComunSyAplicacionreportetopico filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyAplicacionreportetopico.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getTopico()))
			filtro.setTopico(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_topico", String.class, filtro.getTopico()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunSyAplicacionreportetopico.class, "syaplicacionreportetopico.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyAplicacionreportetopico filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyAplicacionreportetopico.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getTopico()))
			filtro.setTopico(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_topico", String.class, filtro.getTopico()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("syaplicacionreportetopico.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syaplicacionreportetopico.listarPaginadoSentencia",DtoComunSyAplicacionreportetopico.class);
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
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "syaplicacionreportetopico.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
