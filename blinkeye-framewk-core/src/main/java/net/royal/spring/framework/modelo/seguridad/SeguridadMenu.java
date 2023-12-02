package net.royal.spring.framework.modelo.seguridad;

import java.util.List;

public class SeguridadMenu {
	private List<SeguridadMenuItem> items;

	public SeguridadMenu() {
	}

	public SeguridadMenu(List<SeguridadMenuItem> items) {
		this.items = items;
	}

	public List<SeguridadMenuItem> getItems() {
		return items;
	}

	public void setItems(List<SeguridadMenuItem> items) {
		this.items = items;
	}
}
