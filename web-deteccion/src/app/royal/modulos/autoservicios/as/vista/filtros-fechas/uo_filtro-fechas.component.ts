import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { FiltroPaginacionEmpleado } from '../../dominio/filtros/filtropaginacionempleado';
import { AsAccesosdiariosService } from '../../servicio/asaccesosdiarios.service';

@Component({
    selector: 'selector-filtrosfechas',
    templateUrl: 'uo_filtro-fechas.component.html'
})

export class FiltrosFechasComponent extends BaseComponent implements OnInit, UIListadoController {
    constructor(
        private asAccesosdiariosService: AsAccesosdiariosService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    coreNuevo(): void {
        throw new Error('Method not implemented.');
    }
    coreBusquedaRapida(filtro: string): void {
        throw new Error('Method not implemented.');
    }
    coreBuscar(tabla: LazyLoadEvent): void {
        throw new Error('Method not implemented.');
    }
    coreFiltro(flag: boolean): void {
        throw new Error('Method not implemented.');
    }
    coreAnular(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreEliminar(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreEditar(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreVer(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'EMPLEADO') {
            this.filtro.empleaadonombrecompleto = mensage.resultado.busqueda;
            this.filtro.empleadoid = mensage.resultado.persona;
        }
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }

    @ViewChild(PersonaComunSelectorComponent, { static: false })
    personaComunSelectorComponent: PersonaComunSelectorComponent;
    filtro: FiltroPaginacionEmpleado = new FiltroPaginacionEmpleado();

    @Output() parentFunction: EventEmitter<any> = new EventEmitter();

    listaCompanias: SelectItem[] = [];
    listaTipoPlanilla: SelectItem[] = [];
    listaCentroCostos: SelectItem[] = [];
    listaSucursales: SelectItem[] = [];
    listaUnidadNegocio: SelectItem[] = [];
    listaAreas: SelectItem[] = [];

    ngOnInit() {
        this.bloquearPagina();
        const seguridadIniciar = sessionStorage.getItem('seguridadIniciar');
        if (seguridadIniciar !== undefined && seguridadIniciar != null) {
            this.filtro = JSON.parse(seguridadIniciar) as FiltroPaginacionEmpleado;
            this.filtro = convertDateStringsToDates(this.filtro);
            this.llenarListas();
        }
    }

    llenarListas() {
        this.listaCentroCostos.push({ label: '-- Todos --', value: '' });
        this.listaAreas.push({ label: '-- Todos --', value: '' });
        this.listaTipoPlanilla.push({ label: '-- Todos --', value: '' });
        this.listaSucursales.push({ label: '-- Todos --', value: '' });
        this.listaUnidadNegocio.push({ label: '-- Todos --', value: '' });

        this.filtro.listaCompanias.forEach(obj =>
            this.listaCompanias.push({ label: obj.nombre, value: obj.codigo }));


        this.filtro.listaCentroCosto.forEach(obj =>
            this.listaCentroCostos.push({ label: obj.nombre, value: obj.codigo }));

        this.filtro.listaArea.forEach(obj =>
            this.listaAreas.push({ label: obj.nombre, value: obj.id }));

        this.filtro.listaTipoPlanilla.forEach(obj =>
            this.listaTipoPlanilla.push({ label: obj.nombre, value: obj.codigo }));

        this.filtro.listaSucursal.forEach(obj =>
            this.listaSucursales.push({ label: obj.nombre, value: obj.codigo }));

        this.filtro.listaUnidadNegocio.forEach(obj =>
            this.listaUnidadNegocio.push({ label: obj.nombre, value: obj.codigo }));
        this.desbloquearPagina();

        this.filtro.idcompaniasocio = null;
        this.filtro.idtipoplanilla = null;
        this.filtro.idcentrocosto = null;
        this.filtro.idsucursal = null;
        this.filtro.idunidadnegocioasignada = null;
        this.filtro.idarea = null;
    }

    onChangeArea (check) {
       if(check){
        this.filtro.idarea = null;
       }
    }

    onChangeUnidadNegocio(check) {
        if(check){
            this.filtro.idunidadnegocioasignada = null;
       }
    }

    onChangeSucursal(check) {
        if(check){
            this.filtro.idsucursal = null;
       }
    }

    onChangeCentroCosto(check) {
        if(check){
            this.filtro.idcentrocosto = null;
       }
    }

    onChangePlanilla(check) {
        if(check){
            this.filtro.idtipoplanilla = null;
       }
    }

    onChangeCompania(check) {
        if(check){
            this.filtro.idcompaniasocio = null;
       }
    }

    limpiarEmpleado() {
        this.filtro.empleaadonombrecompleto = '';
        this.filtro.empleadoid = null;
    }

    agregarEmpleadoModal() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'EMOT'));
    }

    buscar() {
        this.parentFunction.emit(this.filtro);
    }

}