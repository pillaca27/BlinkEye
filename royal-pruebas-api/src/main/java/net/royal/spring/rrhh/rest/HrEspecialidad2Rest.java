package net.royal.spring.rrhh.rest;

import org.springframework.transaction.annotation.Transactional;
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

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad2;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad2;
import net.royal.spring.rrhh.servicio.impl.HrEspecialidad2ServicioImpl;

@RestController
@RequestMapping("/spring/rrhh/hrespecialidad2")
@CrossOrigin(origins = "*")
public class HrEspecialidad2Rest extends GenericoRest{

	
	@Autowired
	private HrEspecialidad2ServicioImpl hrEspecialidad2ServicioImpl;
	
//	@Autowired
//	private HrEspecialidad2ValidarImpl validar;
	
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarParametros", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarParametros(@RequestBody FiltroHrEspecialidad2 filtros) throws Exception {
		
		return hrEspecialidad2ServicioImpl.listarPaginacion(filtros);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerDto", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrEspecialidad2 obtenerDto(@RequestBody DtoHrEspecialidad2 dto) {
		return hrEspecialidad2ServicioImpl.obtenerDto(dto);
	}
	
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrEspecialidad2> registrar(@RequestBody DtoHrEspecialidad2 dto) throws Exception {
		
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		dto =  hrEspecialidad2ServicioImpl.coreInsertar(usuario,dto);
		return new ResponseEntity<DtoHrEspecialidad2>(dto, HttpStatus.CREATED);
	}
	
	@Transactional
	@PutMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrEspecialidad2> actualizar(@RequestBody DtoHrEspecialidad2 dto) throws Exception {
		
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		dto = hrEspecialidad2ServicioImpl.coreActualizar(usuario,dto);
		return new ResponseEntity<DtoHrEspecialidad2>(dto, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/anular",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrEspecialidad2> anular(@RequestBody DtoHrEspecialidad2 dto) throws Exception {
		
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		dto = hrEspecialidad2ServicioImpl.coreAnular(usuario, dto);
		return new ResponseEntity<DtoHrEspecialidad2>(dto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoHrEspecialidad2> eliminar(@RequestBody DtoHrEspecialidad2 dto) throws Exception {
		
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		dto = hrEspecialidad2ServicioImpl.coreEliminar(this.getUsuarioActual(),dto);
		return new ResponseEntity<DtoHrEspecialidad2>(dto,HttpStatus.OK);
	}
	
	
//	@Transactional
//	@PutMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<DtoHrEspecialidad2> validar( @RequestBody DtoHrEspecialidad2 dto) throws Exception {
//		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
//		usuario.setUsuario("MISESF");
//		usuario.setPersonaId(-1);
//		
//		dto = validar.core(usuario, dto.getAccion(), dto);
//		return new ResponseEntity<DtoHrEspecialidad2>(dto, HttpStatus.OK);
//	}
	
	

}
