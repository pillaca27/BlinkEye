package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;
import java.util.List;

import net.royal.spring.framework.modelo.WorkFlowAprobadorTransaccion;

public class DtoFlujoTransaccionRequest {

	private String proceso;
	private String criterios;
	private String referencia;
	private String referenciaPadre;
	private Integer solicitante;
	private Integer origenTransaccion;
	private String companiaid;
	private String centrocostosid;
	private String sucursalid;
	private String proyectoid;
	private String monedaid;
	private String areaid;
	private BigDecimal monto;
	private Integer personaReferencia;
	private List<WorkFlowAprobadorTransaccion> listaAprobador;
	private List<DtoFlujoAdjunto> adjuntos;
	private String segmentoEnviado;
	private String motivoReclutamiento;
	private String clasificacionRequerimientoOrden;

	private String tipoMantenimientoGrupo;
	private String maquinaGrupo;
	
	/**
	 * campos que se usan para determinar el duenio de la transaccion 
	 * y no puedan ser consultados por alguien diferente, a raiz de auditoria de ETHIKAL HACKING
	 */
	private Integer propietarioId;
	/**
	 * campos que se usan para determinar el duenio de la transaccion 
	 * y no puedan ser consultados por alguien diferente, a raiz de auditoria de ETHIKAL HACKING
	 */
	private String propietarioCodigo;
	
	private String tareaId;
	private String tareaNombre;
	
	/**
	 * nueva funcionalidad para el envio de correos desde distintas cuentas de correo
	 * por defecto puede estar en nulo y tomar los parametros por defecto
	 */
	private String perfilCorreoId;
	
	private String tipoUtilizacion;
	
	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getCriterios() {
		return criterios;
	}

	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}

	public Integer getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Integer solicitante) {
		this.solicitante = solicitante;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<DtoFlujoAdjunto> getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List<DtoFlujoAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Integer getOrigenTransaccion() {
		return origenTransaccion;
	}

	public void setOrigenTransaccion(Integer origenTransaccion) {
		this.origenTransaccion = origenTransaccion;
	}

	public String getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(String companiaid) {
		this.companiaid = companiaid;
	}

	public String getCentrocostosid() {
		return centrocostosid;
	}

	public void setCentrocostosid(String centrocostosid) {
		this.centrocostosid = centrocostosid;
	}

	public String getSucursalid() {
		return sucursalid;
	}

	public void setSucursalid(String sucursalid) {
		this.sucursalid = sucursalid;
	}

	public String getProyectoid() {
		return proyectoid;
	}

	public void setProyectoid(String proyectoid) {
		this.proyectoid = proyectoid;
	}

	public String getMonedaid() {
		return monedaid;
	}

	public void setMonedaid(String monedaid) {
		this.monedaid = monedaid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getPersonaReferencia() {
		return personaReferencia;
	}

	public void setPersonaReferencia(Integer personaReferencia) {
		this.personaReferencia = personaReferencia;
	}

	public List<WorkFlowAprobadorTransaccion> getListaAprobador() {
		return listaAprobador;
	}

	public void setListaAprobador(List<WorkFlowAprobadorTransaccion> listaAprobador) {
		this.listaAprobador = listaAprobador;
	}

	public String getSegmentoEnviado() {
		return segmentoEnviado;
	}

	public void setSegmentoEnviado(String segmentoEnviado) {
		this.segmentoEnviado = segmentoEnviado;
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
	
	public String getClasificacionRequerimientoOrden() {
		return clasificacionRequerimientoOrden;
	}

	public void setClasificacionRequerimientoOrden(String clasificacionRequerimientoOrden) {
		this.clasificacionRequerimientoOrden = clasificacionRequerimientoOrden;
	}
	
	public String getTipoMantenimientoGrupo() {
		return tipoMantenimientoGrupo;
	}

	public void setTipoMantenimientoGrupo(String tipoMantenimientoGrupo) {
		this.tipoMantenimientoGrupo = tipoMantenimientoGrupo;
	}
	
	public String getMaquinaGrupo() {
		return maquinaGrupo;
	}

	public void setMaquinaGrupo(String maquinaGrupo) {
		this.maquinaGrupo = maquinaGrupo;
	}
	
	public String getMotivoReclutamiento() {
		return motivoReclutamiento;
	}

	public void setMotivoReclutamiento(String motivoReclutamiento) {
		this.motivoReclutamiento = motivoReclutamiento;
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

	public String getTipoUtilizacion() {
		return tipoUtilizacion;
	}

	public void setTipoUtilizacion(String tipoUtilizacion) {
		this.tipoUtilizacion = tipoUtilizacion;
	}
	
}
