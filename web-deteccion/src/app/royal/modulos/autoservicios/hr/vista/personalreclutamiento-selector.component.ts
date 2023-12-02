import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';

import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { FiltroPersonalReclutamientoSelector } from '../dominio/filtro/FiltroPersonalReclutamientoSelector';

import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { HrRequerimientoServicio } from '../servicio/HrRequerimientoServicio';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
@Component({
    selector: 'app-selector-personalreclutamiento',
    templateUrl: './personalreclutamiento-selector.component.html'
})
export class PersonalReclutamientoSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;

    verSelector: Boolean = false;
    filtro: FiltroPersonalReclutamientoSelector = new FiltroPersonalReclutamientoSelector();
    registrosPorPagina: number = 7;
    registroSeleccionado: any;
    tag: string;

    constructor(
        private hrRequerimientoServicio: HrRequerimientoServicio,
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
        this.hrRequerimientoServicio.listarPersonalReclutamiento(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrRequerimientoServicio.listarPersonalReclutamiento(this.filtro)
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
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'COMPANIA') {
            this.filtro.compania = mensage.resultado.companyowner;
            this.filtro.auxCompaniaNombre = mensage.resultado.description;
        }

        else if (mensage.componente == 'TIPOPLANILLA') {
            this.filtro.tipoPlanilla = mensage.resultado.codigo;
            this.filtro.auxTipoPlanillaNombre = mensage.resultado.descripcion;
        }
    }
    coreAccion() {
    }
    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroPersonalReclutamientoSelector();
        if (this.mensajeController.parametros) {
            var companiaDto = this.mensajeController.parametros as DtoTabla;
            this.filtro.compania = companiaDto.codigo;
            this.filtro.auxCompaniaNombre = companiaDto.descripcion;
        }
        this.registroSeleccionado = null;
        this.listarDefecto();
    }
    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }
    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA', null));
    }
}
