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
import net.royal.spring.sistema.dao.impl.SyAplicacionreportetopicoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopico;
import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopicoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyAplicacionreportetopico;
import net.royal.spring.sistema.servicio.validar.SyAplicacionreportetopicoServicioValidar;

@Service (value = "BeanServicioSyAplicacionreportetopico")
public class SyAplicacionreportetopicoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyAplicacionreportetopico";
	private static Logger logger = LogManager.getLogger(SyAplicacionreportetopicoServicioImpl.class);

	@Autowired
	private SyAplicacionreportetopicoDaoImpl syAplicacionreportetopicoDao;

	@Autowired
	private SyAplicacionreportetopicoServicioValidar validar;

	@Transactional
	public DtoComunSyAplicacionreportetopico coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBeanRegistrar();
		syAplicacionreportetopico = coreInsertar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyAplicacionreportetopico coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		// valores por defecto - preparando objeto
		syAplicacionreportetopico = validar.prepararInsertar(usuarioActual, syAplicacionreportetopico);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty()) {
			syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syAplicacionreportetopico.setTransaccionListaMensajes(lst);
			return syAplicacionreportetopico;
		}
		
		// transaccion
		syAplicacionreportetopico = syAplicacionreportetopicoDao.coreInsertar(syAplicacionreportetopico);
		syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.OK);
		syAplicacionreportetopico.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syAplicacionreportetopico;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = syAplicacionreportetopicoDao.obtenerPorUuid(dto.getUuid());
		syAplicacionreportetopico = dto.obtenerBeanActualizar(syAplicacionreportetopico);
		syAplicacionreportetopico = coreActualizar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBean();
		syAplicacionreportetopico = coreActualizar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyAplicacionreportetopico coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		// valores por defecto - preparando objeto
		syAplicacionreportetopico = validar.prepararActualizar(usuarioActual, syAplicacionreportetopico);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty()) {
			syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syAplicacionreportetopico.setTransaccionListaMensajes(lst);
			return syAplicacionreportetopico;
		}
		
		// transaccion
	    syAplicacionreportetopico = syAplicacionreportetopicoDao.coreActualizar(syAplicacionreportetopico);
		syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.OK);
		syAplicacionreportetopico.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syAplicacionreportetopico;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreAnularPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = syAplicacionreportetopicoDao.obtenerPorUuid(dto.getUuid());
		syAplicacionreportetopico = coreAnular(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBean();
		syAplicacionreportetopico = coreAnular(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyAplicacionreportetopico coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		// valores por defecto - preparando objeto
		syAplicacionreportetopico = validar.prepararAnular(usuarioActual, syAplicacionreportetopico);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty()) {
			syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syAplicacionreportetopico.setTransaccionListaMensajes(lst);
			return syAplicacionreportetopico;
		}
		
		// transaccion
	    syAplicacionreportetopico.setEstado("I");
	    syAplicacionreportetopico = syAplicacionreportetopicoDao.coreActualizar(syAplicacionreportetopico);
		syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.OK);
		syAplicacionreportetopico.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico bean = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSyAplicacionreportetopico coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String ptopico) throws Exception {
		return coreAnular(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreActivarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = syAplicacionreportetopicoDao.obtenerPorUuid(dto.getUuid());
		syAplicacionreportetopico = coreActivar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreActivar(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBean();
		syAplicacionreportetopico = coreActivar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyAplicacionreportetopico coreActivar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		// valores por defecto - preparando objeto
		syAplicacionreportetopico = validar.prepararActivar(usuarioActual, syAplicacionreportetopico);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActivar(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty()) {
			syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syAplicacionreportetopico.setTransaccionListaMensajes(lst);
			return syAplicacionreportetopico;
		}
		
		// transaccion
	    syAplicacionreportetopico.setEstado("A");
	    syAplicacionreportetopico = syAplicacionreportetopicoDao.coreActualizar(syAplicacionreportetopico);
		syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.OK);
		syAplicacionreportetopico.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico coreActivar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico bean = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public BeanSyAplicacionreportetopico coreActivar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String ptopico) throws Exception {
		return coreActivar(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = syAplicacionreportetopicoDao.obtenerPorUuid(dto.getUuid());
		syAplicacionreportetopico = coreEliminar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyAplicacionreportetopico coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyAplicacionreportetopico dto) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBean();
		syAplicacionreportetopico = coreEliminar(usuarioActual, syAplicacionreportetopico);
		dto.setTransaccionEstado(syAplicacionreportetopico.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syAplicacionreportetopico.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyAplicacionreportetopico coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		// valores por defecto - preparando objeto
		syAplicacionreportetopico = validar.prepararEliminar(usuarioActual, syAplicacionreportetopico);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty()) {
			syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syAplicacionreportetopico.setTransaccionListaMensajes(lst);
			return syAplicacionreportetopico;
		}
		
		// transaccion
		syAplicacionreportetopicoDao.eliminar(syAplicacionreportetopico);
		syAplicacionreportetopico=new BeanSyAplicacionreportetopico();
		syAplicacionreportetopico.setTransaccionEstado(DominioTransaccion.OK);
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico syAplicacionreportetopico = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syAplicacionreportetopico);
	}

	public BeanSyAplicacionreportetopico coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String ptopico) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

}
