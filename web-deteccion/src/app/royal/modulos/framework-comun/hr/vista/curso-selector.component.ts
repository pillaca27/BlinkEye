import { DtoComunHrCursodescripcion } from './../dominio/dto/DtoComunHrCursodescripcion';
import { FiltroComunHrCursodescripcion } from './../dominio/filtro/FiltroComunHrCursodescripcion';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from '../../servicioComun.service';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { HrCursodescripcionComunService } from '../servicio/hrcursodescripcion-comun.service';


@Component({
    selector: 'app-selector-curso',
    templateUrl: './curso-selector.component.html'
})
export class CursoSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunHrCursodescripcion = new FiltroComunHrCursodescripcion();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunHrCursodescripcion = new DtoComunHrCursodescripcion();
    tag: string;

    constructor(
        private hrCursodescripcionComunService: HrCursodescripcionComunService,
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
        this.hrCursodescripcionComunService.listarcursoporfiltro(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.updateRowGroupMetaData();
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrCursodescripcionComunService.listarcursoporfiltro(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.updateRowGroupMetaData();
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
        this.filtro = new FiltroComunHrCursodescripcion();

        if (!this.esObjetoVacio(msj.parametros)) {
            this.filtro = msj.parametros;
        }

        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunHrCursodescripcion();
        this.listarDefecto();
    }

    rowGroupMetadata: any;
    updateRowGroupMetaData() {
        this.rowGroupMetadata = {};

        if (this.filtro.paginacion.paginacionListaResultado) {
            for (let i = 0; i < this.filtro.paginacion.paginacionListaResultado.length; i++) {
                let rowData = this.filtro.paginacion.paginacionListaResultado[i];
                let representativeName = rowData.area;

                if (i == 0) {
                    this.rowGroupMetadata[representativeName] = { index: 0, size: 1 };
                }
                else {
                    let previousRowData = this.filtro.paginacion.paginacionListaResultado[i - 1];
                    let previousRowGroup = previousRowData.area;
                    if (representativeName === previousRowGroup)
                        this.rowGroupMetadata[representativeName].size++;
                    else
                        this.rowGroupMetadata[representativeName] = { index: i, size: 1 };
                }

            }

        }
    }
}
