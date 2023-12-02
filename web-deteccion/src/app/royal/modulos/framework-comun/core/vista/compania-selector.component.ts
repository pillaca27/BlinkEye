import { Component, OnInit } from '@angular/core';
import { DtoComunCompanyowner } from '@framework-comun/core/dominio/dto/DtoComunCompanyowner';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { BaseComponent } from '../../../../framework/angular/component/BaseComponent';
import { MensajeController } from '../../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '../../servicioComun.service';
import { CompanyownerComunService } from '../servicio/companyowner-comun.service';
import { FiltroComunCompanyowner } from '../dominio/filtro/FiltroComunCompanyowner';
 
@Component({
    selector: 'app-selector-compania',
    templateUrl: './compania-selector.component.html'
})
export class CompaniaSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunCompanyowner = new FiltroComunCompanyowner();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunCompanyowner = new DtoComunCompanyowner();
    tag: string;    

    constructor(        
        private companyownerComunService: CompanyownerComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

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
        this.companyownerComunService.listarcompanyownerporfiltro(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;        
        this.companyownerComunService.listarcompanyownerporfiltro(this.filtro)
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
        this.filtro = new FiltroComunCompanyowner();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunCompanyowner();
        this.listarDefecto();
    }


}
