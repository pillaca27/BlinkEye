package net.royal.spring.rrhh.rest;

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
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrCarta;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrGradoinstruccion;

@RestController
@RequestMapping("/spring/rrhh/hrcartacomun")
@CrossOrigin(origins = "*")
public class HrCartaComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(HrCartaComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private CorrelativosmastDaoImpl dao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public HrCartaComunRest() {
		super("hrcarta");
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla hr_carta y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-CARTA-LISTAR", tags = {"RRHH", "CARTA"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listar() {
		logger.debug("listar Activos Tipo Trabajador");
		List datos = this.listarPorQuery(DtoTabla.class, "hrcarta.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	 
	/**
	 * QQUECHOD VALIDADO
	 * @param filtro
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Obtener Por id hr_carta | Parametros de entrada: FiltroComunHrCursodescripcion | Parametros de salida: DtoTabla(id, nombre) ", 
			value = "HR-CARTA-OBTTAB", tags = {"RRHH", "CARTA"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_carta", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "hrcarta.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "Listado de todos los Horarios Activos de la tabla HR_GradoInstruccion "
			+ "<br><b>Entrada : </b>Sin parametros de entrada <br><b>Salida : </b>Lista de DtoComunHrGradoinstruccion", 
			value = "RH-CARTA-OBTDTO", tags = {"RRHH", "CARTA"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunHrCarta> obtenerdto(@RequestBody DtoComunHrCarta pk) throws Exception {
		logger.debug("hrgradoinstruccion.obtenerDtoPorId");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_carta", String.class, pk.getCarta()));
		List datos = this.listarPorQuery(DtoComunHrCarta.class, "hrcarta.obtenerdto",parametros);
		DtoComunHrCarta dto;
		if (datos.size()==1) {
			dto = (DtoComunHrCarta)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunHrCarta();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunHrCarta>(dto,HttpStatus.OK);
	}
	
	/***
	 * QQUECHOD VALIDADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado Activos Tipo trabajador de la tabla Hr_Tipotrabajador y estado activo | Sin parametros de entrada | Parametros de salida: codigo, nombre", 
			value = "HR-CARTA-LISDTOFIL", tags = {"RRHH", "CARTA"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunHrCarta>> listarDtoFiltros(@RequestBody DtoComunHrCarta filtro) {
		logger.debug("listar Filtros");
		if (UString.esNuloVacio(filtro.getCarta()))
			filtro.setCarta(null);
		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento(null);
		else
			filtro.setDocumento(filtro.getDocumento().toUpperCase());
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_carta", String.class, filtro.getCarta()));
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		List datos = this.listarPorQuery(DtoComunHrCarta.class, "hrcarta.listarfiltros",parametros);
		return new ResponseEntity<List<DtoComunHrCarta>>(datos, HttpStatus.OK);
	}
}
