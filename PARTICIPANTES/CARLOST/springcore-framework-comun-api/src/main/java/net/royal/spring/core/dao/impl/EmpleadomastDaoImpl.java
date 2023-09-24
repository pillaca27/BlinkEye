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
import net.royal.spring.framework.core.UValidador;
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
import net.royal.spring.core.dominio.BeanEmpleadomast;
import net.royal.spring.core.dominio.BeanEmpleadomastPk;
import net.royal.spring.core.dominio.dto.DtoComunEmpleadomast;
import net.royal.spring.core.dominio.filtro.FiltroComunEmpleadomast;
import net.royal.spring.core.dominio.lista.DtlComunEmpleadomast;

@Repository
public class EmpleadomastDaoImpl extends GenericoDaoImpl<BeanEmpleadomast, BeanEmpleadomastPk> {

	private static Logger logger = LogManager.getLogger(BeanEmpleadomast.class);

	public EmpleadomastDaoImpl() {
		super("empleadomast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanEmpleadomast obtenerPorId(Integer pempleado, String pcompaniasocio) {
		return obtenerPorId(new BeanEmpleadomastPk(pempleado, pcompaniasocio));
	}

	public BeanEmpleadomast coreInsertar(BeanEmpleadomast bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanEmpleadomast coreActualizar(BeanEmpleadomast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunEmpleadomast obtenerDtoCore(DtoComunEmpleadomast pk) throws Exception {
		DtoComunEmpleadomast dto = obtenerDto(pk);
		if (dto == null) {
			dto = new DtoComunEmpleadomast();
			dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunEmpleadomast> listarDtoCore(DtoComunEmpleadomast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Empleadomast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_empleado", String.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_empleado", String.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));

		List datos = this.listarPorQuery(DtoComunEmpleadomast.class, "empleadomast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunEmpleadomast obtenerDto(DtoComunEmpleadomast pk) {
		DtoComunEmpleadomast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, pk.getEmpleado()));

		List lst = listarPorQuery(DtoComunEmpleadomast.class, "empleadomast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunEmpleadomast) lst.get(0);
		return dto;
	}

	public List<DtoTabla> obtenerDtoDetalle(BeanEmpleadomastPk pk) {
		DtoTabla dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, pk.getEmpleado()));

		List lst = listarPorQuery(DtoTabla.class, "empleadomast.obtenerDtoDetalleEliminar", parametros);

		return lst;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunEmpleadomast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Empleadomast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_empleado", String.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_empleado", String.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));

		Integer registros = contar("empleadomast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "empleadomast.listarPaginadoSentencia",
				DtlComunEmpleadomast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public BigDecimal obtenerMaximoEmpleado(String codigoUsuario) {
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("codigousuario", codigoUsuario.toUpperCase()));

		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.max("pk.empleado"));

		cri.setProjection(proj);

		List lista = this.listarPorCriterios(cri);

		if (UValidador.esListaVacia(lista))
			return null;

		Integer empl = (Integer) lista.get(0);

		return new BigDecimal(empl);
	}

}
