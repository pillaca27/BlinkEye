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
 * @tabla dbo.WH_ClaseFamilia
*/
@Entity
@Table(name = "WH_CLASEFAMILIA")
public class BeanWhClasefamilia extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanWhClasefamiliaPk pk;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONLOCAL", length = 50, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 50)
	@Column(name = "DESCRIPCIONINGLES", length = 50, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 100)
	@Column(name = "DESCRIPCIONCOMPLETA", length = 100, nullable = true)
	private String descripcioncompleta;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAINVENTARIO", length = 20, nullable = true)
	private String cuentainventario;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAGASTO", length = 20, nullable = true)
	private String cuentagasto;

	@Size(min = 0, max = 6)
	@Column(name = "ELEMENTOGASTO", length = 6, nullable = true)
	private String elementogasto;

	@Size(min = 0, max = 4)
	@Column(name = "PARTIDAPRESUPUESTAL", length = 4, nullable = true)
	private String partidapresupuestal;

	@Size(min = 0, max = 4)
	@Column(name = "FLUJODECAJA", length = 4, nullable = true)
	private String flujodecaja;

	@Size(min = 0, max = 12)
	@Column(name = "LINEAFAMILIA", length = 12, nullable = true)
	private String lineafamilia;

	@Size(min = 0, max = 1)
	@Column(name = "LOTEVALIDACIONFLAG", length = 1, nullable = true)
	private String lotevalidacionflag;

	@Size(min = 0, max = 1)
	@Column(name = "MEDICINAGRUPOAFLAG", length = 1, nullable = true)
	private String medicinagrupoaflag;

	@Size(min = 0, max = 1)
	@Column(name = "MEDICINAGRUPOHFLAG", length = 1, nullable = true)
	private String medicinagrupohflag;

	@Size(min = 0, max = 1)
	@Column(name = "MEDICINAGRUPOEFLAG", length = 1, nullable = true)
	private String medicinagrupoeflag;

	@Size(min = 0, max = 1)
	@Column(name = "MEDICINAGRUPORFLAG", length = 1, nullable = true)
	private String medicinagruporflag;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 10)
	@Column(name = "ULTIMOUSUARIO", length = 10, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTAVENTAS", length = 20, nullable = true)
	private String cuentaventas;

	@Size(min = 0, max = 20)
	@Column(name = "CUENTATRANSITO", length = 20, nullable = true)
	private String cuentatransito;

	@Size(min = 0, max = 50)
	@Column(name = "CONTACTONOMBRE", length = 50, nullable = true)
	private String contactonombre;

	@Size(min = 0, max = 10)
	@Column(name = "CONTACTOTELEFONO", length = 10, nullable = true)
	private String contactotelefono;

	@Size(min = 0, max = 10)
	@Column(name = "CONTACTOFAX", length = 10, nullable = true)
	private String contactofax;

	@Size(min = 0, max = 50)
	@Column(name = "CONTACTOEMAIL", length = 50, nullable = true)
	private String contactoemail;

	@Size(min = 0, max = 3)
	@Column(name = "CARACTERISTICA01", length = 3, nullable = true)
	private String caracteristica01;

	@Size(min = 0, max = 3)
	@Column(name = "CARACTERISTICA02", length = 3, nullable = true)
	private String caracteristica02;

	@Size(min = 0, max = 3)
	@Column(name = "CARACTERISTICA03", length = 3, nullable = true)
	private String caracteristica03;

	@Size(min = 0, max = 3)
	@Column(name = "CARACTERISTICA04", length = 3, nullable = true)
	private String caracteristica04;

	@Size(min = 0, max = 3)
	@Column(name = "CARACTERISTICA05", length = 3, nullable = true)
	private String caracteristica05;

	@Size(min = 0, max = 20)
	@Column(name = "REFERENCIAFISCAL02", length = 20, nullable = true)
	private String referenciafiscal02;


	public BeanWhClasefamilia() {
		pk = new BeanWhClasefamiliaPk();
	}


	public BeanWhClasefamilia(BeanWhClasefamiliaPk pk) {
		this.pk = pk;
	}

	public BeanWhClasefamiliaPk getPk() {
		return pk;
	}

	public void setPk(BeanWhClasefamiliaPk pk) {
		this.pk = pk;
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
	 * @campo LineaFamilia
	*/
	public String getLineafamilia() {
		return lineafamilia;
	}

	/**
	 * 
	 * 
	 * @campo LineaFamilia
	*/
	public void setLineafamilia(String lineafamilia) {
		this.lineafamilia = lineafamilia;
	}
	/**
	 * 
	 * 
	 * @campo LoteValidacionFlag
	*/
	public String getLotevalidacionflag() {
		return lotevalidacionflag;
	}

	/**
	 * 
	 * 
	 * @campo LoteValidacionFlag
	*/
	public void setLotevalidacionflag(String lotevalidacionflag) {
		this.lotevalidacionflag = lotevalidacionflag;
	}
	/**
	 * 
	 * 
	 * @campo MedicinaGrupoAFlag
	*/
	public String getMedicinagrupoaflag() {
		return medicinagrupoaflag;
	}

	/**
	 * 
	 * 
	 * @campo MedicinaGrupoAFlag
	*/
	public void setMedicinagrupoaflag(String medicinagrupoaflag) {
		this.medicinagrupoaflag = medicinagrupoaflag;
	}
	/**
	 * 
	 * 
	 * @campo MedicinaGrupoHFlag
	*/
	public String getMedicinagrupohflag() {
		return medicinagrupohflag;
	}

	/**
	 * 
	 * 
	 * @campo MedicinaGrupoHFlag
	*/
	public void setMedicinagrupohflag(String medicinagrupohflag) {
		this.medicinagrupohflag = medicinagrupohflag;
	}
	/**
	 * 
	 * 
	 * @campo MedicinaGrupoEFlag
	*/
	public String getMedicinagrupoeflag() {
		return medicinagrupoeflag;
	}

	/**
	 * 
	 * 
	 * @campo MedicinaGrupoEFlag
	*/
	public void setMedicinagrupoeflag(String medicinagrupoeflag) {
		this.medicinagrupoeflag = medicinagrupoeflag;
	}
	/**
	 * 
	 * 
	 * @campo MedicinaGrupoRFlag
	*/
	public String getMedicinagruporflag() {
		return medicinagruporflag;
	}

	/**
	 * 
	 * 
	 * @campo MedicinaGrupoRFlag
	*/
	public void setMedicinagruporflag(String medicinagruporflag) {
		this.medicinagruporflag = medicinagruporflag;
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
	 * @campo ContactoNombre
	*/
	public String getContactonombre() {
		return contactonombre;
	}

	/**
	 * 
	 * 
	 * @campo ContactoNombre
	*/
	public void setContactonombre(String contactonombre) {
		this.contactonombre = contactonombre;
	}
	/**
	 * 
	 * 
	 * @campo ContactoTelefono
	*/
	public String getContactotelefono() {
		return contactotelefono;
	}

	/**
	 * 
	 * 
	 * @campo ContactoTelefono
	*/
	public void setContactotelefono(String contactotelefono) {
		this.contactotelefono = contactotelefono;
	}
	/**
	 * 
	 * 
	 * @campo ContactoFax
	*/
	public String getContactofax() {
		return contactofax;
	}

	/**
	 * 
	 * 
	 * @campo ContactoFax
	*/
	public void setContactofax(String contactofax) {
		this.contactofax = contactofax;
	}
	/**
	 * 
	 * 
	 * @campo ContactoEMail
	*/
	public String getContactoemail() {
		return contactoemail;
	}

	/**
	 * 
	 * 
	 * @campo ContactoEMail
	*/
	public void setContactoemail(String contactoemail) {
		this.contactoemail = contactoemail;
	}
	/**
	 * 
	 * 
	 * @campo Caracteristica01
	*/
	public String getCaracteristica01() {
		return caracteristica01;
	}

	/**
	 * 
	 * 
	 * @campo Caracteristica01
	*/
	public void setCaracteristica01(String caracteristica01) {
		this.caracteristica01 = caracteristica01;
	}
	/**
	 * 
	 * 
	 * @campo Caracteristica02
	*/
	public String getCaracteristica02() {
		return caracteristica02;
	}

	/**
	 * 
	 * 
	 * @campo Caracteristica02
	*/
	public void setCaracteristica02(String caracteristica02) {
		this.caracteristica02 = caracteristica02;
	}
	/**
	 * 
	 * 
	 * @campo Caracteristica03
	*/
	public String getCaracteristica03() {
		return caracteristica03;
	}

	/**
	 * 
	 * 
	 * @campo Caracteristica03
	*/
	public void setCaracteristica03(String caracteristica03) {
		this.caracteristica03 = caracteristica03;
	}
	/**
	 * 
	 * 
	 * @campo Caracteristica04
	*/
	public String getCaracteristica04() {
		return caracteristica04;
	}

	/**
	 * 
	 * 
	 * @campo Caracteristica04
	*/
	public void setCaracteristica04(String caracteristica04) {
		this.caracteristica04 = caracteristica04;
	}
	/**
	 * 
	 * 
	 * @campo Caracteristica05
	*/
	public String getCaracteristica05() {
		return caracteristica05;
	}

	/**
	 * 
	 * 
	 * @campo Caracteristica05
	*/
	public void setCaracteristica05(String caracteristica05) {
		this.caracteristica05 = caracteristica05;
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

}
