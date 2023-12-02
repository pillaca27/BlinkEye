package net.royal.spring.contabilidad.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunVoucherMain extends DominioTransaccion implements java.io.Serializable {

	private List<DtoComunVoucherInterface> dw = new ArrayList<DtoComunVoucherInterface>();
	private List<DtoComunVoucherError> dwerror = new ArrayList<DtoComunVoucherError>();
	private Boolean btnaceptar;
	private String accion;
	private Integer count;
	private Integer total;
	private String voucher;

	private String companiasocio;
	private String periodo;
	private Integer signo;
	private String jsonobligaciones;
	private String sourcekey;
	private Integer cantidadregistros;
	private List<DtoComunJsonVoucherObligaciones> lstJson = new ArrayList<DtoComunJsonVoucherObligaciones>();

	// parametros
	private String parametroperiod;
	private String parametrovoulindes;
	private String parametrovougenerr;
	private String parametrovouchpost;

	public String getParametrovouchpost() {
		return parametrovouchpost;
	}

	public void setParametrovouchpost(String parametrovouchpost) {
		this.parametrovouchpost = parametrovouchpost;
	}

	public String getSourcekey() {
		return sourcekey;
	}

	public void setSourcekey(String sourcekey) {
		this.sourcekey = sourcekey;
	}

	public String getParametroperiod() {
		return parametroperiod;
	}

	public void setParametroperiod(String parametroperiod) {
		this.parametroperiod = parametroperiod;
	}

	public String getParametrovoulindes() {
		return parametrovoulindes;
	}

	public void setParametrovoulindes(String parametrovoulindes) {
		this.parametrovoulindes = parametrovoulindes;
	}

	public String getParametrovougenerr() {
		return parametrovougenerr;
	}

	public void setParametrovougenerr(String parametrovougenerr) {
		this.parametrovougenerr = parametrovougenerr;
	}

	public List<DtoComunJsonVoucherObligaciones> getLstJson() {
		return lstJson;
	}

	public void setLstJson(List<DtoComunJsonVoucherObligaciones> lstJson) {
		this.lstJson = lstJson;
	}

	public String getCompaniasocio() {
		return companiasocio;
	}

	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Integer getSigno() {
		return signo;
	}

	public void setSigno(Integer signo) {
		this.signo = signo;
	}

	public String getJsonobligaciones() {
		return jsonobligaciones;
	}

	public void setJsonobligaciones(String jsonobligaciones) {
		this.jsonobligaciones = jsonobligaciones;
	}

	public Integer getCantidadregistros() {
		return cantidadregistros;
	}

	public void setCantidadregistros(Integer cantidadregistros) {
		this.cantidadregistros = cantidadregistros;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getAccion() {
		if (accion == null)
			return "N";
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Boolean getBtnaceptar() {
		return btnaceptar;
	}

	public void setBtnaceptar(Boolean btnaceptar) {
		this.btnaceptar = btnaceptar;
	}

	public List<DtoComunVoucherInterface> getDw() {
		return dw;
	}

	public void setDw(List<DtoComunVoucherInterface> dw) {
		this.dw = dw;
	}

	public List<DtoComunVoucherError> getDwerror() {
		return dwerror;
	}

	public void setDwerror(List<DtoComunVoucherError> dwerror) {
		this.dwerror = dwerror;
	}

}
