package net.royal.spring.sistema.dao.impl;

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
import net.royal.spring.sistema.dominio.BeanSyTipodocumento;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyTipoDocumento;

@Repository
public class SyTipodocumentoDaoImpl extends GenericoDaoImpl<BeanSyTipodocumento, BeanSyTipodocumentoPk> {

	private static Logger logger = LogManager.getLogger(BeanSyTipodocumento.class);

	public SyTipodocumentoDaoImpl() {
		super("sytipodocumento");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyTipodocumento obtenerPorId(String ptipodocumentoid) {
		return obtenerPorId(new BeanSyTipodocumentoPk( ptipodocumentoid));
	}

	public BeanSyTipodocumento coreInsertar(BeanSyTipodocumento bean) {
		// TODO SyTipodocumento.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public BeanSyTipodocumento coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanSyTipodocumento coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSyTipodocumento coreActualizar(BeanSyTipodocumento bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public DtoComunSyTipodocumento obtenerDtoPorId(DtoComunSyTipodocumento pk) {
		DtoComunSyTipodocumento dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumentoid", String.class, pk.getTipodocumentoid()));
		
		List lst = listarPorQuery(DtoComunSyTipodocumento.class, "sytipodocumento.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyTipodocumento) lst.get(0);
		return dto;
	}

	
	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyTipoDocumento filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		if(UString.esNuloVacio(filtro.getCodigo())) {
			filtro.setCodigo(null);
		}
		if(UString.esNuloVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}
		if(UString.esNuloVacio(filtro.getComentarios())) {
			filtro.setComentarios(null);
		}
		if(UString.esNuloVacio(filtro.getEstado())) {
			filtro.setEstado(null);
		}
		
		parametros.add(new DominioParametroPersistencia("p_tipodocumentoid", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_comentarios", String.class, filtro.getComentarios()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("sytipodocumento.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "sytipodocumento.listarPaginadoSentencia",DtoComunSyTipodocumento.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public List<DtoTabla> listarProcesos(String aplicacion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacion));
		
		List lst = listarPorQuery(DtoTabla.class, "sytipodocumento.listarProcesos",parametros);		
		return lst;
	}

	public List<DtoComunSyTipodocumento> listaractivos() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoComunSyTipodocumento.class, "sytipodocumento.listaractivos",parametros);		
		return lst;
	}
}
