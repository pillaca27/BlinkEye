package net.royal.spring.core.dao.impl;

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
import net.royal.spring.core.dominio.BeanReportingcompany;
import net.royal.spring.core.dominio.BeanReportingcompanyPk;
import net.royal.spring.core.dominio.dto.DtoComunReportingcompany;
import net.royal.spring.core.dominio.filtro.FiltroComunReportingcompany;
import net.royal.spring.core.dominio.lista.DtlComunReportingcompany;

@Repository
public class ReportingcompanyDaoImpl extends GenericoDaoImpl<BeanReportingcompany, BeanReportingcompanyPk> {

	private static Logger logger = LogManager.getLogger(BeanReportingcompany.class);

	public ReportingcompanyDaoImpl() {
		super("reportingcompany");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanReportingcompany obtenerPorId(String pcompaniacodigo,String pcompanyowner) {
		return obtenerPorId(new BeanReportingcompanyPk( pcompaniacodigo, pcompanyowner));
	}

	public BeanReportingcompany coreInsertar(BeanReportingcompany bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanReportingcompany coreActualizar(BeanReportingcompany bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunReportingcompany obtenerDtoCore(DtoComunReportingcompany pk) throws Exception {
		DtoComunReportingcompany dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunReportingcompany();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunReportingcompany> listarDtoCore(DtoComunReportingcompany filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Reportingcompany.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));

		List datos = this.listarPorQuery(DtoComunReportingcompany.class, "reportingcompany.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunReportingcompany obtenerDto(DtoComunReportingcompany pk) {
		DtoComunReportingcompany dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));

		List lst = listarPorQuery(DtoComunReportingcompany.class, "reportingcompany.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunReportingcompany) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunReportingcompany filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Reportingcompany.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));

		Integer registros = contar("reportingcompany.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "reportingcompany.listarPaginadoSentencia",DtlComunReportingcompany.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
