import { DtlComunUsuario } from './../dominio/lista/DtlComunUsuario';
import { FiltroComunUsuario } from './../dominio/filtro/FiltroComunUsuario';
 
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { UsuarioComunService } from '../servicio/usuario-comun.service';

@Component({
    selector: 'app-selector-usuariocomun',
    templateUrl: './usuariocomun-selector.component.html'
})
export class UsuarioComunSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunUsuario = new FiltroComunUsuario();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtlComunUsuario = new DtlComunUsuario();
    tag: string;    
    lstEstados: SelectItem[] = [];
    lstTipo: SelectItem[] = [];

    constructor(        
        private usuarioComunService: UsuarioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

    ngOnInit() {
        this.cargarEstados();
    }            

    cargarEstados() {
        this.lstEstados = [];
        this.lstEstados.push({ label: 'Todos', value: null });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });

        this.lstTipo = [];
        this.lstTipo.push({ label: 'Todos', value: null });
        this.lstTipo.push({ label: 'Usuario', value: 'US' });
        this.lstTipo.push({ label: 'Perfil', value: 'PE' });
        
    }    

    cargarPuesto(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;        
        this.usuarioComunService.listarpaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;        
        this.usuarioComunService.listarpaginado(this.filtro)
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


    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(dt: Table){
        dt.reset();
    }

    coreFiltro(flag: boolean){
    };

    coreSalir(){
        this.mensajeController.componenteDestino.desbloquearPagina();        
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
        this.filtro = new FiltroComunUsuario();
        this.filtro.estado = 'A';
        this.filtro.tipo = 'US';
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtlComunUsuario();
        this.listarDefecto();
    }


}
