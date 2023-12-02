import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PrSolicitudVacacionesServicio } from '../servicio/PrSolicitudVacacionesServicio';
import { DtoHistorialVacacionPeriodo } from '../dominio/dto/DtoHistorialVacacionPeriodo';

@Component({
  templateUrl: './consulta.vacacionespendientes.component.html'
})
export class ConsultaVacacionesPendientesComponent extends FormularioComponent implements OnInit, UIListadoController {

  constructor(
    private prSolicitudVacacionesServicio: PrSolicitudVacacionesServicio,
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
    this.prSolicitudVacacionesServicio.solicitudNuevo().then(
      res => {
        this.iniciarComponente(res.historial);
        this.desbloquearPagina();
      }
    );
  }

  lstPeriodos: any[] = [];
  lstDetalle: any[] = [];
  periodoSeleccionado: any;
  totalDias: number = 0;
  totalUtiles: number = 0;

  iniciarComponente(historial: DtoHistorialVacacionPeriodo[]) {
    this.periodoSeleccionado = null;
    this.lstPeriodos = historial;
    this.lstDetalle = [];
  }

  verDetalle(row: any) {
    this.totalDias = 0;
    this.totalUtiles = 0;
    this.bloquearPagina();
    this.periodoSeleccionado = row;
    this.prSolicitudVacacionesServicio.listarUtilizacion(row)
      .then(pg => {
        this.lstDetalle = pg;
        this.lstDetalle.forEach(x => {
          this.totalDias += x.dias;
          this.totalUtiles += x.diasutiles;
        });
        this.desbloquearPagina();
      });
  }

}
