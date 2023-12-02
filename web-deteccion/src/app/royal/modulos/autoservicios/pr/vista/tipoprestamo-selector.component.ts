import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';

import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService } from 'primeng/api';

import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { PrPrestamoServicio } from '../servicio/PrPrestamoServicio';
@Component({
    selector: 'app-selector-tipoprestamo',
    templateUrl: './tipoprestamo-selector.component.html'
})
export class TipoprestamoSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: DtoTabla = new DtoTabla();
    lista: any[] = [];
    registrosPorPagina: number = 7;
    registroSeleccionado: any;
    tag: string;

    constructor(
        private prPrestamoServicio: PrPrestamoServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    listarDefecto() {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.prPrestamoServicio.lstTipoPrestamo(this.filtro)
            .then(pg => {
                this.lista = pg;
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
        this.listarDefecto();
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
        this.filtro = new DtoTabla();
        this.registroSeleccionado = null;
        this.listarDefecto();
    }
}
