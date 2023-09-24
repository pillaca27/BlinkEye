import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { Subscription } from 'rxjs';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { AppConfig } from 'src/environments/appconfig';
import { SeguridadUsuarioLogin } from '../dominio/SeguridadUsuarioLogin';
import { LoginService } from '../servicio/login.service';

@Component({
  selector: 'app-comunlogin',
  templateUrl: './app.comun-login.component.html',
})
export class AppComunLoginComponent extends BaseComponent {

  subscription: Subscription;
  usuario: SeguridadUsuarioLogin = new SeguridadUsuarioLogin();
  mensajeAMostrar: string;
  verMensaje: boolean = false;
  logo = "";

  blocked: boolean = false;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private config: AppConfig,
    // noAuthorizationInterceptor: NoAuthorizationInterceptor,
     messageService: MessageService,
    // servicioComun: ServicioComunService
  ) {
    super(messageService);
    //super(noAuthorizationInterceptor, messageService, servicioComun);
    sessionStorage.clear();
    localStorage.clear();
  }

  ngOnInit() {

    // this.logo = `assets/imagenes/${this.config.getEnv('recursos-compania')}/login.png`;
    // this.validarCaptcha = this.config.getEnv('captcha.validar') == 'S' ? true : false;

    this.usuario.tipoUsuarioId = "EMPL";
    this.usuario.companiaCodigo='00000000';

  }
  login(): void {
    this.bloquearPagina();
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
    if (this.usuario.tipoUsuarioId == "EMPL") {
      this.router.navigate(['portal']);
    }
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
