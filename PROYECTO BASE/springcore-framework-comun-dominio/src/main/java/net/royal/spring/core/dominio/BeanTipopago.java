package net.royal.spring.core.dominio;

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
@Entity
@Table(name = "TIPOPAGO")
public class BeanTipopago extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private TipopagoPk pk;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCION", length = 40, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 1)
	@Column(name = "RESTRINGIDOFLAG", length = 1, nullable = true)
	private String restringidoflag;

	@Size(min = 0, max = 1)
	@Column(name = "CARTAFLAG", length = 1, nullable = true)
	private String cartaflag;

	@Size(min = 0, max = 1)
	@Column(name = "GENERARNUMERACIONFLAG", length = 1, nullable = true)
	private String generarnumeracionflag;

	@Size(min = 0, max = 1)
	@Column(name = "ENTREGABLEFLAG", length = 1, nullable = true)
	private String entregableflag;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;


	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 1)
	@Column(name = "VOUCHERSUMARIZADOFLAG", length = 1, nullable = true)
	private String vouchersumarizadoflag;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLEFEEDERFLAG", length = 1, nullable = true)
	private String disponiblefeederflag;

	@Size(min = 0, max = 40)
	@Column(name = "DESCRIPCIONINGLES", length = 40, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOSIAF", length = 3, nullable = true)
	private String codigosiaf;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOFISCAL", length = 3, nullable = true)
	private String codigofiscal;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOPAGORTPS", length = 1, nullable = true)
	private String tipopagortps;


	public BeanTipopago() {
		pk = new TipopagoPk();
	}


	public BeanTipopago(TipopagoPk pk) {
		this.pk = pk;
	}

	public TipopagoPk getPk() {
		return pk;
	}

	public void setPk(TipopagoPk pk) {
		this.pk = pk;
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
