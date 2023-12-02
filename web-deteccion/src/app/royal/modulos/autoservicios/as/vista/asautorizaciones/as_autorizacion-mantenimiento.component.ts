import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpleadomastComunService } from '@framework-comun/core/servicio/empleadomast-comun.service';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { WfTransaccionPk } from '@framework-workflow/dominio/dto/WfTransaccion';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MessageService, SelectItem } from 'primeng/api';
import { DtoAsAutorizacion } from '../../dominio/dto/DtoAsAutorizacion';
import { AsAutorizacionService } from '../../servicio/as_autorizacion.servicio';
import { DtoDwCambioHorarioExcepcion } from '../../dominio/dto/DtoDwCambioHorarioExcepcion';
import { ConstanteAutoservicios } from '../../../ConstanteAutoservicios';
import { OwAsAutorizacionPreprocesoHextraEdit } from '../../dominio/dto/OwAsAutorizacionPreprocesoHextraEdit';

@Component({
    templateUrl: 'as_autorizacion-mantenimiento.component.html'
})

export class AsAutorizacionMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    autorizacion: DtoAsAutorizacion = new DtoAsAutorizacion();
    dwc_conceptoaccesofiltrado: SelectItem[] = [];
    vistaAprobador: boolean;
    puedeEditar: boolean;
    verListaConceptos = false;
    codigoUsuario: string;
    esAdministrador: string;
    archivoAdjunto: string;
    verMantenimiento = false;
    listaTippoDia: SelectItem[] = [];
    accionHorario: string;
    horarioSeleccionado: DtoDwCambioHorarioExcepcion = new DtoDwCambioHorarioExcepcion();
    mensajeAMostrar: string;
    verMensaje = false;
    verDetalle = false;
    minDate: Date;
    maxDate: Date;
    inicioFinJornada: SelectItem[] = [];
    verListaInicioFin = false;
    checkedFalse = false;
    checkedTrue = true;
    horasGanadasSeleccionada: DtoAsAutorizacion[] = [];
    totalHorasSeleccionadas: number;

    verBoton = false;

    lstComportamiento: SelectItem[] = [];

    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;

    constructor(
        private datePipe: DatePipe,
        private router: Router,
        private route: ActivatedRoute,
        private asAutorizacionService: AsAutorizacionService,
        private usuario: UsuarioService,
        private personamast: EmpleadomastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComunService: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComunService);
    }

    mostrarMensajeHorario() {
        if (!this.estaVacio(this.autorizacion.mensajeHorario)) {
            this.mostrarMensajeAdvertencia(this.autorizacion.mensajeHorario);
            this.autorizacion.mensajeHorario = null;
        }
    }

    ngOnInit() {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.codigoUsuario = sessionStorage.getItem('usuarioCodigo');
        this.totalHorasSeleccionadas = 0;
        this.horasGanadasSeleccionada = [];

        console.log("ngoninit");

        this.bloquearPagina();

        this.lstComportamiento.push({ label: '-- Seleccione --', value: '' });
        this.lstComportamiento.push({ label: 'Por Compensar ', value: 'C' });
        this.lstComportamiento.push({ label: 'Ninguno ', value: 'N' });
        this.lstComportamiento.push({ label: 'Horas Extras ', value: 'P' });

        this.inicioFinJornada.push({ label: 'En el Inicio de jornada ', value: 'I' });
        this.inicioFinJornada.push({ label: 'En el Término de Jornada', value: 'F' });
        this.esAdministrador = 'N';

        if (this.accion == undefined) {
            this.accion = this.ACCIONES.NUEVO;
            this.puedeEditar = true;
            this.autorizacion = new DtoAsAutorizacion();
            this.usuario.obtenerUsuarioActual().then(resp => {
                this.personamast.obtenerEmpleadoPorDtoApi(resp.companiaCodigo, resp.personaId).then(rr => {
                    this.autorizacion.empleado = rr.persona;
                    this.autorizacion.nombrecompleto = rr.nombrecompleto;
                    this.autorizacion.companiasocio = rr.companiasocio;
                    this.autorizacion.solicitadopor = rr.codigousuario;
                    this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
                    this.nuevo();
                });
            });

        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.puedeEditar = true;
            this.verBoton = false;
            console.log('EDITAR');
            this.accion = this.ACCIONES.EDITAR;
            this.autorizacion = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            this.editar();
        } else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.puedeEditar = false;
            this.verBoton = false;
            this.accion = this.ACCIONES.ELIMINAR;
            console.log('ELIMINAR');
            this.autorizacion = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            this.eliminar();
        } else if (this.accion == this.ACCIONES.VER) {
            this.puedeEditar = false;
            this.verBoton = true;
            console.log('VER');
            this.accion = this.ACCIONES.VER;
            this.autorizacion = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            this.editar();
        } else if (this.accion == this.ACCIONES.WORKFLOW) {
            this.puedeEditar = false;
            this.verBoton = true;
            console.log('WORKFLOW');
            this.accion = this.ACCIONES.WORKFLOW;
            this.autorizacion = new DtoAsAutorizacion();
            this.autorizacion.idtransaccion = (JSON.parse((this.route.snapshot.params['dto'] as string)) as WfTransaccionPk).transaccionid;

            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
            this.editar();
        }
    }


    itemchanged(any, tipo: string) {
        this.autorizacion.tipo = tipo;
        this.bloquearPagina();
        this.asAutorizacionService.itemchanged(this.autorizacion).then(
            resp => {
                this.desbloquearPagina();
                this.autorizacion = resp;
                this.cargarConcepto();
            }
        );
    }

    iniciarComponentesWorkflow() {
        if (this.autorizacion.transaccionUUID != null) {
            this.wfTransaccionSeguimientoComponent.iniciarComponente(this.autorizacion.transaccionUUID);
            this.wfTransaccionBotonesComponent.iniciarComponente(this.autorizacion.transaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
        }
    }

    onRowSelect(event) {
        if (isNaN(this.autorizacion.totalcantidadHorasSeleccionadas)) {
            this.autorizacion.totalcantidadHorasSeleccionadas = 0;
        }

        this.autorizacion.totalcantidadHorasSeleccionadas = this.autorizacion.totalcantidadHorasSeleccionadas + event.data.cantidad;

        this.asAutorizacionService.obtenerSumaDecimales(this.autorizacion.totalcantidadHorasSeleccionadas).then(
            resp => {
                this.autorizacion.totalCCantidadsexadecimalSeleccionadas = resp.compute1;
            }
        );
    }

    onRowUnselect(event) {
        if (isNaN(this.autorizacion.totalcantidadHorasSeleccionadas)) {
            this.autorizacion.totalcantidadHorasSeleccionadas = 0;
        }
        this.autorizacion.totalcantidadHorasSeleccionadas = this.autorizacion.totalcantidadHorasSeleccionadas - event.data.cantidad;
        this.autorizacion.totalcantidadHorasSeleccionadas = Math.abs(this.autorizacion.totalcantidadHorasSeleccionadas);

        this.asAutorizacionService.obtenerSumaDecimales(this.autorizacion.totalcantidadHorasSeleccionadas).then(
            resp => {
                this.autorizacion.totalCCantidadsexadecimalSeleccionadas = resp.compute1;
            }
        );
    }

    eliminar() {
        console.log('eliminar');
        this.bloquearPagina();
        this.autorizacion.opciones = new OwAsAutorizacionPreprocesoHextraEdit();
        this.autorizacion.opciones.ismostrardesde1 = true;
        this.autorizacion.opciones.ishabilitardesde1 = true;
        this.autorizacion.opciones.ismostrarhasta1 = true;
        this.asAutorizacionService.obtenerInformacionParaPermisoEditar(this.autorizacion).then(
            resp => {
                if (resp != null) {
                    this.autorizacion = resp;
                    this.autorizacion.accion = 'Delete';
                    this.cargarConcepto();
                    this.iniciarComponentesWorkflow();
                }
                this.desbloquearPagina();
            }
        );
    }

    editar() {
        console.log('editar');
        this.bloquearPagina();
        this.autorizacion.opciones = new OwAsAutorizacionPreprocesoHextraEdit();
        this.autorizacion.opciones.ismostrardesde1 = true;
        this.autorizacion.opciones.ishabilitardesde1 = true;
        this.autorizacion.opciones.ismostrarhasta1 = true;
        this.asAutorizacionService.obtenerInformacionParaPermisoEditar(this.autorizacion).then(
            resp => {
                console.log("obtenerInformacionParaPermisoEditar");
                if (resp != null) {
                    this.autorizacion = resp;
                    this.autorizacion.accion = 'Update';
                    this.cargarConcepto();
                    this.iniciarComponentesWorkflow();
                }
                this.desbloquearPagina();
            }
        );
    }

    // guardar() {
    //     this.bloquearPagina();
    //     if (this.autorizacion.accion === 'Delete') {
    //         if (this.autorizacion.estado === 'A') {
    //             this.mostrarMensajeError('No se puede eliminar en este estado. Solo se puede eliminar en estado Solicitado.');
    //             return;
    //         }
    //     }
    //     this.autorizacion.tipoAutorizacion = 'A';
    //     this.autorizacion.estado = 'S';
    //     this.asAutorizacionService.registrar(this.autorizacion).then(
    //         res => {
    //             this.desbloquearPagina();
    //             if (res != null) {
    //                 if (this.transaccionResultado(res)) {
    //                     this.salir();
    //                 }
    //                 this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_listado]);
    //             }
    //         }
    //     );
    // }

    nuevo() {
        console.log('nuevo');
        this.bloquearPagina();
        this.autorizacion.accion = 'Add';
        this.asAutorizacionService.solicitudNuevo(this.autorizacion, this.esAdministrador).then(
            permiso => {
                this.desbloquearPagina();
                this.autorizacion = convertDateStringsToDates(permiso);
                this.autorizacion.categoriaautorizacion = 'OMIS';
                this.autorizacion.conceptoacceso = this.autorizacion.categoriaautorizacion;
                this.itemchanged(null, 'categoriaautorizacion');
            }
        );
    }

    cargarConcepto() {
        this.dwc_conceptoaccesofiltrado = [];
        this.dwc_conceptoaccesofiltrado.push({ label: '-- Seleccione --', value: '' });
        this.autorizacion.dwc_conceptoaccesofiltrado.forEach(obj =>
            this.dwc_conceptoaccesofiltrado.push({ label: obj.descripcionlocal, value: obj.conceptoacceso }));
    }

    mostrarSelectorEmpleadomast() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }

    limpiarEmpleado() {
        this.autorizacion.nombrecompleto = '';
        this.autorizacion.empleado = null;
    }

    salir() {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.router.navigate([ConstanteWorkflow.ruta_wf_aprobacion_listado], { skipLocationChange: true });
        }
        else {
            this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_listado], { skipLocationChange: true })
        }
    }

    coreGuardar() {
        if (this.autorizacion.accion === 'Delete') {
            if (this.autorizacion.estado === 'A') {
                this.mostrarMensajeError('No se puede eliminar en este estado. Solo se puede eliminar en estado Solicitado.');
                return;
            }
        }

        this.bloquearPagina();

        if (this.accion == this.ACCIONES.NUEVO) {
            this.asAutorizacionService.solicitudRegistrar(this.autorizacion).then(
                resp => {
                    this.desbloquearPagina();
                    this.autorizacion = resp;
                    if (resp.transaccionListaMensajes.length > 0) {
                        for (let i = 0, len = resp.transaccionListaMensajes.length; i < len; i++) {
                            if (resp.transaccionListaMensajes[i].tipoMensaje.toString() === 'ADVERTENCIA') {
                                this.mensajeAMostrar = resp.transaccionListaMensajes[i].mensaje;
                                this.verMensaje = true;
                            } else {
                                this.messageService.add({
                                    severity: 'error', summary: 'Error',
                                    detail: resp.transaccionListaMensajes[i].mensaje
                                });
                            }
                        }
                    } else {
                        this.mostrarMensajeExito('Registro guardado correctamente.');
                        this.salir();
                    }
                }
            );
        } else {
            this.asAutorizacionService.solicitudActualizar(this.autorizacion).then(
                resp => {
                    this.desbloquearPagina();
                    this.autorizacion = resp;
                    if (resp.transaccionListaMensajes.length > 0) {
                        for (let i = 0, len = resp.transaccionListaMensajes.length; i < len; i++) {
                            if (resp.transaccionListaMensajes[i].tipoMensaje.toString() === 'ADVERTENCIA') {
                                this.mensajeAMostrar = resp.transaccionListaMensajes[i].mensaje;
                                this.verMensaje = true;
                            } else {
                                this.messageService.add({
                                    severity: 'error', summary: 'Error',
                                    detail: resp.transaccionListaMensajes[i].mensaje
                                });
                            }
                        }
                    } else {
                        this.mostrarMensajeExito('Registro  actualizado correctamente.');
                        this.salir();
                    }
                }
            );
        }
    }


    coreSalir() { }

    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) {
        if (mensage.componente == 'EMPLEADO') {
            this.autorizacion.categoriaautorizacion = undefined;
            this.autorizacion.opciones.ismostrarventanavacaciones = false;
            this.autorizacion.opciones.ismostrardwhoras = false;
            this.autorizacion.opciones.ismostrardwhorario = false;
            this.autorizacion.nombrecompleto = mensage.resultado.busqueda;
            this.autorizacion.empleado = mensage.resultado.persona;
        }
    }
    coreAccion(accion: string) { }
}