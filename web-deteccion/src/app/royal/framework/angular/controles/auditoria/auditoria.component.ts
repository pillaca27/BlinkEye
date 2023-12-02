import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { Component, OnInit, ViewChild, ChangeDetectorRef, Input, EventEmitter } from '@angular/core';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
    selector: 'app-auditoria',
    templateUrl: 'auditoria.component.html'
})

export class AuditoriaComponent extends PrincipalBaseComponent implements OnInit {        

    @Input() tipoAuditoria = '';
    @Input() creacionFecha = new Date();
    @Input() creacionUsuario = '';
    @Input() modificacionUsuario = '';    
    @Input() modificacionFecha = new Date();

    
    @Input() aprobacionUsuario = '';
    @Input() aprobacionFecha = new Date();

    constructor(
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        private cdref: ChangeDetectorRef
        
    ) {super(noAuthorizationInterceptor, messageService,servicioComun); }    

    ngOnInit() {                
        console.log('iniciarAuditoria');
       this.modificacionFecha=new Date();
    }

    public ngAfterViewInit(): void {
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }    


}
