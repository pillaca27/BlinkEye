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
import { DtoHrPostulanterequerimiento } from '../dominio/DtoHrPostulanterequerimiento';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';
import { PostulanteRequerimientoAprobadoSelectorComponent } from './postulante-requerimientoaprobado-selector.component';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    templateUrl: './postulante-reqasoc.component.html',
    selector: 'postulante-reqasoc'
})
export class PostulanteReqAsocComponent extends FormularioComponent implements OnInit {

    @ViewChild(PostulanteRequerimientoAprobadoSelectorComponent, { static: false }) postulanteRequerimientoAprobadoSelectorComponent: PostulanteRequerimientoAprobadoSelectorComponent;

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
        return this.hrPostulanteService.reqAsocListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    verMantenimiento: boolean = false;
    dto: DtoHrPostulanterequerimiento = new DtoHrPostulanterequerimiento();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulanterequerimiento();
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
                this.hrPostulanteService.crud(dto, 'reqAsocEliminar').then(res => {
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
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'reqAsocRegistrar' : 'reqAsocActualizar';
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
        if (this.estaVacio(this.dto.requerimiento)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el Requerimiento');
            valido = false;
        }
        return valido;
    }

    buscarRequerimiento() {
        var t: DtoTabla[] = [];
        this.lista.filter(x => x.secuencia != this.dto.secuencia).forEach(r => {
            var c = new DtoTabla();
            c.codigo = r.requerimientocompania;
            c.descripcion = r.requerimiento;
            t.push(c);
        });
        this.postulanteRequerimientoAprobadoSelectorComponent.iniciarComponente(new MensajeController(this, 'REQUERIMIENTO', null), t)
    }

    coreMensaje(mensage: MensajeController) {
        if (mensage.componente == 'REQUERIMIENTO') {
            this.dto.requerimientocompania = mensage.resultado.companiaId;
            this.dto.requerimiento = mensage.resultado.requerimiento;
            this.dto.auxPuestoNombre = mensage.resultado.puestoSolicitado;
            this.dto.auxCompaniaNombre = mensage.resultado.compania;
        }
    }

}
