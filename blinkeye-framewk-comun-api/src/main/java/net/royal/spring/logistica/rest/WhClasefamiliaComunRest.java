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
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;

@RestController
@RequestMapping("/spring/logistica/whclasefamiliacomun")
@CrossOrigin(origins = "*")
public class WhClasefamiliaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhClasefamiliaComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhClasefamiliaComunRest() {
		super("whclasefamilia");
	}
	
	public DtoComunWhClasefamilia obtenerDtoCore(DtoComunWhClasefamilia pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		List datos = this.listarPorQuery(DtoComunWhClasefamilia.class, "whclasefamilia.obtenerDto",parametros);
		DtoComunWhClasefamilia dto;
		if (datos.size()==1) {
			dto = (DtoComunWhClasefamilia)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhClasefamilia();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="WH_CLASEFAMILIA-OBT", value = "Obtener tabla.", tags = {"WH_CLASEFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClasefamilia> obtenerDto(@RequestBody DtoComunWhClasefamilia pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunWhClasefamilia dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhClasefamilia>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto descripcion. Entrada: DtoComunWhClasefamilia. Retorno: String",					
			nickname="WH_CLASEFAMILIA-OBT", value = "Obtener tabla.", tags = {"WH_CLASEFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdescripcionlocal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcionlocal(@RequestBody DtoComunWhClasefamilia pk) throws Exception {
		logger.debug("obtenerdescripcionlocal");
		DtoComunWhClasefamilia dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcionlocal(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasefamilia>> listarDtoFiltros(@RequestBody DtoComunWhClasefamilia filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);		
		if (UString.esNuloVacio(filtro.getFamilia()))
			filtro.setFamilia(null);	
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
        parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhClasefamilia.class, "whclasefamilia.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasefamilia>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporlinea", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasefamilia>> listarDtoPorLinea(@RequestBody DtoComunWhClasefamilia filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
        parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhClasefamilia.class, "whclasefamilia.listardtoporlinea", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasefamilia>>(datos, HttpStatus.OK);
	}
}
