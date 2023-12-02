package net.royal.spring.contabilidad.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.contabilidad.dao.impl.VoucherheaderComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanVoucherheader;
import net.royal.spring.contabilidad.dominio.dto.DtoComunLastvouchernumber;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherdetail;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherheader;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service(value = "BeanValidarVoucherheaderComun")
public class VoucherheaderComunServicioValidar extends GenericoServicioValidar {

	@Autowired
	private VoucherheaderComunDaoImpl voucherheaderDao;

	public static String SPRING_NOMBRE = "BeanValidarVoucherheaderComun";
	private static Logger logger = LogManager.getLogger(VoucherheaderComunServicioValidar.class);

	public BeanVoucherheader prepararRegistrar(SeguridadUsuarioActual usuarioActual, BeanVoucherheader voucherheader,
			DtoComunVoucherheader dto) throws Exception {

		voucherheader.setPreparedby(usuarioActual.getPersonaId());
		voucherheader.setPrepareddate(new Date());
		voucherheader.setTotallines(dto.getDw_detail().size());
		voucherheader.setTotalerrorlines(dto.getTotalerrorlines());

		voucherheader.setLocalcredits(dto.getTotal_local_02());
		voucherheader.setLocaldebits(dto.getTotal_dolar_02());

		voucherheader.setDollarcredits(dto.getTotal_local_04());
		voucherheader.setDollardebits(dto.getTotal_dolar_04());
		voucherheader.getPk().setPeriod(UString.esPeriodoValido(voucherheader.getPk().getPeriod()));

		if (dto.getIndicadorvoucher().equals("N")) {
			dto.setVoucherno(dto.getVoucherno().substring(0, 2));
			String pk = f_Update_Lastvouchernumber(usuarioActual, dto).getCodigovoucher();
			voucherheader.getPk().setVoucherno(pk);
		}

		if (dto.getParametroVOUCHPOSTS().equals("N"))
			voucherheader.setStatus("AP");

		if (voucherheader.getStatus().equals("AP")) {
			voucherheader.setApprovedby(usuarioActual.getPersonaId());
			voucherheader.setApproveddate(new Date());
		}
		// TODO Voucherheader.Insertar : valores por defecto

		return voucherheader;
	}

	public DtoComunLastvouchernumber f_Update_Lastvouchernumber(SeguridadUsuarioActual usuarioActual, DtoComunVoucherheader dto)
			throws Exception {
		return voucherheaderDao.f_Update_Lastvouchernumber(dto, usuarioActual);
	}

	public List<DominioMensajeUsuario> f_sql_validate_voucher_header(SeguridadUsuarioActual usuarioActual,
			DtoComunVoucherheader dto, String parametro) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		if (UString.esNuloVacio(dto.getPeriod()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_PERIODO));

		if (UString.esNuloVacio(dto.getCompanyowner()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_COMPANY));

		if (UString.esNuloVacio(dto.getVoucherno()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_NUMERO));

		if (UString.esNuloVacio(dto.getLedger()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_LIBRO));

		if (UString.esNuloVacio(dto.getBusinessunit()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_UNIDAD_NEGOCIO));

		if (UString.esNuloVacio(dto.getDepartment()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_DEPARTAMENTO));

		if (UString.esNuloVacio(dto.getCurrency()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_MONEDA));

		if (UString.esNuloVacio(dto.getVouchertype()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_TIPO_VOUCHER));

		if (UBigDecimal.esCeroOrNulo(dto.getExchangerate()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_TIPO_CAMBIO_MONEY));

		if (UString.esNuloVacio(dto.getFecha_tc()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_TIPO_CAMBIO_FECHA));

		if (UString.esNuloVacio(dto.getCurrency()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_CURRENCY));

		if (UString.esNuloVacio(dto.getFecha())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_FECHA));
		} else {
			String periodoVoucher = UFechaHora.calcularPeriodoPorFecha(dto.getVoucherdate());
			String periodoActual = dto.getPeriod().replace("-", "");

			if (!periodoVoucher.equals(periodoActual))
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_PERIOD));
		}

		lst.addAll(f_sql_validate_period_monthly_massive(usuarioActual, dto.obtenerBean(), parametro));

		if (dto.getIndicadorvoucher().equals("S")) {
			Integer w_count = voucherheaderDao.f_read_voucher(dto);
			if (w_count > 0)
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_DUPLICATE));
		}

		if (UString.esNuloVacio(dto.getVouchertitle()))
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_VALID_TITULO));

		return lst;
	}

	public List<DominioMensajeUsuario> f_sql_validate_period_monthly_massive(SeguridadUsuarioActual usuarioActual,
			BeanVoucherheader voucherheader, String parametro) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		String period = voucherheaderDao.f_sql_validate_period_monthly(voucherheader);
		if (UString.esNuloVacio(period)) {
			lst.add(this.getMsjUsuarioError("No se encontr\u00F3 ningun periodo activo (MC), Compa\u00F1\u00EDa "
					+ voucherheader.getPk().getCompanyowner() + ", Libro " + voucherheader.getLedger()));
		} else {
			String indicador = voucherheaderDao.f_sql_validate_period_monthly_indicador(voucherheader);
			String aplicacion = UString.esNuloVacio(usuarioActual.getAplicacionCodigo()) ? "AC"
					: usuarioActual.getAplicacionCodigo();

			if (indicador.equals("N") || (indicador.equals("C") && aplicacion.equals("AC"))) {

			} else {
				lst.add(this.getMsjUsuarioError(
						"El Periodo (PO) " + voucherheader.getPk().getPeriod() + " se encuentra Cerrado"));
			}

			String periodo = UFechaHora.calcularPeriodoPorFecha(new Date());
			if (!periodo.equals(period)) {

				if (parametro.equals("S")) {
					// lst.add(this.getMsjUsuarioError("El Periodo (MC) del Voucher no est\u00E1
					// activo"));
					lst.add(this.getMsjUsuarioError("El per\u00EDodo del voucher no corresponde al periodo actual"));
				}
			}

		}

		return lst;
	}

	public List<DominioMensajeUsuario> f_sql_validate_invoice_approve(SeguridadUsuarioActual usuarioActual,
			DtoComunVoucherdetail dw) throws Exception {
		dw.getTransaccionListaMensajes().addAll(voucherheaderDao.validate_invoice_approve(usuarioActual, dw));

		return dw.getTransaccionListaMensajes();
	}

	public BeanVoucherheader prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanVoucherheader voucherheader) {
		if (voucherheader == null)
			return voucherheader;
		if (voucherheader.getAuxFlgPreparadoBoolean())
			return voucherheader;
		voucherheader = prepararBasico(usuarioActual, voucherheader, false);
		voucherheader.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return voucherheader;
	}

	private BeanVoucherheader prepararBasico(SeguridadUsuarioActual usuarioActual, BeanVoucherheader voucherheader,
			Boolean flgInsertar) {
		voucherheader.setLastdate(new Date());
		voucherheader.setLastuser(usuarioActual.getUsuario());
		voucherheader.getPk().setPeriod(voucherheader.getPk().getPeriod().replace("-", ""));
		return voucherheader;
	}

}
