package net.royal.spring.util.dominio.dto;

import java.util.ArrayList;
import java.util.List;

public class DtoComunFile {

	private String data;
	private String label;
	private String icon;
	private String expandedIcon;
	private String collapsedIcon;
	private Boolean leaf;
	private List<DtoComunFile> children;

	public DtoComunFile() {
		this.children = new ArrayList<DtoComunFile>();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public List<DtoComunFile> getChildren() {
		return children;
	}

	public void setChildren(List<DtoComunFile> children) {
		this.children = children;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

}
