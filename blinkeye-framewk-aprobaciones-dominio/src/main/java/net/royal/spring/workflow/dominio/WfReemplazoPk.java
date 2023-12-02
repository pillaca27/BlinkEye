package net.royal.spring.workflow.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @tabla SGCORESYS.WF_REEMPLAZO
 */
public class WfReemplazoPk implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "SECUENCIA", nullable = false)
	private Integer secuencia;

	public WfReemplazoPk() {
	}

	public WfReemplazoPk(Integer psecuencia) {
		this.secuencia = psecuencia;
	}

	/**
	 * 
	 * 
	 * @campo SECUENCIA
	 */
	public Integer getSecuencia() {
		return secuencia;
	}

	/**
	 * 
	 * 
	 * @campo SECUENCIA
	 */
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

}
