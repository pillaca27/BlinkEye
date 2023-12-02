package net.royal.spring.sistema.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexos;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexosPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyDocumentoanexos;

@Repository
public class SyDocumentoanexosDaoImpl extends GenericoDaoImpl<BeanSyDocumentoanexos, BeanSyDocumentoanexosPk> {

	private static Logger logger = LogManager.getLogger(BeanSyDocumentoanexos.class);

	public SyDocumentoanexosDaoImpl() {
		super("sydocumentoanexos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyDocumentoanexos obtenerPorId(String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) {
		return obtenerPorId(new BeanSyDocumentoanexosPk( pcompaniasocio, pmodulo, ptipodocumento, pnumerodocumento, plinea, psecuencia));
	}

	public BeanSyDocumentoanexos coreInsertar(BeanSyDocumentoanexos bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyDocumentoanexos coreActualizar(BeanSyDocumentoanexos bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyDocumentoanexos obtenerDtoCore(DtoComunSyDocumentoanexos pk) throws Exception {
		DtoComunSyDocumentoanexos dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSyDocumentoanexos();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSyDocumentoanexos> listarDtoCore(DtoComunSyDocumentoanexos filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyDocumentoanexos.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);
		if (UString.esNuloVacio(filtro.getModulo()))
			filtro.setModulo(null);
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);
		if (UString.esNuloVacio(filtro.getNumerodocumento()))
			filtro.setNumerodocumento(null);
		if (UInteger.esCeroOrNulo(filtro.getLinea()))
			filtro.setLinea(null);
		if (UInteger.esCeroOrNulo(filtro.getSecuencia()))
			filtro.setSecuencia(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_modulo", String.class, filtro.getModulo()));
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
		parametros.add(new DominioParametroPersistencia("p_numerodocumento", String.class, filtro.getNumerodocumento()));
		parametros.add(new DominioParametroPersistencia("p_linea", Integer.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunSyDocumentoanexos.class, "sydocumentoanexos.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSyDocumentoanexos obtenerDto(DtoComunSyDocumentoanexos pk) {
		DtoComunSyDocumentoanexos dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, pk.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_modulo", String.class, pk.getModulo()));
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, pk.getTipodocumento()));
		parametros.add(new DominioParametroPersistencia("p_numerodocumento", String.class, pk.getNumerodocumento()));
		parametros.add(new DominioParametroPersistencia("p_linea", Integer.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, pk.getSecuencia()));

		List lst = listarPorQuery(DtoComunSyDocumentoanexos.class, "sydocumentoanexos.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyDocumentoanexos) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyDocumentoanexos filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyDocumentoanexos.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);
		if (UString.esNuloVacio(filtro.getModulo()))
			filtro.setModulo(null);
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);
		if (UString.esNuloVacio(filtro.getNumerodocumento()))
			filtro.setNumerodocumento(null);
		if (UInteger.esCeroOrNulo(filtro.getLinea()))
			filtro.setLinea(null);
		if (UInteger.esCeroOrNulo(filtro.getSecuencia()))
			filtro.setSecuencia(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_modulo", String.class, filtro.getModulo()));
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
		parametros.add(new DominioParametroPersistencia("p_numerodocumento", String.class, filtro.getNumerodocumento()));
		parametros.add(new DominioParametroPersistencia("p_linea", Integer.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", Integer.class, filtro.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("sydocumentoanexos.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "sydocumentoanexos.listarPaginadoSentencia",DtoComunSyDocumentoanexos.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "sydocumentoanexos.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

	@Transactional
	public Integer generarSecuencia(DtoComunSyDocumentoanexos dto) {
		Criteria c = this
				.getCriteria()
				.add(Restrictions.eq("pk.companiasocio", dto.getCompaniasocio()))
				.add(Restrictions.eq("pk.modulo", dto.getModulo()))
				.add(Restrictions.eq("pk.tipodocumento", dto.getTipodocumento()))
				.add(Restrictions.eq("pk.numerodocumento", dto.getNumerodocumento()))
				.add(Restrictions.eq("pk.linea", dto.getLinea()))
				.setProjection(
						Projections.projectionList().add(
								Projections.max("pk.secuencia")));
		return this.incrementarInteger(c);
	}
	
	public String obtenerParametroValorExplicacion(String parametroClave, String aplicacionCodigo) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionCodigo));
		parametros.add(new DominioParametroPersistencia("p_parametro", String.class, parametroClave));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, "999999"));

		List query = this.listarPorQuery(DtoTabla.class, "sydocumentoanexos.obtenerParametroValorExplicacion",
				parametros);

		if (query.size() != 1) {
			return null;
		}

		return ((List<DtoTabla>) query).get(0).getDescripcion();
	}
	
}
