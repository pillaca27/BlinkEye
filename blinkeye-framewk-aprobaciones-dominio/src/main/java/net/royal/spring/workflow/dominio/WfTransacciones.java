package net.royal.spring.workflow.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WF_TRANSACCION", schema = "sgworkflowsys")
public class WfTransacciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	//private WfTransaccionesPk pk;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TRANSACCION_ID", nullable = true)
	private Integer transaccionid;

	@Column(name = "PROCESO_ID")
	private String procesoid;

	/**
	 * MA_MiscelaneosDetalle.CodigoElemento
	 * 
	 * @return
	 */
	@Column(name = "AREA_REVISORA_ID")
	private String areaRevisoraId;

	/**
	 * COMPANYOWNER.companyowner
	 * 
	 * @return
	 */
	@Column(name = "COMPANIA_SOCIO_ID")
	private String companiaSocioId;

	/**
	 * empleadomast.CentroCostos = AC_COSTCENTERMST.COSTCENTER
	 * 
	 * @return
	 */
	@Column(name = "CENTRO_COSTOS_ID")
	private String centroCostosId;

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	@Column(name = "SUCURSAL_ID")
	private String sucursalId;

	/**
	 * AFEMST.afe
	 * 
	 * @return
	 */
	@Column(name = "PROYECTO_ID")
	private String proyectoId;

	/*
	 * MONEDAMAST (LO,EX,EU)
	 */
	@Column(name = "MONEDA_ID")
	private String monedaId;

	@Column(name = "MONTO")
	private BigDecimal monto;

	@Column(name = "VERSION_ID")
	private Integer versionid;

	@Column(name = "FLUJO_ID")
	private Integer flujoid;

	@Column(name = "NIVEL_ID")
	private Integer nivelid;

	@Column(name = "CRITERIOS")
	private String criterios;

	@Column(name = "SOLICITANTE_ID")
	private Integer solicitanteid;

	/**
	 * CLIENTE,PROVEEDOR,EMPLEADO
	 * 
	 * @return
	 */
	@Column(name = "PERSONA_REFERENCIA_ID")
	private Integer personaReferenciaid;

	@Column(name = "FECHA_REGISTRO")
	private Date fecharegistro;

	@Column(name = "REFERENCIA")
	private String referencia;

	@Column(name = "REFERENCIA_PADRE")
	private String referenciaPadre;
	
	@Column(name = "TRANSACCION_ORIGEN_ID", nullable = false)
	private Integer transaccionOrigenId;

	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "observacionaprobacion")
	private String observacionaprobacion;

	@Column(name = "CREACION_USUARIO", nullable = true)
	private String creacionusuario;

	@Column(name = "MODIFICACION_USUARIO", nullable = true)
	private String modificacionusuario;

	@Column(name = "CREACION_FECHA", nullable = true)
	private Date creacionfecha;

	@Column(name = "MODIFICACION_FECHA", nullable = true)
	private Date modificacionfecha;

	@Column(name = "SEGMENTO_ENVIADO", nullable = true)
	private String segmentoEnviado;

	@Column(name = "SEGMENTO_PENDIENTE", nullable = true)
	private String segmentoPendiente;

	@Column(name = "SEGMENTO_APROBADO", nullable = true)
	private String segmentoAprobado;
	
	@Column(name = "NIVEL_ESTADO_ID", nullable = true)
	private String nivelEstadoId;
	
	@Column(name = "NIVEL_ESTADO_SIGUIENTE_ID", nullable = true)
	private String nivelEstadoSiguienteId;

	/**
	 * campos que se usan para determinar el duenio de la transaccion 
	 * y no puedan ser consultados por alguien diferente, a raiz de auditoria de ETHIKAL HACKING
	 */
	@Column(name = "PROPIETARIO_ID", nullable = true)
	private Integer propietarioId;
	/**
	 * campos que se usan para determinar el duenio de la transaccion 
	 * y no puedan ser consultados por alguien diferente, a raiz de auditoria de ETHIKAL HACKING
	 */
	@Column(name = "PROPIETARIO_CODIGO", nullable = true)
	private String propietarioCodigo;
	
	@Column(name = "UUID")
	private String uuid;

	@Column(name = "TAREA_ID")
	private String tareaId;
	
	@Column(name = "TAREA_NOMBRE")
	private String tareaNombre;
	
	@Column(name = "PERFIL_CORREO_ID")
	private String perfilCorreoId;
	
	public WfTransacciones() {
	}

	public String getProcesoid() {
		return procesoid;
	}

	public void setProcesoid(String procesoid) {
		this.procesoid = procesoid;
	}

	public Integer getVersionid() {
		return versionid;
	}

	public void setVersionid(Integer versionid) {
		this.versionid = versionid;
	}

	public Integer getFlujoid() {
		return flujoid;
	}

	public void setFlujoid(Integer flujoid) {
		this.flujoid = flujoid;
	}

	public Integer getNivelid() {
		return nivelid;
	}

	public void setNivelid(Integer nivelid) {
		this.nivelid = nivelid;
	}

	public String getCriterios() {
		return criterios;
	}

	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}

	public Integer getSolicitanteid() {
		return solicitanteid;
	}

	public void setSolicitanteid(Integer solicitanteid) {
		this.solicitanteid = solicitanteid;
	}

	public Date getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCreacionusuario() {
		return creacionusuario;
	}

	public void setCreacionusuario(String creacionusuario) {
		this.creacionusuario = creacionusuario;
	}

	public String getModificacionusuario() {
		return modificacionusuario;
	}

	public void setModificacionusuario(String modificacionusuario) {
		this.modificacionusuario = modificacionusuario;
	}

	public Date getCreacionfecha() {
		return creacionfecha;
	}

	public void setCreacionfecha(Date creacionfecha) {
		this.creacionfecha = creacionfecha;
	}

	public Date getModificacionfecha() {
		return modificacionfecha;
	}

	public void setModificacionfecha(Date modificacionfecha) {
		this.modificacionfecha = modificacionfecha;
	}

	public Integer getTransaccionOrigenId() {
		return transaccionOrigenId;
	}

	public void setTransaccionOrigenId(Integer transaccionOrigenId) {
		this.transaccionOrigenId = transaccionOrigenId;
	}

	public String getCompaniaSocioId() {
		return companiaSocioId;
	}

	public void setCompaniaSocioId(String companiaSocioId) {
		this.companiaSocioId = companiaSocioId;
	}

	public String getCentroCostosId() {
		return centroCostosId;
	}

	public void setCentroCostosId(String centroCostosId) {
		this.centroCostosId = centroCostosId;
	}

	public String getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getProyectoId() {
		return proyectoId;
	}

	public void setProyectoId(String proyectoId) {
		this.proyectoId = proyectoId;
	}

	public String getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(String monedaId) {
		this.monedaId = monedaId;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getAreaRevisoraId() {
		return areaRevisoraId;
	}

	public void setAreaRevisoraId(String areaRevisoraId) {
		this.areaRevisoraId = areaRevisoraId;
	}

	public Integer getPersonaReferenciaid() {
		return personaReferenciaid;
	}

	public void setPersonaReferenciaid(Integer personaReferenciaid) {
		this.personaReferenciaid = personaReferenciaid;
	}

	public String getSegmentoEnviado() {
		return segmentoEnviado;
	}

	public void setSegmentoEnviado(String segmentoEnviado) {
		this.segmentoEnviado = segmentoEnviado;
	}

	public String getSegmentoPendiente() {
		return segmentoPendiente;
	}

	public void setSegmentoPendiente(String segmentoPendiente) {
		this.segmentoPendiente = segmentoPendiente;
	}

	public String getSegmentoAprobado() {
		return segmentoAprobado;
	}

	public void setSegmentoAprobado(String segmentoAprobado) {
		this.segmentoAprobado = segmentoAprobado;
	}

	public String getNivelEstadoId() {
		return nivelEstadoId;
	}

	public void setNivelEstadoId(String nivelEstadoId) {
		this.nivelEstadoId = nivelEstadoId;
	}

	public String getNivelEstadoSiguienteId() {
		return nivelEstadoSiguienteId;
	}

	public void setNivelEstadoSiguienteId(String nivelEstadoSiguienteId) {
		this.nivelEstadoSiguienteId = nivelEstadoSiguienteId;
	}

	public Integer getTransaccionid() {
		return transaccionid;
	}

	public void setTransaccionid(Integer transaccionid) {
		this.transaccionid = transaccionid;
	}

	public String getObservacionaprobacion() {
		return observacionaprobacion;
	}

	public void setObservacionaprobacion(String observacionaprobacion) {
		this.observacionaprobacion = observacionaprobacion;
	}

	public Integer getPropietarioId() {
		return propietarioId;
	}

	public void setPropietarioId(Integer propietarioId) {
		this.propietarioId = propietarioId;
	}

	public String getPropietarioCodigo() {
		return propietarioCodigo;
	}

	public void setPropietarioCodigo(String propietarioCodigo) {
		this.propietarioCodigo = propietarioCodigo;
	}

	public String getReferenciaPadre() {
		return referenciaPadre;
	}

	public void setReferenciaPadre(String referenciaPadre) {
		this.referenciaPadre = referenciaPadre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getTareaId() {
		return tareaId;
	}

	public void setTareaId(String tareaId) {
		this.tareaId = tareaId;
	}

	public String getTareaNombre() {
		return tareaNombre;
	}

	public void setTareaNombre(String tareaNombre) {
		this.tareaNombre = tareaNombre;
	}

	public String getPerfilCorreoId() {
		return perfilCorreoId;
	}

	public void setPerfilCorreoId(String perfilCorreoId) {
		this.perfilCorreoId = perfilCorreoId;
	}

}
