package com.royal.rest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.royal.dao.ParametrosmastDaoImpl;
import com.royal.dominio.dto.DtoParametros;
import com.royal.dominio.dto.FiltroParametros;
import com.royal.genericos.GenericoRest;
import com.royal.servicio.ParametrosmastServicioImpl;
import com.royal.servicio.ParametrosmastValidarImpl;

@RestController
@RequestMapping("/spring/core/parametrosmast")
@CrossOrigin(origins = "*")
public class ParametosmastRest extends GenericoRest{

	@Autowired
	private ParametrosmastDaoImpl parametrosDaoImpl;
	
	@Autowired
	private ParametrosmastValidarImpl validar;
	
	@Autowired
	private ParametrosmastServicioImpl servicio;
	
	
	@Transactional
	@GetMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> prueba() throws Exception {

		String p = "Hola";

		return new ResponseEntity<String>(p, HttpStatus.OK);
	}

	
	@Transactional
	@PostMapping(value = "/listarParametros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoParametros>> listarParametros(@RequestBody FiltroParametros filtros) throws Exception {

		List<DtoParametros> lista = parametrosDaoImpl.listarParametros( filtros);

		return new ResponseEntity<List<DtoParametros>>(lista, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoParametros> validar( @RequestBody DtoParametros dto) throws Exception {
		dto = validar.core(this.getUsuarioActual(), dto.getAccion(), dto);
		return new ResponseEntity<DtoParametros>(dto, HttpStatus.OK);
	}
	
	
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoParametros> registrar(@RequestBody DtoParametros dto) throws Exception {
		dto =  servicio.coreInsertar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoParametros>(dto, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoParametros> actualizar(@RequestBody DtoParametros dto) throws Exception {
		dto = servicio.coreActualizar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoParametros>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoParametros> anular(@RequestBody DtoParametros dto) throws Exception {
		dto = servicio.coreAnular(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoParametros>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoParametros> eliminar(@RequestBody DtoParametros dto) throws Exception {
		dto = servicio.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoParametros>(dto,HttpStatus.OK);
	}
	
}