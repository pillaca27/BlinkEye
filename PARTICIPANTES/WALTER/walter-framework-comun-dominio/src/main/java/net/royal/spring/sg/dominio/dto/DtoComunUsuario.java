package net.royal.spring.sg.dominio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.sg.dominio.BeanUsuario;

public class DtoComunUsuario  extends DominioTransaccion{
	private String usuario;
	private String usuarioperfil;
	private String nombre;
	private String clave;
	private String expirarpasswordflag;
	private java.util.Date fechaexpiracion;
	private java.util.Date ultimologin;
	private Integer numerologinsdisponible;
	private Integer numerologinsusados;
	private String sqllogin;
	private String sqlpassword;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String usuariored;
	private String horainicio;
	private String horafin;
	private String horainicioap;
	private String horafinap;
	private String valuehsh;
	private java.util.Date fechacreacion;
	private String usuariocreacion;
	private String forcelogonspringflag;
	private java.util.Date fechapassword;
	private String situacion;

	private Integer status;
	private String mensajeuser;
	private Integer personagroid;
	private String usuarioupdate;
	 

	
	
	
	private String claveOld;
	private String claveNueva;
	private String claveRep;
	private Integer li_retorno;
	private Integer il_vigencia;
	private List<DominioMensajeUsuario> listaErrores;
	private String informacionConexion;
	private String mostrarPantallaCambioClave;
	private String puedePasar;
	private String aplicacioncodigo;
	private String ultimoperiodocontable;
	private String claveOriginal;
	private Boolean ib_cambiarClave;
	private String claveDefault;
	private String compania;
	
	// ATRIBUTOS DTO ANTERIOR AGRO - LEONARDO
	private String codigo;
	private String tipoRegistro;
	private Character situacioncod;
	public BigDecimal ROWNUM_;
	// FIN - LEONARDO

	//PARA KPI
	private Integer persona;
	private Integer personanumero;
	private Integer frmcodigo;
	private List<DtoComunUsuarioDetalle> detalleunidadnegocio;
	private List<DtoComunUsuarioDetalle> detallecampania;
	private List<DtoComunUsuarioDetalle> detallearea;
	//FIN
	private String transaccionEstado = "OK";
	//private List<DominioMensajeUsuario> transaccionListaMensajes = new ArrayList<>();


	private BeanUsuario usuarioBean;
	
	
	public BeanUsuario getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(BeanUsuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensajeuser() {
		return mensajeuser;
	}

	public void setMensajeuser(String mensajeuser) {
		this.mensajeuser = mensajeuser;
	}

	public Integer getPersonagroid() {
		return personagroid;
	}

	public void setPersonagroid(Integer personagroid) {
		this.personagroid = personagroid;
	}

	public String getUsuarioupdate() {
		return usuarioupdate;
	}

	public void setUsuarioupdate(String usuarioupdate) {
		this.usuarioupdate = usuarioupdate;
	}

	public String getClaveOld() {
		return claveOld;
	}

	public void setClaveOld(String claveOld) {
		this.claveOld = claveOld;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getClaveRep() {
		return claveRep;
	}

	public void setClaveRep(String claveRep) {
		this.claveRep = claveRep;
	}

	public Integer getLi_retorno() {
		return li_retorno;
	}

	public void setLi_retorno(Integer li_retorno) {
		this.li_retorno = li_retorno;
	}

	public Integer getIl_vigencia() {
		return il_vigencia;
	}

	public void setIl_vigencia(Integer il_vigencia) {
		this.il_vigencia = il_vigencia;
	}

	public List<DominioMensajeUsuario> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<DominioMensajeUsuario> listaErrores) {
		this.listaErrores = listaErrores;
	}

	public String getInformacionConexion() {
		return informacionConexion;
	}

	public void setInformacionConexion(String informacionConexion) {
		this.informacionConexion = informacionConexion;
	}

	public String getMostrarPantallaCambioClave() {
		return mostrarPantallaCambioClave;
	}

	public void setMostrarPantallaCambioClave(String mostrarPantallaCambioClave) {
		this.mostrarPantallaCambioClave = mostrarPantallaCambioClave;
	}

	public String getPuedePasar() {
		return puedePasar;
	}

	public void setPuedePasar(String puedePasar) {
		this.puedePasar = puedePasar;
	}

	public String getAplicacioncodigo() {
		return aplicacioncodigo;
	}

	public void setAplicacioncodigo(String aplicacioncodigo) {
		this.aplicacioncodigo = aplicacioncodigo;
	}

	public String getUltimoperiodocontable() {
		return ultimoperiodocontable;
	}

	public void setUltimoperiodocontable(String ultimoperiodocontable) {
		this.ultimoperiodocontable = ultimoperiodocontable;
	}

	public String getClaveOriginal() {
		return claveOriginal;
	}

	public void setClaveOriginal(String claveOriginal) {
		this.claveOriginal = claveOriginal;
	}

	public Boolean getIb_cambiarClave() {
		return ib_cambiarClave;
	}

	public void setIb_cambiarClave(Boolean ib_cambiarClave) {
		this.ib_cambiarClave = ib_cambiarClave;
	}

	public String getClaveDefault() {
		return claveDefault;
	}

	public void setClaveDefault(String claveDefault) {
		this.claveDefault = claveDefault;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Character getSituacioncod() {
		return situacioncod;
	}

	public void setSituacioncod(Character situacioncod) {
		this.situacioncod = situacioncod;
	}

	public BigDecimal getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(BigDecimal rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public Integer getPersona() {
		return persona;
	}

	public void setPersona(Integer persona) {
		this.persona = persona;
	}

	public Integer getPersonanumero() {
		return personanumero;
	}

	public void setPersonanumero(Integer personanumero) {
		this.personanumero = personanumero;
	}

	public Integer getFrmcodigo() {
		return frmcodigo;
	}

	public void setFrmcodigo(Integer frmcodigo) {
		this.frmcodigo = frmcodigo;
	}

	public List<DtoComunUsuarioDetalle> getDetalleunidadnegocio() {
		return detalleunidadnegocio;
	}

	public void setDetalleunidadnegocio(List<DtoComunUsuarioDetalle> detalleunidadnegocio) {
		this.detalleunidadnegocio = detalleunidadnegocio;
	}

	public List<DtoComunUsuarioDetalle> getDetallecampania() {
		return detallecampania;
	}

	public void setDetallecampania(List<DtoComunUsuarioDetalle> detallecampania) {
		this.detallecampania = detallecampania;
	}

	public List<DtoComunUsuarioDetalle> getDetallearea() {
		return detallearea;
	}

	public void setDetallearea(List<DtoComunUsuarioDetalle> detallearea) {
		this.detallearea = detallearea;
	}

	public String getTransaccionEstado() {
		return transaccionEstado;
	}

	public void setTransaccionEstado(String transaccionEstado) {
		this.transaccionEstado = transaccionEstado;
	}

	/*public List<DominioMensajeUsuario> getTransaccionListaMensajes() {
		return transaccionListaMensajes;
	}

	public void setTransaccionListaMensajes(List<DominioMensajeUsuario> transaccionListaMensajes) {
		this.transaccionListaMensajes = transaccionListaMensajes;
	}*/

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUsuarioperfil() {
		return usuarioperfil;
	}

	public void setUsuarioperfil(String usuarioperfil) {
		this.usuarioperfil = usuarioperfil;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getExpirarpasswordflag() {
		return expirarpasswordflag;
	}

	public void setExpirarpasswordflag(String expirarpasswordflag) {
		this.expirarpasswordflag = expirarpasswordflag;
	}
	public java.util.Date getFechaexpiracion() {
		return fechaexpiracion;
	}

	public void setFechaexpiracion(java.util.Date fechaexpiracion) {
		this.fechaexpiracion = fechaexpiracion;
	}
	public java.util.Date getUltimologin() {
		return ultimologin;
	}

	public void setUltimologin(java.util.Date ultimologin) {
		this.ultimologin = ultimologin;
	}
	public Integer getNumerologinsdisponible() {
		return numerologinsdisponible;
	}

	public void setNumerologinsdisponible(Integer numerologinsdisponible) {
		this.numerologinsdisponible = numerologinsdisponible;
	}
	public Integer getNumerologinsusados() {
		return numerologinsusados;
	}

	public void setNumerologinsusados(Integer numerologinsusados) {
		this.numerologinsusados = numerologinsusados;
	}
	public String getSqllogin() {
		return sqllogin;
	}

	public void setSqllogin(String sqllogin) {
		this.sqllogin = sqllogin;
	}
	public String getSqlpassword() {
		return sqlpassword;
	}

	public void setSqlpassword(String sqlpassword) {
		this.sqlpassword = sqlpassword;
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
	public String getUsuariored() {
		return usuariored;
	}

	public void setUsuariored(String usuariored) {
		this.usuariored = usuariored;
	}
	public String getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(String horainicio) {
		this.horainicio = horainicio;
	}
	public String getHorafin() {
		return horafin;
	}

	public void setHorafin(String horafin) {
		this.horafin = horafin;
	}
	public String getHorainicioap() {
		return horainicioap;
	}

	public void setHorainicioap(String horainicioap) {
		this.horainicioap = horainicioap;
	}
	public String getHorafinap() {
		return horafinap;
	}

	public void setHorafinap(String horafinap) {
		this.horafinap = horafinap;
	}
	public String getValuehsh() {
		return valuehsh;
	}

	public void setValuehsh(String valuehsh) {
		this.valuehsh = valuehsh;
	}
	public java.util.Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(java.util.Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}
	public String getForcelogonspringflag() {
		return forcelogonspringflag;
	}

	public void setForcelogonspringflag(String forcelogonspringflag) {
		this.forcelogonspringflag = forcelogonspringflag;
	}
	public java.util.Date getFechapassword() {
		return fechapassword;
	}

	public void setFechapassword(java.util.Date fechapassword) {
		this.fechapassword = fechapassword;
	}
	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	public BeanUsuario obtenerBean() {
		BeanUsuario bean = new BeanUsuario();
		return obtenerBean(bean);
	}

	public BeanUsuario obtenerBean(BeanUsuario bean) {
		if (bean == null)
			bean = new BeanUsuario();

		bean.getPk().setUsuario(usuario);
		bean.setUsuarioperfil(usuarioperfil);
		bean.setNombre(nombre);
		bean.setClave(clave);
		bean.setExpirarpasswordflag(expirarpasswordflag);
		bean.setFechaexpiracion(fechaexpiracion);
		bean.setUltimologin(ultimologin);
		bean.setNumerologinsdisponible(numerologinsdisponible);
		bean.setNumerologinsusados(numerologinsusados);
		bean.setSqllogin(sqllogin);
		bean.setSqlpassword(sqlpassword);
		bean.setEstado(estado);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setUsuariored(usuariored);
		//bean.setHorainicio(horainicio);
		//bean.setHorafin(horafin);
		//bean.setHorainicioap(horainicioap);
		//bean.setHorafinap(horafinap);
		//bean.setValuehsh(valuehsh);
		//bean.setFechacreacion(fechacreacion);
		//bean.setUsuariocreacion(usuariocreacion);
		//bean.setForcelogonspringflag(forcelogonspringflag);
		bean.setFechapassword(fechapassword);
		bean.setSituacion(situacion);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
	
}
