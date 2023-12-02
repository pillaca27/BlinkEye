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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accostcenteraccountcomun")
@CrossOrigin(origins = "*")
public class AcCostcenteraccountComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCostcenteraccountComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCostcenteraccountComunRest() {
		super("accostcenteraccount");
	}
	
	@ApiOperation(notes = "Obtener dto . Retorno: List DtoComunAcCashflowmst ",					
			nickname="AC_CASHFLOWMST_COBTDTO", value = "Obtener dto", tags = {"AC_COSTCENTERACCOUNT", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcCostcenteraccount> obtenerDto(@RequestBody DtoComunAcCostcenteraccount pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcCostcenteraccount dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcCostcenteraccount>(dto,HttpStatus.OK);
	}
	
	public DtoComunAcCostcenteraccount obtenerDtoCore(DtoComunAcCostcenteraccount pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, pk.getAccount()));
		List datos = this.listarPorQuery(DtoComunAcCostcenteraccount.class, "accostcenteraccount.obtenerDto",parametros);
		DtoComunAcCostcenteraccount dto;
		if (datos.size()==1) {
			dto = (DtoComunAcCostcenteraccount)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcCostcenteraccount();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Obtener status . Retorno: String ",					
			nickname="AC_CASHFLOWMST_COBTSTA", value = "Obtener dto", tags = {"AC_COSTCENTERACCOUNT", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerStatus(@RequestBody DtoComunAcCostcenteraccount pk) throws Exception {
		logger.debug("obtenerstatus");
		DtoComunAcCostcenteraccount dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getStatus(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto filtros: costcenter, account, status. Retorno: List DtoComunAcCostcenteraccount ",					
			nickname="AC_CASHFLOWMST_CLISTDTOFIL", value = "Obtener dto", tags = {"AC_COSTCENTERACCOUNT", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcCostcenteraccount>> listarDtoFiltros(@RequestBody DtoComunAcCostcenteraccount filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter(null);
		if (UString.esNuloVacio(filtro.getAccount()))
			filtro.setAccount(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcCostcenteraccount.class, "accostcenteraccount.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAcCostcenteraccount>>(datos, HttpStatus.OK);
	}
	
}
