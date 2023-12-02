package net.royal.spring.framework.modelo.generico.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DtoMaster {

	private String table;
	private String objeto;
	private String campo;
	private List<?> lstObjetos = new ArrayList<>();
	private List<String> variables = new ArrayList<String>();
	private BigDecimal ROWNUM_;

	public List<?> getLstObjetos() {
		return lstObjetos;
	}

	public void setLstObjetos(List<?> lstObjetos) {
		this.lstObjetos = lstObjetos;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

}
