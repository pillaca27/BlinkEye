import { DominioPaginacion } from './../../../util/DominioPaginacion';
import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { FiltroComunPersonamast } from '../dominio/FiltroComunPersonamast';
import { CapacitacionService } from '../servicio/capacitacion.service';

@Component({
    selector: 'app-cursocomun-selector',
    templateUrl: './cursocomunselector.component.html'
})
export class CursoComunSelectorComponent extends BaseComponent implements OnInit {

    verSelector = false;
    lstEstados: SelectItem[] = [];
    filtro: FiltroComunPersonamast = new FiltroComunPersonamast();
    paginacion: DominioPaginacion = new DominioPaginacion();
    titulo: string;
    @Output() cargarSeleccionEvent = new EventEmitter();

    constructor(
        private capacitacionesService: CapacitacionService,
        private cdref: ChangeDetectorRef,
        messageService: MessageService,
    ) {
        super(messageService);
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        this.verSelector = false;
        this.titulo = 'CURSOS';
        this.filtro.estado = 'A';
    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar(dt);
        }
    }

    cargarEvent(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.bloquearPagina();
        this.capacitacionesService.listarCursos(this.filtro).then(
            res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
                this.verSelector = true;
            }
        );
    }

    coreBusquedaRapida(filtro: string) {
    }

    coreBuscar(dt: Table) {
        dt.reset();
    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.desbloquearPagina();
        this.verSelector = false;
    };

    coreSeleccionar(event: any) {
        console.log(event)
        const reg: any = new Object();
        reg.data = event;
        this.cargarSeleccionEvent.emit(reg);
        this.coreSalir();
    }

    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }
    coreIniciarComponente() {
        this.verSelector = true;
        this.filtro = new FiltroComunPersonamast();
        this.filtro.estado = 'A';
    }

}
