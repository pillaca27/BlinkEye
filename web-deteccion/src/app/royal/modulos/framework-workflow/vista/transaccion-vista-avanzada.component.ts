import { WfTransaccionSeguimientoMacroProcesoComponent } from './transaccion-seguimiento-macroproceso.component';
import { WfTransaccionSeguimientoComponent } from './transaccion-seguimiento.component';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { WfTransaccionListadoCorreoComponent } from './transaccion-listado-correos.component';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { Component, OnInit, ViewChild, Input, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { WfTransaccion, WfTransaccionPk } from '../dominio/dto/WfTransaccion';
import { WfTransaccionServicio } from '../servicio/wftransaccion.service';
import { WfProcesoServicio } from '@framework-workflow/servicio/wfproceso.service';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DtoWfTransaccionAdjunto } from '../dominio/dto/DtoWfTransaccionAdjunto';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoTransaccionVistaAvanzada } from '../dominio/dto/DtoTransaccionVistaAvanzada';
//import { DtoComunSyReportearchivo } from '@framework-comun/sistema/dominio/dto/DtoComunSyReportearchivo';
//import { SyReporteDetalleMantenimientoComponent } from '@framework-comun/sistema/vista/syreporte-detalle-mantenimiento.component';

@Component({
    templateUrl: './transaccion-vista-avanzada.component.html'
})
export class WfTransaccionVistaAvanzadaComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    @ViewChild(WfTransaccionSeguimientoMacroProcesoComponent, { static: false }) wfTransaccionSeguimientoMacroProcesoComponent: WfTransaccionSeguimientoMacroProcesoComponent;
    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionListadoCorreoComponent, { static: false }) wfTransaccionListadoCorreoComponent: WfTransaccionListadoCorreoComponent;
    //@ViewChild(SyReporteDetalleMantenimientoComponent, { static: false }) syReporteDetalleMantenimientoComponent: SyReporteDetalleMantenimientoComponent;


    dto: DtoTransaccionVistaAvanzada = new DtoTransaccionVistaAvanzada();
    adjuntos: any[] = [];
    seguimiento: any[] = [];
    transaccionAprobador: DtoTabla[] = [];

    lstProcesos: SelectItem[] = [];
    lstTipoDocumento: SelectItem[] = [];
    adjuntosConfig: any[] = [];
    cambioNivel: number;
    verCambiarNivel: boolean = false;

    constructor(
        private procesosService: WfProcesoServicio,
        private router: Router,
        private route: ActivatedRoute,
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    lstHTML: DtoWfTransaccionAdjunto[] = [];

    ngOnInit() {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.lstProcesos.push({ label: " -- Seleccione -- ", value: null });

        const accionExterna = this.route.snapshot.params['accion'] as number;
        this.accion = accionExterna;

        this.bloquearPagina();

        var p1 = this.procesosService.listarProcesosSinVersion(new DtoTabla()).then(
            x => {
                x.forEach(row => {
                    this.lstProcesos.push({ label: row.descripcion, value: row.proceso });
                });
            }
        )

        var p2 = this.wfTransaccionServicio.sytipodocumento().then(
            docs => {
                docs = docs.sort(this.compare);
                this.lstTipoDocumento.push({ value: null, label: ' -- Seleccione --' });
                docs.forEach(y => {
                    this.lstTipoDocumento.push({ value: y.codigo.trim(), label: y.descripcion });
                });
            }
        );

        Promise.all([p1, p2]).then(
            res => {
                this.iniciarFormulario();
            }
        );
    }

    refrescarVentana() {
        this.iniciarFormulario();
    }

    compare(a: DtoTabla, b: DtoTabla) {
        if (a.descripcion < b.descripcion) {
            return -1;
        }
        if (a.descripcion > b.descripcion) {
            return 1;
        }
        return 0;
    }

    iniciarFormulario() {


        var pkUUID = JSON.parse(this.route.snapshot.params['pk'] as string);
        var p1 = this.wfTransaccionSeguimientoComponent.iniciarComponente(pkUUID);

        this.wfTransaccionSeguimientoMacroProcesoComponent.iniciarComponente(pkUUID);

        var p2 = this.wfTransaccionServicio.obtenerVistaAvanzada(pkUUID).then(
            res => {
                if (res != null) {
                    this.dto = res;
                    this.wfTransaccionListadoCorreoComponent.coreIniciarComponente('TRA', pkUUID);
                    this.procesosService.obtenerPlantillasRelacionadas(this.dto.procesoUUID).then(
                        res => {
                            this.lstPlantillasRelacionadas = res;
                        });
                }
            }
        );
        var p3 = this.wfTransaccionServicio.obtenerAdjuntosAprobacion(pkUUID).then(
            res => {
                this.adjuntos = res;
            }
        );

        var p4 = this.wfTransaccionServicio.obtenerSeguimientoVistaAvanzada(pkUUID).then(
            res => {
                this.seguimiento = res;
            }
        );

        var p5 = this.wfTransaccionServicio.obtenerTransaccionAprobadorVistaAvanzada(pkUUID).then(
            res => {
                this.transaccionAprobador = res;
            }
        );

        var p6 = this.wfTransaccionServicio.obtenerConfiguracionDocumentosVistaAvanzada(pkUUID).then(
            res => {
                this.adjuntosConfig = res;
            }
        );

        Promise.all([p1, p2, p3, p4, p5, p6]).then(
            res => {
                this.desbloquearPagina();
            }
        );
    }

    salir() {
        this.router.navigate(['/portal/workflow/transaccion-listado-administrador']);
    }

    descargarDocumento(secuencia: number) {
        const doc = this.adjuntos.find(x => x.secuencia == secuencia);
        doc.transaccionUUID = this.dto.transaccionUUID;
        this.bloquearPagina();
        this.wfTransaccionServicio.verAdjunto(doc).then(
            yy => {
                this.desbloquearPagina()
                if (yy.archivostring == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                var temp = [...this.adjuntos];
                doc.archivostring = yy.archivostring;
                const a = document.createElement('a');
                if (!doc.archivostring.startsWith("data:")) {
                    a.href = 'data:application/octet-stream;base64,' + doc.archivostring;
                } else {
                    a.href = doc.archivostring;
                }
                a.setAttribute('download', this.reemplazarRuta(doc.archivonombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
                this.adjuntos = temp;
            }
        );
    }

    coreSalir() {
        this.salir();
    }
    coreExportar(tipo: string): void { }
    coreMensaje(mensage: MensajeController): void { }
    coreAccion(accion: any): void { }
    coreGuardar() {

    }


    cambiarnivel() {
        this.verCambiarNivel = true;
    }

    confirmarCambiarNivel() {


        if (this.esNumeroVacio(this.cambioNivel)) {
            this.mostrarMensajeError('Debe ingresar el nivel');
            return;
        }


        this.bloquearPagina();
        this.wfTransaccionServicio.cambiarNivel(this.dto.transaccionId, this.cambioNivel).then(
            res => {
                this.desbloquearPagina();
                this.verCambiarNivel = false;
                this.salir();
            }
        );

    }

    lstPlantillasRelacionadas: any[];



    editarReporte(dto: any) {
        //this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }

    confirmarPlantilla(event) {
    }
}
