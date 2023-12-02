import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { AsAccesosdiariosService } from '../../servicio/asaccesosdiarios.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { DtoAccesosdiarios } from '../../dominio/dto/DtoAccesosdiarios';
import { AsAccesosdiarios } from '../../dominio/dto/asaccesosdiarios';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { EmpleadomastComunService } from '@framework-comun/core/servicio/empleadomast-comun.service';

@Component({
    templateUrl: './asmarcacion.component.html'
})
export class AsMarcacionComponent extends FormularioComponent implements OnInit, UIListadoController {
    constructor(
        private datePipe: DatePipe,
        private route: ActivatedRoute,
        private asAccesosdiariosService: AsAccesosdiariosService,
        private empleadomastComunService: EmpleadomastComunService,
        private usuario: UsuarioService,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }


    registro: DtoAccesosdiarios = new DtoAccesosdiarios();
    lista: AsAccesosdiarios[] = [];

    listaAccion: SelectItem[] = [];
    time = new Date();
    verBoton: Boolean = false;

    ngOnInit(): void {
        console.log('ngOnInit');

        this.formularioIniciar(this.route);

        this.tituloListadoAsignarConTitulo(this.tituloListadoTipo.ESTANDAR, this, "Registrar Marcaciones", this.objetoBoton);

        setInterval(() => {
            this.time = new Date();
        }, 1000);

        this.listaAccion.push({ label: ' Entrada ', value: 'E' });
        this.listaAccion.push({ label: ' Salida ', value: 'S' });

        this.registro.bean.nombreEmpleado = this.getUsuarioActual().personaNombreCompleto;

        this.formularioOninit = false;
        this.bloquearPagina();
        this.asAccesosdiariosService.obtenerInformacionParaMarcar().then(
            resp => {

                if (resp.listaErrores.length > 0) {
                    this.verBoton = false;
                    for (let i = 0, len = resp.listaErrores.length; i < len; i++) {
                        this.messageService.add({
                            severity: 'error', summary: 'Error',
                            detail: resp.listaErrores[i].mensaje
                        });
                    }
                } else {
                    this.verBoton = true;
                    this.registro = resp;
                    this.registro.lista.forEach(element => {
                        element.fechaaux = this.datePipe.transform(element.pk.fecha, "dd/M/yyyy HH:mm:ss");
                    });
                }

                this.desbloquearPagina();
            }
        );
    }


    aceptar() {

        this.bloquearPagina();
        this.registro.lista.forEach(element => {
            if (element.fechaaux == null) {
                element.fechaaux = this.datePipe.transform(element.pk.fecha, "dd/M/yyyy HH:mm:ss");
            }
        });

        this.asAccesosdiariosService.insertarMarca(this.registro).then(
            resp => {
                if (resp.listaErrores.length > 0) {
                    this.desbloquearPagina();
                    for (let i = 0, len = resp.listaErrores.length; i < len; i++) {
                        this.messageService.add({
                            severity: 'error', summary: 'Error',
                            detail: resp.listaErrores[i].mensaje
                        });
                    }
                } else {
                    this.asAccesosdiariosService.obtenerInformacionParaMarcar().then(
                        final => {
                            this.desbloquearPagina();
                            this.registro = final;
                            this.mostrarMensajeExito('Se registró la marcación Correctamente');
                        }
                    );
                }

            }
        );
    }

    coreNuevo(): void {
        throw new Error('Method not implemented.');
    }
    coreBusquedaRapida(filtro: string): void {
        throw new Error('Method not implemented.');
    }
    coreBuscar(tabla: LazyLoadEvent): void {
        throw new Error('Method not implemented.');
    }
    coreFiltro(flag: boolean): void {
        throw new Error('Method not implemented.');
    }
    coreAnular(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreEliminar(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreEditar(dto: any): void {
        throw new Error('Method not implemented.');
    }
    coreVer(dto: any): void {
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

}
