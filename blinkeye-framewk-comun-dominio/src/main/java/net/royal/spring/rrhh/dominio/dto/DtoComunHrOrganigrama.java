package net.royal.spring.rrhh.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunHrOrganigrama extends DominioTransaccion{
	
	private BigDecimal codigo;
	private String descripcion;
	private String usaCodigoCap;	
	private String codigoCap;
	private BigDecimal tiempoReclutar;
	private BigDecimal peso;
	private String estado;
	private String categoriaFuncional;
	private String gradoSalarial;
	private BigDecimal unidadOrganigrama;
	private String codigoOrganigrama;
	private String unidadOrganigramaDescripcion;
	private String companiaSocio;
	private BigDecimal anio;
	private String unidadNegocio;
	private BigDecimal secuencia;
	
	private BigDecimal codigoPadre;
	private String orden;

    private String principal;
    private BigDecimal nivel;
    private BigDecimal vacantes;
    private BigDecimal vacantesOcupados;
    private BigDecimal vacantesDisponibles;
    private String icono;
    private String descripcionPadre;
    private BigDecimal cantidadUbicacion;
    private String organigramaNombre;
    private String codigoNombre;
    private BigDecimal numeroEstandar;
    
    private String descripcionCap;
    private String tipoPuesto;
    private String tipoTrabajador;
    private String lineaCarrera;
    
    private BigDecimal ROWNUM_;
    
	public BigDecimal getCodigo() {
		return codigo;
	}
	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUsaCodigoCap() {
		return usaCodigoCap;
	}
	public void setUsaCodigoCap(String usaCodigoCap) {
		this.usaCodigoCap = usaCodigoCap;
	}
	public String getCodigoCap() {
		return codigoCap;
	}
	public void setCodigoCap(String codigoCap) {
		this.codigoCap = codigoCap;
	}
	public BigDecimal getTiempoReclutar() {
		return tiempoReclutar;
	}
	public void setTiempoReclutar(BigDecimal tiempoReclutar) {
		this.tiempoReclutar = tiempoReclutar;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCategoriaFuncional() {
		return categoriaFuncional;
	}
	public void setCategoriaFuncional(String categoriaFuncional) {
		this.categoriaFuncional = categoriaFuncional;
	}
	public String getGradoSalarial() {
		return gradoSalarial;
	}
	public void setGradoSalarial(String gradoSalarial) {
		this.gradoSalarial = gradoSalarial;
	}
	public BigDecimal getUnidadOrganigrama() {
		return unidadOrganigrama;
	}
	public void setUnidadOrganigrama(BigDecimal unidadOrganigrama) {
		this.unidadOrganigrama = unidadOrganigrama;
	}
	public String getCodigoOrganigrama() {
		return codigoOrganigrama;
	}
	public void setCodigoOrganigrama(String codigoOrganigrama) {
		this.codigoOrganigrama = codigoOrganigrama;
	}
	public String getUnidadOrganigramaDescripcion() {
		return unidadOrganigramaDescripcion;
	}
	public void setUnidadOrganigramaDescripcion(String unidadOrganigramaDescripcion) {
		this.unidadOrganigramaDescripcion = unidadOrganigramaDescripcion;
	}
	public String getCompaniaSocio() {
		return companiaSocio;
	}
	public void setCompaniaSocio(String companiaSocio) {
		this.companiaSocio = companiaSocio;
	}
	public BigDecimal getAnio() {
		return anio;
	}
	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}
	public String getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public BigDecimal getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(BigDecimal secuencia) {
		this.secuencia = secuencia;
	}
	public BigDecimal getCodigoPadre() {
		return codigoPadre;
	}
	public void setCodigoPadre(BigDecimal codigoPadre) {
		this.codigoPadre = codigoPadre;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public BigDecimal getNivel() {
		return nivel;
	}
	public void setNivel(BigDecimal nivel) {
		this.nivel = nivel;
	}
	public BigDecimal getVacantes() {
		return vacantes;
	}
	public void setVacantes(BigDecimal vacantes) {
		this.vacantes = vacantes;
	}
	public BigDecimal getVacantesOcupados() {
		return vacantesOcupados;
	}
	public void setVacantesOcupados(BigDecimal vacantesOcupados) {
		this.vacantesOcupados = vacantesOcupados;
	}
	public BigDecimal getVacantesDisponibles() {
		return vacantesDisponibles;
	}
	public void setVacantesDisponibles(BigDecimal vacantesDisponibles) {
		this.vacantesDisponibles = vacantesDisponibles;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getDescripcionPadre() {
		return descripcionPadre;
	}
	public void setDescripcionPadre(String descripcionPadre) {
		this.descripcionPadre = descripcionPadre;
	}
	public BigDecimal getCantidadUbicacion() {
		return cantidadUbicacion;
	}
	public void setCantidadUbicacion(BigDecimal cantidadUbicacion) {
		this.cantidadUbicacion = cantidadUbicacion;
	}
	public String getOrganigramaNombre() {
		return organigramaNombre;
	}
	public void setOrganigramaNombre(String organigramaNombre) {
		this.organigramaNombre = organigramaNombre;
	}
	public String getCodigoNombre() {
		return codigoNombre;
	}
	public void setCodigoNombre(String codigoNombre) {
		this.codigoNombre = codigoNombre;
	}
	public BigDecimal getNumeroEstandar() {
		return numeroEstandar;
	}
	public void setNumeroEstandar(BigDecimal numeroEstandar) {
		this.numeroEstandar = numeroEstandar;
	}
	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getDescripcionCap() {
		return descripcionCap;
	}
	public void setDescripcionCap(String descripcionCap) {
		this.descripcionCap = descripcionCap;
	}
	public String getTipoPuesto() {
		return tipoPuesto;
	}
	public void setTipoPuesto(String tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}
	public String getTipoTrabajador() {
		return tipoTrabajador;
	}
	public void setTipoTrabajador(String tipoTrabajador) {
		this.tipoTrabajador = tipoTrabajador;
	}
	public String getLineaCarrera() {
		return lineaCarrera;
	}
	public void setLineaCarrera(String lineaCarrera) {
		this.lineaCarrera = lineaCarrera;
	}
    
}
