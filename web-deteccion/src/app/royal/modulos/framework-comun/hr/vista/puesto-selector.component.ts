import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from '../../servicioComun.service';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';

import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService } from 'primeng/api';

import { FiltroComunHrPuestoempresa } from '../dominio/filtro/FiltroComunHrPuestoempresa';
import { DtoComunHrPuestoempresa } from '../dominio/dto/DtoComunHrPuestoempresa';
import { HrPuestoempresaComunServicio } from '../servicio/hrpuestoempresa-comun.service';

@Component({
    selector: 'app-selector-puesto',
    templateUrl: './puesto-selector.component.html'
})
export class PuestoSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunHrPuestoempresa = new FiltroComunHrPuestoempresa();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunHrPuestoempresa = new DtoComunHrPuestoempresa();
    tag: string;

    constructor(
        private HrPuestoempresaComunServicio: HrPuestoempresaComunServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    cargarPuesto(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.HrPuestoempresaComunServicio.listarPuestosPaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.HrPuestoempresaComunServicio.listarPuestosPaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }


    coreBusquedaRapida(filtro: string) {
    };

    coreBuscar(dt: Table) {
        dt.reset();
    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.mensajeController.componenteDestino.desbloquearPagina();
        this.verSelector = false;
    };

    coreSeleccionar(dto: any) {
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }
    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroComunHrPuestoempresa();
        if (!this.esObjetoVacio(msj.parametros)) {
            this.filtro = msj.parametros;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunHrPuestoempresa();
        this.listarDefecto();
    }


}
