package net.royal.spring.logistica.dominio;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;


/**
 * 
 * 
 * @tabla dbo.WH_CommoditySub
*/
@Entity
@Table(name = "WH_COMMODITYSUB")
public class BeanWhCommoditysub extends DominioTransaccion implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanWhCommoditysubPk pk;

	@Size(min = 0, max = 5)
	@Column(name = "COMMODITY", length = 5, nullable = true)
	private String commodity;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 6)
	@Column(name = "UNIDADPORDEFECTO", length = 6, nullable = true)
	private String unidadpordefecto;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTACONTABLEGASTO", length = 20, nullable = true)
	private String cuentacontablegasto;

	@Size(min = 0, max = 6)
	@Column(name = "ELEMENTOGASTO", length = 6, nullable = true)
	private String elementogasto;

	@Size(min = 0, max = 4)
	@Column(name = "PARTIDAPRESUPUESTAL", length = 4, nullable = true)
	private String partidapresupuestal;

	@Size(min = 0, max = 4)
	@Column(name = "FLUJODECAJA", length = 4, nullable = true)
	private String flujodecaja;

	@Size(min = 0, max = 15)
	@Column(name = "CLASIFICACIONACTIVO", length = 15, nullable = true)
	private String clasificacionactivo;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Column(name = "MONTOREFERENCIAL", precision = 19,scale =4, nullable = true)
	private java.math.BigDecimal montoreferencial;

	@Size(min = 0, max = 2)
	@Column(name = "MONTOREFERENCIALMONEDA", length = 2, nullable = true)
	private String montoreferencialmoneda;

	@Size(min = 0, max = 6)
	@Column(name = "REVISIONFLUJOCODIGO", length = 6, nullable = true)
	private String revisionflujocodigo;

	@Size(min = 0, max = 10)
	@Column(name = "PARTIDAARANCELARIA", length = 10, nullable = true)
	private String partidaarancelaria;

	@Size(min = 0, max = 60)
	@Column(name = "XDESCRIPCIONLOCAL", length = 60, nullable = true)
	private String xdescripcionlocal;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCAL02", length = 20, nullable = true)
	private String referenciafiscal02;

	@Size(min = 0, max = 6)
	@Column(name = "COMMODITYLINEA", length = 6, nullable = true)
	private String commoditylinea;

	@Size(min = 0, max = 6)
	@Column(name = "COMMODITYFAMILIA", length = 6, nullable = true)
	private String commodityfamilia;

	@Size(min = 0, max = 6)
	@Column(name = "COMMODITYSUBFAMILIA", length = 6, nullable = true)
	private String commoditysubfamilia;

	@Size(min = 0, max = 4)
	@Column(name = "DETRACCIONCODIGO", length = 4, nullable = true)
	private String detraccioncodigo;

	@Size(min = 0, max = 3)
	@Column(name = "COMMODITYGRUPOLINEA", length = 3, nullable = true)
	private String commoditygrupolinea;

	@Size(min = 0, max = 6)
	@Column(name = "ELEMENTOINVERSION", length = 6, nullable = true)
	private String elementoinversion;


	public BeanWhCommoditysub() {
		pk = new BeanWhCommoditysubPk();
	}


	public BeanWhCommoditysub(BeanWhCommoditysubPk pk) {
		this.pk = pk;
	}

	public BeanWhCommoditysubPk getPk() {
		return pk;
	}

	public void setPk(BeanWhCommoditysubPk pk) {
		this.pk = pk;
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
	 * @campo UnidadporDefecto
	*/
	public String getUnidadpordefecto() {
		return unidadpordefecto;
	}

	/**
	 * 
	 * 
	 * @campo UnidadporDefecto
	*/
	public void setUnidadpordefecto(String unidadpordefecto) {
		this.unidadpordefecto = unidadpordefecto;
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
	 * @campo ClasificacionActivo
	*/
	public String getClasificacionactivo() {
		return clasificacionactivo;
	}

	/**
	 * 
	 * 
	 * @campo ClasificacionActivo
	*/
	public void setClasificacionactivo(String clasificacionactivo) {
		this.clasificacionactivo = clasificacionactivo;
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
	 * @campo MontoReferencial
	*/
	public java.math.BigDecimal getMontoreferencial() {
		return montoreferencial;
	}

	/**
	 * 
	 * 
	 * @campo MontoReferencial
	*/
	public void setMontoreferencial(java.math.BigDecimal montoreferencial) {
		this.montoreferencial = montoreferencial;
	}
	/**
	 * 
	 * 
	 * @campo MontoReferencialMoneda
	*/
	public String getMontoreferencialmoneda() {
		return montoreferencialmoneda;
	}

	/**
	 * 
	 * 
	 * @campo MontoReferencialMoneda
	*/
	public void setMontoreferencialmoneda(String montoreferencialmoneda) {
		this.montoreferencialmoneda = montoreferencialmoneda;
	}
	/**
	 * 
	 * 
	 * @campo RevisionFlujoCodigo
	*/
	public String getRevisionflujocodigo() {
		return revisionflujocodigo;
	}

	/**
	 * 
	 * 
	 * @campo RevisionFlujoCodigo
	*/
	public void setRevisionflujocodigo(String revisionflujocodigo) {
		this.revisionflujocodigo = revisionflujocodigo;
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
	 * @campo xDescripcionLocal
	*/
	public String getXdescripcionlocal() {
		return xdescripcionlocal;
	}

	/**
	 * 
	 * 
	 * @campo xDescripcionLocal
	*/
	public void setXdescripcionlocal(String xdescripcionlocal) {
		this.xdescripcionlocal = xdescripcionlocal;
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
	 * @campo CommodityLinea
	*/
	public String getCommoditylinea() {
		return commoditylinea;
	}

	/**
	 * 
	 * 
	 * @campo CommodityLinea
	*/
	public void setCommoditylinea(String commoditylinea) {
		this.commoditylinea = commoditylinea;
	}
	/**
	 * 
	 * 
	 * @campo CommodityFamilia
	*/
	public String getCommodityfamilia() {
		return commodityfamilia;
	}

	/**
	 * 
	 * 
	 * @campo CommodityFamilia
	*/
	public void setCommodityfamilia(String commodityfamilia) {
		this.commodityfamilia = commodityfamilia;
	}
	/**
	 * 
	 * 
	 * @campo CommoditySubFamilia
	*/
	public String getCommoditysubfamilia() {
		return commoditysubfamilia;
	}

	/**
	 * 
	 * 
	 * @campo CommoditySubFamilia
	*/
	public void setCommoditysubfamilia(String commoditysubfamilia) {
		this.commoditysubfamilia = commoditysubfamilia;
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
	 * @campo CommodityGrupoLinea
	*/
	public String getCommoditygrupolinea() {
		return commoditygrupolinea;
	}

	/**
	 * 
	 * 
	 * @campo CommodityGrupoLinea
	*/
	public void setCommoditygrupolinea(String commoditygrupolinea) {
		this.commoditygrupolinea = commoditygrupolinea;
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

}
