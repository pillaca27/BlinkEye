import { DtoSolicitudes } from './../dominio/dto/DtoSolicitudes';
import { DtoRestSolicitudLista } from './../dominio/dto/DtoRestSolicitudLista';
import { DtoFlujoSolicitud } from './../dominio/dto/DtoFlujoSolicitud';
import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { WfTransaccionPk } from './../dominio/dto/WfTransaccion';
import { FiltroSolicitudes } from './../dominio/filtro/FiltroSolicitudes';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Table } from 'primeng/table';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { WfProcesoServicio } from "./../servicio/wfproceso.service";
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DominioMensajeUsuario } from 'src/app/royal/framework/modelo/generico/DominioMensajeUsuario';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { SolicitudRenovacioncontratoMantenimientoComponent } from './solicitud-renovacioncontrato-mantenimiento.component';

@Component({
    templateUrl: './aprobacion-listado.component.html'
})
export class AprobacionListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(SolicitudRenovacioncontratoMantenimientoComponent, { static: false }) solicitudRenovacioncontratoMantenimientoComponent: SolicitudRenovacioncontratoMantenimientoComponent;

    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    paginacion: DominioPaginacion = new DominioPaginacion();
    empleado: DtoTabla = new DtoTabla()
    filtro: FiltroSolicitudes = new FiltroSolicitudes();
    companias: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];
    lstProceso: SelectItem[] = [];
    lstEstado: SelectItem[] = [];
    lstAreaRevisora: SelectItem[] = [];
    lstEstadoProceso: SelectItem[] = [];
    lstUnidad: SelectItem[] = [];
    estado: string = '';
    unidad: string = '';
    listar: boolean = false;
    seleccionados: DtoSolicitudes[] = [];
    seleccionadosWf: DtoFlujoSolicitud[] = [];

    filtroConRango: boolean = false;

    lstMoneda: SelectItem[] = [];
    lstTipoPago: SelectItem[] = [];

    filtroSolicitante: any[];
    solicitante: DtoTabla;

    filtroSolicitante2: any[];
    solicitante2: DtoTabla;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private wfTransaccionServicio: WfTransaccionServicio,
        private wfProcesosServicio: WfProcesoServicio,
        private cdref: ChangeDetectorRef,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }
    //
    coreNuevo(): void {
        // this.nuevo();
    }
    coreBusquedaRapida(filtro: string): void {
    }
    coreBuscar(): void {
        this.buscar();
    }
    coreGuardar(): void {
    }
    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
    }
    //

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        this.bloquearPagina();
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.inicializarDatos();
        this.seleccionados = [];
        var date = new Date();
        this.filtro.fechaDesde = new Date(date.getFullYear(), date.getMonth(), 1);
        this.filtro.fechaHasta = new Date(date.getFullYear(), date.getMonth() + 1, 0);

    }

    inicializarDatos(): void {
        this.companias.push({ label: ' -- Todos -- ', value: null });
        this.lstAplicacion.push({ label: ' -- Todos -- ', value: null });
        this.lstProceso.push({ label: ' -- Todos -- ', value: null });
        this.lstAreaRevisora.push({ label: ' -- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: '-- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: 'Solicitado', value: '0' });

        this.wfProcesosServicio.listarSelector("AREV").then(
            areas => {

                areas.forEach(
                    a => {
                        this.lstAreaRevisora.push({ value: a.codigo, label: a.descripcion });
                    }
                );

                this.wfTransaccionServicio.listarCompaniasActivas()
                    .then(comps => {
                        comps.forEach(comp => this.companias.push({ label: comp.descripcion, value: comp.codigo.trim() }));
                        this.wfProcesosServicio.listarWF().then(
                            res => {
                                res.forEach(r => this.lstProceso.push({ label: r.nombre, value: r.pk.procesoid }));

                                var prefPrevJSON = sessionStorage.getItem("aprobacion-listado-preferences");
                                if (prefPrevJSON != null) {
                                    var x = JSON.parse(prefPrevJSON) as AprobacionListadoPreferences;

                                    this.filtro.estado = x.filtro.estado;
                                    this.filtro.companiaSocio = x.filtro.companiaSocio;
                                    this.filtro.area = x.filtro.area;
                                    this.filtro.montodesde = x.filtro.montodesde;
                                    this.filtro.montohasta = x.filtro.montohasta;
                                    this.filtro.estadoProceso = x.filtro.estadoProceso;

                                    if (x.empleado != null) {
                                        if (x.empleado.id != null) {
                                            var solicitante: DtoTabla = new DtoTabla();
                                            solicitante.id = x.empleado.id;
                                            solicitante.nombre = x.empleado.nombre.trim();
                                            this.filtroSolicitante = [];
                                            this.filtroSolicitante.push(solicitante);
                                            this.solicitante = solicitante;
                                        }
                                    }

                                    if (x.empleado2 != null) {
                                        if (x.empleado2.id != null) {
                                            var solicitante2: DtoTabla = new DtoTabla();
                                            solicitante2.id = x.empleado2.id;
                                            solicitante2.nombre = x.empleado2.nombre.trim();
                                            this.filtroSolicitante2 = [];
                                            this.filtroSolicitante2.push(solicitante2);
                                            this.solicitante2 = solicitante2;
                                        }
                                    }

                                    this.filtro.documento = x.filtro.documento;
                                    this.pagina = x.pagina;
                                    this.cargarProcesosPreferencia(x.filtro.proceso, x.filtro.estadoProceso);
                                }
                                else {
                                    this.filtro.montodesde = 0;
                                    this.filtro.montohasta = 999999999;
                                    this.buscar();
                                }
                            }
                        );
                    });
            });
        this.cargarEstados();
        this.cargarUnidadReplicacion();
        this.cargarCombosPrestamo();
    }

    filtroSolicitanteSimple(event) {
        let query = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(query).then(empl => {
            if (empl.length > 0) {
                empl.forEach(element => {
                    element.nombre = element.nombre.trim();
                });
            }
            this.filtroSolicitante = empl;
        });
    }

    filtroSolicitanteSimple2(event) {
        let query = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(query).then(empl => {
            if (empl.length > 0) {
                empl.forEach(element => {
                    element.nombre = element.nombre.trim();
                });
            }
            this.filtroSolicitante2 = empl;
        });
    }


    cargarCombosPrestamo() {

    }

    cargarUnidadReplicacion() {
        this.lstUnidad.push({ label: ' -- Todos --', value: null });
    }

    cargarEstados() {
        this.lstEstado.push({ label: ' -- Todos -- ', value: null });
    }

    cargarAplicaciones() {
        this.lstAplicacion.push({ label: '-- Todos -- ', value: null });
        this.wfTransaccionServicio.listarAplicacionPorUsuario().then(
            res => {
                res.forEach(r => this.lstAplicacion.push({ label: r.nombre, value: r.codigo }));
                this.filtro.aplicacion = null;
            }
        );
    }

    cargarProcesos() {
        //if (!this.blocked) {
        //    this.bloquearPagina();
        //}
        //this.lstProceso = [];
        //this.lstProceso.push({ label: '-- Todos -- ', value: null });
        //this.filtro.proceso = null;

        /*if (this.filtro.aplicacion == null) {
            this.desbloquearPagina();
            return;
        }*/

        this.wfProcesosServicio.listarWF().then(
            res => {
                res.forEach(r => this.lstProceso.push({ label: r.nombre, value: r.pk.procesoid }));
            }
        );
    }

    cargarEstadosProceso() {
        this.bloquearPagina();
        this.lstEstadoProceso = [];
        this.lstEstadoProceso.push({ label: '-- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: 'Solicitado', value: '0' });

        this.wfProcesosServicio.listarEstadosPorProceso(this.filtro.proceso).then(
            estados => {
                estados.forEach(r => this.lstEstadoProceso.push({ value: r.estado, label: r.descripcion }));
                this.filtro.estadoProceso = null;
                this.desbloquearPagina();
            }
        );

    }

    cargarProcesosPreferencia(valor: any, estadoproceso: any) {

        this.lstEstadoProceso = [];
        this.lstEstadoProceso.push({ label: '-- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: 'Solicitado', value: 0 });

        this.filtro.proceso = null;
        this.filtro.estadoProceso = null;

        this.filtro.proceso = valor == null ? null : valor;

        if (this.filtro.proceso != null || this.filtro.proceso != undefined) {
            this.wfProcesosServicio.listarEstadosPorProceso(this.filtro.proceso).then(
                estados => {
                    estados.forEach(r => this.lstEstadoProceso.push({ value: r.estado, label: r.descripcion }));
                    this.filtro.estadoProceso = estadoproceso == null ? null : estadoproceso;
                    this.buscar();
                }
            );

        }
        else {
            this.buscar();
        }
    }

    buscar() {
        let valida: boolean = false;
        if (this.filtro.fechaDesde !== null && this.filtro.fechaDesde !== undefined) {
            valida = true;
        }

        if (this.filtro.fechaHasta !== null && this.filtro.fechaHasta !== undefined) {
            valida = true;
        }

        if (valida) {
            if (this.filtro.fechaDesde > this.filtro.fechaHasta) {
                this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La fecha hasta debe se mayor o igual que la fecha desde' });
                return null;
            }
        }

        this.dataTableComponent.reset();
    }

    pagina: number;
    reload: boolean = true;

    cargarSolicitudes(event: LazyLoadEvent) {

        this.filtro.idPersonaSolicitante = undefined;
        this.filtro.idPersonaSolicitante2 = undefined;

        if (!this.listar) {
            this.listar = true;
            return;
        }

        if (this.pagina != null && this.reload) {
            this.reload = false;
            event.first = this.pagina;
            this.dataTableComponent.first = this.pagina;
        }


        if (this.solicitante != null || this.solicitante != undefined) {
            if (this.solicitante.nombre != null || this.solicitante.nombre != undefined) {
                this.filtro.idPersonaSolicitante = this.solicitante.id;
            }
        }

        if (this.solicitante2 != null || this.solicitante2 != undefined) {
            if (this.solicitante2.nombre != null || this.solicitante2.nombre != undefined) {
                this.filtro.idPersonaSolicitante2 = this.solicitante2.id;
            }
        }


        this.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.wfTransaccionServicio.transaccionListarAprobacion(this.filtro)
            .then(pg => {
                this.desbloquearPagina();
                if (pg != null) {
                    pg.paginacionRegistroInicio = event.first;
                    this.paginacion = pg;
                    var pref = new AprobacionListadoPreferences();
                    pref.filtro = this.filtro;
                    pref.empleado = this.solicitante;
                    pref.empleado2 = this.solicitante2;
                    pref.pagina = event.first;
                    pref.desde = this.filtro.fechaDesde == null ? null : this.filtro.fechaDesde == undefined ? null : this.filtro.fechaDesde.toUTCString();
                    pref.hasta = this.filtro.fechaHasta == null ? null : this.filtro.fechaHasta == undefined ? null : this.filtro.fechaHasta.toUTCString();
                    sessionStorage.setItem('aprobacion-listado-preferences', JSON.stringify(pref));
                }
            });
    }

    limpiarEmpleado() {
        this.filtro.nombreSolicitante = null;
        this.filtro.idPersonaSolicitante = null;
    }

    // cargarEmpleado(dto: any) {
    //     this.empleado = dto.data;
    //     this.filtro.idPersonaSolicitante = this.empleado.personaId;
    // }

    mostrarSelectorEmpleadomast() {
        //this.empleadomastSelectorComponent.tag = 'j';
        //this.empleadomastSelectorComponent.estado = '';
        //this.empleadomastSelectorComponent.iniciarComponente();
    }

    cargarEmpleado(d: any) {
        const data = d.data;

        if (d.tag === 'j') {
            this.filtro.nombreSolicitante = d.data.personaNombre;
            this.filtro.idPersonaSolicitante = d.data.personaId;
        }
    }

    ver() {

    }


    verSeleccionado(seleccionado: DtoFlujoSolicitud) {
        if (seleccionado.componenteweb == null) {
            this.mostrarMensajeAdvertencia("La transacción no tiene un componente web asociado");
            return;
        }
        if (seleccionado.componenteweb == '[renovacion_contratos]') {
            this.bloquearPagina();
            this.solicitudRenovacioncontratoMantenimientoComponent.verVentanaPorWf(seleccionado.uuid);
            return;
        }
        this.router.navigate([seleccionado.componenteweb, this.ACCIONES.WORKFLOW, seleccionado.uuid], { skipLocationChange: true });
    }

    aprobar() {

        if (this.seleccionadosWf.filter(x => x.proceso == 'FP').length > 0) {
            this.mostrarMensajeAdvertencia('Las solicitudes de Ficha de Personal, solo pueden ser aprobadas desde la Vista Previa');
            return;
        }

        this.bloquearPagina();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        if (this.seleccionadosWf.length === 0) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccionar un registro' });
            this.desbloquearPagina();
            return null;
        }

        this.wfTransaccionServicio.transaccionEjecutar({ accion: 'APROBAR', listaSolicitudes: this.seleccionadosWf, subaccion: null }).then(
            x => {
                this.seleccionadosWf = [];
                this.desbloquearPagina();
                if (x.length == 0) {
                    this.mostrarMensajeExito("Se ha aprobado el registro");
                    this.buscar();
                }
                else {
                    this.mostrarMensajesWorkflow(x);
                }
            }
        );

        return;
    }

    validarRegistrosAAprobar(mensaje: string, callBack?: () => void) {
        this.seleccionados.forEach(
            resp => {
                if (resp.procesoAprobar === 51) {
                    const index = this.seleccionados.indexOf(resp);
                    this.messageService.add({
                        severity: 'error', summary: 'Error',
                        detail: 'La ficha de empleado no puede ser  ' + mensaje + ' automáticamente. Presionar el botón VER'
                    });
                    this.seleccionados.splice(index, 1);
                }
            }
        );
        if (callBack) { callBack(); }
    }

    rechazar() {
        if (this.seleccionadosWf.filter(x => x.proceso == 'FP').length > 0) {
            this.mostrarMensajeAdvertencia('Las solicitudes de Ficha de Personal, solo pueden ser rechazadas desde la Vista Previa');
            return;
        }
        if (this.seleccionadosWf.length === 0) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccionar un registro' });
            this.desbloquearPagina();
            return null;
        }

        this.nroSolicitudes = this.seleccionadosWf.length;
        this.iSeleccionado = 0;
        this.siguienteRechazar();
    }

    seleccionadoActual: DtoFlujoSolicitud;
    nroSolicitudes: number;
    iSeleccionado: number;
    motivo: string;
    verMotivoRechazo: boolean = false;
    verMotivoDevolver: boolean = false;
    headerRechazo: string;

    aceptarMotivoRechazo() {
        this.seleccionadoActual.observaciones = this.motivo;
        this.iSeleccionado = this.iSeleccionado + 1;
        this.siguienteRechazar();
    }

    siguienteRechazar() {
        if (this.iSeleccionado + 1 > this.nroSolicitudes) {
            this.verMotivoRechazo = false;
            this.bloquearPagina();
            this.wfTransaccionServicio.transaccionEjecutar({ accion: 'RECHAZAR', listaSolicitudes: this.seleccionadosWf, subaccion: null }).then(
                x => {
                    this.seleccionadosWf = [];
                    this.desbloquearPagina();
                    if (x.length == 0) {
                        this.mostrarMensajeExito("Se ha rechazado el registro");
                        this.buscar();
                    }
                    else {
                        this.mostrarMensajesWorkflow(x);
                    }
                }
            );
        }
        else {
            this.seleccionadoActual = this.seleccionadosWf[this.iSeleccionado];
            this.headerRechazo = this.seleccionadoActual.procesoFlujoDescripcion + ' : ' + this.seleccionadoActual.referencia
            this.motivo = "";
            this.verMotivoRechazo = true;
        }
    }

    devolver() {
        if (this.seleccionadosWf.filter(x => x.proceso == 'FP').length > 0) {
            this.mostrarMensajeAdvertencia('Las solicitudes de Ficha de Personal, solo pueden ser devueltas desde la Vista Previa');
            return;
        }
        if (this.seleccionadosWf.length === 0) {
            this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'Seleccionar un registro' });
            this.desbloquearPagina();
            return null;
        }

        var valida = true;

        this.seleccionadosWf.forEach(
            x => {
                if (x.nivelAprobar == 1 && x.proceso != 'FP') {
                    valida = false;
                }
            }
        );

        if (!valida) {
            this.messageService.add({ severity: 'warn', summary: 'Advertencia', detail: 'El registro seleccionado no se puede devolver' });
            return;
        }

        this.nroSolicitudes = this.seleccionadosWf.length;
        this.iSeleccionado = 0;
        this.siguienteDevolver();
    }

    siguienteDevolver() {
        if (this.iSeleccionado + 1 > this.nroSolicitudes) {
            this.verMotivoDevolver = false;
            this.bloquearPagina();
            this.wfTransaccionServicio.transaccionEjecutar({ accion: 'DEVOLVER', listaSolicitudes: this.seleccionadosWf, subaccion: null }).then(
                x => {
                    this.seleccionadosWf = [];
                    this.desbloquearPagina();
                    if (x.length == 0) {
                        this.mostrarMensajeExito("Se ha devuelto el registro");
                        this.buscar();
                    }
                    else {
                        this.mostrarMensajesWorkflow(x);
                    }
                }
            );
        }
        else {
            this.seleccionadoActual = this.seleccionadosWf[this.iSeleccionado];
            this.headerRechazo = this.seleccionadoActual.procesoFlujoDescripcion + ' : ' + this.seleccionadoActual.referencia
            this.motivo = "";
            this.verMotivoDevolver = true;
        }
    }

    aceptarMotivoDevolver() {
        this.seleccionadoActual.observaciones = this.motivo;
        this.iSeleccionado = this.iSeleccionado + 1;
        this.siguienteDevolver();
    }

    reiniciarSeleccionados() {
        this.seleccionados = [];
    }

    mostrarMensajeEjecutar(bean: DtoRestSolicitudLista) {
        this.seleccionados = [];

        this.messageService.clear();

        bean.listaSolicitudes.forEach(
            solicitud => {
                this.messageService.add({
                    severity: 'info', summary: bean.accion,
                    detail: 'La solicitud ' + solicitud.documentoReferencia + ' ha sido '
                        + (bean.accion === ConstanteWorkflow.SOLICITUD_ACCION_APROBAR ? 'aprobada' : bean.accion == ConstanteWorkflow.SOLICITUD_ACCION_DEVOLVER ? 'devuelta' : 'rechazada')
                });
            }
        );
    }

    onRowUnselect() {
        this.evaluarDevolver();
    }
    onRowSelect() {
        this.evaluarDevolver();
    }

    puedeDevolver: boolean = false;

    evaluarDevolver() {
        if (this.seleccionados.filter(x => x.nivelAprobar == 1).length > 0) {
            this.puedeDevolver = false;
        }
        else {
            this.puedeDevolver = true;
        }
    }

    mostrarMensajesWorkflow(mensajes: DominioMensajeUsuario[]) {
        this.validaciones = mensajes;
        this.verValidaciones = true;
    }
    verValidaciones: boolean = false;
    validaciones: DominioMensajeUsuario[] = [];


    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar(dto: any) { }
    coreEditar(dto: any) { }
    coreVer(dto: any) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }

}

export class AprobacionListadoPreferences {
    filtro: FiltroSolicitudes = new FiltroSolicitudes();
    empleado: DtoTabla;
    empleado2: DtoTabla;
    pagina: number;
    desde: string;
    hasta: string;
}