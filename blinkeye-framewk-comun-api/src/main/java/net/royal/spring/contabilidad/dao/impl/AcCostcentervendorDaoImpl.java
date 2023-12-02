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
import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendorPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcCostcentervendor;

@Repository
public class AcCostcentervendorDaoImpl extends GenericoDaoImpl<BeanAcCostcentervendor, BeanAcCostcentervendorPk> {

	private static Logger logger = LogManager.getLogger(BeanAcCostcentervendor.class);

	public AcCostcentervendorDaoImpl() {
		super("accostcentervendor");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcCostcentervendor obtenerPorId(String pcostcenter,Integer pvendor) {
		return obtenerPorId(new BeanAcCostcentervendorPk( pcostcenter, pvendor));
	}

	public BeanAcCostcentervendor coreInsertar(BeanAcCostcentervendor bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcCostcentervendor coreActualizar(BeanAcCostcentervendor bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcCostcentervendor obtenerDtoCore(DtoComunAcCostcentervendor pk) throws Exception {
		DtoComunAcCostcentervendor dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcCostcentervendor();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcCostcentervendor> listarDtoCore(DtoComunAcCostcentervendor filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcentervendor.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, filtro.getVendor()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, filtro.getVendor()));

		List datos = this.listarPorQuery(DtoComunAcCostcentervendor.class, "accostcentervendor.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcCostcentervendor obtenerDto(DtoComunAcCostcentervendor pk) {
		DtoComunAcCostcentervendor dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, pk.getVendor()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, pk.getVendor()));

		List lst = listarPorQuery(DtoComunAcCostcentervendor.class, "accostcentervendor.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcCostcentervendor) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcentervendor filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcentervendor.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, filtro.getVendor()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_vendor", String.class, filtro.getVendor()));

		Integer registros = contar("accostcentervendor.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentervendor.listarPaginadoSentencia",DtlComunAcCostcentervendor.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
