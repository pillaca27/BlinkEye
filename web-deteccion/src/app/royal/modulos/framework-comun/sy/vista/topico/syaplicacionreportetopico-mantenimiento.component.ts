
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

import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { SyAplicacionreportetopicoComunService } from '@framework-comun/sy/servicio/syaplicacionreportetopico-comun.service';
import { DtoComunSyAplicacionreportetopico } from '@framework-comun/sy/dominio/dto/DtoComunSyAplicacionreportetopico';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';


@Component({
    selector: 'app-syaplicacionreportetopico-mantenimiento',
    templateUrl: 'syaplicacionreportetopico-mantenimiento.component.html'
})
export class SyAplicacionreportetopicoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myAplicacioncodigo', { static: false }) myAplicacioncodigo: ElementRef;
    @ViewChild('myTopico', { static: false }) myTopico: ElementRef;
    @ViewChild('myDescripcionlocal', { static: false }) myDescripcionlocal: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstAplicaciones: SelectItem[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoComunSyAplicacionreportetopico = new DtoComunSyAplicacionreportetopico();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private syAplicacionreportetopicoService: SyAplicacionreportetopicoComunService,
        private aplicacionesmastService: AplicacionesmastComunService,
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
        const p2 = this.cargarAplicaciones();
        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.myAplicacioncodigo.nativeElement.focus();

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunSyAplicacionreportetopico);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.syAplicacionreportetopicoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }
    cargarAplicaciones(): Promise<number> {
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }
    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myAplicacioncodigo });
        this.lstControlesRequeridos.push({ control: this.myTopico });
        this.lstControlesRequeridos.push({ control: this.myDescripcionlocal });

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
            this.syAplicacionreportetopicoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.syAplicacionreportetopicoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComunSistema.ruta_syaplicacionreportetopico_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteComunSistema.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteComunSistema.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteComunSistema.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
