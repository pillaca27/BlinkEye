package net.royal.spring.logistica.dominio;

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
 * @tabla dbo.WH_ItemMast
*/
@Entity
@Table(name = "WH_ITEMMAST")
public class BeanWhItemmast extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhItemmastPk pk;

	@Size(min = 0, max = 2)
	@Column(name = "ITEMTIPO", length = 2, nullable = true)
	private String itemtipo;

	@Size(min = 0, max = 4)
	@Column(name = "MARCACODIGO", length = 4, nullable = true)
	private String marcacodigo;

	@Size(min = 0, max = 6)
	@Column(name = "LINEA", length = 6, nullable = true)
	private String linea;

	@Size(min = 0, max = 6)
	@Column(name = "FAMILIA", length = 6, nullable = true)
	private String familia;

	@Size(min = 0, max = 6)
	@Column(name = "SUBFAMILIA", length = 6, nullable = true)
	private String subfamilia;

	@Size(min = 0, max = 60)
	@Column(name = "DESCRIPCIONLOCAL", length = 60, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 60)
	@Column(name = "DESCRIPCIONINGLES", length = 60, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRIPCIONCOMPLETA", length = 250, nullable = true)
	private String descripcioncompleta;

	@Size(min = 0, max = 20)
	@Column(name = "NUMERODEPARTE", length = 20, nullable = true)
	private String numerodeparte;

	@Size(min = 0, max = 20)
	@Column(name = "CODIGOINTERNO", length = 20, nullable = true)
	private String codigointerno;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADCODIGO", length = 6, nullable = true)
	private String unidadcodigo;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADCODIGODOBLE", length = 6, nullable = true)
	private String unidadcodigodoble;

	@Size(min = 0, max = 10)
	@Column(name = "PARTIDAARANCELARIA", length = 10, nullable = true)
	private String partidaarancelaria;

	@Size(min = 0, max = 20)
	@Column(name = "MODELO", length = 20, nullable = true)
	private String modelo;

	@Size(min = 0, max = 3)
	@Column(name = "COLOR", length = 3, nullable = true)
	private String color;

	@Size(min = 0, max = 13)
	@Column(name = "CODIGOBARRAS", length = 13, nullable = true)
	private String codigobarras;

	@Size(min = 0, max = 13)
	@Column(name = "CODIGOBARRASFABRICANTE", length = 13, nullable = true)
	private String codigobarrasfabricante;

	@Size(min = 0, max = 2)
	@Column(name = "MONEDACODIGO", length = 2, nullable = true)
	private String monedacodigo;

	@Column(name = "PRECIOCOSTO", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal preciocosto;

	@Column(name = "PRECIOUNITARIOLOCAL", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal preciounitariolocal;

	@Column(name = "PRECIOUNITARIODOLARES", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal preciounitariodolares;

	@Size(min = 0, max = 1)
	@Column(name = "ITEMPRECIOFLAG", length = 1, nullable = true)
	private String itemprecioflag;

	@Size(min = 0, max = 20)
	@Column(name = "ITEMPRECIOCODIGO", length = 20, nullable = true)
	private String itempreciocodigo;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLEVENTAFLAG", length = 1, nullable = true)
	private String disponibleventaflag;

	@Size(min = 0, max = 3)
	@Column(name = "ITEMPROCEDENCIA", length = 3, nullable = true)
	private String itemprocedencia;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOXLOTEFLAG", length = 1, nullable = true)
	private String manejoxloteflag;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOXSERIEFLAG", length = 1, nullable = true)
	private String manejoxserieflag;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOXKITFLAG", length = 1, nullable = true)
	private String manejoxkitflag;

	@Size(min = 0, max = 1)
	@Column(name = "AFECTOIMPUESTOVENTASFLAG", length = 1, nullable = true)
	private String afectoimpuestoventasflag;

	@Size(min = 0, max = 1)
	@Column(name = "AFECTOIMPUESTO2FLAG", length = 1, nullable = true)
	private String afectoimpuesto2flag;

	@Size(min = 0, max = 1)
	@Column(name = "REQUISICIONAMIENTOAUTOMATICOFL", length = 1, nullable = true)
	private String requisicionamientoautomaticofl;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLETRANSFERENCIAFLAG", length = 1, nullable = true)
	private String disponibletransferenciaflag;

	@Size(min = 0, max = 1)
	@Column(name = "DISPONIBLECONSUMOFLAG", length = 1, nullable = true)
	private String disponibleconsumoflag;

	@Size(min = 0, max = 1)
	@Column(name = "FORMULARIOFLAG", length = 1, nullable = true)
	private String formularioflag;

	@Column(name = "FORMULARIONROJUEGOS", nullable = true)
	private Integer formularionrojuegos;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOXUNIDADFLAG", length = 1, nullable = true)
	private String manejoxunidadflag;

	@Size(min = 0, max = 1)
	@Column(name = "ISOAPLICABLEFLAG", length = 1, nullable = true)
	private String isoaplicableflag;

	@Size(min = 0, max = 1)
	@Column(name = "CANTIDADDOBLEFLAG", length = 1, nullable = true)
	private String cantidaddobleflag;

	@Size(min = 0, max = 15)
	@Column(name = "ISONORMAINTERNA", length = 15, nullable = true)
	private String isonormainterna;

	@Size(min = 0, max = 4)
	@Column(name = "UNIDADREPLICACION", length = 4, nullable = true)
	private String unidadreplicacion;

	@Size(min = 0, max = 80)
	@Column(name = "IMAGEFILE", length = 80, nullable = true)
	private String imagefile;

	@Size(min = 0, max = 80)
	@Column(name = "MAPAFILE", length = 80, nullable = true)
	private String mapafile;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAINVENTARIO", length = 20, nullable = true)
	private String cuentainventario;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAGASTO", length = 20, nullable = true)
	private String cuentagasto;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAINVERSION", length = 20, nullable = true)
	private String cuentainversion;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTASERVICIOTECNICO", length = 20, nullable = true)
	private String cuentaserviciotecnico;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTASALIDATERCEROS", length = 20, nullable = true)
	private String cuentasalidaterceros;

	@Size(min = 0, max = 6)
	@Column(name = "ELEMENTOGASTO", length = 6, nullable = true)
	private String elementogasto;

	@Size(min = 0, max = 6)
	@Column(name = "ELEMENTOINVERSION", length = 6, nullable = true)
	private String elementoinversion;

	@Size(min = 0, max = 4)
	@Column(name = "PARTIDAPRESUPUESTAL", length = 4, nullable = true)
	private String partidapresupuestal;

	@Size(min = 0, max = 4)
	@Column(name = "FLUJODECAJA", length = 4, nullable = true)
	private String flujodecaja;

	@Column(name = "LOTEDECOMPRA", nullable = true)
	private Integer lotedecompra;

	@Column(name = "LOTEDEDESPACHO", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal lotededespacho;

	@Column(name = "PERIODICIDADCOMPRAMESES", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal periodicidadcomprameses;

	@Size(min = 0, max = 255)
	@Column(name = "ESPECIFICACIONTECNICA", length = 255, nullable = true)
	private String especificaciontecnica;

	@Size(min = 0, max = 30)
	@Column(name = "DIMENSIONES", length = 30, nullable = true)
	private String dimensiones;

	@Column(name = "FACTOREQUIVALENCIACOMERCIAL", precision = 10,scale =4, nullable = true)
	private java.math.BigDecimal factorequivalenciacomercial;

	@Size(min = 0, max = 2)
	@Column(name = "ABCCODIGO", length = 2, nullable = true)
	private String abccodigo;

	@Column(name = "INVENTARIOTOLERANCIA", precision = 10,scale =7, nullable = true)
	private java.math.BigDecimal inventariotolerancia;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAVENTAS", length = 20, nullable = true)
	private String cuentaventas;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADCOMPRA", length = 6, nullable = true)
	private String unidadcompra;

	@Column(name = "LOTEDECOMPRAM3", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal lotedecompram3;

	@Column(name = "LOTEDECOMPRAKG", precision = 16,scale =6, nullable = true)
	private java.math.BigDecimal lotedecomprakg;

	@Size(min = 0, max = 1)
	@Column(name = "CONTROLCALIDADFLAG", length = 1, nullable = true)
	private String controlcalidadflag;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADEMBALAJE", length = 6, nullable = true)
	private String unidadembalaje;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTATRANSITO", length = 20, nullable = true)
	private String cuentatransito;

	@Size(min = 0, max = 1)
	@Column(name = "MANEJOXKITSPLITFLAG", length = 1, nullable = true)
	private String manejoxkitsplitflag;

	@Size(min = 0, max = 10)
	@Column(name = "MAPACODIGO", length = 10, nullable = true)
	private String mapacodigo;

	@Column(name = "CANTIDADDOBLEFACTOR", precision = 20,scale =10, nullable = true)
	private java.math.BigDecimal cantidaddoblefactor;

	@Size(min = 0, max = 6)
	@Column(name = "SUBFAMILIAINFERIOR", length = 6, nullable = true)
	private String subfamiliainferior;

	@Size(min = 0, max = 6)
	@Column(name = "CLASIFICACIONCOMERCIAL", length = 6, nullable = true)
	private String clasificacioncomercial;

	@Column(name = "STOCKMINIMO", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal stockminimo;

	@Column(name = "STOCKMAXIMO", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal stockmaximo;

	@Column(name = "LOTEDEVENTA", nullable = true)
	private Integer lotedeventa;

	@Size(min = 0, max = 16)
	@Column(name = "DESCRIPCIONADICIONAL", length = 16, nullable = true)
	private String descripcionadicional;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOPRECIO", length = 3, nullable = true)
	private String codigoprecio;

	@Size(min = 0, max = 10)
	@Column(name = "CARACTERISTICAVALOR01", length = 10, nullable = true)
	private String caracteristicavalor01;

	@Size(min = 0, max = 10)
	@Column(name = "CARACTERISTICAVALOR02", length = 10, nullable = true)
	private String caracteristicavalor02;

	@Size(min = 0, max = 10)
	@Column(name = "CARACTERISTICAVALOR03", length = 10, nullable = true)
	private String caracteristicavalor03;

	@Size(min = 0, max = 10)
	@Column(name = "CARACTERISTICAVALOR04", length = 10, nullable = true)
	private String caracteristicavalor04;

	@Size(min = 0, max = 10)
	@Column(name = "CARACTERISTICAVALOR05", length = 10, nullable = true)
	private String caracteristicavalor05;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCAL02", length = 20, nullable = true)
	private String referenciafiscal02;

	@Size(min = 0, max = 4)
	@Column(name = "DETRACCIONCODIGO", length = 4, nullable = true)
	private String detraccioncodigo;

	@Size(min = 0, max = 30)
	@Column(name = "CODIGOBARRAS2", length = 30, nullable = true)
	private String codigobarras2;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCALINGRESO02", length = 20, nullable = true)
	private String referenciafiscalingreso02;

	@Size(min = 0, max = 10)
	@Column(name = "CODIGOCATALOGO25", length = 10, nullable = true)
	private String codigocatalogo25;

	@Size(min = 0, max = 25)
	@Column(name = "CODIGOGS1", length = 25, nullable = true)
	private String codigogs1;

	
	@Transient
	private Boolean correlativo;
	
	
	
	public Boolean getCorrelativo() {
		if(correlativo==null)
			return false;
		
		return correlativo;
	}

	public void setCorrelativo(Boolean correlativo) {
		this.correlativo = correlativo;
	}
	

	public BeanWhItemmast() {
		pk = new BeanWhItemmastPk();
	}


	public BeanWhItemmast(BeanWhItemmastPk pk) {
		this.pk = pk;
	}

	public BeanWhItemmastPk getPk() {
		return pk;
	}

	public void setPk(BeanWhItemmastPk pk) {
		this.pk = pk;
	}
	/**
	 * 
	 * 
	 * @campo ItemTipo
	*/
	public String getItemtipo() {
		return itemtipo;
	}

	/**
	 * 
	 * 
	 * @campo ItemTipo
	*/
	public void setItemtipo(String itemtipo) {
		this.itemtipo = itemtipo;
	}
	/**
	 * 
	 * 
	 * @campo MarcaCodigo
	*/
	public String getMarcacodigo() {
		return marcacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MarcaCodigo
	*/
	public void setMarcacodigo(String marcacodigo) {
		this.marcacodigo = marcacodigo;
	}
	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public String getLinea() {
		return linea;
	}

	/**
	 * 
	 * 
	 * @campo Linea
	*/
	public void setLinea(String linea) {
		this.linea = linea;
	}
	/**
	 * 
	 * 
	 * @campo Familia
	*/
	public String getFamilia() {
		return familia;
	}

	/**
	 * 
	 * 
	 * @campo Familia
	*/
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	/**
	 * 
	 * 
	 * @campo SubFamilia
	*/
	public String getSubfamilia() {
		return subfamilia;
	}

	/**
	 * 
	 * 
	 * @campo SubFamilia
	*/
	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionLocal
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
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
	 * @campo DescripcionCompleta
	*/
	public String getDescripcioncompleta() {
		return descripcioncompleta;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionCompleta
	*/
	public void setDescripcioncompleta(String descripcioncompleta) {
		this.descripcioncompleta = descripcioncompleta;
	}
	/**
	 * 
	 * 
	 * @campo NumeroDeParte
	*/
	public String getNumerodeparte() {
		return numerodeparte;
	}

	/**
	 * 
	 * 
	 * @campo NumeroDeParte
	*/
	public void setNumerodeparte(String numerodeparte) {
		this.numerodeparte = numerodeparte;
	}
	/**
	 * 
	 * 
	 * @campo CodigoInterno
	*/
	public String getCodigointerno() {
		return codigointerno;
	}

	/**
	 * 
	 * 
	 * @campo CodigoInterno
	*/
	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
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
	 * @campo UnidadCodigoDoble
	*/
	public String getUnidadcodigodoble() {
		return unidadcodigodoble;
	}

	/**
	 * 
	 * 
	 * @campo UnidadCodigoDoble
	*/
	public void setUnidadcodigodoble(String unidadcodigodoble) {
		this.unidadcodigodoble = unidadcodigodoble;
	}
	/**
	 * 
	 * 
	 * @campo PartidaArancelaria
	*/
	public String getPartidaarancelaria() {
		return partidaarancelaria;
	}

	/**
	 * 
	 * 
	 * @campo PartidaArancelaria
	*/
	public void setPartidaarancelaria(String partidaarancelaria) {
		this.partidaarancelaria = partidaarancelaria;
	}
	/**
	 * 
	 * 
	 * @campo Modelo
	*/
	public String getModelo() {
		return modelo;
	}

	/**
	 * 
	 * 
	 * @campo Modelo
	*/
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * 
	 * 
	 * @campo Color
	*/
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * 
	 * @campo Color
	*/
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 
	 * 
	 * @campo CodigoBarras
	*/
	public String getCodigobarras() {
		return codigobarras;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarras
	*/
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	/**
	 * 
	 * 
	 * @campo CodigoBarrasFabricante
	*/
	public String getCodigobarrasfabricante() {
		return codigobarrasfabricante;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarrasFabricante
	*/
	public void setCodigobarrasfabricante(String codigobarrasfabricante) {
		this.codigobarrasfabricante = codigobarrasfabricante;
	}
	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public String getMonedacodigo() {
		return monedacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MonedaCodigo
	*/
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	/**
	 * 
	 * 
	 * @campo PrecioCosto
	*/
	public java.math.BigDecimal getPreciocosto() {
		return preciocosto;
	}

	/**
	 * 
	 * 
	 * @campo PrecioCosto
	*/
	public void setPreciocosto(java.math.BigDecimal preciocosto) {
		this.preciocosto = preciocosto;
	}
	/**
	 * 
	 * 
	 * @campo PrecioUnitarioLocal
	*/
	public java.math.BigDecimal getPreciounitariolocal() {
		return preciounitariolocal;
	}

	/**
	 * 
	 * 
	 * @campo PrecioUnitarioLocal
	*/
	public void setPreciounitariolocal(java.math.BigDecimal preciounitariolocal) {
		this.preciounitariolocal = preciounitariolocal;
	}
	/**
	 * 
	 * 
	 * @campo PrecioUnitarioDolares
	*/
	public java.math.BigDecimal getPreciounitariodolares() {
		return preciounitariodolares;
	}

	/**
	 * 
	 * 
	 * @campo PrecioUnitarioDolares
	*/
	public void setPreciounitariodolares(java.math.BigDecimal preciounitariodolares) {
		this.preciounitariodolares = preciounitariodolares;
	}
	/**
	 * 
	 * 
	 * @campo ItemPrecioFlag
	*/
	public String getItemprecioflag() {
		return itemprecioflag;
	}

	/**
	 * 
	 * 
	 * @campo ItemPrecioFlag
	*/
	public void setItemprecioflag(String itemprecioflag) {
		this.itemprecioflag = itemprecioflag;
	}
	/**
	 * 
	 * 
	 * @campo ItemPrecioCodigo
	*/
	public String getItempreciocodigo() {
		return itempreciocodigo;
	}

	/**
	 * 
	 * 
	 * @campo ItemPrecioCodigo
	*/
	public void setItempreciocodigo(String itempreciocodigo) {
		this.itempreciocodigo = itempreciocodigo;
	}
	/**
	 * 
	 * 
	 * @campo DisponibleVentaFlag
	*/
	public String getDisponibleventaflag() {
		return disponibleventaflag;
	}

	/**
	 * 
	 * 
	 * @campo DisponibleVentaFlag
	*/
	public void setDisponibleventaflag(String disponibleventaflag) {
		this.disponibleventaflag = disponibleventaflag;
	}
	/**
	 * 
	 * 
	 * @campo ItemProcedencia
	*/
	public String getItemprocedencia() {
		return itemprocedencia;
	}

	/**
	 * 
	 * 
	 * @campo ItemProcedencia
	*/
	public void setItemprocedencia(String itemprocedencia) {
		this.itemprocedencia = itemprocedencia;
	}
	/**
	 * 
	 * 
	 * @campo ManejoxLoteFlag
	*/
	public String getManejoxloteflag() {
		return manejoxloteflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoxLoteFlag
	*/
	public void setManejoxloteflag(String manejoxloteflag) {
		this.manejoxloteflag = manejoxloteflag;
	}
	/**
	 * 
	 * 
	 * @campo ManejoxSerieFlag
	*/
	public String getManejoxserieflag() {
		return manejoxserieflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoxSerieFlag
	*/
	public void setManejoxserieflag(String manejoxserieflag) {
		this.manejoxserieflag = manejoxserieflag;
	}
	/**
	 * 
	 * 
	 * @campo ManejoxKitFlag
	*/
	public String getManejoxkitflag() {
		return manejoxkitflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoxKitFlag
	*/
	public void setManejoxkitflag(String manejoxkitflag) {
		this.manejoxkitflag = manejoxkitflag;
	}
	/**
	 * 
	 * 
	 * @campo AfectoImpuestoVentasFlag
	*/
	public String getAfectoimpuestoventasflag() {
		return afectoimpuestoventasflag;
	}

	/**
	 * 
	 * 
	 * @campo AfectoImpuestoVentasFlag
	*/
	public void setAfectoimpuestoventasflag(String afectoimpuestoventasflag) {
		this.afectoimpuestoventasflag = afectoimpuestoventasflag;
	}
	/**
	 * 
	 * 
	 * @campo AfectoImpuesto2Flag
	*/
	public String getAfectoimpuesto2flag() {
		return afectoimpuesto2flag;
	}

	/**
	 * 
	 * 
	 * @campo AfectoImpuesto2Flag
	*/
	public void setAfectoimpuesto2flag(String afectoimpuesto2flag) {
		this.afectoimpuesto2flag = afectoimpuesto2flag;
	}
	/**
	 * 
	 * 
	 * @campo RequisicionamientoAutomaticoFl
	*/
	public String getRequisicionamientoautomaticofl() {
		return requisicionamientoautomaticofl;
	}

	/**
	 * 
	 * 
	 * @campo RequisicionamientoAutomaticoFl
	*/
	public void setRequisicionamientoautomaticofl(String requisicionamientoautomaticofl) {
		this.requisicionamientoautomaticofl = requisicionamientoautomaticofl;
	}
	/**
	 * 
	 * 
	 * @campo DisponibleTransferenciaFlag
	*/
	public String getDisponibletransferenciaflag() {
		return disponibletransferenciaflag;
	}

	/**
	 * 
	 * 
	 * @campo DisponibleTransferenciaFlag
	*/
	public void setDisponibletransferenciaflag(String disponibletransferenciaflag) {
		this.disponibletransferenciaflag = disponibletransferenciaflag;
	}
	/**
	 * 
	 * 
	 * @campo DisponibleConsumoFlag
	*/
	public String getDisponibleconsumoflag() {
		return disponibleconsumoflag;
	}

	/**
	 * 
	 * 
	 * @campo DisponibleConsumoFlag
	*/
	public void setDisponibleconsumoflag(String disponibleconsumoflag) {
		this.disponibleconsumoflag = disponibleconsumoflag;
	}
	/**
	 * 
	 * 
	 * @campo FormularioFlag
	*/
	public String getFormularioflag() {
		return formularioflag;
	}

	/**
	 * 
	 * 
	 * @campo FormularioFlag
	*/
	public void setFormularioflag(String formularioflag) {
		this.formularioflag = formularioflag;
	}
	/**
	 * 
	 * 
	 * @campo FormularioNroJuegos
	*/
	public Integer getFormularionrojuegos() {
		return formularionrojuegos;
	}

	/**
	 * 
	 * 
	 * @campo FormularioNroJuegos
	*/
	public void setFormularionrojuegos(Integer formularionrojuegos) {
		this.formularionrojuegos = formularionrojuegos;
	}
	/**
	 * 
	 * 
	 * @campo ManejoxUnidadFlag
	*/
	public String getManejoxunidadflag() {
		return manejoxunidadflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoxUnidadFlag
	*/
	public void setManejoxunidadflag(String manejoxunidadflag) {
		this.manejoxunidadflag = manejoxunidadflag;
	}
	/**
	 * 
	 * 
	 * @campo ISOAplicableFlag
	*/
	public String getIsoaplicableflag() {
		return isoaplicableflag;
	}

	/**
	 * 
	 * 
	 * @campo ISOAplicableFlag
	*/
	public void setIsoaplicableflag(String isoaplicableflag) {
		this.isoaplicableflag = isoaplicableflag;
	}
	/**
	 * 
	 * 
	 * @campo CantidadDobleFlag
	*/
	public String getCantidaddobleflag() {
		return cantidaddobleflag;
	}

	/**
	 * 
	 * 
	 * @campo CantidadDobleFlag
	*/
	public void setCantidaddobleflag(String cantidaddobleflag) {
		this.cantidaddobleflag = cantidaddobleflag;
	}
	/**
	 * 
	 * 
	 * @campo ISONormaInterna
	*/
	public String getIsonormainterna() {
		return isonormainterna;
	}

	/**
	 * 
	 * 
	 * @campo ISONormaInterna
	*/
	public void setIsonormainterna(String isonormainterna) {
		this.isonormainterna = isonormainterna;
	}
	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	/**
	 * 
	 * 
	 * @campo ImageFile
	*/
	public String getImagefile() {
		return imagefile;
	}

	/**
	 * 
	 * 
	 * @campo ImageFile
	*/
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	/**
	 * 
	 * 
	 * @campo MapaFile
	*/
	public String getMapafile() {
		return mapafile;
	}

	/**
	 * 
	 * 
	 * @campo MapaFile
	*/
	public void setMapafile(String mapafile) {
		this.mapafile = mapafile;
	}
	/**
	 * 
	 * 
	 * @campo CuentaInventario
	*/
	public String getCuentainventario() {
		return cuentainventario;
	}

	/**
	 * 
	 * 
	 * @campo CuentaInventario
	*/
	public void setCuentainventario(String cuentainventario) {
		this.cuentainventario = cuentainventario;
	}
	/**
	 * 
	 * 
	 * @campo CuentaGasto
	*/
	public String getCuentagasto() {
		return cuentagasto;
	}

	/**
	 * 
	 * 
	 * @campo CuentaGasto
	*/
	public void setCuentagasto(String cuentagasto) {
		this.cuentagasto = cuentagasto;
	}
	/**
	 * 
	 * 
	 * @campo CuentaInversion
	*/
	public String getCuentainversion() {
		return cuentainversion;
	}

	/**
	 * 
	 * 
	 * @campo CuentaInversion
	*/
	public void setCuentainversion(String cuentainversion) {
		this.cuentainversion = cuentainversion;
	}
	/**
	 * 
	 * 
	 * @campo CuentaServicioTecnico
	*/
	public String getCuentaserviciotecnico() {
		return cuentaserviciotecnico;
	}

	/**
	 * 
	 * 
	 * @campo CuentaServicioTecnico
	*/
	public void setCuentaserviciotecnico(String cuentaserviciotecnico) {
		this.cuentaserviciotecnico = cuentaserviciotecnico;
	}
	/**
	 * 
	 * 
	 * @campo CuentaSalidaTerceros
	*/
	public String getCuentasalidaterceros() {
		return cuentasalidaterceros;
	}

	/**
	 * 
	 * 
	 * @campo CuentaSalidaTerceros
	*/
	public void setCuentasalidaterceros(String cuentasalidaterceros) {
		this.cuentasalidaterceros = cuentasalidaterceros;
	}
	/**
	 * 
	 * 
	 * @campo ElementoGasto
	*/
	public String getElementogasto() {
		return elementogasto;
	}

	/**
	 * 
	 * 
	 * @campo ElementoGasto
	*/
	public void setElementogasto(String elementogasto) {
		this.elementogasto = elementogasto;
	}
	/**
	 * 
	 * 
	 * @campo ElementoInversion
	*/
	public String getElementoinversion() {
		return elementoinversion;
	}

	/**
	 * 
	 * 
	 * @campo ElementoInversion
	*/
	public void setElementoinversion(String elementoinversion) {
		this.elementoinversion = elementoinversion;
	}
	/**
	 * 
	 * 
	 * @campo PartidaPresupuestal
	*/
	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}

	/**
	 * 
	 * 
	 * @campo PartidaPresupuestal
	*/
	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
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
	 * @campo LotedeCompra
	*/
	public Integer getLotedecompra() {
		return lotedecompra;
	}

	/**
	 * 
	 * 
	 * @campo LotedeCompra
	*/
	public void setLotedecompra(Integer lotedecompra) {
		this.lotedecompra = lotedecompra;
	}
	/**
	 * 
	 * 
	 * @campo LotedeDespacho
	*/
	public java.math.BigDecimal getLotededespacho() {
		return lotededespacho;
	}

	/**
	 * 
	 * 
	 * @campo LotedeDespacho
	*/
	public void setLotededespacho(java.math.BigDecimal lotededespacho) {
		this.lotededespacho = lotededespacho;
	}
	/**
	 * 
	 * 
	 * @campo PeriodicidadCompraMeses
	*/
	public java.math.BigDecimal getPeriodicidadcomprameses() {
		return periodicidadcomprameses;
	}

	/**
	 * 
	 * 
	 * @campo PeriodicidadCompraMeses
	*/
	public void setPeriodicidadcomprameses(java.math.BigDecimal periodicidadcomprameses) {
		this.periodicidadcomprameses = periodicidadcomprameses;
	}
	/**
	 * 
	 * 
	 * @campo EspecificacionTecnica
	*/
	public String getEspecificaciontecnica() {
		return especificaciontecnica;
	}

	/**
	 * 
	 * 
	 * @campo EspecificacionTecnica
	*/
	public void setEspecificaciontecnica(String especificaciontecnica) {
		this.especificaciontecnica = especificaciontecnica;
	}
	/**
	 * 
	 * 
	 * @campo Dimensiones
	*/
	public String getDimensiones() {
		return dimensiones;
	}

	/**
	 * 
	 * 
	 * @campo Dimensiones
	*/
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	/**
	 * 
	 * 
	 * @campo FactorEquivalenciaComercial
	*/
	public java.math.BigDecimal getFactorequivalenciacomercial() {
		return factorequivalenciacomercial;
	}

	/**
	 * 
	 * 
	 * @campo FactorEquivalenciaComercial
	*/
	public void setFactorequivalenciacomercial(java.math.BigDecimal factorequivalenciacomercial) {
		this.factorequivalenciacomercial = factorequivalenciacomercial;
	}
	/**
	 * 
	 * 
	 * @campo ABCCodigo
	*/
	public String getAbccodigo() {
		return abccodigo;
	}

	/**
	 * 
	 * 
	 * @campo ABCCodigo
	*/
	public void setAbccodigo(String abccodigo) {
		this.abccodigo = abccodigo;
	}
	/**
	 * 
	 * 
	 * @campo InventarioTolerancia
	*/
	public java.math.BigDecimal getInventariotolerancia() {
		return inventariotolerancia;
	}

	/**
	 * 
	 * 
	 * @campo InventarioTolerancia
	*/
	public void setInventariotolerancia(java.math.BigDecimal inventariotolerancia) {
		this.inventariotolerancia = inventariotolerancia;
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
	 * @campo CuentaVentas
	*/
	public String getCuentaventas() {
		return cuentaventas;
	}

	/**
	 * 
	 * 
	 * @campo CuentaVentas
	*/
	public void setCuentaventas(String cuentaventas) {
		this.cuentaventas = cuentaventas;
	}
	/**
	 * 
	 * 
	 * @campo UnidadCompra
	*/
	public String getUnidadcompra() {
		return unidadcompra;
	}

	/**
	 * 
	 * 
	 * @campo UnidadCompra
	*/
	public void setUnidadcompra(String unidadcompra) {
		this.unidadcompra = unidadcompra;
	}
	/**
	 * 
	 * 
	 * @campo LotedeCompraM3
	*/
	public java.math.BigDecimal getLotedecompram3() {
		return lotedecompram3;
	}

	/**
	 * 
	 * 
	 * @campo LotedeCompraM3
	*/
	public void setLotedecompram3(java.math.BigDecimal lotedecompram3) {
		this.lotedecompram3 = lotedecompram3;
	}
	/**
	 * 
	 * 
	 * @campo LotedeCompraKG
	*/
	public java.math.BigDecimal getLotedecomprakg() {
		return lotedecomprakg;
	}

	/**
	 * 
	 * 
	 * @campo LotedeCompraKG
	*/
	public void setLotedecomprakg(java.math.BigDecimal lotedecomprakg) {
		this.lotedecomprakg = lotedecomprakg;
	}
	/**
	 * 
	 * 
	 * @campo ControlCalidadFlag
	*/
	public String getControlcalidadflag() {
		return controlcalidadflag;
	}

	/**
	 * 
	 * 
	 * @campo ControlCalidadFlag
	*/
	public void setControlcalidadflag(String controlcalidadflag) {
		this.controlcalidadflag = controlcalidadflag;
	}
	/**
	 * 
	 * 
	 * @campo UnidadEmbalaje
	*/
	public String getUnidadembalaje() {
		return unidadembalaje;
	}

	/**
	 * 
	 * 
	 * @campo UnidadEmbalaje
	*/
	public void setUnidadembalaje(String unidadembalaje) {
		this.unidadembalaje = unidadembalaje;
	}
	/**
	 * 
	 * 
	 * @campo CuentaTransito
	*/
	public String getCuentatransito() {
		return cuentatransito;
	}

	/**
	 * 
	 * 
	 * @campo CuentaTransito
	*/
	public void setCuentatransito(String cuentatransito) {
		this.cuentatransito = cuentatransito;
	}
	/**
	 * 
	 * 
	 * @campo ManejoxKitSplitFlag
	*/
	public String getManejoxkitsplitflag() {
		return manejoxkitsplitflag;
	}

	/**
	 * 
	 * 
	 * @campo ManejoxKitSplitFlag
	*/
	public void setManejoxkitsplitflag(String manejoxkitsplitflag) {
		this.manejoxkitsplitflag = manejoxkitsplitflag;
	}
	/**
	 * 
	 * 
	 * @campo MapaCodigo
	*/
	public String getMapacodigo() {
		return mapacodigo;
	}

	/**
	 * 
	 * 
	 * @campo MapaCodigo
	*/
	public void setMapacodigo(String mapacodigo) {
		this.mapacodigo = mapacodigo;
	}
	/**
	 * 
	 * 
	 * @campo CantidadDobleFactor
	*/
	public java.math.BigDecimal getCantidaddoblefactor() {
		return cantidaddoblefactor;
	}

	/**
	 * 
	 * 
	 * @campo CantidadDobleFactor
	*/
	public void setCantidaddoblefactor(java.math.BigDecimal cantidaddoblefactor) {
		this.cantidaddoblefactor = cantidaddoblefactor;
	}
	/**
	 * 
	 * 
	 * @campo SubFamiliaInferior
	*/
	public String getSubfamiliainferior() {
		return subfamiliainferior;
	}

	/**
	 * 
	 * 
	 * @campo SubFamiliaInferior
	*/
	public void setSubfamiliainferior(String subfamiliainferior) {
		this.subfamiliainferior = subfamiliainferior;
	}
	/**
	 * 
	 * 
	 * @campo ClasificacionComercial
	*/
	public String getClasificacioncomercial() {
		return clasificacioncomercial;
	}

	/**
	 * 
	 * 
	 * @campo ClasificacionComercial
	*/
	public void setClasificacioncomercial(String clasificacioncomercial) {
		this.clasificacioncomercial = clasificacioncomercial;
	}
	/**
	 * 
	 * 
	 * @campo StockMinimo
	*/
	public java.math.BigDecimal getStockminimo() {
		return stockminimo;
	}

	/**
	 * 
	 * 
	 * @campo StockMinimo
	*/
	public void setStockminimo(java.math.BigDecimal stockminimo) {
		this.stockminimo = stockminimo;
	}
	/**
	 * 
	 * 
	 * @campo StockMaximo
	*/
	public java.math.BigDecimal getStockmaximo() {
		return stockmaximo;
	}

	/**
	 * 
	 * 
	 * @campo StockMaximo
	*/
	public void setStockmaximo(java.math.BigDecimal stockmaximo) {
		this.stockmaximo = stockmaximo;
	}
	/**
	 * 
	 * 
	 * @campo LotedeVenta
	*/
	public Integer getLotedeventa() {
		return lotedeventa;
	}

	/**
	 * 
	 * 
	 * @campo LotedeVenta
	*/
	public void setLotedeventa(Integer lotedeventa) {
		this.lotedeventa = lotedeventa;
	}
	/**
	 * 
	 * 
	 * @campo DescripcionAdicional
	*/
	public String getDescripcionadicional() {
		return descripcionadicional;
	}

	/**
	 * 
	 * 
	 * @campo DescripcionAdicional
	*/
	public void setDescripcionadicional(String descripcionadicional) {
		this.descripcionadicional = descripcionadicional;
	}
	/**
	 * 
	 * 
	 * @campo CodigoPrecio
	*/
	public String getCodigoprecio() {
		return codigoprecio;
	}

	/**
	 * 
	 * 
	 * @campo CodigoPrecio
	*/
	public void setCodigoprecio(String codigoprecio) {
		this.codigoprecio = codigoprecio;
	}
	/**
	 * 
	 * 
	 * @campo CaracteristicaValor01
	*/
	public String getCaracteristicavalor01() {
		return caracteristicavalor01;
	}

	/**
	 * 
	 * 
	 * @campo CaracteristicaValor01
	*/
	public void setCaracteristicavalor01(String caracteristicavalor01) {
		this.caracteristicavalor01 = caracteristicavalor01;
	}
	/**
	 * 
	 * 
	 * @campo CaracteristicaValor02
	*/
	public String getCaracteristicavalor02() {
		return caracteristicavalor02;
	}

	/**
	 * 
	 * 
	 * @campo CaracteristicaValor02
	*/
	public void setCaracteristicavalor02(String caracteristicavalor02) {
		this.caracteristicavalor02 = caracteristicavalor02;
	}
	/**
	 * 
	 * 
	 * @campo CaracteristicaValor03
	*/
	public String getCaracteristicavalor03() {
		return caracteristicavalor03;
	}

	/**
	 * 
	 * 
	 * @campo CaracteristicaValor03
	*/
	public void setCaracteristicavalor03(String caracteristicavalor03) {
		this.caracteristicavalor03 = caracteristicavalor03;
	}
	/**
	 * 
	 * 
	 * @campo CaracteristicaValor04
	*/
	public String getCaracteristicavalor04() {
		return caracteristicavalor04;
	}

	/**
	 * 
	 * 
	 * @campo CaracteristicaValor04
	*/
	public void setCaracteristicavalor04(String caracteristicavalor04) {
		this.caracteristicavalor04 = caracteristicavalor04;
	}
	/**
	 * 
	 * 
	 * @campo CaracteristicaValor05
	*/
	public String getCaracteristicavalor05() {
		return caracteristicavalor05;
	}

	/**
	 * 
	 * 
	 * @campo CaracteristicaValor05
	*/
	public void setCaracteristicavalor05(String caracteristicavalor05) {
		this.caracteristicavalor05 = caracteristicavalor05;
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
	 * @campo CodigoBarras2
	*/
	public String getCodigobarras2() {
		return codigobarras2;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarras2
	*/
	public void setCodigobarras2(String codigobarras2) {
		this.codigobarras2 = codigobarras2;
	}
	/**
	 * 
	 * 
	 * @campo ReferenciaFiscalIngreso02
	*/
	public String getReferenciafiscalingreso02() {
		return referenciafiscalingreso02;
	}

	/**
	 * 
	 * 
	 * @campo ReferenciaFiscalIngreso02
	*/
	public void setReferenciafiscalingreso02(String referenciafiscalingreso02) {
		this.referenciafiscalingreso02 = referenciafiscalingreso02;
	}
	/**
	 * 
	 * 
	 * @campo CodigoCatalogo25
	*/
	public String getCodigocatalogo25() {
		return codigocatalogo25;
	}

	/**
	 * 
	 * 
	 * @campo CodigoCatalogo25
	*/
	public void setCodigocatalogo25(String codigocatalogo25) {
		this.codigocatalogo25 = codigocatalogo25;
	}
	/**
	 * 
	 * 
	 * @campo CodigoGS1
	*/
	public String getCodigogs1() {
		return codigogs1;
	}

	/**
	 * 
	 * 
	 * @campo CodigoGS1
	*/
	public void setCodigogs1(String codigogs1) {
		this.codigogs1 = codigogs1;
	}

}
