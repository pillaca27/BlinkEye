package net.royal.spring.contabilidad.dao.impl;

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
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafePk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcCostcenterafe;

@Repository
public class AcCostcenterafeDaoImpl extends GenericoDaoImpl<BeanAcCostcenterafe, BeanAcCostcenterafePk> {

	private static Logger logger = LogManager.getLogger(BeanAcCostcenterafe.class);

	public AcCostcenterafeDaoImpl() {
		super("accostcenterafe");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcCostcenterafe obtenerPorId(String pcostcenter,String pafe) {
		return obtenerPorId(new BeanAcCostcenterafePk( pcostcenter, pafe));
	}

	public BeanAcCostcenterafe coreInsertar(BeanAcCostcenterafe bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcCostcenterafe coreActualizar(BeanAcCostcenterafe bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcCostcenterafe obtenerDtoCore(DtoComunAcCostcenterafe pk) throws Exception {
		DtoComunAcCostcenterafe dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcCostcenterafe();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcCostcenterafe> listarDtoCore(DtoComunAcCostcenterafe filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenterafe.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));

		List datos = this.listarPorQuery(DtoComunAcCostcenterafe.class, "accostcenterafe.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcCostcenterafe obtenerDto(DtoComunAcCostcenterafe pk) {
		DtoComunAcCostcenterafe dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, pk.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, pk.getAfe()));

		List lst = listarPorQuery(DtoComunAcCostcenterafe.class, "accostcenterafe.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcCostcenterafe) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcenterafe filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenterafe.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));

		Integer registros = contar("accostcenterafe.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcenterafe.listarPaginadoSentencia",DtlComunAcCostcenterafe.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
