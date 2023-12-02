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
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExternoPk;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEstablecimientoexterno;

@Repository
public class RtpsEmpleadoEstablecimientoDaoImpl extends GenericoDaoImpl<BeanRtpsEmpleadoEstablecimiento, BeanRtpsEmpleadoEstablecimientoPk> {

	private static Logger logger = LogManager.getLogger(BeanRtpsEmpleadoEstablecimiento.class);

	public RtpsEmpleadoEstablecimientoDaoImpl() {
		super("rtpsempleadoestablecimiento");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanRtpsEmpleadoEstablecimiento obtenerPorId(Integer pempleado, String companiasocio, Integer pcodigoautomatico) {
		return obtenerPorId(new BeanRtpsEmpleadoEstablecimientoPk( pempleado, companiasocio, pcodigoautomatico));
	}

	public BeanRtpsEmpleadoEstablecimiento coreInsertar(BeanRtpsEmpleadoEstablecimiento bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanRtpsEmpleadoEstablecimiento coreActualizar(BeanRtpsEmpleadoEstablecimiento bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunRtpsEmpleadoEstablecimiento obtenerDtoCore(DtoComunRtpsEmpleadoEstablecimiento pk) throws Exception {
		DtoComunRtpsEmpleadoEstablecimiento dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunRtpsEmpleadoEstablecimiento();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunRtpsEmpleadoEstablecimiento> listarDtoCore(DtoComunRtpsEmpleadoEstablecimiento filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO RtpsEstablecimientoexterno.listarDtoCore : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getCodigoautomatico()))
			filtro.setCodigoautomatico(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_codigoautomatico", Integer.class, filtro.getCodigoautomatico()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunRtpsEmpleadoEstablecimiento.class, "rtpsestablecimientoexterno.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunRtpsEmpleadoEstablecimiento obtenerDto(DtoComunRtpsEmpleadoEstablecimiento pk) {
		DtoComunRtpsEmpleadoEstablecimiento dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigoautomatico", Integer.class, pk.getCodigoautomatico()));

		List lst = listarPorQuery(DtoComunRtpsEmpleadoEstablecimiento.class, "rtpsestablecimientoexterno.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunRtpsEmpleadoEstablecimiento) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunRtpsEmpleadoEstablecimiento filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO RtpsEstablecimientoexterno.listarPaginado : modificar query / modificar propiedades
		if (UInteger.esCeroOrNulo(filtro.getEmpleado()))
			filtro.setEmpleado(null);
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(null);

		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));

		//Integer registros = contar("rtpsempleadoestablecimiento.listarPaginadoContar", parametros);

		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "rtpsempleadoestablecimiento.listarPaginadoSentencia",DtoComunRtpsEmpleadoEstablecimiento.class);
		logger.debug(lst.size());
		Integer registros = lst.size();
		logger.debug(registros);
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
		List datos = this.listarPorQuery(DtoTabla.class, "rtpsempleadoestablecimiento.listaCortaPorNombre", parametros);
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
