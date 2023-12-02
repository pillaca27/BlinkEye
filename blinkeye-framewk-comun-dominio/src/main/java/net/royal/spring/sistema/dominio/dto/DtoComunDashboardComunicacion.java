package net.royal.spring.sistema.dominio.dto;

public class DtoComunDashboardComunicacion {
	private String codigo = "";
	private String fotoUsuario = "assets/layout/images/user.png";
	private Integer personaId;
	private String usuario;
	private String cargo;
	private String icono="pi pi-comment";
	
	private String flgEditar;
	
	private String procesoId;
	private String procesoEstado;
	
	private String externoPk1;
	private String externoPk2;
	private String externoPk3;
	private String externoPk4;
	private String externoPk5;
	private String externoPk6;
	private String externoPk7;
	
	private Integer externoId1;
	private Integer externoId2;
	
	public DtoComunDashboardComunicacion() {}
	public DtoComunDashboardComunicacion(String pfotoUsuario,String usuario,String cargo,String icono) {
		this.fotoUsuario=pfotoUsuario;
		this.usuario=usuario;
		this.cargo=cargo;
		this.icono=icono;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getFlgEditar() {
		return flgEditar;
	}
	public void setFlgEditar(String flgEditar) {
		this.flgEditar = flgEditar;
	}
	public String getProcesoId() {
		return procesoId;
	}
	public void setProcesoId(String procesoId) {
		this.procesoId = procesoId;
	}
	public String getProcesoEstado() {
		return procesoEstado;
	}
	public void setProcesoEstado(String procesoEstado) {
		this.procesoEstado = procesoEstado;
	}
	public String getExternoPk1() {
		return externoPk1;
	}
	public void setExternoPk1(String externoPk1) {
		this.externoPk1 = externoPk1;
	}
	
	public String getFotoUsuario() {
		return fotoUsuario;
	}
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}
	
	public String getExternoPk2() {
		return externoPk2;
	}
	public void setExternoPk2(String externoPk2) {
		this.externoPk2 = externoPk2;
	}
	public String getExternoPk3() {
		return externoPk3;
	}
	public void setExternoPk3(String externoPk3) {
		this.externoPk3 = externoPk3;
	}
	public String getExternoPk4() {
		return externoPk4;
	}
	public void setExternoPk4(String externoPk4) {
		this.externoPk4 = externoPk4;
	}
	public String getExternoPk5() {
		return externoPk5;
	}
	public void setExternoPk5(String externoPk5) {
		this.externoPk5 = externoPk5;
	}
	public String getExternoPk6() {
		return externoPk6;
	}
	public void setExternoPk6(String externoPk6) {
		this.externoPk6 = externoPk6;
	}
	public String getExternoPk7() {
		return externoPk7;
	}
	public void setExternoPk7(String externoPk7) {
		this.externoPk7 = externoPk7;
	}
	public Integer getExternoId1() {
		return externoId1;
	}
	public void setExternoId1(Integer externoId1) {
		this.externoId1 = externoId1;
	}
	public Integer getExternoId2() {
		return externoId2;
	}
	public void setExternoId2(Integer externoId2) {
		this.externoId2 = externoId2;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}	
	
}
