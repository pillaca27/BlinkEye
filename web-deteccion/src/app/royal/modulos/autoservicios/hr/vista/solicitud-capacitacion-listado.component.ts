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
import { FiltroSolicitudCapacitacionListado } from '../dominio/filtro/FiltroSolicitudCapacitacionListado';
import { HrCapacitacionServicio } from '../servicio/HrCapacitacionServicio';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { UnidadnegocioSelectorComponent } from '@framework-comun/core/vista/unidadnegocio-selector.component';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { PuestoSelectorComponent } from '@framework-comun/hr/vista/puesto-selector.component';
import { SucursalselectorComponent } from '@framework-comun/contabilidad/vista/sucursalselector.component';
import { CursoSelectorComponent } from '@framework-comun/hr/vista/curso-selector.component';
import { UnidadoperativaSelectorComponent } from '@framework-comun/hr/vista/unidadoperativa-selector.component';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { HrCentroEstudiosSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';

@Component({
    templateUrl: './solicitud-capacitacion-listado.component.html'
})
export class SolicitudCapacitacionListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(PuestoSelectorComponent, { static: false }) puestoSelectorComponent: PuestoSelectorComponent;
    @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: SucursalselectorComponent;
    @ViewChild(CursoSelectorComponent, { static: false }) cursoSelectorComponent: CursoSelectorComponent;
    @ViewChild(UnidadoperativaSelectorComponent, { static: false }) unidadoperativaSelectorComponent: UnidadoperativaSelectorComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(HrCentroEstudiosSelectorComponent, { static: false }) hrCentroEstudiosSelectorComponent: HrCentroEstudiosSelectorComponent;
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;

    filtro: FiltroSolicitudCapacitacionListado = new FiltroSolicitudCapacitacionListado();
    registroSeleccionado: any;

    constructor(
        private hrCapacitacionServicio: HrCapacitacionServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        //Nombre de sesion para guardar los filtros
        this.nombreParametroSession = "solicitudcapacitacion";
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

        this.hrCapacitacionServicio.solicitudListado(this.filtro)
            .then(res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            });
    }

    coreBuscar(tabla: LazyLoadEvent) {
        this.dt.reset();
    }

    coreNuevo() {
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_capacitacion_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }

    coreEditar(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_capacitacion_mantenimiento, this.ACCIONES.EDITAR, dto.uuid], { skipLocationChange: true });
    }

    coreVer(dto: any) {
        if (dto.uuid == null || dto.uuid == undefined) {
            this.mostrarMensajeAdvertencia("Sin UUID");
            return;
        }
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_capacitacion_mantenimiento, this.ACCIONES.VER, dto.uuid], { skipLocationChange: true });
    }

    coreAnular(dto: any) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: this.getMensajePreguntaAnular(),
            accept: () => {
                this.bloquearPagina();
                this.hrCapacitacionServicio.solicitudAnular(dto).then((res) => {
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
        else if (mensage.componente == 'EMPLEADO') {
            this.filtro.empleado = mensage.resultado.persona;
            this.filtro.auxEmpleadoNombre = mensage.resultado.busqueda;
        }
        else if (mensage.componente == 'UNIDADNEGOCIO') {
            this.filtro.unidadNegocio = mensage.resultado.unidadnegocio;
            this.filtro.auxUnidadnegocioNombre = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'TIPOCAPACITACION') {
            this.filtro.tipoCapacitacion = mensage.resultado.codigo;
            this.filtro.auxTipocapacitacionNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'PUESTO') {
            this.filtro.codigoCargo = mensage.resultado.codigopuesto;
            this.filtro.auxCodigoCargoNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'SUCURSAL') {
            this.filtro.sucursal = mensage.resultado.sucursal;
            this.filtro.auxSucursalNombre = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'CURSO') {
            this.filtro.curso = mensage.resultado.curso;
            this.filtro.auxCursoNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'UNIDADOPERATIVA') {
            this.filtro.unidadOperativa = mensage.resultado.unidadoperativa;
            this.filtro.auxUnidadOperativaNombre = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'CENTROCOSTOS') {
            this.filtro.centroCosto = mensage.resultado.costcenter;
            this.filtro.auxCentroCostoNombre = mensage.resultado.localname;
        }
        else if (mensage.componente == 'TIPOCURSO') {
            this.filtro.cursoTipo = mensage.resultado.codigo;
            this.filtro.auxCursoTipoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'CENTROESTUDIO') {
            this.filtro.centroCapacitacion = mensage.resultado.id;
            this.filtro.auxCentroCapacitacionNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'SITUACEMP') {
            this.filtro.estadoEmpleado = mensage.resultado.codigo;
            this.filtro.auxEstadoEmpleadoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'ESTADO') {
            this.filtro.estado = mensage.resultado.codigo;
            this.filtro.auxEstadoNombre = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'TIPOPLANILLA') {
            this.filtro.tipoPlanilla = mensage.resultado.codigo;
            this.filtro.auxTipoPlanillaNombre = mensage.resultado.descripcion;
        }
    }
    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }
    buscarEmpleado() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }
    buscarUnidadNegocio() {
        this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO', null));
    }
    buscarTipoCapacitacion() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOCAPACITACION', null), 'TIPO DE CAPACITACION', 'HR', 'TIPCAPACIT', '999999');
    }
    buscarPuesto() {
        this.puestoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PUESTO', null));
    }
    buscarSucursal() {
        this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL', null));
    }
    buscarCurso() {
        this.cursoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CURSO', null));
    }
    buscarUnidadOperativa() {
        this.unidadoperativaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADOPERATIVA', null));
    }
    buscarCentroCosto() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS', null));
    }
    buscarTipoCurso() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOCURSO', null), 'TIPO DE CURSO', 'HR', 'TIPOCURSO', '999999');
    }
    buscarCentroCapacitacion() {
        this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO', null));
    }
    buscarSituacionEmpleado() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'SITUACEMP', null), 'SITUACION DEL EMPLEADO', 'HR', 'SITUACEMP', '999999');
    }
    buscarEstado() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ESTADO', null), 'ESTADO', 'HR', 'ESTCAPACIT', '999999');
    }
    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA', null));
    }
}
