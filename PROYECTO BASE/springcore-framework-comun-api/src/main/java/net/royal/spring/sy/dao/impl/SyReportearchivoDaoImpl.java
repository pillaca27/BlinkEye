package net.royal.spring.sy.dao.impl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
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
import net.royal.spring.sy.dominio.BeanSyReportearchivo;
import net.royal.spring.sy.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sy.dominio.dto.DtoComunSyReportearchivo;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyReportearchivo;

@Repository
public class SyReportearchivoDaoImpl extends GenericoDaoImpl<BeanSyReportearchivo, BeanSyReportearchivoPk> {

	private static Logger logger = LogManager.getLogger(BeanSyReportearchivo.class);

	public SyReportearchivoDaoImpl() {
		super("syreportearchivo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyReportearchivo obtenerPorId(String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
		return obtenerPorId(new BeanSyReportearchivoPk( paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public BeanSyReportearchivo coreInsertar(BeanSyReportearchivo bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyReportearchivo coreActualizar(BeanSyReportearchivo bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyReportearchivo obtenerDtoPorId(DtoComunSyReportearchivo pk) {
		if (pk==null)
			return pk;	
		
		if (UString.esNuloVacio(pk.getCompaniasocio()))
			pk.setCompaniasocio("999999");
		if (UString.esNuloVacio(pk.getPeriodo()))
			pk.setPeriodo("999999");
		if (UString.esNuloVacio(pk.getVersion()))
			pk.setVersion("999999");
		BeanSyReportearchivoPk id = new BeanSyReportearchivoPk();
		id.setAplicacioncodigo(pk.getAplicacioncodigo());
		id.setReportecodigo(pk.getReportecodigo());
		id.setCompaniasocio(pk.getCompaniasocio());
		id.setPeriodo(pk.getPeriodo());
		id.setVersion(pk.getVersion());
		BeanSyReportearchivo bean = this.obtenerPorId(id);
		if (bean!=null) {
			pk.setReporte(bean.getReporte());
			pk.setEstado(bean.getEstado());
			if(bean.getReporte() != null) {
				pk.setAuxString(new String(bean.getReporte(), StandardCharsets.UTF_8));	
			}
			//pk.setRutacompleta(bean.getRutacompleta());
			pk.setUltimafechamodif(bean.getUltimafechamodif());
			pk.setUltimousuario(bean.getUltimousuario());
			pk.setNombre(bean.getNombre());
			pk.setAsunto(bean.getAsunto());
		}
		else {
			pk.setVersion("0");
		}
		return pk;
		/*DtoComunSyReportearchivo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_reportecodigo", String.class, pk.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, pk.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_version", String.class, pk.getVersion()));
		List lst = listarPorQuery(DtoComunSyReportearchivo.class, "syreportearchivo.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyReportearchivo) lst.get(0);
		return dto;*/
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoComunSyReportearchivo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyReportearchivo.Paginacion
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_reportecodigo", String.class, filtro.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, filtro.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("p_version", String.class, filtro.getVersion()));


		Integer registros = contar("syreportearchivo.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syreportearchivo.listarPaginadoSentencia",DtoComunSyReportearchivo.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion archivolistarConPaginacion(FiltroComunSyReportearchivo filtro) throws SQLException {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.estaVacio(filtro.getReportecodigo()))
			filtro.setReportecodigo(null);
		if (UString.estaVacio(filtro.getEstado()))
			filtro.setEstado(null);
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));
		cantidadEncontrados = this.contar("syreportearchivo.archivosolicitudListarContar", parametros);
		List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"syreportearchivo.archivosolicitudListarPaginacion", DtoComunSyReportearchivo.class);
		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return filtro.getPaginacion();
	}

}
