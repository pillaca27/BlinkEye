import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
import { ConstanteComun } from './../../ConstanteComun';
import { AplicacionesmastComunService } from '../../seguridad/servicio/aplicacionesmast-comun.service';
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { SyReportearchivoComunService } from '../servicio/syreportearchivo-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { ConfirmationService, MessageService, SelectItem, LazyLoadEvent } from 'primeng/api';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { Component, OnInit, ViewChild, ChangeDetectorRef, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { FiltroComunSyLogWeb } from '../dominio/filtro/FiltroComunSyLogWeb';
import { Table } from 'primeng/table';
import { SyLogWebServicio } from '../servicio/sylogweb.service';
import { DtoComunSyLogWeb } from '../dominio/dto/DtoComunSyLogWeb';

@Component({
    templateUrl: './sylogweb-listado.component.html',
})
export class SyLogWebListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
    constructor(
        private confirmationService: ConfirmationService,
        private servicio: SyLogWebServicio,
        private route: ActivatedRoute,
        private cdref: ChangeDetectorRef,
        private router: Router,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }
    lstTipo: SelectItem[] = [];

    filtro: FiltroComunSyLogWeb = new FiltroComunSyLogWeb();

    @ViewChild(Table, { static: false }) dt: Table;

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {

        this.bloquearPagina();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.lstTipo.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstTipo.push({ label: 'Error', value: 'ERRO' });
        this.lstTipo.push({ label: 'Debug', value: 'DEBG' });
        this.lstTipo.push({ label: 'Información', value: 'INFO' });
        this.lstTipo.push({ label: 'Advertencia', value: 'ADVE' });

        this.filtro.fecha = new Date();

        this.formularioOninit = false;
        this.buscar(this.dt);
    }

    cargando = true;

    buscar(dt: any) {
        this.cargar(dt);
    }

    eliminarPorFecha() {
        if (this.filtro.fecha == null) {
            this.mostrarMensajeAdvertencia("Ingresar la fecha");
            return;
        }
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                this.bloquearPagina();
                var eliminar = new FiltroComunSyLogWeb();
                eliminar.fecha = this.filtro.fecha;
                this.servicio.eliminarPorFecha(eliminar).then(
                    x => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(x)) {
                            this.buscar(this.dt);
                        }
                    }
                );

            }
        });
    }

    cargar(event: LazyLoadEvent) {

        if (this.cargando) {
            this.cargando = false;
            return;
        }

        if (!this.blocked) {
            this.bloquearPagina();
        }

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.servicio.listarpaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.desbloquearPagina();
            });
    }

    help() {
        this.router.navigate(['spring/help-page'], { skipLocationChange: true });
    }


    coreNuevo() {

    }
    coreBusquedaRapida(filtro: string) {

    }
    coreBuscar(tabla: LazyLoadEvent) {
        this.buscar({ first: 0 })
    }
    coreFiltro(flag: boolean) {

    }
    coreAnular(dto: any) {

    }
    coreEliminar(dto: DtoComunSyLogWeb) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                this.bloquearPagina();
                var eliminar = new FiltroComunSyLogWeb();
                eliminar.fecha = this.filtro.fecha;
                this.servicio.eliminar(dto).then(
                    x => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(x)) {
                            this.buscar(this.dt);
                        }
                    }
                );

            }
        });
    }

    trace: string;
    verModalTrace: boolean = false;

    verTrace(dto: DtoComunSyLogWeb) {
        this.trace = "";
        this.bloquearPagina();
        this.servicio.obtenerTrace(dto).then(
            x => {
                this.desbloquearPagina();
                if (this.transaccionResultado(x)) {
                    if (x.traceBase64 != null) {
                        this.trace = x.traceBase64;
                        this.verModalTrace = true;
                    }
                }
            }
        )
    }

    coreEditar(dto: any) {
    }
    coreVer(dto: any) {
    }
    coreExportar(tipo: string) {

    }
    coreMensaje(mensage: MensajeController) {

    }
    coreAccion(accion: string) {

    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.buscar(dt);
        }
    }

    ngAfterViewInit() {

    }

}
