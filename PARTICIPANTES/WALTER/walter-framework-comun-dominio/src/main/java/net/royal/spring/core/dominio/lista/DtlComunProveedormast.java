package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanProveedormast;

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
public class DtlComunProveedormast extends DominioTransaccion implements java.io.Serializable{


	private Integer proveedor;
	private String tiposervicio;
	private String tipoproveedor;
	private String pagaranombre;
	private String pagarendireccion;
	private String tipopago;
	private String monedapago;
	private String formadepago;
	private String representantelegal;
	private String responsible;
	private String cuentacontablegasto;
	private String chequenonegociableflag;
	private String suspensionrentaflag;
	private String suspensionfonaviflag;
	private String exoneradodeclaracion;
	private java.util.Date exoneradofechainicio;
	private java.util.Date exoneradofechafinal;
	private Integer numerodiaspago;
	private Integer numerodiasentrega;
	private String tipodocumentodefault;
	private String cobradortipodocumento;
	private String cobradornumerodocumento;
	private String cobradornombre;
	private String comentarioservicio;
	private java.util.Date comentariofecha;
	private String comentariousuario;
	private String clasificacionfiscal;
	private String intervencionfiscalflag;
	private String intervencionfiscaldocumento;
	private String registropublico;
	private String licenciamunicipal;
	private java.util.Date fechaconstitucion;
	private String personacontacto;
	private String experienciatiempo;
	private Integer proveedorrelacionado;
	private String detraccioncodigoflag;
	private String detraccioncodigo;
	private String detraccioncuentabancaria;
	private String exoneradofonavideclaracion;
	private java.util.Date exoneradofonavifechainicio;
	private java.util.Date exoneradofonavifechafinal;
	private String retenerpagosflag;
	private String exoneradomediopresentacion;
	private String intervencionfiscalcomentario;
	private java.util.Date intervencionfiscalfecha;
	private java.util.Date fechaautorizacionfiscal;
	private String detraccionnoafectoflag;
	private String afpretencionflag;
	private String afpcodigo;
	private String afpcuspp;
	private String afptipocomision;

	/**
	 * 
	 * 
	 * @campo Proveedor
	*/
	public Integer getProveedor() {
		return proveedor;
	}

	/**
	 * 
	 * 
	 * @campo Proveedor
	*/
	public void setProveedor(Integer proveedor) {
		this.proveedor = proveedor;
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
