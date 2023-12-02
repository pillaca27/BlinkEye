import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { DtoPrSolicitudvacacion } from '../dominio/dto/DtoPrSolicitudvacacion';
import { PrSolicitudVacacionesServicio } from '../servicio/PrSolicitudVacacionesServicio';
import { HistorialVacacionesComponent } from './historial-vacaciones.component';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
    templateUrl: './solicitud-vacaciones-mantenimiento.component.html'
})
export class SolicitudVacacionesMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    dtoBean: DtoPrSolicitudvacacion = new DtoPrSolicitudvacacion();
    minDate = new Date();

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;
    @ViewChild(HistorialVacacionesComponent, { static: false }) historialVacacionesComponent: HistorialVacacionesComponent;

    lstTipos: SelectItem[] = [];
    lstPeriodos: SelectItem[] = [];
    lstFeriados: DtoTabla[] = [];

    diasDisponibles: number = 0;
    diasPendientes: number = 0;

    aprobarEditar: boolean = false;

    constructor(
        private router: Router,
        private prSolicitudVacacionesServicio: PrSolicitudVacacionesServicio,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }


    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.bloquearPagina();
        this.cargarUtilizacion();

        if (this.accion == this.ACCIONES.NUEVO) {
            this.nuevo();
        }
        else {
            this.obtenerPorUUID(this.route.snapshot.params['uuid'] as string);
        }
    }

    cargarUtilizacion() {
        this.lstTipos.push({ value: null, label: ' Selecccione ' });
        this.lstTipos.push({ label: 'Goce', value: 'GOC' });
        this.lstTipos.push({ label: 'Compensación de Dinero', value: 'TRA' });
    }

    nuevo() {
        this.prSolicitudVacacionesServicio.solicitudNuevo().then(
            res => {
                this.cargarPeriodos(res);
                this.dtoBean = res;
                this.desbloquearPagina();
            }
        );
    }

    obtenerPorUUID(uuid: string) {
        var dto = new DtoPrSolicitudvacacion();
        if (this.accion == this.ACCIONES.WORKFLOW)
            dto.transaccionUUID = uuid;
        else
            dto.uuid = uuid;

        this.prSolicitudVacacionesServicio.solicitudObtenerPorUuid(dto).then(
            res => {
                this.validarEstado(res);
                this.cargarPeriodos(res);
                this.dtoBean = res;
                this.obtenerPeriodo();
                this.desbloquearPagina();
                if (this.dtoBean.transaccionUUID != null) {
                    this.wfTransaccionSeguimientoComponent.iniciarComponente(this.dtoBean.transaccionUUID);
                    this.wfTransaccionBotonesComponent.iniciarComponente(this.dtoBean.transaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
                }
            }
        );
    }

    cargarPeriodos(w: DtoPrSolicitudvacacion) {
        this.lstPeriodos = [];
        this.lstPeriodos.push({ value: null, label: ' Selecccione ' });
        w.periodos.forEach(
            r => {
                this.lstPeriodos.push({ value: r.periodo, label: r.rangoAnios });
            }
        );
    }

    validarEstado(w: DtoPrSolicitudvacacion) {
        if (w.estado === 'A') {
            this.aprobarEditar = true;
            this.minDate = this.obtenerMesSiguiente(); 
        }
    }

    coreGuardar(): void {
        this.dtoBean.auxDiasDisponibles = this.diasDisponibles;
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.prSolicitudVacacionesServicio.solicitudRegistrar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.messageService.add({ severity: 'success', summary: 'Mensaje', detail: this.getMensajeGrabado(res.numerosolicitud), life: 25000 });
                    this.coreSalir();
                }
            });
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.prSolicitudVacacionesServicio.solicitudActualizar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.messageService.add({ severity: 'success', summary: 'Mensaje', detail: this.getMensajeGrabado(res.numerosolicitud), life: 25000 });
                    this.coreSalir();
                }
            });
        }
    }
    coreSalir(): void {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.router.navigate([ConstanteWorkflow.ruta_wf_aprobacion_listado]);
        }
        else {
            this.router.navigate([ConstanteAutoservicios.ruta_solicitud_vacaciones_listado]);
        }
    }

    registrarSeguimiento(dto: DtoPrSolicitudvacacion): void {
        this.bloquearPagina();
        this.prSolicitudVacacionesServicio.registrarSeguimiento(dto).then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {
                this.mostrarMensajeExito(this.getMensajeGrabado(res.numerosolicitud));
                this.coreSalir();
            }
        });
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

    verHistorial() {
        this.historialVacacionesComponent.iniciarComponente(this.dtoBean.historial);
    }

    calcularFechaFin() {
        if (this.dtoBean.dias === undefined || this.dtoBean.dias == null || this.dtoBean.dias == 0) {
            this.dtoBean.dias = 1;
        }
        this.dtoBean.fechafin = new Date(this.dtoBean.fechainicio);
        this.dtoBean.fechafin.setDate(this.dtoBean.fechafin.getDate() + this.dtoBean.dias - 1);
    }

    dispo() {
        var a = this.dtoBean.diasInfo.diasAcumulado;
        a = a == null || a == undefined ? 0 : a;
        var b = this.dtoBean.diasInfo.diasSolicitados;
        b = b == null || b == undefined ? 0 : b;
        return a - b;
    }

    calcularDias() {
        if (this.dtoBean.fechainicio != null && this.dtoBean.fechafin != null) {
            if (this.dtoBean.fechainicio > this.dtoBean.fechafin) {
                this.mostrarMensajeAdvertencia('La Fecha fin no puede ser menor que la Fecha inicio');
                return;
            }
            var date1 = this.dtoBean.fechainicio;
            var date2 = this.dtoBean.fechafin;
            var timeDiff = Math.abs(date2.getTime() - date1.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            this.dtoBean.dias = diffDays + 1;
            this.calcularDiasUtiles();
        }
    }

    calcularDiasUtiles() {
        var diasRest = 0;
        var date1 = new Date(this.dtoBean.fechainicio);
        var date2 = new Date(this.dtoBean.fechafin);
        this.bloquearPagina();

        var fechas : DtoPrSolicitudvacacion = new DtoPrSolicitudvacacion();
        fechas.fechainicio = date1;
        fechas.fechafin = date2;

        this.prSolicitudVacacionesServicio.obtenerFeriados(fechas).then(res => {
            this.lstFeriados = res;
            console.log(date1);
            console.log(date2);
            date2.setDate(date2.getDate() + 1);

            while (date1 < date2) {
                var weekDay = date1.getDay();
                if (weekDay === 6 || weekDay === 0) { // SÁBADO Y DOMINGO
                    diasRest++;
                }
                else
                {
                    //Si coincide con algun feriado
                    if(!this.esListaVacia(this.lstFeriados))
                    {
                        const esFeriado = this.lstFeriados.find(feriado => {
                            const feriadoDate = new Date(feriado.fecha);
                            if(feriadoDate.getDate()+1 === date1.getDate() && feriadoDate.getMonth() === date1.getMonth() && feriadoDate.getFullYear() === date1.getFullYear())
                                diasRest++;
                        });
                    }
                }
                date1.setDate(date1.getDate() + 1);
            }

            this.dtoBean.diasutiles = this.dtoBean.dias - diasRest;
            this.desbloquearPagina();
        });
    }

    obtenerPeriodo() {
        this.diasDisponibles = 0;
        this.diasPendientes = 0;
        var periodo = this.dtoBean.historial.find(x => x.periodo === this.dtoBean.periodo);
        if (periodo !== undefined) {
            this.bloquearPagina();
            if (this.accion == this.ACCIONES.EDITAR) {
                periodo.nroSolicitud = this.dtoBean.numerosolicitud;
            }
            this.prSolicitudVacacionesServicio.infoPeriodoDias(periodo).then(res => {
                this.desbloquearPagina();
                this.diasDisponibles = res.diasPendientes - res.diasSolicitados;
                this.diasPendientes = res.diasPendientes;
            });
        }
    }

    obtenerMesSiguiente() {
        var fecha = new Date();
        var mesActual = (fecha.getMonth() + 1);
        var anioActual = fecha.getFullYear();

        if (mesActual == 12) {
            anioActual++;
            mesActual = 1;
        }

        return new Date(anioActual, mesActual, 1);
    }

}
