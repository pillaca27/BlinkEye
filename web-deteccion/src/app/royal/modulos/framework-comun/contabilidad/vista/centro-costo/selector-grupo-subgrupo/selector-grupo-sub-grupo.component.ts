import { AcCostcenterComunService } from './../../../servicio/accostcenter-comun.service';
import { Component, OnInit } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { MessageService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { FiltroComunAcCostcentergroup } from '@framework-comun/contabilidad/dominio/filtro/FiltroComunAcCostcentergroup';
 

@Component({
  selector: 'app-selector-grupo-sub-grupo',
  templateUrl: './selector-grupo-sub-grupo.component.html',
  styleUrls: ['./selector-grupo-sub-grupo.component.scss']
})
export class SelectorGrupoSubGrupoComponent extends BaseComponent implements OnInit, UISelectorController {
  verSelector: Boolean = false;
  filtro: FiltroComunAcCostcentergroup = new FiltroComunAcCostcentergroup();
  registrosPorPagina: number = 25;
  registroSeleccionado: any;
  tag: string;
  commodity:number=2

  constructor(
    private acCostcentermstService: AcCostcenterComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService,
    servicioComun: ServicioComunService) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngOnInit() {

  }

  cargarCommodity(event: LazyLoadEvent) {
    if (!this.verSelector) {
        return;
    }

    this.mensajeController.componenteDestino.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;        
    this.acCostcentermstService.listarpaginadoGrupo(this.filtro)
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
    this.acCostcentermstService.listarpaginadoGrupo(this.filtro)
        .then(pg => {
            this.filtro.paginacion.paginacionListaResultado = pg.paginacionListaResultado;
            this.filtro.paginacion.paginacionRegistroInicio = pg.paginacionRegistroInicio;
            this.filtro.paginacion.paginacionRegistrosEncontrados = pg.paginacionRegistrosEncontrados;
            this.filtro.paginacion.paginacionRegistrosPorPagina = pg.paginacionRegistrosPorPagina;
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
    this.filtro = new FiltroComunAcCostcentergroup();
    this.mensajeController.componenteDestino.bloquearPagina();
    this.listarDefecto();
}
}