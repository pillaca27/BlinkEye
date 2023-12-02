import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { FiltroPaginacionEmpleado } from '../../dominio/filtros/filtropaginacionempleado';
import { AsAccesosdiariosService } from '../../servicio/asaccesosdiarios.service';
import { FiltrosFechasComponent } from '../filtros-fechas/uo_filtro-fechas.component';

@Component({
    templateUrl: 'reporte-marca.component.html'
})

export class ReporteMarcaComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild('pdfViewerReporteMarcas', { static: false }) pdfViewerReporteMarcas;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(FiltrosFechasComponent, { static: false })
    filtrosFechasComponent: FiltrosFechasComponent;

    constructor(
        private asAccesosdiariosService: AsAccesosdiariosService,
        private route: ActivatedRoute,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngOnInit() {
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.formularioOninit = false;
        this.desbloquearPagina();
    }

    imprimirMarcar(filtro) {
        this.bloquearPagina();
        this.asAccesosdiariosService.obtenerMarcas(filtro)
            .subscribe(
                (data: Blob) => {
                    this.desbloquearPagina();
                    if (data.size == 0) {
                        this.pdfViewerReporteMarcas.pdfSrc = 'assets/pages/emptyDoc.pdf';
                        this.pdfViewerReporteMarcas.refresh();
                        this.mostrarMensajeInfo('No se encontraron registros');
                        return;
                    }
                    const link = window.URL.createObjectURL(data);
                    this.pdfViewerReporteMarcas.pdfSrc = link;
                    this.pdfViewerReporteMarcas.refresh();
                }
            );
    }


    coreNuevo() { }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar(tabla: LazyLoadEvent) { }
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