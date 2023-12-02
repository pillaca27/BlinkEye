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
import { HrCentroEstudiosCarreraSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector-carrera.component';
import { HrActualizacionfichaempleadoServicio } from '../../autoservicios/hr/servicio/HrActualizacionfichaempleadoServicio';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { DtoHrPostulantecurso } from '../dominio/DtoHrPostulantecurso';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-idiomas.component.html',
    selector: 'postulante-idiomas'
})
export class PostulanteIdiomasComponent extends FormularioComponent implements OnInit {
    @ViewChild(HrCentroEstudiosCarreraSelectorComponent, { static: false }) hrCentroEstudiosCarreraSelectorComponent: HrCentroEstudiosCarreraSelectorComponent;

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
        this.idiomaNivel.push({ label: '-- Seleccione --', value: null });
        this.idiomaNivel.push({ label: 'Básico', value: 'B' });
        this.idiomaNivel.push({ label: 'Intermedio', value: 'I' });
        this.idiomaNivel.push({ label: 'Avanzado', value: 'A' });
        this.cursos.push({ label: '-- Seleccione --', value: null });
        this.tipo.push({ label: '-- Seleccione --', value: null });
        var p = [];
        p.push(this.hrActualizacionfichaempleadoServicio.listarCursosActivos("I").then(respuesta => {
            respuesta.forEach(obj => this.cursos.push({ label: obj.descripcion, value: obj.id })); return 1;
        }));
        p.push(this.servicioComun.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_CURSO)
            .then(respuesta => {
                respuesta.forEach(obj => this.tipo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.parametroObtenerNumero(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.PARAMETRO_TAMANIO_FOLIO).then(res => {
            this.folioMaxSize = res;
            this.folioMaxSizeMb = Math.round(this.folioMaxSize / 1000000);
            return 1;
        }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'SY', ConstanteFichaEmpleado.MISC_DOCUMENTOS_EXTENSIONES_PERMITIDOS).then(res => {
            res.forEach(ele => {
                this.lstExtensiones.push(ele.codigo.trim());
            });
            return 1;
        }));
        return p;
    }

    lstExtensiones: string[] = [];
    folioMaxSize: number = 0;
    folioMaxSizeMb: number = 0;
    registroSeleccionado: any;
    lista: any[] = [];


    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.idiomasListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    cursos: SelectItem[] = [];
    tipo: SelectItem[] = [];
    idiomaNivel: SelectItem[] = [];
    verMantenimiento: boolean = false;
    dto: DtoHrPostulantecurso = new DtoHrPostulantecurso();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulantecurso();
        this.dto.postulante = this.postulante.postulante;
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.dto.tipocurso = this.trim(this.dto.tipocurso);
        this.dto.auxFlagauspicioempresa = this.dto.flagauspicioempresa == 'S' ? true : false;
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.dto.tipocurso = this.trim(this.dto.tipocurso);
        this.dto.auxFlagauspicioempresa = this.dto.flagauspicioempresa == 'S' ? true : false;
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
                this.hrPostulanteService.crud(dto, 'idiomaEliminar').then(res => {
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
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'idiomaRegistrar' : 'idiomaActualizar';
        var mensajeExito = this.accion == this.ACCIONES.NUEVO ? this.getMensajeGrabadoSinCodigo() : this.getMensajeActualizadoSinCodigo();
        this.dto.flagauspicioempresa = this.dto.auxFlagauspicioempresa ? 'S' : 'N';
        this.dto.auxCursoNombre = this.obtenerLabelPorCombo(this.cursos, this.dto.curso);
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
        if (this.dto.curso == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el curso');
            valido = false;
        }
        if (this.estaVacio(this.dto.tipocurso)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo de curso');
            valido = false;
        }
        if (this.dto.centroestudio == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el centro de estudio');
            valido = false;
        }
        if (this.dto.fechadesde == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione la fecha de inicio');
            valido = false;
        }
        return valido;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'CENTROESTUDIO') {
            var centro = mensage.resultado.centro;
            var carrera = mensage.resultado.carrera;
            this.dto.centroestudio = centro.centro;
            this.dto.auxCentroNombre = centro.descripcion;
        }
    }

    mostrarSelectorCentroEstudioEspecializacion() {
        this.hrCentroEstudiosCarreraSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO', null), false);
    }
    limpiarCentroEstudiosEspecializacion() {
        this.dto.centroestudio = null;
        this.dto.auxCentroNombre = null;
    }

    cargarEspecializacion(event: any) {
        var files = event.files;
        if (files.length !== 1) {
            return;
        }
        if (files[0].size > this.folioMaxSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.folioMaxSizeMb + 'mb');
            return;
        }
        this.bloquearPagina();
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result.toString().split(',')[1];
            this.desbloquearPagina();
            if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
                return;
            }
            this.dto.auxArchivoContenido = result;
            this.dto.rutadocumento = files[0].name;
        };
    }
    descargarEspecializacion() {
        if (this.dto.auxArchivoContenido == null) {
            this.mostrarMensajeAdvertencia('No se encontró el documento');
            return;
        }
        const a = document.createElement('a');
        a.href = 'data:application/octet-stream;base64,' + this.dto.auxArchivoContenido;

        a.setAttribute('download', this.dto.rutadocumento);
        const b = document.createEvent('MouseEvents');
        b.initEvent('click', false, true);
        a.dispatchEvent(b);
    }


}
