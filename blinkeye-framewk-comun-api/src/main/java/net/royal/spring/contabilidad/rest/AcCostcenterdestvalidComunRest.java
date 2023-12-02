package net.royal.spring.contabilidad.rest;

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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accostcenterdestvalidcomun")
@CrossOrigin(origins = "*")
public class AcCostcenterdestvalidComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCostcenterdestvalidComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCostcenterdestvalidComunRest() {
		super("accostcenterdestvalid");
	}
	
	@ApiOperation(notes = "Obtener dto Entrada: DtoComunAcCostcenterdestvalid. Retorno: DtoComunAcCostcenterdestvalid",					
			nickname="AC_COSTCENTERDESTVALID_COBTDTO", value = "Listar por filtros", tags = {"AC_COSTCENTERDESTVALID", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCostcenterdestvalid> obtenerDto(@RequestBody DtoComunAcCostcenterdestvalid pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcCostcenterdestvalid dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcCostcenterdestvalid>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener estado. Entrada: DtoComunAcCostcenterdestvalid. Retorno: String",					
			nickname="AC_COSTCENTERDESTVALID_COBTSTR", value = "Obtener estado", tags = {"AC_COSTCENTERDESTVALID", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerStatus(@RequestBody DtoComunAcCostcenterdestvalid pk) throws Exception {
		logger.debug("obtenerstatus");
		DtoComunAcCostcenterdestvalid dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getStatus(),HttpStatus.OK);
	}
	
	public DtoComunAcCostcenterdestvalid obtenerDtoCore(DtoComunAcCostcenterdestvalid pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, pk.getCostcenterdestination()));
		List datos = this.listarPorQuery(DtoComunAcCostcenterdestvalid.class, "accostcenterdestvalid.obtenerDto",parametros);
		DtoComunAcCostcenterdestvalid dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCostcenterdestvalid)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCostcenterdestvalid();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Listar dto filtros: costcenter, costcenterdestination, status. Entrada: DtoComunAcCostcenterdestvalid. Retorno: List DtoComunAcCostcenterdestvalid",					
			nickname="AC_COSTCENTERDESTVALID_CLISTFIL", value = "Obtener estado", tags = {"AC_COSTCENTERDESTVALID", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCostcenterdestvalid>> listarDtoFiltros(@RequestBody DtoComunAcCostcenterdestvalid filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter(null);
		if (UString.esNuloVacio(filtro.getCostcenterdestination()))
			filtro.setCostcenterdestination(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_costcenterdestination", String.class, filtro.getCostcenterdestination()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCostcenterdestvalid.class, "accostcenterdestvalid.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAcCostcenterdestvalid>>(datos, HttpStatus.OK);
	}
	
}
