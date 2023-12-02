import { WfTransaccionChatComponent } from './transaccion-chat.component';
import { DtoTransaccionCorreoFiltros } from './../dominio/dto/DtoTransaccionCorreoFiltros';
import { WfProcesoServicio } from './../servicio/wfproceso.service';
import { DtoWfProceso } from './../dominio/dto/DtoWfProceso';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Table } from 'primeng/table';
import { MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';

@Component({
    templateUrl: './proceso-listado.component.html'
})
export class WfProcesoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
    @ViewChild(Table, { static: false }) dt: Table;
    filtro: DtoTabla = new DtoTabla();
    lst: DtoWfProceso[] = [];

    @ViewChild(WfTransaccionChatComponent, { static: false }) wfTransaccionChatComponent: WfTransaccionChatComponent;

    constructor(
        //private syReporteComunService: SyReporteComunService,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        private servicio: WfProcesoServicio,
        private cdref: ChangeDetectorRef,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    coreNuevo(): void {
        this.router.navigate([ConstanteWorkflow.ruta_wf_proceso_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }
    coreBusquedaRapida(filtro: string): void {
    }
    coreBuscar(): void {
        this.buscar();
    }
    coreGuardar(): void {
    }
    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
    }

    ngOnInit() {

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);


        super.ngOnInit();
        this.bloquearPagina();
        this.servicio.listarProcesos(this.filtro)
            .then(res => {
                this.lst = res;
                this.desbloquearPagina();
            });
    }

    buscar(): void {
        this.bloquearPagina();
        this.servicio.listarProcesos(this.filtro)
            .then(res => {
                this.lst = res;
                this.desbloquearPagina();
            });
    }

    editar(uuid: string) {
        this.router.navigate([ConstanteWorkflow.ruta_wf_proceso_mantenimiento, this.ACCIONES.EDITAR, uuid], { skipLocationChange: true });
    }

    dtoFiltros: DtoTransaccionCorreoFiltros = new DtoTransaccionCorreoFiltros();

    verListadoCorreos(uuid) {
        this.dtoFiltros = new DtoTransaccionCorreoFiltros();
        this.dtoFiltros.tipo_consulta = 'PRO';
        this.dtoFiltros.procesoUUID = uuid;
        this.router.navigate([ConstanteWorkflow.ruta_wf_correo_listado, JSON.stringify(this.dtoFiltros)], { skipLocationChange: true });
    }

    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar(dto: any) { }
    coreEditar(dto: any) {
    }
    coreVer(dto: any) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }

    verChat(tipo) {
        /*if (tipo == 'E') {
            this.wfTransaccionChatComponent.iniciarComponente(1, 'EMPL', this);
        }
        if (tipo == 'P') {
            this.wfTransaccionChatComponent.iniciarComponente(1, 'PROV', this, 103, 'SALAS OLIVERA, CLEIDY GLORIA');
        }*/
    }

    exportar(uuid: string) {
        this.bloquearPagina();
        this.servicio.exportar(uuid)
            .then(res => {
                const a = document.createElement('a');
                var jsonWf = res.descripcion;
                var x = JSON.parse(jsonWf);
                console.log(x);
                jsonWf = JSON.stringify(x, undefined, 4);
                var blob = new Blob([jsonWf]);
                a.href = window.URL.createObjectURL(blob);
                a.setAttribute('download', this.reemplazarRuta(res.nombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
                this.desbloquearPagina();
            });
    }

    importar(event: any) {
        this.bloquearPagina();
        var files = event.files;
        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > 3000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 3 + 'mb');
            this.desbloquearPagina();
            return;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result as string;
            event.files[0].name;
            var dto = new DtoTabla();
            dto.descripcion = result.split(',')[1];
            this.servicio.importar(dto).then(
                res => {
                    if (res.codigo == 'OK') {
                        this.mostrarMensajeExito(res.descripcion);
                        this.coreBuscar();
                    }
                    else {
                        this.desbloquearPagina();
                        this.mostrarMensajeAdvertencia(res.descripcion);
                    }
                }
            );
        };
    }

    copiarVersion(uuid: string) {
        this.confirmationService.confirm({
            header: 'Advertencia',
            icon: 'fa fa-question-circle',
            message: 'Al crear una nueva version las nuevas solicitudes usarán la nueva versión, ¿Desea continuar?',
            accept: () => {
                this.router.navigate([ConstanteWorkflow.ruta_wf_proceso_mantenimiento, this.ACCIONES.COPIAR, uuid], { skipLocationChange: true });
            }
        });
    }

    exportarPlantillas(aplicacion, proceso) {
        /*this.bloquearPagina();
        var filtro: FiltroComunSyReporte = new FiltroComunSyReporte();
        filtro.aplicacioncodigo = aplicacion;
        filtro.reportecodigo = proceso;
        this.syReporteComunService.exportarJsonReportes(filtro).then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res)) {
                const a = document.createElement('a');
                var jsonWf = res.archivoBase64;
                var x = JSON.parse(jsonWf);
                jsonWf = JSON.stringify(x, undefined, 4);
                var blob = new Blob([jsonWf]);
                a.href = window.URL.createObjectURL(blob);
                a.setAttribute('download',this.reemplazarRuta( res.archivoNombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
                this.desbloquearPagina();
            }
        });*/
    }

    aplicacionPlantillasImportar: string;
    procesoPlantillasImportar: string;

    importarPlantillas(aplicacion, proceso) {
        this.aplicacionPlantillasImportar = aplicacion;
        this.procesoPlantillasImportar = proceso;
    }

    importarPlantillasFile(event: any) {
        this.bloquearPagina();
        var files = event.files;
        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > 10000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 10 + 'mb');
            this.desbloquearPagina();
            return;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result as string;
            event.files[0].name;
            /*var dto = new DtoExportarReportes();
            dto.archivoBase64 = result.split(',')[1];
            this.syReporteComunService.importarJsonReportes(dto).then(
                res => {
                    this.desbloquearPagina();
                }
            );*/
        };
    }
}