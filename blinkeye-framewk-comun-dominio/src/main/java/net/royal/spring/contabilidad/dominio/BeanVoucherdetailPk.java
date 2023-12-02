package net.royal.spring.contabilidad.dominio;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * 
 * @tabla dbo.voucherdetail
 */
public class BeanVoucherdetailPk implements java.io.Serializable {

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "PERIOD", length = 6, nullable = false)
	private String period;

	@Size(min = 0, max = 8)
	@NotNull
	@NotEmpty
	@Column(name = "COMPANYOWNER", length = 8, nullable = false)
	private String companyowner;

	@Size(min = 0, max = 6)
	@NotNull
	@NotEmpty
	@Column(name = "VOUCHERNO", length = 6, nullable = false)
	private String voucherno;

	@NotNull
	@Column(name = "VOUCHERLINE", nullable = false)
	private Integer voucherline;

	public BeanVoucherdetailPk() {
	}

	public BeanVoucherdetailPk(String pperiod, String pcompanyowner, String pvoucherno, Integer pvoucherline) {
		this.period = pperiod;
		this.companyowner = pcompanyowner;
		this.voucherno = pvoucherno;
		this.voucherline = pvoucherline;
	}

	/**
	 * 
	 * 
	 * @campo period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * 
	 * 
	 * @campo period
	 */
	public void setPeriod(String period) {
		this.period = period;
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
	 * @campo voucherno
	 */
	public String getVoucherno() {
		return voucherno;
	}

	/**
	 * 
	 * 
	 * @campo voucherno
	 */
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}

	/**
	 * 
	 * 
	 * @campo voucherline
	 */
	public Integer getVoucherline() {
		return voucherline;
	}

	/**
	 * 
	 * 
	 * @campo voucherline
	 */
	public void setVoucherline(Integer voucherline) {
		this.voucherline = voucherline;
	}

}
