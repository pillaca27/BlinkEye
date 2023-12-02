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

import { SyProcesomstService } from '../servicio/syprocesomst.servicio';
import { DtoSyProcesomst } from './../dominio/dto/DtoSyProcesomst';
import { FiltroSyProcesomst } from './../dominio/filtro/FiltroSyProcesomst';
import { ConstanteComunSistema } from '../ConstanteComunSistema';
import { SelectorAplicacionesMastComponent } from './selectores/selector-aplicaciones-mast.component';

@Component({
    selector: 'app-syprocesomst-mantenimiento',
    templateUrl: 'syprocesomst-mantenimiento.component.html'
})
export class SyProcesomstMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myAplicacioncodigo', { static: false }) myAplicacioncodigo: ElementRef;
    @ViewChild('myProcesocodigo', { static: false }) myProcesocodigo: ElementRef;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;
    @ViewChild(SelectorAplicacionesMastComponent, { static: false }) selectorAplicacionesMastComponent: SelectorAplicacionesMastComponent;

    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoSyProcesomst = new DtoSyProcesomst();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private syProcesomstService: SyProcesomstService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados);

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.myAplicacioncodigo.nativeElement.focus();

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSyProcesomst);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.syProcesomstService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myAplicacioncodigo });
        this.lstControlesRequeridos.push({ control: this.myProcesocodigo });
        this.lstControlesRequeridos.push({ control: this.myDescripcion });

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
            this.syProcesomstService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.syProcesomstService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComunSistema.ruta_syprocesomst_listado], { skipLocationChange: true })
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
        /*  if (selector == ConstanteSistema.GENERICO_SELECTOR) {
             console.log(selector);
             //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
         } */
    }

    mostrarselectoraplicaciones() {
        this.selectorAplicacionesMastComponent.coreIniciarComponente(new MensajeController(this, 'APLICACION', ''));
    }
    limpiarselectoraplicaciones() {
        this.dto.aplicacioncodigo = null;
        this.dto.descripcioncorta = null;
    }

}
