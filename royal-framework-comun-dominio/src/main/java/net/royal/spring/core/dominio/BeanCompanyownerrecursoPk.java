package net.royal.spring.core.dominio;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeanCompanyownerrecursoPk  implements java.io.Serializable{

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANYOWNER",  nullable = false)
	public String Companyowner;
	
	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "TIPORECURSO",  nullable = false)
	public String Tiporecurso;
		
	
	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "PERIODO",  nullable = false)
	public String Periodo;
	  
	@Transient
	public String AuxCompanyowner;
	
	
	public BeanCompanyownerrecursoPk() {
	}

	public BeanCompanyownerrecursoPk(String Companyowner, String Tiporecurso, String Periodo) {
			this.Companyowner = Companyowner;
			this.Tiporecurso = Tiporecurso;
			this.Periodo = Periodo;
	}

	public String getCompanyowner() {
		return Companyowner;
	}

	public void setCompanyowner(String companyowner) {
		Companyowner = companyowner;
	}

	public String getTiporecurso() {
		return Tiporecurso;
	}

	public void setTiporecurso(String tiporecurso) {
		Tiporecurso = tiporecurso;
	}

	public String getPeriodo() {
		return Periodo;
	}

	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}

	public String getAuxCompanyowner() {
		return AuxCompanyowner;
	}

	public void setAuxCompanyowner(String auxCompanyowner) {
		AuxCompanyowner = auxCompanyowner;
	}
	
}
