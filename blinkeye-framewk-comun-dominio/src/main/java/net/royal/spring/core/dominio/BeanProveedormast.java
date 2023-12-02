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
 * @tabla dbo.ProveedorMast
*/
@Entity
@Table(name = "PROVEEDORMAST")
public class BeanProveedormast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanProveedormastPk pk;

	@Size(min = 0, max = 6)
	@Column(name = "TIPOSERVICIO", length = 6, nullable = true)
	private String tiposervicio;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOPROVEEDOR", length = 1, nullable = true)
	private String tipoproveedor;

	@Size(min = 0, max = 70)
	@Column(name = "PAGARANOMBRE", length = 70, nullable = true)
	private String pagaranombre;

	@Size(min = 0, max = 80)
	@Column(name = "PAGARENDIRECCION", length = 80, nullable = true)
	private String pagarendireccion;

	@Size(min = 0, max = 2)
	@Column(name = "TIPOPAGO", length = 2, nullable = true)
	private String tipopago;

	@Size(min = 0, max = 2)
	@Column(name = "MONEDAPAGO", length = 2, nullable = true)
	private String monedapago;

	@Size(min = 0, max = 3)
	@Column(name = "FORMADEPAGO", length = 3, nullable = true)
	private String formadepago;

	@Size(min = 0, max = 50)
	@Column(name = "REPRESENTANTELEGAL", length = 50, nullable = true)
	private String representantelegal;

	@Size(min = 0, max = 4)
	@Column(name = "RESPONSIBLE", length = 4, nullable = true)
	private String responsible;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTACONTABLEGASTO", length = 20, nullable = true)
	private String cuentacontablegasto;

	@Size(min = 0, max = 1)
	@Column(name = "CHEQUENONEGOCIABLEFLAG", length = 1, nullable = true)
	private String chequenonegociableflag;

	@Size(min = 0, max = 1)
	@Column(name = "SUSPENSIONRENTAFLAG", length = 1, nullable = true)
	private String suspensionrentaflag;

	@Size(min = 0, max = 1)
	@Column(name = "SUSPENSIONFONAVIFLAG", length = 1, nullable = true)
	private String suspensionfonaviflag;

	@Size(min = 0, max = 15)
	@Column(name = "EXONERADODECLARACION", length = 15, nullable = true)
	private String exoneradodeclaracion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EXONERADOFECHAINICIO", nullable = true)
	private java.util.Date exoneradofechainicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EXONERADOFECHAFINAL", nullable = true)
	private java.util.Date exoneradofechafinal;

	@Column(name = "NUMERODIASPAGO", nullable = true)
	private Integer numerodiaspago;

	@Column(name = "NUMERODIASENTREGA", nullable = true)
	private Integer numerodiasentrega;

	@Size(min = 0, max = 2)
	@Column(name = "TIPODOCUMENTODEFAULT", length = 2, nullable = true)
	private String tipodocumentodefault;

	@Size(min = 0, max = 1)
	@Column(name = "COBRADORTIPODOCUMENTO", length = 1, nullable = true)
	private String cobradortipodocumento;

	@Size(min = 0, max = 10)
	@Column(name = "COBRADORNUMERODOCUMENTO", length = 10, nullable = true)
	private String cobradornumerodocumento;

	@Size(min = 0, max = 60)
	@Column(name = "COBRADORNOMBRE", length = 60, nullable = true)
	private String cobradornombre;

	@Size(min = 0, max = 100)
	@Column(name = "COMENTARIOSERVICIO", length = 100, nullable = true)
	private String comentarioservicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "COMENTARIOFECHA", nullable = true)
	private java.util.Date comentariofecha;

	@Size(min = 0, max = 20)
	@Column(name = "COMENTARIOUSUARIO", length = 20, nullable = true)
	private String comentariousuario;

	@Size(min = 0, max = 4)
	@Column(name = "CLASIFICACIONFISCAL", length = 4, nullable = true)
	private String clasificacionfiscal;

	@Size(min = 0, max = 1)
	@Column(name = "INTERVENCIONFISCALFLAG", length = 1, nullable = true)
	private String intervencionfiscalflag;

	@Size(min = 0, max = 30)
	@Column(name = "INTERVENCIONFISCALDOCUMENTO", length = 30, nullable = true)
	private String intervencionfiscaldocumento;

	@Size(min = 0, max = 20)
	@Column(name = "REGISTROPUBLICO", length = 20, nullable = true)
	private String registropublico;

	@Size(min = 0, max = 20)
	@Column(name = "LICENCIAMUNICIPAL", length = 20, nullable = true)
	private String licenciamunicipal;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACONSTITUCION", nullable = true)
	private java.util.Date fechaconstitucion;

	@Size(min = 0, max = 50)
	@Column(name = "PERSONACONTACTO", length = 50, nullable = true)
	private String personacontacto;

	@Size(min = 0, max = 20)
	@Column(name = "EXPERIENCIATIEMPO", length = 20, nullable = true)
	private String experienciatiempo;

	@Column(name = "PROVEEDORRELACIONADO", nullable = true)
	private Integer proveedorrelacionado;

	@Size(min = 0, max = 1)
	@Column(name = "DETRACCIONCODIGOFLAG", length = 1, nullable = true)
	private String detraccioncodigoflag;

	@Size(min = 0, max = 4)
	@Column(name = "DETRACCIONCODIGO", length = 4, nullable = true)
	private String detraccioncodigo;

	@Size(min = 0, max = 20)
	@Column(name = "DETRACCIONCUENTABANCARIA", length = 20, nullable = true)
	private String detraccioncuentabancaria;

	@Size(min = 0, max = 50)
	@Column(name = "EXONERADOFONAVIDECLARACION", length = 50, nullable = true)
	private String exoneradofonavideclaracion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EXONERADOFONAVIFECHAINICIO", nullable = true)
	private java.util.Date exoneradofonavifechainicio;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EXONERADOFONAVIFECHAFINAL", nullable = true)
	private java.util.Date exoneradofonavifechafinal;

	@Size(min = 0, max = 1)
	@Column(name = "RETENERPAGOSFLAG", length = 1, nullable = true)
	private String retenerpagosflag;

	@Size(min = 0, max = 1)
	@Column(name = "EXONERADOMEDIOPRESENTACION", length = 1, nullable = true)
	private String exoneradomediopresentacion;

	@Size(min = 0, max = 100)
	@Column(name = "INTERVENCIONFISCALCOMENTARIO", length = 100, nullable = true)
	private String intervencionfiscalcomentario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "INTERVENCIONFISCALFECHA", nullable = true)
	private java.util.Date intervencionfiscalfecha;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAAUTORIZACIONFISCAL", nullable = true)
	private java.util.Date fechaautorizacionfiscal;

	@Size(min = 0, max = 1)
	@Column(name = "DETRACCIONNOAFECTOFLAG", length = 1, nullable = true)
	private String detraccionnoafectoflag;

	@Size(min = 0, max = 1)
	@Column(name = "AFPRETENCIONFLAG", length = 1, nullable = true)
	private String afpretencionflag;

	@Size(min = 0, max = 3)
	@Column(name = "AFPCODIGO", length = 3, nullable = true)
	private String afpcodigo;

	@Size(min = 0, max = 20)
	@Column(name = "AFPCUSPP", length = 20, nullable = true)
	private String afpcuspp;

	@Size(min = 0, max = 1)
	@Column(name = "AFPTIPOCOMISION", length = 1, nullable = true)
	private String afptipocomision;


	public BeanProveedormast() {
		pk = new BeanProveedormastPk();
	}


	public BeanProveedormast(BeanProveedormastPk pk) {
		this.pk = pk;
	}

	public BeanProveedormastPk getPk() {
		return pk;
	}

	public void setPk(BeanProveedormastPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public String getTiposervicio() {
		return tiposervicio;
	}

	/**
	 * 
	 * 
	 * @campo TipoServicio
	*/
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	/**
	 * 
	 * 
	 * @campo TipoProveedor
	*/
	public String getTipoproveedor() {
		return tipoproveedor;
	}

	/**
	 * 
	 * 
	 * @campo TipoProveedor
	*/
	public void setTipoproveedor(String tipoproveedor) {
		this.tipoproveedor = tipoproveedor;
	}
	/**
	 * 
	 * 
	 * @campo PagarANombre
	*/
	public String getPagaranombre() {
		return pagaranombre;
	}

	/**
	 * 
	 * 
	 * @campo PagarANombre
	*/
	public void setPagaranombre(String pagaranombre) {
		this.pagaranombre = pagaranombre;
	}
	/**
	 * 
	 * 
	 * @campo PagarEnDireccion
	*/
	public String getPagarendireccion() {
		return pagarendireccion;
	}

	/**
	 * 
	 * 
	 * @campo PagarEnDireccion
	*/
	public void setPagarendireccion(String pagarendireccion) {
		this.pagarendireccion = pagarendireccion;
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
	 * @campo MonedaPago
	*/
	public String getMonedapago() {
		return monedapago;
	}

	/**
	 * 
	 * 
	 * @campo MonedaPago
	*/
	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}
	/**
	 * 
	 * 
	 * @campo FormadePago
	*/
	public String getFormadepago() {
		return formadepago;
	}

	/**
	 * 
	 * 
	 * @campo FormadePago
	*/
	public void setFormadepago(String formadepago) {
		this.formadepago = formadepago;
	}
	/**
	 * 
	 * 
	 * @campo RepresentanteLegal
	*/
	public String getRepresentantelegal() {
		return representantelegal;
	}

	/**
	 * 
	 * 
	 * @campo RepresentanteLegal
	*/
	public void setRepresentantelegal(String representantelegal) {
		this.representantelegal = representantelegal;
	}
	/**
	 * 
	 * 
	 * @campo responsible
	*/
	public String getResponsible() {
		return responsible;
	}

	/**
	 * 
	 * 
	 * @campo responsible
	*/
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	/**
	 * 
	 * 
	 * @campo CuentaContableGasto
	*/
	public String getCuentacontablegasto() {
		return cuentacontablegasto;
	}

	/**
	 * 
	 * 
	 * @campo CuentaContableGasto
	*/
	public void setCuentacontablegasto(String cuentacontablegasto) {
		this.cuentacontablegasto = cuentacontablegasto;
	}
	/**
	 * 
	 * 
	 * @campo ChequeNoNegociableFlag
	*/
	public String getChequenonegociableflag() {
		return chequenonegociableflag;
	}

	/**
	 * 
	 * 
	 * @campo ChequeNoNegociableFlag
	*/
	public void setChequenonegociableflag(String chequenonegociableflag) {
		this.chequenonegociableflag = chequenonegociableflag;
	}
	/**
	 * 
	 * 
	 * @campo SuspensionRentaFlag
	*/
	public String getSuspensionrentaflag() {
		return suspensionrentaflag;
	}

	/**
	 * 
	 * 
	 * @campo SuspensionRentaFlag
	*/
	public void setSuspensionrentaflag(String suspensionrentaflag) {
		this.suspensionrentaflag = suspensionrentaflag;
	}
	/**
	 * 
	 * 
	 * @campo SuspensionFonaviFlag
	*/
	public String getSuspensionfonaviflag() {
		return suspensionfonaviflag;
	}

	/**
	 * 
	 * 
	 * @campo SuspensionFonaviFlag
	*/
	public void setSuspensionfonaviflag(String suspensionfonaviflag) {
		this.suspensionfonaviflag = suspensionfonaviflag;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoDeclaracion
	*/
	public String getExoneradodeclaracion() {
		return exoneradodeclaracion;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoDeclaracion
	*/
	public void setExoneradodeclaracion(String exoneradodeclaracion) {
		this.exoneradodeclaracion = exoneradodeclaracion;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoFechaInicio
	*/
	public java.util.Date getExoneradofechainicio() {
		return exoneradofechainicio;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoFechaInicio
	*/
	public void setExoneradofechainicio(java.util.Date exoneradofechainicio) {
		this.exoneradofechainicio = exoneradofechainicio;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoFechaFinal
	*/
	public java.util.Date getExoneradofechafinal() {
		return exoneradofechafinal;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoFechaFinal
	*/
	public void setExoneradofechafinal(java.util.Date exoneradofechafinal) {
		this.exoneradofechafinal = exoneradofechafinal;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDiasPago
	*/
	public Integer getNumerodiaspago() {
		return numerodiaspago;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDiasPago
	*/
	public void setNumerodiaspago(Integer numerodiaspago) {
		this.numerodiaspago = numerodiaspago;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDiasEntrega
	*/
	public Integer getNumerodiasentrega() {
		return numerodiasentrega;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDiasEntrega
	*/
	public void setNumerodiasentrega(Integer numerodiasentrega) {
		this.numerodiasentrega = numerodiasentrega;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumentoDefault
	*/
	public String getTipodocumentodefault() {
		return tipodocumentodefault;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumentoDefault
	*/
	public void setTipodocumentodefault(String tipodocumentodefault) {
		this.tipodocumentodefault = tipodocumentodefault;
	}
	/**
	 * 
	 * 
	 * @campo CobradorTipoDocumento
	*/
	public String getCobradortipodocumento() {
		return cobradortipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo CobradorTipoDocumento
	*/
	public void setCobradortipodocumento(String cobradortipodocumento) {
		this.cobradortipodocumento = cobradortipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo CobradorNumeroDocumento
	*/
	public String getCobradornumerodocumento() {
		return cobradornumerodocumento;
	}

	/**
	 * 
	 * 
	 * @campo CobradorNumeroDocumento
	*/
	public void setCobradornumerodocumento(String cobradornumerodocumento) {
		this.cobradornumerodocumento = cobradornumerodocumento;
	}
	/**
	 * 
	 * 
	 * @campo CobradorNombre
	*/
	public String getCobradornombre() {
		return cobradornombre;
	}

	/**
	 * 
	 * 
	 * @campo CobradorNombre
	*/
	public void setCobradornombre(String cobradornombre) {
		this.cobradornombre = cobradornombre;
	}
	/**
	 * 
	 * 
	 * @campo ComentarioServicio
	*/
	public String getComentarioservicio() {
		return comentarioservicio;
	}

	/**
	 * 
	 * 
	 * @campo ComentarioServicio
	*/
	public void setComentarioservicio(String comentarioservicio) {
		this.comentarioservicio = comentarioservicio;
	}
	/**
	 * 
	 * 
	 * @campo ComentarioFecha
	*/
	public java.util.Date getComentariofecha() {
		return comentariofecha;
	}

	/**
	 * 
	 * 
	 * @campo ComentarioFecha
	*/
	public void setComentariofecha(java.util.Date comentariofecha) {
		this.comentariofecha = comentariofecha;
	}
	/**
	 * 
	 * 
	 * @campo ComentarioUsuario
	*/
	public String getComentariousuario() {
		return comentariousuario;
	}

	/**
	 * 
	 * 
	 * @campo ComentarioUsuario
	*/
	public void setComentariousuario(String comentariousuario) {
		this.comentariousuario = comentariousuario;
	}
	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public String getClasificacionfiscal() {
		return clasificacionfiscal;
	}

	/**
	 * 
	 * 
	 * @campo ClasificacionFiscal
	*/
	public void setClasificacionfiscal(String clasificacionfiscal) {
		this.clasificacionfiscal = clasificacionfiscal;
	}
	/**
	 * 
	 * 
	 * @campo IntervencionFiscalFlag
	*/
	public String getIntervencionfiscalflag() {
		return intervencionfiscalflag;
	}

	/**
	 * 
	 * 
	 * @campo IntervencionFiscalFlag
	*/
	public void setIntervencionfiscalflag(String intervencionfiscalflag) {
		this.intervencionfiscalflag = intervencionfiscalflag;
	}
	/**
	 * 
	 * 
	 * @campo IntervencionFiscalDocumento
	*/
	public String getIntervencionfiscaldocumento() {
		return intervencionfiscaldocumento;
	}

	/**
	 * 
	 * 
	 * @campo IntervencionFiscalDocumento
	*/
	public void setIntervencionfiscaldocumento(String intervencionfiscaldocumento) {
		this.intervencionfiscaldocumento = intervencionfiscaldocumento;
	}
	/**
	 * 
	 * 
	 * @campo RegistroPublico
	*/
	public String getRegistropublico() {
		return registropublico;
	}

	/**
	 * 
	 * 
	 * @campo RegistroPublico
	*/
	public void setRegistropublico(String registropublico) {
		this.registropublico = registropublico;
	}
	/**
	 * 
	 * 
	 * @campo LicenciaMunicipal
	*/
	public String getLicenciamunicipal() {
		return licenciamunicipal;
	}

	/**
	 * 
	 * 
	 * @campo LicenciaMunicipal
	*/
	public void setLicenciamunicipal(String licenciamunicipal) {
		this.licenciamunicipal = licenciamunicipal;
	}
	/**
	 * 
	 * 
	 * @campo FechaConstitucion
	*/
	public java.util.Date getFechaconstitucion() {
		return fechaconstitucion;
	}

	/**
	 * 
	 * 
	 * @campo FechaConstitucion
	*/
	public void setFechaconstitucion(java.util.Date fechaconstitucion) {
		this.fechaconstitucion = fechaconstitucion;
	}
	/**
	 * 
	 * 
	 * @campo PersonaContacto
	*/
	public String getPersonacontacto() {
		return personacontacto;
	}

	/**
	 * 
	 * 
	 * @campo PersonaContacto
	*/
	public void setPersonacontacto(String personacontacto) {
		this.personacontacto = personacontacto;
	}
	/**
	 * 
	 * 
	 * @campo ExperienciaTiempo
	*/
	public String getExperienciatiempo() {
		return experienciatiempo;
	}

	/**
	 * 
	 * 
	 * @campo ExperienciaTiempo
	*/
	public void setExperienciatiempo(String experienciatiempo) {
		this.experienciatiempo = experienciatiempo;
	}
	/**
	 * 
	 * 
	 * @campo ProveedorRelacionado
	*/
	public Integer getProveedorrelacionado() {
		return proveedorrelacionado;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorRelacionado
	*/
	public void setProveedorrelacionado(Integer proveedorrelacionado) {
		this.proveedorrelacionado = proveedorrelacionado;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCodigoFlag
	*/
	public String getDetraccioncodigoflag() {
		return detraccioncodigoflag;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCodigoFlag
	*/
	public void setDetraccioncodigoflag(String detraccioncodigoflag) {
		this.detraccioncodigoflag = detraccioncodigoflag;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCodigo
	*/
	public String getDetraccioncodigo() {
		return detraccioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCodigo
	*/
	public void setDetraccioncodigo(String detraccioncodigo) {
		this.detraccioncodigo = detraccioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionCuentaBancaria
	*/
	public String getDetraccioncuentabancaria() {
		return detraccioncuentabancaria;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionCuentaBancaria
	*/
	public void setDetraccioncuentabancaria(String detraccioncuentabancaria) {
		this.detraccioncuentabancaria = detraccioncuentabancaria;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviDeclaracion
	*/
	public String getExoneradofonavideclaracion() {
		return exoneradofonavideclaracion;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviDeclaracion
	*/
	public void setExoneradofonavideclaracion(String exoneradofonavideclaracion) {
		this.exoneradofonavideclaracion = exoneradofonavideclaracion;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviFechaInicio
	*/
	public java.util.Date getExoneradofonavifechainicio() {
		return exoneradofonavifechainicio;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviFechaInicio
	*/
	public void setExoneradofonavifechainicio(java.util.Date exoneradofonavifechainicio) {
		this.exoneradofonavifechainicio = exoneradofonavifechainicio;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviFechaFinal
	*/
	public java.util.Date getExoneradofonavifechafinal() {
		return exoneradofonavifechafinal;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoFonaviFechaFinal
	*/
	public void setExoneradofonavifechafinal(java.util.Date exoneradofonavifechafinal) {
		this.exoneradofonavifechafinal = exoneradofonavifechafinal;
	}
	/**
	 * 
	 * 
	 * @campo RetenerPagosFlag
	*/
	public String getRetenerpagosflag() {
		return retenerpagosflag;
	}

	/**
	 * 
	 * 
	 * @campo RetenerPagosFlag
	*/
	public void setRetenerpagosflag(String retenerpagosflag) {
		this.retenerpagosflag = retenerpagosflag;
	}
	/**
	 * 
	 * 
	 * @campo ExoneradoMedioPresentacion
	*/
	public String getExoneradomediopresentacion() {
		return exoneradomediopresentacion;
	}

	/**
	 * 
	 * 
	 * @campo ExoneradoMedioPresentacion
	*/
	public void setExoneradomediopresentacion(String exoneradomediopresentacion) {
		this.exoneradomediopresentacion = exoneradomediopresentacion;
	}
	/**
	 * 
	 * 
	 * @campo IntervencionFiscalComentario
	*/
	public String getIntervencionfiscalcomentario() {
		return intervencionfiscalcomentario;
	}

	/**
	 * 
	 * 
	 * @campo IntervencionFiscalComentario
	*/
	public void setIntervencionfiscalcomentario(String intervencionfiscalcomentario) {
		this.intervencionfiscalcomentario = intervencionfiscalcomentario;
	}
	/**
	 * 
	 * 
	 * @campo IntervencionFiscalFecha
	*/
	public java.util.Date getIntervencionfiscalfecha() {
		return intervencionfiscalfecha;
	}

	/**
	 * 
	 * 
	 * @campo IntervencionFiscalFecha
	*/
	public void setIntervencionfiscalfecha(java.util.Date intervencionfiscalfecha) {
		this.intervencionfiscalfecha = intervencionfiscalfecha;
	}
	/**
	 * 
	 * 
	 * @campo FechaAutorizacionFiscal
	*/
	public java.util.Date getFechaautorizacionfiscal() {
		return fechaautorizacionfiscal;
	}

	/**
	 * 
	 * 
	 * @campo FechaAutorizacionFiscal
	*/
	public void setFechaautorizacionfiscal(java.util.Date fechaautorizacionfiscal) {
		this.fechaautorizacionfiscal = fechaautorizacionfiscal;
	}
	/**
	 * 
	 * 
	 * @campo DetraccionNoAfectoFlag
	*/
	public String getDetraccionnoafectoflag() {
		return detraccionnoafectoflag;
	}

	/**
	 * 
	 * 
	 * @campo DetraccionNoAfectoFlag
	*/
	public void setDetraccionnoafectoflag(String detraccionnoafectoflag) {
		this.detraccionnoafectoflag = detraccionnoafectoflag;
	}
	/**
	 * 
	 * 
	 * @campo AFPRetencionFlag
	*/
	public String getAfpretencionflag() {
		return afpretencionflag;
	}

	/**
	 * 
	 * 
	 * @campo AFPRetencionFlag
	*/
	public void setAfpretencionflag(String afpretencionflag) {
		this.afpretencionflag = afpretencionflag;
	}
	/**
	 * 
	 * 
	 * @campo AFPCodigo
	*/
	public String getAfpcodigo() {
		return afpcodigo;
	}

	/**
	 * 
	 * 
	 * @campo AFPCodigo
	*/
	public void setAfpcodigo(String afpcodigo) {
		this.afpcodigo = afpcodigo;
	}
	/**
	 * 
	 * 
	 * @campo AFPCUSPP
	*/
	public String getAfpcuspp() {
		return afpcuspp;
	}

	/**
	 * 
	 * 
	 * @campo AFPCUSPP
	*/
	public void setAfpcuspp(String afpcuspp) {
		this.afpcuspp = afpcuspp;
	}
	/**
	 * 
	 * 
	 * @campo AFPTipoComision
	*/
	public String getAfptipocomision() {
		return afptipocomision;
	}

	/**
	 * 
	 * 
	 * @campo AFPTipoComision
	*/
	public void setAfptipocomision(String afptipocomision) {
		this.afptipocomision = afptipocomision;
	}

}
