import { AppConfig } from './../../../../../environments/appconfig';
import { SeguridadUsuarioLogin } from './../../../framework/modelo/seguridad/SeguridadUsuarioLogin';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ReCaptchaV3Service } from 'ng-recaptcha';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-comunlogin',
  templateUrl: './app.comun-login.component.html',
})
export class AppComunLoginComponent extends BaseComponent {

  subscription: Subscription;
  companias: SelectItem[] = [];
  usuario: SeguridadUsuarioLogin = new SeguridadUsuarioLogin();
  mensajeAMostrar: string;
  verMensaje: boolean = false;
  logo = "";
  img = "";
  img2 = "";
  validarCaptcha: boolean = false;
  aplicacionCodigo: string = '';

  constructor(
    private recaptchaV3Service: ReCaptchaV3Service,
    private loginService: LoginService,
    private router: Router,
    private config: AppConfig,
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
    // this.img2 = `assets/imagenes/${this.config.getEnv('recursos-compania')}/2login.png`;
    this.validarCaptcha = this.config.getEnv('captcha.validar') == 'S' ? true : false;

    this.aplicacionCodigo = this.config.getEnv('aplicacioncodigo');
  }
  handleToken(token): void {
    debugger;
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
              this.usuario.tipoUsuarioId = obj.tipoUsuarioId;
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

  login(): void {
    if (!this.validarCaptcha) {
      this.handleToken('token')
    }
    else {
      this.subscription = this.recaptchaV3Service.execute('login')
        .subscribe((token) => this.handleToken(token));
    }
  }

  cargarCompaniasPorUsuario() {
    this.bloquearPagina();
    this.loginService.listarcompaniasporusuario(this.usuario)
      .then(
        res => {
          this.companias = [];
          this.desbloquearPagina();
          if (res != null) {
            res.forEach(obj => this.companias.push({ label: obj.nombre, value: obj.codigo }));
            if (res.length > 0) {
              this.usuario.companiaCodigo = res[0].codigo;
            } else {
              this.usuario.companiaCodigo = null;
            }
          }
        }
      );
  }
  ingresarAlSistema() {
    sessionStorage.setItem('access_token', this.usuario.token);
    this.irDashboard();
  }

  irDashboard() {

    var prs = [];

    // prs.push(this.asAccesosdiariosService.inicializar().then(resp => {
    //   sessionStorage.setItem('seguridadIniciar', JSON.stringify(resp));
    //   return 1;
    // }));

    Promise.all(prs).then(res => {
      if (this.usuario.tipoUsuarioId == "DOCENTE" || this.usuario.tipoUsuarioId == "AUXILIAR" 
          || this.usuario.tipoUsuarioId == "DIRECTOR" || this.usuario.tipoUsuarioId == "SUBDIRECTOR") {
        this.router.navigate(['spring']);
        sessionStorage.setItem('login', '');
      }
      else
      {
        this.mostrarMensajeAdvertenciaNoClear("No existe el tipo de usuario");
        this.desbloquearPagina();
      }
    });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
