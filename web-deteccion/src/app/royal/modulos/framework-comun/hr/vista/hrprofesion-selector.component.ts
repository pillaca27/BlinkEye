import { ServicioComunService } from './../../servicioComun.service';
import { MaMiscelaneosdetalleComunService } from '../../core/servicio/mamiscelaneosdetalle-comun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { UsuarioService } from './../../../framework-seguridad/servicio/spring/seguridad/usuario.service';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { DtoComunHrProfesion } from './../dominio/dto/DtoComunHrProfesion';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { HrProfesionComunService } from '../servicio/hrprofesion-comun.service';
import { FiltroComunHrProfesion } from '../dominio/filtro/FiltroComunHrProfesion';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-profesion-selector',
    templateUrl: './hrprofesion-selector.component.html'
})
export class HrProfesionSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunHrProfesion = new FiltroComunHrProfesion();
    registroSeleccionado: DtoComunHrProfesion = new DtoComunHrProfesion();
    registrosPorPagina: number = 7;

    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() cargarSeleccionEvent = new EventEmitter();


    constructor(private hrProfesionServicio: HrProfesionComunService,
        private usuario: UsuarioService,
        private mamiscelaneosdetalleService: MaMiscelaneosdetalleComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);
    }

    ngOnInit() {
        
    }

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroComunHrProfesion();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunHrProfesion();
        this.listarDefecto();
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrProfesionServicio.listarPaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    coreBusquedaRapida(filtro: string) {
    };

    coreBuscar(datatable?: any) {
        datatable.reset();
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

    cargarProfesiones(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.hrProfesionServicio.listarPaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }

}
