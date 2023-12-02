package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhImportacionSelect;
import net.royal.spring.logistica.dominio.dto.DtoComunWhImportacionSelectBl;
import net.royal.spring.logistica.dominio.dto.DtoComunWhImportacionSelectBlDetalle;
import net.royal.spring.logistica.dominio.dto.DtoComunWhImportacionSelectDetalle;

@RestController
@RequestMapping("/spring/logistica/whimportacioncomun")
@CrossOrigin(origins = "*")
public class WhImportacionComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhImportacionComunRest.class);
		
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
		
	public WhImportacionComunRest() {
		super("whimportacioncomun");
	}
	
	@Transactional
	@GetMapping(value = "/DwWhImportacionSelectBl", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhImportacionSelectBl>> DwWhImportacionSelectBl(@RequestParam String companiasocio) throws Exception {
		
		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("companiasocio", String.class, companiasocio));

		List lista = this.listarPorQuery(DtoComunWhImportacionSelectBl.class, "whimportacioncomun.DwWhImportacionSelectBl", filtros);		
		
		return new ResponseEntity<List<DtoComunWhImportacionSelectBl>>(lista, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/DwWhImportacionSelectBlDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhImportacionSelectBlDetalle>> DwWhImportacionSelectBlDetalle(@RequestParam String companiasocio, @RequestParam String blnumero) throws Exception {
		
		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("companiasocio", String.class, companiasocio));
		filtros.add(new DominioParametroPersistencia("blnumero", String.class, blnumero));		
		List lista = this.listarPorQuery(DtoComunWhImportacionSelectBlDetalle.class, "whimportacioncomun.DwWhImportacionSelectBlDetalle", filtros);		
		
		return new ResponseEntity<List<DtoComunWhImportacionSelectBlDetalle>>(lista, HttpStatus.OK);
	}
	
	
	@Transactional
	@GetMapping(value = "/DwWhImportacionSelect", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhImportacionSelect>> DwWhImportacionSelect(@RequestParam String companiasocio) throws Exception {
		
		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("companiasocio", String.class, companiasocio));
		List lista = this.listarPorQuery(DtoComunWhImportacionSelect.class, "whimportacioncomun.DwWhImportacionSelect", filtros);		
		
		return new ResponseEntity<List<DtoComunWhImportacionSelect>>(lista, HttpStatus.OK);
	}
	
	
	@Transactional
	@GetMapping(value = "/DwWhImportacionSelectDetalle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhImportacionSelectDetalle>> DwWhImportacionSelectDetalle(@RequestParam String companiasocio, @RequestParam String numeroOrden) throws Exception {
		
		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("companiasocio", String.class, companiasocio));
		filtros.add(new DominioParametroPersistencia("numeroorden", String.class, numeroOrden));
		List lista = this.listarPorQuery(DtoComunWhImportacionSelectDetalle.class, "whimportacioncomun.DwWhImportacionSelectDetalle", filtros);		
		
		return new ResponseEntity<List<DtoComunWhImportacionSelectDetalle>>(lista, HttpStatus.OK);
	}
	
}
