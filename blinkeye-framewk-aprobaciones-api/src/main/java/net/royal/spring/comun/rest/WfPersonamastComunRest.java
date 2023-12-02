package net.royal.spring.comun.rest;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.comun.dominio.dto.FiltroComunPersonamastclis001Wf;
import net.royal.spring.framework.modelo.EmpleadomastTransaccion;
import net.royal.spring.framework.modelo.PersonamastTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comun/wfpersonamastcomun")
@CrossOrigin(origins = "*")
public class WfPersonamastComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WfPersonamastComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfPersonamastComunRest() {
		super("wfpersonamast");
	}

	/**
	 * ARMAS MIGRADO
	 * 
	 * @param pTran
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-COBTAPI", tags = {
					"CORE", "PERSONA" })
	@Transactional
	@PutMapping(value = "/obtenerpersonapordtoapi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonamastTransaccion> obtenerpersonapordtoapi(@RequestBody PersonamastTransaccion pTran) {
		PersonamastTransaccion bean = obtenerTransaccionPorPersonaCore(pTran);
		return new ResponseEntity<PersonamastTransaccion>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/obtenertransaccionporpersona", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonamastTransaccion> obtenerTransaccionPorPersona(
			@RequestBody PersonamastTransaccion pTran) {
		PersonamastTransaccion bean = obtenerTransaccionPorPersonaCore(pTran);
		return new ResponseEntity<PersonamastTransaccion>(bean, HttpStatus.OK);
	}

	public PersonamastTransaccion obtenerTransaccionPorPersonaCore(PersonamastTransaccion pTran) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id_persona", Integer.class, pTran.getPersona()));
		try {
			List datos = this.listarPorQuery(PersonamastTransaccion.class, "wfpersonamast.obtenerpersonapordtoapi",
					parametros);
			if (datos == null) {
				pTran.setTransaccionEstado(DominioTransaccion.ERROR);
				pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
			} else if (datos.size() == 0) {
				pTran = new PersonamastTransaccion();
			} else {
				pTran = (PersonamastTransaccion) datos.get(0);
				pTran.setTransaccionEstado(DominioTransaccion.OK);
			}
		} catch (Exception e) {
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			e.printStackTrace();
		}
		return pTran;
	}

	/**
	 * ARMAS MIGRADO TIPO(EMOT) : Empleado / Otro
	 * 
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-CLIS001", tags = {
					"CORE", "PERSONA" })
	@Transactional
	@PutMapping(value = "/clis001", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> clis001(@RequestBody FiltroComunPersonamastclis001Wf filtro) {
		logger.debug("TIPO(EMOT) : Empleado / Otro");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		if (UString.esNuloVacio(filtro.getCentrocosto()))
			filtro.setCentrocosto(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		parametros.add(new DominioParametroPersistencia("p_centrocosto", String.class, filtro.getCentrocosto()));

		Integer contador = this.contar("wfpersonamast.paginadoEmpleadoOtroContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wfpersonamast.paginadoEmpleadoOtroListar", EmpleadomastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	

	@Transactional
	@PutMapping(value = "/listargestor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listargestor(@RequestBody FiltroComunPersonamastclis001Wf filtro) {
		logger.debug("TIPO(PRCLOT) : Proveedor / Cliente / Otro");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer contador = this.contar("wfpersonamast.paginadoGestorContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros, "wfpersonamast.paginadoGestorListar",
				PersonamastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	/**
	 * TIPO(PRCLOT) : Proveedor / Cliente / Otro
	 * 
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Personamast (Proveedores) | Parametros de entrada: PersonamastFiltroPaginado ( persona, busqueda,"
			+ "documento, documentofiscal, documentoidentidad, estado  )  | Parametros de salida: persona, busqueda, escliente, esproveedor, esempleado, esotro, documento, documentofiscal, documentoidentidad, estado, centroCosto, descripcioncentroCosto, unidad, descripcionunidad, departamento, descripciondepartamento, sucursal", value = "99-PERSON-PROVEED", tags = {
					"CORE", "PROVEEDOR" })
	@Transactional
	@PutMapping(value = "/listarclienteproveedorotro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarClienteProveedorOtro(
			@RequestBody FiltroComunPersonamastclis001Wf filtro) {
		logger.debug("TIPO(PRCLOT) : Proveedor / Cliente / Otro");

		if (UBigDecimal.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda(null);

		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal(null);
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", BigDecimal.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getBusqueda()));

		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_docfiscal", String.class, filtro.getDocumentofiscal()));
		parametros
				.add(new DominioParametroPersistencia("p_docidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer contador = this.contar("wfpersonamast.paginadoClienteProveedorOtroContar", parametros);
		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wfpersonamast.paginadoClienteProveedorOtroListar", PersonamastTransaccion.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(contador);
		filtro.getPaginacion().setPaginacionListaResultado(lista);
		logger.debug(contador);
		logger.debug(lista.size());

		return new ResponseEntity<DominioPaginacion>(filtro.getPaginacion(), HttpStatus.OK);
	}

	
}
