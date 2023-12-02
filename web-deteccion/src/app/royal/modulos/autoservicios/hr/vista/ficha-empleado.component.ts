import { Component, OnInit, ViewChild, ChangeDetectorRef, ViewEncapsulation } from '@angular/core';
import { ConfirmationService, SelectItem, MessageService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ConstanteFichaEmpleado, Dependientetemp, Direcciontemp, HrActualizacionFichaEmpleado, HrEmpleadocursotemp, HrEmpleadodocumentotemp, HrEmpleadoexperienciatemp, HrEmpleadoinstrucciontemp, HrEmpleadoreferenciatemp } from '../dominio/dto/HrActualizacionFichaEmpleado';
import { HrActualizacionfichaempleadoServicio } from '../servicio/HrActualizacionfichaempleadoServicio';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { EdadCompletaPipe } from '@framework/angular/tipetransform/edad.pipe';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';
import { UbicacionGeograficaSelectorComponent } from '@framework-comun/core/vista/ubicaciongeografica-selector.component';
import { DtoHrGradoInstruccionConsulta } from '../dominio/dto/DtoHrGradoInstruccionConsulta';
import { HrCentroEstudiosSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector.component';
import { CursoSelectorComponent } from '@framework-comun/hr/vista/curso-selector.component';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';
import { DatePipe } from '@angular/common';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { HrCentroEstudiosCarreraSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector-carrera.component';

@Component({
  templateUrl: './ficha-empleado.component.html',
  encapsulation: ViewEncapsulation.None,
  styles: [`
        .nuevo {
            background-color: rgb(239, 255, 253) !important;
        }
        .modificado {
            background-color:rgb(127, 224, 168) !important;
        }
        .eliminado {
          background-color: rgb(226, 113, 102) !important;
      }
    `
  ]
})
export class FichaDeDatosComponent extends PrincipalBaseComponent implements OnInit, UIMantenimientoController {

  //@ViewChild(UbicacionGeograficaSelectorComponent, { static: false }) ubigeoSelectorComponent: UbicacionGeograficaSelectorComponent;
  //@ViewChild(HrCentroEstudiosSelectorComponent, { static: false }) hrCentroEstudiosSelectorComponent: HrCentroEstudiosSelectorComponent;
  //@ViewChild(HrCursoDescripcionSelectorComponent, { static: false }) hrCursoDescripcionSelectorComponent: HrCursoDescripcionSelectorComponent;
  //@ViewChild(HrPuestoEmpresaSelectorComponent, { static: false }) hrPuestoEmpresaSelectorComponent: HrPuestoEmpresaSelectorComponent;
  @ViewChild(CursoSelectorComponent, { static: false }) cursoSelectorComponent: CursoSelectorComponent;
  @ViewChild(HrCentroEstudiosSelectorComponent, { static: false }) hrCentroEstudiosSelectorComponent: HrCentroEstudiosSelectorComponent;
  @ViewChild(UbicacionGeograficaSelectorComponent, { static: false }) ubicacionGeograficaSelectorComponent: UbicacionGeograficaSelectorComponent;
  @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
  @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;
  @ViewChild(HrCentroEstudiosCarreraSelectorComponent, { static: false }) hrCentroEstudiosCarreraSelectorComponent: HrCentroEstudiosCarreraSelectorComponent;

  fichaEmpleado: HrActualizacionFichaEmpleado = new HrActualizacionFichaEmpleado();

  fotoMaxSize: number = 0;
  folioMaxSize: number = 0;
  fotoMaxSizeMb: number = 0;
  folioMaxSizeMb: number = 0;

  // Información General
  estadoCivil: SelectItem[] = [];
  paises: SelectItem[] = [];
  paisesExtrajenro: SelectItem[] = [];
  paisesnac: SelectItem[] = [];
  paisesL: SelectItem[] = [];
  tbTipoCurso: SelectItem[] = [];
  lstProfesiones: DtoTabla[] = [];
  profesiones: SelectItem[] = [];

  departamentosNacimiento: SelectItem[] = [];
  provinciasNacimiento: SelectItem[] = [];
  distritosNacimiento: SelectItem[] = [];

  tipoBrevetes: SelectItem[] = [];
  nacionalidades: SelectItem[] = [];
  tipoContratos: SelectItem[] = [];
  departamentosExtranjero: SelectItem[] = [];
  provinciasExtranjero: SelectItem[] = [];
  distritosExtranjero: SelectItem[] = [];

  departamentosLocal: SelectItem[] = [];
  provinciasLocal: SelectItem[] = [];
  distritosLocal: SelectItem[] = [];

  nombreDepartamentoL: string = '';
  nombreProvinciaL: string = '';
  nombreDistritoL: string = '';

  // Dirección
  verModalDireccion: boolean;
  empDir: Direcciontemp = new Direcciontemp();
  tipovias: SelectItem[] = [];
  zonas: SelectItem[] = [];
  editaDireccion: boolean = true;

  // Familiares
  verModalFamiliar: boolean;
  empFam: Dependientetemp = new Dependientetemp();
  parentescos: SelectItem[] = [];
  parentescosSel: SelectItem[] = [];
  tipodocumento: SelectItem[] = [];
  paisemisor: SelectItem[] = [];
  listaSexo: SelectItem[] = [];
  gruposanguineo: SelectItem[] = [];
  tipovinculo: SelectItem[] = [];
  tipodocumentoacreditado: SelectItem[] = [];
  listaCodigoPostal: SelectItem[] = [];
  listaCentrosSalud: SelectItem[] = [];
  editaFamiliar: boolean = true;
  flagVinculo: boolean = false;
  flagCheck: boolean = false;

  // Formación
  verModalFormacion: boolean;
  empFor: HrEmpleadoinstrucciontemp = new HrEmpleadoinstrucciontemp();
  grados: SelectItem[] = [];
  areas: SelectItem[] = [];
  colegiosprofesionales: SelectItem[] = [];
  editaFormacion: boolean = true;
  lstGrados: DtoHrGradoInstruccionConsulta[] = [];
  verOtrocentro: boolean = false;

  // Especializaciones (Cursos)
  verModalEspecializaciones: boolean;
  empEsp: HrEmpleadocursotemp = new HrEmpleadocursotemp();
  cursos: SelectItem[] = [];
  tipo: SelectItem[] = [];
  editaEspecializacion: boolean = true;
  modalidad: SelectItem[] = [];

  // Idioma
  verModalIdioma: boolean;
  empIdio: HrEmpleadocursotemp = new HrEmpleadocursotemp();
  idiomas: SelectItem[] = [];
  idiomaNivel: SelectItem[] = [];
  editaIdioma: boolean = true;

  // Informática
  verModalInformatica: boolean;
  empInf: HrEmpleadocursotemp = new HrEmpleadocursotemp();
  cursosinformatica: SelectItem[] = [];
  infomaticaNivel: SelectItem[] = [];
  editaInformatica: boolean = true;

  // Informacion Laboral
  lstpensiones: SelectItem[] = [];
  lstafp: SelectItem[] = [];
  tipocomision: SelectItem[] = [];
  lstbancos: SelectItem[] = [];
  tipocuentacts: SelectItem[] = [];

  // Experiencia Laboral
  verModalExperienciaLaboral: boolean;
  empExp: HrEmpleadoexperienciatemp = new HrEmpleadoexperienciatemp();
  motivoretiro: SelectItem[] = [];
  monedas: SelectItem[] = [];
  areasdeexperiencia: SelectItem[] = [];
  tiposdeexperiencia: SelectItem[] = [];
  tiposdeentidad: SelectItem[] = [];
  puestossimilares: SelectItem[] = [];
  editaExperienciaLaboral: boolean = true;

  // ReferenciaLaboral
  verModalReferenciaLaboral: boolean;
  empRef: HrEmpleadoreferenciatemp = new HrEmpleadoreferenciatemp();
  editaReferenciaLaboral: boolean = true;

  // Folios
  verModalFolio: boolean;
  empDoc: HrEmpleadodocumentotemp = new HrEmpleadodocumentotemp();
  lstTipoDoc: SelectItem[] = [];
  editaDocumento: boolean = true;

  // Adicionales
  nombrePaisExtranjero: string = '';
  paisExtranjero: string = '';

  // Lista Documentos
  validaIngreso: string = '';
  flagReingreso: string = '';
  mostrarMsj: boolean = false;

  // PARA PROGRAMAR
  sexo: string = '';
  edadFamiliar: string = '';
  editarPension: boolean = false;

  flagCentroEstudios: string;
  flagTieneCarrera: string;
  flagtermino: string;

  entEspinstitucionnombre: string;
  descripcionEntr: string;

  anio: number = 0;
  mes: number = 0;
  dia: number = 0;

  lstExtensiones: string[] = [];

  puedeAnular: boolean = false;

  verTabA: boolean = true;
  verTabB: boolean = true;
  verTabC: boolean = true;
  verTabD: boolean = true;
  verTabE: boolean = true;
  verTabF: boolean = true;
  verTabG: boolean = true;
  verTabH: boolean = true;
  verTabI: boolean = true;
  verTabJ: boolean = true;
  verTabK: boolean = true;
  verTabL: boolean = true;

  constructor(
    public datepipe: DatePipe,
    private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
    private router: Router,
    private route: ActivatedRoute,
    private fichaEmpleadoService: HrActualizacionfichaempleadoServicio,
    private confirmationService: ConfirmationService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor, servicioComun: ServicioComunService, messageService: MessageService) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }
  coreGuardar(): void {
    throw new Error('Method not implemented.');
  }
  coreSalir(): void {
    this.router.navigate([ConstanteWorkflow.ruta_wf_aprobacion_listado]);
    //if (this.accion == this.ACCIONES.WORKFLOW) {
    //}
    //else {
    //this.router.navigate([ConstanteAutoservicios.ruta_solicitud_prestamo_listado]);
    //}
  }
  coreExportar(tipo: string): void {
    throw new Error('Method not implemented.');
  }
  coreMensaje(mensage: MensajeController): void {
    if (mensage.componente == 'UBIGEO-DIRECCIONES') {
      this.empDir.auxUbigeoNombre = mensage.resultado.descripcion;
      this.empDir.departamento = mensage.resultado.departamento;
      this.empDir.provincia = mensage.resultado.provincia;
      this.empDir.codigopostal = mensage.resultado.zonapostal;
    }
    else if (mensage.componente == 'CENTROESTUDIO-FORMACION') {

      var centro = mensage.resultado.centro;
      var carrera = mensage.resultado.carrera;

      this.empFor.centroestudio = centro.centro;
      this.empFor.auxCentroestudiosNombre = centro.descripcion;

      if (carrera == null) {
        this.empFor.area = null;
        this.empFor.auxArea = null;
        this.empFor.profesion = null;
        this.empFor.auxCarrera = '(NINGUNA)';
      }
      else {
        if (carrera.profesion == null) {
          this.empFor.area = null;
          this.empFor.auxArea = null;
          this.empFor.profesion = null;
          this.empFor.auxCarrera = '(NINGUNA)';
        }
        else {
          this.empFor.area = carrera.profesionArea;
          this.empFor.auxArea = carrera.profesionAreaDescripcion;
          this.empFor.profesion = carrera.profesion;
          this.empFor.auxCarrera = carrera.profesionDescripcion;
        }
      }

    }
    else if (mensage.componente == 'CENTROESTUDIO-ESPECIALIZACION') {
      this.empEsp.centroestudio = mensage.resultado.id;
      this.empEsp.auxCentroestudioNombre = mensage.resultado.nombre;
    }
    else if (mensage.componente == 'CENTROESTUDIO-IDIOMA') {
      this.empIdio.centroestudio = mensage.resultado.id;
      this.empIdio.auxCentroestudioNombre = mensage.resultado.nombre;
    }
    else if (mensage.componente == 'CENTROESTUDIO-INFORMATICA') {
      this.empInf.centroestudio = mensage.resultado.id;
      this.empInf.auxCentroestudioNombre = mensage.resultado.nombre;
    }
  }
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    if (this.accion == null || this.accion == undefined) this.accion = this.ACCIONES.EDITAR;
    this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
    this.bloquearPagina();
    var p1 = this.inicializarDatos();
    var p2 = this.inicializarDatosFamiliar();
    var p3 = this.inicializarDatosDireccion();
    var p4 = this.inicializarDatosFormacion();
    var p5 = this.inicializarDatosEspecializacion();
    var p6 = this.inicializarDatosIdioma();
    var p7 = this.inicializarDatosInformatica();
    var p8 = this.inicializarDatosInformacionLaboral();
    var p9 = this.inicializarDatosExperienciaLaboral();
    var p10 = this.inicializarDatosDocumento();
    Promise.all([p1, p2, p3, p4, p5, p6, p7, p8, p9, p10]).then(
      f => {
        this.obtenerFichaEmpleado();
      });
  }
  inicializarDatos() {
    var p = [];
    //EXTENSIONES PERMITIDAS
    this.lstExtensiones = [];
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'SY', ConstanteFichaEmpleado.MISC_DOCUMENTOS_EXTENSIONES_PERMITIDOS).then(res => {
      res.forEach(ele => {
        this.lstExtensiones.push(ele.codigo.trim());
      });
      return 1;
    }));
    //TAMANIO MAXIMO DE FOTO
    p.push(this.parametroObtenerNumero(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.PARAMETRO_FOTO_TAMANIO).then(res => {
      this.fotoMaxSize = res;
      this.fotoMaxSizeMb = Math.round(this.fotoMaxSize / 1000000);
      return 1;
    }));
    //TAMANIO MAXIMO DE DOCUMENTOS
    p.push(this.parametroObtenerNumero(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.PARAMETRO_TAMANIO_FOLIO).then(res => {
      this.folioMaxSize = res;
      this.folioMaxSizeMb = Math.round(this.folioMaxSize / 1000000);
      return 1;
    }));
    this.estadoCivil.push({ label: '-- Seleccione --', value: null });
    this.paisesL.push({ label: '-- Seleccione --', value: null });
    this.paises.push({ label: '-- Seleccione --', value: null });
    this.nacionalidades.push({ label: '-- Seleccione --', value: null });
    this.tipoBrevetes.push({ label: '-- Seleccione --', value: null });
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_ESTADO_CIVIL)
      .then(respuesta => {
        respuesta.forEach(obj => this.estadoCivil.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.fichaEmpleadoService.listarPaisesActivos().then(respuesta => {
      respuesta.forEach(pais => this.nacionalidades.push({ label: pais.descripcion, value: pais.codigo.trim() }));
      respuesta.forEach(pais => this.paises.push({ label: pais.nombre, value: pais.codigo.trim() }));
      respuesta.forEach(pais => this.paisesL.push({ label: pais.nombre, value: pais.codigo.trim() })); return 1;
    }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_BREVETE)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipoBrevetes.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosFamiliar() {
    this.listaSexo.push({ label: '-- Seleccione --', value: null });
    this.listaSexo.push({ label: 'Masculino', value: 'M' });
    this.listaSexo.push({ label: 'Femenino', value: 'F' });
    this.listaCentrosSalud.push({ label: '-- Seleccione --', value: null });
    this.listaCentrosSalud.push({ label: 'Dirección 1', value: '1' });
    this.listaCentrosSalud.push({ label: 'Dirección 2', value: '2' });
    this.parentescos.push({ label: '-- Seleccione --', value: null });
    this.parentescosSel.push({ label: '-- Seleccione --', value: null });
    this.tipodocumento.push({ label: '-- Seleccione --', value: null });
    this.paisemisor.push({ label: '-- Seleccione --', value: null });
    this.tipovinculo.push({ label: '-- Seleccione --', value: null });
    this.listaCodigoPostal.push({ label: '-- Seleccione --', value: null });
    this.gruposanguineo.push({ label: '-- Seleccione --', value: null });
    this.tipodocumentoacreditado.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_PARENTESCO)
      .then(respuesta => {
        respuesta.forEach(obj => this.parentescos.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_PARENTESCO)
      .then(respuesta => {
        respuesta.forEach(obj => this.parentescosSel.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_DOCUMENTO_PERSONA)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipodocumento.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_PAIS_EMISOR_DOCUMENTO)
      .then(respuesta => {
        respuesta.forEach(pais => this.paisemisor.push({ label: pais.nombre, value: pais.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_TIPO_VINCULO_FAMILIAR)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipovinculo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_TIPO_DOCUMENTO_ACREDITADO)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipodocumentoacreditado.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_CODIGO_POSTAL)
      .then(respuesta => {
        respuesta.forEach(obj => this.listaCodigoPostal.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_GRUPO_SANGUIENEO)
      .then(respuesta => {
        respuesta.forEach(obj => this.gruposanguineo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosDireccion() {
    this.tipovias.push({ label: '-- Seleccione --', value: null });
    this.zonas.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_VIAS)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipovias.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_ZONA)
      .then(respuesta => {
        respuesta.forEach(obj => this.zonas.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosFormacion() {
    this.profesiones.push({ label: '-- Seleccione --', value: null });
    this.grados.push({ label: '-- Seleccione --', value: null });
    this.areas.push({ label: '', value: null });
    this.colegiosprofesionales.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.fichaEmpleadoService.listarGradosActivos().then(respuesta => {
      respuesta.forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
      this.lstGrados = respuesta;
      return 1;
    }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_AREAS)
      .then(respuesta => {
        respuesta.forEach(obj => this.areas.push({ label: obj.nombre, value: obj.codigo.trim() }));
      }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_COLEGIO_PROFESIONAL)
      .then(respuesta => {
        respuesta.forEach(obj => this.colegiosprofesionales.push({ label: obj.nombre, value: obj.codigo.trim() }));
      }));
    p.push(this.fichaEmpleadoService.listarProfesionesActivas()
      .then(respuesta => {
        respuesta.forEach(obj => this.profesiones.push({ label: obj.nombre, value: obj.codigo.trim() }));
        this.lstProfesiones = respuesta;
      }));
    return p;
  }
  inicializarDatosEspecializacion() {
    this.cursos.push({ label: '-- Seleccione --', value: null });
    this.tipo.push({ label: '-- Seleccione --', value: null });
    this.modalidad.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.fichaEmpleadoService.listarCursosActivos("C").then(respuesta => {
      respuesta.forEach(obj => this.cursos.push({ label: obj.descripcion, value: obj.id })); return 1;
    }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_CURSO)
      .then(respuesta => {
        respuesta.forEach(obj => this.tipo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_MODALIDAD)
      .then(respuesta => {
        respuesta.forEach(obj => this.modalidad.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosIdioma() {
    this.idiomas.push({ label: '-- Seleccione --', value: null });
    this.idiomaNivel.push({ label: '-- Seleccione --', value: null });
    this.idiomaNivel.push({ label: 'Básico', value: 'B' });
    this.idiomaNivel.push({ label: 'Intermedio', value: 'I' });
    this.idiomaNivel.push({ label: 'Avanzado', value: 'A' });
    var p = [];
    p.push(this.fichaEmpleadoService.listarCursosActivos("I").then(respuesta => {
      respuesta.forEach(obj => this.idiomas.push({ label: obj.descripcion, value: obj.id })); return 1
    }));
    return p;
  }
  inicializarDatosInformatica() {
    this.cursosinformatica.push({ label: '-- Seleccione --', value: null });
    this.infomaticaNivel.push({ label: '-- Seleccione --', value: null });
    this.infomaticaNivel.push({ label: 'Básico', value: 'B' });
    this.infomaticaNivel.push({ label: 'Intermedio', value: 'I' });
    this.infomaticaNivel.push({ label: 'Avanzado', value: 'A' });
    var p = [];
    p.push(this.fichaEmpleadoService.listarCursosActivos("F").then(respuesta => {
      respuesta.forEach(obj => this.cursosinformatica.push({ label: obj.descripcion, value: obj.id })); return 1;
    }));
    return p;
  }
  inicializarDatosInformacionLaboral() {
    this.lstpensiones.push({ label: '-- Seleccione --', value: null });
    this.tipocomision.push({ label: '-- Seleccione --', value: null });
    this.tipocomision.push({ label: 'Flujo', value: 'F' });
    this.tipocomision.push({ label: 'Mixta', value: 'M' });
    this.lstbancos.push({ label: '-- Seleccione --', value: null });
    this.tipocuentacts.push({ label: '', value: null });
    this.tipocuentacts.push({ label: 'Ahorros', value: 'A' });
    this.tipocuentacts.push({ label: 'Cuenta Corriente', value: 'C' });
    this.tipocuentacts.push({ label: 'Movim.Efectivo', value: 'E' });
    this.tipocuentacts.push({ label: 'Cuenta Maestra', value: 'M' });
    this.tipocuentacts.push({ label: 'CTS', value: 'T' });
    this.lstafp.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, "PR", ConstanteFichaEmpleado.MISCELANEO_SISTEMA_PENSION)
      .then(respuesta => {
        respuesta.forEach(obj => this.lstpensiones.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.fichaEmpleadoService.listarBancosActivos()
      .then(respuesta => {
        respuesta.forEach(obj => this.lstbancos.push({ label: obj.descripcion, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.fichaEmpleadoService.listarAfpActivos()
      .then(respuesta => {
        respuesta.forEach(obj => this.lstafp.push({ label: obj.descripcion, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosExperienciaLaboral() {
    this.motivoretiro.push({ label: '-- Seleccione --', value: null });
    this.monedas.push({ label: '-- Seleccione --', value: null });
    this.monedas.push({ label: 'SOLES', value: 'LO' });
    this.monedas.push({ label: 'DOLARES', value: 'EX' });
    this.areasdeexperiencia.push({ label: '-- Seleccione --', value: null });
    this.tiposdeexperiencia.push({ label: '-- Seleccione --', value: null });
    this.tiposdeentidad.push({ label: '-- Seleccione --', value: null });
    var p = [];
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_MOTIVO_CESE)
      .then(respuesta => {
        respuesta.forEach(obj => this.motivoretiro.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_AREA_EXPERIENCIA)
      .then(respuesta => {
        respuesta.forEach(obj => this.areasdeexperiencia.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_EXPERIENCIA)
      .then(respuesta => {
        respuesta.forEach(obj => this.tiposdeexperiencia.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_ENTIDAD)
      .then(respuesta => {
        respuesta.forEach(obj => this.tiposdeentidad.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  inicializarDatosDocumento() {
    this.lstTipoDoc.push({ label: '-- Seleccione --', value: null });
    this.tipoContratos.push({ label: '-Seleccione-', value: null });
    var p = [];
    p.push(this.maMiscelaneosdetalleComunService.listarDtoPorHeader(ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_DOCUMENTOS_PRESENTADOS, ConstanteFichaEmpleado.COMPANIA)
      .then(respuesta => {
        this.tipoDocumentosBean = respuesta;
        respuesta.forEach(obj => this.lstTipoDoc.push({ label: obj.descripcionlocal, value: obj.codigoelemento.trim() })); return 1;
      }));
    p.push(this.fichaEmpleadoService.listarTipoContratosActivos().then(
      res => {
        res.forEach(obj => this.tipoContratos.push({ label: obj.descripcion, value: obj.codigo.trim() })); return 1;
      }));
    return p;
  }
  prepararVerTabsAprobacion(tabsvisibles) {
    this.verTabA = false;
    this.verTabB = false;
    this.verTabC = false;
    this.verTabD = false;
    this.verTabE = false;
    this.verTabF = false;
    this.verTabG = false;
    this.verTabH = false;
    this.verTabI = false;
    this.verTabJ = false;
    this.verTabK = false;
    this.verTabL = false;
    if (tabsvisibles != null && tabsvisibles != undefined) {
      if (tabsvisibles == '%') {
        this.todosLosTabs();
        return;
      }
      var tabsPermitidos = tabsvisibles.split(";");
      tabsPermitidos.forEach(tab => {
        switch (tab) {
          case "A":
            this.verTabA = true;
            break;
          case "B":
            this.verTabB = true;
            break;
          case "C":
            this.verTabC = true;
            break;
          case "D":
            this.verTabD = true;
            break;
          case "E":
            this.verTabE = true;
            break;
          case "F":
            this.verTabF = true;
            break;
          case "G":
            this.verTabG = true;
            break;
          case "H":
            this.verTabH = true;
            break;
          case "I":
            this.verTabI = true;
            break;
          case "J":
            this.verTabJ = true;
            break;
          case "K":
            this.verTabK = true;
            break;
          case "L":
            this.verTabL = true;
            break;
          default:
            break;
        }
      });
    }
  }
  todosLosTabs() {
    this.verTabA = true;
    this.verTabB = true;
    this.verTabC = true;
    this.verTabD = true;
    this.verTabE = true;
    this.verTabF = true;
    this.verTabG = true;
    this.verTabH = true;
    this.verTabI = true;
    this.verTabJ = true;
    this.verTabK = true;
    this.verTabL = true;
  }
  obtenerFichaEmpleado() {
    if (this.accion == this.ACCIONES.WORKFLOW) {
      this.fichaEmpleadoService.obtenerPorTransaccion(this.route.snapshot.params['uuid'] as string).then(respuesta => {
        respuesta.axuTransaccionUUID = this.route.snapshot.params['uuid'] as string;
        this.prepararVerTabsAprobacion(respuesta.auxSegmentos);
        this.cargarDatos(respuesta);
      });
    } else {
      this.fichaEmpleadoService.obtenerPorEmpleado().then(respuesta => {
        this.cargarDatos(respuesta);
      });
    }
  }
  cargarDatos(respuesta: HrActualizacionFichaEmpleado) {

    if (respuesta.axuTransaccionUUID != null) {
      this.wfTransaccionSeguimientoComponent.iniciarComponente(respuesta.axuTransaccionUUID);
      this.wfTransaccionBotonesComponent.iniciarComponente(respuesta.axuTransaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
    }

    if (this.accion != this.ACCIONES.WORKFLOW) {
      if (respuesta.auxNivelAprobacion === undefined && respuesta.estado === 'REGI') {
        this.puedeAnular = true;
      }
      if (respuesta.auxNivelAprobacion == null && respuesta.estado === 'REGI') {
        this.puedeAnular = true;
      }
      if (respuesta.auxNivelAprobacion === 0 && respuesta.estado === 'REGI') {
        this.puedeAnular = true;
      }
    }

    respuesta.listaDocumentos.forEach(r => {
      r.documento = this.trim(r.documento);
    });

    respuesta.listaInstruccionesBasica.forEach(r => {
      r.gradoinstruccion = this.trim(r.gradoinstruccion);
    });

    respuesta.listaInstruccionesSuperior.forEach(r => {
      r.gradoinstruccion = this.trim(r.gradoinstruccion);
    });

    respuesta.listaCursos.forEach(r => {
      r.tipocurso = this.trim(r.tipocurso);
    });

    respuesta.listaCursosIdioma.forEach(r => {
      r.tipocurso = this.trim(r.tipocurso);
    });

    respuesta.listaCursosInformatica.forEach(r => {
      r.tipocurso = this.trim(r.tipocurso);
    });

    this.fichaEmpleado = respuesta;

    var p = [];

    this.fichaEmpleado.hrEmpleadotemp.gruposanguineo = this.trim(this.fichaEmpleado.hrEmpleadotemp.gruposanguineo);
    this.fichaEmpleado.hrEmpleado.gruposanguineo = this.trim(this.fichaEmpleado.hrEmpleado.gruposanguineo);

    this.fichaEmpleado.hrEmpleadotemp.telefonoextranjero = this.trim(this.fichaEmpleado.hrEmpleadotemp.telefonoextranjero);
    this.fichaEmpleado.hrEmpleado.telefonoextranjero = this.trim(this.fichaEmpleado.hrEmpleado.telefonoextranjero);

    this.fichaEmpleado.hrEmpleadotemp.celularextranjero = this.trim(this.fichaEmpleado.hrEmpleadotemp.celularextranjero);
    this.fichaEmpleado.hrEmpleado.celularextranjero = this.trim(this.fichaEmpleado.hrEmpleado.celularextranjero);

    this.fichaEmpleado.hrEmpleadotemp.direccionextranjera = this.trim(this.fichaEmpleado.hrEmpleadotemp.direccionextranjera);
    this.fichaEmpleado.hrEmpleado.direccionextranjera = this.trim(this.fichaEmpleado.hrEmpleado.direccionextranjera);

    this.fichaEmpleado.hrEmpleadotemp.paisextranjero = this.trim(this.fichaEmpleado.hrEmpleadotemp.paisextranjero);
    this.fichaEmpleado.hrEmpleado.paisextranjero = this.trim(this.fichaEmpleado.hrEmpleado.paisextranjero);

    this.fichaEmpleado.personamasttemp.direccion = this.trim(this.fichaEmpleado.personamasttemp.direccion);
    this.fichaEmpleado.personamast.direccion = this.trim(this.fichaEmpleado.personamast.direccion);

    this.fichaEmpleado.personamasttemp.nacionalidad = this.trim(this.fichaEmpleado.personamasttemp.nacionalidad);
    this.fichaEmpleado.personamast.nacionalidad = this.trim(this.fichaEmpleado.personamast.nacionalidad);

    this.fichaEmpleado.personamasttemp.parentescoemergencia = this.trim(this.fichaEmpleado.personamasttemp.parentescoemergencia);
    this.fichaEmpleado.personamast.parentescoemergencia = this.trim(this.fichaEmpleado.personamast.parentescoemergencia);

    this.fichaEmpleado.hrEmpleado.nombreemergencia = this.trim(this.fichaEmpleado.hrEmpleado.nombreemergencia);
    this.fichaEmpleado.hrEmpleadotemp.nombreemergencia = this.trim(this.fichaEmpleado.hrEmpleadotemp.nombreemergencia);

    this.fichaEmpleado.hrEmpleado.direccionemergencia = this.trim(this.fichaEmpleado.hrEmpleado.direccionemergencia);
    this.fichaEmpleado.hrEmpleadotemp.direccionemergencia = this.trim(this.fichaEmpleado.hrEmpleadotemp.direccionemergencia);

    this.fichaEmpleado.hrEmpleado.parentescoemergencia = this.trim(this.fichaEmpleado.hrEmpleado.parentescoemergencia);
    this.fichaEmpleado.hrEmpleadotemp.parentescoemergencia = this.trim(this.fichaEmpleado.hrEmpleadotemp.parentescoemergencia);

    this.fichaEmpleado.hrEmpleado.telefonoemergencia = this.trim(this.fichaEmpleado.hrEmpleado.telefonoemergencia);
    this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia = this.trim(this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia);

    this.fichaEmpleado.hrEmpleado.celularemergencia = this.trim(this.fichaEmpleado.hrEmpleado.celularemergencia);
    this.fichaEmpleado.hrEmpleadotemp.celularemergencia = this.trim(this.fichaEmpleado.hrEmpleadotemp.celularemergencia);

    this.fichaEmpleado.empleadomast.tipopension = this.trim(this.fichaEmpleado.empleadomast.tipopension);
    this.fichaEmpleado.empleadomasttemp.tipopension = this.trim(this.fichaEmpleado.empleadomasttemp.tipopension);

    this.fichaEmpleado.empleadomast.codigoafp = this.trim(this.fichaEmpleado.empleadomast.codigoafp);
    this.fichaEmpleado.empleadomasttemp.codigoafp = this.trim(this.fichaEmpleado.empleadomasttemp.codigoafp);

    this.fichaEmpleado.empleadomast.numeroafp = this.trim(this.fichaEmpleado.empleadomast.numeroafp);
    this.fichaEmpleado.empleadomasttemp.numeroafp = this.trim(this.fichaEmpleado.empleadomasttemp.numeroafp);

    this.fichaEmpleado.empleadomast.tipocomisionafp = this.trim(this.fichaEmpleado.empleadomast.tipocomisionafp);
    this.fichaEmpleado.empleadomasttemp.tipocomisionafp = this.trim(this.fichaEmpleado.empleadomasttemp.tipocomisionafp);

    //Carga de combos NACIMIENTO
    this.departamentosNacimiento = [];
    this.provinciasNacimiento = [];
    this.distritosNacimiento = [];

    this.fichaEmpleado.hrEmpleado.paisnacimiento = this.trim(this.fichaEmpleado.hrEmpleado.paisnacimiento);
    this.fichaEmpleado.hrEmpleado.departamentonacimiento = this.trim(this.fichaEmpleado.hrEmpleado.departamentonacimiento);
    this.fichaEmpleado.hrEmpleado.provincianacimiento = this.trim(this.fichaEmpleado.hrEmpleado.provincianacimiento);
    this.fichaEmpleado.hrEmpleado.distritonacimiento = this.trim(this.fichaEmpleado.hrEmpleado.distritonacimiento);

    this.fichaEmpleado.hrEmpleadotemp.paisnacimiento = this.trim(this.fichaEmpleado.hrEmpleadotemp.paisnacimiento);
    this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento = this.trim(this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento);
    this.fichaEmpleado.hrEmpleadotemp.provincianacimiento = this.trim(this.fichaEmpleado.hrEmpleadotemp.provincianacimiento);
    this.fichaEmpleado.hrEmpleadotemp.distritonacimiento = this.trim(this.fichaEmpleado.hrEmpleadotemp.distritonacimiento);

    var ubigeoNacimientoPais = this.fichaEmpleado.hrEmpleadotemp.paisnacimiento;
    var ubigeoNacimientoDepartamento = this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento;
    var ubigeoNacimientoProvincia = this.fichaEmpleado.hrEmpleadotemp.provincianacimiento;
    var ubigeoNacimientoDistrito = this.fichaEmpleado.hrEmpleadotemp.distritonacimiento;

    this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento = null;
    this.fichaEmpleado.hrEmpleadotemp.provincianacimiento = null;
    this.fichaEmpleado.hrEmpleadotemp.distritonacimiento = null;

    this.departamentosNacimiento.push({ label: '-- Seleccione --', value: null });
    this.provinciasNacimiento.push({ label: '-- Seleccione --', value: null });
    this.distritosNacimiento.push({ label: '-- Seleccione --', value: null });

    if (!this.estaVacio(ubigeoNacimientoPais)) {
      p.push(this.fichaEmpleadoService.listarDepartamentosActivos(ubigeoNacimientoPais).then(r1 => {
        r1.forEach(dep => { this.departamentosNacimiento.push({ label: dep.nombre, value: dep.codigo.trim() }); });
        this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento = ubigeoNacimientoDepartamento;
        p.push(this.fichaEmpleadoService.listarProvinciasActivos(ubigeoNacimientoPais, ubigeoNacimientoDepartamento).then(r2 => {
          r2.forEach(prov => { this.provinciasNacimiento.push({ label: prov.nombre, value: prov.codigo.trim() }); });
          this.fichaEmpleado.hrEmpleadotemp.provincianacimiento = ubigeoNacimientoProvincia;
          p.push(this.fichaEmpleadoService.listarDistritosActivos(ubigeoNacimientoPais, ubigeoNacimientoDepartamento, ubigeoNacimientoProvincia).then(r3 => {
            r3.forEach(dis => { this.distritosNacimiento.push({ label: dis.nombre, value: dis.codigo.trim() }); });
            this.fichaEmpleado.hrEmpleadotemp.distritonacimiento = ubigeoNacimientoDistrito;
            return 1;
          }));
          return 1;
        }));
        return 1;
      }));
    }

    //Carga de combos LOCAL
    this.departamentosLocal = [];
    this.provinciasLocal = [];
    this.distritosLocal = [];

    this.fichaEmpleado.personamasttemp.pais = this.trim(this.fichaEmpleado.personamasttemp.pais);
    this.fichaEmpleado.personamasttemp.departamento = this.trim(this.fichaEmpleado.personamasttemp.departamento);
    this.fichaEmpleado.personamasttemp.provincia = this.trim(this.fichaEmpleado.personamasttemp.provincia);
    this.fichaEmpleado.personamasttemp.codigopostal = this.trim(this.fichaEmpleado.personamasttemp.codigopostal);

    this.fichaEmpleado.personamast.pais = this.trim(this.fichaEmpleado.personamast.pais);
    this.fichaEmpleado.personamast.departamento = this.trim(this.fichaEmpleado.personamast.departamento);
    this.fichaEmpleado.personamast.provincia = this.trim(this.fichaEmpleado.personamast.provincia);
    this.fichaEmpleado.personamast.codigopostal = this.trim(this.fichaEmpleado.personamast.codigopostal);

    var ubigeoLocalPais = this.fichaEmpleado.personamasttemp.pais;
    var ubigeoLocalDepartamento = this.fichaEmpleado.personamasttemp.departamento;
    var ubigeoLocalProvincia = this.fichaEmpleado.personamasttemp.provincia;
    var ubigeoLocalDistrito = this.fichaEmpleado.personamasttemp.codigopostal;

    this.fichaEmpleado.personamasttemp.departamento = null;
    this.fichaEmpleado.personamasttemp.provincia = null;
    this.fichaEmpleado.personamasttemp.codigopostal = null;

    this.departamentosLocal.push({ label: '-- Seleccione --', value: null });
    this.provinciasLocal.push({ label: '-- Seleccione --', value: null });
    this.distritosLocal.push({ label: '-- Seleccione --', value: null });

    if (!this.estaVacio(ubigeoLocalPais)) {
      p.push(this.fichaEmpleadoService.listarDepartamentosActivos(ubigeoLocalPais).then(r1 => {
        r1.forEach(dep => { this.departamentosLocal.push({ label: dep.nombre, value: dep.codigo.trim() }); });
        this.fichaEmpleado.personamasttemp.departamento = ubigeoLocalDepartamento;
        p.push(this.fichaEmpleadoService.listarProvinciasActivos(ubigeoLocalPais, ubigeoLocalDepartamento).then(r2 => {
          r2.forEach(prov => { this.provinciasLocal.push({ label: prov.nombre, value: prov.codigo.trim() }); });
          this.fichaEmpleado.personamasttemp.provincia = ubigeoLocalProvincia;
          p.push(this.fichaEmpleadoService.listarDistritosActivos(ubigeoLocalPais, ubigeoLocalDepartamento, ubigeoLocalProvincia).then(r3 => {
            r3.forEach(dis => { this.distritosLocal.push({ label: dis.nombre, value: dis.codigo.trim() }); });
            this.fichaEmpleado.personamasttemp.codigopostal = ubigeoLocalDistrito;
            return 1;
          }));
          return 1;
        }));
        return 1;
      }));
    }
    Promise.all(p).then(res => {
      this.desbloquearPagina();
    });
  }
  cargarDepartamentoPorPaisLocal() {
    this.departamentosLocal = [];
    this.departamentosLocal.push({ label: '-- Seleccione --', value: null });
    if (this.fichaEmpleado.personamasttemp.pais === undefined) {
      return;
    }
    this.bloquearPagina();
    this.fichaEmpleadoService.listarDepartamentosActivos(this.fichaEmpleado.personamasttemp.pais).then(respuesta => {
      respuesta.forEach(dep => { this.departamentosLocal.push({ label: dep.nombre, value: dep.codigo.trim() }); });
      this.fichaEmpleado.personamasttemp.departamento = null;
      this.desbloquearPagina();
    });
  }
  cargarProvinciasPorDepartamentoLocal() {
    this.provinciasLocal = [];
    this.provinciasLocal.push({ label: '-- Seleccione --', value: null });
    this.bloquearPagina();
    this.fichaEmpleadoService.listarProvinciasActivos(this.fichaEmpleado.personamasttemp.pais, this.fichaEmpleado.personamasttemp.departamento).then(respuesta => {
      respuesta.forEach(prov => this.provinciasLocal.push({ label: prov.nombre, value: prov.codigo.trim() }));
      this.fichaEmpleado.personamasttemp.provincia = null;
      this.desbloquearPagina();
    });
  }
  cargarDistritosPorProvinciaLocal() {
    this.distritosLocal = [];
    this.distritosLocal.push({ label: '-- Seleccione --', value: null });
    this.bloquearPagina();
    this.fichaEmpleadoService.listarDistritosActivos(this.fichaEmpleado.personamasttemp.pais, this.fichaEmpleado.personamasttemp.departamento, this.fichaEmpleado.personamasttemp.provincia).then(respuesta => {
      respuesta.forEach(dis => this.distritosLocal.push({ label: dis.nombre, value: dis.codigo.trim() }));
      this.fichaEmpleado.personamasttemp.codigopostal = null;
      this.desbloquearPagina();
    });
  }
  cargarDepartamentosPorPais() {
    this.departamentosNacimiento = [];
    this.departamentosNacimiento.push({ label: '-- Seleccione --', value: null });
    if (this.fichaEmpleado.hrEmpleadotemp.paisnacimiento === undefined) {
      return;
    }
    this.bloquearPagina();
    this.fichaEmpleadoService.listarDepartamentosActivos(this.fichaEmpleado.hrEmpleadotemp.paisnacimiento).then(respuesta => {
      respuesta.forEach(dep => { this.departamentosNacimiento.push({ label: dep.nombre, value: dep.codigo.trim() }); });
      this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento = null;
      this.desbloquearPagina();
    });
  }
  cargarProvinciasPorDepartamento() {
    this.bloquearPagina();
    this.provinciasNacimiento = [];
    this.provinciasNacimiento.push({ label: '-- Seleccione --', value: null });
    this.fichaEmpleadoService.listarProvinciasActivos(this.fichaEmpleado.hrEmpleadotemp.paisnacimiento, this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento).then(respuesta => {
      respuesta.forEach(prov => this.provinciasNacimiento.push({ label: prov.nombre, value: prov.codigo.trim() }));
      this.fichaEmpleado.hrEmpleadotemp.provincianacimiento = null;
      this.desbloquearPagina();
    });
  }
  cargarDistritosPorProvincia() {
    this.bloquearPagina();
    this.distritosNacimiento = [];
    this.distritosNacimiento.push({ label: '-- Seleccione --', value: null });
    this.fichaEmpleadoService.listarDistritosActivos(this.fichaEmpleado.hrEmpleadotemp.paisnacimiento, this.fichaEmpleado.hrEmpleadotemp.departamentonacimiento, this.fichaEmpleado.hrEmpleadotemp.provincianacimiento).then(respuesta => {
      respuesta.forEach(dis => this.distritosNacimiento.push({ label: dis.nombre, value: dis.codigo.trim() }));
      this.fichaEmpleado.hrEmpleadotemp.distritonacimiento = null;
      this.desbloquearPagina();
    });
  }
  generarSecuencia(lista: any[]): number {
    if (lista.length === 0) {
      return 1;
    }
    let max = lista[0].pk.secuencia;
    lista.forEach(reg => { if (reg.pk.secuencia > max) { max = reg.pk.secuencia; } });
    return max + 1;
  }
  cargarFoto(event: any) {
    this.bloquearPagina();
    var files = event.files;
    if (files.length !== 1) {
      this.desbloquearPagina();
      return;
    }
    if (files[0].size > this.fotoMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.fotoMaxSizeMb + 'mb');
      this.desbloquearPagina();
      return null;
    }
    var filePath = files[0].name;
    var allowedExtensions = /(.jpg|.jpeg|.png|.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
      this.mostrarMensajeAdvertencia('Solo puede subir imágenes con estas extensiones .jpeg/.jpg/.png/.gif');
      files[0].value = '';
      this.desbloquearPagina();
      return;
    }
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result as string;
      this.fichaEmpleado.auxFotoString = result;
      this.desbloquearPagina();
    };
  }
  guardar() {
    if (!this.validar()) {
      return;
    }
    this.bloquearPagina();
    this.fichaEmpleadoService.guardar(this.fichaEmpleado).then(res => {
      this.desbloquearPagina();
      if (this.transaccionResultadoSimple(res)) {
        this.messageService.add({ severity: 'info', summary: 'Información', detail: 'Se guardó la ficha N° ' + res.transaccionid });
        this.fichaEmpleado = res;
      }
    });
  }
  enviar() {
    if (!this.validar()) {
      return;
    }
    this.bloquearPagina();
    this.fichaEmpleadoService.enviar(this.fichaEmpleado).then(res => {
      this.desbloquearPagina();
      if (this.transaccionResultadoSimple(res)) {
        this.mostrarMensajeInfo('La Ficha # ' + res.transaccionid + ' se envió para su verificación, una vez aprobado se visualizará los datos en el Spring.');
        this.fichaEmpleado = res;
        this.puedeAnular = true;
        this.wfTransaccionSeguimientoComponent.iniciarComponente(this.fichaEmpleado.axuTransaccionUUID);
      }
    });
  }
  anular() {
    this.bloquearPagina();
    this.fichaEmpleadoService.anular(this.fichaEmpleado).then(res => {
      this.desbloquearPagina();
      if (this.transaccionResultadoSimple(res)) {
        this.router.navigate(['/spring']);
        this.mostrarMensajeInfo('Se ha anulado la ficha N° ' + this.fichaEmpleado.transaccionid);
      }
    });
  }
  // FAMILIAR
  evaluarEstadoCivil() {
    if (this.fichaEmpleado.personamasttemp.estadocivil !== 'C') {
      this.fichaEmpleado.hrEmpleado.apellidocasada = null;
    }
  }
  nuevoFamiliar() {
    this.bloquearPagina();
    this.empFam = new Dependientetemp();
    this.empFam.estado = 'A';
    this.accion = accionSolicitada.NUEVO;
    this.edadFamiliar = "Años: Meses: Días: ";
    this.empFam.flagcentroasistencia = '1';
    if (!this.esListaVacia(this.paisemisor)) {
      this.empFam.paisemisor = '604';
    }
    this.verModalFamiliar = true;
    this.editaFamiliar = true;
    this.flagVinculo = false;
    this.desbloquearPagina();
  }
  editarFamiliar(secuencia: number) {
    this.verFamiliar(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaFamiliar = true;
  }
  verFamiliar(secuencia: number) {
    this.editaFamiliar = false;
    this.bloquearPagina();
    const aux = this.fichaEmpleado.listaDependientes.find(obj => obj.pk.secuencia === secuencia);
    this.empFam = this.clonarFam(aux);

    this.empFam.flagbeneficioB = this.flagABoolean(this.empFam.flagbeneficio);
    this.empFam.flagsmfB = this.flagAInteger(this.empFam.flagsmf);
    this.empFam.flagviveconempleadoB = this.flagABoolean(this.empFam.flagviveconempleado);

    this.empFam.paisemisor = this.trim(this.empFam.paisemisor);
    this.empFam.tiposangre = this.trim(this.empFam.tiposangre);
    this.empFam.codigopostal = this.trim(this.empFam.codigopostal);

    this.accion = accionSolicitada.VER;
    this.edadFamiliar = "Años: Meses: Días: ";
    this.transfromarFechaNacimiento();
    this.cambiarSegunParentesco();
    this.verModalFamiliar = true;
    this.desbloquearPagina();
  }
  clonarFam(obj: Dependientetemp): Dependientetemp {
    const exp = new Dependientetemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarFamiliar(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaDependientes];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaDependientes = lst;
      }
    });
  }
  validarFamiliar(): boolean {
    let valida = true;

    this.messageService.clear();

    if (this.estaVacio(this.empFam.tipodependiente)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Parentesco es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.nombres)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Nombre es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.apellidopaterno)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Apellido paterno es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.apellidomaterno)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Apellido materno es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.sexo)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Sexo es requerido' });
      valida = false;
    }

    if (!this.estaVacio(this.empFam.codigopostal) && this.estaVacio(this.empFam.telefono)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El teléfono es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.tipodocumentoidentidad)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Tipo de documento de identidad es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empFam.documentoidentidad)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El N° de documento de identidad es requerido' });
      valida = false;
    }

    if (this.empFam.fechanacimiento == null || this.empFam.fechanacimiento === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Fecha de nacimiento es requerida' });
      valida = false;
    }

    if (this.empFam.fechanacimiento >= new Date()) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Fecha de nacimiento debe ser menor a la fecha actual' });
      valida = false;
    }

    if (!this.estaVacio(this.empFam.correoelectronico)) {
      const correoelectronico = this.esCorreoValido(this.empFam.correoelectronico);
      if (!correoelectronico) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Correo Electrónico ingresado no es correcto, considerar el @ y el "." ' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.empFam.tipodependiente)) {
      if (this.empFam.tipodependiente == 'H' || this.empFam.tipodependiente == 'C' || this.empFam.tipodependiente == 'O') {
        if (this.estaVacio(this.empFam.tipovinculo)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Tipo Vinculo es requerido' });
          valida = false;
        }
        if (this.estaVacio(this.empFam.tipodocumentopaternidad)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Tipo Acredita es requerido' });
          valida = false;
        }
      }
    }

    if (this.empFam.tipodocumentoidentidad != null && this.empFam.tipodocumentoidentidad !== undefined) {
      if (this.empFam.tipodocumentoidentidad === 'D') {
        if (this.empFam.documentoidentidad.trim().length !== 8) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El D.N.I debe tener 8 caracteres' });
          valida = false;
        }

        const dniNumerico = Number(this.empFam.documentoidentidad);

        if (isNaN(dniNumerico)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El D.N.I debe ingresar números' });
          valida = false;
        }

      }

      if (this.empFam.tipodocumentoidentidad === 'P') {
        if (this.empFam.documentoidentidad.trim().length > 12) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Pasaporte solo permite 12 caracteres' });
          valida = false;
        }
      }

      if (this.empFam.tipodocumentoidentidad === 'N') {
        if (this.empFam.documentoidentidad.trim().length > 15) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Carnet de Extranjeria solo permite 15 caracteres' });
          valida = false;
        }
      }

      if (this.empFam.tipodocumentoidentidad === 'X') {
        if (this.empFam.documentoidentidad.trim().length > 12) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Carnet de Extranjeria solo permite 12 caracteres' });
          valida = false;
        }
      }

      if (this.empFam.tipodocumentoidentidad === 'R') {
        if (this.empFam.documentoidentidad.trim().length !== 11) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El R.U.C. debe tener 11 caracteres' });
          valida = false;
        }

        const rucNumerico = Number(this.empFam.documentoidentidad);

        if (isNaN(rucNumerico)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El R.U.C. debe ingresar números' });
          valida = false;
        }

      }
    }

    return valida;
  }
  guardarFamiliar() {
    if (!this.validarFamiliar()) {
      return;
    }
    this.empFam.flagmodificado = 'S';
    this.empFam.flageliminado = 'N';
    this.empFam.flagbeneficio = this.booleanAFlag(this.empFam.flagbeneficioB);
    this.empFam.flagsmf = this.integerAFlag(this.empFam.flagsmfB);
    this.empFam.flagviveconempleado = this.booleanAFlag(this.empFam.flagviveconempleadoB);
    this.empFam.flagtrabaja = this.estaVacio(this.empFam.empresa) ? 'N' : 'S';
    this.empFam.auxTipodependienteNombre = this.obtenerLabelPorCombo(this.parentescos, this.empFam.tipodependiente);
    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarFamiliar();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarFamiliar();
    }
  }
  registrarFamiliar() {
    this.empFam.flagnuevo = 'S';
    this.empFam.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaDependientes];
    this.empFam.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaDependientes);
    lst.push(this.empFam);
    this.fichaEmpleado.listaDependientes = lst;
    this.verModalFamiliar = false;
  }
  actualizarFamiliar() {
    const lst = [...this.fichaEmpleado.listaDependientes];
    const aux = this.fichaEmpleado.listaDependientes.find(obj => obj.pk.secuencia === this.empFam.pk.secuencia);
    const index = this.fichaEmpleado.listaDependientes.indexOf(aux);
    lst[index] = this.empFam;
    this.fichaEmpleado.listaDependientes = lst;
    this.verModalFamiliar = false;
  }
  cambiarSegunParentesco() {
    this.flagCheck = false;
    if (this.empFam.tipodependiente == 'C' || this.empFam.tipodependiente == 'O') {
      //this.flagCheck = true;
    }
    if (this.empFam.tipodependiente == 'H' || this.empFam.tipodependiente == 'C' || this.empFam.tipodependiente == 'O') {
      this.flagVinculo = true;
    } else {
      this.flagVinculo = false;
    }
  }
  // DIRECCION
  nuevoDireccion() {
    this.bloquearPagina();
    this.empDir = new Direcciontemp();
    this.empDir.pais = 'PER';
    this.empDir.flagcentroessalud = 'N';
    this.empDir.direccion = '';
    this.empDir.numero = '';
    this.empDir.pdttipozona = '';
    this.accion = accionSolicitada.NUEVO;
    this.verModalDireccion = true;
    this.editaDireccion = true;
    this.desbloquearPagina();
  }
  editarDireccion(secuencia: number) {
    this.verDireccion(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaDireccion = true;
  }
  verDireccion(secuencia: number) {
    this.editaDireccion = false;
    const aux = this.fichaEmpleado.listaDirecciones.find(obj => obj.pk.secuencia === secuencia);
    this.empDir = this.copiar(aux);
    this.empDir.pdttipocalle = this.trim(this.empDir.pdttipocalle);
    this.empDir.pais = 'PER';
    this.accion = accionSolicitada.VER;
    this.verModalDireccion = true;
  }
  eliminarDireccion(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaDirecciones];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaDirecciones = lst;
      }
    });
  }
  validarDireccion(): boolean {
    let valida: boolean = true;
    this.messageService.clear();
    if (this.estaVacio(this.empDir.pdttipocalle)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe ingresar el Tipo de vía' });
      valida = false;
    }
    if (this.estaVacio(this.empDir.direccion)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe ingresar la Dirección' });
      valida = false;
    }
    if (this.estaVacio(this.empDir.auxUbigeoNombre)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccionar su Ubicación geográfica' });
      valida = false;
    }
    return valida;
  }
  guardarDireccion() {
    console.log('guardarDireccion');
    if (!this.validarDireccion()) {
      return;
    }
    this.empDir.flagmodificado = 'S';
    this.empDir.flageliminado = 'N';
    this.empDir.auxPdttipocalleNombre = this.obtenerLabelPorCombo(this.tipovias, this.empDir.pdttipocalle);
    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarDireccion();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarDireccion();
    }
  }
  registrarDireccion() {
    this.empDir.flagnuevo = 'S';
    this.empDir.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaDirecciones];
    this.empDir.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaDirecciones);
    lst.push(this.empDir);
    this.fichaEmpleado.listaDirecciones = lst;
    this.verModalDireccion = false;
  }
  actualizarDireccion() {
    const lst = [...this.fichaEmpleado.listaDirecciones];
    const aux = this.fichaEmpleado.listaDirecciones.find(obj => obj.pk.secuencia === this.empDir.pk.secuencia);
    const index = this.fichaEmpleado.listaDirecciones.indexOf(aux);
    lst[index] = this.empDir;
    this.fichaEmpleado.listaDirecciones = lst;
    this.verModalDireccion = false;
  }
  mostrarSelectorUbigeo() {
    this.ubicacionGeograficaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UBIGEO-DIRECCIONES', ''))
  }
  limpiarUbigeo() {
    this.empDir.auxUbigeoNombre = null;
    this.empDir.departamento = null;
    this.empDir.provincia = null;
    this.empDir.codigopostal = null;
  }
  // FORMACION
  cargarGradoPorNivel() {
    this.flagCentroEstudios = 'N';
    this.flagTieneCarrera = 'N';
    this.flagtermino = 'N';
    if (!this.estaVacio(this.empFor.gradoinstruccion)) {
      var c = this.lstGrados.find(x => x.gradoInstruccion == this.empFor.gradoinstruccion);
      if (c) {
        this.flagCentroEstudios = c.flagcentroestudios;
        this.flagTieneCarrera = c.flagtienecarrera;
        this.flagtermino = c.flagtermino;
      }
    }
  }
  cargarAreaPorCarrera() {
    if (this.empFor.profesion != null) {
      var c = this.lstProfesiones.find(x => x.codigo == this.empFor.profesion);
      if (c) {
        this.empFor.area = null;
        this.empFor.area = c.estadoId;
        if (this.estaVacio(this.empFor.area)) {
          this.mostrarMensajeInfo("No cuenta con Área Profesional la carrera seleccionada");
        }
      }
    } else {
      this.empFor.area = null;
    }
  }
  nuevaFormacion() {
    this.grados = [];
    this.grados.push({ label: '-- Seleccione --', value: null });
    this.lstGrados.filter(x => x.tipoMaestro == 'B').forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
    this.empFor = new HrEmpleadoinstrucciontemp();
    this.empFor.pk.tipomaestro = 'B';
    this.empFor.auxFlagotrocentroestudio = false;
    this.empFor.auxFlagultimogrado = false;
    this.accion = accionSolicitada.NUEVO;
    this.verModalFormacion = true;
    this.flagCentroEstudios = 'N';
    this.flagTieneCarrera = 'N';
    this.flagtermino = 'N';
    this.editaFormacion = true;
  }
  editarFormacion(secuencia: number, tipomaestro: string) {
    this.verFormacion(secuencia, tipomaestro, () => { });
    this.accion = accionSolicitada.EDITAR;
    this.editaFormacion = true;
  }
  verFormacion(secuencia: number, tipomaestro: string, callBack?: () => void): void {
    this.editaFormacion = false;
    this.accion = accionSolicitada.VER;

    const aux = this.fichaEmpleado.listaInstruccionesBasica.find(obj => obj.pk.tipomaestro === tipomaestro && obj.pk.secuencia === secuencia);
    this.empFor = this.clonarIns(aux);

    this.empFor.fechadesde = this.empFor.fechadesde === null ? null : this.empFor.fechadesde === undefined ? null : new Date(this.empFor.fechadesde);
    this.empFor.fechahasta = this.empFor.fechahasta === null ? null : this.empFor.fechahasta === undefined ? null : new Date(this.empFor.fechahasta);

    this.empFor.auxFlagpracticas = this.empFor.flagpracticas === 'S' ? true : false;
    this.empFor.auxFlagultimogrado = this.empFor.flagultimogrado === 'S' ? true : false;
    this.empFor.auxFlagotrocentroestudio = this.empFor.flagotrocentroestudio === 'S' ? true : false;

    this.flagCentroEstudios = 'N';
    this.flagTieneCarrera = 'N';
    this.flagtermino = 'N';

    if (!this.estaVacio(this.empFor.gradoinstruccion)) {
      var c = this.lstGrados.find(x => x.gradoInstruccion == this.empFor.gradoinstruccion);
      if (c) {
        this.flagCentroEstudios = c.flagcentroestudios;
        this.flagTieneCarrera = c.flagtienecarrera;
        this.flagtermino = c.flagtermino;
      }
    }

    this.empFor.area = this.trim(this.empFor.area);
    this.empFor.colegioprofesional = this.trim(this.empFor.colegioprofesional);
    this.empFor.profesion = this.trim(this.empFor.profesion);
    this.verModalFormacion = true;
  }
  clonarIns(obj: HrEmpleadoinstrucciontemp): HrEmpleadoinstrucciontemp {
    const exp = new HrEmpleadoinstrucciontemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarFormacion(secuencia: number, tipomaestro: string) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaInstruccionesBasica];
        const aux = lst.find(obj => obj.pk.tipomaestro === tipomaestro && obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.tipomaestro === tipomaestro && x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaInstruccionesBasica = lst;
      }
    });
  }
  cambioSegunFlagCentro() {
    this.empFor.centroestudio = null;
    this.empFor.auxCentroestudiosNombre = null;
    if (this.empFor.auxFlagotrocentroestudio) {
      this.verOtrocentro = true;
    } else {
      this.verOtrocentro = false;
    }
  }
  validarFormacion(): boolean {
    let valida = true;
    let fechas = true;
    this.messageService.clear();
    if (this.estaVacio(this.empFor.gradoinstruccion)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Grado de Instrucción es requerido' });
      valida = false;
    }

    if (this.flagCentroEstudios === 'S') {
      if (!this.empFor.auxFlagotrocentroestudio) {
        if (this.empFor.centroestudio === undefined || this.empFor.centroestudio == null) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Centro de Estudios es requerido' });
          valida = false;
        }
      } else {
        if (this.estaVacio(this.empFor.otrocentroestudio)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Otro Centro de Estudios es requerido' });
          valida = false;
        }
      }
    }

    if (this.empFor.fechadesde == null || this.empFor.fechadesde === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El año de inicio es requerido' });
      valida = false;
      fechas = false;
    }
    if (!this.estaVacio(this.flagtermino)) {
      if (this.flagtermino === 'S') {
        if (this.empFor.fechahasta == null || this.empFor.fechahasta === undefined) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El año de termino es requerido' });
          valida = false;
          fechas = false;
        }
      }
    }
    if (!this.estaVacio(this.flagTieneCarrera)) {
      if (this.flagTieneCarrera === 'S') {
        if (this.estaVacio(this.empFor.profesion)) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Carrera es requerida' });
          valida = false;
        }
      }
    }
    if (fechas && this.empFor.fechahasta != null) {
      if (this.empFor.fechadesde >= this.empFor.fechahasta) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El año de termino debe ser posterior al año de inicio' });
        valida = false;
      }

      if (this.empFor.fechahasta >= new Date()) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El año de termino no puede ser mayor a la fecha actual' });
        valida = false;
      }
    }
    return valida;
  }
  guardarFormacion() {
    if (!this.validarFormacion()) {
      return;
    }
    this.empFor.flagmodificado = 'S';
    this.empFor.flageliminado = 'N';
    this.empFor.auxGradoinstruccionNombre = this.obtenerLabelPorCombo(this.grados, this.empFor.gradoinstruccion);
    this.empFor.flagotrocentroestudio = this.booleanAFlag(this.empFor.auxFlagotrocentroestudio);
    this.empFor.fechahasta = this.flagtermino === 'S' ? new Date(this.empFor.fechahasta) : null;
    if (!this.empFor.auxFlagultimogrado) {
      this.confirmationService.confirm({
        key: 'cd0',
        header: 'Confirmación',
        icon: 'fa fa-question-circle',
        message: '¿Desea asignar este registro como "Último Grado"?',
        accept: () => {
          this.empFor.flagultimogrado = 'S';
          this.guardarFormacionValidado();
        },
        reject: () => {
          this.empFor.flagultimogrado = 'N';
          this.guardarFormacionValidado();
        }
      });
    } else {
      this.empFor.flagultimogrado = this.empFor.auxFlagultimogrado ? 'S' : 'N';
      this.guardarFormacionValidado();
    }
  }
  guardarFormacionValidado() {
    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarFormacion();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarFormacion();
    }
    this.modificarLstInstruccion();
  }
  registrarFormacion() {
    this.empFor.flagnuevo = 'S';
    this.empFor.flagmodificado = 'N';
    if (this.empFor.pk.tipomaestro == 'B') {
      const lst = [...this.fichaEmpleado.listaInstruccionesBasica];
      this.empFor.flagpracticas = this.empFor.auxFlagpracticas ? 'S' : 'N';
      this.empFor.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaInstruccionesBasica);
      lst.push(this.empFor);
      this.fichaEmpleado.listaInstruccionesBasica = lst;
    }
    if (this.empFor.pk.tipomaestro == 'S') {
      const lst = [...this.fichaEmpleado.listaInstruccionesSuperior];
      this.empFor.flagpracticas = this.empFor.auxFlagpracticas ? 'S' : 'N';
      this.empFor.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaInstruccionesSuperior);
      lst.push(this.empFor);
      this.fichaEmpleado.listaInstruccionesSuperior = lst;
    }
    this.verModalFormacion = false;
  }
  actualizarFormacion() {
    this.empFor.flagpracticas = this.empFor.auxFlagpracticas ? 'S' : 'N';
    if (this.empFor.pk.tipomaestro == 'B') {
      const lst = [...this.fichaEmpleado.listaInstruccionesBasica];
      const aux = this.fichaEmpleado.listaInstruccionesBasica.find(obj => obj.pk.secuencia === this.empFor.pk.secuencia);
      const index = this.fichaEmpleado.listaInstruccionesBasica.indexOf(aux);
      lst[index] = this.empFor;
      this.fichaEmpleado.listaInstruccionesBasica = lst;
    }
    if (this.empFor.pk.tipomaestro == 'S') {
      const lst = [...this.fichaEmpleado.listaInstruccionesSuperior];
      const aux = this.fichaEmpleado.listaInstruccionesSuperior.find(obj => obj.pk.secuencia === this.empFor.pk.secuencia);
      const index = this.fichaEmpleado.listaInstruccionesSuperior.indexOf(aux);
      lst[index] = this.empFor;
      this.fichaEmpleado.listaInstruccionesSuperior = lst;
    }
    this.verModalFormacion = false;
  }
  modificarLstInstruccion() {
    if (this.empFor.pk.tipomaestro == 'B') {
      var lstSuperior = this.fichaEmpleado.listaInstruccionesBasica.filter(x => x.flagultimogrado === 'S');
      if (lstSuperior.length > 0 && this.empFor.flagultimogrado === 'S') {
        lstSuperior.forEach(
          s => {
            if (s.gradoinstruccion !== this.empFor.gradoinstruccion) {
              s.flagultimogrado = 'N';
            }
          }
        )
      }
    }
    if (this.empFor.pk.tipomaestro == 'S') {
      var lstSuperior = this.fichaEmpleado.listaInstruccionesSuperior.filter(x => x.flagultimogrado === 'S');
      if (lstSuperior.length > 0 && this.empFor.flagultimogrado === 'S') {
        lstSuperior.forEach(
          s => {
            if (s.gradoinstruccion !== this.empFor.gradoinstruccion) {
              s.flagultimogrado = 'N';
            }
          }
        )
      }
    }
  }
  cargarFormacion(event: any) {
    var files = event.files;
    if (files.length !== 1) {
      return;
    }
    if (files[0].size > this.folioMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
      return;
    }
    this.bloquearPagina();
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result.toString().split(',')[1];
      this.desbloquearPagina();
      if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
        return;
      }
      this.empFor.auxDocumentoContenidoBase64 = result;
      this.empFor.auxDocumentoNombre = files[0].name;
    };
  }
  descargarFormacion() {
    if (this.empFor.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + this.empFor.auxDocumentoContenidoBase64;

    a.setAttribute('download', this.empFor.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  mostrarSelectorCentroEstudio() {
    this.hrCentroEstudiosCarreraSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO-FORMACION', null), true);
  }
  limpiarCentroEstudios() {
    this.empFor.area = null;
    this.empFor.auxArea = null;
    this.empFor.profesion = null;
    this.empFor.auxCarrera = null;
    this.empFor.centroestudio = null;
    this.empFor.auxCentroestudiosNombre = null;
  }
  mostrarSelectorCentroEstudioInfo() {
    this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO-INFORMATICA', null));
  }
  limpiarCentroEstudiosInfo() {
    this.empInf.centroestudio = null;
    this.empInf.auxCentroestudioNombre = null;
  }
  // ESPECIALIZACION (CURSOS)
  nuevaEspecializacion() {
    this.empEsp = new HrEmpleadocursotemp();
    this.accion = accionSolicitada.NUEVO;
    this.editaEspecializacion = true;
    this.verModalEspecializaciones = true;
  }
  editarEspecializacion(secuencia: number) {
    this.verEspecializacion(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaEspecializacion = true;
  }
  verEspecializacion(secuencia: number) {
    this.editaEspecializacion = false;
    this.bloquearPagina();
    const aux = this.fichaEmpleado.listaCursos.find(obj => obj.pk.secuencia === secuencia);
    this.empEsp = this.clonarEsp(aux);
    this.empEsp.tipocurso = this.trim(this.empEsp.tipocurso);
    this.empEsp.auxFlagauspicioempresa = this.flagABoolean(this.empEsp.flagauspicioempresa);
    this.accion = accionSolicitada.VER;
    this.verModalEspecializaciones = true;
    this.desbloquearPagina();
  }
  clonarEsp(obj: HrEmpleadocursotemp): HrEmpleadocursotemp {
    const exp = new HrEmpleadocursotemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarEspecializacion(secuencia: number) {
    console.log(secuencia);
    console.log(this.fichaEmpleado.listaCursos);
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaCursos];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaCursos = lst;
      }
    });
  }
  validarEspecializacion(): boolean {
    let valida = true;
    let fecha = true;
    this.messageService.clear();

    if (this.empEsp.curso === null || this.empEsp.curso === undefined) {
      valida = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El curso es requerido' });
    }

    if (this.empEsp.centroestudio === null || this.empEsp.centroestudio === undefined) {
      valida = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El centro de estudios es requerido' });
    }

    if (this.empEsp.fechadesde === null || this.empEsp.fechadesde === undefined) {
      valida = false;
      fecha = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha inicio es requerida ' });
    }

    if (this.empEsp.fechahasta !== null && this.empEsp.fechahasta !== undefined) {
      fecha = true;
      if (this.empEsp.fechahasta >= new Date()) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha fin no puede ser mayor que la fecha actual ' });
      }
    } else {
      fecha = false;
    }

    if (fecha) {
      if (this.empEsp.fechadesde >= this.empEsp.fechahasta) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha Inicio no puede ser mayor que la fecha Fin ' });
      }
    }
    return valida;
  }
  guardarEspecializacion() {
    if (!this.validarEspecializacion()) {
      return;
    }
    this.empEsp.flagmodificado = 'S';
    this.empEsp.flageliminado = 'N';
    this.empEsp.auxCursoNombre = this.obtenerLabelPorCombo(this.cursos, this.empEsp.curso);
    this.empEsp.flagauspicioempresa = this.booleanAFlag(this.empEsp.auxFlagauspicioempresa);
    this.empEsp.pk.tipomaestro = 'C';

    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarEspecializacion();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarEspecializacion();
    }
  }
  registrarEspecializacion() {
    this.empEsp.flagnuevo = 'S';
    this.empEsp.flagmodificado = 'N';
    this.empEsp.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaCursos);
    const lst = [...this.fichaEmpleado.listaCursos];
    lst.push(this.empEsp);
    this.fichaEmpleado.listaCursos = lst;
    this.verModalEspecializaciones = false;
  }
  actualizarEspecializacion() {
    const lst = [...this.fichaEmpleado.listaCursos];
    const aux = this.fichaEmpleado.listaCursos.find(obj => obj.pk.secuencia === this.empEsp.pk.secuencia);
    const index = this.fichaEmpleado.listaCursos.indexOf(aux);
    lst[index] = this.empEsp;
    this.fichaEmpleado.listaCursos = lst;
    this.verModalEspecializaciones = false;
  }
  cargarEspecializacion(event: any) {
    var files = event.files;
    if (files.length !== 1) {
      return;
    }
    if (files[0].size > this.folioMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
      return;
    }
    this.bloquearPagina();
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result.toString().split(',')[1];
      this.desbloquearPagina();
      if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
        return;
      }
      this.empEsp.auxDocumentoContenidoBase64 = result;
      this.empEsp.auxDocumentoNombre = files[0].name;
    };
  }
  descargarEspecializacion() {
    if (this.empEsp.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + this.empEsp.auxDocumentoContenidoBase64;

    a.setAttribute('download', this.empEsp.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  mostrarSelectorCentroEstudioEspecializacion() {
    this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO-ESPECIALIZACION', null));
  }
  limpiarCentroEstudiosEspecializacion() {
    this.empEsp.centroestudio = null;
    this.empEsp.auxCentroestudioNombre = null;
  }
  // IDIOMA
  mostrarSelectorCentroEstudioIdioma() {
    this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO-IDIOMA', null));
  }
  limpiarCentroEstudiosIdioma() {
    this.empIdio.centroestudio = null;
    this.empIdio.auxCentroestudioNombre = null;
  }
  nuevoIdioma() {
    this.bloquearPagina();
    this.empIdio = new HrEmpleadocursotemp();
    this.accion = accionSolicitada.NUEVO;
    this.verModalIdioma = true;
    this.desbloquearPagina();
    this.editaIdioma = true;
  }
  editarIdioma(secuencia: number) {
    this.verIdioma(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaIdioma = true;
  }
  verIdioma(secuencia: number) {
    this.bloquearPagina();
    const aux = this.fichaEmpleado.listaCursosIdioma.find(obj => obj.pk.secuencia === secuencia);
    this.empIdio = this.clonarIdio(aux);

    if (aux.tipocurso !== null && aux.tipocurso !== undefined) {
      aux.tipocurso = aux.tipocurso.trim();
    }

    this.empIdio.auxFlagauspicioempresa = this.flagABoolean(this.empIdio.flagauspicioempresa);

    this.accion = accionSolicitada.VER;
    this.verModalIdioma = true;
    this.desbloquearPagina();
    this.editaIdioma = false;
  }
  clonarIdio(obj: HrEmpleadocursotemp): HrEmpleadocursotemp {
    const exp = new HrEmpleadocursotemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarIdioma(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaCursosIdioma];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaCursosIdioma = lst;
      }
    });
  }
  guardarIdioma() {
    if (!this.validarIdioma()) {
      return;
    }
    this.empIdio.flagmodificado = 'S';
    this.empIdio.flageliminado = 'N';
    this.empIdio.auxCursoNombre = this.obtenerLabelPorCombo(this.idiomas, this.empIdio.curso);
    this.empIdio.flagauspicioempresa = this.booleanAFlag(this.empIdio.auxFlagauspicioempresa);
    this.empIdio.pk.tipomaestro = 'I';

    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarIdioma();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarIdioma();
    }
  }
  registrarIdioma() {
    this.empIdio.flagnuevo = 'S';
    this.empIdio.flagmodificado = 'N';
    this.empIdio.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaCursosIdioma);
    const lst = [...this.fichaEmpleado.listaCursosIdioma];
    lst.push(this.empIdio);
    this.fichaEmpleado.listaCursosIdioma = lst;
    this.verModalIdioma = false;
  }
  actualizarIdioma() {
    const lst = [...this.fichaEmpleado.listaCursosIdioma];
    if (this.fichaEmpleado.listaCursosIdioma !== null || this.fichaEmpleado.listaCursosIdioma !== undefined) {
      const aux = this.fichaEmpleado.listaCursosIdioma.find(obj => obj.pk.secuencia === this.empIdio.pk.secuencia);
      const index = this.fichaEmpleado.listaCursosIdioma.indexOf(aux);

      lst[index] = this.empIdio;

    }
    this.fichaEmpleado.listaCursosIdioma = [];
    this.fichaEmpleado.listaCursosIdioma = lst;
    this.verModalIdioma = false;
  }
  cargarIdioma(event: any) {
    var files = event.files;
    if (files.length !== 1) {
      return;
    }
    if (files[0].size > this.folioMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
      return;
    }
    this.bloquearPagina();
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result.toString().split(',')[1];
      this.desbloquearPagina();
      if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
        return;
      }
      this.empIdio.auxDocumentoContenidoBase64 = result;
      this.empIdio.auxDocumentoNombre = files[0].name;
    };
  }
  descargarIdioma() {
    if (this.empIdio.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + this.empIdio.auxDocumentoContenidoBase64;

    a.setAttribute('download', this.empIdio.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  validarIdioma(): boolean {
    let fecha = true;
    let can = 0;

    this.messageService.clear();
    if (this.empIdio.curso === null || this.empIdio.curso === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El idioma es requerido' });
      return false;
    }
    let valida = true;

    this.fichaEmpleado.listaCursosIdioma.forEach(
      reg => {
        if (reg.curso === this.empIdio.curso) {
          can++;
        }
      }
    );

    if (this.accion === this.ACCIONES.NUEVO && can === 1) {
      this.mostrarMensaje('El idioma ya se encuentra en la lista', 'error');
      valida = false;
    }
    if (this.accion === this.ACCIONES.EDITAR && can === 2) {
      this.mostrarMensaje('El idioma ya se encuentra en la lista', 'error');
      valida = false;
    }

    if (this.empIdio.nivelgeneral === null || this.empIdio.nivelgeneral === undefined) {
      this.mostrarMensaje('El nivel general es requerido', 'error');
      valida = false;
    }

    if (this.empIdio.centroestudio === null || this.empIdio.centroestudio === undefined) {
      this.mostrarMensaje('El centro de estudios es requerido', 'error');
      valida = false;
    }

    if (this.empIdio.fechadesde === null || this.empIdio.fechadesde === undefined) {
      valida = false;
      fecha = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha inicio es requerida ' });
    }

    if (this.empIdio.fechahasta !== null && this.empIdio.fechahasta !== undefined) {
      fecha = true;
      if (this.empIdio.fechahasta >= new Date()) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha fin no puede ser mayor que la fecha actual ' });
      }
    } else {
      fecha = false;
    }

    if (fecha) {
      if (this.empIdio.fechadesde >= this.empIdio.fechahasta) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha Inicio no puede ser mayor que la fecha Fin ' });
      }
    }
    return valida;
  }
  // INFORMÁTICA
  nuevoInformatica() {
    this.empInf = new HrEmpleadocursotemp();
    this.accion = accionSolicitada.NUEVO;
    this.verModalInformatica = true;
    this.editaInformatica = true;
  }
  editarInformatica(secuencia: number) {
    this.verInformatica(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaInformatica = true;
  }
  verInformatica(secuencia: number) {
    const aux = this.fichaEmpleado.listaCursosInformatica.find(obj => obj.pk.secuencia === secuencia);
    this.empInf = this.clonarInf(aux);
    this.empInf.nivelgeneral = this.trim(this.empInf.nivelgeneral);
    this.empInf.tipocurso = this.trim(this.empInf.tipocurso);
    this.empInf.auxFlagauspicioempresa = this.flagABoolean(this.empInf.flagauspicioempresa);
    this.accion = accionSolicitada.VER;
    this.editaInformatica = false;
    this.verModalInformatica = true;
  }
  clonarInf(obj: HrEmpleadocursotemp): HrEmpleadocursotemp {
    const exp = new HrEmpleadocursotemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarInformatica(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaCursosInformatica];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaCursosInformatica = lst;
      }
    });
  }
  validarInformatica() {
    let valida = true;
    let fecha = true;

    this.messageService.clear();

    if (this.empInf.curso === null) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El curso es requerido' });
      valida = false;
    }

    if (this.empInf.centroestudio === null) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El centro de estudios es requerido' });
      valida = false;
    }

    if (this.empInf.fechadesde === null || this.empInf.fechadesde === undefined) {
      valida = false;
      fecha = false;
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha inicio es requerida ' });
    }

    if (this.empInf.fechahasta !== null && this.empInf.fechahasta !== undefined) {
      fecha = true;
      if (this.empInf.fechahasta >= new Date()) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha fin no puede ser mayor que la fecha actual ' });
      }
    } else {
      fecha = false;
    }

    if (fecha) {
      if (this.empInf.fechadesde >= this.empInf.fechahasta) {
        valida = false;
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha Inicio no puede ser mayor que la fecha Fin ' });
      }
    }

    return valida;
  }
  guardarInformatica() {
    if (!this.validarInformatica()) {
      return;
    }
    this.empInf.flagmodificado = 'S';
    this.empInf.flageliminado = 'N';
    this.empInf.auxCursoNombre = this.obtenerLabelPorCombo(this.cursosinformatica, this.empInf.curso);
    this.empInf.flagauspicioempresa = this.booleanAFlag(this.empInf.auxFlagauspicioempresa);
    this.empInf.pk.tipomaestro = 'F';

    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarInformatica();
    } else if (this.accion === accionSolicitada.EDITAR) { this.actualizarInformatica(); }
  }
  registrarInformatica() {
    this.empInf.flagnuevo = 'S';
    this.empInf.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaCursosInformatica];
    this.empInf.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaCursosInformatica);
    lst.push(this.empInf);
    this.fichaEmpleado.listaCursosInformatica = lst;
    this.verModalInformatica = false;
  }
  actualizarInformatica() {
    const lst = [...this.fichaEmpleado.listaCursosInformatica];
    const aux = this.fichaEmpleado.listaCursosInformatica.find(obj => obj.pk.secuencia === this.empInf.pk.secuencia);
    const index = this.fichaEmpleado.listaCursosInformatica.indexOf(aux);
    lst[index] = this.empInf;
    this.fichaEmpleado.listaCursosInformatica = lst;
    this.verModalInformatica = false;
  }
  cargarInformatica(event: any) {
    var files = event.files;
    if (files.length !== 1) {
      return;
    }
    if (files[0].size > this.folioMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
      return;
    }
    this.bloquearPagina();
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result.toString().split(',')[1];
      this.desbloquearPagina();
      if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
        return;
      }
      this.empInf.auxDocumentoContenidoBase64 = result;
      this.empInf.auxDocumentoNombre = files[0].name;
    };
  }
  descargarInformatica() {
    if (this.empInf.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + this.empInf.auxDocumentoContenidoBase64;

    a.setAttribute('download', this.empInf.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  // EXPERIENCIA LABORAL
  nuevaExperienciaLaboral() {
    this.empExp = new HrEmpleadoexperienciatemp();
    this.accion = accionSolicitada.NUEVO;
    this.verModalExperienciaLaboral = true;
    this.editaExperienciaLaboral = true;
  }
  editarExperienciaLaboral(secuencia: number) {
    this.verExperienciaLaboral(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaExperienciaLaboral = true;
  }
  verExperienciaLaboral(secuencia: number) {
    const aux = this.fichaEmpleado.listaExperiencias.find(obj => obj.pk.secuencia === secuencia);
    this.accion = accionSolicitada.VER;
    this.empExp = this.clonarExpLab(aux);
    this.empExp.tipoexperiencia = this.trim(this.empExp.tipoexperiencia);
    this.empExp.motivocese = this.trim(this.empExp.motivocese);
    this.empExp.areaexperiencia = this.trim(this.empExp.areaexperiencia);
    this.verModalExperienciaLaboral = true;
    this.editaExperienciaLaboral = false;
  }
  clonarExpLab(obj: HrEmpleadoexperienciatemp): HrEmpleadoexperienciatemp {
    const exp = new HrEmpleadoexperienciatemp();
    for (const prop in obj) {
      exp[prop] = obj[prop];
    }
    return exp;
  }
  eliminarExperienciaLaboral(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaExperiencias];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaExperiencias = lst;
      }
    });
  }
  validarExperiencia(): boolean {
    let valida = true;
    let fecha = true;

    this.messageService.clear();

    if (this.estaVacio(this.empExp.empresa)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La empresa es requerida' });
      valida = false;
    }

    if (this.empExp.fechadesde === null || this.empExp.fechadesde === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha desde es requerida' });
      valida = false;
      fecha = false;
    }

    if (this.empExp.fechahasta === null || this.empExp.fechahasta == undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha hasta es requerida' });
      valida = false;
      fecha = false;
    } else {
      if (this.empExp.fechahasta >= new Date()) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha hasta no debe ser mayor a la fecha actual' });
        valida = false;
      }
    }

    if (fecha) {
      if (this.empExp.fechadesde >= this.empExp.fechahasta) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha hasta debe ser mayor a la fecha desde' });
        valida = false;
      } else {
        this.empExp.ano = this.transformDiff(this.empExp.fechadesde, this.empExp.fechahasta);
      }
    }

    if (this.empExp.motivocese === null || this.empExp.motivocese === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El motivo de retiro es requerido' });
      valida = false;
    }

    if (this.empExp.tipoexperiencia === null || this.empExp.tipoexperiencia === undefined) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El tipo de experiencia es requerido' });
      valida = false;
    }

    return valida;
  }
  guardarExperienciaLaboral() {
    if (!this.validarExperiencia()) {
      return;
    }
    this.empExp.flagmodificado = 'S';
    this.empExp.flageliminado = 'N';
    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarExperienciaLaboral();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarExperienciaLaboral();
    }
  }
  registrarExperienciaLaboral() {
    this.empExp.flagnuevo = 'S';
    this.empExp.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaExperiencias];
    this.empExp.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaExperiencias);
    lst.push(this.empExp);
    this.fichaEmpleado.listaExperiencias = lst;
    this.verModalExperienciaLaboral = false;
  }
  actualizarExperienciaLaboral() {
    const lst = [...this.fichaEmpleado.listaExperiencias];
    const aux = this.fichaEmpleado.listaExperiencias.find(obj => obj.pk.secuencia === this.empExp.pk.secuencia);
    const index = this.fichaEmpleado.listaExperiencias.indexOf(aux);
    lst[index] = this.empExp;
    this.fichaEmpleado.listaExperiencias = lst;
    this.verModalExperienciaLaboral = false;
  }
  validaSueldo(txt) {
    if (this.empExp.sueldo === null) {
      this.empExp.sueldo = null;
      txt.value = null;
    }
  }
  calcular() {
    if (this.empExp.fechahasta !== undefined) {
      if (this.empExp.fechahasta.getTime() == null || this.empExp.fechahasta.getTime() === undefined) {
        return;
      }
    }

    if (this.empExp.fechahasta !== undefined) {
      if (this.empExp.fechadesde.getTime() == null || this.empExp.fechadesde.getTime() === undefined) {
        return;
      }
    }

    if (this.empExp.fechahasta !== undefined && this.empExp.fechahasta !== undefined) {
      if (this.empExp.fechadesde >= this.empExp.fechahasta) {
        return;
      }
      let diff = (this.empExp.fechahasta.getTime() - this.empExp.fechadesde.getTime()) / 1000;
      diff /= (60 * 60 * 24);
      this.empExp.ano = Math.abs(Math.round(diff / 365.25));
    }
  }
  //REFERENCIA LABORAL
  nuevaReferenciaLaboral() {
    this.bloquearPagina();
    this.empRef = new HrEmpleadoreferenciatemp();
    this.accion = accionSolicitada.NUEVO;
    this.verModalReferenciaLaboral = true;
    this.desbloquearPagina();
    this.editaReferenciaLaboral = true;
  }
  editarReferenciaLaboral(secuencia: number) {
    this.verReferenciaLaboral(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaReferenciaLaboral = true;
  }
  verReferenciaLaboral(secuencia: number) {
    this.editaReferenciaLaboral = false;
    this.bloquearPagina();
    const aux = this.fichaEmpleado.listaReferencias.find(obj => obj.pk.secuencia === secuencia);
    this.empRef = this.copiar(aux);
    this.accion = accionSolicitada.VER;
    this.verModalReferenciaLaboral = true;
    this.desbloquearPagina();
  }
  eliminarReferenciaLaboral(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaReferencias];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaReferencias = lst;
      }
    });
  }
  validarReferencia(): boolean {
    let valida = true;

    this.messageService.clear();

    if (this.estaVacio(this.empRef.nombre)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El nombre del jefe es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empRef.telefono)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El teléfono es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empRef.empresa)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La organización es requerida' });
      valida = false;
    }

    if (this.estaVacio(this.empRef.cargo)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El cargo es requerido' });
      valida = false;
    }

    if (this.estaVacio(this.empRef.direccion)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La dirección es requerida' });
      valida = false;
    }

    return valida;
  }
  guardarReferenciaLaboral() {
    if (!this.validarReferencia()) {
      return;
    }
    this.empRef.flagmodificado = 'S';
    this.empRef.flageliminado = 'N';
    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarReferenciaLaboral();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarReferenciaLaboral();
    }
  }
  registrarReferenciaLaboral() {
    this.empRef.flagnuevo = 'S';
    this.empRef.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaReferencias];
    this.empRef.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaReferencias);
    lst.push(this.empRef);
    this.fichaEmpleado.listaReferencias = lst;
    this.verModalReferenciaLaboral = false;
  }
  actualizarReferenciaLaboral() {
    const lst = [...this.fichaEmpleado.listaReferencias];
    const aux = this.fichaEmpleado.listaReferencias.find(obj => obj.pk.secuencia === this.empRef.pk.secuencia);
    const index = this.fichaEmpleado.listaReferencias.indexOf(aux);
    lst[index] = this.empRef;
    this.fichaEmpleado.listaReferencias = lst;
    this.verModalReferenciaLaboral = false;
  }
  // DOCUMENTOS
  tipoDocumentosBean: DtoComunMaMiscelaneosdetalle[] = [];
  tieneVencimiento(tipo: string) {
    if (this.estaVacio(tipo)) {
      return '';
    }
    tipo = tipo.trim();
    var enc = this.tipoDocumentosBean.find(x => x.codigoelemento.trim() == tipo.trim());
    if (enc) {
      if (this.trimNoNulo(enc.valorcodigo1) == 'S') {
        return 'Si';
      }
      else {
        return 'No'
      }
    }
    return '';
  }
  mesesVencimiento(tipo: string) {
    if (this.estaVacio(tipo)) {
      return '';
    }
    tipo = tipo.trim();
    var enc = this.tipoDocumentosBean.find(x => x.codigoelemento.trim() == tipo.trim());
    if (enc) {
      return enc.valornumerico;
    }
    return '';
  }
  nuevoDocumento() {
    this.bloquearPagina();
    this.empDoc = new HrEmpleadodocumentotemp();
    this.empDoc.auxFlagpresento = false;
    this.empDoc.fecha = new Date();
    this.accion = accionSolicitada.NUEVO;
    this.verModalFolio = true;
    this.desbloquearPagina();
    this.editaDocumento = true;
  }
  editarDocumento(secuencia: number) {
    this.verDocumento(secuencia);
    this.accion = accionSolicitada.EDITAR;
    this.editaDocumento = true;
  }
  verDocumento(secuencia: number) {
    const aux = this.fichaEmpleado.listaDocumentos.find(obj => obj.pk.secuencia === secuencia);
    this.empDoc = this.copiar(aux);
    this.empDoc.documento = this.empDoc.documento === undefined ? null : this.empDoc.documento === null ? null : this.empDoc.documento.trim();
    this.empDoc.auxFlagpresento = this.empDoc.flagpresento === 'S' ? true : false;
    this.accion = accionSolicitada.VER;
    this.verModalFolio = true;
    this.editaDocumento = false;
  }
  eliminarDocumento(secuencia: number) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaDocumentos];
        const aux = lst.find(obj => obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }
        this.fichaEmpleado.listaDocumentos = lst;
      }
    });
  }
  guardarFolio() {
    if (!this.validarFolio()) {
      return;
    }
    this.empDoc.flagmodificado = 'S';
    this.empDoc.flageliminado = 'N';

    this.empDoc.flagpresento = this.empDoc.auxFlagpresento ? 'S' : 'N';
    this.empDoc.auxTipoDocumentoNombre = this.obtenerLabelPorCombo(this.lstTipoDoc, this.empDoc.documento);

    if (this.accion === accionSolicitada.NUEVO) {
      this.registrarFolio();
    } else if (this.accion === accionSolicitada.EDITAR) {
      this.actualizarFolio();
    }
  }
  validarFolio(): boolean {
    this.messageService.clear();
    let valida = true;
    if (this.estaVacio(this.empDoc.documento)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El tipo de documento es requerido' });
      valida = false;
    }

    if (this.empDoc.fecha == null) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Fecha es requerida' });
      valida = false;
    }

    if (this.tieneVencimiento(this.empDoc.documento) == 'Si') {
      if (this.empDoc.fechavencimiento == null) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Fecha de Vencimiento es requerida' });
        valida = false;
      }
      else {
        //Validar que la fecha vencimiento sea posterior a la fecha
        var f1 = this.empDoc.fecha;
        var f2 = this.empDoc.fechavencimiento;
        f1.setHours(0, 0, 0, 0);
        f2.setHours(0, 0, 0, 0);

        if (f1 > f2) {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Fecha de Vencimiento debe ser posterior o igual a la Fecha.' });
          valida = false;
        }
      }
    }
    if (this.estaVacio(this.empDoc.auxDocumentoNombre)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El documento es requerido' });
      valida = false;
    }
    return valida;
  }
  registrarFolio() {
    this.empDoc.flagnuevo = 'S';
    this.empDoc.flagmodificado = 'N';
    const lst = [...this.fichaEmpleado.listaDocumentos];
    this.empDoc.pk.secuencia = this.generarSecuencia(this.fichaEmpleado.listaDocumentos);
    lst.push(this.empDoc);
    this.fichaEmpleado.listaDocumentos = lst;
    this.verModalFolio = false;
  }
  actualizarFolio() {
    const lst = [...this.fichaEmpleado.listaDocumentos];
    const aux = this.fichaEmpleado.listaDocumentos.find(obj => obj.pk.secuencia === this.empDoc.pk.secuencia);
    const index = this.fichaEmpleado.listaDocumentos.indexOf(aux);
    lst[index] = this.empDoc;
    this.fichaEmpleado.listaDocumentos = lst;
    this.verModalFolio = false;
  }
  cargarFolio(event: any) {
    var files = event.files;
    if (files.length !== 1) {
      return;
    }
    if (files[0].size > this.folioMaxSize) {
      this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
      return;
    }
    this.bloquearPagina();
    var reader = new FileReader();
    reader.readAsDataURL(files[0]);
    reader.onloadend = (evt) => {
      var result = reader.result.toString().split(',')[1];
      this.desbloquearPagina();
      if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
        return;
      }
      this.empDoc.auxDocumentoContenidoBase64 = result;
      this.empDoc.auxDocumentoNombre = files[0].name;
    };
  }
  descargarDocumento(secuencia: number) {
    const doc = this.fichaEmpleado.listaDocumentos.find(x => x.pk.secuencia === secuencia);
    if (doc.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + doc.auxDocumentoContenidoBase64;

    a.setAttribute('download', doc.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  descargarDocumento2() {
    if (this.empDoc.auxDocumentoContenidoBase64 == null) {
      this.mostrarMensajeAdvertencia('No se encontró el documento');
      return;
    }
    const a = document.createElement('a');
    a.href = 'data:application/octet-stream;base64,' + this.empDoc.auxDocumentoContenidoBase64;

    a.setAttribute('download', this.empDoc.auxDocumentoNombre);
    const b = document.createEvent('MouseEvents');
    b.initEvent('click', false, true);
    a.dispatchEvent(b);
  }
  validar(): boolean {

    this.messageService.clear();
    let valida: boolean = true;

    if (this.estaVacio(this.fichaEmpleado.personamasttemp.correoelectronico)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Correo Personal es obligatorio' });
      valida = false;
    }

    if (!this.estaVacio(this.fichaEmpleado.personamasttemp.correoelectronico)) {
      const correoelectronico = this.esCorreoValido(this.fichaEmpleado.personamasttemp.correoelectronico.trim());
      if (!correoelectronico) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Correo Personal ingresado no es correcto, considerar el @ y el "." ' });
        valida = false;
      }
    }

    if (this.estaVacio(this.fichaEmpleado.personamasttemp.estadocivil)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Estado Civil es requerido' });
      valida = false;
    }

    if (!this.estaVacio(this.fichaEmpleado.personamasttemp.telefono)) {
      if (this.fichaEmpleado.personamasttemp.telefono.trim().length < 7 || this.fichaEmpleado.personamasttemp.telefono.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Teléfono Casa debe tener un mínimo de 7 digitos y como máximo 11' });
        valida = false;
      }
    }

    if (this.estaVacio(this.fichaEmpleado.personamasttemp.celular)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Celular es obligatorio' });
      valida = false;
    } else {
      if (this.fichaEmpleado.personamasttemp.celular.trim().length < 9 || this.fichaEmpleado.personamasttemp.celular.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Celular debe tener un mínimo de 9 digitos y como maximo 11' });
        valida = false;
      }
    }

    if (this.estaVacio(this.fichaEmpleado.personamasttemp.direccion)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La Dirección local es obligatoria' });
      valida = false;
    }

    if (this.estaVacio(this.fichaEmpleado.personamasttemp.pais)) {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Pais local es obligatorio' });
      valida = false;
    }

    if (!this.estaVacio(this.fichaEmpleado.hrEmpleadotemp.telefonoextranjero)) {
      if (this.fichaEmpleado.hrEmpleadotemp.telefonoextranjero.trim().length < 7 || this.fichaEmpleado.hrEmpleadotemp.telefonoextranjero.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Teléfono (Extranjero) debe tener un mínimo de 7 digitos y como máximo 11' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.fichaEmpleado.hrEmpleadotemp.celularextranjero)) {
      if (this.fichaEmpleado.hrEmpleadotemp.celularextranjero.trim().length < 9 || this.fichaEmpleado.hrEmpleadotemp.celularextranjero.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Celular (Extranjero) debe tener un mínimo de 9 digitos y como maximo 11' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.fichaEmpleado.personamasttemp.telefonoemergencia)) {
      const telefono1 = Number(this.fichaEmpleado.personamasttemp.telefonoemergencia);
      if (isNaN(telefono1)) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'En el Teléfono (Contacto 1) debe ingresar números' });
        valida = false;
      }
      if (this.fichaEmpleado.personamasttemp.telefonoemergencia.trim().length < 7 || this.fichaEmpleado.personamasttemp.telefonoemergencia.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Teléfono (Contacto 1) debe tener un mínimo de 7 digitos y como máximo 11' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.fichaEmpleado.personamasttemp.celularemergencia)) {
      const celular1 = Number(this.fichaEmpleado.personamasttemp.celularemergencia);
      if (isNaN(celular1)) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'En el Celular (Contacto 1) debe ingresar números' });
        valida = false;
      }
      if (this.fichaEmpleado.personamasttemp.celularemergencia.trim().length < 9 || this.fichaEmpleado.personamasttemp.celularemergencia.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Celular (Contacto 1) debe tener un mínimo de 9 digitos y como maximo 11' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia)) {
      const telefono1 = Number(this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia);
      if (isNaN(telefono1)) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'En el Teléfono (Contacto 2) debe ingresar números' });
        valida = false;
      }
      if (this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia.trim().length < 7 || this.fichaEmpleado.hrEmpleadotemp.telefonoemergencia.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Teléfono (Contacto 2) debe tener un mínimo de 7 digitos y como máximo 11' });
        valida = false;
      }
    }

    if (!this.estaVacio(this.fichaEmpleado.hrEmpleadotemp.celularemergencia)) {
      const celular1 = Number(this.fichaEmpleado.hrEmpleadotemp.celularemergencia);
      if (isNaN(celular1)) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'En el Celular (Contacto 2) debe ingresar números' });
        valida = false;
      }
      if (this.fichaEmpleado.hrEmpleadotemp.celularemergencia.trim().length < 9 || this.fichaEmpleado.hrEmpleadotemp.celularemergencia.trim().length > 11) {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Celular (Contacto 2) debe tener un mínimo de 9 digitos y como maximo 11' });
        valida = false;
      }
    }

    return valida;
  }
  transform(value: any) {
    if (value == null) {
      return 0;
    }

    var v: Date = new Date(value);

    var now = new Date();
    var anios = 0;
    var meses = 0;
    var dias = 0;

    if (v.getTime() > now.getTime()) {
      return 0;
    }

    while (this.addDate('y', 1, v).getTime() < now.getTime()) {
      v = this.addDate('y', 1, v);
      anios++;

    };
    while (this.addDate('m', 1, v).getTime() < now.getTime()) {
      v = this.addDate('m', 1, v);
      meses++;
    };

    while (this.addDate('d', 1, v).getTime() < now.getTime()) {
      v = this.addDate('d', 1, v);
      dias++;
    };

    return anios;
  }
  transformDiff(inicio: any, fin: any) {
    if (inicio == null) {
      return 0;
    }

    var v: Date = new Date(inicio);
    var now = new Date(fin);
    var anios = 0;

    if (v.getTime() > now.getTime()) {
      return 0;
    }

    while (this.addDate('y', 1, v).getTime() < now.getTime()) {
      v = this.addDate('y', 1, v);
      anios++;

    };

    return anios;
  }
  transfromarFechaNacimiento() {

    const value = this.empFam.fechanacimiento;

    if (value == null) {
      return this.edadFamiliar;
    }

    let v: Date = new Date(value);

    const now = new Date();
    let anios = 0;
    let meses = 0;
    let dias = 0;

    if (v.getTime() > now.getTime()) {
      return;
    }

    while (this.addDate('y', 1, v).getTime() < now.getTime()) {
      v = this.addDate('y', 1, v);
      anios++;

    }

    while (this.addDate('m', 1, v).getTime() < now.getTime()) {
      v = this.addDate('m', 1, v);
      meses++;
    }

    while (this.addDate('d', 1, v).getTime() < now.getTime()) {
      v = this.addDate('d', 1, v);
      dias++;
    }

    this.anio = anios;
    this.mes = meses;
    this.dia = dias;
    this.edadFamiliar = "Años: " + anios + " Meses: " + meses + " Días: " + dias;
    return this.edadFamiliar;
  }
  addDate(pattern: string, mount: number, fecha: Date): Date {

    const f2 = new Date(fecha);

    switch (pattern) {
      case 'y': {
        f2.setFullYear(f2.getFullYear() + mount);
        break;
      }
      case 'm': {
        f2.setMonth(f2.getMonth() + mount);
        break;
      }
      case 'd': {
        f2.setDate(f2.getDate() + mount);
        break;
      }
      default:
        break;
    }

    return f2;
  }
  cambiarColorFechas(actual: any, old: any) {
    if (actual == null || actual === undefined) {
      actual = undefined;
    } else {
      actual = this.datepipe.transform(actual, 'yyyy-MM-dd');
    }

    if (old == null || old === undefined) {
      old = undefined;
    } else {
      old = this.datepipe.transform(actual, 'yyyy-MM-dd');
    }

    let clase = undefined;
    if (actual != old) {
      clase = '#7FE0A8';
      return clase;
    }
  }
  cambiarColor(actual: any, old: any) {
    if (this.estaVacio(actual)) {
      actual = undefined;
    }

    if (this.estaVacio(old)) {
      old = undefined;
    }

    let clase = undefined;
    if (actual != old) {
      clase = '#7FE0A8';
      return clase;
    }
  }
  cambiarColorCkeck(actual: any, old: any) {
    if (this.estaVacio(actual)) {
      actual = undefined;
    } else {
      if (actual) {
        actual = 'S'
      } else {
        actual = 'N'
      }
    }

    if (this.estaVacio(old)) {
      old = undefined;
    }

    let clase = undefined;
    if (actual != old) {
      clase = '#7FE0A8';
      return clase;
    }
  }
  cambiarColorCombo(actual: any, old: any, lista: any) {
    if (this.estaVacio(actual)) {
      return;
    }
    if (this.estaVacio(old)) {
      old = undefined;
    }
    let nombre = undefined;
    let clase = undefined;
    if (lista.length > 0) {
      nombre = this.obtenerLabelPorCombo(lista, actual);
      if (this.estaVacio(nombre)) {
        nombre = undefined;
      }
    }
    if (old != nombre) {
      clase = '#7FE0A8';
      return clase;
    }
  }
  //INSTRUCCION SUPERIOR
  nuevaFormacionSuperior() {
    this.grados = [];
    this.grados.push({ label: '-- Seleccione --', value: null });
    this.lstGrados.filter(x => x.tipoMaestro == 'S').forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
    this.empFor = new HrEmpleadoinstrucciontemp();
    this.empFor.pk.tipomaestro = 'S';
    this.empFor.auxFlagotrocentroestudio = false;
    this.empFor.auxFlagultimogrado = false;
    this.accion = accionSolicitada.NUEVO;
    this.flagCentroEstudios = 'N';
    this.flagTieneCarrera = 'N';
    this.flagtermino = 'N';
    this.editaFormacion = true;
    this.verModalFormacion = true;
  }
  editarFormacionSuperior(secuencia: number, tipomaestro: string) {
    this.verFormacionSuperior(secuencia, tipomaestro, () => { });
    this.accion = accionSolicitada.EDITAR;
    this.editaFormacion = true;
  }
  eliminarFormacionSuperior(secuencia: number, tipomaestro: string) {
    this.confirmationService.confirm({
      key: 'cd0',
      header: 'Confirmación',
      icon: 'fa fa-question-circle',
      message: this.getMensajePreguntaEliminar(),
      accept: () => {
        let lst = [...this.fichaEmpleado.listaInstruccionesSuperior];
        const aux = lst.find(obj => obj.pk.tipomaestro === tipomaestro && obj.pk.secuencia === secuencia);
        if (aux.flagnuevo === 'S') {
          // eliminar de la lista
          lst = lst.filter(x => x.pk.tipomaestro === tipomaestro && x.pk.secuencia !== aux.pk.secuencia);
        } else {
          aux.flageliminado = 'S';
          aux.flagmodificado = 'S';
        }

        this.fichaEmpleado.listaInstruccionesSuperior = lst;
      }
    });
  }
  verFormacionSuperior(secuencia: number, tipomaestro: string, callBack?: () => void): void {
    this.editaFormacion = false;
    this.accion = accionSolicitada.VER;

    const aux = this.fichaEmpleado.listaInstruccionesSuperior.find(obj => obj.pk.tipomaestro === tipomaestro && obj.pk.secuencia === secuencia);
    this.empFor = this.clonarIns(aux);
    console.log(this.empFor);
    this.empFor.fechadesde = this.empFor.fechadesde === null ? null : this.empFor.fechadesde === undefined ? null : new Date(this.empFor.fechadesde);
    this.empFor.fechahasta = this.empFor.fechahasta === null ? null : this.empFor.fechahasta === undefined ? null : new Date(this.empFor.fechahasta);
    this.empFor.auxFlagpracticas = this.empFor.flagpracticas === 'S' ? true : false;
    this.empFor.auxFlagultimogrado = this.empFor.flagultimogrado === 'S' ? true : false;
    this.empFor.auxFlagotrocentroestudio = this.flagABoolean(this.empFor.flagotrocentroestudio);
    this.flagCentroEstudios = 'N';
    this.flagTieneCarrera = 'N';
    this.flagtermino = 'N';
    if (!this.estaVacio(this.empFor.gradoinstruccion)) {
      var c = this.lstGrados.find(x => x.gradoInstruccion == this.empFor.gradoinstruccion);
      if (c) {
        this.flagCentroEstudios = c.flagcentroestudios;
        this.flagTieneCarrera = c.flagtienecarrera;
        this.flagtermino = c.flagtermino;
      }
    }
    this.empFor.area = this.trim(this.empFor.area);
    this.empFor.colegioprofesional = this.trim(this.empFor.colegioprofesional);
    this.empFor.profesion = this.trim(this.empFor.profesion);
    this.verModalFormacion = true;
  }
}
