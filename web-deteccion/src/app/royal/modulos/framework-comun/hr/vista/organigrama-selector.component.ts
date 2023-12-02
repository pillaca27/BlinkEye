import { UISelectorController } from '@framework/angular/interface/UISelectorController';

import { Table } from 'primeng/table';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from '../../servicioComun.service';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';

import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';

import { FiltroComunOrganigramaPuestos } from '../dominio/filtro/FiltroComunOrganigramaPuestos';
import { DtoComunHrPuestoempresa } from '../dominio/dto/DtoComunHrPuestoempresa';
import { HrPuestoempresaComunServicio } from '../servicio/hrpuestoempresa-comun.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Component({
    selector: 'app-selector-organigrama',
    templateUrl: './organigrama-selector.component.html'
})
export class OrganigramaSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunOrganigramaPuestos = new FiltroComunOrganigramaPuestos();
    registrosPorPagina: number = 7;
    registroSeleccionado: any;
    tag: string;

    constructor(
        private confirmationService: ConfirmationService,
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
        this.HrPuestoempresaComunServicio.listarPuestosOrganigrama(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.HrPuestoempresaComunServicio.listarPuestosOrganigrama(this.filtro)
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

    dtoOrganigrama: any;
    verSelectorUbicacion: boolean = false;
    ubicaciones: any[] = [];

    coreSeleccionarUbicacion(dto: DtoTabla) {
        this.dtoOrganigrama.unidadorganigrama = dto.id;
        this.dtoOrganigrama.unidadorganigramanombre = dto.descripcion;
        this.dtoOrganigrama.orden = dto.codigo;
        this.dtoOrganigrama.cc = dto.estadoId;
        this.dtoOrganigrama.ccNombre = dto.nombre;
        this.mensajeController.resultado = this.dtoOrganigrama;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.verSelectorUbicacion = false;
        this.coreSalir();
    }
    coreSeleccionar(dto: any) {
        this.mensajeController.componenteDestino.bloquearPagina();
        var validaUbicaciones = new FiltroComunOrganigramaPuestos();
        validaUbicaciones.companiaSocio = this.filtro.companiaSocio;
        validaUbicaciones.unidadNegocio = this.filtro.unidadNegocio;
        validaUbicaciones.puestoOriginal = dto.codigopuesto;
        validaUbicaciones.secuencia = this.filtro.secuencia;
        validaUbicaciones.anio = this.filtro.anio;

        if (dto.unidades.length > 1) {
            this.mensajeController.componenteDestino.desbloquearPagina();
            this.confirmationService.confirm({
                header: 'Información',
                icon: 'fa fa-question-circle',
                message: 'El Puesto seleccionado se encuentra en más de una ubicación, especifique cual de ellas desea mostrar.',
                accept: () => {
                    this.dtoOrganigrama = dto;
                    this.ubicaciones = dto.unidades;
                    this.verSelectorUbicacion = true;
                },
                key: 'confirm2'
            });
        } else {
            dto.cc = dto.unidades[0].estadoId;
            dto.ccNombre = dto.unidades[0].nombre;
            this.mensajeController.resultado = dto;
            this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
            this.coreSalir();
        }
    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }
    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroComunOrganigramaPuestos();
        if (!this.esObjetoVacio(msj.parametros)) {
            this.filtro = msj.parametros;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = null;
        this.listarDefecto();
    }
}
