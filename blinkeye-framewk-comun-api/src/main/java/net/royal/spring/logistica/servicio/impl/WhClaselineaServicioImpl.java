package net.royal.spring.logistica.servicio.impl;

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
import net.royal.spring.logistica.dao.impl.WhClaselineaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClaselinea;
import net.royal.spring.logistica.dominio.BeanWhClaselineaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClaselinea;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;
import net.royal.spring.logistica.servicio.validar.WhClaselineaServicioValidar;

@Service (value = "BeanServicioWhClaselinea")
public class WhClaselineaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWhClaselinea";
	private static Logger logger = LogManager.getLogger(WhClaselineaServicioImpl.class);

	@Autowired
	private WhClaselineaDaoImpl whClaselineaDao;

	@Autowired
	private WhClaselineaServicioValidar validar;
	
	@Autowired
	private WhClasefamiliaServicioImpl serviceFamilia;
	
	@Autowired
	private WhClasesubfamiliaServicioImpl whClasesubfamiliaServicioImpl;

	@Transactional
	public DtoComunWhClaselinea coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunWhClaselinea dto) throws UException {
		BeanWhClaselinea whClaselinea = dto.obtenerBean();
		whClaselinea = coreInsertar(usuarioActual, whClaselinea,dto);

		dto.setTransaccionEstado(whClaselinea.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClaselinea.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClaselinea coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea,DtoComunWhClaselinea dto) throws UException {
		// valores por defecto - preparando objeto
		whClaselinea = validar.prepararInsertar(usuarioActual, whClaselinea);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertarLineas(usuarioActual, whClaselinea,dto);
		if (!lst.isEmpty()) {
			whClaselinea.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClaselinea.setTransaccionListaMensajes(lst);
			return whClaselinea;
		}else {
			// transaccion
			whClaselinea.setDescripcionlocal(whClaselinea.getDescripcionlocal().toUpperCase());
			whClaselinea = whClaselineaDao.coreInsertar(whClaselinea);
			whClaselinea.setTransaccionEstado(DominioTransaccion.OK);
			whClaselinea.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
					
			if(dto.getLstDetalle().size()>0) {
				for (DtoComunWhClasefamilia detalle : dto.getLstDetalle()) {
					detalle.setLineafamilia(detalle.getLinea().trim().concat(detalle.getFamilia()));
					detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
					serviceFamilia.coreInsertar(usuarioActual, detalle);
				}
			}
		}
		

		return whClaselinea;
	}

	@Transactional
	public DtoComunWhClaselinea coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunWhClaselinea dto) throws UException {
		BeanWhClaselinea whClaselinea = dto.obtenerBean();
		whClaselinea = coreActualizar(usuarioActual, whClaselinea,dto);
			
		dto.setTransaccionEstado(whClaselinea.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClaselinea.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClaselinea coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea,DtoComunWhClaselinea dto) throws UException {
		// valores por defecto - preparando objeto
		whClaselinea = validar.prepararActualizar(usuarioActual, whClaselinea);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizarLineas(usuarioActual, whClaselinea,dto);
		if (!lst.isEmpty()) {
			whClaselinea.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClaselinea.setTransaccionListaMensajes(lst);
			return whClaselinea;
		}else {
			
			// transaccion
			whClaselinea.setDescripcionlocal(whClaselinea.getDescripcionlocal().toUpperCase());
		    whClaselinea = whClaselineaDao.coreActualizar(whClaselinea);
			whClaselinea.setTransaccionEstado(DominioTransaccion.OK);
			whClaselinea.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
			
			
			if(dto.getFlagFamilia()) {
				if(dto.getLstDetalleSubFamilia().size()>0) {
					for (DtoComunWhClasesubfamilia detalle : dto.getLstDetalleSubFamilia()) {
						if(detalle.getAccion().equals("N")) {

							logger.debug("nuevo");
							whClasesubfamiliaServicioImpl.coreInsertar(usuarioActual, detalle);
						}
						else if(detalle.getAccion().equals("A")) {
													
							logger.debug("update");
							whClasesubfamiliaServicioImpl.coreActualizar(usuarioActual, detalle);
						}
						else if(detalle.getAccion().equals("E")) {
							logger.debug("delete");
							whClasesubfamiliaServicioImpl.coreEliminar(usuarioActual, detalle);
						}
						
					}
				}
				
			}else {
				if(dto.getLstDetalle().size()>0) {
					for (DtoComunWhClasefamilia detalle : dto.getLstDetalle()) {
						if(detalle.getAccion().equals("N")) {
							detalle.setLineafamilia(detalle.getLinea().trim().concat(detalle.getFamilia()));
							detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
							logger.debug("nuevo");
							serviceFamilia.coreInsertar(usuarioActual, detalle);
						}
						else if(detalle.getAccion().equals("A")) {
							
							detalle.setDescripcionlocal(detalle.getDescripcionlocal().toUpperCase());
							logger.debug("update");
							serviceFamilia.coreActualizar(usuarioActual, detalle);
						}
						else if(detalle.getAccion().equals("E")) {
							logger.debug("delete");
							serviceFamilia.coreEliminar(usuarioActual, detalle);
						}
						
					}
				}
			}

		}
		

		return whClaselinea;
	}


	@Transactional
	public DtoComunWhClaselinea coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunWhClaselinea dto) throws UException {
		BeanWhClaselinea whClaselinea = dto.obtenerBean();
		whClaselinea = coreAnular(usuarioActual, whClaselinea);
		dto.setTransaccionEstado(whClaselinea.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClaselinea.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClaselinea coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea) throws UException {
		// valores por defecto - preparando objeto
		whClaselinea = validar.prepararAnular(usuarioActual, whClaselinea);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, whClaselinea);
		if (!lst.isEmpty()) {
			whClaselinea.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClaselinea.setTransaccionListaMensajes(lst);
			return whClaselinea;
		}
		
		// transaccion
	    whClaselinea.setEstado("I");
	    whClaselinea = whClaselineaDao.coreActualizar(whClaselinea);
		whClaselinea.setTransaccionEstado(DominioTransaccion.OK);
		whClaselinea.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return whClaselinea;
	}

	public BeanWhClaselinea coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClaselineaPk pk) throws UException {
		BeanWhClaselinea bean = whClaselineaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanWhClaselinea coreAnular(SeguridadUsuarioActual usuarioActual, String plinea) throws UException {
		return coreAnular(usuarioActual,new BeanWhClaselineaPk( plinea));
	}

	@Transactional
	public DtoComunWhClaselinea coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunWhClaselinea dto) throws UException {
		

			if(dto.getLstDetalle().size()>0) {
				for (DtoComunWhClasefamilia detalle : dto.getLstDetalle()) {
					serviceFamilia.coreEliminar(usuarioActual, detalle);
				}
			}
		

		
		BeanWhClaselinea whClaselinea = dto.obtenerBean();
		whClaselinea = coreEliminar(usuarioActual, whClaselinea);
		dto.setTransaccionEstado(whClaselinea.getTransaccionEstado());
		dto.setTransaccionListaMensajes(whClaselinea.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanWhClaselinea coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea) throws UException {
		// valores por defecto - preparando objeto
		whClaselinea = validar.prepararEliminar(usuarioActual, whClaselinea);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, whClaselinea);
		if (!lst.isEmpty()) {
			whClaselinea.setTransaccionEstado(DominioTransaccion.VALIDACION);
			whClaselinea.setTransaccionListaMensajes(lst);
			return whClaselinea;
		}
		
		// transaccion
		whClaselineaDao.eliminar(whClaselinea);
		whClaselinea=new BeanWhClaselinea();
		whClaselinea.setTransaccionEstado(DominioTransaccion.OK);
		return whClaselinea;
	}

	public BeanWhClaselinea coreEliminar(SeguridadUsuarioActual usuarioActual, BeanWhClaselineaPk pk) throws UException {
		BeanWhClaselinea whClaselinea = whClaselineaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,whClaselinea);
	}

	public BeanWhClaselinea coreEliminar(SeguridadUsuarioActual usuarioActual, String plinea) throws UException {
		return coreEliminar(usuarioActual,new BeanWhClaselineaPk( plinea));
	}

}
