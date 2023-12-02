package net.royal.spring.logistica.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.logistica.dominio.BeanWhItemmast;

public class DtoComunWhItemmast extends DominioTransaccion{
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
	private BigDecimal preciocosto;
	private BigDecimal preciounitariolocal;
	private BigDecimal preciounitariodolares;
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
	private BigDecimal lotededespacho;
	private BigDecimal periodicidadcomprameses;
	private String especificaciontecnica;
	private String dimensiones;
	private BigDecimal factorequivalenciacomercial;
	private String abccodigo;
	private BigDecimal inventariotolerancia;
	private String estado;
	private java.util.Date ultimafechamodif;
	private String ultimousuario;
	private String cuentaventas;
	private String unidadcompra;
	private BigDecimal lotedecompram3;
	private BigDecimal lotedecomprakg;
	private String controlcalidadflag;
	private String unidadembalaje;
	private String cuentatransito;
	private String manejoxkitsplitflag;
	private String mapacodigo;
	private BigDecimal cantidaddoblefactor;
	private String subfamiliainferior;
	private String clasificacioncomercial;
	private String caracteristicavalor01;
	private String caracteristicavalor02;
	private String caracteristicavalor03;
	private String caracteristicavalor04;
	private String caracteristicavalor05;
	private String codigobarras2;
	private String codigoprecio;
	private String detraccioncodigo;
	private String referenciafiscal02;
	private String referenciafiscalingreso02;
	private BigDecimal stockmaximo;
	private BigDecimal stockminimo;
	private Integer lotedeventa;
	private String paisfabricacion;
	private String flujodecajaingreso;
	private String activofijoflag;
	private String descripcionadicional;
	private String centrocosto;
	private String clasificacionactivo;
	private String categoriaactivo;
	private String referenciafiscal02inv;
	private String codigocatalogo25;
	private String itemBien;
	
	@JsonIgnore
	private BigDecimal ROWNUM_;
	
	
	 
	   
	private String codigogs1;
	
	private String lineaDescri;
	private String familiaDescri;
	private String subFamiliaDescri;
	
	private String accionItem;
	private Integer parametroDigitos;
	private Boolean subfamily;
	
	//DETALLE WH_ITEMMAST
	private List<DtoComunWhItemunidad>lstItemUnidad= new ArrayList<DtoComunWhItemunidad>();
	private List<DtoComunWhItemkit>lstItemKit= new ArrayList<DtoComunWhItemkit>();
	
	public DtoComunWhItemmast() {}
	public DtoComunWhItemmast(String item) {
		this.item=item;
		this.lstItemUnidad = new ArrayList<DtoComunWhItemunidad>();
		this.lstItemKit = new ArrayList<DtoComunWhItemkit>();
	}
	
	public String getCodigogs1() {
		return codigogs1;
	}
	public void setCodigogs1(String codigogs1) {
		this.codigogs1 = codigogs1;
	}
	public String getLineaDescri() {
		return lineaDescri;
	}
	public void setLineaDescri(String lineaDescri) {
		this.lineaDescri = lineaDescri;
	}
	public String getFamiliaDescri() {
		return familiaDescri;
	}
	public void setFamiliaDescri(String familiaDescri) {
		this.familiaDescri = familiaDescri;
	}
	public String getSubFamiliaDescri() {
		return subFamiliaDescri;
	}
	public void setSubFamiliaDescri(String subFamiliaDescri) {
		this.subFamiliaDescri = subFamiliaDescri;
	}
	public String getAccionItem() {
		return accionItem;
	}
	public void setAccionItem(String accionItem) {
		this.accionItem = accionItem;
	}
	public Integer getParametroDigitos() {
		return parametroDigitos;
	}
	public void setParametroDigitos(Integer parametroDigitos) {
		this.parametroDigitos = parametroDigitos;
	}
	public Boolean getSubfamily() {
		return subfamily;
	}
	public void setSubfamily(Boolean subfamily) {
		this.subfamily = subfamily;
	}
	public List<DtoComunWhItemunidad> getLstItemUnidad() {
		return lstItemUnidad;
	}
	public void setLstItemUnidad(List<DtoComunWhItemunidad> lstItemUnidad) {
		this.lstItemUnidad = lstItemUnidad;
	}
	public List<DtoComunWhItemkit> getLstItemKit() {
		return lstItemKit;
	}
	public void setLstItemKit(List<DtoComunWhItemkit> lstItemKit) {
		this.lstItemKit = lstItemKit;
	}

	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getDescripcionlocal() {
		return descripcionlocal;
	}
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getItemtipo() {
		return itemtipo;
	}
	public void setItemtipo(String itemtipo) {
		this.itemtipo = itemtipo;
	}
	public String getMarcacodigo() {
		return marcacodigo;
	}
	public void setMarcacodigo(String marcacodigo) {
		this.marcacodigo = marcacodigo;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
	}
	public String getDescripcioningles() {
		return descripcioningles;
	}
	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}
	public String getDescripcioncompleta() {
		return descripcioncompleta;
	}
	public void setDescripcioncompleta(String descripcioncompleta) {
		this.descripcioncompleta = descripcioncompleta;
	}
	public String getNumerodeparte() {
		return numerodeparte;
	}
	public void setNumerodeparte(String numerodeparte) {
		this.numerodeparte = numerodeparte;
	}
	public String getCodigointerno() {
		return codigointerno;
	}
	public void setCodigointerno(String codigointerno) {
		this.codigointerno = codigointerno;
	}
	public String getUnidadcodigo() {
		return unidadcodigo;
	}
	public void setUnidadcodigo(String unidadcodigo) {
		this.unidadcodigo = unidadcodigo;
	}
	public String getUnidadcodigodoble() {
		return unidadcodigodoble;
	}
	public void setUnidadcodigodoble(String unidadcodigodoble) {
		this.unidadcodigodoble = unidadcodigodoble;
	}
	public String getPartidaarancelaria() {
		return partidaarancelaria;
	}
	public void setPartidaarancelaria(String partidaarancelaria) {
		this.partidaarancelaria = partidaarancelaria;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCodigobarras() {
		return codigobarras;
	}
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	public String getCodigobarrasfabricante() {
		return codigobarrasfabricante;
	}
	public void setCodigobarrasfabricante(String codigobarrasfabricante) {
		this.codigobarrasfabricante = codigobarrasfabricante;
	}
	public String getMonedacodigo() {
		return monedacodigo;
	}
	public void setMonedacodigo(String monedacodigo) {
		this.monedacodigo = monedacodigo;
	}
	public BigDecimal getPreciocosto() {
		return preciocosto;
	}
	public void setPreciocosto(BigDecimal preciocosto) {
		this.preciocosto = preciocosto;
	}
	public BigDecimal getPreciounitariolocal() {
		return preciounitariolocal;
	}
	public void setPreciounitariolocal(BigDecimal preciounitariolocal) {
		this.preciounitariolocal = preciounitariolocal;
	}
	public BigDecimal getPreciounitariodolares() {
		return preciounitariodolares;
	}
	public void setPreciounitariodolares(BigDecimal preciounitariodolares) {
		this.preciounitariodolares = preciounitariodolares;
	}
	public String getItemprecioflag() {
		return itemprecioflag;
	}
	public void setItemprecioflag(String itemprecioflag) {
		this.itemprecioflag = itemprecioflag;
	}
	public String getItempreciocodigo() {
		return itempreciocodigo;
	}
	public void setItempreciocodigo(String itempreciocodigo) {
		this.itempreciocodigo = itempreciocodigo;
	}
	public String getDisponibleventaflag() {
		return disponibleventaflag;
	}
	public void setDisponibleventaflag(String disponibleventaflag) {
		this.disponibleventaflag = disponibleventaflag;
	}
	public String getItemprocedencia() {
		return itemprocedencia;
	}
	public void setItemprocedencia(String itemprocedencia) {
		this.itemprocedencia = itemprocedencia;
	}
	public String getManejoxloteflag() {
		return manejoxloteflag;
	}
	public void setManejoxloteflag(String manejoxloteflag) {
		this.manejoxloteflag = manejoxloteflag;
	}
	public String getManejoxserieflag() {
		return manejoxserieflag;
	}
	public void setManejoxserieflag(String manejoxserieflag) {
		this.manejoxserieflag = manejoxserieflag;
	}
	public String getManejoxkitflag() {
		return manejoxkitflag;
	}
	public void setManejoxkitflag(String manejoxkitflag) {
		this.manejoxkitflag = manejoxkitflag;
	}
	public String getAfectoimpuestoventasflag() {
		return afectoimpuestoventasflag;
	}
	public void setAfectoimpuestoventasflag(String afectoimpuestoventasflag) {
		this.afectoimpuestoventasflag = afectoimpuestoventasflag;
	}
	public String getAfectoimpuesto2flag() {
		return afectoimpuesto2flag;
	}
	public void setAfectoimpuesto2flag(String afectoimpuesto2flag) {
		this.afectoimpuesto2flag = afectoimpuesto2flag;
	}
	public String getRequisicionamientoautomaticofl() {
		return requisicionamientoautomaticofl;
	}
	public void setRequisicionamientoautomaticofl(String requisicionamientoautomaticofl) {
		this.requisicionamientoautomaticofl = requisicionamientoautomaticofl;
	}
	public String getDisponibletransferenciaflag() {
		return disponibletransferenciaflag;
	}
	public void setDisponibletransferenciaflag(String disponibletransferenciaflag) {
		this.disponibletransferenciaflag = disponibletransferenciaflag;
	}
	public String getDisponibleconsumoflag() {
		return disponibleconsumoflag;
	}
	public void setDisponibleconsumoflag(String disponibleconsumoflag) {
		this.disponibleconsumoflag = disponibleconsumoflag;
	}
	public String getFormularioflag() {
		return formularioflag;
	}
	public void setFormularioflag(String formularioflag) {
		this.formularioflag = formularioflag;
	}
	public Integer getFormularionrojuegos() {
		return formularionrojuegos;
	}
	public void setFormularionrojuegos(Integer formularionrojuegos) {
		this.formularionrojuegos = formularionrojuegos;
	}
	public String getManejoxunidadflag() {
		return manejoxunidadflag;
	}
	public void setManejoxunidadflag(String manejoxunidadflag) {
		this.manejoxunidadflag = manejoxunidadflag;
	}
	public String getIsoaplicableflag() {
		return isoaplicableflag;
	}
	public void setIsoaplicableflag(String isoaplicableflag) {
		this.isoaplicableflag = isoaplicableflag;
	}
	public String getCantidaddobleflag() {
		return cantidaddobleflag;
	}
	public void setCantidaddobleflag(String cantidaddobleflag) {
		this.cantidaddobleflag = cantidaddobleflag;
	}
	public String getIsonormainterna() {
		return isonormainterna;
	}
	public void setIsonormainterna(String isonormainterna) {
		this.isonormainterna = isonormainterna;
	}
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public String getMapafile() {
		return mapafile;
	}
	public void setMapafile(String mapafile) {
		this.mapafile = mapafile;
	}
	public String getCuentainventario() {
		return cuentainventario;
	}
	public void setCuentainventario(String cuentainventario) {
		this.cuentainventario = cuentainventario;
	}
	public String getCuentagasto() {
		return cuentagasto;
	}
	public void setCuentagasto(String cuentagasto) {
		this.cuentagasto = cuentagasto;
	}
	public String getCuentainversion() {
		return cuentainversion;
	}
	public void setCuentainversion(String cuentainversion) {
		this.cuentainversion = cuentainversion;
	}
	public String getCuentaserviciotecnico() {
		return cuentaserviciotecnico;
	}
	public void setCuentaserviciotecnico(String cuentaserviciotecnico) {
		this.cuentaserviciotecnico = cuentaserviciotecnico;
	}
	public String getCuentasalidaterceros() {
		return cuentasalidaterceros;
	}
	public void setCuentasalidaterceros(String cuentasalidaterceros) {
		this.cuentasalidaterceros = cuentasalidaterceros;
	}
	public String getElementogasto() {
		return elementogasto;
	}
	public void setElementogasto(String elementogasto) {
		this.elementogasto = elementogasto;
	}
	public String getElementoinversion() {
		return elementoinversion;
	}
	public void setElementoinversion(String elementoinversion) {
		this.elementoinversion = elementoinversion;
	}
	public String getPartidapresupuestal() {
		return partidapresupuestal;
	}
	public void setPartidapresupuestal(String partidapresupuestal) {
		this.partidapresupuestal = partidapresupuestal;
	}
	public String getFlujodecaja() {
		return flujodecaja;
	}
	public void setFlujodecaja(String flujodecaja) {
		this.flujodecaja = flujodecaja;
	}
	public Integer getLotedecompra() {
		return lotedecompra;
	}
	public void setLotedecompra(Integer lotedecompra) {
		this.lotedecompra = lotedecompra;
	}
	public BigDecimal getLotededespacho() {
		return lotededespacho;
	}
	public void setLotededespacho(BigDecimal lotededespacho) {
		this.lotededespacho = lotededespacho;
	}
	public BigDecimal getPeriodicidadcomprameses() {
		return periodicidadcomprameses;
	}
	public void setPeriodicidadcomprameses(BigDecimal periodicidadcomprameses) {
		this.periodicidadcomprameses = periodicidadcomprameses;
	}
	public String getEspecificaciontecnica() {
		return especificaciontecnica;
	}
	public void setEspecificaciontecnica(String especificaciontecnica) {
		this.especificaciontecnica = especificaciontecnica;
	}
	public String getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	public BigDecimal getFactorequivalenciacomercial() {
		return factorequivalenciacomercial;
	}
	public void setFactorequivalenciacomercial(BigDecimal factorequivalenciacomercial) {
		this.factorequivalenciacomercial = factorequivalenciacomercial;
	}
	public String getAbccodigo() {
		return abccodigo;
	}
	public void setAbccodigo(String abccodigo) {
		this.abccodigo = abccodigo;
	}
	public BigDecimal getInventariotolerancia() {
		return inventariotolerancia;
	}
	public void setInventariotolerancia(BigDecimal inventariotolerancia) {
		this.inventariotolerancia = inventariotolerancia;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public String getCuentaventas() {
		return cuentaventas;
	}
	public void setCuentaventas(String cuentaventas) {
		this.cuentaventas = cuentaventas;
	}
	public String getUnidadcompra() {
		return unidadcompra;
	}
	public void setUnidadcompra(String unidadcompra) {
		this.unidadcompra = unidadcompra;
	}
	public BigDecimal getLotedecompram3() {
		return lotedecompram3;
	}
	public void setLotedecompram3(BigDecimal lotedecompram3) {
		this.lotedecompram3 = lotedecompram3;
	}
	public BigDecimal getLotedecomprakg() {
		return lotedecomprakg;
	}
	public void setLotedecomprakg(BigDecimal lotedecomprakg) {
		this.lotedecomprakg = lotedecomprakg;
	}
	public String getControlcalidadflag() {
		return controlcalidadflag;
	}
	public void setControlcalidadflag(String controlcalidadflag) {
		this.controlcalidadflag = controlcalidadflag;
	}
	public String getUnidadembalaje() {
		return unidadembalaje;
	}
	public void setUnidadembalaje(String unidadembalaje) {
		this.unidadembalaje = unidadembalaje;
	}
	public String getCuentatransito() {
		return cuentatransito;
	}
	public void setCuentatransito(String cuentatransito) {
		this.cuentatransito = cuentatransito;
	}
	public String getManejoxkitsplitflag() {
		return manejoxkitsplitflag;
	}
	public void setManejoxkitsplitflag(String manejoxkitsplitflag) {
		this.manejoxkitsplitflag = manejoxkitsplitflag;
	}
	public String getMapacodigo() {
		return mapacodigo;
	}
	public void setMapacodigo(String mapacodigo) {
		this.mapacodigo = mapacodigo;
	}
	public BigDecimal getCantidaddoblefactor() {
		return cantidaddoblefactor;
	}
	public void setCantidaddoblefactor(BigDecimal cantidaddoblefactor) {
		this.cantidaddoblefactor = cantidaddoblefactor;
	}
	public String getSubfamiliainferior() {
		return subfamiliainferior;
	}
	public void setSubfamiliainferior(String subfamiliainferior) {
		this.subfamiliainferior = subfamiliainferior;
	}
	public String getClasificacioncomercial() {
		return clasificacioncomercial;
	}
	public void setClasificacioncomercial(String clasificacioncomercial) {
		this.clasificacioncomercial = clasificacioncomercial;
	}
	public String getCaracteristicavalor01() {
		return caracteristicavalor01;
	}
	public void setCaracteristicavalor01(String caracteristicavalor01) {
		this.caracteristicavalor01 = caracteristicavalor01;
	}
	public String getCaracteristicavalor02() {
		return caracteristicavalor02;
	}
	public void setCaracteristicavalor02(String caracteristicavalor02) {
		this.caracteristicavalor02 = caracteristicavalor02;
	}
	public String getCaracteristicavalor03() {
		return caracteristicavalor03;
	}
	public void setCaracteristicavalor03(String caracteristicavalor03) {
		this.caracteristicavalor03 = caracteristicavalor03;
	}
	public String getCaracteristicavalor04() {
		return caracteristicavalor04;
	}
	public void setCaracteristicavalor04(String caracteristicavalor04) {
		this.caracteristicavalor04 = caracteristicavalor04;
	}
	public String getCaracteristicavalor05() {
		return caracteristicavalor05;
	}
	public void setCaracteristicavalor05(String caracteristicavalor05) {
		this.caracteristicavalor05 = caracteristicavalor05;
	}
	public String getCodigobarras2() {
		return codigobarras2;
	}
	public void setCodigobarras2(String codigobarras2) {
		this.codigobarras2 = codigobarras2;
	}
	public String getCodigoprecio() {
		return codigoprecio;
	}
	public void setCodigoprecio(String codigoprecio) {
		this.codigoprecio = codigoprecio;
	}
	public String getDetraccioncodigo() {
		return detraccioncodigo;
	}
	public void setDetraccioncodigo(String detraccioncodigo) {
		this.detraccioncodigo = detraccioncodigo;
	}
	public String getReferenciafiscal02() {
		return referenciafiscal02;
	}
	public void setReferenciafiscal02(String referenciafiscal02) {
		this.referenciafiscal02 = referenciafiscal02;
	}
	public String getReferenciafiscalingreso02() {
		return referenciafiscalingreso02;
	}
	public void setReferenciafiscalingreso02(String referenciafiscalingreso02) {
		this.referenciafiscalingreso02 = referenciafiscalingreso02;
	}
	public BigDecimal getStockmaximo() {
		return stockmaximo;
	}
	public void setStockmaximo(BigDecimal stockmaximo) {
		this.stockmaximo = stockmaximo;
	}
	public BigDecimal getStockminimo() {
		return stockminimo;
	}
	public void setStockminimo(BigDecimal stockminimo) {
		this.stockminimo = stockminimo;
	}
	public Integer getLotedeventa() {
		return lotedeventa;
	}
	public void setLotedeventa(Integer lotedeventa) {
		this.lotedeventa = lotedeventa;
	}
	public String getPaisfabricacion() {
		return paisfabricacion;
	}
	public void setPaisfabricacion(String paisfabricacion) {
		this.paisfabricacion = paisfabricacion;
	}
	public String getFlujodecajaingreso() {
		return flujodecajaingreso;
	}
	public void setFlujodecajaingreso(String flujodecajaingreso) {
		this.flujodecajaingreso = flujodecajaingreso;
	}
	public String getActivofijoflag() {
		return activofijoflag;
	}
	public void setActivofijoflag(String activofijoflag) {
		this.activofijoflag = activofijoflag;
	}
	public String getDescripcionadicional() {
		return descripcionadicional;
	}
	public void setDescripcionadicional(String descripcionadicional) {
		this.descripcionadicional = descripcionadicional;
	}
	public String getCentrocosto() {
		return centrocosto;
	}
	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}
	public String getClasificacionactivo() {
		return clasificacionactivo;
	}
	public void setClasificacionactivo(String clasificacionactivo) {
		this.clasificacionactivo = clasificacionactivo;
	}
	public String getCategoriaactivo() {
		return categoriaactivo;
	}
	public void setCategoriaactivo(String categoriaactivo) {
		this.categoriaactivo = categoriaactivo;
	}
	public String getReferenciafiscal02inv() {
		return referenciafiscal02inv;
	}
	public void setReferenciafiscal02inv(String referenciafiscal02inv) {
		this.referenciafiscal02inv = referenciafiscal02inv;
	}
	public String getCodigocatalogo25() {
		return codigocatalogo25;
	}
	public void setCodigocatalogo25(String codigocatalogo25) {
		this.codigocatalogo25 = codigocatalogo25;
	}
	public String getItemBien() {
		return itemBien;
	}
	public void setItemBien(String itemBien) {
		this.itemBien = itemBien;
	}
	
	public BeanWhItemmast obtenerBean() {
		BeanWhItemmast bean = new BeanWhItemmast();
		return obtenerBean(bean);
	}

	public BeanWhItemmast obtenerBean(BeanWhItemmast bean) {
		if (bean == null)
			bean = new BeanWhItemmast();

		bean.getPk().setItem(item);
		bean.setItemtipo(itemtipo);
		bean.setMarcacodigo(marcacodigo);
		bean.setLinea(linea);
		bean.setFamilia(familia);
		bean.setSubfamilia(subfamilia);
		bean.setDescripcionlocal(descripcionlocal);
		bean.setDescripcioningles(descripcioningles);
		bean.setDescripcioncompleta(descripcioncompleta);
		bean.setNumerodeparte(numerodeparte);
		bean.setCodigointerno(codigointerno);
		bean.setUnidadcodigo(unidadcodigo);
		bean.setUnidadcodigodoble(unidadcodigodoble);
		bean.setPartidaarancelaria(partidaarancelaria);
		bean.setModelo(modelo);
		bean.setColor(color);
		bean.setCodigobarras(codigobarras);
		bean.setCodigobarrasfabricante(codigobarrasfabricante);
		bean.setMonedacodigo(monedacodigo);
		bean.setPreciocosto(preciocosto);
		bean.setPreciounitariolocal(preciounitariolocal);
		bean.setPreciounitariodolares(preciounitariodolares);
		bean.setItemprecioflag(itemprecioflag);
		bean.setItempreciocodigo(itempreciocodigo);
		bean.setDisponibleventaflag(disponibleventaflag);
		bean.setItemprocedencia(itemprocedencia);
		bean.setManejoxloteflag(manejoxloteflag);
		bean.setManejoxserieflag(manejoxserieflag);
		bean.setManejoxkitflag(manejoxkitflag);
		bean.setAfectoimpuestoventasflag(afectoimpuestoventasflag);
		bean.setAfectoimpuesto2flag(afectoimpuesto2flag);
		bean.setRequisicionamientoautomaticofl(requisicionamientoautomaticofl);
		bean.setDisponibletransferenciaflag(disponibletransferenciaflag);
		bean.setDisponibleconsumoflag(disponibleconsumoflag);
		bean.setFormularioflag(formularioflag);
		bean.setFormularionrojuegos(formularionrojuegos);
		bean.setManejoxunidadflag(manejoxunidadflag);
		bean.setIsoaplicableflag(isoaplicableflag);
		bean.setCantidaddobleflag(cantidaddobleflag);
		bean.setIsonormainterna(isonormainterna);
		bean.setUnidadreplicacion(unidadreplicacion);
		bean.setImagefile(imagefile);
		bean.setMapafile(mapafile);
		bean.setCuentainventario(cuentainventario);
		bean.setCuentagasto(cuentagasto);
		bean.setCuentainversion(cuentainversion);
		bean.setCuentaserviciotecnico(cuentaserviciotecnico);
		bean.setCuentasalidaterceros(cuentasalidaterceros);
		bean.setElementogasto(elementogasto);
		bean.setElementoinversion(elementoinversion);
		bean.setPartidapresupuestal(partidapresupuestal);
		bean.setFlujodecaja(flujodecaja);
		bean.setLotedecompra(lotedecompra);
		bean.setLotededespacho(lotededespacho);
		bean.setPeriodicidadcomprameses(periodicidadcomprameses);
		bean.setEspecificaciontecnica(especificaciontecnica);
		bean.setDimensiones(dimensiones);
		bean.setFactorequivalenciacomercial(factorequivalenciacomercial);
		bean.setAbccodigo(abccodigo);
		bean.setInventariotolerancia(inventariotolerancia);
		bean.setEstado(estado);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUltimousuario(ultimousuario);
		bean.setCuentaventas(cuentaventas);
		bean.setUnidadcompra(unidadcompra);
		bean.setLotedecompram3(lotedecompram3);
		bean.setLotedecomprakg(lotedecomprakg);
		bean.setControlcalidadflag(controlcalidadflag);
		bean.setUnidadembalaje(unidadembalaje);
		bean.setCuentatransito(cuentatransito);
		bean.setManejoxkitsplitflag(manejoxkitsplitflag);
		bean.setMapacodigo(mapacodigo);
		bean.setCantidaddoblefactor(cantidaddoblefactor);
		bean.setSubfamiliainferior(subfamiliainferior);
		bean.setClasificacioncomercial(clasificacioncomercial);
		bean.setStockminimo(stockminimo);
		bean.setStockmaximo(stockmaximo);
		bean.setLotedeventa(lotedeventa);
		bean.setDescripcionadicional(descripcionadicional);
		bean.setCodigoprecio(codigoprecio);
		bean.setCaracteristicavalor01(caracteristicavalor01);
		bean.setCaracteristicavalor02(caracteristicavalor02);
		bean.setCaracteristicavalor03(caracteristicavalor03);
		bean.setCaracteristicavalor04(caracteristicavalor04);
		bean.setCaracteristicavalor05(caracteristicavalor05);
		bean.setReferenciafiscal02(referenciafiscal02);
		bean.setDetraccioncodigo(detraccioncodigo);
		bean.setCodigobarras2(codigobarras2);
		bean.setReferenciafiscalingreso02(referenciafiscalingreso02);
		bean.setCodigocatalogo25(codigocatalogo25);
		bean.setCodigogs1(codigogs1);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
