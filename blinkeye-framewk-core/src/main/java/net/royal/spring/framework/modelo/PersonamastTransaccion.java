package net.royal.spring.framework.modelo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class PersonamastTransaccion extends DominioTransaccion{
	private Integer persona;
	private String busqueda;
	private String nombrecompleto;	
	private String nombres;
	private String apellidopaterno;
	private String apellidomaterno;
	private String sexo;
	private String tipocomprador;
	private String tipodocumento;
	private String tipopersona;
	private Date fechanacimiento;
	private String codigousuario;	
	private String escliente;	
	private String esproveedor;
	private String esempleado;
	private String esotro;
	private String documento;
	private String documentofiscal;
	private String documentoidentidad;
	private String estado;
	private String estadoNombre;
	private String correoelectronico;
	private String correoElectronicoOtros;
		
	private String direccion;
	private String telefono;
	private String pais;		
	private String moneda;
	private String formapago;
	private String fax;
	private String tiposervicio;
	private String personaContacto;
	private String localidad;
	private String actividad;
	
	
	

	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
	public String getTiposervicio() {
		return tiposervicio;
	}
	public void setTiposervicio(String tiposervicio) {
		this.tiposervicio = tiposervicio;
	}
	public PersonamastTransaccion() {}
	public PersonamastTransaccion(Integer persona) {
		this.persona=persona;
	}
	
	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	@JsonIgnore
	private Integer ROWNUM_;

	public Integer getPersona() {
		return persona;
	}

	public void setPersona(Integer persona) {
		this.persona = persona;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getNombrecompleto() {
		return nombrecompleto;
	}

	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidopaterno() {
		return apellidopaterno;
	}

	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}

	public String getApellidomaterno() {
		return apellidomaterno;
	}

	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getTipopersona() {
		return tipopersona;
	}

	public void setTipopersona(String tipopersona) {
		this.tipopersona = tipopersona;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(String codigousuario) {
		this.codigousuario = codigousuario;
	}

	public String getEscliente() {
		return escliente;
	}

	public void setEscliente(String escliente) {
		this.escliente = escliente;
	}

	public String getEsproveedor() {
		return esproveedor;
	}

	public void setEsproveedor(String esproveedor) {
		this.esproveedor = esproveedor;
	}

	public String getEsempleado() {
		return esempleado;
	}

	public void setEsempleado(String esempleado) {
		this.esempleado = esempleado;
	}

	public String getEsotro() {
		return esotro;
	}

	public void setEsotro(String esotro) {
		this.esotro = esotro;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDocumentofiscal() {
		return documentofiscal;
	}

	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}

	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public Integer getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(Integer rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFormapago() {
		return formapago;
	}
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTipocomprador() {
		return tipocomprador;
	}
	public void setTipocomprador(String tipocomprador) {
		this.tipocomprador = tipocomprador;
	}
	public String getCorreoElectronicoOtros() {
		return correoElectronicoOtros;
	}
	public void setCorreoElectronicoOtros(String correoElectronicoOtros) {
		this.correoElectronicoOtros = correoElectronicoOtros;
	}
	
	
}
