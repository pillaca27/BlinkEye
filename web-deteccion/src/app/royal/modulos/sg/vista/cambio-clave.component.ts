import { DtoComunUsuario } from './../../framework-comun/sg/dominio/dto/DtoComunUsuario';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { SeguridadUsuarioLogin } from '@framework/modelo/seguridad/SeguridadUsuarioLogin';
import { MessageService } from 'primeng/api';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
  selector: 'app-cambio-clave',
  templateUrl: './cambio-clave.component.html',
  styleUrls: ['./cambio-clave.component.scss']
})
export class CambioClaveComponent extends BaseComponent implements OnInit, UIMantenimientoController {
  @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

  public lottieConfig: Object;
  private anim: any;
  private animationSpeed: number = 1;

  verModal: boolean = false;
  usuario: string = '';
  clave: string = '';
  clave1: string = '';
  clave2: string = '';
  compania: string = '';
  verUsuario = true;
  anio = '';
  values: any;
  mensajeAMostrar: string;
  legntext: number;
  usuarioDto: SeguridadUsuarioLogin = new SeguridadUsuarioLogin();
  usuarioDtoCambio: DtoComunUsuario = new DtoComunUsuario();

  constructor(
    // private autorizacionServicio: AutorizacionService,     
    private loginService: LoginService,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService,
    comun: ServicioComunService,
  ) {
    super(noAuthorizationInterceptor, messageService, comun);

    this.lottieConfig = {
      path: 'assets/15651-heart-with-arrow.json',
      renderer: 'canvas',
      autoplay: true,
      loop: true
    };
  }
  coreGuardar(): void {
    this.guardar();
  }
  coreSalir(): void {
    throw new Error('Method not implemented.');
  }
  coreExportar(tipo: string): void {
    throw new Error('Method not implemented.');
  }
  coreMensaje(mensage: MensajeController): void {
    throw new Error('Method not implemented.');
  }
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit(): void {

    this.formularioIniciar(this.route);
    this.tituloMantenimientoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
    this.iniciarComponente(this.getUsuarioActual().usuario, '');
  }

  iniciarComponente(values, mensajeAMostrar: string) {
    console.log(values);
    this.mensajeAMostrar = mensajeAMostrar;
    this.usuario = values;
    this.clave = '';
    this.clave1 = '';
    this.clave2 = '';
    if (this.estaVacio(values)) {
      this.verUsuario = false;
    }
    this.verModal = true;
  }

  guardar() {

    if (this.validar()) {
      return;
    }

    console.log('guardar');

    this.usuarioDtoCambio.usuario = this.usuario
    this.usuarioDtoCambio.compania = this.compania;
    this.usuarioDtoCambio.claveOld = this.clave;
    this.usuarioDtoCambio.claveNueva = this.clave1;
    this.usuarioDtoCambio.claveRep = this.clave2;

    this.usuarioDto.usuario = this.usuario;
    this.bloquearPagina();
    this.usuarioService.cambiarClaveUsuario(this.usuarioDtoCambio).then(
      res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          if (res != null) {
            this.bloquearPagina();
            //this.messageService.add({ severity: 'info', summary: 'Información', detail: 'Se han guardado los cambios' });
            this.loginService.listarcompaniasporusuario(this.usuarioDto).then(resp => {
              this.compania = resp[0].codigo;
              this.login();
            
              this.salir();
            }
            );
          }
        }
      }
    )
  }

  login() {
    this.bloquearPagina();
    this.usuarioDto.usuario = this.usuario
    this.usuarioDto.companiaCodigo = this.compania;
    this.usuarioDto.clave = this.clave1;
    this.loginService.ingresar(this.usuarioDto).then(
      obj => {
        this.desbloquearPagina();
        //if (obj != null) {
          if (obj != null) {
            sessionStorage.setItem('access_token', obj.token);
            this.router.navigate(['spring']);
          }
        //}
      }
    );
  }

  salir() {
    this.verModal = false;
  }

  validar(): boolean {
    const patternclave = new RegExp('^[a-zA-Z0-9 ]+$');
    let result: boolean = false;
    this.messageService.clear();

    if (this.clave === '') {
      this.mostrarMensaje('Es obligatorio ingresar la Clave Anterior', 'error');
      return result = true;
    }
    if (this.clave1 === '') {
      this.mostrarMensaje('Es obligatorio ingresar la Clave Nueva', 'error');
      return result = true;
    }
    if (this.clave2 === '') {
      this.mostrarMensaje('Es obligatorio ingresar Repetir Clave', 'error');
      return result = true;
    }

    if (this.clave1 != null) {
      this.legntext = this.clave1.length;
    }

    if (!patternclave.test(this.clave1)) {
      this.mostrarMensajeAdvertencia("La clave ingresada debe ser alfanumérica.!")
      return result = true;
    } else {
      if (this.clave1 == null || this.clave1 == undefined || this.clave1 == '') {
        this.mostrarMensajeAdvertencia("Ingrese su clave!")
        return result = true;
      } else if (this.legntext <= 2) {
        this.mostrarMensajeAdvertencia("La clave ingresada debe tener 6 dígitos como mínimo!")
        return result = true;
      } else if (this.legntext >= 11) {
        this.mostrarMensajeAdvertencia("La clave ingresada debe tener 10 dígitos como máximo!")
        return result = true;
      } else if (this.clave1.search(/\d/) == -1) {
        this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 número como mínimo!")
        return result = true;
      } else if (this.clave1.search(/[a-zA-Z]/) == -1) {
        this.mostrarMensajeAdvertencia("La clave ingresada debe tener 1 letra como mínimo!")
        return result = true;
      }
    }

    return result;
  }

  handleAnimation(anim: any) {
    this.anim = anim;
  }

  stop() {
    this.anim.stop();
  }

  play() {
    this.anim.play();
  }

  pause() {
    this.anim.pause();
  }

  setSpeed(speed: number) {
    this.animationSpeed = speed;
    this.anim.setSpeed(speed);
  }
}
