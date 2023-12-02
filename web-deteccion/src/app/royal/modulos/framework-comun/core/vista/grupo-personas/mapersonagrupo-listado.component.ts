import { DtoComunMaPersonagrupo } from './../../dominio/dto/DtoComunMaPersonagrupo';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { FiltroComunMaPersonagrupo } from './../../dominio/filtro/FiltroComunMaPersonagrupo';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';


@Component({
    selector: 'app-mapersonagrupo-listado',
    templateUrl: './mapersonagrupo-listado.component.html',
})
export class MaPersonagrupoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    filtro: FiltroComunMaPersonagrupo = new FiltroComunMaPersonagrupo();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private maPersonagrupoService: MaPersonagrupoComunService,
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
        const p2 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO, ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA, this.lstEstados);
        const p1 = 1

        Promise.all([p1, p2]).then((resp) => {
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
        this.maPersonagrupoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void {
        this.filtro.tipoexportar = tipo
        this.exportar()
    }

    coreMensaje(mensage: MensajeController): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteComun.ruta_mapersonagrupo_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunMaPersonagrupo) {
        this.router.navigate([ConstanteComun.ruta_mapersonagrupo_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunMaPersonagrupo) {
        this.router.navigate([ConstanteComun.ruta_mapersonagrupo_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunMaPersonagrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.maPersonagrupoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunMaPersonagrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.maPersonagrupoService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.maPersonagrupoService.exportarTipoPersona(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Tipos de Personas', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
