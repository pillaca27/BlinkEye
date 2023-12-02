package net.royal.spring.proveedores.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.GP_EVAASPECTO
*/
@Entity
@Table(name = "GP_EVAASPECTO")
public class BeanGpEvaaspecto extends DominioTransaccion implements java.io.Serializable{


//	@EmbeddedId
//	private GpEvaaspectoPk pk;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EASEVAASPECTOID", nullable = false)
	private Integer easevaaspectoid;

	@Size(min = 0, max = 1)
	@NotNull
	@NotEmpty
	@Column(name = "EASESTADO", length = 1, nullable = false)
	private String easestado;

	@NotNull
	@Column(name = "EVTEVATABLAID", nullable = false)
	private Integer evtevatablaid;

	private java.lang.Short easnivel;

	@Size(min = 0, max = 10)
	@Column(name = "EASASPECTO", length = 10, nullable = true)
	private String easaspecto;

	@Size(min = 0, max = 100)
	@Column(name = "EASDESCRIPCION", length = 100, nullable = true)
	private String easdescripcion;

	@NotNull
	@Column(name = "EASPUNTAJEMAXIMO", precision = 6,scale =2, nullable = false)
	private java.math.BigDecimal easpuntajemaximo;
	
	
	@Column(name = "EASREPRESENTATIVO", precision = 10,scale =4, nullable = true)
	private java.math.BigDecimal easrepresentativo;
	
	@Column(name = "EASPONDERACION", precision = 10,scale =4, nullable = true)
	private java.math.BigDecimal easponderacion;
	
	

	@Size(min = 0, max = 2147483647)
	@Column(name = "EASTEXTO", length = 16, nullable = true)
	private String eastexto;

	@Column(name = "EASEVAASPECTOIDPAPA", nullable = true)
	private Integer easevaaspectoidpapa;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "EASULTIMAFECHAMODIF", nullable = true)
	private java.util.Date easultimafechamodif;

	@Size(min = 0, max = 20)
	@Column(name = "EASULTIMOUSUARIO", length = 20, nullable = true)
	private String easultimousuario;


//	public GpEvaaspecto() {
//		pk = new GpEvaaspectoPk();
//	}
//
//
//	public GpEvaaspecto(GpEvaaspectoPk pk) {
//		this.pk = pk;
//	}
//
//	public GpEvaaspectoPk getPk() {
//		return pk;
//	}
//
//	public void setPk(GpEvaaspectoPk pk) {
//		this.pk = pk;
//	}
	
	
	
	
	
	/**
	 * 
	 * 
	 * @campo EASESTADO
	*/
	public String getEasestado() {
		return easestado;
	}

	public java.math.BigDecimal getEasponderacion() {
		return easponderacion;
	}

	public void setEasponderacion(java.math.BigDecimal easponderacion) {
		this.easponderacion = easponderacion;
	}

	public java.math.BigDecimal getEasrepresentativo() {
		return easrepresentativo;
	}

	public void setEasrepresentativo(java.math.BigDecimal easrepresentativo) {
		this.easrepresentativo = easrepresentativo;
	}

	public Integer getEasevaaspectoid() {
		return easevaaspectoid;
	}

	public void setEasevaaspectoid(Integer easevaaspectoid) {
		this.easevaaspectoid = easevaaspectoid;
	}

	/**
	 * 
	 * 
	 * @campo EASESTADO
	*/
	public void setEasestado(String easestado) {
		this.easestado = easestado;
	}
	/**
	 * 
	 * 
	 * @campo EVTEVATABLAID
	*/
	public Integer getEvtevatablaid() {
		return evtevatablaid;
	}

	/**
	 * 
	 * 
	 * @campo EVTEVATABLAID
	*/
	public void setEvtevatablaid(Integer evtevatablaid) {
		this.evtevatablaid = evtevatablaid;
	}
	/**
	 * 
	 * 
	 * @campo EASNIVEL
	*/
	public java.lang.Short getEasnivel() {
		return easnivel;
	}

	/**
	 * 
	 * 
	 * @campo EASNIVEL
	*/
	public void setEasnivel(java.lang.Short easnivel) {
		this.easnivel = easnivel;
	}
	/**
	 * 
	 * 
	 * @campo EASASPECTO
	*/
	public String getEasaspecto() {
		return easaspecto;
	}

	/**
	 * 
	 * 
	 * @campo EASASPECTO
	*/
	public void setEasaspecto(String easaspecto) {
		this.easaspecto = easaspecto;
	}
	/**
	 * 
	 * 
	 * @campo EASDESCRIPCION
	*/
	public String getEasdescripcion() {
		return easdescripcion;
	}

	/**
	 * 
	 * 
	 * @campo EASDESCRIPCION
	*/
	public void setEasdescripcion(String easdescripcion) {
		this.easdescripcion = easdescripcion;
	}
	/**
	 * 
	 * 
	 * @campo EASPUNTAJEMAXIMO
	*/
	public java.math.BigDecimal getEaspuntajemaximo() {
		return easpuntajemaximo;
	}

	/**
	 * 
	 * 
	 * @campo EASPUNTAJEMAXIMO
	*/
	public void setEaspuntajemaximo(java.math.BigDecimal easpuntajemaximo) {
		this.easpuntajemaximo = easpuntajemaximo;
	}
	/**
	 * 
	 * 
	 * @campo EASTEXTO
	*/
	public String getEastexto() {
		return eastexto;
	}

	/**
	 * 
	 * 
	 * @campo EASTEXTO
	*/
	public void setEastexto(String eastexto) {
		this.eastexto = eastexto;
	}
	/**
	 * 
	 * 
	 * @campo EASEVAASPECTOIDPAPA
	*/
	public Integer getEasevaaspectoidpapa() {
		return easevaaspectoidpapa;
	}

	/**
	 * 
	 * 
	 * @campo EASEVAASPECTOIDPAPA
	*/
	public void setEasevaaspectoidpapa(Integer easevaaspectoidpapa) {
		this.easevaaspectoidpapa = easevaaspectoidpapa;
	}
	/**
	 * 
	 * 
	 * @campo EASULTIMAFECHAMODIF
	*/
	public java.util.Date getEasultimafechamodif() {
		return easultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo EASULTIMAFECHAMODIF
	*/
	public void setEasultimafechamodif(java.util.Date easultimafechamodif) {
		this.easultimafechamodif = easultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo EASULTIMOUSUARIO
	*/
	public String getEasultimousuario() {
		return easultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo EASULTIMOUSUARIO
	*/
	public void setEasultimousuario(String easultimousuario) {
		this.easultimousuario = easultimousuario;
	}

}
