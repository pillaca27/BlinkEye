import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { DatePipe } from '@angular/common';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';

import { FiltroConsultaBoleta } from "../dominio/filtro/FiltroConsultaBoleta";
import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PrPlanillaempleadoServicio } from '../servicio/PrPlanillaempleadoServicio';
import { DtoConsultaBoletaCabecera } from '../dominio/dto/DtoConsultaBoletaCabecera';
import { FiltroConsultaCumpleanio } from '../dominio/filtro/FiltroConsultaCumpleanio';
import { Table } from 'primeng/table';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';

@Component({
  templateUrl: './consulta.cumpleanios.component.html'
})
export class ConsultaCumpleaniosComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(Table, { static: false }) dt: Table;
  @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

  filtro: FiltroConsultaCumpleanio = new FiltroConsultaCumpleanio();

  lstDias: SelectItem[] = [];
  lstMeses: SelectItem[] = [];

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

    this.lstMeses.push({ label: '  Todos ', value: null });
    this.lstMeses.push({ label: 'Enero', value: 1 });
    this.lstMeses.push({ label: 'Febrero', value: 2 });
    this.lstMeses.push({ label: 'Marzo', value: 3 });
    this.lstMeses.push({ label: 'Abril', value: 4 });
    this.lstMeses.push({ label: 'Mayo', value: 5 });
    this.lstMeses.push({ label: 'Junio', value: 6 });
    this.lstMeses.push({ label: 'Julio', value: 7 });
    this.lstMeses.push({ label: 'Agosto', value: 8 });
    this.lstMeses.push({ label: 'Septiembre', value: 9 });
    this.lstMeses.push({ label: 'Octubre', value: 10 });
    this.lstMeses.push({ label: 'Noviembre', value: 11 });
    this.lstMeses.push({ label: 'Diciembre', value: 12 });

    this.lstDias.push({ label: '  Todos ', value: null });
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

    this.prPlanillaempleadoServicio.consultaCumpleanio(this.filtro)
      .then(res => {
        this.filtro.paginacion = res;
        this.desbloquearPagina();
      });
  }

  buscarEmpleado() {
    this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
  }

  cargarDias() {
    this.lstDias = [];
    this.lstDias.push({ label: '  Todos ', value: null });
    for (var index = 1; index < 30; index++) {
      this.lstDias.push({ label: '' + index, value: index });
    }
    switch (this.filtro.mes) {
      case 4: case 6: case 9: case 11: {
        this.lstDias.push({ label: '30', value: 30 });
        break;
      }
      case 1: case 3: case 5: case 7: case 8: case 10: case 12: {
        this.lstDias.push({ label: '30', value: 30 });
        this.lstDias.push({ label: '31', value: 31 });
        break;
      }
    }
  }
}
