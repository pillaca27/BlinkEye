import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FiltroSolicitudRenovacioncontratoListado } from '../dominio/filtro/FiltroSolicitudRenovacioncontratoListado';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { HrRenovacioncontratoServicio } from '../servicio/HrRenovacioncontratoServicio';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
import { TipocontratoSelectorComponent } from './tipocontrato-selector.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { DtoHrContratos } from '../dominio/dto/DtoHrContratos';
import { SolicitudRenovacioncontratoMantenimientoComponent } from '@framework-workflow/vista/solicitud-renovacioncontrato-mantenimiento.component';

@Component({
    templateUrl: './solicitud-renovacioncontrato-listado.component.html'
})
export class SolicitudRenovacioncontratoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;
    @ViewChild(TipocontratoSelectorComponent, { static: false }) tipocontratoSelectorComponent: TipocontratoSelectorComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(SolicitudRenovacioncontratoMantenimientoComponent, { static: false }) solicitudRenovacioncontratoMantenimientoComponent: SolicitudRenovacioncontratoMantenimientoComponent;

    textoBotonSolicitud: string;
    verBotonSolicitud: Boolean = false;
    habilitarBotonSolicitud: Boolean = false;
    filtro: FiltroSolicitudRenovacioncontratoListado = new FiltroSolicitudRenovacioncontratoListado();
    registroSeleccionado: DtoHrContratos;

    constructor(
        private servicio: HrRenovacioncontratoServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        //Nombre de sesion para guardar los filtros
        this.nombreParametroSession = "solicitudrenovacioncontrato";
        //Restaurar filtros
        this.filtro = this.formularioFiltrosRestaurarNombre(this.filtro, this.nombreParametroSession);
        this.filtro.estadoContrato = 'A';
        this.filtro.auxEstadoContratoNombre = 'Vigente';
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

        this.servicio.solicitudListado(this.filtro)
            .then(res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            });
    }

    coreBuscar(tabla: LazyLoadEvent) {
        this.dt.reset();
    }
    coreNuevo() {
    }
    coreEditar(dto: any) {
    }
    coreVer(dto: any) {
    }
    coreAnular(dto: any) {
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
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {

        if (mensage.componente == 'TIPOCONTRATO') {
            this.filtro.tipoContrato = mensage.resultado.codigo;
            this.filtro.auxTipoContratoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'TIPOPLANILLA') {
            this.filtro.tipoPlanilla = mensage.resultado.codigo;
            this.filtro.auxTipoPlanillaNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'ESTADOSOL') {
            this.filtro.estadoSolicitud = mensage.resultado.codigo;
            this.filtro.auxEstadoSolicitudNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'ESTADOCON') {
            this.filtro.estadoContrato = mensage.resultado.codigo;
            this.filtro.auxEstadoContratoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'EMPLEADO') {
            this.filtro.empleado = mensage.resultado.persona;
            this.filtro.auxEmpleadoNombre = mensage.resultado.busqueda;
        }
    }

    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA', null));
    }
    buscarTipoContrato() {
        this.tipocontratoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOCONTRATO', null));
    }
    buscarEstadoSolicitud() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADOSOL', null), 'ESTADO DE SOLICITUD', 'HR', 'ESTRECONS', '999999');
    }
    buscarEstadoContrato() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADOCON', null), 'ESTADO DE CONTRATO', 'HR', 'ESTRECONC', '999999');
    }
    buscarEmpleado() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }
    onRowSelect(event) {
        this.verBotonSolicitud = true;
        this.habilitarBotonSolicitud = true;
        var reg = event.data as DtoHrContratos;
        if (reg.solicitud > 0) {
            if (reg.estadoSolicitud === 'S') {
                this.textoBotonSolicitud = 'Modif. Solicitud';
            }
            else {
                this.textoBotonSolicitud = 'Ver Solicitud';
            }
        } else {
            this.textoBotonSolicitud = 'Agregar Solicitud';
        }
    }

    verSolicitud() {

        if (this.registroSeleccionado === undefined || this.registroSeleccionado == null) {
            this.messageService.clear();
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe seleccionar un registro' });
            return;
        }

        if (this.textoBotonSolicitud === 'Agregar Solicitud') {
            this.registroSeleccionado.tipoEvento = 'SOLICITARRENOVACION';
            this.registroSeleccionado.auxAccion = 'AGREGAR';
        } else {
            this.registroSeleccionado.tipoEvento = 'VERSOLICITUD';
            if (this.registroSeleccionado.estadoSolicitud === 'S') {
                this.registroSeleccionado.auxAccion = 'MODIFICAR';
            } else {
                this.registroSeleccionado.auxAccion = 'VER';
            }
        }

        this.bloquearPagina();
        this.servicio.validarPedidoSolicitud(this.registroSeleccionado).then(
            res => {
                if (this.transaccionResultadoSimple(res)) {
                    this.solicitudRenovacioncontratoMantenimientoComponent.verVentana(this.registroSeleccionado);
                    this.habilitarBotonSolicitud = false;
                    this.registroSeleccionado = null;
                }
                else {
                    this.desbloquearPagina();
                }
            }
        );
    }
}