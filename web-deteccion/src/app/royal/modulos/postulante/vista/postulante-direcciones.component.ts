import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ConstantePostulante } from "../ConstantePostulante";
import { DtoHrPostulante } from '../dominio/DtoHrPostulante';
import { DtoHrPostulantedireccion } from '../dominio/DtoHrPostulantedireccion';
import { HrPostulanteService } from '../servicio/hrpostulante.service';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { PostulanteUbicacionGeograficaSelectorComponent } from './postulante-ubicaciongeografica-selector.component';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-direcciones.component.html',
    selector: 'postulante-direcciones'
})
export class PostulanteDireccionesComponent extends FormularioComponent implements OnInit {

    @ViewChild(PostulanteUbicacionGeograficaSelectorComponent, { static: false }) postulanteUbicacionGeograficaSelectorComponent: PostulanteUbicacionGeograficaSelectorComponent;

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
        this.tipovias.push({ label: '-- Seleccione --', value: null });
        this.zonas.push({ label: '-- Seleccione --', value: null });
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_VIAS)
            .then(respuesta => {
                respuesta.forEach(obj => this.tipovias.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_ZONA)
            .then(respuesta => {
                respuesta.forEach(obj => this.zonas.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        return p;
    }

    registroSeleccionado: any;
    lista: any[] = [];

    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.direccionesListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    tipovias: SelectItem[] = [];
    zonas: SelectItem[] = [];
    verMantenimiento: boolean = false;
    dto: DtoHrPostulantedireccion = new DtoHrPostulantedireccion();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulantedireccion();
        this.dto.pais = 'PER';
        this.dto.postulante = this.postulante.postulante;
        this.dto.secuenciadependiente = 0;
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.dto.pdttipocalle = this.trim(this.dto.pdttipocalle);
        this.dto.pdttipozona = this.trim(this.dto.pdttipozona);
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.dto.pdttipocalle = this.trim(this.dto.pdttipocalle);
        this.dto.pdttipozona = this.trim(this.dto.pdttipozona);
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
                this.hrPostulanteService.crud(dto, 'direccionEliminar').then(res => {
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
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'direccionRegistrar' : 'direccionActualizar';
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
        if (this.estaVacio(this.dto.pdttipocalle)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo');
            valido = false;
        }
        if (this.estaVacio(this.dto.direccion)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese la dirección');
            valido = false;
        }
        if (this.estaVacio(this.dto.auxUbigeoNombre)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione Dpto-Prov-Dist');
            valido = false;
        }
        return valido;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'UBIGEO') {
            this.dto.auxUbigeoNombre = mensage.resultado.descripcion;
            this.dto.departamento = mensage.resultado.departamento;
            this.dto.provincia = mensage.resultado.provincia;
            this.dto.codigopostal = mensage.resultado.zonapostal;
        }
    }

    mostrarSelectorUbigeo() {
        this.postulanteUbicacionGeograficaSelectorComponent.iniciarComponente(new MensajeController(this, 'UBIGEO', ''), this.dto.pais)
    }
    limpiarUbigeo() {
        this.dto.departamento = null;
        this.dto.provincia = null;
        this.dto.codigopostal = null;
        this.dto.auxUbigeoNombre = null;
    }
}
