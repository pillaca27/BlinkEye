package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.dto.DtoComunMaMaestroMiscelaneodetalle;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.servicio.impl.MaMiscelaneosdetalleServicioImpl;
import net.royal.spring.core.servicio.validar.MaMiscelaneosdetalleServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/mamaestromiscelaneosdetallecomun")
@CrossOrigin(origins = "*")
public class MaMaestroMiscelaneodetalleComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaMaestroMiscelaneodetalleComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaMaestroMiscelaneodetalleComunRest() {
		super("mamaestromiscelaneosdetallecomun");
	}

	@ApiOperation(notes = "-Descripcion: Listado de MaMestrosMiscelaneo activos | Parametros de entrada:  aplicacion y tabla | Parametros de salida: codigo, nombre", value = "99-MAESTRO-MISDET-CLHACT", tags = {
			"CORE", "MAESTRO MISCELANEOS DETALLE" })
	@Transactional
	@PutMapping(value = "/listartablaporheader", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listartablaporheader(
			@ApiParam(value = "Codigos de la tabla", required = true) @RequestBody DtoComunMaMaestroMiscelaneodetalle dto) {

		if (UString.esNuloVacio(dto.getAplicacioncodigo()))
			dto.setAplicacioncodigo("99");
		if (UString.esNuloVacio(dto.getEstado()))
			dto.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, dto.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, dto.getMaestrocodigo()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, dto.getEstado()));
		List datos = this.listarPorQuery(DtoTabla.class, "mamaestromiscelaneosdetallecomun.listarActivos", parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@ApiOperation(notes = "-Descripcion: Listado de MaMestrosMiscelaneo activos | Parametros de entrada:  aplicacion y tabla | Parametros de salida: codigo, nombre", value = "99-MAESTRO-MISDET-CLHACT", tags = {
			"CORE", "MAESTRO MISCELANEOS DETALLE" })
	@Transactional
	@PutMapping(value = "/listartablaporheaderordennombre", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listartablaporheaderOrdenadoNombre(
			@ApiParam(value = "Codigos de la tabla", required = true) @RequestBody DtoComunMaMaestroMiscelaneodetalle dto) {

		if (UString.esNuloVacio(dto.getAplicacioncodigo()))
			dto.setAplicacioncodigo("99");
		if (UString.esNuloVacio(dto.getEstado()))
			dto.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, dto.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, dto.getMaestrocodigo()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, dto.getEstado()));
		List datos = this.listarPorQuery(DtoTabla.class, "mamaestromiscelaneosdetallecomun.listarActivosOrdenadoNombre",
				parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/listarActivosOrdenadoTipo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivosOrdenadoTipo(
			@ApiParam(value = "Codigos de la tabla", required = true) @RequestBody DtoComunMaMaestroMiscelaneodetalle dto) {

		if (UString.esNuloVacio(dto.getAplicacioncodigo()))
			dto.setAplicacioncodigo("99");
		if (UString.esNuloVacio(dto.getEstado()))
			dto.setEstado(null);
		if (UString.esNuloVacio(dto.getCodtipo()))
			dto.setCodtipo(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, dto.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, dto.getMaestrocodigo()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, dto.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_codtipo", String.class, dto.getCodtipo()));
		List datos = this.listarPorQuery(DtoTabla.class, "mamaestromiscelaneosdetallecomun.listarActivosOrdenadoTipo",
				parametros);
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional
	@GetMapping(value = "/listarCombocategorias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listartipolicitacion() throws Exception {
		logger.debug("MaMaestromiscelaneoheaderRest.listarCombocategorias");
		List datos = this.listarPorQuery(DtoTabla.class, "mamaestromiscelaneosdetallecomun.listarCombocategorias");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

}
