package net.royal.spring.core.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "COMPANYOWNERRECURSO")
public class BeanCompanyownerrecurso extends DominioTransaccion implements java.io.Serializable {

	@EmbeddedId
	private BeanCompanyownerrecursoPk pk;

	@Column(name = "RECURSO", nullable = true)
	public byte[] Recurso;

	@Column(name = "NOMBRERECURSO", nullable = true)
	public String Nombrerecurso;

	@Column(name = "ULTIMOUSUARIO", nullable = true)
	public String Ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	public Date Ultimafechamodif;

	@Transient
	public String AuxString;

	public BeanCompanyownerrecurso() {
		pk = new BeanCompanyownerrecursoPk();
	}

	public BeanCompanyownerrecursoPk getPk() {
		return pk;
	}

	public void setPk(BeanCompanyownerrecursoPk pk) {
		this.pk = pk;
	}

	public byte[] getRecurso() {
		return Recurso;
	}

	public void setRecurso(byte[] recurso) {
		Recurso = recurso;
	}

	public String getNombrerecurso() {
		return Nombrerecurso;
	}

	public void setNombrerecurso(String nombrerecurso) {
		Nombrerecurso = nombrerecurso;
	}

	public String getUltimousuario() {
		return Ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		Ultimousuario = ultimousuario;
	}

	public Date getUltimafechamodif() {
		return Ultimafechamodif;
	}

	public void setUltimafechamodif(Date ultimafechamodif) {
		Ultimafechamodif = ultimafechamodif;
	}

	public String getAuxString() {
		return AuxString;
	}

	public void setAuxString(String auxString) {
		AuxString = auxString;
	}

}
