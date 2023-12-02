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
import { DtoHrPostulanteexperiencia } from '../dominio/DtoHrPostulanteexperiencia';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { HrActualizacionfichaempleadoServicio } from '../../autoservicios/hr/servicio/HrActualizacionfichaempleadoServicio';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { PuestoSelectorComponent } from '@framework-comun/hr/vista/puesto-selector.component';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-experiencia.component.html',
    selector: 'postulante-experiencia'
})
export class PostulanteExperienciaComponent extends FormularioComponent implements OnInit {

    @ViewChild(PuestoSelectorComponent, { static: false }) puestoSelectorComponent: PuestoSelectorComponent;

    constructor(
        private hrActualizacionfichaempleadoServicio: HrActualizacionfichaempleadoServicio,
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
        this.tipoContratos.push({ label: '--Seleccione--', value: null });
        this.tiposdeexperiencia.push({ label: '-- Seleccione --', value: null });
        this.areasdeexperiencia.push({ label: '-- Seleccione --', value: null });
        this.tiposdeentidad.push({ label: '-- Seleccione --', value: null });
        this.motivoretiro.push({ label: '-- Seleccione --', value: null });
        this.monedas.push({ label: '-- Seleccione --', value: null });
        this.monedas.push({ label: 'SOLES', value: 'LO' });
        this.monedas.push({ label: 'DOLARES', value: 'EX' });
        p.push(this.hrActualizacionfichaempleadoServicio.listarTipoContratosActivos().then(
            res => {
                res.forEach(obj => this.tipoContratos.push({ label: obj.descripcion, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_EXPERIENCIA)
            .then(respuesta => {
                respuesta.forEach(obj => this.tiposdeexperiencia.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_AREA_EXPERIENCIA)
            .then(respuesta => {
                respuesta.forEach(obj => this.areasdeexperiencia.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_ENTIDAD)
            .then(respuesta => {
                respuesta.forEach(obj => this.tiposdeentidad.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_MOTIVO_CESE)
            .then(respuesta => {
                respuesta.forEach(obj => this.motivoretiro.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        return p;
    }

    monedas: SelectItem[] = [];
    tiposdeentidad: SelectItem[] = [];
    areasdeexperiencia: SelectItem[] = [];
    tiposdeexperiencia: SelectItem[] = [];
    motivoretiro: SelectItem[] = [];
    tipoContratos: SelectItem[] = [];
    registroSeleccionado: any;
    lista: any[] = [];


    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.experienciaListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    verMantenimiento: boolean = false;
    dto: DtoHrPostulanteexperiencia = new DtoHrPostulanteexperiencia();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulanteexperiencia();
        this.dto.postulante = this.postulante.postulante;
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.dto.tipocontrato = this.trim(this.dto.tipocontrato);
        this.dto.motivocese = this.trim(this.dto.motivocese);
        this.dto.moneda = this.trim(this.dto.moneda);
        this.dto.areaexperiencia = this.trim(this.dto.areaexperiencia);
        this.dto.tipoexperiencia = this.trim(this.dto.tipoexperiencia);
        this.dto.tipoentidad = this.trim(this.dto.tipoentidad);
        this.dto.auxflagtrabaja = this.dto.flagtrabaja == 'S' ? true : false;
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.dto.tipocontrato = this.trim(this.dto.tipocontrato);
        this.dto.motivocese = this.trim(this.dto.motivocese);
        this.dto.moneda = this.trim(this.dto.moneda);
        this.dto.areaexperiencia = this.trim(this.dto.areaexperiencia);
        this.dto.tipoexperiencia = this.trim(this.dto.tipoexperiencia);
        this.dto.tipoentidad = this.trim(this.dto.tipoentidad);
        this.dto.auxflagtrabaja = this.dto.flagtrabaja == 'S' ? true : false;
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
                this.hrPostulanteService.crud(dto, 'experienciaEliminar').then(res => {
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
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'experienciaRegistrar' : 'experienciaActualizar';
        var mensajeExito = this.accion == this.ACCIONES.NUEVO ? this.getMensajeGrabadoSinCodigo() : this.getMensajeActualizadoSinCodigo();
        this.dto.flagtrabaja = this.dto.auxflagtrabaja ? 'S' : 'N';
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
        if (this.estaVacio(this.dto.empresa)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese el nombre de la Empresa');
            valido = false;
        }
        if (this.dto.fechadesde == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione la fecha Inicio');
            valido = false;
        }
        if (this.dto.fechahasta == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione la fecha Término');
            valido = false;
        }
        if (this.dto.fechadesde != null && this.dto.fechahasta != null) {
            if (this.dto.fechadesde >= this.dto.fechahasta) {
                this.mostrarMensajeAdvertenciaNoClear('La fecha de Término debe ser posterior a la fecha de Inicio');
                valido = false;
            }

            if (this.dto.fechahasta >= new Date()) {
                this.mostrarMensajeAdvertenciaNoClear('La fecha de Término no puede ser mayor a la fecha actual');
                valido = false;
            }
        }
        if (this.estaVacio(this.dto.tipoexperiencia)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el Tipo de Experiencia');
            valido = false;
        }
        return valido;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'PUESTO') {
            this.dto.puestosimilar = mensage.resultado.codigopuesto;
            this.dto.auxPuestoNombre = mensage.resultado.descripcion;
        }
    }

    validaSueldo(txt) {
        if (this.dto.sueldo === null) {
            this.dto.sueldo = null;
            txt.value = null;
        }
    }

    calcular() {
        if (this.dto.fechahasta !== undefined) {
            if (this.dto.fechahasta.getTime() == null || this.dto.fechahasta.getTime() === undefined) {
                return;
            }
        }

        if (this.dto.fechahasta !== undefined) {
            if (this.dto.fechadesde.getTime() == null || this.dto.fechadesde.getTime() === undefined) {
                return;
            }
        }

        if (this.dto.fechahasta !== undefined && this.dto.fechahasta !== undefined) {
            if (this.dto.fechadesde >= this.dto.fechahasta) {
                return;
            }
            let diff = (this.dto.fechahasta.getTime() - this.dto.fechadesde.getTime()) / 1000;
            diff /= (60 * 60 * 24);
            this.dto.ano = Math.abs(Math.round(diff / 365.25));
        }
    }

    buscarPuesto() {
        this.puestoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PUESTO', null));
    }
}
