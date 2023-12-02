import { AplicacionesmastComunService } from './../../../sg/servicio/aplicacionesmast-comun.service';
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
import { FiltroComunAplicacionesmast } from '@framework-comun/sg/dominio/filtro/FiltroComunAplicacionesmast';
import { DtoComunAplicacionesmast } from '@framework-comun/sg/dominio/dto/DtoComunAplicacionesmast';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';

@Component({
    selector: 'app-aplicacionesmast-listado',
    templateUrl: './aplicacionesmast-listado.component.html',
})
export class AplicacionesmastListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    filtro: FiltroComunAplicacionesmast = new FiltroComunAplicacionesmast();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
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
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR,this,this.objetoBoton);

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

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            tb.reset();
        }
    }
    

    coreBuscar(): void {
        this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.grillaCargarDatos({ first: this.dataTableComponent.first });
    }

    grillaCargarDatos(event: LazyLoadEvent) {
        if (this.formularioOninit == true) return;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION,JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.aplicacionesmastService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void {}

    coreExportar(tipo: string): void {
        this.filtro.tipoexportar = tipo
        this.exportar()
    }

    coreMensaje(mensage: MensajeController): void {}

    coreFiltro(val: boolean): void {}

    coreAccion(accion: string): void {}

    coreNuevo(): void {
        this.router.navigate([ConstanteComun.ruta_aplicacionesmast_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunAplicacionesmast){
        this.router.navigate([ConstanteComun.ruta_aplicacionesmast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunAplicacionesmast){
        this.router.navigate([ConstanteComun.ruta_aplicacionesmast_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunAplicacionesmast) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.aplicacionesmastService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunAplicacionesmast) {
        this.router.navigate([ConstanteComun.ruta_aplicacionesmast_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    
    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.aplicacionesmastService.exportarAplicaciones(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Aplicaciones ', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
