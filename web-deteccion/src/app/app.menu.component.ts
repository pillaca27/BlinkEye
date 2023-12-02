import { Subscription } from 'rxjs';
import { MenuService } from './app.menu.service';
import { AppConfig } from './../environments/appconfig';
import { SeguridadMenuItem } from './royal/framework/modelo/seguridad/SeguridadMenuItem';
import { Component, OnInit } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
AppConfig

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
    styles: [
        `
        :host ::ng-deep .p-panelmenu .p-panelmenu-header>a .p-menuitem-icon {
            margin-right: 0.4rem!important;
        }
        `
    ]
})
export class AppMenuComponent extends BaseComponent implements OnInit {

    logo = "";
    model: any[];
    active = false;

    menuSourceSubscription: Subscription;

    menuResetSubscription: Subscription;

    key: string;

    slimClick = true;

    item: any;

    index: number;

    root: boolean;

    periodo: string;

    fechaInicio: Date = new Date();
    fechaFin: Date = new Date();

    constructor(
        private router: Router,
        public app: AppMainComponent,
        private servicio: UsuarioService,
        private config: AppConfig,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        private menuService: MenuService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngOnInit() {

        this.logo = `assets/imagenes/${this.config.getEnv('recursos-compania')}/logo.png`;


        //const p1 = this.obtenerPeriodo();
        //const p2 = this.obtenerTipoCambio();

        if (sessionStorage.getItem('registrar-postulante') == 'S') {
            return;
        }

        Promise.all([]).then(
            f => {
                this.model = [
                    { label: 'Cargando...', icon: 'fa fa-fw fa-spinner' }];

                this.servicio.menucore().then(
                    menu => {
                        this.model = [];
                        menu.items.forEach(element => {
                            if (element.items) {
                                element.items = this.actualizarItem(element.items);
                                this.model.push(element);
                            }
                            else {
                                this.model.push(this.actualizar1Item(element));
                            }
                        });
                    }
                );

            });
    }

    onMenuClick(event) {
        this.app.onMenuClick(event);
    }

    ejecutarAccion(ruta: string) {
        switch (ruta) {
            case 'CAMBIO_CLAVE':
                this.app.cambiarclave();
                break;
            default:
                break;
        }
    }

    verPantalla(titulo: string, icon: string, ruta: string, item: any) {
        console.log('verPantalla');
        var obj = new SeguridadMenuItem();
        var grupo = '';

        obj.flgAgregar = item.flgAgregar;
        obj.flgBorrar = item.flgBorrar;
        obj.flgModificar = item.flgModificar;
        obj.label = titulo;
        obj.icon = icon;
        obj.urlAyuda = item.urlAyuda;

        obj.auxFechaInicioPerido = this.fechaInicio;
        obj.auxFechaFinPerido = this.fechaFin;

        this.itemClick();
        this.model.forEach(ele => {
            if (ele.items) {
                ele.items.forEach(item => {
                    if (item.label == obj.label) {
                        grupo = ele.label;
                        return;
                    }
                    if (item.items) {
                        item.items.forEach(i => {
                            if (i.label == obj.label) {
                                grupo = ele.label;
                                return;
                            }
                        });
                    }
                });
            }
        });

        obj.grupo = this.estaVacio(grupo) ? "-" : grupo;

        sessionStorage.removeItem(ConstanteAngular.MENUSEGURIDAD);
        sessionStorage.setItem(ConstanteAngular.MENUSEGURIDAD, JSON.stringify(obj));
        ruta = ruta.trim();
        console.log(ruta);
        console.log(this.model);

        sessionStorage.removeItem(ConstanteAngular.FILTROSESION);

        this.router.navigate([ruta]);
    }


    itemClick() {

        if (this.app.isSlim()) {
            this.slimClick = true;
        }


        // navigate with hover in horizontal mode
        if (this.root) {
            this.app.menuHoverActive = !this.app.menuHoverActive;
        }

        // notify other items
        this.menuService.onMenuStateChange(this.key);


        // activate item
        this.active = true;

        if (this.app.isMobile()) {
            this.app.staticMenuMobileActive = false;
        }

        // reset horizontal menu
        if (this.app.isSlim()) {
            this.menuService.reset();
            this.app.menuHoverActive = false;
        }

        this.app.unblockBodyScroll();
    }

    nvl1: any[] = []
    nvl2: any[] = []
    nvl3: any[] = []
    recursividadGrupoConcepto(nodo: any, items: any) {
        let array = Array.isArray(items) ? items : [items]
        this.nvl1 = array.filter(y => y.orden == 1)
        this.nvl2 = array.filter(y => y.orden == 2)
        this.nvl3 = array.filter(y => y.orden == null)

        this.ordenarLstNiveles()

        this.nvl1.forEach(niveles => {
            if (!this.estaVacio(niveles.id)) {
                let linea = niveles.id
                let nivel01 = []
                if (niveles.id.length == 2) {
                    nivel01 = array.filter(y => y.orden == 1)
                    let nivelNodo02 = this.nvl2.filter(x => x.id.substr(0, 2) == linea)

                    nivelNodo02 = this.recursividadGrupoConceptoNiveles(nivelNodo02) //LLENO EL NIVEL 3

                    nivel01.filter(x => x.id == linea).forEach(nivel1 => {
                        nivel1.items = []
                        nivel1.routerLink = this.estaVacio(nivel1.routerLink) ? null : nivel1.routerLink.trim();
                        nodo.items.push({
                            label: nivel1.label, icon: nivel1.icon, items: nivelNodo02

                        });
                    });
                }
            }
        });

        return nodo

    }

    recursividadGrupoConceptoNiveles(nodo: any) { //NIVEL 3 A MAS
        let array = Array.isArray(nodo) ? nodo : [nodo]

        return array.reduce((acc, el) => {

            el.routerLink = this.estaVacio(el.routerLink) ? null : el.routerLink.trim();
            el.items = []
            let nivelNodo03 = this.nvl3.filter(x => !this.estaVacio(x.id)).filter(x => x.id.substr(0, 4) == el.id)

            if (this.estaVacio(el.routerLink)) {
                el.items = nivelNodo03
                el.items.forEach(element => {
                    element.command = event => { this.verPantalla(element.label, element.icon, element.routerLink, element) }
                });
            } else {
                el.items = null
                el.command = event => { this.verPantalla(el.label, el.icon, el.routerLink, el) }
            }

            acc.push(el)

            return acc

        }, [])
    }

    ordenarLstNiveles() {
        this.nvl1.sort(function (a, b) {
            if (a.id > b.id) {
                return 1;
            }
            if (a.id < b.id) {
                return -1;
            }
            // a must be equal to b
            return 0;
        });

        this.nvl2.sort(function (a, b) {
            if (a.id > b.id) {
                return 1;
            }
            if (a.id < b.id) {
                return -1;
            }
            // a must be equal to b
            return 0;
        });

        this.nvl3.sort(function (a, b) {
            if (a.id > b.id) {
                return 1;
            }
            if (a.id < b.id) {
                return -1;
            }
            // a must be equal to b
            return 0;
        });

    }


    actualizarItem(listado) {
        let element = listado;
        element.forEach(e1 => {
            this.actualizar1Item(e1);
        });
        return element;
    }

    actualizar1Item(e1) {
        if (this.esListaVacia(e1.items)) {
            e1.items = null;
            if (!this.estaVacio(e1.routerLink)) {
                e1.command = event => { this.verPantalla(e1.label, e1.icon, e1.routerLink, e1); }
            } else if (!this.estaVacio(e1.action)) {
                e1.command = event => { this.ejecutarAccion(e1.action); }
            }
        } else {
            e1.items = this.actualizarItem(e1.items);
        }
        return e1;
    }



    /*    obtenerPeriodo(): Promise<number> {
            return this.servicio.auxPeriodoobtenerDto("WH").then(resp => {
                if(resp != null){
                    resp.ultimoperiodocontable;
                    var anio = '';
                    var mes = '';
                    anio = resp.ultimoperiodocontable.substring(0,4);
                    mes = resp.ultimoperiodocontable.substring(4,6);
                    this.periodo = 'Periodo: '+anio+'-'+mes;
    
                    var y = +anio;
                    var m = +mes;
                    m = m-1;
    
                    this.fechaInicio = new Date();
                    this.fechaFin = new Date();
    
                    this.fechaInicio = new Date(y, m, 1);
                    this.fechaFin = new Date(y, m + 1, 0);
                    return 1;
    
                }
            });
        }*/


    /*obtenerTipoCambio(): Promise<number> {
        this.bloquearPagina();
        return this.servicio.auxObtenerTipoCambioCreacionDefault().then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {
            }
            return 1;
        });
    }*/


}