package net.royal.spring.core.servicio.impl;

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
import net.royal.spring.core.dao.impl.MaPersonacuentabancariaDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonacuentabancaria;
import net.royal.spring.core.dominio.BeanMaPersonacuentabancariaPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonacuentabancaria;
import net.royal.spring.core.servicio.validar.MaPersonacuentabancariaServicioValidar;

@Service (value = "BeanServicioMaPersonacuentabancaria")
public class MaPersonacuentabancariaServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaPersonacuentabancaria";
	private static Logger logger = LogManager.getLogger(MaPersonacuentabancariaServicioImpl.class);

	@Autowired
	private MaPersonacuentabancariaDaoImpl maPersonacuentabancariaDao;

	@Autowired
	private MaPersonacuentabancariaServicioValidar validar;

	@Transactional
	public DtoComunMaPersonacuentabancaria coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonacuentabancaria dto) throws UException {
		BeanMaPersonacuentabancaria maPersonacuentabancaria = dto.obtenerBean();
		maPersonacuentabancaria = coreInsertar(usuarioActual, maPersonacuentabancaria);
		dto.setTransaccionEstado(maPersonacuentabancaria.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonacuentabancaria.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonacuentabancaria coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) throws UException {
		// valores por defecto - preparando objeto
		maPersonacuentabancaria = validar.prepararInsertar(usuarioActual, maPersonacuentabancaria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty()) {
			maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonacuentabancaria.setTransaccionListaMensajes(lst);
			return maPersonacuentabancaria;
		}
		
		// transaccion
		maPersonacuentabancaria = maPersonacuentabancariaDao.coreInsertar(maPersonacuentabancaria);
		maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.OK);
		maPersonacuentabancaria.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonacuentabancaria;
	}

	@Transactional
	public DtoComunMaPersonacuentabancaria coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonacuentabancaria dto) throws UException {
		BeanMaPersonacuentabancaria maPersonacuentabancaria = dto.obtenerBean();
		maPersonacuentabancaria = coreActualizar(usuarioActual, maPersonacuentabancaria);
		dto.setTransaccionEstado(maPersonacuentabancaria.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonacuentabancaria.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonacuentabancaria coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) throws UException {
		// valores por defecto - preparando objeto
		maPersonacuentabancaria = validar.prepararActualizar(usuarioActual, maPersonacuentabancaria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty()) {
			maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonacuentabancaria.setTransaccionListaMensajes(lst);
			return maPersonacuentabancaria;
		}
		
		// transaccion
	    maPersonacuentabancaria = maPersonacuentabancariaDao.coreActualizar(maPersonacuentabancaria);
		maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.OK);
		maPersonacuentabancaria.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonacuentabancaria;
	}


	@Transactional
	public DtoComunMaPersonacuentabancaria coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonacuentabancaria dto) throws UException {
		BeanMaPersonacuentabancaria maPersonacuentabancaria = dto.obtenerBean();
		maPersonacuentabancaria = coreAnular(usuarioActual, maPersonacuentabancaria);
		dto.setTransaccionEstado(maPersonacuentabancaria.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonacuentabancaria.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonacuentabancaria coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) throws UException {
		// valores por defecto - preparando objeto
		maPersonacuentabancaria = validar.prepararAnular(usuarioActual, maPersonacuentabancaria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty()) {
			maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonacuentabancaria.setTransaccionListaMensajes(lst);
			return maPersonacuentabancaria;
		}
		
		// transaccion
	    maPersonacuentabancaria.setEstado("I");
	    maPersonacuentabancaria = maPersonacuentabancariaDao.coreActualizar(maPersonacuentabancaria);
		maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.OK);
		maPersonacuentabancaria.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancariaPk pk) throws UException {
		BeanMaPersonacuentabancaria bean = maPersonacuentabancariaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanMaPersonacuentabancaria coreAnular(SeguridadUsuarioActual usuarioActual, Integer ppersona,Integer psecuencia) throws UException {
		return coreAnular(usuarioActual,new BeanMaPersonacuentabancariaPk( ppersona, psecuencia));
	}

	@Transactional
	public DtoComunMaPersonacuentabancaria coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonacuentabancaria dto) throws UException {
		BeanMaPersonacuentabancaria maPersonacuentabancaria = dto.obtenerBean();
		maPersonacuentabancaria = coreEliminar(usuarioActual, maPersonacuentabancaria);
		dto.setTransaccionEstado(maPersonacuentabancaria.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonacuentabancaria.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonacuentabancaria coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) throws UException {
		// valores por defecto - preparando objeto
		maPersonacuentabancaria = validar.prepararEliminar(usuarioActual, maPersonacuentabancaria);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty()) {
			maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonacuentabancaria.setTransaccionListaMensajes(lst);
			return maPersonacuentabancaria;
		}
		
		// transaccion
		maPersonacuentabancariaDao.eliminar(maPersonacuentabancaria);
		maPersonacuentabancaria=new BeanMaPersonacuentabancaria();
		maPersonacuentabancaria.setTransaccionEstado(DominioTransaccion.OK);
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancariaPk pk) throws UException {
		BeanMaPersonacuentabancaria maPersonacuentabancaria = maPersonacuentabancariaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maPersonacuentabancaria);
	}

	public BeanMaPersonacuentabancaria coreEliminar(SeguridadUsuarioActual usuarioActual, Integer ppersona,Integer psecuencia) throws UException {
		return coreEliminar(usuarioActual,new BeanMaPersonacuentabancariaPk( ppersona, psecuencia));
	}

}
