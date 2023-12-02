import { FiltroPaginacionPerfilUsuario } from './../dominio/filtro/FiltroPaginacionPerfilUsuario';
import { FiltroPaginacionUsuario } from './../dominio/filtro/FiltroPaginacionUsuario';
import { SeguridadperfilusuarioBean } from './../dominio/dto/SeguridadperfilusuarioBean';
import { FiltroPaginacionAgGePersona } from './../dominio/filtro/FiltroPaginacionAgGePersona';
import { Usuario } from './../dominio/dto/Usuario';
import { UsuarioMaestroServicio } from './../servicio/UsuarioMaestroServicio';
import { ConfirmationService, LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { Seguridadperfilusuario } from '../dominio/dto/Seguridadperfilusuario';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { TituloListadoComponent } from '@framework/angular/controles/titulos/titulo-listado.component';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';

@Component({
  selector: 'app-maestroperfil',
  templateUrl: './maestroperfil.component.html'
})
export class MaestroperfilComponent extends FormularioComponent implements OnInit, UIListadoController {
  @ViewChild(Table) dd: Table;
  pipeDate: DatePipe = new DatePipe('en-US');
  /*   @ViewChild(UsuarioSelectorComponent, { static: false }) usuarioselector: UsuarioSelectorComponent;
    @ViewChild(AgroPersonaComunSelectorComponent, { static: false }) clienteConponent: AgroPersonaComunSelectorComponent; */
  /*   @ViewChild(TipoExportarSelectorComponent, { static: false }) tipoExportarSelectorComponent: TipoExportarSelectorComponent; */

  /*     @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
      @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;
      @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent; */
  @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
  @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;

  filtro: FiltroPaginacionUsuario = new FiltroPaginacionUsuario();
  filtroPerfil: FiltroPaginacionUsuario = new FiltroPaginacionUsuario();
  filtroAgGePersona: FiltroPaginacionAgGePersona = new FiltroPaginacionAgGePersona();
  usuarioSession: string;
  fechaActual: Date;
  filtroSPerfilUsuario: FiltroPaginacionPerfilUsuario = new FiltroPaginacionPerfilUsuario();
  filtroSPerfilUsuario2: FiltroPaginacionPerfilUsuario = new FiltroPaginacionPerfilUsuario();
  ultimaFechaModif: string; fechaExpiracion: string; fechaCreacion: string; ultimoIngreso: string; ultimousuario: string;

  display: boolean;
  indicadorusuarioperfil: boolean = false;
  buttonaction: boolean;
  displayPerfil: boolean;
  displayPerfilPopup: boolean;
  displayPerfilPopupSelector: boolean;
  displayPerfilSelector: boolean;
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

  reporteusuariospefils: number = 0;

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

  fecha: string = '';

  //  menuSeguridad: DtoMenu = new DtoMenu();
  lstEstados: SelectItem[] = [];

  constructor(
    private usuarioMaestroServicio: UsuarioMaestroServicio,
    //  private autorizacionService: AutorizacionService,
    private confirmationService: ConfirmationService,
    private router: Router,
    private cdref: ChangeDetectorRef,
    private route: ActivatedRoute,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService,
    servicioComun: ServicioComunService) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }


  coreAnular(dto: any): void { }
  coreEliminar(dto: any): void { }
  coreEditar(dto: any): void { }
  coreVer(dto: any): void { }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  //
  coreNuevo(): void {
    this.nuevo();
  }
  coreBusquedaRapida(filtro: string): void {
  }
  coreBuscar(): void {
    console.log("core buscar ---")
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
  }
  coreFiltro(val: boolean): void {
  }
  coreAccion(accion: any): void { }
  //

  ngOnInit(): void {
    this.bloquearPagina();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);


    /* this.objetoTitulo.listaOtros = [
      {
        label: 'Usuarios Por Perfil', icon: 'fa fa-filter', command: () => {
          this.showfilterperfil_sindttable();
        }
      }

    ];
    if (this.route.snapshot.params['menu'] != null && this.route.snapshot.params['menu'] != undefined) {
      this.menuSeguridad = JSON.parse(this.route.snapshot.params['menu']) as DtoMenu;
      sessionStorage.setItem('menu-seguridad', JSON.stringify(this.menuSeguridad));
    } else {
      const ss = sessionStorage.getItem('menu-seguridad');
      if (ss !== undefined && ss != null) {
        const seguridad = JSON.parse(ss) as DtoMenu;
        this.menuSeguridad = seguridad;
      }
    } */
    console.log("ENTRO A LA WEB MIGRADAAAA");
    this.fecha = this.obtenerFechaActual();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtroSPerfilUsuario.paginacion.paginacionListaResultado = [];
    this.filtroPerfil.paginacion.paginacionListaResultado = [];
    this.filtroAgGePersona.paginacion.paginacionListaResultado = [];
    /*  this.autorizacionService.obtenerUsuarioActual().then(obj => {
       this.usuarioSession =  obj.usuarioLogin;
     }); */
    //cambiando : 
    this.usuarioSession = this.getUsuarioActual().usuario;

    // this.autorizacionService.obtenerParametro().then(obj => {
    //   // this.usuarioSession = obj.usuarioLogin;
    // });
    this.inicializarVariables();
    this.listaSituacion();
    this.listaTipoRegistro();
    this.listaestado();

    const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO,
      ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO,
      ConstanteComunSistema.DEFECTO_COMPANIA, this.lstEstados);
    Promise.all([p1]).then(resp => {

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

    // let p = this.sumarDias(this.fechaActual, 14);
    // // console.log(p)
    // // console.log(new Date())


  }

  selectperfil(dto: any) {

    console.log(dto);

    this.perfilTemp = dto.codigo;
    this.usuarioTemp = dto.nombre;

    this.displayPerfilPopupSelector = false;

    this.filtroSPerfilUsuario.usuario = dto.codigo;
  }

  ShowModalPerfiles(dt: Table) {
    this.displayPerfilPopup = true;
    dt.reset();
  }

  ShowModalPerfilesSele(dt: Table) {
    this.filtroSPerfilUsuario.usuario = null;
    this.displayPerfilPopupSelector = true;
    dt.reset();
  }



  BuscarPerfiles(dt: Table) {
    dt.reset();

  }


  ExportarUsuarios(dto: any, dt: Table) {


    this.reporteusuariospefils = 1;
    this.filtroSPerfilUsuario.usuario = dto.usuario;
    /*    this.tipoExportarSelectorComponent.iniciarComponente(); */

  }


  ExportarPefilUsuario(number: number) {

    this.filtroSPerfilUsuario2.usuario = this.perfilTemp;

    if (number == 1) {

      this.reporteusuariospefils = 2;
    }


    /*     this.tipoExportarSelectorComponent.iniciarComponente(); */

  }


  limpiarper() {
    this.perfilTemp = '';
    this.usuarioTemp = '';
    this.filtroSPerfilUsuario.usuario = null;
  }
  showfilterperfil_sindttable() {
    this.perfilTemp = '';
    this.usuarioTemp = '';


    this.labeldescriptiontype = 'Perfiles';

    this.labeelbutton = 'Usuario';
    this.TypeFiltro = "PE";
    this.listarPerfilUsuario2({ first: 0, rows: 50 });
    this.displayPerfilSelector = true;
  }
  showfilterperfil(dt: Table) {

    this.perfilTemp = '';
    this.usuarioTemp = '';


    this.labeldescriptiontype = 'Perfiles';

    this.labeelbutton = 'Usuario';
    this.TypeFiltro = "PE"

    dt.reset();




    this.displayPerfilSelector = true;
  }


  buscarperfiles(dt: Table) {

    this.filtroSPerfilUsuario2.usuario = this.perfilTemp;
    dt.reset();
  }

  buscarusuarios(dt: Table) {

    this.bloquearPagina()
    this.filtroSPerfilUsuario.labeelbutton = 'Usuario';

    this.usuarioMaestroServicio.listarUsuarioByPerfil(this.filtroSPerfilUsuario).then(pg => {
      this.filtroSPerfilUsuario.paginacion = pg;
      this.desbloquearPagina();
    });

  }

  Desbloquearusuario(dto: any, dt: Table) {

    if (dto.tipoRegistro == "Perfil") {

      this.mostrarMensajeAdvertencia("No se puede desbloquear un perfil.!!")

    } else if (dto.situacioncod == "B") {

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

    } else if (dto.situacioncod == "V") {

      this.mostrarMensajeAdvertencia("El Usuario : " + dto.codigo + " se encuentra Vigente ")

    } else if (dto.situacioncod == "N") {

      this.mostrarMensajeAdvertencia("El Usuario : " + dto.codigo + " No está Vigente ")


    }



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
      // { code : null, name : '-- Todos --'},
      // { code : 'US', name : 'Usuario'},
      { code: 'PE', name: 'Perfil' }
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
    // this.filtro.fecha1 = moment(this.filtro.fecha1).format('DD/MM/YY');
    // this.filtro.fecha2 = moment(this.filtro.fecha2).format('DD/MM/YY');
    this.dd.reset();
  }

  listar(event: LazyLoadEvent) {
    this.bloquearPagina();
    this.filtro.usuarioPerfil = "PE";
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.usuarioMaestroServicio.listar(this.filtro).then(pg => {
      this.filtro.paginacion = pg;
      this.desbloquearPagina();
    });
  }

  nuevo() {

    this.modoOnlyGrupo = true;

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
    this.objRegistro.situacion = 'V';
    this.objRegistro.ultimousuario = this.usuarioSession;
    this.objRegistro.usuariocreacion = this.usuarioSession;

    this.accionlabel = 'Nuevo'
    this.buttonaction = true;

    this.indicadorusuarioperfil = false;

    //   this.selectSituacion = this.listarSituacion.find(x => x.situacion = 'V');

    // console.log( this.sumarDias(new Date(), 1))
    // console.log(this.objRegistro.fechaexpiracion)

    // this.ultimoIngreso = this.pipeDate.transform(fechaActual, 'dd-MM-yyyy HH:mm');
    this.ultimaFechaModif = this.pipeDate.transform(this.fechaActual, 'dd/MM/yyyy HH:mm');
    // this.fechaExpiracion = this.pipeDate.transform(fechaActual, 'dd-MM-yyyy');
    this.fechaCreacion = this.pipeDate.transform(this.fechaActual, 'dd/MM/yyyy HH:mm');
    this.ultimousuario = this.getUsuarioActual().usuario;
    // this.objRegistro.ultimologin = fechaActual;
    this.objRegistro.ultimafechamodif = this.fechaActual;
    // this.objRegistro.fechaexpiracion = fechaActual;
    this.objRegistro.fechacreacion = this.fechaActual;
    //this.objRegistro.fechaexpiracion = this.sumarDias(new Date(), 14);
    this.fechaExpiracion = this.pipeDate.transform(this.objRegistro.fechaexpiracion, 'dd/MM/yyyy');
    this.titulos = 'Usuario';
    this.disabledCheck = true;
    this.validaFormulario = true


    this.validaUsuario("PE");


  }

  modificar(dto: any, tipo: any) {

    this.accionlabel = 'Modificar'
    this.tipoRegistro = tipo;
    this.bloquearPagina();
    this.titulos = "Perfil";
    this.modoOnlyGrupo = false;
    this.selectSituacion = null;
    this.buttonaction = false;
    this.usuarioMaestroServicio.obtenerPorId(dto.codigo).then(
      response => {
        this.display = true;

        this.objRegistro = { ...response.usuarioBean };

        this.objRegistro.clave = response.clave;
        this.objRegistro.usuarioupdate = this.objRegistro.pk.usuario;
        this.ultimoIngreso = this.pipeDate.transform(this.objRegistro.ultimologin, 'dd/MM/yyyy HH:mm');
        this.ultimaFechaModif = this.pipeDate.transform(this.objRegistro.ultimafechamodif, 'dd/MM/yyyy HH:mm');
        this.fechaExpiracion = this.pipeDate.transform(this.objRegistro.fechaexpiracion, 'dd/MM/yyyy');
        this.fechaCreacion = this.pipeDate.transform(this.objRegistro.fechacreacion, 'dd/MM/yyyy HH:mm');
        this.ultimousuario = this.objRegistro.ultimousuario;

        console.log(this.objRegistro)
        //  this.listarSituacion = this.objRegistro.situacion;


        this.estado = this.objRegistro.estado;
        this.tipoUsuario = this.objRegistro.usuarioperfil;
        this.disabledCheck = false;

        if (this.tipoUsuario == "US") {
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
      console.log('COMO LLEGA this.objRegistro    ', this.objRegistro)
      this.objRegistro.estado = this.estado;
      this.objRegistro.usuarioperfil = this.tipoUsuario;
      this.objRegistro.situacion = this.objRegistro.situacion;


      // if (this.directorioLogin) {
      //   this.objRegistro.forcelogonspringflag = 'S';
      // } else {
      this.objRegistro.forcelogonspringflag = 'N';
      // }
      if (this.tipoRegistro == 'NUEVO') {
        this.usuarioMaestroServicio.registrar(this.objRegistro).then(
          obj => {
            this.mostrarMensajeExito(this.getMensajeRegistroUsuarioPerfil(obj.pk.usuario, 'Perfil'));
            // this.salir();
            dt.reset();
            this.desbloquearPagina();
            this.display = false;
          }
        );
      }
      if (this.tipoRegistro == 'MODIFICAR') {
        this.objRegistro.ultimousuario = this.usuarioSession;
        this.objRegistro.ultimafechamodif = this.fechaActual;
        this.objRegistro.situacion = this.objRegistro.situacion;
        this.objRegistro.usuariored = this.objRegistro.usuarioupdate;
        this.objRegistro.usuario = this.objRegistro.pk.usuario;


        this.usuarioMaestroServicio.actualizarPerfil(this.objRegistro).then(
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


    const pattern = new RegExp('^[a-zA-Z0-9 ]+$');

    const patternclave = new RegExp('^[a-zA-Z0-9 ]+$');
    const paternumeroletra = new RegExp('^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$');


    if (this.objRegistro.clave != null) {

      this.legntext = this.objRegistro.clave.length;

    }



    if (this.objRegistro.nombre == null || this.objRegistro.nombre == undefined || this.objRegistro.nombre == '') {

      this.mostrarMensajeAdvertencia("Seleccione una persona.!")

    } else if (this.objRegistro.pk.usuario == null || this.objRegistro.pk.usuario == undefined || this.objRegistro.pk.usuario == '') {
      this.mostrarMensajeAdvertencia("Ingrese un usuario.!")

    } else if (this.objRegistro.clave == null || this.objRegistro.clave == undefined || this.objRegistro.clave == '') {
      this.mostrarMensajeAdvertencia("Ingrese su contraseña!")

    } else {

      if (!patternclave.test(this.objRegistro.clave)) {
        this.mostrarMensajeAdvertencia("La clave ingresada debe ser alfanumérica.!")

      } else {

        if (this.objRegistro.clave == null || this.objRegistro.clave == undefined || this.objRegistro.clave == '') {
          this.mostrarMensajeAdvertencia("Ingrese su clave!")
        } else if (this.legntext <= 5) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 6 dígitos como mínimo!")
        } else if (this.legntext >= 11) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 10 dígitos como máximo!")
        } else if (this.objRegistro.clave.search(/\d/) == -1) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 número como mínimo!")
        } else if (this.objRegistro.clave.search(/[a-zA-Z]/) == -1) {
          this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 letra como mínimo!")
        } else {


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
              this.usuarioMaestroServicio.registrar(this.objRegistro).then(
                obj => {
                  this.mostrarMensajeExito(this.getMensajeRegistroUsuarioPerfil(obj.pk.usuario, 'Usuario'));
                  // this.salir();
                  dt.reset();
                  this.desbloquearPagina();
                  this.display = false;
                }
              );
            }
            if (this.tipoRegistro == 'MODIFICAR') {
              this.objRegistro.ultimousuario = this.usuarioSession;
              this.objRegistro.ultimafechamodif = this.fechaActual;
              this.objRegistro.situacion = this.objRegistro.situacion;
              this.objRegistro.usuariored = this.objRegistro.usuarioupdate;
              this.objRegistro.usuario = this.objRegistro.pk.usuario;
              


              this.usuarioMaestroServicio.actualizarPerfil(this.objRegistro).then(
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
    console.log("tipo enviado", tipo);

    if (this.buttonaction == true) {
      if (tipo == "PE") {
        this.tipoAccionPerfil = 'PE';
        this.objRegistro.clave = '';
        this.titulos = 'Perfil'
        this.objRegistro.situacion = 'V';
        this.validaFormulario = false;

        this.tipoUsuario = 'PE';
        this.objRegistro.nombre = "";
        this.objRegistro.pk.usuario = "";
        this.indicadorusuarioperfil = true;
      } else {
        this.tipoAccionPerfil = 'US';
        this.titulos = 'Usuario'
        this.objRegistro.situacion = null;
        this.validaFormulario = true;
        this.indicadorusuarioperfil = false;
        this.objRegistro.nombre = "";
        this.objRegistro.pk.usuario = "";
        this.objRegistro.clave = '';

      }
    }


  }


  cancelar() {
    this.display = false;
    this.displayPerfil = false;
  }

  cancelar1() {
    this.displayPerfilSelector = false;
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

  listarPerfilUsuario2(event: LazyLoadEvent) {
    this.bloquearPagina();
    this.filtroSPerfilUsuario2.paginacion.paginacionListaResultado = [];
    this.filtroSPerfilUsuario2.paginacion.paginacionRegistroInicio = event.first;
    this.filtroSPerfilUsuario2.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.filtroSPerfilUsuario2.labeelbutton = this.labeelbutton;
    this.usuarioMaestroServicio.listarSeguridadPerfilUsuario(this.filtroSPerfilUsuario2).then(pg => {
      this.filtroSPerfilUsuario2.paginacion = pg;
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


              if (obj.status == 0) {
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
              }


            }
          );


        }
      });

    }



  }

  eliminar(dto: any, tipo: any, dt: Table) {
    this.confirmationService.confirm({
      header: 'Confirmación', icon: 'fa fa-question-circle', message: this.getMensajePreguntaInactivar(),
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
      },
      key: "confirm"
    });

    /* this.confirmationService.confirm({
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
    }); */
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
    /*     this.clienteConponent.iniciarComponente('PERS', null); */
  }

  mostrarAgroPersona() {
    // this.displayAgGePersona = true;
    console.log(this.filtroAgGePersona)
    // this.usuarioMaestroServicio.listarAgGePersona(this.filtroAgGePersona).then(pg => {
    //   this.filtroAgGePersona.paginacion = pg;
    //   this.desbloquearPagina();
    // });


    /* this.clienteConponent.iniciarComponente('PERS', null); */
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
  }


  exportarDatos() {
    this.reporteusuariospefils = 0;
    /*  this.tipoExportarSelectorComponent.iniciarComponente(); */

  }

  seleccionarExportarTipo(tipo: any) {

    this.filtro.tipoexportar = tipo;
    this.filtroSPerfilUsuario.tipoexportar = tipo;

    if (this.reporteusuariospefils == 1) {
      this.exportarusuariopefil();
    } else if (this.reporteusuariospefils == 2) {

      this.filtroSPerfilUsuario2.tipoexportar = tipo;
      this.exportarusuariopefilgeneral();
    }

    else {
      this.exportar();
    }

  }

  exportarusuariopefil() {
    if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();

    /* this.usuarioMaestroServicio.exportarusuariosperfil(this.filtroSPerfilUsuario)
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
            a.download = 'Listado-Usuarios-Perfiles' + this.fecha + '.xls';
          } else {
            a.download = 'Listado-Usuarios-Perfiles-' + this.fecha + '.' + type[1];
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

      ); */
  }

  exportarusuariopefilgeneral() {
    if (this.esListaVacia(this.filtroSPerfilUsuario2.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();
    /* 
        this.usuarioMaestroServicio.exportarperfilgeneral(this.filtroSPerfilUsuario2)
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
                a.download = 'Listado-Usuarios-Perfiles' + this.fecha + '.xls';
              } else {
                a.download = 'Listado-Usuarios-Perfiles-' + this.fecha + '.' + type[1];
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
    
          ); */
  }

  exportar() {
    if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();
    this.filtro.usuarioPerfil = "PE"
    /*  this.usuarioMaestroServicio.exportarperfil(this.filtro)
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
             a.download = 'Listado-Perfiles-' + this.fecha + '.xls';
           } else {
             a.download = 'Listado-Perfiles-' + this.fecha + '.' + type[1];
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
 
       ); */
  }


  //  LEONARDO AGREGANDO

  //  LEONARDO FIN
}

function AuditoriaComponent(AuditoriaComponent: any, arg1: { static: false; }) {
  throw new Error('Function not implemented.');
}

function TituloMantenimientoComponent(TituloMantenimientoComponent: any, arg1: { static: false; }) {
  throw new Error('Function not implemented.');
}

function BotonesMantenimientoComponent(BotonesMantenimientoComponent: any, arg1: { static: false; }) {
  throw new Error('Function not implemented.');
}

