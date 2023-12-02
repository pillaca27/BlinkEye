import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { DtoComunCompaniamast } from './../../dominio/dto/DtoComunCompaniamast';
 
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
 
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { FiltroComunCompaniamast } from '@framework-comun/core/dominio/filtro/FiltroComunCompaniamast';
import { CompaniamastComunService } from '@framework-comun/core/servicio/companiamast-comun.service';
 

@Component({
    selector: 'app-companiamast-listado',
    templateUrl: './companiamast-listado.component.html',
    styles: [`
    .tablaHeaderCodigoBig {
        text-align: center !important;
        width: 250px;
      }
    .tablaColumnaCodigoBig{
        text-align: center !important;
        width: 250px;
    }
    `]
})
export class CompaniamastListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    filtro: FiltroComunCompaniamast = new FiltroComunCompaniamast();
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;
    rowGroupMetadata: any;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private companiamastService: CompaniamastComunService,
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

        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO,ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,this.lstEstados);

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
        sessionStorage.setItem(ConstanteAngular.FILTROSESION,JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.companiamastService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.updateRowGroupMetaData()
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
        this.router.navigate([ConstanteComun.ruta_companiamast_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunCompaniamast){
        this.router.navigate([ConstanteComun.ruta_companiamast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunCompaniamast){
        this.router.navigate([ConstanteComun.ruta_companiamast_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunCompaniamast) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.companiamastService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunCompaniamast) {
        this.router.navigate([ConstanteComun.ruta_companiamast_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    updateRowGroupMetaData() {
        this.rowGroupMetadata = {};
    
        if (this.filtro.paginacion.paginacionListaResultado) {
            for (let i = 0; i < this.filtro.paginacion.paginacionListaResultado.length; i++) {
                let rowData = this.filtro.paginacion.paginacionListaResultado[i];
                let representativeName = rowData.tipocompania;
                
                if (i == 0) {
                    this.rowGroupMetadata[representativeName] = { index: 0, size: 1 };
                }
                else {
                    let previousRowData = this.filtro.paginacion.paginacionListaResultado[i - 1];
                    let previousRowGroup = previousRowData.tipocompania;
                    if (representativeName === previousRowGroup)
                        this.rowGroupMetadata[representativeName].size++;
                    else
                        this.rowGroupMetadata[representativeName] = { index: i, size: 1 };
                }
    
            }
    
        }
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.companiamastService.exportarCompanias(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Compañías', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
