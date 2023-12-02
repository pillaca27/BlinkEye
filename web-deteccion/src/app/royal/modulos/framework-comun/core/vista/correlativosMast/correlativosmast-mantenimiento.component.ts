import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';


import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { CompanyownerComunService } from '@framework-comun/core/servicio/companyowner-comun.service';
import { DtoComunCorrelativosmast } from '@framework-comun/core/dominio/dto/DtoComunCorrelativosmast';
import { CorrelativosmastComunService } from '@framework-comun/core/servicio/correlativosmast-comun.service';
import { CompaniamastComunService } from '@framework-comun/core/servicio/companiamast-comun.service';

@Component({
    selector: 'app-correlativosmast-mantenimiento',
    templateUrl: 'correlativosmast-mantenimiento.component.html'
})
export class CorrelativosmastMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    @ViewChild('myTipoComprobante', { static: false }) myTipoComprobante: ElementRef;
    @ViewChild('mySerie', { static: false }) mySerie: ElementRef;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;
    @ViewChild('myCompania', { static: false }) myCompania: ElementRef;
    @ViewChild('myCorrelativoNumero', { static: false }) myCorrelativoNumero: ElementRef;
    @ViewChild('myCorrelativoDesde', { static: false }) myCorrelativoDesde: ElementRef;
    @ViewChild('myCorrelativoHasta', { static: false }) myCorrelativoHasta: ElementRef;
    @ViewChild('myEstado', { static: false }) myEstado: ElementRef;

    lstEstados: SelectItem[] = [];
    lstCompanias: SelectItem[] = []
    lstTipos: SelectItem[] = []
    dto: DtoComunCorrelativosmast = new DtoComunCorrelativosmast();
    otraCompania: boolean = false
    accionnuevo: boolean = false;
    lstControlesRequeridos: any[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private correlativosmastService: CorrelativosmastComunService,
        private companiamastComunService: CompaniamastComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        /*         const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
                    ConstanteCore.APLICACION,ConstanteCore.MISC_ESTADO_GENERICO,ConstanteCore.DEFECTO_COMPANIA,
                    this.lstEstados); */

        const p1 = this.cargarCompanias()
        const p2 = this.listarTipoItem()
        const p3 = this.listarEstados()

        Promise.all([p1, p2, p3]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.accionnuevo = true;
                this.dto.companiacodigo = "999999"
                this.dto.auxAccion = "N"
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunCorrelativosmast);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.correlativosmastService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.dto.auxAccion = "U"
                        this.otraCompania = resp.companiacodigo.trim() == "999999" ? true : false
                        this.dto.companiacodigo = this.dto.companiacodigo.trim()
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }
    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myTipoComprobante });
        this.lstControlesRequeridos.push({ control: this.mySerie });
        this.lstControlesRequeridos.push({ control: this.myDescripcion });
        if (this.otraCompania) {
            this.lstControlesRequeridos.push({ control: this.myCompania });
        }


        this.lstControlesRequeridos.push({ control: this.myCorrelativoNumero });
        this.lstControlesRequeridos.push({ control: this.myCorrelativoDesde });
        this.lstControlesRequeridos.push({ control: this.myCorrelativoHasta });
        this.lstControlesRequeridos.push({ control: this.myEstado });
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
            this.correlativosmastService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.correlativosmastService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.confirmationService.confirm({
                header: 'Confirmación', icon: 'fa fa-question-circle', message: '¿Desea eliminar este registro?',
                accept: () => {
                    this.bloquearPagina();
                    this.messageService.clear();
                    this.correlativosmastService.eliminar(this.dto).then(res => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res)) {
                            this.coreSalir();
                        }
                    });
                },
                key: "confirm"
            });
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_correlativosmast_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void { }

    coreAccion(accion: any): void { }

    listarEstados() {
        this.lstEstados.push({ label: '--Seleccione--', value: null });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    cargarCompanias(): Promise<number> {
        this.lstCompanias.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.companiamastComunService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstCompanias.push({ label: ele.nombre, value: ele.codigo });


            });
            let x = res.find(x => x.codigo == '999999');
            if (this.esObjetoVacio(x)) {
                this.lstCompanias.push({ label: 'Default (999999)', value: '999999' });
            }
            return 1;
        })
    }

    listarTipoItem(): Promise<number> {
        this.lstTipos.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.correlativosmastService.listarTipos().then(res => {
            res.forEach(ele => {
                this.lstTipos.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    validarCompania() {
        if (this.otraCompania) {
            this.dto.companiacodigo = "999999"
        } else {
            this.dto.companiacodigo = null
        }
    }

    onChangeCompania(event) {
        if (this.dto.companiacodigo == "999999") {
            this.otraCompania = true;
        } else {
            this.otraCompania = false;
        }
    }
}
