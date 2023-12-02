import { ContraseniaMantenientoComponent } from './contrasenia-mantenimiento.component';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MenuService } from './app.menu.service';
import { PrimeNGConfig } from 'primeng/api';
import { Router } from '@angular/router';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';

@Component({
    selector: 'app-main',
    templateUrl: './app.main.component.html',
})
export class AppMainComponent implements OnInit {

    @ViewChild(ContraseniaMantenientoComponent, { static: false }) contraseniaMantenientoComponent: ContraseniaMantenientoComponent;

    menuMode = 'static';

    colorScheme = 'light';

    menuTheme = 'layout-sidebar-darkgray';

    overlayMenuActive: boolean;

    staticMenuDesktopInactive: boolean;

    staticMenuMobileActive: boolean;

    menuClick: boolean;

    search = false;

    searchClick = false;

    userMenuClick: boolean;

    topbarUserMenuActive: boolean;

    notificationMenuClick: boolean;

    topbarNotificationMenuActive: boolean;

    rightMenuClick: boolean;

    rightMenuActive: boolean;

    configActive: boolean;

    configClick: boolean;

    resetMenu: boolean;

    menuHoverActive = false;

    inputStyle = 'outlined';

    ripple: boolean;

    constructor(
        private menuService: MenuService, 
        private primengConfig: PrimeNGConfig, 
        public usuarioService: UsuarioService,
        private router: Router) {
    }

    ngOnInit() {
        this.primengConfig.ripple = true;
    }

    onLayoutClick() {
        if (!this.searchClick) {
            this.search = false;
        }

        if (!this.userMenuClick) {
            this.topbarUserMenuActive = false;
        }

        if (!this.notificationMenuClick) {
            this.topbarNotificationMenuActive = false;
        }

        if (!this.rightMenuClick) {
            this.rightMenuActive = false;
        }

        if (!this.menuClick) {
            if (this.isSlim()) {
                this.menuService.reset();
            }

            if (this.overlayMenuActive || this.staticMenuMobileActive) {
                this.hideOverlayMenu();
            }

            this.menuHoverActive = false;
            this.unblockBodyScroll();
        }

        if (this.configActive && !this.configClick) {
            this.configActive = false;
        }

        this.searchClick = false;
        this.configClick = false;
        this.userMenuClick = false;
        this.rightMenuClick = false;
        this.notificationMenuClick = false;
        this.menuClick = false;
    }

    onMenuButtonClick(event) {
        this.menuClick = true;
        this.topbarUserMenuActive = false;
        this.topbarNotificationMenuActive = false;
        this.rightMenuActive = false;

        if (this.isOverlay()) {
            this.overlayMenuActive = !this.overlayMenuActive;
        }

        if (this.isDesktop()) {
            this.staticMenuDesktopInactive = !this.staticMenuDesktopInactive;
        } else {
            this.staticMenuMobileActive = !this.staticMenuMobileActive;
            if (this.staticMenuMobileActive) {
                this.blockBodyScroll();
            } else {
                this.unblockBodyScroll();
            }
        }

        event.preventDefault();
    }

    onSearchClick(event) {
        this.search = !this.search;
        this.searchClick = !this.searchClick;
    }

    onSearchHide(event) {
        this.search = false;
        this.searchClick = false;
    }

    onMenuClick($event) {
        this.menuClick = true;
        this.resetMenu = false;
    }

    onTopbarUserMenuButtonClick(event) {
        this.userMenuClick = true;
        this.topbarUserMenuActive = !this.topbarUserMenuActive;

        this.hideOverlayMenu();

        event.preventDefault();
    }

    onTopbarNotificationMenuButtonClick(event) {
        this.notificationMenuClick = true;
        this.topbarNotificationMenuActive = !this.topbarNotificationMenuActive;

        this.hideOverlayMenu();

        event.preventDefault();
    }

    onRightMenuClick(event) {
        this.usuarioService.logOut().then(res=>{});
        sessionStorage.removeItem('access_token');
        var login =  sessionStorage.getItem('login');
        if(login){
            this.router.navigate([login]);
        }
        else{
            this.router.navigate(['']);
        }        
        event.preventDefault();

    }

    onRippleChange(event) {
        this.ripple = event.checked;
    }

    onConfigClick(event) {
        this.configClick = true;
    }

    isSlim() {
        return this.menuMode === 'slim';
    }

    isOverlay() {
        return this.menuMode === 'overlay';
    }

    isDesktop() {
        return window.innerWidth > 991;
    }

    isMobile() {
        return window.innerWidth <= 991;
    }

    hideOverlayMenu() {
        this.overlayMenuActive = false;
        this.staticMenuMobileActive = false;
    }

    blockBodyScroll(): void {
        if (document.body.classList) {
            document.body.classList.add('blocked-scroll');
        } else {
            document.body.className += ' blocked-scroll';
        }
    }

    unblockBodyScroll(): void {
        if (document.body.classList) {
            document.body.classList.remove('blocked-scroll');
        } else {
            document.body.className = document.body.className.replace(new RegExp('(^|\\b)' +
                'blocked-scroll'.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
        }
    }

    cambiarclave() {
        this.usuarioService.obtenerUsuarioActual().then(
            resp => {
                this.contraseniaMantenientoComponent.iniciarComponente(resp.usuario, '');
            }
        );
    }

}
