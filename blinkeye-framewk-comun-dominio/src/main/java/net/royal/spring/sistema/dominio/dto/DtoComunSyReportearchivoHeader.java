package net.royal.spring.sistema.dominio.dto;

import java.util.List;

public class DtoComunSyReportearchivoHeader {
	private DtoComunSyReportearchivo dto;
	private List<DtoComunSyReportearchivo>lista;
	
	public DtoComunSyReportearchivo getDto() {
		return dto;
	}
	public void setDto(DtoComunSyReportearchivo dto) {
		this.dto = dto;
	}
	public List<DtoComunSyReportearchivo> getLista() {
		return lista;
	}
	public void setLista(List<DtoComunSyReportearchivo> lista) {
		this.lista = lista;
	}
	
}
