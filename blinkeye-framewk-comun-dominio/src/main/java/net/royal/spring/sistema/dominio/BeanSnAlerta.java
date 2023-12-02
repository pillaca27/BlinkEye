package net.royal.spring.sistema.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

@Entity
@Table(name = "SN_ALERTA")
public class BeanSnAlerta extends DominioTransaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanSnAlertaPk pk;

	@Column(name = "PERSONAID", nullable = true)
	private Integer personaId;
	
	@Column(name = "LINK", nullable = true)
	private String link;
	
	@Column(name = "ESTADO", nullable = true)
	private String ESTADO;
	
	@Column(name = "NOMBRE", nullable = true)
	private String nombre;
	
	@Column(name = "UltimoUsuario", nullable = true)
	private String UltimoUsuario;
	
	@Column(name = "codigoProcesoWf", nullable = true)
	private String codigoProcesoWf;
	
	
	@Column(name = "accionWf", nullable = true)
	private String accionWf;
	
	@Column(name = "CodigoExternoId", nullable = true)
	private String CodigoExternoId;
	
	@Column(name = "TransaccionWfId", nullable = true)
	private Integer TransaccionWfId;
		
	@Column(name = "UltimaFechaModif", nullable = true)
	private java.util.Date UltimaFechaModif;

	public BeanSnAlertaPk getPk() {
		return pk;
	}

	public void setPk(BeanSnAlertaPk pk) {
		this.pk = pk;
	}

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUltimoUsuario() {
		return UltimoUsuario;
	}

	public void setUltimoUsuario(String ultimoUsuario) {
		UltimoUsuario = ultimoUsuario;
	}

	public String getCodigoProcesoWf() {
		return codigoProcesoWf;
	}

	public void setCodigoProcesoWf(String codigoProcesoWf) {
		this.codigoProcesoWf = codigoProcesoWf;
	}

	public String getAccionWf() {
		return accionWf;
	}

	public void setAccionWf(String accionWf) {
		this.accionWf = accionWf;
	}

	public String getCodigoExternoId() {
		return CodigoExternoId;
	}

	public void setCodigoExternoId(String codigoExternoId) {
		CodigoExternoId = codigoExternoId;
	}

	public Integer getTransaccionWfId() {
		return TransaccionWfId;
	}

	public void setTransaccionWfId(Integer transaccionWfId) {
		TransaccionWfId = transaccionWfId;
	}

	public java.util.Date getUltimaFechaModif() {
		return UltimaFechaModif;
	}

	public void setUltimaFechaModif(java.util.Date ultimaFechaModif) {
		UltimaFechaModif = ultimaFechaModif;
	}		
	
	
}
