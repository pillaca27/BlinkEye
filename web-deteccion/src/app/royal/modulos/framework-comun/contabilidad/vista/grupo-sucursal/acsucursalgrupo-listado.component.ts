import { DtoComunAcSucursalgrupo } from '../../dominio/dto/DtoComunAcSucursalgrupo';
import { FiltroAcSucursalgrupo } from './../../dominio/filtro/FiltroAcSucursalgrupo';
 

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
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { ConstanteComunContabilidad } from '@framework-comun/contabilidad/ConstanteComunContabilidad';
import { AcSucursalgrupomayorComunService } from '@framework-comun/contabilidad/servicio/acsucursalgrupomayor-comun.service';
import { AcSucursalgrupoComunService } from '@framework-comun/contabilidad/servicio/acsucursalgrupo-comun.service';
 


@Component({
    selector: 'app-acsucursalgrupo-listado',
    templateUrl: './acsucursalgrupo-listado.component.html',
})
export class AcSucursalgrupoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myDescripcionlocal', { static: false }) myDescripcionlocal: ElementRef;

    lstEstados: SelectItem[] = [];
    filtro: FiltroAcSucursalgrupo = new FiltroAcSucursalgrupo();
    lstGrupoMayor: SelectItem[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private acSucursalgrupoService: AcSucursalgrupoComunService,
        private acSucursalgrupomayorService: AcSucursalgrupomayorComunService,
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
        const p2 = this.cargarGrupoMayor();
        Promise.all([p1, p2]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.desbloquearPagina();
            //if (this.filtro.paginacion.filtroSessionGuardado==true)
            this.coreBuscar();
        });
    }

    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar();
        }
    }
    cargarGrupoMayor(): Promise<number> {
        this.lstGrupoMayor.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.acSucursalgrupomayorService.listarActivos().then(res => {
            res.forEach(obj => { this.lstGrupoMayor.push({ label: obj.nombre, value: obj.codigo }); });
            return 1;
        });
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
        this.acSucursalgrupoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteComunContabilidad.ruta_acsucursalgrupo_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunAcSucursalgrupo) {
        this.router.navigate([ConstanteComunContabilidad.ruta_acsucursalgrupo_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunAcSucursalgrupo) {
        this.router.navigate([ConstanteComunContabilidad.ruta_acsucursalgrupo_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunAcSucursalgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.acSucursalgrupoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreActivar(dto: DtoComunAcSucursalgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea activar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.acSucursalgrupoService.activar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunAcSucursalgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.acSucursalgrupoService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    ngAfterViewInit() {
        this.myDescripcionlocal.nativeElement.focus();
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
        this.filtro = new FiltroAcSucursalgrupo();
        this.verVentanaEmergente = true;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
