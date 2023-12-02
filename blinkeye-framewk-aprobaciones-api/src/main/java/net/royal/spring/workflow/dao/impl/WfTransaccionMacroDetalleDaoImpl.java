package net.royal.spring.workflow.dao.impl;

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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionMacroDetalle;
import net.royal.spring.workflow.dominio.WfTransaccionMacroDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroDetalle;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroDetalleFiltro;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroDetalleListado;

@Repository
public class WfTransaccionMacroDetalleDaoImpl extends GenericoDaoImpl<WfTransaccionMacroDetalle, WfTransaccionMacroDetallePk> {

	private static Logger logger = LogManager.getLogger(WfTransaccionMacroDetalle.class);

	public WfTransaccionMacroDetalleDaoImpl() {
		super("wftransaccionmacrodetalle");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfTransaccionMacroDetalle obtenerPorId(Integer ptransaccionMacroId,Integer ptransaccionMacroDetalleId) {
		return obtenerPorId(new WfTransaccionMacroDetallePk( ptransaccionMacroId, ptransaccionMacroDetalleId));
	}

	public WfTransaccionMacroDetalle coreInsertar(WfTransaccionMacroDetalle bean) {
		this.registrar(bean);
		return bean;
	}


	public WfTransaccionMacroDetalle coreActualizar(WfTransaccionMacroDetalle bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoWfTransaccionMacroDetalle obtenerDtoPorId(DtoWfTransaccionMacroDetalle pk) {
		DtoWfTransaccionMacroDetalle dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_transaccionmacroid", String.class, pk.getTransaccionMacroId()));
		parametros.add(new DominioParametroPersistencia("p_transaccionmacrodetalleid", String.class, pk.getTransaccionMacroDetalleId()));


		List lst = listarPorQuery(DtoWfTransaccionMacroDetalle.class, "wftransaccionmacrodetalle.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoWfTransaccionMacroDetalle) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoWfTransaccionMacroDetalleFiltro filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfTransaccionMacroDetalle.Paginacion
		//parametros.add(new DominioParametroPersistencia("p_transaccionmacroid", String.class, filtro.getTransaccionMacroId()));
		//parametros.add(new DominioParametroPersistencia("p_transaccionmacrodetalleid", String.class, filtro.getTransaccionMacroDetalleId()));


		Integer registros = contar("wftransaccionmacrodetalle.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wftransaccionmacrodetalle.listarPaginadoSentencia",DtoWfTransaccionMacroDetalleListado.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
