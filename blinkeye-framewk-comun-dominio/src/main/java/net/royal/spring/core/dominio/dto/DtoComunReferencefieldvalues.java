package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunReferencefieldvalues  extends DominioTransaccion{
	private String referencefield;
	private String referencevalue;
	private String description;
	
	public DtoComunReferencefieldvalues() {}
	public DtoComunReferencefieldvalues(String referencefield,String referencevalue) {
		this.referencefield=referencefield;
		this.referencevalue=referencevalue;
	}
	
	public String getReferencefield() {
		return referencefield;
	}
	public void setReferencefield(String referencefield) {
		this.referencefield = referencefield;
	}
	public String getReferencevalue() {
		return referencevalue;
	}
	public void setReferencevalue(String referencevalue) {
		this.referencevalue = referencevalue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
