package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.dto.DtoComunTipodocumentocxp;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.dominio.lista.DtlComunTipocambiomast;
import net.royal.spring.core.servicio.impl.TipocambiomastServicioImpl;
import net.royal.spring.core.servicio.validar.TipocambiomastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.TipoCambioSunatTransaccion;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/tipocambiosunat")
@CrossOrigin(origins = "*")
public class TipoCambioSunatComunRest {

	@Transactional
	@GetMapping(value = "/obteneractual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoCambioSunatTransaccion> obtenerActual() { 
		TipoCambioSunatTransaccion dto = new TipoCambioSunatTransaccion();
		try {
			dto = obtenerTipoDeCambioEx();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
				dto = obtenerTipoDeCambioEx();
			} catch (Exception e1) {
				e1.printStackTrace();
				dto.setTransaccionEstado(DominioTransaccion.ERROR);
				dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e1));
			}
		}		
		return new ResponseEntity<TipoCambioSunatTransaccion>(dto, HttpStatus.OK); 
	}
	
	public static TipoCambioSunatTransaccion obtenerTipoDeCambioEx() throws Exception {

		TipoCambioSunatTransaccion dto = new TipoCambioSunatTransaccion();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.TEXT_HTML);
		headers.add("Cookie",
				"visid_incap_2355492=msIJgwjzRWeEOfNiudGhO7DiN2AAAAAAQUIPAAAAAAC/o9psxacaT8jHiQQs4pbE; incap_ses_994_2355492=UghNZIuK5jTmik77CmbLDbDiN2AAAAAAbS/cNFaqFSyskv+UyFQpKg==; ___utmvmcMuIwpVZ=dIzdFnwazKT; ___utmvbcMuIwpVZ=pZZ XqsOValf: mty");
		headers.add("User-Agent", "PostmanRuntime/7.26.8");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				"https://www.sbs.gob.pe/app/pp/sistip_portal/paginas/publicacion/tipocambiopromedio.aspx",
				HttpMethod.GET, new HttpEntity<String>(headers), String.class);

		if (response.getStatusCode() == HttpStatus.OK) {

			String body = response.getBody();

			int indexOfCompraInicio = body.indexOf("<td class=\"APLI_fila2\"");

			String nuevoBodyCompra = body.substring(indexOfCompraInicio + 35, body.length());

			int indexOfCompraFin = nuevoBodyCompra.indexOf("</td>");

			Double dblCompra = Double.parseDouble(nuevoBodyCompra.substring(0, indexOfCompraFin));
			dto.setCompra(new BigDecimal(dblCompra));

			String nuevoBody = nuevoBodyCompra.substring(indexOfCompraFin, nuevoBodyCompra.length());

			int indexOfVentaInicio = nuevoBody.indexOf("<td class=\"APLI_fila2\"");

			String nuevoBodyVenta = nuevoBody.substring(indexOfVentaInicio + 35, nuevoBody.length());

			int indexOfVentaFin = nuevoBodyVenta.indexOf("</td>");

			Double dblVenta = Double.parseDouble(nuevoBodyVenta.substring(0, indexOfVentaFin));
			dto.setVenta(new BigDecimal(dblVenta));

			//dto.setTipoRespuesta(1);
			//dto.setMensajeRespuesta("Ok");
			dto.setTransaccionEstado(DominioTransaccion.OK);
		} else {
			//dto.setTipoRespuesta(2);
			//dto.setMensajeRespuesta("Error");
			dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		}

		return dto;
	}
	
}

