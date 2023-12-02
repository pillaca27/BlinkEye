import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { HrEjecucionevaluaciondesempenioServicio } from '../servicio/HrEjecucionevaluaciondesempenioServicio';
import { DtoEvaluacionDisponibleListado } from '../dominio/dto/DtoEvaluacionDisponibleListado';
import { DtoEvaluacionParticipante } from '../dominio/dto/DtoEvaluacionParticipante';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';

@Component({
    templateUrl: './evaluaciondesempenio-participantes.component.html'
})
export class EvaluacionDesempenioParticipantesComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild('vReporte', { static: false }) vReporte;
    verModalPDF: boolean = false;

    @ViewChild(Table, { static: false }) dt: Table;

    dtoEvaluacion: DtoEvaluacionDisponibleListado = new DtoEvaluacionDisponibleListado();
    listaParticipantes: DtoEvaluacionParticipante[] = [];
    registroSeleccionado: any;

    constructor(
        private hrEjecucionevaluaciondesempenioServicio: HrEjecucionevaluaciondesempenioServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.bloquearPagina();
        const accionExterna = this.route.snapshot.params['accion'] as number;
        this.accion = accionExterna;
        this.dtoEvaluacion = JSON.parse(this.route.snapshot.params['dto'] as string) as DtoEvaluacionDisponibleListado;
        this.hrEjecucionevaluaciondesempenioServicio.listarParticipantes(this.dtoEvaluacion)
            .then(res => {
                this.listaParticipantes = res;
                this.desbloquearPagina();
            });
    }
    evaluar(row: DtoEvaluacionParticipante) {
        this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_evaluacion, this.ACCIONES.EDITAR, JSON.stringify(row), JSON.stringify(this.dtoEvaluacion)], { skipLocationChange: true });
    }
    ver(row: DtoEvaluacionParticipante) {
        this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_evaluacion, this.ACCIONES.VER, JSON.stringify(row), JSON.stringify(this.dtoEvaluacion)], { skipLocationChange: true });
    }
    imprimir(row: DtoEvaluacionParticipante) {
        this.bloquearPagina();
        this.hrEjecucionevaluaciondesempenioServicio.imprimir(row)
            .then(respuesta => {
                this.desbloquearPagina();
                if (respuesta.codigo == 'OK') {
                    //this.vReporte.pdfSrc = respuesta.nombre;
                    //this.vReporte.refresh();
                    //this.verModalPDF = true;
                    const a = document.createElement('a');
                    a.href = respuesta.nombre
                    a.setAttribute('download', row.empleadoNombre.trim() + '.pdf');
                    const b = document.createEvent('MouseEvents');
                    b.initEvent('click', false, true);
                    a.dispatchEvent(b);
                }
                else {
                    this.mostrarMensajeAdvertencia(respuesta.descripcion);
                }
            });
    }
    salir() {
        this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_listado], { skipLocationChange: true });
    }
    clear(table: Table) {
        table.clear();
    }
    coreBuscar(tabla: LazyLoadEvent) {
    }
    coreNuevo() {
    }
    coreEditar(dto: any) {
    }
    coreVer(dto: any) {
    }
    coreAnular(dto: any) {
    }
    coreBusquedaRapida(filtro: string): void {
    }
    coreFiltro(flag: boolean): void {
    }
    coreEliminar(dto: any): void {
    }
    coreExportar(tipo: string): void {
    }
    coreAccion(accion: string): void {
    }
    coreMensaje(mensage: MensajeController): void {
    }
}
