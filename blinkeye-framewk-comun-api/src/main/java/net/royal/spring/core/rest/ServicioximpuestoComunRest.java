package net.royal.spring.core.rest;

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
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/servicioximpuestocomun")
@CrossOrigin(origins = "*")
public class ServicioximpuestoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ServicioximpuestoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ServicioximpuestoComunRest() {
		super("servicioximpuesto");
	}
		
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunServicioximpuesto: tiposervicio, impuesto. Retorno: DtoComunServicioximpuesto",					
			nickname="SERVICIOXIMPUESTO_COBT", value = "Obtener dto", tags = {"SERVICIOXIMPUESTO", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunServicioximpuesto> obtenerDto(@RequestBody DtoComunServicioximpuesto pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, pk.getImpuesto()));
		List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.obtenerDto",parametros);
		DtoComunServicioximpuesto dto;
		if (datos.size()==1) {
			dto = (DtoComunServicioximpuesto)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunServicioximpuesto();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunServicioximpuesto>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto Serivio por impuesto. Entrada: DtoComunServicioximpuesto: tiposervicio. Retorno: List DtoComunServicioximpuesto",					
			nickname="SERVICIOXIMPUESTO_COBTXIMP", value = "Obtener dto Serivio por impuesto", tags = {"SERVICIOXIMPUESTO", "LISTAR"})
	@Transactional
	@PutMapping(value="/obtenerServicioXImpuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunServicioximpuesto>> obtenerServicioXImpuesto(@RequestBody DtoComunServicioximpuesto pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, pk.getTiposervicio()));
		List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.obtenerServicioXImpuesto",parametros);
		
		return new ResponseEntity<List<DtoComunServicioximpuesto>>(datos,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunServicioximpuesto>> listarDtoFiltros(@RequestBody DtoComunServicioximpuesto filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTiposervicio()))
			filtro.setTiposervicio(null);		
		if (UString.esNuloVacio(filtro.getImpuesto()))
			filtro.setImpuesto(null);		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));
        List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunServicioximpuesto>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoportiposervicio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunServicioximpuesto>> listarDtoPorTipoServicio(@RequestBody DtoComunServicioximpuesto filtro) {
		logger.debug("listardtofiltros");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, filtro.getTiposervicio()));
        List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.listardtoportiposervicio", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunServicioximpuesto>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporimpuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunServicioximpuesto>> listarDtoPorImpuesto(@RequestBody DtoComunServicioximpuesto filtro) {
		logger.debug("listardtofiltros");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_impuesto", String.class, filtro.getImpuesto()));
        List datos = this.listarPorQuery(DtoComunServicioximpuesto.class, "servicioximpuesto.listardtoporimpuesto", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunServicioximpuesto>>(datos, HttpStatus.OK);
	}
	
}
