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
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcAfecompany;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/acafecompanycomun")
@CrossOrigin(origins = "*")
public class AcAfecompanyComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcAfecompanyComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcAfecompanyComunRest() {
		super("acafecompany");
	}
	
	public DtoComunAcAfecompany obtenerDtoCore(DtoComunAcAfecompany pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, pk.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		List datos = this.listarPorQuery(DtoComunAcAfecompany.class, "acafecompany.obtenerDto",parametros);
		DtoComunAcAfecompany dto;
		if (datos.size()==1) {
			dto = (DtoComunAcAfecompany)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAcAfecompany();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
		
	@ApiOperation(notes = "Obtener Dto por PK. Retorno: DtoComunAcAfecompany",					
			nickname="AC_AFECOMPANY_COBTENERDTO", value = "Obtener dto por pk", tags = {"AC_AFECOMPANY", "OBTENER", "DTO"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAcAfecompany> obtenerDto(@RequestBody DtoComunAcAfecompany pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAcAfecompany dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunAcAfecompany>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener Estado por PK. Retorno: String",					
			nickname="AC_AFECOMPANY_COBTSTA", value = "Obtener estado por pk", tags = {"AC_AFECOMPANY", "OBTENER", "ESTADO"})
	@Transactional
	@PutMapping(value="/obtenerstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerStatus(@RequestBody DtoComunAcAfecompany pk) throws Exception {
		logger.debug("obtenerstatus");
		DtoComunAcAfecompany dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getStatus(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto por filtros: afe, companyowner, status. Retorno: List DtoComunAcAfecompany",					
			nickname="AC_AFECOMPANY_CLISTARFIL", value = "LISTAR DTO POR FILTRO", tags = {"AC_AFECOMPANY", "LISTAR", "DTO"})
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAcAfecompany>> listarDtoFiltros(@RequestBody DtoComunAcAfecompany filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getAfe()))
			filtro.setAfe(null);
		if (UString.esNuloVacio(filtro.getCompanyowner()))
			filtro.setCompanyowner(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_afe", String.class, filtro.getAfe()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunAcAfecompany.class, "acafecompany.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunAcAfecompany>>(datos, HttpStatus.OK);
	}
	
}
