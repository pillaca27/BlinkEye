package net.royal.spring.ac.dominio.dto;

import net.royal.spring.ac.dominio.BeanAcSucursalgrupo;

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
 * @tabla dbo.AC_SucursalGrupo
*/
public class DtoComunAcSucursalgrupo extends DominioTransaccion implements java.io.Serializable{

	private String sucursalgrupo;
	private String sucursalgrupomayor;
	private String descripcionlocal;
	private String descripcioningles;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String uuid;
	
	private String sucursalgrupomayornombre;
	
	
	public String getSucursalgrupomayornombre() {
		return sucursalgrupomayornombre;
	}

	public void setSucursalgrupomayornombre(String sucursalgrupomayornombre) {
		this.sucursalgrupomayornombre = sucursalgrupomayornombre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public String getSucursalgrupo() {
		return sucursalgrupo;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupo
	*/
	public void setSucursalgrupo(String sucursalgrupo) {
		this.sucursalgrupo = sucursalgrupo;
	}
	/**
	 * 
	 * 
	 * @campo SucursalGrupoMayor
	*/
	public String getSucursalgrupomayor() {
		return sucursalgrupomayor;
	}

	/**
	 * 
	 * 
	 * @campo SucursalGrupoMayor
	*/
	public void setSucursalgrupomayor(String sucursalgrupomayor) {
		this.sucursalgrupomayor = sucursalgrupomayor;
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


	public BeanAcSucursalgrupo obtenerBean() {
		return obtenerBeanCore(new BeanAcSucursalgrupo(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanAcSucursalgrupo obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanAcSucursalgrupo(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanAcSucursalgrupo obtenerBeanActualizar(BeanAcSucursalgrupo bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanAcSucursalgrupo obtenerBeanCore(BeanAcSucursalgrupo bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR,ConstantePantallaAccion.NINGUNO:
			bean.getPk().setSucursalgrupo(sucursalgrupo);
			bean.setSucursalgrupomayor(sucursalgrupomayor);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			//bean.getPk().setSucursalgrupo(sucursalgrupo);
			bean.setSucursalgrupomayor(sucursalgrupomayor);
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
