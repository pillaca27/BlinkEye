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
import { DtoSeguridadconcepto } from '../../dominio/dto/DtoSeguridadconcepto';
import { FiltroSeguridadconcepto } from '../../dominio/filtro/FiltroSeguridadconcepto';
import { SeguridadconceptoService } from '../../servicio/seguridadconcepto.servicio';
import { ConstanteSg } from '../../ConstanteSg';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';
import { SeguridadgrupoService } from '../../servicio/seguridadgrupo.servicio';
import { FiltroSeguridadgrupo } from '../../dominio/filtro/FiltroSeguridadgrupo';


@Component({
    selector: 'app-seguridadconcepto-listado',
    templateUrl: './seguridadconcepto-listado.component.html',
})
export class SeguridadconceptoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    lstEstados: SelectItem[] = [];
    filtro: FiltroSeguridadconcepto = new FiltroSeguridadconcepto();
    lstAplicaciones: SelectItem[] = [];
    lstGrupos: SelectItem[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private seguridadconceptoService: SeguridadconceptoService,
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
        //const p3 = this.cargarGrupos();
        Promise.all([p1, p2/* , p3 */]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.desbloquearPagina();

            if (!this.estaVacio(this.filtro.aplicacioncodigo))
                this.onChangeAplicacion();
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
    cargarGrupos(): Promise<number> {
        this.lstGrupos = [];
        this.lstGrupos.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        let filtro: FiltroSeguridadgrupo = new FiltroSeguridadgrupo();
        filtro.aplicacioncodigo = this.filtro.aplicacioncodigo;
        return this.seguridadgrupoService.listardtoactivosPorAplicacion(filtro).then(res => {
            res.forEach(ele => {
                this.lstGrupos.push({ label: ele.descripcion, value: ele.grupo });
            });
            return 1;
        })
    }
    onChangeAplicacion() {
        this.bloquearPagina();
        this.filtro.grupo = null;
        const p1 = this.cargarGrupos();
        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
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
        this.seguridadconceptoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteSg.ruta_seguridadconcepto_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoSeguridadconcepto) {
        this.router.navigate([ConstanteSg.ruta_seguridadconcepto_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoSeguridadconcepto) {
        this.router.navigate([ConstanteSg.ruta_seguridadconcepto_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSeguridadconcepto) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.seguridadconceptoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreActivar(dto: DtoSeguridadconcepto) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea activar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.seguridadconceptoService.activar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoSeguridadconcepto) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.seguridadconceptoService.eliminar(dto).then((res) => {
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
        this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroSeguridadconcepto();
        this.verVentanaEmergente = true;
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
