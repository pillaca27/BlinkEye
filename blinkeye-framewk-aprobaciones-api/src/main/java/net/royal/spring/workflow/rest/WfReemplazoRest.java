package net.royal.spring.workflow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.workflow.dominio.WfReemplazo;
import net.royal.spring.workflow.dominio.WfReemplazoPk;
import net.royal.spring.workflow.dominio.dto.DtoReemplazoPaginacion;
import net.royal.spring.workflow.servicio.impl.WfReemplazoServicioImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/spring/workflow/wfreemplazo")
public class WfReemplazoRest extends GenericoRest {

	@Autowired
	private WfReemplazoServicioImpl servicio;

	@Transactional
	@PostMapping(value = "/listarPaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody DtoReemplazoPaginacion paginacion)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(
				servicio.listarPaginado(getUsuarioActual(), paginacion.getPaginacion(), paginacion.getFiltro()),
				HttpStatus.OK);
	}

	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfReemplazoPk> registrar(@RequestBody WfReemplazo bean) throws Exception {
		SeguridadUsuarioActual usuarioActual = getUsuarioActual();
		WfReemplazoPk pk = servicio.registrar(usuarioActual, bean);
		bean.setPk(pk);
		servicio.reemplazarNivelesAprobacion(bean, usuarioActual);
		return new ResponseEntity<WfReemplazoPk>(pk, HttpStatus.OK);
	}

	@PostMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfReemplazoPk> actualizar(@RequestBody WfReemplazo bean) throws Exception {
		SeguridadUsuarioActual usuarioActual = new SeguridadUsuarioActual();
		usuarioActual.setUsuario("MISESF");
		WfReemplazoPk pk = servicio.actualizar(usuarioActual, bean);
		servicio.reemplazarNivelesAprobacion(bean, usuarioActual);
		return new ResponseEntity<WfReemplazoPk>(pk, HttpStatus.OK);
	}

	@PostMapping(value = "/obtenerPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfReemplazo> obtenerPorId(@RequestBody WfReemplazoPk pk) throws Exception {
		return new ResponseEntity<WfReemplazo>(servicio.obtenerPorId(pk), HttpStatus.OK);
	}

	@PostMapping(value = "/anular", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfReemplazoPk> anular(@RequestBody WfReemplazoPk pk) throws Exception {
		WfReemplazo b = servicio.obtenerPorId(pk);
		b.setEstado("I");
		servicio.actualizar(getUsuarioActual(), b);
		return new ResponseEntity<WfReemplazoPk>(pk, HttpStatus.OK);
	}

}
