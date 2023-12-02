package net.royal.spring.sistema.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApi;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApipath;
import net.royal.spring.sistema.dominio.dto.DtoComunWsDefinicion;
import net.royal.spring.sistema.dominio.filtro.FiltroComunApi;
import net.royal.spring.sistema.servicio.impl.SyApiServicioImpl;

@RestController
@RequestMapping("/spring/sistema/syapi")
@CrossOrigin(origins = "*")
public class SyApiRest extends GenericoRest {

	@Autowired
	private SyApiServicioImpl servicio;

	@Transactional(readOnly = true)
	@GetMapping(value = "/listarApis", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarApis() throws Exception {
		return new ResponseEntity<List<DtoTabla>>(servicio.listarApis(), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarrutaspaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarrutaspaginado(@RequestBody FiltroComunApi filtro) throws Exception {
		return new ResponseEntity<DominioPaginacion>(servicio.listarrutaspaginado(filtro), HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioTransaccion> registrar(@RequestBody DtoComunWsApi dto) throws Exception {
		return new ResponseEntity<DominioTransaccion>(servicio.registrar(dto), HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/registrarDefinicion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWsDefinicion> registrarDefinicion(@RequestBody DtoComunWsDefinicion dto) throws Exception {
		return new ResponseEntity<DtoComunWsDefinicion>(servicio.registrarDefinicion(dto), HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/registrarPath", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWsApipath> registrarPath(@RequestBody DtoComunWsApipath dto) throws Exception {
		return new ResponseEntity<DtoComunWsApipath>(servicio.registrarPath(dto), HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/obtenerDetallePath", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWsApipath> obtenerDetallePath(@RequestBody DtoComunWsApipath dto) throws Exception {
		return new ResponseEntity<DtoComunWsApipath>(servicio.obtenerDetallePath(dto), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/vistobueno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWsApipath> vistobueno(@RequestBody DtoComunWsApipath dto) throws Exception {
		return new ResponseEntity<DtoComunWsApipath>(servicio.vistobueno(dto), HttpStatus.OK);
	}

}
