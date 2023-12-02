import { FiltroTransaccion } from './../dominio/filtro/FiltroTransaccion';
import { WfProcesoServicio } from '@framework-workflow/servicio/wfproceso.service';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { WfTransaccionServicio } from '@framework-workflow/servicio/wftransaccion.service';
import { WfTransaccionPk } from '../dominio/dto/WfTransaccion';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';

@Component({
    templateUrl: './transaccion-listado.component.html'
})
export class WfTransaccionListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;

    filtro: FiltroTransaccion = new FiltroTransaccion();
    lstEstados: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];
    procesos: SelectItem[] = [];
    verBotonNuevo: boolean = true;
    companias: SelectItem[] = [];
    lstAreaRevisora: SelectItem[] = [];
    lstEstadoProceso: SelectItem[] = [];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private wfTransaccionServicio: WfTransaccionServicio,
        private wfProcesosServicio: WfProcesoServicio,
        private cdref: ChangeDetectorRef,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    pagina: number;

    filtroSolicitante2: any[];
    solicitante2: DtoTabla;

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


    ngOnInit() {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.bloquearPagina();

        this.lstEstados.push({ label: " -- Todos --", value: null });
        this.lstEstados.push({ label: "Solicitud", value: "S" });
        this.lstEstados.push({ label: "Aprobado", value: "A" });
        this.lstEstados.push({ label: "Rechazado", value: "R" });

        this.lstAplicacion.push({ label: " -- Todos --", value: null });
        this.procesos.push({ label: ' -- Todos -- ', value: null });
        this.companias.push({ label: ' -- Todos -- ', value: null });
        this.lstAreaRevisora.push({ label: ' -- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: '-- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: 'Solicitado', value: '0' });
        this.lstEstadoProceso.push({ label: 'Rechazado', value: '-1' });

        var date = new Date();
        this.filtro.desde = new Date(date.getFullYear(), date.getMonth(), 1);
        this.filtro.hasta = new Date(date.getFullYear(), date.getMonth() + 1, 0);

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
                                res.forEach(r => this.procesos.push({ label: r.nombre, value: r.pk.procesoid }));

                                var prefPrevJSON = sessionStorage.getItem("transaccion-listado-preferences");

                                if (prefPrevJSON != null) {
                                    var x = JSON.parse(prefPrevJSON) as TranasccionListadoPreferences;

                                    this.filtro.estado = x.filtro.estado;
                                    this.filtro.referencia = x.filtro.referencia;
                                    this.filtro.desde = x.desde == null ? null : new Date(x.desde);
                                    this.filtro.hasta = x.hasta == null ? null : new Date(x.hasta);

                                    this.filtro.compania = x.filtro.compania;
                                    this.filtro.area = x.filtro.area;
                                    this.filtro.montodesde = x.filtro.montodesde;
                                    this.filtro.montohasta = x.filtro.montohasta;
                                    this.filtro.estadoProceso = x.filtro.estadoProceso;
                                    this.pagina = x.pagina;

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

                                    this.cargarProcesosPreferencia(x.filtro.proceso, x.filtro.estadoProceso);
                                }
                                else {
                                    this.filtro.montodesde = 0;
                                    this.filtro.montohasta = 999999999;
                                    this.buscar(this.dt);
                                }
                            }
                        );
                    });
            });
    }

    cargarEstadosProceso() {
        this.bloquearPagina();
        this.lstEstadoProceso = [];
        this.lstEstadoProceso.push({ label: '-- Todos -- ', value: null });
        this.lstEstadoProceso.push({ label: 'Solicitado', value: '0' });
        this.lstEstadoProceso.push({ label: 'Rechazado', value: '-1' });

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
        this.lstEstadoProceso.push({ label: 'Solicitado', value: '0' });
        this.lstEstadoProceso.push({ label: 'Rechazado', value: '-1' });

        this.filtro.proceso = null;
        this.filtro.estadoProceso = null;

        this.filtro.proceso = valor;

        if (this.filtro.proceso != null || this.filtro.proceso != undefined) {
            this.wfProcesosServicio.listarEstadosPorProceso(this.filtro.proceso).then(
                estados => {
                    estados.forEach(r => this.lstEstadoProceso.push({ value: r.estado, label: r.descripcion }));
                    this.filtro.estadoProceso = estadoproceso == null ? null : estadoproceso;
                    this.buscar(this.dt);
                }
            );

        }
        else {
            this.buscar(this.dt);
        }

    }

    cargarProcesos() {
        this.bloquearPagina();

        this.filtro.proceso = null;

        this.procesos = [];
        this.procesos.push({ label: ' -- Todos -- ', value: null });

        if (this.filtro.aplicacion == null) {
            this.desbloquearPagina();
            return;
        }

        this.wfProcesosServicio.listarPorAplicacionWF(this.filtro.aplicacion).then(
            res => {
                res.forEach(r => this.procesos.push({ label: r.nombre, value: r.pk.procesoid }));
                this.desbloquearPagina();
            }
        );
    }

    cargaDef: Boolean = true;
    reload: boolean = true;

    cargarPrestamos(event: LazyLoadEvent) {

        this.filtro.idPersonaSolicitante2 = undefined;


        if (this.cargaDef) {
            this.cargaDef = false;
            return;
        }

        if (this.pagina != null && this.reload) {
            this.reload = false;
            event.first = this.pagina;
            this.dt.first = this.pagina;
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

        this.wfTransaccionServicio.transaccionListarSolicitante(this.filtro)
            .then(res => {
                res.paginacionRegistroInicio = event.first;
                this.filtro.paginacion = res;
                //guardar en temporal los datos filtrados
                var pref = new TranasccionListadoPreferences();
                pref.filtro = this.filtro;
                pref.pagina = event.first;
                pref.empleado2 = this.solicitante2;
                pref.desde = this.filtro.desde == null ? null : this.filtro.desde == undefined ? null : this.filtro.desde.toUTCString();
                pref.hasta = this.filtro.hasta == null ? null : this.filtro.hasta == undefined ? null : this.filtro.hasta.toUTCString();

                sessionStorage.setItem('transaccion-listado-preferences', JSON.stringify(pref));
                this.desbloquearPagina();
            });
    }

    buscar(datatable: any): void {
        this.bloquearPagina();
        datatable.reset();
    }

    inicializarDatos(): void {

    }

    ver(seleccionado: any) {
        /*if (seleccionado.componenteweb == null) {
            this.mostrarMensajeAdvertencia("La transacción no tiene un componente web asociado");
            return;
        }
        var pk = new WfTransaccionPk();
        pk.transaccionid = seleccionado.transaccion;
        this.router.navigate([seleccionado.componenteweb, this.ACCIONES.VER, JSON.stringify(pk)], { skipLocationChange: true });
        */
        this.router.navigate([ConstanteWorkflow.ruta_wftransaccion_mantenimiento, this.ACCIONES.VER, seleccionado.uuid], { skipLocationChange: true });
    }

    nuevo() {
        this.router.navigate([ConstanteWorkflow.ruta_wftransaccion_mantenimiento, this.ACCIONES.NUEVO, JSON.stringify(new WfTransaccionPk())], { skipLocationChange: true });
    }

    test001() {
        if (this.estaVacio(this.filtro.referencia)) {
            this.mostrarMensajeAdvertencia("Ingresar referencia");
            return;
        }
        this.bloquearPagina();
        this.wfTransaccionServicio.test0001(this.filtro.referencia.trim()).then(
            res => {
                console.log(res);
                this.mostrarMensajeExito(res.mensaje + ' ' + res.transaccionid);
                this.filtro.referencia = null;
                this.buscar(this.dt);
            }
        );
    }


    coreNuevo() { this.nuevo() }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) {
        this.buscar(this.dt);
    }
    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar(dto: any) { }
    coreEditar(dto: any) { }
    coreVer(dto: any) {
        this.ver(dto);
    }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }

}
export class TranasccionListadoPreferences {
    filtro: FiltroTransaccion = new FiltroTransaccion();
    pagina: number;
    desde: string;
    hasta: string;
    empleado2: DtoTabla;
}