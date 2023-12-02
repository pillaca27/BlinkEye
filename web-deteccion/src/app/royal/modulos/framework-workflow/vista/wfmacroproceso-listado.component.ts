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
import { WfMacroProcesoService } from './../servicio/wfmacroproceso.servicio';
import { DtoWfMacroProceso } from './../dominio/dto/DtoWfMacroProceso';
import { FiltroWfMacroProceso } from './../dominio/filtro/FiltroWfMacroProceso';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';

@Component({
    selector: 'app-wfmacroproceso-listado',
    templateUrl: './wfmacroproceso-listado.component.html',
})
export class WfMacroProcesoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myNombre', { static: false }) myNombre: ElementRef;

    lstEstados: SelectItem[] = [];
    filtro: FiltroWfMacroProceso = new FiltroWfMacroProceso();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private wfMacroProcesoService: WfMacroProcesoService,
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
            ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO,
            ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados
        );

        Promise.all([p1]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.coreBuscar();
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
        this.wfMacroProcesoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroproceso_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoWfMacroProceso) {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroproceso_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoWfMacroProceso) {
        this.router.navigate([ConstanteWorkflow.ruta_wfmacroproceso_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoWfMacroProceso) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.wfMacroProcesoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoWfMacroProceso) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.wfMacroProcesoService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    ngAfterViewInit() {
        this.myNombre.nativeElement.focus();
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
        this.filtro = new FiltroWfMacroProceso();
        //this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        /*  if (mensage.componente == ConstanteWorkflow.GENERICO_SELECTOR) {
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
