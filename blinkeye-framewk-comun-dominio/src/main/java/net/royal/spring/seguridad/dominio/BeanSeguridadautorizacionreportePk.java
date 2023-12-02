package net.royal.spring.seguridad.dominio;


import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @tabla SGAGROSYS.SEGURIDADAUTORIZACIONREPORTE
*/
public class BeanSeguridadautorizacionreportePk implements java.io.Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "USUARIO", length = 20, nullable = false)
	private String usuario;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APLICACIONCODIGO", length = 2, nullable = false)
	private String aplicacioncodigo;

	@Size(min = 0, max = 3)
	@NotNull
	@NotEmpty
	@Column(name = "REPORTECODIGO", length = 3, nullable = false)
	private String reportecodigo;


	public BeanSeguridadautorizacionreportePk() {
	}

	public BeanSeguridadautorizacionreportePk(String pusuario,String paplicacioncodigo,String preportecodigo) {
this.usuario = pusuario;
		this.aplicacioncodigo = paplicacioncodigo;
		this.reportecodigo = preportecodigo;
	}

	/**
	 * Codigo Usuario 
	 * 
	 * @campo USUARIO
	*/
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Codigo Usuario 
	 * 
	 * @campo USUARIO
	*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * codigo de aplicacion 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	/**
	 * codigo de aplicacion 
	 * 
	 * @campo APLICACIONCODIGO
	*/
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	/**
	 * Codigo de Reporte 
	 * 
	 * @campo REPORTECODIGO
	*/
	public String getReportecodigo() {
		return reportecodigo;
	}

	/**
	 * Codigo de Reporte 
	 * 
	 * @campo REPORTECODIGO
	*/
	public void setReportecodigo(String reportecodigo) {
		this.reportecodigo = reportecodigo;
	}

}
