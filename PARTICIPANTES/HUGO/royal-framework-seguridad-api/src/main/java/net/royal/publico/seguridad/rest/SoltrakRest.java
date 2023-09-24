package net.royal.publico.seguridad.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.royal.autorizacion.seguridad.rest.LoginRest;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioLogin;
import net.royal.spring.framework.soltrak.LoginSoltrakRequest;
import net.royal.spring.framework.soltrak.LoginSoltrakResponse;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.servicio.impl.UsuarioServicioImpl;

@RestController
@RequestMapping("/publico/seguridad/soltrak")
@CrossOrigin(origins = "*")
public class SoltrakRest extends GenericoRest {

	@Autowired
	private UsuarioServicioImpl servicio;

	private static Logger logger = LogManager.getLogger(SoltrakRest.class);

	@Transactional
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginSoltrakResponse login(@RequestBody LoginSoltrakRequest bean) throws Exception {

		SeguridadUsuarioActual usuarioValidado = null;

		bean.setUsuarioLogin(UString.obtenerSinNulo(bean.getUsuarioLogin()).toUpperCase());

		usuarioValidado = servicio.login(bean.getAplicacionCodigo(), bean.getUsuarioLogin(), bean.getUsuarioClave(),
				bean.getCompaniaCodigo());

		ObjectMapper mapper = new ObjectMapper();
		SeguridadUsuarioActual usuarioActual = usuarioValidado;

		usuarioActual.setTipoUsuarioId(SeguridadUsuarioLogin.TIPOUSUARIO_EMPLEADO);
		usuarioActual.setAplicacionCodigo(bean.getAplicacionCodigo());
		usuarioActual.setCompaniaCodigo(bean.getCompaniaCodigo());
		usuarioActual.setUsuario(bean.getUsuarioLogin());

		Integer minutos = 480;

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date desde = new Date();
		Date hasta = new Date(t + (100000 * minutos));
		Date hastaLargo = new Date(t + (100000 * minutos));

		String jwtToken = Jwts.builder().setSubject(mapper.writeValueAsString(usuarioActual)).claim("roles", "user")
				.setIssuedAt(desde).signWith(SignatureAlgorithm.HS256, ConstanteBoot.TOKEN_JWTKEY).setExpiration(hastaLargo).compact();

		LoginSoltrakResponse response = new LoginSoltrakResponse();
		response.setToken(jwtToken);

		Map<String, Object> mapUsuario = new HashMap<String, Object>();
		mapUsuario.put("hasta", hasta);
		mapUsuario.put("usuario", usuarioActual);
		LoginRest.sessiones.put(jwtToken, mapUsuario);

		return response;
	}
}
