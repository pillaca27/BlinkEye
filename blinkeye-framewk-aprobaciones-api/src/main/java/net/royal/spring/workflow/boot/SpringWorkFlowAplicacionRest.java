package net.royal.spring.workflow.boot;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.workflow.dao.impl.WfReemplazoDaoImpl;

@RestController
@RequestMapping("/spring/publico/workflow")
@CrossOrigin(origins = "*")
public class SpringWorkFlowAplicacionRest {
	
	@Autowired
	private WfReemplazoDaoImpl whItemmastDao;
	
	@GetMapping(value = "/estavivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		Date now = new Date();
		return new ResponseEntity<String>("SI - " + now.toString(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/estavivobd", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivobd() {
		String now="";
		try {
			List lst = whItemmastDao.listarPorQuery(DtoTabla.class, "wfreemplazo.estavivobd");
			if (lst.size()==1) {
				DtoTabla d=(DtoTabla)lst.get(0);
				now=d.getNombre();
			}				
		} catch (Exception e) {
			now = e.getMessage();
		}
		return new ResponseEntity<String>("SI - " + now, HttpStatus.OK);
	}
}
