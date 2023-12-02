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
import net.royal.spring.core.dominio.BeanMaMiscelaneosheader;
import net.royal.spring.core.dominio.BeanMaMiscelaneosheaderPk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.filtro.FiltroComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.lista.DtlComunMaMiscelaneosheader;

@Repository
public class MaMiscelaneosheaderDaoImpl extends GenericoDaoImpl<BeanMaMiscelaneosheader, BeanMaMiscelaneosheaderPk> {

	private static Logger logger = LogManager.getLogger(BeanMaMiscelaneosheader.class);

	public MaMiscelaneosheaderDaoImpl() {
		super("mamiscelaneosheader");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaMiscelaneosheader obtenerPorId(String paplicacioncodigo,String pcodigotabla,String pcompania) {
		return obtenerPorId(new BeanMaMiscelaneosheaderPk( paplicacioncodigo, pcodigotabla, pcompania));
	}

	public BeanMaMiscelaneosheader coreInsertar(BeanMaMiscelaneosheader bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaMiscelaneosheader coreActualizar(BeanMaMiscelaneosheader bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaMiscelaneosheader obtenerDtoPorId(DtoComunMaMiscelaneosheader pk) {
		DtoComunMaMiscelaneosheader dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, pk.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, pk.getCompania()));		
		


		List lst = listarPorQuery(DtoComunMaMiscelaneosheader.class, "mamiscelaneosheader.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaMiscelaneosheader) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunMaMiscelaneosheader filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaMiscelaneosheader.Paginacion
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));


		Integer registros = contar("mamiscelaneosheader.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "mamiscelaneosheader.listarPaginadoSentencia",DtlComunMaMiscelaneosheader.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarMiscelaneos(FiltroComunMaMiscelaneosheader filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaMiscelaneosheader.Paginacion
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, filtro.getCodigotabla()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List lst = listarPorQuery(DtlComunMaMiscelaneosheader.class, "mamiscelaneosheader.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	@Transactional
	public DtoComunMaMiscelaneosheader obtenerDtoPorUuid(String uuid) {
		DtoComunMaMiscelaneosheader dto = null;
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class ,uuid));
		
		List lst =  listarPorQuery(DtoComunMaMiscelaneosheader.class, "mamiscelaneosheader.obtenerDtoPorUuid", parametros);
		
		if(lst.size() == 1)
			dto = (DtoComunMaMiscelaneosheader) lst.get(0);
		
		return dto;
	}
}
