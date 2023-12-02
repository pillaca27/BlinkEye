package net.royal.spring.framework.web.database;

import javax.persistence.Column;
import javax.persistence.Id;


public class ClienteDatabase {

	@Id
	@Column(name = "SID")
	private String sid;

	@Column(name = "URI")
	private String uri;

	@Column(name = "USER")
	private String user;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DRIVER")
	private String driver;

	@Column(name = "USUARIO_SID")
	private String usuarioSid;

	@Column(name = "CLAVE_SID")
	private String claveSid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String usuer) {
		this.user = usuer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsuarioSid() {
		return usuarioSid;
	}

	public void setUsuarioSid(String usuarioSid) {
		this.usuarioSid = usuarioSid;
	}

	public String getClaveSid() {
		return claveSid;
	}

	public void setClaveSid(String claveSid) {
		this.claveSid = claveSid;
	}

}
