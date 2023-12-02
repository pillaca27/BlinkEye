import { WfAplicacionesmastComunService } from './../servicio/wfaplicacionesmast-comun.service';
import { WfSyReportearchivoComunService } from './../servicio/wfsyreportearchivo-comun.service';
import { WfDtoComunSyReporte } from './../dominio/dto/WfDtoComunSyReporte';
import { NoAuthorizationInterceptor } from './../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { WfDtoComunSyReportearchivo } from './../dominio/dto/WfDtoComunSyReportearchivo';
import { ServicioComunService } from './../../framework-comun/servicioComun.service';
import { BotonesMantenimientoComponent } from './../../../framework/angular/controles/botones/botones-mantenimiento.component';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { ActivatedRoute, Router } from '@angular/router';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { SelectItem, MessageService } from 'primeng/api';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-wf-popsyreportedetalle',
    templateUrl: './wf-syreporte-detalle-mantenimiento.component.html'
})
export class WfSyReporteDetalleMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        //private companyownerComunService: CompanyownerComunService,
        private servicio: WfSyReportearchivoComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        private wfTransaccionServicio: WfTransaccionServicio,
        private aplicaciones: WfAplicacionesmastComunService,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    accionModal: string = '';
    verSelector: Boolean = false;
    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() cargarSeleccionEvent = new EventEmitter();
    syReporteArchivo: WfDtoComunSyReportearchivo;
    lstCompania: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];
    verAsuntoGeneral: boolean = true;
    tipo: string;
    config: AngularEditorConfig = {
        editable: true,
        spellcheck: true,
        height: '25rem',
        minHeight: '25rem',
        placeholder: 'Enter text here...',
        translate: 'no',
        defaultParagraphSeparator: 'p',
        defaultFontName: 'Arial',
        toolbarPosition: 'top',
        customClasses: [
            {
                name: "quote",
                class: "quote",
            },
            {
                name: 'redText',
                class: 'redText'
            },
            {
                name: "titleText",
                class: "titleText",
                tag: "h1",
            },
        ]
    };


    ngOnInit() {

        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, '3', this.objetoBoton);

        this.syReporteArchivo = new WfDtoComunSyReportearchivo();

        this.lstCompania = [];
        this.lstCompania.push({ label: '999999', value: '999999' });
        /*const p4 = this.companyownerComunService.listar()
            .then(td => {
                td.forEach(obj => this.lstCompania.push({ label: obj.descripcion, value: obj.codigo }));
            });
*/
        this.listarAplicaciones();

    }



    listarAplicaciones() {
        this.bloquearPagina();
        this.lstAplicacion.push({ label: ConstanteAngular.COMBOTODOS, value: '' });
        //TO DO
        this.aplicaciones.listarActivos().then(
            res => {
                res.forEach(r => this.lstAplicacion.push({ label: r.nombre, value: r.codigo.trim() }));
            }
        );

    }

    nuevo(syReportePk: WfDtoComunSyReporte, tipo: string) {
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, '3', this.objetoBoton);
        this.accionModal = 'NUEVO';
        this.tipo = tipo;
        this.verSelector = true;
        this.accion = this.ACCIONES.NUEVO;
        this.syReporteArchivo = new WfDtoComunSyReportearchivo();
        this.syReporteArchivo.aplicacioncodigo = syReportePk.aplicacioncodigo;
        this.syReporteArchivo.reportecodigo = syReportePk.reportecodigo;
        this.syReporteArchivo.estado = 'A';
        this.syReporteArchivo.companiasocio = '999999';
        this.syReporteArchivo.periodo = '999999';
        this.syReporteArchivo.version = '999999';
        this.syReporteArchivo.auxString = '';
    }

    nombreSubAsunto: string = '';

    verEjecucion(dto: WfDtoComunSyReportearchivo, tipo: string) {
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, '4', this.objetoBoton);

        if (dto.periodo.startsWith('F')) {
            this.nombreSubAsunto = 'Nivel';
        }
        else {
            this.nombreSubAsunto = 'Versión';
        }

        this.accionModal = 'VER';
        this.tipo = tipo;
        this.accion = this.ACCIONES.VER;
        const pk: WfDtoComunSyReportearchivo = new WfDtoComunSyReportearchivo();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
        pk.companiasocio = dto.companiasocio;
        pk.periodo = dto.periodo;
        pk.version = dto.version;

        this.bloquearPagina();
        this.servicio.obtenerDto(pk).then(reg => {
            if (reg.version == '0') {
                this.desbloquearPagina();
                this.verSelector = false;
                this.mostrarMensajeAdvertencia('Sin Plantilla configurada');
                return;
            }
            else {
                this.metadatos = [];
                this.wfTransaccionServicio.obtenerMetadatos(dto.transaccion_id).then(
                    res => {
                        this.desbloquearPagina();
                        this.metadatos = res;
                        this.verDlgMetadatos = true;
                        this.metadatos.forEach(row => {
                            reg.auxString = reg.auxString.replace('[' + row.codigo + ']', row.nombre);
                            reg.asunto = reg.asunto.replace('[' + row.codigo + ']', row.nombre);
                        });
                        this.syReporteArchivo = reg;
                        this.verSelector = true;
                    }
                );
            }
        });
    }

    editar(dto: WfDtoComunSyReportearchivo, tipo: string) {
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, '3', this.objetoBoton);

        if (!this.esNumeroVacio(dto.transaccion_id)) {
            this.verMetadatos(dto.transaccion_id);
        }

        if (dto.periodo.startsWith('F')) {
            this.nombreSubAsunto = 'Nivel';
        }
        else {
            this.nombreSubAsunto = 'Versión';
        }

        this.accionModal = 'EDITAR';
        this.tipo = tipo;
        this.accion = this.ACCIONES.EDITAR;
        const pk: WfDtoComunSyReportearchivo = new WfDtoComunSyReportearchivo();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
        pk.companiasocio = dto.companiasocio;
        pk.periodo = dto.periodo;
        pk.version = dto.version;

        this.bloquearPagina();
        this.servicio.obtenerDto(pk).then(reg => {
            this.desbloquearPagina();
            if (reg.version == '0') {
                this.accionModal = 'NUEVO';
                this.tipo = tipo;
                this.verSelector = true;
                this.accion = this.ACCIONES.NUEVO;
                this.syReporteArchivo = new WfDtoComunSyReportearchivo();
                this.syReporteArchivo.aplicacioncodigo = dto.aplicacioncodigo;
                this.syReporteArchivo.reportecodigo = dto.reportecodigo;
                this.syReporteArchivo.estado = 'A';
                this.syReporteArchivo.companiasocio = '999999';
                this.syReporteArchivo.periodo = dto.periodo;
                this.syReporteArchivo.version = dto.version;
                this.syReporteArchivo.auxString = '';
                this.syReporteArchivo.asuntoPrincipal = reg.asuntoPrincipal;
            }
            else {
                this.syReporteArchivo = reg;
                this.verSelector = true;
            }
        });

    }


    guardar() {
        // if (!this.validar()) {
        //     return;
        // }

        this.bloquearPagina();
        if (this.accion === this.ACCIONES.NUEVO) {
            this.servicio.registrar(this.syReporteArchivo).then(
                r => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoAux(r)) {
                        this.getMensajeGrabado(this.syReporteArchivo.reportecodigo);
                        this.cargarSeleccionEvent.emit();
                        this.verSelector = false;
                    }
                }
            ).catch(
            );
        } else {
            this.servicio.actualizar(this.syReporteArchivo).then(
                r => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoAux(r)) {
                        this.getMensajeActualizado(this.syReporteArchivo.reportecodigo);
                        this.cargarSeleccionEvent.emit();
                        this.verSelector = false;
                    }
                }
            );
        }
    }

    validar() {
        let valida: Boolean = true;
        this.messageService.clear();
        if (this.estaVacio(this.syReporteArchivo.periodo)) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El periodo es requerido' });
            valida = false;
        }
        if (this.estaVacio(this.syReporteArchivo.version)) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La version es requerida' });
            valida = false;
        }
        return valida;
    }

    coreGuardar() { this.guardar() }
    coreSalir() { this.verSelector = false; }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }



    transaccionResultadoAux(dto: any): boolean {
        this.mensajeResultado = 'Acción realizada con éxito.';
        if (dto != null) {
            console.log(dto);
            if (dto.transaccionEstado == 'ER' || dto.transaccionEstado == 'VA') {
                if (dto.transaccionListaMensajes.length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje });
                    });
                }
                return false;
            } else if (dto.transaccionEstado == 'OK') {
                this.mostrarMensajeExito('Acción realizada con éxito.');
                return true;
            }
        }
        return false;
    }



    metadatos: DtoTabla[] = [];
    verDlgMetadatos: boolean = false;

    verMetadatos(transaccionid: number) {
        this.metadatos = [];
        this.bloquearPagina();
        this.wfTransaccionServicio.obtenerMetadatos(transaccionid).then(
            res => {
                this.desbloquearPagina();
                this.metadatos = res;
                this.verDlgMetadatos = true;
            }
        )

    }

    subiendoDto: WfDtoComunSyReportearchivo = null;
    resultado: string;

    subirArchivoDet(dto: WfDtoComunSyReportearchivo, fs: any) {
        fs.click();
        this.subiendoDto = dto;
    }

    cargarArchivoDet(event: any) {
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

            const pk: WfDtoComunSyReportearchivo = new WfDtoComunSyReportearchivo();
            pk.aplicacioncodigo = this.subiendoDto.aplicacioncodigo;
            pk.reportecodigo = this.subiendoDto.reportecodigo;
            pk.companiasocio = this.subiendoDto.companiasocio;
            pk.periodo = this.subiendoDto.periodo;
            pk.version = this.subiendoDto.version;

            this.servicio.obtenerDto(pk).then(reg => {
                this.syReporteArchivo.auxString = cadena;
                console.log('cargar data');
                this.syReporteArchivo.auxString = this.servicio.cambiarHtml(this.syReporteArchivo.auxString);
                this.desbloquearPagina();

                // this.servicio.actualizar(reg).then(
                //     r => {
                //         this.mostrarMensajeExito("Se ha subido el archivo");
                //         this.syReporteArchivo = reg;
                //         this.desbloquearPagina();
                //     }
                // );
            });


        }
        reader.readAsText(files[0]);
    }


    bajarArchivoDet(dto: WfDtoComunSyReportearchivo) {

        const pk: WfDtoComunSyReportearchivo = new WfDtoComunSyReportearchivo();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
        pk.companiasocio = dto.companiasocio;
        pk.periodo = dto.periodo;
        pk.version = dto.version;

        this.servicio.obtenerDto(pk).then(reg => {

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
            a.setAttribute('download',  this.reemplazarRuta(reg.reportecodigo, ',', '_')  + "_" + reg.version + '.html');
            a.style.display = 'none';

            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        });
    }


    guardarDet(dto: WfDtoComunSyReportearchivo) {
        // if (!this.validar()) {
        //     return;
        // }

        this.bloquearPagina();
        if (this.accion === this.ACCIONES.NUEVO) {
            this.servicio.registrar(this.syReporteArchivo).then(
                r => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoAux(r)) {
                        // this.getMensajeGrabado(this.syReporteArchivo.reportecodigo);
                        this.cargarSeleccionEvent.emit();
                        this.bajarArchivoDet(dto);
                        this.verSelector = false;
                    }
                }
            ).catch(
            );
        } else {
            this.servicio.actualizar(this.syReporteArchivo).then(
                r => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoAux(r)) {
                        // this.getMensajeActualizado(this.syReporteArchivo.reportecodigo);
                        this.cargarSeleccionEvent.emit();
                        this.bajarArchivoDet(dto);
                    }
                }
            );
        }
    }


}
