package net.royal.spring.core.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/mabancocomun")
@CrossOrigin(origins = "*")
public class MaBancoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaBancoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaBancoComunRest() {
		super("mabanco");
	}

	
	@ApiOperation(notes = "Listado de todos los Bancos Activos de la tabla Ma_Banco "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoTabla(id, nombre)", 
			value = "99-BACNO-CLIACT", tags = {"CORE", "BANCO"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listaractivos() {
		List datos = listarPorQuery(DtoTabla.class, "mabanco.listaractivos");
		return new ResponseEntity<List<DtoTabla>>(datos,HttpStatus.OK);
	}
	
}
