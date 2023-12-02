import { UsuarioComunSelectorComponent } from './../../seguridad/vista/usuariocomun-selector.component';
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
 
import { SyPreferencesService } from './../servicio/sypreferences.servicio';
import { DtoSyPreferences } from './../dominio/dto/DtoSyPreferences';
import { FiltroSyPreferences } from './../dominio/filtro/FiltroSyPreferences';
import { ConstanteComunSistema } from '../ConstanteComunSistema';

@Component({
    selector: 'app-sypreferences-listado',
    templateUrl: './sypreferences-listado.component.html',
})
export class SyPreferencesListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild(UsuarioComunSelectorComponent, { static: false }) usuarioComunSelectorComponent: UsuarioComunSelectorComponent;
    lstEstados: SelectItem[] = [];
    filtro: FiltroSyPreferences = new FiltroSyPreferences();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private syPreferencesService: SyPreferencesService,
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
        this.filtro.usuario = 'SYSTEM';
        this.syPreferencesService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void {}

    coreExportar(tipo: string): void {}

    coreFiltro(val: boolean): void {}

    coreAccion(accion: string): void {}

    coreNuevo(): void {
        this.router.navigate([ConstanteComunSistema.ruta_sypreferences_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoSyPreferences){
        this.router.navigate([ConstanteComunSistema.ruta_sypreferences_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoSyPreferences){
        this.router.navigate([ConstanteComunSistema.ruta_sypreferences_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSyPreferences) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.syPreferencesService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoSyPreferences) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.messageService.clear();
            this.syPreferencesService.eliminar(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreSeleccionar(dto: any){
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSalir(){
        //this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController){
        this.mensajeController = msj;
        this.filtro=new FiltroSyPreferences();
        //this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        /* if (mensage.componente == ConstanteSistema.GENERICO_SELECTOR) {
            //
        } */
    }

    coreMostrarSelector(selector:string) {
        /* if (selector == ConstanteSistema.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        } */
    }

    mostrarSelectorUsuario(){
        this.usuarioComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'USUARIOSEL', 'USUARIO'));
    }
    limpiarSelectorUsuario(){
        this.filtro.usuario = null;
        this.filtro.usuariodescripcion = null;
    }
}
