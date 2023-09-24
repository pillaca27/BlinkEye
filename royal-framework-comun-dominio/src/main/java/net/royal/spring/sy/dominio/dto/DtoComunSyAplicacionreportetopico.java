package net.royal.spring.sy.dominio.dto;

import net.royal.spring.sy.dominio.BeanSyAplicacionreportetopico;

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
 * @tabla dbo.SY_AplicacionReporteTopico
*/
public class DtoComunSyAplicacionreportetopico extends DominioTransaccion implements java.io.Serializable{

	private String aplicacioncodigo;
	private String topico;
	private String descripcionlocal;
	private String descripcioningles;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String uuid;

	private String aplicacioncodigonombre;
	
	
	public String getAplicacioncodigonombre() {
		return aplicacioncodigonombre;
	}

	public void setAplicacioncodigonombre(String aplicacioncodigonombre) {
		this.aplicacioncodigonombre = aplicacioncodigonombre;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo AplicacionCodigo
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo Topico
	*/
	public String getTopico() {
		return topico;
	}

	/**
	 * 
	 * 
	 * @campo Topico
	*/
	public void setTopico(String topico) {
		this.topico = topico;
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
	 * @campo UUID
	*/
	public String getUuid() {
		return uuid;
	}

	/**
	 * 
	 * 
	 * @campo UUID
	*/
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public BeanSyAplicacionreportetopico obtenerBean() {
		return obtenerBeanCore(new BeanSyAplicacionreportetopico(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanSyAplicacionreportetopico obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanSyAplicacionreportetopico(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanSyAplicacionreportetopico obtenerBeanActualizar(BeanSyAplicacionreportetopico bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanSyAplicacionreportetopico obtenerBeanCore(BeanSyAplicacionreportetopico bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setAplicacioncodigo(aplicacioncodigo);
			bean.getPk().setTopico(topico);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUuid(uuid);

			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setAplicacioncodigo(aplicacioncodigo);
			//bean.getPk().setTopico(topico);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			//bean.setUuid(uuid);

			break;
		}

		return bean;
	}

}
