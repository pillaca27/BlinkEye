package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.ProvinciaDaoImpl;
import net.royal.spring.core.dominio.BeanProvincia;
import net.royal.spring.core.dominio.BeanProvinciaPk;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.servicio.validar.ProvinciaServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioProvincia")
public class ProvinciaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioProvincia";
	private static Logger logger = LogManager.getLogger(ProvinciaServicioImpl.class);

	@Autowired
	private ProvinciaDaoImpl provinciaDao;

	@Autowired
	private ProvinciaServicioValidar validar;
	
	@Autowired
	private ZonapostalServicioImpl zonapostalServicioImpl;

	@Transactional
	public DtoComunProvincia coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunProvincia dto) throws UException {
		BeanProvincia provincia = dto.obtenerBean();
		provincia = coreInsertar(usuarioActual, provincia,dto);
		dto.setTransaccionEstado(provincia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(provincia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProvincia coreInsertar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia,DtoComunProvincia dto) throws UException {
		// valores por defecto - preparando objeto
		provincia = validar.prepararInsertar(usuarioActual, provincia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, provincia);
		if (!lst.isEmpty()) {
			provincia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			provincia.setTransaccionListaMensajes(lst);
			return provincia;
		}
		
		// transaccion
		provincia = provinciaDao.coreInsertar(provincia);
		
		provincia.setTransaccionEstado(DominioTransaccion.OK);
		provincia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return provincia;
	}

	@Transactional
	public DtoComunProvincia coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunProvincia dto) throws UException {
		BeanProvincia provincia = dto.obtenerBean();
		provincia = coreActualizar(usuarioActual, provincia,dto);
		dto.setTransaccionEstado(provincia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(provincia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProvincia coreActualizar(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia,DtoComunProvincia dto) throws UException {
		// valores por defecto - preparando objeto
		provincia = validar.prepararActualizar(usuarioActual, provincia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, provincia);
		if (!lst.isEmpty()) {
			provincia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			provincia.setTransaccionListaMensajes(lst);
			return provincia;
		}
		

		
		// transaccion
	    provincia = provinciaDao.coreActualizar(provincia);
		provincia.setTransaccionEstado(DominioTransaccion.OK);
		provincia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return provincia;
	}


	@Transactional
	public DtoComunProvincia coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunProvincia dto) throws UException {
		BeanProvincia provincia = dto.obtenerBean();
		provincia = coreAnular(usuarioActual, provincia);
		dto.setTransaccionEstado(provincia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(provincia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProvincia coreAnular(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia) throws UException {
		// valores por defecto - preparando objeto
		provincia = validar.prepararAnular(usuarioActual, provincia);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, provincia);
		if (!lst.isEmpty()) {
			provincia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			provincia.setTransaccionListaMensajes(lst);
			return provincia;
		}
		
		// transaccion
	    provincia.setEstado("I");
	    provincia = provinciaDao.coreActualizar(provincia);
		provincia.setTransaccionEstado(DominioTransaccion.OK);
		provincia.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return provincia;
	}

	public BeanProvincia coreAnular(SeguridadUsuarioActual usuarioActual, BeanProvinciaPk pk) throws UException {
		BeanProvincia bean = provinciaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}



	@Transactional
	public DtoComunProvincia coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunProvincia dto) throws UException {
		BeanProvincia provincia = dto.obtenerBean();
		provincia = coreEliminar(usuarioActual, provincia,dto);
		dto.setTransaccionEstado(provincia.getTransaccionEstado());
		dto.setTransaccionListaMensajes(provincia.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanProvincia coreEliminar(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia,DtoComunProvincia dto) throws UException {
		// valores por defecto - preparando objeto
		provincia = validar.prepararEliminar(usuarioActual, provincia);
		
		if(dto.getZonaPostal().size() > 0) {
			
			for (DtoComunZonapostal detalle : dto.getZonaPostal()) {				
					zonapostalServicioImpl.coreEliminar(usuarioActual, detalle);					
			}
		}
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, provincia);
		if (!lst.isEmpty()) {
			provincia.setTransaccionEstado(DominioTransaccion.VALIDACION);
			provincia.setTransaccionListaMensajes(lst);
			return provincia;
		}
		
		// transaccion
		provinciaDao.eliminar(provincia);
		provincia=new BeanProvincia();
		provincia.setTransaccionEstado(DominioTransaccion.OK);
		return provincia;
	}




}
