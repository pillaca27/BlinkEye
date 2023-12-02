import { ObjetoBoton } from '@framework/angular/dominio/ObjetoBoton';
import { Component, OnInit, ViewChild, ChangeDetectorRef, ChangeDetectionStrategy, Input } from '@angular/core';
import { ObjetoTitulo } from '@framework/angular/dominio/ObjetoTitulo';
import { AppConfig } from 'src/environments/appconfig';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MessageService } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { TipoExportarSelectorComponent } from '../selectores/tipoExportar/vista/tipoExportar.component';


@Component({
    selector: 'app-botones-listado',
    templateUrl: 'botones-listado.component.html',
    styles:[

        `
        .btn_generar_orden{
            width: 160px;
        }
        .btn_modificar_orden{
            width: 180px;
        }
        .btn_cotix{
            width: 203px;
            margin-left: -22px;
        }
        .btn_cotix2{
            width: 203px;
            margin-left: -12px;
        }
        @media screen and (max-width: 600px) {
            .btn_generar_orden, .btn_modificar_orden {
                width: 100%;
            }
            .btn_cotix{
                width: 100%;
                margin-left: 0px;
            }
    }
        `
    ]
})

export class BotonesListadoComponent extends PrincipalBaseComponent implements OnInit {
    @ViewChild(TipoExportarSelectorComponent, { static: false }) tipoExportarSelectorComponent: TipoExportarSelectorComponent;
    @Input() objTitulo = new ObjetoTitulo();    
    @Input() tipo = '';
    tipoExportar: string;

    constructor(
        private cdref: ChangeDetectorRef, 
        private config: AppConfig,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        ) {super(noAuthorizationInterceptor, messageService,servicioComun); } 
    

    ngOnInit() {
        
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

    exportar(){
        this.objTitulo.componente.coreExportar(this.tipoExportar);
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

    exportarDatos() {
            this.tipoExportarSelectorComponent.iniciarComponenteWh();        
    }

    seleccionarExportarTipo(tipo: any) {
        this.tipoExportar = tipo;
        this.exportar();
    }
}
