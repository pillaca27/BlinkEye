package net.royal.spring.seguridad.dominio;

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
 * @tabla dbo.SeguridadConcepto
*/
@Entity
@Table(name = "SEGURIDADCONCEPTO")
public class BeanSeguridadconcepto extends DominioTransaccion implements java.io.Serializable{
	@EmbeddedId
	private BeanSeguridadconceptoPk pk;


	@Size(min = 0, max = 200)
	@Column(name = "DESCRIPCION", length = 200, nullable = true)
	private String descripcion;

	@Size(min = 0, max = 100)
	@Column(name = "DESCRIPCIONINGLES", length = 100, nullable = true)
	private String descripcioningles;

	@Size(min = 0, max = 1)
	@Column(name = "VISIBLEFLAG", length = 1, nullable = true)
	private String visibleflag;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEDETALLE", length = 1, nullable = true)
	private String tipodedetalle;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEOBJETO", length = 1, nullable = true)
	private String tipodeobjeto;

	@Size(min = 0, max = 50)
	@Column(name = "OBJETO", length = 50, nullable = true)
	private String objeto;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEACCESO", length = 1, nullable = true)
	private String tipodeacceso;

	@Size(min = 0, max = 80)
	@Column(name = "OBJETOWINDOW", length = 80, nullable = true)
	private String objetowindow;

	@Size(min = 0, max = 1)
	@Column(name = "WORKFLAG", length = 1, nullable = true)
	private String workflag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKAGREGARFLAG", length = 1, nullable = true)
	private String workagregarflag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKMODIFICARFLAG", length = 1, nullable = true)
	private String workmodificarflag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKBORRARFLAG", length = 1, nullable = true)
	private String workborrarflag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKAPROBARFLAG", length = 1, nullable = true)
	private String workaprobarflag;

	@Size(min = 0, max = 1)
	@Column(name = "WEBFLAG", length = 1, nullable = true)
	private String webflag;

	@Size(min = 0, max = 500)
	@Column(name = "WEBPAGE", length = 500, nullable = true)
	private String webpage;

	@Size(min = 0, max = 20)
	@Column(name = "WEBACTION", length = 20, nullable = true)
	private String webaction;

	@Size(min = 0, max = 6)
	@Column(name = "WEBGRUPO", length = 6, nullable = true)
	private String webgrupo;

	@Column(name = "WEBGRUPOSECUENCIA", nullable = true)
	private Integer webgruposecuencia;

	@Size(min = 0, max = 30)
	@Column(name = "ULTIMOUSUARIO", length = 30, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEACCESO2", length = 1, nullable = true)
	private String tipodeacceso2;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEACCESO3", length = 1, nullable = true)
	private String tipodeacceso3;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODEACCESO4", length = 1, nullable = true)
	private String tipodeacceso4;

	@Size(min = 0, max = 1)
	@Column(name = "WORKPROCESARFLAG", length = 1, nullable = true)
	private String workprocesarflag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKOPCION1FLAG", length = 1, nullable = true)
	private String workopcion1flag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKOPCION2FLAG", length = 1, nullable = true)
	private String workopcion2flag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKOPCION3FLAG", length = 1, nullable = true)
	private String workopcion3flag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKOPCION4FLAG", length = 1, nullable = true)
	private String workopcion4flag;

	@Size(min = 0, max = 1)
	@Column(name = "WORKOPCION5FLAG", length = 1, nullable = true)
	private String workopcion5flag;

	@Column(name = "ORDEN", nullable = true)
	private Integer orden;

	@Size(min = 0, max = 50)
	@Column(name = "IMAGEN", length = 50, nullable = true)
	private String imagen;

	@Size(min = 0, max = 1000)
	@Column(name = "JERARQUIA", length = 1000, nullable = true)
	private String jerarquia;

	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;

	public BeanSeguridadconcepto() {
		pk = new BeanSeguridadconceptoPk();
	}


	public BeanSeguridadconcepto(BeanSeguridadconceptoPk pk) {
		this.pk = pk;
	}

	public BeanSeguridadconceptoPk getPk() {
		return pk;
	}

	public void setPk(BeanSeguridadconceptoPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * 
	 * 
	 * @campo Descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @campo VisibleFlag
	*/
	public String getVisibleflag() {
		return visibleflag;
	}

	/**
	 * 
	 * 
	 * @campo VisibleFlag
	*/
	public void setVisibleflag(String visibleflag) {
		this.visibleflag = visibleflag;
	}
	/**
	 * 
	 * 
	 * @campo TipodeDetalle
	*/
	public String getTipodedetalle() {
		return tipodedetalle;
	}

	/**
	 * 
	 * 
	 * @campo TipodeDetalle
	*/
	public void setTipodedetalle(String tipodedetalle) {
		this.tipodedetalle = tipodedetalle;
	}
	/**
	 * 
	 * 
	 * @campo TipodeObjeto
	*/
	public String getTipodeobjeto() {
		return tipodeobjeto;
	}

	/**
	 * 
	 * 
	 * @campo TipodeObjeto
	*/
	public void setTipodeobjeto(String tipodeobjeto) {
		this.tipodeobjeto = tipodeobjeto;
	}
	/**
	 * 
	 * 
	 * @campo Objeto
	*/
	public String getObjeto() {
		return objeto;
	}

	/**
	 * 
	 * 
	 * @campo Objeto
	*/
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	/**
	 * 
	 * 
	 * @campo TipodeAcceso
	*/
	public String getTipodeacceso() {
		return tipodeacceso;
	}

	/**
	 * 
	 * 
	 * @campo TipodeAcceso
	*/
	public void setTipodeacceso(String tipodeacceso) {
		this.tipodeacceso = tipodeacceso;
	}
	/**
	 * 
	 * 
	 * @campo ObjetoWindow
	*/
	public String getObjetowindow() {
		return objetowindow;
	}

	/**
	 * 
	 * 
	 * @campo ObjetoWindow
	*/
	public void setObjetowindow(String objetowindow) {
		this.objetowindow = objetowindow;
	}
	/**
	 * 
	 * 
	 * @campo WorkFlag
	*/
	public String getWorkflag() {
		return workflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkFlag
	*/
	public void setWorkflag(String workflag) {
		this.workflag = workflag;
	}
	/**
	 * 
	 * 
	 * @campo WorkAgregarFlag
	*/
	public String getWorkagregarflag() {
		return workagregarflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkAgregarFlag
	*/
	public void setWorkagregarflag(String workagregarflag) {
		this.workagregarflag = workagregarflag;
	}
	/**
	 * 
	 * 
	 * @campo WorkModificarFlag
	*/
	public String getWorkmodificarflag() {
		return workmodificarflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkModificarFlag
	*/
	public void setWorkmodificarflag(String workmodificarflag) {
		this.workmodificarflag = workmodificarflag;
	}
	/**
	 * 
	 * 
	 * @campo WorkBorrarFlag
	*/
	public String getWorkborrarflag() {
		return workborrarflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkBorrarFlag
	*/
	public void setWorkborrarflag(String workborrarflag) {
		this.workborrarflag = workborrarflag;
	}
	/**
	 * 
	 * 
	 * @campo WorkAprobarFlag
	*/
	public String getWorkaprobarflag() {
		return workaprobarflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkAprobarFlag
	*/
	public void setWorkaprobarflag(String workaprobarflag) {
		this.workaprobarflag = workaprobarflag;
	}
	/**
	 * 
	 * 
	 * @campo WebFlag
	*/
	public String getWebflag() {
		return webflag;
	}

	/**
	 * 
	 * 
	 * @campo WebFlag
	*/
	public void setWebflag(String webflag) {
		this.webflag = webflag;
	}
	/**
	 * 
	 * 
	 * @campo WebPage
	*/
	public String getWebpage() {
		return webpage;
	}

	/**
	 * 
	 * 
	 * @campo WebPage
	*/
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	/**
	 * 
	 * 
	 * @campo WebAction
	*/
	public String getWebaction() {
		return webaction;
	}

	/**
	 * 
	 * 
	 * @campo WebAction
	*/
	public void setWebaction(String webaction) {
		this.webaction = webaction;
	}
	/**
	 * 
	 * 
	 * @campo WebGrupo
	*/
	public String getWebgrupo() {
		return webgrupo;
	}

	/**
	 * 
	 * 
	 * @campo WebGrupo
	*/
	public void setWebgrupo(String webgrupo) {
		this.webgrupo = webgrupo;
	}
	/**
	 * 
	 * 
	 * @campo WebGrupoSecuencia
	*/
	public Integer getWebgruposecuencia() {
		return webgruposecuencia;
	}

	/**
	 * 
	 * 
	 * @campo WebGrupoSecuencia
	*/
	public void setWebgruposecuencia(Integer webgruposecuencia) {
		this.webgruposecuencia = webgruposecuencia;
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
	 * @campo TipodeAcceso2
	*/
	public String getTipodeacceso2() {
		return tipodeacceso2;
	}

	/**
	 * 
	 * 
	 * @campo TipodeAcceso2
	*/
	public void setTipodeacceso2(String tipodeacceso2) {
		this.tipodeacceso2 = tipodeacceso2;
	}
	/**
	 * 
	 * 
	 * @campo TipodeAcceso3
	*/
	public String getTipodeacceso3() {
		return tipodeacceso3;
	}

	/**
	 * 
	 * 
	 * @campo TipodeAcceso3
	*/
	public void setTipodeacceso3(String tipodeacceso3) {
		this.tipodeacceso3 = tipodeacceso3;
	}
	/**
	 * 
	 * 
	 * @campo TipodeAcceso4
	*/
	public String getTipodeacceso4() {
		return tipodeacceso4;
	}

	/**
	 * 
	 * 
	 * @campo TipodeAcceso4
	*/
	public void setTipodeacceso4(String tipodeacceso4) {
		this.tipodeacceso4 = tipodeacceso4;
	}
	/**
	 * 
	 * 
	 * @campo WorkProcesarFlag
	*/
	public String getWorkprocesarflag() {
		return workprocesarflag;
	}

	/**
	 * 
	 * 
	 * @campo WorkProcesarFlag
	*/
	public void setWorkprocesarflag(String workprocesarflag) {
		this.workprocesarflag = workprocesarflag;
	}
	/**
	 * 
	 * 
	 * @campo WorkOpcion1Flag
	*/
	public String getWorkopcion1flag() {
		return workopcion1flag;
	}

	/**
	 * 
	 * 
	 * @campo WorkOpcion1Flag
	*/
	public void setWorkopcion1flag(String workopcion1flag) {
		this.workopcion1flag = workopcion1flag;
	}
	/**
	 * 
	 * 
	 * @campo WorkOpcion2Flag
	*/
	public String getWorkopcion2flag() {
		return workopcion2flag;
	}

	/**
	 * 
	 * 
	 * @campo WorkOpcion2Flag
	*/
	public void setWorkopcion2flag(String workopcion2flag) {
		this.workopcion2flag = workopcion2flag;
	}
	/**
	 * 
	 * 
	 * @campo WorkOpcion3Flag
	*/
	public String getWorkopcion3flag() {
		return workopcion3flag;
	}

	/**
	 * 
	 * 
	 * @campo WorkOpcion3Flag
	*/
	public void setWorkopcion3flag(String workopcion3flag) {
		this.workopcion3flag = workopcion3flag;
	}
	/**
	 * 
	 * 
	 * @campo WorkOpcion4Flag
	*/
	public String getWorkopcion4flag() {
		return workopcion4flag;
	}

	/**
	 * 
	 * 
	 * @campo WorkOpcion4Flag
	*/
	public void setWorkopcion4flag(String workopcion4flag) {
		this.workopcion4flag = workopcion4flag;
	}
	/**
	 * 
	 * 
	 * @campo WorkOpcion5Flag
	*/
	public String getWorkopcion5flag() {
		return workopcion5flag;
	}

	/**
	 * 
	 * 
	 * @campo WorkOpcion5Flag
	*/
	public void setWorkopcion5flag(String workopcion5flag) {
		this.workopcion5flag = workopcion5flag;
	}
	/**
	 * 
	 * 
	 * @campo ORDEN
	*/
	public Integer getOrden() {
		return orden;
	}

	/**
	 * 
	 * 
	 * @campo ORDEN
	*/
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * 
	 * 
	 * @campo imagen
	*/
	public String getImagen() {
		return imagen;
	}

	/**
	 * 
	 * 
	 * @campo imagen
	*/
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/**
	 * 
	 * 
	 * @campo Jerarquia
	*/
	public String getJerarquia() {
		return jerarquia;
	}

	/**
	 * 
	 * 
	 * @campo Jerarquia
	*/
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
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


}
