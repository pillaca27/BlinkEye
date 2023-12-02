import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AppConfig } from '@env/appconfig';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { SeguridadUsuarioLogin } from '@framework/modelo/seguridad/SeguridadUsuarioLogin';
import { ReCaptchaV3Service } from 'ng-recaptcha';
import { MessageService, SelectItem } from 'primeng/api';
import { Subscription } from 'rxjs';

@Component({
    templateUrl: './postulante-login.component.html'
})
export class PostulanteLoginComponent extends BaseComponent {

    subscription: Subscription;
    img = "";
    img2 = "";
    logo = "";
    validarCaptcha: boolean = false;
    aplicacionCodigo: string;
    usuario: SeguridadUsuarioLogin = new SeguridadUsuarioLogin();
    lstDocumentos: SelectItem[] = [];
    mensajeAMostrar: string;
    verMensaje: boolean = false;

    constructor(
        private router: Router,
        private loginService: LoginService,
        private config: AppConfig,
        private recaptchaV3Service: ReCaptchaV3Service,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        sessionStorage.clear();
        localStorage.clear();
    }

    ngOnInit() {
        this.logo = `assets/imagenes/${this.config.getEnv('recursos-compania')}/login.png`;
        this.img = `assets/imagenes/${this.config.getEnv('recursos-compania')}/img.png`;
        this.img2 = `assets/imagenes/${this.config.getEnv('recursos-compania')}/2login.png`;
        this.validarCaptcha = this.config.getEnv('captcha.validar') == 'S' ? true : false;
        this.aplicacionCodigo = "PE";
        this.usuario.tipoUsuarioId = "POST";
        this.bloquearPagina();
        this.loginService.listarTipoDocumento().then(res => {
            this.usuario.companiaCodigo = null;
            var tipoDocDef = null;
            res.forEach(r => {
                if (r.codigo == 'DEFAULT') {
                    tipoDocDef = r.descripcion;
                }
                else {
                    this.lstDocumentos.push({ value: r.codigo, label: r.descripcion });
                }
            });
            this.usuario.companiaCodigo = tipoDocDef;
            this.desbloquearPagina();
        });
    }

    login(): void {
        if (!this.validarCaptcha) {
            this.handleToken('token')
        }
        else {
            this.subscription = this.recaptchaV3Service.execute('login')
                .subscribe((token) => this.handleToken(token));
        }
    }

    handleToken(token): void {
        this.bloquearPagina();
        this.usuario.token = token;
        this.usuario.aplicacionCodigo = this.aplicacionCodigo;
        this.loginService.ingresar(this.usuario).then(
            obj => {
                if (obj != null) {
                    this.usuario.token = obj.token;
                    if (!this.esListaVacia(obj.transaccionListaMensajes)) {
                        this.desbloquearPagina();
                        for (let i = 0, len = obj.transaccionListaMensajes.length; i < len; i++) {
                            if (obj.transaccionListaMensajes[i].tipoMensaje.toString() === 'ADVERTENCIA') {
                                this.mensajeAMostrar = obj.transaccionListaMensajes[i].mensaje;
                                this.verMensaje = true;
                            } else if (obj.transaccionListaMensajes[i].tipoMensaje.toString() === 'INFORMACION') {
                                this.mensajeAMostrar = obj.transaccionListaMensajes[i].mensaje;
                            } else {
                                this.messageService.add({
                                    severity: 'error', summary: 'Error',
                                    detail: obj.transaccionListaMensajes[i].mensaje
                                });
                            }
                        }
                    } else {
                        if (obj != null) {
                            sessionStorage.setItem('access_token', obj.token);
                            this.irDashboard();
                        } else {
                            this.desbloquearPagina();
                        }
                    }
                } else {
                    this.desbloquearPagina();
                }
            }
        );
    }

    ingresarAlSistema() {
        sessionStorage.setItem('access_token', this.usuario.token);
        this.irDashboard();
    }

    irDashboard() {
        this.router.navigate(['spring/postulante/datos-personales']);
        sessionStorage.setItem('login', 'postulante-login');
    }

    registrarse() {
        this.router.navigate(['spring/postulante/registrar']);
        sessionStorage.setItem('login', 'postulante-login');
        sessionStorage.setItem('registrar-postulante', 'S');
    }
}
