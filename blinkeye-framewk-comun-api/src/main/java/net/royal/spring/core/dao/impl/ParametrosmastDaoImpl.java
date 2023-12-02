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
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.BeanParametrosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunParametrosmast;
import net.royal.spring.core.dominio.lista.DtlComunParametrosmast;

@Repository
public class ParametrosmastDaoImpl extends GenericoDaoImpl<BeanParametrosmast, BeanParametrosmastPk> {

	private static Logger logger = LogManager.getLogger(BeanParametrosmast.class);

	public ParametrosmastDaoImpl() {
		super("parametrosmast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DtoComunParametrosmast obtenerDtoPorUuid(String uuid) {
		DtoComunParametrosmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunParametrosmast.class, "parametrosmast.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunParametrosmast) lst.get(0); 
		}
		return dto;
	}

	public BeanParametrosmast obtenerPorId(String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) {
		return obtenerPorId(new BeanParametrosmastPk( pcompaniacodigo, paplicacioncodigo, pparametroclave), false);
	}

	public BeanParametrosmast coreInsertar(BeanParametrosmast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanParametrosmast coreActualizar(BeanParametrosmast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunParametrosmast obtenerDtoPorId(DtoComunParametrosmast pk) {
		DtoComunParametrosmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_parametroclave", String.class, pk.getParametroclave()));


		List lst = listarPorQuery(DtoComunParametrosmast.class, "parametrosmast.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunParametrosmast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunParametrosmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcionparametro()))
			filtro.setDescripcionparametro(null);
		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo(null);
		if (UString.esNuloVacio(filtro.getParametroclave()))
			filtro.setParametroclave(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		// TODO Parametrosmast.Paginacion
		parametros.add(new DominioParametroPersistencia("p_desc", String.class, filtro.getDescripcionparametro()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_parametroclave", String.class, filtro.getParametroclave()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("parametrosmast.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "parametrosmast.listarPaginadoSentencia",DtlComunParametrosmast.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	
	public List obtenerParametroCorreo() throws Exception {		
		List lista = listarPorQuery(DtoTabla.class, "parametrosmast.obtenerParametroCorreo");
		return lista;
	}

	public String obtenerParametroExplicacion(String parametroCodigo, String aplicacionCodigo) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionCodigo));
		parametros.add(new DominioParametroPersistencia("p_parametro", String.class, parametroCodigo));
		List datos = this.listarPorQuery(DtoTabla.class, "parametrosmast.obtenerParametroExplicacion", parametros);
		if (datos.size() > 0) {
			DtoTabla temp = (DtoTabla) datos.get(0);
			return temp.getDescripcion();
		}
		return null;
	}

	public String obtenerTexto(String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) {
		BeanParametrosmast para = this.obtenerPorId(pcompaniacodigo, paplicacioncodigo, pparametroclave);
		if (para==null)
			return "";
		return para.getTexto();
	}
	
	public DominioPaginacion exportarParametros( FiltroComunParametrosmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcionparametro()))
			filtro.setDescripcionparametro(null);
		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo(null);
		if (UString.esNuloVacio(filtro.getParametroclave()))
			filtro.setParametroclave(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		// TODO Parametrosmast.Paginacion
		parametros.add(new DominioParametroPersistencia("p_desc", String.class, filtro.getDescripcionparametro()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_parametroclave", String.class, filtro.getParametroclave()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List lst = this.listarPorQuery(DtlComunParametrosmast.class, "parametrosmast.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public List<ParametroTransaccion> obtenerDtoPorAplicacion(String  w_compania,String w_aplicacion,String w_parametros) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, w_compania));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class,w_aplicacion));
		StringBuilder sb = new StringBuilder();
		sb.append(this.obtenerSentenciaSqlPorQuery("parametrosmast.obtenerDtoPorAplicacion"));
		String dest[] = w_parametros.split(";");
		String w_buscar="";
		for (String d : dest) {
			if(UString.esNuloVacio(w_buscar)) {
				w_buscar="'" + d +"'";
			}
			else if (!UString.estaVacio(d)) {			
				w_buscar=w_buscar+","+"'" + d +"'";
			}
		}
		sb.append(" AND parametroclave in ( " + w_buscar + " )  ");
		List lst =  this.listarPorSentenciaSQL(sb, parametros, ParametroTransaccion.class); //listarPorQuery(ParametroTransaccion.class, "parametrosmast.obtenerDtoPorAplicacion", parametros);
		return lst;
	}
}
