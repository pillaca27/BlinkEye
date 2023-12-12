import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ObjetoBoton } from './royal/framework/angular/dominio/ObjetoBoton';
import { SeguridadMenuItem } from './royal/framework/modelo/seguridad/SeguridadMenuItem';
import { DialogAyuda } from './royal/framework/angular/controles/selectores/paginaayuda/vista/dialog-ayuda';
import { TipoExportarSelectorComponent } from './royal/framework/angular/controles/selectores/tipoExportar/vista/tipoExportar.component';
import { AppConfig } from './../environments/appconfig';
import { ObjetoTitulo } from './royal/framework/angular/dominio/ObjetoTitulo';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { BreadcrumbService } from './app.breadcrumb.service';
import { Subscription } from 'rxjs';
import { MenuItem, MessageService } from 'primeng/api';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
import { JWT_RESPONSE_HEADER } from '@framework-seguridad/constantes';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnDestroy, OnInit {

    @ViewChild(TipoExportarSelectorComponent, { static: false }) tipoExportarSelectorComponent: TipoExportarSelectorComponent;
    @ViewChild(DialogAyuda, { static: false }) dialogayuda: DialogAyuda;

    subscription: Subscription;
    subscription2: Subscription;
    subscription3: Subscription;
    item: ObjetoTitulo = new ObjetoTitulo();
    nombre: string = "Cargando...";

    foto: string = "";
    nombreUsuario: string = "";
    nombreCompania: string = "";

    flgVerFiltro: boolean;
    valorFiltro: string;
    tipoExportar: string;
    urlAyuda: string;
    verModalAyuda: boolean = false;
    url: string;
    verIconoAyuda: boolean = false;

    verTiposExportar: Boolean = false;
    listaTipoExportar: any[] = [];
    tipo: string;
    bloquearPantalla: Boolean = false;

    items: MenuItem[] = [];
    itemsMovil: MenuItem[] = [];
    home: MenuItem;

    sucursalNombre: string = '';
    departamentoNombre: string = '';

    menu: boolean= false;

    lstAplicaciones: any[] = [];

    constructor(
        private config: AppConfig,
        public breadcrumbService: ServicioComunService, 
        public app: AppMainComponent, 
        public message: MessageService,
        public usuarioService: UsuarioService) {

        this.subscription = breadcrumbService.itemsHandler.subscribe(response => {
            this.item = response;
        });

        this.subscription = breadcrumbService.bloqueoHandler.subscribe(response => {
            this.bloquearPantalla = response;            
        });

        this.subscription3 = breadcrumbService.breadHandler.subscribe(response => {
            this.items = response;                
        });

        this.subscription3 = breadcrumbService.breadHandlerMovil.subscribe(response => {            
            this.itemsMovil = response;        
        });        

        this.subscription3 = breadcrumbService.breadIconHandler.subscribe(response => {
            this.home = response;            
        });

    }

    ngOnInit() {
        this.verIconoAyuda = false;
        console.log(this.item.menuSeguridad.urlAyuda);
        if (this.item.menuSeguridad.urlAyuda != null && this.item.menuSeguridad.urlAyuda != undefined) {
            if (this.item.menuSeguridad.urlAyuda.trim() != "") {
                this.verIconoAyuda=true;
            }            
        }

        debugger;
        this.item.accion = '';
        this.item.menuSeguridad.label = '';

        this.home = {icon: 'pi pi-home'};

        debugger;
        this.usuarioService.obtenerUsuarioActual().then(
            response => {
                
                this.nombre = response.personaNombreCompleto;
                this.nombreCompania = response.companiaNombre;
                this.nombreUsuario = response.personaNombreCompleto;
                // this.sucursalNombre = response.sucursalNombre;
                // this.departamentoNombre = response.departamentoNombre;

                // Si hay una foto, úsala. De lo contrario, usa una foto predeterminada.
                if(response.personaFotoUrl !== 'assets/layout/images/user.png')
                {
                    this.foto = 'data:image/jpeg;base64,' + response.personaFotoUrl;
                }

                //sino hay foto
                sessionStorage.setItem('usuarioActual', JSON.stringify(response));
            }
        );   
        
        // this.usuarioService.aplicaciones().then(resp => {
        //     this.lstAplicaciones = resp;
        //     console.log(this.lstAplicaciones)
        //     if(this.esListaVacia(this.lstAplicaciones)){
        //         this.menu = false;
        //     }else{
        //         this.menu = true;
        //     }
            
        //   });
       

        
    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    accion(event: any){
        if(event.accion == ConstanteAngular.BOTONEXPORTAR){
            this.iniciarComponenteExportar();
        }else if(event.accion == ConstanteAngular.BOTONEXPORTARCORTO){
            this.iniciarComponenteExportarCorto();
        }else{
            //TO DO, validar acciones
            // this.item.componente.coreNuevo();
        }
    }

    accionOtros(event: ObjetoBoton){
        if(event.accion == ConstanteAngular.BOTONEXPORTAR){
            this.iniciarComponenteExportar();
        }else if(event.accion == ConstanteAngular.BOTONEXPORTARCORTO){
            this.iniciarComponenteExportarCorto();
        }else{
            //TO DO, validar acciones
            // this.item.componente.coreNuevo();
        }
        
    }


    // PAGINA DE AYUDA
    ayuda() {
        var url = this.obtenerPaginaAyuda(this.item.menuSeguridad);
        if (url != undefined && url != null) {
            this.iniciarComponenteAyuda(true, url);
        }
    }

    obtenerPaginaAyuda(menu: SeguridadMenuItem): string {
        this.urlAyuda = this.config.getEnv('manuales') + menu.urlAyuda;
        console.log(this.urlAyuda);
        return this.urlAyuda;
    }

    iniciarComponenteAyuda(ver: boolean, ruta: string) {
        console.log('ruta', ruta);
        this.verModalAyuda = ver;
        this.url = ruta;
      }
    // FIN PAGINA DE AYUDA


    //EXPORTAR LISTADOS
    iniciarComponenteExportar() {
        this.bloquearPantalla = true;
        this.listaTipoExportar = [];        
            this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fa fa-file-excel-o' });
            this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fa fa-file-pdf-o' });
            this.listaTipoExportar.push({ nombre: 'XML', codigo: 'XML', icon: 'fa fa-file-text-o' });                    
        
        this.verTiposExportar = true;                
        this.bloquearPantalla = false;
    }    
 
    iniciarComponenteExportarCorto() {        
        this.bloquearPantalla = true;
        this.listaTipoExportar = [];
        this.listaTipoExportar.push({ nombre: 'Excel', codigo: 'XLS', icon: 'fa fa-file-excel-o' });
        this.listaTipoExportar.push({ nombre: 'PDF', codigo: 'PDF', icon: 'fa fa-file-pdf-o' });
        this.verTiposExportar = true;                
        this.bloquearPantalla = false;
    } 

    salir() {
        this.bloquearPantalla = false;
        this.verTiposExportar = false;
    }

    exportarSegunTipo(){
        if(this.tipo == '' || this.tipo == undefined || this.tipo == null){
            this.message.add({severity: 'info', summary: 'Debe seleccionar un tipo'});            
            return;
        }        
        this.item.componente.coreExportar(this.tipo);
        this.salir();
    }
    

    exportarTipo(tipo: string){
        this.tipo = tipo;
        this.exportarSegunTipo();
    }
    //FIN EXPORTAR LISTADOS


    coreBuscar(varlo: string){

    }

    irWeb(web) {
        window.location.href = web + "login/" + sessionStorage.getItem(JWT_RESPONSE_HEADER);
      }


      esListaVacia(lista: any): boolean {
        if (lista == null) {
            return true;
        }
        if (lista == undefined) {
            return true;
        }
        if (lista.length == 0) {
            return true;
        }
        return false;
    }

}
