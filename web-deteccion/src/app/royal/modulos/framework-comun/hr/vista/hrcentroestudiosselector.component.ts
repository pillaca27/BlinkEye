import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { FiltroComunHrCentroestudios } from '../dominio/filtro/FiltroComunHrCentroestudios';
import { HrCentroestudiosComunService } from '../servicio/hrcentroestudios-comun.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';

@Component({
    selector: 'app-centro-estudios-selector',
    templateUrl: './hrcentroestudiosselector.component.html'
})
export class HrCentroEstudiosSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    tag: string = '';
    verSelector: Boolean = false;
    anio: string;
    mes: string;
    lstEstados: SelectItem[] = [];
    filtro: FiltroComunHrCentroestudios = new FiltroComunHrCentroestudios();    
    registrosPorPagina: number = 7;

    constructor(private hrCentroestudiosService: HrCentroestudiosComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);
    }

    ngOnInit() {
    }

    cargarEstados() {
        this.lstEstados = [];
        this.lstEstados.push({ label: 'Todos', value: '' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 1;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrCentroestudiosService.listarpaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }   

    cargarCentroEstudios(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.hrCentroestudiosService.listarpaginacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }



    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(dt: Table){
        dt.reset();
    }

    coreFiltro(flag: boolean){
    };

    coreSalir(){
        this.verSelector = false;
    };

    coreSeleccionar(dto: any){
        this.mensajeController.resultado = dto;        
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreExportar(){        
    }
    coreMensaje(){    
    }
    coreAccion(){        
    }
    coreIniciarComponente(msj: MensajeController){
        this.mensajeController = msj;
        this.filtro = new FiltroComunHrCentroestudios();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.listarDefecto();
        this.cargarEstados();                

    }



}
