package net.royal.spring.core.rest;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dao.impl.ErrorDaoImpl;
import net.royal.spring.core.dominio.BeanError;
import net.royal.spring.core.dominio.filtro.FiltroComunPaginacionErrores;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sistema.dominio.dto.DtoComunError;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyLogWeb;

@RestController
@RequestMapping("/spring/core/errorcomun")
@CrossOrigin(origins = "*")
public class ErrorComunRest extends GenericoHibernateRest {
	private static Logger logger = LogManager.getLogger(ErrorComunRest.class);
	@Autowired
	private ErrorDaoImpl errorDaoImpl;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ErrorComunRest() {
		super("error");
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ErrorTransaccion> listaractivos(@RequestBody ErrorTransaccion dto) {
		try {
			BeanError bean = new BeanError();
			bean.setEstado(dto.getEstado());
			bean.setIdReglaNegocio(dto.getIdReglaNegocio());
			bean.setIdCorreo(dto.getIdCorreo());
			bean.setIdLogAlerta(dto.getIdLogAlerta());
			bean.setIdAlerta(dto.getIdAlerta());
			bean.setProceso(dto.getProceso());
			bean.setDescripcionError(dto.getDescripcionError());
			bean.setDominioMensajeUsuario(dto.getDominioMensajeUsuario());
			bean.setClassName(dto.getClassName());
			bean.setObjetoBasedatos(dto.getObjetoBasedatos());
			bean.setCreacionFecha(new Date());
			SeguridadUsuarioActual us = getUsuarioActual();
			if (us != null) {
				bean.setCreacionUsuario(us.getUsuario());
			}
			
			if (UString.esNuloVacio(bean.getEstado())){
				bean.setEstado("ACT");	
			}
			
			errorDaoImpl.registrar(bean);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		} catch (Exception e) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			e.printStackTrace();
		}
		return new ResponseEntity<ErrorTransaccion>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyLogWeb filtro) throws Exception {
		DominioPaginacion paginacion = errorDaoImpl.listarPaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/listarErrores", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarErrores(@RequestBody FiltroComunPaginacionErrores filtro) throws Exception {
		return errorDaoImpl.listarErrores(filtro);
	}

	@Transactional
	@PutMapping(value = "/actualizarEstadoMasivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunError> actualizarEstadoMasivo(@RequestBody DtoComunError bean) throws Exception {
		errorDaoImpl.actualizarEstadoMasivo(bean);
		return new ResponseEntity<DtoComunError>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/limpiarDatod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> limpiarInformativos(@RequestBody BeanError bean) throws Exception {
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			errorDaoImpl.elimpiarInformativos(bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
