import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
 
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { ConstanteAngular } from '../../../../framework/angular/ConstanteAngular';

import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from './../../servicioComun.service';
import { DtoComunDwMaAccount } from './../dominio/dto/DtoComunDwMaAccount';
import { FiltroComunAfemst } from './../dominio/filtro/FiltroComunAfemst';
import { AfemstComunService } from './../servicio/afemst-comun.service';

@Component({
    selector: 'app-selector-afemstcomun',
    templateUrl: './afemstcomun-selector.component.html'
})
export class AfemstSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunAfemst = new FiltroComunAfemst();    
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunDwMaAccount = new DtoComunDwMaAccount();
    tag: string;                
    lstEstado: SelectItem[] = [];            
    lstJefaturas0: any[] = [];        
    loading: boolean;
    index: number = 0;
    tipo: string;
    titulo: string;    

    constructor(        
        private afemstComunService: AfemstComunService,        
        //private afetypemstComunService: AfetypemstComunService,        
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        private cdref: ChangeDetectorRef,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

    ngOnInit() {
        //this.listarTipos();

    }    
    
    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }
    
    /*listarTipos(): Promise<number> {            
        this.lstEstado.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.afetypemstComunService.listaractivosProyecto().then(
          td => {
           td.forEach(tipo => this.lstEstado.push({ label: tipo.nombre, value: tipo.codigo.trim() }));
            return 1;
          }
        );
    }*/

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }


    loadCarsLazy0(event: LazyLoadEvent) {        
        if (!this.verSelector) {
            return;
        }
    
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;        
        this.afemstComunService.listarproyectoporfiltro(this.filtro)
            .then(pg => {
             
                this.filtro.paginacion.paginacionListaResultado = pg.paginacionListaResultado;
                this.filtro.paginacion.paginacionRegistroInicio = pg.paginacionRegistroInicio;
                this.filtro.paginacion.paginacionRegistrosEncontrados = pg.paginacionRegistrosEncontrados;
                this.filtro.paginacion.paginacionRegistrosPorPagina = pg.paginacionRegistrosPorPagina;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });

    }

    listarDefecto() {

        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;     
        this.afemstComunService.listarproyectoporfiltro(this.filtro)
            .then(pg => {
                this.filtro.paginacion.paginacionListaResultado = pg.paginacionListaResultado;
                this.filtro.paginacion.paginacionRegistroInicio = pg.paginacionRegistroInicio;
                this.filtro.paginacion.paginacionRegistrosEncontrados = pg.paginacionRegistrosEncontrados;
                this.filtro.paginacion.paginacionRegistrosPorPagina = pg.paginacionRegistrosPorPagina;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
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
        this.filtro = new FiltroComunAfemst();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.listarDefecto();
    }


}
