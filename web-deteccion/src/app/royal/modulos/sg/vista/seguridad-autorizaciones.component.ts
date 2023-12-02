import { FiltroPaginacionAutorizacionConcepto } from './../dominio/filtro/FiltroPaginacionAutorizacionConcepto';
import { FiltroPaginacionAplicacionesMast } from './../dominio/filtro/FiltroPaginacionAplicacionesMast';
import { SySeguridadgrupoService } from './../servicio/syseguridadgrupo.servicio';
import { DtoAutorizacionConceptoListado } from './../dominio/dto/DtoAutorizacionConceptoListado';
import { DtoAutorizacionFuncionListado } from './../dominio/dto/DtoAutorizacionFuncionListado';
import { DtoSySeguridadgrupo } from './../dominio/dto/DtoSySeguridadgrupo';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ConcetoSeguridadMaestroServicio } from './../servicio/ConcetoSeguridadMaestroServicio';

import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { Table } from 'primeng/table';
import { TituloListadoComponent } from '@framework/angular/controles/titulos/titulo-listado.component';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { UsuarioComunSelectorComponent } from '@framework-comun/sg/vista/usuariocomun-selector.component';
import { DtoAutorizacionFuncion } from '../dominio/dto/DtoAutorizacionFuncion';
import { DtoAutorizacionConcepto } from '../dominio/dto/DtoAutorizacionConcepto';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';

@Component({
  selector: 'app-seguridad-autorizaciones',
  templateUrl: './seguridad-autorizaciones.component.html'
})
export class SeguridadAutorizacionesComponent extends FormularioComponent implements OnInit, UIListadoController {
  @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
  @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
  @ViewChild(PersonaComunSelectorComponent, { static: false }) usuarioselector: PersonaComunSelectorComponent;
  /*   @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(UsuarioSelectorComponent, { static: false }) usuarioselector: UsuarioSelectorComponent;
    @ViewChild(TipoExportarSelectorComponent, { static: false }) tipoExportarSelectorComponent: TipoExportarSelectorComponent; */
  @ViewChild(UsuarioComunSelectorComponent, { static: false }) usuarioComunSelectorComponent: UsuarioComunSelectorComponent;
  listaraplicacionsub: SelectItem[];
  index: number = 0;
  //menuSeguridad: DtoMenu = new DtoMenu();

  constructor(
    private concetoSeguridadMaestroServicio: ConcetoSeguridadMaestroServicio,
    private sySeguridadgrupoService: SySeguridadgrupoService,
    private aplicacionesmastService: AplicacionesmastComunService,
    private router: Router,
    private route: ActivatedRoute,
    private cdref: ChangeDetectorRef,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService,
    servicioComun: ServicioComunService,
  ) {
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
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  //
  coreNuevo(): void {
    //this.nuevo();
  }
  coreBusquedaRapida(filtro: string): void {
  }
  coreBuscar(): void {


    if (this.filtro.codigoAplicacion == null || this.filtro.codigoAplicacion == undefined || this.filtro.codigoAplicacion == '') {
      this.mostrarMensajeAdvertencia("Seleccione una aplicación!")
    } else if (this.filtro.usuario == null || this.filtro.usuario == undefined || this.filtro.usuario == '') {
      this.mostrarMensajeAdvertencia("Debe seleccionar un usuario!")
    } else {
      this.listar({ first: 0, rows: 200 });
      this.listarConceptos({ first: 0, rows: 200 });
      this.listarreportes({ first: 0, rows: 200 });

    }
  }
  coreGuardar(): void {
    this.funcionGuardar();
  }
  coreExportar(tipo: string): void {
    this.seleccionarExportarTipo(tipo);
  }
  coreSalir(): void {
  }
  coreMensaje(mensage: MensajeController): void {
    console.log("mensaje: ", mensage.resultado);
    /*    if (mensage.componente == 'USUARIO') { */

    // this.filtro.usuario = mensage.resultado.persona;
    this.filtro.usuario = mensage.resultado.usuario;
    this.filtro.usuariodescripcion = mensage.resultado.nombre;
    /*     } */
  }
  coreFiltro(val: boolean): void {
  }
  //
  modoOnlyGrupo: boolean = false;

  modoOnlyGrupoConcepto: boolean = false;

  modoOnlyCombo: boolean = true;

  listarAplicacionesMast: DtoTabla[] = []
  filtro: FiltroPaginacionAutorizacionConcepto;
  filtroReporte: FiltroPaginacionAutorizacionConcepto;
  filtroConceptos: FiltroPaginacionAutorizacionConcepto;
  lstTemfunciones: DtoAutorizacionFuncion[];
  lstAutoConceptos: DtoAutorizacionConcepto[];

  dtoAutoConceptosListado: DtoAutorizacionConceptoListado = new DtoAutorizacionConceptoListado();
  dtoAutorizacionFuncionListado: DtoAutorizacionFuncionListado = new DtoAutorizacionFuncionListado();

  lstReportes: DtoAutorizacionConcepto[];
  dtoReportesListado: DtoAutorizacionConceptoListado = new DtoAutorizacionConceptoListado();
  clonedConceptops: { [s: string]: DtoAutorizacionFuncion; } = {};
  clonedConceptopsAuto: { [s: string]: DtoAutorizacionConcepto; } = {};
  objsend = new DtoAutorizacionFuncion();
  fechaActual: string = '';

  lstEstados: SelectItem[] = [];
  lstgruposSY: SelectItem[] = [];
  lstgruposSG: SelectItem[] = [];
  ngOnInit(): void {
    /* this.tituloListadoAsignar(10, this);
    this.objetoTitulo.listaOtros = [
      {
        label: 'Guardar', icon: 'fa fa-save', command: () => {
          this.funcionGuardar()
        }
      }]; */
    this.bloquearPagina();
    this.formularioIniciar(this.route);



    this.filtro = new FiltroPaginacionAutorizacionConcepto;
    this.filtroConceptos = new FiltroPaginacionAutorizacionConcepto;
    this.filtroReporte = new FiltroPaginacionAutorizacionConcepto;
    this.lstTemfunciones = new Array();
    this.lstAutoConceptos = new Array();
    this.lstReportes = new Array();
    this.listaraplicacionsub = new Array();

    this.fechaActual = this.obtenerFechaActual();

    /* if (this.route.snapshot.params['menu'] != null && this.route.snapshot.params['menu'] != undefined) {
      this.menuSeguridad = JSON.parse(this.route.snapshot.params['menu']) as DtoMenu;
      sessionStorage.setItem('menu-seguridad', JSON.stringify(this.menuSeguridad));
    } else {
      const ss = sessionStorage.getItem('menu-seguridad');
      if (ss !== undefined && ss != null) {
        const seguridad = JSON.parse(ss) as DtoMenu;
        this.menuSeguridad = seguridad;
      }
    } */

    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    const p3 = this.cargarAplicaciones(); // this.listaAplicacionesMast();
    Promise.all([p3]).then(r => {
      const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO, ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA, this.lstEstados);
      const p2 = this.listargruposSY_SEGURIDAD();
      const p4 = this.listargruposSEGURIDADGRUPO();
      Promise.all([p1, p2, p3, p4]).then(resp => {
        this.filtro = this.formularioFiltrosRestaurar(this.filtro);
        this.formularioOninit = false;
        this.desbloquearPagina();
        if (this.estaVacio(this.filtro.codigoAplicacion)) {
          this.filtro.codigoAplicacion = 'WH';
          this.onchangeAplicacion();
        }
        this.coreBuscar();
        let x = { index: 0 }
        this.handleChange(x);
      });
    });

  }

  funcionGuardar() {
    if (this.index === 0) {
      this.tipo = "AP";
      console.log("GUARDAR FUNCIONES")
      // alert("GUARDAR FUNCIONES")
      this.SendList();
    } else if (this.index === 1) {
      // alert("GUARDAR CONCEPTOS")
      console.log("GUARDAR CONCEPTOS")
      this.SendItemsConceptos();
      this.tipo = 'CO';

    } else if (this.index === 2) {
      // alert("GUARDAR REPORTES")
      console.log("GUARDAR REPORTES")
      this.tipo = 'RE';
      this.SendItemsReportes();
    }
  }
  ocultarComboGrupo: boolean = false;
  ocultarComboGrupoReporte: boolean = true;
  handleChange(e) {
    this.ocultarComboGrupoReporte = true;
    this.filtro.grupo = null;
    this.index = e.index;
    if (this.index == 0) {
      this.modoOnlyCombo = true;
      this.ocultarComboGrupo = true;
      this.tipo = "AP";
    } /* else if (this.index == 1) {
      this.modoOnlyCombo = false;
      this.tipo = 'CO';

    }  */else if (this.index == 1) {
      this.modoOnlyCombo = true;
      this.ocultarComboGrupo = false;
      this.tipo = 'RE';
    } else if (this.index == 2) {
      this.ocultarComboGrupoReporte = false;
    }

    this.coreBuscar();
  }


  marked = false;
  toggleVisibility() {
    this.filtro.paginacion.paginacionListaResultado.forEach(element => {
      element.seleccionar = this.filtro.seleccionar;

    });
    console.log(this.filtro.paginacion.paginacionListaResultado);
  }


  toggleVisibilityreporte() {
    this.filtroReporte.paginacion.paginacionListaResultado.forEach(element => {
      element.seleccionar = this.filtroReporte.seleccionar;

    });
    console.log(this.filtroReporte.paginacion.paginacionListaResultado);
  }



  toggleVisibilitynuevo() {
    this.filtro.paginacion.paginacionListaResultado.forEach(element => {
      element.flgAgregar = this.filtro.flgAgregar;

    });
    console.log(this.filtro.paginacion.paginacionListaResultado);
  }


  toggleVisibilitymodificar() {
    this.filtro.paginacion.paginacionListaResultado.forEach(element => {
      element.flgModificar = this.filtro.flgModificar;

    });
    console.log(this.filtro.paginacion.paginacionListaResultado);
  }


  toggleVisibilityaprobar() {
    this.filtro.paginacion.paginacionListaResultado.forEach(element => {
      element.flgAprobar = this.filtro.flgAprobar;

    });
    console.log(this.filtro.paginacion.paginacionListaResultado);
  }

  toggleVisibilityeliminar() {
    this.filtro.paginacion.paginacionListaResultado.forEach(element => {
      element.flgBorrar = this.filtro.flgBorrar;

    });
    console.log(this.filtro.paginacion.paginacionListaResultado);
  }



  toggleVisibility2() {

    this.filtroConceptos.paginacion.paginacionListaResultado.forEach(element => {

      element.seleccionar = this.filtro.seleccionar2;



    });


    console.log(this.filtroConceptos.paginacion.paginacionListaResultado);


  }


  cargarAplicaciones(): Promise<number> {
    return this.aplicacionesmastService.listarActivos().then(res => {
      res.forEach(ele => {
        this.listaraplicacionsub.push({ label: ele.nombre, value: ele.codigo });

      });
      if (res.length == 1) {
        this.filtro.codigoAplicacion = res[0].codigo;
      }
      return 1;
    })
  }

  /* listaAplicacionesMast(): Promise<number> {
    let temp = new FiltroPaginacionAplicacionesMast();
    temp.estado = "A";
    return this.concetoSeguridadMaestroServicio.listarAplicacionesMast(temp).then(pg => {
      // this.listarAplicacionesMast = pg.paginacionListaResultado;
      var number = 0;
      pg.paginacionListaResultado.forEach(obj => {
        this.listaraplicacionsub.push({ label: obj.nombre, value: obj.codigo });
        number++;
        if (number == 1) {
          this.filtro.codigoAplicacion = obj.codigo;
        }
      });
      return 1;
    });
  } */

  listargruposSY_SEGURIDAD(): Promise<number> {
    this.lstgruposSY = [];
    this.lstgruposSY.push({ label: ConstanteAngular.COMBOTODOS, value: null });
    let filtro = new DtoSySeguridadgrupo();
    filtro.aplicacioncodigo = this.filtro.codigoAplicacion;
    return this.sySeguridadgrupoService.listarseguridadgrupo(filtro).then(res => {
      res.forEach(obj => { this.lstgruposSY.push({ label: obj.nombre, value: obj.codigo }); })
      return 1;
    });
  }


  listargruposSEGURIDADGRUPO(): Promise<number> {
    this.lstgruposSG = [];
    this.lstgruposSG.push({ label: ConstanteAngular.COMBOTODOS, value: null });
    let filtro = new DtoSySeguridadgrupo();
    filtro.aplicacioncodigo = this.filtro.codigoAplicacion;
    return this.sySeguridadgrupoService.listar(filtro).then(res => {
      res.forEach(obj => { this.lstgruposSG.push({ label: obj.nombre, value: obj.codigo }); })
      return 1;
    });
  }

  onchangeAplicacion() {
    this.bloquearPagina();
    this.filtro.grupo = null;
    const p1 = this.listargruposSEGURIDADGRUPO();
    const p2 = this.listargruposSY_SEGURIDAD();
    Promise.all([p1, p2]).then(res => {
      this.desbloquearPagina();
    })
  }

  mostrarusuarios() {
    /*   this.usuarioselector.iniciarComponente('T', 'A');   */
    this.usuarioComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'USUARIOSEL', 'USUARIO'));
  }

  limpiaruser() {
    this.filtro.usuario = null;
    this.filtro.usuariodescripcion = '';
  }

  Buscar(dt: Table, dt2: Table) {
    if (this.filtro.codigoAplicacion == null || this.filtro.codigoAplicacion == undefined || this.filtro.codigoAplicacion == '') {
      this.mostrarMensajeAdvertencia("Seleccione una aplicación!")
    } else if (this.filtro.usuario == null || this.filtro.usuario == undefined || this.filtro.usuario == '') {
      this.mostrarMensajeAdvertencia("Debe seleccionar un usuario!")
    } else {
      dt.reset();
      dt2.reset();
    }
  }

  listar(event: LazyLoadEvent) {
    this.bloquearPagina();
    console.log("USUARIO : " + this.filtro.usuario)
    this.filtro.codigoAplicacion = this.filtro.codigoAplicacion;
    this.filtro.usuario = this.filtro.usuario;
    this.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.concetoSeguridadMaestroServicio.listarFunciones(this.filtro).then(pg => {
      if (pg.paginacionListaResultado.length == 0) {
        this.modoOnlyGrupo = false;
      } else {
        this.modoOnlyGrupo = true;
      }
      pg.paginacionListaResultado.forEach(element => {


        if (element.seleccionar == 'A') {
          element.seleccionar = true
        } else {
          element.seleccionar = false
        }
        if (element.flgAgregar == 'S') {
          element.flgAgregar = true
        } else {
          element.flgAgregar = false
        }
        if (element.flgModificar == 'S') {
          element.flgModificar = true
        } else {
          element.flgModificar = false
        }
        if (element.flgBorrar == 'S') {
          element.flgBorrar = true
        } else {
          element.flgBorrar = false
        }
        if (element.flgAprobar == 'S') {
          element.flgAprobar = true
        } else {
          element.flgAprobar = false
        }
      });
      this.lstTemfunciones = pg.paginacionListaResultado;
      this.filtro.paginacion = pg;
      this.desbloquearPagina();
    });
  }

  listarConceptos(event: LazyLoadEvent) {
    this.bloquearPagina();
    this.filtroConceptos.codigoAplicacion = this.filtro.codigoAplicacion;
    this.filtroConceptos.usuario = this.filtro.usuario;
    this.filtroConceptos.grupo = this.filtro.grupo;
    this.bloquearPagina();
    this.filtroConceptos.paginacion.paginacionListaResultado = [];
    this.filtroConceptos.paginacion.paginacionRegistroInicio = event.first;
    this.filtroConceptos.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.concetoSeguridadMaestroServicio.listarAutoConceptos(this.filtroConceptos).then(pg => {
      console.log("DATA FUNCION : " + pg.paginacionListaResultado);
      //    this.conceptostemp = 
      this.filtroConceptos.paginacion = pg;
      if (pg.paginacionListaResultado.length == 0) {
        this.modoOnlyGrupoConcepto = false;
      } else {
        this.modoOnlyGrupoConcepto = true;
      }
      pg.paginacionListaResultado.forEach(element => {
        if (element.ultimaFecha != null) {
          element.ultimaFecha = new Date(element.ultimaFecha);
          console.log("lst concepto cambiada", element.ultimaFecha)
        }

        if (element.seleccionar == 'A') {
          element.seleccionar = true
        } else {
          element.seleccionar = false
        }

      });
      this.lstAutoConceptos = pg.paginacionListaResultado;
      this.desbloquearPagina();
    });
  }


  listarreportes(event: LazyLoadEvent) {
    this.bloquearPagina();



    this.filtroReporte.codigoAplicacion = this.filtro.codigoAplicacion;
    this.filtroReporte.usuario = this.filtro.usuario;
    this.bloquearPagina();
    this.filtroReporte.paginacion.paginacionListaResultado = [];
    this.filtroReporte.paginacion.paginacionRegistroInicio = event.first;
    this.filtroReporte.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.concetoSeguridadMaestroServicio.listarreportes(this.filtroReporte).then(pg => {
      this.filtroReporte.paginacion = pg;
      pg.paginacionListaResultado.forEach(element => {
        if (element.seleccionar == 'S') {
          element.seleccionar = true
        } else {
          element.seleccionar = false
        }
      });



      this.lstReportes = pg.paginacionListaResultado;
      this.updateRowGroupMetaData();
      this.desbloquearPagina();
    });
  }

  onRowEditSave(concepto: DtoAutorizacionFuncion) {
    delete this.clonedConceptops[concepto.idLinea];
  }

  onRowEditCancel(concepto: DtoAutorizacionFuncion, index: number, dt: Table) {
    this.lstTemfunciones.splice(index, 1);
  }

  onRowEditInit(conceto: DtoAutorizacionFuncion) {
    this.clonedConceptops[conceto.idLinea] = { ...conceto };
  }

  SendList() {
    this.lstTemfunciones.forEach(element => {
      element.usernew = this.filtro.usuario;
    });
    this.bloquearPagina();
    this.dtoAutorizacionFuncionListado.listado = [];
    this.dtoAutorizacionFuncionListado.listado = this.lstTemfunciones;
    this.concetoSeguridadMaestroServicio.reqObjUpdateFunciones(this.dtoAutorizacionFuncionListado).then(pg => {
      this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
      this.desbloquearPagina();
    });
  }
  Cancelar() {
    console.log("DTA : " + this.lstTemfunciones)
  }
  ///SEND LIST CONCEPTOS
  onRowEditSaveConceptos(concepto: DtoAutorizacionConcepto) {
    delete this.clonedConceptops[concepto.idLinea];
  }

  onRowEditConceptos(concepto: DtoAutorizacionConcepto, index: number, dt: Table) {
    this.lstTemfunciones.splice(index, 1);
  }

  onRowEditInitConceptos(conceto: DtoAutorizacionConcepto) {
    this.clonedConceptopsAuto[conceto.idLinea] = { ...conceto };
  }

  SendItemsConceptos() {

    this.lstAutoConceptos.forEach(element => {
      element.usernew = this.filtro.usuario;
    });

    this.dtoAutoConceptosListado.listado = [];
    this.dtoAutoConceptosListado.listado = this.lstAutoConceptos;

    this.bloquearPagina();
    this.concetoSeguridadMaestroServicio.reqObjUpdateAutoConceptos(this.dtoAutoConceptosListado).then(pg => {
      this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
      this.desbloquearPagina();

    });
  }


  SendItemsReportes() {

    this.lstReportes.forEach(element => {
      element.usernew = this.filtro.usuario;
    });

    this.bloquearPagina();


    this.dtoReportesListado.listado = [];
    this.dtoReportesListado.listado = this.lstReportes;

    this.concetoSeguridadMaestroServicio.reqObjUpdateAutoReportes(this.dtoReportesListado).then(pg => {
      this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
      this.desbloquearPagina();
      this.listarreportes({ first: 0, rows: 200 });

    });
  }

  cargarUsuarios(dto: any) {
    console.log(dto);

    this.filtro.usuario = dto.usuario;
    this.filtro.usuariodescripcion = dto.nombre;
  }

  tipo: string = '';

  exportarDatos() {
    this.tipo = 'AP';
    /*   this.tipoExportarSelectorComponent.iniciarComponente(); */
  }

  exportarDatosConceptos() {
    this.tipo = 'CO';
    /*    this.tipoExportarSelectorComponent.iniciarComponente(); */
  }

  seleccionarExportarTipo(tipo: any) {

    if (this.index === 0) {
      this.filtro.tipoexportar = tipo;
      this.exportar();
    } else if (this.index === 1) {
      this.filtroConceptos.tipoexportar = tipo;
      this.exportarConcepto();

    } else if (this.index === 2) {
      this.filtroReporte.tipoexportar = tipo;
      this.exportarReporte();
    }





  }

  obtenerFechaActual() {
    var hoy = new Date();
    var diaActual = hoy.getDate().toString();
    var mesActual = (hoy.getMonth() + 1).toString();
    var anioActual = hoy.getFullYear().toString();
    var segActual = hoy.getSeconds().toString();
    if (diaActual.length === 1) { diaActual = '0' + diaActual; }
    if (mesActual.length === 1) { mesActual = '0' + mesActual; }
    return diaActual + '-' + mesActual + '-' + anioActual + '-' + segActual;
  }

  exportar() {
    if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();
    this.concetoSeguridadMaestroServicio.exportarfunciones(this.filtro)
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
            a.download = 'Listado-Funciones-' + this.fechaActual + '.xls';
          } else {
            a.download = 'Listado-Funciones-' + this.fechaActual + '.' + type[1];
          }

          a.click();
          this.fechaActual = this.obtenerFechaActual();
          window.URL.revokeObjectURL(link);
          a.remove();
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }

  exportarConcepto() {
    if (this.esListaVacia(this.filtroConceptos.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();
    this.concetoSeguridadMaestroServicio.exportarconcepto(this.filtroConceptos)
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
            a.download = 'Listado-Concepto-' + this.fechaActual + '.xls';
          } else {
            a.download = 'Listado-Concepto-' + this.fechaActual + '.' + type[1];
          }

          a.click();
          this.fechaActual = this.obtenerFechaActual();
          window.URL.revokeObjectURL(link);
          a.remove();
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }

  exportarReporte() {
    if (this.esListaVacia(this.filtroReporte.paginacion.paginacionListaResultado)) {
      this.mostrarMensajevalidarexportarGridPrincipal();
      return
    }
    this.bloquearPagina();


    this.filtroReporte.codigoAplicacion = this.filtro.codigoAplicacion;
    this.filtroReporte.usuario = this.filtro.usuario;
    this.concetoSeguridadMaestroServicio.exportarreporte(this.filtroReporte)
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
            a.download = 'Listado-Concepto-Reporte-' + this.fechaActual + '.xls';
          } else {
            a.download = 'Listado-Concepto-Reporte-' + this.fechaActual + '.' + type[1];
          }

          a.click();
          this.fechaActual = this.obtenerFechaActual();
          window.URL.revokeObjectURL(link);
          a.remove();
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }

  rowGroupMetadata: any;
  updateRowGroupMetaData() {
    this.rowGroupMetadata = {};
    debugger
    if (this.filtroReporte.paginacion.paginacionListaResultado) {
      for (let i = 0; i < this.filtroReporte.paginacion.paginacionListaResultado.length; i++) {
        let rowData = this.filtroReporte.paginacion.paginacionListaResultado[i];
        let representativeName = rowData.topico;

        if (i == 0) {
          this.rowGroupMetadata[representativeName] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.filtroReporte.paginacion.paginacionListaResultado[i - 1];
          let previousRowGroup = previousRowData.topico;
          if (representativeName === previousRowGroup)
            this.rowGroupMetadata[representativeName].size++;
          else
            this.rowGroupMetadata[representativeName] = { index: i, size: 1 };
        }
      }
    }
    console.log(this.rowGroupMetadata);
  }

}
