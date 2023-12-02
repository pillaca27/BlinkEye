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
import { DtoHrPostulantedocumento } from '../dominio/DtoHrPostulantedocumento';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { ConstanteFichaEmpleado } from '../../autoservicios/hr/dominio/dto/HrActualizacionFichaEmpleado';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';

@Component({
    templateUrl: './postulante-documentos.component.html',
    selector: 'postulante-documentos'
})
export class PostulanteDocumentosComponent extends FormularioComponent implements OnInit {
    constructor(
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
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
        this.lstTipoDoc.push({ label: '-- Seleccione --', value: null });
        var p = [];
        p.push(this.maMiscelaneosdetalleComunService.listarDtoPorHeader(ConstanteFichaEmpleado.APLICACION_CODIGO, ConstanteFichaEmpleado.MISCELANEO_DOCUMENTOS_PRESENTADOS, ConstanteFichaEmpleado.COMPANIA)
            .then(respuesta => {
                this.tipoDocumentosBean = respuesta;
                respuesta.forEach(obj => this.lstTipoDoc.push({ label: obj.descripcionlocal, value: obj.codigoelemento.trim() })); return 1;
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
    lstTipoDoc: SelectItem[] = [];
    tipoDocumentosBean: any[] = [];
    registroSeleccionado: any;
    lista: any[] = [];


    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.documentosListar(this.postulante).then(res => {
            this.lista = res;
            return 1;
        });
    }

    verMantenimiento: boolean = false;
    dto: DtoHrPostulantedocumento = new DtoHrPostulantedocumento();

    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.dto = new DtoHrPostulantedocumento();
        this.dto.postulante = this.postulante.postulante;
        this.dto.fecha = new Date();
        this.verMantenimiento = true;
    }

    editar(dto: any) {
        this.accion = this.ACCIONES.EDITAR;
        this.dto = this.copiar(dto);
        this.dto.documento = this.trim(this.dto.documento);
        this.dto.auxFlagPresento = this.dto.flagpresento == 'S' ? true : false;
        this.verMantenimiento = true;
    }

    ver(dto: any) {
        this.accion = this.ACCIONES.VER;
        this.dto = this.copiar(dto);
        this.dto.documento = this.trim(this.dto.documento);
        this.dto.auxFlagPresento = this.dto.flagpresento == 'S' ? true : false;
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
                this.hrPostulanteService.crud(dto, 'documentoEliminar').then(res => {
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
        var accionCRUD = this.accion == this.ACCIONES.NUEVO ? 'documentoRegistrar' : 'documentoActualizar';
        var mensajeExito = this.accion == this.ACCIONES.NUEVO ? this.getMensajeGrabadoSinCodigo() : this.getMensajeActualizadoSinCodigo();
        this.dto.auxNombreTipoDocumento = this.obtenerLabelPorCombo(this.lstTipoDoc, this.dto.documento);
        this.dto.flagpresento = this.dto.auxFlagPresento ? 'S' : 'N';
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
        if (this.estaVacio(this.dto.documento)) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el tipo de documento');
            valido = false;
        }
        if (this.dto.fecha == null) {
            this.mostrarMensajeAdvertenciaNoClear('Seleccione el fecha');
            valido = false;
        }
        if (this.tieneVencimiento(this.dto.documento) == 'Si') {
            if (this.dto.fechavencimiento == null) {
                this.mostrarMensajeAdvertenciaNoClear('La Fecha de Vencimiento es requerida');
                valido = false;
            }
            else {
                //Validar que la fecha vencimiento sea posterior a la fecha
                var f1 = this.dto.fecha;
                var f2 = this.dto.fechavencimiento;
                f1.setHours(0, 0, 0, 0);
                f2.setHours(0, 0, 0, 0);

                if (f1 > f2) {
                    this.mostrarMensajeAdvertenciaNoClear('La Fecha de Vencimiento debe ser posterior o igual a la Fecha.');
                    valido = false;
                }
            }
        }
        if (this.estaVacio(this.dto.auxContenido)) {
            this.mostrarMensajeAdvertenciaNoClear('El documento es requerido');
            valido = false;
        }
        return valido;
    }


    descargarDocumento2() {
        this.descargar(this.dto);
    }

    descargar(dto: DtoHrPostulantedocumento) {
        if (dto.auxContenido == null) {
            this.mostrarMensajeAdvertencia('No se encontró el documento');
            return;
        }
        const a = document.createElement('a');
        a.href = 'data:application/octet-stream;base64,' + dto.auxContenido;

        a.setAttribute('download', dto.rutadocumento);
        const b = document.createEvent('MouseEvents');
        b.initEvent('click', false, true);
        a.dispatchEvent(b);
    }

    tieneVencimiento(tipo: string) {
        if (this.estaVacio(tipo)) {
            return '';
        }
        tipo = tipo.trim();
        var enc = this.tipoDocumentosBean.find(x => x.codigoelemento.trim() == tipo.trim());
        if (enc) {
            if (this.trimNoNulo(enc.valorcodigo1) == 'S') {
                return 'Si';
            }
            else {
                return 'No'
            }
        }
        return '';
    }
    mesesVencimiento(tipo: string) {
        if (this.estaVacio(tipo)) {
            return '';
        }
        tipo = tipo.trim();
        var enc = this.tipoDocumentosBean.find(x => x.codigoelemento.trim() == tipo.trim());
        if (enc) {
            return enc.valornumerico;
        }
        return '';
    }

    cargarFolio(event: any) {
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
            this.dto.auxContenido = result;
            this.dto.rutadocumento = files[0].name;
        };
    }

}

