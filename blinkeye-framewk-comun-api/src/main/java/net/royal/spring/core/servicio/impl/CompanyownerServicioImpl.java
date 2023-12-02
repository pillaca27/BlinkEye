package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.CompanyownerDaoImpl;
import net.royal.spring.core.dominio.BeanCompanyowner;
import net.royal.spring.core.dominio.BeanCompanyownerPk;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.core.servicio.validar.CompanyownerServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioCompanyowner")
public class CompanyownerServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCompanyowner";
	private static Logger logger = LogManager.getLogger(CompanyownerServicioImpl.class);

	@Autowired
	private CompanyownerDaoImpl companyownerDao;

	@Autowired
	private CompanyownerServicioValidar validar;

	@Transactional
	public DtoComunCompanyowner coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunCompanyowner dto) throws UException {
		BeanCompanyowner companyowner = dto.obtenerBean();
		companyowner = coreInsertar(usuarioActual, companyowner);
		dto.setTransaccionEstado(companyowner.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companyowner.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompanyowner coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) throws UException {
		// valores por defecto - preparando objeto
		companyowner = validar.prepararInsertar(usuarioActual, companyowner);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, companyowner);
		if (!lst.isEmpty()) {
			companyowner.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companyowner.setTransaccionListaMensajes(lst);
			return companyowner;
		}
		
		// transaccion
		companyowner = companyownerDao.coreInsertar(companyowner);
		companyowner.setTransaccionEstado(DominioTransaccion.OK);
		companyowner.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return companyowner;
	}

	@Transactional
	public DtoComunCompanyowner coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunCompanyowner dto) throws UException {
		BeanCompanyowner companyowner = dto.obtenerBean();
		companyowner = coreActualizar(usuarioActual, companyowner);
		dto.setTransaccionEstado(companyowner.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companyowner.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompanyowner coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCompanyowner companyowner) throws UException {
		// valores por defecto - preparando objeto
		companyowner = validar.prepararActualizar(usuarioActual, companyowner);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, companyowner);
		if (!lst.isEmpty()) {
			companyowner.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companyowner.setTransaccionListaMensajes(lst);
			return companyowner;
		}
		
		// transaccion
	    companyowner = companyownerDao.coreActualizar(companyowner);
		companyowner.setTransaccionEstado(DominioTransaccion.OK);
		companyowner.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return companyowner;
	}

	@Transactional
	public DtoComunCompanyowner coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunCompanyowner dto) throws UException {
		BeanCompanyowner companyowner = dto.obtenerBean();
		companyowner = coreEliminar(usuarioActual, companyowner);
		dto.setTransaccionEstado(companyowner.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companyowner.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompanyowner coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCompanyowner companyowner) throws UException {
		// valores por defecto - preparando objeto
		companyowner = validar.prepararEliminar(usuarioActual, companyowner);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, companyowner);
		if (!lst.isEmpty()) {
			companyowner.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companyowner.setTransaccionListaMensajes(lst);
			return companyowner;
		}
		
		// transaccion
		companyownerDao.eliminar(companyowner);
		companyowner=new BeanCompanyowner();
		companyowner.setTransaccionEstado(DominioTransaccion.OK);
		return companyowner;
	}

	public BeanCompanyowner coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCompanyownerPk pk) throws UException {
		BeanCompanyowner companyowner = companyownerDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,companyowner);
	}

	public BeanCompanyowner coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompanyowner) throws UException {
		return coreEliminar(usuarioActual,new BeanCompanyownerPk( pcompanyowner));
	}

}
