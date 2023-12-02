package net.royal.spring.comun.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

import net.royal.spring.comun.dominio.WfBeanSyReporte;
import net.royal.spring.comun.dominio.WfBeanSyReportePk;
import net.royal.spring.comun.dominio.dto.WfDtoComunSyReporte;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class WfSyReporteDaoImpl extends GenericoDaoImpl<WfBeanSyReporte, WfBeanSyReportePk> {

	private static Logger logger = LogManager.getLogger(WfBeanSyReporte.class);

	public WfSyReporteDaoImpl() {
		super("syreporte");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfBeanSyReporte obtenerPorId(String paplicacioncodigo, String preportecodigo) {
		return obtenerPorId(new WfBeanSyReportePk(paplicacioncodigo, preportecodigo));
	}

	public WfBeanSyReporte coreInsertar(WfBeanSyReporte bean) {
		this.registrar(bean);
		return bean;
	}

	public WfBeanSyReporte coreActualizar(WfBeanSyReporte bean) {
		this.actualizar(bean);
		return bean;
	}

	public WfDtoComunSyReporte obtenerDtoPorId(WfDtoComunSyReporte pk) {
		WfDtoComunSyReporte dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_reportecodigo", String.class, pk.getReportecodigo()));

		List lst = listarPorQuery(WfDtoComunSyReporte.class, "syreporte.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (WfDtoComunSyReporte) lst.get(0);
		return dto;
	}

}
