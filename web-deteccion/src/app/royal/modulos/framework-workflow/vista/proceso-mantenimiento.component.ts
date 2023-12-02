import { FiltroSyDocumentos } from './../dominio/filtro/FiltroSyDocumentos';
import { DtoWfTipoDocumento } from './../dominio/dto/DtoWfTipoDocumento';
import { DtoSelectorDinamico } from './../dominio/dto/DtoSelectorDinamico';
import { DtoWfFlujoNivelDocumento } from './../dominio/dto/DtoWfFlujoNivelDocumento';
import { DtoWfFlujoNivelAccion } from './../dominio/dto/DtoWfFlujoNivelAccion';
import { DtoWfEstado } from './../dominio/dto/DtoWfEstado';
import { DtoWfFlujoConfiguracion } from './../dominio/dto/DtoWfFlujoConfiguracion';
import { DtoWfFlujo } from './../dominio/dto/DtoWfFlujo';
import { DtoWfProceso } from './../dominio/dto/DtoWfProceso';
import { DtoTabla } from './../../../framework/modelo/generico/dto/DtoTabla';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { BotonesMantenimientoComponent } from './../../../framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem, ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { WfProcesoServicio } from "@framework-workflow/servicio/wfproceso.service";
import { WfTransaccionServicio } from '@framework-workflow/servicio/wftransaccion.service';
//import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
//import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { UIBaseController } from '@framework/angular/interface/UIBaseController';
import { Table } from 'primeng/table';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { zip } from 'rxjs';
import { WfPersonaComunSelectorComponent } from './wf-personacomunselector.component';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
//import { SyReporteDetalleMantenimientoComponent } from '@framework-comun/sistema/vista/syreporte-detalle-mantenimiento.component';
//import { DtoComunSyReportearchivo } from '@framework-comun/sistema/dominio/dto/DtoComunSyReportearchivo';

@Component({
    templateUrl: './proceso-mantenimiento.component.html'
})
export class ProcesoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    colores: string[] = [];

    @ViewChild(WfPersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: WfPersonaComunSelectorComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    bean: DtoWfProceso = new DtoWfProceso();
    flujoSeleccionado: DtoWfFlujo = null;
    documentosPermitidos: string[] = [];

    lstVariablesMaster: SelectItem[] = [];
    lstTiposDocumentosMaster: SelectItem[] = [];
    lstComparacionMaster: SelectItem[] = [];
    lstProcesoMastRaw: DtoTabla[] = [];
    lstProcesoMast: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];
    lstAplicacion2: SelectItem[] = [];
    lstOrigenes: SelectItem[] = [];
    lstEstadosBean: SelectItem[] = [];
    lstEstadosBasicos: SelectItem[] = [];
    lstAccionesWF: SelectItem[] = [];
    lstTipoAprobador: SelectItem[] = [];
    lstCondicionAprobacion: SelectItem[] = [];
    lstTiposDuraciones: SelectItem[] = [];
    lstTipoEstado: SelectItem[] = [];

    puedeFirmarDigital: boolean = false;
    puedeFirmarImagen: boolean = false;

    constructor(
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private wfTransaccionServicio: WfTransaccionServicio,
        private route: ActivatedRoute,
        private router: Router,
        private wfProcesoServicio: WfProcesoServicio,
        private confirmationService: ConfirmationService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }


    //
    coreNuevo(): void {
    }
    coreBusquedaRapida(filtro: string): void {
    }
    coreBuscar(): void {
    }
    coreGuardar(): void {
        this.guardar();
    }
    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
        this.router.navigate([ConstanteWorkflow.ruta_wf_proceso_listado]);
    }

    ngOnInit() {
        super.ngOnInit();

        this.formularioIniciar(this.route);

        this.bloquearPagina();
        //this.maMiscelaneosdetalleComunService.listarTablaPorHeaderActivos('SY', 'EXTADJPER', '999999').then(res => res.forEach(r => { this.documentosPermitidos.push(r.codigo.trim()) }));


        var p5 = this.parametroObtenerTexto(ConstanteWorkflow.DEFECTO_COMPANIA, ConstanteWorkflow.APLICACION, ConstanteWorkflow.PARAMETRO_HABFIRELEC).then
            (
                x => {
                    this.puedeFirmarDigital = x == 'S' ? true : false;
                }
            );

        var p6 = this.parametroObtenerTexto(ConstanteWorkflow.DEFECTO_COMPANIA, ConstanteWorkflow.APLICACION, ConstanteWorkflow.PARAMETRO_HABFIRIMAG).then
            (
                x => {
                    this.puedeFirmarImagen = x == 'S' ? true : false;
                }
            );

        this.lstAplicacion.push({ label: ' -- Seleccione -- ', value: null });
        this.lstAplicacion2.push({ label: ' -- Todos -- ', value: null });
        this.wfTransaccionServicio.listarAplicacionPorUsuario().then(
            res => {
                res.forEach(r => this.lstAplicacion.push({ label: r.nombre, value: r.codigo }));
                res.forEach(r => this.lstAplicacion2.push({ label: r.nombre, value: r.codigo }));
            }
        );

        this.lstProcesoMast.push({ label: ' -- Todos -- ', value: null });

        this.lstOrigenes.push({ label: ' -- Seleccione -- ', value: null });
        this.wfTransaccionServicio.listarOrigenes().then(
            res => {
                res.forEach(r => this.lstOrigenes.push({ label: r.descripcion, value: parseInt(r.codigo) }));
            }
        );

        this.lstTipoEstado.push({ value: null, label: ' -- Seleccione --' });
        this.lstTipoEstado.push({ value: 'INIC', label: 'Inicial' });
        this.lstTipoEstado.push({ value: 'RECH', label: 'Rechazado' });
        this.lstTipoEstado.push({ value: 'APRO', label: 'Aprobado' });

        this.lstEstadosBasicos.push({ value: null, label: ' -- Seleccione --' });
        this.lstEstadosBasicos.push({ value: 'A', label: 'Activo' });
        this.lstEstadosBasicos.push({ value: 'I', label: 'Inactivo' });

        this.lstTipoAprobador.push({ value: 'PERS', label: 'Empleado' });
        this.lstTipoAprobador.push({ value: 'JEIN', label: 'Jefe Inmediato' });
        this.lstTipoAprobador.push({ value: 'TRAP', label: 'Transacción' });
        this.lstTipoAprobador.push({ value: 'SOLI', label: 'Solicitante' });
        this.lstTipoAprobador.push({ value: 'REFE', label: 'Persona Referencia' });

        this.lstAccionesWF.push({ value: 'A', label: 'Aprobar' });
        this.lstAccionesWF.push({ value: 'R', label: 'Rechazar' });
        this.lstAccionesWF.push({ value: 'D', label: 'Devolver' });

        this.lstCondicionAprobacion.push({ value: 'SOLOUNO', label: 'Basta que 1 usuario apruebe' });
        this.lstCondicionAprobacion.push({ value: 'TODOS', label: 'Todos los usuarios deben aprobar' });
        this.lstCondicionAprobacion.push({ value: 'TODOSUNAPR', label: 'Todos los segmentos enviados deben aprobarse' });
        this.lstCondicionAprobacion.push({ value: 'ALMENOS', label: 'Deben aprobar por lo menos' });

        this.cargarVariablesMaster();

        this.lstComparacionMaster.push({ value: null, label: '-- Seleccione --' });
        this.lstComparacionMaster.push({ value: 'IN', label: 'Dentro' });
        this.lstComparacionMaster.push({ value: 'BW', label: 'Entre' });
        this.lstComparacionMaster.push({ value: '>', label: 'Mayor que' });


        this.colores.push('', '#9575cd', '#dce775', '#e57373', '#e0e0e0', '#f06292', '#ffb74d', '#4dd0e1', '#4db6ac', '#7986cb', '#64b5f6', '#a1887f', '#ba68c8', '', '', '', '', '', '', '', '', '', '');


        var p1 = this.wfTransaccionServicio.listarMiscelaneosActivos("HR", "DOCUMENTOS").then(
            docs => {
                this.lstTiposDocumentosMaster.push({ value: null, label: ' -- Seleccione --' });
                docs = docs.sort(this.compare);
                docs.forEach(y => {
                    this.lstTiposDocumentosMaster.push({ value: y.codigo.trim(), label: y.descripcion.substring(0, (y.descripcion.length > 50 ? 50 : y.descripcion.length)) });
                });
            }
        );

        //const p2 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO, 'WF', 'TIEMPOACTI', '999999', this.lstTiposDuraciones);

        const p3 = this.wfProcesoServicio.syprocesomst().then(
            r => {
                this.lstProcesoMastRaw = r;
                r.forEach(r => this.lstProcesoMast.push({ label: r.descripcion, value: r.codigo }));
            }
        );

        Promise.all([p1/*, p2*/, p3]).then(
            res => {
                this.iniciarComponente();
            }
        );
    }

    cargarVariablesMaster() {
        this.lstVariablesMaster.push({ value: null, label: ' -- Seleccione --' });
        this.lstVariablesMaster.push({ value: 'TIDC', label: 'Documento Identidad' });
        this.lstVariablesMaster.push({ value: 'AREV', label: 'Área Revisora' });
        this.lstVariablesMaster.push({ value: 'MONT', label: 'Monto' });
        this.lstVariablesMaster.push({ value: 'AFEM', label: 'Proyecto' });
        this.lstVariablesMaster.push({ value: 'COMI', label: 'Comisión' });
        this.lstVariablesMaster.push({ value: 'COMP', label: 'Compañía' });
        this.lstVariablesMaster.push({ value: 'SUCU', label: 'Sucursal' });
        this.lstVariablesMaster.push({ value: 'MOND', label: 'Moneda' });
        this.lstVariablesMaster.push({ value: 'MORE', label: 'Motivo Reclutamiento' });
        this.lstVariablesMaster.push({ value: 'UNOP', label: 'Unidad Operativa' });
        this.lstVariablesMaster.push({ value: 'AREA', label: 'Área' });
        this.lstVariablesMaster.push({ value: 'TADJ', label: 'Tipo Adjudicación' });
        this.lstVariablesMaster.push({ value: 'TIPE', label: 'Tipo de Persona' });
        this.lstVariablesMaster.push({ value: 'TUTL', label: 'Tipo Utilización' });
    }

    compare(a: DtoTabla, b: DtoTabla) {
        if (a.descripcion < b.descripcion) {
            return -1;
        }
        if (a.descripcion > b.descripcion) {
            return 1;
        }
        return 0;
    }

    puedeEditar: boolean = false;
    puedeCopiar: boolean = false;


    iniciarComponente() {

        if (this.route.snapshot.params['uuid'] == null || this.route.snapshot.params['uuid'] == undefined) {
            this.accion = this.ACCIONES.NUEVO;
            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            this.puedeEditar = true;
            this.nuevo();
        }
        else {
            const accionExterna = this.route.snapshot.params['accion'] as number;
            this.accion = accionExterna;

            this.puedeEditar = true;
            this.puedeCopiar = this.accion == this.ACCIONES.COPIAR ? true : false;

            if (this.puedeEditar && this.puedeCopiar) {
                this.accion = this.ACCIONES.COPIAR;
                this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            }
            else if (this.puedeEditar) {
                this.accion = this.ACCIONES.EDITAR;
                this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            }
            else {
                this.accion = this.ACCIONES.VER;
                this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            }
            this.obtenerPorId((this.route.snapshot.params['uuid'] as string));
        }
    }

    nuevo() {
        this.bean.auxEsNuevaVersion = 'N';
        this.bean.estado = "A";
        //CARGAR LOS ROLES
        this.bean.roles.push({ codigo: 'PERS', descripcion: 'Empleado' });
        this.bean.roles.push({ codigo: 'JEIN', descripcion: 'Jefe Inmediato' });
        this.bean.roles.push({ codigo: 'TRAP', descripcion: 'Transacción' });
        this.bean.roles.push({ codigo: 'SOLI', descripcion: 'Solicitante' });
        this.bean.roles.push({ codigo: 'REFE', descripcion: 'Persona Referencia' });
        this.armarRoles(this.bean.roles);
        this.desbloquearPagina();
    }

    obtenerPorId(uuid: string) {

        this.obtenerPlantillasRelacionadas(uuid);

        this.wfProcesoServicio.obtenerProcesoPorId(uuid).then(
            x => {
                this.lstEstadosBean.push({ value: null, label: ' -- Seleccione --' });
                this.reloadEstados(x.estados);

                x.flujos.forEach(
                    flujo => {
                        flujo.niveles.forEach(
                            nivel => {
                                nivel.documentos.forEach(
                                    doc => {
                                        var docbean = this.lstTiposDocumentosMaster.find(x => x.value == doc.tipodocumento);
                                        if (docbean) {
                                            doc.des = docbean.label;
                                        }
                                        else {
                                            doc.des = doc.tipodocumento;
                                        }
                                    }
                                );
                            }
                        );
                    }
                );

                this.armarRoles(x.roles);

                this.bean = x;

                //Ya no se despliega por default

                /*if (this.bean.flujos.length > 0) {
                    this.flujoSeleccionado = this.bean.flujos[0];
                }*/
                this.flujoSeleccionado = null;
                this.desbloquearPagina();
            }
        );
    }

    reloadEstados(ls: DtoWfEstado[]) {
        this.lstEstadosBean = this.lstEstadosBean.filter(x => x.value == null);
        if (this.lstEstadosBean.length == 0) {
            this.lstEstadosBean.push({ value: null, label: ' -- Seleccione -- ' });
        }
        ls.forEach(
            estado => {
                this.lstEstadosBean.push({ value: estado.estado, label: estado.descripcion });
            }
        );
    }


    verModalDocumento: boolean = false;
    verModalCorreo: boolean = false;
    lstCorreos: DtoTabla[] = [];

    agregarCorreo() {
        var temp = [...this.lstCorreos];
        var nuevo = new DtoTabla();
        nuevo.descripcion = '';
        temp.push(nuevo);
        this.lstCorreos = temp;
    }

    verModalVariableMaster: boolean = false;
    dlgVariable: string;


    abrirDialogoVariable() {
        this.dlgVariable = null;
        this.verModalVariableMaster = true;
    }

    agregarVariable() {
        if (this.dlgVariable == null || this.dlgVariable == undefined) {
            this.mostrarMensajeAdvertencia("Seleccionar una variable");
            return;
        }
        if (this.bean.variables.filter(x => x.variable == this.dlgVariable).length != 0) {
            this.mostrarMensajeAdvertencia("Ya se ha seleccionada la variable");
            return;
        }

        var lst = [...this.bean.variables];
        lst.push({ proceso: this.bean.proceso, variable: this.dlgVariable });
        this.bean.variables = lst;

        this.verModalVariableMaster = false;
    }

    eliminarVariable(variable: string) {
        var enFlujo = false;
        var mensaje = "No se puede eliminar, la variable es usada en el flujo : ";

        this.bean.flujos.forEach(
            flujo => {
                flujo.configuraciones.forEach(
                    config => {
                        if (config.variable == variable) {
                            enFlujo = true;
                            mensaje = mensaje + flujo.descripcion + ', ';
                        }
                    }
                );
            }
        );

        if (enFlujo) {
            this.mostrarMensajeAdvertencia(mensaje.substring(0, mensaje.length - 2));
            return;
        }

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.bean.variables];
                lst = lst.filter(x => x.variable != variable);
                this.bean.variables = lst;
                this.desbloquearPagina();
            }
        });

    }

    verModalDocumentoMaster: boolean = false;
    dlgDocumento: string;
    dlgDocumentoRuta: string;
    dlgDocumentoGrupo: string;

    abrirDialogoDocumento() {
        this.mostrarSelectorDocumentos();
        //this.dlgDocumento = null;
        //this.dlgDocumentoGrupo = null;
        //this.dlgDocumentoRuta = null;
        //this.verModalDocumentoMaster = true;
    }

    agregarDocumento() {

        if (this.dlgDocumento == null || this.dlgDocumento == undefined) {
            this.mostrarMensajeAdvertencia("Seleccionar un documento");
            return;
        }
        if (this.bean.tipodocumentos.filter(x => x.tipodocumento == this.dlgDocumento).length != 0) {
            this.mostrarMensajeAdvertencia("Ya se ha seleccionada el documento");
            return;
        }

        var lst = [...this.bean.tipodocumentos];
        lst.push({ proceso: this.bean.proceso, tipodocumento: this.dlgDocumento, grupo: null, rutaplantilla: null, archivoString: null, tipodocumentoAux: null });
        this.bean.tipodocumentos = lst;

        this.verModalDocumentoMaster = false;
    }

    eliminarDocumento(tipodocumento: string) {
        var enFlujo = false;
        var mensaje = "No se puede eliminar, el documento es usado en el flujo : ";

        this.bean.flujos.forEach(
            flujo => {
                flujo.niveles.forEach(
                    nivel => {
                        nivel.documentos.forEach(
                            documento => {
                                if (documento.tipodocumento == tipodocumento) {
                                    enFlujo = true;
                                    mensaje = mensaje + flujo.descripcion + ', ';
                                }
                            }
                        );
                    }
                );
            }
        );

        if (enFlujo) {
            this.mostrarMensajeAdvertencia(mensaje.substring(0, mensaje.length - 2));
            return;
        }

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.bean.tipodocumentos];
                lst = lst.filter(x => x.tipodocumento != tipodocumento);
                this.bean.tipodocumentos = lst;
                this.desbloquearPagina();
            }
        });
    }

    verModalEstadoMaster: boolean = false;
    dlgEstado: string;
    dlgEstadoCodigo: string;

    abrirDialogoEstado() {
        this.dlgEstadoCodigo = null;
        this.dlgEstado = null;
        this.verModalEstadoMaster = true;
    }

    agregarEstado() {
        if (this.estaVacio(this.dlgEstado)) {
            this.mostrarMensajeAdvertencia("Ingresar un Id");
            return;
        }
        if (this.estaVacio(this.dlgEstado)) {
            this.mostrarMensajeAdvertencia("Ingresar una descripción");
            return;
        }
        if (this.bean.estados.filter(x => x.estado.toUpperCase().trim() == this.dlgEstadoCodigo.toUpperCase().trim()).length != 0) {
            this.mostrarMensajeAdvertencia("Ya se ha ingresado el Id");
            return;
        }

        var lst = [...this.bean.estados];
        lst.push({ proceso: this.bean.proceso, descripcion: this.dlgEstado.trim(), estado: this.dlgEstadoCodigo, tipo: null });
        this.bean.estados = lst;

        this.reloadEstados(this.bean.estados);

        this.verModalEstadoMaster = false;
    }

    eliminarEstado(estado: string) {

        if (this.bean.nivelestadoidinicial == estado) {
            this.mostrarMensajeAdvertencia("No se puede eliminar, el estado es el nivel inicial del proceso");
            return;
        }

        var enFlujo = false;
        var mensaje = "No se puede eliminar, el estado es usado en el flujo : ";

        this.bean.flujos.forEach(
            flujo => {
                flujo.niveles.forEach(
                    nivel => {
                        if (nivel.estado == estado) {
                            enFlujo = true;
                            mensaje = mensaje + flujo.descripcion + ', ';
                        }
                    }
                );
            }
        );

        if (enFlujo) {
            this.mostrarMensajeAdvertencia(mensaje.substring(0, mensaje.length - 2));
            return;
        }

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.bean.estados];
                lst = lst.filter(x => x.estado != estado);
                this.bean.estados = lst;
                this.reloadEstados(this.bean.estados);
                this.desbloquearPagina();
            }
        });
    }

    agregarFlujo() {

        if (this.bean.variables.length == 0) {
            this.mostrarMensajeAdvertencia("Ingresar Variables");
            return;
        }

        window.scrollTo(0, document.body.scrollHeight || document.documentElement.scrollHeight);

        var lst = [...this.bean.flujos];

        var secuencia = 0;

        this.bean.flujos.forEach(
            flujo => {
                if (flujo.flujo > secuencia) {
                    secuencia = flujo.flujo;
                }
            }
        );

        secuencia = secuencia + 1;

        lst.push({ proceso: this.bean.proceso, flujo: secuencia, descripcion: 'F' + secuencia, configuraciones: [], niveles: [] });
        this.bean.flujos = lst;
    }

    eliminarFlujo(flujo) {
        //criterio para validar al eliminar ?
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.bean.flujos];
                lst = lst.filter(x => x.flujo != flujo);
                this.bean.flujos = lst;
                if (this.flujoSeleccionado.flujo == flujo) {
                    this.flujoSeleccionado = new DtoWfFlujo();
                }
                this.desbloquearPagina();
            }
        });

    }

    agregarCriterio(flujo: number) {
        var lst = [...this.bean.flujos];
        var flujoBean = lst.find(x => x.flujo == flujo);
        var ind = lst.indexOf(flujoBean);

        var secuencia = 0;

        lst[ind].configuraciones.forEach(
            config => {
                if (config.configuracion > secuencia) {
                    secuencia = config.configuracion;
                }
            }
        );

        secuencia = secuencia + 1;

        lst[ind].configuraciones.push({ proceso: this.bean.proceso, flujo: flujo, variable: null, comparacion: null, valorDescripcion: null, valores: [], configuracion: secuencia });
        this.bean.flujos = lst;
    }

    eliminarCriterio(flujo: number, configuracion: number) {
        //criterio para validar al eliminar ?
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.bean.flujos];
                var flujoBean = lst.find(x => x.flujo == flujo);
                var ind = lst.indexOf(flujoBean);
                lst[ind].configuraciones = lst[ind].configuraciones.filter(x => x.configuracion != configuracion);
                this.bean.flujos = lst;
                this.desbloquearPagina();
            }
        });
    }

    lstCriterioVariables: SelectItem[] = [];
    configSeleccionada: DtoWfFlujoConfiguracion = new DtoWfFlujoConfiguracion();
    verModalConfiguracion: boolean = false;
    comparacionInicial: string;
    variableInicial: string;

    editarCriterio(flujo: number, configuracion: number) {
        this.lstCriterioVariables = [];
        this.lstCriterioVariables.push({ value: null, label: ' -- Seleccionar --' });

        var lst = [...this.bean.flujos];
        var flujoBean = lst.find(x => x.flujo == flujo);
        var ind = lst.indexOf(flujoBean);

        this.bean.variables.forEach(
            variable => {
                var descripcion = this.lstVariablesMaster.find(x => x.value == variable.variable).label;
                this.lstCriterioVariables.push({ value: variable.variable, label: descripcion });
            }
        );

        var temp = lst[ind].configuraciones.find(x => x.configuracion == configuracion)

        this.configSeleccionada = new DtoWfFlujoConfiguracion();
        this.configSeleccionada.flujo = flujo;
        this.configSeleccionada.configuracion = configuracion;
        this.configSeleccionada.variable = temp.variable;
        this.configSeleccionada.comparacion = temp.comparacion;
        this.comparacionInicial = temp.comparacion;
        this.variableInicial = temp.variable;

        this.verModalConfiguracion = true;
    }

    guardarConfiguracion() {
        if (this.configSeleccionada.variable == null || this.configSeleccionada.comparacion == null) {
            this.mostrarMensajeAdvertencia("Completar los datos");
            return;
        }
        if (this.configSeleccionada.comparacion != this.comparacionInicial || this.configSeleccionada.variable != this.variableInicial) {
            if (this.comparacionInicial != null) {
                this.confirmationService.confirm({
                    header: 'Confirmación',
                    icon: 'fa fa-question-circle',
                    message: "Si cambia el método de comparación, los valores se reiniciarán. ¿Desea continuar? ",
                    accept: () => {
                        this.guardarConfiguracionFinal(true);
                    }
                });
            }
            else {
                this.guardarConfiguracionFinal(false);
            }

        }
        else {
            this.guardarConfiguracionFinal(false);
        }
    }

    guardarConfiguracionFinal(resetValores: boolean) {

        var lst = [...this.bean.flujos];
        var flujoBean = lst.find(x => x.flujo == this.configSeleccionada.flujo);
        var ind = lst.indexOf(flujoBean);

        var configBean = lst[ind].configuraciones.find(x => x.configuracion == this.configSeleccionada.configuracion);
        var ind2 = lst[ind].configuraciones.indexOf(configBean);

        lst[ind].configuraciones[ind2].variable = this.configSeleccionada.variable;
        lst[ind].configuraciones[ind2].comparacion = this.configSeleccionada.comparacion;

        if (resetValores) {
            lst[ind].configuraciones[ind2].valorDescripcion = null;
            lst[ind].configuraciones[ind2].valores = [];
        }

        this.bean.flujos = lst;

        this.verModalConfiguracion = false;
    }

    verModalValores: boolean = false;
    verModalValores2: boolean = false;
    lstSelector: DtoTabla[] = [];
    lstSeleccionados: DtoTabla[] = [];
    valores0: any;
    valores1: any;

    editarValores(flujo: number, configuracion: number) {

        var lst = [...this.bean.flujos];
        var flujoBean = lst.find(x => x.flujo == flujo);
        var ind = lst.indexOf(flujoBean);

        var configBean = lst[ind].configuraciones.find(x => x.configuracion == configuracion);

        if (configBean.comparacion == null || configBean.variable == null) {
            this.mostrarMensajeAdvertencia("Asignar una variable y método de comparación");
            return;
        }

        this.configSeleccionada = new DtoWfFlujoConfiguracion();
        this.configSeleccionada.flujo = configBean.flujo;
        this.configSeleccionada.configuracion = configBean.configuracion;
        this.configSeleccionada.comparacion = configBean.comparacion;
        this.configSeleccionada.variable = configBean.variable;
        this.configSeleccionada.valorDescripcion = configBean.valorDescripcion;
        this.configSeleccionada.valores = [];

        configBean.valores.forEach(
            x => {
                this.configSeleccionada.valores.push(x);
            }
        );

        if (this.configSeleccionada.comparacion == 'BW') {
            if (configBean.valores.length == 0) {
                this.valores0 = null;
                this.valores1 = null;
            }
            else if (configBean.valores.length == 1) {
                this.valores0 = configBean.valores[0];
                this.valores1 = null;
            }
            else {
                this.valores0 = configBean.valores[0];
                this.valores1 = configBean.valores[1];
            }
        }

        if (this.configSeleccionada.comparacion == '>') {
            if (configBean.valores.length == 0) {
                this.valores0 = null;
            }
            else {
                this.valores0 = configBean.valores[0];
            }
        }

        if (this.configSeleccionada.comparacion == 'IN') {
            this.bloquearPagina();
            this.lstSelector = [];

            this.lstSeleccionados = [];
            var i = 0;
            this.configSeleccionada.valores.forEach(
                valorPrevio => {
                    var elegido = new DtoTabla();
                    elegido.codigo = valorPrevio;
                    elegido.descripcion = this.configSeleccionada.valorDescripcion.split('; ')[i];
                    this.lstSeleccionados.push(elegido);
                    i++;
                }
            );

            this.iniciarSelectorDinamico();
        }
        else {
            this.verModalValores = true;
        }

    }

    @ViewChild('dtDinamico') dtDinamico: Table;
    filtroSelectorDinamico: DtoSelectorDinamico = new DtoSelectorDinamico();

    iniciarSelectorDinamico() {
        this.filtroSelectorDinamico = new DtoSelectorDinamico();
        this.filtroSelectorDinamico.estadoId = this.configSeleccionada.variable;
        this.dtDinamico.reset();
    }

    buscarDinamico() {
        this.dtDinamico.reset();
    }

    buscarSelectorDinamico(event: LazyLoadEvent) {

        if (this.filtroSelectorDinamico.estadoId == null) {
            return;
        }

        this.bloquearPagina();

        this.filtroSelectorDinamico.paginacion.paginacionListaResultado = [];
        this.filtroSelectorDinamico.paginacion.paginacionRegistroInicio = event.first;
        this.filtroSelectorDinamico.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.wfProcesoServicio.listarSelectorpaginado(this.filtroSelectorDinamico).then(
            lista => {
                this.filtroSelectorDinamico.paginacion = lista;
                this.verModalValores2 = true;
                this.desbloquearPagina();
            }
        );
    }

    agregarValorSeleccionado(codigo, desccripcion) {
        if (this.lstSeleccionados.filter(x => x.codigo == codigo).length > 0) {
            this.mostrarMensajeAdvertencia("El registro ya se encuentra seleccionado");
            return;
        }
        var temp = [...this.lstSeleccionados];
        var nuevo = new DtoTabla();
        nuevo.codigo = codigo;
        nuevo.descripcion = desccripcion;
        temp.push(nuevo);
        this.lstSeleccionados = temp;
    }

    eliminarValorSeleccionado(codigo) {
        this.lstSeleccionados = this.lstSeleccionados.filter(x => x.codigo != codigo);
    }

    actualizarValores() {
        var lst = [...this.bean.flujos];
        var flujoBean = lst.find(x => x.flujo == this.configSeleccionada.flujo);
        var ind = lst.indexOf(flujoBean);

        var configBean = lst[ind].configuraciones.find(x => x.configuracion == this.configSeleccionada.configuracion);
        var ind2 = lst[ind].configuraciones.indexOf(configBean);


        if (this.configSeleccionada.comparacion == 'BW') {
            if (this.valores0 == null || this.valores0 == undefined || this.valores1 == null || this.valores1 == undefined) {
                this.mostrarMensajeAdvertencia("Ingresar los valores");
                return;
            }
            lst[ind].configuraciones[ind2].valorDescripcion = '' + this.valores0 + ', ' + this.valores1;
            lst[ind].configuraciones[ind2].valores = [this.valores0, this.valores1];
        }
        else if (this.configSeleccionada.comparacion == '>') {
            if (this.valores0 == null || this.valores0 == undefined) {
                this.mostrarMensajeAdvertencia("Ingresar el valor");
                return;
            }
            lst[ind].configuraciones[ind2].valorDescripcion = this.valores0;
            lst[ind].configuraciones[ind2].valores = [this.valores0];
        }
        else if (this.configSeleccionada.comparacion == 'IN') {
            var valoresDescripcion = '';
            var valores = [];
            this.lstSeleccionados.forEach(
                x => {
                    valores.push(x.codigo);
                    valoresDescripcion = valoresDescripcion + x.descripcion + '; ';
                }
            );
            if (valoresDescripcion.length > 0) {
                valoresDescripcion = valoresDescripcion.substring(0, valoresDescripcion.length - 2);
            }
            lst[ind].configuraciones[ind2].valorDescripcion = valoresDescripcion;
            lst[ind].configuraciones[ind2].valores = valores;
        }

        this.bean.flujos = lst;

        this.verModalValores = false;
        this.verModalValores2 = false;
    }

    verNiveles(flujo: number) {
        var lst = [...this.bean.flujos];
        this.flujoSeleccionado = lst.find(x => x.flujo == flujo);
    }

    quitarNivel0() {
        this.flujoSeleccionado.niveles = this.flujoSeleccionado.niveles.filter(x => x.nivel != 0);
    }

    agregarNivel0() {
        if (this.flujoSeleccionado.flujo == null) {
            this.mostrarMensajeAdvertencia("Seleccionar un nivel");
            return;
        }

        if (this.bean.estados.length == 0) {
            this.mostrarMensajeAdvertencia("Ingresar Estados");
            return;
        }

        if (this.flujoSeleccionado.niveles.filter(x => x.nivel == 0).length > 0) {
            this.mostrarMensajeAdvertencia("Ya hay un nivel 0");
            return;
        }

        var obj = {
            proceso: this.flujoSeleccionado.proceso,
            flujo: this.flujoSeleccionado.flujo, nivel: 0, estado: null, correojefe: false, correosolicitante: false, correootros: null,
            aprobadores: [], documentos: [], jefeAux: null, soliAux: null, spaprobar: null, sprechazar: null, spdevolver: null, spvalidar: null, api: null,
            acciones: [], componenteweb: null, origenDatos: null, tipoaprobador: "PERS", condicionaprobacion: "SOLOUNO", btnAprobar: true, btnDevolver: true,
            btnNotificar: false, btnRechazar: true, auxFlagPlanificacionEditar: false, auxFlagPlanificacionVer: false, duracioncantidad: null, duraciontipo: null,
            nombre: null, auxFlgPlanificacionValidar: null, planificacionTag: null,
            auxDocumentoFlgColumnaNuevo: false,
            auxDocumentoFlgColumnaGrupo: false,
            documentoFlgColumnaGrupo: 'N',
            documentoFlgColumnaNuevo: 'N',
            auxFlgComentarioDetalladoAprobar: false,
            auxFlgComentarioDetalladoRechazar: false,
            auxFlgComentarioDetalladoDevolver: false,
            flgComentarioDetalladoAprobar: 'N',
            flgComentarioDetalladoRechazar: 'N',
            flgComentarioDetalladoDevolver: 'N',
            auxFlgCorreoPersonaReferencia: false,
            flgCorreoPersonaReferencia: "N",
            auxFlgCorreoTransaccion: false,
            flgCorreoTransaccion: "N",
            auxFlgCorreoPersona: true,
            flgCorreoPersona: "S",
            auxFlgAprobarComentario: false,
            flgAprobarComentario: "N",
            auxFlgCorreoAdjuntarDocumentos: false,
            flgCorreoAdjuntarDocumentos: 'N',
            tienePlantillaAprobar: 'N',
            tienePlantillaRechazar: 'N',
            tienePlantillaDevolver: 'N'
        };

        var temp = [...this.flujoSeleccionado.niveles];

        temp.unshift(obj);

        this.flujoSeleccionado.niveles = temp;
    }

    agregarNivel() {

        if (this.flujoSeleccionado.flujo == null) {
            this.mostrarMensajeAdvertencia("Seleccionar un nivel");
            return;
        }

        if (this.bean.estados.length == 0) {
            this.mostrarMensajeAdvertencia("Ingresar Estados");
            return;
        }

        var secuencia = 0;

        this.flujoSeleccionado.niveles.forEach(
            config => {
                if (config.nivel > secuencia) {
                    secuencia = config.nivel;
                }
            }
        );

        secuencia = secuencia + 1;

        this.flujoSeleccionado.niveles.push({
            proceso: this.flujoSeleccionado.proceso,
            flujo: this.flujoSeleccionado.flujo, nivel: secuencia, estado: null, correojefe: false, correosolicitante: false, correootros: null,
            aprobadores: [], documentos: [], jefeAux: null, soliAux: null, spaprobar: null, sprechazar: null, spdevolver: null, spvalidar: null, api: null,
            acciones: [], componenteweb: null, origenDatos: null, tipoaprobador: "PERS", condicionaprobacion: "SOLOUNO", btnAprobar: true, btnDevolver: true,
            btnNotificar: false, btnRechazar: true, auxFlagPlanificacionEditar: false, auxFlagPlanificacionVer: false, duracioncantidad: null, duraciontipo: null,
            nombre: null, auxFlgPlanificacionValidar: null, planificacionTag: null,
            auxDocumentoFlgColumnaNuevo: false,
            auxDocumentoFlgColumnaGrupo: false,
            documentoFlgColumnaGrupo: 'N',
            documentoFlgColumnaNuevo: 'N',
            auxFlgComentarioDetalladoAprobar: false,
            auxFlgComentarioDetalladoRechazar: false,
            auxFlgComentarioDetalladoDevolver: false,
            flgComentarioDetalladoAprobar: 'N',
            flgComentarioDetalladoRechazar: 'N',
            flgComentarioDetalladoDevolver: 'N',
            auxFlgCorreoPersonaReferencia: false,
            flgCorreoPersonaReferencia: "N",
            auxFlgCorreoTransaccion: false,
            flgCorreoTransaccion: "N",
            auxFlgCorreoPersona: true,
            flgCorreoPersona: "S",
            auxFlgAprobarComentario: false,
            flgAprobarComentario: "N",
            auxFlgCorreoAdjuntarDocumentos: false,
            flgCorreoAdjuntarDocumentos: 'N',
            tienePlantillaAprobar: 'N',
            tienePlantillaRechazar: 'N',
            tienePlantillaDevolver: 'N'
        });

    }

    nivelActualizando: number;

    verOtros(nivel: number) {
        this.nivelActualizando = nivel;
        var correos = this.flujoSeleccionado.niveles.find(x => x.nivel == nivel).correootros;
        if (this.estaVacio(correos)) {
            correos = '';
        }

        this.lstCorreos = [];
        if (!this.estaVacio(correos)) {
            var correosSplit = correos.split(";");
            correosSplit.forEach(
                correoPrevio => {
                    var correoBean = new DtoTabla();
                    correoBean.descripcion = correoPrevio;
                    this.lstCorreos.push(correoBean);
                }
            );
        }
        this.verModalCorreo = true;
    }

    eliminarCorreo(r: DtoTabla) {
        var temp = [...this.lstCorreos];
        temp = temp.filter(x => x != r);
        this.lstCorreos = temp;
    }

    guardarCorreosOtros() {
        var valida = true;
        var regexp = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        var unit = true;

        this.lstCorreos.forEach(reg => {
            unit = regexp.test(reg.descripcion);
            if (!unit) {
                valida = false;
            }
        });

        if (!valida) {
            this.mostrarMensajeAdvertencia("Colocar correos válidos");
            return;
        }

        var correos = '';

        var lstCorreoSinRepetir: DtoTabla[] = [];
        this.lstCorreos.forEach(
            c => {
                var encontrado = lstCorreoSinRepetir.filter(x => x.descripcion.toUpperCase().trim() == c.descripcion.toUpperCase().trim()).length;
                if (encontrado == 0) {
                    lstCorreoSinRepetir.push(c);
                }
            }
        );

        lstCorreoSinRepetir.forEach(
            correo => {
                if (!this.estaVacio(correo.descripcion)) {
                    correos = correos + correo.descripcion + ';';
                }
            }
        );

        if (correos.length > 0) {
            correos = correos.substring(0, correos.length - 1);
        }

        var temp = [...this.flujoSeleccionado.niveles];
        var bean = temp.find(x => x.nivel == this.nivelActualizando);
        var ind = temp.indexOf(bean);
        temp[ind].correootros = correos;
        this.verModalCorreo = false;
    }

    lstTiposDocumentosBean: SelectItem[] = [];
    documentoModal: string;
    requeridoModal: boolean;
    digitalModal: boolean;
    imagenModal: boolean;
    editableModal: boolean;

    agregarDocumentoModal(nivel: number) {

        if (this.bean.tipodocumentos.length == 0) {
            this.mostrarMensajeAdvertencia("Ingresar Documentos");
            return;
        }

        this.abrirModalDocumentoXNivel(nivel);

        /*
        this.lstTiposDocumentosBean = [];
        this.lstTiposDocumentosBean.push({ value: null, label: ' -- Seleccione --' });
        this.bean.tipodocumentos.forEach(
            x => {
                var des = this.lstTiposDocumentosMaster.find(y => y.value == x.tipodocumento).label;
                this.lstTiposDocumentosBean.push({ value: x.tipodocumento, label: des });
            }
        );

        this.documentoModal = null;
        this.requeridoModal = true;
        this.digitalModal = false;
        this.imagenModal = false;
        this.editableModal = false;

        this.nivelActualizando = nivel;
        this.verModalDocumento = true;
        */
    }

    guardarDocumentoBean() {

        if (this.documentoModal == null) {
            this.mostrarMensajeAdvertencia("Seleccionar el documento");
            return;
        }
        var temp = [...this.flujoSeleccionado.niveles];
        var bean = temp.find(x => x.nivel == this.nivelActualizando);

        if (bean.documentos.filter(x => x.tipodocumento == this.documentoModal).length > 0) {
            this.mostrarMensajeAdvertencia("El documento ya ha sido seleccionado");
            return;
        }

        var ind = temp.indexOf(bean);
        temp[ind].documentos.push({ des: (this.lstTiposDocumentosBean.find(x => x.value == this.documentoModal).label), proceso: this.bean.proceso, flujo: this.flujoSeleccionado.flujo, nivel: this.nivelActualizando, tipodocumento: this.documentoModal, firmadigital: this.digitalModal, firmaimagen: this.imagenModal, requerido: this.requeridoModal, requeridoA: null, firmadigitalA: null, firmaimagenA: null, editable: this.editableModal, editableA: null });
        this.flujoSeleccionado.niveles = temp;
        this.verModalDocumento = false;
    }

    eliminarDocumentoNivel(nivel: number, tipodocumento: string) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var temp = [...this.flujoSeleccionado.niveles];
                var bean = temp.find(x => x.nivel == nivel);
                bean.documentos = bean.documentos.filter(x => x.tipodocumento != tipodocumento);
                this.flujoSeleccionado.niveles = temp;
            }
        });
    }

    eliminarAprobadorNivel(nivel: number, empleado: number) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var temp = [...this.flujoSeleccionado.niveles];
                var bean = temp.find(x => x.nivel == nivel);
                bean.aprobadores = bean.aprobadores.filter(x => x.empleado != empleado);
                this.flujoSeleccionado.niveles = temp;
            }
        });
    }

    agregarEmpleadoModal(nivel: number) {
        this.nivelActualizando = nivel;
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO-NIVEL', 'EMOT'));
    }

    cargarEmpleado(data) {
        var temp = [...this.flujoSeleccionado.niveles];
        var bean = temp.find(x => x.nivel == this.nivelActualizando);

        if (bean.aprobadores.filter(x => x.empleado == data.persona).length > 0) {
            this.mostrarMensajeAdvertencia("El aprobador ya ha sido seleccionado");
            return;
        }

        var ind = temp.indexOf(bean);
        temp[ind].aprobadores.push({ proceso: this.bean.proceso, flujo: this.flujoSeleccionado.flujo, nivel: this.nivelActualizando, empleado: data.persona, nombreEmpleado: data.busqueda, valorDescripcion: null, valores: [] });
        this.flujoSeleccionado.niveles = temp;
    }


    agregarJefeDirecto(nivel: number) {
        this.nivelActualizando = nivel;
        var temp = [...this.flujoSeleccionado.niveles];
        var bean = temp.find(x => x.nivel == this.nivelActualizando);
        if (bean.aprobadores.filter(x => x.empleado == 99999999).length > 0) {
            this.mostrarMensajeAdvertencia("El aprobador ya ha sido seleccionado");
            return;
        }
        var ind = temp.indexOf(bean);
        temp[ind].aprobadores.push({ proceso: this.bean.proceso, flujo: this.flujoSeleccionado.flujo, nivel: this.nivelActualizando, empleado: 99999999, nombreEmpleado: 'Jefe Directo', valorDescripcion: null, valores: [] });
        this.flujoSeleccionado.niveles = temp;
    }

    verModalNivel: boolean = false;
    verModalNivel2: boolean = false;
    dlgNivelSPV: string;
    dlgNivelSPA: string;
    dlgNivelSPR: string;
    dlgNivelSPD: string;
    dlgNivelAPI: string;
    dlgAprobar: boolean;
    dlgRechazar: boolean;
    dlgDevolver: boolean;
    dlgNivelcomponente: string;
    dlgOrigen: number;

    dlgNivelNombre: string;
    dlgNivelDuracionTipo: string;
    dlgNivelDuracionCantidad: number;
    dlgNivelPlaniVer: boolean;
    dlgNivelPlaniEdi: boolean;
    dlgNivelPlanificacionTag: string;
    dlgNivelPlaniValidar: boolean;
    dlgNivelDocumentoNuevo: boolean;
    dlgNivelDocumentoGrupo: boolean;

    dlgComentarioAprobar: boolean;
    dlgComentarioRechazar: boolean;
    dlgComentarioDevolver: boolean;

    dlgComentarioAprobarAlAprobar: boolean;

    nivelEditando: number;

    lstDestinos: SelectItem[] = [];

    editarNivel(nivel: number) {
        this.nivelEditando = nivel;
        var temp = this.flujoSeleccionado.niveles.filter(x => x.nivel == nivel);
        if (temp.length > 0) {
            this.botones = [];
            this.armarDestinos();
            this.dlgNivelSPV = temp[0].spvalidar;
            this.dlgNivelSPA = temp[0].spaprobar;
            this.dlgNivelSPR = temp[0].sprechazar;
            this.dlgNivelSPD = temp[0].spdevolver;
            this.dlgNivelAPI = temp[0].api;
            this.dlgAprobar = temp[0].btnAprobar;
            this.dlgDevolver = temp[0].btnDevolver;
            this.dlgRechazar = temp[0].btnRechazar;
            this.dlgOrigen = temp[0].origenDatos;
            this.botones = temp[0].acciones;
            this.dlgNivelcomponente = temp[0].componenteweb;
            this.dlgNivelDocumentoGrupo = temp[0].auxDocumentoFlgColumnaGrupo;
            this.dlgNivelDocumentoNuevo = temp[0].auxDocumentoFlgColumnaNuevo;

            this.dlgComentarioAprobar = temp[0].auxFlgComentarioDetalladoAprobar;
            this.dlgComentarioRechazar = temp[0].auxFlgComentarioDetalladoRechazar;
            this.dlgComentarioDevolver = temp[0].auxFlgComentarioDetalladoDevolver;
            this.dlgComentarioAprobarAlAprobar = temp[0].auxFlgAprobarComentario;


            this.verModalNivel = true;
        }
    }

    armarDestinos() {
        this.lstDestinos = [];
        this.lstDestinos.push({ value: null, label: ' -- Seleccione -- ' });
        this.lstDestinos.push({ value: 0, label: ' 0 -  Inicio ' });
        this.flujoSeleccionado.niveles.forEach(
            n => {
                var nombre = this.estaVacio(n.nombre) ? '' : n.nombre;
                this.lstDestinos.push({ value: n.nivel, label: n.nivel + ' - ' + nombre });
            }
        );
    }

    editarNivelPlanificacion(nivel: number) {
        this.nivelEditando = nivel;
        var temp = this.flujoSeleccionado.niveles.filter(x => x.nivel == nivel);
        if (temp.length > 0) {
            this.dlgNivelNombre = temp[0].nombre;
            this.dlgNivelDuracionTipo = temp[0].duraciontipo;
            this.dlgNivelDuracionCantidad = temp[0].duracioncantidad;
            this.dlgNivelPlaniVer = temp[0].auxFlagPlanificacionVer;
            this.dlgNivelPlaniEdi = temp[0].auxFlagPlanificacionEditar;

            this.dlgNivelPlaniValidar = temp[0].auxFlgPlanificacionValidar;
            this.dlgNivelPlanificacionTag = temp[0].planificacionTag;

            this.verModalNivel2 = true;
        }
    }

    cambiarTipoAprobadorNivel(nivel: number) {
        var temp = [...this.flujoSeleccionado.niveles];
        var n = temp[temp.indexOf(temp.find(x => x.nivel == nivel))];
        if (n.tipoaprobador != 'PERS') {
            //n.aprobadores = [];
        }
        this.flujoSeleccionado.niveles = temp;
    }

    actualizarNivel() {

        //validar que si ha seleecionado un destino, debe haber un subaccion
        //las subacciones no deben repetirse

        var valida = true;

        this.botones.forEach(
            x => {
                if (!this.estaVacio(x.subaccion)) {
                    if (this.botones.filter(yy => yy.subaccion == x.subaccion).length > 1) {
                        valida = false;
                        this.mostrarMensajeAdvertencia("El estado " + x.subaccion + " no puede repetirse");
                    }
                }

                if (x.nivelDestinoId != null && this.estaVacio(x.subaccion)) {
                    valida = false;
                    this.mostrarMensajeAdvertencia("El estado es requerido para el boton " + x.nombre);
                }

                if (x.nivelDestinoId != null && x.accionwf == 'R') {
                    valida = false;
                    this.mostrarMensajeAdvertencia("El destino no aplica al comando rechazar");
                }
            }
        );

        if (!valida) {
            return;
        }

        var temp = [...this.flujoSeleccionado.niveles];
        var n = temp[temp.indexOf(temp.find(x => x.nivel == this.nivelEditando))];
        n.spvalidar = this.dlgNivelSPV;
        n.spaprobar = this.dlgNivelSPA;
        n.sprechazar = this.dlgNivelSPR;
        n.spdevolver = this.dlgNivelSPD;
        n.api = this.dlgNivelAPI;
        n.btnAprobar = this.dlgAprobar;
        n.btnDevolver = this.dlgDevolver;
        n.btnRechazar = this.dlgRechazar;
        n.origenDatos = this.dlgOrigen;
        n.acciones = this.botones;
        n.componenteweb = this.dlgNivelcomponente;
        n.auxDocumentoFlgColumnaNuevo = this.dlgNivelDocumentoNuevo;
        n.auxDocumentoFlgColumnaGrupo = this.dlgNivelDocumentoGrupo;
        n.auxFlgComentarioDetalladoAprobar = this.dlgComentarioAprobar;
        n.auxFlgComentarioDetalladoRechazar = this.dlgComentarioRechazar;
        n.auxFlgComentarioDetalladoDevolver = this.dlgComentarioDevolver;
        n.auxFlgAprobarComentario = this.dlgComentarioAprobarAlAprobar
        this.flujoSeleccionado.niveles = temp;
        this.verModalNivel = false;
    }

    actualizarNivel2() {
        var temp = [...this.flujoSeleccionado.niveles];
        var n = temp[temp.indexOf(temp.find(x => x.nivel == this.nivelEditando))];
        n.auxFlagPlanificacionEditar = this.dlgNivelPlaniEdi;
        n.auxFlagPlanificacionVer = this.dlgNivelPlaniVer;
        n.nombre = this.dlgNivelNombre;
        n.duracioncantidad = this.dlgNivelDuracionCantidad;
        n.duraciontipo = this.dlgNivelDuracionTipo;
        n.planificacionTag = this.dlgNivelPlanificacionTag;
        n.auxFlgPlanificacionValidar = this.dlgNivelPlaniValidar;
        this.flujoSeleccionado.niveles = temp;
        this.verModalNivel2 = false;
    }

    eliminarNivel(nivel: number) {
        //buscar si un nivel lo tiene como destino
        var usado = false;
        var nivelUso = 0;
        this.flujoSeleccionado.niveles.filter(x => {
            x.acciones.forEach(
                a => {
                    if (a.nivelDestinoId == nivel) {
                        nivelUso = a.nivel;
                        usado = true;
                    }
                }
            );
        });

        if (usado) {
            this.mostrarMensajeAdvertencia("No se puede eliminar, el nivel " + nivelUso + ' lo tiene como destino en sus acciones');
            return;
        }

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var temp = [...this.flujoSeleccionado.niveles];
                temp = temp.filter(x => x.nivel != nivel);
                this.flujoSeleccionado.niveles = temp;
            }
        });
    }

    validar() {
        var valida = true;

        if (this.estaVacio(this.bean.proceso)) {
            this.mostrarMensajeAdvertencia("Ingresar el código");
            return false;
        }

        if (this.estaVacio(this.bean.descripcion)) {
            this.mostrarMensajeAdvertencia("Ingresar la descripción del proceso");
            return false;
        }

        if (this.estaVacio(this.bean.aplicacion)) {
            this.mostrarMensajeAdvertencia("Seleccionar la aplicación del proceso");
            return false;
        }

        if (this.estaVacio(this.bean.estado)) {
            this.mostrarMensajeAdvertencia("Seleccionar el estado");
            return false;
        }

        if (this.estaVacio(this.bean.nivelestadoidinicial)) {
            this.mostrarMensajeAdvertencia("Seleccione el estado inicial");
            return false;
        }

        if (this.bean.estados.filter(x => x.tipo == 'RECH').length > 1) {
            this.mostrarMensajeAdvertencia("Solo puede haber un estado de tipo Rechazado");
            return false;
        }

        if (this.bean.flujos.length == 0) {
            this.mostrarMensajeAdvertencia("Ingresar un flujo como mínimo");
            return false;
        }

        for (let index = 0; index < this.bean.flujos.length && valida; index++) {
            var flujo = this.bean.flujos[index];
            if (flujo.configuraciones.length == 0) {
                this.mostrarMensajeAdvertencia("El flujo " + flujo.descripcion + " debe tener configuraciones");
                valida = false;
            }
        }

        if (!valida) {
            return valida;
        }

        for (let index = 0; index < this.bean.flujos.length && valida; index++) {
            var flujo = this.bean.flujos[index];
            for (let j = 0; j < flujo.configuraciones.length && valida; j++) {
                let config = flujo.configuraciones[j];
                if (config.comparacion == null || config.variable == null || config.valores.length == 0) {
                    this.mostrarMensajeAdvertencia("Completar las configuraciones del flujo " + flujo.descripcion);
                    valida = false;
                }
            }
        }

        if (!valida) {
            return valida;
        }

        for (let index = 0; index < this.bean.flujos.length && valida; index++) {
            var flujo = this.bean.flujos[index] as DtoWfFlujo;
            if (flujo.niveles.length == 0) {
                this.mostrarMensajeAdvertencia("El flujo " + flujo.descripcion + " debe tener niveles");
                valida = false;
            }
        }

        if (!valida) {
            return valida;
        }

        for (let index = 0; index < this.bean.flujos.length && valida; index++) {
            var flujo = this.bean.flujos[index];
            for (let j = 0; j < flujo.niveles.length && valida; j++) {
                let nivel = flujo.niveles[j];
                if (nivel.nivel != 0) {

                    if (nivel.tipoaprobador == null) {
                        this.mostrarMensajeAdvertencia("Completar el tipo de aprobador en el nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                        valida = false;
                    }

                    if (this.estaVacio(nivel.nombre)) {
                        this.mostrarMensajeAdvertencia("Completar el nombre del nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                        valida = false;
                    }

                    if (nivel.tipoaprobador == 'PERS') {
                        if (nivel.aprobadores.length == 0) {
                            this.mostrarMensajeAdvertencia("Completar los aprobadores en el nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                            valida = false;
                        }
                        //Validar la condicion de aprobacion
                        if (nivel.condicionaprobacion == 'TODOSUNAPR') {
                            if (nivel.aprobadores.filter(x => x.valores.length == 0).length > 0) {
                                this.mostrarMensajeAdvertencia("Asignar Segmentos a los aprobadores en el nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                                valida = false;
                            }
                        }
                    }
                    else {
                        if (nivel.tipoaprobador != 'TRAP') {
                            nivel.condicionaprobacion = "SOLOUNO";
                            nivel.aprobadores = [];
                        }
                    }

                    if (nivel.estado == null) {
                        this.mostrarMensajeAdvertencia("Completar el estado en el nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                        valida = false;
                    }

                    //si tiene el flag de planificacion, validar que se llene el nombre, tipo y duracion
                    if (this.bean.flagPlanificacionGenerar) {
                        if (this.estaVacio(nivel.nombre) || this.estaVacio(nivel.duraciontipo) || nivel.duracioncantidad == null || nivel.duracioncantidad <= 0) {
                            this.mostrarMensajeAdvertencia("Completar los datos de planificación en el nivel " + nivel.nivel + " del flujo " + flujo.descripcion);
                            valida = false;
                        }
                    }
                }
            }
        }

        if (!valida) {
            return valida;
        }


        return valida;
    }

    guardar() {
        if (!this.validar()) {
            return;
        }

        this.bloquearPagina();

        if (this.accion == this.ACCIONES.NUEVO) {
            this.wfProcesoServicio.registrarProceso(this.bean).then(
                x => {
                    this.desbloquearPagina();
                    if (x != null) {
                        this.mostrarMensajeExito(this.getMensajeGrabadoSinCodigo());
                        this.coreSalir();
                    }
                }
            );
        }
        else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.COPIAR) {
            if (this.accion == this.ACCIONES.COPIAR) {
                this.bean.auxEsNuevaVersion = 'S';
                this.bean.version = -1;
            }
            else {
                this.bean.auxEsNuevaVersion = 'N';
            }
            this.wfProcesoServicio.actualizaProceso(this.bean).then(
                x => {
                    this.desbloquearPagina();
                    if (x != null) {
                        this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
                        this.coreSalir();
                    }
                }
            );
        }
    }

    botones: DtoWfFlujoNivelAccion[] = [];

    eliminarAccion(accionid: number) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                var lst = [...this.botones];
                lst = lst.filter(x => x.accion != accionid);
                this.botones = lst;
                this.desbloquearPagina();
            }
        });
    }

    agregarAccion() {
        var lst = [...this.botones];
        var nuevo = new DtoWfFlujoNivelAccion();
        nuevo.accion = 0;
        nuevo.accionwf = "A";
        this.botones.forEach(
            x => {
                if (x.accion > nuevo.accion) {
                    nuevo.accion = x.accion;
                }
            }
        );
        nuevo.accion = nuevo.accion + 1;
        lst.push(nuevo);
        this.botones = lst;
    }


    coreMensaje(mensage: MensajeController) {
        if (mensage.componente == 'EMPLEADO-NIVEL') {
            this.cargarEmpleado(mensage.resultado);
        }
        if (mensage.componente == 'ADMIN') {
            this.bean.administradorId = mensage.resultado.persona;
            this.bean.administradorNombre = mensage.resultado.busqueda;
        }
    }
    coreAccion(accion: string) { }


    volverANiveles() {
        this.flujoSeleccionado = null;
    }

    adjustHexOpacity(color) {
        const r = parseInt(color.slice(1, 3), 16);
        const g = parseInt(color.slice(3, 5), 16);
        const b = parseInt(color.slice(5, 7), 16);
        return 'rgba(' + r + ', ' + g + ', ' + b + ', ' + 0.08 + ')';
    }

    aprobadorActualizando: number;

    abrirSegmentos(nivel: number, empleado: number) {

        this.nivelActualizando = nivel;
        this.aprobadorActualizando = empleado;

        var temp = [...this.flujoSeleccionado.niveles];
        var niveles = this.flujoSeleccionado.niveles.find(x => x.nivel == nivel);
        var aprobador = niveles.aprobadores.find(x => x.empleado == empleado);
        var indiceAprobador = niveles.aprobadores.indexOf(aprobador);

        if (this.estaVacio(this.bean.segmentocodigotabla)) {
            aprobador.valores = [];
            aprobador.valorDescripcion = "";
            niveles.aprobadores[indiceAprobador] = aprobador;
            this.flujoSeleccionado.niveles = temp;
            this.mostrarMensajeAdvertencia("Seleccionar el 'Segmento Tabla' en el Tab 'Información General'");
            return;
        }

        this.lstSeleccionados = [];
        var i = 0;
        aprobador.valores.forEach(
            valorPrevio => {
                var elegido = new DtoTabla();
                elegido.codigo = valorPrevio;
                elegido.descripcion = aprobador.valorDescripcion.split('; ')[i];
                this.lstSeleccionados.push(elegido);
                i++;
            }
        );

        this.lstSegmentosDisponibles = [];

        this.maMiscelaneosdetalleComunService.listarTablaPorHeaderActivos(this.bean.aplicacion, this.bean.segmentocodigotabla, ConstanteWorkflow.DEFECTO_COMPANIA).then(
            misc => {
                this.lstSegmentosDisponibles = misc;
                this.verModalValores3 = true;
            }
        )
    }

    agregarValorSeleccionado2(codigo, desccripcion) {
        if (this.lstSeleccionados.filter(x => x.codigo == codigo).length > 0) {
            this.mostrarMensajeAdvertencia("El registro ya se encuentra seleccionado");
            return;
        }
        var temp = [...this.lstSeleccionados];
        var nuevo = new DtoTabla();
        nuevo.codigo = codigo;
        nuevo.descripcion = desccripcion;
        temp.push(nuevo);
        this.lstSeleccionados = temp;
    }

    eliminarValorSeleccionado2(codigo) {
        this.lstSeleccionados = this.lstSeleccionados.filter(x => x.codigo != codigo);
    }

    actualizarValores2() {
        var valoresDescripcion = '';
        var valores = [];
        this.lstSeleccionados.forEach(
            x => {
                valores.push(x.codigo);
                valoresDescripcion = valoresDescripcion + x.descripcion + '; ';
            }
        );
        if (valoresDescripcion.length > 0) {
            valoresDescripcion = valoresDescripcion.substring(0, valoresDescripcion.length - 2);
        }


        var temp = [...this.flujoSeleccionado.niveles];
        var niveles = this.flujoSeleccionado.niveles.find(x => x.nivel == this.nivelActualizando);
        var aprobador = niveles.aprobadores.find(x => x.empleado == this.aprobadorActualizando);
        var indiceAprobador = niveles.aprobadores.indexOf(aprobador);

        aprobador.valorDescripcion = valoresDescripcion;
        aprobador.valores = valores;

        niveles.aprobadores[indiceAprobador] = aprobador;
        this.flujoSeleccionado.niveles = temp;

        this.verModalValores3 = false;
    }

    obtenerNombreEnLista(cadena: string): string[] {
        if (this.estaVacio(cadena)) {
            return [];
        }
        return cadena.split(";");
    }

    verModalValores3: boolean = false;
    lstSegmentosDisponibles: DtoTabla[] = [];

    verPlantilla(tipodocumento: string) {
        const doc = this.bean.tipodocumentos.find(x => x.tipodocumento == tipodocumento);
        if (doc === null) {
            return;
        }
        if (doc.archivoString == null) {
            this.mostrarMensajeAdvertencia("No hay una plantilla");
            return;
        }
        else {
            const a = document.createElement('a');
            if (!doc.archivoString.startsWith("data:")) {
                a.href = 'data:application/octet-stream;base64,' + doc.archivoString;
            } else {
                a.href = doc.archivoString;
            }
            a.setAttribute('download', this.reemplazarRuta(doc.rutaplantilla, ',', '_'));
            const b = document.createEvent('MouseEvents');
            b.initEvent('click', false, true);
            a.dispatchEvent(b);
        }
    }

    documentoSubiendo: string;

    subirDocumentoPre(doc: string) {
        this.documentoSubiendo = doc;
    }

    subirDocumento(event: any) {
        this.bloquearPagina()

        var temp = [...this.bean.tipodocumentos];
        var doc = temp.find(x => x.tipodocumento == this.documentoSubiendo);

        var files = event.files;

        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > 3000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 3 + 'mb');
            this.desbloquearPagina();
            return;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }
            doc.archivoString = result.toString().split(",")[1];
            doc.rutaplantilla = event.files[0].name;
            this.desbloquearPagina();
        };
    }

    buscarAdministrador() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ADMIN', 'EMOT'));
    }
    limpiarAdministrador() {
        this.bean.administradorId = null;
        this.bean.administradorNombre = null;
    }

    verModalDocxNivel: boolean = false;

    lstDocumentosPorProceso: DtoWfFlujoNivelDocumento[] = [];
    lstDocumentosPorProcesoSeleccionados: DtoWfFlujoNivelDocumento[] = [];

    abrirModalDocumentoXNivel(nivel: number) {

        this.nivelActualizando = nivel;

        var temp = [...this.flujoSeleccionado.niveles];
        var nivelBean = temp.find(x => x.nivel == this.nivelActualizando);

        //ARMAR REGISTROS CON LOS DOCUMENTOS DE LA CABECERA
        this.lstDocumentosPorProcesoSeleccionados = [];
        this.lstDocumentosPorProceso = [];
        this.bean.tipodocumentos.forEach(
            row => {
                var reg = new DtoWfFlujoNivelDocumento();
                reg.tipodocumento = row.tipodocumento;
                reg.requerido = false;
                reg.editable = false;
                this.lstDocumentosPorProceso.push(reg);
                //VER SI FUE SELECCIONADO PARA ARMAR LOS REGISTROS SELECCIONADOS
                var seleccionado = nivelBean.documentos.find(x => x.tipodocumento == row.tipodocumento);
                if (seleccionado) {
                    reg.requerido = seleccionado.requerido;
                    reg.editable = seleccionado.editable;
                    reg.firmadigital = seleccionado.firmadigital;
                    reg.firmaimagen = seleccionado.firmaimagen;
                    this.lstDocumentosPorProcesoSeleccionados.push(reg);
                }
            }
        );

        this.verModalDocxNivel = true;
    }
    guardarDocumentosXNivel() {
        var temp = [...this.flujoSeleccionado.niveles];
        var nivelBean = temp.find(x => x.nivel == this.nivelActualizando);
        var ind = temp.indexOf(nivelBean);
        temp[ind].documentos = this.lstDocumentosPorProcesoSeleccionados;
        this.flujoSeleccionado.niveles = temp;
        this.verModalDocxNivel = false;
    }

    nombreEstadoEdicion: string;
    tipoEstadoEdicion: string;
    codigoEstadoEdicion: string;
    verDialogoEstadoEdicion: boolean = false;

    editarEstado(codigo) {
        var e = this.bean.estados.find(x => x.estado == codigo);
        this.nombreEstadoEdicion = e.descripcion;
        this.tipoEstadoEdicion = e.tipo;
        this.codigoEstadoEdicion = codigo;
        this.verDialogoEstadoEdicion = true;
    }

    actualizarEstado() {
        if (this.estaVacio(this.nombreEstadoEdicion)) {
            this.mostrarMensajeAdvertencia("Ingrese el estado");
            return;
        }
        var temp = [...this.bean.estados];
        var i = temp.indexOf(this.bean.estados.find(x => x.estado == this.codigoEstadoEdicion));
        temp[i].descripcion = this.nombreEstadoEdicion;
        temp[i].tipo = this.tipoEstadoEdicion;
        this.bean.estados = temp;
        this.verDialogoEstadoEdicion = false;
    }

    codigoRolEditando: string;
    nombreRolEditando: string;
    verDialogoRolEdicion: boolean = false;

    editarRol(codigo) {
        this.codigoRolEditando = codigo;
        this.nombreRolEditando = this.bean.roles.find(x => x.codigo == codigo).descripcion;
        this.verDialogoRolEdicion = true;
    }

    actualizarRol() {
        if (this.estaVacio(this.nombreRolEditando)) {
            this.mostrarMensajeAdvertencia('Ingrese el nombre del rol');
            return;
        }
        var temp = [...this.bean.roles];
        var i = temp.indexOf(this.bean.roles.find(x => x.codigo == this.codigoRolEditando));
        temp[i].descripcion = this.nombreRolEditando;
        this.bean.roles = temp;
        this.armarRoles(this.bean.roles);
        this.verDialogoRolEdicion = false;
    }

    armarRoles(roles) {
        this.lstTipoAprobador = [];
        roles.forEach(
            rol => {
                this.lstTipoAprobador.push({ value: rol.codigo, label: rol.descripcion });
            }
        );
    }

    filtroDocumentos: FiltroSyDocumentos = new FiltroSyDocumentos();
    verModalSelectorDocumentos: boolean = false;
    @ViewChild('dtDocSy') dtDocSy: Table;

    buscarDocumentos() {
        this.dtDocSy.reset();
    }

    buscarSelectorDocumentos(event: LazyLoadEvent) {
        if (!this.verModalSelectorDocumentos) {
            return;
        }
        this.bloquearPagina();
        this.filtroDocumentos.paginacion.paginacionListaResultado = [];
        this.filtroDocumentos.paginacion.paginacionRegistroInicio = event.first;
        this.filtroDocumentos.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.wfProcesoServicio.listarSyTipoDocumentos(this.filtroDocumentos).then(
            lista => {
                this.filtroDocumentos.paginacion = lista;
                this.verModalSelectorDocumentos = true;
                this.desbloquearPagina();
            }
        );
    }

    lstDocumentosSeleccionados: DtoWfTipoDocumento[] = [];

    mostrarSelectorDocumentos() {
        this.lstDocumentosSeleccionados = [];
        this.filtroDocumentos = new FiltroSyDocumentos();
        this.verModalSelectorDocumentos = true;
    }

    agregarDocumentoSeleccionado(codigo, descripcion) {
        if (this.lstDocumentosSeleccionados.filter(x => x.tipodocumento == codigo).length > 0) {
            this.mostrarMensajeAdvertencia('El documento ya ha sido seleccionado');
            return;
        }
        var nuevoDoc = new DtoWfTipoDocumento();
        nuevoDoc.proceso = this.bean.proceso;
        nuevoDoc.tipodocumento = codigo;
        nuevoDoc.tipodocumentoAux = descripcion;
        nuevoDoc.grupo = null;
        nuevoDoc.rutaplantilla = null;
        nuevoDoc.archivoString = null;
        var lst = [...this.lstDocumentosSeleccionados];
        lst.push(nuevoDoc);
        this.lstDocumentosSeleccionados = lst;
    }

    eliminarDocumentoSeleccionado(codigo) {
        var temp = [...this.lstDocumentosSeleccionados];
        temp = temp.filter(x => x.tipodocumento != codigo);
        this.lstDocumentosSeleccionados = temp;
    }

    aceptarDocumentos() {
        var temp = [...this.bean.tipodocumentos];
        this.lstDocumentosSeleccionados.forEach(
            d => {
                if (this.bean.tipodocumentos.filter(x => x.tipodocumento == d.tipodocumento).length == 0) {
                    temp.push(d);
                }
            }
        );
        this.bean.tipodocumentos = temp;
        this.verModalSelectorDocumentos = false;
    }

    cambiarProcesosXAplicacion() {
        if (this.filtroDocumentos.aplicacion == null) {
            this.filtroDocumentos.proceso = null;
            this.lstProcesoMast = [];
            this.lstProcesoMast.push({ label: ' -- Todos -- ', value: null });
            this.lstProcesoMastRaw.forEach(
                r => {
                    this.lstProcesoMast.push({ label: r.descripcion, value: r.codigo });
                }
            );
            return;
        }
        else {
            this.filtroDocumentos.proceso = null;
            this.lstProcesoMast = [];
            this.lstProcesoMast.push({ label: ' -- Todos -- ', value: null });
            this.lstProcesoMastRaw.filter(x => x.estadoId == this.filtroDocumentos.aplicacion).forEach(
                r => {
                    this.lstProcesoMast.push({ label: r.descripcion, value: r.codigo });
                }
            );
            return;
        }
    }

    nombreDoc(tipo) {
        var obj = this.bean.tipodocumentos.find(x => x.tipodocumento == tipo);
        if (obj) {
            return obj.tipodocumentoAux;
        }
        return tipo;
    }

    verDlgPosicion: boolean = false;
    nivelDestino: number;
    nivelOrigen: number;

    editarPosicionNivel(nivel) {
        this.nivelDestino = nivel;
        this.nivelOrigen = nivel;
        this.verDlgPosicion = true;
    }

    actualizarPosicion() {
        if (this.nivelDestino == this.nivelOrigen) {
            this.verDlgPosicion = false;
            return
        }
        if (this.nivelDestino <= 0) {
            this.mostrarMensajeAdvertencia("Ingrese un nivel mayor a 0");
            return;
        }
        if (this.nivelDestino > this.flujoSeleccionado.niveles.length) {
            this.mostrarMensajeAdvertencia("Ingrese un nivel menor o igual a " + this.flujoSeleccionado.niveles.length);
            return;
        }
        var temp = [...this.flujoSeleccionado.niveles];

        //insertar el nivel origen en el nivel destino

        this.arraymove(temp, this.nivelOrigen - 1, this.nivelDestino - 1);

        //asignar numeracion

        for (let index = 0; index < temp.length; index++) {
            const element = temp[index];
            element.nivel = index + 1;
            element.aprobadores.forEach(a => { a.nivel = index + 1; });
            element.documentos.forEach(a => { a.nivel = index + 1; });
            element.acciones.forEach(a => { a.nivel = index + 1; });
        }

        this.flujoSeleccionado.niveles = temp;
        this.verDlgPosicion = false;
    }

    arraymove(arr, fromIndex, toIndex) {
        var element = arr[fromIndex];
        arr.splice(fromIndex, 1);
        arr.splice(toIndex, 0, element);
    }

    obtenerNombreRol(tipo) {
        var obj = this.bean.roles.find(x => x.codigo == tipo);
        if (obj == undefined) {
            if (tipo == 'JEIN') {
                return 'Jefe Inmediato'
            }
            if (tipo == 'PERS') {
                return 'Persona'
            }
            if (tipo == 'REFE') {
                return 'Referencia'
            }
            if (tipo == 'SOLI') {
                return 'Solicitante'
            }
            if (tipo == 'TRAP') {
                return 'Transaccion'
            }
            return tipo;
        }
        return obj.descripcion;
    }

    verModalPlantillas: boolean = false;
    accionPlantilla: string;
    //@ViewChild(SyReporteDetalleMantenimientoComponent, { static: false }) syReporteDetalleMantenimientoComponent: SyReporteDetalleMantenimientoComponent;

    verPlantillaAprobar(nivel) {
        /*this.nivelActualizando = nivel;
        this.accionPlantilla = 'A';
        var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.aplicacioncodigo = this.bean.aplicacion;
        dto.reportecodigo = this.bean.proceso;
        dto.version = 'APROBA';
        dto.companiasocio = '999999';
        dto.periodo = this.armarPeriodo(this.flujoSeleccionado.flujo, this.nivelActualizando);
        this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }
    verPlantillaRechazar(nivel) {
        /*this.nivelActualizando = nivel;
        this.accionPlantilla = 'R';
        var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.aplicacioncodigo = this.bean.aplicacion;
        dto.reportecodigo = this.bean.proceso;
        dto.version = 'RECHAZ';
        dto.companiasocio = '999999';
        dto.periodo = this.armarPeriodo(this.flujoSeleccionado.flujo, this.nivelActualizando);
        this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }
    verPlantillaDevolver(nivel) {
        /*this.nivelActualizando = nivel;
        this.accionPlantilla = 'D';
        var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.aplicacioncodigo = this.bean.aplicacion;
        dto.reportecodigo = this.bean.proceso;
        dto.version = 'DEVOLV';
        dto.companiasocio = '999999';
        dto.periodo = this.armarPeriodo(this.flujoSeleccionado.flujo, this.nivelActualizando);
        this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }

    verPlantillaSeguimiento(nivel) {
        this.nivelActualizando = nivel;
        this.accionPlantilla = 'S';
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.aplicacioncodigo = this.bean.aplicacion;
        dto.reportecodigo = this.bean.proceso;
        dto.version = 'SEGUIM';
        dto.companiasocio = '999999';
        dto.periodo = this.armarPeriodo(this.flujoSeleccionado.flujo, this.nivelActualizando);
        this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }

    armarPeriodo(flujo, nivel) {
        var p = 'F';
        if (flujo > 9) {
            p = p + flujo;
        }
        else {
            p = p + '0' + flujo;
        }

        p = p + '-';

        if (nivel > 9) {
            p = p + nivel;
        }
        else {
            p = p + '0' + nivel;
        }
        return p;
    }

    iconoConPlantilla: string = 'fad fa-file';
    iconoSinPlantilla: string = 'fad fa-question';

    iconoPlantilla(nivel, accion) {
        var flgEvaluar = '';

        if (accion == 'A')
            flgEvaluar = nivel.tienePlantillaAprobar;
        else if (accion == 'R')
            flgEvaluar = nivel.tienePlantillaRechazar;
        else if (accion == 'D')
            flgEvaluar = nivel.tienePlantillaDevolver;
        else if (accion == 'S')
            flgEvaluar = nivel.tienePlantillaSeguimiento;
        else if (accion == 'AD')
            flgEvaluar = this.bean.tienePlantillaAprobar;
        else if (accion == 'RD')
            flgEvaluar = this.bean.tienePlantillaRechazar;
        else if (accion == 'DD')
            flgEvaluar = this.bean.tienePlantillaDevolver;
        else if (accion == 'GD')
            flgEvaluar = this.bean.tienePlantillaGuardar;
        else if (accion == 'SD')
            flgEvaluar = this.bean.tienePlantillaSeguimiento;
        else if (accion == 'TD')
            flgEvaluar = this.bean.tienePlantillaAlerta;

        if (flgEvaluar == 'S')
            return this.iconoConPlantilla;

        return this.iconoSinPlantilla;
    }

    confirmarPlantilla(event) {
        if (this.accionPlantilla.length == 1) {
            var temp = [...this.flujoSeleccionado.niveles];
            var index = temp.indexOf(temp.find(x => x.nivel == this.nivelActualizando));
            if (this.accionPlantilla == 'A') {
                temp[index].tienePlantillaAprobar = 'S';
            }
            else if (this.accionPlantilla == 'R') {
                temp[index].tienePlantillaRechazar = 'S';
            }
            else if (this.accionPlantilla == 'D') {
                temp[index].tienePlantillaDevolver = 'S';
            }
            else if (this.accionPlantilla == 'S') {
                temp[index].tienePlantillaDevolver = 'S';
            }
            this.flujoSeleccionado.niveles = temp;
        }
        else if (this.accionPlantilla.length == 2) {
            if (this.accionPlantilla == 'AD') {
                this.bean.tienePlantillaAprobar = 'S';
            }
            else if (this.accionPlantilla == 'RD') {
                this.bean.tienePlantillaRechazar = 'S';
            }
            else if (this.accionPlantilla == 'DD') {
                this.bean.tienePlantillaDevolver = 'S';
            }
            else if (this.accionPlantilla == 'GD') {
                this.bean.tienePlantillaGuardar = 'S';
            }
            else if (this.accionPlantilla == 'SD') {
                this.bean.tienePlantillaSeguimiento = 'S';
            }
            else if (this.accionPlantilla == 'TD') {
                this.bean.tienePlantillaAlerta = 'S';
            }
        }
    }

    verPlantillaDefault(accion, version) {
        /*this.accionPlantilla = accion;
        var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.aplicacioncodigo = this.bean.aplicacion;
        dto.reportecodigo = this.bean.proceso;
        dto.version = version;
        dto.companiasocio = '999999';
        dto.periodo = '999999';
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }

    lstPlantillasRelacionadas: any[];

    obtenerPlantillasRelacionadas(uuid) {
        this.wfProcesoServicio.obtenerPlantillasRelacionadas(uuid).then(
            res => {
                this.lstPlantillasRelacionadas = res;
            });
    }

    editarReporte(dto: any) {
        //this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }
}
