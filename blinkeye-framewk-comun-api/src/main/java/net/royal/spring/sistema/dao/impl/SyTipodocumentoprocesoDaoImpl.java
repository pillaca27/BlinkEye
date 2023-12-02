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
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoproceso;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoprocesoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumentoproceso;

@Repository
public class SyTipodocumentoprocesoDaoImpl extends GenericoDaoImpl<BeanSyTipodocumentoproceso, BeanSyTipodocumentoprocesoPk> {

	private static Logger logger = LogManager.getLogger(BeanSyTipodocumentoproceso.class);

	public SyTipodocumentoprocesoDaoImpl() {
		super("sytipodocumentoproceso");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyTipodocumentoproceso obtenerPorId(String ptipodocumentoid,String paplicacioncodigo,String pprocesocodigo) {
		return obtenerPorId(new BeanSyTipodocumentoprocesoPk( ptipodocumentoid, paplicacioncodigo, pprocesocodigo));
	}

	public BeanSyTipodocumentoproceso coreInsertar(BeanSyTipodocumentoproceso bean) {
		// TODO SyTipodocumentoproceso.Insertar Datos
		
		this.registrar(bean);
		return bean;
	}

	public BeanSyTipodocumentoproceso coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanSyTipodocumentoproceso coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSyTipodocumentoproceso coreActualizar(BeanSyTipodocumentoproceso bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public DtoComunSyTipodocumentoproceso obtenerDtoPorId(DtoComunSyTipodocumentoproceso pk) {
		DtoComunSyTipodocumentoproceso dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumentoid", String.class, pk.getTipodocumentoid()));
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_procesocodigo", String.class, pk.getProcesocodigo()));
		
		List lst = listarPorQuery(DtoComunSyTipodocumentoproceso.class, "sytipodocumentoproceso.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyTipodocumentoproceso) lst.get(0);
		return dto;
		
	}
	
	public List<DtoComunSyTipodocumentoproceso> listarDtoPorHeader(DtoComunSyTipodocumentoproceso filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumentoid", String.class, filtro.getTipodocumentoid()));
		
		List lst = listarPorQuery(DtoComunSyTipodocumentoproceso.class, "sytipodocumentoproceso.listarDtoPorHeader", parametros);		
		return lst;
	}
	

}
