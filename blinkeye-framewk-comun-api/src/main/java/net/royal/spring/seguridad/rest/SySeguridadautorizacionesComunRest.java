package net.royal.spring.seguridad.rest;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionreporteComunDaoImpl;
import net.royal.spring.seguridad.dao.impl.SySeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.dto.DtoComunAutorizacionConcepto;
import net.royal.spring.seguridad.dominio.dto.DtoComunAutorizacionFuncion;
import net.royal.spring.seguridad.servicio.impl.SeguridadautorizacionreporteServicioImpl;
import net.royal.spring.seguridad.servicio.impl.SySeguridadautorizacionesServicioImpl;
import net.royal.spring.seguridad.servicio.validar.SySeguridadautorizacionesServicioValidar;

@RestController
@RequestMapping("/spring/seguridad/syseguridadautorizaciones")
@CrossOrigin(origins = "*")
public class SySeguridadautorizacionesComunRest extends GenericoHibernateRest {

	public SySeguridadautorizacionesComunRest() {
		super("syseguridadautorizacionesrest");
	}

	private static Logger logger = LogManager.getLogger(SySeguridadautorizacionesComunRest.class);

	@Autowired
	private SySeguridadautorizacionesServicioImpl servicio;

	@Autowired
	private SySeguridadautorizacionesServicioValidar validar;

	@Autowired
	private SySeguridadautorizacionesComunDaoImpl consulta;
	

	@Autowired
	private SeguridadautorizacionreporteServicioImpl serviciorep;

	@Autowired
	private SeguridadautorizacionreporteComunDaoImpl consultarep;

	@Transactional
	@PutMapping(value = "/validar/{accion}/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@Validated @PathVariable String aplicacioncodigo, @Validated @PathVariable String grupo,
			@Validated @PathVariable String concepto, @Validated @PathVariable String usuario,
			@RequestBody BeanSySeguridadautorizaciones bean) throws Exception {
		logger.debug("SySeguridadautorizacionesRest.validar");
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean, aplicacioncodigo, grupo,
				concepto, usuario);
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@GetMapping(value = "/obtenerporid/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSySeguridadautorizaciones> obtenerPorId(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SySeguridadautorizacionesRest.obtenerPorId");
		BeanSySeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSySeguridadautorizaciones>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BeanSySeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSySeguridadautorizaciones> registrar(@RequestBody BeanSySeguridadautorizaciones bean)
			throws Exception {
		logger.debug("SySeguridadautorizacionesRest.registrar");
		
		bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSySeguridadautorizaciones>(bean, HttpStatus.CREATED);
	}
	
	@Transactional
	@PostMapping(value = "/registrarSeguridadAutorizacionVarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSySeguridadautorizaciones> registrarSeguridadAutorizacionVarios(@RequestBody BeanSySeguridadautorizaciones bean)
			throws Exception {
		logger.debug("SySeguridadautorizacionesRest.registrarSeguridadAutorizacionVarios");
		
		bean.setEstado("A");
		bean.setUltimousuario(this.getUsuarioActual().getUsuario());
		bean.setUltimafechamodif(new Date());
		BeanSySeguridadautorizaciones obtenerporidvarios = consulta.obtenerPorId(bean.getPk().getAplicacioncodigo(), bean.getPk().getGrupo(),
				bean.getPk().getConcepto(),bean.getPk().getUsuario());
		
		if(obtenerporidvarios == null) {
			bean = consulta.coreInsertar(bean);
		}else {
			bean = null;
		}
		
		
		return new ResponseEntity<BeanSySeguridadautorizaciones>(bean, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSySeguridadautorizaciones> actualizar(@RequestBody BeanSySeguridadautorizaciones bean)
			throws Exception {
		logger.debug("SySeguridadautorizacionesRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSySeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSySeguridadautorizaciones> anular(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SySeguridadautorizacionesRest.anular");
		BeanSySeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSySeguridadautorizaciones>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSySeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SySeguridadautorizacionesRest.eliminar");
		BeanSySeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PostMapping(value = "/updateListConcepto", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAutorizacionConcepto> updateListConcepto(@RequestBody List<DtoComunAutorizacionConcepto> lst)
			throws Exception {

		logger.debug("ConceptoSendDto.ConceptoSendDto");

		BeanSySeguridadautorizaciones bean = new BeanSySeguridadautorizaciones();
		List<DtoComunAutorizacionConcepto> lstReq = lst;

		// INIT UPDATE SEGURIDADCONCEPTO

		for (DtoComunAutorizacionConcepto items : lstReq) {
			if (items.getSeleccionar() == "true") {
				items.setSeleccionar("A");
			} else {
				items.setSeleccionar("I");
			}
			bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
			bean.getPk().setConcepto(items.getConcepto());
			bean.getPk().setGrupo(items.getGrupo());
			bean.getPk().setUsuario(items.getUsuariocodigo());
			bean = consulta.obtenerPorId(bean.getPk());
			if (bean == null) {
				bean = new BeanSySeguridadautorizaciones();
				bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
				bean.getPk().setConcepto(items.getConcepto());
				bean.getPk().setGrupo(items.getGrupo());
				bean.getPk().setUsuario(items.getUsernew());
				bean.setEstado(items.getSeleccionar());
				bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
			} else {
				if(items.getSeleccionar().equals("I")) {
					servicio.coreEliminar(this.getUsuarioActual(), bean);
				} else {
					bean.setEstado(items.getSeleccionar());
					bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
				}
				
			}

		}

		return lstReq;

	}
	
	@PostMapping(value = "/updateListReportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAutorizacionFuncion> updateListReportes(@RequestBody List<DtoComunAutorizacionFuncion> lst) throws UException {

		logger.debug("ConceptoSendDto.updateListReportes");

		BeanSeguridadautorizacionreporte bean = new BeanSeguridadautorizacionreporte();

		List<DtoComunAutorizacionFuncion> lstReq = lst;

		// INIT UPDATE SEGURIDADCONCEPTO

		for (DtoComunAutorizacionFuncion items : lstReq) {

			if (items.getSeleccionar().equals("true")) {
				items.setSeleccionar("S");
			}else {
				items.setSeleccionar("A");
			}

			logger.debug("APLICACION : " + items.getAplicacion());
			logger.debug("REPORTE : " + items.getGrupo());
			logger.debug("USUARIO : " + items.getUsernew());
			
			bean.getPk().setAplicacioncodigo(items.getAplicacion());
			bean.getPk().setReportecodigo(items.getGrupo()); 
			bean.getPk().setUsuario(items.getUsernew());
	 

			bean = consultarep.obtenerPorId(bean.getPk());

			if (bean == null) {

				bean = new BeanSeguridadautorizacionreporte();

				bean.getPk().setAplicacioncodigo(items.getAplicacion()); 
				bean.getPk().setReportecodigo(items.getGrupo());
				bean.getPk().setUsuario(items.getUsernew());
				bean.setDisponible(items.getSeleccionar());
				bean.setUltimafechamodif(new Date());
				bean.setUltimousuario(this.getUsuarioActual().getUsuario());
 
				

				bean = serviciorep.coreInsertar(this.getUsuarioActual(), bean);
			} else {

				bean.setUltimafechamodif(new Date());
				bean.setUltimousuario(this.getUsuarioActual().getUsuario());
				bean.setDisponible(items.getSeleccionar());

				bean = serviciorep.coreActualizar(this.getUsuarioActual(), bean);

			}

 

		}

		return lstReq;

	}

}
