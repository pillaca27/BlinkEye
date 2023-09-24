package net.royal.spring.sy.rest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.sy.dao.impl.SyPreferencesDaoImpl;
import net.royal.spring.sy.dominio.BeanSyPreferences;
import net.royal.spring.sy.dominio.BeanSyPreferencesPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyPreferences;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyPreferences;
import net.royal.spring.sy.servicio.impl.SyPreferencesServicioImpl;
import net.royal.spring.sy.servicio.validar.SyPreferencesServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/sypreferencescomun")
@CrossOrigin(origins = "*")
public class SyPreferencesComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyPreferencesComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyPreferencesComunRest() {
		super("sypreferences");
	}
	
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyPreferences> obtenerDto(@RequestBody DtoComunSyPreferences pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunSyPreferences dto = obtenerDtoCore(pk);
		return new ResponseEntity<DtoComunSyPreferences>(dto,HttpStatus.OK);
	}
	
	public DtoComunSyPreferences obtenerDtoCore(DtoComunSyPreferences pk) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_preference", String.class, pk.getPreference()));
		List datos = this.listarPorQuery(DtoComunSyPreferences.class, "sypreferences.obtenerDto",parametros);
		DtoComunSyPreferences dto;
		if (datos.size()==1) {
			dto = (DtoComunSyPreferences)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunSyPreferences();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@Transactional
	@PutMapping(value="/obtenervalorstring", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerValorstring(@RequestBody DtoComunSyPreferences pk) throws Exception {
		logger.debug("obtenervalorstring");
		DtoComunSyPreferences dto = obtenerDtoCore(pk);
		return new ResponseEntity<String>(dto.getValorstring(),HttpStatus.OK);
	}
}
