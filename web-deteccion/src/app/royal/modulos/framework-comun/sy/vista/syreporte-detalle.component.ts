import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesMantenimientoComponent } from './../../../../framework/angular/controles/botones/botones-mantenimiento.component';
import { ConstanteComun } from './../../ConstanteComun';
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { SyReportearchivoComunService } from '../servicio/syreportearchivo-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { SyReporteDetalleMantenimientoComponent } from './syreporte-detalle-mantenimiento.component';
import { ActivatedRoute, Router } from '@angular/router';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { SyReporteArchivoDatoComponent } from './syreporte-archivo.component';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { Table } from 'primeng/table';
import { DtoComunSyReportearchivo } from '../dominio/dto/DtoComunSyReportearchivo';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { FiltroComunSyReportearchivo } from '../dominio/filtro/FiltroComunSyReportearchivo';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-syreportedetalle',
    templateUrl: './syreporte-detalle.component.html'
})
export class SyReporteDetalleComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private servicio: SyReporteComunService,
        private servicioarchivo: SyReportearchivoComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;

    @ViewChild(SyReporteDetalleMantenimientoComponent, { static: false })
    syReporteDetalleMantenimientoComponent: SyReporteDetalleMantenimientoComponent;

    @ViewChild(SyReporteArchivoDatoComponent, { static: false })
    syReporteArchivoDatoComponent: SyReporteArchivoDatoComponent;

    syReportePk: DtoComunSyReporte;
    registrosPorPagina: number = 7;
    filtro: FiltroComunSyReportearchivo;
    resultado: string;
    tipoReporte: string;
    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @ViewChild(Table, { static: false }) dt: Table;

    ngOnInit() {        
        this.formularioIniciar(this.route);                
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        this.syReportePk = new DtoComunSyReporte();
        const reporteCodigo = this.route.snapshot.params['reporteCodigo'];
        const aplicacioncodigo = this.route.snapshot.params['aplicacionCodigo'];
        const uuid = this.route.snapshot.params['uuid'];

        if (aplicacioncodigo) {
            this.syReportePk.aplicacioncodigo = aplicacioncodigo;
            this.syReportePk.reportecodigo = reporteCodigo;
            this.syReportePk.uuid = uuid;
            this.servicio.obtenerDto(this.syReportePk).then(reg => {
                this.tipoReporte = reg.tiporeporte;
            });
        }

        this.listar();
    }



    nuevo() {
        this.syReporteDetalleMantenimientoComponent.nuevo(this.syReportePk, this.tipoReporte);
    }

    editar(dto: DtoComunSyReportearchivo) {
        this.syReporteDetalleMantenimientoComponent.editar(dto, this.tipoReporte);
    }

    subiendoDto: DtoComunSyReportearchivo = null;

    subirArchivo(dto: DtoComunSyReportearchivo, fs: any) {
        fs.click();
        this.subiendoDto = dto;
    }
    cargarArchivo(event: any) {
        this.bloquearPagina();

        var files = event.files;

        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > 1000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 1 + 'mb');
            this.desbloquearPagina();
            return null;
        }

        if (files[0].type != 'text/html') {
            this.mostrarMensajeAdvertencia('Solo se permiten archivos de extensión .html');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.onloadend = (evt) => {


            this.resultado = reader.result as string;

            var lines = this.resultado.split('\n');
            var cadena = '';
            for (var line = 0; line < lines.length; line++) {
                cadena = cadena + (lines[line]);
            }

            const pk: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
            pk.aplicacioncodigo = this.subiendoDto.aplicacioncodigo;
            pk.reportecodigo = this.subiendoDto.reportecodigo;
            pk.companiasocio = this.subiendoDto.companiasocio;
            pk.periodo = this.subiendoDto.periodo;
            pk.version = this.subiendoDto.version;            

            this.servicioarchivo.obtenerDto(pk).then(reg => {
                reg.auxString = cadena;
                this.servicioarchivo.actualizar(reg).then(
                    r => {
                        this.mostrarMensajeExito("Se ha subido el archivo");
                        this.desbloquearPagina();
                    }
                );
            });


        }
        reader.readAsText(files[0]);
    }

    listar() {
        if (this.estaVacio(this.syReportePk.aplicacioncodigo)) {
            return;
        }

        this.filtro = new FiltroComunSyReportearchivo();
        this.filtro.estado = 'A';
        this.filtro.aplicacioncodigo = this.syReportePk.aplicacioncodigo;
        this.filtro.reportecodigo = this.syReportePk.reportecodigo;

        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;

        this.servicioarchivo.listarPaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
            });
    }

    cargar(event: LazyLoadEvent) {
        if (this.estaVacio(this.filtro.aplicacioncodigo)) {
            return;
        }
        this.block.emit();
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.servicioarchivo.listarPaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.unBlock.emit();
            });
    }

    eliminar(dto: DtoComunSyReportearchivo) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {

                this.bloquearPagina();
                const pk: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
                pk.aplicacioncodigo = dto.aplicacioncodigo;
                pk.reportecodigo = dto.reportecodigo;
                pk.companiasocio = dto.companiasocio;
                pk.periodo = dto.periodo;
                pk.version = dto.version;
                this.block.emit();
                this.servicioarchivo.eliminar(pk).then(r => {
                    this.desbloquearPagina();
                    this.dt.reset();
                    this.messageService.clear();
                    this.messageService.add({
                        severity: 'info', summary: 'Información',
                        detail: this.getMensajeActualizado(dto.reportecodigo)

                    });
                });
            }
        });
    }



    salir() {
        this.router.navigate([ConstanteComun.ruta_syreporte_listado]);
    }


    bajarArchivo(dto: DtoComunSyReportearchivo) {

        const pk: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
        pk.companiasocio = dto.companiasocio;
        pk.periodo = dto.periodo;
        pk.version = dto.version;

        this.servicioarchivo.obtenerDto(pk).then(reg => {

            if (reg === null) {
                return;
            }

            if (reg.auxString === null) {
                this.mostrarMensajeAdvertencia('No se encontró el documento');
                return;
            }


            reg.auxString = reg.auxString.replace(new RegExp('&lt;', 'g'), '<');
            reg.auxString = reg.auxString.replace(new RegExp('&gt;', 'g'), '>');

            const a = document.createElement('a');

            a.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(reg.auxString));
            a.setAttribute('download', this.reemplazarRuta(reg.reportecodigo, ',', '_')  + "_" + reg.version + '.html');
            a.style.display = 'none';

            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        });
    }


    coreGuardar(){this.nuevo()}
    coreSalir(){this.salir()}
    coreExportar(tipo: string){}
    coreMensaje(mensage: MensajeController){}
    coreAccion(accion: string){}

}
