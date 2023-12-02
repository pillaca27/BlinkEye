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
import { DtoComunAcSucursalgrupo } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcSucursalgrupo';
 
import { ConstanteComunContabilidad } from '@framework-comun/contabilidad/ConstanteComunContabilidad';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { AcSucursalgrupoComunService } from '@framework-comun/contabilidad/servicio/acsucursalgrupo-comun.service';
import { AcSucursalgrupomayorComunService } from '@framework-comun/contabilidad/servicio/acsucursalgrupomayor-comun.service';
 



@Component({
    selector: 'app-acsucursalgrupo-mantenimiento',
    templateUrl: 'acsucursalgrupo-mantenimiento.component.html'
})
export class AcSucursalgrupoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('mySucursalgrupo', { static: false }) mySucursalgrupo: ElementRef;
    @ViewChild('mySucursalgrupoMayor', { static: false }) mySucursalgrupoMayor: ElementRef;
    @ViewChild('myDescripcionlocal', { static: false }) myDescripcionlocal: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    lstGrupoMayor: SelectItem[] = [];
    dto: DtoComunAcSucursalgrupo = new DtoComunAcSucursalgrupo();

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private acSucursalgrupoService: AcSucursalgrupoComunService,
        private acSucursalgrupomayorService: AcSucursalgrupomayorComunService,
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
        const p2 = this.cargarGrupoMayor();
        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.mySucursalgrupo.nativeElement.focus();

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunAcSucursalgrupo);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.acSucursalgrupoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }
    cargarGrupoMayor(): Promise<number> {
        this.lstGrupoMayor.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.acSucursalgrupomayorService.listarActivos().then(res => {
            res.forEach(obj => { this.lstGrupoMayor.push({ label: obj.nombre, value: obj.codigo }); });
            return 1;
        });
    }
    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.mySucursalgrupo });
        this.lstControlesRequeridos.push({ control: this.mySucursalgrupoMayor });
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
            this.acSucursalgrupoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.acSucursalgrupoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComunContabilidad.ruta_acsucursalgrupo_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteComunContabilidad.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
