package net.royal.spring.contabilidad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.contabilidad.dao.impl.LastvouchernumberComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanLastvouchernumber;
import net.royal.spring.contabilidad.dominio.dto.DtoComunLastvouchernumber;
import net.royal.spring.contabilidad.servicio.validar.LastvouchernumberComunServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioLastvouchernumberComun")
public class LastvouchernumberComunServicioImpl extends GenericoServicioImpl {

	@Autowired
	private LastvouchernumberComunDaoImpl lastvouchernumberDao;

	@Autowired
	private LastvouchernumberComunServicioValidar validar;

	public static String SPRING_NOMBRE = "BeanServicioLastvouchernumberComun";
	private static Logger logger = LogManager.getLogger(LastvouchernumberComunServicioImpl.class);

	@Transactional
	public DtoComunLastvouchernumber coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunLastvouchernumber dto)
			throws UException {
		BeanLastvouchernumber lastvouchernumber = dto.obtenerBean();
		lastvouchernumber = coreActualizar(usuarioActual, lastvouchernumber);
		dto.setTransaccionEstado(lastvouchernumber.getTransaccionEstado());
		dto.setTransaccionListaMensajes(lastvouchernumber.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanLastvouchernumber coreActualizar(SeguridadUsuarioActual usuarioActual, BeanLastvouchernumber lastvouchernumber)
			throws UException {
		// valores por defecto - preparando objeto
		lastvouchernumber = validar.prepararActualizar(usuarioActual, lastvouchernumber);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, lastvouchernumber);
		if (!lst.isEmpty()) {
			lastvouchernumber.setTransaccionEstado(DominioTransaccion.VALIDACION);
			lastvouchernumber.setTransaccionListaMensajes(lst);
			return lastvouchernumber;
		}

		// transaccion
		lastvouchernumber = lastvouchernumberDao.coreActualizar(lastvouchernumber);
		lastvouchernumber.setTransaccionEstado(DominioTransaccion.OK);
		lastvouchernumber.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return lastvouchernumber;
	}

	@Transactional
	public DtoComunLastvouchernumber coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunLastvouchernumber dto)
			throws UException {
		BeanLastvouchernumber lastvouchernumber = dto.obtenerBean();
		lastvouchernumber = coreInsertar(usuarioActual, lastvouchernumber);
		dto.setTransaccionEstado(lastvouchernumber.getTransaccionEstado());
		dto.setTransaccionListaMensajes(lastvouchernumber.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanLastvouchernumber coreInsertar(SeguridadUsuarioActual usuarioActual, BeanLastvouchernumber lastvouchernumber)
			throws UException {
		// valores por defecto - preparando objeto
		lastvouchernumber = validar.prepararInsertar(usuarioActual, lastvouchernumber);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, lastvouchernumber);
		if (!lst.isEmpty()) {
			lastvouchernumber.setTransaccionEstado(DominioTransaccion.VALIDACION);
			lastvouchernumber.setTransaccionListaMensajes(lst);
			return lastvouchernumber;
		}

		// transaccion
		lastvouchernumber = lastvouchernumberDao.coreInsertar(lastvouchernumber);
		lastvouchernumber.setTransaccionEstado(DominioTransaccion.OK);
		lastvouchernumber.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return lastvouchernumber;
	}

}
