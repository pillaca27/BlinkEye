import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PrPlanillaempleadoServicio } from '../servicio/PrPlanillaempleadoServicio';
import { Table } from 'primeng/table';
import { FiltroConsultaCts } from '../dominio/filtro/FiltroConsultaCts';

@Component({
  templateUrl: './consulta.cts.component.html'
})
export class ConsultaCtsComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(Table, { static: false }) dt: Table;

  filtro: FiltroConsultaCts = new FiltroConsultaCts();
  lstPeriodos: SelectItem[] = [];

  constructor(
    private prPlanillaempleadoServicio: PrPlanillaempleadoServicio,
    private route: ActivatedRoute,
    servicioComun: ServicioComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService
  ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

  coreNuevo(): void {
    throw new Error('Method not implemented.');
  }
  coreBusquedaRapida(filtro: string): void {
    throw new Error('Method not implemented.');
  }
  coreBuscar(): void {
    this.dt.reset();
  }
  coreFiltro(flag: boolean): void {
    throw new Error('Method not implemented.');
  }
  coreAnular(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEliminar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEditar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreVer(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreExportar(tipo: string): void {
    throw new Error('Method not implemented.');
  }
  coreMensaje(mensage: MensajeController): void {
    throw new Error('Method not implemented.');
  }
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    this.bloquearPagina();
    this.lstPeriodos.push({ label: ' -- Seleccione -- ', value: null });
    this.prPlanillaempleadoServicio.listarPeriodosCts().then(res => {
      res.forEach(row => {
        this.lstPeriodos.push({ label: row.nombre, value: row.codigo });
      });
      this.desbloquearPagina();
    });
  }

  cargarListado(event: LazyLoadEvent) {
    if (this.formularioOninit) {
      this.formularioOninit = false;
      return;
    }

    if (this.estaVacio(this.filtro.periodoId)) {
      this.mostrarMensajeAdvertencia('Seleccione un periodo');
      return;
    }

    this.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

    this.prPlanillaempleadoServicio.consultaCts(this.filtro)
      .then(res => {
        this.filtro.paginacion = res;
        this.desbloquearPagina();
      });
  }

  imprimir(bean: any) {
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.imprimirCts(bean)
      .then(respuesta => {
        this.desbloquearPagina();
        if (respuesta.codigo == 'OK') {
          //var a = document.createElement("a");
          //a.href = "data:text/html;base64," + respuesta.nombre;
          //a.download = respuesta.descripcion;
          //a.click();
          var win = window.open();
          win.document.body.innerHTML = atob(respuesta.nombre);
        }
        else {
          this.mostrarMensajeAdvertencia(respuesta.descripcion);
        }
      });
  }
}
