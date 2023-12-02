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
import net.royal.spring.workflow.dominio.WfTransaccionMacro;
import net.royal.spring.workflow.dominio.WfTransaccionMacroPk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacro;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroFiltro;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroListado;

@Repository
public class WfTransaccionMacroDaoImpl extends GenericoDaoImpl<WfTransaccionMacro, WfTransaccionMacroPk> {

	private static Logger logger = LogManager.getLogger(WfTransaccionMacro.class);

	public WfTransaccionMacroDaoImpl() {
		super("wftransaccionmacro");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfTransaccionMacro obtenerPorId(Integer ptransaccionMacroId) {
		return obtenerPorId(new WfTransaccionMacroPk( ptransaccionMacroId));
	}

	public WfTransaccionMacro coreInsertar(WfTransaccionMacro bean) {
		this.registrar(bean);
		return bean;
	}


	public WfTransaccionMacro coreActualizar(WfTransaccionMacro bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoWfTransaccionMacro obtenerDtoPorId(DtoWfTransaccionMacro pk) {
		DtoWfTransaccionMacro dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_transaccionmacroid", String.class, pk.getTransaccionMacroId()));


		List lst = listarPorQuery(DtoWfTransaccionMacro.class, "wftransaccionmacro.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoWfTransaccionMacro) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoWfTransaccionMacroFiltro filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WfTransaccionMacro.Paginacion
		//parametros.add(new DominioParametroPersistencia("p_transaccionmacroid", String.class, filtro.getTransaccionMacroId()));


		Integer registros = contar("wftransaccionmacro.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "wftransaccionmacro.listarPaginadoSentencia",DtoWfTransaccionMacroListado.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
