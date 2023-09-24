package com.royal.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.royal.dao.ParametrosmastDaoImpl;
import com.royal.dominio.Parametrosmast;
import com.royal.dominio.ParametrosmastPk;
import com.royal.dominio.dto.DtoParametros;
import com.royal.util.DominioMensajeUsuario;
import com.royal.util.DominioTransaccion;
import com.royal.util.SeguridadUsuarioActual;
import com.royal.util.UException;

@Service(value = "BeanServicioParametros")
public class ParametrosmastServicioImpl {
	
	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;
	
	@Autowired
	private  ParametrosmastValidarImpl validar;

	@Transactional
	public DtoParametros coreInsertar(SeguridadUsuarioActual usuarioActual,DtoParametros dto) throws UException {
		dto.setUltimafechamodif(new Date());
		dto.setUltimousuario(usuarioActual.getUsuario());
		
		Parametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreInsertar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Parametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual,Parametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararInsertar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
		parametrosmast = parametrosmastDao.coreInsertar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	@Transactional
	public DtoParametros coreActualizar(SeguridadUsuarioActual usuarioActual,DtoParametros dto) throws UException {
		Parametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreActualizar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Parametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararActualizar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
	    parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}


	@Transactional
	public DtoParametros coreAnular(SeguridadUsuarioActual usuarioActual,DtoParametros dto) throws UException {
		Parametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreAnular(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Parametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararAnular(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
	    parametrosmast.setEstado("I");
	    parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	public Parametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, ParametrosmastPk pk) throws UException {
		Parametrosmast bean = parametrosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public Parametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, String companiacodigo,String aplicacioncodigo,String parametroclave) throws UException {
		return coreAnular(usuarioActual,new ParametrosmastPk(companiacodigo,  aplicacioncodigo,  parametroclave));
	}

	@Transactional
	public DtoParametros coreEliminar(SeguridadUsuarioActual usuarioActual,DtoParametros dto) throws UException {
		Parametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreEliminar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Parametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararEliminar(usuarioActual, parametrosmast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}
		
		// transaccion
		parametrosmastDao.eliminar(parametrosmast);
		parametrosmast=new Parametrosmast();
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		return parametrosmast;
	}

	public Parametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, ParametrosmastPk pk) throws UException {
		Parametrosmast parametrosmast = parametrosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,parametrosmast);
	}

	public Parametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual,  String companiacodigo,String aplicacioncodigo,String parametroclave) throws UException {
		return coreEliminar(usuarioActual,new ParametrosmastPk(companiacodigo,  aplicacioncodigo,  parametroclave));
	}


}
