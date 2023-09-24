package net.royal.spring.sy.dao.impl;

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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyReporte;
import net.royal.spring.sy.dominio.BeanSyReportePk;
import net.royal.spring.sy.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.sy.dominio.lista.DtlComunSyReporte;

@Repository
public class SyReporteDaoImpl extends GenericoDaoImpl<BeanSyReporte, BeanSyReportePk> {

	private static Logger logger = LogManager.getLogger(BeanSyReporte.class);

	public SyReporteDaoImpl() {
		super("syreporte");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyReporte obtenerPorId(String paplicacioncodigo,String preportecodigo) {
		return obtenerPorId(new BeanSyReportePk( paplicacioncodigo, preportecodigo));
	}

	public BeanSyReporte coreInsertar(BeanSyReporte bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyReporte coreActualizar(BeanSyReporte bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyReporte obtenerDtoPorId(DtoComunSyReporte pk) {
		DtoComunSyReporte dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_reportecodigo", String.class, pk.getReportecodigo()));


		List lst = listarPorQuery(DtoComunSyReporte.class, "syreporte.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyReporte) lst.get(0);
		return dto;
	}

	/*public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_reportecodigo", String.class, filtro.getReportecodigo()));
		Integer registros = contar("syreporte.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syreporte.listarPaginadoSentencia",DtoComunSyReporte.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}*/
	
	
	
	public DominioPaginacion listarpaginadoReportes(FiltroComunSyReporte filtro) {

		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.estaVacio(filtro.getReportecodigo()))
			filtro.setReportecodigo(null);
		if (UString.estaVacio(filtro.getTopico()))
			filtro.setTopico(null);
		if (UString.estaVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_topico", String.class, filtro.getTopico()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));

		cantidadEncontrados = this.contar("syreporte.syreportesContar", parametros);

		List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"syreporte.syreportesListar", DtlComunSyReporte.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	
	public List<DtoTabla> obtenerTopicos(String aplicacion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacionCodigo", String.class, aplicacion));
		List lst = listarPorQuery(DtoTabla.class, "syreporte.obtenerTopicos", parametros);
		
		return lst;
	}
	
	
	public DominioPaginacion reportelistarConPaginacion(FiltroComunSyReporte filtro) {

		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.estaVacio(filtro.getReportecodigo()))
			filtro.setReportecodigo(null);
		if (UString.estaVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.estaVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.estaVacio(filtro.getTiporeporte()))
			filtro.setTiporeporte(null);
		

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_tiporeporte", String.class, filtro.getTiporeporte()));
		
		

		cantidadEncontrados = this.contar("syreporte.syreportesolicitudListarContar", parametros);

		List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"syreporte.syreportesolicitudListarPaginacion", DtlComunSyReporte.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarReportes(FiltroComunSyReporte filtro) {

		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.estaVacio(filtro.getReportecodigo()))
			filtro.setReportecodigo(null);
		if (UString.estaVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.estaVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.estaVacio(filtro.getTiporeporte()))
			filtro.setTiporeporte(null);
		

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getReportecodigo()));
		parametros.add(new DominioParametroPersistencia("p_id_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_tiporeporte", String.class, filtro.getTiporeporte()));
		
		List lst = listarPorQuery(DtlComunSyReporte.class, "syreporte.syreportesolicitudListarPaginacion", parametros);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}
	
	
	

}
