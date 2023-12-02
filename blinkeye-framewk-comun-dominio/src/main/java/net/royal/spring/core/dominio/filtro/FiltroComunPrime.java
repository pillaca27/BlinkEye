package net.royal.spring.core.dominio.filtro;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class FiltroComunPrime  extends DominioTransaccion{	
	
	private BigDecimal digits;

	public BigDecimal getDigits() {
		return digits;
	}

	public void setDigits(BigDecimal digits) {
		this.digits = digits;
	}
		
}
