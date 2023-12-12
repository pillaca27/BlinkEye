import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { MicrosuenosService } from '../../servicio/microsuenos.servicio';
import { FiltroMicrosuenos } from '../../dominio/filtro/FiltroMicrosuenos';
import { Table } from 'primeng/table';
import { ClasesService } from '../../servicio/clases.servicio';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DocentesService } from '../../servicio/docentes.servicio';

@Component({
    templateUrl: 'consulta-clase.component.html'
})

export class ConsultaClaseComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dt: Table;

    constructor(
        private clasesService: ClasesService,
        private docentesServicee: DocentesService,
        private microsuenosService: MicrosuenosService,
        private route: ActivatedRoute,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); 
        this.nombreParametroSession = "consultaclase";
        this.filtro = this.formularioFiltrosRestaurarNombre(this.filtro, this.nombreParametroSession);
        this.formularioOninit = false;
    }

    filtro: FiltroMicrosuenos = new FiltroMicrosuenos;
    lstClases: SelectItem[] = [];
    lstDocentes: SelectItem[] = [];

    ngOnInit() {
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.bloquearPagina();
        const p1 = this.inicializarClases();
        const p2 = this.inicializarDocentes();

        Promise.all([p1, p2]).then(f => {
                this.desbloquearPagina();
        });
    }

    inicializarClases()
    {
        this.lstClases.push({ label: '--- Todos ---', value: null });
        this.clasesService.listarActivos().then( td => {
            if(!this.esListaVacia(td))
            {
                td.forEach(element => {
                    this.lstClases.push({ label: element.nombre, value: element.id });
                });
            }
        })
        return 1;
    }

    inicializarDocentes()
    {
        this.lstDocentes.push({ label: '--- Todos ---', value: null });
        this.docentesServicee.listarActivos().then( td => {
            if(!this.esListaVacia(td))
            {
                td.forEach(element => {
                    this.lstDocentes.push({ label: element.nombre, value: element.id });
                });
            }
        })
        return 1;
    }

    cargarListado(event: LazyLoadEvent) {

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

        this.microsuenosService.listarPorClasepaginado(this.filtro)
            .then(res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            });
    }

    coreNuevo() { }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) {
        this.dt.reset();
    }
    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar(dto: any) { }
    coreEditar(dto: any) { }
    coreVer(dto: any) { }

    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) {
    }

    coreAccion(accion: string) { }

}