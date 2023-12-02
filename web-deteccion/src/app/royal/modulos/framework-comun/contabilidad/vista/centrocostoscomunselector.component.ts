import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from './../../servicioComun.service';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { AcCostcenterComunService } from '../servicio/accostcenter-comun.service';
import { FiltroComunAcCostcentermst } from '../dominio/filtro/FiltroComunAcCostcentermst';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef } from '@angular/core';


@Component({
    selector: 'app-centrocostos-selector',
    templateUrl: './centrocostoscomunselector.component.html'
})
export class CentroCostosComunSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    lstEstados: SelectItem[] = [];
    filtro: FiltroComunAcCostcentermst = new FiltroComunAcCostcentermst();
    registrosPorPagina: number = 20;
    lstJefaturas0: any[] = [];
    lstJefaturas1: any[] = [];
    lstJefaturas2: any[] = [];
    lstJefaturasTodos: any[] = [];
    lstSeleccionada: any[] = [];
    registroSeleccionado: any;
    regPorPag0: number;
    regPorPag1: number;
    regPorPag2: number;
    regPorPagTodos: number;
    loading: boolean;
    index: number = 0;
    tipo: string = 'N';
    selectorMultiple: boolean = false;
    general: boolean = false;
    esAdministradorCC: boolean = false;

    titulo: string = 'ÁREA USUARIA';
    columncodigo:string = 'Área Usuaria'
    constructor(
        private centroCostosService: AcCostcenterComunService,
        private cdref: ChangeDetectorRef,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {


        this.tieneAcceso('WH', 'SYSTEM', 'CCSUP').then(
            permiso => {
                this.esAdministradorCC = permiso;
            }
        );

    }

    cargarEstados() {
        this.lstEstados = [];
        this.lstEstados.push({ label: ConstanteAngular.COMBOTODOS, value: '' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.filtro.status = 'A';
    }


    iniciarComponenteVarios(tipo: string) {
        console.log('Varios');
        console.log(tipo);
        this.tipo = tipo;
        this.verSelector = true;
        this.filtro = new FiltroComunAcCostcentermst();
        this.lstJefaturas0 = [];
        this.buscar();
    }


    buscar() {

        if (!this.verSelector) {
            return;
        }
        this.lstJefaturas0 = [];
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        if (this.index == 0 && !this.general) {
            this.bloquearPagina();
            this.centroCostosService.listarautorizados(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.expenseadministrativeflag == "N") {
                            element.expenseadministrativeflag = false;
                        } else { element.expenseadministrativeflag = true; }
                        if (element.expensefinanceflag == "N") {
                            element.expensefinanceflag = false;
                        } else { element.expensefinanceflag = true; }
                        if (element.expenseproductionflag == "N") {
                            element.expenseproductionflag = false;
                        } else { element.expenseproductionflag = true; }
                        if (element.expensesalesflag == "N") {
                            element.expensesalesflag = false;
                        } else { element.expensesalesflag = true; }
                    });
                    this.lstJefaturas0 = res.paginacionListaResultado;
                    console.log(this.lstJefaturas0);
                    this.regPorPag0 = res.paginacionRegistrosEncontrados;
                    this.desbloquearPagina();
                    this.verSelector = true;
                    this.loading = false;
                }
            );
        } else if (this.index == 1 && !this.general) {
            this.bloquearPagina();
            this.filtro.idAplicacion = 'WH';
            this.centroCostosService.listarsupervisados(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.expenseadministrativeflag == "N") {
                            element.expenseadministrativeflag = false;
                        } else { element.expenseadministrativeflag = true; }
                        if (element.expensefinanceflag == "N") {
                            element.expensefinanceflag = false;
                        } else { element.expensefinanceflag = true; }
                        if (element.expenseproductionflag == "N") {
                            element.expenseproductionflag = false;
                        } else { element.expenseproductionflag = true; }
                        if (element.expensesalesflag == "N") {
                            element.expensesalesflag = false;
                        } else { element.expensesalesflag = true; }
                    });
                    this.lstJefaturas1 = res.paginacionListaResultado;
                    console.log(this.lstJefaturas1);
                    this.regPorPag1 = res.paginacionRegistrosEncontrados;
                    this.desbloquearPagina();
                    this.verSelector = true;
                    this.loading = false;
                    this.filtro.idAplicacion = null;
                }
            );
        }
        else if (this.general) {
            this.bloquearPagina();
            this.centroCostosService.listarTodos(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.expenseadministrativeflag == "N") {
                            element.expenseadministrativeflag = false;
                        } else { element.expenseadministrativeflag = true; }
                        if (element.expensefinanceflag == "N") {
                            element.expensefinanceflag = false;
                        } else { element.expensefinanceflag = true; }
                        if (element.expenseproductionflag == "N") {
                            element.expenseproductionflag = false;
                        } else { element.expenseproductionflag = true; }
                        if (element.expensesalesflag == "N") {
                            element.expensesalesflag = false;
                        } else { element.expensesalesflag = true; }
                    });
                    this.lstJefaturasTodos = res.paginacionListaResultado;
                    this.cantlstJefaturasTodos = this.lstJefaturasTodos.length;
                    console.log(this.lstJefaturas2);
                    this.regPorPagTodos = 20;
                    this.desbloquearPagina();
                    this.verSelector = true;
                    this.loading = false;
                }
            );

        }
    }

    cantlstJefaturasTodos: number;

    validaRepetido(d: any): boolean {
        let valida = true;
        this.lstSeleccionada.forEach(trg => {
            if (trg.area === d.area) {
                valida = false;
            }
        });
        return valida;
    }

    // CENTRO DE COSTOS
    onTabChange(event) {
        // TAB event.index 0 = Autorizados
        // TAB event.index 1 = Supervisados
        // TAB event.index 2 = Empleados

        console.log(event.index);
        this.index = event.index;

        if (this.lstJefaturas0.length == 0 && this.index == 0) {
            this.buscar();
        } else if (this.lstJefaturas1.length == 0 && this.index == 1) {
            this.buscar();
        } else if (this.lstJefaturas2.length == 0 && this.index == 2) {
            this.buscar();
        }
    }



    loadCarsLazy0(event: LazyLoadEvent) {
        this.loading = true;
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.buscar();
    }

    loadCarsLazy1(event: LazyLoadEvent) {
        this.loading = true;
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.buscar();
    }

    loadCarsLazy2(event: LazyLoadEvent) {
        this.loading = true;
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.buscar();
    }



    coreBusquedaRapida(filtro: string) { };
    coreBuscar(dt: Table) { }
    coreFiltro(flag: boolean) { };
    coreSalir() {
        this.verSelector = false;
        this.mensajeController.componenteDestino.desbloquearPagina();
    };
    coreSeleccionar(dto: any) {
        this.registroSeleccionado = dto;
        console.log('aceptar');
        if (this.tipo == 'D') {
            this.mensajeController.resultado = this.registroSeleccionado;
            this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        } else if (this.tipo == 'R') {
            this.mensajeController.resultado = this.registroSeleccionado;
            this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        } else {
            this.mensajeController.resultado = this.registroSeleccionado;
            this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        }
        this.coreSalir();
    }
    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroComunAcCostcentermst();
        this.lstJefaturas0 = [];
        this.general = true;
        this.selectorMultiple = false;
        this.titulo = 'CENTRO DE COSTOS';
        this.verSelector = true;
        this.buscar();
    }

    coreIniciarComponenteMulti(msj: MensajeController, multiple?: boolean) {
        this.mensajeController = msj;
        this.verSelector = true;
        this.filtro = new FiltroComunAcCostcentermst();
        this.lstJefaturas0 = [];
        if (multiple != null && multiple != undefined) {
            this.selectorMultiple = multiple;
        } else {
            this.selectorMultiple = false;
        }
        this.buscar();
    }


    iniciarComponentePrincipales(msj: MensajeController, multiple?: boolean) {
        this.mensajeController = msj;
        this.verSelector = true;
        this.filtro = new FiltroComunAcCostcentermst();
        this.filtro.principales = 'S';
        this.lstJefaturas0 = [];

        if (multiple != null && multiple != undefined) {
            this.selectorMultiple = multiple;
        } else {
            this.selectorMultiple = false;
        }

        this.buscar();
    }

    iniciarComponentePrincipalesGenerados(msj: MensajeController, multiple?: boolean) {
        this.mensajeController = msj;
        this.verSelector = true;
        this.filtro = new FiltroComunAcCostcentermst();
        this.filtro.principales = 'G';
        this.lstJefaturas0 = [];

        if (multiple != null && multiple != undefined) {
            this.selectorMultiple = multiple;
        } else {
            this.selectorMultiple = false;
        }

        this.buscar();
    }

    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.buscar();
        }
    }
    // FIN CENTRO DE COSTOS


    coreSeleccionarMultiple() {
        if (this.esListaVacia(this.registroSeleccionado)) {
            this.mostrarMensajeInfo('Debe seleccionar un registro');
            return;
        }
        this.mensajeController.resultado = this.registroSeleccionado;
        this.mensajeController.parametros = 'MULTIPLE';
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }


}
