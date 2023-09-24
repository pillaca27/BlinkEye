package net.royal.spring.util.dominio.dto;

import java.util.ArrayList;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunArbolFile extends DominioTransaccion{
	
	private List<DtoComunFile> data;

	public DtoComunArbolFile() {
		this.data = new ArrayList<DtoComunFile>();
	}

	public List<DtoComunFile> getData() {
		return data;
	}

	public void setData(List<DtoComunFile> data) {
		this.data = data;
	}

}
