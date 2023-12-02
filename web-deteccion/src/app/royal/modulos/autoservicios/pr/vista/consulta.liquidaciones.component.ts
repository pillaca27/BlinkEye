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
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { FiltroConsultaLiquidaciones } from '../dominio/filtro/FiltroConsultaLiquidaciones';

@Component({
  templateUrl: './consulta.liquidaciones.component.html'
})
export class ConsultaLiquidacionesComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(Table, { static: false }) dt: Table;
  @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

  filtro: FiltroConsultaLiquidaciones = new FiltroConsultaLiquidaciones();

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
    if (mensage.componente == 'EMPLEADO') {
      this.filtro.auxEmpleadoNombre = mensage.resultado.busqueda;
      this.filtro.empleado = mensage.resultado.persona;
      this.filtro.companiasocio = mensage.resultado.companiasocio;
    }
  }
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
  }

  cargarListado(event: LazyLoadEvent) {

    if (this.formularioOninit) {
      this.formularioOninit = false;
      return;
    }

    this.bloquearPagina();
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

    this.prPlanillaempleadoServicio.consultaLiquidaciones(this.filtro)
      .then(res => {
        this.filtro.paginacion = res;
        this.desbloquearPagina();
      });
  }

  buscarEmpleado() {
    this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
  }

  imprimir(bean: any) {
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.imprimirLiquidacion(bean)
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
