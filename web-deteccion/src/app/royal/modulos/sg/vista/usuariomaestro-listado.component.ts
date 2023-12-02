
import { UIListadoController } from '@framework/angular/interface/UIListadoController';

import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { TituloMantenimientoComponent } from '@framework/angular/controles/titulos/titulo-mantenimiento.component';
import { SelectItem, ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { SeguridadperfilusuarioBean } from '../dominio/dto/SeguridadperfilusuarioBean';
import { Usuario } from '../dominio/dto/Usuario';
import { FiltroPaginacionAgGePersona } from '../dominio/filtro/FiltroPaginacionAgGePersona';
import { FiltroPaginacionPerfilUsuario } from '../dominio/filtro/FiltroPaginacionPerfilUsuario';
import { FiltroPaginacionUsuario } from '../dominio/filtro/FiltroPaginacionUsuario';
import { UsuarioMaestroServicio } from '../servicio/UsuarioMaestroServicio';
import { Seguridadperfilusuario } from '../dominio/dto/Seguridadperfilusuario';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { DtoComunUsuario } from '@framework-comun/sg/dominio/dto/DtoComunUsuario';

@Component({
  selector: 'app-usuariomaestro-listado',
  templateUrl: './usuariomaestro-listado.component.html'
})
export class UsuariomaestroListadoComponent extends FormularioComponent implements OnInit, UIListadoController {


  @ViewChild(Table) dd: Table;
  pipeDate: DatePipe = new DatePipe('en-US');

  @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
  @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;
  @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

  filtro: FiltroPaginacionUsuario = new FiltroPaginacionUsuario();
  filtroPerfil: FiltroPaginacionUsuario = new FiltroPaginacionUsuario();
  filtroAgGePersona: FiltroPaginacionAgGePersona = new FiltroPaginacionAgGePersona();
  usuarioSession: string;
  fechaActual: Date;
  filtroSPerfilUsuario: FiltroPaginacionPerfilUsuario = new FiltroPaginacionPerfilUsuario();
  ultimaFechaModif: string; fechaExpiracion: string; fechaCreacion: string; ultimoIngreso: string;
  display: boolean;
  indicadorusuarioperfil: boolean = false;
  buttonaction: boolean;
  displayPerfil: boolean;
  displayPerfilPopup: boolean;
  listarSituacion: SelectItem[];
  selectSituacion: any;
  estado: string;
  tipoUsuario: string;
  accionlabel: string;
  objRegistro: Usuario;
  perfilTemp: string;
  usuarioTemp: string;
  tipoRegistro: string;
  legntext: number;

  objRegistroPerfilUs: Seguridadperfilusuario;

  modoOnlyGrupo: boolean = false;

  listarTipoRegistro: any[];
  listarstatus: any[];

  titulos: string;
  TypeFiltro: string;
  labeelbutton: string;
  tipoAccionPerfil: string;
  labeldescriptiontype: string;
  labelgrilla: string;
  disabledCheck: boolean;
  displayAgGePersona: boolean;
  stringCorrreoPersona: string;
  validaFormulario: boolean;
  checkexpiracionclave: boolean = false;
  fecha: string = '';
  lstEstados: SelectItem[] = [];

  // menuSeguridad: DtoMenu = new DtoMenu();

  constructor(
    private usuarioMaestroServicio: UsuarioMaestroServicio,
    //  private autorizacionService: AutorizacionService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private cdref: ChangeDetectorRef,
    private route: ActivatedRoute,
    servicioComun: ServicioComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  coreAnular(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEliminar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEditar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreVer(dto: any): void {
    throw new Error('Method not implemented.');
  }
  //
  coreNuevo(): void {
    this.nuevo();
  }
  coreBusquedaRapida(filtro: string): void {
  }
  coreBuscar(): void {
    this.listar({ first: 0, rows: 50 });
  }
  coreGuardar(): void {
  }
  coreExportar(tipo: string): void {
    this.seleccionarExportarTipo(tipo);
  }
  coreSalir(): void {
  }
  coreMensaje(mensage: MensajeController): void {
    console.log("mensaje: ", mensage);
    console.log(this.mensajeController)
    if (mensage.componente == 'EMPLEADO') {
      if(this.estaVacio(mensage.resultado.codigousuario)){
        return this.mostrarMensajeAdvertencia('No tiene Código Usuario')
      }

      this.objRegistro.personagroid = mensage.resultado.persona;
      this.objRegistro.nombre = mensage.resultado.busqueda;
      this.objRegistro.pk.usuario = mensage.resultado.codigousuario;
      this.objRegistro.usuariored = mensage.resultado.codigousuario;
      this.objRegistro.personanumero = mensage.resultado.persona;
      this.objRegistro.clave = '';
      this.objRegistro.fechaexpiracion = undefined;

  
      mensage.componenteDestino.bloquearPagina();
      this.bloquearPagina();
      const p1 = this.validarExisteUsuario();
      Promise.all([p1]).then(res => {
        this.desbloquearPagina();
        mensage.componenteDestino.desbloquearPagina();
      }).catch(err=> this.limpiarCampos());
    }

    if (mensage.componente == 'PROVEEDOR') {
      this.objRegistro.pk.usuario = mensage.resultado.documentofiscal;
      this.objRegistro.personagroid = mensage.resultado.persona;
      this.objRegistro.nombre = mensage.resultado.busqueda;


      this.bloquearPagina();
      const p1 = this.validarExisteUsuario();
      Promise.all([p1]).then(res => {
        this.desbloquearPagina();
      }).catch(err=> this.limpiarCampos());
    }

  }
  validarExisteUsuario(): Promise<number> {
    return this.usuarioMaestroServicio.validarUsuarioExistente(this.objRegistro).then(obj => {
      if (this.transaccionResultado(obj)) {
        //exitoso
      }
      return 1;
    });
  }
  coreFiltro(val: boolean): void {
  }
  coreAccion(accion: any): void { }
  //
  ngOnInit(): void {
    this.bloquearPagina();
    this.formularioIniciar(this.route);
    let perf: SeguridadperfilusuarioBean = new SeguridadperfilusuarioBean();
    this.fecha = this.obtenerFechaActual();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtroSPerfilUsuario.paginacion.paginacionListaResultado = [];
    this.filtroPerfil.paginacion.paginacionListaResultado = [];
    this.filtroAgGePersona.paginacion.paginacionListaResultado = [];
    this.usuarioSession = this.getUsuarioActual().usuario;

    const p1 = this.inicializarVariables();
    const p2 = this.listaSituacion();
    const p3 = this.listaTipoRegistro();
    const p4 = this.listaestado();


    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

    const p5 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO, ConstanteComunSistema.APLICACION,
      ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA, this.lstEstados);
    Promise.all([p1, p2, p3, p4, p5]).then(resp => {
      this.filtro = this.formularioFiltrosRestaurar(this.filtro);
      this.formularioOninit = false;
      this.desbloquearPagina();
      this.coreBuscar();
    });

  }

  inicializarVariables() {
    this.display = false;
    this.displayPerfil = false;
    this.objRegistro = new Usuario();
    this.tipoRegistro = "";
    this.fechaActual = new Date();
    this.objRegistroPerfilUs = new SeguridadperfilusuarioBean();
    this.stringCorrreoPersona = "";
    this.tipoAccionPerfil = 'US';
    this.filtro.usuarioPerfil = "US";
  }

  ShowModalPerfiles(dt: Table) {
    this.displayPerfilPopup = true;
    dt.reset();
  }

  BuscarPerfiles(dt: Table) {
    dt.reset();
  }

  Desbloquearusuario(dto: any, dt: Table) {

    this.confirmationService.confirm({
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaDesbloqeuar(),
      accept: () => {

        this.bloquearPagina();
        this.objRegistro.pk.usuario = dto.codigo;



        this.usuarioMaestroServicio.actualizarestadodesbloquear(this.objRegistro).then(
          obj => {

            console.log("RESPONSE :    " + obj);


            this.mostrarMensajeExito(this.getMensajeRegistroUsuarioDesbloqeuar(obj.pk.usuario));

            this.desbloquearPagina();
            this.display = false;
            dt.reset();
          }
        );


      }
    });
  };


  sumarDias(fecha, dias) {
    fecha.setDate(fecha.getDate() + dias);
    return fecha;
  }

  listaSituacion() {

    this.listarSituacion = [
      { label: 'Vigente', value: 'V' },
      { label: 'No Vigente', value: 'N' }

    ]
  }

  listaTipoRegistro() {
    this.listarTipoRegistro = [
      { code: 'US', name: 'Usuario' },
      { code: 'PV', name: 'Proveedor' }, // es PV O PR , PARA EL PROVEEDOR?
    ]
  }

  listaestado() {
    this.listarstatus = [
      { code: null, name: '-- Todos --' },
      { code: 'A', name: 'Activo' },
      { code: 'I', name: 'Inactivo' }
    ]
  }


  buscar() {
    this.dd.reset();
  }

  listar(event: LazyLoadEvent) {
    this.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.usuarioMaestroServicio.listar(this.filtro).then(pg => {
      this.filtro.paginacion = pg;
      this.desbloquearPagina();
    });
  }

  nuevo() {
    this.modoOnlyGrupo = false;

    this.display = true;
    this.objRegistro = new Usuario();
    this.estado = "";
    this.tipoUsuario = "";
    this.selectSituacion = null;
    this.ultimoIngreso = null;
    this.fechaExpiracion = null;
    this.tipoRegistro = 'NUEVO';
    this.estado = 'A';
    this.tipoUsuario = 'US';
    this.objRegistro.situacion = null;
    this.objRegistro.ultimousuario = this.usuarioSession;
    this.objRegistro.usuariocreacion = this.usuarioSession;

    this.accionlabel = 'Nuevo'
    this.buttonaction = true;

    this.indicadorusuarioperfil = false;

    this.ultimaFechaModif = this.pipeDate.transform(this.fechaActual, 'dd/MM/yyyy');
    this.fechaCreacion = this.pipeDate.transform(this.fechaActual, 'dd/MM/yyyy');
    this.objRegistro.ultimafechamodif = this.fechaActual;
    this.objRegistro.fechacreacion = this.fechaActual;
    this.objRegistro.fechaexpiracion = null;
    this.objRegistro.situacion = 'V';
    this.objRegistro.fechapassword = new Date();
    this.fechaExpiracion = null;
    this.titulos = 'Usuario';

    this.disabledCheck = true;
    this.validaFormulario = true
  }

  modificar(dto: any, tipo: any) {

    this.accionlabel = 'Modificar'
    this.tipoRegistro = tipo;
    this.bloquearPagina();
    this.modoOnlyGrupo = false;
    this.selectSituacion = null;
    this.buttonaction = false;
    this.usuarioMaestroServicio.obtenerPorId(dto.codigo).then(
      response => {
        this.display = true;

        this.objRegistro = response.usuarioBean;
        this.objRegistro.ultimousuario = !this.estaVacio(this.objRegistro.ultimousuario) ? this.objRegistro.ultimousuario.toUpperCase() : null;
        this.objRegistro.personagroid = response.usuarioBean.personagroid;
        this.objRegistro.personanumero = response.usuarioBean.personanumero;
        this.objRegistro.clave = '';
        this.objRegistro.usuarioupdate = this.objRegistro.pk.usuario;
        this.ultimoIngreso = this.pipeDate.transform(this.objRegistro.ultimologin, 'dd/MM/yyyy');
        this.ultimaFechaModif = this.pipeDate.transform(this.objRegistro.ultimafechamodif, 'dd/MM/yyyy');

        if (this.objRegistro.fechaexpiracion != null && this.objRegistro.fechaexpiracion != undefined) {
          this.fechaExpiracion = this.pipeDate.transform(this.objRegistro.fechaexpiracion, 'dd/MM/yyyy');
        } else {
          this.fechaExpiracion = null;
        }

        console.log(this.objRegistro.expirarpasswordflag);
        this.checkexpiracionclave = this.flagABoolean(this.objRegistro.expirarpasswordflag);


        this.fechaCreacion = this.pipeDate.transform(this.objRegistro.fechacreacion, 'dd/MM/yyyy');

        this.estado = this.objRegistro.estado;
        this.tipoUsuario = this.objRegistro.usuarioperfil;
        this.disabledCheck = false;
        if (this.tipoUsuario == "US") {
          this.validaFormulario = true;
          this.indicadorusuarioperfil = false;

        } else if (this.tipoUsuario == "PV") {
          this.modoOnlyGrupo = false;
          this.validaFormulario = true;
          this.indicadorusuarioperfil = false;
        } else {
          this.validaFormulario = false;
          this.indicadorusuarioperfil = true;
        }

        this.desbloquearPagina();

      }
    );
  }

  cargarUsuarios(dto: any) {
    console.log(dto);

    this.objRegistro.pk.usuario = dto.usuario;
    this.objRegistro.nombre = dto.nombre;
  }

  registar(dt: Table) {
    if (this.indicadorusuarioperfil == false) {
      this.eventoGuardarUsuario(dt);
    } else {

      if (this.indicadorusuarioperfil) {

        if (this.objRegistro.nombre == null || this.objRegistro.nombre == undefined || this.objRegistro.nombre == '') {

          this.mostrarMensajeAdvertencia("Ingrese nombre perfil.!")
        } else if (this.objRegistro.pk.usuario == null || this.objRegistro.pk.usuario == undefined || this.objRegistro.pk.usuario == '') {


          this.mostrarMensajeAdvertencia("Ingrese Perfil.!")

        } else {

          this.eventoGuardaPerfil(dt);
        }
        // 
      }

    }




  }


  eventoGuardaPerfil(dt: Table) {
    this.bloquearPagina();
    if (this.validarFormulario()) {
      this.objRegistro.estado = this.estado;
      this.objRegistro.usuarioperfil = this.tipoUsuario;
      this.objRegistro.situacion = this.objRegistro.situacion;


      // if (this.directorioLogin) {
      //   this.objRegistro.forcelogonspringflag = 'S';
      // } else {
      this.objRegistro.forcelogonspringflag = 'N';
      // }


      if (this.tipoRegistro == 'NUEVO') {
        this.objRegistro.expirarpasswordflag = this.booleanAFlag(this.checkexpiracionclave);
        this.usuarioMaestroServicio.registrar(this.objRegistro).then(
          obj => {
            this.desbloquearPagina();
            if (this.transaccionResultado(obj)) {
              // this.salir();
              this.mostrarMensajeExito(this.getMensajeRegistroUsuarioPerfil(obj.pk.usuario, 'Perfil'));
              dt.reset();
              this.desbloquearPagina();
              this.display = false;
            }
          }
        );
      }
      if (this.tipoRegistro == 'MODIFICAR') {
        this.objRegistro.ultimousuario = this.usuarioSession;
        this.objRegistro.ultimafechamodif = this.fechaActual;
        this.objRegistro.situacion = this.objRegistro.situacion;
        // this.objRegistro.usuariored = this.objRegistro.usuarioupdate;

        this.objRegistro.expirarpasswordflag = this.booleanAFlag(this.checkexpiracionclave);

        var dtoUsuario = new DtoComunUsuario();
        dtoUsuario = this.beanToDto(this.objRegistro);


        this.usuarioMaestroServicio.actualizar(dtoUsuario).then(
          obj => {
            this.mostrarMensajeExito(this.getMensajeActualizarUsuario(obj.pk.usuario));
            // this.salir();
            dt.reset();
            this.desbloquearPagina();
            this.display = false;
          }
        );
      }


    }
    if (this.display == false) {
      this.filtro.paginacion.paginacionRegistroInicio = 0;
      this.filtro.paginacion.paginacionRegistrosPorPagina = 20;
      this.filtro.usuarioPerfil = "US";
      this.usuarioMaestroServicio.listar(this.filtro).then(pg => {
        this.filtro.paginacion = pg;
        this.desbloquearPagina();
      });
      this.buscar();
    }

  }

  eventoGuardarUsuario(dt: Table) {
    const pattern = new RegExp('^[a-zA-Z0-9]+$');

    const patternclave = new RegExp('^[a-zA-Z0-9]+$');
    const paternumeroletra = new RegExp('^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$');

    if (this.objRegistro.clave != null) {
      this.legntext = this.objRegistro.clave.length;
    }

    if (this.estaVacio(this.objRegistro.usuariored)) {
      this.mostrarMensajeAdvertencia("Debe ingresar el usuario de Red");
      return;
    }

    if (this.estaVacio(this.objRegistro.nombre)) {
      this.mostrarMensajeAdvertencia("Seleccione una persona!")
    } else if (this.estaVacio(this.objRegistro.pk.usuario)) {
      this.mostrarMensajeAdvertencia("Ingrese un usuario!")

    } else if (this.estaVacio(this.objRegistro.clave) && this.tipoRegistro != 'MODIFICAR') {
      this.mostrarMensajeAdvertencia("Ingrese su contraseña!")

    } else {

      if (!this.estaVacio(this.objRegistro.clave)) {

        if (!this.estaVacio(this.objRegistro.clave) && !patternclave.test(this.objRegistro.clave)) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe ser alfanumérica.!")
          return;
        } else if (this.legntext <= 5) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 6 dígitos como mínimo!")
          return;
        } else if (this.legntext >= 11) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 10 dígitos como máximo!")
          return;
        } else if (this.objRegistro.clave.search(/\d/) == -1) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 número como mínimo!")
          return;
        } else if (this.objRegistro.clave.search(/[a-zA-Z]/) == -1) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 letra como mínimo!")
          return;
        }
      }

      this.bloquearPagina();
      if (this.validarFormulario()) {
        this.objRegistro.estado = this.estado;
        this.objRegistro.usuarioperfil = this.tipoUsuario;
        this.objRegistro.situacion = this.objRegistro.situacion;


        // if (this.directorioLogin) {
        //   this.objRegistro.forcelogonspringflag = 'S';
        // } else {
        this.objRegistro.forcelogonspringflag = 'N';
        // }
        if (this.tipoRegistro == 'NUEVO') {
          this.objRegistro.ultimologin = new Date();

          this.objRegistro.fechaexpiracion = new Date(this.fechaExpiracion);

          if (!this.estaVacio(this.fechaExpiracion)) {
            this.objRegistro.fechaexpiracion = new Date(this.fechaExpiracion);
          } else {
            this.objRegistro.fechaexpiracion = null;
          }

          this.objRegistro.expirarpasswordflag = this.booleanAFlag(this.checkexpiracionclave);

          this.usuarioMaestroServicio.registrar(this.objRegistro).then(
            obj => {
              this.desbloquearPagina();
              if (this.transaccionResultado(obj)) {
                this.mostrarMensajeExito(this.getMensajeRegistroUsuarioPerfil(obj.pk.usuario, 'Usuario'));
                dt.reset();
                this.desbloquearPagina();
                this.display = false;
              }
            }
          );
        }
        if (this.tipoRegistro == 'MODIFICAR') {
          this.objRegistro.ultimousuario = this.usuarioSession;
          this.objRegistro.ultimafechamodif = this.fechaActual;
          this.objRegistro.situacion = this.objRegistro.situacion;
          // this.objRegistro.usuariored = this.objRegistro.usuarioupdate;

          if (!this.estaVacio(this.fechaExpiracion)) {
            this.objRegistro.fechaexpiracion = new Date(this.fechaExpiracion);
          } else {
            this.objRegistro.fechaexpiracion = null;
          }

          var dtoUsuario = new DtoComunUsuario();

          this.objRegistro.expirarpasswordflag = this.booleanAFlag(this.checkexpiracionclave);

          dtoUsuario = this.beanToDto(this.objRegistro);


          this.usuarioMaestroServicio.actualizar(dtoUsuario).then(
            obj => {
              this.desbloquearPagina();
              if (this.transaccionResultado(obj)) {
                this.mostrarMensajeExito(this.getMensajeActualizarUsuario(obj.pk.usuario));
                dt.reset();
                this.desbloquearPagina();
                this.display = false;
              }
            }
          );
        }


      }
      if (this.display == false) {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = 20;
        this.filtro.usuarioPerfil = "US";
        this.usuarioMaestroServicio.listar(this.filtro).then(pg => {
          this.filtro.paginacion = pg;
          this.desbloquearPagina();
        });
        this.buscar();
      }


    }
  }

  generarClave() {
    if (this.estaVacio(this.stringCorrreoPersona)) {
      this.mostrarMensajeExito(this.getMensajeNoExisteCorreoPersona());
      return;
    }
    if (this.esCorreoValido(this.stringCorrreoPersona)) {
      this.mostrarMensajeExito(this.getMensajeInvalidoCorreoPersona());
      return;
    }

  }

  validarFormulario() {
    return true;
  }

  anio01: string = '';
  obtenerFechaActual() {
    var hoy = new Date();

    var diaActual = hoy.getDate().toString();
    var mesActual = (hoy.getMonth() + 1).toString();
    var anioActual = hoy.getFullYear().toString();
    var segActual = hoy.getSeconds().toString();

    if (diaActual.length === 1) {
      diaActual = '0' + diaActual;
    }

    if (mesActual.length === 1) {
      mesActual = '0' + mesActual;
    }

    return diaActual + '/' + mesActual + '/' + anioActual;
  }


  validaUsuario(tipo: any) {
    this.modoOnlyGrupo = true;
    if (this.buttonaction == true) {
      if (tipo == "PE") {
        this.tipoAccionPerfil = 'PE';
        this.objRegistro.clave = '';
        this.titulos = 'Perfil'
        this.objRegistro.situacion = 'V';
        this.validaFormulario = false;

        this.objRegistro.nombre = "";
        this.objRegistro.pk.usuario = "";
        this.objRegistro.personagroid = null;
        this.indicadorusuarioperfil = true;
      } else if (tipo == "US") {
        this.tipoAccionPerfil = 'US';
        this.titulos = 'Usuario'
        this.objRegistro.situacion = null;
        this.validaFormulario = true;
        this.indicadorusuarioperfil = false;
        this.objRegistro.nombre = "";
        this.objRegistro.pk.usuario = "";
        this.objRegistro.clave = '';
        this.objRegistro.personagroid = null;
        this.modoOnlyGrupo = false;
      } else {
        this.modoOnlyGrupo = false;
        this.tipoAccionPerfil = 'PV'; //CAMBIE DE PV A PR -- PREGUNTAR CUAL USARAN
        this.titulos = 'Proveedor'
        this.objRegistro.situacion = null;
        this.validaFormulario = true;
        this.indicadorusuarioperfil = false;
        this.objRegistro.nombre = "";
        this.objRegistro.pk.usuario = "";
        this.objRegistro.clave = '';
        this.objRegistro.personagroid = null;
      }
    }


  }


  cancelar() {
    this.display = false;
    this.displayPerfil = false;
  }

  adicionarPerfiles(dto: any) {

    console.log(dto);

    if (dto.tipoRegistro == 'Perfil') {
      this.labeldescriptiontype = 'Usuarios';

      this.labeelbutton = 'Usuario';
      this.TypeFiltro = "US"
      this.bloquearPagina();
      this.perfilTemp = dto.codigo;
      this.usuarioTemp = dto.nombre;
      this.filtroSPerfilUsuario.usuario = dto.codigo;
      this.objRegistroPerfilUs.pk.usuario = dto.codigo;
      this.filtroSPerfilUsuario.labeelbutton = this.labeelbutton;
      this.usuarioMaestroServicio.listarUsuarioByPerfil(this.filtroSPerfilUsuario).then(pg => {
        this.filtroSPerfilUsuario.paginacion = pg;
        this.desbloquearPagina();
      });
      this.displayPerfil = true;

    } else if (dto.tipoRegistro == 'Usuario') {
      this.labeldescriptiontype = 'Perfiles';
      this.labeelbutton = 'Perfil';

      this.TypeFiltro = "PE"
      this.bloquearPagina();
      this.perfilTemp = dto.codigo;
      this.usuarioTemp = dto.nombre;
      this.filtroSPerfilUsuario.usuario = dto.codigo;
      this.objRegistroPerfilUs.pk.usuario = dto.codigo;
      this.filtroSPerfilUsuario.labeelbutton = this.labeelbutton;
      this.usuarioMaestroServicio.listarSeguridadPerfilUsuario(this.filtroSPerfilUsuario).then(pg => {
        this.filtroSPerfilUsuario.paginacion = pg;
        this.desbloquearPagina();
      });
      this.displayPerfil = true;
    }


  }

  listarPerfilUsuario(event: LazyLoadEvent) {
    this.bloquearPagina();
    this.filtroSPerfilUsuario.paginacion.paginacionListaResultado = [];
    this.filtroSPerfilUsuario.paginacion.paginacionRegistroInicio = event.first;
    this.filtroSPerfilUsuario.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.filtroSPerfilUsuario.labeelbutton = this.labeelbutton;
    this.usuarioMaestroServicio.listarSeguridadPerfilUsuario(this.filtroSPerfilUsuario).then(pg => {
      this.filtroSPerfilUsuario.paginacion = pg;
      this.desbloquearPagina();
    });
  }

  listarPerfiles(event: LazyLoadEvent) {

    console.log("TIPOOO : " + this.TypeFiltro);

    this.bloquearPagina();
    this.filtroPerfil.paginacion.paginacionListaResultado = [];
    this.filtroPerfil.paginacion.paginacionRegistroInicio = event.first;
    this.filtroPerfil.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.filtroPerfil.usuarioPerfil = this.TypeFiltro;
    this.filtroPerfil.nombre = this.filtroPerfil.nombre;
    this.filtroPerfil.labeelbutton = this.labeelbutton;
    this.filtroPerfil.estado = 'A';
    this.usuarioMaestroServicio.listar(this.filtroPerfil).then(pg => {
      this.filtroPerfil.paginacion = pg;
      this.desbloquearPagina();
    });
  }




  registrarPerfil(dto: any, dt: Table) {

    this.bloquearPagina();
    this.objRegistroPerfilUs.pk.perfil = dto.codigo;
    this.objRegistroPerfilUs.estado = 'A';
    this.objRegistroPerfilUs.ultimousuario = this.usuarioSession;
    this.objRegistroPerfilUs.ultimafechamodif = this.fechaActual;
    this.objRegistroPerfilUs.labeelbutton = this.labeelbutton;

    this.usuarioMaestroServicio.registrarPerfil(this.objRegistroPerfilUs).then(
      obj => {


        console.log("RESPONSE :    " + obj);
        if (obj)
          this.desbloquearPagina();
        this.mostrarMensajeExito(this.getMensajeGrabadoSinCodigo());


        dt.reset();

        // this.salir();
        this.displayPerfilPopup = false;
      }
    );



  }


  generarclave(dto: any, tipo: any, dt: Table) {
    console.log(dto);


    if (dto.tipoRegistro == "Perfil") {

      this.mostrarMensajeAdvertencia("No se puede generar una clave a un perfil.!!")
    } else {

      this.confirmationService.confirm({
        message: 'Desea generar una clave para este usuario?',
        accept: () => {


          this.bloquearPagina();
          this.objRegistro.ultimousuario = this.usuarioSession;
          this.objRegistro.ultimafechamodif = this.fechaActual;
          this.objRegistro.situacion = this.objRegistro.situacion;
          this.objRegistro.pk.usuario = dto.codigo;



          this.usuarioMaestroServicio.generarclave(this.objRegistro).then(
            obj => {


              /* if (obj.status == 0) {
                this.mostrarMensajeError(obj.mensajeuser);
                this.desbloquearPagina();
              } else if (obj.status == 1) {
                this.mostrarMensajeError(obj.mensajeuser);
                this.desbloquearPagina();
              } else if (obj.status == 2) {
                this.mostrarMensajeError(obj.mensajeuser);
                this.desbloquearPagina();
              } else if (obj.status == 200) {
                this.mostrarMensajeExito(obj.mensajeuser);
                this.desbloquearPagina();
                this.display = false;
                dt.reset();
              } */
              this.desbloquearPagina();
              if (this.transaccionResultado(obj)) {
                dt.reset();
              }
            }
          );


        }
      });

    }



  }


  eliminar(dto: any, tipo: any, dt: Table) {


    this.confirmationService.confirm({
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaInactivar(),
      accept: () => {

        this.bloquearPagina();
        this.objRegistro.ultimousuario = this.usuarioSession;
        this.objRegistro.ultimafechamodif = this.fechaActual;
        this.objRegistro.situacion = this.objRegistro.situacion;
        this.objRegistro.pk.usuario = dto.codigo;
        this.objRegistro.estado = 'I';


        this.usuarioMaestroServicio.actualizarestado(this.objRegistro).then(
          obj => {

            console.log("RESPONSE :    " + obj);


            this.mostrarMensajeExito(this.getMensajeActualizarUsuario(obj.pk.usuario));

            this.desbloquearPagina();
            this.display = false;
            dt.reset();
          }
        );


      }
    });
  }






  desactivarPerfil(dto: any, dt: Table) {


    if (this.labeelbutton == 'Perfil') {

      this.bloquearPagina();
      this.usuarioMaestroServicio.eliminarpefilUsuario(dto.perfil, this.perfilTemp).then(
        obj => {
          this.mostrarMensajeExito(this.getMensajeEliminarUsuario());
          this.desbloquearPagina();
          dt.reset();
        }
      );
    } else if (this.labeelbutton == 'Usuario') {
      this.bloquearPagina();
      this.usuarioMaestroServicio.eliminarpefil(dto.perfil, this.perfilTemp).then(
        obj => {
          this.mostrarMensajeExito(this.getMensajeEliminarUsuario());
          this.desbloquearPagina();
          dt.reset();
        }
      );
    }



  }

  obtenerUsuario() {
    // this.clienteConponent.iniciarComponente('PERS', null);
  }


  listarAgGePersona(event: LazyLoadEvent) {
    this.filtroAgGePersona.paginacion.paginacionListaResultado = [];
    this.filtroAgGePersona.paginacion.paginacionRegistroInicio = event.first;
    this.filtroAgGePersona.paginacion.paginacionRegistrosPorPagina = event.rows;
    console.log(this.filtroAgGePersona)
    /*COMENTADO LEONARDO
    this.usuarioMaestroServicio.listarAgGePersona(this.filtroAgGePersona).then(pg => {
      this.filtroAgGePersona.paginacion = pg;
      this.desbloquearPagina();
    });*/
  }

  buscarAgGePersona() {
    console.log(this.filtroAgGePersona)
    /*COMENTADO LEONARDO
    this.usuarioMaestroServicio.listarAgGePersona(this.filtroAgGePersona).then(pg => {
      this.filtroAgGePersona.paginacion = pg;
      this.desbloquearPagina();
    });*/
  }

  obtenerAgGePersona(dto: any) {
    console.log(dto)
    this.stringCorrreoPersona = dto.correo;
    this.objRegistro.nombre = dto.nombre;
    this.displayAgGePersona = false
    // this.objRegistro.personaAgroId= dto.codigo;
  }
  seleccionarCliente(dto: any) {
    console.log(dto)

    //this.objRegistro.pk.usuario = dto.usuario;

    this.objRegistro.personagroid = dto.personaAgroId;
    this.objRegistro.nombre = dto.busqueda;

  }



  limpiarCampos() {
    this.objRegistro.pk.usuario = '';
    this.objRegistro.nombre = '';
    this.objRegistro.personagroid = 0;
    this.objRegistro.personanumero = null;
  }


  exportarDatos() {
    // this.tipoExportarSelectorComponent.iniciarComponente();
  }

  seleccionarExportarTipo(tipo: any) {
    this.filtro.usuarioPerfil = "US";
    this.filtro.tipoexportar = tipo;
    this.exportar();
  }

  exportar() {
    if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();
    this.usuarioMaestroServicio.exportar(this.filtro)
      .subscribe(
        res => {
          this.desbloquearPagina();
          const link = window.URL.createObjectURL(res);
          const a = document.createElement('a');
          const type = res.type.split('/');
          document.body.appendChild(a);
          a.setAttribute('style', 'display: none');
          a.href = link;
          if (type[1] === 'vnd.ms-excel') {
            a.download = 'Listado-Usuarios-' + this.fecha + '.xls';
          } else {
            a.download = 'Listado-Usuarios-' + this.fecha + '.' + type[1];
          }

          a.click();
          this.fecha = this.obtenerFechaActual();
          window.URL.revokeObjectURL(link);
          a.remove();
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }

  onclickCheckexperiacionclave() {
    console.log(this.objRegistro.fechaexpiracion);

    if (this.checkexpiracionclave) {
      this.objRegistro.fechaexpiracion = this.sumarDias(new Date(), 14);
      this.fechaExpiracion = this.pipeDate.transform(this.objRegistro.fechaexpiracion, 'dd/MM/yyyy')
    } else {
      this.fechaExpiracion = null;
      this.objRegistro.fechaexpiracion = null;
    }
  }


  beanToDto(bean: Usuario) {

    var dto = new DtoComunUsuario();

    dto.usuario = bean.pk.usuario;
    dto.usuarioperfil = bean.usuarioperfil;
    dto.nombre = bean.nombre;
    dto.clave = bean.clave;
    dto.expirarpasswordflag = bean.expirarpasswordflag;
    dto.fechaexpiracion = bean.fechaexpiracion;
    dto.ultimologin = bean.ultimologin;
    dto.numeroLoginDisponible = bean.numeroLoginDisponible;
    dto.numeroLoginUsados = bean.numeroLoginUsados;
    dto.sqllogin = bean.sqllogin;
    dto.sqlpassword = bean.sqlpassword;
    dto.estado = bean.estado;
    dto.ultimousuario = bean.ultimousuario;
    dto.ultimafechamodif = bean.ultimafechamodif;
    dto.usuariored = bean.usuariored;
    dto.horainicio = bean.horainicio;
    dto.horafin = bean.horafin;
    dto.horainicioap = bean.horainicioap;
    dto.horafinap = bean.horafinap;
    dto.fechacreacion = bean.fechacreacion;
    dto.usuariocreacion = bean.usuariocreacion;
    dto.forcelogonspringflag = bean.forcelogonspringflag;
    dto.fechapassword = bean.fechapassword;
    dto.situacion = bean.situacion;
    dto.usuarioupdate = bean.usuarioupdate;
    dto.personagroid = bean.personagroid;
    dto.personanumero = bean.personanumero;
    dto.status = bean.status;
    dto.mensajeuser = bean.mensajeuser;
    dto.transaccionEstado = bean.transaccionEstado;
    dto.transaccionListaMensajes = bean.transaccionListaMensajes;

    return dto;


  }



}
