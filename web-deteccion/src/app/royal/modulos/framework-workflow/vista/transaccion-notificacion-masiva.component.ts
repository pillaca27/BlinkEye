import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { DtoNotificacionMasiva, DtoPersonaNotificar } from "./../dominio/dto/DtoNotificacionMasiva";
@Component({
    templateUrl: './transaccion-notificacion-masiva.component.html',
    selector: 'transaccion-notificacion-masiva'
})
export class WfTransaccionNotificacionMasivaComponent extends PrincipalBaseComponent implements OnInit {

    constructor(
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    dto: DtoNotificacionMasiva = new DtoNotificacionMasiva();
    cargando: boolean = true;

    iniciarComponente(t: DtoPersonaNotificar[]) {
        this.cargando = true;
        this.dto = new DtoNotificacionMasiva();
        this.dto.transacciones = t;
        this.cargando = false;
    }

    enviarNotificacion() {
        if (this.estaVacio(this.dto.mensaje)) {
            this.mostrarMensajeAdvertencia("Ingrese el mensaje");
            return;
        }
        this.bloquearPagina();
        this.wfTransaccionServicio.registrarNotificacionMasiva(this.dto).then(
            res => {
                this.desbloquearPagina();
                this.cargando = true;
                this.mostrarMensajeAdvertencia("Acción realizada con éxito.");
            }
        );
    }
}
