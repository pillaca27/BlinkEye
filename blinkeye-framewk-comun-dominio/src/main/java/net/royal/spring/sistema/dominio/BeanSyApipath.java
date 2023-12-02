package net.royal.spring.sistema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "SY_APIPATH")
public class BeanSyApipath extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSyApipathPk pk;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "METODO", nullable = true)
	private String metodo;

	@Column(name = "NOTAS", nullable = true)
	private String notas;
	
	@Column(name = "VG_FLG", nullable = true)
	private String vb;

	@Column(name = "DEFINICION_REQUEST_ID", nullable = true)
	private Integer definicionrequestid;

	@Column(name = "DEFINICION_RESPONSE_ID", nullable = true)
	private Integer definicionresponseid;

	@Column(name = "TIPODATO_REQUEST", nullable = true)
	private String tipodatorequest;

	@Column(name = "TIPODATO_RESPONSE", nullable = true)
	private String tipodatoresponse;

	public BeanSyApipath() {
		pk = new BeanSyApipathPk();
	}

	public BeanSyApipath(BeanSyApipathPk pk) {
		this.pk = pk;
	}

	public BeanSyApipathPk getPk() {
		return pk;
	}

	public void setPk(BeanSyApipathPk pk) {
		this.pk = pk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Integer getDefinicionrequestid() {
		return definicionrequestid;
	}

	public void setDefinicionrequestid(Integer definicionrequestid) {
		this.definicionrequestid = definicionrequestid;
	}

	public Integer getDefinicionresponseid() {
		return definicionresponseid;
	}

	public void setDefinicionresponseid(Integer definicionresponseid) {
		this.definicionresponseid = definicionresponseid;
	}

	public String getTipodatorequest() {
		return tipodatorequest;
	}

	public void setTipodatorequest(String tipodatorequest) {
		this.tipodatorequest = tipodatorequest;
	}

	public String getTipodatoresponse() {
		return tipodatoresponse;
	}

	public void setTipodatoresponse(String tipodatoresponse) {
		this.tipodatoresponse = tipodatoresponse;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getVb() {
		return vb;
	}

	public void setVb(String vb) {
		this.vb = vb;
	}

}
