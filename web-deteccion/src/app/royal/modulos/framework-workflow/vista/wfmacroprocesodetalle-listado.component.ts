import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';

import { WfMacroProcesoDetalleService } from './../servicio/wfmacroprocesodetalle.servicio';
import { DtoWfMacroProcesoDetalle } from './../dominio/dto/DtoWfMacroProcesoDetalle';
import { FiltroWfMacroProcesoDetalle } from './../dominio/filtro/FiltroWfMacroProcesoDetalle';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';

@Component({
    selector: 'app-wfmacroprocesodetalle-listado',
    templateUrl: './wfmacroprocesodetalle-listado.component.html',
})
export class WfMacroProcesoDetalleListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    filtro: FiltroWfMacroProcesoDetalle = new FiltroWfMacroProcesoDetalle();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private wfMacroProcesoDetalleService: WfMacroProcesoDetalleService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        const p1 = this.miscelaneosListar(
            this.comboEtiquetaTipo.LISTADO,
            ConstanteWorkflow.APLICACION,
            ConstanteWorkflow.MISC_ESTADO_GENERICO,
            ConstanteWorkflow.DEFECTO_COMPANIA,
            this.lstEstados
        );

        Promise.all([p1]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.desbloquearPagina();
        });
    }

    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar();
        }
    }

    coreBuscar(): void {
        this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.grillaCargarDatos({ first: this.dataTableComponent.first });
    }

    grillaCargarDatos(event: LazyLoadEvent) {
        if (this.formularioOninit == true) return;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.wfMacroProcesoDetalleService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroprocesodetalle_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoWfMacroProcesoDetalle) {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroprocesodetalle_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoWfMacroProcesoDetalle) {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroprocesodetalle_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoWfMacroProcesoDetalle) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.wfMacroProcesoDetalleService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoWfMacroProcesoDetalle) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.wfMacroProcesoDetalleService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreSeleccionar(dto: any) {
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSalir() {
        //this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroWfMacroProcesoDetalle();
        //this.verVentanaEmergente=true;
    }

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

}
