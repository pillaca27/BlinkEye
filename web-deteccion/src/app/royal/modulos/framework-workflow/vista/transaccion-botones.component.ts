import { DtoWfFlujoNivel } from './../dominio/dto/DtoWfFlujoNivel';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { ConstanteWorkflow } from './../ConstanteWorkflow';
import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { DtoFlujoAdjunto } from './../dominio/dto/DtoFlujoTransaccionRequest';
import { DtoFlujoSolicitud } from './../dominio/dto/DtoFlujoSolicitud';
import { DtoAprobacionAccion, DtoAprobacionAcciones } from './../dominio/dto/DtoAprobacionAcciones';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { FirmaComponent } from './firma.component';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { DominioMensajeUsuario } from 'src/app/royal/framework/modelo/generico/DominioMensajeUsuario';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
//import { Console } from 'node:console';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { EmailTransaccion } from '@framework/modelo/correo/EmailTransaccion';
import { EmailDestino } from '@framework/modelo/correo/EmailDestino';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { DominioAdjunto } from '@framework/modelo/generico/DominioAdjunto';

@Component({
    templateUrl: './transaccion-botones.component.html',
    selector: 'transaccion-botones',
    styles: [`
    :host ::ng-deep .p-datatable-scrollable-body {
        padding-right: 17px;
      }
    `]
})
export class WfTransaccionBotonesComponent extends PrincipalBaseComponent implements OnInit {

    maxFileSize: number;

    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() cargarDataEvent = new EventEmitter();
    @Output() enviarResumenCabeceraEvent = new EventEmitter();
    @ViewChild(FirmaComponent, { static: false }) firmaComponent: FirmaComponent;

    tooltipWF: string = '';

    tieneAccionesXWorkflow: boolean = false;

    accionesPermitidas: DtoAprobacionAcciones = new DtoAprobacionAcciones();
    botonesAdicionales: DtoAprobacionAccion[];

    puedeFirmarDigital: boolean = false;
    puedeFirmarImagen: boolean = false;
    puedeSeleccionar: boolean = false;

    flgVerDocumentoGrupo: boolean = false;
    flgVerDocumentoNuevo: boolean = false;

    cargando: boolean = true;
    transaccionUUID: string;

    componenteAprobar: any;
    componenteDevolver: any;
    componenteRechazar: any;

    llaveDocs: DtoFlujoAdjunto;

    subaccion: string;

    motivo: string;
    verDlgMotivo: boolean = false;
    tituloDlgMotivo: string = '';
    motivoWfAccion: string;
    motivoWfAccionDescripcion: string;

    verDlgMotivoDetallado: boolean = false;

    configNivel: DtoWfFlujoNivel = new DtoWfFlujoNivel();

    mail: EmailTransaccion = new EmailTransaccion();

    puedeEditarAgregarEliminarAdjunto: boolean = false;

    puedeDescargarAdjunto: boolean = true;

    configHtmlEditor: AngularEditorConfig = {
        editable: true,
        spellcheck: false,
        height: '25rem',
        minHeight: '25rem',
        placeholder: 'Puede editar el mensaje...',
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
    documentosPermitidos: string[] = [];
    constructor(
        //private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.servicioComunLocal.listaractivos('999999', 'SY', 'EXTADJPER').then(res => {
            res.forEach(r => { this.documentosPermitidos.push(r.codigo.trim()) });
        });
    }

    nivelActual: number;
    recargarBotoneraLuegoDeEjecucion: boolean = false;
    /**
     * 
     * @param transaccionuuid
     * @param flagVerBotones 
     * @param botonesAdicionales Opcional
     * @returns 
     */
    iniciarComponente(transaccionUUID: string, flagVerBotones: string, recargarBotoneraLuegoDeEjecucion: boolean, botonesAdicionales?: DtoAprobacionAccion[]) {
        this.recargarBotoneraLuegoDeEjecucion = recargarBotoneraLuegoDeEjecucion;

        this.tieneAccionesXWorkflow = false;
        this.botonesAdicionales = [];

        if (botonesAdicionales != null && botonesAdicionales != undefined) {
            this.botonesAdicionales = botonesAdicionales;
        }


        var adjuntosTemporal: DtoFlujoAdjunto[] = [];

        this.tituloDocumentos = 'Documentos';
        this.flagVerBotones = flagVerBotones;
        if (transaccionUUID == null) {
            return;
        }
        this.transaccionUUID = transaccionUUID;
        var p = [];
        this.cargando = true;
        this.tipoDocs = [];
        var p2 = this.wfTransaccionServicio.sytipodocumento().then(
            docs => {
                this.tipoDocsRaw = docs;
                this.tipoDocs.push({ value: null, label: ' -- Seleccione --' });
                docs.forEach(y => {
                    this.tipoDocs.push({ value: y.codigo.trim(), label: y.descripcion });
                });
            }
        );
        p.push(p2);
        var p3 = this.wfTransaccionServicio.obtenerAdjuntosAprobacion(transaccionUUID).then(
            x => {
                x.forEach(
                    y => {
                        y.firmaElectronica = 'N';
                        y.firmaImagen = 'N'
                    }
                );
                adjuntosTemporal = x;
            }
        )
        p.push(p3);

        this.tipoDocsNivel = [];
        var p4 = this.wfTransaccionServicio.listarDocumentoRequeridos(transaccionUUID).then(
            docs => {
                this.tipoDocsNivelRaw = docs;
                if (docs.length > 0) {
                    this.tipoDocsNivel.push({ value: null, label: ' -- Seleccione --' });
                }
                docs.forEach(y => {
                    var des = y.descripcion;
                    this.tipoDocsNivel.push({ value: y.codigo.trim(), label: des });
                });
            }
        );
        p.push(p4);

        if (this.flagVerBotones == 'S') {
            var p1 = this.wfTransaccionServicio.obtenerAccionesAprobacion(transaccionUUID).then(
                x => {
                    this.nivelActual = x.nivelActual;
                    var acciones = 0;
                    acciones = acciones + x.botonesaprobar.length;
                    acciones = acciones + x.botonesdevolver.length;
                    acciones = acciones + x.botonesrechazar.length;
                    if (acciones > 0) {
                        this.tieneAccionesXWorkflow = true;
                    }
                    this.accionesPermitidas = x;
                }
            );
            p.push(p1);
        }

        var p5 = this.parametroObtenerTexto(ConstanteWorkflow.DEFECTO_COMPANIA, ConstanteWorkflow.APLICACION, ConstanteWorkflow.PARAMETRO_HABFIRELEC).then
            (
                x => {
                    this.puedeFirmarDigital = x == 'S' ? true : false;
                }
            );

        var p6 = this.parametroObtenerTexto(ConstanteWorkflow.DEFECTO_COMPANIA, ConstanteWorkflow.APLICACION, ConstanteWorkflow.PARAMETRO_HABFIRIMAG).then
            (
                x => {
                    this.puedeFirmarImagen = x == 'S' ? true : false;
                }
            );

        var p7 = this.wfTransaccionServicio.obtenerLlaveParaSyDocumento(transaccionUUID).then(
            llaveDocs => {
                this.llaveDocs = llaveDocs;
                this.flgVerDocumentoGrupo = this.llaveDocs.flgVerDocumentoGrupo == 'S' ? true : false;
                this.flgVerDocumentoNuevo = this.llaveDocs.flgVerDocumentoNuevo == 'S' ? true : false;
                this.puedeEditarAgregarEliminarAdjunto = this.llaveDocs.estadoTransaccion == 'S' ? true : false;
            }
        );

        p.push(p5);
        p.push(p6);
        p.push(p7);

        var p8 = this.wfTransaccionServicio.obtenerConfiguracionObservaciones(transaccionUUID).then(
            res => {
                this.configNivel = res;
            }
        );

        p.push(p8);


        var pTitle = this.wfTransaccionServicio.obtenerTitleWF(transaccionUUID).then(
            res => {
                this.tooltipWF = res.descripcion;
            }
        );

        p.push(pTitle);

        var pFSize = this.parametroObtenerNumero('999999', 'HR', 'MXSIFOLIO').then(
            res => {
                if (res == null || res == undefined) {
                    this.maxFileSize = 1000000;
                }
                else {
                    this.maxFileSize = res;
                }
                return 1;
            }
        );

        p.push(pFSize);

        Promise.all(p).then(
            x => {
                //hacer merge con los requeridos (tipoDocsNivelRaw) y los propios de transaccionadjunto (adjuntos)
                //colocar los requeridos actuales al inicio
                var adjuntosFinal: DtoFlujoAdjunto[] = [];
                this.tipoDocsNivelRaw.sort((a, b) => (a.estadoId > b.estadoId) ? 1 : -1);
                this.tipoDocsNivelRaw.forEach(
                    d => {
                        var req = new DtoFlujoAdjunto();
                        req.tipodocumento = d.codigo;
                        req.grupo = d.estadoId;
                        req.plantilla = d.estadoNombre;
                        req.archivonombre = '';
                        req.auxRequerido = d.nombre;
                        //si el tipo ya fue registrado, se coloca
                        var yaSubido = adjuntosTemporal.filter(x => x.tipodocumento == d.codigo);
                        if (yaSubido.length > 0) {
                            //cuando hay mas de un tipo, agregarlos
                            yaSubido.forEach(row => {
                                adjuntosFinal.push(row);
                            });
                        }
                        else {
                            adjuntosFinal.push(req);
                        }
                    }
                );
                //aun si se han subido documentos antes, solo filtrar a lo que le toca en el nivel
                /*adjuntosTemporal.forEach(
                    d => {
                        if (adjuntosFinal.filter(x => x.tipodocumento == d.tipodocumento).length == 0) {
                            adjuntosFinal.push(d);
                        }
                    }
                );*/
                this.adjuntos = adjuntosFinal;
                this.cargando = false;
            }
        )
    }

    aprobar(subaccion: string, nombreBoton: string) {
        this.descripcionAccionEjecutandose = nombreBoton;
        this.motivoWfAccion = 'APROBAR';
        this.motivoWfAccionDescripcion = 'aprobado';
        if (this.componenteAprobar == null || this.componenteAprobar === undefined) {
            if (!this.configNivel.auxFlgAprobarComentario) {
                this.motivo = ' ';
                this.aceptarMotivo('N')
            }
            else {
                this.tituloDlgMotivo = 'Observaciones';
                if (this.configNivel.auxFlgComentarioDetalladoAprobar) {
                    this.abrirDialogoComentarioDetallado();
                }
                else {
                    this.motivo = "";
                    this.subaccion = subaccion;
                    this.verDlgMotivo = true;
                }
            }
        } else {
            let msjContr = new MensajeController(null, 'WORKFLOW', 'APROBAR');
            msjContr.parametros = subaccion;

            if (this.puedeSeleccionar) {
                this.docsSeleccionados.forEach(ele => {
                    var adj = this.adjuntos.find(x => x.secuencia == ele.secuencia);
                    if (adj != undefined) {
                        adj.auxSeleccionado = 'S';
                    }
                });
            }

            msjContr.resultado = this.adjuntos.filter(x => x.secuencia != null);

            console.log(msjContr.resultado);
            this.componenteAprobar.coreMensaje(msjContr);
        }
    }

    rechazar(subaccion: string, nombreBoton: string) {
        this.descripcionAccionEjecutandose = nombreBoton;
        this.motivoWfAccion = 'RECHAZAR';
        this.motivoWfAccionDescripcion = 'rechazado';
        if (this.componenteRechazar == null || this.componenteRechazar === undefined) {
            this.tituloDlgMotivo = 'Motivo de Rechazo';
            if (this.configNivel.auxFlgComentarioDetalladoRechazar) {
                this.abrirDialogoComentarioDetallado();
            }
            else {
                this.motivo = "";
                this.subaccion = subaccion;
                this.verDlgMotivo = true;
            }
        } else {
            let msjContr = new MensajeController(null, 'WORKFLOW', 'RECHAZAR');
            msjContr.parametros = subaccion;
            this.componenteRechazar.coreMensaje(msjContr);
        }
    }

    descripcionAccionEjecutandose: string;

    devolver(subaccion: string, nombreBoton: string) {
        this.descripcionAccionEjecutandose = nombreBoton;
        this.motivoWfAccion = 'DEVOLVER';
        this.motivoWfAccionDescripcion = 'devuelto';
        if (this.componenteDevolver == null || this.componenteDevolver === undefined) {
            this.tituloDlgMotivo = 'Motivo';
            if (this.configNivel.auxFlgComentarioDetalladoDevolver) {
                this.abrirDialogoComentarioDetallado();
            }
            else {
                this.motivo = "";
                this.subaccion = subaccion;
                this.verDlgMotivo = true;
            }
        } else {
            let msjContr = new MensajeController(null, 'WORKFLOW', 'DEVOLVER');
            msjContr.parametros = subaccion;
            this.componenteDevolver.coreMensaje(msjContr);
        }
    }

    aceptarMotivo(conMailPreparado: string) {

        //cuando se devuelve o rechaza y se usa el componente simple, campo obligatorio
        if ((this.motivoWfAccion == 'RECHAZAR' || this.motivoWfAccion == 'DEVOLVER') && this.verDlgMotivo) {
            if (this.estaVacio(this.motivo)) {
                this.mostrarMensajeAdvertencia('Ingrese el ' + this.tituloDlgMotivo.toLowerCase());
                return;
            }
        }

        this.verDlgMotivoDetallado = false;
        this.verDlgMotivo = false;
        this.block.emit();
        var flujo: DtoFlujoSolicitud[] = [];
        var dto = new DtoFlujoSolicitud();
        dto.nivelActual = this.nivelActual;
        dto.uuid = this.transaccionUUID;
        dto.conEmailPreparado = conMailPreparado;
        dto.adjuntos = this.adjuntos.filter(x => x.secuencia != null);
        if (dto.conEmailPreparado == 'N') {
            dto.observaciones = this.motivo;
        }
        if (dto.conEmailPreparado == 'S') {
            dto.observaciones = '';
            dto.email = this.mail;
            dto.email.cuerpoCorreoBase64 = btoa(dto.email.cuerpoCorreoBase64);
        }
        flujo.push(dto);
        this.wfTransaccionServicio.transaccionEjecutar({ accion: this.motivoWfAccion, subaccion: this.subaccion, listaSolicitudes: flujo }).then(
            x => {
                this.unBlock.emit();
                if (x.length == 0) {
                    this.mostrarMensajeExito("Se ha " + this.motivoWfAccionDescripcion + " el registro");
                    if (this.recargarBotoneraLuegoDeEjecucion) {
                        this.iniciarComponente(this.transaccionUUID, this.flagVerBotones, this.recargarBotoneraLuegoDeEjecucion, this.botonesAdicionales);
                    }
                    this.cargarDataEvent.emit();
                }
                else {
                    this.mostrarMensajesWorkflow(x);
                }
            }
        );
    }

    abrirDialogoComentarioDetallado() {
        this.bloquearPagina();
        this.wfTransaccionServicio.obtenerCorreoObservacion(this.transaccionUUID, this.motivoWfAccion).then(
            res => {
                this.mail = res;
                this.adjuntosDlgObservaciones = this.adjuntos.filter(x => x.secuencia != null);
                console.log(this.adjuntosDlgObservaciones);
                this.verDlgMotivoDetallado = true;
                this.desbloquearPagina();
            }
        );
    }

    agregarCorreo() {
        var temp = [...this.mail.listaCorreoDestino];
        var nuevo = new EmailDestino();
        nuevo.correoDestino = '';
        temp.push(nuevo);
        this.mail.listaCorreoDestino = temp;
    }

    eliminarCorreo(r: EmailDestino) {
        var temp = [...this.mail.listaCorreoDestino];
        temp = temp.filter(x => x != r);
        this.mail.listaCorreoDestino = temp;
    }

    eliminarDocumentoObservaciones(r: DominioAdjunto) {
        var temp = [...this.mail.listaCorreoAdjunto];
        temp = temp.filter(x => x != r);
        this.mail.listaCorreoAdjunto = temp;
    }

    seleccionarDocProcesosDlgObservacion(secuencia: number) {
        var documento = this.adjuntos.find(x => x.secuencia == secuencia);
        if (this.mail.listaCorreoAdjunto.filter(x => x.nombreArchivo == documento.archivonombre).length > 0) {
            this.mostrarMensajeAdvertencia("El archivo ya se ha seleccionado");
            return;
        }
        this.bloquearPagina();
        documento.transaccionUUID = this.transaccionUUID;
        this.wfTransaccionServicio.verAdjunto(documento).then(
            yy => {
                this.desbloquearPagina();
                if (yy.archivostring == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                var doc = new DominioAdjunto();
                doc.nombreArchivo = documento.archivonombre;
                doc.archivoAdjuntoBase64 = yy.archivostring;
                var temp = [...this.mail.listaCorreoAdjunto];
                temp.push(doc);
                this.mail.listaCorreoAdjunto = temp;
            }
        );
    }

    cargarFolioDlgMail(event: any) {
        this.bloquearPagina();
        var doc = new DominioAdjunto();
        var files = event.files;
        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.desbloquearPagina();
            return;
        }
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }
            doc.nombreArchivo = event.files[0].name;
            doc.archivoAdjuntoBase64 = result.toString();
            doc.archivoAdjuntoBase64 = doc.archivoAdjuntoBase64.split(',')[1];
            var temp = [...this.mail.listaCorreoAdjunto];
            temp.push(doc);
            this.mail.listaCorreoAdjunto = temp;
            this.desbloquearPagina();
        };
    }

    enviarMotivoDetallado() {
        var regexp = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

        if (this.mail.listaCorreoDestino.length == 0) {
            this.mostrarMensajeAdvertencia('Ingrese Destinatarios');
            return;
        }
        var valida = true;
        var unit = true;

        this.mail.listaCorreoDestino.forEach(
            row => {
                unit = regexp.test(row.correoDestino);
                if (!unit) {
                    valida = false;
                }
            }
        );

        if (!valida) {
            this.mostrarMensajeAdvertencia("Colocar correos válidos");
            return;
        }

        if (this.estaVacio(this.mail.asunto)) {
            this.mostrarMensajeAdvertencia('Ingrese el asunto');
            return;
        }

        if (this.estaVacio(this.mail.cuerpoCorreoBase64)) {
            this.mostrarMensajeAdvertencia('Ingrese el mensaje');
            return;
        }

        this.aceptarMotivo('S');
    }

    mostrarMensajesWorkflow(mensajes: DominioMensajeUsuario[]) {
        this.validaciones = mensajes;
        this.verValidaciones = true;
    }

    adicional(subaccion: string, nombreBoton: string) {
        this.descripcionAccionEjecutandose = nombreBoton;
        if (this.componenteAprobar != null && this.componenteAprobar != undefined) {
            let msjContr = new MensajeController(null, 'WORKFLOW', subaccion);
            msjContr.parametros = subaccion;
            msjContr.resultado = this.adjuntos.filter(x => x.secuencia != null);
            this.componenteAprobar.coreMensaje(msjContr);
        }
    }

    verValidaciones: boolean = false;
    validaciones: DominioMensajeUsuario[] = [];
    tituloDocumentos: string = "";
    flagVerBotones: string = 'S';
    adjuntos: DtoFlujoAdjunto[] = [];
    adjuntosDlgObservaciones: DtoFlujoAdjunto[] = [];
    tipoDocs: SelectItem[] = [];
    tipoDocsRaw: DtoTabla[] = [];
    tipoDocsNivelRaw: DtoTabla[] = [];
    tipoDocsNivel: SelectItem[] = [];
    verModalTipoDocumento = false;
    tipoDocumento: string;
    docsSeleccionados: DtoFlujoAdjunto[] = [];
    idFirma: number;
    cantidadConsultas: number = 0;
    intervaloConsulta: number = 5000;
    minutosEspera: number = 4 * 60000;
    yaConsultado = true;
    firmaBase64: string;



    cargarFolio(event: any) {
        this.verModalTipoDocumento = false;
        this.block.emit();
        var empDoc = new DtoFlujoAdjunto();
        //empDoc.secuencia = this.generarSecuencia();
        empDoc.transaccion = this.llaveDocs.transaccion;
        empDoc.transaccionUUID = this.transaccionUUID;
        empDoc.tCompania = this.llaveDocs.tCompania;
        empDoc.tProceso = this.llaveDocs.tProceso;
        empDoc.tReferencia = this.llaveDocs.tReferencia;
        var files = event.files;
        if (files.length != 1) {
            this.unBlock.emit();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.unBlock.emit();
            return null;
        }
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }
            empDoc.archivonombre = event.files[0].name;
            empDoc.archivostring = result.toString();
            empDoc.firmaImagen = "N";
            empDoc.firmaElectronica = "N";
            empDoc.cantidadfirmas = 0;
            empDoc.tipodocumento = this.tipoDocumento;
            empDoc.flagVer = 'N';
            empDoc.puedeEditar = 'S';
            var tmp = [...this.adjuntos];
            this.wfTransaccionServicio.registrarAdjunto(empDoc).then(
                x => {
                    if (x != null) {
                        empDoc.secuencia = x.secuencia;
                        empDoc.fecha = x.fecha;
                        empDoc.usuario = x.usuario;
                        tmp.push(empDoc);
                        this.adjuntos = tmp;
                    }
                    else {
                        this.mostrarMensajeAdvertencia("Error al adjuntar archivos");
                    }
                    this.unBlock.emit();
                }
            );
        };
    }

    /*generarSecuencia() {
        if (this.adjuntos.length == 0) {
            return 1;
        }
        var max = this.adjuntos[0].secuencia;
        this.adjuntos.forEach(
            x => {
                if (x.secuencia > max) {
                    max = x.secuencia;
                }
            }
        );
        return max + 1;
    }*/

    cargarFolioPre() {
        this.tipoDocumento = null;
        this.verModalTipoDocumento = true;
    }

    enviarDocumentoFirmaElectronica() {
        //this.docsSeleccionados = this.docsSeleccionados.filter(x => x.flagVer != 'S');
        if (this.docsSeleccionados.length == 0) {
            this.mostrarMensajeAdvertencia("Seleccionar un archivo");
            return;
        }
        this.block.emit();
        var docsPK: DtoFlujoAdjunto[] = [];
        this.docsSeleccionados.forEach(
            x => {
                var archivo = new DtoFlujoAdjunto();
                archivo.transaccion = x.transaccion;
                archivo.secuencia = x.secuencia;
                archivo.archivonombre = x.archivonombre;
                archivo.cantidadfirmas = x.cantidadfirmas;
                docsPK.push(archivo);
            }
        );
        this.wfTransaccionServicio.firmar(docsPK).then(
            x => {
                if (x != null) {
                    const a = document.createElement('a');
                    a.href = x.descripcion;
                    a.setAttribute('download', "inicio.jnlp");
                    const b = document.createEvent('MouseEvents');
                    b.initEvent('click', false, true);
                    a.dispatchEvent(b);
                    this.idFirma = parseInt(x.codigo);
                    this.cantidadConsultas = 1;
                    this.yaConsultado = false;
                    setTimeout(() => { this.consultarFinFirma(); }, this.intervaloConsulta);
                }
                else {
                    this.mostrarMensajeError("");
                    this.unBlock.emit();
                }
            }
        );
    }

    consultarFinFirma() {
        if (this.cantidadConsultas * this.intervaloConsulta > this.minutosEspera) {
            this.desbloquearPagina();
            this.mostrarMensajeAdvertencia("El proceso ha tardado en responder");
            return;
        }
        this.wfTransaccionServicio.consultarFinFirma(this.idFirma, this.transaccionUUID).then(
            x => {
                if (x.length == 0 && !this.yaConsultado) {
                    this.cantidadConsultas = this.cantidadConsultas + 1;
                    setTimeout(() => { this.consultarFinFirma(); }, this.intervaloConsulta);
                }
                else {
                    this.yaConsultado = true;
                    var res = x as DtoFlujoAdjunto[];
                    this.mostrarMensajeExito("Se han actualizado los documentos");
                    var temp = [...this.adjuntos];
                    temp.forEach(original => {
                        var fueEnviado = this.docsSeleccionados.filter(x => x.secuencia == original.secuencia).length > 0 ? true : false;

                        if (fueEnviado) {
                            var docFirmado = res.find(x => x.secuencia == original.secuencia);
                            var index = this.adjuntos.indexOf(original);
                            temp[index].firmaElectronica = 'S';
                            temp[index].archivostring = docFirmado.archivostring;
                            temp[index].cantidadfirmas = docFirmado.cantidadfirmas;
                            temp[index].fecha = docFirmado.fecha;
                        }
                    })
                    this.docsSeleccionados = [];
                    this.adjuntos = temp;
                    this.unBlock.emit();
                }
            }
        );
    }

    enviarDocumentoFirmaImagen() {
        //this.docsSeleccionados = this.docsSeleccionados.filter(x => x.flagVer != 'S');
        if (this.docsSeleccionados.length != 1) {
            this.mostrarMensajeAdvertencia("Seleccionar un archivo");
            return;
        }
        if (this.docsSeleccionados[0].archivostring == null || this.docsSeleccionados[0].archivostring == undefined) {
            this.block.emit();
            const doc = this.adjuntos.find(x => x.secuencia == this.docsSeleccionados[0].secuencia);
            doc.transaccionUUID = this.transaccionUUID;
            this.wfTransaccionServicio.verAdjunto(doc).then(
                yy => {
                    this.unBlock.emit();
                    if (yy.archivostring == null) {
                        this.mostrarMensajeAdvertencia("No se encontró el archivo");
                        return;
                    }
                    var temp = [...this.adjuntos];
                    doc.archivostring = yy.archivostring;
                    this.adjuntos = temp;
                    this.firmaComponent.iniciarComponente(yy.archivostring, this.firmaBase64);
                }
            );
        }
        else {
            this.firmaComponent.iniciarComponente(this.docsSeleccionados[0].archivostring, this.firmaBase64);
        }

    }

    cargarFirmaImagen() {
        this.block.emit();
        var dtoFirma = new DtoFlujoAdjunto();
        dtoFirma.transaccion = this.docsSeleccionados[0].transaccion;
        dtoFirma.secuencia = this.docsSeleccionados[0].secuencia;

        dtoFirma.x = this.firmaComponent.x;
        dtoFirma.y = this.firmaComponent.y;
        dtoFirma.w = this.firmaComponent.w;
        dtoFirma.h = this.firmaComponent.h;

        var canvas = this.firmaComponent.canvasREN.canvas.nativeElement;
        const base64 = canvas.toDataURL("image/png");
        dtoFirma.base64Image = base64;

        setTimeout(() => { this.cargarFirmaImagen2(dtoFirma); }, 700);
    }

    cargarFirmaImagen2(dto: DtoFlujoAdjunto) {
        this.wfTransaccionServicio.escribirImagenPDF(dto).then(
            x => {
                this.firmaComponent.verSelector = false;
                var temp = [...this.adjuntos];
                var posicion = this.adjuntos.indexOf(this.docsSeleccionados[0]);
                temp[posicion].archivostring = x.descripcion;
                temp[posicion].cantidadfirmas = x.id;
                temp[posicion].fecha = new Date();
                temp[posicion].firmaImagen = 'S';
                temp[posicion].usuario = x.estadoId;
                this.adjuntos = temp;
                this.mostrarMensajeExito("Se ha actualizado el documento");
                this.unBlock.emit();
            }
        );
    }

    bloquearPaginaPadre() {
        this.block.emit();
    }

    desbloquearPaginaPadre() {
        this.unBlock.emit();
    }

    eliminarDocumento(secuencia: number, doc: DtoFlujoAdjunto) {
        this.block.emit();
        // let lst = [...this.adjuntos];
        // lst = lst.filter(x => x.secuencia != secuencia);
        // this.adjuntos = lst;

        var pk = new DtoFlujoAdjunto();
        pk.transaccionUUID = this.transaccionUUID;
        pk.secuencia = secuencia;

        this.wfTransaccionServicio.eliminarAdjunto(pk).then(x => {
            this.unBlock.emit();
            doc.secuencia = null;
            doc.archivonombre = '';
            doc.puedeEditar = 'N';
        });

    }

    descargarDocumento(secuencia: number) {
        if (secuencia == null || secuencia == undefined) {
            this.mostrarMensajeAdvertencia("El adjunto está pendiente");
            return;
        }
        const doc = this.adjuntos.find(x => x.secuencia == secuencia);
        if (doc === null) {
            return;
        }
        if (doc.archivostring == null || doc.archivostring == undefined) {
            this.block.emit();
            doc.transaccionUUID = this.transaccionUUID;
            this.wfTransaccionServicio.verAdjunto(doc).then(
                yy => {
                    this.unBlock.emit();
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
        else {
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
        }

    }

    cancelarTipoDoc() {
        this.verModalTipoDocumento = false;
    }

    documentoEditando: number;

    editarDocumento(secuencia: number) {
        this.documentoEditando = secuencia;
    }

    cargarFolioActualizarDocumento(event: any) {
        this.verModalTipoDocumento = false;
        this.block.emit();

        var empDoc = new DtoFlujoAdjunto();
        empDoc.secuencia = this.documentoEditando;
        empDoc.transaccionUUID = this.transaccionUUID;
        empDoc.transaccion = this.llaveDocs.transaccion;
        empDoc.tCompania = this.llaveDocs.tCompania;
        empDoc.tProceso = this.llaveDocs.tProceso;
        empDoc.tReferencia = this.llaveDocs.tReferencia;

        var files = event.files;

        if (files.length != 1) {
            this.unBlock.emit();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.unBlock.emit();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }
            empDoc.archivonombre = event.files[0].name;
            empDoc.archivostring = result.toString();
            empDoc.tipodocumento = this.tipoDocumento;

            var tmp = [...this.adjuntos];

            this.wfTransaccionServicio.actualizarAdjunto(empDoc).then(
                x => {
                    if (x != null) {
                        var docActualizar = tmp.find(doc => doc.secuencia == this.documentoEditando);
                        docActualizar.firmaElectronica = 'N';
                        docActualizar.firmaImagen = "N";
                        docActualizar.cantidadfirmas = 0;
                        docActualizar.fecha = x.fecha;
                        docActualizar.usuario = x.usuario;
                        docActualizar.archivonombre = empDoc.archivonombre;
                        docActualizar.archivostring = null;
                        this.adjuntos = tmp;
                    }
                    else {
                        this.mostrarMensajeAdvertencia("Error al adjuntar archivos");
                    }
                    this.unBlock.emit();
                }
            );
        };
    }

    verPlantilla(nombrePlantilla) {
        this.block.emit();
        var dtoFlujoAdjunto = new DtoFlujoAdjunto();
        dtoFlujoAdjunto.transaccionUUID = this.transaccionUUID;
        dtoFlujoAdjunto.archivonombre = nombrePlantilla;
        this.wfTransaccionServicio.verPlantilla(dtoFlujoAdjunto).then(
            yy => {
                this.unBlock.emit();
                if (yy.archivostring == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                var temp = [...this.adjuntos];
                const a = document.createElement('a');
                a.href = 'data:application/octet-stream;base64,' + yy.archivostring;
                a.setAttribute('download', this.reemplazarRuta(yy.archivonombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
                this.adjuntos = temp;
            }
        );
    }

    subirDocumento(tipodocumento, grupo) {
        this.tipoDocumento = tipodocumento;
        this.grupo = grupo;
    }

    grupo: string;

    cargarFolioSubida(event: any) {

        var archivoEncontrado: boolean = false;

        this.adjuntos.forEach(res => {

            console.log('"' + res.archivonombre + '"', '"' + event.files[0].name + '"')
            if (res.archivonombre == event.files[0].name) {
                archivoEncontrado = true;
            }
        });

        if (archivoEncontrado) {
            this.mostrarMensajeError("El archivo ya existe en el listado.")
            return;
        }


        this.block.emit();

        var empDoc = new DtoFlujoAdjunto();
        //empDoc.secuencia = this.generarSecuencia();
        empDoc.transaccionUUID = this.transaccionUUID;
        empDoc.transaccion = this.llaveDocs.transaccion;
        empDoc.tCompania = this.llaveDocs.tCompania;
        empDoc.tProceso = this.llaveDocs.tProceso;
        empDoc.tReferencia = this.llaveDocs.tReferencia;

        var files = event.files;

        if (files.length != 1) {
            this.unBlock.emit();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.unBlock.emit();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }
            empDoc.archivonombre = event.files[0].name;
            empDoc.archivostring = result.toString();
            empDoc.firmaImagen = "N";
            empDoc.firmaElectronica = "N";
            empDoc.cantidadfirmas = 0;
            empDoc.tipodocumento = this.tipoDocumento;
            empDoc.flagVer = 'N';
            empDoc.puedeEditar = 'S';


            this.wfTransaccionServicio.registrarAdjunto(empDoc).then(
                x => {
                    if (x != null) {
                        var tmp = [...this.adjuntos];
                        empDoc.fecha = x.fecha;
                        empDoc.usuario = x.usuario;
                        empDoc.grupo = this.grupo;
                        empDoc.secuencia = x.secuencia;
                        //tmp.push(empDoc);

                        //reemplazar el registro
                        var iReem = tmp.indexOf(tmp.filter(y => y.secuencia == null && y.tipodocumento == this.tipoDocumento)[0]);
                        tmp[iReem] = empDoc;
                        this.adjuntos = tmp;
                    }
                    else {
                        this.mostrarMensajeAdvertencia("Error al adjuntar archivos");
                    }
                    this.unBlock.emit();
                }
            );
        };
    }

    tieneLink(tipo: string) {
        var tipoDto = this.tipoDocsRaw.find(x => x.codigo.trim() == tipo);
        if (tipoDto) {
            if (!this.estaVacio(tipoDto.estadoId)) {
                return true;
            }
            return false;
        }
        return false;
    }

    irLink(tipo: string) {
        var tipoDto = this.tipoDocsRaw.find(x => x.codigo.trim() == tipo);
        if (tipoDto) {
            if (!this.estaVacio(tipoDto.estadoId)) {
                window.open(tipoDto.estadoId, '_blank').focus();
            }
        }
    }

    salir() {
        this.cargarDataEvent.emit()
    }
}
