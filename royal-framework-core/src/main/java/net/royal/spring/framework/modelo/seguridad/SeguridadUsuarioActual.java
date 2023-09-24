
package net.royal.spring.framework.modelo.seguridad;

import net.royal.spring.framework.util.UString;

public class SeguridadUsuarioActual extends SeguridadUsuarioLogin {

	public SeguridadUsuarioActual() {
		super();
	}

	private String personaFotoUrl;

	/**
	 * identificador unico de la entidad principal tipo persona
	 */
	private Integer personaId;

	/**
	 * tipo de documento del usuario logeado
	 */
	private String personaTipoDocumentoId;

	/**
	 * nro del documento de identificacion del usuario actual
	 */
	private String personaNroDocumento;

	/**
	 * Nombre completo del usuario incluye nombres y apellidos
	 */
	private String personaNombreCompleto;

	/**
	 * nombre o nombres del usuario
	 */
	private String personaNombres;

	/**
	 * apellidos del usuario logeado paterno y materno
	 */
	private String personaApellidos;

	/**
	 * apellido Paterno del usuario logeado
	 */
	private String personaApellidoPaterno;

	/**
	 * apellido Materno del usuario logeado
	 */
	private String personaApellidoMaterno;

	/**
	 * empleadomast.unidadnegocioasignada = MA_UnidadNegocio.UnidadNegocio
	 * 
	 * @return
	 */
	private String unidadNegocioAsignadaCodigo;
	private String unidadNegocioAsignadaNombre;

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	private String sucursalCodigo;
	private String sucursalNombre;

	/**
	 * empleadomast.deptoorganizacion = DEPARTMENTMST.department
	 * 
	 * @return
	 */
	private String departamentoCodigo;
	private String departamentoNombre;

	/**
	 * empleadomast.codigocargo = HR_PUESTOEMPRESA.codigopuesto
	 * 
	 * @return
	 */
	private Integer puestoEmpresaCodigo;
	private String puestoEmpresaNombre;

	/**
	 * empleadomast.CentroCostos = AC_COSTCENTERMST.COSTCENTER
	 * 
	 * @return
	 */
	private String centroCostosCodigo;
	private String centroCostosNombre;

	private String unidadReplicacionCodigo;
	private String unidadReplicacionNombre;

	private String usuarioUniqueIdString;
	private Integer usuarioUniqueIdInteger;

	private Integer dptoareaoperativa;
	private String token;

	private String esAdministradorAplicacion = "N";

	private String esAdministradorWh = "N";

	private String esAdministradorSg = "N";

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	public String getSucursalCodigo() {
		return sucursalCodigo;
	}

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	public void setSucursalCodigo(String sucursalCodigo) {
		this.sucursalCodigo = sucursalCodigo;
	}

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	public String getSucursalNombre() {
		return sucursalNombre;
	}

	/**
	 * empleadomast.sucursal = AC_Sucursal.sucursal
	 * 
	 * @return
	 */
	public void setSucursalNombre(String sucursalNombre) {
		this.sucursalNombre = sucursalNombre;
	}

	/**
	 * identificador unico de la entidad principal tipo persona
	 */
	public Integer getPersonaId() {
		return personaId;
	}

	/**
	 * identificador unico de la entidad principal tipo persona
	 */

	/**
	 * apellidos del usuario logeado
	 */
	public String getPersonaApellidos() {
		String apes = "";

		if (personaApellidos != null) {
			personaApellidos.toLowerCase();
		}

		if (!(this.personaApellidoPaterno == null))
			apes = personaApellidoPaterno;

		if (!(this.personaApellidoMaterno == null))
			apes = apes + " " + personaApellidoMaterno;

		return apes;
	}

	/**
	 * apellidos del usuario logeado
	 */
	public void setPersonaApellidos(String personaApellidos) {
		this.personaApellidos = personaApellidos;
	}

	/**
	 * nombre o nombres del usuario
	 */
	public String getPersonaNombres() {
		return personaNombres;
	}

	/**
	 * nombre o nombres del usuario
	 */
	public void setPersonaNombres(String personaNombres) {
		this.personaNombres = personaNombres;
	}

	/**
	 * Nombre completo del usuario
	 */
	public String getPersonaNombreCompleto() {
		return personaNombreCompleto;
	}

	/**
	 * Nombre completo del usuario
	 */
	public void setPersonaNombreCompleto(String personaNombreCompleto) {
		this.personaNombreCompleto = personaNombreCompleto;
	}

	/**
	 * tipo de documento del usuario logeado
	 */
	public String getPersonaTipoDocumentoId() {
		return personaTipoDocumentoId;
	}

	/**
	 * tipo de documento del usuario logeado
	 */
	public void setTipoDocumentoId(String personaTipoDocumento) {
		this.personaTipoDocumentoId = personaTipoDocumentoId;
	}

	/**
	 * nro del documento de identificacion del usuario actual
	 */
	public String getPersonaNroDocumento() {
		return personaNroDocumento;
	}

	/**
	 * nro del documento de identificacion del usuario actual
	 */
	public void setPersonaNroDocumento(String personaNroDocumento) {
		this.personaNroDocumento = personaNroDocumento;
	}

	public String getPersonaApellidoPaterno() {
		return personaApellidoPaterno;
	}

	public void setPersonaApellidoPaterno(String personaApellidoPaterno) {
		this.personaApellidoPaterno = personaApellidoPaterno;
	}

	public String getPersonaApellidoMaterno() {
		return personaApellidoMaterno;
	}

	public void setPersonaApellidoMaterno(String personaApellidoMaterno) {
		this.personaApellidoMaterno = personaApellidoMaterno;
	}

	/**
	 * EmpleadoMast.CentroCostos = AC_CostCenterMst.CostCenter
	 * 
	 * @return
	 */
	public String getCentroCostosCodigo() {
		return centroCostosCodigo;
	}

	/**
	 * EmpleadoMast.CentroCostos = AC_CostCenterMst.CostCenter
	 * 
	 * @return
	 */
	public void setCentroCostosCodigo(String centroCostosCodigo) {
		this.centroCostosCodigo = centroCostosCodigo;
	}

	/**
	 * EmpleadoMast.CentroCostos = AC_CostCenterMst.CostCenter
	 * 
	 * @return
	 */
	public String getCentroCostosNombre() {
		return centroCostosNombre;
	}

	/**
	 * EmpleadoMast.CentroCostos = AC_CostCenterMst.CostCenter
	 * 
	 * @return
	 */
	public void setCentroCostosNombre(String centroCostosNombre) {
		this.centroCostosNombre = centroCostosNombre;
	}

	public String getDepartamentoCodigo() {
		return departamentoCodigo;
	}

	public void setDepartamentoCodigo(String departamentoCodigo) {
		this.departamentoCodigo = departamentoCodigo;
	}

	public String getDepartamentoNombre() {
		return departamentoNombre;
	}

	public void setDepartamentoNombre(String departamentoNombre) {
		this.departamentoNombre = departamentoNombre;
	}

	public String getUnidadNegocioAsignadaCodigo() {
		return unidadNegocioAsignadaCodigo;
	}

	public void setUnidadNegocioAsignadaCodigo(String unidadNegocioAsignadaCodigo) {
		this.unidadNegocioAsignadaCodigo = unidadNegocioAsignadaCodigo;
	}

	public String getUnidadNegocioAsignadaNombre() {
		return unidadNegocioAsignadaNombre;
	}

	public void setUnidadNegocioAsignadaNombre(String unidadNegocioAsignadaNombre) {
		this.unidadNegocioAsignadaNombre = unidadNegocioAsignadaNombre;
	}

	public Integer getPuestoEmpresaCodigo() {
		return puestoEmpresaCodigo;
	}

	public void setPuestoEmpresaCodigo(Integer puestoEmpresaCodigo) {
		this.puestoEmpresaCodigo = puestoEmpresaCodigo;
	}

	public String getPuestoEmpresaNombre() {
		return puestoEmpresaNombre;
	}

	public void setPuestoEmpresaNombre(String puestoEmpresaNombre) {
		this.puestoEmpresaNombre = puestoEmpresaNombre;
	}

	public String getUnidadReplicacionCodigo() {
		return unidadReplicacionCodigo;
	}

	public void setUnidadReplicacionCodigo(String unidadReplicacionCodigo) {
		this.unidadReplicacionCodigo = unidadReplicacionCodigo;
	}

	public String getUnidadReplicacionNombre() {
		return unidadReplicacionNombre;
	}

	public void setUnidadReplicacionNombre(String unidadReplicacionNombre) {
		this.unidadReplicacionNombre = unidadReplicacionNombre;
	}

	public Integer getDptoareaoperativa() {
		return dptoareaoperativa;
	}

	public void setDptoareaoperativa(Integer dptoareaoperativa) {
		this.dptoareaoperativa = dptoareaoperativa;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public void setPersonaTipoDocumentoId(String personaTipoDocumentoId) {
		this.personaTipoDocumentoId = personaTipoDocumentoId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsuarioUniqueIdString() {
		return usuarioUniqueIdString;
	}

	public void setUsuarioUniqueIdString(String usuarioUniqueIdString) {
		this.usuarioUniqueIdString = usuarioUniqueIdString;
	}

	public Integer getUsuarioUniqueIdInteger() {
		return usuarioUniqueIdInteger;
	}

	public void setUsuarioUniqueIdInteger(Integer usuarioUniqueIdInteger) {
		this.usuarioUniqueIdInteger = usuarioUniqueIdInteger;
	}

	public String getPersonaFotoUrl() {
		return personaFotoUrl;
	}

	public void setPersonaFotoUrl(String personaFotoUrl) {
		this.personaFotoUrl = personaFotoUrl;
	}

	public String getEsAdministradorWh() {
		esAdministradorWh = UString.obtenerValorCadenaSinNulo(esAdministradorWh);
		return esAdministradorWh;
	}

	public void setEsAdministradorWh(String esAdministradorWh) {
		this.esAdministradorWh = esAdministradorWh;
	}

	public String getEsAdministradorAplicacion() {
		return esAdministradorAplicacion;
	}

	public void setEsAdministradorAplicacion(String esAdministradorAplicacion) {
		this.esAdministradorAplicacion = esAdministradorAplicacion;
	}

	public String getEsAdministradorSg() {
		return esAdministradorSg;
	}

	public void setEsAdministradorSg(String esAdministradorSg) {
		esAdministradorSg = UString.obtenerValorCadenaSinNulo(esAdministradorSg);
		this.esAdministradorSg = esAdministradorSg;
	}

	public void setUsuarioSeguro() {
		this.setUsuario("SEGURO");
	}
}
