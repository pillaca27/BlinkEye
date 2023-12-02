package net.royal.spring.contabilidad.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherInterface;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherMain;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherheader;
import net.royal.spring.contabilidad.servicio.impl.VoucherheaderComunServicioImpl;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comun/ac/voucherheader")
@CrossOrigin(origins = "*")
public class VoucherheaderComunRest extends GenericoHibernateRest {
	
	@Autowired
	private VoucherheaderComunServicioImpl servicio;

	private static Logger logger = LogManager.getLogger(VoucherheaderComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public VoucherheaderComunRest() {
		super("voucherheader");
	}

	// PRESENTACI\u00D3N DE PANTALLA CON LOS VOUCHERS
	@Transactional
	@PostMapping(value = "/voucher_validation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunVoucherMain> voucher_validation(@RequestBody DtoComunVoucherMain lst) throws Exception {
		logger.debug("VoucherheaderComunRest.voucher_validation");
		DtoComunVoucherMain dto = servicio.voucher_validation(this.getUsuarioActual(), lst);
		return new ResponseEntity<DtoComunVoucherMain>(dto, HttpStatus.CREATED);
	}

	// GENERAR LOS 2 ARRAYS (AP,WH,CO,FA,WF)
	@Transactional
	@PostMapping(value = "/generar_voucher", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunVoucherInterface>> generar(@RequestBody DtoComunVoucherMain lst) throws Exception {
		logger.debug("VoucherheaderComunRest.generar_voucher");
		List<DtoComunVoucherInterface> dto = servicio.coreGenerar(this.getUsuarioActual(), lst);
		return new ResponseEntity<List<DtoComunVoucherInterface>>(dto, HttpStatus.CREATED);
	}

	// LABEL DE COLUMNAS DEL VOUCHER
	@Transactional
	@GetMapping(value = "/listarLabelDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarLabelDetail() {
		logger.debug("VoucherheaderComunRest.listarLabelDetail");
		// TODO VoucherheaderRest.listar : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "voucherheader.f_label_detail");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}

	// REGISTRAR VOUCHERS
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunVoucherheader> generar(@RequestBody DtoComunVoucherheader dto) throws Exception {
		logger.debug("VoucherheaderComunRest.registrar");
		dto = servicio.coreRegistrar(this.getUsuarioActual(), dto);
		return new ResponseEntity<DtoComunVoucherheader>(dto, HttpStatus.CREATED);
	}

	// MAYORIZADO
	@Transactional
//	@PutMapping(value = "/mayorizar", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/mayorizarvouchers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunVoucherheader> mayorizar(@RequestBody List<DtoComunVoucherheader> lst) throws Exception {
		logger.debug("VoucherheaderComunRest.mayorizar");
		DtoComunVoucherheader dto = new DtoComunVoucherheader();
		dto = servicio.coreMayorizar(this.getUsuarioActual(), lst);
		return new ResponseEntity<DtoComunVoucherheader>(dto, HttpStatus.OK);
	}

	// EXPORTAR DETALLE DEL VOUCHER
	@Transactional
	@PostMapping(value = "/exportarVoucherDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarVoucherDetail(HttpServletResponse response, @RequestBody DtoComunVoucherheader filtro)
			throws Exception {

		filtro.getDw_detail().stream()
				.forEach(x -> x.setVariabledateaux(UFechaHora.convertirFechaCadenaSinHora(x.getVariabledate())));

		String[] arrCabecera = new String[] { "#", "Cuenta", "Persona", "Factura", "Fecha", "Monto Local",
				"Monto D\u00F3lar", "C.Costos", "Dest Gasto", "Proyecto", "Sucursal", "OC/OS", "Libro Aux.", "C04", "P.Neto",
				"C06", "C07", "OT", "Descripci\u00f3n" };
		String[] arrColumnas = new String[] { "voucherline", "account", "vendor", "invoice", "variabledateaux",
				"localamount", "dollaramount", "costcenter", "invoice", "afe", "sucursal", "commitment",
				"subledgercode", "variablecode04", "variablecode05", "variablecode06", "variablecode07",
				"variablecode08", "description" };

		String title = "Listado de Detalle de Vouchers";
		DtoWhExportar dtoExportar = new DtoWhExportar();
		dtoExportar.setTitulo(title);
		dtoExportar.setTipoExpotar(filtro.getTipoExportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(null);
		dtoExportar.setLista(filtro.getDw_detail());

		servicio.exportarInformacionWh(response, dtoExportar);
	}
}
