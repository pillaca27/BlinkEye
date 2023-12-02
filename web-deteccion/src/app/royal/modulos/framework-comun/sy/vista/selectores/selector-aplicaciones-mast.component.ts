import { FiltroComunAplicacionesmast } from './../../../sg/dominio/filtro/FiltroComunAplicacionesmast';
 
 
import { Component, OnInit } from '@angular/core';
import { DtoComunUbigeo } from '@framework-comun/core/dominio/dto/DtoComunUbigeo';
import { FiltroComunUbigeo } from '@framework-comun/core/dominio/filtro/FiltroComunUbigeo';
import { DepartamentoComunService } from '@framework-comun/core/servicio/departamento-comun.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { MessageService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
 
import { SyProcesomstService } from '@framework-comun/sy/servicio/syprocesomst.servicio';
import { DtoComunAplicacionesmast } from '@framework-comun/sg/dominio/dto/DtoComunAplicacionesmast';
 

@Component({
  selector: 'app-selector-aplicaciones-mast',
  templateUrl: './selector-aplicaciones-mast.component.html',
  styleUrls: ['./selector-aplicaciones-mast.component.scss']
})
export class SelectorAplicacionesMastComponent extends BaseComponent implements OnInit, UISelectorController {

  verSelector: Boolean = false;
  filtro: FiltroComunAplicacionesmast = new FiltroComunAplicacionesmast();
  registrosPorPagina: number = 7;
  registroSeleccionado: DtoComunAplicacionesmast = new DtoComunAplicacionesmast();
  tag: string;

  lstEstados: SelectItem[] = []


  constructor(
    private syProcesomstService: SyProcesomstService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService,
    servicioComun: ServicioComunService) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngOnInit() {
    
  }

  cargarEstados(){
    this.lstEstados.push({ label: '-- Todos --', value: null });
    this.lstEstados.push({ label: 'Activo', value: 'A' });
    this.lstEstados.push({ label: 'Inactivo', value: 'I' });
  }

  cargarPuesto(event: LazyLoadEvent) {
    if (!this.verSelector) {
      return;
    }
    this.mensajeController.componenteDestino.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
    this.syProcesomstService.listaraplicacionesmastporfiltro(this.filtro)
      .then(pg => {
        this.filtro.paginacion = pg;
        this.mensajeController.componenteDestino.desbloquearPagina();
      });
  }

  listarDefecto() {
    this.filtro.paginacion.paginacionRegistroInicio = 0;
    this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
    this.syProcesomstService.listaraplicacionesmastporfiltro(this.filtro)
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
  coreIniciarComponente(msj: MensajeController) {
    this.mensajeController = msj;
    this.filtro = new FiltroComunAplicacionesmast();
    this.cargarEstados();
    this.filtro.estado = 'A';
    this.mensajeController.componenteDestino.bloquearPagina();
    this.registroSeleccionado = new DtoComunAplicacionesmast();
    this.listarDefecto();
  }


}
