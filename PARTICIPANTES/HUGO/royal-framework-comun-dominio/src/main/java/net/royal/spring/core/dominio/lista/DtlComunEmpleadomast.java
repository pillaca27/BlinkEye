package net.royal.spring.core.dominio.lista;

import net.royal.spring.core.dominio.BeanEmpleadomast;

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
 * @tabla dbo.EmpleadoMast
*/
public class DtlComunEmpleadomast extends DominioTransaccion implements java.io.Serializable{


	private Integer empleado;
	private String companiasocio;
	private String tipopago;
	private String tipotrabajador;
	private String tipoplanilla;
	private String raza;
	private String religion;
	private String tipovisa;
	private java.util.Date visafechainicio;
	private java.util.Date visafechaexpiracion;
	private java.util.Date serviciomilitardesde;
	private java.util.Date serviciomilitarhasta;
	private String numeroarchivo;
	private String unidadnegocioasignada;
	private String locaciondepago;
	private String codigousuario;
	private String tipoasistenciasocial;
	private String centroasistenciasocial;
	private String tipocarnetasistenciasocial;
	private String carnetasistenciasocial;
	private String tipopension;
	private java.util.Date fechainiciopension;
	private java.util.Date fechaterminopension;
	private String codigoafp;
	private String numeroafp;
	private String bancocts;
	private String tipocuentacts;
	private String tipomonedacts;
	private String numerocuentacts;
	private String estadoempleado;
	private java.util.Date fechaingreso;
	private java.util.Date fechaultimaplanilla;
	private String tipocontrato;
	private java.util.Date fechainiciocontrato;
	private java.util.Date fechafincontrato;
	private java.util.Date fechacese;
	private String razoncese;
	private Integer motivocese;
	private Integer contratista;
	private String centrocostos;
	private String afe;
	private String deptoorganizacion;
	private String departamentooperacional;
	private Integer tipohorario;
	private String gradosalario;
	private String cargo;
	private String nivelacceso;
	private String flagipssvida;
	private String flagacctrabajo;
	private String correointerno;
	private java.math.BigDecimal sueldoactuallocal;
	private java.math.BigDecimal sueldoactualdolar;
	private java.math.BigDecimal sueldoanteriorlocal;
	private java.math.BigDecimal sueldoanteriordolar;
	private String monedapago;
	private Integer perfil;
	private String foto;
	private java.util.Date fechaliquidacion;
	private java.util.Date fechareingreso;
	private String unidadreplicacion;
	private Integer codigocargo;
	private java.util.Date ultimafechapagovacacion;
	private String estado;
	private String sucursal;
	private String ruccentroasistenciasocial;
	private String tarjetadecredito;
	private Integer plantillaconcepto;
	private String flagsmf;
	private java.util.Date fechavacaciones;
	private String flagtrabajadorpensionista;
	private String flagsctrsalud;
	private String flagsctrpension;
	private String flagdiscapacidad;
	private String flagsujetocontrol;
	private String flagsindicalizado;
	private String flagdomiciliado;
	private String niveleducativortps;
	private String flagregimenalternativo;
	private String flagjornadamaxima;
	private String flaghorarionocturno;
	private String flagotrosquinta;
	private String flagquintaexonerada;
	private String situacionespecial;
	private String flagmadreresponsabilidad;
	private String flagcentroformacion;
	private String tipomodalidadformativa;
	private String prestadorservicio;
	private String flagasegurapension;
	private String categoriaocupacional;
	private String flagconveniodobletrib;
	private String firmadigitalizada;
	private String flagdeconfianza;
	private java.util.Date fechabajaeps;
	private Integer codigounidad;
	private String division;
	private String oficina;
	private String responsableempleado;
	private String responsablejefe;
	private String tipocomisionafp;
	private String locacionasignada;
	private String ultimousuario;
	private java.util.Date ultimafechamodif;
	private String motivo;
	private String costcenterdestination;
	private String unidadtrabajo;
	private Integer jeferesponsable;
	private String ordenOrganigrama;
	private String unidadoperativa;
	private Integer empleadorelacionado;
	private String posicion;
	private java.math.BigDecimal grupoocupacional;
	private String estadonivelacion;
	private Integer aprobadornivelacion;
	private Integer solicitadornivelacion;
	private String flageducacioncompletaiep;

	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public Integer getEmpleado() {
		return empleado;
	}

	/**
	 * 
	 * 
	 * @campo Empleado
	*/
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public String getCompaniasocio() {
		return companiasocio;
	}

	/**
	 * 
	 * 
	 * @campo CompaniaSocio
	*/
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public String getTipopago() {
		return tipopago;
	}

	/**
	 * 
	 * 
	 * @campo TipoPago
	*/
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	/**
	 * 
	 * 
	 * @campo TipoTrabajador
	*/
	public String getTipotrabajador() {
		return tipotrabajador;
	}

	/**
	 * 
	 * 
	 * @campo TipoTrabajador
	*/
	public void setTipotrabajador(String tipotrabajador) {
		this.tipotrabajador = tipotrabajador;
	}
	/**
	 * 
	 * 
	 * @campo TipoPlanilla
	*/
	public String getTipoplanilla() {
		return tipoplanilla;
	}

	/**
	 * 
	 * 
	 * @campo TipoPlanilla
	*/
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	/**
	 * 
	 * 
	 * @campo Raza
	*/
	public String getRaza() {
		return raza;
	}

	/**
	 * 
	 * 
	 * @campo Raza
	*/
	public void setRaza(String raza) {
		this.raza = raza;
	}
	/**
	 * 
	 * 
	 * @campo Religion
	*/
	public String getReligion() {
		return religion;
	}

	/**
	 * 
	 * 
	 * @campo Religion
	*/
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * 
	 * 
	 * @campo TipoVisa
	*/
	public String getTipovisa() {
		return tipovisa;
	}

	/**
	 * 
	 * 
	 * @campo TipoVisa
	*/
	public void setTipovisa(String tipovisa) {
		this.tipovisa = tipovisa;
	}
	/**
	 * 
	 * 
	 * @campo VisaFechaInicio
	*/
	public java.util.Date getVisafechainicio() {
		return visafechainicio;
	}

	/**
	 * 
	 * 
	 * @campo VisaFechaInicio
	*/
	public void setVisafechainicio(java.util.Date visafechainicio) {
		this.visafechainicio = visafechainicio;
	}
	/**
	 * 
	 * 
	 * @campo VisaFechaExpiracion
	*/
	public java.util.Date getVisafechaexpiracion() {
		return visafechaexpiracion;
	}

	/**
	 * 
	 * 
	 * @campo VisaFechaExpiracion
	*/
	public void setVisafechaexpiracion(java.util.Date visafechaexpiracion) {
		this.visafechaexpiracion = visafechaexpiracion;
	}
	/**
	 * 
	 * 
	 * @campo ServicioMilitarDesde
	*/
	public java.util.Date getServiciomilitardesde() {
		return serviciomilitardesde;
	}

	/**
	 * 
	 * 
	 * @campo ServicioMilitarDesde
	*/
	public void setServiciomilitardesde(java.util.Date serviciomilitardesde) {
		this.serviciomilitardesde = serviciomilitardesde;
	}
	/**
	 * 
	 * 
	 * @campo ServicioMilitarHasta
	*/
	public java.util.Date getServiciomilitarhasta() {
		return serviciomilitarhasta;
	}

	/**
	 * 
	 * 
	 * @campo ServicioMilitarHasta
	*/
	public void setServiciomilitarhasta(java.util.Date serviciomilitarhasta) {
		this.serviciomilitarhasta = serviciomilitarhasta;
	}
	/**
	 * 
	 * 
	 * @campo NumeroArchivo
	*/
	public String getNumeroarchivo() {
		return numeroarchivo;
	}

	/**
	 * 
	 * 
	 * @campo NumeroArchivo
	*/
	public void setNumeroarchivo(String numeroarchivo) {
		this.numeroarchivo = numeroarchivo;
	}
	/**
	 * 
	 * 
	 * @campo UnidadNegocioAsignada
	*/
	public String getUnidadnegocioasignada() {
		return unidadnegocioasignada;
	}

	/**
	 * 
	 * 
	 * @campo UnidadNegocioAsignada
	*/
	public void setUnidadnegocioasignada(String unidadnegocioasignada) {
		this.unidadnegocioasignada = unidadnegocioasignada;
	}
	/**
	 * 
	 * 
	 * @campo LocaciondePago
	*/
	public String getLocaciondepago() {
		return locaciondepago;
	}

	/**
	 * 
	 * 
	 * @campo LocaciondePago
	*/
	public void setLocaciondepago(String locaciondepago) {
		this.locaciondepago = locaciondepago;
	}
	/**
	 * 
	 * 
	 * @campo CodigoUsuario
	*/
	public String getCodigousuario() {
		return codigousuario;
	}

	/**
	 * 
	 * 
	 * @campo CodigoUsuario
	*/
	public void setCodigousuario(String codigousuario) {
		this.codigousuario = codigousuario;
	}
	/**
	 * 
	 * 
	 * @campo TipoAsistenciaSocial
	*/
	public String getTipoasistenciasocial() {
		return tipoasistenciasocial;
	}

	/**
	 * 
	 * 
	 * @campo TipoAsistenciaSocial
	*/
	public void setTipoasistenciasocial(String tipoasistenciasocial) {
		this.tipoasistenciasocial = tipoasistenciasocial;
	}
	/**
	 * 
	 * 
	 * @campo CentroAsistenciaSocial
	*/
	public String getCentroasistenciasocial() {
		return centroasistenciasocial;
	}

	/**
	 * 
	 * 
	 * @campo CentroAsistenciaSocial
	*/
	public void setCentroasistenciasocial(String centroasistenciasocial) {
		this.centroasistenciasocial = centroasistenciasocial;
	}
	/**
	 * 
	 * 
	 * @campo TipoCarnetAsistenciaSocial
	*/
	public String getTipocarnetasistenciasocial() {
		return tipocarnetasistenciasocial;
	}

	/**
	 * 
	 * 
	 * @campo TipoCarnetAsistenciaSocial
	*/
	public void setTipocarnetasistenciasocial(String tipocarnetasistenciasocial) {
		this.tipocarnetasistenciasocial = tipocarnetasistenciasocial;
	}
	/**
	 * 
	 * 
	 * @campo CarnetAsistenciaSocial
	*/
	public String getCarnetasistenciasocial() {
		return carnetasistenciasocial;
	}

	/**
	 * 
	 * 
	 * @campo CarnetAsistenciaSocial
	*/
	public void setCarnetasistenciasocial(String carnetasistenciasocial) {
		this.carnetasistenciasocial = carnetasistenciasocial;
	}
	/**
	 * 
	 * 
	 * @campo TipoPension
	*/
	public String getTipopension() {
		return tipopension;
	}

	/**
	 * 
	 * 
	 * @campo TipoPension
	*/
	public void setTipopension(String tipopension) {
		this.tipopension = tipopension;
	}
	/**
	 * 
	 * 
	 * @campo FechaInicioPension
	*/
	public java.util.Date getFechainiciopension() {
		return fechainiciopension;
	}

	/**
	 * 
	 * 
	 * @campo FechaInicioPension
	*/
	public void setFechainiciopension(java.util.Date fechainiciopension) {
		this.fechainiciopension = fechainiciopension;
	}
	/**
	 * 
	 * 
	 * @campo FechaTerminoPension
	*/
	public java.util.Date getFechaterminopension() {
		return fechaterminopension;
	}

	/**
	 * 
	 * 
	 * @campo FechaTerminoPension
	*/
	public void setFechaterminopension(java.util.Date fechaterminopension) {
		this.fechaterminopension = fechaterminopension;
	}
	/**
	 * 
	 * 
	 * @campo CodigoAFP
	*/
	public String getCodigoafp() {
		return codigoafp;
	}

	/**
	 * 
	 * 
	 * @campo CodigoAFP
	*/
	public void setCodigoafp(String codigoafp) {
		this.codigoafp = codigoafp;
	}
	/**
	 * 
	 * 
	 * @campo NumeroAFP
	*/
	public String getNumeroafp() {
		return numeroafp;
	}

	/**
	 * 
	 * 
	 * @campo NumeroAFP
	*/
	public void setNumeroafp(String numeroafp) {
		this.numeroafp = numeroafp;
	}
	/**
	 * 
	 * 
	 * @campo BancoCTS
	*/
	public String getBancocts() {
		return bancocts;
	}

	/**
	 * 
	 * 
	 * @campo BancoCTS
	*/
	public void setBancocts(String bancocts) {
		this.bancocts = bancocts;
	}
	/**
	 * 
	 * 
	 * @campo TipoCuentaCTS
	*/
	public String getTipocuentacts() {
		return tipocuentacts;
	}

	/**
	 * 
	 * 
	 * @campo TipoCuentaCTS
	*/
	public void setTipocuentacts(String tipocuentacts) {
		this.tipocuentacts = tipocuentacts;
	}
	/**
	 * 
	 * 
	 * @campo TipoMonedaCTS
	*/
	public String getTipomonedacts() {
		return tipomonedacts;
	}

	/**
	 * 
	 * 
	 * @campo TipoMonedaCTS
	*/
	public void setTipomonedacts(String tipomonedacts) {
		this.tipomonedacts = tipomonedacts;
	}
	/**
	 * 
	 * 
	 * @campo NumeroCuentaCTS
	*/
	public String getNumerocuentacts() {
		return numerocuentacts;
	}

	/**
	 * 
	 * 
	 * @campo NumeroCuentaCTS
	*/
	public void setNumerocuentacts(String numerocuentacts) {
		this.numerocuentacts = numerocuentacts;
	}
	/**
	 * 
	 * 
	 * @campo EstadoEmpleado
	*/
	public String getEstadoempleado() {
		return estadoempleado;
	}

	/**
	 * 
	 * 
	 * @campo EstadoEmpleado
	*/
	public void setEstadoempleado(String estadoempleado) {
		this.estadoempleado = estadoempleado;
	}
	/**
	 * 
	 * 
	 * @campo FechaIngreso
	*/
	public java.util.Date getFechaingreso() {
		return fechaingreso;
	}

	/**
	 * 
	 * 
	 * @campo FechaIngreso
	*/
	public void setFechaingreso(java.util.Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	/**
	 * 
	 * 
	 * @campo FechaUltimaPlanilla
	*/
	public java.util.Date getFechaultimaplanilla() {
		return fechaultimaplanilla;
	}

	/**
	 * 
	 * 
	 * @campo FechaUltimaPlanilla
	*/
	public void setFechaultimaplanilla(java.util.Date fechaultimaplanilla) {
		this.fechaultimaplanilla = fechaultimaplanilla;
	}
	/**
	 * 
	 * 
	 * @campo TipoContrato
	*/
	public String getTipocontrato() {
		return tipocontrato;
	}

	/**
	 * 
	 * 
	 * @campo TipoContrato
	*/
	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}
	/**
	 * 
	 * 
	 * @campo FechaInicioContrato
	*/
	public java.util.Date getFechainiciocontrato() {
		return fechainiciocontrato;
	}

	/**
	 * 
	 * 
	 * @campo FechaInicioContrato
	*/
	public void setFechainiciocontrato(java.util.Date fechainiciocontrato) {
		this.fechainiciocontrato = fechainiciocontrato;
	}
	/**
	 * 
	 * 
	 * @campo FechaFinContrato
	*/
	public java.util.Date getFechafincontrato() {
		return fechafincontrato;
	}

	/**
	 * 
	 * 
	 * @campo FechaFinContrato
	*/
	public void setFechafincontrato(java.util.Date fechafincontrato) {
		this.fechafincontrato = fechafincontrato;
	}
	/**
	 * 
	 * 
	 * @campo FechaCese
	*/
	public java.util.Date getFechacese() {
		return fechacese;
	}

	/**
	 * 
	 * 
	 * @campo FechaCese
	*/
	public void setFechacese(java.util.Date fechacese) {
		this.fechacese = fechacese;
	}
	/**
	 * 
	 * 
	 * @campo RazonCese
	*/
	public String getRazoncese() {
		return razoncese;
	}

	/**
	 * 
	 * 
	 * @campo RazonCese
	*/
	public void setRazoncese(String razoncese) {
		this.razoncese = razoncese;
	}
	/**
	 * 
	 * 
	 * @campo MotivoCese
	*/
	public Integer getMotivocese() {
		return motivocese;
	}

	/**
	 * 
	 * 
	 * @campo MotivoCese
	*/
	public void setMotivocese(Integer motivocese) {
		this.motivocese = motivocese;
	}
	/**
	 * 
	 * 
	 * @campo Contratista
	*/
	public Integer getContratista() {
		return contratista;
	}

	/**
	 * 
	 * 
	 * @campo Contratista
	*/
	public void setContratista(Integer contratista) {
		this.contratista = contratista;
	}
	/**
	 * 
	 * 
	 * @campo CentroCostos
	*/
	public String getCentrocostos() {
		return centrocostos;
	}

	/**
	 * 
	 * 
	 * @campo CentroCostos
	*/
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}
	/**
	 * 
	 * 
	 * @campo AFE
	*/
	public String getAfe() {
		return afe;
	}

	/**
	 * 
	 * 
	 * @campo AFE
	*/
	public void setAfe(String afe) {
		this.afe = afe;
	}
	/**
	 * 
	 * 
	 * @campo DeptoOrganizacion
	*/
	public String getDeptoorganizacion() {
		return deptoorganizacion;
	}

	/**
	 * 
	 * 
	 * @campo DeptoOrganizacion
	*/
	public void setDeptoorganizacion(String deptoorganizacion) {
		this.deptoorganizacion = deptoorganizacion;
	}
	/**
	 * 
	 * 
	 * @campo DepartamentoOperacional
	*/
	public String getDepartamentooperacional() {
		return departamentooperacional;
	}

	/**
	 * 
	 * 
	 * @campo DepartamentoOperacional
	*/
	public void setDepartamentooperacional(String departamentooperacional) {
		this.departamentooperacional = departamentooperacional;
	}
	/**
	 * 
	 * 
	 * @campo TipoHorario
	*/
	public Integer getTipohorario() {
		return tipohorario;
	}

	/**
	 * 
	 * 
	 * @campo TipoHorario
	*/
	public void setTipohorario(Integer tipohorario) {
		this.tipohorario = tipohorario;
	}
	/**
	 * 
	 * 
	 * @campo GradoSalario
	*/
	public String getGradosalario() {
		return gradosalario;
	}

	/**
	 * 
	 * 
	 * @campo GradoSalario
	*/
	public void setGradosalario(String gradosalario) {
		this.gradosalario = gradosalario;
	}
	/**
	 * 
	 * 
	 * @campo Cargo
	*/
	public String getCargo() {
		return cargo;
	}

	/**
	 * 
	 * 
	 * @campo Cargo
	*/
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	/**
	 * 
	 * 
	 * @campo NivelAcceso
	*/
	public String getNivelacceso() {
		return nivelacceso;
	}

	/**
	 * 
	 * 
	 * @campo NivelAcceso
	*/
	public void setNivelacceso(String nivelacceso) {
		this.nivelacceso = nivelacceso;
	}
	/**
	 * 
	 * 
	 * @campo FlagIPSSVIDA
	*/
	public String getFlagipssvida() {
		return flagipssvida;
	}

	/**
	 * 
	 * 
	 * @campo FlagIPSSVIDA
	*/
	public void setFlagipssvida(String flagipssvida) {
		this.flagipssvida = flagipssvida;
	}
	/**
	 * 
	 * 
	 * @campo FlagAccTrabajo
	*/
	public String getFlagacctrabajo() {
		return flagacctrabajo;
	}

	/**
	 * 
	 * 
	 * @campo FlagAccTrabajo
	*/
	public void setFlagacctrabajo(String flagacctrabajo) {
		this.flagacctrabajo = flagacctrabajo;
	}
	/**
	 * 
	 * 
	 * @campo CorreoInterno
	*/
	public String getCorreointerno() {
		return correointerno;
	}

	/**
	 * 
	 * 
	 * @campo CorreoInterno
	*/
	public void setCorreointerno(String correointerno) {
		this.correointerno = correointerno;
	}
	/**
	 * 
	 * 
	 * @campo SueldoActualLocal
	*/
	public java.math.BigDecimal getSueldoactuallocal() {
		return sueldoactuallocal;
	}

	/**
	 * 
	 * 
	 * @campo SueldoActualLocal
	*/
	public void setSueldoactuallocal(java.math.BigDecimal sueldoactuallocal) {
		this.sueldoactuallocal = sueldoactuallocal;
	}
	/**
	 * 
	 * 
	 * @campo SueldoActualDolar
	*/
	public java.math.BigDecimal getSueldoactualdolar() {
		return sueldoactualdolar;
	}

	/**
	 * 
	 * 
	 * @campo SueldoActualDolar
	*/
	public void setSueldoactualdolar(java.math.BigDecimal sueldoactualdolar) {
		this.sueldoactualdolar = sueldoactualdolar;
	}
	/**
	 * 
	 * 
	 * @campo SueldoAnteriorLocal
	*/
	public java.math.BigDecimal getSueldoanteriorlocal() {
		return sueldoanteriorlocal;
	}

	/**
	 * 
	 * 
	 * @campo SueldoAnteriorLocal
	*/
	public void setSueldoanteriorlocal(java.math.BigDecimal sueldoanteriorlocal) {
		this.sueldoanteriorlocal = sueldoanteriorlocal;
	}
	/**
	 * 
	 * 
	 * @campo SueldoAnteriorDolar
	*/
	public java.math.BigDecimal getSueldoanteriordolar() {
		return sueldoanteriordolar;
	}

	/**
	 * 
	 * 
	 * @campo SueldoAnteriorDolar
	*/
	public void setSueldoanteriordolar(java.math.BigDecimal sueldoanteriordolar) {
		this.sueldoanteriordolar = sueldoanteriordolar;
	}
	/**
	 * 
	 * 
	 * @campo MonedaPago
	*/
	public String getMonedapago() {
		return monedapago;
	}

	/**
	 * 
	 * 
	 * @campo MonedaPago
	*/
	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}
	/**
	 * 
	 * 
	 * @campo Perfil
	*/
	public Integer getPerfil() {
		return perfil;
	}

	/**
	 * 
	 * 
	 * @campo Perfil
	*/
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	/**
	 * 
	 * 
	 * @campo Foto
	*/
	public String getFoto() {
		return foto;
	}

	/**
	 * 
	 * 
	 * @campo Foto
	*/
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/**
	 * 
	 * 
	 * @campo FechaLiquidacion
	*/
	public java.util.Date getFechaliquidacion() {
		return fechaliquidacion;
	}

	/**
	 * 
	 * 
	 * @campo FechaLiquidacion
	*/
	public void setFechaliquidacion(java.util.Date fechaliquidacion) {
		this.fechaliquidacion = fechaliquidacion;
	}
	/**
	 * 
	 * 
	 * @campo FechaReingreso
	*/
	public java.util.Date getFechareingreso() {
		return fechareingreso;
	}

	/**
	 * 
	 * 
	 * @campo FechaReingreso
	*/
	public void setFechareingreso(java.util.Date fechareingreso) {
		this.fechareingreso = fechareingreso;
	}
	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}

	/**
	 * 
	 * 
	 * @campo UnidadReplicacion
	*/
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	/**
	 * 
	 * 
	 * @campo CodigoCargo
	*/
	public Integer getCodigocargo() {
		return codigocargo;
	}

	/**
	 * 
	 * 
	 * @campo CodigoCargo
	*/
	public void setCodigocargo(Integer codigocargo) {
		this.codigocargo = codigocargo;
	}
	/**
	 * 
	 * 
	 * @campo UltimaFechaPagoVacacion
	*/
	public java.util.Date getUltimafechapagovacacion() {
		return ultimafechapagovacacion;
	}

	/**
	 * 
	 * 
	 * @campo UltimaFechaPagoVacacion
	*/
	public void setUltimafechapagovacacion(java.util.Date ultimafechapagovacacion) {
		this.ultimafechapagovacacion = ultimafechapagovacacion;
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
	 * @campo Sucursal
	*/
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * 
	 * 
	 * @campo Sucursal
	*/
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * 
	 * 
	 * @campo RUCCentroAsistenciaSocial
	*/
	public String getRuccentroasistenciasocial() {
		return ruccentroasistenciasocial;
	}

	/**
	 * 
	 * 
	 * @campo RUCCentroAsistenciaSocial
	*/
	public void setRuccentroasistenciasocial(String ruccentroasistenciasocial) {
		this.ruccentroasistenciasocial = ruccentroasistenciasocial;
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
	 * @campo PlantillaConcepto
	*/
	public Integer getPlantillaconcepto() {
		return plantillaconcepto;
	}

	/**
	 * 
	 * 
	 * @campo PlantillaConcepto
	*/
	public void setPlantillaconcepto(Integer plantillaconcepto) {
		this.plantillaconcepto = plantillaconcepto;
	}
	/**
	 * 
	 * 
	 * @campo FlagSMF
	*/
	public String getFlagsmf() {
		return flagsmf;
	}

	/**
	 * 
	 * 
	 * @campo FlagSMF
	*/
	public void setFlagsmf(String flagsmf) {
		this.flagsmf = flagsmf;
	}
	/**
	 * 
	 * 
	 * @campo FechaVacaciones
	*/
	public java.util.Date getFechavacaciones() {
		return fechavacaciones;
	}

	/**
	 * 
	 * 
	 * @campo FechaVacaciones
	*/
	public void setFechavacaciones(java.util.Date fechavacaciones) {
		this.fechavacaciones = fechavacaciones;
	}
	/**
	 * 
	 * 
	 * @campo FlagTrabajadorPensionista
	*/
	public String getFlagtrabajadorpensionista() {
		return flagtrabajadorpensionista;
	}

	/**
	 * 
	 * 
	 * @campo FlagTrabajadorPensionista
	*/
	public void setFlagtrabajadorpensionista(String flagtrabajadorpensionista) {
		this.flagtrabajadorpensionista = flagtrabajadorpensionista;
	}
	/**
	 * 
	 * 
	 * @campo FlagSCTRSalud
	*/
	public String getFlagsctrsalud() {
		return flagsctrsalud;
	}

	/**
	 * 
	 * 
	 * @campo FlagSCTRSalud
	*/
	public void setFlagsctrsalud(String flagsctrsalud) {
		this.flagsctrsalud = flagsctrsalud;
	}
	/**
	 * 
	 * 
	 * @campo FlagSCTRPension
	*/
	public String getFlagsctrpension() {
		return flagsctrpension;
	}

	/**
	 * 
	 * 
	 * @campo FlagSCTRPension
	*/
	public void setFlagsctrpension(String flagsctrpension) {
		this.flagsctrpension = flagsctrpension;
	}
	/**
	 * 
	 * 
	 * @campo FlagDiscapacidad
	*/
	public String getFlagdiscapacidad() {
		return flagdiscapacidad;
	}

	/**
	 * 
	 * 
	 * @campo FlagDiscapacidad
	*/
	public void setFlagdiscapacidad(String flagdiscapacidad) {
		this.flagdiscapacidad = flagdiscapacidad;
	}
	/**
	 * 
	 * 
	 * @campo FlagSujetoControl
	*/
	public String getFlagsujetocontrol() {
		return flagsujetocontrol;
	}

	/**
	 * 
	 * 
	 * @campo FlagSujetoControl
	*/
	public void setFlagsujetocontrol(String flagsujetocontrol) {
		this.flagsujetocontrol = flagsujetocontrol;
	}
	/**
	 * 
	 * 
	 * @campo FlagSindicalizado
	*/
	public String getFlagsindicalizado() {
		return flagsindicalizado;
	}

	/**
	 * 
	 * 
	 * @campo FlagSindicalizado
	*/
	public void setFlagsindicalizado(String flagsindicalizado) {
		this.flagsindicalizado = flagsindicalizado;
	}
	/**
	 * 
	 * 
	 * @campo FlagDomiciliado
	*/
	public String getFlagdomiciliado() {
		return flagdomiciliado;
	}

	/**
	 * 
	 * 
	 * @campo FlagDomiciliado
	*/
	public void setFlagdomiciliado(String flagdomiciliado) {
		this.flagdomiciliado = flagdomiciliado;
	}
	/**
	 * 
	 * 
	 * @campo NivelEducativoRTPS
	*/
	public String getNiveleducativortps() {
		return niveleducativortps;
	}

	/**
	 * 
	 * 
	 * @campo NivelEducativoRTPS
	*/
	public void setNiveleducativortps(String niveleducativortps) {
		this.niveleducativortps = niveleducativortps;
	}
	/**
	 * 
	 * 
	 * @campo FlagRegimenAlternativo
	*/
	public String getFlagregimenalternativo() {
		return flagregimenalternativo;
	}

	/**
	 * 
	 * 
	 * @campo FlagRegimenAlternativo
	*/
	public void setFlagregimenalternativo(String flagregimenalternativo) {
		this.flagregimenalternativo = flagregimenalternativo;
	}
	/**
	 * 
	 * 
	 * @campo FlagJornadaMaxima
	*/
	public String getFlagjornadamaxima() {
		return flagjornadamaxima;
	}

	/**
	 * 
	 * 
	 * @campo FlagJornadaMaxima
	*/
	public void setFlagjornadamaxima(String flagjornadamaxima) {
		this.flagjornadamaxima = flagjornadamaxima;
	}
	/**
	 * 
	 * 
	 * @campo FlagHorarioNocturno
	*/
	public String getFlaghorarionocturno() {
		return flaghorarionocturno;
	}

	/**
	 * 
	 * 
	 * @campo FlagHorarioNocturno
	*/
	public void setFlaghorarionocturno(String flaghorarionocturno) {
		this.flaghorarionocturno = flaghorarionocturno;
	}
	/**
	 * 
	 * 
	 * @campo FlagOtrosQuinta
	*/
	public String getFlagotrosquinta() {
		return flagotrosquinta;
	}

	/**
	 * 
	 * 
	 * @campo FlagOtrosQuinta
	*/
	public void setFlagotrosquinta(String flagotrosquinta) {
		this.flagotrosquinta = flagotrosquinta;
	}
	/**
	 * 
	 * 
	 * @campo FlagQuintaExonerada
	*/
	public String getFlagquintaexonerada() {
		return flagquintaexonerada;
	}

	/**
	 * 
	 * 
	 * @campo FlagQuintaExonerada
	*/
	public void setFlagquintaexonerada(String flagquintaexonerada) {
		this.flagquintaexonerada = flagquintaexonerada;
	}
	/**
	 * 
	 * 
	 * @campo SituacionEspecial
	*/
	public String getSituacionespecial() {
		return situacionespecial;
	}

	/**
	 * 
	 * 
	 * @campo SituacionEspecial
	*/
	public void setSituacionespecial(String situacionespecial) {
		this.situacionespecial = situacionespecial;
	}
	/**
	 * 
	 * 
	 * @campo FlagMadreResponsabilidad
	*/
	public String getFlagmadreresponsabilidad() {
		return flagmadreresponsabilidad;
	}

	/**
	 * 
	 * 
	 * @campo FlagMadreResponsabilidad
	*/
	public void setFlagmadreresponsabilidad(String flagmadreresponsabilidad) {
		this.flagmadreresponsabilidad = flagmadreresponsabilidad;
	}
	/**
	 * 
	 * 
	 * @campo FlagCentroFormacion
	*/
	public String getFlagcentroformacion() {
		return flagcentroformacion;
	}

	/**
	 * 
	 * 
	 * @campo FlagCentroFormacion
	*/
	public void setFlagcentroformacion(String flagcentroformacion) {
		this.flagcentroformacion = flagcentroformacion;
	}
	/**
	 * 
	 * 
	 * @campo TipoModalidadFormativa
	*/
	public String getTipomodalidadformativa() {
		return tipomodalidadformativa;
	}

	/**
	 * 
	 * 
	 * @campo TipoModalidadFormativa
	*/
	public void setTipomodalidadformativa(String tipomodalidadformativa) {
		this.tipomodalidadformativa = tipomodalidadformativa;
	}
	/**
	 * 
	 * 
	 * @campo PrestadorServicio
	*/
	public String getPrestadorservicio() {
		return prestadorservicio;
	}

	/**
	 * 
	 * 
	 * @campo PrestadorServicio
	*/
	public void setPrestadorservicio(String prestadorservicio) {
		this.prestadorservicio = prestadorservicio;
	}
	/**
	 * 
	 * 
	 * @campo FlagAseguraPension
	*/
	public String getFlagasegurapension() {
		return flagasegurapension;
	}

	/**
	 * 
	 * 
	 * @campo FlagAseguraPension
	*/
	public void setFlagasegurapension(String flagasegurapension) {
		this.flagasegurapension = flagasegurapension;
	}
	/**
	 * 
	 * 
	 * @campo CategoriaOcupacional
	*/
	public String getCategoriaocupacional() {
		return categoriaocupacional;
	}

	/**
	 * 
	 * 
	 * @campo CategoriaOcupacional
	*/
	public void setCategoriaocupacional(String categoriaocupacional) {
		this.categoriaocupacional = categoriaocupacional;
	}
	/**
	 * 
	 * 
	 * @campo FlagConvenioDobleTrib
	*/
	public String getFlagconveniodobletrib() {
		return flagconveniodobletrib;
	}

	/**
	 * 
	 * 
	 * @campo FlagConvenioDobleTrib
	*/
	public void setFlagconveniodobletrib(String flagconveniodobletrib) {
		this.flagconveniodobletrib = flagconveniodobletrib;
	}
	/**
	 * 
	 * 
	 * @campo FirmaDigitalizada
	*/
	public String getFirmadigitalizada() {
		return firmadigitalizada;
	}

	/**
	 * 
	 * 
	 * @campo FirmaDigitalizada
	*/
	public void setFirmadigitalizada(String firmadigitalizada) {
		this.firmadigitalizada = firmadigitalizada;
	}
	/**
	 * 
	 * 
	 * @campo FlagDeConfianza
	*/
	public String getFlagdeconfianza() {
		return flagdeconfianza;
	}

	/**
	 * 
	 * 
	 * @campo FlagDeConfianza
	*/
	public void setFlagdeconfianza(String flagdeconfianza) {
		this.flagdeconfianza = flagdeconfianza;
	}
	/**
	 * 
	 * 
	 * @campo FechaBajaEPS
	*/
	public java.util.Date getFechabajaeps() {
		return fechabajaeps;
	}

	/**
	 * 
	 * 
	 * @campo FechaBajaEPS
	*/
	public void setFechabajaeps(java.util.Date fechabajaeps) {
		this.fechabajaeps = fechabajaeps;
	}
	/**
	 * 
	 * 
	 * @campo CodigoUnidad
	*/
	public Integer getCodigounidad() {
		return codigounidad;
	}

	/**
	 * 
	 * 
	 * @campo CodigoUnidad
	*/
	public void setCodigounidad(Integer codigounidad) {
		this.codigounidad = codigounidad;
	}
	/**
	 * 
	 * 
	 * @campo division
	*/
	public String getDivision() {
		return division;
	}

	/**
	 * 
	 * 
	 * @campo division
	*/
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * 
	 * 
	 * @campo oficina
	*/
	public String getOficina() {
		return oficina;
	}

	/**
	 * 
	 * 
	 * @campo oficina
	*/
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	/**
	 * 
	 * 
	 * @campo Responsableempleado
	*/
	public String getResponsableempleado() {
		return responsableempleado;
	}

	/**
	 * 
	 * 
	 * @campo Responsableempleado
	*/
	public void setResponsableempleado(String responsableempleado) {
		this.responsableempleado = responsableempleado;
	}
	/**
	 * 
	 * 
	 * @campo Responsablejefe
	*/
	public String getResponsablejefe() {
		return responsablejefe;
	}

	/**
	 * 
	 * 
	 * @campo Responsablejefe
	*/
	public void setResponsablejefe(String responsablejefe) {
		this.responsablejefe = responsablejefe;
	}
	/**
	 * 
	 * 
	 * @campo TipoComisionAFP
	*/
	public String getTipocomisionafp() {
		return tipocomisionafp;
	}

	/**
	 * 
	 * 
	 * @campo TipoComisionAFP
	*/
	public void setTipocomisionafp(String tipocomisionafp) {
		this.tipocomisionafp = tipocomisionafp;
	}
	/**
	 * 
	 * 
	 * @campo LocacionAsignada
	*/
	public String getLocacionasignada() {
		return locacionasignada;
	}

	/**
	 * 
	 * 
	 * @campo LocacionAsignada
	*/
	public void setLocacionasignada(String locacionasignada) {
		this.locacionasignada = locacionasignada;
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
	 * @campo Motivo
	*/
	public String getMotivo() {
		return motivo;
	}

	/**
	 * 
	 * 
	 * @campo Motivo
	*/
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * 
	 * 
	 * @campo CostCenterDestination
	*/
	public String getCostcenterdestination() {
		return costcenterdestination;
	}

	/**
	 * 
	 * 
	 * @campo CostCenterDestination
	*/
	public void setCostcenterdestination(String costcenterdestination) {
		this.costcenterdestination = costcenterdestination;
	}
	/**
	 * 
	 * 
	 * @campo UnidadTrabajo
	*/
	public String getUnidadtrabajo() {
		return unidadtrabajo;
	}

	/**
	 * 
	 * 
	 * @campo UnidadTrabajo
	*/
	public void setUnidadtrabajo(String unidadtrabajo) {
		this.unidadtrabajo = unidadtrabajo;
	}
	/**
	 * 
	 * 
	 * @campo JefeResponsable
	*/
	public Integer getJeferesponsable() {
		return jeferesponsable;
	}

	/**
	 * 
	 * 
	 * @campo JefeResponsable
	*/
	public void setJeferesponsable(Integer jeferesponsable) {
		this.jeferesponsable = jeferesponsable;
	}
	/**
	 * 
	 * 
	 * @campo Orden_Organigrama
	*/
	public String getOrdenOrganigrama() {
		return ordenOrganigrama;
	}

	/**
	 * 
	 * 
	 * @campo Orden_Organigrama
	*/
	public void setOrdenOrganigrama(String ordenOrganigrama) {
		this.ordenOrganigrama = ordenOrganigrama;
	}
	/**
	 * 
	 * 
	 * @campo UnidadOperativa
	*/
	public String getUnidadoperativa() {
		return unidadoperativa;
	}

	/**
	 * 
	 * 
	 * @campo UnidadOperativa
	*/
	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}
	/**
	 * 
	 * 
	 * @campo EmpleadoRelacionado
	*/
	public Integer getEmpleadorelacionado() {
		return empleadorelacionado;
	}

	/**
	 * 
	 * 
	 * @campo EmpleadoRelacionado
	*/
	public void setEmpleadorelacionado(Integer empleadorelacionado) {
		this.empleadorelacionado = empleadorelacionado;
	}
	/**
	 * 
	 * 
	 * @campo Posicion
	*/
	public String getPosicion() {
		return posicion;
	}

	/**
	 * 
	 * 
	 * @campo Posicion
	*/
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	/**
	 * 
	 * 
	 * @campo GRUPOOCUPACIONAL
	*/
	public java.math.BigDecimal getGrupoocupacional() {
		return grupoocupacional;
	}

	/**
	 * 
	 * 
	 * @campo GRUPOOCUPACIONAL
	*/
	public void setGrupoocupacional(java.math.BigDecimal grupoocupacional) {
		this.grupoocupacional = grupoocupacional;
	}
	/**
	 * 
	 * 
	 * @campo ESTADONIVELACION
	*/
	public String getEstadonivelacion() {
		return estadonivelacion;
	}

	/**
	 * 
	 * 
	 * @campo ESTADONIVELACION
	*/
	public void setEstadonivelacion(String estadonivelacion) {
		this.estadonivelacion = estadonivelacion;
	}
	/**
	 * 
	 * 
	 * @campo APROBADORNIVELACION
	*/
	public Integer getAprobadornivelacion() {
		return aprobadornivelacion;
	}

	/**
	 * 
	 * 
	 * @campo APROBADORNIVELACION
	*/
	public void setAprobadornivelacion(Integer aprobadornivelacion) {
		this.aprobadornivelacion = aprobadornivelacion;
	}
	/**
	 * 
	 * 
	 * @campo SOLICITADORNIVELACION
	*/
	public Integer getSolicitadornivelacion() {
		return solicitadornivelacion;
	}

	/**
	 * 
	 * 
	 * @campo SOLICITADORNIVELACION
	*/
	public void setSolicitadornivelacion(Integer solicitadornivelacion) {
		this.solicitadornivelacion = solicitadornivelacion;
	}
	/**
	 * 
	 * 
	 * @campo FlagEducacionCompletaIEP
	*/
	public String getFlageducacioncompletaiep() {
		return flageducacioncompletaiep;
	}

	/**
	 * 
	 * 
	 * @campo FlagEducacionCompletaIEP
	*/
	public void setFlageducacioncompletaiep(String flageducacioncompletaiep) {
		this.flageducacioncompletaiep = flageducacioncompletaiep;
	}

}
