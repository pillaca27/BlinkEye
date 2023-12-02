import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { SelectorAplicacionesMastComponent } from './selectores/selector-aplicaciones-mast.component';
import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from './../../servicioComun.service';
import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
import { BotonesListadoComponent } from './../../../../framework/angular/controles/botones/botones-listado.component';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

import { SyProcesomstService } from './../servicio/syprocesomst.servicio';
import { DtoSyProcesomst } from './../dominio/dto/DtoSyProcesomst';
import { FiltroSyProcesomst } from './../dominio/filtro/FiltroSyProcesomst';
import { ConstanteComunSistema } from '../ConstanteComunSistema';
import { MensajeController } from '@framework/angular/dominio/MensajeController';


@Component({
    selector: 'app-syprocesomst-listado',
    templateUrl: './syprocesomst-listado.component.html',
})
export class SyProcesomstListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    @ViewChild(SelectorAplicacionesMastComponent, { static: false }) selectorAplicacionesMastComponent: SelectorAplicacionesMastComponent;

    lstEstados: SelectItem[] = [];
    filtro: FiltroSyProcesomst = new FiltroSyProcesomst();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private syProcesomstService: SyProcesomstService,
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
            this.desbloquearPagina();

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
        this.syProcesomstService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteComunSistema.ruta_syprocesomst_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoSyProcesomst) {
        this.router.navigate([ConstanteComunSistema.ruta_syprocesomst_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoSyProcesomst) {
        this.router.navigate([ConstanteComunSistema.ruta_syprocesomst_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSyProcesomst) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.syProcesomstService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoSyProcesomst) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.syProcesomstService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    ngAfterViewInit() {
        this.myDescripcion.nativeElement.focus();
    }

    coreSeleccionar(dto: any) {
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSalir() {
        // this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroSyProcesomst();
        // this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteComunSistema.GENERICO_APLICACION) {
            this.filtro.aplicacioncodigo = mensage.resultado.aplicacioncodigo;
            this.filtro.descripcioncorta = mensage.resultado.descripcioncorta;
        }
    }

    coreMostrarSelector(selector: string) {
        /*  if (selector == ConstanteSistema.GENERICO_SELECTOR) {
             console.log(selector);
             //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
         } */
    }
    mostrarselectoraplicaciones() {
        this.selectorAplicacionesMastComponent.coreIniciarComponente(new MensajeController(this, 'APLICACION', ''));
    }
    limpiarselectoraplicaciones() {
        this.filtro.aplicacioncodigo = null;
        this.filtro.descripcioncorta = null;
    }
}
