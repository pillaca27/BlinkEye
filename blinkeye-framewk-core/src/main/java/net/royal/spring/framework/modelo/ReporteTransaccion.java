package net.royal.spring.framework.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class ReporteTransaccion extends DominioTransaccion{
	public static final String ORIGEN_WORKFLOW = "WORKFLOW";
	public static final String ORIGEN_GENERICO = "GENERICO";
	
	private String origen = ORIGEN_GENERICO;
	
	private String aplicacionCodigo;
	private String reporteCodigo;
	private String reporteTipo;
	private byte[] reporteBinario;
	private String reporteRutaCompleta;
	
	private String companiaSocio;
	private String periodo;
	private String version;	
	
	private Map parametros;
	private List<DominioParametroPersistencia> params;
	
	private String resultadoAsunto;
	private byte[] resultadoCuerpoBinario;
	private String resultadoCuerpoCadena;

	public Integer getWorkFlowFlujo() {
		return workFlowFlujo;
	}

	public void setWorkFlowFlujo(Integer workFlowFlujo) {
		this.workFlowFlujo = workFlowFlujo;
	}

	public static String getOrigenWorkflow() {
		return ORIGEN_WORKFLOW;
	}

	public static String getOrigenGenerico() {
		return ORIGEN_GENERICO;
	}

	private Integer workFlowNivel;
	private Integer workFlowFlujo;
	private String workFlowAccion;
	private String workFlowFlgCorreoNiveles;
	
	public String getNombreCompletoReporte(){
		String msgRetorno="";
		
		if (UString.estaVacio(companiaSocio))
			companiaSocio="999999";
		if (UString.estaVacio(periodo))
			periodo="999999";
		if (UString.estaVacio(version))
			version="999999";
		
		msgRetorno = aplicacionCodigo + "_" + reporteCodigo;
		msgRetorno = msgRetorno + "_" + companiaSocio;
		msgRetorno = msgRetorno + "_" + periodo;
		msgRetorno = msgRetorno + "_" + version;
		
		return msgRetorno;
	}
	
	public ReporteTransaccion() {};
	public ReporteTransaccion(String aplicacionCodigo,String reporteCodigo,String companiaSocio,String periodo,String version){
		this.aplicacionCodigo=aplicacionCodigo;
		this.reporteCodigo=reporteCodigo;
		
		this.companiaSocio=companiaSocio;
		this.periodo=periodo;
		this.version=version;
	}
	public ReporteTransaccion(String aplicacionCodigo,String reporteCodigo,String companiaSocio,String periodo){
		this.aplicacionCodigo=aplicacionCodigo;
		this.reporteCodigo=reporteCodigo;
		
		this.companiaSocio=companiaSocio;
		this.periodo=periodo;
	}
	public ReporteTransaccion(String aplicacionCodigo,String reporteCodigo,String companiaSocio){
		this.aplicacionCodigo=aplicacionCodigo;
		this.reporteCodigo=reporteCodigo;		
		this.companiaSocio=companiaSocio;
	}
	public ReporteTransaccion(String aplicacionCodigo,String reporteCodigo){
		this.aplicacionCodigo=aplicacionCodigo;
		this.reporteCodigo=reporteCodigo;
	}
	
	public String getCompaniaSocio() {
		return companiaSocio;
	}

	public void setCompaniaSocio(String companiaSocio) {
		this.companiaSocio = companiaSocio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAplicacionCodigo() {
		return aplicacionCodigo;
	}

	public void setAplicacionCodigo(String aplicacionCodigo) {
		this.aplicacionCodigo = aplicacionCodigo;
	}

	public String getReporteCodigo() {
		return reporteCodigo;
	}

	public void setReporteCodigo(String reporteCodigo) {
		this.reporteCodigo = reporteCodigo;
	}

	public Map getParametros() {
		if (parametros==null)
			parametros=new HashMap();
		return parametros;
	}

	public void setParametros(Map parametros) {
		this.parametros = parametros;
	}

	public String getResultadoAsunto() {
		return resultadoAsunto;
	}

	public void setResultadoAsunto(String resultadoAsunto) {
		this.resultadoAsunto = resultadoAsunto;
	}

	public byte[] getResultadoCuerpoBinario() {
		return resultadoCuerpoBinario;
	}

	public void setResultadoCuerpoBinario(byte[] resultadoCuerpoBinario) {
		this.resultadoCuerpoBinario = resultadoCuerpoBinario;
	}

	public String getResultadoCuerpoCadena() {
		return resultadoCuerpoCadena;
	}

	public void setResultadoCuerpoCadena(String resultadoCuerpoCadena) {
		this.resultadoCuerpoCadena = resultadoCuerpoCadena;
	}

	public String getReporteTipo() {
		return reporteTipo;
	}

	public void setReporteTipo(String reporteTipo) {
		this.reporteTipo = reporteTipo;
	}

	public byte[] getReporteBinario() {
		return reporteBinario;
	}

	public void setReporteBinario(byte[] reporteBinario) {
		this.reporteBinario = reporteBinario;
	}

	public String getReporteRutaCompleta() {
		return reporteRutaCompleta;
	}

	public void setReporteRutaCompleta(String reporteRutaCompleta) {
		this.reporteRutaCompleta = reporteRutaCompleta;
	}

	public String getOrigen() {
		if (UString.esNuloVacio(origen))
			origen=ORIGEN_GENERICO;
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Integer getWorkFlowNivel() {
		return workFlowNivel;
	}

	public void setWorkFlowNivel(Integer workFlowNivel) {
		this.workFlowNivel = workFlowNivel;
	}
	
	public String getWorkFlowFlgCorreoNiveles() {
		if (UString.esNuloVacio(workFlowFlgCorreoNiveles))
			workFlowFlgCorreoNiveles="N";
		return workFlowFlgCorreoNiveles;
	}

	public void setWorkFlowFlgCorreoNiveles(String workFlowFlgCorreoNiveles) {
		this.workFlowFlgCorreoNiveles = workFlowFlgCorreoNiveles;
	}

	public String getWorkFlowAccion() {
		return workFlowAccion;
	}

	public void setWorkFlowAccion(String workFlowAccion) {
		this.workFlowAccion = workFlowAccion;
	}

	public List<DominioParametroPersistencia> getParams() {
		return params;
	}

	public void setParams(List<DominioParametroPersistencia> params) {
		this.params = params;
	}
	
}
