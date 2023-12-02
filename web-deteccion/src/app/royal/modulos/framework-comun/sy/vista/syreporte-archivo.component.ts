import { SyReportearchivoComunService } from '../servicio/syreportearchivo-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { DtoComunSyReportearchivo } from '../dominio/dto/DtoComunSyReportearchivo';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { MessageService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';


@Component({
    selector: 'app-syreportearchivo',
    templateUrl: './syreporte-archivo.component.html'
})
export class SyReporteArchivoDatoComponent extends PrincipalBaseComponent implements OnInit {
    verSelector: Boolean = false;
    api: string;

    constructor(
        private syReporteArchivoService: SyReportearchivoComunService,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }
    reporte: string;
    syReporteArchivo: DtoComunSyReportearchivo;
    nombreArchivo: string;

    ngOnInit() {
    }

    mostrarSelector(dto: DtoComunSyReportearchivo) {
        this.verSelector = true;

        const pk: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
        pk.companiasocio = dto.companiasocio;
        pk.periodo = dto.periodo;
        pk.version = dto.version;

        this.syReporteArchivoService.obtenerDto(pk).then(reg => {
            this.syReporteArchivo = reg;
        });

    }

    guardar() {
        this.syReporteArchivoService.actualizar(this.syReporteArchivo).then(
            r => {
                this.getMensajeActualizado(this.syReporteArchivo.reportecodigo);
                this.verSelector = false;
            }
        );
    }

    onUpload(event) {
        const img = event.files[0].objectURL;
        this.reporte = img;
        this.nombreArchivo = event.files[0].name;

        const reader = new FileReader();
        reader.readAsDataURL(event.files[0]);
        reader.onloadend = (evt) => {
            // this.syReporteArchivo.auxString = reader.result;

        };
    }
}
