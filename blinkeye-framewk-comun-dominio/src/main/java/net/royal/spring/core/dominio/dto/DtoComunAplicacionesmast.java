package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;

import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;

public class DtoComunAplicacionesmast  extends DominioTransaccion{
	private String aplicacioncodigo;
	private String descripcioncorta;
	private String descripcionlarga;
	private String ultimoperiodocontable;
	private String sistemafuente;
	private String estadisponible;
	private String departamentorevisor;
	private String ultimoperiodoprocesado;
	private String aplicacionusuario;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String aplicacionusuario02;
	private String aplicacionusuario03;
	private String aplicacionusuario04;
	private Integer orden;
	private String codigocontablevalid;
	
	private BigDecimal ROWNUM_;
	private String uuid;	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	public String getCodigocontablevalid() {
		return codigocontablevalid;
	}

	public void setCodigocontablevalid(String codigocontablevalid) {
		this.codigocontablevalid = codigocontablevalid;
	}

	public DtoComunAplicacionesmast() {}
	public DtoComunAplicacionesmast(String aplicacioncodigo) {
		this.aplicacioncodigo=aplicacioncodigo;
	}
	
	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}
	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}
	public String getDescripcioncorta() {
		return descripcioncorta;
	}
	public void setDescripcioncorta(String descripcioncorta) {
		this.descripcioncorta = descripcioncorta;
	}
	public String getDescripcionlarga() {
		return descripcionlarga;
	}
	public void setDescripcionlarga(String descripcionlarga) {
		this.descripcionlarga = descripcionlarga;
	}
	public String getUltimoperiodocontable() {
		return ultimoperiodocontable;
	}
	public void setUltimoperiodocontable(String ultimoperiodocontable) {
		this.ultimoperiodocontable = ultimoperiodocontable;
	}
	public String getSistemafuente() {
		return sistemafuente;
	}
	public void setSistemafuente(String sistemafuente) {
		this.sistemafuente = sistemafuente;
	}
	public String getEstadisponible() {
		return estadisponible;
	}
	public void setEstadisponible(String estadisponible) {
		this.estadisponible = estadisponible;
	}
	public String getDepartamentorevisor() {
		return departamentorevisor;
	}
	public void setDepartamentorevisor(String departamentorevisor) {
		this.departamentorevisor = departamentorevisor;
	}
	public String getUltimoperiodoprocesado() {
		return ultimoperiodoprocesado;
	}
	public void setUltimoperiodoprocesado(String ultimoperiodoprocesado) {
		this.ultimoperiodoprocesado = ultimoperiodoprocesado;
	}
	public String getAplicacionusuario() {
		return aplicacionusuario;
	}
	public void setAplicacionusuario(String aplicacionusuario) {
		this.aplicacionusuario = aplicacionusuario;
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
	public String getAplicacionusuario02() {
		return aplicacionusuario02;
	}
	public void setAplicacionusuario02(String aplicacionusuario02) {
		this.aplicacionusuario02 = aplicacionusuario02;
	}
	public String getAplicacionusuario03() {
		return aplicacionusuario03;
	}
	public void setAplicacionusuario03(String aplicacionusuario03) {
		this.aplicacionusuario03 = aplicacionusuario03;
	}
	public String getAplicacionusuario04() {
		return aplicacionusuario04;
	}
	public void setAplicacionusuario04(String aplicacionusuario04) {
		this.aplicacionusuario04 = aplicacionusuario04;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
 
	public BeanAplicacionesmast obtenerBean() {
		return obtenerBeanCore(new BeanAplicacionesmast(),ConstantePantallaAccion.NINGUNO);
	}

	public BeanAplicacionesmast obtenerBeanRegistrar() {
		return obtenerBeanCore(new BeanAplicacionesmast(),ConstantePantallaAccion.INSERTAR);
	}

	public BeanAplicacionesmast obtenerBeanActualizar(BeanAplicacionesmast bean) {
		return obtenerBeanCore(bean,ConstantePantallaAccion.ACTUALIZAR);
	}

	private BeanAplicacionesmast obtenerBeanCore(BeanAplicacionesmast bean,String tipo) {
		if (UString.esNuloVacio(tipo))
			tipo=ConstantePantallaAccion.NINGUNO;

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		switch (tipo) {
		case ConstantePantallaAccion.INSERTAR:
		case ConstantePantallaAccion.NINGUNO:
			bean.getPk().setAplicacioncodigo(aplicacioncodigo);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setUltimoperiodocontable(ultimoperiodocontable);
			bean.setSistemafuente(sistemafuente);
			bean.setEstadisponible(estadisponible);
			bean.setDepartamentorevisor(departamentorevisor);
			bean.setUltimoperiodoprocesado(ultimoperiodoprocesado);
			bean.setAplicacionusuario(aplicacionusuario);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setAplicacionusuario02(aplicacionusuario02);
			bean.setAplicacionusuario03(aplicacionusuario03);
			bean.setAplicacionusuario04(aplicacionusuario04);
			bean.setOrden(orden);
			bean.setCodigocontablevalid(codigocontablevalid);
 			bean.setUuid(uuid);
			break;
		case ConstantePantallaAccion.ACTUALIZAR:
			 
			//bean.getPk().setAplicacioncodigo(aplicacioncodigo);
			bean.setDescripcioncorta(descripcioncorta);
			bean.setDescripcionlarga(descripcionlarga);
			bean.setUltimoperiodocontable(ultimoperiodocontable);
			bean.setSistemafuente(sistemafuente);
			bean.setEstadisponible(estadisponible);
			bean.setDepartamentorevisor(departamentorevisor);
			bean.setUltimoperiodoprocesado(ultimoperiodoprocesado);
			bean.setAplicacionusuario(aplicacionusuario);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			bean.setAplicacionusuario02(aplicacionusuario02);
			bean.setAplicacionusuario03(aplicacionusuario03);
			bean.setAplicacionusuario04(aplicacionusuario04);
			bean.setOrden(orden);
			bean.setCodigocontablevalid(codigocontablevalid);
			//bean.setUuid(uuid);
			break;
		}

		return bean;
	}

}
