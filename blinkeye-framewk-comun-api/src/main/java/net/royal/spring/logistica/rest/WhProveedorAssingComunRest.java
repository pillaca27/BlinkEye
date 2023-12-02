package net.royal.spring.logistica.rest;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunProveedorassing;
import net.royal.spring.logistica.dominio.dto.DtoComunWhContrato;
import net.royal.spring.logistica.dominio.dto.DtoComunWhMarcas;
import net.royal.spring.logistica.dominio.dto.DtoComunWhTipodocumento;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhContrato;
import net.royal.spring.logistica.dominio.filtro.FiltroComunProveedorassing;

@RestController
@RequestMapping("/spring/logistica/proveedorassingcomun")
@CrossOrigin(origins = "*")
public class WhProveedorAssingComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhProveedorAssingComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhProveedorAssingComunRest() {
		super("whproveedorassing");
	}
	
	
	@Transactional
	@PutMapping(value = "/listarpaginadoxitem",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginadoxitem(@RequestBody FiltroComunProveedorassing filtro)
    {
        
		if(!UString.esNuloVacio(filtro.getItem())) {
			filtro.setItem(filtro.getItem().trim());
		}
		
        Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();						
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));
		
		contador = this.contar("whproveedorassing.contarxitem", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whproveedorassing.listarpaginadoxitem",
				DtoComunProveedorassing.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	@Transactional
	@PutMapping(value = "/listarpaginadoxlinea",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginadoxlinea(@RequestBody FiltroComunProveedorassing filtro)
    {
        
		if(!UString.esNuloVacio(filtro.getItem())) {
			filtro.setItem(filtro.getItem().trim());
		}
		
        Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();						
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));		
		parametros.add(new DominioParametroPersistencia("p_proveedorcompradorflag", String.class, filtro.getProveedorcompradorflag()));
		
		contador = this.contar("whproveedorassing.contarxlinea", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whproveedorassing.listarpaginadoxlinea",
				DtoComunProveedorassing.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	
	@Transactional
	@PutMapping(value = "/listarpaginadoxcommodity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginadoxcommodity(@RequestBody FiltroComunProveedorassing filtro)
    {
        
        Integer contador = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();						
		parametros.add(new DominioParametroPersistencia("p_commodity", String.class, filtro.getCommodity()));
		
		contador = this.contar("whproveedorassing.contarxcommodity", parametros);

		List lstDatos = this.listarConPaginacion(filtro.getPaginacion(), parametros, "whproveedorassing.listarpaginadoxcommodity",
				DtoComunProveedorassing.class);

		filtro.getPaginacion().setPaginacionListaResultado(lstDatos);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		        
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	
}
