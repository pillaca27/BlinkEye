package net.royal.spring.core.dominio.dto;

import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;

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
 * @tabla MA_MAESTROMISCELANEODETALLE
*/
public class DtoComunMaMaestroMiscelaneodetalle extends DominioTransaccion implements java.io.Serializable{
	
	
	private String aplicacioncodigo;			
	private String maestrocodigo;			
	private String valorcodigo;			
	private String codigo01;			
	private String descripcionlocal;			
	private String codigo02;			
	private String descripcioningles;			
	private String codigo03;			
	private Integer numero01;			
	private String descripcion01;			
	private String estado;			
	private String ultimousuario;			
	private Date ultimafechamodif;			
	private String codtipo;
	
	

	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}
	
	

	public String getCodtipo() {
		return codtipo;
	}



	public void setCodtipo(String codtipo) {
		this.codtipo = codtipo;
	}



	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getMaestrocodigo() {
		return maestrocodigo;
	}

	public void setMaestrocodigo(String maestrocodigo) {
		this.maestrocodigo = maestrocodigo;
	}

	public String getValorcodigo() {
		return valorcodigo;
	}

	public void setValorcodigo(String valorcodigo) {
		this.valorcodigo = valorcodigo;
	}

	public String getCodigo01() {
		return codigo01;
	}

	public void setCodigo01(String codigo01) {
		this.codigo01 = codigo01;
	}

	public String getDescripcionlocal() {
		return descripcionlocal;
	}

	public void setDescripcionlocal(String descripcionlocal) {
		this.descripcionlocal = descripcionlocal;
	}

	public String getCodigo02() {
		return codigo02;
	}

	public void setCodigo02(String codigo02) {
		this.codigo02 = codigo02;
	}

	public String getDescripcioningles() {
		return descripcioningles;
	}

	public void setDescripcioningles(String descripcioningles) {
		this.descripcioningles = descripcioningles;
	}

	public String getCodigo03() {
		return codigo03;
	}

	public void setCodigo03(String codigo03) {
		this.codigo03 = codigo03;
	}

	public Integer getNumero01() {
		return numero01;
	}

	public void setNumero01(Integer numero01) {
		this.numero01 = numero01;
	}

	public String getDescripcion01() {
		return descripcion01;
	}

	public void setDescripcion01(String descripcion01) {
		this.descripcion01 = descripcion01;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}


}
