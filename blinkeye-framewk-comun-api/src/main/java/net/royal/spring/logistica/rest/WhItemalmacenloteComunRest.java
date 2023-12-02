package net.royal.spring.logistica.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemalmacenlote;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemmast;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhItemmast;
import net.royal.spring.rrhh.dominio.dto.DtoComunHrCursodescripcion;

@RestController
@RequestMapping("/spring/logistica/whitemalmacenlotecomun")
@CrossOrigin(origins = "*")
public class WhItemalmacenloteComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhItemalmacenloteComunRest.class);
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public WhItemalmacenloteComunRest() {
		super("whitemalmacenlote");
	}
	
	@ApiOperation(notes = "Obtener dto item almacen lote. Entrada: DtoComunWhItemalmacenlote: item, condicion, almacencodigo, lote. Retorno: DtoComunWhItemalmacenlote",					
			nickname="WH_ITEMALMACENLOTE-COBT", value = "Obtener dto item almacen lote", tags = {"WH_ITEMALMACENLOTE", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhItemalmacenlote> obtenerDto(@RequestBody DtoComunWhItemalmacenlote pk) throws Exception {
		logger.debug("WhItemalmacenloteRest.obtenerDto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_item", String.class, pk.getItem()));
		parametros.add(new DominioParametroPersistencia("p_condicion", String.class, pk.getCondicion()));
		parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, pk.getAlmacencodigo()));
		parametros.add(new DominioParametroPersistencia("p_lote", String.class, pk.getLote()));
		List datos = this.listarPorQuery(DtoComunWhItemalmacenlote.class, "whitemalmacenlote.obtenerDto",parametros);
		DtoComunWhItemalmacenlote dto;
		if (datos.size()==1) {
			dto = (DtoComunWhItemalmacenlote)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhItemalmacenlote();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhItemalmacenlote>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value="/stockactualporitemalmacen", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhItemalmacenlote> stockActualPorItemAlmacen(@RequestBody DtoComunWhItemalmacenlote pk) throws Exception {
		logger.debug("WhItemalmacenloteRest.stockActualPorItemAlmacenv");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_item", String.class, pk.getItem()));
		parametros.add(new DominioParametroPersistencia("p_almacencodigo", String.class, pk.getAlmacencodigo()));
		List datos = this.listarPorQuery(DtoComunWhItemalmacenlote.class, "whitemalmacenlote.stockActualPorItemAlmacen",parametros);
		DtoComunWhItemalmacenlote dto;
		if (datos.size()==1) {
			dto = (DtoComunWhItemalmacenlote)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhItemalmacenlote();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhItemalmacenlote>(dto,HttpStatus.OK);
	}
}
