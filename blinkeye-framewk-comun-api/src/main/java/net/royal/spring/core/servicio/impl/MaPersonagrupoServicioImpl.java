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
import net.royal.spring.core.dao.impl.MaPersonagrupoDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonagrupo;
import net.royal.spring.core.servicio.validar.MaPersonagrupoServicioValidar;

@Service (value = "BeanServicioMaPersonagrupo")
public class MaPersonagrupoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaPersonagrupo";
	private static Logger logger = LogManager.getLogger(MaPersonagrupoServicioImpl.class);

	@Autowired
	private MaPersonagrupoDaoImpl maPersonagrupoDao;

	@Autowired
	private MaPersonagrupoServicioValidar validar;

	@Transactional
	public DtoComunMaPersonagrupo coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonagrupo dto) throws UException {
		BeanMaPersonagrupo maPersonagrupo = dto.obtenerBean();
		maPersonagrupo = coreInsertar(usuarioActual, maPersonagrupo);
		dto.setTransaccionEstado(maPersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonagrupo coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonagrupo = validar.prepararInsertar(usuarioActual, maPersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty()) {
			maPersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonagrupo;
		}
		
		// transaccion
		maPersonagrupo = maPersonagrupoDao.coreInsertar(maPersonagrupo);
		maPersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		maPersonagrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonagrupo;
	}

	@Transactional
	public DtoComunMaPersonagrupo coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonagrupo dto) throws UException {
		BeanMaPersonagrupo maPersonagrupo = dto.obtenerBean();
		maPersonagrupo = coreActualizar(usuarioActual, maPersonagrupo);
		dto.setTransaccionEstado(maPersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonagrupo coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonagrupo = validar.prepararActualizar(usuarioActual, maPersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty()) {
			maPersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonagrupo;
		}
		
		// transaccion
	    maPersonagrupo = maPersonagrupoDao.coreActualizar(maPersonagrupo);
		maPersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		maPersonagrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonagrupo;
	}


	@Transactional
	public DtoComunMaPersonagrupo coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonagrupo dto) throws UException {
		BeanMaPersonagrupo maPersonagrupo = dto.obtenerBean();
		maPersonagrupo = coreAnular(usuarioActual, maPersonagrupo);
		dto.setTransaccionEstado(maPersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonagrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonagrupo = validar.prepararAnular(usuarioActual, maPersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty()) {
			maPersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonagrupo;
		}
		
		// transaccion
	    maPersonagrupo.setEstado("I");
	    maPersonagrupo = maPersonagrupoDao.coreActualizar(maPersonagrupo);
		maPersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		maPersonagrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupoPk pk) throws UException {
		BeanMaPersonagrupo bean = maPersonagrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanMaPersonagrupo coreAnular(SeguridadUsuarioActual usuarioActual, String ppersonagrupo) throws UException {
		return coreAnular(usuarioActual,new BeanMaPersonagrupoPk( ppersonagrupo));
	}

	@Transactional
	public DtoComunMaPersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonagrupo dto) throws UException {
		BeanMaPersonagrupo maPersonagrupo = dto.obtenerBean();
		maPersonagrupo = coreEliminar(usuarioActual, maPersonagrupo);
		dto.setTransaccionEstado(maPersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonagrupo = validar.prepararEliminar(usuarioActual, maPersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty()) {
			maPersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonagrupo;
		}
		
		// transaccion
		maPersonagrupoDao.eliminar(maPersonagrupo);
		maPersonagrupo=new BeanMaPersonagrupo();
		maPersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupoPk pk) throws UException {
		BeanMaPersonagrupo maPersonagrupo = maPersonagrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maPersonagrupo);
	}

	public BeanMaPersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, String ppersonagrupo) throws UException {
		return coreEliminar(usuarioActual,new BeanMaPersonagrupoPk( ppersonagrupo));
	}

}
