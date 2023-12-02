import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FiltroSolicitudRequerimientoListado } from '../dominio/filtro/FiltroSolicitudRequerimientoListado';
import { HrRequerimientoServicio } from '../servicio/HrRequerimientoServicio';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { UnidadnegocioSelectorComponent } from '@framework-comun/core/vista/unidadnegocio-selector.component';
import { PuestoSelectorComponent } from '@framework-comun/hr/vista/puesto-selector.component';
import { SucursalselectorComponent } from '@framework-comun/contabilidad/vista/sucursalselector.component';
import { UnidadoperativaSelectorComponent } from '@framework-comun/hr/vista/unidadoperativa-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
import { PostulanteSelectorComponent } from './postulante-selector.component';
import { PersonalReclutamientoSelectorComponent } from './personalreclutamiento-selector.component';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Component({
    templateUrl: './solicitud-requerimiento-listado.component.html'
})
export class SolicitudRequerimientoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(PuestoSelectorComponent, { static: false }) puestoSelectorComponent: PuestoSelectorComponent;
    @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: SucursalselectorComponent;
    @ViewChild(UnidadoperativaSelectorComponent, { static: false }) unidadoperativaSelectorComponent: UnidadoperativaSelectorComponent;
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;
    @ViewChild(PostulanteSelectorComponent, { static: false }) postulanteSelectorComponent: PostulanteSelectorComponent;
    @ViewChild(PersonalReclutamientoSelectorComponent, { static: false }) personalReclutamientoSelectorComponent: PersonalReclutamientoSelectorComponent;

    filtro: FiltroSolicitudRequerimientoListado = new FiltroSolicitudRequerimientoListado();
    registroSeleccionado: any;

    constructor(
        private hrRequerimientoServicio: HrRequerimientoServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        //Nombre de sesion para guardar los filtros
        this.nombreParametroSession = "solicitudrequerimiento";
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

        this.hrRequerimientoServicio.solicitudListado(this.filtro)
            .then(res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            });
    }

    coreBuscar(tabla: LazyLoadEvent) {
        this.dt.reset();
    }

    coreNuevo() {
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_requerimiento_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }

    coreEditar(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_requerimiento_mantenimiento, this.ACCIONES.EDITAR, dto.uuid], { skipLocationChange: true });
    }

    coreVer(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_requerimiento_mantenimiento, this.ACCIONES.VER, dto.uuid], { skipLocationChange: true });
    }

    coreAnular(dto: any) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: this.getMensajePreguntaAnular(),
            accept: () => {
                this.bloquearPagina();
                this.hrRequerimientoServicio.solicitudAnular(dto).then((res) => {
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
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'COMPANIA') {
            this.filtro.compania = mensage.resultado.companyowner;
            this.filtro.auxCompaniaNombre = mensage.resultado.description;
        }
        else if (mensage.componente == 'CENTROCOSTOS') {
            this.filtro.centroCosto = mensage.resultado.costcenter;
            this.filtro.auxCentroCostoNombre = mensage.resultado.localname;
        }
        else if (mensage.componente == 'TIPOREQUERIMIENTO') {
            this.filtro.tipo = mensage.resultado.codigo;
            this.filtro.auxTipoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'UNIDADNEGOCIO') {
            this.filtro.unidadNegocio = mensage.resultado.unidadnegocio;
            this.filtro.auxUnidadnegocioNombre = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'PUESTO') {
            this.filtro.codigoCargo = mensage.resultado.codigopuesto;
            this.filtro.auxCodigoCargoNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'MOTIVO') {
            this.filtro.motivo = mensage.resultado.codigo;
            this.filtro.auxMotivoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'SUCURSAL') {
            this.filtro.sucursal = mensage.resultado.sucursal;
            this.filtro.auxSucursalNombre = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'UNIDADOPERATIVA') {
            this.filtro.unidadOperativa = mensage.resultado.unidadoperativa;
            this.filtro.auxUnidadOperativaNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'TIPOPLANILLA') {
            this.filtro.tipoPlanilla = mensage.resultado.codigo;
            this.filtro.auxTipoPlanillaNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'ESTADOREQ') {
            this.filtro.estado = mensage.resultado.codigo;
            this.filtro.auxEstadoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'ESTADOPOST') {
            this.filtro.estadoPostulante = mensage.resultado.codigo;
            this.filtro.auxEstadoPostulanteNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'POSTULANTE') {
            this.filtro.postulante = mensage.resultado.postulante;
            this.filtro.auxPostulanteNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'PERSRECLUTAMIENTO') {
            this.filtro.responsable = mensage.resultado.empleado;
            this.filtro.auxResponsableNombre = mensage.resultado.nombre;
        }
    }
    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }
    buscarCentroCosto() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS', null));
    }
    buscarTipoRequerimiento() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOREQUERIMIENTO', null), 'TIPO DE REQUERIMIENTO', 'HR', 'TIPREQUERI', '999999');
    }
    buscarUnidadNegocio() {
        this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO', null));
    }
    buscarPuesto() {
        this.puestoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PUESTO', null));
    }
    buscarMotivo() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'MOTIVO', null), 'MOTIVO DE REQUERIMIENTO', 'HR', 'RSMOTIVO', '999999');
    }
    buscarSucursal() {
        this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL', null));
    }
    buscarUnidadOperativa() {
        this.unidadoperativaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADOPERATIVA', null));
    }
    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA', null));
    }
    buscarEstado() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADOREQ', null), 'ESTADO DE REQUERIMIENTO', 'HR', 'ESTREQUERI', '999999');
    }
    buscarEstadoPostulante() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADOPOST', null), 'ESTADO DE POSTULANTE', 'HR', 'ESTPOSTULA', '999999');
    }
    buscarPostulante() {
        this.postulanteSelectorComponent.coreIniciarComponente(new MensajeController(this, 'POSTULANTE', null));
    }
    buscarPersonalReclutamiento() {
        var dtoTabla = new DtoTabla();
        dtoTabla.codigo = this.filtro.compania;
        dtoTabla.descripcion = this.filtro.auxCompaniaNombre;
        var mensaje = new MensajeController(this, 'PERSRECLUTAMIENTO', null);
        mensaje.parametros = dtoTabla;
        this.personalReclutamientoSelectorComponent.coreIniciarComponente(mensaje);
    }
}
