package net.royal.spring.core.dominio.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.royal.spring.core.dominio.BeanEmpleadomast;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class DtoComunEmpleadomast extends DominioTransaccion{
	private Integer empleado;
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
	private String responsableempleado;
	private String responsablejefe;
	private String responsablesueldo;
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
	private String companiasocio;
	private String centrocostos;
	private String afe;
	private String deptoorganizacion;
	private String departamentooperacional;
	private Integer tipohorario;
	private String gradosalario;
	private String cargo;
	private String posicion;
	private String nivelacceso;
	private String flagipssvida;
	private String flagacctrabajo;
	private String flagsindicato;
	private String flagcooperativa;
	private String flagretencionjudicial;
	private String flagimpuestorenta;
	private String flagreingresos;
	private String flagcomision;
	private String correointerno;
	private BigDecimal sueldoactuallocal;
	private BigDecimal sueldoactualdolar;
	private BigDecimal sueldoanteriorlocal;
	private BigDecimal sueldoanteriordolar;
	private String monedapago;
	private Integer perfil;
	private String foto;
	private String nivelsalario;
	private java.util.Date fechaliquidacion;
	private java.util.Date fechareingreso;
	private String unidadreplicacion;
	private Integer codigocargo;
	private java.util.Date ultimafechapagovacacion;
	private String ultimousuario;
	private String estado;
	private String locacionasignada;
	private Integer empleadorelacionado;
	private String departamentoorganizacional;
	private String tarjetadecredito;
	private String sucursal;
	private String ruccentroasistenciasocial;
	private String actividad;
	private Integer gerencia;
	private Integer subgerencia;
	private Integer plantillaconcepto;
	private java.util.Date ultimafechamodif;
	private String redondeoacuenta;
	private String flagsmf;
	private String firmadigitalizada;
	private String provincia;
	private Integer posicionorganigrama;
	private String planta;
	private String flagjefedepartamento;
	private String flagporcentaje;
	private String centroriesgosctr;
	private String tipopensionjubilacion;
	private String flagtrabajadorpensionista;
	private String flagsctrsalud;
	private String flagsctrpension;
	private String flagdiscapacidad;
	private String flagsujetocontrol;
	private String flagsindicalizado;
	private String flagdomiciliado;
	private String flagdeconfianza;
	private String empleadoant;
	private String asignacionfamiliar;
	private String ordenservicio;
	private java.util.Date fechabajaeps;
	private String tiporemuneracionrtps;
	private String municipalidadnacimiento;
	private String establecimientortps;
	private Integer proveedorintermediacion;
	private String niveleducativortps;
	private String flagacctrabajop;
	private String sucursalbanconacion;
	private BigDecimal grupoocupacional;
	private Integer cliente;
	private Integer clienteunidad;
	private Integer tareonivel;
	private Integer empleadonivel;
	private Integer tipopuesto;
	private String estadonivelacion;
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
	private java.util.Date fechavacaciones;
	private String formato2;
	private Integer aprobadornivelacion;
	private Integer solicitadornivelacion;
	private String flagasegurapension;
	private String categoriaocupacional;
	private String flagconveniodobletrib;
	private String tipocomisionafp;
	private String fuentefinanciamientocp;
	private String actividadcp;
	private Integer jeferesponsable;
	private BigDecimal jefequienreporta;
	private String unidadoperativa;
	private Integer codigounidad;
	private String tiempoContratoTotal;
	private String division;
	private String oficina;
	private String unidadtrabajo;
	private String ordenOrganigrama;
	private String tiempoServicio;
	private Integer dptoareaoperativa;
	private String flageducacioncompletaiep;
	private String jeferesponsablecompania;
	
	private String motivo;
	private String costcenterdestination;
	private String centrocostosDescri;

	
	
	
	public String getCentrocostosDescri() {
		return centrocostosDescri;
	}
	public void setCentrocostosDescri(String centrocostosDescri) {
		this.centrocostosDescri = centrocostosDescri;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCostcenterdestination() {
		return costcenterdestination;
	}
	public void setCostcenterdestination(String costcenterdestination) {
		this.costcenterdestination = costcenterdestination;
	}
	public DtoComunEmpleadomast() {}
	public DtoComunEmpleadomast(Integer empleado) {
		this.empleado=empleado;
	}
	public DtoComunEmpleadomast(Integer empleado,String codigousuario) {
		this.empleado=empleado;
		this.codigousuario=codigousuario;
	}
	
	public Integer getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Integer empleado) {
		this.empleado = empleado;
	}
	public String getTipopago() {
		return tipopago;
	}
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	public String getTipotrabajador() {
		return tipotrabajador;
	}
	public void setTipotrabajador(String tipotrabajador) {
		this.tipotrabajador = tipotrabajador;
	}
	public String getTipoplanilla() {
		return tipoplanilla;
	}
	public void setTipoplanilla(String tipoplanilla) {
		this.tipoplanilla = tipoplanilla;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getTipovisa() {
		return tipovisa;
	}
	public void setTipovisa(String tipovisa) {
		this.tipovisa = tipovisa;
	}
	public java.util.Date getVisafechainicio() {
		return visafechainicio;
	}
	public void setVisafechainicio(java.util.Date visafechainicio) {
		this.visafechainicio = visafechainicio;
	}
	public java.util.Date getVisafechaexpiracion() {
		return visafechaexpiracion;
	}
	public void setVisafechaexpiracion(java.util.Date visafechaexpiracion) {
		this.visafechaexpiracion = visafechaexpiracion;
	}
	public java.util.Date getServiciomilitardesde() {
		return serviciomilitardesde;
	}
	public void setServiciomilitardesde(java.util.Date serviciomilitardesde) {
		this.serviciomilitardesde = serviciomilitardesde;
	}
	public java.util.Date getServiciomilitarhasta() {
		return serviciomilitarhasta;
	}
	public void setServiciomilitarhasta(java.util.Date serviciomilitarhasta) {
		this.serviciomilitarhasta = serviciomilitarhasta;
	}
	public String getNumeroarchivo() {
		return numeroarchivo;
	}
	public void setNumeroarchivo(String numeroarchivo) {
		this.numeroarchivo = numeroarchivo;
	}
	public String getUnidadnegocioasignada() {
		return unidadnegocioasignada;
	}
	public void setUnidadnegocioasignada(String unidadnegocioasignada) {
		this.unidadnegocioasignada = unidadnegocioasignada;
	}
	public String getLocaciondepago() {
		return locaciondepago;
	}
	public void setLocaciondepago(String locaciondepago) {
		this.locaciondepago = locaciondepago;
	}
	public String getResponsableempleado() {
		return responsableempleado;
	}
	public void setResponsableempleado(String responsableempleado) {
		this.responsableempleado = responsableempleado;
	}
	public String getResponsablejefe() {
		return responsablejefe;
	}
	public void setResponsablejefe(String responsablejefe) {
		this.responsablejefe = responsablejefe;
	}
	public String getResponsablesueldo() {
		return responsablesueldo;
	}
	public void setResponsablesueldo(String responsablesueldo) {
		this.responsablesueldo = responsablesueldo;
	}
	public String getCodigousuario() {
		return codigousuario;
	}
	public void setCodigousuario(String codigousuario) {
		this.codigousuario = codigousuario;
	}
	public String getTipoasistenciasocial() {
		return tipoasistenciasocial;
	}
	public void setTipoasistenciasocial(String tipoasistenciasocial) {
		this.tipoasistenciasocial = tipoasistenciasocial;
	}
	public String getCentroasistenciasocial() {
		return centroasistenciasocial;
	}
	public void setCentroasistenciasocial(String centroasistenciasocial) {
		this.centroasistenciasocial = centroasistenciasocial;
	}
	public String getTipocarnetasistenciasocial() {
		return tipocarnetasistenciasocial;
	}
	public void setTipocarnetasistenciasocial(String tipocarnetasistenciasocial) {
		this.tipocarnetasistenciasocial = tipocarnetasistenciasocial;
	}
	public String getCarnetasistenciasocial() {
		return carnetasistenciasocial;
	}
	public void setCarnetasistenciasocial(String carnetasistenciasocial) {
		this.carnetasistenciasocial = carnetasistenciasocial;
	}
	public String getTipopension() {
		return tipopension;
	}
	public void setTipopension(String tipopension) {
		this.tipopension = tipopension;
	}
	public java.util.Date getFechainiciopension() {
		return fechainiciopension;
	}
	public void setFechainiciopension(java.util.Date fechainiciopension) {
		this.fechainiciopension = fechainiciopension;
	}
	public java.util.Date getFechaterminopension() {
		return fechaterminopension;
	}
	public void setFechaterminopension(java.util.Date fechaterminopension) {
		this.fechaterminopension = fechaterminopension;
	}
	public String getCodigoafp() {
		return codigoafp;
	}
	public void setCodigoafp(String codigoafp) {
		this.codigoafp = codigoafp;
	}
	public String getNumeroafp() {
		return numeroafp;
	}
	public void setNumeroafp(String numeroafp) {
		this.numeroafp = numeroafp;
	}
	public String getBancocts() {
		return bancocts;
	}
	public void setBancocts(String bancocts) {
		this.bancocts = bancocts;
	}
	public String getTipocuentacts() {
		return tipocuentacts;
	}
	public void setTipocuentacts(String tipocuentacts) {
		this.tipocuentacts = tipocuentacts;
	}
	public String getTipomonedacts() {
		return tipomonedacts;
	}
	public void setTipomonedacts(String tipomonedacts) {
		this.tipomonedacts = tipomonedacts;
	}
	public String getNumerocuentacts() {
		return numerocuentacts;
	}
	public void setNumerocuentacts(String numerocuentacts) {
		this.numerocuentacts = numerocuentacts;
	}
	public String getEstadoempleado() {
		return estadoempleado;
	}
	public void setEstadoempleado(String estadoempleado) {
		this.estadoempleado = estadoempleado;
	}
	public java.util.Date getFechaingreso() {
		return fechaingreso;
	}
	public void setFechaingreso(java.util.Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}
	public java.util.Date getFechaultimaplanilla() {
		return fechaultimaplanilla;
	}
	public void setFechaultimaplanilla(java.util.Date fechaultimaplanilla) {
		this.fechaultimaplanilla = fechaultimaplanilla;
	}
	public String getTipocontrato() {
		return tipocontrato;
	}
	public void setTipocontrato(String tipocontrato) {
		this.tipocontrato = tipocontrato;
	}
	public java.util.Date getFechainiciocontrato() {
		return fechainiciocontrato;
	}
	public void setFechainiciocontrato(java.util.Date fechainiciocontrato) {
		this.fechainiciocontrato = fechainiciocontrato;
	}
	public java.util.Date getFechafincontrato() {
		return fechafincontrato;
	}
	public void setFechafincontrato(java.util.Date fechafincontrato) {
		this.fechafincontrato = fechafincontrato;
	}
	public java.util.Date getFechacese() {
		return fechacese;
	}
	public void setFechacese(java.util.Date fechacese) {
		this.fechacese = fechacese;
	}
	public String getRazoncese() {
		return razoncese;
	}
	public void setRazoncese(String razoncese) {
		this.razoncese = razoncese;
	}
	public Integer getMotivocese() {
		return motivocese;
	}
	public void setMotivocese(Integer motivocese) {
		this.motivocese = motivocese;
	}
	public Integer getContratista() {
		return contratista;
	}
	public void setContratista(Integer contratista) {
		this.contratista = contratista;
	}
	public String getCompaniasocio() {
		return companiasocio;
	}
	public void setCompaniasocio(String companiasocio) {
		this.companiasocio = companiasocio;
	}
	public String getCentrocostos() {
		return centrocostos;
	}
	public void setCentrocostos(String centrocostos) {
		this.centrocostos = centrocostos;
	}
	public String getAfe() {
		return afe;
	}
	public void setAfe(String afe) {
		this.afe = afe;
	}
	public String getDeptoorganizacion() {
		return deptoorganizacion;
	}
	public void setDeptoorganizacion(String deptoorganizacion) {
		this.deptoorganizacion = deptoorganizacion;
	}
	public String getDepartamentooperacional() {
		return departamentooperacional;
	}
	public void setDepartamentooperacional(String departamentooperacional) {
		this.departamentooperacional = departamentooperacional;
	}
	public Integer getTipohorario() {
		return tipohorario;
	}
	public void setTipohorario(Integer tipohorario) {
		this.tipohorario = tipohorario;
	}
	public String getGradosalario() {
		return gradosalario;
	}
	public void setGradosalario(String gradosalario) {
		this.gradosalario = gradosalario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getNivelacceso() {
		return nivelacceso;
	}
	public void setNivelacceso(String nivelacceso) {
		this.nivelacceso = nivelacceso;
	}
	public String getFlagipssvida() {
		return flagipssvida;
	}
	public void setFlagipssvida(String flagipssvida) {
		this.flagipssvida = flagipssvida;
	}
	public String getFlagacctrabajo() {
		return flagacctrabajo;
	}
	public void setFlagacctrabajo(String flagacctrabajo) {
		this.flagacctrabajo = flagacctrabajo;
	}
	public String getFlagsindicato() {
		return flagsindicato;
	}
	public void setFlagsindicato(String flagsindicato) {
		this.flagsindicato = flagsindicato;
	}
	public String getFlagcooperativa() {
		return flagcooperativa;
	}
	public void setFlagcooperativa(String flagcooperativa) {
		this.flagcooperativa = flagcooperativa;
	}
	public String getFlagretencionjudicial() {
		return flagretencionjudicial;
	}
	public void setFlagretencionjudicial(String flagretencionjudicial) {
		this.flagretencionjudicial = flagretencionjudicial;
	}
	public String getFlagimpuestorenta() {
		return flagimpuestorenta;
	}
	public void setFlagimpuestorenta(String flagimpuestorenta) {
		this.flagimpuestorenta = flagimpuestorenta;
	}
	public String getFlagreingresos() {
		return flagreingresos;
	}
	public void setFlagreingresos(String flagreingresos) {
		this.flagreingresos = flagreingresos;
	}
	public String getFlagcomision() {
		return flagcomision;
	}
	public void setFlagcomision(String flagcomision) {
		this.flagcomision = flagcomision;
	}
	public String getCorreointerno() {
		return correointerno;
	}
	public void setCorreointerno(String correointerno) {
		this.correointerno = correointerno;
	}
	public BigDecimal getSueldoactuallocal() {
		return sueldoactuallocal;
	}
	public void setSueldoactuallocal(BigDecimal sueldoactuallocal) {
		this.sueldoactuallocal = sueldoactuallocal;
	}
	public BigDecimal getSueldoactualdolar() {
		return sueldoactualdolar;
	}
	public void setSueldoactualdolar(BigDecimal sueldoactualdolar) {
		this.sueldoactualdolar = sueldoactualdolar;
	}
	public BigDecimal getSueldoanteriorlocal() {
		return sueldoanteriorlocal;
	}
	public void setSueldoanteriorlocal(BigDecimal sueldoanteriorlocal) {
		this.sueldoanteriorlocal = sueldoanteriorlocal;
	}
	public BigDecimal getSueldoanteriordolar() {
		return sueldoanteriordolar;
	}
	public void setSueldoanteriordolar(BigDecimal sueldoanteriordolar) {
		this.sueldoanteriordolar = sueldoanteriordolar;
	}
	public String getMonedapago() {
		return monedapago;
	}
	public void setMonedapago(String monedapago) {
		this.monedapago = monedapago;
	}
	public Integer getPerfil() {
		return perfil;
	}
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getNivelsalario() {
		return nivelsalario;
	}
	public void setNivelsalario(String nivelsalario) {
		this.nivelsalario = nivelsalario;
	}
	public java.util.Date getFechaliquidacion() {
		return fechaliquidacion;
	}
	public void setFechaliquidacion(java.util.Date fechaliquidacion) {
		this.fechaliquidacion = fechaliquidacion;
	}
	public java.util.Date getFechareingreso() {
		return fechareingreso;
	}
	public void setFechareingreso(java.util.Date fechareingreso) {
		this.fechareingreso = fechareingreso;
	}
	public String getUnidadreplicacion() {
		return unidadreplicacion;
	}
	public void setUnidadreplicacion(String unidadreplicacion) {
		this.unidadreplicacion = unidadreplicacion;
	}
	public Integer getCodigocargo() {
		return codigocargo;
	}
	public void setCodigocargo(Integer codigocargo) {
		this.codigocargo = codigocargo;
	}
	public java.util.Date getUltimafechapagovacacion() {
		return ultimafechapagovacacion;
	}
	public void setUltimafechapagovacacion(java.util.Date ultimafechapagovacacion) {
		this.ultimafechapagovacacion = ultimafechapagovacacion;
	}
	public String getUltimousuario() {
		return ultimousuario;
	}
	public void setUltimousuario(String ultimousuario) {
		this.ultimousuario = ultimousuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getLocacionasignada() {
		return locacionasignada;
	}
	public void setLocacionasignada(String locacionasignada) {
		this.locacionasignada = locacionasignada;
	}
	public Integer getEmpleadorelacionado() {
		return empleadorelacionado;
	}
	public void setEmpleadorelacionado(Integer empleadorelacionado) {
		this.empleadorelacionado = empleadorelacionado;
	}
	public String getDepartamentoorganizacional() {
		return departamentoorganizacional;
	}
	public void setDepartamentoorganizacional(String departamentoorganizacional) {
		this.departamentoorganizacional = departamentoorganizacional;
	}
	public String getTarjetadecredito() {
		return tarjetadecredito;
	}
	public void setTarjetadecredito(String tarjetadecredito) {
		this.tarjetadecredito = tarjetadecredito;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getRuccentroasistenciasocial() {
		return ruccentroasistenciasocial;
	}
	public void setRuccentroasistenciasocial(String ruccentroasistenciasocial) {
		this.ruccentroasistenciasocial = ruccentroasistenciasocial;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public Integer getGerencia() {
		return gerencia;
	}
	public void setGerencia(Integer gerencia) {
		this.gerencia = gerencia;
	}
	public Integer getSubgerencia() {
		return subgerencia;
	}
	public void setSubgerencia(Integer subgerencia) {
		this.subgerencia = subgerencia;
	}
	public Integer getPlantillaconcepto() {
		return plantillaconcepto;
	}
	public void setPlantillaconcepto(Integer plantillaconcepto) {
		this.plantillaconcepto = plantillaconcepto;
	}
	public java.util.Date getUltimafechamodif() {
		return ultimafechamodif;
	}
	public void setUltimafechamodif(java.util.Date ultimafechamodif) {
		this.ultimafechamodif = ultimafechamodif;
	}
	public String getRedondeoacuenta() {
		return redondeoacuenta;
	}
	public void setRedondeoacuenta(String redondeoacuenta) {
		this.redondeoacuenta = redondeoacuenta;
	}
	public String getFlagsmf() {
		return flagsmf;
	}
	public void setFlagsmf(String flagsmf) {
		this.flagsmf = flagsmf;
	}
	public String getFirmadigitalizada() {
		return firmadigitalizada;
	}
	public void setFirmadigitalizada(String firmadigitalizada) {
		this.firmadigitalizada = firmadigitalizada;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Integer getPosicionorganigrama() {
		return posicionorganigrama;
	}
	public void setPosicionorganigrama(Integer posicionorganigrama) {
		this.posicionorganigrama = posicionorganigrama;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getFlagjefedepartamento() {
		return flagjefedepartamento;
	}
	public void setFlagjefedepartamento(String flagjefedepartamento) {
		this.flagjefedepartamento = flagjefedepartamento;
	}
	public String getFlagporcentaje() {
		return flagporcentaje;
	}
	public void setFlagporcentaje(String flagporcentaje) {
		this.flagporcentaje = flagporcentaje;
	}
	public String getCentroriesgosctr() {
		return centroriesgosctr;
	}
	public void setCentroriesgosctr(String centroriesgosctr) {
		this.centroriesgosctr = centroriesgosctr;
	}
	public String getTipopensionjubilacion() {
		return tipopensionjubilacion;
	}
	public void setTipopensionjubilacion(String tipopensionjubilacion) {
		this.tipopensionjubilacion = tipopensionjubilacion;
	}
	public String getFlagtrabajadorpensionista() {
		return flagtrabajadorpensionista;
	}
	public void setFlagtrabajadorpensionista(String flagtrabajadorpensionista) {
		this.flagtrabajadorpensionista = flagtrabajadorpensionista;
	}
	public String getFlagsctrsalud() {
		return flagsctrsalud;
	}
	public void setFlagsctrsalud(String flagsctrsalud) {
		this.flagsctrsalud = flagsctrsalud;
	}
	public String getFlagsctrpension() {
		return flagsctrpension;
	}
	public void setFlagsctrpension(String flagsctrpension) {
		this.flagsctrpension = flagsctrpension;
	}
	public String getFlagdiscapacidad() {
		return flagdiscapacidad;
	}
	public void setFlagdiscapacidad(String flagdiscapacidad) {
		this.flagdiscapacidad = flagdiscapacidad;
	}
	public String getFlagsujetocontrol() {
		return flagsujetocontrol;
	}
	public void setFlagsujetocontrol(String flagsujetocontrol) {
		this.flagsujetocontrol = flagsujetocontrol;
	}
	public String getFlagsindicalizado() {
		return flagsindicalizado;
	}
	public void setFlagsindicalizado(String flagsindicalizado) {
		this.flagsindicalizado = flagsindicalizado;
	}
	public String getFlagdomiciliado() {
		return flagdomiciliado;
	}
	public void setFlagdomiciliado(String flagdomiciliado) {
		this.flagdomiciliado = flagdomiciliado;
	}
	public String getFlagdeconfianza() {
		return flagdeconfianza;
	}
	public void setFlagdeconfianza(String flagdeconfianza) {
		this.flagdeconfianza = flagdeconfianza;
	}
	public String getEmpleadoant() {
		return empleadoant;
	}
	public void setEmpleadoant(String empleadoant) {
		this.empleadoant = empleadoant;
	}
	public String getAsignacionfamiliar() {
		return asignacionfamiliar;
	}
	public void setAsignacionfamiliar(String asignacionfamiliar) {
		this.asignacionfamiliar = asignacionfamiliar;
	}
	public String getOrdenservicio() {
		return ordenservicio;
	}
	public void setOrdenservicio(String ordenservicio) {
		this.ordenservicio = ordenservicio;
	}
	public java.util.Date getFechabajaeps() {
		return fechabajaeps;
	}
	public void setFechabajaeps(java.util.Date fechabajaeps) {
		this.fechabajaeps = fechabajaeps;
	}
	public String getTiporemuneracionrtps() {
		return tiporemuneracionrtps;
	}
	public void setTiporemuneracionrtps(String tiporemuneracionrtps) {
		this.tiporemuneracionrtps = tiporemuneracionrtps;
	}
	public String getMunicipalidadnacimiento() {
		return municipalidadnacimiento;
	}
	public void setMunicipalidadnacimiento(String municipalidadnacimiento) {
		this.municipalidadnacimiento = municipalidadnacimiento;
	}
	public String getEstablecimientortps() {
		return establecimientortps;
	}
	public void setEstablecimientortps(String establecimientortps) {
		this.establecimientortps = establecimientortps;
	}
	public Integer getProveedorintermediacion() {
		return proveedorintermediacion;
	}
	public void setProveedorintermediacion(Integer proveedorintermediacion) {
		this.proveedorintermediacion = proveedorintermediacion;
	}
	public String getNiveleducativortps() {
		return niveleducativortps;
	}
	public void setNiveleducativortps(String niveleducativortps) {
		this.niveleducativortps = niveleducativortps;
	}
	public String getFlagacctrabajop() {
		return flagacctrabajop;
	}
	public void setFlagacctrabajop(String flagacctrabajop) {
		this.flagacctrabajop = flagacctrabajop;
	}
	public String getSucursalbanconacion() {
		return sucursalbanconacion;
	}
	public void setSucursalbanconacion(String sucursalbanconacion) {
		this.sucursalbanconacion = sucursalbanconacion;
	}
	public BigDecimal getGrupoocupacional() {
		return grupoocupacional;
	}
	public void setGrupoocupacional(BigDecimal grupoocupacional) {
		this.grupoocupacional = grupoocupacional;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getClienteunidad() {
		return clienteunidad;
	}
	public void setClienteunidad(Integer clienteunidad) {
		this.clienteunidad = clienteunidad;
	}
	public Integer getTareonivel() {
		return tareonivel;
	}
	public void setTareonivel(Integer tareonivel) {
		this.tareonivel = tareonivel;
	}
	public Integer getEmpleadonivel() {
		return empleadonivel;
	}
	public void setEmpleadonivel(Integer empleadonivel) {
		this.empleadonivel = empleadonivel;
	}
	public Integer getTipopuesto() {
		return tipopuesto;
	}
	public void setTipopuesto(Integer tipopuesto) {
		this.tipopuesto = tipopuesto;
	}
	public String getEstadonivelacion() {
		return estadonivelacion;
	}
	public void setEstadonivelacion(String estadonivelacion) {
		this.estadonivelacion = estadonivelacion;
	}
	public String getFlagregimenalternativo() {
		return flagregimenalternativo;
	}
	public void setFlagregimenalternativo(String flagregimenalternativo) {
		this.flagregimenalternativo = flagregimenalternativo;
	}
	public String getFlagjornadamaxima() {
		return flagjornadamaxima;
	}
	public void setFlagjornadamaxima(String flagjornadamaxima) {
		this.flagjornadamaxima = flagjornadamaxima;
	}
	public String getFlaghorarionocturno() {
		return flaghorarionocturno;
	}
	public void setFlaghorarionocturno(String flaghorarionocturno) {
		this.flaghorarionocturno = flaghorarionocturno;
	}
	public String getFlagotrosquinta() {
		return flagotrosquinta;
	}
	public void setFlagotrosquinta(String flagotrosquinta) {
		this.flagotrosquinta = flagotrosquinta;
	}
	public String getFlagquintaexonerada() {
		return flagquintaexonerada;
	}
	public void setFlagquintaexonerada(String flagquintaexonerada) {
		this.flagquintaexonerada = flagquintaexonerada;
	}
	public String getSituacionespecial() {
		return situacionespecial;
	}
	public void setSituacionespecial(String situacionespecial) {
		this.situacionespecial = situacionespecial;
	}
	public String getFlagmadreresponsabilidad() {
		return flagmadreresponsabilidad;
	}
	public void setFlagmadreresponsabilidad(String flagmadreresponsabilidad) {
		this.flagmadreresponsabilidad = flagmadreresponsabilidad;
	}
	public String getFlagcentroformacion() {
		return flagcentroformacion;
	}
	public void setFlagcentroformacion(String flagcentroformacion) {
		this.flagcentroformacion = flagcentroformacion;
	}
	public String getTipomodalidadformativa() {
		return tipomodalidadformativa;
	}
	public void setTipomodalidadformativa(String tipomodalidadformativa) {
		this.tipomodalidadformativa = tipomodalidadformativa;
	}
	public String getPrestadorservicio() {
		return prestadorservicio;
	}
	public void setPrestadorservicio(String prestadorservicio) {
		this.prestadorservicio = prestadorservicio;
	}
	public java.util.Date getFechavacaciones() {
		return fechavacaciones;
	}
	public void setFechavacaciones(java.util.Date fechavacaciones) {
		this.fechavacaciones = fechavacaciones;
	}
	public String getFormato2() {
		return formato2;
	}
	public void setFormato2(String formato2) {
		this.formato2 = formato2;
	}
	public Integer getAprobadornivelacion() {
		return aprobadornivelacion;
	}
	public void setAprobadornivelacion(Integer aprobadornivelacion) {
		this.aprobadornivelacion = aprobadornivelacion;
	}
	public Integer getSolicitadornivelacion() {
		return solicitadornivelacion;
	}
	public void setSolicitadornivelacion(Integer solicitadornivelacion) {
		this.solicitadornivelacion = solicitadornivelacion;
	}
	public String getFlagasegurapension() {
		return flagasegurapension;
	}
	public void setFlagasegurapension(String flagasegurapension) {
		this.flagasegurapension = flagasegurapension;
	}
	public String getCategoriaocupacional() {
		return categoriaocupacional;
	}
	public void setCategoriaocupacional(String categoriaocupacional) {
		this.categoriaocupacional = categoriaocupacional;
	}
	public String getFlagconveniodobletrib() {
		return flagconveniodobletrib;
	}
	public void setFlagconveniodobletrib(String flagconveniodobletrib) {
		this.flagconveniodobletrib = flagconveniodobletrib;
	}
	public String getTipocomisionafp() {
		return tipocomisionafp;
	}
	public void setTipocomisionafp(String tipocomisionafp) {
		this.tipocomisionafp = tipocomisionafp;
	}
	public String getFuentefinanciamientocp() {
		return fuentefinanciamientocp;
	}
	public void setFuentefinanciamientocp(String fuentefinanciamientocp) {
		this.fuentefinanciamientocp = fuentefinanciamientocp;
	}
	public String getActividadcp() {
		return actividadcp;
	}
	public void setActividadcp(String actividadcp) {
		this.actividadcp = actividadcp;
	}
	public Integer getJeferesponsable() {
		return jeferesponsable;
	}
	public void setJeferesponsable(Integer jeferesponsable) {
		this.jeferesponsable = jeferesponsable;
	}
	public BigDecimal getJefequienreporta() {
		return jefequienreporta;
	}
	public void setJefequienreporta(BigDecimal jefequienreporta) {
		this.jefequienreporta = jefequienreporta;
	}
	public String getUnidadoperativa() {
		return unidadoperativa;
	}
	public void setUnidadoperativa(String unidadoperativa) {
		this.unidadoperativa = unidadoperativa;
	}
	public Integer getCodigounidad() {
		return codigounidad;
	}
	public void setCodigounidad(Integer codigounidad) {
		this.codigounidad = codigounidad;
	}
	public String getTiempoContratoTotal() {
		return tiempoContratoTotal;
	}
	public void setTiempoContratoTotal(String tiempoContratoTotal) {
		this.tiempoContratoTotal = tiempoContratoTotal;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getUnidadtrabajo() {
		return unidadtrabajo;
	}
	public void setUnidadtrabajo(String unidadtrabajo) {
		this.unidadtrabajo = unidadtrabajo;
	}
	public String getOrdenOrganigrama() {
		return ordenOrganigrama;
	}
	public void setOrdenOrganigrama(String ordenOrganigrama) {
		this.ordenOrganigrama = ordenOrganigrama;
	}
	public String getTiempoServicio() {
		return tiempoServicio;
	}
	public void setTiempoServicio(String tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}
	public Integer getDptoareaoperativa() {
		return dptoareaoperativa;
	}
	public void setDptoareaoperativa(Integer dptoareaoperativa) {
		this.dptoareaoperativa = dptoareaoperativa;
	}
	public String getFlageducacioncompletaiep() {
		return flageducacioncompletaiep;
	}
	public void setFlageducacioncompletaiep(String flageducacioncompletaiep) {
		this.flageducacioncompletaiep = flageducacioncompletaiep;
	}
	public String getJeferesponsablecompania() {
		return jeferesponsablecompania;
	}
	public void setJeferesponsablecompania(String jeferesponsablecompania) {
		this.jeferesponsablecompania = jeferesponsablecompania;
	}
	
	
	
	public BeanEmpleadomast obtenerBean() {
		BeanEmpleadomast bean = new BeanEmpleadomast();
		return obtenerBean(bean);
	}

	public BeanEmpleadomast obtenerBean(BeanEmpleadomast bean) {
		if (bean == null)
			bean = new BeanEmpleadomast();

		bean.getPk().setEmpleado(empleado);
		bean.getPk().setCompaniasocio(companiasocio);
		bean.setTipopago(tipopago);
		bean.setTipotrabajador(tipotrabajador);
		bean.setTipoplanilla(tipoplanilla);
		bean.setRaza(raza);
		bean.setReligion(religion);
		bean.setTipovisa(tipovisa);
		bean.setVisafechainicio(visafechainicio);
		bean.setVisafechaexpiracion(visafechaexpiracion);
		bean.setServiciomilitardesde(serviciomilitardesde);
		bean.setServiciomilitarhasta(serviciomilitarhasta);
		bean.setNumeroarchivo(numeroarchivo);
		bean.setUnidadnegocioasignada(unidadnegocioasignada);
		bean.setLocaciondepago(locaciondepago);
		bean.setCodigousuario(codigousuario);
		bean.setTipoasistenciasocial(tipoasistenciasocial);
		bean.setCentroasistenciasocial(centroasistenciasocial);
		bean.setTipocarnetasistenciasocial(tipocarnetasistenciasocial);
		bean.setCarnetasistenciasocial(carnetasistenciasocial);
		bean.setTipopension(tipopension);
		bean.setFechainiciopension(fechainiciopension);
		bean.setFechaterminopension(fechaterminopension);
		bean.setCodigoafp(codigoafp);
		bean.setNumeroafp(numeroafp);
		bean.setBancocts(bancocts);
		bean.setTipocuentacts(tipocuentacts);
		bean.setTipomonedacts(tipomonedacts);
		bean.setNumerocuentacts(numerocuentacts);
		bean.setEstadoempleado(estadoempleado);
		bean.setFechaingreso(fechaingreso);
		bean.setFechaultimaplanilla(fechaultimaplanilla);
		bean.setTipocontrato(tipocontrato);
		bean.setFechainiciocontrato(fechainiciocontrato);
		bean.setFechafincontrato(fechafincontrato);
		bean.setFechacese(fechacese);
		bean.setRazoncese(razoncese);
		bean.setMotivocese(motivocese);
		bean.setContratista(contratista);
		bean.setCentrocostos(centrocostos);
		bean.setAfe(afe);
		bean.setDeptoorganizacion(deptoorganizacion);
		bean.setDepartamentooperacional(departamentooperacional);
		bean.setTipohorario(tipohorario);
		bean.setGradosalario(gradosalario);
		bean.setCargo(cargo);
		bean.setNivelacceso(nivelacceso);
		bean.setFlagipssvida(flagipssvida);
		bean.setFlagacctrabajo(flagacctrabajo);
		bean.setCorreointerno(correointerno);
		bean.setSueldoactuallocal(sueldoactuallocal);
		bean.setSueldoactualdolar(sueldoactualdolar);
		bean.setSueldoanteriorlocal(sueldoanteriorlocal);
		bean.setSueldoanteriordolar(sueldoanteriordolar);
		bean.setMonedapago(monedapago);
		bean.setPerfil(perfil);
		bean.setFoto(foto);
		bean.setFechaliquidacion(fechaliquidacion);
		bean.setFechareingreso(fechareingreso);
		bean.setUnidadreplicacion(unidadreplicacion);
		bean.setCodigocargo(codigocargo);
		bean.setUltimafechapagovacacion(ultimafechapagovacacion);
		bean.setEstado(estado);
		bean.setSucursal(sucursal);
		bean.setRuccentroasistenciasocial(ruccentroasistenciasocial);
		bean.setTarjetadecredito(tarjetadecredito);
		bean.setPlantillaconcepto(plantillaconcepto);
		bean.setFlagsmf(flagsmf);
		bean.setFechavacaciones(fechavacaciones);
		bean.setFlagtrabajadorpensionista(flagtrabajadorpensionista);
		bean.setFlagsctrsalud(flagsctrsalud);
		bean.setFlagsctrpension(flagsctrpension);
		bean.setFlagdiscapacidad(flagdiscapacidad);
		bean.setFlagsujetocontrol(flagsujetocontrol);
		bean.setFlagsindicalizado(flagsindicalizado);
		bean.setFlagdomiciliado(flagdomiciliado);
		bean.setNiveleducativortps(niveleducativortps);
		bean.setFlagregimenalternativo(flagregimenalternativo);
		bean.setFlagjornadamaxima(flagjornadamaxima);
		bean.setFlaghorarionocturno(flaghorarionocturno);
		bean.setFlagotrosquinta(flagotrosquinta);
		bean.setFlagquintaexonerada(flagquintaexonerada);
		bean.setSituacionespecial(situacionespecial);
		bean.setFlagmadreresponsabilidad(flagmadreresponsabilidad);
		bean.setFlagcentroformacion(flagcentroformacion);
		bean.setTipomodalidadformativa(tipomodalidadformativa);
		bean.setPrestadorservicio(prestadorservicio);
		bean.setFlagasegurapension(flagasegurapension);
		bean.setCategoriaocupacional(categoriaocupacional);
		bean.setFlagconveniodobletrib(flagconveniodobletrib);
		bean.setFirmadigitalizada(firmadigitalizada);
		bean.setFlagdeconfianza(flagdeconfianza);
		bean.setFechabajaeps(fechabajaeps);
		bean.setCodigounidad(codigounidad);
		bean.setDivision(division);
		bean.setOficina(oficina);
		bean.setResponsableempleado(responsableempleado);
		bean.setResponsablejefe(responsablejefe);
		bean.setTipocomisionafp(tipocomisionafp);
		bean.setLocacionasignada(locacionasignada);
		bean.setUltimousuario(ultimousuario);
		bean.setUltimafechamodif(ultimafechamodif);
		bean.setMotivo(motivo);
		bean.setCostcenterdestination(costcenterdestination);
		bean.setUnidadtrabajo(unidadtrabajo);
		bean.setJeferesponsable(jeferesponsable);
		bean.setOrdenorganigrama(ordenOrganigrama);
		bean.setUnidadoperativa(unidadoperativa);
		bean.setEmpleadorelacionado(empleadorelacionado);
		bean.setPosicion(posicion);
		bean.setGrupoocupacional(grupoocupacional);
		bean.setEstadonivelacion(estadonivelacion);
		bean.setAprobadornivelacion(aprobadornivelacion);
		bean.setSolicitadornivelacion(solicitadornivelacion);
		bean.setFlageducacioncompletaiep(flageducacioncompletaiep);

		bean.setAuxFlgPreparado(this.getAuxFlgPreparado());
		bean.setAuxFlgValidado(this.getAuxFlgValidado());

		return bean;
	}
}
