import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef, EventEmitter, Output } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { DtoHrContratos } from '../../autoservicios/hr/dominio/dto/DtoHrContratos';
import { HrRenovacioncontratoServicio } from '../../autoservicios/hr/servicio/HrRenovacioncontratoServicio';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';

@Component({
    selector: 'solicitud-renovacioncontrato-mantenimiento',
    templateUrl: './solicitud-renovacioncontrato-mantenimiento.component.html'
})
export class SolicitudRenovacioncontratoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;

    dtoHrContratos: DtoHrContratos = new DtoHrContratos();
    puedeEditar: boolean = false;
    verModal: boolean = false;
    lstExtensiones: string[] = [];

    @Output() volverABuscar = new EventEmitter();

    constructor(
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private hrRenovacioncontratoServicio: HrRenovacioncontratoServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }
    coreGuardar(): void {
        if (this.dtoHrContratos.auxArchivo === undefined
            || this.dtoHrContratos.auxArchivo === null
            || this.dtoHrContratos.auxArchivo === '') {
            this.confirmationService.confirm({
                message: 'El sustento no fue ingresado. ¿Desea continuar?',
                accept: () => {
                    this.bloquearPagina();
                    this.hrRenovacioncontratoServicio.solicitudContratoRegistrar(this.dtoHrContratos).then(
                        resp => {
                            if (this.transaccionResultadoSimple(resp)) {
                                if (resp != null) {
                                    this.mostrarMensaje('La Solicitud se guardó correctamente ', 'info');
                                    this.volverABuscar.emit();
                                    this.verModal = false;
                                }
                            }
                            else {
                                this.desbloquearPagina();
                            }
                        }
                    );
                }
            });
        } else {
            this.bloquearPagina();
            this.hrRenovacioncontratoServicio.solicitudContratoRegistrar(this.dtoHrContratos).then(
                resp => {
                    if (this.transaccionResultadoSimple(resp)) {
                        if (resp != null) {
                            this.mostrarMensaje('La Solicitud se guardó correctamente ', 'info');
                            this.volverABuscar.emit();
                            this.verModal = false;
                        }
                    }
                    else {
                        this.desbloquearPagina();
                    }
                }
            );
        }
    }
    coreEliminar() {
        if (this.dtoHrContratos.nivelAprobacion > 0) {
            if (this.dtoHrContratos.solRenovacionEstado === 'S') {
                this.mostrarMensajeAdvertencia('La Solicitud se encuentra en proceso de Aprobación.');
                return;
            } else {
                this.mostrarMensajeAdvertencia('La Solicitud se encuentra Aprobada.');
                return;
            }
        }
        this.confirmationService.confirm({
            message: '¿Seguro de eliminar la solicitud?. Nota: El registro se eliminará permanentemente.',
            accept: () => {
                this.bloquearPagina();
                this.hrRenovacioncontratoServicio.solicitudContratoEliminar(this.dtoHrContratos).then(
                    resp => {
                        if (this.transaccionResultadoSimple(resp)) {
                            this.mostrarMensaje('La Solicitud se eliminó correctamente ', 'info');
                            if (this.volverABuscar) {
                                this.volverABuscar.emit();
                            }
                            this.verModal = false;
                        }
                        else {
                            this.desbloquearPagina();
                        }
                    }
                );
            }
        });
    }
    coreSalir(): void {
        this.verModal = false;
        if (this.volverABuscar) {
            this.volverABuscar.emit();
        }
    }
    coreExportar(tipo: string): void {
    }
    coreMensaje(mensage: MensajeController): void {
    }
    coreAccion(accion: string): void {
    }

    ngOnInit() {
        super.ngOnInit();
        var p = [];
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'SY', ConstanteFichaEmpleado.MISC_DOCUMENTOS_EXTENSIONES_PERMITIDOS).then(res => {
            res.forEach(ele => {
                this.lstExtensiones.push(ele.codigo.trim());
            });
            return 1;
        }));

        this.lstMotivosS.push({ label: '-- Seleccione --', value: null });
        this.lstMotivosN.push({ label: '-- Seleccione --', value: null });

        this.maMiscelaneosdetalleComunService.listarDtoPorHeader(ConstanteFichaEmpleado.APLICACION_CODIGO, 'CCMOTIVOSO', ConstanteFichaEmpleado.COMPANIA)
            .then(respuesta => {
                respuesta.forEach(obj => {
                    if (obj.valorcodigo1 == 'S' || obj.valorcodigo1 == 'A') {
                        this.lstMotivosS.push({ label: obj.descripcionlocal, value: obj.codigoelemento.trim() });
                    }
                    if (obj.valorcodigo1 == 'N' || obj.valorcodigo1 == 'A') {
                        this.lstMotivosN.push({ label: obj.descripcionlocal, value: obj.codigoelemento.trim() });
                    }
                });
            });

    }

    verVentanaPorWf(uuid: string) {
        this.puedeEditar = false;
        var registroSeleccionado = new DtoHrContratos();
        registroSeleccionado.transaccionUUID = uuid;
        this.accion = this.ACCIONES.WORKFLOW;
        this.obtenerSolicitudContrato(registroSeleccionado);
    }

    verVentana(registroSeleccionado: DtoHrContratos) {
        this.bloquearPagina();
        this.dtoHrContratos = new DtoHrContratos();

        if (registroSeleccionado.auxAccion === 'VER') {
            this.puedeEditar = false;
            this.accion = this.ACCIONES.VER;
        } else {
            this.puedeEditar = true;
            this.accion = this.ACCIONES.EDITAR;
        }
        this.obtenerSolicitudContrato(registroSeleccionado);
    }

    obtenerSolicitudContrato(registroSeleccionado: DtoHrContratos) {
        this.hrRenovacioncontratoServicio.obtenerSolicitudContrato(registroSeleccionado).then(
            resp => {
                this.desbloquearPagina();
                if (resp != null) {
                    this.listaTipoContratos = [];
                    this.listaTipoContratos.push({ value: null, label: ' -- Seleccione -- ' });
                    resp.lstTipoContratos.forEach(
                        x => {
                            this.listaTipoContratos.push({ value: x.codigo.trim(), label: x.descripcion });
                        }
                    );
                    this.listaTiempoRenovacion = [];
                    this.listaTiempoRenovacion.push({ value: null, label: ' -- Selecccione -- ' });
                    resp.lstTiempoRenovacion.forEach(
                        x => {
                            this.listaTiempoRenovacion.push({ value: x.codigoelemento.trim(), label: x.descripcionlocal });
                        }
                    );
                    this.habilitarCampos(resp);
                    if (resp.flagRenovar == 'S') {
                        this.lstMotivos = this.lstMotivosS;
                    }
                    else {
                        this.lstMotivos = this.lstMotivosN;
                    }
                    resp.solRenovacionTiemporenovacion = this.trim(resp.solRenovacionTiemporenovacion);
                    resp.idMotivo = this.trim(resp.idMotivo);

                    this.dtoHrContratos = resp;
                    this.verModal = true;

                    if (this.dtoHrContratos.transaccionUUID != null) {
                        this.wfTransaccionSeguimientoComponent.iniciarComponente(this.dtoHrContratos.transaccionUUID);
                        this.wfTransaccionBotonesComponent.iniciarComponente(this.dtoHrContratos.transaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
                    }


                }
            }
        );
    }

    evaluarMotivos() {
        this.dtoHrContratos.idMotivo = null;
        this.lstMotivos = this.dtoHrContratos.flagRenovar == 'S' ? this.lstMotivosS : this.lstMotivosN;
        this.dtoHrContratos.idMotivo = null;
    }

    habilitarEliminar: boolean = false;
    habilTipoContrato: boolean = false;
    habilRenovacion: boolean = false;
    listaTipoContratos: SelectItem[] = [];
    listaTiempoRenovacion: SelectItem[] = [];
    lstMotivosS: SelectItem[] = [];
    lstMotivosN: SelectItem[] = [];
    lstMotivos: SelectItem[] = [];

    cargarTiempoRenovacion() {

    }

    habilitarCampos(w: DtoHrContratos) {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.habilitarEliminar = false;
            this.habilTipoContrato = false;
            this.habilRenovacion = false;
            return;
        }
        if (w.visibleEliminar === 'S') {
            this.habilitarEliminar = true;
        } else {
            this.habilitarEliminar = false;
        }
        if (w.flagRenovar === 'S') {
            this.habilTipoContrato = true;
            if (w.flagbloqueartiemporenovacion === 'S') {
                this.habilRenovacion = false;
            } else {
                this.habilRenovacion = true;
            }
        } else {
            this.habilTipoContrato = false;
            this.habilRenovacion = false;
        }
    }

    cargarFolio(event: any) {
        const files = event.files;

        if (files.length !== 1) {
            return;
        }

        const reader = new FileReader();
        reader.readAsDataURL(files[0]);

        this.bloquearPagina();
        reader.onloadend = (evt) => {
            this.desbloquearPagina();
            var result = reader.result.toString().split(',')[1];
            if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
                return;
            }
            this.dtoHrContratos.auxRutaSustento = event.files[0].name;
            this.dtoHrContratos.auxArchivo = result.toString();
        };
    }

    descargarSustento() {
        if (this.dtoHrContratos.auxArchivo == null || this.dtoHrContratos.auxArchivo === undefined) {
            this.mostrarMensajeAdvertencia('No tiene ningún archivo adjunto para descargar.');
            return;
        }

        const a = document.createElement('a');
        a.href = 'data:application/octet-stream;base64,' + this.dtoHrContratos.auxArchivo;

        a.setAttribute('download', this.dtoHrContratos.auxRutaSustento);
        const b = document.createEvent('MouseEvents');
        b.initEvent('click', false, true);
        a.dispatchEvent(b);
    }
}