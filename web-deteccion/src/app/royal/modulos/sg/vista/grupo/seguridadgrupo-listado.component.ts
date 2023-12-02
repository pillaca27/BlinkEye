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
import { DtoSeguridadgrupo } from '../../dominio/dto/DtoSeguridadgrupo';
import { FiltroSeguridadgrupo } from '../../dominio/filtro/FiltroSeguridadgrupo';
import { SeguridadgrupoService } from '../../servicio/seguridadgrupo.servicio';
import { ConstanteSg } from '../../ConstanteSg';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';


@Component({
    selector: 'app-seguridadgrupo-listado',
    templateUrl: './seguridadgrupo-listado.component.html',
})
export class SeguridadgrupoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    lstEstados: SelectItem[] = [];
    lstAplicaciones: SelectItem[] = [];
    filtro: FiltroSeguridadgrupo = new FiltroSeguridadgrupo();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private seguridadgrupoService: SeguridadgrupoService,
        private aplicacionesmastService: AplicacionesmastComunService,
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
            ConstanteSg.APLICACION,
            ConstanteSg.MISC_ESTADO_GENERICO,
            ConstanteSg.DEFECTO_COMPANIA,
            this.lstEstados
        );

        const p2 = this.cargarAplicaciones();
        Promise.all([p1, p2]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.desbloquearPagina();
            // if (this.filtro.paginacion.filtroSessionGuardado==true)
            this.coreBuscar();
        });
    }
    cargarAplicaciones(): Promise<number> {
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
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
        this.seguridadgrupoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteSg.ruta_seguridadgrupo_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoSeguridadgrupo) {
        this.router.navigate([ConstanteSg.ruta_seguridadgrupo_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoSeguridadgrupo) {
        this.router.navigate([ConstanteSg.ruta_seguridadgrupo_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSeguridadgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.seguridadgrupoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreActivar(dto: DtoSeguridadgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea activar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.seguridadgrupoService.activar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoSeguridadgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.seguridadgrupoService.eliminar(dto).then((res) => {
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
        //this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroSeguridadgrupo();
        // this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteSg.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
