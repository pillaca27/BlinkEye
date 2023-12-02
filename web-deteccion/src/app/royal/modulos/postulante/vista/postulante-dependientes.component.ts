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
import { DtoHrPostulantedependiente } from '../dominio/DtoHrPostulantedependiente';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { HrActualizacionfichaempleadoServicio } from '../../autoservicios/hr/servicio/HrActualizacionfichaempleadoServicio';
import { DtoHrPostulantedireccion } from '../dominio/DtoHrPostulantedireccion';
import { PostulanteUbicacionGeograficaSelectorComponent } from './postulante-ubicaciongeografica-selector.component';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-dependientes.component.html',
    selector: 'postulante-dependientes'
})
export class PostulanteDependientesComponent extends FormularioComponent implements OnInit {

    index = 0;

    @ViewChild(PostulanteUbicacionGeograficaSelectorComponent, { static: false }) postulanteUbicacionGeograficaSelectorComponent: PostulanteUbicacionGeograficaSelectorComponent;
    accionPadre:accionSolicitada;
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
        //this.listaSexo.push({ label: '-- Seleccione --', value: null });
        this.listaSexo.push({ label: 'Masculino', value: 'M' });
        this.listaSexo.push({ label: 'Femenino', value: 'F' });
        this.listaCentrosSalud.push({ label: '-- Seleccione --', value: null });
        this.listaCentrosSalud.push({ label: 'Dirección 1 (Titular)', value: '1' });
        this.listaCentrosSalud.push({ label: 'Dirección 2 (Dependiente)', value: '2' });
        this.parentescos.push({ label: '-- Seleccione --', value: null });
        this.parentescosSel.push({ label: '-- Seleccione --', value: null });
        this.tipodocumento.push({ label: '-- Seleccione --', value: null });
        this.paisemisor.push({ label: '-- Seleccione --', value: null });
        this.tipovinculo.push({ label: '-- Seleccione --', value: null });
        this.listaCodigoPostal.push({ label: '-- Seleccione --', value: null });
        this.gruposanguineo.push({ label: '-- Seleccione --', value: null });
        this.tipodocumentoacreditado.push({ label: '-- Seleccione --', value: null });
        this.motivosbaja.push({ label: '-- Seleccione --', value: null });
        this.grados.push({ label: '-- Seleccione --', value: null });
        this.escolaridades.push({ label: '-- Seleccione --', value: null });
        this.escolaridades.push({ label: 'Privada', value: 'P' });
        this.escolaridades.push({ label: 'Nacional', value: 'N' });

        this.tipoasis.push({ label: '-- Seleccione --', value: null });
        this.tipoasis.push({ label: 'Sin EPS', value: 'IPS' });
        this.tipoasis.push({ label: 'Con EPS', value: 'PRI' });

        var p = [];
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_PARENTESCO)
            .then(respuesta => {
                respuesta.forEach(obj => this.parentescos.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_PARENTESCO)
            .then(respuesta => {
                respuesta.forEach(obj => this.parentescosSel.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_TIPO_DOCUMENTO_PERSONA)
            .then(respuesta => {
                respuesta.forEach(obj => this.tipodocumento.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_PAIS_EMISOR_DOCUMENTO)
            .then(respuesta => {
                respuesta.forEach(pais => this.paisemisor.push({ label: pais.nombre, value: pais.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_TIPO_VINCULO_FAMILIAR)
            .then(respuesta => {
                respuesta.forEach(obj => this.tipovinculo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_TIPO_DOCUMENTO_ACREDITADO)
            .then(respuesta => {
                respuesta.forEach(obj => this.tipodocumentoacreditado.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'PR', ConstanteFichaEmpleado.MISCELANEO_CODIGO_POSTAL)
            .then(respuesta => {
                respuesta.forEach(obj => this.listaCodigoPostal.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_GRUPO_SANGUIENEO)
            .then(respuesta => {
                respuesta.forEach(obj => this.gruposanguineo.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, ConstanteFichaEmpleado.APLICACION_CODIGO, 'DEPEMOBA')
            .then(respuesta => {
                respuesta.forEach(obj => this.motivosbaja.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));
        p.push(this.hrActualizacionfichaempleadoServicio.listarGradosActivos().then(respuesta => {
            respuesta.forEach(obj => this.grados.push({ label: obj.descripcion, value: obj.gradoInstruccion })); return 1;
        }));

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

    tipovias: SelectItem[] = [];
    zonas: SelectItem[] = [];
    tipoasis: SelectItem[] = [];
    escolaridades: SelectItem[] = [];
    grados: SelectItem[] = [];
    motivosbaja: SelectItem[] = [];
    parentescos: SelectItem[] = [];
    parentescosSel: SelectItem[] = [];
    tipodocumento: SelectItem[] = [];
    paisemisor: SelectItem[] = [];
    listaSexo: SelectItem[] = [];
    gruposanguineo: SelectItem[] = [];
    tipovinculo: SelectItem[] = [];
    tipodocumentoacreditado: SelectItem[] = [];
    listaCodigoPostal: SelectItem[] = [];
    listaCentrosSalud: SelectItem[] = [];
    registroSeleccionado: any;
    lista: any[] = [];


    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.dependientesListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    verMantenimiento: boolean = false;
    dto: DtoHrPostulantedependiente = new DtoHrPostulantedependiente();

    nuevo() {
        this.index = 0;
        this.accion = this.ACCIONES.NUEVO;
        this.flagVinculo = false;
        this.dto = new DtoHrPostulantedependiente();
        this.dto.postulante = this.postulante.postulante;
        this.dto.sexo = 'M';
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.index = 0;
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.dto.tipocarnetasistenciasocial = this.trim(this.dto.tipocarnetasistenciasocial);
        this.dto.documentoidentidad = this.trim(this.dto.documentoidentidad);
        this.dto.tipodependiente = this.trim(this.dto.tipodependiente);
        this.dto.tipodocumentoidentidad = this.trim(this.dto.tipodocumentoidentidad);
        this.dto.paisemisor = this.trim(this.dto.paisemisor);
        this.dto.codigopostal = this.trim(this.dto.codigopostal);
        this.dto.tiposangre = this.trim(this.dto.tiposangre);
        this.dto.tipovinculo = this.trim(this.dto.tipovinculo);
        this.dto.tipodocumentopaternidad = this.trim(this.dto.tipodocumentopaternidad);
        this.dto.motivobaja = this.trim(this.dto.motivobaja);
        this.dto.gradoeducacion = this.trim(this.dto.gradoeducacion);
        this.dto.auxFlagSmf = this.dto.flagsmf == '1' ? true : false;
        this.dto.auxFlagViveConEmpleado = this.dto.flagviveconempleado == 'S' ? true : false;
        this.dto.auxFlagBeneficio = this.dto.flagbeneficio == 'S' ? true : false;
        this.dto.auxFlagEscolaridad = this.dto.tieneescolaridad == 'S' ? true : false;
        this.dto.auxFlagTrabaja = this.dto.flagtrabaja == 'S' ? true : false;
        this.dto.auxFlagTrabajaDentro = this.dto.flagtrabajadentro == 'S' ? true : false;
        if (this.dto.lstDireccion.length > 0) {
            this.dto.lstDireccion[0].pdttipocalle = this.trim(this.dto.lstDireccion[0].pdttipocalle);
            this.dto.lstDireccion[0].pdttipozona = this.trim(this.dto.lstDireccion[0].pdttipozona);
        }
        this.transfromarFechaNacimiento();
        this.cambiarSegunParentesco();
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.index = 0;
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.dto.tipocarnetasistenciasocial = this.trim(this.dto.tipocarnetasistenciasocial);
        this.dto.tipodependiente = this.trim(this.dto.tipodependiente);
        this.dto.tipodocumentoidentidad = this.trim(this.dto.tipodocumentoidentidad);
        this.dto.paisemisor = this.trim(this.dto.paisemisor);
        this.dto.codigopostal = this.trim(this.dto.codigopostal);
        this.dto.tiposangre = this.trim(this.dto.tiposangre);
        this.dto.tipovinculo = this.trim(this.dto.tipovinculo);
        this.dto.tipodocumentopaternidad = this.trim(this.dto.tipodocumentopaternidad);
        this.dto.motivobaja = this.trim(this.dto.motivobaja);
        this.dto.gradoeducacion = this.trim(this.dto.gradoeducacion);
        this.dto.auxFlagSmf = this.dto.flagsmf == '1' ? true : false;
        this.dto.auxFlagViveConEmpleado = this.dto.flagviveconempleado == 'S' ? true : false;
        this.dto.auxFlagBeneficio = this.dto.flagbeneficio == 'S' ? true : false;
        this.dto.auxFlagEscolaridad = this.dto.tieneescolaridad == 'S' ? true : false;
        this.dto.auxFlagTrabaja = this.dto.flagtrabaja == 'S' ? true : false;
        this.dto.auxFlagTrabajaDentro = this.dto.flagtrabajadentro == 'S' ? true : false;
        if (this.dto.lstDireccion.length > 0) {
            this.dto.lstDireccion[0].pdttipocalle = this.trim(this.dto.lstDireccion[0].pdttipocalle);
            this.dto.lstDireccion[0].pdttipozona = this.trim(this.dto.lstDireccion[0].pdttipozona);
        }
        this.transfromarFechaNacimiento();
        this.cambiarSegunParentesco();
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
                this.hrPostulanteService.crud(dto, 'dependienteEliminar').then(res => {
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

    eliminDir() {
        this.confirmationService.confirm({
            key: 'confirm',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                this.dto.lstDireccion = [];
            }
        });
    }

    nuevoDir() {
        var dtoD = new DtoHrPostulantedireccion();
        dtoD.pais = 'PER';
        this.dto.lstDireccion.push(dtoD);
    }

    guardar() {
        if (!this.validar()) {
            return;
        }
        if (!this.estaVacio(this.dto.mesconcepcion)) {
            if (this.dto.mesconcepcion.length == 7) {
                this.dto.mesconcepcion = this.dto.mesconcepcion.replace('-', '');
            }
        }
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'dependienteRegistrar' : 'dependienteActualizar';
        var mensajeExito = this.accion == this.ACCIONES.NUEVO ? this.getMensajeGrabadoSinCodigo() : this.getMensajeActualizadoSinCodigo();
        this.dto.flagsmf = this.dto.auxFlagSmf ? '1' : '0';
        this.dto.flagviveconempleado = this.dto.auxFlagViveConEmpleado ? 'S' : 'N';
        this.dto.flagbeneficio = this.dto.auxFlagBeneficio ? 'S' : 'N';
        this.dto.tieneescolaridad = this.dto.auxFlagEscolaridad ? 'S' : 'N';
        this.dto.flagtrabaja = this.dto.auxFlagTrabaja ? 'S' : 'N';
        this.dto.flagtrabajadentro = this.dto.auxFlagTrabajaDentro ? 'S' : 'N';
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
        if (this.estaVacio(this.dto.tipodependiente)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el parentesco');
            valido = false;
        }
        if (this.estaVacio(this.dto.apellidopaterno)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese el apellido paterno');
            valido = false;
        }
        if (this.estaVacio(this.dto.apellidomaterno)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese el apellido materno');
            valido = false;
        }
        if (this.estaVacio(this.dto.nombres)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese el nombre');
            valido = false;
        }
        if (this.dto.fechanacimiento == null) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese la fecha de nacimiento');
            valido = false;
        }
        if (this.dto.tipodocumentoidentidad == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo de documento');
            valido = false;
        }
        if (this.estaVacio(this.dto.documentoidentidad)) {
            this.mostrarMensajeAdvertenciaNoClear('Ingrese el documento de identidad');
            valido = false;
        }
        if (!this.estaVacio(this.dto.tipodocumentoidentidad) && !this.estaVacio(this.dto.documentoidentidad)) {
            if (this.dto.tipodocumentoidentidad === 'D' || this.dto.tipodocumentoidentidad === 'E') {
                if (this.dto.documentoidentidad.length !== 8) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe tener 8 caracteres');
                    valido = false;
                }
                const dniNumerico = Number(this.dto.documentoidentidad);
                if (isNaN(dniNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valido = false;
                }
            }
            if (this.dto.tipodocumentoidentidad === 'P') {
                if (this.dto.documentoidentidad.length > 12) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 12 caracteres');
                    valido = false;
                }
                const pasptNumerico = Number(this.dto.documentoidentidad);
                if (isNaN(pasptNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valido = false;
                }
            }
            if (this.dto.tipodocumentoidentidad === 'N') {
                if (this.dto.documentoidentidad.length > 15) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 15 caracteres');
                    valido = false;
                }
            }
            if (this.dto.tipodocumentoidentidad === 'X') {
                if (this.dto.documentoidentidad.length > 12) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 12 caracteres');
                    valido = false;
                }
            }
            if (this.dto.tipodocumentoidentidad === 'R') {
                if (this.dto.documentoidentidad.length !== 11) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe tener 11 caracteres');
                    valido = false;
                }
                const rucNumerico = Number(this.dto.documentoidentidad);
                if (isNaN(rucNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valido = false;
                }
            }
        }
        if (this.estaVacio(this.dto.tipovinculo)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo de vínculo');
            valido = false;
        }
        if (this.estaVacio(this.dto.tipodocumentopaternidad)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo de acreditación');
            valido = false;
        }
        if (!this.estaVacio(this.dto.mesconcepcion)) {
            if (this.dto.mesconcepcion.length == 6 || this.dto.mesconcepcion.length == 7) {
                //ok
            }
            else {
                this.mostrarMensajeAdvertenciaNoClear('El formato del periodo Concepción es incorrecto');
                valido = false;
            }
        }
        if (this.dto.lstDireccion.length > 0) {
            if (this.estaVacio(this.dto.lstDireccion[0].pdttipocalle)) {
                this.mostrarMensajeAdvertenciaNoClear('Dirección: Seleccione el tipo');
                valido = false;
            }
            if (this.estaVacio(this.dto.lstDireccion[0].direccion)) {
                this.mostrarMensajeAdvertenciaNoClear('Dirección: Ingrese la dirección');
                valido = false;
            }
            if (this.estaVacio(this.dto.lstDireccion[0].auxUbigeoNombre)) {
                this.mostrarMensajeAdvertenciaNoClear('Dirección: Seleccione Dpto-Prov-Dist');
                valido = false;
            }
        }
        return valido;
    }

    edadFamiliar: string = '';

    transfromarFechaNacimiento() {

        const value = this.dto.fechanacimiento;

        if (value == null) {
            return this.edadFamiliar;
        }

        let v: Date = new Date(value);

        const now = new Date();
        let anios = 0;
        let meses = 0;
        let dias = 0;

        if (v.getTime() > now.getTime()) {
            return;
        }

        while (this.addDate('y', 1, v).getTime() < now.getTime()) {
            v = this.addDate('y', 1, v);
            anios++;

        }

        while (this.addDate('m', 1, v).getTime() < now.getTime()) {
            v = this.addDate('m', 1, v);
            meses++;
        }

        while (this.addDate('d', 1, v).getTime() < now.getTime()) {
            v = this.addDate('d', 1, v);
            dias++;
        }

        var anio = anios;
        var mes = meses;
        var dia = dias;
        this.edadFamiliar = "Años: " + anios + " Meses: " + meses + " Días: " + dias;
        return this.edadFamiliar;
    }

    addDate(pattern: string, mount: number, fecha: Date): Date {

        const f2 = new Date(fecha);

        switch (pattern) {
            case 'y': {
                f2.setFullYear(f2.getFullYear() + mount);
                break;
            }
            case 'm': {
                f2.setMonth(f2.getMonth() + mount);
                break;
            }
            case 'd': {
                f2.setDate(f2.getDate() + mount);
                break;
            }
            default:
                break;
        }

        return f2;
    }

    flagVinculo: boolean = false;

    cambiarSegunParentesco() {
        this.dto.mesconcepcion = null;
        //this.flagCheck = false;
        if (this.dto.tipodependiente == 'C' || this.dto.tipodependiente == 'O') {
            //this.flagCheck = true;
        }
        if (this.dto.tipodependiente == 'H' || this.dto.tipodependiente == 'C' || this.dto.tipodependiente == 'O') {
            this.flagVinculo = true;
        } else {
            this.flagVinculo = false;
        }
    }

    cambioSegunFlagTrabaja() {
        this.dto.empresa = null;
        this.dto.direccionempresa = null;
        this.dto.puesto = null;
        this.dto.tiemposervicio = null;
        this.dto.sueldo = null;
        this.dto.comentarios = null;
    }
    cambioSegunFlagEscolaridad() {
        this.dto.tipoescolaridad = null;
    }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'UBIGEO') {
            this.dto.lstDireccion[0].auxUbigeoNombre = mensage.resultado.descripcion;
            this.dto.lstDireccion[0].departamento = mensage.resultado.departamento;
            this.dto.lstDireccion[0].provincia = mensage.resultado.provincia;
            this.dto.lstDireccion[0].codigopostal = mensage.resultado.zonapostal;
        }
    }

    mostrarSelectorUbigeo() {
        this.postulanteUbicacionGeograficaSelectorComponent.iniciarComponente(new MensajeController(this, 'UBIGEO', ''), this.dto.lstDireccion[0].pais)
    }
    limpiarUbigeo() {
        this.dto.lstDireccion[0].departamento = null;
        this.dto.lstDireccion[0].provincia = null;
        this.dto.lstDireccion[0].codigopostal = null;
        this.dto.lstDireccion[0].auxUbigeoNombre = null;
    }
}
