package net.royal.spring.core.dominio.filtro;

import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.EmpleadoMast
*/
public class FiltroComunEmpleadomast extends DominioTransaccion implements java.io.Serializable{

	private Integer empleado;
	private String companiasocio;
	private String unidadnegocio;
	private String sucursal;
	private String unidadoperativa;
	private String tipoplanilla;
	private String centrocostos;
	private Integer puesto;
	private Integer unidadorganigrama;
	private Date fechaingresodesde;
	private Date fechaingresohasta;
	private String categoria;
	private String gradosalario;
	private String tipocontrato;
	private Date fechainiciocontrdesde;
	private Date fechainiciocontrhasta;
	private Date fechafincontrdesde;
	private Date fechafincontrhasta;
 
	private Integer codigo;
	private String descripcion;
	private String documento;
	private String auxCompaniaNombre;
	private String auxCentroCostoNombre;
	private String auxCategoriaNombre;
	private String auxUnidadNegocioNombre;
	private String auxPuestoNombre;
	private String auxNivelSalarNombre;
	private String auxSucursalNombre;
	private String auxUnidadOrgNombre;
	private String auxTipoContratoNombre;
	private String auxUnidadOperativaNombre;
	private String auxEmpleadoNombre;
	private String auxTipoPlanillaNombre;
	private String auxResponsableNombre;
	private String auxMotivoNombre;
	
	private String empleadoString;
	private String codigopuestoString;
	private String unidadorganigramaString;
	private String responsableString;
	private String motivoString;
 
	
	private DominioPaginacion paginacion;
	
	private Date fecha;
	private String tipo;
	private Integer mes;
	private Integer anodesde;
	private Integer anohasta;
	private Integer dias; //demora
	private String estadoempleado;
	private String auxEstadoEmpleadoNombre;
	private String parentesco;
	private String auxParentescoNombre;
	private String tipoatributo;
	private String auxTipoatributoNombre;
	private String atributo;
	private String auxAtributoNombre;

	private String auxDocumentoNombre;
	private String estado;
	private String auxEstadoNombre;
	private Boolean fechaingresoCheckBox;
	private Boolean fechainiciocontrCheckBox;
	private Boolean fechafincontrCheckBox;
	private Boolean tipoCheckBox;

	private String merito;
	private String auxMeritoNombre;
	private String demerito;
	private String auxDemeritoNombre;
	private Date fecharegdesde;
	private Date fechareghasta;
	private Date fechavencdesde;
	private Date fechavenchasta;
	private String auxConceptoNombre;
	private String auxPeriodoNombre;
	private String concepto;
	private String periodo;
	private String proyecto;
	private String auxProyectoNombre;
	private String auspiciado;
	private String curso;
	private String auxCursoNombre;
	private String auxCentroCapacitacionNombre;
	private String centroCapacitacion;
	private String auxCursoTipoNombre;
	private String cursoTipo;
	private String numCapacitacion;
	private String anio;
	private String planCapacitacion;
	private String auxTipoCapacitacionNombre;
	private String tipoCapacitacion;
	private Date fechasolicitudesde;
	private Date fechasolicitudhasta;
	private Date fechavigenciadesde;
	private Date fechavigenciahasta;
	private String convenio;
	private String puestofinalString;
	private String auxNombrePuestoFinal;
	private String tipoactividad;
	private String auxTipoActNombre;
	private Integer numactividad;



	public String getTipoactividad() {
		return tipoactividad;
	}

	public void setTipoactividad(String tipoactividad) {
		this.tipoactividad = tipoactividad;
	}

	public String getAuxTipoActNombre() {
		return auxTipoActNombre;
	}

	public void setAuxTipoActNombre(String auxTipoActNombre) {
		this.auxTipoActNombre = auxTipoActNombre;
	}

	public Integer getNumactividad() {
		return numactividad;
	}

	public void setNumactividad(Integer numactividad) {
		this.numactividad = numactividad;
	}

	public String getPuestofinalString() {
		return puestofinalString;
	}

	public void setPuestofinalString(String puestofinalString) {
		this.puestofinalString = puestofinalString;
	}

	public String getAuxNombrePuestoFinal() {
		return auxNombrePuestoFinal;
	}

	public void setAuxNombrePuestoFinal(String auxNombrePuestoFinal) {
		this.auxNombrePuestoFinal = auxNombrePuestoFinal;
	}

	public String getAuxCentroCapacitacionNombre() {
		return auxCentroCapacitacionNombre;
	}

	public void setAuxCentroCapacitacionNombre(String auxCentroCapacitacionNombre) {
		this.auxCentroCapacitacionNombre = auxCentroCapacitacionNombre;
	}

	public String getAuxCursoTipoNombre() {
		return auxCursoTipoNombre;
	}

	public void setAuxCursoTipoNombre(String auxCursoTipoNombre) {
		this.auxCursoTipoNombre = auxCursoTipoNombre;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getPlanCapacitacion() {
		return planCapacitacion;
	}

	public void setPlanCapacitacion(String planCapacitacion) {
		this.planCapacitacion = planCapacitacion;
	}

	public String getAuxTipoCapacitacionNombre() {
		return auxTipoCapacitacionNombre;
	}

	public void setAuxTipoCapacitacionNombre(String auxTipoCapacitacionNombre) {
		this.auxTipoCapacitacionNombre = auxTipoCapacitacionNombre;
	}

	

	public String getAuspiciado() {
		return auspiciado;
	}

	public void setAuspiciado(String auspiciado) {
		this.auspiciado = auspiciado;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAuxCursoNombre() {
		return auxCursoNombre;
	}

	public void setAuxCursoNombre(String auxCursoNombre) {
		this.auxCursoNombre = auxCursoNombre;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getAuxProyectoNombre() {
		return auxProyectoNombre;
	}

	public void setAuxProyectoNombre(String auxProyectoNombre) {
		this.auxProyectoNombre = auxProyectoNombre;
	}

	public Date getFecharegdesde() {
		return fecharegdesde;
	}

	public void setFecharegdesde(Date fecharegdesde) {
		this.fecharegdesde = fecharegdesde;
	}

	public Date getFechareghasta() {
		return fechareghasta;
	}

	public void setFechareghasta(Date fechareghasta) {
		this.fechareghasta = fechareghasta;
	}

	public Date getFechavencdesde() {
		return fechavencdesde;
	}

	public void setFechavencdesde(Date fechavencdesde) {
		this.fechavencdesde = fechavencdesde;
	}

	public Date getFechavenchasta() {
		return fechavenchasta;
	}

	public void setFechavenchasta(Date fechavenchasta) {
		this.fechavenchasta = fechavenchasta;
	}

	public String getAuxConceptoNombre() {
		return auxConceptoNombre;
	}

	public void setAuxConceptoNombre(String auxConceptoNombre) {
		this.auxConceptoNombre = auxConceptoNombre;
	}

	public String getAuxPeriodoNombre() {
		return auxPeriodoNombre;
	}

	public void setAuxPeriodoNombre(String auxPeriodoNombre) {
		this.auxPeriodoNombre = auxPeriodoNombre;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Boolean getFechaingresoCheckBox() {
		return fechaingresoCheckBox;
	}

	public void setFechaingresoCheckBox(Boolean fechaingresoCheckBox) {
		this.fechaingresoCheckBox = fechaingresoCheckBox;
	}

	public Boolean getTipoCheckBox() {
		return tipoCheckBox;
	}

	public void setTipoCheckBox(Boolean tipoCheckBox) {
		this.tipoCheckBox = tipoCheckBox;
	}

	public String getMerito() {
		return merito;
	}

	public void setMerito(String merito) {
		this.merito = merito;
	}

	public String getAuxMeritoNombre() {
		return auxMeritoNombre;
	}

	public void setAuxMeritoNombre(String auxMeritoNombre) {
		this.auxMeritoNombre = auxMeritoNombre;
	}

	public String getDemerito() {
		return demerito;
	}

	public void setDemerito(String demerito) {
		this.demerito = demerito;
	}

	public String getAuxDemeritoNombre() {
		return auxDemeritoNombre;
	}

	public void setAuxDemeritoNombre(String auxDemeritoNombre) {
		this.auxDemeritoNombre = auxDemeritoNombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAuxEstadoNombre() {
		return auxEstadoNombre;
	}

	public void setAuxEstadoNombre(String auxEstadoNombre) {
		this.auxEstadoNombre = auxEstadoNombre;
	}

	public Boolean getFechainiciocontrCheckBox() {
		return fechainiciocontrCheckBox;
	}

	public void setFechainiciocontrCheckBox(Boolean fechainiciocontrCheckBox) {
		this.fechainiciocontrCheckBox = fechainiciocontrCheckBox;
	}

	public Boolean getFechafincontrCheckBox() {
		return fechafincontrCheckBox;
	}

	public void setFechafincontrCheckBox(Boolean fechafincontrCheckBox) {
		this.fechafincontrCheckBox = fechafincontrCheckBox;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getAuxDocumentoNombre() {
		return auxDocumentoNombre;
	}

	public void setAuxDocumentoNombre(String auxDocumentoNombre) {
		this.auxDocumentoNombre = auxDocumentoNombre;
	}

	public String getTipoatributo() {
		return tipoatributo;
	}

	public void setTipoatributo(String tipoatributo) {
		this.tipoatributo = tipoatributo;
	}

	public String getAuxTipoatributoNombre() {
		return auxTipoatributoNombre;
	}

	public void setAuxTipoatributoNombre(String auxTipoatributoNombre) {
		this.auxTipoatributoNombre = auxTipoatributoNombre;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getAuxAtributoNombre() {
		return auxAtributoNombre;
	}

	public void setAuxAtributoNombre(String auxAtributoNombre) {
		this.auxAtributoNombre = auxAtributoNombre;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getAuxParentescoNombre() {
		return auxParentescoNombre;
	}

	public void setAuxParentescoNombre(String auxParentescoNombre) {
		this.auxParentescoNombre = auxParentescoNombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstadoempleado() {
		return estadoempleado;
	}

	public void setEstadoempleado(String estadoempleado) {
		this.estadoempleado = estadoempleado;
	}

	public String getAuxEstadoEmpleadoNombre() {
		return auxEstadoEmpleadoNombre;
	}





	public void setAuxEstadoEmpleadoNombre(String auxEstadoEmpleadoNombre) {
		this.auxEstadoEmpleadoNombre = auxEstadoEmpleadoNombre;
	}





	public Integer getMes() {
		return mes;
	}





	public void setMes(Integer mes) {
		this.mes = mes;
	}





	public String getTipo() {
		return tipo;
	}





	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public Integer getAnodesde() {
		return anodesde;
	}





	public void setAnodesde(Integer anodesde) {
		this.anodesde = anodesde;
	}





	public Integer getAnohasta() {
		return anohasta;
	}





	public void setAnohasta(Integer anohasta) {
		this.anohasta = anohasta;
	}





	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public Integer getEmpleado() {
		return empleado;
	}

	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}

	public DominioPaginacion getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public String getUnidadnegocio() {
		return unidadnegocio;
	}

	public void setUnidadnegocio(String unidadnegocio) {
		this.unidadnegocio = unidadnegocio;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getUnidadoperativa() {
		return unidadoperativa;
	}

	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}

	public String getTipoplanilla() {
		return tipoplanilla;
	}

	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}

	public String getCentrocostos() {
		return centrocostos;
	}

	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}

	public Integer getPuesto() {
		return puesto;
	}

	public void setPuesto(Integer puesto) {
		this.puesto = puesto;
	}

	public Integer getUnidadorganigrama() {
		return unidadorganigrama;
	}

	public void setUnidadorganigrama(Integer unidadorganigrama) {
		this.unidadorganigrama = unidadorganigrama;
	}

	public Date getFechaingresodesde() {
		return fechaingresodesde;
	}

	public void setFechaingresodesde(Date fechaingresodesde) {
		this.fechaingresodesde = fechaingresodesde;
	}

	public Date getFechaingresohasta() {
		return fechaingresohasta;
	}

	public void setFechaingresohasta(Date fechaingresohasta) {
		this.fechaingresohasta = fechaingresohasta;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getGradosalario() {
		return gradosalario;
	}

	public void setGradosalario(String gradosalario) {
		this.gradosalario = gradosalario;
	}

	public String getTipocontrato() {
		return tipocontrato;
	}

	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}

	public Date getFechainiciocontrdesde() {
		return fechainiciocontrdesde;
	}

	public void setFechainiciocontrdesde(Date fechainiciocontrdesde) {
		this.fechainiciocontrdesde = fechainiciocontrdesde;
	}

	public Date getFechainiciocontrhasta() {
		return fechainiciocontrhasta;
	}

	public void setFechainiciocontrhasta(Date fechainiciocontrhasta) {
		this.fechainiciocontrhasta = fechainiciocontrhasta;
	}

	public Date getFechafincontrdesde() {
		return fechafincontrdesde;
	}

	public void setFechafincontrdesde(Date fechafincontrdesde) {
		this.fechafincontrdesde = fechafincontrdesde;
	}

	public Date getFechafincontrhasta() {
		return fechafincontrhasta;
	}

	public void setFechafincontrhasta(Date fechafincontrhasta) {
		this.fechafincontrhasta = fechafincontrhasta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAuxCompaniaNombre() {
		return auxCompaniaNombre;
	}

	public void setAuxCompaniaNombre(String auxCompaniaNombre) {
		this.auxCompaniaNombre = auxCompaniaNombre;
	}

	public String getAuxCentroCostoNombre() {
		return auxCentroCostoNombre;
	}

	public void setAuxCentroCostoNombre(String auxCentroCostoNombre) {
		this.auxCentroCostoNombre = auxCentroCostoNombre;
	}

	public String getAuxCategoriaNombre() {
		return auxCategoriaNombre;
	}

	public void setAuxCategoriaNombre(String auxCategoriaNombre) {
		this.auxCategoriaNombre = auxCategoriaNombre;
	}

	public String getAuxUnidadNegocioNombre() {
		return auxUnidadNegocioNombre;
	}

	public void setAuxUnidadNegocioNombre(String auxUnidadNegocioNombre) {
		this.auxUnidadNegocioNombre = auxUnidadNegocioNombre;
	}

	public String getAuxPuestoNombre() {
		return auxPuestoNombre;
	}

	public void setAuxPuestoNombre(String auxPuestoNombre) {
		this.auxPuestoNombre = auxPuestoNombre;
	}

	public String getAuxNivelSalarNombre() {
		return auxNivelSalarNombre;
	}

	public void setAuxNivelSalarNombre(String auxNivelSalarNombre) {
		this.auxNivelSalarNombre = auxNivelSalarNombre;
	}

	public String getAuxSucursalNombre() {
		return auxSucursalNombre;
	}

	public void setAuxSucursalNombre(String auxSucursalNombre) {
		this.auxSucursalNombre = auxSucursalNombre;
	}

	public String getAuxUnidadOrgNombre() {
		return auxUnidadOrgNombre;
	}

	public void setAuxUnidadOrgNombre(String auxUnidadOrgNombre) {
		this.auxUnidadOrgNombre = auxUnidadOrgNombre;
	}

	public String getAuxTipoContratoNombre() {
		return auxTipoContratoNombre;
	}

	public void setAuxTipoContratoNombre(String auxTipoContratoNombre) {
		this.auxTipoContratoNombre = auxTipoContratoNombre;
	}

	public String getAuxUnidadOperativaNombre() {
		return auxUnidadOperativaNombre;
	}

	public void setAuxUnidadOperativaNombre(String auxUnidadOperativaNombre) {
		this.auxUnidadOperativaNombre = auxUnidadOperativaNombre;
	}

	public String getAuxEmpleadoNombre() {
		return auxEmpleadoNombre;
	}

	public void setAuxEmpleadoNombre(String auxEmpleadoNombre) {
		this.auxEmpleadoNombre = auxEmpleadoNombre;
	}

	public String getAuxTipoPlanillaNombre() {
		return auxTipoPlanillaNombre;
	}

	public void setAuxTipoPlanillaNombre(String auxTipoPlanillaNombre) {
		this.auxTipoPlanillaNombre = auxTipoPlanillaNombre;
	}

	public String getEmpleadoString() {
		return empleadoString;
	}

	public void setEmpleadoString(String empleadoString) {
		this.empleadoString = empleadoString;
	}

	public String getCodigopuestoString() {
		return codigopuestoString;
	}

	public void setCodigopuestoString(String codigopuestoString) {
		this.codigopuestoString = codigopuestoString;
	}

	public String getUnidadorganigramaString() {
		return unidadorganigramaString;
	}

	public void setUnidadorganigramaString(String unidadorganigramaString) {
		this.unidadorganigramaString = unidadorganigramaString;
	}

	public String getAuxResponsableNombre() {
		return auxResponsableNombre;
	}

	public void setAuxResponsableNombre(String auxResponsableNombre) {
		this.auxResponsableNombre = auxResponsableNombre;
	}

	public String getResponsableString() {
		return responsableString;
	}

	public void setResponsableString(String responsableString) {
		this.responsableString = responsableString;
	}

	public String getAuxMotivoNombre() {
		return auxMotivoNombre;
	}

	public void setAuxMotivoNombre(String auxMotivoNombre) {
		this.auxMotivoNombre = auxMotivoNombre;
	}

	public String getMotivoString() {
		return motivoString;
	}

	public void setMotivoString(String motivoString) {
		this.motivoString = motivoString;
	}

	public String getCursoTipo() {
		return cursoTipo;
	}

	public void setCursoTipo(String cursoTipo) {
		this.cursoTipo = cursoTipo;
	}

	public String getTipoCapacitacion() {
		return tipoCapacitacion;
	}

	public void setTipoCapacitacion(String tipoCapacitacion) {
		this.tipoCapacitacion = tipoCapacitacion;
	}

	public String getCentroCapacitacion() {
		return centroCapacitacion;
	}

	public void setCentroCapacitacion(String centroCapacitacion) {
		this.centroCapacitacion = centroCapacitacion;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getNumCapacitacion() {
		return numCapacitacion;
	}

	public void setNumCapacitacion(String numCapacitacion) {
		this.numCapacitacion = numCapacitacion;
	}

	public Date getFechasolicitudesde() {
		return fechasolicitudesde;
	}

	public void setFechasolicitudesde(Date fechasolicitudesde) {
		this.fechasolicitudesde = fechasolicitudesde;
	}

	public Date getFechavigenciahasta() {
		return fechavigenciahasta;
	}

	public void setFechavigenciahasta(Date fechavigenciahasta) {
		this.fechavigenciahasta = fechavigenciahasta;
	}



	public Date getFechasolicitudhasta() {
		return fechasolicitudhasta;
	}

	public void setFechasolicitudhasta(Date fechasolicitudhasta) {
		this.fechasolicitudhasta = fechasolicitudhasta;
	}

	public Date getFechavigenciadesde() {
		return fechavigenciadesde;
	}

	public void setFechavigenciadesde(Date fechavigenciadesde) {
		this.fechavigenciadesde = fechavigenciadesde;
	}
	
	

}
