import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from '../../servicioComun.service';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';

import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService } from 'primeng/api';

import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { PrTipoplanillaComunService } from '../servicio/prtipoplanilla-comun.service';

@Component({
    selector: 'app-selector-tipoplanilla',
    templateUrl: './tipoplanilla-selector.component.html'
})
export class TipoPlanillaSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: DtoTabla = new DtoTabla();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoTabla = new DtoTabla();
    tag: string;
    lista: DtoTabla[] = [];

    constructor(
        private tipoPlanillaSelectorComponent: PrTipoplanillaComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }


    listarDefecto() {
        this.tipoPlanillaSelectorComponent.listarFiltros(this.filtro.codigo, this.filtro.descripcion, 'A')
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
        if (!this.esObjetoVacio(msj.parametros)) {
            this.filtro = msj.parametros;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoTabla();
        this.listarDefecto();
    }


}
