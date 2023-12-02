package net.royal.spring.legal.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.controlpatrimonial.dominio.filtro.FiltroComunFaActivo;
import net.royal.spring.controlpatrimonial.dominio.lista.DtlComunFaActivo;
import net.royal.spring.controlpatrimonial.rest.FaActivoComunRest;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.legal.dominio.dto.DtoComunJuicio;
import net.royal.spring.legal.dominio.filtro.FiltroComunJuicio;

@RestController
@RequestMapping("/spring/legal/juiciocomun")
@CrossOrigin(origins = "*")
public class JucioComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(JucioComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public JucioComunRest() {
		super("juciocomun");
	}
		
	
	@ApiOperation(notes = "Listado paginados de juicio"
			+ "<br><b>Entrada : </b>FiltroComunJuicio<br><b>Salida : </b>Lista de DtoComunJuicio", 
			value = "LE-JUICIO-CLSPAG", tags = {"JUICIOS", "ACTIVOS"})
	@Transactional
	@PutMapping(value = "/listaractivospaginacion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listaractivospaginacion(@RequestBody FiltroComunJuicio filtro)
    {
		logger.debug("listar Proyectos filtro paginado : Activos");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(usu.getCompaniaCodigo());		
			
		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		
		Integer contador = this.contar("juciocomun.paginadoContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "juciocomun.paginadoListar", DtoComunJuicio.class);
        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());
        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
	
	@ApiOperation(notes = "Obtener juicio. Entrada: DtoTabla. Retorno: DtoTabla",					
			nickname="JUCIO-OBT", value = "Obtener juicio.", tags = {"JUICIOS", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenerJucio",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenerJucio(@RequestBody DtoTabla dto)
    {
		logger.debug("obtenerJucio");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, dto.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_juicio", String.class, dto.getNombre()));
        List lista = this.listarPorQuery(DtoTabla.class, "juciocomun.obtenerJucio", parametros);
        
        
        if(lista.size() > 0) {
        	return new ResponseEntity<DtoTabla>((DtoTabla) lista.get(0), HttpStatus.OK);	
        }
        
		return new ResponseEntity<DtoTabla>(new DtoTabla(), HttpStatus.OK);
    }
	
	

}
