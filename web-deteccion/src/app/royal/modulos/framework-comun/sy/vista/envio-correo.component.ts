import { DominioAdjunto } from '@framework/modelo/generico/DominioAdjunto';
import { DtoComunSyDocumentoanexos } from '@framework-comun/adjuntos/adjuntos-simple/dominio/DtoComunSyDocumentoanexos';
import { TituloMantenimientoComponent } from './../../../../framework/angular/controles/titulos/titulo-mantenimiento.component';
import { DominioMensajeUsuario } from './../../../../framework/modelo/generico/DominioMensajeUsuario';
import { EmailTransaccion } from 'src/app/royal/framework/modelo/correo/EmailTransaccion';
import { EmailDestino } from './../../../../framework/modelo/correo/EmailDestino';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { SelectItem, MessageService } from 'primeng/api';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SyCorreoComunServicio } from '../servicio/sycorreo-comun.service';
import { DtoComunEmailTest } from '../dominio/dto/DtoComunEmailTest';
import { EmailConfiguracion } from 'src/app/royal/framework/modelo/correo/EmailConfiguracion';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-enviocorreo',
    templateUrl: './envio-correo.component.html'
})
export class EnvioCorreoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private envioCorreoServicio: SyCorreoComunServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;

    verSelector: Boolean = false;
    lstCompania: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];
    tipo: string;
    emailConfig: EmailConfiguracion;
    destino: string;
    asunto: string;
    mensaje: string;

    dtoEnviar: EmailTransaccion;

    dtoEnviarPrueba: DtoComunEmailTest = new DtoComunEmailTest();

    errores: string;

    archivo: string = '';
    archivostring: string = '';
    flagCredenciales: boolean = false;


    ngOnInit() {
        this.dtoEnviar = new EmailTransaccion();
        this.emailConfig = new EmailConfiguracion();
        this.errores = '';

        this.accion = null;
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

    }


    obtenerConfiguracion() {
        this.errores = '';
        this.emailConfig = new EmailConfiguracion();
        this.bloquearPagina();
        this.envioCorreoServicio.obtenerConfiguracion().then(resp => {
            this.emailConfig = resp;
            this.desbloquearPagina();
        });
    }


    enviar() {
        if (this.emailConfig.flgCorreoPrueba == 'S') {

        }
        else {
            if (this.estaVacio(this.destino)) {
                this.mostrarMensajeAdvertencia("Ingrese el Destino a enviar");
                return;
            }
        }
        if (this.estaVacio(this.asunto)) {
            this.mostrarMensajeAdvertencia("Ingrese el Asunto a enviar");
            return;
        }

        if (this.estaVacio(this.mensaje)) {
            this.mostrarMensajeAdvertencia("Ingrese su mensaje");
            return;
        }

        var obs = `
      <meta http-equiv="X-UA-Compatible" content="IE=edge"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Cache-Control" content="no-cache"><title>Spring | Notificación</title>
      <table>
          <tbody>
              <tr><td><p>Envio de Correo Pruebas</p></td></tr>              
              <tr><td style="text-decoration:underline; font-weight: bolder "><p><br></p><p>Datos</p></td></tr>
          </tbody>
      </table>
      <table>
          <tbody>                                  
              <tr colspan="2"><td style="width:120px">Mensaje:</td><td>`+ this.mensaje + `</td></tr>
      </tbody>
      </table>        
      `;

        this.bloquearPagina();

        var email = new EmailDestino();
        email.correoDestino = this.destino;
        this.dtoEnviar.listaCorreoDestino.push(email);
        this.dtoEnviar.asunto = this.asunto;
        //TODO

        this.dtoEnviar.cuerpoCorreoBase64 = btoa(obs);

        console.log(this.emailConfig);
        console.log(this.dtoEnviar);

        this.errores = '';
        this.emailConfig.emailRemitente = this.emailConfig.emailCuenta;
        this.emailConfig.emailFlgCredenciales = this.booleanAFlag(this.flagCredenciales);

        this.dtoEnviarPrueba.config = this.emailConfig;
        this.dtoEnviarPrueba.correo = this.dtoEnviar;

        this.envioCorreoServicio.enviarCorreoPrueba(this.dtoEnviarPrueba).then(
            x => {
                this.desbloquearPagina();
                if (x.transaccionListaMensajes.length > 0) {
                    x.transaccionListaMensajes.forEach(
                        error => {
                            this.errores = this.errores + ' Message:' + error.mensaje + '\n';
                        }
                    );
                } else {
                    this.errores = 'Enviado con éxito';
                }

                this.dtoEnviarPrueba.correo.listaCorreoDestino = [];

            }
        );

    }


    enviarAlertas() {
        if (this.emailConfig.flgCorreoPrueba == 'S') {

        }
        else {
            if (this.estaVacio(this.destino)) {
                this.mostrarMensajeAdvertencia("Ingrese el Destino a enviar");
                return;
            }
        }
        if (this.estaVacio(this.asunto)) {
            this.mostrarMensajeAdvertencia("Ingrese el Asunto a enviar");
            return;
        }

        if (this.estaVacio(this.mensaje)) {
            this.mostrarMensajeAdvertencia("Ingrese su mensaje");
            return;
        }

        var obs = `
        <meta http-equiv="X-UA-Compatible" content="IE=edge"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Cache-Control" content="no-cache"><title>Spring | Notificación</title>
      <table>
          <tbody>
              <tr><td><p>Envio de Correo Pruebas</p></td></tr>              
              <tr><td style="text-decoration:underline; font-weight: bolder "><p><br></p><p>Datos</p></td></tr>
          </tbody>
      </table>
      <table>
          <tbody>                                  
              <tr colspan="2"><td style="width:120px">Mensaje:</td><td>`+ this.mensaje + `</td></tr>
      </tbody>
      </table>        
      `;

        this.bloquearPagina();

        var email = new EmailDestino();
        email.correoDestino = this.destino;
        this.dtoEnviar.listaCorreoDestino.push(email);
        this.dtoEnviar.asunto = this.asunto;
        //TODO

        var base64 = this.b64EncodeUnicode(obs);

        this.dtoEnviar.cuerpoCorreoBase64 = base64;

        console.log(this.emailConfig);
        console.log(this.dtoEnviar);

        this.errores = '';
        
        if(!this.estaVacio(this.archivostring)){
            this.dtoEnviar.listaCorreoAdjunto = [];
            var adj = new DominioAdjunto();
            adj.archivoAdjuntoBase64 = this.archivostring;
            adj.nombreArchivo = this.archivo;
            this.dtoEnviar.listaCorreoAdjunto.push(adj);
        
        }

        this.dtoEnviarPrueba.config = this.emailConfig;
        this.dtoEnviarPrueba.correo = this.dtoEnviar;  

        this.envioCorreoServicio.enviarCorreoPruebaAlertas(this.dtoEnviarPrueba).then(
            x => {
                this.desbloquearPagina();
                if (x.transaccionListaMensajes.length > 0) {
                    x.transaccionListaMensajes.forEach(
                        error => {
                            this.errores = this.errores + ' Message:' + error.mensaje + '\n';
                        }
                    );
                } else {
                    this.errores = 'Enviado con éxito';
                }

                this.dtoEnviarPrueba.correo.listaCorreoDestino = [];

            }
        );

    }    

    coreGuardar() { }
    coreSalir() { }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }




    subirArchivo( fs: any) {
        fs.click();
    }

    cargarArchivo(event: any) {
        console.log('ADJUNTO')
        this.bloquearPagina();
        const files = event.files;
        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        // if (files[0].size > this.maxFileSize) {
        //     this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
        //     this.desbloquearPagina();
        //     return null;
        // }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var archivorepetido: boolean = false;

        if (archivorepetido) {
            return null;
        }

        const reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            const result = reader.result as string;

            this.archivo = files[0].name;
            this.archivostring = result.split('base64,')[1];

            this.desbloquearPagina();
        };
    }


    b64EncodeUnicode(str) {
        return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
            return String.fromCharCode(parseInt(p1, 16))
        }))
    }


}
