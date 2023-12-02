package net.royal.spring.contabilidad.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @tabla dbo.lastvouchernumber
 */
public class BeanLastvouchernumberPk implements java.io.Serializable {

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANYOWNER", length = 8, nullable = false)
	private String companyowner;

	@Size(min = 0, max = 4)
	@NotNull
	@NotEmpty
	@Column(name = "YEAR", length = 4, nullable = false)
	private String year;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "APPLICATION", length = 2, nullable = false)
	private String application;

	@Size(min = 0, max = 2)
	@NotNull
	@NotEmpty
	@Column(name = "TYPE", length = 2, nullable = false)
	private String type;

	public BeanLastvouchernumberPk() {
	}

	public BeanLastvouchernumberPk(String pcompanyowner, String pyear, String papplication, String ptype) {
		this.companyowner = pcompanyowner;
		this.year = pyear;
		this.application = papplication;
		this.type = ptype;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public String getCompanyowner() {
		return companyowner;
	}

	/**
	 * 
	 * 
	 * @campo companyowner
	 */
	public void setCompanyowner(String companyowner) {
		this.companyowner = companyowner;
	}

	/**
	 * 
	 * 
	 * @campo year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 
	 * 
	 * @campo year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 
	 * 
	 * @campo application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * 
	 * 
	 * @campo application
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * 
	 * 
	 * @campo type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * 
	 * @campo type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
