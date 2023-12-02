import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { DtoPrCuentacorriente, DtoPrPrestamo } from '../dominio/dto/DtoPrPrestamo';
import { PrPrestamoServicio } from '../servicio/PrPrestamoServicio';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Component({
    templateUrl: './solicitud-prestamo-mantenimiento.component.html'
})
export class SolicitudPrestamoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    dtoBean: DtoPrPrestamo = new DtoPrPrestamo();

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;

    lstTipoDocumento: SelectItem[] = [];
    lstConcepto: SelectItem[] = [];
    lstTipo: SelectItem[] = [];
    lstMonedas: SelectItem[] = [];

    constructor(
        private confirmationService: ConfirmationService,
        private router: Router,
        private prPrestamoServicio: PrPrestamoServicio,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }


    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.bloquearPagina();

        var p1 = this.prPrestamoServicio.lstTipoPrestamo(new DtoTabla()).then(
            res => {
                this.lstTipo.push({ value: null, label: ' -- Seleccione --' });
                res.forEach(r => {
                    this.lstTipo.push({ value: r.codigo.trim(), label: r.descripcion.trim() });
                });
            }
        );

        var p2 = this.prPrestamoServicio.lstConcepto().then(
            res => {
                this.lstConcepto.push({ value: null, label: ' -- Seleccione --' });
                res.forEach(r => {
                    this.lstConcepto.push({ value: r.codigo.trim(), label: r.descripcion.trim() });
                });
            }
        );

        var p3 = this.prPrestamoServicio.lstTipoDocumento().then(
            res => {
                this.lstTipoDocumento.push({ value: null, label: ' -- Seleccione --' });
                res.forEach(r => {
                    this.lstTipoDocumento.push({ value: r.codigo.trim(), label: r.descripcion.trim() });
                });
            }
        );

        this.lstMonedas.push({ value: 'LO', label: 'Moneda Local' });
        this.lstMonedas.push({ value: 'EX', label: 'Moneda Extranjera' });

        Promise.all([p1, p2, p3]).then(
            res => {
                if (this.accion == this.ACCIONES.NUEVO) {
                    this.nuevo();
                }
                else {
                    this.obtenerPorUUID(this.route.snapshot.params['uuid'] as string);
                }
            }
        );
    }

    nuevo() {
        this.prPrestamoServicio.solicitudNuevo().then(
            res => {
                this.dtoBean = res;
                this.desbloquearPagina();
            }
        );
    }

    obtenerPorUUID(uuid: string) {
        var dto = new DtoPrPrestamo();
        if (this.accion == this.ACCIONES.WORKFLOW)
            dto.transaccionUUID = uuid;
        else
            dto.uuid = uuid;

        this.prPrestamoServicio.solicitudObtenerPorUuid(dto).then(
            res => {
                this.dtoBean = res;
                this.desbloquearPagina();
                if (this.dtoBean.transaccionUUID != null) {
                    this.wfTransaccionSeguimientoComponent.iniciarComponente(this.dtoBean.transaccionUUID);
                    this.wfTransaccionBotonesComponent.iniciarComponente(this.dtoBean.transaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
                }
            }
        );
    }

    coreGuardar(): void {
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.prPrestamoServicio.solicitudRegistrar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeGrabado(res.nroprestamo));
                    this.coreSalir();
                }
            });
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.prPrestamoServicio.solicitudActualizar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeActualizado(res.nroprestamo));
                    this.coreSalir();
                }
            });
        }
    }
    coreSalir(): void {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.router.navigate([ConstanteWorkflow.ruta_wf_aprobacion_listado]);
        }
        else {
            this.router.navigate([ConstanteAutoservicios.ruta_solicitud_prestamo_listado]);
        }
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        throw new Error('Method not implemented.');
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }
    agregarPeriodo() {
        var nuevoPeriodo = new DtoPrCuentacorriente();
        var hoy = new Date();

        if (this.dtoBean.lstPeriodos.length == 0) {
            nuevoPeriodo.periodo = hoy.getFullYear().toString() + ((hoy.getMonth() + 1) > 9 ? (hoy.getMonth() + 1).toString() : '0' + (hoy.getMonth() + 1));
        }
        else {
            var periodoMaximo = '000000';

            console.log(this.dtoBean.lstPeriodos);

            this.dtoBean.lstPeriodos.forEach(x => {

                var anio1 = parseInt(x.periodo.substring(0, 4));
                var mes1 = parseInt(x.periodo.substring(4, 6));

                var anio2 = parseInt(periodoMaximo.substring(0, 4));
                var mes2 = parseInt(periodoMaximo.substring(4, 6));

                if ((anio1 * 12 + mes1) > (anio2 * 12 + mes2)) {
                    periodoMaximo = x.periodo;
                }
            });

            console.log(periodoMaximo);

            var anio = parseInt(periodoMaximo.substring(0, 4));
            var mes = parseInt(periodoMaximo.substring(4, 6));

            if (mes == 12) {
                anio = anio + 1;
                mes = 1;
            }
            else {
                mes++;
            }
            nuevoPeriodo.periodo = anio + '' + (mes > 9 ? mes.toString() : '0' + mes);
        }

        nuevoPeriodo.cuotaquincena = 0;
        nuevoPeriodo.cuotagratificacion = 0;
        nuevoPeriodo.cuotautilidades = 0;
        nuevoPeriodo.cuotafindemes = 0;
        nuevoPeriodo.cuotapago = 0;
        var tmp = [...this.dtoBean.lstPeriodos];
        tmp.push(nuevoPeriodo);
        this.dtoBean.lstPeriodos = tmp;
    }
    eliminarPeriodo(row: DtoPrCuentacorriente) {
        var tmp = [...this.dtoBean.lstPeriodos];
        tmp = tmp.filter(x => x != row);
        this.dtoBean.lstPeriodos = tmp;
    }

    ordenarPeriodo() {
        var tmp = [...this.dtoBean.lstPeriodos];
        tmp.sort((a, b) => (a.periodo > b.periodo) ? 1 : -1);
        this.dtoBean.lstPeriodos = tmp;
    }

    _montomensual = null;
    _apagar = null;
    _diferencial = null;
    _ultimacuota = null;

    aplicarPeriodo() {
        if (this.dtoBean.montosolicitado == null || this.dtoBean.montosolicitado == undefined || this.dtoBean.montosolicitado == 0) {
            this.mostrarMensajeAdvertencia('Debe ingresar primero el monto del Préstamo');
            return;
        }
        if ((this.dtoBean.auxNroCuotas == null || this.dtoBean.auxNroCuotas == 0) && (this.dtoBean.auxMontoMensual == null || this.dtoBean.auxMontoMensual == 0)) {
            this.mostrarMensajeAdvertencia('Debe de elegir entre número de cuotas o monto mensual');
            return;
        }
        if (this.estaVacio(this.dtoBean.auxPeriodo)) {
            this.mostrarMensajeAdvertencia('Debe ingresar el periodo aaaa-mm');
            return;
        }

        if (this.dtoBean.auxPeriodo.length == 7) {
            this.dtoBean.auxPeriodo = this.dtoBean.auxPeriodo.replace('-', '');
        }

        this._montomensual = this.dtoBean.auxMontoMensual;
        this._apagar = 0;
        this._diferencial = 0;
        this._ultimacuota = 0;

        if (this.dtoBean.auxNroCuotas > 0) {
            this._montomensual = Math.round(this.dtoBean.montoprestamo / this.dtoBean.auxNroCuotas);
            this._apagar = Math.round(this._montomensual * this.dtoBean.auxNroCuotas);
            this._diferencial = this.dtoBean.montoprestamo - this._apagar;
        }
        else {
            if (this._montomensual > 0) {
                if (this.dtoBean.montoprestamo % this._montomensual > 0) {
                    this.dtoBean.auxNroCuotas = Math.floor(this.dtoBean.montoprestamo / this._montomensual);
                    this._apagar = Math.round(this._montomensual * this.dtoBean.auxNroCuotas);
                    this._ultimacuota = this.dtoBean.montoprestamo - this._apagar;
                    this.dtoBean.auxNroCuotas = this.dtoBean.auxNroCuotas + 1;
                }
                else {
                    this.dtoBean.auxNroCuotas = Math.floor(this.dtoBean.montoprestamo / this._montomensual);
                }
            }
        }

        if (this.dtoBean.lstPeriodos.length > 0) {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: 'Los datos en el Calendario de Pagos serán modificados. ¿Desea continuar?',
                key: "confirm",
                accept: () => {
                    this.aplicarPeriodoPart2();
                },
            });
        }
        else {
            this.aplicarPeriodoPart2();
        }
    }

    aplicarPeriodoPart2() {
        var temp = [];

        var _ano = parseInt(this.dtoBean.auxPeriodo.substring(0, 4));
        var _mes = parseInt(this.dtoBean.auxPeriodo.substring(4, 6));
        var _periodo = _ano.toString() + this.padLeadingZeros(_mes, 2);

        for (let _counter = 1; _counter <= this.dtoBean.auxNroCuotas; _counter++) {
            if (_mes > 12) {
                _mes = 1;
                _ano++;
            }
            _periodo = _ano.toString() + this.padLeadingZeros(_mes, 2);

            var nuevoPeriodo = new DtoPrCuentacorriente();
            temp.push(nuevoPeriodo);
            nuevoPeriodo.periodo = _periodo;

            if (_counter == this.dtoBean.auxNroCuotas) {
                if (this._ultimacuota > 0) {
                    this._montomensual = this._ultimacuota;
                }
                else {
                    this._montomensual = this._montomensual + this._diferencial;
                }
            }
            nuevoPeriodo.cuotaquincena = 0;
            nuevoPeriodo.cuotagratificacion = 0;
            nuevoPeriodo.cuotautilidades = 0;
            nuevoPeriodo.cuotafindemes = this._montomensual;
            nuevoPeriodo.cuotapago = this._montomensual;
            _mes++;
        }

        this.dtoBean.lstPeriodos = temp;
    }

    padLeadingZeros(num, size) {
        var s = num + "";
        while (s.length < size) s = "0" + s;
        return s;
    }

    uiUpdateMontoSolicitado(valor) {
        var monto = valor;
        if (monto != null && monto != undefined) {
            monto = monto.replaceAll(',', '');
        }
        if (monto == null) monto = 0;
        else if (monto < 0) monto = 0;
        monto = parseFloat(monto);
        this.dtoBean.montoprestamo = monto + Math.round((monto * this.dtoBean.interesprestamo / 100));
    }

    uiUpdateMontoQuincena(valor, row: DtoPrCuentacorriente) {
        var monto = valor;
        if (monto != null && monto != undefined) {
            monto = monto.replaceAll(',', '');
        }
        if (monto == null) monto = 0;
        else if (monto < 0) monto = 0;
        monto = parseFloat(monto);
        row.cuotapago = monto + row.cuotagratificacion + row.cuotautilidades + row.cuotafindemes;
    }
    uiUpdateMontoGratificacion(valor, row: DtoPrCuentacorriente) {
        var monto = valor;
        if (monto != null && monto != undefined) {
            monto = monto.replaceAll(',', '');
        }
        if (monto == null) monto = 0;
        else if (monto < 0) monto = 0;
        monto = parseFloat(monto);
        row.cuotapago = row.cuotaquincena + monto + row.cuotautilidades + row.cuotafindemes;
    }
    uiUpdateMontoUtilidades(valor, row: DtoPrCuentacorriente) {
        var monto = valor;
        if (monto != null && monto != undefined) {
            monto = monto.replaceAll(',', '');
        }
        if (monto == null) monto = 0;
        else if (monto < 0) monto = 0;
        monto = parseFloat(monto);
        row.cuotapago = row.cuotaquincena + row.cuotagratificacion + monto + row.cuotafindemes;
    }
    uiUpdateMontoFinDeMes(valor, row: DtoPrCuentacorriente) {
        var monto = valor;
        if (monto != null && monto != undefined) {
            monto = monto.replaceAll(',', '');
        }
        if (monto == null) monto = 0;
        else if (monto < 0) monto = 0;
        monto = parseFloat(monto);
        row.cuotapago = row.cuotaquincena + row.cuotagratificacion + row.cuotautilidades + monto;
    }
    sumarCuotaMensuales() {
        var m = 0;
        this.dtoBean.lstPeriodos.forEach(r => {
            var monto = r.cuotapago;
            if (monto == null) monto = 0;
            else if (monto < 0) monto = 0;
            m = m + monto;
        });
        return m;
    }
}
