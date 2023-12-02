import { Component, OnInit } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { LazyLoadEvent, MessageService } from 'primeng/api';
import { DtoHistorialVacacionPeriodo } from '../dominio/dto/DtoHistorialVacacionPeriodo';
import { PrSolicitudVacacionesServicio } from '../servicio/PrSolicitudVacacionesServicio';

@Component({
    selector: 'historial-vacaciones',
    templateUrl: './historial-vacaciones.component.html'
})
export class HistorialVacacionesComponent extends BaseComponent {

    verModal: boolean = false;
    lstPeriodos: any[] = [];
    lstDetalle: any[] = [];
    periodoSeleccionado: any;

    totalDias: number = 0;
    totalUtiles: number = 0;

    constructor(
        private prSolicitudVacacionesServicio: PrSolicitudVacacionesServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    iniciarComponente(historial: DtoHistorialVacacionPeriodo[]) {
        this.periodoSeleccionado = null;
        this.lstPeriodos = historial;
        this.lstDetalle = [];
        this.verModal = true;
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
