package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioAdjunto;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;

public class WorkFlowTransaccion extends DominioTransaccion {
	
	public WorkFlowTransaccion() {}
	public WorkFlowTransaccion(Integer transaccionId) {
		this.transaccionId=transaccionId;
	}
	
	/**
	 * transaccion original que da inicio a la creacion a una nueva transaccion
	 * (MacroProceso)
	 */
	private Integer transaccionOrigenId;

	private String procesoId;
	private Integer transaccionId;
	
	private Integer empleadosolicitanteId;
	
	/**
	 * CLIENTE,PROVEEDOR,EMPLEADO
	 * 
	 * @return
	 */
	private Integer personaReferenciaid;

	private List<WorkFlowAprobadorTransaccion> listaAprobador;
	
	private String documentoReferencia;
	private String documentoReferenciaPadre;
	private String tipoDocumento;
	private String tipoAdjudicacion;
	private SeguridadUsuarioActual usuarioActual;
	
	/*
	 * J:Juridica
	 * N:Natural
	 * B:Natural con Negocio
	 */
	private String tipoPersona;

	/**
	 * WF_TRANSACCION.NIVEL_ID
	 */
	private Integer nivelId;
	
	/**
	 * WF_TRANSACCION.NIVEL_ESTADO_ID
	 */
	private String nivelEstadoId;
	
	/**
	 * segun configuracion, siguiente nivel estado a asignar
	 */
	private String nivelSiguienteEstadoId;
	
	/**
	 * segun configuracion, anterior nivel estado a asignar
	 */
	private String nivelAnteriorEstadoId;
	
	/**
	 * segun configuracion, anterior estado a asignar cuando se rechaza
	 */
	private String nivelRechazadoEstadoId;
	
	/**
	 * COMPANYOWNER.companyowner
	 * 
	 * @return
	 */
	private String companiasocioId;

	/**
	 * empleadomast.CentroCostos = AC_COSTCENTERMST.COSTCENTER
	 * 
	 * @return
	 */
	private String centrocostoId;

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	private String sucursalId;

	/**
	 * AFEMST.afe
	 * 
	 * @return
	 */
	private String proyectoId;

	/*
	 * MONEDAMAST (LO,EX,EU)
	 */
	private String moneda;

	/*
	 * RESPONSIBLEMST.RESPONSIBLE
	 */
	private String areaRevisoraId;

	private String asArea;
	private String unidadOperativa;
	private String clasificacionRequerimientoOrden;
	private String tipoMantenimientoGrupo;
	private String maquinaGrupo;

	private BigDecimal monto;
	
	private List<DominioParametroPersistencia> metadatos;
	private List<DominioAdjunto> listaAdjuntos;
	private List<WorkFlowAdjunto> adjuntos;
	
	private String motivoReclutamiento;	
	private String segmentoEnviado;

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

	public String getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionid) {
		this.transaccionId = transaccionid;
	}

	public String getCompaniasocioId() {
		return companiasocioId;
	}

	public void setCompaniasocioId(String companiasocioId) {
		this.companiasocioId = companiasocioId;
	}

	public Integer getEmpleadosolicitanteId() {
		return empleadosolicitanteId;
	}

	public void setEmpleadosolicitanteId(Integer empleadosolicitanteId) {
		this.empleadosolicitanteId = empleadosolicitanteId;
	}

	/*
	 * public String getAreaId() { return areaId; }
	 * 
	 * public void setAreaId(String areaId) { this.areaId = areaId; }
	 */

	public String getCentrocostoId() {
		return centrocostoId;
	}

	public void setCentrocostoId(String centrocostoId) {
		this.centrocostoId = centrocostoId;
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

	public List<DominioAdjunto> getListaAdjuntos() {
		return listaAdjuntos;
	}

	public void setListaAdjuntos(List<DominioAdjunto> listaAdjuntos) {
		this.listaAdjuntos = listaAdjuntos;
	}

	public String getDocumentoReferencia() {
		return documentoReferencia;
	}

	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public List<DominioParametroPersistencia> getMetadatos() {
		return metadatos;
	}

	public void setMetadatos(List<DominioParametroPersistencia> metadatos) {
		this.metadatos = metadatos;
	}

	/**
	 * transaccion original que da inicio a la creacion a una nueva transaccion
	 * (MacroProceso)
	 */
	public Integer getTransaccionOrigenId() {
		return transaccionOrigenId;
	}

	/**
	 * transaccion original que da inicio a la creacion a una nueva transaccion
	 * (MacroProceso)
	 */
	public void setTransaccionOrigenId(Integer transaccionOrigenId) {
		this.transaccionOrigenId = transaccionOrigenId;
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

	public List<WorkFlowAprobadorTransaccion> getListaAprobador() {
		
		if(listaAprobador == null) {
			listaAprobador = new ArrayList<WorkFlowAprobadorTransaccion>();
		}
		
		return listaAprobador;
	}

	public void setListaAprobador(List<WorkFlowAprobadorTransaccion> listaAprobador) {
		this.listaAprobador = listaAprobador;
	}

	public String getMotivoReclutamiento() {
		return motivoReclutamiento;
	}

	public void setMotivoReclutamiento(String motivoReclutamiento) {
		this.motivoReclutamiento = motivoReclutamiento;
	}

	public String getAsArea() {
		return asArea;
	}

	public void setAsArea(String asArea) {
		this.asArea = asArea;
	}

	public String getUnidadOperativa() {
		return unidadOperativa;
	}

	public void setUnidadOperativa(String unidadOperativa) {
		this.unidadOperativa = unidadOperativa;
	}

	public String getSegmentoEnviado() {
		return segmentoEnviado;
	}

	public void setSegmentoEnviado(String segmentoEnviado) {
		this.segmentoEnviado = segmentoEnviado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoAdjudicacion() {
		return tipoAdjudicacion;
	}

	public void setTipoAdjudicacion(String tipoAdjudicacion) {
		this.tipoAdjudicacion = tipoAdjudicacion;
	}

	public Integer getNivelId() {
		return nivelId;
	}

	public void setNivelId(Integer nivelId) {
		this.nivelId = nivelId;
	}

	public String getNivelEstadoId() {
		return nivelEstadoId;
	}

	public void setNivelEstadoId(String nivelEstadoId) {
		this.nivelEstadoId = nivelEstadoId;
	}

	public String getNivelSiguienteEstadoId() {
		return nivelSiguienteEstadoId;
	}

	public void setNivelSiguienteEstadoId(String nivelSiguienteEstadoId) {
		this.nivelSiguienteEstadoId = nivelSiguienteEstadoId;
	}
	public String getNivelAnteriorEstadoId() {
		return nivelAnteriorEstadoId;
	}
	public void setNivelAnteriorEstadoId(String nivelAnteriorEstadoId) {
		this.nivelAnteriorEstadoId = nivelAnteriorEstadoId;
	}
	public String getNivelRechazadoEstadoId() {
		return nivelRechazadoEstadoId;
	}
	public void setNivelRechazadoEstadoId(String nivelRechazadoEstadoId) {
		this.nivelRechazadoEstadoId = nivelRechazadoEstadoId;
	}
	
	/*
	 * J:Juridica
	 * N:Natural
	 * B:Natural con Negocio
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	
	/*
	 * J:Juridica
	 * N:Natural
	 * B:Natural con Negocio
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public SeguridadUsuarioActual getUsuarioActual() {
		return usuarioActual;
	}
	public void setUsuarioActual(SeguridadUsuarioActual usuarioActual) {
		this.usuarioActual = usuarioActual;
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
	public String getDocumentoReferenciaPadre() {
		return documentoReferenciaPadre;
	}
	public void setDocumentoReferenciaPadre(String documentoReferenciaPadre) {
		this.documentoReferenciaPadre = documentoReferenciaPadre;
	}
	public List<WorkFlowAdjunto> getAdjuntos() {
		if (adjuntos==null)
			adjuntos=new ArrayList<WorkFlowAdjunto>();
		return adjuntos;
	}
	public void setAdjuntos(List<WorkFlowAdjunto> adjuntos) {
		this.adjuntos = adjuntos;
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
