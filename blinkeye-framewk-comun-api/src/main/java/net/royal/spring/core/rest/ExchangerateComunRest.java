package net.royal.spring.core.rest;

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
import net.royal.spring.core.dominio.dto.DtoComunAfemst;
import net.royal.spring.core.dominio.dto.DtoComunExchangerate;
import net.royal.spring.core.dominio.filtro.FiltroComunAfemst;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrUnidadoperativa;
import net.royal.spring.tesoreria.dominio.dto.DtoComunApCajachicaruta;

@RestController
@RequestMapping("/spring/core/exchangeratecomun")
@CrossOrigin(origins = "*")
public class ExchangerateComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(ExchangerateComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public ExchangerateComunRest() {
		super("exchangerate");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunExchangerate> obtenerDto(@RequestBody DtoComunExchangerate pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunExchangerate dto = obtenerDtocore(pk);
		return new ResponseEntity<DtoComunExchangerate>(dto,HttpStatus.OK);
	}
	
	public DtoComunExchangerate obtenerDtocore(DtoComunExchangerate pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_period", String.class, pk.getPeriod()));
		List datos = this.listarPorQuery(DtoComunExchangerate.class, "exchangerate.obtenerDto",parametros);
		DtoComunExchangerate dto;
		if (datos.size()==1) {
			dto = (DtoComunExchangerate)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunExchangerate();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value="/obtenersbsrate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> obtenerSbsrate(@RequestBody DtoComunExchangerate pk) throws Exception {
		logger.debug("obtenersbsrate");
		DtoComunExchangerate dto = obtenerDtocore(pk);
		return new ResponseEntity<BigDecimal>(dto.getSbsrate(),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunExchangerate>> listardtofiltros(@RequestBody DtoComunExchangerate filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getPeriod()))
            filtro.setPeriod(null);
        if (UString.estaVacio(filtro.getStatus()))
            filtro.setStatus(null);
        parametros.add(new DominioParametroPersistencia("p_period", String.class, filtro.getPeriod()));
        parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
        List datos = this.listarPorQuery(DtoComunExchangerate.class, "exchangerate.listardtofiltros", parametros);
		return new ResponseEntity<List<DtoComunExchangerate>>(datos, HttpStatus.OK); 
	}
}
