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
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyAdjunto;
import net.royal.spring.sistema.dominio.BeanSyAdjuntoPk;

@Repository
public class SyAdjuntoDaoImpl extends GenericoDaoImpl<BeanSyAdjunto, BeanSyAdjuntoPk> {

	private static Logger logger = LogManager.getLogger(BeanSyAdjunto.class);

	public SyAdjuntoDaoImpl() {
		super("syadjunto");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyAdjunto obtenerPorId(String pnombretabla,String pclavetabla,Integer psecuencia) {
		return obtenerPorId(new BeanSyAdjuntoPk( pnombretabla, pclavetabla, psecuencia));
	}

	public BeanSyAdjunto coreInsertar(BeanSyAdjunto bean) {
		// TODO SyAdjunto.Insertar Datos		
		bean.getPk().setSecuencia(this.obtenerMaximoId()+1);
		this.registrar(bean);
		return bean;
	}

	public BeanSyAdjunto obtenerMaximoId(BeanSyAdjunto bean) {
		// TODO SyAdjunto.Insertar Datos		
		bean.getPk().setSecuencia(this.obtenerMaximoId()+1);
		return bean;
	}
	
	public BeanSyAdjunto coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		bean.getPk().setSecuencia(this.obtenerMaximoId()+1);
		this.registrar(bean);
		return bean;
	}

	public BeanSyAdjunto coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto bean) {
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSyAdjunto coreActualizar(BeanSyAdjunto bean) {
		this.actualizar(bean);
		return bean;
	}
	
	
	@Transactional
	public List<BeanSyAdjunto> listarPortabla(String tablaNombre, String tablaClave ) {
		 
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.nombretabla", tablaNombre));
		cri.add(Restrictions.eq("pk.clavetabla", tablaClave));
		List lista = this.listarPorCriterios(cri);		

		return lista;		
	}
	
	
	@Transactional
	public Integer obtenerMaximoId() {
		 
		Criteria cri = this.getCriteria();		
		ProjectionList proj = Projections.projectionList();
		proj.add(Projections.max("pk.secuencia"));

		cri.setProjection(proj);

		List lista = this.listarPorCriterios(cri);

		if (UValidador.esListaVacia(lista)) {
			return 0;	
		}
		
		if(lista.get(0) == null) {
			return 0;	
		}

		return (Integer) lista.get(0);
		
	}
	
}
