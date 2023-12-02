package net.royal.spring.core.dominio;

import java.math.BigDecimal;
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
 * Maestro miscelaneos detalle
 * 
 * @tabla MA_MISCELANEOSDETALLE
*/
@Entity
@Table(name = "MA_MISCELANEOSDETALLE")
public class BeanMaMiscelaneosdetalle extends DominioTransaccion implements java.io.Serializable{


	@EmbeddedId
	private BeanMaMiscelaneosdetallePk pk;

	@Size(min = 0, max = 200)
	@Column(name = "DESCRIPCIONLOCAL", length = 200, nullable = true)
	private String descripcionlocal;

	@Size(min = 0, max = 200)
	@Column(name = "DESCRIPCIONEXTRANJERA", length = 200, nullable = true)
	private String descripcionextranjera;

	@Column(name = "VALORNUMERICO", precision = 126,scale =0, nullable = true)
	private BigDecimal valornumerico;	

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "VALORFECHA", nullable = true)
	private java.util.Date valorfecha;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 4000)
	@Column(name = "VALORCODIGO1", length = 4000, nullable = true)
	private String valorcodigo1;
	
	
	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;


	public BeanMaMiscelaneosdetalle() {
		pk = new BeanMaMiscelaneosdetallePk();
	}


	public BeanMaMiscelaneosdetalle(BeanMaMiscelaneosdetallePk pk) {
		this.pk = pk;
	}

	public BeanMaMiscelaneosdetallePk getPk() {
		return pk;
	}

	public void setPk(BeanMaMiscelaneosdetallePk pk) {
		this.pk = pk;
	}
	/**
	 * Descripcion local 
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	/**
	 * Descripcion local 
	 * 
	 * @campo DESCRIPCIONLOCAL
	*/
	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}
	/**
	 * Descripcion extranjera 
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public String getDescripcionextranjera() {
		return descripcionextranjera;
	}

	/**
	 * Descripcion extranjera 
	 * 
	 * @campo DESCRIPCIONEXTRANJERA
	*/
	public void setDescripcionextranjera(String descripcionextranjera) {
		this.descripcionextranjera = descripcionextranjera;
	}
	/**
	 * Valor numerico 
	 * 
	 * @campo VALORNUMERICO
	*/
	public BigDecimal getValornumerico() {
		return valornumerico;
	}

	/**
	 * Valor numerico 
	 * 
	 * @campo VALORNUMERICO
	*/
	public void setValornumerico(BigDecimal valornumerico) {
		this.valornumerico = valornumerico;
	}	
	/**
	 * Fecha del valor 
	 * 
	 * @campo VALORFECHA
	*/
	public java.util.Date getValorfecha() {
		return valorfecha;
	}

	/**
	 * Fecha del valor 
	 * 
	 * @campo VALORFECHA
	*/
	public void setValorfecha(java.util.Date valorfecha) {
		this.valorfecha = valorfecha;
	}
	/**
	 * Estado 
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * Estado 
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * Ultimo Usuario de Modificacion
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * Ultima Fecha de Modificacion
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}


	public String getValorcodigo1() {
		return valorcodigo1;
	}


	public void setValorcodigo1(String valorcodigo1) {
		this.valorcodigo1 = valorcodigo1;
	}
	

}
