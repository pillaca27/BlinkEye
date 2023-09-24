package net.royal.spring.hr.dominio;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.HR_Organigrama_Previo
*/
@Entity
@Table(name = "HR_ORGANIGRAMA_PREVIO")
public class BeanHrOrganigramaPrevio extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanHrOrganigramaPrevioPk pk;


	@Size(min = 0, max = 1)
	@Column(name = "RAIZ", length = 1, nullable = true)
	private String raiz;

	@Size(min = 0, max = 1)
	@Column(name = "TIPO", length = 1, nullable = true)
	private String tipo;

	@Column(name = "CODIGO", nullable = true)
	private Integer codigo;

	@Size(min = 0, max = 1)
	@Column(name = "PRINCIPAL", length = 1, nullable = true)
	private String principal;

	@Column(name = "NUMEROESTANDAR", nullable = true)
	private Integer numeroestandar;

	@Size(min = 0, max = 1)
	@Column(name = "FLAG_ENCARGADO", length = 1, nullable = true)
	private String flagEncargado;

	@Column(name = "CODIGOENCARGADO", nullable = true)
	private Integer codigoencargado;

	@Size(min = 0, max = 100)
	@Column(name = "ORDEN_NUEVO", length = 100, nullable = true)
	private String ordenNuevo;

	public BeanHrOrganigramaPrevio() {
		pk = new BeanHrOrganigramaPrevioPk();
	}


	public BeanHrOrganigramaPrevio(BeanHrOrganigramaPrevioPk pk) {
		this.pk = pk;
	}

	public BeanHrOrganigramaPrevioPk getPk() {
		return pk;
	}

	public void setPk(BeanHrOrganigramaPrevioPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo Raiz
	*/
	public String getRaiz() {
		return raiz;
	}

	/**
	 * 
	 * 
	 * @campo Raiz
	*/
	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}
	/**
	 * 
	 * 
	 * @campo Tipo
	*/
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * 
	 * @campo Tipo
	*/
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * 
	 * 
	 * @campo Codigo
	*/
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * 
	 * @campo Codigo
	*/
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	/**
	 * 
	 * 
	 * @campo Principal
	*/
	public String getPrincipal() {
		return principal;
	}

	/**
	 * 
	 * 
	 * @campo Principal
	*/
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	/**
	 * 
	 * 
	 * @campo NumeroEstandar
	*/
	public Integer getNumeroestandar() {
		return numeroestandar;
	}

	/**
	 * 
	 * 
	 * @campo NumeroEstandar
	*/
	public void setNumeroestandar(Integer numeroestandar) {
		this.numeroestandar = numeroestandar;
	}
	/**
	 * 
	 * 
	 * @campo Flag_Encargado
	*/
	public String getFlagEncargado() {
		return flagEncargado;
	}

	/**
	 * 
	 * 
	 * @campo Flag_Encargado
	*/
	public void setFlagEncargado(String flagEncargado) {
		this.flagEncargado = flagEncargado;
	}
	/**
	 * 
	 * 
	 * @campo CodigoEncargado
	*/
	public Integer getCodigoencargado() {
		return codigoencargado;
	}

	/**
	 * 
	 * 
	 * @campo CodigoEncargado
	*/
	public void setCodigoencargado(Integer codigoencargado) {
		this.codigoencargado = codigoencargado;
	}
	/**
	 * 
	 * 
	 * @campo Orden_Nuevo
	*/
	public String getOrdenNuevo() {
		return ordenNuevo;
	}

	/**
	 * 
	 * 
	 * @campo Orden_Nuevo
	*/
	public void setOrdenNuevo(String ordenNuevo) {
		this.ordenNuevo = ordenNuevo;
	}


}
