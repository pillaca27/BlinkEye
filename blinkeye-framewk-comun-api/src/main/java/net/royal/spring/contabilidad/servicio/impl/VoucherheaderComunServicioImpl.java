package net.royal.spring.contabilidad.servicio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import net.royal.spring.contabilidad.dao.impl.VoucherheaderComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanVoucherheader;
import net.royal.spring.contabilidad.dominio.dto.DtoComunJsonVoucher;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherError;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherInterface;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherMain;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherdetail;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherheader;
import net.royal.spring.contabilidad.servicio.validar.VoucherheaderComunServicioValidar;
import net.royal.spring.core.servicio.impl.ParametrosmastServicioImpl;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.ULista;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioVoucherheaderComun")
public class VoucherheaderComunServicioImpl extends GenericoServicioImpl {

	@Autowired
	private VoucherheaderComunDaoImpl voucherheaderDao;

	@Autowired
	private VoucherheaderComunServicioValidar validar;

	@Autowired
	private VoucherdetailComunServicioImpl voucherdetailServicioImpl;

	@Autowired
	private ParametrosmastServicioImpl parametrosmastServicioImpl;

	private Integer w_linea;

	public static String SPRING_NOMBRE = "BeanServicioVoucherheaderComun";
	private static Logger logger = LogManager.getLogger(VoucherheaderComunServicioImpl.class);

	@Transactional
	public DtoComunVoucherheader coreRegistrar(SeguridadUsuarioActual usuarioActual, DtoComunVoucherheader dto) throws Exception {
		BeanVoucherheader voucherheader = dto.obtenerBean();
		voucherheader = coreRegistrar(usuarioActual, voucherheader, dto);
		dto.setVoucherno(voucherheader.getPk().getVoucherno());
		dto.setTransaccionEstado(voucherheader.getTransaccionEstado());
		dto.setTransaccionListaMensajes(voucherheader.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanVoucherheader coreRegistrar(SeguridadUsuarioActual usuarioActual, BeanVoucherheader voucherheader,
			DtoComunVoucherheader dto) throws Exception {

		// valores por defecto - preparando objeto
		voucherheader = validar.prepararRegistrar(usuarioActual, voucherheader, dto);
		voucherheader = voucherheaderDao.coreInsertar(voucherheader);

		for (DtoComunVoucherdetail detalle : dto.getDw_detail()) {
			detalle.setPeriod(voucherheader.getPk().getPeriod());
			detalle.setVoucherno(voucherheader.getPk().getVoucherno());
			detalle.setCompanyowner(voucherheader.getPk().getCompanyowner());

			voucherdetailServicioImpl.coreInsertar(usuarioActual, detalle);
		}
		voucherheader.setTransaccionEstado(DominioTransaccion.OK);
		voucherheader.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return voucherheader;
	}

	public List<DtoComunVoucherInterface> coreGenerar(SeguridadUsuarioActual usuarioActual, DtoComunVoucherMain dw1)
			throws Exception {

		// CREAMOS UN MAP PARA ENVIAR UNA ESTRUCTURA JSON AL SP
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("data", dw1.getLstJson());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(mapJson);

		dw1.setJsonobligaciones(jsonStr); // asignamos las keys

		// LEEMOS ESTRUCTURA JSON QUE DEVUELVA VOUCHERS
		List<DtoComunJsonVoucher> lstJson = voucherheaderDao.f_read_spw_ap_100_voucher(usuarioActual, dw1);
		String w_error = lstJson.get(0).getTitulo();

		List<DtoComunVoucherInterface> dto = new ArrayList<DtoComunVoucherInterface>();

		if (w_error.equals("OK")) {
			dw1.setTransaccionEstado(DominioTransaccion.OK);
			// ESTRUCTURAMOS DTO ENVIANDO EL JSON
			ObjectMapper objectMapper = new ObjectMapper();
			List<DtoComunVoucherheader> lstvoucherheader = objectMapper.readValue(lstJson.get(0).getVoucherheader(),
					new TypeReference<List<DtoComunVoucherheader>>() {
					});
			List<DtoComunVoucherdetail> lstvoucherdetalle = objectMapper.readValue(lstJson.get(0).getVoucherdetalle(),
					new TypeReference<List<DtoComunVoucherdetail>>() {
					});

			// ASIGNAMOS VALORES PARA LA CABECERA y la INTERFACE
			for (DtoComunVoucherheader dw2 : lstvoucherheader) {
				dw2.setFecha_tc(UFechaHora.convertirFechaCadena(dw2.getExchangerateperiod()));
				dw2.setFecha(UFechaHora.convertirFechaCadena(dw2.getVoucherdate()));
				dw2.setDw_detail(lstvoucherdetalle.stream().filter(x -> x.getVoucherno().equals(dw2.getVoucherno()))
						.collect(Collectors.toList()));
				dw2.getDw_detail().stream().forEach(x -> {
					x.setAccion("N");
					x.setVariabledate_aux(UFechaHora.convertirFechaCadena(x.getVariabledate()));
				});
				DtoComunVoucherInterface dwInterface = new DtoComunVoucherInterface();
				dwInterface.setPeriodo(dw2.getPeriod());
				dwInterface.setCompania(dw2.getCompanyowner());
				dwInterface.setVoucher(dw2.getVoucherno());
				dwInterface.setFecha(dw2.getVoucherdate());
				dwInterface.setLineas(dw2.getDw_detail().size());
				dwInterface.setDwvoucher(dw2);
				dto.add(dwInterface);
			}
		} else {
			dw1.setTransaccionEstado(DominioTransaccion.VALIDACION);
			lstJson.stream().forEach(x -> {
				DominioMensajeUsuario dwerror = new DominioMensajeUsuario();
				dwerror.setTitulo(x.getTitulo());
				dwerror.setMensaje(x.getMensaje());
				dw1.getTransaccionListaMensajes().add(dwerror);
			});
			dto.add((DtoComunVoucherInterface) dw1.getDw());
		}

		return dto;
	}

	// GENERA VOUCHERS EN MEMORIA
	public DtoComunVoucherMain voucher_validation(SeguridadUsuarioActual usuarioActual, DtoComunVoucherMain dw1)
			throws Exception {

		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// DECLARACI\u00D3N DE PARAMETROS
		String parametros = "VOUPERIOD;VOULINEDES;VOUGENERR;VOUCHPOST";
		List<ParametroTransaccion> lstParametros = parametrosmastServicioImpl.parametroList("AC", parametros);

		dw1.setParametroperiod(voucherheaderDao.parametroObtenerTexto(lstParametros, "VOUPERIOD"));
		dw1.setParametrovoulindes(voucherheaderDao.parametroObtenerTexto(lstParametros, "VOULINEDES"));
		dw1.setParametrovougenerr(voucherheaderDao.parametroObtenerTexto(lstParametros, "VOUGENERR"));
		dw1.setParametrovouchpost(voucherheaderDao.parametroObtenerTexto(lstParametros, "VOUCHPOST"));

		for (DtoComunVoucherInterface dw : dw1.getDw()) {

			// VALIDACIONES DE VOUCHER
			String periodoVoucher = UFechaHora.calcularPeriodoPorFecha(dw.getDwvoucher().getVoucherdate());
			String periodoActual = dw.getPeriodo().replace("-", "");

			if (!periodoVoucher.equals(periodoActual)) {
				dw.getDwvoucher().setVoucherdate(UFechaHora.calcularfechaporperiododefault(periodoActual));
				dw.getDwvoucher().setFecha(UFechaHora.convertirFechaCadenaSinHora(dw.getDwvoucher().getVoucherdate()));
				dw.setFecha(UFechaHora.calcularfechaporperiododefault(periodoActual));
			}

			// SP DE VALIDACIONES
			lst = validar.f_sql_validate_voucher_header(usuarioActual, dw.getDwvoucher(), dw1.getParametroperiod());

			// SETEO DE DETALLE
			dw.getDwvoucher()
					.setDw_detail(
							dw.getDwvoucher().getDw_detail().stream()
									.filter(x -> !UBigDecimal.esCeroOrNulo(x.getLocalamount())
											&& !UBigDecimal.esCeroOrNulo(x.getDollaramount()))
									.collect(Collectors.toList()));

			dw.getDwvoucher().setLastdate(new Date());
			dw.getDwvoucher().setLastuser(usuarioActual.getUsuario());
			dw.getDwvoucher().setPreparadopor(usuarioActual.getPersonaNombreCompleto());
			dw.getDwvoucher().setReplicationunit(UString.estaVacio(usuarioActual.getUnidadReplicacionCodigo()) ? "LIMA"
					: usuarioActual.getUnidadReplicacionCodigo());
			dw.getDwvoucher().setRunnumber(0);
			dw.getDwvoucher().setPostingnumber(0);
			dw.getDwvoucher().setReprintnumber(0);
			dw.getDwvoucher().setVoucherno_first(dw.getDwvoucher().getVoucherno().substring(0, 2));

			Integer w_line = 1;
			for (DtoComunVoucherdetail dtoVoucherdetail : dw.getDwvoucher().getDw_detail()) {
				if (dw1.getParametrovoulindes().equals("A"))
					dtoVoucherdetail.setDescription(dtoVoucherdetail.getAccountlocalname());

				dtoVoucherdetail.setPostedamountdollar(new BigDecimal(0));
				dtoVoucherdetail.setPostedamountlocal(new BigDecimal(0));
				dtoVoucherdetail.setVoucherline(w_line);

				double w_local = dw.getDwvoucher().getLocalacum().doubleValue()
						+ dtoVoucherdetail.getLocalamount().doubleValue();
				double w_dolar = dw.getDwvoucher().getDolaracum().doubleValue()
						+ dtoVoucherdetail.getDollaramount().doubleValue();

				dw.getDwvoucher().setLocalacum(new BigDecimal(w_local));
				dw.getDwvoucher().setDolaracum(new BigDecimal(w_dolar));

				voucherheaderDao.f_account_validate(dtoVoucherdetail);
				List<DominioMensajeUsuario> lstvalidatedetail = validar.f_sql_validate_invoice_approve(usuarioActual,
						dtoVoucherdetail);
				lst.addAll(lstvalidatedetail);

				if (!ULista.esListaVacia(lstvalidatedetail))
					dw.getDwvoucher().setStatus("E");
				else
					dw.getDwvoucher().setStatus("V");

				w_line++;
			}

			/* DISTRIBUCI\u00D3N DE ERRORES */
			w_linea = 0;
			lst.stream().forEach(x -> {
				w_linea++;
				DtoComunVoucherError err = new DtoComunVoucherError();
				err.setMensaje(x.getMensaje());
				err.setCompanyowner(dw.getCompania());
				err.setPeriodo(dw.getPeriodo());
				err.setLinea(w_linea);
				err.setVoucherno(dw.getVoucher());
				dw.getDwerror().add(err);
			});
			dw.setErrores(dw.getDwerror().size());

			if (dw.getErrores() > 0) {
				dw.setStatus("ER");
				dw.getDwvoucher().setStatus("ER");
			} else {
				if (dw.getStatus().equals("AP")) {
					dw.getDwvoucher().setAprobadopor(usuarioActual.getPersonaNombreCompleto());
					dw.getDwvoucher().setApprovedby(usuarioActual.getPersonaId());
					dw.getDwvoucher().setApproveddate(new Date());
					dw.getDwvoucher().setStatus("AP");
				}
			}
			/**************************/

			/*
			 * if(UBigDecimal.esCeroOrNulo(dw.getDwvoucher().getDolaracum()) &&
			 * UBigDecimal.esCeroOrNulo(dw.getDwvoucher().getLocalacum())) { DtoVoucherError
			 * err= new DtoVoucherError();
			 * err.setMensaje("El voucher no esta cuadrado. Debitos <> Creditos");
			 * err.setCompanyowner(dw.getCompania()); err.setPeriodo(dw.getPeriodo());
			 * err.setLinea(w_linea); err.setVoucherno(dw.getVoucher());
			 * dw.getDwerror().add(err); dw.setErrores(dw.getDwerror().size());
			 * 
			 * }
			 */

			// SI EL PARAMETRO VOUGENER ES 'N' PERMITIR GUARDAR
			if (dw1.getParametrovougenerr().equals("N") && dw.getStatus().equals("ER"))
				dw1.setBtnaceptar(true);

			dw.getDwvoucher().setTotalerrorlines(dw.getErrores());

		}
		// dw1.setDw(dto);

		return dw1;
	}

	@Transactional
	public DtoComunVoucherheader coreMayorizar(SeguridadUsuarioActual usuarioActual, List<DtoComunVoucherheader> lst)
			throws Exception {
		DtoComunVoucherheader dto = new DtoComunVoucherheader();
		for (DtoComunVoucherheader dtoVoucherheader : lst) {
			BeanVoucherheader voucherheader = dtoVoucherheader.obtenerBean();
			voucherheader = coreMayorizar(usuarioActual, voucherheader);
			dto.setTransaccionEstado(voucherheader.getTransaccionEstado());
			dto.setTransaccionListaMensajes(voucherheader.getTransaccionListaMensajes());
		}
		return dto;
	}
	
	@Transactional
	public BeanVoucherheader coreMayorizar(SeguridadUsuarioActual usuarioActual, BeanVoucherheader voucherheader)
			throws Exception {

		voucherheader = validar.prepararActualizar(usuarioActual, voucherheader);
		voucherheader.setAuxAccion("POSTING");
		List<DominioMensajeUsuario> dw=voucherheaderDao.uf_voucher_posting(voucherheader,usuarioActual.getUsuario());
		
		if(!ULista.esListaVacia(dw)) {
			if(!dw.get(0).getMensaje().equals("ok")){
				voucherheader.setTransaccionEstado(DominioTransaccion.VALIDACION);
				voucherheader.setTransaccionListaMensajes(dw);
				return voucherheader;
			}
		}	
		voucherheader.setTransaccionEstado(DominioTransaccion.OK);
		voucherheader.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return voucherheader;
	}
}
