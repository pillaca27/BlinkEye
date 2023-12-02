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
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;


@RestController
@RequestMapping("/spring/logistica/whclasesubfamiliacomun")
@CrossOrigin(origins = "*")
public class WhClasesubfamiliaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhClasesubfamiliaComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhClasesubfamiliaComunRest() {
		super("whclasesubfamilia");
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunWhClasesubfamilia. Retorno: DtoComunWhClasesubfamilia",					
			nickname="WH_CLASESUBFAMILIA-OBT", value = "Obtener dto. Entrada", tags = {"WH_CLASESUBFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhClasesubfamilia> obtenerDto(@RequestBody DtoComunWhClasesubfamilia pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunWhClasesubfamilia dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunWhClasesubfamilia>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto descripcion local. Entrada: DtoComunWhClasesubfamilia. Retorno: String",					
			nickname="WH_CLASESUBFAMILIA-OBTDESC", value = "Obtener dto descripcion local. Entrada", tags = {"WH_CLASESUBFAMILIA", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdescripcionlocal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerDescripcionlocal(@RequestBody DtoComunWhClasesubfamilia pk) throws Exception {
		logger.debug("obtenerdescripcionlocal");
		DtoComunWhClasesubfamilia dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getDescripcionlocal(),HttpStatus.OK);
	}
	
	public DtoComunWhClasesubfamilia obtenerDtoCore(DtoComunWhClasesubfamilia pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, pk.getSubfamilia()));
		List datos = this.listarPorQuery(DtoComunWhClasesubfamilia.class, "whclasesubfamilia.obtenerDto",parametros);
		DtoComunWhClasesubfamilia dto;
		if (datos.size()==1) {
			dto = (DtoComunWhClasesubfamilia)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhClasesubfamilia();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasesubfamilia>> listarDtoFiltros(@RequestBody DtoComunWhClasesubfamilia filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);		
		if (UString.esNuloVacio(filtro.getFamilia()))
			filtro.setFamilia(null);
		if (UString.esNuloVacio(filtro.getSubfamilia()))
			filtro.setSubfamilia(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
        parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
        parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhClasesubfamilia.class, "whclasesubfamilia.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasesubfamilia>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporfamilia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhClasesubfamilia>> listarDtoPorFamilia(@RequestBody DtoComunWhClasesubfamilia filtro) {
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
        List datos = this.listarPorQuery(DtoComunWhClasesubfamilia.class, "whclasesubfamilia.listarDtoPorFamilia", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhClasesubfamilia>>(datos, HttpStatus.OK);
	}
}
