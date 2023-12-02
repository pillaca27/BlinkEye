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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCondicion;
import net.royal.spring.logistica.dominio.dto.DtoComunWhLicitacionmotivo;


@RestController
@RequestMapping("/spring/logistica/whlicitacionmotivocomun")
@CrossOrigin(origins = "*")
public class WhLicitacionmotivoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhLicitacionmotivoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhLicitacionmotivoComunRest() {
		super("whlicitacionmotivo");
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunWhLicitacionmotivo: tipomotivo, motivo. DtoComunWhLicitacionmotivo: DtoTabla",					
			nickname="WH_LICITACIONMOVITO-COBTDTO", value = "Obtener dto", tags = {"WH_LICITACIONMOVITO", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhLicitacionmotivo> obtenerDto(@RequestBody DtoComunWhLicitacionmotivo pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipomotivo", String.class, pk.getTipomotivo()));
		parametros.add(new DominioParametroPersistencia("p_motivo", String.class, pk.getMotivo()));
		List datos = this.listarPorQuery(DtoComunWhLicitacionmotivo.class, "whlicitacionmotivo.obtenerDto",parametros);
		DtoComunWhLicitacionmotivo dto;
		if (datos.size()==1) {
			dto = (DtoComunWhLicitacionmotivo)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhLicitacionmotivo();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhLicitacionmotivo>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhLicitacionmotivo>> listarDtoFiltros(@RequestBody DtoComunWhLicitacionmotivo filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipomotivo()))
			filtro.setTipomotivo(null);
		if (UString.esNuloVacio(filtro.getMotivo()))
			filtro.setMotivo(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		else
			filtro.setDescripcion(filtro.getDescripcion().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipomotivo", String.class, filtro.getTipomotivo()));
		parametros.add(new DominioParametroPersistencia("p_motivo", String.class, filtro.getMotivo()));
        parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhLicitacionmotivo.class, "whlicitacionmotivo.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhLicitacionmotivo>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/listar/{tipomotivo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarPorTipo(@PathVariable String tipomotivo) {
		logger.debug("WhPaacRest.listaractivos");
		// TODO WhPaacRest.listaractivos : modificar query
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipomotivo", String.class, tipomotivo));
		List datos = this.listarPorQuery(DtoTabla.class, "whlicitacionmotivo.listar",parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
}
