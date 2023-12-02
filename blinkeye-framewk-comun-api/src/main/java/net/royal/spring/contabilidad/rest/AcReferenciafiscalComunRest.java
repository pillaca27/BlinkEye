package net.royal.spring.contabilidad.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcReferenciafiscal;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcReferenciafiscalSelector;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunTipodocumentocxp;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.modelo.MiscelaneosHeaderTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/acreferenciafiscalcomun")
@CrossOrigin(origins = "*")
public class AcReferenciafiscalComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcReferenciafiscalComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcReferenciafiscalComunRest() {
		super("acreferenciafiscal");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcReferenciafiscal> obtenerDto(@RequestBody DtoComunAcReferenciafiscal pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcReferenciafiscal dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcReferenciafiscal>(dto,HttpStatus.OK);
	}
	
	public DtoComunAcReferenciafiscal obtenerDtoCore(DtoComunAcReferenciafiscal pk) throws Exception {
		logger.debug("obtenerDtoCore");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_ano", String.class, pk.getAno()));
		parametros.add(new DominioParametroPersistencia("p_tiporeferenciafiscal", String.class, pk.getTiporeferenciafiscal()));
		parametros.add(new DominioParametroPersistencia("p_nivel", String.class, pk.getNivel()));
		parametros.add(new DominioParametroPersistencia("p_referenciafiscal", String.class, pk.getReferenciafiscal()));
		List datos = this.listarPorQuery(DtoComunAcReferenciafiscal.class, "acreferenciafiscal.obtenerDto",parametros);
		DtoComunAcReferenciafiscal dto;
		if (datos.size()==1) {
			dto = (DtoComunAcReferenciafiscal)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcReferenciafiscal();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}

	/***
	 * ARMAS MIGRADO
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Referencia fiscal actual | Parametros de entrada: tipoReferenciaFiscal, nivel | Parametros de salida: codigo, nombre", 
			value = "AC-REFFIS-C0001", tags = {"CONTABILIDAD", "REFERENCIA FISCAL"})
	@Transactional
	@PutMapping(value = "/listarreferenciafiscalactual", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> listarFinanciamiento(
			@ApiParam(value = "Codigos de la tabla", required = true)
			@RequestBody DtoComunAcReferenciafiscal dto) {
		//SeguridadUsuarioActual usu = this.getUsuarioActual();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_referenciafiscal", String.class, dto.getTiporeferenciafiscal()));
		parametros.add(new DominioParametroPersistencia("p_nivel", String.class, dto.getNivel()));
		List datos = this.listarPorQuery(DtoTabla.class, "acreferenciafiscal.listarReferenciaFiscalActual", parametros);
		return datos;
	}

	@Transactional
	@PutMapping(value = "/listardtoporaniotiponivel", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAcReferenciafiscal> listarDtoPorAnioTipoNivel(@RequestBody FiltroComunAcReferenciafiscalSelector filtro) {
		logger.debug("listarDtoPorAnioTipoNivel");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_tiporeferenciafiscal", String.class, filtro.getTiporeferenciafiscal()));
		parametros.add(new DominioParametroPersistencia("p_ano", String.class, filtro.getAno()));
		parametros.add(new DominioParametroPersistencia("p_nivel", String.class, filtro.getNivel()));
		List datos = this.listarPorQuery(DtoComunAcReferenciafiscal.class, "acreferenciafiscal.listarDtoPorAnioTipoNivel", parametros);
		return datos;
	}
	
	@Transactional
	@PutMapping(value = "/listardtoporseguridadaniotiponivel", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAcReferenciafiscal> listarDtoPorSeguridadAnioTipoNivel(@RequestBody FiltroComunAcReferenciafiscalSelector filtro) {
		logger.debug("listarDtoPorSeguridadAnioTipoNivel");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();		
		parametros.add(new DominioParametroPersistencia("p_tiporeferenciafiscal", String.class, filtro.getTiporeferenciafiscal()));
		parametros.add(new DominioParametroPersistencia("p_ano", String.class, filtro.getAno()));
		parametros.add(new DominioParametroPersistencia("p_nivel", String.class, filtro.getNivel()));
		List datos = this.listarPorQuery(DtoComunAcReferenciafiscal.class, "acreferenciafiscal.listarDtoPorSeguridadAnioTipoNivel", parametros);
		return datos;
	}
}
