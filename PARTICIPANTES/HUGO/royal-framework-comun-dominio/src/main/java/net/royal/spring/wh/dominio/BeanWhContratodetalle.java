package net.royal.spring.wh.dominio;

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
 * @tabla dbo.WH_ContratoDetalle
*/
@Entity
@Table(name = "WH_CONTRATODETALLE")
public class BeanWhContratodetalle extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhContratodetallePk pk;

	@Size(min = 0, max = 255)
	@Column(name = "DESCRIPCION", length = 255, nullable = true)
	private String descripcion;

	@Column(name = "CANTIDAD", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal cantidad;

	@Column(name = "PRECIOUNITARIO", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal preciounitario;

	@Column(name = "MONTOTOTAL", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montototal;

	@Size(min = 0, max = 20)
	@Column(name = "ITEM", length = 20, nullable = true)
	private String item;

	@Size(min = 0, max = 5)
	@Column(name = "COMMODITY", length = 5, nullable = true)
	private String commodity;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADCODIGO", length = 6, nullable = true)
	private String unidadcodigo;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTACONTABLE", length = 20, nullable = true)
	private String cuentacontable;

	@Size(min = 0, max = 10)
	@Column(name = "CENTROCOSTO", length = 10, nullable = true)
	private String centrocosto;

	@Size(min = 0, max = 6)
	@Column(name = "CENTROCOSTODESTINO", length = 6, nullable = true)
	private String centrocostodestino;

	@Size(min = 0, max = 15)
	@Column(name = "PROYECTO", length = 15, nullable = true)
	private String proyecto;

	@Size(min = 0, max = 4)
	@Column(name = "SUCURSAL", length = 4, nullable = true)
	private String sucursal;

	@Size(min = 0, max = 12)
	@Column(name = "CAMPOREFERENCIA", length = 12, nullable = true)
	private String camporeferencia;

	@Size(min = 0, max = 8)
	@Column(name = "DESTINOCOMPANIASOCIO", length = 8, nullable = true)
	private String destinocompaniasocio;

	@Size(min = 0, max = 16)
	@Column(name = "COMENTARIO", length = 16, nullable = true)
	private String comentario;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 1)
	@Column(name = "IGVEXONERADOFLAG", length = 1, nullable = true)
	private String igvexoneradoflag;

	@Size(min = 0, max = 10)
	@Column(name = "REFERENCIAFISCAL01", length = 10, nullable = true)
	private String referenciafiscal01;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCAL02", length = 20, nullable = true)
	private String referenciafiscal02;

	@Size(min = 0, max = 10)
	@Column(name = "REFERENCIAFISCAL03", length = 10, nullable = true)
	private String referenciafiscal03;

	@Size(min = 0, max = 1)
	@Column(name = "ADDFUTUROFLAG", length = 1, nullable = true)
	private String addfuturoflag;

	@Size(min = 0, max = 15)
	@Column(name = "YEC_AFE", length = 15, nullable = true)
	private String yecAfe;

	@Column(name = "ADENDANUMERO", nullable = true)
	private Integer adendanumero;

	@Size(min = 0, max = 1)
	@Column(name = "ADENDAFLAG", length = 1, nullable = true)
	private String adendaflag;

	@Size(min = 0, max = 2)
	@Column(name = "ADENDAESTADO", length = 2, nullable = true)
	private String adendaestado;

	@Size(min = 0, max = 100)
	@Column(name = "ADENDARAZONRECHAZO", length = 100, nullable = true)
	private String adendarazonrechazo;

	@Size(min = 0, max = 10)
	@Column(name = "ADENDAREQUISICION", length = 10, nullable = true)
	private String adendarequisicion;

	@Size(min = 0, max = 1)
	@Column(name = "FUTUROFLAG", length = 1, nullable = true)
	private String futuroflag;


	public BeanWhContratodetalle() {
		pk = new BeanWhContratodetallePk();
	}


	public BeanWhContratodetalle(BeanWhContratodetallePk pk) {
		this.pk = pk;
	}

	public BeanWhContratodetallePk getPk() {
		return pk;
	}

	public void setPk(BeanWhContratodetallePk pk) {
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
	 * @campo Cantidad
	*/
	public java.math.BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * 
	 * 
	 * @campo Cantidad
	*/
	public void setCantidad(java.math.BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public java.math.BigDecimal getPreciounitario() {
		return preciounitario;
	}

	/**
	 * 
	 * 
	 * @campo PrecioUnitario
	*/
	public void setPreciounitario(java.math.BigDecimal preciounitario) {
		this.preciounitario = preciounitario;
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
	 * @campo Item
	*/
	public String getItem() {
		return item;
	}

	/**
	 * 
	 * 
	 * @campo Item
	*/
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * 
	 * 
	 * @campo Commodity
	*/
	public String getCommodity() {
		return commodity;
	}

	/**
	 * 
	 * 
	 * @campo Commodity
	*/
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public String getUnidadcodigo() {
		return unidadcodigo;
	}

	/**
	 * 
	 * 
	 * @campo UnidadCodigo
	*/
	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}
	/**
	 * 
	 * 
	 * @campo CuentaContable
	*/
	public String getCuentacontable() {
		return cuentacontable;
	}

	/**
	 * 
	 * 
	 * @campo CuentaContable
	*/
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
	}
	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public String getCentrocosto() {
		return centrocosto;
	}

	/**
	 * 
	 * 
	 * @campo CentroCosto
	*/
	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}
	/**
	 * 
	 * 
	 * @campo CentroCostoDestino
	*/
	public String getCentrocostodestino() {
		return centrocostodestino;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostoDestino
	*/
	public void setCentrocostodestino(String centrocostodestino) {
		this.centrocostodestino = centrocostodestino;
	}
	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public String getProyecto() {
		return proyecto;
	}

	/**
	 * 
	 * 
	 * @campo Proyecto
	*/
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public String getCamporeferencia() {
		return camporeferencia;
	}

	/**
	 * 
	 * 
	 * @campo CampoReferencia
	*/
	public void setCamporeferencia(String camporeferencia) {
		this.camporeferencia = camporeferencia;
	}
	/**
	 * 
	 * 
	 * @campo DestinoCompaniaSocio
	*/
	public String getDestinocompaniasocio() {
		return destinocompaniasocio;
	}

	/**
	 * 
	 * 
	 * @campo DestinoCompaniaSocio
	*/
	public void setDestinocompaniasocio(String destinocompaniasocio) {
		this.destinocompaniasocio = destinocompaniasocio;
	}
	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public String getComentario() {
		return comentario;
	}

	/**
	 * 
	 * 
	 * @campo Comentario
	*/
	public void setComentario(String comentario) {
		this.comentario = comentario;
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
	 * @campo IGVExoneradoFlag
	*/
	public String getIgvexoneradoflag() {
		return igvexoneradoflag;
	}

	/**
	 * 
	 * 
	 * @campo IGVExoneradoFlag
	*/
	public void setIgvexoneradoflag(String igvexoneradoflag) {
		this.igvexoneradoflag = igvexoneradoflag;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	*/
	public String getReferenciafiscal01() {
		return referenciafiscal01;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal01
	*/
	public void setReferenciafiscal01(String referenciafiscal01) {
		this.referenciafiscal01 = referenciafiscal01;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	*/
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal02
	*/
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	*/
	public String getReferenciafiscal03() {
		return referenciafiscal03;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscal03
	*/
	public void setReferenciafiscal03(String referenciafiscal03) {
		this.referenciafiscal03 = referenciafiscal03;
	}
	/**
	 * 
	 * 
	 * @campo ADDFUTUROFLAG
	*/
	public String getAddfuturoflag() {
		return addfuturoflag;
	}

	/**
	 * 
	 * 
	 * @campo ADDFUTUROFLAG
	*/
	public void setAddfuturoflag(String addfuturoflag) {
		this.addfuturoflag = addfuturoflag;
	}
	/**
	 * 
	 * 
	 * @campo YEC_Afe
	*/
	public String getYecAfe() {
		return yecAfe;
	}

	/**
	 * 
	 * 
	 * @campo YEC_Afe
	*/
	public void setYecAfe(String yecAfe) {
		this.yecAfe = yecAfe;
	}
	/**
	 * 
	 * 
	 * @campo AdendaNumero
	*/
	public Integer getAdendanumero() {
		return adendanumero;
	}

	/**
	 * 
	 * 
	 * @campo AdendaNumero
	*/
	public void setAdendanumero(Integer adendanumero) {
		this.adendanumero = adendanumero;
	}
	/**
	 * 
	 * 
	 * @campo AdendaFlag
	*/
	public String getAdendaflag() {
		return adendaflag;
	}

	/**
	 * 
	 * 
	 * @campo AdendaFlag
	*/
	public void setAdendaflag(String adendaflag) {
		this.adendaflag = adendaflag;
	}
	/**
	 * 
	 * 
	 * @campo ADENDAESTADO
	*/
	public String getAdendaestado() {
		return adendaestado;
	}

	/**
	 * 
	 * 
	 * @campo ADENDAESTADO
	*/
	public void setAdendaestado(String adendaestado) {
		this.adendaestado = adendaestado;
	}
	/**
	 * 
	 * 
	 * @campo ADENDARAZONRECHAZO
	*/
	public String getAdendarazonrechazo() {
		return adendarazonrechazo;
	}

	/**
	 * 
	 * 
	 * @campo ADENDARAZONRECHAZO
	*/
	public void setAdendarazonrechazo(String adendarazonrechazo) {
		this.adendarazonrechazo = adendarazonrechazo;
	}
	/**
	 * 
	 * 
	 * @campo ADENDAREQUISICION
	*/
	public String getAdendarequisicion() {
		return adendarequisicion;
	}

	/**
	 * 
	 * 
	 * @campo ADENDAREQUISICION
	*/
	public void setAdendarequisicion(String adendarequisicion) {
		this.adendarequisicion = adendarequisicion;
	}
	/**
	 * 
	 * 
	 * @campo FUTUROFLAG
	*/
	public String getFuturoflag() {
		return futuroflag;
	}

	/**
	 * 
	 * 
	 * @campo FUTUROFLAG
	*/
	public void setFuturoflag(String futuroflag) {
		this.futuroflag = futuroflag;
	}

}
