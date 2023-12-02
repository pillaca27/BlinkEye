package net.royal.spring.sistema.dominio.dto;

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

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoproceso;

/**
 * 
 * 
 * @tabla dbo.SY_TipoDocumentoProceso
*/
public class DtoComunSyTipodocumentoproceso extends DominioTransaccion implements java.io.Serializable{


	private String tipodocumentoid;
	private String aplicacioncodigo;
	private String procesocodigo;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getProcesocodigo() {
		return procesocodigo;
	}

	public void setProcesocodigo(String procesocodigo) {
		this.procesocodigo = procesocodigo;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public BeanSyTipodocumentoproceso obtenerBean() {
		BeanSyTipodocumentoproceso bean = new BeanSyTipodocumentoproceso();
		return obtenerBean(bean);
	}

	public BeanSyTipodocumentoproceso obtenerBean(BeanSyTipodocumentoproceso bean) {
		if (bean == null)
			bean = new BeanSyTipodocumentoproceso();

		bean.getPk().setTipodocumentoid(tipodocumentoid);
		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setProcesocodigo(procesocodigo);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);


		return bean;
	}

}
