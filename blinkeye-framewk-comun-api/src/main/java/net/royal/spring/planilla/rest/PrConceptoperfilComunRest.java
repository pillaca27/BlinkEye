package net.royal.spring.planilla.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
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

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.core.dominio.filtro.FiltroComunTipopago;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.planilla.dominio.filtro.FiltroComunPrConceptoperfil;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunHrAfp;

@RestController
@RequestMapping("/spring/planilla/prconceptoperfil")
@CrossOrigin(origins = "*")
public class PrConceptoperfilComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(PrConceptoperfilComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public PrConceptoperfilComunRest() {
		super("prconceptoperfil");
	}
	
	/**
	 * MALCAN VALIDADO
	 * HR-AFP COMUN SELECTOR
	 */
	@Transactional
	@PostMapping(value = "/listarperfilporfiltro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DominioPaginacion> listarPerfilPorFiltro(@RequestBody FiltroComunPrConceptoperfil filtro)
    {
        logger.debug("PrConceptoperfilRest.listarperfilporfiltro");        
        Integer cantidadEncontrados = 0;
        List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        
        if(UInteger.esCeroOrNulo(filtro.getPerfil()))
        	filtro.setPerfil(null);
        if(UString.estaVacio(filtro.getDescripcion()))
        	filtro.setDescripcion(null);

        parametros.add(new DominioParametroPersistencia("p_perfil", Integer.class, filtro.getPerfil()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, "A"));

        cantidadEncontrados = this.contar("prconceptoperfil.contarperfilporfiltro", parametros);

        List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros, "prconceptoperfil.listarperfilporfiltro", 
        		DtoTabla.class);

        filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		
        return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}
