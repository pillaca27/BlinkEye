package net.royal.spring.hr.dominio.dto;

import net.royal.spring.hr.dominio.BeanHrOrganigramaPrevio;

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
public class DtoComunHrOrganigramaPrevio extends DominioTransaccion implements java.io.Serializable{

	private String companyowner;
	private Integer ano;
	private String unidadnegocio;
	private Integer secuencia;
	private String orden;
	private String raiz;
	private String tipo;
	private Integer codigo;
	private String principal;
	private Integer numeroestandar;
	private String flagEncargado;
	private Integer codigoencargado;
	private String ordenNuevo;
	
	private String codigonombre;
	private String codigotiponombre;
	private Integer empleados;
	private Integer codigotipo;
	private Integer tipoposicionorden;
	private String estado;
	private String centrocosto;
	private String unidadorganigramanombre;
	
	
	public String getCentrocosto() {
		return centrocosto;
	}

	public void setCentrocosto(String centrocosto) {
		this.centrocosto = centrocosto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCodigotipo() {
		return codigotipo;
	}

	public void setCodigotipo(Integer codigotipo) {
		this.codigotipo = codigotipo;
	}

	public Integer getTipoposicionorden() {
		return tipoposicionorden;
	}

	public void setTipoposicionorden(Integer tipoposicionorden) {
		this.tipoposicionorden = tipoposicionorden;
	}

	public String getCodigonombre() {
		return codigonombre;
	}

	public void setCodigonombre(String codigonombre) {
		this.codigonombre = codigonombre;
	}

	public String getCodigotiponombre() {
		return codigotiponombre;
	}

	public void setCodigotiponombre(String codigotiponombre) {
		this.codigotiponombre = codigotiponombre;
	}

	public Integer getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Integer empleados) {
		this.empleados = empleados;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo CompanyOwner
	*/
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}
	/**
	 * 
	 * 
	 * @campo Ano
	*/
	public Integer getAno() {
		return ano;
	}

	/**
	 * 
	 * 
	 * @campo Ano
	*/
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocio
	*/
	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}
	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo Secuencia
	*/
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public String getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo Orden
	*/
	public void setOrden(String orden) {
		this.orden = orden;
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


	public BeanHrOrganigramaPrevio obtenerBean() {
		return obtenerBeanCore(new BeanHrOrganigramaPrevio(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanHrOrganigramaPrevio obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanHrOrganigramaPrevio(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanHrOrganigramaPrevio obtenerBeanActualizar(BeanHrOrganigramaPrevio bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanHrOrganigramaPrevio obtenerBeanCore(BeanHrOrganigramaPrevio bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setCompanyowner(companyowner);
			bean.getPk().setAno(ano);
			bean.getPk().setUnidadnegocio(unidadnegocio);
			bean.getPk().setSecuencia(secuencia);
			bean.getPk().setOrden(orden);
			bean.setRaiz(raiz);
			bean.setTipo(tipo);
			bean.setCodigo(codigo);
			bean.setPrincipal(principal);
			bean.setNumeroestandar(numeroestandar);
			bean.setFlagEncargado(flagEncargado);
			bean.setCodigoencargado(codigoencargado);
			bean.setOrdenNuevo(ordenNuevo);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:

			break;
		}

		return bean;
	}

	public String getUnidadorganigramanombre() {
		return unidadorganigramanombre;
	}

	public void setUnidadorganigramanombre(String unidadorganigramanombre) {
		this.unidadorganigramanombre = unidadorganigramanombre;
	}

}
