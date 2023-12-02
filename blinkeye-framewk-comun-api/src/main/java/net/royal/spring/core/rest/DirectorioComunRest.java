package net.royal.spring.core.rest;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.servicio.impl.PaisServicioImpl;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.util.dominio.dto.DtoComunArbolFile;
import net.royal.spring.util.dominio.filtro.FiltroComunDirectorio;

@RestController
@RequestMapping("/spring/core/directorio")
@CrossOrigin(origins = "*")
public class DirectorioComunRest extends GenericoHibernateRest {

	@Autowired
	private PaisServicioImpl servicio;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DirectorioComunRest() {
		super("");
	}

	@PostMapping(value = "/verDirectorio", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunArbolFile verDirectorio(@RequestBody FiltroComunDirectorio filtro) throws Exception {
		return servicio.verDirectorio(filtro);
	}
}
