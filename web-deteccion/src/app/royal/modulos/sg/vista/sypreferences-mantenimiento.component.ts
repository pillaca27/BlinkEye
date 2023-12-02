import { SelectorAplicacionesMastComponent } from './../../framework-comun/sy/vista/selectores/selector-aplicaciones-mast.component';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { SelectItem, MessageService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { InputNumber } from 'primeng/inputnumber';
import { Calendar } from '@fullcalendar/core';
import { Dropdown } from 'primeng/dropdown';

import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';


import { SyPreferencesService } from '../servicio/sypreferences.servicio';
import { DtoSyPreferences } from './../dominio/dto/DtoSyPreferences';
import { ConstanteSg } from '../ConstanteSg';

@Component({
    selector: 'app-sypreferences-mantenimiento',
    templateUrl: 'sypreferences-mantenimiento.component.html'
})
export class SyPreferencesMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myUsuario', { static: false }) myUsuario: ElementRef;
    @ViewChild('myPreference', { static: false }) myPreference: ElementRef;

    @ViewChild('myValorString', { static: false }) myValorString: ElementRef;
    @ViewChild('myValorNumero', { static: false }) myValorNumero: ElementRef;
    @ViewChild('myValorFecha', { static: false }) myValorFecha: ElementRef;

    @ViewChild(SelectorAplicacionesMastComponent, { static: false }) selectorAplicacionesMastComponent: SelectorAplicacionesMastComponent;

    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    lstTipoValor: SelectItem[] = [];
    dto: DtoSyPreferences = new DtoSyPreferences();
    ocultarcadena = false;
    ocultarnumero = false;
    ocultarfecha = false;
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private syPreferencesService: SyPreferencesService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
        this.cargarTipoValor();
        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados);

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.myUsuario.nativeElement.focus();

                this.dto.usuario = 'SYSTEM';
                this.dto.aplicacioncodigo = this.getUsuarioActual().aplicacionCodigo;
                this.dto.descripcioncorta = this.getUsuarioActual().aplicacionNombre;
                this.dto.tipovalor = 'C';
                this.dto.ultimafechamodif = new Date();
                this.dto.ultimousuario = this.getUsuarioActual().usuario;
                this.onCambioValidado();
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSyPreferences);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.syPreferencesService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.desbloquearPagina();
                        this.onCambioValidado();
                    });
                }
            }


        });
    }
    cargarTipoValor() {
        this.lstTipoValor.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.lstTipoValor.push({ label: 'Numérico', value: 'N' });
        this.lstTipoValor.push({ label: 'Fecha', value: 'D' });
        this.lstTipoValor.push({ label: 'Cadena', value: 'C' });
    }
    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myUsuario });
        this.lstControlesRequeridos.push({ control: this.myPreference });

        switch (this.dto.tipovalor) {
            case 'N':
                //this.ocultarnumero = true;
                this.lstControlesRequeridos.push({ control: this.myValorNumero });
                break;
            case 'D':
                //this.ocultarfecha = true;
                this.lstControlesRequeridos.push({ control: this.myValorFecha });
                break;
            case 'C':
                //this.ocultarcadena = true;
                this.lstControlesRequeridos.push({ control: this.myValorString });
                break;
            default: ''
                break;
        }

    }

    coreGuardar(): void {
        this.cargarControlesRequeridos();
        var mensajes = this.validarControlesRequeridos(this.lstControlesRequeridos);
        if (mensajes.length > 0) {
            this.mostrarMensajeListado(mensajes);
            return;
        }

        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.syPreferencesService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.syPreferencesService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteSg.ruta_sypreferences_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteComunSistema.GENERICO_APLICACION) {
            this.dto.aplicacioncodigo = mensage.resultado.aplicacioncodigo;
            this.dto.descripcioncorta = mensage.resultado.descripcioncorta;
        }
    }

    coreMostrarSelector(selector: string) {
        /* if (selector == ConstanteComunSistema.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        } */
    }

    onChange() {
        this.ocultarcadena = false;
        this.ocultarnumero = false;
        this.ocultarfecha = false;

        this.dto.valorstring = null;
        this.dto.valorfecha = null;
        this.dto.valornumero = null;
        this.onCambioValidado();
    }
    onCambioValidado() {
        switch (this.dto.tipovalor) {
            case 'N':
                this.ocultarnumero = true;
                break;
            case 'D':
                this.ocultarfecha = true;
                break;
            case 'C':
                this.ocultarcadena = true;
                break;
            default: ''
                break;
        }
    }

    mostrarselectoraplicaciones() {
        this.selectorAplicacionesMastComponent.coreIniciarComponente(new MensajeController(this, 'APLICACION', ''));
    }
    limpiarselectoraplicaciones() {
        this.dto.aplicacioncodigo = null;
        this.dto.descripcioncorta = null;
    }




}
