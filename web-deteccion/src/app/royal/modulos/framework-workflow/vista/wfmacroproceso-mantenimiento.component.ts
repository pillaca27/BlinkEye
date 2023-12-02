import { WfMacroProcesoDetalleService } from './../servicio/wfmacroprocesodetalle.servicio';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { InputNumber } from 'primeng/inputnumber';
import { Calendar } from '@fullcalendar/core';
import { Dropdown } from 'primeng/dropdown';

import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';


import { WfMacroProcesoService } from '../servicio/wfmacroproceso.servicio';
import { DtoWfMacroProceso } from './../dominio/dto/DtoWfMacroProceso';
import { FiltroWfMacroProceso } from './../dominio/filtro/FiltroWfMacroProceso';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { DtoWfMacroProcesoDetalle } from '../dominio/dto/DtoWfMacroProcesoDetalle';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';

@Component({
    selector: 'app-wfmacroproceso-mantenimiento',
    templateUrl: 'wfmacroproceso-mantenimiento.component.html'
})
export class WfMacroProcesoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myMacroProcesoId', { static: false }) myMacroProcesoId: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoWfMacroProceso = new DtoWfMacroProceso();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private wfMacroProcesoService: WfMacroProcesoService,
        private wfMacroProcesoDetalleService: WfMacroProcesoDetalleService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados);

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.myMacroProcesoId.nativeElement.focus();

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoWfMacroProceso);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.wfMacroProcesoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myMacroProcesoId });

    }

    coreGuardar(): void {
        this.cargarControlesRequeridos();
        /* var mensajes = this.validarControlesRequeridos(this.lstControlesRequeridos);
        if (mensajes.length > 0) {
            this.mostrarMensajeListado(mensajes);
            return;
        } */

        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.wfMacroProcesoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.wfMacroProcesoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroproceso_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        /* if (mensage.componente == ConstanteWorkflow.GENERICO_SELECTOR) {
            //
        } */
    }

    coreMostrarSelector(selector: string) {
        /*  if (selector == ConstanteWorkflow.GENERICO_SELECTOR) {
             console.log(selector);
             //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
         } */
    }
    nuevodetalle() {
        let nuevo = new DtoWfMacroProcesoDetalle();
        nuevo.flgactualizar = 'NUEVO';
        nuevo.macroProcesoId = this.dto.macroProcesoId;

        this.dto.detalle.push(nuevo);
    }

    eliminardetalle(dto: DtoWfMacroProcesoDetalle, indice: number) {
        if (dto.flgactualizar == 'NUEVO') {
            this.dto.detalle.splice(indice, 1)
        } else {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.messageService.clear();
                    this.wfMacroProcesoDetalleService.eliminar(dto).then((res) => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res))
                            this.dto.detalle.splice(indice, 1)
                    });
                },
                key: "confirm",
            });
        }

    }
}
