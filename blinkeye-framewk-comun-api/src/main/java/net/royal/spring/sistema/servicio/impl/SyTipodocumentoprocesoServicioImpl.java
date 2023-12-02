package net.royal.spring.sistema.servicio.impl;

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
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyTipodocumentoprocesoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyTipodocumento;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoproceso;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoprocesoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumentoproceso;
import net.royal.spring.sistema.servicio.validar.SyTipodocumentoprocesoServicioValidar;

@Service (value = "BeanServicioSyTipodocumentoproceso")
public class SyTipodocumentoprocesoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyTipodocumentoproceso";
	private static Logger logger = LogManager.getLogger(SyTipodocumentoprocesoServicioImpl.class);

	@Autowired
	private SyTipodocumentoprocesoDaoImpl syTipodocumentoprocesoDao;

	@Autowired
	private SyTipodocumentoprocesoServicioValidar validar;
	
	@Transactional
	public DtoComunSyTipodocumentoproceso coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyTipodocumentoproceso dto) throws UException {
		BeanSyTipodocumentoproceso sytipodocumento = dto.obtenerBean();
		sytipodocumento = coreActualizar(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public DtoComunSyTipodocumentoproceso coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyTipodocumentoproceso dto) throws UException {
		BeanSyTipodocumentoproceso sytipodocumento = dto.obtenerBean();
		sytipodocumento = coreInsertar(usuarioActual, sytipodocumento);
		dto.setTransaccionEstado(sytipodocumento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sytipodocumento.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public DtoComunSyTipodocumentoproceso coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyTipodocumentoproceso dto) throws UException {
		BeanSyTipodocumentoproceso maMiscelaneosdetalle = dto.obtenerBean();
		 coreEliminar(usuarioActual, maMiscelaneosdetalle);
		dto.setTransaccionEstado(maMiscelaneosdetalle.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maMiscelaneosdetalle.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public BeanSyTipodocumentoproceso coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) throws UException {
		// valores por defecto - preparando objeto
		syTipodocumentoproceso = validar.prepararInsertar(usuarioActual, syTipodocumentoproceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syTipodocumentoproceso);
		if (!lst.isEmpty()) {
			syTipodocumentoproceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syTipodocumentoproceso.setTransaccionListaMensajes(lst);
			return syTipodocumentoproceso;
		}
		
		// transaccion
		syTipodocumentoproceso = syTipodocumentoprocesoDao.coreInsertar(syTipodocumentoproceso);
		syTipodocumentoproceso.setTransaccionEstado(DominioTransaccion.OK);
		syTipodocumentoproceso.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syTipodocumentoproceso;
		
	}

	@Transactional
	public BeanSyTipodocumentoproceso coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso syTipodocumentoproceso) throws UException {
		// valores por defecto - preparando objeto
		syTipodocumentoproceso = validar.prepararActualizar(usuarioActual, syTipodocumentoproceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syTipodocumentoproceso);
		if (!lst.isEmpty()) {
			syTipodocumentoproceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syTipodocumentoproceso.setTransaccionListaMensajes(lst);
			return syTipodocumentoproceso;
		}
		
		// transaccion
		syTipodocumentoproceso = syTipodocumentoprocesoDao.coreActualizar(syTipodocumentoproceso);
		syTipodocumentoproceso.setTransaccionEstado(DominioTransaccion.OK);
		syTipodocumentoproceso.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syTipodocumentoproceso;
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso syTipodocumentoproceso) throws UException {
		// valores por defecto - preparando objeto
		syTipodocumentoproceso = validar.prepararEliminar(usuarioActual, syTipodocumentoproceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syTipodocumentoproceso);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		syTipodocumentoprocesoDao.eliminar(syTipodocumentoproceso);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoprocesoPk pk) throws UException {
		BeanSyTipodocumentoproceso syTipodocumentoproceso = syTipodocumentoprocesoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,syTipodocumentoproceso);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String ptipodocumentoid,String paplicacioncodigo,String pprocesocodigo) throws UException {
		coreEliminar(usuarioActual,new BeanSyTipodocumentoprocesoPk( ptipodocumentoid, paplicacioncodigo, pprocesocodigo));
	}

}
