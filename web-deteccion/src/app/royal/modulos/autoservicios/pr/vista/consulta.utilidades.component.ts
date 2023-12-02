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
import { FiltroConsultaUtilidades } from '../dominio/filtro/FiltroConsultaUtilidades';

@Component({
  templateUrl: './consulta.utilidades.component.html'
})
export class ConsultaUtilidadesComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(Table, { static: false }) dt: Table;

  filtro: FiltroConsultaUtilidades = new FiltroConsultaUtilidades();
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
    throw new Error('Method not implemented.');
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
    this.prPlanillaempleadoServicio.listarPeriodosUtilidad().then(res => {
      res.forEach(row => {
        this.lstPeriodos.push({ label: row.nombre, value: row.codigo });
      });
      this.desbloquearPagina();
    });
  }

  imprimir() {
    if (this.estaVacio(this.filtro.periodoId)) {
      this.mostrarMensajeAdvertencia('Seleccione un periodo');
      return;
    }
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.imprimirUtilidades(this.filtro)
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
