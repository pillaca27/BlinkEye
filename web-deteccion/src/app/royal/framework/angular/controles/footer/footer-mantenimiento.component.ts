import { BaseComponentLista } from '@framework/angular/component/BaseComponentLista';
import { MessageService } from 'primeng/api';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { SeguridadMenuItem } from '@framework/modelo/seguridad/SeguridadMenuItem';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { AuditoriaDto } from '@framework/angular/dominio/AuditoriaDto';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
    selector: 'app-footermantenimiento-listado',
    templateUrl: 'footer-mantenimiento.component.html'
})

export class FooterMantenimientoComponent extends BaseComponentLista implements OnInit {
    tipoComponente: number;
    objMenu: SeguridadMenuItem;
    objComponente: UIMantenimientoController;
    flgVerFiltro: boolean;
    valorFiltro: string;
    tipoExportar: string;
    urlAyuda: string;
    KeyAlfanumerico: RegExp = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\d\-_\s]+$/;
    titulo: string;
    auditoriaDto: AuditoriaDto = new AuditoriaDto();
    puedeEditar: boolean = true;
    tipo0: boolean = false;
    tipo1: boolean = true;
    accion2: string;

    constructor(
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        private cdref: ChangeDetectorRef
    ) {super(noAuthorizationInterceptor, messageService,servicioComun); }    

    ngOnInit() {
        console.log('iniciarComponente');
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    public ngAfterViewInit(): void {
        // this.elementRef.nativeElement.focus();
        setTimeout(() => {
            if(this.accion2 == ConstanteAngular.ACCION_SOLICITADA_NUEVO || this.accion2 == ConstanteAngular.ACCION_SOLICITADA_EDITAR ){
                this.puedeEditar = true;
            }else{
                this.puedeEditar = false;
            }
            this.validarTipo();
        });
    }

    busquedaRapida() {
    }
    buscar() {
    }
    nuevo() {
    }
    exportar() {
    }
    verfiltro() {
    }
    ayuda() {
    }
    guardar(){
        this.objComponente.coreGuardar();
    }

    salir(){
        this.objComponente.coreSalir();
    }

    iniciarComponente(Tipo: number, menuSeguridad: SeguridadMenuItem, objComponent: UIMantenimientoController, accion: string) {
        console.log('iniciarComponente !!!');
        this.tipoComponente = Tipo;
        this.objMenu = menuSeguridad;
        this.objComponente = objComponent;
        this.accion2 = accion;
        
    }    
    
    validarTipo(){
        if(this.tipoComponente == 0){
            this.tipo0 = true;
            this.tipo1 = false;
        }else if(this.tipoComponente == 1){
            this.tipo0 = false;
            this.tipo1 = true;
        }
    }
    

}
