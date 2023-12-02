import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';
 
 
import { TipopagoComunService } from '@framework-comun/core/servicio/tipopago-comun.service';
import { DtoComunTipopago } from '@framework-comun/core/dominio/dto/DtoComunTipopago';
 
 

@Component({
    selector: 'app-tipopago-mantenimiento',
    templateUrl: 'tipopago-mantenimiento.component.html'
})
export class TipopagoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    dto: DtoComunTipopago = new DtoComunTipopago();
    accionnuevo: boolean = false
    blockSpecial: RegExp = /^[a-zA-Z0-9]*$/;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private tipopagoService: TipopagoComunService,
        servicioComun: ServicioComunService,
        private confirmationService: ConfirmationService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        /*         const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
                    ConstanteComunSistema.APLICACION,ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,
                    this.lstEstados); */

        const p1 = this.listarEstados()

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A'
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunTipopago);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.tipopagoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.accionnuevo=true
                        this.validarChecksIniciar()
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    validarChecksIniciar() {
        if (this.dto.restringidoflag == "S") {
            this.dto.restringidocheck = true
        }
        if (this.dto.generarnumeracionflag == "S") {
            this.dto.generanumerocheck = true
        }
        if (this.dto.vouchersumarizadoflag == "S") {
            this.dto.generavouchercheck = true
        }
    }

    coreGuardar(): void {
        this.validarChecksGuardar()
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.tipopagoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.tipopagoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.tipopagoService.eliminar(this.dto).then(res => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res)) {
                            this.coreSalir();
                        }
                    }
                    );
                },
                key: "confirm",
            });

        }
    }

    validarChecksGuardar() {
        this.dto.restringidoflag = this.dto.restringidocheck ? "S" : "N"
        this.dto.generarnumeracionflag = this.dto.generanumerocheck ? "S" : "N"
        this.dto.vouchersumarizadoflag = this.dto.generavouchercheck ? "S" : "N"
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_tipopago_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void { }

    coreAccion(accion: any): void { }

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }
}
