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
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalidPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcCostcenterdestvalid;

@Repository
public class AcCostcenterdestvalidDaoImpl extends GenericoDaoImpl<BeanAcCostcenterdestvalid, BeanAcCostcenterdestvalidPk> {

	private static Logger logger = LogManager.getLogger(BeanAcCostcenterdestvalid.class);

	public AcCostcenterdestvalidDaoImpl() {
		super("accostcenterdestvalid");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcCostcenterdestvalid obtenerPorId(String pcostcenter,String pcostcenterdestination) {
		return obtenerPorId(new BeanAcCostcenterdestvalidPk( pcostcenter, pcostcenterdestination));
	}

	public BeanAcCostcenterdestvalid coreInsertar(BeanAcCostcenterdestvalid bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcCostcenterdestvalid coreActualizar(BeanAcCostcenterdestvalid bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcCostcenterdestvalid obtenerDtoCore(DtoComunAcCostcenterdestvalid pk) throws Exception {
		DtoComunAcCostcenterdestvalid dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcCostcenterdestvalid();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcCostcenterdestvalid> listarDtoCore(DtoComunAcCostcenterdestvalid filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenterdestvalid.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));

		List datos = this.listarPorQuery(DtoComunAcCostcenterdestvalid.class, "accostcenterdestvalid.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcCostcenterdestvalid obtenerDto(DtoComunAcCostcenterdestvalid pk) {
		DtoComunAcCostcenterdestvalid dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, pk.getCostcenterdestination()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, pk.getCostcenterdestination()));

		List lst = listarPorQuery(DtoComunAcCostcenterdestvalid.class, "accostcenterdestvalid.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcCostcenterdestvalid) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcenterdestvalid filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenterdestvalid.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));

		Integer registros = contar("accostcenterdestvalid.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcenterdestvalid.listarPaginadoSentencia",DtlComunAcCostcenterdestvalid.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
