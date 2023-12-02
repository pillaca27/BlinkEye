import { AccountMstSelectorComponent } from './../../../core/vista/accountmst-selector.component';
 
import { ParametrosmastComunService } from './../../../core/servicio/parametrosmast-comun.service';
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

import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
 

import { AfemstSelectorComponent } from '@framework-comun/core/vista/afemstcomun-selector.component';
import { AcCostcenterComunService } from '../../servicio/accostcenter-comun.service';
 
import { DtoComunAcCostcentermst } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcentermst';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { DtoComunAcCostcenteraccount } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcenteraccount';
import { DtoComunAcCostcentervendor } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcentervendor';
import { DtoComunAcCostcenterdestvalid } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcenterdestvalid';
import { DtoComunAcCostcenterafe } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcenterafe';
import { PersonamastComunService } from '@framework-comun/core/servicio/personamast-comun.service';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { SelectorGrupoSubGrupoComponent } from './selector-grupo-subgrupo/selector-grupo-sub-grupo.component';


@Component({
    selector: 'app-accostcentermst-mantenimiento',
    templateUrl: 'accostcentermst-mantenimiento.component.html',
    styles: [`
    :host ::ng-deep .p-button.p-button-icon-only {
        width: 2.357rem;
        padding: 0.2rem 0;
    }
    :host ::ng-deep .p-datatable-scrollable-body {
      padding-right: 0px;
  }
    :host ::ng-deep .p-field.p-grid, .p-formgrid.p-grid {
        padding: 4px;
    }`]
})
export class AcCostcentermstMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(AccountMstSelectorComponent, { static: false }) accountMstSelectorComponent: AccountMstSelectorComponent;
    @ViewChild(SelectorGrupoSubGrupoComponent, { static: false }) selectorGrupoSubGrupoComponent: SelectorGrupoSubGrupoComponent;
    @ViewChild(AfemstSelectorComponent, { static: false }) afemstSelectorComponent: AfemstSelectorComponent;

    lstEstados: SelectItem[] = [];
    lstSucursal: SelectItem[] = []
    lstClasificacion: SelectItem[] = []
    lstCostosDestino: SelectItem[] = []

    dto: DtoComunAcCostcentermst = new DtoComunAcCostcentermst();

    accionnuevo: boolean = false
    blockSpecial: RegExp = /^[a-zA-Z0-9]*$/;
    parametro1: boolean = false
    parametro2: boolean = false

    parametroDetalle1: boolean = false
    parametroDetalle2: boolean = false
    parametroDetalle3: boolean = false
    parametroDetalle4: boolean = false

    index: number = 0;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private personamastService: PersonamastComunService,
        private comunServive: MaPersonagrupoComunService,
        private acCostcentermstService: AcCostcenterComunService,
        private parametrosmastComunService: ParametrosmastComunService,
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
        const p2 = this.listarParametros1()
        const p3 = this.listarParametros2()
        const p4 = this.listarParametrosDetalle1()
        const p5 = this.listarParametrosDetalle2()
        const p6 = this.listarParametrosDetalle3()
        const p7 = this.listarParametrosDetalle4()
        const p8 = this.listarClasificacion()
        const p9 = this.listarSucursal()
        const p10 = this.listarCostoDestino()

        Promise.all([p1, p2, p3, p4, p5, p6, p7, p8, p9, p10]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.status = 'A'
                this.dto.vendorDescri = this.getUsuarioActual().personaNombreCompleto
                this.dto.vendor = this.getUsuarioActual().personaId
                this.dto.check01 = true
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunAcCostcentermst);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.acCostcentermstService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.validarCheck()
                        this.accionnuevo = true
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    validarCheck() {

        if (this.dto.expenseadministrativeflag == "Y") {
            this.dto.check01 = true
        }
        if (this.dto.expensesalesflag == "Y") {
            this.dto.check02 = true
        }
        if (this.dto.expensefinanceflag == "Y") {
            this.dto.check03 = true
        }
        if (this.dto.expenseproductionflag == "Y") {
            this.dto.check04 = true
        }
        if (this.dto.incomeflag == "Y") {
            this.dto.check05 = true
        }

        this.dto.auxParametro1 = this.parametro1 ? "S" : "N"
        this.dto.auxParametro2 = this.parametro2 ? "S" : "N"
    }

    validarChecksGuardar() {
        this.dto.expenseadministrativeflag = this.dto.check01 ? "Y" : "N"
        this.dto.expensesalesflag = this.dto.check02 ? "Y" : "N"
        this.dto.expensefinanceflag = this.dto.check03 ? "Y" : "N"
        this.dto.expenseproductionflag = this.dto.check04 ? "Y" : "N"
        this.dto.incomeflag = this.dto.check05 ? "Y" : "N"

        this.dto.auxParametro1 = this.parametro1 ? "S" : "N"
        this.dto.auxParametro2 = this.parametro2 ? "S" : "N"
    }


    coreGuardar(): void {
        let guardado = false
        if (this.validarDetalle1()) {
            guardado = true
        }
        if (this.validarDetalle2()) {
            guardado = true
        }
        if (this.validarDetalle3()) {
            guardado = true
        }
        if (this.validarDetalle4()) {
            guardado = true
        }

        if (guardado) {
            return
        }
        this.validarChecksGuardar()
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.acCostcentermstService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.acCostcentermstService.actualizar(this.dto).then(res => {
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
                    this.acCostcentermstService.eliminar(this.dto).then(res => {
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

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarSucursal(): Promise<number> {
        this.lstSucursal.push({ label: 'Todos', value: null });
        return this.comunServive.listarSucusal().then(res => {
            res.forEach(ele => {
                this.lstSucursal.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarClasificacion(): Promise<number> {
        this.lstClasificacion.push({ label: 'Todos', value: null });
        return this.comunServive.listarClasificacion().then(res => {
            res.forEach(ele => {
                this.lstClasificacion.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarCostoDestino(): Promise<number> {
        this.lstCostosDestino.push({ label: 'Todos', value: null });
        return this.comunServive.listarCostoDestino().then(res => {
            res.forEach(ele => {
                this.lstCostosDestino.push({ label: ele.codigo.trim() + " - " + ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }
    listarParametros1(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'COSTCENTVE').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametro1 = res.texto.trim() == "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    listarParametros2(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'VOUSTATCC').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametro2 = res.texto.trim() != "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    listarParametrosDetalle1(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'COSTCENTAC').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametroDetalle1 = res.texto.trim() != "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    listarParametrosDetalle2(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'COSTCENTVE').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametroDetalle2 = res.texto.trim() != "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    listarParametrosDetalle3(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'COSTCENTCD').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametroDetalle3 = res.texto.trim() != "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    listarParametrosDetalle4(): Promise<number> {
        return this.parametrosmastComunService.parametroobtener('999999', 'AC', 'COSTCENTAF').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametroDetalle4 = res.texto.trim() != "N" ? true : false
                    }
                }
                return 1
            }
        )
    }

    onTabChange(event) {
        this.index = event.index
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_accostcentermst_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'PRCLOT') {
            this.dto.vendor = mensage.resultado.persona
            this.dto.vendorDescri = mensage.resultado.busqueda;
        }
        else if (mensage.componente == 'CENTROCOSTO') {
            this.dto.costcenternext = mensage.resultado.costcenter.trim()
            this.dto.costcenternextDescri = mensage.resultado.costcenter.trim() + " - " + mensage.resultado.localname;
        }
        else if (mensage.componente == 'ACCOUNTMST') {
            this.dto.account = mensage.resultado.account.trim()
            this.dto.accountDescri = mensage.resultado.localname;
        }
        else if (mensage.componente == 'ACCOUNTMST2') {
            this.dto.accountdestination = mensage.resultado.account.trim()
            this.dto.accountdestinationDescri = mensage.resultado.localname;
        }
        else if (mensage.componente == 'CUENTADETALLE') {
            this.cuentaTemp.account = mensage.resultado.account.trim()
            this.cuentaTemp.descripcion = mensage.resultado.localname;
        }
        else if (mensage.componente == 'GRUPO') {
            this.dto.costcentergroup = mensage.resultado.costcentergroup
            this.dto.costcentersubgroup = mensage.resultado.costcentermajorgroup
            this.dto.grupo = mensage.resultado.localname;
            this.dto.subgrupo = mensage.resultado.subgrupo
        }
        else if (mensage.componente == 'EMPLEADO') {
            this.empleadoTemp.vendor = mensage.resultado.persona
            this.empleadoTemp.descripcion = mensage.resultado.busqueda;
        }
        else if (mensage.componente == 'AFEMST') {
            this.proyectoTemp.descripcion = mensage.resultado.localname;
            this.proyectoTemp.afe = mensage.resultado.afe.trim();
        }
    }

    coreAccion(accion: any): void { }

    //TAB1
    selector(tipo: string) {

        if (tipo == "EMPSUP") {
            this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PRCLOT', 'EMOT'));
        }
        else if (tipo == "NIVAUT") {
            this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTO', ''));
        }
        else if (tipo == "CUENTA") {
            this.accountMstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ACCOUNTMST', ''));
        }
        else if (tipo == "CUENTA2") {
            this.accountMstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'ACCOUNTMST2', ''));
        }
        else if (tipo == "GRUPO") {
            this.selectorGrupoSubGrupoComponent.coreIniciarComponente(new MensajeController(this, 'GRUPO', ''));
        }
    }

    limpiar(tipo: string) {
        if (tipo == "EMPSUP") {
            this.dto.vendorDescri = ''
            this.dto.vendor = null
        }
        else if (tipo == "NIVAUT") {
            this.dto.costcenternext = ''
            this.dto.costcenternextDescri = ''
        }
        else if (tipo == "CUENTA") {
            this.dto.account = ''
            this.dto.accountDescri = ''
        }
        else if (tipo == "CUENTA2") {
            this.dto.accountdestination = ''
            this.dto.accountdestinationDescri = ''
        }
        else if (tipo == "GRUPO") {
            this.dto.grupo = ''
            this.dto.subgrupo = ''
            this.dto.costcentergroup = ''
            this.dto.costcentersubgroup = ''
        }
    }

    agregarDetalle(tipo: string) {

        if (tipo == "TAB01") {
            let detalle1 = new DtoComunAcCostcenteraccount()
            detalle1.accion = 'N'
            detalle1.status = 'A'
            this.dto.lstDetalle1.push(detalle1)
        }
        else if (tipo == "TAB02") {
            let detalle2 = new DtoComunAcCostcentervendor()
            detalle2.accion = 'N'
            detalle2.status = 'A'
            this.dto.lstDetalle2.push(detalle2)
        }
        else if (tipo == "TAB03") {
            let detalle3 = new DtoComunAcCostcenterdestvalid()
            detalle3.accion = 'N'
            detalle3.status = 'A'
            this.dto.lstDetalle3.push(detalle3)
        }
        else if (tipo == "TAB04") {
            let detalle4 = new DtoComunAcCostcenterafe()
            detalle4.accion = 'N'
            detalle4.status = 'A'
            this.dto.lstDetalle4.push(detalle4)
        }
    }

    cuentaTemp = new DtoComunAcCostcenteraccount()
    agregarCuentaDetalle(dto: any) {
        this.cuentaTemp = dto
        this.accountMstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CUENTADETALLE', ''));
    }

    empleadoTemp = new DtoComunAcCostcentervendor()
    agregarEmpleadoDetalle(dto: any) {
        this.empleadoTemp = dto
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'PRCLOT'));
    }

    proyectoTemp = new DtoComunAcCostcenterafe()
    agregarProyectos(dto: any) {
        this.proyectoTemp = dto
        this.afemstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'AFEMST', ''));
    }

    coreEliminar(tipo: string, dto: any, index: number) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                if (this.accion == this.ACCIONES.NUEVO) {
                    if (tipo == "TAB01") {
                        this.dto.lstDetalle1.splice(index, 1);
                    }
                    else if (tipo == "TAB02") {
                        this.dto.lstDetalle2.splice(index, 1);
                    }
                    else if (tipo == "TAB03") {
                        this.dto.lstDetalle3.splice(index, 1);
                    }
                    else if (tipo == "TAB04") {
                        this.dto.lstDetalle4.splice(index, 1);
                    }

                } else if (this.accion == this.ACCIONES.EDITAR) {
                    dto.accion = "E"
                }
            },
            key: "confirm",
        });
    }

    validarDetalle1() {
        let resultado = false

        if (!this.esListaVacia(this.dto.lstDetalle1)) {
            let array = this.dto.lstDetalle1.filter(x => x.accion != "E")

            let cod = array.map(x => x.account).filter(x => this.estaVacio(x))

            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La cuenta no debe estar vació." });
                resultado = true
            }
            let data = array.map(x => x.account).filter(x => !this.estaVacio(x))

            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La cuenta ya existe en el registro." });
                resultado = true
            }
        }

        return resultado
    }

    validarDetalle2() {
        let resultado = false

        if (!this.esListaVacia(this.dto.lstDetalle2)) {
            let array = this.dto.lstDetalle2.filter(x => x.accion != "E")

            let cod = array.map(x => x.vendor).filter(x => this.esNumeroVacio(x))

            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El empleado no debe estar vació." });
                resultado = true
            }
            let data = array.map(x => x.vendor).filter(x => !this.esNumeroVacio(x))

            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El empleado ya existe en el registro." });
                resultado = true
            }
        }

        return resultado
    }

    validarDetalle3() {
        let resultado = false

        if (!this.esListaVacia(this.dto.lstDetalle3)) {
            let array = this.dto.lstDetalle3.filter(x => x.accion != "E")

            let cod = array.map(x => x.costcenterdestination).filter(x => this.estaVacio(x))

            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El C.Costos destino no debe estar vació." });
                resultado = true
            }
            let data = array.map(x => x.costcenterdestination).filter(x => !this.estaVacio(x))

            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El C.Costos destino ya existe en el registro." });
                resultado = true
            }
        }

        return resultado
    }

    validarDetalle4() {
        let resultado = false

        if (!this.esListaVacia(this.dto.lstDetalle4)) {
            let array = this.dto.lstDetalle4.filter(x => x.accion != "E")

            let cod = array.map(x => x.afe).filter(x => this.estaVacio(x))

            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El proyecto no debe estar vació." });
                resultado = true
            }
            let data = array.map(x => x.afe).filter(x => !this.estaVacio(x))

            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El proyecto ya existe en el registro." });
                resultado = true
            }
        }

        return resultado
    }

    centerCostCod() {
        if (this.parametro1) {
            if (!this.estaVacio(this.dto.costcenter)) {
                this.dto.costcenternext = this.dto.costcenter
                this.dto.costcenternextDescri = this.dto.costcenternext + ' - ' + (this.estaVacio(this.dto.localname) ? '' : this.dto.localname)
            }
        }

    }

    centerCostDescri() {
        if (this.parametro1) {
            if (!this.estaVacio(this.dto.localname)) {
                this.dto.costcenternextDescri = this.dto.costcenter + ' - ' + this.dto.localname
            }
        }

    }
}
