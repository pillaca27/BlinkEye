import { Component, OnInit } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { HrEncuestaServicio } from '../../../autoservicios/hr/servicio/HrEncuestaServicio';

@Component({
    selector: 'app-encuestaplantilla-selector',
    templateUrl: './encuestaplantilla-selector.component.html'
})
export class EncuestaPlantillaSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: DtoTabla = new DtoTabla();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoTabla = new DtoTabla();
    tag: string;
    lista: DtoTabla[] = [];

    constructor(
        private hrEncuestaServicio: HrEncuestaServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    listarDefecto() {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.hrEncuestaServicio.listarPlantillas(this.filtro)
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
    coreIniciarComponente(msj: MensajeController, tipo?: string) {
        this.mensajeController = msj;
        this.filtro = new DtoTabla();
        this.filtro.codigo = tipo;
        this.registroSeleccionado = new DtoTabla();
        this.listarDefecto();
    }


}
