package net.royal.spring.controlpatrimonial.rest;

import java.math.BigDecimal;
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
import net.royal.spring.controlpatrimonial.dominio.dto.DtoComunFaUbicaciones;
import net.royal.spring.controlpatrimonial.dominio.filtro.FiltroComunFaActivo;
import net.royal.spring.controlpatrimonial.dominio.filtro.FiltroComunFaUbicacionesC001;
import net.royal.spring.controlpatrimonial.dominio.lista.DtlComunFaActivo;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.tesoreria.dominio.dto.DtoComunApConceptogasto;

@RestController
@RequestMapping("/spring/controlpatrimonial/faubicacionescomun")
@CrossOrigin(origins = "*")
public class FaUbicacionesComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(FaUbicacionesComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public FaUbicacionesComunRest() {
		super("faubicaciones");
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunFaUbicaciones. Retorno: DtoComunFaUbicaciones",					
			nickname="FA_UBICACIONES_COBT", value = "Obtener dto", tags = {"FA_UBICACIONES", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunFaUbicaciones> obtenerDto(@RequestBody DtoComunFaUbicaciones pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_ubicacion", String.class, pk.getUbicacion()));
		List datos = this.listarPorQuery(DtoComunFaUbicaciones.class, "faubicaciones.obtenerDto",parametros);
		DtoComunFaUbicaciones dto;
		if (datos.size()==1) {
			dto = (DtoComunFaUbicaciones)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunFaUbicaciones();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunFaUbicaciones>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto filtros. Entrada: DtoComunFaUbicaciones: ubicacion, descripcionlocal, estado. Retorno: List DtoComunFaUbicaciones",					
			nickname="FA_UBICACIONES_COBT", value = "Obtener dto", tags = {"FA_UBICACIONES", "OBTENER"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunFaUbicaciones>> listardtofiltros(@RequestBody DtoComunFaUbicaciones filtro) { 
		logger.debug("listardtofiltros");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getUbicacion()))
            filtro.setUbicacion(null);
        if (UString.estaVacio(filtro.getDescripcionlocal()))
            filtro.setDescripcionlocal(null);
        else
        	filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        parametros.add(new DominioParametroPersistencia("p_ubicacion", String.class, filtro.getUbicacion()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunFaUbicaciones.class, "faubicaciones.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunFaUbicaciones>>(datos, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listac001", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunFaUbicaciones>> listac001(@RequestBody FiltroComunFaUbicacionesC001 filtro) { 
		logger.debug("listac001");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        parametros.add(new DominioParametroPersistencia("p_numerodigitos", BigDecimal.class, filtro.getNumerodigitos()));
        parametros.add(new DominioParametroPersistencia("p_hijosnumerodigitos", BigDecimal.class, filtro.getNumerodigitosHijos()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunFaUbicaciones.class, "faubicaciones.listac001", parametros);
		return new ResponseEntity<List<DtoComunFaUbicaciones>>(datos, HttpStatus.OK); 
	}
	
	@Transactional
	@PutMapping(value = "/listac001hijos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunFaUbicaciones>> listac001hijos(@RequestBody FiltroComunFaUbicacionesC001 filtro) { 
		logger.debug("listac001hijos");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
        if (UString.estaVacio(filtro.getEstado()))
            filtro.setEstado(null);
        parametros.add(new DominioParametroPersistencia("p_numerodigitos", BigDecimal.class, filtro.getNumerodigitos()));
        parametros.add(new DominioParametroPersistencia("p_hijosnumerodigitos", BigDecimal.class, filtro.getNumerodigitosHijos()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        
        parametros.add(new DominioParametroPersistencia("p_ubicacion", String.class, filtro.getUbicacion()));
        parametros.add(new DominioParametroPersistencia("p_anteriornumerodigitos", BigDecimal.class, filtro.getNumerodigitosAnterior()));
        List datos = this.listarPorQuery(DtoComunFaUbicaciones.class, "faubicaciones.listac001hijos", parametros);
		return new ResponseEntity<List<DtoComunFaUbicaciones>>(datos, HttpStatus.OK); 
	}
}