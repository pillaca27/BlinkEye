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
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { FiltroEvaluacionDesempenio } from '../dominio/filtro/FiltroEvaluacionDesempenio';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';

@Component({
    templateUrl: './evaluaciondesempenio-listado.component.html'
})
export class EvaluacionDesempenioListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;

    filtro: FiltroEvaluacionDesempenio = new FiltroEvaluacionDesempenio();
    listaEvaluaciones: DtoEvaluacionDisponibleListado[] = [];
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
        this.buscar();
    }
    evaluar(row: DtoEvaluacionDisponibleListado) {
        this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_participantes, this.ACCIONES.EDITAR, JSON.stringify(row)], { skipLocationChange: true });
    }
    ver(row: DtoEvaluacionDisponibleListado) {
        this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_participantes, this.ACCIONES.VER, JSON.stringify(row)], { skipLocationChange: true });
    }
    buscar() {
        this.bloquearPagina();
        this.hrEjecucionevaluaciondesempenioServicio.listarEvaluacionesDisponibles(this.filtro)
            .then(res => {
                this.listaEvaluaciones = res;
                this.desbloquearPagina();
            });
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
        if (mensage.componente == 'COMPANIA') {
            this.filtro.compania = mensage.resultado.companyowner;
            this.filtro.auxCompaniaNombre = mensage.resultado.description;
        }
        else if (mensage.componente == 'ESTADO') {
            this.filtro.estado = mensage.resultado.codigo;
            this.filtro.auxEstadoNombre = mensage.resultado.nombre;
        }
    }
    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }
    buscarEstado() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADO', null), 'ESTADO', 'HR', 'ESTEVALDES', '999999');
    }
}
