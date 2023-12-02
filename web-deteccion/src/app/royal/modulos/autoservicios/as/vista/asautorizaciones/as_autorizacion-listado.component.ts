
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { AsAutorizacionService } from '../../servicio/as_autorizacion.servicio';
import { DtoSolicitudPermisoListado } from '../../dominio/dto/DtoSolicitudPermisoListado';
import { DtoAsAutorizacion } from '../../dominio/dto/DtoAsAutorizacion';
import { FiltroPaginacionEmpleado } from '../../dominio/filtros/filtropaginacionempleado';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ConstanteAutoservicios } from '../../../ConstanteAutoservicios';

@Component({
    templateUrl: 'as_autorizacion-listado.component.html'
})

export class AsAutorizacionListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
    constructor(
        private asAutorizacionService: AsAutorizacionService,
        private cdref: ChangeDetectorRef,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,

        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    listadoAsAutorizaciones: DtoSolicitudPermisoListado[] = [];
    conceptos: SelectItem[] = [];
    estados: SelectItem[] = [];
    permisosSeleccionados: DtoAsAutorizacion[] = [];
    seleccionados: DtoSolicitudPermisoListado[] = [];
    filtro: FiltroPaginacionEmpleado = new FiltroPaginacionEmpleado();
    verMantenimiento = false;
    puedeAutorizar = false;
    rechazoActualSumado: number;
    motivoRechazoIndividual: string;
    nombrePersonaRechazado: string;

    @ViewChild(Table, { static: false }) dt: Table;

    ngOnInit() {
        this.formularioIniciar(this.route);

        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        const p1 = this.inicializarConceptos();
        const p2 = this.inicializarEstados();

        Promise.all([p1, p2]).then(
            f => {
                const seguridadIniciar = sessionStorage.getItem('seguridadIniciar');
                if (seguridadIniciar !== undefined && seguridadIniciar != null) {
                    this.filtro = JSON.parse(seguridadIniciar) as FiltroPaginacionEmpleado;
                    this.filtro = this.formularioFiltrosRestaurar(this.filtro);
                    this.formularioOninit = false;
                    this.cargarEvent();
                }
            });
    }

    cargarEvent() {
        if (this.formularioOninit == true) {
            this.formularioOninit = false;
            return;
        }
        this.bloquearPagina();

        sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));

        this.asAutorizacionService.solicitudListado(this.filtro).then((res) => {
            this.listadoAsAutorizaciones = res;
            this.desbloquearPagina();
        });
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    inicializarConceptos() {
        this.bloquearPagina();
        console.log('inicializarConceptos');

        this.conceptos.push({ label: ' Todos ', value: '' });
        this.filtro.conceptoacceso = null;
        this.asAutorizacionService.obtenerConceptos('N').then(res => {
            res.forEach(obj => this.conceptos.push({ label: obj.descripcionlocal, value: obj.conceptoacceso }));
        });
        this.asAutorizacionService.puedeAutorizar().then(
            aut => {
                this.puedeAutorizar = this.flagABoolean(aut.codigo);
            }
        );
        const date = new Date();
        const y = date.getFullYear();
        const m = date.getMonth();
        this.filtro.fechaDesde = new Date(y, m, 1);
        this.filtro.fechaHasta = new Date(y, m + 1, 0);
    }

    inicializarEstados() {
        this.estados.push({ label: ' Todos ', value: '' });
        this.estados.push({ label: ' Solicitado ', value: 'S' });
        this.estados.push({ label: ' Aprobado ', value: 'A' });
        this.estados.push({ label: ' Rechazado ', value: 'R' });
    }

    buscar() {
        this.cargarEvent();
    }


    aprobar(dt: any) {
        console.log('aprobar');
        this.bloquearPagina();

        if (this.permisosSeleccionados === null) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe Seleccionar un registro' });

            this.desbloquearPagina();
            return;
        }

        if (this.permisosSeleccionados.length === 0) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe Seleccionar un registro' });
            this.desbloquearPagina();
            return;
        }

        for (const permiso of this.permisosSeleccionados) {
            permiso.isseleccionaprobado = true;
            permiso.isseleccionrechazado = false;
        }

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Esta seguro de procesar la seleccion?',
            accept: () => {
                this.aprobarSolicitudes(dt);
                this.desbloquearPagina();
            }
        });
    }


    aprobarSolicitudes(dt: any) {
        this.asAutorizacionService.procesoAprobacion(this.permisosSeleccionados, this.filtro.isAdmin).then(
            resp => {
                if (this.esListaVacia(resp)) {
                    this.mostrarMensajeExito('Se proceso correctamente');
                    this.permisosSeleccionados = [];
                    this.buscar();
                } else {
                    if (resp.length > 0) {
                        for (let i = 0, len = resp.length; i < len; i++) {

                            if (resp[i].tipoMensaje.toString() === 'ADVERTENCIA') {
                                this.messageService.add({
                                    severity: 'info', summary: 'info',
                                    detail: resp[i].mensaje
                                });
                            } else {
                                this.messageService.add({
                                    severity: 'error', summary: 'Error',
                                    detail: resp[i].mensaje
                                });
                            }
                        }
                    }
                    this.desbloquearPagina();
                }

            }
        );
    }


    rechazar() {
        console.log('rechazar');
        this.bloquearPagina();

        if (this.permisosSeleccionados === null) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe Seleccionar un registro' });

            this.desbloquearPagina();
            return;
        }

        if (this.permisosSeleccionados.length === 0) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Debe Seleccionar un registro' });
            this.desbloquearPagina();
            return;
        }

        for (const permiso of this.permisosSeleccionados) {
            permiso.isseleccionaprobado = false;
            permiso.isseleccionrechazado = true;
        }

        this.asAutorizacionService.validarAprobacion(this.permisosSeleccionados).then(
            resp => {
                if (this.esListaVacia(resp)) {
                    this.confirmationService.confirm({
                        header: 'Confirmación',
                        icon: 'fa fa-question-circle',
                        message: '¿Esta seguro de procesar la seleccion?',
                        accept: () => {
                            this.rechazoActualSumado = 0;
                            this.verMantenimiento = true;
                            this.nombrePersonaRechazado = this.permisosSeleccionados[0].nombrecompleto;
                            this.desbloquearPagina();
                        },
                        reject: () => {
                            this.desbloquearPagina();
                        }
                    });
                }
            }
        );
    }


    aceptarMotivoRechazo(rechazoActual: number, dt: any) {
        console.log('aceptarMotivoRechazo');
        if (this.estaVacio(this.motivoRechazoIndividual)) {
            this.mostrarMensajeError('Debe de Ingresar el Motivo del Rechazo');
            return;
        }

        this.verMantenimiento = false;

        if (rechazoActual === this.permisosSeleccionados.length - 1) {
            this.permisosSeleccionados[rechazoActual].motivorechazo = this.motivoRechazoIndividual;
            this.aprobarSolicitudes(dt);
        } else {
            this.permisosSeleccionados[rechazoActual].motivorechazo = this.motivoRechazoIndividual;
            this.rechazoActualSumado++;
            this.verMantenimiento = true;
        }
        this.motivoRechazoIndividual = '';
    }


    reiniciarSeleccionados() {
        this.seleccionados = [];
    }

    nuevo() {
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_mantenimiento], { skipLocationChange: true });
    }

    editar(bean: DtoAsAutorizacion): void {
        this.bloquearPagina();
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_mantenimiento,
            this.ACCIONES.EDITAR, JSON.stringify(bean)], { skipLocationChange: true });
    }

    validar(accion, pk: number): Promise<boolean> {
        return this.asAutorizacionService.solicitudValidarAccion(accion, pk).then(
            response => {
                if (response != null) {
                    if (response.length === 0) {
                        return true;
                    }
                    this.messageService.addAll(this.getMensajesUsuario(response));
                    this.desbloquearPagina();
                    return false;
                } else {
                    return false;
                }
            }
        );
    }

    eliminar(bean: DtoAsAutorizacion): void {
        if (bean.estado != 'S') {
            this.mostrarMensajeAdvertencia('Solo puede anular permisos en estado Solicitado');
            return;
        }
        this.bloquearPagina();
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_mantenimiento, this.ACCIONES.ELIMINAR,
            JSON.stringify(bean)], { skipLocationChange: true });
    }

    ver(bean: DtoAsAutorizacion) {
        this.router.navigate([ConstanteAutoservicios.ruta_solicitud_permiso_mantenimiento, this.ACCIONES.VER, JSON.stringify(bean)], { skipLocationChange: true });
    }

    mostrarSelectorEmpleadomast() {
        //  this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }


    limpiarEmpleado() {
        // this.empleadoNombreCompleto = '';
        this.filtro.empleadoid = null;
    }


    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.buscarSinPaginacion();
        }
    }

    buscarSinPaginacion() {

    }

    coreNuevo() { this.nuevo() }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) {
        this.buscar();
    }
    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar(dto: any) { }
    coreEditar(dto: any) { }
    coreVer(dto: any) { }

    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) {
        if (mensage.componente == 'EMPLEADO') {
            //  this.empleadoNombreCompleto = mensage.resultado.busqueda;
            this.filtro.empleadoid = mensage.resultado.persona;
        }
    }

    coreAccion(accion: string) { }
}