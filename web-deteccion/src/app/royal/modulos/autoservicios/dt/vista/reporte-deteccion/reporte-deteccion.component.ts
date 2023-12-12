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
import { ClasesService } from '../../servicio/clases.servicio';

@Component({
    templateUrl: 'reporte-deteccion.component.html'
})

export class ReporteDeteccionComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild('pdfViewerReporteDeteccion', { static: false }) pdfViewerReporteDeteccion;

    constructor(
        private microsuenosService: MicrosuenosService,
        private clasesService: ClasesService,
        private route: ActivatedRoute,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    filtro: FiltroMicrosuenos = new FiltroMicrosuenos;
    lstClases: SelectItem[] = [];

    ngOnInit() {
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.formularioOninit = false;
        this.desbloquearPagina();

        this.bloquearPagina();
        const p1 = this.inicializarClases();

        Promise.all([p1]).then(f => {
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

    imprimirDeteccion(filtro) {
        this.bloquearPagina();
        this.microsuenosService.obtenerDeteccion(filtro)
            .subscribe(
                (data: Blob) => {
                    this.desbloquearPagina();
                    if (data.size == 0) {
                        this.pdfViewerReporteDeteccion.pdfSrc = 'assets/pages/emptyDoc.pdf';
                        this.pdfViewerReporteDeteccion.refresh();
                        this.mostrarMensajeInfo('No se encontraron registros');
                        return;
                    }
                    const link = window.URL.createObjectURL(data);
                    this.pdfViewerReporteDeteccion.pdfSrc = link;
                    this.pdfViewerReporteDeteccion.refresh();
                }
            );
    }


    coreNuevo() { }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) {
        if(this.esNumeroVacioOrCero(this.filtro.idClase))
        {
            this.mostrarMensajeAdvertencia("La Clase es requerida");
        }
        else
        {
            this.imprimirDeteccion(this.filtro);
        }
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