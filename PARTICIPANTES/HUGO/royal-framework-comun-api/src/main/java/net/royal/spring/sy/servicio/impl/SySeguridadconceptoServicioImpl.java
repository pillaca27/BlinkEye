package net.royal.spring.sy.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SySeguridadconceptoDaoImpl;
import net.royal.spring.sy.dominio.BeanSySeguridadconcepto;
import net.royal.spring.sy.dominio.BeanSySeguridadconceptoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sy.servicio.validar.SySeguridadconceptoServicioValidar;

@Service (value = "BeanServicioSySeguridadconcepto")
public class SySeguridadconceptoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSySeguridadconcepto";
	private static Logger logger = LogManager.getLogger(SySeguridadconceptoServicioImpl.class);

	@Autowired
	private SySeguridadconceptoDaoImpl sySeguridadconceptoDao;

	@Autowired
	private SySeguridadconceptoServicioValidar validar;

	@Transactional
	public DtoComunSySeguridadconcepto coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadconcepto dto) throws Exception {
		BeanSySeguridadconcepto sySeguridadconcepto = dto.obtenerBean();
		sySeguridadconcepto = coreInsertar(usuarioActual, sySeguridadconcepto);
		dto.setTransaccionEstado(sySeguridadconcepto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadconcepto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadconcepto coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadconcepto = validar.prepararInsertar(usuarioActual, sySeguridadconcepto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty()) {
			sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadconcepto.setTransaccionListaMensajes(lst);
			return sySeguridadconcepto;
		}
		
		// transaccion
		sySeguridadconcepto = sySeguridadconceptoDao.coreInsertar(sySeguridadconcepto);
		sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadconcepto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadconcepto;
	}

	@Transactional
	public DtoComunSySeguridadconcepto coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadconcepto dto) throws Exception {
		BeanSySeguridadconcepto sySeguridadconcepto = dto.obtenerBean();
		sySeguridadconcepto = coreActualizar(usuarioActual, sySeguridadconcepto);
		dto.setTransaccionEstado(sySeguridadconcepto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadconcepto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadconcepto coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadconcepto = validar.prepararActualizar(usuarioActual, sySeguridadconcepto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty()) {
			sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadconcepto.setTransaccionListaMensajes(lst);
			return sySeguridadconcepto;
		}
		
		// transaccion
	    sySeguridadconcepto = sySeguridadconceptoDao.coreActualizar(sySeguridadconcepto);
		sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadconcepto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadconcepto;
	}


	@Transactional
	public DtoComunSySeguridadconcepto coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadconcepto dto) throws Exception {
		BeanSySeguridadconcepto sySeguridadconcepto = dto.obtenerBean();
		sySeguridadconcepto = coreAnular(usuarioActual, sySeguridadconcepto);
		dto.setTransaccionEstado(sySeguridadconcepto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadconcepto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadconcepto coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadconcepto = validar.prepararAnular(usuarioActual, sySeguridadconcepto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty()) {
			sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadconcepto.setTransaccionListaMensajes(lst);
			return sySeguridadconcepto;
		}
		
		// transaccion
	    sySeguridadconcepto.setEstado("I");
	    sySeguridadconcepto = sySeguridadconceptoDao.coreActualizar(sySeguridadconcepto);
		sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadconcepto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconceptoPk pk) throws Exception {
		BeanSySeguridadconcepto bean = sySeguridadconceptoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSySeguridadconcepto coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto) throws Exception {
		return coreAnular(usuarioActual,new BeanSySeguridadconceptoPk( paplicacioncodigo, pgrupo, pconcepto));
	}

	@Transactional
	public DtoComunSySeguridadconcepto coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadconcepto dto) throws Exception {
		BeanSySeguridadconcepto sySeguridadconcepto = dto.obtenerBean();
		sySeguridadconcepto = coreEliminar(usuarioActual, sySeguridadconcepto);
		dto.setTransaccionEstado(sySeguridadconcepto.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadconcepto.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadconcepto coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		// valores por defecto - preparando objeto
		
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, sySeguridadconcepto.getPk().getGrupo().trim()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, sySeguridadconcepto.getPk().getConcepto().trim()));
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, sySeguridadconcepto.getPk().getAplicacioncodigo().trim()));
		
		Integer cantidad = sySeguridadconceptoDao.contar("syseguridadconcepto.contarCantidadAsignados", parametros);

		if(cantidad > 0) {
			sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadconcepto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Existen autorizaciones asignadas."));
			return sySeguridadconcepto;
		}
		
		sySeguridadconcepto = validar.prepararEliminar(usuarioActual, sySeguridadconcepto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty()) {
			sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadconcepto.setTransaccionListaMensajes(lst);
			return sySeguridadconcepto;
		}
		
		// transaccion
		sySeguridadconceptoDao.eliminar(sySeguridadconcepto);
		sySeguridadconcepto=new BeanSySeguridadconcepto();
		sySeguridadconcepto.setTransaccionEstado(DominioTransaccion.OK);
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconceptoPk pk) throws Exception {
		BeanSySeguridadconcepto sySeguridadconcepto = sySeguridadconceptoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,sySeguridadconcepto);
	}

	public BeanSySeguridadconcepto coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto) throws Exception {
		return coreEliminar(usuarioActual,new BeanSySeguridadconceptoPk( paplicacioncodigo, pgrupo, pconcepto));
	}

}
