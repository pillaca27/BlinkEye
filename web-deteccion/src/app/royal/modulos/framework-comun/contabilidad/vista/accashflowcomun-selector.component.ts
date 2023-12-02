import { AcCashflowmajorComunService } from './../servicio/accashflowmajor-comun.service';
import { AcCashflowComunService } from './../servicio/accashflow-comun.service';
import { DtoComunAcCashflow } from './../dominio/dto/DtoComunAcCashflow';
import { FiltroComunAcCashflow } from './../dominio/filtro/FiltroComunAcCashflow';
import { DtoComunAcReferenciafiscalSeleccion } from './../dominio/dto/DtoComunAcReferenciafiscalSeleccion';
import { DtoComunAcReferenciafiscal } from './../dominio/dto/DtoComunAcReferenciafiscal';
import { FiltroComunAcReferenciafiscalSelector } from './../dominio/filtro/FiltroComunAcReferenciafiscalSelector';
import { FiltroComunAfemst } from './../../core/dominio/filtro/FiltroComunAfemst';

import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { AcReferenciafiscalComunService } from '../servicio/acreferenciafiscal-comun.service';

@Component({
    selector: 'app-selector-accashflowcomun',
    templateUrl: './accashflowcomun-selector.component.html'
})
export class AcCashflowcomunSelectorComponent extends BaseComponent implements OnInit, UISelectorController {
    
    verSelector: Boolean = false;
    filtro: FiltroComunAcCashflow = new FiltroComunAcCashflow();
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunAcCashflow = new DtoComunAcCashflow();
    tag: string;        
    cantidad: number = 0;   
    referencevalue: string; 
    lstMajor: SelectItem[] = [];

    lstTiporegistro: SelectItem[] = [];
    lstTipooperacion: SelectItem[] = [];

    constructor(        
        private servicio: AcCashflowComunService,        
        private servicioMajor: AcCashflowmajorComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

    ngOnInit() {
        this.cargarCombos();

    }           
    
    cargarCombos(){   
        
        this.lstTiporegistro.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstTiporegistro.push({ label: 'Ingreso', value: '1' });
        this.lstTiporegistro.push({ label: 'Egreso', value: '2' });
        this.lstTiporegistro.push({ label: 'Transferencia', value: '3' });

        this.lstTipooperacion.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstTipooperacion.push({ label: 'Operación', value: '1' });
        this.lstTipooperacion.push({ label: 'Financiamiento', value: '2' });
        this.lstTipooperacion.push({ label: 'Inversión', value: '3' });

        this.lstMajor.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.servicioMajor.listarActivos().then(res=>{
            res.forEach(element => {
                this.lstMajor.push({ label: element.nombre, value: element.codigo });
            });
        });
    }    

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }


    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(event: any){

        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.mensajeController.componenteDestino.bloquearPagina();
        this.servicio.listarpaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;                
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    listardefecto(){
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = 7;

        this.mensajeController.componenteDestino.bloquearPagina();
        this.servicio.listarpaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;                
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
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
        this.filtro = new FiltroComunAcCashflow();        
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunAcCashflow();
        this.listardefecto();
    }


}
