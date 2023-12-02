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
import { DtoHrPostulanteinstruccion } from '../dominio/DtoHrPostulanteinstruccion';
import { HrActualizacionfichaempleadoServicio } from '../../autoservicios/hr/servicio/HrActualizacionfichaempleadoServicio';
import { DtoHrGradoInstruccionConsulta } from '../../autoservicios/hr/dominio/dto/DtoHrGradoInstruccionConsulta';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { HrCentroEstudiosCarreraSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector-carrera.component';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-instruccionbasica.component.html',
    selector: 'postulante-instruccionbasica'
})
export class PostulanteInstruccionBasicaComponent extends FormularioComponent implements OnInit {

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
        this.grados.push({ label: '-- Seleccione --', value: null });
        p.push(this.hrActualizacionfichaempleadoServicio.listarGradosActivos().then(respuesta => {
            respuesta.forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
            this.lstGrados = respuesta;
            return 1;
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

    registroSeleccionado: any;
    lista: any[] = [];

    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.instruccionBasicaListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    lstExtensiones: string[] = [];
    folioMaxSize: number = 0;
    folioMaxSizeMb: number = 0;
    lstGrados: DtoHrGradoInstruccionConsulta[] = [];
    grados: SelectItem[] = [];
    verMantenimiento: boolean = false;
    dto: DtoHrPostulanteinstruccion = new DtoHrPostulanteinstruccion();

    nuevo() {
        this.grados = [];
        this.grados.push({ label: '-- Seleccione --', value: null });
        this.lstGrados.filter(x => x.tipoMaestro == 'B').forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
        this.flagCentroEstudios = 'N';
        this.flagTieneCarrera = 'N';
        this.flagtermino = 'N';
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulanteinstruccion();
        this.dto.postulante = this.postulante.postulante;
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.grados = [];
        this.grados.push({ label: '-- Seleccione --', value: null });
        this.lstGrados.filter(x => x.tipoMaestro == 'B').forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);

        this.flagCentroEstudios = 'N';
        this.flagTieneCarrera = 'N';
        this.flagtermino = 'N';

        this.dto.gradoinstruccion = this.trim(this.dto.gradoinstruccion);
        this.dto.auxFlagultimogrado = this.dto.flagultimogrado === 'S' ? true : false;
        this.dto.auxFlagotrocentroestudio = this.dto.flagotrocentroestudio === 'S' ? true : false;

        if (!this.estaVacio(this.dto.gradoinstruccion)) {
            var c = this.lstGrados.find(x => x.gradoInstruccion.trim() == this.dto.gradoinstruccion);
            if (c) {
                this.flagCentroEstudios = c.flagcentroestudios;
                this.flagTieneCarrera = c.flagtienecarrera;
                this.flagtermino = c.flagtermino;
            }
        }
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.grados = [];
        this.grados.push({ label: '-- Seleccione --', value: null });
        this.lstGrados.filter(x => x.tipoMaestro == 'B').forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion }));
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);

        this.flagCentroEstudios = 'N';
        this.flagTieneCarrera = 'N';
        this.flagtermino = 'N';

        this.dto.gradoinstruccion = this.trim(this.dto.gradoinstruccion);
        this.dto.auxFlagultimogrado = this.dto.flagultimogrado === 'S' ? true : false;
        this.dto.auxFlagotrocentroestudio = this.dto.flagotrocentroestudio === 'S' ? true : false;

        if (!this.estaVacio(this.dto.gradoinstruccion)) {
            var c = this.lstGrados.find(x => x.gradoInstruccion.trim() == this.dto.gradoinstruccion);
            if (c) {
                this.flagCentroEstudios = c.flagcentroestudios;
                this.flagTieneCarrera = c.flagtienecarrera;
                this.flagtermino = c.flagtermino;
            }
        }


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
                this.hrPostulanteService.crud(dto, 'instruccionBasicaEliminar').then(res => {
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
        this.dto.auxGradoNombre = this.obtenerLabelPorCombo(this.grados, this.dto.gradoinstruccion);
        this.dto.flagotrocentroestudio = this.booleanAFlag(this.dto.auxFlagotrocentroestudio);
        this.dto.flagultimogrado = this.booleanAFlag(this.dto.auxFlagultimogrado);
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'instruccionBasicaRegistrar' : 'instruccionBasicaActualizar';
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

    flagCentroEstudios: string = 'N';
    flagtermino: string = 'N';
    flagTieneCarrera: string = 'N';

    validar() {
        var valido = true;
        this.messageService.clear();
        if (this.estaVacio(this.dto.gradoinstruccion)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el Grado de Instrucción');
            valido = false;
        }
        if (this.estaVacio(this.dto.gradoinstruccion)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el Grado de Instrucción');
            valido = false;
        }
        if (this.flagCentroEstudios === 'S') {
            if (!this.dto.auxFlagotrocentroestudio) {
                if (this.dto.centroestudio === undefined || this.dto.centroestudio == null) {
                    this.mostrarMensajeAdvertenciaNoClear('El Centro de Estudios es requerido');
                    valido = false;
                }
            } else {
                if (this.estaVacio(this.dto.otrocentroestudio)) {
                    this.mostrarMensajeAdvertenciaNoClear('El Otro Centro de Estudios es requerido');
                    valido = false;
                }
            }
        }
        var fechas = true;
        if (this.dto.fechadesde == null || this.dto.fechadesde === undefined) {
            this.mostrarMensajeAdvertenciaNoClear('La fecha de inicio es requerida');
            valido = false;
            fechas = false;
        }
        if (!this.estaVacio(this.flagtermino)) {
            if (this.flagtermino === 'S') {
                if (this.dto.fechahasta == null || this.dto.fechahasta === undefined) {
                    this.mostrarMensajeAdvertenciaNoClear('La fecha de termino es requerida');
                    valido = false;
                    fechas = false;
                }
            }
        }
        if (!this.estaVacio(this.flagTieneCarrera)) {
            if (this.flagTieneCarrera === 'S') {
                if (this.estaVacio(this.dto.profesion)) {
                    this.mostrarMensajeAdvertenciaNoClear('La Carrera es requerida');
                    valido = false;
                }
            }
        }
        if (fechas && this.dto.fechahasta != null) {
            if (this.dto.fechadesde >= this.dto.fechahasta) {
                this.mostrarMensajeAdvertenciaNoClear('La fecha de termino debe ser posterior a la fecha de inicio');
                valido = false;
            }

            if (this.dto.fechahasta >= new Date()) {
                this.mostrarMensajeAdvertenciaNoClear('La fecha de termino no puede ser mayor a la fecha actual');
                valido = false;
            }
        }
        return valido;
    }

    cargarGradoPorNivel() {
        this.flagCentroEstudios = 'N';
        this.flagTieneCarrera = 'N';
        this.flagtermino = 'N';
        if (!this.estaVacio(this.dto.gradoinstruccion)) {
            var c = this.lstGrados.find(x => x.gradoInstruccion == this.dto.gradoinstruccion);
            if (c) {
                this.flagCentroEstudios = c.flagcentroestudios;
                this.flagTieneCarrera = c.flagtienecarrera;
                this.flagtermino = c.flagtermino;
            }
        }
    }

    cambioSegunFlagCentro() {
        this.dto.centroestudio = null;
        this.dto.auxCentroNombre = null;
    }

    mostrarSelectorCentroEstudio() {
        this.hrCentroEstudiosCarreraSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO', null), true);
    }
    limpiarCentroEstudios() {
        this.dto.centroestudio = null;
        this.dto.auxCentroNombre = null;
    }

    cargarFormacion(event: any) {
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
    descargarFormacion() {
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

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'CENTROESTUDIO') {
            var centro = mensage.resultado.centro;
            var carrera = mensage.resultado.carrera;
            this.dto.centroestudio = centro.centro;
            this.dto.auxCentroNombre = centro.descripcion;
        }
    }

}
