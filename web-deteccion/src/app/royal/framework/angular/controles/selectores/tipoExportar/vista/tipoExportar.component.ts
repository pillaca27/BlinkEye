import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef } from '@angular/core';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';

@Component({
    selector: 'app-tipoExportar-selector',
    templateUrl: './tipoExportar.component.html'
})
export class TipoExportarSelectorComponent extends BaseComponent implements OnInit {

    verTiposExportar: Boolean = false;
    listaTipoExportar: any[] = [];
    tipo: string;
    wh:boolean=false
    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() cargarSeleccionEvent = new EventEmitter();

    constructor(        
        private cdref: ChangeDetectorRef,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,        
        servicioComun: ServicioComunService        
         ) {     
             super(noAuthorizationInterceptor, messageService, servicioComun);          
        }


    ngOnInit() {

    }
    
    cargarEstados() {
        // this.lstEstados = [];
        // this.lstEstados.push({ label: 'Todos', value: '' });
        // this.lstEstados.push({ label: 'Activo', value: 'A' });
        // this.lstEstados.push({ label: 'Inactivo', value: 'I' });
    }

    iniciarComponenteWh() {
        this.wh=true
        this.block.emit();
        this.listaTipoExportar = [];      
            this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fas fa-file-excel' });
            this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fas fa-file-pdf' });
            this.listaTipoExportar.push({ nombre: 'XML', codigo: 'XML', icon: 'fas fa-file-code' });                    
            this.listaTipoExportar.push({ nombre: 'JSON', codigo: 'JSON', icon: 'fas fa-brackets-curly' });  
        this.verTiposExportar = true;                
        this.unBlock.emit();
    }  

    iniciarComponente() {
        this.block.emit();
        this.listaTipoExportar = [];        
            this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fa fa-file-excel-o' });
            this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fa fa-file-pdf-o' });
            this.listaTipoExportar.push({ nombre: 'XML', codigo: 'XML', icon: 'fa fa-file-text-o' });                    
        
        this.verTiposExportar = true;                
        this.unBlock.emit();
    }    

    iniciarComponente2() {
        this.block.emit();
        this.listaTipoExportar = [];        
            this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fa fa-file-excel-o' });
            this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fa fa-file-pdf-o' });
        this.verTiposExportar = true;                
        this.unBlock.emit();
    }

    iniciarComponenteCorto() {        
        this.block.emit();
        this.listaTipoExportar = [];
        this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fa fa-file-excel-o' });
        this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fa fa-file-pdf-o' });
        this.verTiposExportar = true;                
        this.unBlock.emit();
    } 

    salir() {
        this.unBlock.emit();
        this.verTiposExportar = false;
    }

    exportarSegunTipo(){
        if(this.estaVacio(this.tipo)){
            this.mostrarMensajeAdvertencia('Debe seleccionar un tipo');
            return;
        }
        this.block.emit();
        this.cargarSeleccionEvent.emit(this.tipo);
        this.salir();
    }
    

    exportarTipo(tipo: string){
        this.tipo = tipo;
        this.exportarSegunTipo();
    }

}
