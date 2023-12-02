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
import { HrEncuestaServicio } from '../servicio/HrEncuestaServicio';
import { DtoEncuestaClimaLaboralListado } from '../dominio/dto/DtoEncuestaClimaLaboralListado';
import { DtoEncuestaEjecucion } from '../dominio/dto/DtoEncuestaEjecucion';

@Component({
    templateUrl: './encuestaclimalaboral-listado.component.html'
})
export class EncuestaClimaLaboralListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;
    lst: DtoEncuestaClimaLaboralListado[] = [];
    dtoEncuesta: DtoEncuestaEjecucion = new DtoEncuestaEjecucion();
    modalEjecucion: boolean = false;

    constructor(
        private hrEncuestaServicio: HrEncuestaServicio,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.listarEncuestasPendientes();
    }

    listarEncuestasPendientes() {
        this.bloquearPagina();
        this.hrEncuestaServicio.listarEncuestasPendientes()
            .then(res => {
                this.lst = res;
                this.desbloquearPagina();
            });
    }

    ejecutar(row: DtoEncuestaClimaLaboralListado) {
        this.confirmationService.confirm({
            header: "Confirmación",
            icon: "fa fa-question-circle",
            message: "¿Esta seguro de ejecutar la encuesta? ",
            accept: () => {
                this.bloquearPagina();
                this.hrEncuestaServicio.obtenerEncuesta(row).then((res) => {
                    this.dtoEncuesta = res;
                    this.modalEjecucion = true;
                    this.desbloquearPagina();
                });
            },
            key: "confirm",
        });
    }

    guardar() {
        this.bloquearPagina();
        if (!this.validar()) {
            this.desbloquearPagina();
            return;
        }
        this.hrEncuestaServicio.registrarMuestra(this.dtoEncuesta).then(res => {
            if (this.transaccionResultado(res)) {
                this.modalEjecucion = false;
                this.listarEncuestasPendientes();
            }
            else {
                this.desbloquearPagina();
            }
        });
    }

    validar(): boolean {
        let valida = true;
        this.messageService.clear();
        this.dtoEncuesta.detalle.forEach(x => {
            if (x.tipo == 'P') {
                if (x.valor == null || x.valor === undefined) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccione: ' + x.descripcion });
                    valida = false;
                }
            }
            if (x.tipo == 'C') {
                if (this.estaVacio(x.observacion)) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Responda: ' + x.descripcion });
                    valida = false;
                }
            }
            if (x.tipo == 'A') {
                if (this.estaVacio(x.observacion) && (x.valor == null || x.valor === undefined)) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Seleccione y Responda: ' + x.descripcion });
                    valida = false;
                }
            }
        });
        return valida;
    }

    coreBuscar(tabla: LazyLoadEvent) {
        throw new Error('Method not implemented.');
    }
    coreNuevo() {
        throw new Error('Method not implemented.');
    }
    coreEditar(dto: any) {
        throw new Error('Method not implemented.');
    }
    coreVer(dto: any) {
        throw new Error('Method not implemented.');
    }
    coreAnular(dto: any) {
        throw new Error('Method not implemented.');
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
        throw new Error('Method not implemented.');
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }


}
