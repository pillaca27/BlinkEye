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
 
import { AcSucursalComunService } from '@framework-comun/contabilidad/servicio/acsucursal-comun.service';
import { FiltroComunAcSucursal } from '@framework-comun/contabilidad/dominio/filtro/FiltroComunAcSucursal';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { DtoComunAcSucursal } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcSucursal';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';

@Component({
    selector: 'app-acsucursal-listado',
    templateUrl: './acsucursal-listado.component.html',
})
export class AcSucursalListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    lstGrupoSucursal:SelectItem[]=[]
    filtro: FiltroComunAcSucursal = new FiltroComunAcSucursal();
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;
    
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private comunServive: MaPersonagrupoComunService,
        private acSucursalService: AcSucursalComunService,
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

        const p2 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO, ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA, this.lstEstados);
        const p1 = this.listarGrupoSucursal()

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
        this.acSucursalService.listarpaginado(this.filtro).then((res) => {
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
        this.router.navigate([ConstanteComun.ruta_acsucursal_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunAcSucursal){
        this.router.navigate([ConstanteComun.ruta_acsucursal_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunAcSucursal){
        this.router.navigate([ConstanteComun.ruta_acsucursal_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunAcSucursal) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.acSucursalService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunAcSucursal) {
        this.router.navigate([ConstanteComun.ruta_acsucursal_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    listarGrupoSucursal(): Promise<number> {
        return this.comunServive.listarGrupoSucursal().then(res => {
            res.forEach(ele => {
                this.lstGrupoSucursal.push({ label: ele.nombre, value: ele.codigo.trim() });
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
        this.acSucursalService.exportarSucursales(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Sucursales', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
