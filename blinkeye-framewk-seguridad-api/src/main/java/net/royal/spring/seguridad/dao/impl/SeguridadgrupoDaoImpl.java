package net.royal.spring.seguridad.dao.impl;

import java.math.BigDecimal;
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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.Seguridadgrupo;
import net.royal.spring.seguridad.dominio.SeguridadgrupoPk;


@Repository
public class SeguridadgrupoDaoImpl extends GenericoDaoImpl<Seguridadgrupo, SeguridadgrupoPk> {

	private static Logger logger = LogManager.getLogger(Seguridadgrupo.class);

	public SeguridadgrupoDaoImpl() {
		super("seguridadgrupo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Seguridadgrupo obtenerPorId(String paplicacioncodigo,String pgrupo) {
		return obtenerPorId(new SeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

	public Seguridadgrupo coreInsertar(Seguridadgrupo bean) {
		this.registrar(bean);
		return bean;
	}


	public Seguridadgrupo coreActualizar(Seguridadgrupo bean) {
		this.actualizar(bean);
		return bean;
	}

	public SeguridadMenuItem obtenerMenuItem(String aplicacionId,String contenedorId) {
		Seguridadgrupo bean = this.obtenerPorId(aplicacionId, contenedorId);
		if (bean==null)
			return null;
		return crearMenuItem(bean);
	}
	
	public SeguridadMenuItem crearMenuItem(Seguridadgrupo segComp) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = segComp.getPk().getAplicacioncodigo() + segComp.getPk().getGrupo();
		//itemC.setSidId(segComp.getPk().getSidId());
		itemC.setId(id);
		itemC.setLabel(segComp.getDescripcion());
		itemC.setIcon(segComp.getImagen());
		itemC.setOrden(segComp.getOrden());
		
		itemC.setGrupoCodigo(segComp.getPk().getGrupo());
		itemC.setAplicacioncodigo(segComp.getPk().getAplicacioncodigo());
		itemC.setGrupoPadreCodigo(segComp.getGrupopadre());
		return itemC;
	}

}
