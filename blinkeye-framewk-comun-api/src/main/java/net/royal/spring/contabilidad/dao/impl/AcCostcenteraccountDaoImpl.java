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
import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccountPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcCostcenteraccount;

@Repository
public class AcCostcenteraccountDaoImpl extends GenericoDaoImpl<BeanAcCostcenteraccount, BeanAcCostcenteraccountPk> {

	private static Logger logger = LogManager.getLogger(BeanAcCostcenteraccount.class);

	public AcCostcenteraccountDaoImpl() {
		super("accostcenteraccount");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcCostcenteraccount obtenerPorId(String pcostcenter,String paccount) {
		return obtenerPorId(new BeanAcCostcenteraccountPk( pcostcenter, paccount));
	}

	public BeanAcCostcenteraccount coreInsertar(BeanAcCostcenteraccount bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcCostcenteraccount coreActualizar(BeanAcCostcenteraccount bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcCostcenteraccount obtenerDtoCore(DtoComunAcCostcenteraccount pk) throws Exception {
		DtoComunAcCostcenteraccount dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcCostcenteraccount();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcCostcenteraccount> listarDtoCore(DtoComunAcCostcenteraccount filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenteraccount.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));

		List datos = this.listarPorQuery(DtoComunAcCostcenteraccount.class, "accostcenteraccount.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcCostcenteraccount obtenerDto(DtoComunAcCostcenteraccount pk) {
		DtoComunAcCostcenteraccount dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, pk.getAccount()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, pk.getAccount()));

		List lst = listarPorQuery(DtoComunAcCostcenteraccount.class, "accostcenteraccount.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcCostcenteraccount) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcenteraccount filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcenteraccount.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));

		Integer registros = contar("accostcenteraccount.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcenteraccount.listarPaginadoSentencia",DtlComunAcCostcenteraccount.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
