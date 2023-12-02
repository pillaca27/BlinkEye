import { Component, OnInit } from '@angular/core';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { ServicioComunService } from '../../servicioComun.service';
import { DtoComunMaMiscelaneosdetalle } from '../dominio/dto/DtoComunMaMiscelaneosdetalle';
import { MaMiscelaneosdetalleComunService } from '../servicio/mamiscelaneosdetalle-comun.service';
import { DtoComunMaUnidadnegocio } from './../dominio/dto/DtoComunMaUnidadnegocio';
import { FiltroComunMaUnidadnegocio } from './../dominio/filtro/FiltroComunMaUnidadnegocio';
import { MaUnidadnegocioComunService } from './../servicio/maunidadnegocio-comun.service';

@Component({
    selector: 'app-selector-miscelaneos',
    templateUrl: './miscelaneos-selector.component.html'
})
export class MiscelaneosSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: DtoComunMaMiscelaneosdetalle = new DtoComunMaMiscelaneosdetalle();
    lista: DtoTabla[] = [];
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunMaUnidadnegocio = new DtoComunMaUnidadnegocio();
    tag: string;

    nombreSelector: string = '';

    constructor(
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
    }
    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }
    coreBusquedaRapida(filtro: string) {
    };
    coreBuscar(dt: Table) {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.maMiscelaneosdetalleComunService.listarTablaPorHeaderCore(this.filtro)
            .then(pg => {
                this.lista = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
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
    coreIniciarComponente(msj: MensajeController, nombreSelector?: string, aplicacion?: string, codigotabla?: string, compania?: string) {
        this.nombreSelector = nombreSelector;
        this.mensajeController = msj;
        this.filtro = new DtoComunMaMiscelaneosdetalle();
        this.filtro.compania = compania;
        this.filtro.aplicacioncodigo = aplicacion;
        this.filtro.codigotabla = codigotabla;
        this.lista = [];
        this.registroSeleccionado = new DtoComunMaUnidadnegocio();
        this.verSelector = true;
        this.coreBuscar(null);
    }
}
