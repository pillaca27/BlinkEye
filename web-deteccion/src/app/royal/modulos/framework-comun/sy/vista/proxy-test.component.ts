import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { ErrorComunServicio } from './../servicio/error-comun.service';
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
    selector: 'app-proxy-test',
    templateUrl: './proxy-test.component.html'
})
export class ProxyTestComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private errorComunServicio: ErrorComunServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;
            
    destino: string;

    dtoEnviar: EmailTransaccion;

    dtoEnviarPrueba: DtoComunEmailTest = new DtoComunEmailTest();

    errores: string;


    ngOnInit() {                
        this.errores = '';

        this.accion = null;
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

    }




    enviar() {
            this.errores = '';

            if (this.estaVacio(this.destino)) {
                this.mostrarMensajeAdvertencia("Ingrese la url");
                return;
            }

        this.bloquearPagina();
        var dto = new DtoTabla();
        dto.nombre = this.destino;
        this.errorComunServicio.prueballamadaexterna(dto).then(
            x => {
                this.desbloquearPagina();                
                this.errores = x.descripcion;
            }
        );

    }


    coreGuardar() { }
    coreSalir() { }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }

}
