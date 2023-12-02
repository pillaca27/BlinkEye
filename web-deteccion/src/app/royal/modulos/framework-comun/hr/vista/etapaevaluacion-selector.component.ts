import { FiltroComunHrReqetapa } from './../dominio/filtro/FiltroComunHrReqetapa';
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
import { HrReqetapaComunServicio } from '../servicio/hrreqetapa-comun.service';
import { DtoComunHrReqetapa } from '../dominio/dto/DtoComunHrReqetapa';

@Component({
    selector: 'app-selector-etapaevaluacion',
    templateUrl: './etapaevaluacion-selector.component.html'
})
export class EtapaevaluacionSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunHrReqetapa = new FiltroComunHrReqetapa();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunHrReqetapa = new DtoComunHrReqetapa();
    tag: string;

    constructor(
        private hrReqetapaComunServicio: HrReqetapaComunServicio,
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
        this.hrReqetapaComunServicio.listaretapaevaluacionporfiltro(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrReqetapaComunServicio.listaretapaevaluacionporfiltro(this.filtro)
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
        this.filtro = new FiltroComunHrReqetapa();

        if (!this.esObjetoVacio(msj.parametros)) {
            this.filtro = msj.parametros;
        }

        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunHrReqetapa();
        this.listarDefecto();
    }


}
