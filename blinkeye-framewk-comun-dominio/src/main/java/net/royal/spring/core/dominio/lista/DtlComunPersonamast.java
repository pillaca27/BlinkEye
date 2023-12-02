package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanPersonamast;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.PersonaMast
*/
public class DtlComunPersonamast extends DominioTransaccion implements java.io.Serializable{


	private Integer persona;
	private String origen;
	private String apellidopaterno;
	private String apellidomaterno;
	private String nombres;
	private String nombrecompleto;
	private String busqueda;
	private String tipodocumento;
	private String documento;
	private String codigobarras;
	private String escliente;
	private String esproveedor;
	private String esempleado;
	private String esotro;
	private String tipopersona;
	private java.util.Date fechanacimiento;
	private String ciudadnacimiento;
	private String sexo;
	private String nacionalidad;
	private String estadocivil;
	private String nivelinstruccion;
	private String direccion;
	private String codigopostal;
	private String provincia;
	private String departamento;
	private String telefono;
	private String fax;
	private String documentofiscal;
	private String documentoidentidad;
	private String carnetextranjeria;
	private String documentomilitarfa;
	private String tipobrevete;
	private String brevete;
	private String pasaporte;
	private String nombreemergencia;
	private String direccionemergencia;
	private String telefonoemergencia;
	private String bancomonedalocal;
	private String tipocuentalocal;
	private String cuentamonedalocal;
	private String bancomonedaextranjera;
	private String tipocuentaextranjera;
	private String cuentamonedaextranjera;
	private String personaant;
	private String correoelectronico;
	private String clasepersonacodigo;
	private String enfermedadgraveflag;
	private String estado;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String tipopersonausuario;
	private java.util.Date ingresofecharegistro;
	private String ingresoaplicacioncodigo;
	private String ingresousuario;
	private String pymeflag;
	private String grupoempresarial;
	private String personaclasificacion;
	private String tarjetadecredito;
	private String flagactualizacion;
	private String celular;
	private String parentescoemergencia;
	private String celularemergencia;
	private String lugarnacimiento;
	private String sunatnacionalidad;
	private String sunatvia;
	private String sunatzona;
	private String sunatubigeo;
	private String sunatdomiciliado;
	private java.util.Date breveteFecvcto;
	private String paisemisor;
	private String codigoldn;
	private String codigointerbancario;
	private String direccionreferencia;
	private String sunatconvenio;
	private String flagsolicitausuario;
	private java.util.Date carnetextranjeriaFecvcto;
	private String pais;
	private String sunatndconvenio;
	private String sunatndtiporenta;
	private String sunatndexoneracion;
	private String sunatndservicio;
	
	private String estadodescripcion;
	private String cli;
	private String prov;
	private String emp;
	private Date fechainactivacion;	
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getFechainactivacion() {
		return fechainactivacion;
	}

	public void setFechainactivacion(Date fechainactivacion) {
		this.fechainactivacion = fechainactivacion;
	}

	public String getCli() {
		return cli;
	}

	public void setCli(String cli) {
		this.cli = cli;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getEstadodescripcion() {
		return estadodescripcion;
	}

	public void setEstadodescripcion(String estadodescripcion) {
		this.estadodescripcion = estadodescripcion;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public Integer getPersona() {
		return persona;
	}

	/**
	 * 
	 * 
	 * @campo Persona
	*/
	public void setPersona(Integer persona) {
		this.persona = persona;
	}
	/**
	 * 
	 * 
	 * @campo Origen
	*/
	public String getOrigen() {
		return origen;
	}

	/**
	 * 
	 * 
	 * @campo Origen
	*/
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * 
	 * 
	 * @campo ApellidoPaterno
	*/
	public String getApellidopaterno() {
		return apellidopaterno;
	}

	/**
	 * 
	 * 
	 * @campo ApellidoPaterno
	*/
	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}
	/**
	 * 
	 * 
	 * @campo ApellidoMaterno
	*/
	public String getApellidomaterno() {
		return apellidomaterno;
	}

	/**
	 * 
	 * 
	 * @campo ApellidoMaterno
	*/
	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}
	/**
	 * 
	 * 
	 * @campo Nombres
	*/
	public String getNombres() {
		return nombres;
	}

	/**
	 * 
	 * 
	 * @campo Nombres
	*/
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * 
	 * 
	 * @campo NombreCompleto
	*/
	public String getNombrecompleto() {
		return nombrecompleto;
	}

	/**
	 * 
	 * 
	 * @campo NombreCompleto
	*/
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	/**
	 * 
	 * 
	 * @campo Busqueda
	*/
	public String getBusqueda() {
		return busqueda;
	}

	/**
	 * 
	 * 
	 * @campo Busqueda
	*/
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * 
	 * 
	 * @campo TipoDocumento
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 * 
	 * 
	 * @campo Documento
	*/
	public String getDocumento() {
		return documento;
	}

	/**
	 * 
	 * 
	 * @campo Documento
	*/
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	/**
	 * 
	 * 
	 * @campo CodigoBarras
	*/
	public String getCodigobarras() {
		return codigobarras;
	}

	/**
	 * 
	 * 
	 * @campo CodigoBarras
	*/
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	/**
	 * 
	 * 
	 * @campo EsCliente
	*/
	public String getEscliente() {
		return escliente;
	}

	/**
	 * 
	 * 
	 * @campo EsCliente
	*/
	public void setEscliente(String escliente) {
		this.escliente = escliente;
	}
	/**
	 * 
	 * 
	 * @campo EsProveedor
	*/
	public String getEsproveedor() {
		return esproveedor;
	}

	/**
	 * 
	 * 
	 * @campo EsProveedor
	*/
	public void setEsproveedor(String esproveedor) {
		this.esproveedor = esproveedor;
	}
	/**
	 * 
	 * 
	 * @campo EsEmpleado
	*/
	public String getEsempleado() {
		return esempleado;
	}

	/**
	 * 
	 * 
	 * @campo EsEmpleado
	*/
	public void setEsempleado(String esempleado) {
		this.esempleado = esempleado;
	}
	/**
	 * 
	 * 
	 * @campo EsOtro
	*/
	public String getEsotro() {
		return esotro;
	}

	/**
	 * 
	 * 
	 * @campo EsOtro
	*/
	public void setEsotro(String esotro) {
		this.esotro = esotro;
	}
	/**
	 * 
	 * 
	 * @campo TipoPersona
	*/
	public String getTipopersona() {
		return tipopersona;
	}

	/**
	 * 
	 * 
	 * @campo TipoPersona
	*/
	public void setTipopersona(String tipopersona) {
		this.tipopersona = tipopersona;
	}
	/**
	 * 
	 * 
	 * @campo FechaNacimiento
	*/
	public java.util.Date getFechanacimiento() {
		return fechanacimiento;
	}

	/**
	 * 
	 * 
	 * @campo FechaNacimiento
	*/
	public void setFechanacimiento(java.util.Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	/**
	 * 
	 * 
	 * @campo CiudadNacimiento
	*/
	public String getCiudadnacimiento() {
		return ciudadnacimiento;
	}

	/**
	 * 
	 * 
	 * @campo CiudadNacimiento
	*/
	public void setCiudadnacimiento(String ciudadnacimiento) {
		this.ciudadnacimiento = ciudadnacimiento;
	}
	/**
	 * 
	 * 
	 * @campo Sexo
	*/
	public String getSexo() {
		return sexo;
	}

	/**
	 * 
	 * 
	 * @campo Sexo
	*/
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * 
	 * 
	 * @campo Nacionalidad
	*/
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * 
	 * 
	 * @campo Nacionalidad
	*/
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * 
	 * 
	 * @campo EstadoCivil
	*/
	public String getEstadocivil() {
		return estadocivil;
	}

	/**
	 * 
	 * 
	 * @campo EstadoCivil
	*/
	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}
	/**
	 * 
	 * 
	 * @campo NivelInstruccion
	*/
	public String getNivelinstruccion() {
		return nivelinstruccion;
	}

	/**
	 * 
	 * 
	 * @campo NivelInstruccion
	*/
	public void setNivelinstruccion(String nivelinstruccion) {
		this.nivelinstruccion = nivelinstruccion;
	}
	/**
	 * 
	 * 
	 * @campo Direccion
	*/
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * 
	 * @campo Direccion
	*/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public String getCodigopostal() {
		return codigopostal;
	}

	/**
	 * 
	 * 
	 * @campo CodigoPostal
	*/
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public String getProvincia() {
		return provincia;
	}

	/**
	 * 
	 * 
	 * @campo Provincia
	*/
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * 
	 * 
	 * @campo Departamento
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 * 
	 * 
	 * @campo Telefono
	*/
	public String getTelefono() {
		return telefono;
	}

	/**
	 * 
	 * 
	 * @campo Telefono
	*/
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * 
	 * 
	 * @campo Fax
	*/
	public String getFax() {
		return fax;
	}

	/**
	 * 
	 * 
	 * @campo Fax
	*/
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 
	 * 
	 * @campo DocumentoFiscal
	*/
	public String getDocumentofiscal() {
		return documentofiscal;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoFiscal
	*/
	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}
	/**
	 * 
	 * 
	 * @campo DocumentoIdentidad
	*/
	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoIdentidad
	*/
	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}
	/**
	 * 
	 * 
	 * @campo CarnetExtranjeria
	*/
	public String getCarnetextranjeria() {
		return carnetextranjeria;
	}

	/**
	 * 
	 * 
	 * @campo CarnetExtranjeria
	*/
	public void setCarnetextranjeria(String carnetextranjeria) {
		this.carnetextranjeria = carnetextranjeria;
	}
	/**
	 * 
	 * 
	 * @campo DocumentoMilitarFA
	*/
	public String getDocumentomilitarfa() {
		return documentomilitarfa;
	}

	/**
	 * 
	 * 
	 * @campo DocumentoMilitarFA
	*/
	public void setDocumentomilitarfa(String documentomilitarfa) {
		this.documentomilitarfa = documentomilitarfa;
	}
	/**
	 * 
	 * 
	 * @campo TipoBrevete
	*/
	public String getTipobrevete() {
		return tipobrevete;
	}

	/**
	 * 
	 * 
	 * @campo TipoBrevete
	*/
	public void setTipobrevete(String tipobrevete) {
		this.tipobrevete = tipobrevete;
	}
	/**
	 * 
	 * 
	 * @campo Brevete
	*/
	public String getBrevete() {
		return brevete;
	}

	/**
	 * 
	 * 
	 * @campo Brevete
	*/
	public void setBrevete(String brevete) {
		this.brevete = brevete;
	}
	/**
	 * 
	 * 
	 * @campo Pasaporte
	*/
	public String getPasaporte() {
		return pasaporte;
	}

	/**
	 * 
	 * 
	 * @campo Pasaporte
	*/
	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}
	/**
	 * 
	 * 
	 * @campo NombreEmergencia
	*/
	public String getNombreemergencia() {
		return nombreemergencia;
	}

	/**
	 * 
	 * 
	 * @campo NombreEmergencia
	*/
	public void setNombreemergencia(String nombreemergencia) {
		this.nombreemergencia = nombreemergencia;
	}
	/**
	 * 
	 * 
	 * @campo DireccionEmergencia
	*/
	public String getDireccionemergencia() {
		return direccionemergencia;
	}

	/**
	 * 
	 * 
	 * @campo DireccionEmergencia
	*/
	public void setDireccionemergencia(String direccionemergencia) {
		this.direccionemergencia = direccionemergencia;
	}
	/**
	 * 
	 * 
	 * @campo TelefonoEmergencia
	*/
	public String getTelefonoemergencia() {
		return telefonoemergencia;
	}

	/**
	 * 
	 * 
	 * @campo TelefonoEmergencia
	*/
	public void setTelefonoemergencia(String telefonoemergencia) {
		this.telefonoemergencia = telefonoemergencia;
	}
	/**
	 * 
	 * 
	 * @campo BancoMonedaLocal
	*/
	public String getBancomonedalocal() {
		return bancomonedalocal;
	}

	/**
	 * 
	 * 
	 * @campo BancoMonedaLocal
	*/
	public void setBancomonedalocal(String bancomonedalocal) {
		this.bancomonedalocal = bancomonedalocal;
	}
	/**
	 * 
	 * 
	 * @campo TipoCuentaLocal
	*/
	public String getTipocuentalocal() {
		return tipocuentalocal;
	}

	/**
	 * 
	 * 
	 * @campo TipoCuentaLocal
	*/
	public void setTipocuentalocal(String tipocuentalocal) {
		this.tipocuentalocal = tipocuentalocal;
	}
	/**
	 * 
	 * 
	 * @campo CuentaMonedaLocal
	*/
	public String getCuentamonedalocal() {
		return cuentamonedalocal;
	}

	/**
	 * 
	 * 
	 * @campo CuentaMonedaLocal
	*/
	public void setCuentamonedalocal(String cuentamonedalocal) {
		this.cuentamonedalocal = cuentamonedalocal;
	}
	/**
	 * 
	 * 
	 * @campo BancoMonedaExtranjera
	*/
	public String getBancomonedaextranjera() {
		return bancomonedaextranjera;
	}

	/**
	 * 
	 * 
	 * @campo BancoMonedaExtranjera
	*/
	public void setBancomonedaextranjera(String bancomonedaextranjera) {
		this.bancomonedaextranjera = bancomonedaextranjera;
	}
	/**
	 * 
	 * 
	 * @campo TipoCuentaExtranjera
	*/
	public String getTipocuentaextranjera() {
		return tipocuentaextranjera;
	}

	/**
	 * 
	 * 
	 * @campo TipoCuentaExtranjera
	*/
	public void setTipocuentaextranjera(String tipocuentaextranjera) {
		this.tipocuentaextranjera = tipocuentaextranjera;
	}
	/**
	 * 
	 * 
	 * @campo CuentaMonedaExtranjera
	*/
	public String getCuentamonedaextranjera() {
		return cuentamonedaextranjera;
	}

	/**
	 * 
	 * 
	 * @campo CuentaMonedaExtranjera
	*/
	public void setCuentamonedaextranjera(String cuentamonedaextranjera) {
		this.cuentamonedaextranjera = cuentamonedaextranjera;
	}
	/**
	 * 
	 * 
	 * @campo PersonaAnt
	*/
	public String getPersonaant() {
		return personaant;
	}

	/**
	 * 
	 * 
	 * @campo PersonaAnt
	*/
	public void setPersonaant(String personaant) {
		this.personaant = personaant;
	}
	/**
	 * 
	 * 
	 * @campo CorreoElectronico
	*/
	public String getCorreoelectronico() {
		return correoelectronico;
	}

	/**
	 * 
	 * 
	 * @campo CorreoElectronico
	*/
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	/**
	 * 
	 * 
	 * @campo ClasePersonaCodigo
	*/
	public String getClasepersonacodigo() {
		return clasepersonacodigo;
	}

	/**
	 * 
	 * 
	 * @campo ClasePersonaCodigo
	*/
	public void setClasepersonacodigo(String clasepersonacodigo) {
		this.clasepersonacodigo = clasepersonacodigo;
	}
	/**
	 * 
	 * 
	 * @campo EnfermedadGraveFlag
	*/
	public String getEnfermedadgraveflag() {
		return enfermedadgraveflag;
	}

	/**
	 * 
	 * 
	 * @campo EnfermedadGraveFlag
	*/
	public void setEnfermedadgraveflag(String enfermedadgraveflag) {
		this.enfermedadgraveflag = enfermedadgraveflag;
	}
	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * 
	 * @campo Estado
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 * 
	 * 
	 * @campo UltimoUsuario
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaModif
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	/**
	 * 
	 * 
	 * @campo TipoPersonaUsuario
	*/
	public String getTipopersonausuario() {
		return tipopersonausuario;
	}

	/**
	 * 
	 * 
	 * @campo TipoPersonaUsuario
	*/
	public void setTipopersonausuario(String tipopersonausuario) {
		this.tipopersonausuario = tipopersonausuario;
	}
	/**
	 * 
	 * 
	 * @campo IngresoFechaRegistro
	*/
	public java.util.Date getIngresofecharegistro() {
		return ingresofecharegistro;
	}

	/**
	 * 
	 * 
	 * @campo IngresoFechaRegistro
	*/
	public void setIngresofecharegistro(java.util.Date ingresofecharegistro) {
		this.ingresofecharegistro = ingresofecharegistro;
	}
	/**
	 * 
	 * 
	 * @campo IngresoAplicacionCodigo
	*/
	public String getIngresoaplicacioncodigo() {
		return ingresoaplicacioncodigo;
	}

	/**
	 * 
	 * 
	 * @campo IngresoAplicacionCodigo
	*/
	public void setIngresoaplicacioncodigo(String ingresoaplicacioncodigo) {
		this.ingresoaplicacioncodigo = ingresoaplicacioncodigo;
	}
	/**
	 * 
	 * 
	 * @campo IngresoUsuario
	*/
	public String getIngresousuario() {
		return ingresousuario;
	}

	/**
	 * 
	 * 
	 * @campo IngresoUsuario
	*/
	public void setIngresousuario(String ingresousuario) {
		this.ingresousuario = ingresousuario;
	}
	/**
	 * 
	 * 
	 * @campo PYMEFlag
	*/
	public String getPymeflag() {
		return pymeflag;
	}

	/**
	 * 
	 * 
	 * @campo PYMEFlag
	*/
	public void setPymeflag(String pymeflag) {
		this.pymeflag = pymeflag;
	}
	/**
	 * 
	 * 
	 * @campo GrupoEmpresarial
	*/
	public String getGrupoempresarial() {
		return grupoempresarial;
	}

	/**
	 * 
	 * 
	 * @campo GrupoEmpresarial
	*/
	public void setGrupoempresarial(String grupoempresarial) {
		this.grupoempresarial = grupoempresarial;
	}
	/**
	 * 
	 * 
	 * @campo PersonaClasificacion
	*/
	public String getPersonaclasificacion() {
		return personaclasificacion;
	}

	/**
	 * 
	 * 
	 * @campo PersonaClasificacion
	*/
	public void setPersonaclasificacion(String personaclasificacion) {
		this.personaclasificacion = personaclasificacion;
	}
	/**
	 * 
	 * 
	 * @campo TarjetadeCredito
	*/
	public String getTarjetadecredito() {
		return tarjetadecredito;
	}

	/**
	 * 
	 * 
	 * @campo TarjetadeCredito
	*/
	public void setTarjetadecredito(String tarjetadecredito) {
		this.tarjetadecredito = tarjetadecredito;
	}
	/**
	 * 
	 * 
	 * @campo FlagActualizacion
	*/
	public String getFlagactualizacion() {
		return flagactualizacion;
	}

	/**
	 * 
	 * 
	 * @campo FlagActualizacion
	*/
	public void setFlagactualizacion(String flagactualizacion) {
		this.flagactualizacion = flagactualizacion;
	}
	/**
	 * 
	 * 
	 * @campo Celular
	*/
	public String getCelular() {
		return celular;
	}

	/**
	 * 
	 * 
	 * @campo Celular
	*/
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * 
	 * 
	 * @campo ParentescoEmergencia
	*/
	public String getParentescoemergencia() {
		return parentescoemergencia;
	}

	/**
	 * 
	 * 
	 * @campo ParentescoEmergencia
	*/
	public void setParentescoemergencia(String parentescoemergencia) {
		this.parentescoemergencia = parentescoemergencia;
	}
	/**
	 * 
	 * 
	 * @campo CelularEmergencia
	*/
	public String getCelularemergencia() {
		return celularemergencia;
	}

	/**
	 * 
	 * 
	 * @campo CelularEmergencia
	*/
	public void setCelularemergencia(String celularemergencia) {
		this.celularemergencia = celularemergencia;
	}
	/**
	 * 
	 * 
	 * @campo LugarNacimiento
	*/
	public String getLugarnacimiento() {
		return lugarnacimiento;
	}

	/**
	 * 
	 * 
	 * @campo LugarNacimiento
	*/
	public void setLugarnacimiento(String lugarnacimiento) {
		this.lugarnacimiento = lugarnacimiento;
	}
	/**
	 * 
	 * 
	 * @campo SUNATNacionalidad
	*/
	public String getSunatnacionalidad() {
		return sunatnacionalidad;
	}

	/**
	 * 
	 * 
	 * @campo SUNATNacionalidad
	*/
	public void setSunatnacionalidad(String sunatnacionalidad) {
		this.sunatnacionalidad = sunatnacionalidad;
	}
	/**
	 * 
	 * 
	 * @campo SUNATVia
	*/
	public String getSunatvia() {
		return sunatvia;
	}

	/**
	 * 
	 * 
	 * @campo SUNATVia
	*/
	public void setSunatvia(String sunatvia) {
		this.sunatvia = sunatvia;
	}
	/**
	 * 
	 * 
	 * @campo SUNATZona
	*/
	public String getSunatzona() {
		return sunatzona;
	}

	/**
	 * 
	 * 
	 * @campo SUNATZona
	*/
	public void setSunatzona(String sunatzona) {
		this.sunatzona = sunatzona;
	}
	/**
	 * 
	 * 
	 * @campo SUNATUbigeo
	*/
	public String getSunatubigeo() {
		return sunatubigeo;
	}

	/**
	 * 
	 * 
	 * @campo SUNATUbigeo
	*/
	public void setSunatubigeo(String sunatubigeo) {
		this.sunatubigeo = sunatubigeo;
	}
	/**
	 * 
	 * 
	 * @campo SUNATDomiciliado
	*/
	public String getSunatdomiciliado() {
		return sunatdomiciliado;
	}

	/**
	 * 
	 * 
	 * @campo SUNATDomiciliado
	*/
	public void setSunatdomiciliado(String sunatdomiciliado) {
		this.sunatdomiciliado = sunatdomiciliado;
	}
	/**
	 * 
	 * 
	 * @campo brevete_fecvcto
	*/
	public java.util.Date getBreveteFecvcto() {
		return breveteFecvcto;
	}

	/**
	 * 
	 * 
	 * @campo brevete_fecvcto
	*/
	public void setBreveteFecvcto(java.util.Date breveteFecvcto) {
		this.breveteFecvcto = breveteFecvcto;
	}
	/**
	 * 
	 * 
	 * @campo PaisEmisor
	*/
	public String getPaisemisor() {
		return paisemisor;
	}

	/**
	 * 
	 * 
	 * @campo PaisEmisor
	*/
	public void setPaisemisor(String paisemisor) {
		this.paisemisor = paisemisor;
	}
	/**
	 * 
	 * 
	 * @campo CodigoLDN
	*/
	public String getCodigoldn() {
		return codigoldn;
	}

	/**
	 * 
	 * 
	 * @campo CodigoLDN
	*/
	public void setCodigoldn(String codigoldn) {
		this.codigoldn = codigoldn;
	}
	/**
	 * 
	 * 
	 * @campo CodigoInterbancario
	*/
	public String getCodigointerbancario() {
		return codigointerbancario;
	}

	/**
	 * 
	 * 
	 * @campo CodigoInterbancario
	*/
	public void setCodigointerbancario(String codigointerbancario) {
		this.codigointerbancario = codigointerbancario;
	}
	/**
	 * 
	 * 
	 * @campo DireccionReferencia
	*/
	public String getDireccionreferencia() {
		return direccionreferencia;
	}

	/**
	 * 
	 * 
	 * @campo DireccionReferencia
	*/
	public void setDireccionreferencia(String direccionreferencia) {
		this.direccionreferencia = direccionreferencia;
	}
	/**
	 * 
	 * 
	 * @campo SUNATConvenio
	*/
	public String getSunatconvenio() {
		return sunatconvenio;
	}

	/**
	 * 
	 * 
	 * @campo SUNATConvenio
	*/
	public void setSunatconvenio(String sunatconvenio) {
		this.sunatconvenio = sunatconvenio;
	}
	/**
	 * 
	 * 
	 * @campo FlagSolicitaUsuario
	*/
	public String getFlagsolicitausuario() {
		return flagsolicitausuario;
	}

	/**
	 * 
	 * 
	 * @campo FlagSolicitaUsuario
	*/
	public void setFlagsolicitausuario(String flagsolicitausuario) {
		this.flagsolicitausuario = flagsolicitausuario;
	}
	/**
	 * 
	 * 
	 * @campo CarnetExtranjeria_FecVcto
	*/
	public java.util.Date getCarnetextranjeriaFecvcto() {
		return carnetextranjeriaFecvcto;
	}

	/**
	 * 
	 * 
	 * @campo CarnetExtranjeria_FecVcto
	*/
	public void setCarnetextranjeriaFecvcto(java.util.Date carnetextranjeriaFecvcto) {
		this.carnetextranjeriaFecvcto = carnetextranjeriaFecvcto;
	}
	/**
	 * 
	 * 
	 * @campo PAIS
	*/
	public String getPais() {
		return pais;
	}

	/**
	 * 
	 * 
	 * @campo PAIS
	*/
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * 
	 * 
	 * @campo SUNATNDConvenio
	*/
	public String getSunatndconvenio() {
		return sunatndconvenio;
	}

	/**
	 * 
	 * 
	 * @campo SUNATNDConvenio
	*/
	public void setSunatndconvenio(String sunatndconvenio) {
		this.sunatndconvenio = sunatndconvenio;
	}
	/**
	 * 
	 * 
	 * @campo SUNATNDTipoRenta
	*/
	public String getSunatndtiporenta() {
		return sunatndtiporenta;
	}

	/**
	 * 
	 * 
	 * @campo SUNATNDTipoRenta
	*/
	public void setSunatndtiporenta(String sunatndtiporenta) {
		this.sunatndtiporenta = sunatndtiporenta;
	}
	/**
	 * 
	 * 
	 * @campo SUNATNDExoneracion
	*/
	public String getSunatndexoneracion() {
		return sunatndexoneracion;
	}

	/**
	 * 
	 * 
	 * @campo SUNATNDExoneracion
	*/
	public void setSunatndexoneracion(String sunatndexoneracion) {
		this.sunatndexoneracion = sunatndexoneracion;
	}
	/**
	 * 
	 * 
	 * @campo SUNATNDServicio
	*/
	public String getSunatndservicio() {
		return sunatndservicio;
	}

	/**
	 * 
	 * 
	 * @campo SUNATNDServicio
	*/
	public void setSunatndservicio(String sunatndservicio) {
		this.sunatndservicio = sunatndservicio;
	}

}
