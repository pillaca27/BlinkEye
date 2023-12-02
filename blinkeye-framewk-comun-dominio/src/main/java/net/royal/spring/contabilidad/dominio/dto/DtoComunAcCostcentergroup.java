package net.royal.spring.contabilidad.dominio.dto;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentergroup;

import java.util.ArrayList;
import java.util.List;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

/**
 * 
 * 
 * @tabla dbo.AC_CostCenterGroup
*/
public class DtoComunAcCostcentergroup extends DominioTransaccion implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String costcentergroup;
	private String localname;
	private String englishname;
	private String costcentermajorgroup;
	private String status;
	private String lastuser;
	private java.util.Date lastdate;
	private String subgrupo;
	private List<DtoComunAcCostcentersubgroup> lstDetalle = new ArrayList<DtoComunAcCostcentersubgroup>();

	
	
	public String getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}

	public List<DtoComunAcCostcentersubgroup> getLstDetalle() {
		return lstDetalle;
	}

	public void setLstDetalle(List<DtoComunAcCostcentersubgroup> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public String getCostcentergroup() {
		return costcentergroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterGroup
	*/
	public void setCostcentergroup(String costcentergroup) {
		this.costcentergroup = costcentergroup;
	}
	/**
	 * 
	 * 
	 * @campo LocalName
	*/
	public String getLocalname() {
		return localname;
	}

	/**
	 * 
	 * 
	 * @campo LocalName
	*/
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	/**
	 * 
	 * 
	 * @campo EnglishName
	*/
	public String getEnglishname() {
		return englishname;
	}

	/**
	 * 
	 * 
	 * @campo EnglishName
	*/
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterMajorGroup
	*/
	public String getCostcentermajorgroup() {
		return costcentermajorgroup;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterMajorGroup
	*/
	public void setCostcentermajorgroup(String costcentermajorgroup) {
		this.costcentermajorgroup = costcentermajorgroup;
	}
	/**
	 * 
	 * 
	 * @campo Status
	*/
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * 
	 * @campo Status
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 
	 * 
	 * @campo LastUser
	*/
	public String getLastuser() {
		return lastuser;
	}

	/**
	 * 
	 * 
	 * @campo LastUser
	*/
	public void setLastuser(String lastuser) {
		this.lastuser = lastuser;
	}
	/**
	 * 
	 * 
	 * @campo Lastdate
	*/
	public java.util.Date getLastdate() {
		return lastdate;
	}

	/**
	 * 
	 * 
	 * @campo Lastdate
	*/
	public void setLastdate(java.util.Date lastdate) {
		this.lastdate = lastdate;
	}
	public BeanAcCostcentergroup obtenerBean() {
		BeanAcCostcentergroup bean = new BeanAcCostcentergroup();
		return obtenerBean(bean);
	}

	public BeanAcCostcentergroup obtenerBean(BeanAcCostcentergroup bean) {
		if (bean == null)
			bean = new BeanAcCostcentergroup();

		bean.getPk().setCostcentergroup(costcentergroup);
		bean.setLocalname(localname);
		bean.setEnglishname(englishname);
		bean.setCostcentermajorgroup(costcentermajorgroup);
		bean.setStatus(status);
		bean.setLastuser(lastuser);
		bean.setLastdate(lastdate);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}

}
