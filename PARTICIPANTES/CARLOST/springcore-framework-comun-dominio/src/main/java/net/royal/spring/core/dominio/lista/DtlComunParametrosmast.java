package net.royal.spring.core.dominio.lista;

import java.math.BigDecimal;

import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtlComunParametrosmast {
	private String companiacodigo;
	private String aplicacioncodigo;
	private String parametroclave;
	private String descripcionparametro;
	private String explicacion;
	private String tipodedatoflag;
	private String texto;
	private BigDecimal numero;
	private java.util.Date fecha;
	private String financecomunflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String explicacionadicional;
	
	private String tipovalordescripcion;
	private String estadodescripcion;
	private String valordescripcion;
	
	
	
	public String getTipovalordescripcion() {
		return tipovalordescripcion;
	}

	public void setTipovalordescripcion(String tipovalordescripcion) {
		this.tipovalordescripcion = tipovalordescripcion;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	public String getValordescripcion() {
		return valordescripcion;
	}

	public void setValordescripcion(String valordescripcion) {
		this.valordescripcion = valordescripcion;
	}

	private BigDecimal ROWNUM_;

	public String getCompaniacodigo() {
		return companiacodigo;
	}

	public void setCompaniacodigo(String companiacodigo) {
		this.companiacodigo = companiacodigo;
	}
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getParametroclave() {
		return parametroclave;
	}

	public void setParametroclave(String parametroclave) {
		this.parametroclave = parametroclave;
	}
	public String getDescripcionparametro() {
		return descripcionparametro;
	}

	public void setDescripcionparametro(String descripcionparametro) {
		this.descripcionparametro = descripcionparametro;
	}
	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	public String getTipodedatoflag() {
		return tipodedatoflag;
	}

	public void setTipodedatoflag(String tipodedatoflag) {
		this.tipodedatoflag = tipodedatoflag;
	}
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public BigDecimal getNumero() {
		return numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}
	public java.util.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	
	private DominioPaginacion paginacion;

	public DominioPaginacion getPaginacion() {
		if (paginacion == null)
			paginacion = new DominioPaginacion();
		return paginacion;
	}

	public void setPaginacion(DominioPaginacion paginacion) {
		this.paginacion = paginacion;
	}

	public BeanParametrosmast obtenerBean() {
		BeanParametrosmast bean = new BeanParametrosmast();
		return obtenerBean(bean);
	}

	public BeanParametrosmast obtenerBean(BeanParametrosmast bean) {
		if (bean == null)
			bean = new BeanParametrosmast();

		bean.getPk().setCompaniacodigo(companiacodigo);
		bean.getPk().setAplicacioncodigo(aplicacioncodigo);
		bean.getPk().setParametroclave(parametroclave);
		bean.setDescripcionparametro(descripcionparametro);
		bean.setExplicacion(explicacion);
		bean.setTipodedatoflag(tipodedatoflag);
		bean.setTexto(texto);
		bean.setNumero(numero != null ? numero.intValue() : null );
		bean.setFecha(fecha);
		bean.setFinancecomunflag(financecomunflag);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setExplicacionadicional(explicacionadicional);
		return bean;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getFinancecomunflag() {
		return financecomunflag;
	}

	public void setFinancecomunflag(String financecomunflag) {
		this.financecomunflag = financecomunflag;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUltimousuario() {
		return ultimousuario;
	}

	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}

	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}

	public String getExplicacionadicional() {
		return explicacionadicional;
	}

	public void setExplicacionadicional(String explicacionadicional) {
		this.explicacionadicional = explicacionadicional;
	}
}
