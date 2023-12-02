import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
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


import { CompanyownerComunService } from '@framework-comun/core/servicio/companyowner-comun.service';
import { FiltroComunCorrelativosmast } from '@framework-comun/core/dominio/filtro/FiltroComunCorrelativosmast';
import { CorrelativosmastComunService } from '@framework-comun/core/servicio/correlativosmast-comun.service';
import { DtoComunCorrelativosmast } from '@framework-comun/core/dominio/dto/DtoComunCorrelativosmast';
import { CompaniamastComunService } from '@framework-comun/core/servicio/companiamast-comun.service';

@Component({
    selector: 'app-correlativosmast-listado',
    templateUrl: './correlativosmast-listado.component.html',
    styles: [`
    .tablaHeaderCodigoSmall {
        text-align: center !important;
        width: 60px;
      }
    .tablaColumnaCodigoSmall{
        text-align: center !important;
        width: 60px;
    }
    `]
})
export class CorrelativosmastListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    lstCompanias: SelectItem[] = []
    lstTipos: SelectItem[] = []
    filtro: FiltroComunCorrelativosmast = new FiltroComunCorrelativosmast();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private correlativosmastService: CorrelativosmastComunService,
        servicioComun: ServicioComunService,
        private companiamastComunService: CompaniamastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        const p1 = this.cargarCompanias()
        const p2 = this.listarTipoItem()

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
        this.correlativosmastService.listarpaginado(this.filtro).then((res) => {
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
        this.router.navigate([ConstanteComun.ruta_correlativosmast_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunCorrelativosmast) {
        this.router.navigate([ConstanteComun.ruta_correlativosmast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunCorrelativosmast) {
        this.router.navigate([ConstanteComun.ruta_correlativosmast_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunCorrelativosmast) {
    }

    coreEliminar(dto: DtoComunCorrelativosmast) {
        this.router.navigate([ConstanteComun.ruta_correlativosmast_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    cargarCompanias(): Promise<number> {
        this.lstCompanias.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.companiamastComunService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstCompanias.push({ label: ele.nombre, value: ele.codigo });
            });
            //this.lstCompanias.push({ label: 'Default (999999)', value: '999999' });
            return 1;
        })
    }

    listarTipoItem(): Promise<number> {
        this.lstTipos.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.correlativosmastService.listarTipos().then(res => {
            res.forEach(ele => {
                this.lstTipos.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.correlativosmastService.exportarCorrelativos(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Números Correlativos ', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
