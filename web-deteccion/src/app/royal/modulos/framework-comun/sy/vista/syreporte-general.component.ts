import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
import { ServicioComunService } from './../../servicioComun.service';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
    selector: 'app-syreporte',
    templateUrl: './syreporte-general.component.html'
})
export class SyReporteComponent   extends FormularioComponent implements OnInit, UIMantenimientoController {

    verDetalle: Boolean = false;
    constructor(
        private route: ActivatedRoute,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

        this.bloquearPagina();

        // this.formularioIniciar(this.route);        
        // this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);         

        const aplicacioncodigo = this.route.snapshot.params['aplicacionCodigo'];
        const reporteTipo = this.route.snapshot.params['reporteTipo'];

        if (reporteTipo === 'CORRE') {
            this.verDetalle = false;
        } else if (reporteTipo !== 'CORRE') {
            if (aplicacioncodigo) {
                this.verDetalle = true;
            }
        }
    }

    coreGuardar(){

    }
    coreSalir(){

    }

    coreExportar(tipo: string){}
    
    coreMensaje(mensage: MensajeController){}
    coreAccion(accion: string){}

}
