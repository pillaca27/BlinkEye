import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
    templateUrl: './dashboard.componente.html',
    styleUrls: ['./dashboard.componente.scss']
})
export class DashboardComponent extends FormularioComponent implements OnInit {

    constructor(
        //Servicio
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        debugger;
        this.formularioOninit = true;
        //this.bloquearPagina();
    }


}
