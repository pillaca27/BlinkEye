import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { ConstanteSg } from './../../ConstanteSg';
import { DtoSeguridadgrupo } from './../../dominio/dto/DtoSeguridadgrupo';
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
import { SeguridadgrupoService } from '../../servicio/seguridadgrupo.servicio';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';



@Component({
    selector: 'app-seguridadgrupo-mantenimiento',
    templateUrl: 'seguridadgrupo-mantenimiento.component.html'
})
export class SeguridadgrupoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myAplicacioncodigo', { static: false }) myAplicacioncodigo: ElementRef;
    @ViewChild('myGrupo', { static: false }) myGrupo: ElementRef;
    @ViewChild('myDescripciOn', { static: false }) myDescripciOn: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoSeguridadgrupo = new DtoSeguridadgrupo();
    lstAplicaciones: SelectItem[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private seguridadgrupoService: SeguridadgrupoService,
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
            ConstanteSg.APLICACION, ConstanteSg.MISC_ESTADO_GENERICO, ConstanteSg.DEFECTO_COMPANIA,
            this.lstEstados);
        const p2 = this.cargarAplicaciones();
        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                //this.myAplicacioncodigo.nativeElement.focus();
                this.dto.imagen = 'fad fa-folder-tree';
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSeguridadgrupo);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.seguridadgrupoService.obtenerDto(tempdto).then(resp => {
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
        this.lstControlesRequeridos.push({ control: this.myGrupo });
        this.lstControlesRequeridos.push({ control: this.myDescripciOn });

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
            this.seguridadgrupoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.seguridadgrupoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteSg.ruta_seguridadgrupo_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteSg.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
