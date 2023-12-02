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
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExternoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEstablecimientoexterno;

@Repository
public class RtpsEmpleadoEstablecimientoExternoDaoImpl extends GenericoDaoImpl<BeanRtpsEmpleadoEstablecimientoExterno, BeanRtpsEmpleadoEstablecimientoExternoPk> {

	private static Logger logger = LogManager.getLogger(BeanRtpsEmpleadoEstablecimientoExterno.class);

	public RtpsEmpleadoEstablecimientoExternoDaoImpl() {
		super("rtpsempleadoestablecimientoexterno");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanRtpsEmpleadoEstablecimientoExterno obtenerPorId(Integer pcodigoautomatico) {
		return obtenerPorId(new BeanRtpsEmpleadoEstablecimientoExternoPk( pcodigoautomatico));
	}

	public BeanRtpsEmpleadoEstablecimientoExterno coreInsertar(BeanRtpsEmpleadoEstablecimientoExterno bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanRtpsEmpleadoEstablecimientoExterno coreActualizar(BeanRtpsEmpleadoEstablecimientoExterno bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunRtpsEmpleadoEstablecimientoExterno obtenerDtoCore(DtoComunRtpsEmpleadoEstablecimientoExterno pk) throws Exception {
		DtoComunRtpsEmpleadoEstablecimientoExterno dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunRtpsEmpleadoEstablecimientoExterno();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunRtpsEmpleadoEstablecimientoExterno> listarDtoCore(DtoComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO RtpsEstablecimientoexterno.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getCodigoautomatico()))
			filtro.setCodigoautomatico(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_codigoautomatico", Integer.class, filtro.getCodigoautomatico()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunRtpsEstablecimientoexterno.class, "rtpsempleadoestablecimientoexterno.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunRtpsEmpleadoEstablecimientoExterno obtenerDto(DtoComunRtpsEmpleadoEstablecimientoExterno pk) {
		DtoComunRtpsEmpleadoEstablecimientoExterno dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigoautomatico", Integer.class, pk.getCodigoautomatico()));

		List lst = listarPorQuery(DtoComunRtpsEmpleadoEstablecimientoExterno.class, "rtpsempleadoestablecimientoexterno.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunRtpsEmpleadoEstablecimientoExterno) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunRtpsEmpleadoEstablecimientoExterno filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();


		if (UInteger.esCeroOrNulo(filtro.getEmpleado()))
			filtro.setEmpleado(null);
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);

		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));

		Integer registros = contar("rtpsempleadoestablecimientoexterno.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "rtpsempleadoestablecimientoexterno.listarPaginadoSentencia",DtoComunRtpsEmpleadoEstablecimientoExterno.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsempleadoestablecimientoexterno.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	
	@Transactional
	public Integer generarSecuencia() {
		Criteria c = this.getCriteria()
				.setProjection(
						Projections.projectionList().add(
								Projections.max("pk.codigoautomatico")));
		return this.incrementarInteger(c);
	}
	

}
