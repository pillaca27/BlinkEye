import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { WfReemplazo, WfReemplazoPk } from './../dominio/dto/WfReemplazo';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { FiltroReemplazo, WfReemplazoServicio } from '../servicio/WfReemplazoServicio';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
    templateUrl: './reemplazo-listado.component.html'
})
export class WfReemplazoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;

    filtro: FiltroReemplazo = new FiltroReemplazo();
    paginacion: DominioPaginacion = new DominioPaginacion();
    lstTipos: SelectItem[] = [];
    lstEstados: SelectItem[] = [];

    empleadoBuscadorA: DtoTabla = new DtoTabla();
    sugerenciasEmpleadoA: any[];

    empleadoBuscadorB: DtoTabla = new DtoTabla();
    sugerenciasEmpleadoB: any[];

    empleadoBuscadorC: DtoTabla = new DtoTabla();
    sugerenciasEmpleadoC: any[];

    empleadoBuscadorD: DtoTabla = new DtoTabla();
    sugerenciasEmpleadoD: any[];

    filtroSugerenciaEmpleadoA(event) {
        let busqueda = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(busqueda).then(empleados => {
            this.sugerenciasEmpleadoA = empleados
        });
    }
    filtroSugerenciaEmpleadoB(event) {
        let busqueda = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(busqueda).then(empleados => {
            this.sugerenciasEmpleadoB = empleados
        });
    }
    filtroSugerenciaEmpleadoC(event) {
        let busqueda = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(busqueda).then(empleados => {
            this.sugerenciasEmpleadoC = empleados
        });
    }
    filtroSugerenciaEmpleadoD(event) {
        let busqueda = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(busqueda).then(empleados => {
            this.sugerenciasEmpleadoD = empleados
        });
    }

    constructor(
        private confirmationService: ConfirmationService,
        private wfTransaccionServicio: WfTransaccionServicio,
        private route: ActivatedRoute,
        private wfReemplazoServicio: WfReemplazoServicio,
        private cdref: ChangeDetectorRef,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        super.ngOnInit();
        this.lstTipos.push({ label: "Temporal", value: "T" });
        this.lstTipos.push({ label: "Definitivo", value: "D" });

        this.lstEstados.push({ label: " -- Todos -- ", value: null });
        this.lstEstados.push({ label: "Activo", value: "A" });
        this.lstEstados.push({ label: "Inactivo", value: "I" });

        this.filtro = new FiltroReemplazo();
    }

    cargarPrestamos(event: LazyLoadEvent) {

        this.bloquearPagina();

        this.filtro.id = this.empleadoBuscadorC.id;
        this.filtro.reemplazo = this.empleadoBuscadorD.id;

        this.paginacion.paginacionListaResultado = [];
        this.paginacion.paginacionRegistroInicio = event.first;
        this.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.wfReemplazoServicio.listarPaginado(this.filtro, this.paginacion)
            .then(res => {
                this.paginacion = res;
                this.desbloquearPagina();
            });
    }

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.empleadoBuscadorA = new DtoTabla();
        this.empleadoBuscadorB = new DtoTabla();

        this.sugerenciasEmpleadoA = [];
        this.sugerenciasEmpleadoB = [];

        this.reemplazo = new WfReemplazo();
        this.reemplazo.tipo = "T";
        this.reemplazo.desde = new Date();
        this.reemplazo.hasta = new Date();
        this.reemplazo.estado = "A";
        this.verModal = true;

    }

    reemplazo: WfReemplazo = new WfReemplazo();
    verModal: boolean = false;

    editar(dto: any) {

        this.empleadoBuscadorA = new DtoTabla();
        this.empleadoBuscadorB = new DtoTabla();

        this.sugerenciasEmpleadoA = [];
        this.sugerenciasEmpleadoB = [];

        this.bloquearPagina();

        var pk = new WfReemplazoPk();
        pk.secuencia = dto.secuencia;
        this.wfReemplazoServicio.obtenerPorId(pk).then(
            x => {
                this.reemplazo = x;

                var antiguo = new DtoTabla();
                antiguo.nombre = this.reemplazo.antiguoNombre.trim();
                antiguo.id = this.reemplazo.empleadoAntiguo;

                var nuevo = new DtoTabla();
                nuevo.nombre = this.reemplazo.nuevoNombre.trim();
                nuevo.id = this.reemplazo.empleadoNuevo;

                this.empleadoBuscadorB = nuevo;
                this.empleadoBuscadorA = antiguo;

                this.verModal = true;
                this.desbloquearPagina();
            }
        )
    }


    buscar(): void {
        this.dt.reset();
    }

    inicializarDatos(): void {

    }

    salir() {
        this.verModal = false;
    }

    guardar() {

        this.reemplazo.empleadoAntiguo = this.empleadoBuscadorA.id;
        this.reemplazo.empleadoNuevo = this.empleadoBuscadorB.id;


        if (this.reemplazo.empleadoAntiguo == null) {
            this.mostrarMensajeAdvertencia("Seleccione el empleado");
            return;
        }
        if (this.reemplazo.empleadoNuevo == null) {
            this.mostrarMensajeAdvertencia("Seleccione el reemplazo");
            return;
        }

        if (this.reemplazo.tipo == null || this.reemplazo.tipo == undefined) {
            this.mostrarMensajeAdvertencia("Seleccione el tipo");
            return;
        }

        if (this.reemplazo.tipo == "T") {
            if (this.reemplazo.desde == null) {
                this.mostrarMensajeAdvertencia("Seleccione la fecha de inicio");
                return;
            }
            if (this.reemplazo.hasta == null) {
                this.mostrarMensajeAdvertencia("Seleccione la fecha de fin");
                return;
            }
            if (this.reemplazo.hasta < this.reemplazo.desde) {
                this.mostrarMensajeAdvertencia("La fecha hasta debe ser mayor o igual a la fecha desde");
                return;
            }
        }
        else {
            this.reemplazo.desde = null;
            this.reemplazo.hasta = null;
        }

        this.bloquearPagina();

        if (this.accion == this.ACCIONES.NUEVO) {
            this.wfReemplazoServicio.registrar(this.reemplazo).then(
                x => {
                    this.verModal = false;
                    this.buscar();
                }
            );
        }
        else {
            this.wfReemplazoServicio.actualizar(this.reemplazo).then(
                x => {
                    this.verModal = false;
                    this.buscar();
                }
            );
        }



    }


    coreNuevo() { this.nuevo() }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) {
        this.buscar();
    }
    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: this.getMensajePreguntaAnular(),
            accept: () => {
                this.bloquearPagina();
                this.wfReemplazoServicio.anular(dto).then((res) => {
                    this.coreBuscar(null);
                });
            },
            key: "confirm",
        });
    }
    coreEliminar(dto: any) { }

    coreEditar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.editar(dto);
    }
    coreVer(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.editar(dto);
    }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }


}
