package net.royal.spring.logistica.dominio.lista;

import net.royal.spring.logistica.dominio.BeanWhItemmast;

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
public class DtlComunWhItemmast extends DominioTransaccion implements java.io.Serializable{


	private String item;
	private String itemtipo;
	private String marcacodigo;
	private String linea;
	private String familia;
	private String subfamilia;
	private String descripcionlocal;
	private String descripcioningles;
	private String descripcioncompleta;
	private String numerodeparte;
	private String codigointerno;
	private String unidadcodigo;
	private String unidadcodigodoble;
	private String partidaarancelaria;
	private String modelo;
	private String color;
	private String codigobarras;
	private String codigobarrasfabricante;
	private String monedacodigo;
	private java.math.BigDecimal preciocosto;
	private java.math.BigDecimal preciounitariolocal;
	private java.math.BigDecimal preciounitariodolares;
	private String itemprecioflag;
	private String itempreciocodigo;
	private String disponibleventaflag;
	private String itemprocedencia;
	private String manejoxloteflag;
	private String manejoxserieflag;
	private String manejoxkitflag;
	private String afectoimpuestoventasflag;
	private String afectoimpuesto2flag;
	private String requisicionamientoautomaticofl;
	private String disponibletransferenciaflag;
	private String disponibleconsumoflag;
	private String formularioflag;
	private Integer formularionrojuegos;
	private String manejoxunidadflag;
	private String isoaplicableflag;
	private String cantidaddobleflag;
	private String isonormainterna;
	private String unidadreplicacion;
	private String imagefile;
	private String mapafile;
	private String cuentainventario;
	private String cuentagasto;
	private String cuentainversion;
	private String cuentaserviciotecnico;
	private String cuentasalidaterceros;
	private String elementogasto;
	private String elementoinversion;
	private String partidapresupuestal;
	private String flujodecaja;
	private Integer lotedecompra;
	private java.math.BigDecimal lotededespacho;
	private java.math.BigDecimal periodicidadcomprameses;
	private String especificaciontecnica;
	private String dimensiones;
	private java.math.BigDecimal factorequivalenciacomercial;
	private String abccodigo;
	private java.math.BigDecimal inventariotolerancia;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String cuentaventas;
	private String unidadcompra;
	private java.math.BigDecimal lotedecompram3;
	private java.math.BigDecimal lotedecomprakg;
	private String controlcalidadflag;
	private String unidadembalaje;
	private String cuentatransito;
	private String manejoxkitsplitflag;
	private String mapacodigo;
	private java.math.BigDecimal cantidaddoblefactor;
	private String subfamiliainferior;
	private String clasificacioncomercial;
	private java.math.BigDecimal stockminimo;
	private java.math.BigDecimal stockmaximo;
	private Integer lotedeventa;
	private String descripcionadicional;
	private String codigoprecio;
	private String caracteristicavalor01;
	private String caracteristicavalor02;
	private String caracteristicavalor03;
	private String caracteristicavalor04;
	private String caracteristicavalor05;
	private String referenciafiscal02;
	private String detraccioncodigo;
	private String codigobarras2;
	private String referenciafiscalingreso02;
	private String codigocatalogo25;
	private String codigogs1;
	private String familiadescripcion;
	private String subfamiliadescripcion;
	private String estadodescripcion;
	
	public String getEstadodescripcion() {
		return estadodescripcion;
	}
	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}
	
	

	public String getFamiliadescripcion() {
		return familiadescripcion;
	}

	public void setFamiliadescripcion(String familiadescripcion) {
		this.familiadescripcion = familiadescripcion;
	}

	public String getSubfamiliadescripcion() {
		return subfamiliadescripcion;
	}

	public void setSubfamiliadescripcion(String subfamiliadescripcion) {
		this.subfamiliadescripcion = subfamiliadescripcion;
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
