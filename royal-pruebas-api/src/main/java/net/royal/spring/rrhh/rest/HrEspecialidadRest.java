package net.royal.spring.rrhh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad;
import net.royal.spring.rrhh.servicio.impl.HrEspecialidadServicioImpl;

@RestController
@RequestMapping("/spring/rrhh/hrespecialidad")
@CrossOrigin(origins = "*")
public class HrEspecialidadRest extends GenericoRest{
	
	@Autowired
	private HrEspecialidadServicioImpl hrEspecialidadServicioImpl;
	
	@Transactional
	@GetMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> prueba() throws Exception{
		
		String p = "Hola";
		
		return new ResponseEntity<String>(p, HttpStatus.OK);
	
	}
	
	@Transactional
	@PostMapping(value = "/listarEspecialidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoHrEspecialidad>> listarEspecialidades(@RequestBody FiltroHrEspecialidad filtros) throws Exception{
		
		List<DtoHrEspecialidad> lista = hrEspecialidadServicioImpl.listarEspecialidades(filtros);
		
		return new ResponseEntity<List<DtoHrEspecialidad>>(lista, HttpStatus.OK);
	
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerDto", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrEspecialidad obtenerDto(@RequestBody DtoHrEspecialidad dto) {
		return hrEspecialidadServicioImpl.obtenerDto(dto);
	}
	
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrEspecialidad registrar(@RequestBody DtoHrEspecialidad dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		
		return hrEspecialidadServicioImpl.registrar(dto, usuario);
	}
	
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrEspecialidad actualizar(@RequestBody DtoHrEspecialidad dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrEspecialidadServicioImpl.actualizar(dto, usuario);
	}
	
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoHrEspecialidad eliminar(@RequestBody DtoHrEspecialidad dto) {
		SeguridadUsuarioActual usuario= new SeguridadUsuarioActual();
		usuario.setUsuario("MISESF");
		usuario.setPersonaId(-1);
		return hrEspecialidadServicioImpl.eliminar(dto, usuario);
	}

}
