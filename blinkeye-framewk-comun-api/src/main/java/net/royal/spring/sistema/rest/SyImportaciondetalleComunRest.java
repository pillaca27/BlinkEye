package net.royal.spring.sistema.rest;

import java.math.BigDecimal;
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
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sistema.dominio.dto.DtoComunSyImportacion;
import net.royal.spring.sistema.dominio.dto.DtoComunSyImportaciondetalle;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;

@RestController
@RequestMapping("/spring/sistema/syimportaciondetallecomun")
@CrossOrigin(origins = "*")
public class SyImportaciondetalleComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyImportaciondetalleComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyImportaciondetalleComunRest() {
		super("syimportaciondetalle");
	}
		
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyImportaciondetalle> obtenerDto(@RequestBody DtoComunSyImportaciondetalle pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_tablacodigo", String.class, pk.getTablacodigo()));
		parametros.add(new DominioParametroPersistencia("p_linea", BigDecimal.class, pk.getLinea()));
		List datos = this.listarPorQuery(DtoComunSyImportaciondetalle.class, "syimportaciondetalle.obtenerDto",parametros);
		DtoComunSyImportaciondetalle dto;
		if (datos.size()==1) {
			dto = (DtoComunSyImportaciondetalle)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunSyImportaciondetalle();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunSyImportaciondetalle>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyImportaciondetalle>> listarDtoFiltros(@RequestBody DtoComunSyImportaciondetalle filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getTablacodigo()))
			filtro.setTablacodigo(null);
		if (UBigDecimal.esCeroOrNulo(filtro.getLinea()))
			filtro.setLinea(null);
		if (UString.esNuloVacio(filtro.getDescripcioncampo()))
			filtro.setDescripcioncampo(null);
		else
			filtro.setDescripcioncampo(filtro.getDescripcioncampo().toUpperCase());
				
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
        parametros.add(new DominioParametroPersistencia("p_tablacodigo", String.class, filtro.getTablacodigo()));
        parametros.add(new DominioParametroPersistencia("p_linea", BigDecimal.class, filtro.getLinea()));
        parametros.add(new DominioParametroPersistencia("p_descripcioncampo", String.class, filtro.getDescripcioncampo()));
        List datos = this.listarPorQuery(DtoComunSyImportaciondetalle.class, "syimportaciondetalle.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunSyImportaciondetalle>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoportabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyImportaciondetalle>> listardtoportabla(@RequestBody DtoComunSyImportaciondetalle filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getTablacodigo()))
			filtro.setTablacodigo(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
        parametros.add(new DominioParametroPersistencia("p_tablacodigo", String.class, filtro.getTablacodigo()));
        List datos = this.listarPorQuery(DtoComunSyImportaciondetalle.class, "syimportaciondetalle.listardtoportabla", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunSyImportaciondetalle>>(datos, HttpStatus.OK);
	}
	
}
