import { SelectItem, MessageService } from 'primeng/api';
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

 
import { WfMacroProcesoDetalleService } from '../servicio/wfmacroprocesodetalle.servicio';
import { DtoWfMacroProcesoDetalle } from './../dominio/dto/DtoWfMacroProcesoDetalle';
import { FiltroWfMacroProcesoDetalle } from './../dominio/filtro/FiltroWfMacroProcesoDetalle';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';

@Component({
    selector: 'app-wfmacroprocesodetalle-mantenimiento',
    templateUrl: 'wfmacroprocesodetalle-mantenimiento.component.html'
})
export class WfMacroProcesoDetalleMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myMacroProcesoId', { static: false }) myMacroProcesoId: ElementRef;
    @ViewChild('myProcesoOrigenId', { static: false }) myProcesoOrigenId: ElementRef;
    @ViewChild('myProcesoDestinoId', { static: false }) myProcesoDestinoId: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoWfMacroProcesoDetalle = new DtoWfMacroProcesoDetalle();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private wfMacroProcesoDetalleService: WfMacroProcesoDetalleService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        /*const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteWorkflow.APLICACION,ConstanteWorkflow.MISC_ESTADO_GENERICO,ConstanteWorkflow.DEFECTO_COMPANIA,
            this.lstEstados);*/

        Promise.all([/*p1*/]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.myMacroProcesoId.nativeElement.focus();

            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoWfMacroProcesoDetalle);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.wfMacroProcesoDetalleService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    cargarControlesRequeridos() {
        this.lstControlesRequeridos=[];
        this.lstControlesRequeridos.push({ control: this.myMacroProcesoId });
        this.lstControlesRequeridos.push({ control: this.myProcesoOrigenId });
        this.lstControlesRequeridos.push({ control: this.myProcesoDestinoId });

    }

    coreGuardar(): void {
        this.cargarControlesRequeridos();
       /*  var mensajes = this.validarControlesRequeridos(this.lstControlesRequeridos);
        if (mensajes.length > 0) {
            this.mostrarMensajeListado(mensajes);
            return;
        } */

        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.wfMacroProcesoDetalleService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.wfMacroProcesoDetalleService.actualizar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }
    }

    coreSalir(): void {
        //this.router.navigate([ConstanteWorkflow.ruta_wfmacroprocesodetalle_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreAccion(accion: any):void{}

    coreMensaje(mensage: MensajeController): void {
     /*    if (mensage.componente == ConstanteWorkflow.GENERICO_SELECTOR) {
            //
        } */
    }

    coreMostrarSelector(selector:string) {
     /*    if (selector == ConstanteWorkflow.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        } */
    }

}
