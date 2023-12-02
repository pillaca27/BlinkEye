package net.royal.spring.contabilidad.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.lastvouchernumber
 */
@Entity
@Table(name = "LASTVOUCHERNUMBER")
public class BeanLastvouchernumber extends DominioTransaccion implements java.io.Serializable {

	@EmbeddedId
	private BeanLastvouchernumberPk pk;

	@Column(name = "MONTH01", nullable = true)
	private Integer month01;

	@Column(name = "MONTH02", nullable = true)
	private Integer month02;

	@Column(name = "MONTH03", nullable = true)
	private Integer month03;

	@Column(name = "MONTH04", nullable = true)
	private Integer month04;

	@Column(name = "MONTH05", nullable = true)
	private Integer month05;

	@Column(name = "MONTH06", nullable = true)
	private Integer month06;

	@Column(name = "MONTH07", nullable = true)
	private Integer month07;

	@Column(name = "MONTH08", nullable = true)
	private Integer month08;

	@Column(name = "MONTH09", nullable = true)
	private Integer month09;

	@Column(name = "MONTH10", nullable = true)
	private Integer month10;

	@Column(name = "MONTH11", nullable = true)
	private Integer month11;

	@Column(name = "MONTH12", nullable = true)
	private Integer month12;

	@Size(min = 0, max = 10)
	@Column(name = "LASTUSER", length = 10, nullable = true)
	private String lastuser;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "LASTDATE", nullable = true)
	private java.util.Date lastdate;

	public BeanLastvouchernumber() {
		pk = new BeanLastvouchernumberPk();
	}

	public BeanLastvouchernumber(BeanLastvouchernumberPk pk) {
		this.pk = pk;
	}

	public BeanLastvouchernumberPk getPk() {
		return pk;
	}

	public void setPk(BeanLastvouchernumberPk pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * 
	 * @campo month01
	 */
	public Integer getMonth01() {
		return month01;
	}

	/**
	 * 
	 * 
	 * @campo month01
	 */
	public void setMonth01(Integer month01) {
		this.month01 = month01;
	}

	/**
	 * 
	 * 
	 * @campo month02
	 */
	public Integer getMonth02() {
		return month02;
	}

	/**
	 * 
	 * 
	 * @campo month02
	 */
	public void setMonth02(Integer month02) {
		this.month02 = month02;
	}

	/**
	 * 
	 * 
	 * @campo month03
	 */
	public Integer getMonth03() {
		return month03;
	}

	/**
	 * 
	 * 
	 * @campo month03
	 */
	public void setMonth03(Integer month03) {
		this.month03 = month03;
	}

	/**
	 * 
	 * 
	 * @campo month04
	 */
	public Integer getMonth04() {
		return month04;
	}

	/**
	 * 
	 * 
	 * @campo month04
	 */
	public void setMonth04(Integer month04) {
		this.month04 = month04;
	}

	/**
	 * 
	 * 
	 * @campo month05
	 */
	public Integer getMonth05() {
		return month05;
	}

	/**
	 * 
	 * 
	 * @campo month05
	 */
	public void setMonth05(Integer month05) {
		this.month05 = month05;
	}

	/**
	 * 
	 * 
	 * @campo month06
	 */
	public Integer getMonth06() {
		return month06;
	}

	/**
	 * 
	 * 
	 * @campo month06
	 */
	public void setMonth06(Integer month06) {
		this.month06 = month06;
	}

	/**
	 * 
	 * 
	 * @campo month07
	 */
	public Integer getMonth07() {
		return month07;
	}

	/**
	 * 
	 * 
	 * @campo month07
	 */
	public void setMonth07(Integer month07) {
		this.month07 = month07;
	}

	/**
	 * 
	 * 
	 * @campo month08
	 */
	public Integer getMonth08() {
		return month08;
	}

	/**
	 * 
	 * 
	 * @campo month08
	 */
	public void setMonth08(Integer month08) {
		this.month08 = month08;
	}

	/**
	 * 
	 * 
	 * @campo month09
	 */
	public Integer getMonth09() {
		return month09;
	}

	/**
	 * 
	 * 
	 * @campo month09
	 */
	public void setMonth09(Integer month09) {
		this.month09 = month09;
	}

	/**
	 * 
	 * 
	 * @campo month10
	 */
	public Integer getMonth10() {
		return month10;
	}

	/**
	 * 
	 * 
	 * @campo month10
	 */
	public void setMonth10(Integer month10) {
		this.month10 = month10;
	}

	/**
	 * 
	 * 
	 * @campo month11
	 */
	public Integer getMonth11() {
		return month11;
	}

	/**
	 * 
	 * 
	 * @campo month11
	 */
	public void setMonth11(Integer month11) {
		this.month11 = month11;
	}

	/**
	 * 
	 * 
	 * @campo month12
	 */
	public Integer getMonth12() {
		return month12;
	}

	/**
	 * 
	 * 
	 * @campo month12
	 */
	public void setMonth12(Integer month12) {
		this.month12 = month12;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public String getLastuser() {
		return lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastuser
	 */
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo lastdate
	 */
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}

}
