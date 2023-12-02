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
import net.royal.spring.core.dao.impl.MaPersonapersonagrupoDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonapersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonapersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;
import net.royal.spring.core.servicio.validar.MaPersonapersonagrupoServicioValidar;

@Service (value = "BeanServicioMaPersonapersonagrupo")
public class MaPersonapersonagrupoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMaPersonapersonagrupo";
	private static Logger logger = LogManager.getLogger(MaPersonapersonagrupoServicioImpl.class);

	@Autowired
	private MaPersonapersonagrupoDaoImpl maPersonapersonagrupoDao;

	@Autowired
	private MaPersonapersonagrupoServicioValidar validar;

	@Transactional
	public DtoComunMaPersonapersonagrupo coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonapersonagrupo dto) throws UException {
		BeanMaPersonapersonagrupo maPersonapersonagrupo = dto.obtenerBean();
		maPersonapersonagrupo = coreInsertar(usuarioActual, maPersonapersonagrupo);
		dto.setTransaccionEstado(maPersonapersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonapersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonapersonagrupo coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonapersonagrupo = validar.prepararInsertar(usuarioActual, maPersonapersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, maPersonapersonagrupo);
		if (!lst.isEmpty()) {
			maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonapersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonapersonagrupo;
		}
		
		// transaccion
		maPersonapersonagrupo = maPersonapersonagrupoDao.coreInsertar(maPersonapersonagrupo);
		maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		maPersonapersonagrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonapersonagrupo;
	}

	@Transactional
	public DtoComunMaPersonapersonagrupo coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonapersonagrupo dto) throws UException {
		BeanMaPersonapersonagrupo maPersonapersonagrupo = dto.obtenerBean();
		maPersonapersonagrupo = coreActualizar(usuarioActual, maPersonapersonagrupo);
		dto.setTransaccionEstado(maPersonapersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonapersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonapersonagrupo coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonapersonagrupo maPersonapersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonapersonagrupo = validar.prepararActualizar(usuarioActual, maPersonapersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, maPersonapersonagrupo);
		if (!lst.isEmpty()) {
			maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonapersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonapersonagrupo;
		}
		
		// transaccion
	    maPersonapersonagrupo = maPersonapersonagrupoDao.coreActualizar(maPersonapersonagrupo);
		maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		maPersonapersonagrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return maPersonapersonagrupo;
	}

	@Transactional
	public DtoComunMaPersonapersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunMaPersonapersonagrupo dto) throws UException {
		BeanMaPersonapersonagrupo maPersonapersonagrupo = dto.obtenerBean();
		maPersonapersonagrupo = coreEliminar(usuarioActual, maPersonapersonagrupo);
		dto.setTransaccionEstado(maPersonapersonagrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(maPersonapersonagrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanMaPersonapersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonapersonagrupo maPersonapersonagrupo) throws UException {
		// valores por defecto - preparando objeto
		maPersonapersonagrupo = validar.prepararEliminar(usuarioActual, maPersonapersonagrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, maPersonapersonagrupo);
		if (!lst.isEmpty()) {
			maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			maPersonapersonagrupo.setTransaccionListaMensajes(lst);
			return maPersonapersonagrupo;
		}
		
		// transaccion
		maPersonapersonagrupoDao.eliminar(maPersonapersonagrupo);
		maPersonapersonagrupo=new BeanMaPersonapersonagrupo();
		maPersonapersonagrupo.setTransaccionEstado(DominioTransaccion.OK);
		return maPersonapersonagrupo;
	}

	public BeanMaPersonapersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanMaPersonapersonagrupoPk pk) throws UException {
		BeanMaPersonapersonagrupo maPersonapersonagrupo = maPersonapersonagrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,maPersonapersonagrupo);
	}

	public BeanMaPersonapersonagrupo coreEliminar(SeguridadUsuarioActual usuarioActual, Integer ppersona,String ppersonagrupo) throws UException {
		return coreEliminar(usuarioActual,new BeanMaPersonapersonagrupoPk( ppersona, ppersonagrupo));
	}

}
