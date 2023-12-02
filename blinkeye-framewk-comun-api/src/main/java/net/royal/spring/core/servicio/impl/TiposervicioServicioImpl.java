package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.TiposervicioDaoImpl;
import net.royal.spring.core.dominio.BeanTiposervicio;
import net.royal.spring.core.dominio.BeanTiposervicioPk;
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;
import net.royal.spring.core.dominio.dto.DtoComunTiposervicio;
import net.royal.spring.core.servicio.validar.TiposervicioServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioTiposervicio")
public class TiposervicioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioTiposervicio";
	private static Logger logger = LogManager.getLogger(TiposervicioServicioImpl.class);

	@Autowired
	private TiposervicioDaoImpl tiposervicioDao;

	@Autowired
	private TiposervicioServicioValidar validar;
	
	@Autowired
	private ServicioximpuestoServicioImpl detalleService;

	@Transactional
	public DtoComunTiposervicio coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws Exception {
		BeanTiposervicio acSucursalgrupo = tiposervicioDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunTiposervicio coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws Exception {
		BeanTiposervicio acSucursalgrupo = tiposervicioDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunTiposervicio coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunTiposervicio dto)
			throws Exception {
		BeanTiposervicio bean = tiposervicioDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = tiposervicioDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
	
	@Transactional
	public DtoComunTiposervicio coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws UException {
		BeanTiposervicio tiposervicio = dto.obtenerBean();
		tiposervicio = coreInsertar(usuarioActual, tiposervicio,dto);
		dto.setTransaccionEstado(tiposervicio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tiposervicio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTiposervicio coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio, DtoComunTiposervicio dto) throws UException {
		// valores por defecto - preparando objeto
		tiposervicio = validar.prepararInsertar(usuarioActual, tiposervicio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, tiposervicio);
		if (!lst.isEmpty()) {
			tiposervicio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tiposervicio.setTransaccionListaMensajes(lst);
			return tiposervicio;
		}
			
		// transaccion
		tiposervicio = tiposervicioDao.coreInsertar(tiposervicio);
		
		if(dto.getLstDetalle().size() > 0) {
			
			for (DtoComunServicioximpuesto detalle : dto.getLstDetalle()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setTiposervicio(tiposervicio.getPk().getTiposervicio());
					detalleService.coreInsertar(usuarioActual, detalle);
				}		
			}
		}
		tiposervicio.setTransaccionEstado(DominioTransaccion.OK);
		tiposervicio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tiposervicio;
	}

	@Transactional
	public DtoComunTiposervicio coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws UException {
		BeanTiposervicio tiposervicio = dto.obtenerBean();
		tiposervicio = coreActualizar(usuarioActual, tiposervicio,dto);
		dto.setTransaccionEstado(tiposervicio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tiposervicio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTiposervicio coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio,DtoComunTiposervicio dto) throws UException {
		// valores por defecto - preparando objeto
		tiposervicio = validar.prepararActualizar(usuarioActual, tiposervicio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, tiposervicio);
		if (!lst.isEmpty()) {
			tiposervicio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tiposervicio.setTransaccionListaMensajes(lst);
			return tiposervicio;
		}
		

		// transaccion
	    tiposervicio = tiposervicioDao.coreActualizar(tiposervicio);
	    
		if(dto.getLstDetalle().size() > 0) {
		
			//ELIMINAR PRIMERO
			for (DtoComunServicioximpuesto detalle : dto.getLstDetalle()) {
	
				 if(detalle.getAccion().equals("E")) {
					detalleService.coreEliminar(usuarioActual, detalle);
				}	
			}
			
			
			for (DtoComunServicioximpuesto detalle : dto.getLstDetalle()) {

				if(detalle.getAccion().equals("N")) {
					detalleService.coreInsertar(usuarioActual, detalle);
				}
				else if(detalle.getAccion().equals("A")) {
					detalleService.coreActualizar(usuarioActual, detalle);
				}	
			}
		}
		
		tiposervicio.setTransaccionEstado(DominioTransaccion.OK);
		tiposervicio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tiposervicio;
	}


	@Transactional
	public DtoComunTiposervicio coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws UException {
		BeanTiposervicio tiposervicio = dto.obtenerBean();
		tiposervicio = coreAnular(usuarioActual, tiposervicio);
		dto.setTransaccionEstado(tiposervicio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tiposervicio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTiposervicio coreAnular(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio) throws UException {
		// valores por defecto - preparando objeto
		tiposervicio = validar.prepararAnular(usuarioActual, tiposervicio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, tiposervicio);
		if (!lst.isEmpty()) {
			tiposervicio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tiposervicio.setTransaccionListaMensajes(lst);
			return tiposervicio;
		}
		
		// transaccion
	    tiposervicio.setEstado("I");
	    tiposervicio = tiposervicioDao.coreActualizar(tiposervicio);
		tiposervicio.setTransaccionEstado(DominioTransaccion.OK);
		tiposervicio.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return tiposervicio;
	}

	public BeanTiposervicio coreAnular(SeguridadUsuarioActual usuarioActual, BeanTiposervicioPk pk) throws UException {
		BeanTiposervicio bean = tiposervicioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanTiposervicio coreAnular(SeguridadUsuarioActual usuarioActual, String ptiposervicio) throws UException {
		return coreAnular(usuarioActual,new BeanTiposervicioPk( ptiposervicio));
	}

	@Transactional
	public DtoComunTiposervicio coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunTiposervicio dto) throws UException {
		BeanTiposervicio tiposervicio = dto.obtenerBean();
		tiposervicio = coreEliminar(usuarioActual, tiposervicio,dto);
		dto.setTransaccionEstado(tiposervicio.getTransaccionEstado());
		dto.setTransaccionListaMensajes(tiposervicio.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanTiposervicio coreEliminar(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio,DtoComunTiposervicio dto) throws UException {
		// valores por defecto - preparando objeto
		tiposervicio = validar.prepararEliminar(usuarioActual, tiposervicio);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, tiposervicio);
		if (!lst.isEmpty()) {
			tiposervicio.setTransaccionEstado(DominioTransaccion.VALIDACION);
			tiposervicio.setTransaccionListaMensajes(lst);
			return tiposervicio;
		}
		
		if(dto.getLstDetalle().size() > 0) {
			
			for (DtoComunServicioximpuesto detalle : dto.getLstDetalle()) {
					detalleService.coreEliminar(usuarioActual, detalle);			
			}
		}
		
		// transaccion
		tiposervicioDao.eliminar(tiposervicio);
		tiposervicio=new BeanTiposervicio();
		tiposervicio.setTransaccionEstado(DominioTransaccion.OK);
		return tiposervicio;
	}


}
