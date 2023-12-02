package net.royal.spring.seguridad.dominio.dto;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.seguridad.dominio.BeanUsuario;

public class DtoComunUsuarioBean extends DominioTransaccion {
	
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
	private Integer status;
	private String mensajeuser;
	private java.util.Date fechacreacion;
	private String usuariocreacion;
	private String forcelogonspringflag;
	private java.util.Date fechapassword;
	private String situacion;
	private Integer personagroid;
	private String usuarioupdate;
	private BeanUsuario usuario;

	public BeanUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(BeanUsuario usuario) {
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

	public Integer getPersonagroid() {
		return personagroid;
	}

	public void setPersonagroid(Integer personagroid) {
		this.personagroid = personagroid;
	}

	

	public String getMensajeuser() {
		return mensajeuser;
	}

	public void setMensajeuser(String mensajeuser) {
		this.mensajeuser = mensajeuser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUsuarioupdate() {
		return usuarioupdate;
	}

	public void setUsuarioupdate(String usuarioupdate) {
		this.usuarioupdate = usuarioupdate;
	}
}
