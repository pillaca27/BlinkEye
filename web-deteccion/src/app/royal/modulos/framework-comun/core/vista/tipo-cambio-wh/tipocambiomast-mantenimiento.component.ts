import { ConstanteComun } from '@framework-comun/ConstanteComun';
 
import { TipocambiomastComunService } from './../../servicio/tipocambiomast-comun.service';
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
import { DtoComunTipocambiomast } from '@framework-comun/core/dominio/dto/DtoComunTipocambiomast';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
 


@Component({
    selector: 'app-tipocambiomast-mantenimiento',
    templateUrl: 'tipocambiomast-mantenimiento.component.html'
})
export class TipocambiomastMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstmonedacodigo: SelectItem[] = [];
    lstmonedadolarcodigo: SelectItem[] = [];
    dto: DtoComunTipocambiomast = new DtoComunTipocambiomast();
    accionnuevo: boolean = false

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private tipocambiomastService: TipocambiomastComunService,
        private confirmationService: ConfirmationService,
        private comunServive: MaPersonagrupoComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        const p1 = this.listarEstados()
        const p2 = this.listarMonedas()

        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.fechacambio = new Date();
                this.dto.estado = 'A';
                this.dto.monedacodigo = 'EX';
                this.dto.monedacambiocodigo = 'LO';
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunTipocambiomast);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.tipocambiomastService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.decimalVar()
                        this.accionnuevo = true
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    decimalVar() {
        this.dto.factorcompra = +this.dto.factorcompra2
        this.dto.factorcomprasbs = +this.dto.factorcomprasbs2
        this.dto.factorventa = +this.dto.factorventa2
        this.dto.factorventasbs = +this.dto.factorventasbs2
        this.dto.factorpromedio = +this.dto.factorpromedio2

    }

    coreGuardar(): void {
        this.calcularPromedio();
        
        if (this.validarMontosSBS() || this.validarMontos()) {
            return
        }
        if (this.accion == this.ACCIONES.NUEVO) {
            this.convertirDate(this.dto.fechacambio)
            this.bloquearPagina();
            this.tipocambiomastService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.tipocambiomastService.actualizar(this.dto).then(res => {
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
                    this.tipocambiomastService.eliminar(this.dto).then(res => {
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

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_tipocambiomast_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void { }

    coreAccion(accion: any): void { }

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    convertirDate(date: Date): string {
        if (!this.esFechaVacia(date)) {
            var diaActual = date.getDate().toString();
            var mesActual = (date.getMonth() + 1).toString();
            var anioActual = date.getFullYear().toString();

            if (diaActual.length === 1) {
                diaActual = '0' + diaActual;
            }

            if (mesActual.length === 1) {
                mesActual = '0' + mesActual;
            }

            this.dto.fechacambiostring = anioActual + mesActual + diaActual
            return this.dto.fechacambiostring
        } else {
            return this.dto.fechacambiostring = ""
        }

    }

    validarMontosSBS(): boolean {
        let resultado = false

        let comprasbs = this.esNumeroVacio(this.dto.factorcomprasbs) ? 0 : this.dto.factorcomprasbs
        let ventasbs = this.esNumeroVacio(this.dto.factorventasbs) ? 0 : this.dto.factorventasbs

        if (comprasbs <= ventasbs) {
            resultado = false
        } else {
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El compra debe ser menor o igual a la venta (SBS)." });
            resultado = true
        }
        return resultado

    }


    validarMontos(): boolean {
        let resultado = false

        let comprasbs = this.esNumeroVacio(this.dto.factorcompra) ? 0 : this.dto.factorcompra
        let ventasbs = this.esNumeroVacio(this.dto.factorventa) ? 0 : this.dto.factorventa

        if (comprasbs <= ventasbs) {
            resultado = false
        } else {
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El compra debe ser menor o igual a la venta" });
            resultado = true
        }
        return resultado

    }

    calcularPromedio() {
        let resultado = this.dto.factorcompra + this.dto.factorventa
        resultado = resultado / 2
        this.dto.factorpromedio = resultado
    }
    onKeypressEvent(event: any) {
        console.log("key", event);
        this.calcularPromedio();
    }

    listarMonedas(): Promise<number> {
        return this.comunServive.listarMonedas().then(res => {
            res.forEach(ele => {
                this.lstmonedacodigo.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
                this.lstmonedadolarcodigo.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

}
