package net.royal.spring.workflow.dominio.dto;

import java.math.BigDecimal;

public class DtoWfFlujoNivelDocumento {
	private String proceso;
	private Integer flujo;
	private Integer nivel;
	private String tipodocumento;
	private String des;
	private boolean requerido;
	private boolean firmadigital;
	private boolean firmaimagen;
	private boolean editable;

	private String requeridoA;
	private String firmadigitalA;
	private String firmaimagenA;
	private String editableA;

	public Integer getFlujo() {
		return flujo;
	}

	public void setFlujo(Integer flujo) {
		this.flujo = flujo;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	public boolean isFirmadigital() {
		return firmadigital;
	}

	public void setFirmadigital(boolean firmadigital) {
		this.firmadigital = firmadigital;
	}

	public boolean isFirmaimagen() {
		return firmaimagen;
	}

	public void setFirmaimagen(boolean firmaimagen) {
		this.firmaimagen = firmaimagen;
	}

	public String getRequeridoA() {
		return requeridoA;
	}

	public void setRequeridoA(String requeridoA) {
		this.requeridoA = requeridoA;
	}

	public String getFirmadigitalA() {
		return firmadigitalA;
	}

	public void setFirmadigitalA(String firmadigitalA) {
		this.firmadigitalA = firmadigitalA;
	}

	public String getFirmaimagenA() {
		return firmaimagenA;
	}

	public void setFirmaimagenA(String firmaimagenA) {
		this.firmaimagenA = firmaimagenA;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getEditableA() {
		return editableA;
	}

	public void setEditableA(String editableA) {
		this.editableA = editableA;
	}

}
