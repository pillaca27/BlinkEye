import { Component, OnInit } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { DtoComunAcSucursal } from '../dominio/dto/DtoComunAcSucursal';
import { AcSucursalComunService } from '../servicio/acsucursal-comun.service';
import { FiltroComunAcSucursal } from './../dominio/filtro/FiltroComunAcSucursal';

@Component({
  selector: 'app-sucursalselector',
  templateUrl: './sucursalselector.component.html'
})
export class SucursalselectorComponent extends BaseComponent implements OnInit, UISelectorController {

  verSelector: Boolean = false;
  filtro: FiltroComunAcSucursal = new FiltroComunAcSucursal();
  registrosPorPagina: number = 7;
  registroSeleccionado: DtoComunAcSucursal = new DtoComunAcSucursal();
  tag: string;    

  constructor(        
      private acSucursalComunService: AcSucursalComunService,
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
      this.acSucursalComunService.listarsucursalporfiltro(this.filtro)
          .then(pg => {
              this.filtro.paginacion = pg;
              this.mensajeController.componenteDestino.desbloquearPagina();
          });
  }

  listarDefecto() {
      this.filtro.paginacion.paginacionRegistroInicio = 0;
      this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;        
      this.acSucursalComunService.listarsucursalporfiltro(this.filtro)
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
      this.filtro = new FiltroComunAcSucursal();
      this.mensajeController.componenteDestino.bloquearPagina();
      this.registroSeleccionado = new DtoComunAcSucursal();
      this.listarDefecto();
  }


}
