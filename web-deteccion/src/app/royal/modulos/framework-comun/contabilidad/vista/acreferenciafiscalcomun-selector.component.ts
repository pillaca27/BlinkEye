import { DtoComunAcReferenciafiscalSeleccion } from './../dominio/dto/DtoComunAcReferenciafiscalSeleccion';
import { DtoComunAcReferenciafiscal } from './../dominio/dto/DtoComunAcReferenciafiscal';
import { FiltroComunAcReferenciafiscalSelector } from './../dominio/filtro/FiltroComunAcReferenciafiscalSelector';
import { FiltroComunAfemst } from './../../core/dominio/filtro/FiltroComunAfemst';

import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { AcReferenciafiscalComunService } from '../servicio/acreferenciafiscal-comun.service';

@Component({
    selector: 'app-selector-acreferenciafiscal',
    templateUrl: './acreferenciafiscalcomun-selector.component.html'
})
export class AcreferenciafiscalSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunAfemst = new FiltroComunAfemst();
    filtroreferencia: FiltroComunAcReferenciafiscalSelector = new FiltroComunAcReferenciafiscalSelector();
    registrosPorPagina: number = 5;
    tag: string;
    lstEstado: SelectItem[] = [];
    lstJefaturas0: any[] = [];
    loading: boolean;
    index: number = 0;
    tipo: string;
    titulo: string;

    lstActivos: DtoComunAcReferenciafiscal[] = [];
    lstActivosCantidad: number = 0;

    lstFFinanciamiento: DtoComunAcReferenciafiscal[] = [];
    lstFFinanciamientoCantidad: number = 0;

    lstGastos: DtoComunAcReferenciafiscal[] = [];
    lstGatosCantidad: number = 0;

    registroSeleccionado: any;
    proyecto: string = null;
    tipopresupuesto: string = 'GA';

    actividad: string;
    financiamiento: string;
    gasto: string;
    versoloActividad: boolean;

    dtoSeleccion: DtoComunAcReferenciafiscalSeleccion = new DtoComunAcReferenciafiscalSeleccion();

    ano: string = '';


    constructor(
        private acReferenciafiscalComunService: AcReferenciafiscalComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.listarTipos();
    }

    listarTipos(): Promise<number> {
        return;
    }

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar();
        }
    }


    loadCarsLazy0(event: LazyLoadEvent) {
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.coreBuscar();
    }


    coreBusquedaRapida(filtro: string) {
    };

    coreBuscar() {

        this.bloquearPagina();
        const p1 = this.coreBuscar1();
        const p2 = this.coreBuscar2();
        const p3 = this.coreBuscar3();

        Promise.all([p1, p2, p3]).then(
            f => {
                this.desbloquearPagina();
            });

    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.mensajeController.componenteDestino.desbloquearPagina();
        this.verSelector = false;
    };

    coreSeleccionar() {

        if (this.estaVacio(this.actividad)) {
            this.mostrarMensajeAdvertencia('Debe seleccionar una actividad.');
            return;
        }

        if (!this.versoloActividad) {
            if (this.estaVacio(this.financiamiento)) {
                this.mostrarMensajeAdvertencia('Debe seleccionar una fuente de financiamiento.');
                return;
            }
        }



        this.mensajeController.resultado = this.dtoSeleccion;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSeleccionarActividad(dto: DtoComunAcReferenciafiscal) {
        
        this.dtoSeleccion.actividad = dto;
        this.actividad = this.dtoSeleccion.actividad.referenciafiscal + ' - ' + this.dtoSeleccion.actividad.descripcionlocal;
  
        if (this.versoloActividad) {
            this.mensajeController.resultado = this.dtoSeleccion;
            this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
            this.coreSalir();
        }
  
    }

    coreSeleccionarFinanciamiento(dto: DtoComunAcReferenciafiscal) {
        this.dtoSeleccion.financiamiento = dto;
        this.financiamiento = this.dtoSeleccion.financiamiento.referenciafiscal + ' - ' + this.dtoSeleccion.financiamiento.descripcionlocal;
    }

    coreSeleccionarGasto(dto: DtoComunAcReferenciafiscal) {
        this.dtoSeleccion.gasto = dto;
        this.gasto = this.dtoSeleccion.gasto.referenciafiscal + ' - ' + this.dtoSeleccion.gasto.descripcionlocal;
    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }

    ingresarVariables(ano: string, dtoExterno: any, tipopresupuesto: string, versoloActividad: boolean) {
        this.ano = ano;
        this.actividad = '';
        this.financiamiento = '';
        this.gasto = '';
        this.versoloActividad = versoloActividad;

        if (!this.versoloActividad) {
            this.proyecto = dtoExterno.afe;
            
            if (!this.estaVacio(dtoExterno.referenciafiscal01) && !this.estaVacio(dtoExterno.auxiliarreferenciafiscal01nombre)) {
                this.actividad = dtoExterno.referenciafiscal01 + '-' + dtoExterno.auxiliarreferenciafiscal01nombre;
                let dtoSeleccionActividad: DtoComunAcReferenciafiscal = new DtoComunAcReferenciafiscal();
                dtoSeleccionActividad.referenciafiscal = dtoExterno.referenciafiscal01;
                dtoSeleccionActividad.descripcionlocal = dtoExterno.auxiliarreferenciafiscal01nombre;

                this.dtoSeleccion.actividad = dtoSeleccionActividad;
            }
            if (!this.estaVacio(dtoExterno.referenciafiscal02) && !this.estaVacio(dtoExterno.auxiliarreferenciafiscal02nombre)) {
                this.financiamiento = dtoExterno.referenciafiscal02 + '-' + dtoExterno.auxiliarreferenciafiscal02nombre;
                let dtoSeleccionFinanciamiento: DtoComunAcReferenciafiscal = new DtoComunAcReferenciafiscal();
                dtoSeleccionFinanciamiento.referenciafiscal = dtoExterno.referenciafiscal02;
                dtoSeleccionFinanciamiento.descripcionlocal = dtoExterno.auxiliarreferenciafiscal02nombre;

                this.dtoSeleccion.financiamiento = dtoSeleccionFinanciamiento;
            }
            if (!this.estaVacio(dtoExterno.referenciafiscal03) && !this.estaVacio(dtoExterno.auxiliarreferenciafiscal03nombre)) {
                this.gasto = dtoExterno.referenciafiscal03 + '-' + dtoExterno.auxiliarreferenciafiscal03nombre;
                let dtoSeleccionGasto: DtoComunAcReferenciafiscal = new DtoComunAcReferenciafiscal();
                dtoSeleccionGasto.referenciafiscal = dtoExterno.referenciafiscal03;
                dtoSeleccionGasto.descripcionlocal = dtoExterno.auxiliarreferenciafiscal03nombre;

                this.dtoSeleccion.gasto = dtoSeleccionGasto;
            }

        }

        this.tipopresupuesto = tipopresupuesto;
        if (this.estaVacio(this.tipopresupuesto)) {
            this.tipopresupuesto = 'GA';
        } else {
            if (this.tipopresupuesto.substring(0, 4) == '9999') {
                this.tipopresupuesto = 'GA';
            }
        }
    }

    coreIniciarComponente(msj: MensajeController) {
        this.lstActivos = [];
        this.lstActivosCantidad = 0;

        this.lstFFinanciamiento = [];
        this.lstFFinanciamientoCantidad = 0;

        this.lstGastos = [];
        this.lstGatosCantidad = 0;

        this.mensajeController = msj;
        this.verSelector = true;
        this.coreBuscar();
    }



    coreBuscar1(): Promise<number> {
        this.filtroreferencia.ano = this.ano;
        this.filtroreferencia.nivel = '02';
        this.filtroreferencia.tiporeferenciafiscal = '08';
        return this.acReferenciafiscalComunService.listarDtoPorSeguridadAnioTipoNivel(this.filtroreferencia).then(resp => {
            console.log(resp);
            this.lstActivos = [];


            if (this.estaVacio(this.proyecto)) {
                this.tipopresupuesto = 'GA';
            } else {
                this.tipopresupuesto = 'IN';
            }

            resp.forEach(element => {
                if (element.tipogasto == this.tipopresupuesto) {
                    this.lstActivos.push(element);
                }
            });

            this.lstActivosCantidad = this.lstActivos.length;
            return 1;
        });
    }

    coreBuscar2(): Promise<number> {
        this.filtroreferencia.ano = this.ano;
        this.filtroreferencia.nivel = '02';
        this.filtroreferencia.tiporeferenciafiscal = '03';
        return this.acReferenciafiscalComunService.listarDtoPorAnioTipoNivel(this.filtroreferencia).then(resp => {
            console.log(resp);
            this.lstFFinanciamiento = [];
            this.lstFFinanciamiento = resp;
            this.lstFFinanciamientoCantidad = this.lstActivos.length;
            return 1;
        });
    }


    coreBuscar3(): Promise<number> {
        this.filtroreferencia.ano = this.ano;
        if (this.tipopresupuesto == 'VE') {
            this.filtroreferencia.nivel = '06';
            this.filtroreferencia.tiporeferenciafiscal = '04';
        } else {
            this.filtroreferencia.nivel = '06';
            this.filtroreferencia.tiporeferenciafiscal = '02';
        }

        return this.acReferenciafiscalComunService.listarDtoPorAnioTipoNivel(this.filtroreferencia).then(resp => {
            console.log(resp);
            this.lstGastos = [];
            this.lstGastos = resp;
            this.lstGatosCantidad = this.lstGastos.length;
            return 1;
        });
    }


}
