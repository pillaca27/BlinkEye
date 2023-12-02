import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { FiltroComunHrCentroestudios } from '../dominio/filtro/FiltroComunHrCentroestudios';
import { HrCentroestudiosComunService } from '../servicio/hrcentroestudios-comun.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { FiltroComunHrCentroestudiosCarrera } from '../dominio/filtro/FiltroComunHrCentroestudiosCarrera';
import { DtoComunHrCentroestudioCarrera } from '../dominio/dto/DtoComunHrCentroestudioCarrera';

@Component({
    selector: 'app-centro-estudios-selector-carrera',
    templateUrl: './hrcentroestudiosselector-carrera.component.html'
})
export class HrCentroEstudiosCarreraSelectorComponent extends BaseComponent implements OnInit {

    verSelector: Boolean = false;

    lstRegimen: SelectItem[] = [];
    lstTipo: SelectItem[] = [];

    centroSeleccionado: DtoComunHrCentroestudioCarrera;
    carreraSeleccionada: DtoComunHrCentroestudioCarrera;

    filtro: FiltroComunHrCentroestudiosCarrera = new FiltroComunHrCentroestudiosCarrera();
    registrosPorPagina: number = 12;
    carreras: DtoComunHrCentroestudioCarrera[] = [];
    verSeccionCarrera: boolean = true;

    constructor(private hrCentroestudiosService: HrCentroestudiosComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.lstRegimen = [];
        this.lstRegimen.push({ label: ' -- Todos -- ', value: null });
        this.lstRegimen.push({ label: 'Pública', value: '1' });
        this.lstRegimen.push({ label: 'Privada', value: '2' });
        this.lstTipo.push({ label: ' -- Todos -- ', value: null });
        this.servicioComun.listaractivos('999999', 'PR', 'TIPOINST')
            .then(respuesta => {
                respuesta.forEach(obj => this.lstTipo.push({ label: obj.codigo.trim() + ' - ' + obj.nombre, value: obj.codigo.trim() }));
            })
    }

    listarDefecto() {
        this.centroSeleccionado = null;
        this.carreraSeleccionada = null;
        this.carreras = [];
        this.filtro.paginacion.paginacionRegistroInicio = 1;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.hrCentroestudiosService.listarPaginacionConCarrera(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    cargarCentroEstudios(event: LazyLoadEvent) {
        this.centroSeleccionado = null;
        this.carreraSeleccionada = null;
        this.carreras = [];
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.hrCentroestudiosService.listarPaginacionConCarrera(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }



    coreBusquedaRapida(filtro: string) {
    };

    coreBuscar(dt: Table) {
        dt.reset();
    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.verSelector = false;
    };

    coreSeleccionar(dto: any) {

    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }

    coreIniciarComponente(msj: MensajeController, verSeccionCarrera: boolean) {
        this.verSeccionCarrera = verSeccionCarrera;
        this.mensajeController = msj;
        this.centroSeleccionado = null;
        this.carreraSeleccionada = null;
        this.carreras = [];
        this.filtro = new FiltroComunHrCentroestudiosCarrera();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.listarDefecto();
    }

    aceptar() {
        if (this.centroSeleccionado == null) {
            this.mostrarMensajeAdvertencia('Seleccione el centro de estudios');
            return;
        }
        this.mensajeController.resultado = { centro: this.centroSeleccionado, carrera: this.carreraSeleccionada };
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    onRowSelect() {
        this.bloquearPagina();
        this.carreraSeleccionada = null;
        this.hrCentroestudiosService.listarCarreraPorCentroEstudio(this.centroSeleccionado)
            .then(pg => {
                this.desbloquearPagina();
                this.carreras = pg;
            });
    }
}
