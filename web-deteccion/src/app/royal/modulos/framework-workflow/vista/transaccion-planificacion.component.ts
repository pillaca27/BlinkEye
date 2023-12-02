import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { DtoWfPlanificacion } from '../dominio/dto/DtoWfPlanificacion';
import { WorkFlowTransaccion } from '../dominio/dto/WorkFlowTransaccion';

@Component({
    templateUrl: './transaccion-planificacion.component.html',
    selector: 'transaccion-planificacion'
})
export class WfTransaccionPlanificacionComponent extends PrincipalBaseComponent implements OnInit {


    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() cargarDataEvent = new EventEmitter();
    @Output() enviarResumenCabeceraEvent = new EventEmitter();

    planificacion: DtoWfPlanificacion = new DtoWfPlanificacion();


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

    cargando: boolean = true;

    transaccion: number;

    /**
     * 
     * @param request Enviar obligatorio el proceso, y los criterios acorde a su proceso
     */
    proyectarPlanificacion(request: WorkFlowTransaccion) {
        if (this.estaVacio(request.procesoId)) {
            this.mostrarMensajeAdvertencia("No se puede proyectar la planificación: se ha especificado el Id del proceso");
            return;
        }
        this.cargando = true;
        this.wfTransaccionServicio.obtenerProyeccionPlanificacion(request).then(
            res => {
                if (res.transaccionEstado == 'ER' || res.transaccionEstado == 'VA' || res.transaccionEstado == 'ERROR') {
                    if (res.transaccionListaMensajes.length > 0) {
                        res.transaccionListaMensajes.forEach(element => {
                            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje });
                        });
                    }
                }
                else {
                    this.cargando = false;
                    this.planificacion = res;
                }
            }
        );
    }

    iniciarComponente(transaccionid: number) {
        this.transaccion = transaccionid;
        var p = [];
        this.cargando = true;
        var p2 = this.wfTransaccionServicio.listarPlanificacion(this.transaccion).then(
            res => {
                this.planificacion = res;
            }
        );
        p.push(p2);

        Promise.all(p).then(
            x => {
                this.cargando = false;
            }
        )
    }

    actualizarPlanificacion() {
        this.bloquearPagina();
        this.wfTransaccionServicio.actualizarPlanificacion(this.planificacion).then(
            res => {
                this.desbloquearPagina();
                this.mostrarMensajeExito("Se ha actualizado la planificación");
            }
        );
    }
}
