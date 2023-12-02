import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { FiltroSolicitudPrestamoListado } from '../dominio/filtro/FiltroSolicitudPrestamoListado';
import { PrPrestamoServicio } from '../servicio/PrPrestamoServicio';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { TipoprestamoSelectorComponent } from './tipoprestamo-selector.component';

@Component({
    templateUrl: './solicitud-prestamo-listado.component.html'
})
export class SolicitudPrestamoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    filtro: FiltroSolicitudPrestamoListado = new FiltroSolicitudPrestamoListado();
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(TipoprestamoSelectorComponent, { static: false }) tipoprestamoSelectorComponent: TipoprestamoSelectorComponent;
    registroSeleccionado: any;

    constructor(
        private prPrestamoServicio: PrPrestamoServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        //Nombre de sesion para guardar los filtros
        this.nombreParametroSession = "solicitudprestamo";
        //Restaurar filtros
        this.filtro = this.formularioFiltrosRestaurarNombre(this.filtro, this.nombreParametroSession);
        this.formularioOninit = false;
    }

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    }

    cargarListado(event: LazyLoadEvent) {

        //Se cancela el primer buscar que hace automatico la tabla
        if (this.formularioOninit) {
            this.formularioOninit = false;
            return;
        }

        this.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        //Luego de cada busqueda, se guardan los filtros
        sessionStorage.setItem(this.nombreParametroSession, JSON.stringify(this.filtro));

        this.prPrestamoServicio.solicitudListado(this.filtro)
            .then(res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            });
    }

    coreBuscar(tabla: LazyLoadEvent) {
        this.dt.reset();
    }

    coreNuevo() {
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_prestamo_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }

    coreEditar(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_prestamo_mantenimiento, this.ACCIONES.EDITAR, dto.uuid], { skipLocationChange: true });
    }

    coreVer(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_prestamo_mantenimiento, this.ACCIONES.VER, dto.uuid], { skipLocationChange: true });
    }

    coreAnular(dto: any) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: this.getMensajePreguntaAnular(),
            accept: () => {
                this.bloquearPagina();
                this.prPrestamoServicio.solicitudAnular(dto).then((res) => {
                    if (this.transaccionResultado(res)) {
                        this.coreBuscar(null);
                    }
                    else {
                        this.desbloquearPagina();
                    }
                });
            },
            key: "confirm",
        });
    }
    coreBusquedaRapida(filtro: string): void {
        throw new Error('Method not implemented.');
    }
    coreFiltro(flag: boolean): void {
        throw new Error('Method not implemented.');
    }
    coreEliminar(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'ESTADO') {
            this.filtro.estado = mensage.resultado.codigo;
            this.filtro.auxEstadoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'TIPOPRESTAMO') {
            this.filtro.tipoPrestamo = mensage.resultado.codigo;
            this.filtro.auxTipoPrestamoNombre = mensage.resultado.descripcion;
        }
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }

    buscarTipoPrestamo() {
        this.tipoprestamoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPRESTAMO', null));
    }
    buscarTipoPlanilla() {

    }
    buscarEmpleado() {

    }
    buscarEstado() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADO', null), 'ESTADO', 'PR', 'ESTPRESTA', '999999');
    }
    buscarUnidadNegocio() {

    }
    buscarCompania() {

    }
}
