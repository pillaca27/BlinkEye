package net.royal.spring.contabilidad.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.royal.spring.contabilidad.dominio.BeanVoucherheader;
import net.royal.spring.contabilidad.dominio.BeanVoucherheaderPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAccountmst;
import net.royal.spring.contabilidad.dominio.dto.DtoComunJsonVoucher;
import net.royal.spring.contabilidad.dominio.dto.DtoComunLastvouchernumber;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherMain;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherdetail;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherheader;
import net.royal.spring.contabilidad.servicio.impl.LastvouchernumberComunServicioImpl;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class VoucherheaderComunDaoImpl extends GenericoDaoImpl<BeanVoucherheader, BeanVoucherheaderPk> {

	@Autowired
	private LastvouchernumberComunServicioImpl lastvouchernumberComunServicioImpl;

	private static Logger logger = LogManager.getLogger(BeanVoucherheader.class);

	public VoucherheaderComunDaoImpl() {
		super("voucherheader");
	}

	public BeanVoucherheader coreInsertar(BeanVoucherheader bean) {
		this.registrar(bean);
		return bean;
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DtoComunLastvouchernumber f_Update_Lastvouchernumber(DtoComunVoucherheader filtro,
			SeguridadUsuarioActual usuarioActual) throws UException {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		String year = filtro.getPeriod().substring(0, 4);
		parametros.add(new DominioParametroPersistencia("p_company", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_year", String.class, year));
		parametros.add(new DominioParametroPersistencia("p_voucher_type", String.class, filtro.getVoucherno()));

		List lst = listarPorQuery(DtoComunLastvouchernumber.class, "voucherheader.f_list_Lastvouchernumber", parametros);

		DtoComunLastvouchernumber dto = new DtoComunLastvouchernumber();
		String mes = filtro.getPeriod().substring(filtro.getPeriod().length() - 2);

		if (lst.size() > 0) {
			List<DtoComunLastvouchernumber> lstVoucersNumber = lst;
			dto = lstVoucersNumber.get(0);

			dto = f_setter_mounth(dto, mes);
			dto.setCompanyowner(filtro.getCompanyowner());
			dto.setType(filtro.getVoucherno());
			dto.setYear(year);
			dto = lastvouchernumberComunServicioImpl.coreActualizar(usuarioActual, dto);

		} else {

			dto.setMonth01(0);
			dto.setMonth02(0);
			dto.setMonth03(0);
			dto.setMonth04(0);
			dto.setMonth05(0);
			dto.setMonth06(0);
			dto.setMonth07(0);
			dto.setMonth08(0);
			dto.setMonth09(0);
			dto.setMonth10(0);
			dto.setMonth11(0);
			dto.setMonth12(0);
			dto.setApplication("XX");
			dto.setCompanyowner(filtro.getCompanyowner());
			dto.setType(filtro.getVoucherno());
			dto.setYear(year);

			dto = f_setter_mounth(dto, mes);

			dto = lastvouchernumberComunServicioImpl.coreInsertar(usuarioActual, dto);
		}

		if (dto.getLimit()) {
			dto.setCodigovoucher("NO_VALID");
			return dto;
		}

		dto.setCodigovoucher(f_voucherno(dto, dto.getSecuenciames()));

		return dto;

	}

	private String f_voucherno(DtoComunLastvouchernumber dto, String mes) {
		String nro = dto.getType() + "0000";

		nro = nro.substring(0, nro.length() - mes.length());

		nro = nro + mes;
		return nro;
	}

	private DtoComunLastvouchernumber f_setter_mounth(DtoComunLastvouchernumber dto, String mes) {

		Integer dw = 0;
		switch (mes) {
		case "01":
			dw = UInteger.esCeroOrNulo(dto.getMonth01()) ? 0 : dto.getMonth01();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth01(dto.getMonth01() + 1);
			break;

		case "02":
			dw = UInteger.esCeroOrNulo(dto.getMonth02()) ? 0 : dto.getMonth02();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth02(dto.getMonth02() + 1);
			break;

		case "03":
			dw = UInteger.esCeroOrNulo(dto.getMonth03()) ? 0 : dto.getMonth03();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth03(dto.getMonth03() + 1);
			break;

		case "04":
			dw = UInteger.esCeroOrNulo(dto.getMonth04()) ? 0 : dto.getMonth04();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth04(dto.getMonth04() + 1);
			break;

		case "05":
			dw = UInteger.esCeroOrNulo(dto.getMonth05()) ? 0 : dto.getMonth05();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth05(dto.getMonth05() + 1);
			break;

		case "06":
			dw = UInteger.esCeroOrNulo(dto.getMonth06()) ? 0 : dto.getMonth06();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth06(dto.getMonth06() + 1);
			break;

		case "07":
			dw = UInteger.esCeroOrNulo(dto.getMonth07()) ? 0 : dto.getMonth07();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth07(dto.getMonth07() + 1);
			break;

		case "08":
			dw = UInteger.esCeroOrNulo(dto.getMonth08()) ? 0 : dto.getMonth08();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth08(dto.getMonth08() + 1);
			break;

		case "09":
			dw = UInteger.esCeroOrNulo(dto.getMonth09()) ? 0 : dto.getMonth09();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth09(dto.getMonth09() + 1);
			break;

		case "10":
			dw = UInteger.esCeroOrNulo(dto.getMonth10()) ? 0 : dto.getMonth10();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth10(dto.getMonth10() + 1);
			break;

		case "11":
			dw = UInteger.esCeroOrNulo(dto.getMonth11()) ? 0 : dto.getMonth11();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth11(dto.getMonth11() + 1);
			break;

		case "12":
			dw = UInteger.esCeroOrNulo(dto.getMonth12()) ? 0 : dto.getMonth12();
			if (dw + 1 > 9999) {
				dto.setLimit(true);
				break;
			}
			dto.setSecuenciames((dw + 1) + "");
			dto.setMonth12(dto.getMonth12() + 1);
			break;
		}

		return dto;
	}

	public List<DtoComunJsonVoucher> f_read_spw_ap_100_voucher(SeguridadUsuarioActual usuarioActual, DtoComunVoucherMain pk) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("i_companiasocio", String.class, pk.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("i_periodo", String.class, pk.getPeriodo()));
		parametros.add(new DominioParametroPersistencia("i_accion", String.class, "PROVISION"));
		parametros.add(new DominioParametroPersistencia("i_signo", Integer.class, 1));
		parametros
				.add(new DominioParametroPersistencia("i_usuarionumero", Integer.class, usuarioActual.getPersonaId()));
		parametros.add(new DominioParametroPersistencia("i_usuariocodigo", String.class, usuarioActual.getUsuario()));
		parametros.add(new DominioParametroPersistencia("i_jsonobligaciones", String.class, pk.getJsonobligaciones()));
		parametros
				.add(new DominioParametroPersistencia("i_cantidadregistros", Integer.class, pk.getCantidadregistros()));

		List lst = listarPorQuery(DtoComunJsonVoucher.class, "voucherheader.f_read_spw_ap_100_voucher", parametros);
		return lst;
	}

	public Integer f_read_voucher(DtoComunVoucherheader pk) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(
				new DominioParametroPersistencia("p_period", String.class, UString.esPeriodoValido(pk.getPeriod())));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_voucherno", String.class, pk.getVoucherno()));

		Integer w_count = contar("voucherheader.f_read_voucher", parametros);
		return w_count;
	}

	public String f_sql_validate_period_monthly(BeanVoucherheader filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_company", String.class, filtro.getPk().getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_ledger", String.class, filtro.getLedger()));
		List lst = listarPorQuery(DtoTabla.class, "voucherheader.f_validate_period_01", parametros);

		if (lst.size() > 0) {
			List<DtoTabla> dto = lst;
			return dto.get(0).getCodigo();
		} else {
			return "";
		}
	}

	public String f_sql_validate_period_monthly_indicador(BeanVoucherheader filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_company", String.class, filtro.getPk().getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_ledger", String.class, filtro.getLedger()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, filtro.getPk().getPeriod()));
		List lst = listarPorQuery(DtoTabla.class, "voucherheader.f_validate_period_02", parametros);

		if (lst.size() > 0) {
			List<DtoTabla> dto = lst;
			return dto.get(0).getCodigo();
		} else {
			return "NOT";
		}

	}

	public DtoComunVoucherdetail f_account_validate(DtoComunVoucherdetail dto) {

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_account", String.class, dto.getAccount()));
		List accounts = listarPorQuery(DtoComunAccountmst.class, "voucherheader.f_read_account_valid", parametros);

		String var = "";

		if (accounts.size() > 0) {
			DtoComunAccountmst account_validate = (DtoComunAccountmst) accounts.get(0);

			var = UString.esNuloVacio(account_validate.getCostcentervalidation()) ? "N"
					: account_validate.getCostcentervalidation();
			dto.setCostcenter(var.equals("N") ? null : dto.getCostcenter());

			var = UString.esNuloVacio(account_validate.getAfevalidation()) ? "N" : account_validate.getAfevalidation();
			dto.setAfe(var.equals("N") ? null : dto.getAfe());

			var = UString.esNuloVacio(account_validate.getCommitmentvalidation()) ? "N"
					: account_validate.getCommitmentvalidation();
			dto.setCommitment(var.equals("N") ? null : dto.getCommitment());

			var = UString.esNuloVacio(account_validate.getVendorvalidation()) ? "N"
					: account_validate.getVendorvalidation();
			dto.setVendor(var.equals("N") ? null : dto.getVendor());

			var = UString.esNuloVacio(account_validate.getInvoicevalidation()) ? "N"
					: account_validate.getInvoicevalidation();
			dto.setInvoice(var.equals("N") ? null : dto.getInvoice());

			var = UString.esNuloVacio(account_validate.getCheckvalidation()) ? "N"
					: account_validate.getCheckvalidation();
			dto.setChecknumber(var.equals("N") ? null : dto.getChecknumber());

			var = UString.esNuloVacio(account_validate.getDatevalidation()) ? "N"
					: account_validate.getDatevalidation();
			dto.setChecknumber(var.equals("N") ? null : dto.getChecknumber());

			var = UString.esNuloVacio(account_validate.getSubledgervalidation()) ? "N"
					: account_validate.getSubledgervalidation();
			dto.setSubledgercode(var.equals("N") ? null : dto.getSubledgercode());

			var = UString.esNuloVacio(account_validate.getCostcenterdestvalidation()) ? "N"
					: account_validate.getCostcenterdestvalidation();
			dto.setCostcenterdestination(var.equals("N") ? null : dto.getCostcenterdestination());

			var = UString.esNuloVacio(account_validate.getIntercompanyvalidation()) ? "N"
					: account_validate.getIntercompanyvalidation();
			dto.setCompanyowner(var.equals("N") ? null : dto.getCompanyowner());

		}

		return dto;
	}
	
	public List<DominioMensajeUsuario> validate_invoice_approve(SeguridadUsuarioActual usuarioActual,DtoComunVoucherdetail pk) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("w_companyowner", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("w_period", String.class, pk.getPeriod()));
		parametros.add(new DominioParametroPersistencia("w_voucherno", String.class, pk.getVoucherno()));
		parametros.add(new DominioParametroPersistencia("w_voucherline", Integer.class, pk.getVoucherline()));
		parametros.add(new DominioParametroPersistencia("w_account", String.class, pk.getAccount()));
		parametros.add(new DominioParametroPersistencia("w_dollaramount", BigDecimal.class, pk.getDollaramount()));
		parametros.add(new DominioParametroPersistencia("w_localamount", BigDecimal.class, pk.getLocalamount()));
		parametros.add(new DominioParametroPersistencia("w_afe", String.class, pk.getAfe()));
		parametros.add(new DominioParametroPersistencia("w_vendor", Integer.class, pk.getVendor()));
		parametros.add(new DominioParametroPersistencia("w_commitment", String.class, pk.getCommitment()));
		parametros.add(new DominioParametroPersistencia("w_invoice", String.class, pk.getInvoice()));
		parametros.add(new DominioParametroPersistencia("w_checknumber", String.class, pk.getChecknumber()));
		parametros.add(new DominioParametroPersistencia("w_destinationcompanyowner", String.class, pk.getDestinationcompanyowner()));
		parametros.add(new DominioParametroPersistencia("w_variabledate", Date.class,pk.getVariabledate()));
		parametros.add(new DominioParametroPersistencia("w_subledgercode", String.class, pk.getSubledgercode()));
		parametros.add(new DominioParametroPersistencia("w_costcenter", String.class, pk.getCostcenter()));
		parametros.add(new DominioParametroPersistencia("w_costcenterdestination", String.class, pk.getCostcenterdestination()));
		parametros.add(new DominioParametroPersistencia("w_cashflowcode", String.class, pk.getCashflowcode()));
		parametros.add(new DominioParametroPersistencia("w_sucursal", String.class, pk.getSucursal()));
		parametros.add(new DominioParametroPersistencia("w_referenciafiscal01", String.class, pk.getReferenciafiscal01()));
		parametros.add(new DominioParametroPersistencia("w_referenciafiscal02", String.class, pk.getReferenciafiscal02()));
		parametros.add(new DominioParametroPersistencia("w_referenciafiscal03", String.class, pk.getReferenciafiscal03()));
		
		parametros.add(new DominioParametroPersistencia("w_code_data_01", String.class, pk.getVariablecode01()));
		parametros.add(new DominioParametroPersistencia("w_code_data_02", String.class, pk.getVariablecode02()));
		parametros.add(new DominioParametroPersistencia("w_code_data_03", String.class, pk.getVariablecode03()));
		parametros.add(new DominioParametroPersistencia("w_code_data_04", String.class, pk.getVariablecode04()));
		parametros.add(new DominioParametroPersistencia("w_code_data_05", String.class, pk.getVariablecode05()));
		parametros.add(new DominioParametroPersistencia("w_code_data_06", String.class, pk.getVariablecode06()));
		parametros.add(new DominioParametroPersistencia("w_code_data_07", String.class, pk.getVariablecode07()));
		parametros.add(new DominioParametroPersistencia("w_code_data_08", String.class, pk.getVariablecode08()));
		parametros.add(new DominioParametroPersistencia("w_code_data_09", String.class, pk.getVariablecode09()));
		parametros.add(new DominioParametroPersistencia("w_code_data_10", String.class, pk.getVariablecode10()));


		List lst = listarPorQuery(DominioMensajeUsuario.class, "voucherheader.spw_ac_040_voucher_validation_lines", parametros);		
		return lst;
	}
	
	public List<DominioMensajeUsuario> uf_voucher_posting (BeanVoucherheader filtro, String usuario) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_company", String.class, filtro.getPk().getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class, filtro.getPk().getPeriod()));
		parametros.add(new DominioParametroPersistencia("p_voucher", String.class, filtro.getPk().getVoucherno()));
		parametros.add(new DominioParametroPersistencia("p_accion", String.class, filtro.getAuxAccion()));
		
		List lst = listarPorQuery(DominioMensajeUsuario.class, "voucherheader.voucher_posting", parametros);
		return lst;
	}
}
