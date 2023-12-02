package net.royal.spring.controlpatrimonial.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.controlpatrimonial.dominio.dto.DtoComunFaActivo;
import net.royal.spring.controlpatrimonial.dominio.filtro.FiltroComunFaActivo;
import net.royal.spring.controlpatrimonial.dominio.lista.DtlComunFaActivo;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/controlpatrimonial/faactivocomun")
@CrossOrigin(origins = "*")
public class FaActivoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(FaActivoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FaActivoComunRest() {
		super("faactivo");
	}
		
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "Listado paginados de activos"
			+ "<br><b>Entrada : </b>DtoFaActivoFiltroPaginado <br><b>Salida : </b>Lista de DtoFaActivo", 
			value = "FA-ACTIVO-CLSPAG", tags = {"CONTROL PATRIMONIAL", "ACTIVOS"})
	@Transactional
	@PutMapping(value = "/listaractivospaginacion",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listaractivospaginacion(@RequestBody FiltroComunFaActivo filtro)
    {
		logger.debug("listar Proyectos filtro paginado : Activos");
		SeguridadUsuarioActual usu = this.getUsuarioActual();
		
		if (UString.esNuloVacio(filtro.getCompaniasocio()))
			filtro.setCompaniasocio(usu.getCompaniaCodigo());
		if (UString.esNuloVacio(filtro.getActivo()))
			filtro.setActivo(null);
		if (UString.esNuloVacio(filtro.getBusquedalocal())){
			filtro.setBusquedalocal(null);
		}else{
			filtro.setBusquedalocal(filtro.getBusquedalocal().toUpperCase());
		}
			
		if (UString.esNuloVacio(filtro.getCodigointerno()))
			filtro.setCodigointerno(null);
		if (UString.esNuloVacio(filtro.getNumeroplaca()))
			filtro.setNumeroplaca(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);	
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getActivo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusquedalocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		
		Integer contador = this.contar("faactivo.paginadoContar", parametros);
        List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "faactivo.paginadoListar", DtlComunFaActivo.class);
        
        filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        logger.debug(contador);
        logger.debug(lista.size());
        
		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
    }
	
}