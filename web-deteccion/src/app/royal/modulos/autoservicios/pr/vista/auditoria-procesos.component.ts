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
import { FiltroAuditoriaProcesos } from '../dominio/filtro/FiltroAuditoriaProcesos';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
import { PrPlanillaempleadoServicio } from '../servicio/PrPlanillaempleadoServicio';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';

@Component({
    templateUrl: './auditoria-procesos.component.html'
})
export class AuditoriaProcesosComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    filtro: FiltroAuditoriaProcesos = new FiltroAuditoriaProcesos();
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;

    registroSeleccionado: any;
    faltantes: boolean = false;

    lstPeriodos: SelectItem[] = [];

    constructor(
        private prPlanillaempleadoServicio: PrPlanillaempleadoServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        //Nombre de sesion para guardar los filtros
        this.nombreParametroSession = "auditoriaprocesos";
        //Restaurar filtros
        //this.filtro = this.formularioFiltrosRestaurarNombre(this.filtro, this.nombreParametroSession);

        this.formularioOninit = true;
    }

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.bloquearPagina();

        this.filtro.compania = JSON.parse(sessionStorage.getItem('usuarioActual')).companiaCodigo;
        this.filtro.auxCompaniaNombre = JSON.parse(sessionStorage.getItem('usuarioActual')).companiaNombre.trim();

        this.prPlanillaempleadoServicio.listarAuditoriaPeriodo().then(
            res => {
                this.lstPeriodos.push({ value: null, label: ' -- Seleccione --' });
                res.forEach(r => {
                    this.lstPeriodos.push({ value: r.codigo, label: r.descripcion });
                });
                this.filtro.periodo = null;
                this.filtro.periodo = this.lstPeriodos[1].value;
                this.desbloquearPagina();
            }
        );
    }


    exportar() {
        if (this.filtro.periodo == null) {
            this.mostrarMensajeAdvertencia('Seleccione el periodo');
            return;
        }
        this.bloquearPagina();
        this.prPlanillaempleadoServicio.exportarAuditoriaProcesos(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Auditoría de Boletas', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    this.desbloquearPagina();
                    console.log(error);
                }, () => {
                    this.desbloquearPagina();
                    console.log('Completed file download.');
                }
            );
    }

    cargarListado(event: LazyLoadEvent) {

        //Se cancela el primer buscar que hace automatico la tabla
        if (this.formularioOninit) {
            this.formularioOninit = false;
            return;
        }

        if (this.filtro.periodo == null) {
            this.mostrarMensajeAdvertencia('Seleccione el periodo');
            return;
        }

        this.filtro.auxPeriodoNombre = this.lstPeriodos.find(x => x.value == this.filtro.periodo).label;
        this.filtro.faltantes = this.faltantes ? 'S' : 'N';

        this.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        //Luego de cada busqueda, se guardan los filtros
        sessionStorage.setItem(this.nombreParametroSession, JSON.stringify(this.filtro));

        this.prPlanillaempleadoServicio.auditoriaProcesos(this.filtro)
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
    }
    coreFiltro(flag: boolean): void {
    }
    coreEliminar(dto: any): void {
    }
    coreExportar(tipo: string): void {
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'COMPANIA') {
            this.filtro.compania = mensage.resultado.companyowner;
            this.filtro.auxCompaniaNombre = mensage.resultado.description.trim();
        }
        else if (mensage.componente == 'PLANILLA') {
            this.filtro.planilla = mensage.resultado.codigo;
            this.filtro.auxPlanillaNombre = mensage.resultado.nombre.trim();
        }
        else if (mensage.componente == 'EMPLEADO') {
            this.filtro.empleado = mensage.resultado.persona;
            this.filtro.auxEmpleadoNombre = mensage.resultado.busqueda.trim();
        }
        else if (mensage.componente == 'FUNCIONALIDAD') {
            this.filtro.func = mensage.resultado.codigo;
            this.filtro.auxFuncNombre = mensage.resultado.nombre;
        }
    }
    coreAccion(accion: string): void {
    }

    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PLANILLA', null));
    }
    buscarEmpleado() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }
    buscarCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', 'S'));
    }
    buscarFunc() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'FUNCIONALIDAD', null), 'FUNCIONALIDAD', 'SY', 'FUNAUDIT', '999999');
    }

    validarFlag() {
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistrosEncontrados = 0;
    }
}
