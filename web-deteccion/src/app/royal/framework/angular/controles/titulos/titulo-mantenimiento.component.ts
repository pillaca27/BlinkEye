import { Component, OnInit, ViewChild, ChangeDetectorRef, ChangeDetectionStrategy, Input } from '@angular/core';
import { ObjetoTitulo } from '@framework/angular/dominio/ObjetoTitulo';
import { AppConfig } from 'src/environments/appconfig';

import { TipoExportarSelectorComponent } from '../selectores/tipoExportar/vista/tipoExportar.component';
import { DialogAyuda } from '../selectores/paginaayuda/vista/dialog-ayuda';
import { SeguridadMenuItem } from '@framework/modelo/seguridad/SeguridadMenuItem';

@Component({
    selector: 'app-titulo-mantenimiento',
    templateUrl: 'titulo-mantenimiento.component.html'    
})

export class TituloMantenimientoComponent implements OnInit {
    
    urlAyuda: string;                    
    tipoExportar: string;    
    @Input() objTitulo = new ObjetoTitulo();
    @Input() bloquear = false;
    @Input() tipoExp = '';

    constructor(private cdref: ChangeDetectorRef, private config: AppConfig) { }

    @ViewChild(TipoExportarSelectorComponent, { static: false }) tipoExportarSelectorComponent: TipoExportarSelectorComponent;
    @ViewChild(DialogAyuda, { static: false }) dialogayuda: DialogAyuda;

    ngOnInit() {
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }  
    
    nuevo() {
        this.objTitulo.componente.coreNuevo();
    }

    ayuda() {
        var url = this.obtenerPaginaAyuda(this.objTitulo.menuSeguridad);
        if(url != undefined && url != null){
           this.dialogayuda.iniciarComponente(true, url);
        }
    }
    guardar(){
        this.objTitulo.componente.coreGuardar();
    }

    salir(){
        this.objTitulo.componente.coreSalir();
    }

    exportarDatos() {
        if (this.tipoExp == '2') {
            this.exportarDatosPdfXls();
        } else if (this.tipoExp == '3') {
            this.objTitulo.componente.coreExportar('XLS');
        } else {
            this.tipoExportarSelectorComponent.iniciarComponente();
        }
    }

    exportarDatosPdfXls() {
        this.tipoExportarSelectorComponent.iniciarComponente2();
    }
    
    seleccionarExportarTipo(tipo: any) {
        this.tipoExportar = tipo;
        this.exportar();
    }

    exportar() {
        this.objTitulo.componente.coreExportar(this.tipoExportar);
    }

    obtenerPaginaAyuda(menu: SeguridadMenuItem): string{
        this.urlAyuda = this.config.getEnv('manuales')+ menu.urlAyuda;
        console.log(this.urlAyuda);
        return this.urlAyuda;
    }

}
