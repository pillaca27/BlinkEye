package net.royal.spring.core.dominio;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;

/**
 * 
 * 
 * @tabla dbo.PersonaMast
*/
@Entity
@Table(name = "PERSONAMAST")
public class BeanPersonamast extends DominioTransaccion implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeanPersonamastPk pk;

	@Size(min = 0, max = 4)
	//@NotNull
	//@NotEmpty
	@Column(name = "ORIGEN", length = 4, nullable = false)
	private String origen;

	@Size(min = 0, max = 40)
	@Column(name = "APELLIDOPATERNO", length = 40, nullable = true)
	private String apellidopaterno;

	@Size(min = 0, max = 40)
	@Column(name = "APELLIDOMATERNO", length = 40, nullable = true)
	private String apellidomaterno;

	@Size(min = 0, max = 40)
	@Column(name = "NOMBRES", length = 40, nullable = true)
	private String nombres;

	@Size(min = 0, max = 150)
	@Column(name = "NOMBRECOMPLETO", length = 150, nullable = true)
	private String nombrecompleto;

	@Size(min = 0, max = 130)
	@Column(name = "BUSQUEDA", length = 130, nullable = true)
	private String busqueda;

	@Size(min = 0, max = 1)
	@NotNull
	@NotEmpty
	@Column(name = "TIPODOCUMENTO", length = 1, nullable = false)
	private String tipodocumento;

	@Size(min = 0, max = 20)
	@NotNull
	@NotEmpty
	@Column(name = "DOCUMENTO", length = 20, nullable = false)
	private String documento;

	@Size(min = 0, max = 18)
	@Column(name = "CODIGOBARRAS", length = 18, nullable = true)
	private String codigobarras;

	@Size(min = 0, max = 1)
	@Column(name = "ESCLIENTE", length = 1, nullable = true)
	private String escliente;

	@Size(min = 0, max = 1)
	@Column(name = "ESPROVEEDOR", length = 1, nullable = true)
	private String esproveedor;

	@Size(min = 0, max = 1)
	@Column(name = "ESEMPLEADO", length = 1, nullable = true)
	private String esempleado;

	@Size(min = 0, max = 1)
	@Column(name = "ESOTRO", length = 1, nullable = true)
	private String esotro;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOPERSONA", length = 1, nullable = true)
	private String tipopersona;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHANACIMIENTO", nullable = true)
	private java.util.Date fechanacimiento;

	@Size(min = 0, max = 20)
	@Column(name = "CIUDADNACIMIENTO", length = 20, nullable = true)
	private String ciudadnacimiento;

	@Size(min = 0, max = 1)
	@Column(name = "SEXO", length = 1, nullable = true)
	private String sexo;

	@Size(min = 0, max = 20)
	@Column(name = "NACIONALIDAD", length = 20, nullable = true)
	private String nacionalidad;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADOCIVIL", length = 1, nullable = true)
	private String estadocivil;

	@Size(min = 0, max = 3)
	@Column(name = "NIVELINSTRUCCION", length = 3, nullable = true)
	private String nivelinstruccion;

	@Size(min = 0, max = 200)
	@Column(name = "DIRECCION", length = 200, nullable = true)
	private String direccion;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOPOSTAL", length = 3, nullable = true)
	private String codigopostal;

	@Size(min = 0, max = 3)
	@Column(name = "PROVINCIA", length = 3, nullable = true)
	private String provincia;

	@Size(min = 0, max = 3)
	@Column(name = "DEPARTAMENTO", length = 3, nullable = true)
	private String departamento;

	@Size(min = 0, max = 50)
	@Column(name = "TELEFONO", length = 50, nullable = true)
	private String telefono;

	@Size(min = 0, max = 15)
	@Column(name = "FAX", length = 15, nullable = true)
	private String fax;

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTOFISCAL", length = 20, nullable = true)
	private String documentofiscal;

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTOIDENTIDAD", length = 20, nullable = true)
	private String documentoidentidad;

	@Size(min = 0, max = 10)
	@Column(name = "CARNETEXTRANJERIA", length = 10, nullable = true)
	private String carnetextranjeria;

	@Size(min = 0, max = 15)
	@Column(name = "DOCUMENTOMILITARFA", length = 15, nullable = true)
	private String documentomilitarfa;

	@Size(min = 0, max = 1)
	@Column(name = "TIPOBREVETE", length = 1, nullable = true)
	private String tipobrevete;

	@Size(min = 0, max = 18)
	@Column(name = "BREVETE", length = 18, nullable = true)
	private String brevete;

	@Size(min = 0, max = 18)
	@Column(name = "PASAPORTE", length = 18, nullable = true)
	private String pasaporte;

	@Size(min = 0, max = 50)
	@Column(name = "NOMBREEMERGENCIA", length = 50, nullable = true)
	private String nombreemergencia;

	@Size(min = 0, max = 60)
	@Column(name = "DIRECCIONEMERGENCIA", length = 60, nullable = true)
	private String direccionemergencia;

	@Size(min = 0, max = 15)
	@Column(name = "TELEFONOEMERGENCIA", length = 15, nullable = true)
	private String telefonoemergencia;

	@Size(min = 0, max = 3)
	@Column(name = "BANCOMONEDALOCAL", length = 3, nullable = true)
	private String bancomonedalocal;

	@Size(min = 0, max = 3)
	@Column(name = "TIPOCUENTALOCAL", length = 3, nullable = true)
	private String tipocuentalocal;

	@Size(min = 0, max = 40)
	@Column(name = "CUENTAMONEDALOCAL", length = 40, nullable = true)
	private String cuentamonedalocal;

	@Size(min = 0, max = 3)
	@Column(name = "BANCOMONEDAEXTRANJERA", length = 3, nullable = true)
	private String bancomonedaextranjera;

	@Size(min = 0, max = 3)
	@Column(name = "TIPOCUENTAEXTRANJERA", length = 3, nullable = true)
	private String tipocuentaextranjera;

	@Size(min = 0, max = 40)
	@Column(name = "CUENTAMONEDAEXTRANJERA", length = 40, nullable = true)
	private String cuentamonedaextranjera;

	@Size(min = 0, max = 15)
	@Column(name = "PERSONAANT", length = 15, nullable = true)
	private String personaant;

	@Size(min = 0, max = 255)
	@Column(name = "CORREOELECTRONICO", length = 255, nullable = true)
	private String correoelectronico;

	@Size(min = 0, max = 3)
	@Column(name = "CLASEPERSONACODIGO", length = 3, nullable = true)
	private String clasepersonacodigo;

	@Size(min = 0, max = 1)
	@Column(name = "ENFERMEDADGRAVEFLAG", length = 1, nullable = true)
	private String enfermedadgraveflag;

	@Size(min = 0, max = 1)
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	@Size(min = 0, max = 3)
	@Column(name = "TIPOPERSONAUSUARIO", length = 3, nullable = true)
	private String tipopersonausuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "INGRESOFECHAREGISTRO", nullable = true)
	private java.util.Date ingresofecharegistro;

	@Size(min = 0, max = 2)
	@Column(name = "INGRESOAPLICACIONCODIGO", length = 2, nullable = true)
	private String ingresoaplicacioncodigo;

	@Size(min = 0, max = 20)
	@Column(name = "INGRESOUSUARIO", length = 20, nullable = true)
	private String ingresousuario;

	@Size(min = 0, max = 1)
	@Column(name = "PYMEFLAG", length = 1, nullable = true)
	private String pymeflag;

	@Size(min = 0, max = 4)
	@Column(name = "GRUPOEMPRESARIAL", length = 4, nullable = true)
	private String grupoempresarial;

	@Size(min = 0, max = 8)
	@Column(name = "PERSONACLASIFICACION", length = 8, nullable = true)
	private String personaclasificacion;

	@Size(min = 0, max = 20)
	@Column(name = "TARJETADECREDITO", length = 20, nullable = true)
	private String tarjetadecredito;

	@Size(min = 0, max = 1)
	@Column(name = "FLAGACTUALIZACION", length = 1, nullable = true)
	private String flagactualizacion;

	@Size(min = 0, max = 15)
	@Column(name = "CELULAR", length = 15, nullable = true)
	private String celular;

	@Size(min = 0, max = 10)
	@Column(name = "PARENTESCOEMERGENCIA", length = 10, nullable = true)
	private String parentescoemergencia;

	@Size(min = 0, max = 15)
	@Column(name = "CELULAREMERGENCIA", length = 15, nullable = true)
	private String celularemergencia;

	@Size(min = 0, max = 80)
	@Column(name = "LUGARNACIMIENTO", length = 80, nullable = true)
	private String lugarnacimiento;

	@Size(min = 0, max = 6)
	@Column(name = "SUNATNACIONALIDAD", length = 6, nullable = true)
	private String sunatnacionalidad;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATVIA", length = 2, nullable = true)
	private String sunatvia;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATZONA", length = 2, nullable = true)
	private String sunatzona;

	@Size(min = 0, max = 10)
	@Column(name = "SUNATUBIGEO", length = 10, nullable = true)
	private String sunatubigeo;

	@Size(min = 0, max = 1)
	@Column(name = "SUNATDOMICILIADO", length = 1, nullable = true)
	private String sunatdomiciliado;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "BREVETE_FECVCTO", nullable = true)
	private java.util.Date breveteFecvcto;

	@Size(min = 0, max = 3)
	@Column(name = "PAISEMISOR", length = 3, nullable = true)
	private String paisemisor;

	@Size(min = 0, max = 3)
	@Column(name = "CODIGOLDN", length = 3, nullable = true)
	private String codigoldn;

	@Size(min = 0, max = 30)
	@Column(name = "CODIGOINTERBANCARIO", length = 30, nullable = true)
	private String codigointerbancario;

	@Size(min = 0, max = 255)
	@Column(name = "DIRECCIONREFERENCIA", length = 255, nullable = true)
	private String direccionreferencia;

	@Size(min = 0, max = 1)
	@Column(name = "SUNATCONVENIO", length = 1, nullable = true)
	private String sunatconvenio;

	@Size(min = 0, max = 1)
	@Column(name = "FLAGSOLICITAUSUARIO", length = 1, nullable = true)
	private String flagsolicitausuario;

	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "CARNETEXTRANJERIA_FECVCTO", nullable = true)
	private java.util.Date carnetextranjeriaFecvcto;

	@Size(min = 0, max = 4)
	@Column(name = "PAIS", length = 4, nullable = true)
	private String pais;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATNDCONVENIO", length = 2, nullable = true)
	private String sunatndconvenio;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATNDTIPORENTA", length = 2, nullable = true)
	private String sunatndtiporenta;

	@Size(min = 0, max = 1)
	@Column(name = "SUNATNDEXONERACION", length = 1, nullable = true)
	private String sunatndexoneracion;

	@Size(min = 0, max = 1)
	@Column(name = "SUNATNDSERVICIO", length = 1, nullable = true)
	private String sunatndservicio;

	@Size(min = 0, max = 2)
	@Column(name = "ESTADOGESTIONPROVEEDOR", length = 2, nullable = true)
	private String estadogestionproveedor;
	
	
	@JsonSerialize(using = USerializers.DateSerializer.class)
	@JsonDeserialize(using = UDeserializers.DateDeserializer.class)
	@Column(name = "FECHAINACTIVACION", nullable = true)
	private java.util.Date fechainactivacion;
	
	@Size(min = 0, max = 1000)
	@Column(name = "CORREOELECTRONICOOTROS", length = 1000, nullable = true)
	private String correoelectronicootros;
	
	@Size(min = 0, max = 36)
	@Column(name = "UUID", length = 36, nullable = true)
	private String uuid;	
	
	@Transient
	private Boolean flagRuc;
	
	
	
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getCorreoelectronicootros() {
		return correoelectronicootros;
	}


	public void setCorreoelectronicootros(String correoelectronicootros) {
		this.correoelectronicootros = correoelectronicootros;
	}


	public java.util.Date getFechainactivacion() {
		return fechainactivacion;
	}


	public void setFechainactivacion(java.util.Date fechainactivacion) {
		this.fechainactivacion = fechainactivacion;
	}


	public Boolean getFlagRuc() {
		return flagRuc;
	}


	public void setFlagRuc(Boolean flagRuc) {
		this.flagRuc = flagRuc;
	}


	public BeanPersonamast() {
		pk = new BeanPersonamastPk();
	}


	public BeanPersonamast(BeanPersonamastPk pk) {
		this.pk = pk;
	}

	public BeanPersonamastPk getPk() {
		return pk;
	}

	public void setPk(BeanPersonamastPk pk) {
		this.pk = pk;
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


	public String getEstadogestionproveedor() {
		return estadogestionproveedor;
	}


	public void setEstadogestionproveedor(String estadogestionproveedor) {
		this.estadogestionproveedor = estadogestionproveedor;
	}
	
}
