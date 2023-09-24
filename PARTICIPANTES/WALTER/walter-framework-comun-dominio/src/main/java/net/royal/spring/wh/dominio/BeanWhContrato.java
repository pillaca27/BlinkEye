package net.royal.spring.wh.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import net.royal.spring.sy.dominio.dto.DtoComunSyDocumentoanexos;

/**
 * 
 * 
 * @tabla dbo.WH_Contrato
*/
@Entity
@Table(name = "WH_CONTRATO")
public class BeanWhContrato extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhContratoPk pk;

	

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTOINTERNO", length = 20, nullable = true)
	private String documentointerno;

	@Size(min = 0, max = 4)
	@Column(name = "UNIDADNEGOCIO", length = 4, nullable = true)
	private String unidadnegocio;

	@Size(min = 0, max = 20)
	@Column(name = "NUMEROCONTRATORELACIONADO", length = 20, nullable = true)
	private String numerocontratorelacionado;

	@Column(name = "PROVEEDOR", nullable = true)
	private Integer proveedor;

	@Size(min = 0, max = 255)
	@Column(name = "DESCRIPCION", length = 255, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 3)
	@Column(name = "FORMADEPAGO", length = 3, nullable = true)
	private String formadepago;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHADOCUMENTO", nullable = true)
	private java.util.Date fechadocumento;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAVALIDEZDESDE", nullable = true)
	private java.util.Date fechavalidezdesde;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAVALIDEZHASTA", nullable = true)
	private java.util.Date fechavalidezhasta;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACIERRE", nullable = true)
	private java.util.Date fechacierre;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAPREPARACION", nullable = true)
	private java.util.Date fechapreparacion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAAPROBACION", nullable = true)
	private java.util.Date fechaaprobacion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHATOPEGENERACION", nullable = true)
	private java.util.Date fechatopegeneracion;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACARTAFIANZA01", nullable = true)
	private java.util.Date fechacartafianza01;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHACARTAFIANZA02", nullable = true)
	private java.util.Date fechacartafianza02;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHARENOVACION", nullable = true)
	private java.util.Date fecharenovacion;

	@Size(min = 0, max = 4)
	@Column(name = "FLUJODECAJA", length = 4, nullable = true)
	private String flujodecaja;

	@Size(min = 0, max = 2)
	@Column(name = "MONEDADOCUMENTO", length = 2, nullable = true)
	private String monedadocumento;

	@Column(name = "MONTOIMPONIBLE", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montoimponible;

	@Column(name = "MONTOIMPUESTOS", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montoimpuestos;

	@Column(name = "MONTOTOTAL", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montototal;

	@Column(name = "MONTOPAGADO", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montopagado;

	@NotNull
	@Column(name = "PREPARADAPOR", nullable = true)
	private Integer preparadapor;

	@Column(name = "APROBADAPOR", nullable = true)
	private Integer aprobadapor;

	@Size(min = 0, max = 16)
	@Column(name = "COMENTARIOS", length = 16, nullable = true)
	private String comentarios;

	@Size(min = 0, max = 100)
	@Column(name = "RAZONRECHAZO", length = 100, nullable = true)
	private String razonrechazo;

	@Size(min = 0, max = 3)
	@Column(name = "CLASIFICACION", length = 3, nullable = true)
	private String clasificacion;

	@Size(min = 0, max = 2)
	@Column(name = "TIPOPROGRAMACION", length = 2, nullable = true)
	private String tipoprogramacion;

	@Size(min = 0, max = 1)
	@Column(name = "CONDICIONESPERIODICIDADFLAG", length = 1, nullable = true)
	private String condicionesperiodicidadflag;

	@Column(name = "CONDICIONESPERIODICIDADNUMERO", nullable = true)
	private Integer condicionesperiodicidadnumero;

	@Size(min = 0, max = 10)
	@Column(name = "ALMACENCODIGO", length = 10, nullable = true)
	private String almacencodigo;

	@Size(min = 0, max = 4)
	@Column(name = "CONTRATORESPONSABLE", length = 4, nullable = true)
	private String contratoresponsable;

	@Size(min = 0, max = 4)
	@Column(name = "CONTRATOTIPO", length = 4, nullable = true)
	private String contratotipo;

	@Size(min = 0, max = 50)
	@Column(name = "PROVEEDORPAGINAWEB", length = 50, nullable = true)
	private String proveedorpaginaweb;

	@Size(min = 0, max = 1)
	@Column(name = "GASTOSADICIONALESFLAG", length = 1, nullable = true)
	private String gastosadicionalesflag;

	@Size(min = 0, max = 2)
	@Column(name = "ESTADO", length = 2, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 50)
	@Column(name = "PROVEEDORCONTACTO", length = 50, nullable = true)
	private String proveedorcontacto;

	@Size(min = 0, max = 1)
	@Column(name = "CONTRATOVENTAFLAG", length = 1, nullable = true)
	private String contratoventaflag;

	@Size(min = 0, max = 1)
	@Column(name = "CONTRATOMONTOZEROFLAG", length = 1, nullable = true)
	private String contratomontozeroflag;

	@Size(min = 0, max = 4)
	@Column(name = "RESPONSABLECODIGO", length = 4, nullable = true)
	private String responsablecodigo;

	@Column(name = "REVISADAPOR", nullable = true)
	private Integer revisadapor;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAREVISION", nullable = true)
	private java.util.Date fecharevision;

	@Column(name = "MONTONOAFECTO", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montonoafecto;

	@Column(name = "MONTOTOTALCONTRATOPADRE", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montototalcontratopadre;

	@Size(min = 0, max = 1)
	@Column(name = "ENVIADOFLAG", length = 1, nullable = true)
	private String enviadoflag;

	@Size(min = 0, max = 1)
	@Column(name = "REPORTARPPFLAG", length = 1, nullable = true)
	private String reportarppflag;

	@Size(min = 0, max = 1)
	@Column(name = "NOREPORTABLEFLAG", length = 1, nullable = true)
	private String noreportableflag;

	@Size(min = 0, max = 1)
	@Column(name = "VERCONTRATOVENCIDOFLAG", length = 1, nullable = true)
	private String vercontratovencidoflag;

	@Size(min = 0, max = 100)
	@Column(name = "FORMATOADMINISTRADOR", length = 100, nullable = true)
	private String formatoadministrador;

	@Size(min = 0, max = 100)
	@Column(name = "FORMATOSUPERVISOR", length = 100, nullable = true)
	private String formatosupervisor;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRIPCIONFORMAPAGO", length = 250, nullable = true)
	private String descripcionformapago;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRIPCIONPLAZOCONTRATO", length = 250, nullable = true)
	private String descripcionplazocontrato;

	@Size(min = 0, max = 250)
	@Column(name = "ANTECEDENTES", length = 250, nullable = true)
	private String antecedentes;

	@Size(min = 0, max = 250)
	@Column(name = "DOCUMENTOSCONTRACTUALES", length = 250, nullable = true)
	private String documentoscontractuales;

	@Size(min = 0, max = 1)
	@Column(name = "MODALIDADCONTRATACION", length = 1, nullable = true)
	private String modalidadcontratacion;

	@Size(min = 0, max = 100)
	@Column(name = "FORMATORESPONSABLE", length = 100, nullable = true)
	private String formatoresponsable;

	@Size(min = 0, max = 2)
	@Column(name = "TIPOPROCESO", length = 2, nullable = true)
	private String tipoproceso;

	@Size(min = 0, max = 6)
	@Column(name = "TIPOADJ", length = 6, nullable = true)
	private String tipoadj;

	@Size(min = 0, max = 10)
	@Column(name = "NUMEROPROCESO", length = 10, nullable = true)
	private String numeroproceso;

	@Column(name = "MONTOGARANTIA", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montogarantia;

	@Size(min = 0, max = 2)
	@Column(name = "MONTOGARANTIAMONEDA", length = 2, nullable = true)
	private String montogarantiamoneda;

	@Size(min = 0, max = 2)
	@Column(name = "ESTADOINTERNO", length = 2, nullable = true)
	private String estadointerno;

	@Size(min = 0, max = 255)
	@Column(name = "REQUISICIONCOMENTARIO", length = 255, nullable = true)
	private String requisicioncomentario;
	
	
	@Size(min = 0, max = 3)
	@Column(name = "REQUISICIONMODALIDADCONTRATACION", length = 3, nullable = true)
	private String requisicionmodalidadcontratacion;
	
	@Size(min = 0, max = 3)
	@Column(name = "REQUISICIONTIPOLICITACION", length = 3, nullable = true)
	private String requisiciontipolicitacion;
	
	@Column(name = "PLAZOENTREGA", nullable = true)
	private Integer plazoentrega;
	
	@Transient
	private List<DtoComunSyDocumentoanexos> lstDocumentos=new ArrayList<DtoComunSyDocumentoanexos>();
	
	
	public List<DtoComunSyDocumentoanexos> getLstDocumentos() {
		return lstDocumentos;
	}


	public void setLstDocumentos(List<DtoComunSyDocumentoanexos> lstDocumentos) {
		this.lstDocumentos = lstDocumentos;
	}


	public BeanWhContrato() {
		pk = new BeanWhContratoPk();
	}


	public BeanWhContrato(BeanWhContratoPk pk) {
		this.pk = pk;
	}

	public BeanWhContratoPk getPk() {
		return pk;
	}

	public void setPk(BeanWhContratoPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	
	/**
	 * 
	 * 
	 * @campo DocumentoInterno
	*/
	public String getDocumentointerno() {
		return documentointerno;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoInterno
	*/
	public void setDocumentointerno(String documentointerno) {
		this.documentointerno = documentointerno;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	/**
	 * 
	 * 
	 * @campo NumeroContratoRelacionado
	*/
	public String getNumerocontratorelacionado() {
		return numerocontratorelacionado;
	}

	/**
	 * 
	 * 
	 * @campo NumeroContratoRelacionado
	*/
	public void setNumerocontratorelacionado(String numerocontratorelacionado) {
		this.numerocontratorelacionado = numerocontratorelacionado;
	}
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
	 * @campo FechaDocumento
	*/
	public java.util.Date getFechadocumento() {
		return fechadocumento;
	}

	/**
	 * 
	 * 
	 * @campo FechaDocumento
	*/
	public void setFechadocumento(java.util.Date fechadocumento) {
		this.fechadocumento = fechadocumento;
	}
	/**
	 * 
	 * 
	 * @campo FechaValidezDesde
	*/
	public java.util.Date getFechavalidezdesde() {
		return fechavalidezdesde;
	}

	/**
	 * 
	 * 
	 * @campo FechaValidezDesde
	*/
	public void setFechavalidezdesde(java.util.Date fechavalidezdesde) {
		this.fechavalidezdesde = fechavalidezdesde;
	}
	/**
	 * 
	 * 
	 * @campo FechaValidezHasta
	*/
	public java.util.Date getFechavalidezhasta() {
		return fechavalidezhasta;
	}

	/**
	 * 
	 * 
	 * @campo FechaValidezHasta
	*/
	public void setFechavalidezhasta(java.util.Date fechavalidezhasta) {
		this.fechavalidezhasta = fechavalidezhasta;
	}
	/**
	 * 
	 * 
	 * @campo FechaCierre
	*/
	public java.util.Date getFechacierre() {
		return fechacierre;
	}

	/**
	 * 
	 * 
	 * @campo FechaCierre
	*/
	public void setFechacierre(java.util.Date fechacierre) {
		this.fechacierre = fechacierre;
	}
	/**
	 * 
	 * 
	 * @campo FechaPreparacion
	*/
	public java.util.Date getFechapreparacion() {
		return fechapreparacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaPreparacion
	*/
	public void setFechapreparacion(java.util.Date fechapreparacion) {
		this.fechapreparacion = fechapreparacion;
	}
	/**
	 * 
	 * 
	 * @campo FechaAprobacion
	*/
	public java.util.Date getFechaaprobacion() {
		return fechaaprobacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaAprobacion
	*/
	public void setFechaaprobacion(java.util.Date fechaaprobacion) {
		this.fechaaprobacion = fechaaprobacion;
	}
	/**
	 * 
	 * 
	 * @campo FechaTopeGeneracion
	*/
	public java.util.Date getFechatopegeneracion() {
		return fechatopegeneracion;
	}

	/**
	 * 
	 * 
	 * @campo FechaTopeGeneracion
	*/
	public void setFechatopegeneracion(java.util.Date fechatopegeneracion) {
		this.fechatopegeneracion = fechatopegeneracion;
	}
	/**
	 * 
	 * 
	 * @campo FechaCartaFianza01
	*/
	public java.util.Date getFechacartafianza01() {
		return fechacartafianza01;
	}

	/**
	 * 
	 * 
	 * @campo FechaCartaFianza01
	*/
	public void setFechacartafianza01(java.util.Date fechacartafianza01) {
		this.fechacartafianza01 = fechacartafianza01;
	}
	/**
	 * 
	 * 
	 * @campo FechaCartaFianza02
	*/
	public java.util.Date getFechacartafianza02() {
		return fechacartafianza02;
	}

	/**
	 * 
	 * 
	 * @campo FechaCartaFianza02
	*/
	public void setFechacartafianza02(java.util.Date fechacartafianza02) {
		this.fechacartafianza02 = fechacartafianza02;
	}
	/**
	 * 
	 * 
	 * @campo FechaRenovacion
	*/
	public java.util.Date getFecharenovacion() {
		return fecharenovacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaRenovacion
	*/
	public void setFecharenovacion(java.util.Date fecharenovacion) {
		this.fecharenovacion = fecharenovacion;
	}
	/**
	 * 
	 * 
	 * @campo FlujodeCaja
	*/
	public String getFlujodecaja() {
		return flujodecaja;
	}

	/**
	 * 
	 * 
	 * @campo FlujodeCaja
	*/
	public void setFlujodecaja(String flujodecaja) {
		this.flujodecaja = flujodecaja;
	}
	/**
	 * 
	 * 
	 * @campo MonedaDocumento
	*/
	public String getMonedadocumento() {
		return monedadocumento;
	}

	/**
	 * 
	 * 
	 * @campo MonedaDocumento
	*/
	public void setMonedadocumento(String monedadocumento) {
		this.monedadocumento = monedadocumento;
	}
	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public java.math.BigDecimal getMontoimponible() {
		return montoimponible;
	}

	/**
	 * 
	 * 
	 * @campo MontoImponible
	*/
	public void setMontoimponible(java.math.BigDecimal montoimponible) {
		this.montoimponible = montoimponible;
	}
	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public java.math.BigDecimal getMontoimpuestos() {
		return montoimpuestos;
	}

	/**
	 * 
	 * 
	 * @campo MontoImpuestos
	*/
	public void setMontoimpuestos(java.math.BigDecimal montoimpuestos) {
		this.montoimpuestos = montoimpuestos;
	}
	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public java.math.BigDecimal getMontototal() {
		return montototal;
	}

	/**
	 * 
	 * 
	 * @campo MontoTotal
	*/
	public void setMontototal(java.math.BigDecimal montototal) {
		this.montototal = montototal;
	}
	/**
	 * 
	 * 
	 * @campo MontoPagado
	*/
	public java.math.BigDecimal getMontopagado() {
		return montopagado;
	}

	/**
	 * 
	 * 
	 * @campo MontoPagado
	*/
	public void setMontopagado(java.math.BigDecimal montopagado) {
		this.montopagado = montopagado;
	}
	/**
	 * 
	 * 
	 * @campo PreparadaPor
	*/
	public Integer getPreparadapor() {
		return preparadapor;
	}

	/**
	 * 
	 * 
	 * @campo PreparadaPor
	*/
	public void setPreparadapor(Integer preparadapor) {
		this.preparadapor = preparadapor;
	}
	/**
	 * 
	 * 
	 * @campo AprobadaPor
	*/
	public Integer getAprobadapor() {
		return aprobadapor;
	}

	/**
	 * 
	 * 
	 * @campo AprobadaPor
	*/
	public void setAprobadapor(Integer aprobadapor) {
		this.aprobadapor = aprobadapor;
	}
	/**
	 * 
	 * 
	 * @campo Comentarios
	*/
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * 
	 * 
	 * @campo Comentarios
	*/
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * 
	 * 
	 * @campo RazonRechazo
	*/
	public String getRazonrechazo() {
		return razonrechazo;
	}

	/**
	 * 
	 * 
	 * @campo RazonRechazo
	*/
	public void setRazonrechazo(String razonrechazo) {
		this.razonrechazo = razonrechazo;
	}
	/**
	 * 
	 * 
	 * @campo Clasificacion
	*/
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * 
	 * 
	 * @campo Clasificacion
	*/
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	/**
	 * 
	 * 
	 * @campo TipoProgramacion
	*/
	public String getTipoprogramacion() {
		return tipoprogramacion;
	}

	/**
	 * 
	 * 
	 * @campo TipoProgramacion
	*/
	public void setTipoprogramacion(String tipoprogramacion) {
		this.tipoprogramacion = tipoprogramacion;
	}
	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadFlag
	*/
	public String getCondicionesperiodicidadflag() {
		return condicionesperiodicidadflag;
	}

	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadFlag
	*/
	public void setCondicionesperiodicidadflag(String condicionesperiodicidadflag) {
		this.condicionesperiodicidadflag = condicionesperiodicidadflag;
	}
	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadNumero
	*/
	public Integer getCondicionesperiodicidadnumero() {
		return condicionesperiodicidadnumero;
	}

	/**
	 * 
	 * 
	 * @campo CondicionesPeriodicidadNumero
	*/
	public void setCondicionesperiodicidadnumero(Integer condicionesperiodicidadnumero) {
		this.condicionesperiodicidadnumero = condicionesperiodicidadnumero;
	}
	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public String getAlmacencodigo() {
		return almacencodigo;
	}

	/**
	 * 
	 * 
	 * @campo AlmacenCodigo
	*/
	public void setAlmacencodigo(String almacencodigo) {
		this.almacencodigo = almacencodigo;
	}
	/**
	 * 
	 * 
	 * @campo ContratoResponsable
	*/
	public String getContratoresponsable() {
		return contratoresponsable;
	}

	/**
	 * 
	 * 
	 * @campo ContratoResponsable
	*/
	public void setContratoresponsable(String contratoresponsable) {
		this.contratoresponsable = contratoresponsable;
	}
	/**
	 * 
	 * 
	 * @campo ContratoTipo
	*/
	public String getContratotipo() {
		return contratotipo;
	}

	/**
	 * 
	 * 
	 * @campo ContratoTipo
	*/
	public void setContratotipo(String contratotipo) {
		this.contratotipo = contratotipo;
	}
	/**
	 * 
	 * 
	 * @campo ProveedorPaginaWeb
	*/
	public String getProveedorpaginaweb() {
		return proveedorpaginaweb;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorPaginaWeb
	*/
	public void setProveedorpaginaweb(String proveedorpaginaweb) {
		this.proveedorpaginaweb = proveedorpaginaweb;
	}
	/**
	 * 
	 * 
	 * @campo GastosAdicionalesFlag
	*/
	public String getGastosadicionalesflag() {
		return gastosadicionalesflag;
	}

	/**
	 * 
	 * 
	 * @campo GastosAdicionalesFlag
	*/
	public void setGastosadicionalesflag(String gastosadicionalesflag) {
		this.gastosadicionalesflag = gastosadicionalesflag;
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
	 * @campo ProveedorContacto
	*/
	public String getProveedorcontacto() {
		return proveedorcontacto;
	}

	/**
	 * 
	 * 
	 * @campo ProveedorContacto
	*/
	public void setProveedorcontacto(String proveedorcontacto) {
		this.proveedorcontacto = proveedorcontacto;
	}
	/**
	 * 
	 * 
	 * @campo ContratoVentaFlag
	*/
	public String getContratoventaflag() {
		return contratoventaflag;
	}

	/**
	 * 
	 * 
	 * @campo ContratoVentaFlag
	*/
	public void setContratoventaflag(String contratoventaflag) {
		this.contratoventaflag = contratoventaflag;
	}
	/**
	 * 
	 * 
	 * @campo ContratoMontoZeroFlag
	*/
	public String getContratomontozeroflag() {
		return contratomontozeroflag;
	}

	/**
	 * 
	 * 
	 * @campo ContratoMontoZeroFlag
	*/
	public void setContratomontozeroflag(String contratomontozeroflag) {
		this.contratomontozeroflag = contratomontozeroflag;
	}
	/**
	 * 
	 * 
	 * @campo ResponsableCodigo
	*/
	public String getResponsablecodigo() {
		return responsablecodigo;
	}

	/**
	 * 
	 * 
	 * @campo ResponsableCodigo
	*/
	public void setResponsablecodigo(String responsablecodigo) {
		this.responsablecodigo = responsablecodigo;
	}
	/**
	 * 
	 * 
	 * @campo RevisadaPor
	*/
	public Integer getRevisadapor() {
		return revisadapor;
	}

	/**
	 * 
	 * 
	 * @campo RevisadaPor
	*/
	public void setRevisadapor(Integer revisadapor) {
		this.revisadapor = revisadapor;
	}
	/**
	 * 
	 * 
	 * @campo FechaRevision
	*/
	public java.util.Date getFecharevision() {
		return fecharevision;
	}

	/**
	 * 
	 * 
	 * @campo FechaRevision
	*/
	public void setFecharevision(java.util.Date fecharevision) {
		this.fecharevision = fecharevision;
	}
	/**
	 * 
	 * 
	 * @campo MontoNoAfecto
	*/
	public java.math.BigDecimal getMontonoafecto() {
		return montonoafecto;
	}

	/**
	 * 
	 * 
	 * @campo MontoNoAfecto
	*/
	public void setMontonoafecto(java.math.BigDecimal montonoafecto) {
		this.montonoafecto = montonoafecto;
	}
	/**
	 * 
	 * 
	 * @campo MontoTotalContratoPadre
	*/
	public java.math.BigDecimal getMontototalcontratopadre() {
		return montototalcontratopadre;
	}

	/**
	 * 
	 * 
	 * @campo MontoTotalContratoPadre
	*/
	public void setMontototalcontratopadre(java.math.BigDecimal montototalcontratopadre) {
		this.montototalcontratopadre = montototalcontratopadre;
	}
	/**
	 * 
	 * 
	 * @campo EnviadoFlag
	*/
	public String getEnviadoflag() {
		return enviadoflag;
	}

	/**
	 * 
	 * 
	 * @campo EnviadoFlag
	*/
	public void setEnviadoflag(String enviadoflag) {
		this.enviadoflag = enviadoflag;
	}
	/**
	 * 
	 * 
	 * @campo ReportarPPFlag
	*/
	public String getReportarppflag() {
		return reportarppflag;
	}

	/**
	 * 
	 * 
	 * @campo ReportarPPFlag
	*/
	public void setReportarppflag(String reportarppflag) {
		this.reportarppflag = reportarppflag;
	}
	/**
	 * 
	 * 
	 * @campo NoReportableFlag
	*/
	public String getNoreportableflag() {
		return noreportableflag;
	}

	/**
	 * 
	 * 
	 * @campo NoReportableFlag
	*/
	public void setNoreportableflag(String noreportableflag) {
		this.noreportableflag = noreportableflag;
	}
	/**
	 * 
	 * 
	 * @campo VerContratoVencidoFlag
	*/
	public String getVercontratovencidoflag() {
		return vercontratovencidoflag;
	}

	/**
	 * 
	 * 
	 * @campo VerContratoVencidoFlag
	*/
	public void setVercontratovencidoflag(String vercontratovencidoflag) {
		this.vercontratovencidoflag = vercontratovencidoflag;
	}
	/**
	 * 
	 * 
	 * @campo FormatoAdministrador
	*/
	public String getFormatoadministrador() {
		return formatoadministrador;
	}

	/**
	 * 
	 * 
	 * @campo FormatoAdministrador
	*/
	public void setFormatoadministrador(String formatoadministrador) {
		this.formatoadministrador = formatoadministrador;
	}
	/**
	 * 
	 * 
	 * @campo FormatoSupervisor
	*/
	public String getFormatosupervisor() {
		return formatosupervisor;
	}

	/**
	 * 
	 * 
	 * @campo FormatoSupervisor
	*/
	public void setFormatosupervisor(String formatosupervisor) {
		this.formatosupervisor = formatosupervisor;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionFormaPago
	*/
	public String getDescripcionformapago() {
		return descripcionformapago;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionFormaPago
	*/
	public void setDescripcionformapago(String descripcionformapago) {
		this.descripcionformapago = descripcionformapago;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionPlazoContrato
	*/
	public String getDescripcionplazocontrato() {
		return descripcionplazocontrato;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionPlazoContrato
	*/
	public void setDescripcionplazocontrato(String descripcionplazocontrato) {
		this.descripcionplazocontrato = descripcionplazocontrato;
	}
	/**
	 * 
	 * 
	 * @campo Antecedentes
	*/
	public String getAntecedentes() {
		return antecedentes;
	}

	/**
	 * 
	 * 
	 * @campo Antecedentes
	*/
	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}
	/**
	 * 
	 * 
	 * @campo DocumentosContractuales
	*/
	public String getDocumentoscontractuales() {
		return documentoscontractuales;
	}

	/**
	 * 
	 * 
	 * @campo DocumentosContractuales
	*/
	public void setDocumentoscontractuales(String documentoscontractuales) {
		this.documentoscontractuales = documentoscontractuales;
	}
	/**
	 * 
	 * 
	 * @campo ModalidadContratacion
	*/
	public String getModalidadcontratacion() {
		return modalidadcontratacion;
	}

	/**
	 * 
	 * 
	 * @campo ModalidadContratacion
	*/
	public void setModalidadcontratacion(String modalidadcontratacion) {
		this.modalidadcontratacion = modalidadcontratacion;
	}
	/**
	 * 
	 * 
	 * @campo FormatoResponsable
	*/
	public String getFormatoresponsable() {
		return formatoresponsable;
	}

	/**
	 * 
	 * 
	 * @campo FormatoResponsable
	*/
	public void setFormatoresponsable(String formatoresponsable) {
		this.formatoresponsable = formatoresponsable;
	}
	/**
	 * 
	 * 
	 * @campo TipoProceso
	*/
	public String getTipoproceso() {
		return tipoproceso;
	}

	/**
	 * 
	 * 
	 * @campo TipoProceso
	*/
	public void setTipoproceso(String tipoproceso) {
		this.tipoproceso = tipoproceso;
	}
	/**
	 * 
	 * 
	 * @campo TipoAdj
	*/
	public String getTipoadj() {
		return tipoadj;
	}

	/**
	 * 
	 * 
	 * @campo TipoAdj
	*/
	public void setTipoadj(String tipoadj) {
		this.tipoadj = tipoadj;
	}
	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public String getNumeroproceso() {
		return numeroproceso;
	}

	/**
	 * 
	 * 
	 * @campo NumeroProceso
	*/
	public void setNumeroproceso(String numeroproceso) {
		this.numeroproceso = numeroproceso;
	}
	/**
	 * 
	 * 
	 * @campo MontoGarantia
	*/
	public java.math.BigDecimal getMontogarantia() {
		return montogarantia;
	}

	/**
	 * 
	 * 
	 * @campo MontoGarantia
	*/
	public void setMontogarantia(java.math.BigDecimal montogarantia) {
		this.montogarantia = montogarantia;
	}
	/**
	 * 
	 * 
	 * @campo MontoGarantiaMoneda
	*/
	public String getMontogarantiamoneda() {
		return montogarantiamoneda;
	}

	/**
	 * 
	 * 
	 * @campo MontoGarantiaMoneda
	*/
	public void setMontogarantiamoneda(String montogarantiamoneda) {
		this.montogarantiamoneda = montogarantiamoneda;
	}
	/**
	 * 
	 * 
	 * @campo EstadoInterno
	*/
	public String getEstadointerno() {
		return estadointerno;
	}

	/**
	 * 
	 * 
	 * @campo EstadoInterno
	*/
	public void setEstadointerno(String estadointerno) {
		this.estadointerno = estadointerno;
	}


	public String getRequisicioncomentario() {
		return requisicioncomentario;
	}


	public void setRequisicioncomentario(String requisicioncomentario) {
		this.requisicioncomentario = requisicioncomentario;
	}


	public String getRequisicionmodalidadcontratacion() {
		return requisicionmodalidadcontratacion;
	}


	public void setRequisicionmodalidadcontratacion(String requisicionmodalidadcontratacion) {
		this.requisicionmodalidadcontratacion = requisicionmodalidadcontratacion;
	}


	public String getRequisiciontipolicitacion() {
		return requisiciontipolicitacion;
	}


	public void setRequisiciontipolicitacion(String requisiciontipolicitacion) {
		this.requisiciontipolicitacion = requisiciontipolicitacion;
	}


	public Integer getPlazoentrega() {
		return plazoentrega;
	}


	public void setPlazoentrega(Integer plazoentrega) {
		this.plazoentrega = plazoentrega;
	}

	
}
