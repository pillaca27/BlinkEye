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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;

@Repository
public class MaMiscelaneosdetalleDaoImpl extends GenericoDaoImpl<BeanMaMiscelaneosdetalle, BeanMaMiscelaneosdetallePk> {

	private static Logger logger = LogManager.getLogger(BeanMaMiscelaneosdetalle.class);

	public MaMiscelaneosdetalleDaoImpl() {
		super("mamiscelaneosdetalle");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaMiscelaneosdetalle obtenerPorId(String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) {
		return obtenerPorId(new BeanMaMiscelaneosdetallePk( paplicacioncodigo, pcodigotabla, pcompania, pcodigoelemento));
	}

	public BeanMaMiscelaneosdetalle coreInsertar(BeanMaMiscelaneosdetalle bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaMiscelaneosdetalle coreActualizar(BeanMaMiscelaneosdetalle bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaMiscelaneosdetalle obtenerDtoPorId(DtoComunMaMiscelaneosdetalle pk) {
		DtoComunMaMiscelaneosdetalle dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, pk.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, pk.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_codigoelemento", String.class, pk.getCodigoelemento()));
		List lst = listarPorQuery(DtoComunMaMiscelaneosdetalle.class, "mamiscelaneosdetalle.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaMiscelaneosdetalle) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoComunMaMiscelaneosdetalle filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaMiscelaneosdetalle.Paginacion
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_codigoelemento", String.class, filtro.getCodigoelemento()));


		Integer registros = contar("mamiscelaneosdetalle.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "mamiscelaneosdetalle.listarPaginadoSentencia",DtoComunMaMiscelaneosdetalle.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	
	public List<DtoComunMaMiscelaneosdetalle> listarDtoPorHeader(DtoComunMaMiscelaneosdetalle filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));		
		parametros.add(new DominioParametroPersistencia("p_codigoelemento", String.class, filtro.getCodigoelemento()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));		
		List lst = listarPorQuery(DtoComunMaMiscelaneosdetalle.class, "mamiscelaneosdetalle.listarDtoPorHeader", parametros);		
		return lst;
	}
	
	public List<DtoComunMaMiscelaneosdetalle> listarDtoPorAplicacionCodigoTabla(DtoComunMaMiscelaneosdetalle filtro) {
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.esNuloVacio(filtro.getCompania()))
			filtro.setCompania(null);
				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));	
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));		
		List lst = listarPorQuery(DtoComunMaMiscelaneosdetalle.class, "mamiscelaneosdetalle.listarDtoPorAplicacionCodigoTabla", parametros);		
		return lst;
	}
	
	public DtoComunMaMiscelaneosdetalle obtenerDtoPorAplicacionTablaElemento(DtoComunMaMiscelaneosdetalle pk) {
		DtoComunMaMiscelaneosdetalle dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, pk.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_codigoelemento", String.class, pk.getCodigoelemento()));
		List lst = listarPorQuery(DtoComunMaMiscelaneosdetalle.class, "mamiscelaneosdetalle.obtenerDtoPorAplicacionTablaElemento", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaMiscelaneosdetalle) lst.get(0);
		return dto;
	}
	
	public List<DtoComunMaMiscelaneosdetalle> listarDtoPorAplicacionCodigo(DtoComunMaMiscelaneosdetalle filtro) {
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		if (UString.esNuloVacio(filtro.getCompania()))
			filtro.setCompania(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));	
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));		
		List lst = listarPorQuery(DtoComunMaMiscelaneosdetalle.class, "mamiscelaneosdetalle.listarDtoPorAplicacionCodigo", parametros);		
		return lst;
	}
	
}
