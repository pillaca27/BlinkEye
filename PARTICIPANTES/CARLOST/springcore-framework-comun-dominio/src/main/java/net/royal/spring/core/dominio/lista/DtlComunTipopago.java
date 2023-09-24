package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanTipopago;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.TipoPago
*/
public class DtlComunTipopago extends DominioTransaccion implements java.io.Serializable{


	private String tipopago;
	private String descripcion;
	private String restringidoflag;
	private String cartaflag;
	private String generarnumeracionflag;
	private String entregableflag;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private byte[] timestamp;
	private String estado;
	private String vouchersumarizadoflag;
	private String disponiblefeederflag;
	private String descripcioningles;
	private String codigosiaf;
	private String codigofiscal;
	private String tipopagortps;
	private String estadodescripcion;
	
	

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public String getTipopago() {
		return tipopago;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * 
	 * 
	 * @campo RestringidoFlag
	*/
	public String getRestringidoflag() {
		return restringidoflag;
	}

	/**
	 * 
	 * 
	 * @campo RestringidoFlag
	*/
	public void setRestringidoflag(String restringidoflag) {
		this.restringidoflag = restringidoflag;
	}
	/**
	 * 
	 * 
	 * @campo CartaFlag
	*/
	public String getCartaflag() {
		return cartaflag;
	}

	/**
	 * 
	 * 
	 * @campo CartaFlag
	*/
	public void setCartaflag(String cartaflag) {
		this.cartaflag = cartaflag;
	}
	/**
	 * 
	 * 
	 * @campo GenerarNumeracionFlag
	*/
	public String getGenerarnumeracionflag() {
		return generarnumeracionflag;
	}

	/**
	 * 
	 * 
	 * @campo GenerarNumeracionFlag
	*/
	public void setGenerarnumeracionflag(String generarnumeracionflag) {
		this.generarnumeracionflag = generarnumeracionflag;
	}
	/**
	 * 
	 * 
	 * @campo EntregableFlag
	*/
	public String getEntregableflag() {
		return entregableflag;
	}

	/**
	 * 
	 * 
	 * @campo EntregableFlag
	*/
	public void setEntregableflag(String entregableflag) {
		this.entregableflag = entregableflag;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public byte[] getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * 
	 * @campo Timestamp
	*/
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo VoucherSumarizadoFlag
	*/
	public String getVouchersumarizadoflag() {
		return vouchersumarizadoflag;
	}

	/**
	 * 
	 * 
	 * @campo VoucherSumarizadoFlag
	*/
	public void setVouchersumarizadoflag(String vouchersumarizadoflag) {
		this.vouchersumarizadoflag = vouchersumarizadoflag;
	}
	/**
	 * 
	 * 
	 * @campo DisponibleFeederFlag
	*/
	public String getDisponiblefeederflag() {
		return disponiblefeederflag;
	}

	/**
	 * 
	 * 
	 * @campo DisponibleFeederFlag
	*/
	public void setDisponiblefeederflag(String disponiblefeederflag) {
		this.disponiblefeederflag = disponiblefeederflag;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public String getDescripcioningles() {
		return descripcioningles;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionIngles
	*/
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	/**
	 * 
	 * 
	 * @campo CodigoSIAF
	*/
	public String getCodigosiaf() {
		return codigosiaf;
	}

	/**
	 * 
	 * 
	 * @campo CodigoSIAF
	*/
	public void setCodigosiaf(String codigosiaf) {
		this.codigosiaf = codigosiaf;
	}
	/**
	 * 
	 * 
	 * @campo CodigoFiscal
	*/
	public String getCodigofiscal() {
		return codigofiscal;
	}

	/**
	 * 
	 * 
	 * @campo CodigoFiscal
	*/
	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}
	/**
	 * 
	 * 
	 * @campo tipopagortps
	*/
	public String getTipopagortps() {
		return tipopagortps;
	}

	/**
	 * 
	 * 
	 * @campo tipopagortps
	*/
	public void setTipopagortps(String tipopagortps) {
		this.tipopagortps = tipopagortps;
	}

}
