import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { ObjetoBoton } from '@framework/angular/dominio/ObjetoBoton';
import { Component, OnInit, ViewChild, ChangeDetectorRef, ChangeDetectionStrategy, Input } from '@angular/core';
import { ObjetoTitulo } from '@framework/angular/dominio/ObjetoTitulo';
import { AppConfig } from 'src/environments/appconfig';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MessageService } from 'primeng/api';


@Component({
    selector: 'app-botones-mantenimiento',
    templateUrl: 'botones-mantenimiento.component.html'    
})

export class BotonesMantenimientoComponent extends PrincipalBaseComponent implements OnInit {
        
    @Input() objTitulo = new ObjetoTitulo();    
    @Input() tipo = '';
    @Input() listabotones: any[] = [];

    constructor(
        private cdref: ChangeDetectorRef, 
        private config: AppConfig,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        ) {super(noAuthorizationInterceptor, messageService,servicioComun); }    
    

    ngOnInit() {        
        console.log(this.objTitulo.codigoAccion)
        
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }  
    
    nuevo() {
        this.objTitulo.componente.coreNuevo();
    }

    guardar(){
        this.objTitulo.componente.coreGuardar();
    }

    salir(){
        this.objTitulo.componente.coreSalir();
    }    

    varios(accion: any){
        this.objTitulo.componente.coreAccion(accion);
    }

    buscar(){
        this.objTitulo.componente.coreBuscar();
    }



    accionRealizar(boton: ObjetoBoton){             

        if(boton.accion == ConstanteAngular.ACCION_SOLICITADA_GUARDAR){
            this.guardar();
        }

        if(boton.accion == ConstanteAngular.ACCION_SOLICITADA_SALIR){
            this.salir()
        }

        if(boton.accion == ConstanteAngular.ACCION_SOLICITADA_NUEVO){
            this.nuevo()
        }

        if(boton.accion == ConstanteAngular.ACCION_SOLICITADA_BUSCAR){
            this.buscar()
        }

        else{
            this.varios(boton.accion)
        }


    }


}
