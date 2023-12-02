import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ConstantePostulante } from "../ConstantePostulante";
import { HrPostulanteService } from '../servicio/hrpostulante.service';
import { DtoHrPostulante } from '../dominio/DtoHrPostulante';
import { DtoHrPostulantereferencia } from '../dominio/DtoHrPostulantereferencia';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-referencia.component.html',
    selector: 'postulante-referencia'
})
export class PostulanteReferenciaComponent extends FormularioComponent implements OnInit {
    constructor(
        private hrPostulanteService: HrPostulanteService,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    moduloOrigen: string;
    postulante: DtoHrPostulante = new DtoHrPostulante();
    accionPadre:accionSolicitada;
    ngOnInit() {
        super.ngOnInit();
        this.route.data.subscribe(x => this.moduloOrigen = x.moduloOrigen);
        if (ConstantePostulante.ORIGEN_MODULO_POSTULANTE == this.moduloOrigen) {
            this.formularioIniciar(this.route);
            this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
            this.accionPadre = this.ACCIONES.EDITAR;
            this.postulante.postulante = 0;
            //Obtener data por usuario actual, caso desde modulo de postulante
            this.bloquearPagina();
            Promise.all(this.cargarCombos()).then(res => {
                Promise.all([this.refrescarVista()]).then(res => {
                    this.desbloquearPagina();
                });
            });
        }
    }

    iniciarComponente(postulanteId: number, accion: accionSolicitada): Promise<number> {
        this.accionPadre = accion;
        this.moduloOrigen = ConstantePostulante.ORIGEN_MODULO_GTH;
        this.postulante = new DtoHrPostulante();
        this.postulante.postulante = postulanteId;
        //Obtener data por parametro, caso desde modulo GTH
        return Promise.all(this.cargarCombos()).then(res => {
            return Promise.all([this.refrescarVista()]).then(res => {
                this.desbloquearPagina();
                return 1;
            });
        });
    }

    cargarCombos(): Promise<number>[] {
        var p = [];
        return p;
    }

    registroSeleccionado: any;
    lista: any[] = [];


    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.referenciaListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    verMantenimiento: boolean = false;
    dto: DtoHrPostulantereferencia = new DtoHrPostulantereferencia();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulantereferencia();
        this.dto.postulante = this.postulante.postulante;
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.verMantenimiento = true;
    }

    eliminar(dto: any): void {
        this.confirmationService.confirm({
            key: 'confirm',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                this.bloquearPagina();
                this.hrPostulanteService.crud(dto, 'referenciaEliminar').then(res => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoSimple(res)) {
                        let lst = [...this.lista];
                        lst = lst.filter(x => x.secuencia != dto.secuencia);
                        this.lista = lst;
                        this.mostrarMensajeExito(this.getMensajeEliminadoSinCodigo());
                    }
                });
            }
        });
    }

    guardar() {
        if (!this.validar()) {
            return;
        }
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'referenciaRegistrar' : 'referenciaActualizar';
        var mensajeExito = this.accion == this.ACCIONES.NUEVO ? this.getMensajeGrabadoSinCodigo() : this.getMensajeActualizadoSinCodigo();
        this.bloquearPagina();
        this.hrPostulanteService.crud(this.dto, accionCRUD).then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {
                this.verMantenimiento = false;
                this.mostrarMensajeExito(mensajeExito);
                this.bloquearPagina();
                Promise.all([this.refrescarVista()]).then(res => {
                    this.desbloquearPagina();
                    return 1;
                });
            }
        });
    }

    validar() {
        var valido = true;
        this.messageService.clear();
        if (this.estaVacio(this.dto.nombre)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingresar el nombre del jefe');
            valido = false;
        }
        if (this.estaVacio(this.dto.cargo)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingresar el cargo');
            valido = false;
        }
        if (this.estaVacio(this.dto.empresa)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingresar la empresa');
            valido = false;
        }
        if (this.estaVacio(this.dto.direccion)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingresar la dirección');
            valido = false;
        }
        return valido;
    }

}
