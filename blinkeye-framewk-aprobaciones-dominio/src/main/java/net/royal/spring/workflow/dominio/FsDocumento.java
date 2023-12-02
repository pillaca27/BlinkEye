package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FS_DOCUMENTO")
public class FsDocumento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FsDocumentoPk pk;

	@Column(name = "WORKFLOW_TRANSACCION_ID")
	private Integer workflowtransaccionid;

	@Column(name = "WORKFLOW_PROCESO_ID")
	private Integer workflowprocesoid;

	@Column(name = "WORKFLOW_VERSION_ID")
	private Integer workflowversionid;

	@Column(name = "WORKFLOW_FLUJO_ID")
	private Integer workflowflujoid;

	@Column(name = "NOMBRE_REAL")
	private String nombrereal;
	
	@Column(name = "TIPO_DOCUMENTO_ID")
	private String tipodocumentoid;

	@Column(name = "CODIGOTEXTO1_ID")
	private String codigotexto1id;

	@Column(name = "CODIGONUMERO1_ID")
	private Integer codigonumero1id;

	@Column(name = "CREACION_FECHA")
	private Date creacionfecha;

	@Column(name = "CREACION_USUARIO")
	private String creacionusuario;

	@Column(name = "MODIFICACION_FECHA")
	private Date modificacionfecha;

	@Column(name = "MODIFICACION_USUARIO")
	private String modificacionusuario;

	public FsDocumento() {
		pk = new FsDocumentoPk();
	}

	public FsDocumento(FsDocumentoPk pk) {
		this.pk = pk;
	}

	public FsDocumentoPk getPk() {
		return pk;
	}

	public void setPk(FsDocumentoPk pk) {
		this.pk = pk;
	}

	public Integer getWorkflowtransaccionid() {
		return workflowtransaccionid;
	}

	public void setWorkflowtransaccionid(Integer workflowtransaccionid) {
		this.workflowtransaccionid = workflowtransaccionid;
	}

	public Integer getWorkflowprocesoid() {
		return workflowprocesoid;
	}

	public void setWorkflowprocesoid(Integer workflowprocesoid) {
		this.workflowprocesoid = workflowprocesoid;
	}

	public Integer getWorkflowversionid() {
		return workflowversionid;
	}

	public void setWorkflowversionid(Integer workflowversionid) {
		this.workflowversionid = workflowversionid;
	}

	public Integer getWorkflowflujoid() {
		return workflowflujoid;
	}

	public void setWorkflowflujoid(Integer workflowflujoid) {
		this.workflowflujoid = workflowflujoid;
	}

	public String getNombrereal() {
		return nombrereal;
	}

	public void setNombrereal(String nombrereal) {
		this.nombrereal = nombrereal;
	}

	public String getCodigotexto1id() {
		return codigotexto1id;
	}

	public void setCodigotexto1id(String codigotexto1id) {
		this.codigotexto1id = codigotexto1id;
	}

	public Integer getCodigonumero1id() {
		return codigonumero1id;
	}

	public void setCodigonumero1id(Integer codigonumero1id) {
		this.codigonumero1id = codigonumero1id;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public String getCreacionusuario() {
		return creacionusuario;
	}

	public void setCreacionusuario(String creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public String getModificacionusuario() {
		return modificacionusuario;
	}

	public void setModificacionusuario(String modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public String getTipodocumentoid() {
		return tipodocumentoid;
	}

	public void setTipodocumentoid(String tipodocumentoid) {
		this.tipodocumentoid = tipodocumentoid;
	}

}
