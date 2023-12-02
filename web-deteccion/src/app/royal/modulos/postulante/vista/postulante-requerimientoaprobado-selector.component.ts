import { Component, OnInit, ViewChild } from '@angular/core';
import { DtoComunUbigeo } from '@framework-comun/core/dominio/dto/DtoComunUbigeo';
import { FiltroComunUbigeo } from '@framework-comun/core/dominio/filtro/FiltroComunUbigeo';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { FiltroSolicitudRequerimientoListado } from '../../autoservicios/hr/dominio/filtro/FiltroSolicitudRequerimientoListado';
import { HrPostulanteService } from '../servicio/hrpostulante.service';

@Component({
    selector: 'postulante-requerimientoaprobado-selector',
    templateUrl: './postulante-requerimientoaprobado-selector.component.html'
})
export class PostulanteRequerimientoAprobadoSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;

    verSelector: Boolean = false;
    lista: any[] = [];
    filtro: FiltroSolicitudRequerimientoListado = new FiltroSolicitudRequerimientoListado();
    registrosPorPagina: number = 7;
    registroSeleccionado: any;
    tag: string;

    constructor(
        private postulanteService: HrPostulanteService,
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
        this.postulanteService.listarrequerimientoaprobado(this.filtro)
            .then(pg => {
                this.lista = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.postulanteService.listarrequerimientoaprobado(this.filtro)
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
        dt.reset();
    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.mensajeController.componenteDestino.desbloquearPagina();
        this.verSelector = false;
    };

    coreSeleccionar(dto: any) {
        if (this.listaPrevia.filter(x => x.codigo == dto.companiaId && x.descripcion == dto.requerimiento).length > 0) {
            this.mostrarMensajeAdvertencia('Registro ya seleccionado');
            return;
        }
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreExportar() {
    }
    coreMensaje(mensage: MensajeController) {
        if (mensage.componente == 'COMPANIA') {
            this.filtro.compania = mensage.resultado.companyowner;
            this.filtro.auxCompaniaNombre = mensage.resultado.description;
        }
    }
    coreAccion() {
    }
    listaPrevia: DtoTabla[] = [];

    iniciarComponente(msj: MensajeController, listaRequerimientos: DtoTabla[]) {
        this.listaPrevia = listaRequerimientos;
        this.filtro = new FiltroSolicitudRequerimientoListado();
        this.coreIniciarComponente(msj);
    }

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = null;
        this.lista = [];
        this.listarDefecto();
    }

    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }


}
