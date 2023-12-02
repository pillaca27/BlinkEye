package net.royal.spring.dt.servicio.impl;

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
import net.royal.spring.dt.dao.impl.DocentesDaoImpl;

import net.royal.spring.dt.dominio.Docentes;
import net.royal.spring.dt.dominio.DocentesPk;
import net.royal.spring.dt.dominio.dto.DtoDocentes;

@Service (value = "BeanServicioDocentes")
public class DocentesServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioDocentes";
	private static Logger logger = LogManager.getLogger(DocentesServicioImpl.class);

	@Autowired
	private DocentesDaoImpl docentesDao;

	@Autowired
	private DocentesServicioValidar validar;

	@Transactional
	public DtoDocentes coreInsertar(SeguridadUsuarioActual usuarioActual,DtoDocentes dto) throws Exception {
		Docentes docentes = dto.obtenerBeanRegistrar();
		docentes = coreInsertar(usuarioActual, docentes);
		dto.setTransaccionEstado(docentes.getTransaccionEstado());
		dto.setTransaccionListaMensajes(docentes.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Docentes coreInsertar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		// valores por defecto - preparando objeto
		docentes = validar.prepararInsertar(usuarioActual, docentes);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, docentes);
		if (!lst.isEmpty()) {
			docentes.setTransaccionEstado(DominioTransaccion.VALIDACION);
			docentes.setTransaccionListaMensajes(lst);
			return docentes;
		}
		
		// transaccion
		docentes = docentesDao.coreInsertar(docentes);
		docentes.setTransaccionEstado(DominioTransaccion.OK);
		docentes.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return docentes;
	}

	@Transactional
	public DtoDocentes coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoDocentes dto) throws Exception {
		Docentes docentes = docentesDao.obtenerPorId(dto.getIdDocente());
		docentes = dto.obtenerBeanActualizar(docentes);
		docentes = coreActualizar(usuarioActual, docentes);
		dto.setTransaccionEstado(docentes.getTransaccionEstado());
		dto.setTransaccionListaMensajes(docentes.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoDocentes coreActualizar(SeguridadUsuarioActual usuarioActual,DtoDocentes dto) throws Exception {
		Docentes docentes = dto.obtenerBean();
		docentes = coreActualizar(usuarioActual, docentes);
		dto.setTransaccionEstado(docentes.getTransaccionEstado());
		dto.setTransaccionListaMensajes(docentes.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Docentes coreActualizar(SeguridadUsuarioActual usuarioActual, Docentes docentes) throws Exception {
		// valores por defecto - preparando objeto
		docentes = validar.prepararActualizar(usuarioActual, docentes);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, docentes);
		if (!lst.isEmpty()) {
			docentes.setTransaccionEstado(DominioTransaccion.VALIDACION);
			docentes.setTransaccionListaMensajes(lst);
			return docentes;
		}
		
		// transaccion
	    docentes = docentesDao.coreActualizar(docentes);
		docentes.setTransaccionEstado(DominioTransaccion.OK);
		docentes.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return docentes;
	}

	@Transactional
	public DtoDocentes coreEliminar(SeguridadUsuarioActual usuarioActual,DtoDocentes dto) throws Exception {
		Docentes docentes = dto.obtenerBean();
		docentes = coreEliminar(usuarioActual, docentes);
		dto.setTransaccionEstado(docentes.getTransaccionEstado());
		dto.setTransaccionListaMensajes(docentes.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public Docentes coreEliminar(SeguridadUsuarioActual usuarioActual, Docentes docentes) throws Exception {
		// valores por defecto - preparando objeto
		docentes = validar.prepararEliminar(usuarioActual, docentes);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, docentes);
		if (!lst.isEmpty()) {
			docentes.setTransaccionEstado(DominioTransaccion.VALIDACION);
			docentes.setTransaccionListaMensajes(lst);
			return docentes;
		}
		
		// transaccion
		docentesDao.eliminar(docentes);
		docentes=new Docentes();
		docentes.setTransaccionEstado(DominioTransaccion.OK);
		return docentes;
	}

	public Docentes coreEliminar(SeguridadUsuarioActual usuarioActual, DocentesPk pk) throws Exception {
		Docentes docentes = docentesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,docentes);
	}

	public Docentes coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pidDocente) throws Exception {
		return coreEliminar(usuarioActual,new DocentesPk( pidDocente));
	}

}
