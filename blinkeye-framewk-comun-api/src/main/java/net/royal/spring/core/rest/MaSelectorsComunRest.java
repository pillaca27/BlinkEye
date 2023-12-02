package net.royal.spring.core.rest;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comun/ma/maselectores")
@CrossOrigin(origins = "*")
public class MaSelectorsComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(MaSelectorsComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public MaSelectorsComunRest() {
		super("maselectors");
	}

	@Transactional // UNIDAD NEGOCIO
	@GetMapping(value = "/listarUnidadNegocio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarUnidadNegocio() {
		logger.debug("MaPersonagrupoRest.listarUnidadNegocio");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarUnidadNegocio");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // COMUN MONEDAS
	@GetMapping(value = "/listarMonedas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarMonedas() {
		logger.debug("MaPersonagrupoRest.listarMonedas");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarMonedas");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // COMUN DEPARTAMENTOS
	@GetMapping(value = "/listarDepartamentos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarDepartamentos() {
		logger.debug("MaPersonagrupoRest.listarDepartamentos");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarDepartamentos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // LIBRO
	@GetMapping(value = "/listarLibro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarLibro() {
		logger.debug("MaPersonagrupoRest.listarLibro");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarLibro");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // VOUCHER SOURCE
	@GetMapping(value = "/listarVoucherSource", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarVoucherSource() {
		logger.debug("MaPersonagrupoRest.listarVoucherSource");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarVoucherSource");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // TIPO VOUCHER
	@GetMapping(value = "/listarTipoVoucher", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoVoucher() {
		logger.debug("MaPersonagrupoRest.listarTipoVoucher");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarTipoVoucher");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // SUCURSAL
	@GetMapping(value = "/listarSucusal", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarSucusal() {
		logger.debug("MaPersonagrupoRest.listarSucusal");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarSucusal");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // COSTO DESTINO
	@GetMapping(value = "/listarCostoDestino", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarCostoDestino() {
		logger.debug("MaPersonagrupoRest.listarCostoDestino");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarCostoDestino");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // COMUN TIPO DOCUMENTO
	@GetMapping(value = "/listarTipoDocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarTipoDocumento() {
		logger.debug("MaPersonagrupoRest.listarTipoDocumento");
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "maselectors.listarTipoDocumento");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	@Transactional // TIPO DE CAMBIO
	@GetMapping(value = "/f_sql_get_rate/{fecha}/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> f_sql_get_rate(@PathVariable String fecha, @PathVariable String tipo) {
		logger.debug("MaPersonagrupoRest.f_sql_get_rate");
		BigDecimal result = new BigDecimal(0);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_fecha", String.class, fecha));
		List datos = this.listarPorQuery(DtoComunTipocambiomast.class, "maselectors.f_sql_get_rate", parametros);

		if (datos.size() > 0) {
			DtoComunTipocambiomast dto = (DtoComunTipocambiomast) datos.get(0);

			switch (tipo) {
			case "P":
				result = dto.getFactorpromedio();
				break;

			case "C":
				result = dto.getFactorcompra();
				break;

			case "V":
				result = dto.getFactorventa();
				break;

			case "":
				result = dto.getFactor();
				break;

			case "CS":
				result = dto.getFactorcomprasbs();
				break;

			case "VS":
			case "S":
				result = dto.getFactorventasbs();
				break;
			}

		}
		return new ResponseEntity<BigDecimal>(result, HttpStatus.OK);
	}

}
