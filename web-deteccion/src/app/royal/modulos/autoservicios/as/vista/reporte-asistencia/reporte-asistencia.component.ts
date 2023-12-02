import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AcCostcenterComunService } from '@framework-comun/contabilidad/servicio/accostcenter-comun.service';
import { EmpleadomastComunService } from '@framework-comun/core/servicio/empleadomast-comun.service';
import { PersonamastComunService } from '@framework-comun/core/servicio/personamast-comun.service';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { PrTipoplanillaComunService } from '@framework-comun/pr/servicio/prtipoplanilla-comun.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { FiltroPaginacionEmpleado } from '../../dominio/filtros/filtropaginacionempleado';
import { AsAccesosdiariosService } from '../../servicio/asaccesosdiarios.service';
import { FiltrosFechasComponent } from '../filtros-fechas/uo_filtro-fechas.component';

@Component({
    templateUrl: 'reporte-asistencia.component.html'
})

export class ReporteAsistenciaComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild('pdfViewerReporteAsistencia', { static: false }) pdfViewerReporteAsistencia;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(FiltrosFechasComponent, { static: false })
    filtrosFechasComponent: FiltrosFechasComponent;

    constructor(
        private cdref: ChangeDetectorRef,
        private asAccesosdiariosService: AsAccesosdiariosService,
        private route: ActivatedRoute,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngOnInit() {
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    imprimirAsistencias(filtro: FiltroPaginacionEmpleado) {
        this.bloquearPagina();
        this.asAccesosdiariosService.obtenerAsistencia(filtro)
            .subscribe(
                (data: Blob) => {
                    this.desbloquearPagina();
                    if (data.size == 0) {
                        this.pdfViewerReporteAsistencia.pdfSrc = 'assets/pages/emptyDoc.pdf';
                        this.pdfViewerReporteAsistencia.refresh();
                        this.mostrarMensajeInfo('No se encontraron registros');
                        return;
                    }
                    const link = window.URL.createObjectURL(data);
                    this.pdfViewerReporteAsistencia.pdfSrc = link;
                    this.pdfViewerReporteAsistencia.refresh();
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