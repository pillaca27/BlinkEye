package net.royal.spring.core.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.rest.UDeserializers;
import net.royal.spring.framework.web.rest.USerializers;
import net.royal.spring.proveedores.dominio.BeanGpRpActividadEconomica;

/**
 * 
 * 
 * @tabla SGCORESYS.PERSONAMAST
*/
@Entity
@Table(name = "PERSONAMAST")
public class BeanPersonaProveedormast extends DominioTransaccion implements java.io.Serializable{

	@EmbeddedId
	private BeanPersonaProveedorPk pk;

	@Size(min = 0, max = 4)
	@Column(name = "ORIGEN", length = 4, nullable = true)
	private String origen;

	@Size(min = 0, max = 60)
	@Column(name = "APELLIDOPATERNO", length = 60, nullable = true)
	private String apellidopaterno;

	@Size(min = 0, max = 60)
	@Column(name = "APELLIDOMATERNO", length = 60, nullable = true)
	private String apellidomaterno;

	@Size(min = 0, max = 300)
	@Column(name = "NOMBRES", length = 300, nullable = true)
	private String nombres;

	@Size(min = 0, max = 150)
	@Column(name = "NOMBRECOMPLETO", length = 150, nullable = true)
	private String nombrecompleto;

	@Size(min = 0, max = 80)
	@Column(name = "BUSQUEDA", length = 80, nullable = true)
	private String busqueda;

	@Size(min = 0, max = 1)
	@Column(name = "TIPODOCUMENTO", length = 1, nullable = true)
	private String tipodocumento;

	@Size(min = 0, max = 20)
	@Column(name = "DOCUMENTO", length = 20, nullable = true)
	private String documento;

	@Size(min = 0, max = 18)
	@Column(name = "CODIGOBARRAS", length = 18, nullable = true)
	private String codigobarras;

	@Size(min = 0, max = 3)
	@Column(name = "TIPOPERSONAUSUARIO", length = 3, nullable = true)
	private String tipopersonausuario;

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

	@Column(name = "FECHANACIMIENTO", nullable = true)
	private java.util.Date fechanacimiento;
	
	@Column(name = "FECHAINACTIVACION", nullable = true)
	private java.util.Date fechainactivacion;
	

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
	@Column(name = "DEPARTAMENTO", length = 3, nullable = true)
	private String departamento;

	@Size(min = 0, max = 30)
	@Column(name = "TELEFONO", length = 30, nullable = true)
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

	@Size(min = 0, max = 10)
	@Column(name = "DOCUMENTOMILITARFA", length = 10, nullable = true)
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
	@Column(name = "ESTADO", length = 1, nullable = true)
	private String estado;

	@Size(min = 0, max = 20)
	@Column(name = "ULTIMOUSUARIO", length = 20, nullable = true)
	private String ultimousuario;

	@Column(name = "ULTIMAFECHAMODIF", nullable = true)
	private java.util.Date ultimafechamodif;

	//@Size(min = 0, max = 15)
	//@Column(name = "CUENTAMONEDALOCAL_TMP", length = 15, nullable = true)
	//private String cuentamonedalocalTmp;

	//@Size(min = 0, max = 15)
	//@Column(name = "CUENTAMONEDAEXTRANJERA_TMP", length = 15, nullable = true)
	//private String cuentamonedaextranjeraTmp;

	@Size(min = 0, max = 1)
	@Column(name = "ENFERMEDADGRAVEFLAG", length = 1, nullable = true)
	private String enfermedadgraveflag;

	@Size(min = 0, max = 3)
	@Column(name = "PROVINCIA", length = 3, nullable = true)
	private String provincia;

	@Size(min = 0, max = 1)
	@Column(name = "TARJETADECREDITO", length = 1, nullable = true)
	private String tarjetadecredito;

	@Size(min = 0, max = 6)
	@Column(name = "FLAGACTUALIZACION", length = 6, nullable = true)
	private String flagactualizacion;

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

	@Size(min = 0, max = 15)
	@Column(name = "CELULAR", length = 15, nullable = true)
	private String celular;

	@Size(min = 0, max = 15)
	@Column(name = "CELULAREMERGENCIA", length = 15, nullable = true)
	private String celularemergencia;

	@Size(min = 0, max = 255)
	@Column(name = "LUGARNACIMIENTO", length = 255, nullable = true)
	private String lugarnacimiento;

	@Size(min = 0, max = 1)
	@Column(name = "PARENTESCOEMERGENCIA", length = 1, nullable = true)
	private String parentescoemergencia;

	@Size(min = 0, max = 8)
	@Column(name = "PERSONACLASIFICACION", length = 8, nullable = true)
	private String personaclasificacion;

	@Size(min = 0, max = 255)
	@Column(name = "DIRECCIONREFERENCIA", length = 255, nullable = true)
	private String direccionreferencia;

	//@Size(min = 0, max = 1)
	//@Column(name = "FLAGREPETIDO", length = 1, nullable = true)
	//private String flagrepetido;

	//@Size(min = 0, max = 15)
	//@Column(name = "CODDISCAMEC", length = 15, nullable = true)
	//private String coddiscamec;

	//@Column(name = "FECINIDISCAMEC", nullable = true)
	//private java.util.Date fecinidiscamec;

	//@Column(name = "FECFINDISCAMEC", nullable = true)
	//private java.util.Date fecfindiscamec;

	//@Size(min = 0, max = 15)
	//@Column(name = "CODLICARMA", length = 15, nullable = true)
	//private String codlicarma;

	//@Size(min = 0, max = 10)
	//@Column(name = "MARCAARMA", length = 10, nullable = true)
	//private String marcaarma;

	//@Size(min = 0, max = 10)
	//@Column(name = "SERIEARMA", length = 10, nullable = true)
	//private String seriearma;

	//@Column(name = "INICIOARMA", nullable = true)
	//private java.util.Date inicioarma;

	//@Column(name = "VENCIMIENTOARMA", nullable = true)
	//private java.util.Date vencimientoarma;

	//@Size(min = 0, max = 1)
	//@Column(name = "SEGURODISCAMEC", length = 1, nullable = true)
	//private String segurodiscamec;

	//@Size(min = 0, max = 3)
	//@Column(name = "CORRELATIVOSCTR", length = 3, nullable = true)
	//private String correlativosctr;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATZONA", length = 2, nullable = true)
	private String sunatzona;

	@Size(min = 0, max = 10)
	@Column(name = "SUNATUBIGEO", length = 10, nullable = true)
	private String sunatubigeo;

	@Size(min = 0, max = 6)
	@Column(name = "SUNATNACIONALIDAD", length = 6, nullable = true)
	private String sunatnacionalidad;

	@Size(min = 0, max = 2)
	@Column(name = "SUNATVIA", length = 2, nullable = true)
	private String sunatvia;

	@Size(min = 0, max = 1)
	@Column(name = "SUNATDOMICILIADO", length = 1, nullable = true)
	private String sunatdomiciliado;

	@Column(name = "BREVETE_FECVCTO", nullable = true)
	private java.util.Date breveteFecvcto;

	//@Column(name = "CARNETEXTRANJERIA_FECVCTO", nullable = true)
	//private java.util.Date carnetextranjeriaFecvcto;

	//@Size(min = 0, max = 1)
	//@Column(name = "PASAPORTE_FECVCTO", length = 1, nullable = true)
	//private String pasaporteFecvcto;

	//@Size(min = 0, max = 15)
	//@Column(name = "PERSONANEW", length = 15, nullable = true)
	//private String personanew;

	//@Size(min = 0, max = 6)
	//@Column(name = "PERSONA_OLD", length = 6, nullable = true)
	//private String personaOld;

	//@Size(min = 0, max = 1)
	//@Column(name = "DOCUMENTOIDENTIDAD_FECVCTO", length = 1, nullable = true)
	//private String documentoidentidadFecvcto;

	//@Column(name = "TEMP_PERSONA", nullable = true)
	//private Integer tempPersona;

	//@Size(min = 0, max = 80)
	//@Column(name = "CADENABUSQUEDA", length = 80, nullable = true)
	//private String cadenabusqueda;

	//@Size(min = 0, max = 20)
	//@Column(name = "CUENTACCILOCAL", length = 20, nullable = true)
	//private String cuentaccilocal;

	//@Size(min = 0, max = 20)
	//@Column(name = "CUENTACCIEXTRANJERA", length = 20, nullable = true)
	//private String cuentacciextranjera;

	@Size(min = 0, max = 3)
	@Column(name = "PAISEMISOR", length = 3, nullable = true)
	private String paisemisor;

	//@Size(min = 0, max = 3)
	//@Column(name = "CODIGOLDN", length = 3, nullable = true)
	//private String codigoldn;

	//@Size(min = 0, max = 20)
	//@Column(name = "CODIGOUSUARIO", length = 20, nullable = true)
	//private String codigousuario;

	//@Size(min = 0, max = 1)
	//@Column(name = "ESUSUARIO", length = 1, nullable = true)
	//private String esusuario;

	//@Size(min = 0, max = 1)
	//@Column(name = "FLAGSOLICITAUSUARIOSIGA", length = 1, nullable = true)
	//private String flagsolicitausuariosiga;

	//@Column(name = "SECUENCIACUENTABANCARIA", nullable = true)
	//private Integer secuenciacuentabancaria;

	//@Size(min = 0, max = 30)
	//@Column(name = "EXTENCIONFIRMA", length = 30, nullable = true)
	//private String extencionfirma;

	//private byte[] firma;

	//@Size(min = 0, max = 10)
	//@Column(name = "TIPOZONA", length = 10, nullable = true)
	//private String tipozona;

	//@Size(min = 0, max = 4000)
	//@Column(name = "ZONA", length = 4000, nullable = true)
	//private String zona;

	//@Column(name = "FECHAFALLECIMIENTO", nullable = true)
	//private java.util.Date fechafallecimiento;

	//@Column(name = "FECHASOLICITUDCU", nullable = true)
	//private java.util.Date fechasolicitudcu;

	//@Column(name = "PERSONASOLICITUDCU", nullable = true)
	//private Integer personasolicitudcu;

	//@Column(name = "IDPERSONAUNIFICADO", nullable = true)
	//private Integer idpersonaunificado;

	//@Size(min = 0, max = 10)
	//@Column(name = "SUNATACTIVIDAD", length = 10, nullable = true)
	//private String sunatactividad;
	
	@Size(min = 0, max = 4)
	@Column(name = "PAIS", length = 4, nullable = true)
	private String pais;
	
	@Size(min = 0, max = 100)
	@Column(name = "REPRESETANTENOMBRE", length = 100, nullable = true)
	private String represetanteNombre;

	@Size(min = 0, max = 1)
	@Column(name = "REPRESETANTETIPODOCUMENTO", length = 1, nullable = true)
	private String represetanteTipoDocumento;

	@Size(min = 0, max = 20)
	@Column(name = "REPRESETANTEDOCUMENTO", length = 20, nullable = true)
	private String represetanteDocumento;

	@Size(min = 0, max = 200)
	@Column(name = "REPRESETANTEDOMICILIOLEGAL", length = 200, nullable = true)
	private String represetanteDomicilioLegal;

	@Size(min = 0, max = 100)
	@Column(name = "REPRESETANTECONTACTO", length = 100, nullable = true)
	private String represetanteContacto;

	@Size(min = 0, max = 20)
	@Column(name = "REPRESETANTETELEFONO", length = 20, nullable = true)
	private String represetanteTelefono;

	@Size(min = 0, max = 100)
	@Column(name = "REPRESETANTECORREO", length = 100, nullable = true)
	private String represetanteCorreo;

	@Size(min = 0, max = 3)
	@Column(name = "REPRESENTANTEDISTRITO", length = 3, nullable = true)
	private String representanteDistrito;

	@Size(min = 0, max = 3)
	@Column(name = "REPRESENTANTEPROVINCIA", length = 3, nullable = true)
	private String representanteProvincia;

	@Size(min = 0, max = 3)
	@Column(name = "REPRESENTANTEDEPARTAMENTO", length = 3, nullable = true)
	private String representanteDepartamento;

	@Size(min = 0, max = 4)
	@Column(name = "REPRESETANTENAC", length = 4, nullable = true)
	private String represetanteNac;

	@Size(min = 0, max = 200)
	@Column(name = "REPRESETANTEOCUPACION", length = 200, nullable = true)
	private String represetanteOcupacion;
	
	@Size(min = 0, max = 2)
	@Column(name = "ESTADOGESTIONPROVEEDOR", length = 2, nullable = true)
	private String estadogestionproveedor;
	
	@Transient
	private String tipoproveedor;
	
	@Transient
	private String password1;
	
	@Transient
	private String password2;
	
	@Transient
	private List<BeanGpRpActividadEconomica> listaActividadEconomica;    

	
	public java.util.Date getFechainactivacion() {
		return fechainactivacion;
	}


	public void setFechainactivacion(java.util.Date fechainactivacion) {
		this.fechainactivacion = fechainactivacion;
	}


	public BeanPersonaProveedormast() {
		pk = new BeanPersonaProveedorPk();
		this.listaActividadEconomica = new ArrayList<BeanGpRpActividadEconomica>();
	}


	public BeanPersonaProveedormast(BeanPersonaProveedorPk pk) {
		this.pk = pk;
		this.listaActividadEconomica = new ArrayList<BeanGpRpActividadEconomica>();
	}

	public BeanPersonaProveedorPk getPk() {
		return pk;
	}

	public void setPk(BeanPersonaProveedorPk pk) {
		this.pk = pk;
	}
	/**
	 *  
	 * 
	 * @campo ORIGEN
	*/
	public String getOrigen() {
		return origen;
	}

	/**
	 *  
	 * 
	 * @campo ORIGEN
	*/
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 *  
	 * 
	 * @campo APELLIDOPATERNO
	*/
	public String getApellidopaterno() {
		return apellidopaterno;
	}

	/**
	 *  
	 * 
	 * @campo APELLIDOPATERNO
	*/
	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}
	/**
	 *  
	 * 
	 * @campo APELLIDOMATERNO
	*/
	public String getApellidomaterno() {
		return apellidomaterno;
	}

	/**
	 *  
	 * 
	 * @campo APELLIDOMATERNO
	*/
	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRES
	*/
	public String getNombres() {
		return nombres;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRES
	*/
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 *  
	 * 
	 * @campo NOMBRECOMPLETO
	*/
	public String getNombrecompleto() {
		return nombrecompleto;
	}

	/**
	 *  
	 * 
	 * @campo NOMBRECOMPLETO
	*/
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	/**
	 *  
	 * 
	 * @campo BUSQUEDA
	*/
	public String getBusqueda() {
		return busqueda;
	}

	/**
	 *  
	 * 
	 * @campo BUSQUEDA
	*/
	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	/**
	 *  
	 * 
	 * @campo TIPODOCUMENTO
	*/
	public String getTipodocumento() {
		return tipodocumento;
	}

	/**
	 *  
	 * 
	 * @campo TIPODOCUMENTO
	*/
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	/**
	 *  
	 * 
	 * @campo DOCUMENTO
	*/
	public String getDocumento() {
		return documento;
	}

	/**
	 *  
	 * 
	 * @campo DOCUMENTO
	*/
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	/**
	 *  
	 * 
	 * @campo CODIGOBARRAS
	*/
	public String getCodigobarras() {
		return codigobarras;
	}

	/**
	 *  
	 * 
	 * @campo CODIGOBARRAS
	*/
	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}
	/**
	 *  
	 * 
	 * @campo TIPOPERSONAUSUARIO
	*/
	public String getTipopersonausuario() {
		return tipopersonausuario;
	}

	/**
	 *  
	 * 
	 * @campo TIPOPERSONAUSUARIO
	*/
	public void setTipopersonausuario(String tipopersonausuario) {
		this.tipopersonausuario = tipopersonausuario;
	}
	/**
	 *  
	 * 
	 * @campo ESCLIENTE
	*/
	public String getEscliente() {
		return escliente;
	}

	/**
	 *  
	 * 
	 * @campo ESCLIENTE
	*/
	public void setEscliente(String escliente) {
		this.escliente = escliente;
	}
	/**
	 *  
	 * 
	 * @campo ESPROVEEDOR
	*/
	public String getEsproveedor() {
		return esproveedor;
	}

	/**
	 *  
	 * 
	 * @campo ESPROVEEDOR
	*/
	public void setEsproveedor(String esproveedor) {
		this.esproveedor = esproveedor;
	}
	/**
	 *  
	 * 
	 * @campo ESEMPLEADO
	*/
	public String getEsempleado() {
		return esempleado;
	}

	/**
	 *  
	 * 
	 * @campo ESEMPLEADO
	*/
	public void setEsempleado(String esempleado) {
		this.esempleado = esempleado;
	}
	/**
	 *  
	 * 
	 * @campo ESOTRO
	*/
	public String getEsotro() {
		return esotro;
	}

	/**
	 *  
	 * 
	 * @campo ESOTRO
	*/
	public void setEsotro(String esotro) {
		this.esotro = esotro;
	}
	/**
	 *  
	 * 
	 * @campo TIPOPERSONA
	*/
	public String getTipopersona() {
		return tipopersona;
	}

	/**
	 *  
	 * 
	 * @campo TIPOPERSONA
	*/
	public void setTipopersona(String tipopersona) {
		this.tipopersona = tipopersona;
	}
	/**
	 *  
	 * 
	 * @campo FECHANACIMIENTO
	*/
	public java.util.Date getFechanacimiento() {
		return fechanacimiento;
	}

	/**
	 *  
	 * 
	 * @campo FECHANACIMIENTO
	*/
	public void setFechanacimiento(java.util.Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	/**
	 *  
	 * 
	 * @campo CIUDADNACIMIENTO
	*/
	public String getCiudadnacimiento() {
		return ciudadnacimiento;
	}

	/**
	 *  
	 * 
	 * @campo CIUDADNACIMIENTO
	*/
	public void setCiudadnacimiento(String ciudadnacimiento) {
		this.ciudadnacimiento = ciudadnacimiento;
	}
	/**
	 *  
	 * 
	 * @campo SEXO
	*/
	public String getSexo() {
		return sexo;
	}

	/**
	 *  
	 * 
	 * @campo SEXO
	*/
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 *  
	 * 
	 * @campo NACIONALIDAD
	*/
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 *  
	 * 
	 * @campo NACIONALIDAD
	*/
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 *  
	 * 
	 * @campo ESTADOCIVIL
	*/
	public String getEstadocivil() {
		return estadocivil;
	}

	/**
	 *  
	 * 
	 * @campo ESTADOCIVIL
	*/
	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}
	/**
	 *  
	 * 
	 * @campo NIVELINSTRUCCION
	*/
	public String getNivelinstruccion() {
		return nivelinstruccion;
	}

	/**
	 *  
	 * 
	 * @campo NIVELINSTRUCCION
	*/
	public void setNivelinstruccion(String nivelinstruccion) {
		this.nivelinstruccion = nivelinstruccion;
	}
	/**
	 *  
	 * 
	 * @campo DIRECCION
	*/
	public String getDireccion() {
		return direccion;
	}

	/**
	 *  
	 * 
	 * @campo DIRECCION
	*/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 *  
	 * 
	 * @campo CODIGOPOSTAL
	*/
	public String getCodigopostal() {
		return codigopostal;
	}

	/**
	 *  
	 * 
	 * @campo CODIGOPOSTAL
	*/
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	/**
	 *  
	 * 
	 * @campo DEPARTAMENTO
	*/
	public String getDepartamento() {
		return departamento;
	}

	/**
	 *  
	 * 
	 * @campo DEPARTAMENTO
	*/
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**
	 *  
	 * 
	 * @campo TELEFONO
	*/
	public String getTelefono() {
		return telefono;
	}

	/**
	 *  
	 * 
	 * @campo TELEFONO
	*/
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 *  
	 * 
	 * @campo FAX
	*/
	public String getFax() {
		return fax;
	}

	/**
	 *  
	 * 
	 * @campo FAX
	*/
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 *  
	 * 
	 * @campo DOCUMENTOFISCAL
	*/
	public String getDocumentofiscal() {
		return documentofiscal;
	}

	/**
	 *  
	 * 
	 * @campo DOCUMENTOFISCAL
	*/
	public void setDocumentofiscal(String documentofiscal) {
		this.documentofiscal = documentofiscal;
	}
	/**
	 *  
	 * 
	 * @campo DOCUMENTOIDENTIDAD
	*/
	public String getDocumentoidentidad() {
		return documentoidentidad;
	}

	/**
	 *  
	 * 
	 * @campo DOCUMENTOIDENTIDAD
	*/
	public void setDocumentoidentidad(String documentoidentidad) {
		this.documentoidentidad = documentoidentidad;
	}
	/**
	 *  
	 * 
	 * @campo CARNETEXTRANJERIA
	*/
	public String getCarnetextranjeria() {
		return carnetextranjeria;
	}

	/**
	 *  
	 * 
	 * @campo CARNETEXTRANJERIA
	*/
	public void setCarnetextranjeria(String carnetextranjeria) {
		this.carnetextranjeria = carnetextranjeria;
	}
	/**
	 *  
	 * 
	 * @campo DOCUMENTOMILITARFA
	*/
	public String getDocumentomilitarfa() {
		return documentomilitarfa;
	}

	/**
	 *  
	 * 
	 * @campo DOCUMENTOMILITARFA
	*/
	public void setDocumentomilitarfa(String documentomilitarfa) {
		this.documentomilitarfa = documentomilitarfa;
	}
	/**
	 *  
	 * 
	 * @campo TIPOBREVETE
	*/
	public String getTipobrevete() {
		return tipobrevete;
	}

	/**
	 *  
	 * 
	 * @campo TIPOBREVETE
	*/
	public void setTipobrevete(String tipobrevete) {
		this.tipobrevete = tipobrevete;
	}
	/**
	 *  
	 * 
	 * @campo BREVETE
	*/
	public String getBrevete() {
		return brevete;
	}

	/**
	 *  
	 * 
	 * @campo BREVETE
	*/
	public void setBrevete(String brevete) {
		this.brevete = brevete;
	}
	/**
	 *  
	 * 
	 * @campo PASAPORTE
	*/
	public String getPasaporte() {
		return pasaporte;
	}

	/**
	 *  
	 * 
	 * @campo PASAPORTE
	*/
	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}
	/**
	 *  
	 * 
	 * @campo NOMBREEMERGENCIA
	*/
	public String getNombreemergencia() {
		return nombreemergencia;
	}

	/**
	 *  
	 * 
	 * @campo NOMBREEMERGENCIA
	*/
	public void setNombreemergencia(String nombreemergencia) {
		this.nombreemergencia = nombreemergencia;
	}
	/**
	 *  
	 * 
	 * @campo DIRECCIONEMERGENCIA
	*/
	public String getDireccionemergencia() {
		return direccionemergencia;
	}

	/**
	 *  
	 * 
	 * @campo DIRECCIONEMERGENCIA
	*/
	public void setDireccionemergencia(String direccionemergencia) {
		this.direccionemergencia = direccionemergencia;
	}
	/**
	 *  
	 * 
	 * @campo TELEFONOEMERGENCIA
	*/
	public String getTelefonoemergencia() {
		return telefonoemergencia;
	}

	/**
	 *  
	 * 
	 * @campo TELEFONOEMERGENCIA
	*/
	public void setTelefonoemergencia(String telefonoemergencia) {
		this.telefonoemergencia = telefonoemergencia;
	}
	/**
	 *  
	 * 
	 * @campo BANCOMONEDALOCAL
	*/
	public String getBancomonedalocal() {
		return bancomonedalocal;
	}

	/**
	 *  
	 * 
	 * @campo BANCOMONEDALOCAL
	*/
	public void setBancomonedalocal(String bancomonedalocal) {
		this.bancomonedalocal = bancomonedalocal;
	}
	/**
	 *  
	 * 
	 * @campo TIPOCUENTALOCAL
	*/
	public String getTipocuentalocal() {
		return tipocuentalocal;
	}

	/**
	 *  
	 * 
	 * @campo TIPOCUENTALOCAL
	*/
	public void setTipocuentalocal(String tipocuentalocal) {
		this.tipocuentalocal = tipocuentalocal;
	}
	/**
	 *  
	 * 
	 * @campo CUENTAMONEDALOCAL
	*/
	public String getCuentamonedalocal() {
		return cuentamonedalocal;
	}

	/**
	 *  
	 * 
	 * @campo CUENTAMONEDALOCAL
	*/
	public void setCuentamonedalocal(String cuentamonedalocal) {
		this.cuentamonedalocal = cuentamonedalocal;
	}
	/**
	 *  
	 * 
	 * @campo BANCOMONEDAEXTRANJERA
	*/
	public String getBancomonedaextranjera() {
		return bancomonedaextranjera;
	}

	/**
	 *  
	 * 
	 * @campo BANCOMONEDAEXTRANJERA
	*/
	public void setBancomonedaextranjera(String bancomonedaextranjera) {
		this.bancomonedaextranjera = bancomonedaextranjera;
	}
	/**
	 *  
	 * 
	 * @campo TIPOCUENTAEXTRANJERA
	*/
	public String getTipocuentaextranjera() {
		return tipocuentaextranjera;
	}

	/**
	 *  
	 * 
	 * @campo TIPOCUENTAEXTRANJERA
	*/
	public void setTipocuentaextranjera(String tipocuentaextranjera) {
		this.tipocuentaextranjera = tipocuentaextranjera;
	}
	/**
	 *  
	 * 
	 * @campo CUENTAMONEDAEXTRANJERA
	*/
	public String getCuentamonedaextranjera() {
		return cuentamonedaextranjera;
	}

	/**
	 *  
	 * 
	 * @campo CUENTAMONEDAEXTRANJERA
	*/
	public void setCuentamonedaextranjera(String cuentamonedaextranjera) {
		this.cuentamonedaextranjera = cuentamonedaextranjera;
	}
	/**
	 *  
	 * 
	 * @campo PERSONAANT
	*/
	public String getPersonaant() {
		return personaant;
	}

	/**
	 *  
	 * 
	 * @campo PERSONAANT
	*/
	public void setPersonaant(String personaant) {
		this.personaant = personaant;
	}
	/**
	 *  
	 * 
	 * @campo CORREOELECTRONICO
	*/
	public String getCorreoelectronico() {
		return correoelectronico;
	}

	/**
	 *  
	 * 
	 * @campo CORREOELECTRONICO
	*/
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	/**
	 *  
	 * 
	 * @campo CLASEPERSONACODIGO
	*/
	public String getClasepersonacodigo() {
		return clasepersonacodigo;
	}

	/**
	 *  
	 * 
	 * @campo CLASEPERSONACODIGO
	*/
	public void setClasepersonacodigo(String clasepersonacodigo) {
		this.clasepersonacodigo = clasepersonacodigo;
	}
	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public String getEstado() {
		return estado;
	}

	/**
	 *  
	 * 
	 * @campo ESTADO
	*/
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 *  
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public String getUltimousuario() {
		return ultimousuario;
	}

	/**
	 *  
	 * 
	 * @campo ULTIMOUSUARIO
	*/
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	/**
	 *  
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}

	/**
	 *  
	 * 
	 * @campo ULTIMAFECHAMODIF
	*/
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	
	/**
	 *  
	 * 
	 * @campo ENFERMEDADGRAVEFLAG
	*/
	public String getEnfermedadgraveflag() {
		return enfermedadgraveflag;
	}

	/**
	 *  
	 * 
	 * @campo ENFERMEDADGRAVEFLAG
	*/
	public void setEnfermedadgraveflag(String enfermedadgraveflag) {
		this.enfermedadgraveflag = enfermedadgraveflag;
	}
	/**
	 *  
	 * 
	 * @campo PROVINCIA
	*/
	public String getProvincia() {
		return provincia;
	}

	/**
	 *  
	 * 
	 * @campo PROVINCIA
	*/
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 *  
	 * 
	 * @campo TARJETADECREDITO
	*/
	public String getTarjetadecredito() {
		return tarjetadecredito;
	}

	/**
	 *  
	 * 
	 * @campo TARJETADECREDITO
	*/
	public void setTarjetadecredito(String tarjetadecredito) {
		this.tarjetadecredito = tarjetadecredito;
	}
	/**
	 *  
	 * 
	 * @campo FLAGACTUALIZACION
	*/
	public String getFlagactualizacion() {
		return flagactualizacion;
	}

	/**
	 *  
	 * 
	 * @campo FLAGACTUALIZACION
	*/
	public void setFlagactualizacion(String flagactualizacion) {
		this.flagactualizacion = flagactualizacion;
	}
	/**
	 *  
	 * 
	 * @campo INGRESOFECHAREGISTRO
	*/
	public java.util.Date getIngresofecharegistro() {
		return ingresofecharegistro;
	}

	/**
	 *  
	 * 
	 * @campo INGRESOFECHAREGISTRO
	*/
	public void setIngresofecharegistro(java.util.Date ingresofecharegistro) {
		this.ingresofecharegistro = ingresofecharegistro;
	}
	/**
	 *  
	 * 
	 * @campo INGRESOAPLICACIONCODIGO
	*/
	public String getIngresoaplicacioncodigo() {
		return ingresoaplicacioncodigo;
	}

	/**
	 *  
	 * 
	 * @campo INGRESOAPLICACIONCODIGO
	*/
	public void setIngresoaplicacioncodigo(String ingresoaplicacioncodigo) {
		this.ingresoaplicacioncodigo = ingresoaplicacioncodigo;
	}
	/**
	 *  
	 * 
	 * @campo INGRESOUSUARIO
	*/
	public String getIngresousuario() {
		return ingresousuario;
	}

	/**
	 *  
	 * 
	 * @campo INGRESOUSUARIO
	*/
	public void setIngresousuario(String ingresousuario) {
		this.ingresousuario = ingresousuario;
	}
	/**
	 *  
	 * 
	 * @campo PYMEFLAG
	*/
	public String getPymeflag() {
		return pymeflag;
	}

	/**
	 *  
	 * 
	 * @campo PYMEFLAG
	*/
	public void setPymeflag(String pymeflag) {
		this.pymeflag = pymeflag;
	}
	/**
	 *  
	 * 
	 * @campo GRUPOEMPRESARIAL
	*/
	public String getGrupoempresarial() {
		return grupoempresarial;
	}

	/**
	 *  
	 * 
	 * @campo GRUPOEMPRESARIAL
	*/
	public void setGrupoempresarial(String grupoempresarial) {
		this.grupoempresarial = grupoempresarial;
	}
	/**
	 *  
	 * 
	 * @campo CELULAR
	*/
	public String getCelular() {
		return celular;
	}

	/**
	 *  
	 * 
	 * @campo CELULAR
	*/
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 *  
	 * 
	 * @campo CELULAREMERGENCIA
	*/
	public String getCelularemergencia() {
		return celularemergencia;
	}

	/**
	 *  
	 * 
	 * @campo CELULAREMERGENCIA
	*/
	public void setCelularemergencia(String celularemergencia) {
		this.celularemergencia = celularemergencia;
	}
	/**
	 *  
	 * 
	 * @campo LUGARNACIMIENTO
	*/
	public String getLugarnacimiento() {
		return lugarnacimiento;
	}

	/**
	 *  
	 * 
	 * @campo LUGARNACIMIENTO
	*/
	public void setLugarnacimiento(String lugarnacimiento) {
		this.lugarnacimiento = lugarnacimiento;
	}
	/**
	 *  
	 * 
	 * @campo PARENTESCOEMERGENCIA
	*/
	public String getParentescoemergencia() {
		return parentescoemergencia;
	}

	/**
	 *  
	 * 
	 * @campo PARENTESCOEMERGENCIA
	*/
	public void setParentescoemergencia(String parentescoemergencia) {
		this.parentescoemergencia = parentescoemergencia;
	}
	/**
	 *  
	 * 
	 * @campo PERSONACLASIFICACION
	*/
	public String getPersonaclasificacion() {
		return personaclasificacion;
	}

	/**
	 *  
	 * 
	 * @campo PERSONACLASIFICACION
	*/
	public void setPersonaclasificacion(String personaclasificacion) {
		this.personaclasificacion = personaclasificacion;
	}
	/**
	 *  
	 * 
	 * @campo DIRECCIONREFERENCIA
	*/
	public String getDireccionreferencia() {
		return direccionreferencia;
	}

	/**
	 *  
	 * 
	 * @campo DIRECCIONREFERENCIA
	*/
	public void setDireccionreferencia(String direccionreferencia) {
		this.direccionreferencia = direccionreferencia;
	}	
	/**
	 *  
	 * 
	 * @campo CORRELATIVOSCTR
	*/
	//public String getCorrelativosctr() {
		//return correlativosctr;
	//}

	/**
	 *  
	 * 
	 * @campo CORRELATIVOSCTR
	*/
	//public void setCorrelativosctr(String correlativosctr) {
		//this.correlativosctr = correlativosctr;
	//}
	/**
	 *  
	 * 
	 * @campo SUNATZONA
	*/
	public String getSunatzona() {
		return sunatzona;
	}

	/**
	 *  
	 * 
	 * @campo SUNATZONA
	*/
	public void setSunatzona(String sunatzona) {
		this.sunatzona = sunatzona;
	}
	/**
	 *  
	 * 
	 * @campo SUNATUBIGEO
	*/
	public String getSunatubigeo() {
		return sunatubigeo;
	}

	/**
	 *  
	 * 
	 * @campo SUNATUBIGEO
	*/
	public void setSunatubigeo(String sunatubigeo) {
		this.sunatubigeo = sunatubigeo;
	}
	/**
	 *  
	 * 
	 * @campo SUNATNACIONALIDAD
	*/
	public String getSunatnacionalidad() {
		return sunatnacionalidad;
	}

	/**
	 *  
	 * 
	 * @campo SUNATNACIONALIDAD
	*/
	public void setSunatnacionalidad(String sunatnacionalidad) {
		this.sunatnacionalidad = sunatnacionalidad;
	}
	/**
	 *  
	 * 
	 * @campo SUNATVIA
	*/
	public String getSunatvia() {
		return sunatvia;
	}

	/**
	 *  
	 * 
	 * @campo SUNATVIA
	*/
	public void setSunatvia(String sunatvia) {
		this.sunatvia = sunatvia;
	}
	/**
	 *  
	 * 
	 * @campo SUNATDOMICILIADO
	*/
	public String getSunatdomiciliado() {
		return sunatdomiciliado;
	}

	/**
	 *  
	 * 
	 * @campo SUNATDOMICILIADO
	*/
	public void setSunatdomiciliado(String sunatdomiciliado) {
		this.sunatdomiciliado = sunatdomiciliado;
	}
	/**
	 *  
	 * 
	 * @campo BREVETE_FECVCTO
	*/
	public java.util.Date getBreveteFecvcto() {
		return breveteFecvcto;
	}

	/**
	 *  
	 * 
	 * @campo BREVETE_FECVCTO
	*/
	public void setBreveteFecvcto(java.util.Date breveteFecvcto) {
		this.breveteFecvcto = breveteFecvcto;
	}
	/**
	 *  
	 * 
	 * @campo CARNETEXTRANJERIA_FECVCTO
	*/
	//public java.util.Date getCarnetextranjeriaFecvcto() {
	//return carnetextranjeriaFecvcto;
	//}

	/**
	 *  
	 * 
	 * @campo CARNETEXTRANJERIA_FECVCTO
	*/
	//public void setCarnetextranjeriaFecvcto(java.util.Date carnetextranjeriaFecvcto) {
	//this.carnetextranjeriaFecvcto = carnetextranjeriaFecvcto;
	//}
	/**
	 *  
	 * 
	 * @campo PASAPORTE_FECVCTO
	*/
	//public String getPasaporteFecvcto() {
	//return pasaporteFecvcto;
	//}

	/**
	 *  
	 * 
	 * @campo PASAPORTE_FECVCTO
	*/
	//public void setPasaporteFecvcto(String pasaporteFecvcto) {
	//this.pasaporteFecvcto = pasaporteFecvcto;
	//}
	/**
	 *  
	 * 
	 * @campo PERSONANEW
	*/
	//public String getPersonanew() {
		//return personanew;
	//}

	/**
	 *  
	 * 
	 * @campo PERSONANEW
	*/
	//public void setPersonanew(String personanew) {
		//this.personanew = personanew;
	//}
	/**
	 *  
	 * 
	 * @campo PERSONA_OLD
	*/
	//public String getPersonaOld() {
		//return personaOld;
	//}

	/**
	 *  
	 * 
	 * @campo PERSONA_OLD
	*/
	//public void setPersonaOld(String personaOld) {
		//this.personaOld = personaOld;
	//}
	/**
	 *  
	 * 
	 * @campo DOCUMENTOIDENTIDAD_FECVCTO
	*/
	//public String getDocumentoidentidadFecvcto() {
		//return documentoidentidadFecvcto;
	//}

	/**
	 *  
	 * 
	 * @campo DOCUMENTOIDENTIDAD_FECVCTO
	*/
	//public void setDocumentoidentidadFecvcto(String documentoidentidadFecvcto) {
		//this.documentoidentidadFecvcto = documentoidentidadFecvcto;
	//}
	/**
	 *  
	 * 
	 * @campo TEMP_PERSONA
	*/
	//public Integer getTempPersona() {
		//return tempPersona;
	//}

	/**
	 *  
	 * 
	 * @campo TEMP_PERSONA
	*/
	//public void setTempPersona(Integer tempPersona) {
		//this.tempPersona = tempPersona;
	//}
	/**
	 *  
	 * 
	 * @campo CADENABUSQUEDA
	*/
	//public String getCadenabusqueda() {
	//return cadenabusqueda;
	//}

	/**
	 *  
	 * 
	 * @campo CADENABUSQUEDA
	*/
	//public void setCadenabusqueda(String cadenabusqueda) {
	//this.cadenabusqueda = cadenabusqueda;
	//}
	/**
	 *  
	 * 
	 * @campo CUENTACCILOCAL
	*/
	//public String getCuentaccilocal() {
	//return cuentaccilocal;
	//}

	/**
	 *  
	 * 
	 * @campo CUENTACCILOCAL
	*/
	//public void setCuentaccilocal(String cuentaccilocal) {
	//this.cuentaccilocal = cuentaccilocal;
	//}
	/**
	 *  
	 * 
	 * @campo CUENTACCIEXTRANJERA
	*/
	//public String getCuentacciextranjera() {
	//return cuentacciextranjera;
	//}

	/**
	 *  
	 * 
	 * @campo CUENTACCIEXTRANJERA
	*/
	//public void setCuentacciextranjera(String cuentacciextranjera) {
	//this.cuentacciextranjera = cuentacciextranjera;
	//}
	/**
	 *  
	 * 
	 * @campo PAISEMISOR
	*/
	public String getPaisemisor() {
		return paisemisor;
	}

	/**
	 *  
	 * 
	 * @campo PAISEMISOR
	*/
	public void setPaisemisor(String paisemisor) {
		this.paisemisor = paisemisor;
	}
	/**
	 *  
	 * 
	 * @campo CODIGOLDN
	*/
	//public String getCodigoldn() {
	//return codigoldn;
	//}

	/**
	 *  
	 * 
	 * @campo CODIGOLDN
	*/
	//public void setCodigoldn(String codigoldn) {
	//this.codigoldn = codigoldn;
	//}	
	/**
	 *  
	 * 
	 * @campo ESUSUARIO
	*/
	//public String getEsusuario() {
	//return esusuario;
	//}

	/**
	 *  
	 * 
	 * @campo ESUSUARIO
	*/
	//public void setEsusuario(String esusuario) {
	//this.esusuario = esusuario;
	//}
	/**
	 *  
	 * 
	 * @campo FLAGSOLICITAUSUARIOSIGA
	*/
	//public String getFlagsolicitausuariosiga() {
	//return flagsolicitausuariosiga;
	//}

	/**
	 *  
	 * 
	 * @campo FLAGSOLICITAUSUARIOSIGA
	*/
	//public void setFlagsolicitausuariosiga(String flagsolicitausuariosiga) {
	//this.flagsolicitausuariosiga = flagsolicitausuariosiga;
	//}
	/**
	 *  
	 * 
	 * @campo SECUENCIACUENTABANCARIA
	*/
	//public Integer getSecuenciacuentabancaria() {
		//return secuenciacuentabancaria;
	//}

	/**
	 *  
	 * 
	 * @campo SECUENCIACUENTABANCARIA
	*/
	//public void setSecuenciacuentabancaria(Integer secuenciacuentabancaria) {
		//this.secuenciacuentabancaria = secuenciacuentabancaria;
	//}
	/**
	 *  
	 * 
	 * @campo EXTENCIONFIRMA
	*/
	//public String getExtencionfirma() {
		//return extencionfirma;
	//}

	/**
	 *  
	 * 
	 * @campo EXTENCIONFIRMA
	*/
	//public void setExtencionfirma(String extencionfirma) {
		//this.extencionfirma = extencionfirma;
	//}
	/**
	 *  
	 * 
	 * @campo FIRMA
	*/
	//public byte[] getFirma() {
		//return firma;
	//}

	/**
	 *  
	 * 
	 * @campo FIRMA
	*/
	//public void setFirma(byte[] firma) {
		//this.firma = firma;
	//}
	/**
	 *  
	 * 
	 * @campo TIPOZONA
	*/
	//public String getTipozona() {
		//return tipozona;
	//}

	/**
	 *  
	 * 
	 * @campo TIPOZONA
	*/
	//public void setTipozona(String tipozona) {
		//this.tipozona = tipozona;
	//}
	/**
	 *  
	 * 
	 * @campo ZONA
	*/
	/*public String getZona() {
		return zona;
	}*/

	/**
	 *  
	 * 
	 * @campo ZONA
	*/
	/*
	public void setZona(String zona) {
		this.zona = zona;
	}
	*/
	/**
	 *  
	 * 
	 * @campo FECHAFALLECIMIENTO
	*/
	//public java.util.Date getFechafallecimiento() {
	//return fechafallecimiento;
	//}

	/**
	 *  
	 * 
	 * @campo FECHAFALLECIMIENTO
	*/
	//public void setFechafallecimiento(java.util.Date fechafallecimiento) {
	//this.fechafallecimiento = fechafallecimiento;
	//}
	/**
	 *  
	 * 
	 * @campo FECHASOLICITUDCU
	*/
	//public java.util.Date getFechasolicitudcu() {
	//return fechasolicitudcu;
	//}

	/**
	 *  
	 * 
	 * @campo FECHASOLICITUDCU
	*/
	//public void setFechasolicitudcu(java.util.Date fechasolicitudcu) {
	//this.fechasolicitudcu = fechasolicitudcu;
	//}
	/**
	 *  
	 * 
	 * @campo PERSONASOLICITUDCU
	*/
	//public Integer getPersonasolicitudcu() {
	//return personasolicitudcu;
	//}

	/**
	 *  
	 * 
	 * @campo PERSONASOLICITUDCU
	*/
	//public void setPersonasolicitudcu(Integer personasolicitudcu) {
	//this.personasolicitudcu = personasolicitudcu;
	//}
	/**
	 *  
	 * 
	 * @campo IDPERSONAUNIFICADO
	*/
	//public Integer getIdpersonaunificado() {
		//return idpersonaunificado;
	//}

	/**
	 *  
	 * 
	 * @campo IDPERSONAUNIFICADO
	*/
	//public void setIdpersonaunificado(Integer idpersonaunificado) {
		//this.idpersonaunificado = idpersonaunificado;
	//}
	/**
	 *  
	 * 
	 * @campo SUNATACTIVIDAD
	*/
	//public String getSunatactividad() {
		//return sunatactividad;
	//}

	/**
	 *  
	 * 
	 * @campo SUNATACTIVIDAD
	*/
	//public void setSunatactividad(String sunatactividad) {
		//this.sunatactividad = sunatactividad;
	//}	

	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getTipoproveedor() {
		return tipoproveedor;
	}


	public void setTipoproveedor(String tipoproveedor) {
		this.tipoproveedor = tipoproveedor;
	}


	public String getPassword1() {
		return password1;
	}


	public void setPassword1(String password1) {
		this.password1 = password1;
	}


	public String getPassword2() {
		return password2;
	}


	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	public String getRepresetanteNombre() {
		return represetanteNombre;
	}


	public void setRepresetanteNombre(String represetanteNombre) {
		this.represetanteNombre = represetanteNombre;
	}


	public String getRepresetanteTipoDocumento() {
		return represetanteTipoDocumento;
	}


	public void setRepresetanteTipoDocumento(String represetanteTipoDocumento) {
		this.represetanteTipoDocumento = represetanteTipoDocumento;
	}


	public String getRepresetanteDocumento() {
		return represetanteDocumento;
	}


	public void setRepresetanteDocumento(String represetanteDocumento) {
		this.represetanteDocumento = represetanteDocumento;
	}


	public String getRepresetanteDomicilioLegal() {
		return represetanteDomicilioLegal;
	}


	public void setRepresetanteDomicilioLegal(String represetanteDomicilioLegal) {
		this.represetanteDomicilioLegal = represetanteDomicilioLegal;
	}


	public String getRepresetanteContacto() {
		return represetanteContacto;
	}


	public void setRepresetanteContacto(String represetanteContacto) {
		this.represetanteContacto = represetanteContacto;
	}


	public String getRepresetanteTelefono() {
		return represetanteTelefono;
	}


	public void setRepresetanteTelefono(String represetanteTelefono) {
		this.represetanteTelefono = represetanteTelefono;
	}


	public String getRepresetanteCorreo() {
		return represetanteCorreo;
	}


	public void setRepresetanteCorreo(String represetanteCorreo) {
		this.represetanteCorreo = represetanteCorreo;
	}


	public String getRepresentanteDistrito() {
		return representanteDistrito;
	}


	public void setRepresentanteDistrito(String representanteDistrito) {
		this.representanteDistrito = representanteDistrito;
	}


	public String getRepresentanteProvincia() {
		return representanteProvincia;
	}


	public void setRepresentanteProvincia(String representanteProvincia) {
		this.representanteProvincia = representanteProvincia;
	}


	public String getRepresentanteDepartamento() {
		return representanteDepartamento;
	}


	public void setRepresentanteDepartamento(String representanteDepartamento) {
		this.representanteDepartamento = representanteDepartamento;
	}


	public String getRepresetanteNac() {
		return represetanteNac;
	}


	public void setRepresetanteNac(String represetanteNac) {
		this.represetanteNac = represetanteNac;
	}


	public String getRepresetanteOcupacion() {
		return represetanteOcupacion;
	}


	public void setRepresetanteOcupacion(String represetanteOcupacion) {
		this.represetanteOcupacion = represetanteOcupacion;
	}


	public List<BeanGpRpActividadEconomica> getListaActividadEconomica() {
		return listaActividadEconomica;
	}


	public void setListaActividadEconomica(List<BeanGpRpActividadEconomica> listaActividadEconomica) {
		this.listaActividadEconomica = listaActividadEconomica;
	}


	public String getEstadogestionproveedor() {
		return estadogestionproveedor;
	}


	public void setEstadogestionproveedor(String estadogestionproveedor) {
		this.estadogestionproveedor = estadogestionproveedor;
	}
	
}
