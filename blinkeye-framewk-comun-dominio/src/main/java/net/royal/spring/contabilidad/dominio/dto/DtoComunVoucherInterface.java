package net.royal.spring.contabilidad.dominio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunVoucherInterface extends DominioTransaccion implements java.io.Serializable {

	private String periodo;
	private String compania;
	private String voucher;
	private Date fecha;
	private String status;
	private Integer errores;
	private Integer lineas;
	private String selected;
	private DtoComunVoucherheader dwvoucher;
	private List<DtoComunVoucherError> dwerror = new ArrayList<DtoComunVoucherError>();
	private String accion;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getStatus() {
		if (status == null)
			return "AP";
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getErrores() {
		return errores;
	}

	public void setErrores(Integer errores) {
		this.errores = errores;
	}

	public Integer getLineas() {
		return lineas;
	}

	public void setLineas(Integer lineas) {
		this.lineas = lineas;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public List<DtoComunVoucherError> getDwerror() {
		return dwerror;
	}

	public void setDwerror(List<DtoComunVoucherError> dwerror) {
		this.dwerror = dwerror;
	}

	public DtoComunVoucherheader getDwvoucher() {
		return dwvoucher;
	}

	public void setDwvoucher(DtoComunVoucherheader dwvoucher) {
		this.dwvoucher = dwvoucher;
	}

}
