import { Table } from 'primeng/table';
import { Component, OnInit } from '@angular/core';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from '../../servicioComun.service';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { EmpleadomastComunService } from '../servicio/empleadomast-comun.service';
import { FiltroComunEmpleado } from '../dominio/filtro/FiltroComunEmpleado';
import { DtoComunEmpleadomast } from '../dominio/dto/DtoComunEmpleadomast';


@Component({
    selector: 'app-selector-empleadomast',
    templateUrl: './empleadomast-selector.component.html'
})
export class EmpleadomastSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunEmpleado = new FiltroComunEmpleado();
    registrosPorPagina: number = 20;
    registroSeleccionado: DtoComunEmpleadomast = new DtoComunEmpleadomast();
    tag: string;

    constructor(
        private empleadomastComunService: EmpleadomastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    cargarEmpleados(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.empleadomastComunService.listarPaginadoCore(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.empleadomastComunService.listarPaginadoCore(this.filtro)
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
        this.filtro = new FiltroComunEmpleado();
        this.filtro.compania = msj.tipo;
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunEmpleadomast();
        this.listarDefecto();
    }

}
