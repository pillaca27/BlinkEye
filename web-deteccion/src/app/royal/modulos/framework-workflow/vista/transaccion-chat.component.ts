import { WfTransaccionComunicacion } from './../dominio/dto/WfTransaccionComunicacion';
import { BaseComponent } from './../../../framework/angular/component/BaseComponent';
import { NoAuthorizationInterceptor } from './../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoChat } from '../dominio/dto/DtoChat';
import { WfTransaccionComunicacionServicio } from '../servicio/wftransaccioncomunicacion.service';
import { UIBaseController } from '@framework/angular/interface/UIBaseController';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';

@Component({
    selector: 'transaccion-chat',
    templateUrl: './transaccion-chat.component.html'
})
export class WfTransaccionChatComponent extends PrincipalBaseComponent implements OnInit {

    verSelector = false;
    tituloComponente: string = 'Comunicación del proceso';
    documentosPermitidos: string[] = [];

    verEmisores: boolean = false;
    lstEmisores: SelectItem[] = [];
    emisorId: number;
    nombreProveedor: string;

    tipo: string;
    transaccionId: number;

    lstChat: DtoChat[] = [];

    padre: UIBaseController;

    constructor(
        //private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private wfTransaccionComunicacionServicio: WfTransaccionComunicacionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        //this.maMiscelaneosdetalleComunService.listarTablaPorHeaderActivos('SY', 'EXTADJPER', '999999').then(res => res.forEach(r => { this.documentosPermitidos.push(r.codigo.trim()) }));
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

    }

    maxFileSize: number;

    confirmarLecturaPadre() {
        console.log("confirmarRespuestaPadre");
        if (this.padre != undefined && this.padre != null) {
            console.log("con padre");
            var mensaje = new MensajeController(null, 'CHAT-TRANSACCION', 'RESPUESTA');
            mensaje.resultado = this.transaccionId;
            this.padre.coreMensaje(mensaje);
        }
    }

    iniciarComponente(transaccion: number, tipo: string, padre: UIBaseController, emisorId?: number, emisorNombre?: string) {
        this.padre = padre;
        this.transaccionId = transaccion;
        this.tipo = tipo;
        this.lstChat = [];
        this.mensaje = null;
        this.emisorId = null;
        this.lstEmisores = [];
        this.mensajeSeleccionado = null;

        if (tipo == 'PROV') {
            this.verEmisores = false;
            //aca traer todos los mensajes del emisor actual
            this.lstEmisores.push({ value: emisorId, label: emisorNombre });
            this.emisorId = emisorId;
            this.nombreProveedor = emisorNombre;
            this.buscarConversacion();
        }
        if (tipo == 'EMPL') {
            this.bloquearPagina();
            this.emisorId = JSON.parse(sessionStorage.getItem('usuarioActual')).personaId;
            this.nombreProveedor = emisorNombre;
            //Se oculta ya que siempre sera el mismo el destinatario (proveedor)
            //this.verEmisores = true;
            //aca traer la lista de emisores

            this.wfTransaccionComunicacionServicio.obtenerEmisores(this.transaccionId).then(
                res => {
                    //this.emisorId = null;
                    this.lstEmisores = [];
                    this.lstEmisores.push({ value: null, label: '-- Seleccione --' });
                    //this.emisorId = null;
                    res.forEach(r => {
                        this.lstEmisores.push({ value: r.id, label: r.nombre });
                    });
                    //this.emisorId = null;
                    //this.emisorId = 1;
                    //this.emisorId = null;
                    //si tiene emisores, seleccionar el primero y buscar
                    this.buscarConversacion();
                }
            );
        }
    }

    buscarConversacion() {

        /*if (this.emisorId == null) {
            this.lstChat = [];
            this.mostrarMensajeAdvertencia("Seleccione un emisor");
            this.nombreProveedor = null;
            return;
        }*/
        this.bloquearPagina();
        //this.nombreProveedor = this.lstEmisores.find(x => x.value == this.emisorId).label;
        this.wfTransaccionComunicacionServicio.obtenerConversacionesXEmisor(this.transaccionId, this.emisorId).then(res => {
            this.confirmarLecturaPadre();
            this.desbloquearPagina();
            this.armarChat(res);
            this.verSelector = true;
        });
    }

    armarChat(res: WfTransaccionComunicacion[]) {
        this.lstChat = [];
        //armar el chat ordenar por secuencia asc
        var posPregunta = this.tipo == 'PROV' ? 'I' : 'D';
        var posRespuesta = this.tipo == 'PROV' ? 'D' : 'I';

        var preguntas = res.filter(x => x.comunicacionPadreId == null);
        preguntas.sort((a, b) => (a.pk.comunicacionId > b.pk.comunicacionId) ? 1 : -1);
        preguntas.forEach(
            pr => {
                var pregunta = new DtoChat();
                //Pero si el emisor es el usuario actual ponerlo como respuesta
                if (pr.emisorId == this.emisorId) {
                    pregunta.posicion = posRespuesta;
                    pregunta.nombre = "CajaCusco";
                }
                else {
                    pregunta.posicion = posPregunta;
                    pregunta.nombre = this.nombreProveedor;
                }
                pregunta.secuencia = pr.pk.comunicacionId;
                pregunta.fecha = pr.modificacionFecha;
                pregunta.foto = null;

                pregunta.mensaje = pr.mensaje;
                pregunta.tipo = pr.tipoMensajeid;
                this.lstChat.push(pregunta);

                //agregar sus respuestas
                var respuestas = res.filter(re => re.comunicacionPadreId == pr.pk.comunicacionId);
                respuestas.sort((a, b) => (a.pk.comunicacionId > b.pk.comunicacionId) ? 1 : -1);
                respuestas.forEach(re => {
                    var respuesta = new DtoChat();
                    respuesta.posicion = posRespuesta;
                    respuesta.secuencia = re.pk.comunicacionId;
                    respuesta.fecha = re.modificacionFecha;
                    respuesta.foto = null;
                    respuesta.nombre = "CajaCusco";
                    respuesta.mensaje = re.mensaje;
                    respuesta.tipo = re.tipoMensajeid;
                    this.lstChat.push(respuesta);
                });
            }
        );

        console.log(this.lstChat);
    }

    mensaje: string;
    mensajeSeleccionado: DtoChat;

    dummySeleccionePregunta() {
        this.mostrarMensajeAdvertencia("Seleccione un mensaje para responder");
    }

    enviarPregunta() {
        if (this.tipo == 'EMPL') {
            //Comentamos esto porque ahora desde portal se va a poder enviar mensajes al proveedor D:
            /*if (this.mensajeSeleccionado == null) {
                this.mostrarMensajeAdvertencia("Seleccione un mensaje para responder");
                return;
            }*/
        }
        if (this.estaVacio(this.mensaje)) {
            this.mostrarMensajeAdvertencia("Ingrese su mensaje");
            return;
        }
        var beanPregunta = new WfTransaccionComunicacion();
        beanPregunta.pk.transaccionId = this.transaccionId;
        beanPregunta.mensaje = this.mensaje;
        beanPregunta.tipoMensajeid = "TXT";
        this.bloquearPagina();
        this.wfTransaccionComunicacionServicio.enviarMensaje(beanPregunta).then(res => {
            this.mensaje = null;
            this.buscarConversacion();
        });
    }

    seleccionarParaResponder(mensaje: DtoChat) {
        mensaje.seleccionado = "S";
        this.mensajeSeleccionado = mensaje;
    }
    desSeleccionarParaResponder(mensaje: DtoChat) {
        mensaje.seleccionado = "N";
        this.mensajeSeleccionado = null;
    }

    enviarRespuesta() {
        if (this.estaVacio(this.mensaje)) {
            this.mostrarMensajeAdvertencia("Ingrese su respuesta");
            return;
        }
        this.bloquearPagina();
        var beanRespuesta = new WfTransaccionComunicacion();
        beanRespuesta.pk.transaccionId = this.transaccionId;
        beanRespuesta.mensaje = this.mensaje;
        beanRespuesta.tipoMensajeid = "TXT";
        beanRespuesta.comunicacionPadreId = this.mensajeSeleccionado.secuencia;
        this.wfTransaccionComunicacionServicio.responderMensaje(beanRespuesta).then(res => {
            this.mensajeSeleccionado = null;
            this.mensaje = null;
            this.buscarConversacion();
        });
    }

    cargarAdjunto(event: any) {

        this.bloquearPagina();
        var files = event.files;
        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.desbloquearPagina();
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
            var beanPregunta = new WfTransaccionComunicacion();
            beanPregunta.pk.transaccionId = this.transaccionId;
            beanPregunta.mensaje = event.files[0].name;
            beanPregunta.tipoMensajeid = "ADJ";
            beanPregunta.auxContenido = result.toString();
            if (this.mensajeSeleccionado != null) {
                beanPregunta.comunicacionPadreId = this.mensajeSeleccionado.secuencia;
                this.wfTransaccionComunicacionServicio.responderMensaje(beanPregunta).then(res => {
                    this.mensajeSeleccionado = null;
                    this.mensaje = null;
                    this.buscarConversacion();
                });
            }
            else {
                this.wfTransaccionComunicacionServicio.enviarMensaje(beanPregunta).then(res => {
                    this.mensajeSeleccionado = null;
                    this.mensaje = null;
                    this.buscarConversacion();
                });
            }
        };
    }

    descargarAdjunto(mensaje: DtoChat) {
        this.bloquearPagina();
        var beanAdjunto = new WfTransaccionComunicacion();
        beanAdjunto.pk.transaccionId = this.transaccionId;
        beanAdjunto.pk.comunicacionId = mensaje.secuencia;
        beanAdjunto.mensaje = mensaje.mensaje;
        this.wfTransaccionComunicacionServicio.verAdjunto(beanAdjunto).then(
            yy => {
                this.desbloquearPagina();
                if (yy.descripcion == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                const a = document.createElement('a');
                a.href = 'data:application/octet-stream;base64,' + yy.descripcion;
                a.setAttribute('download', this.reemplazarRuta(yy.nombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
            }
        );
    }
}
