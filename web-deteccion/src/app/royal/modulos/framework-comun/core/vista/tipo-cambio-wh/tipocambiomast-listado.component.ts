import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { TipocambiomastComunService } from './../../servicio/tipocambiomast-comun.service';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
 
 

import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
 
import { FiltroComunTipocambiomast } from '@framework-comun/core/dominio/filtro/FiltroComunTipocambiomast';
import { DtoComunTipocambiomast } from '@framework-comun/core/dominio/dto/DtoComunTipocambiomast';

@Component({
    selector: 'app-tipocambiomast-listado',
    templateUrl: './tipocambiomast-listado.component.html',
})
export class TipocambiomastListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    filtro: FiltroComunTipocambiomast = new FiltroComunTipocambiomast();
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;
    
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private tipocambiomastService: TipocambiomastComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        console.log('ngOnInit');
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR,this,this.objetoBoton);

        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO,ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,this.lstEstados);

        Promise.all([p1]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.desbloquearPagina();
            this.coreBuscar();
        });
    }

    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar();
        }
    }

    coreBuscar(): void {
        this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.grillaCargarDatos({ first: this.dataTableComponent.first });
    }

    grillaCargarDatos(event: LazyLoadEvent) {
        if (this.formularioOninit == true) return;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION,JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.filtro.fechacambiostring= this.esFechaVacia(this.filtro.fechacambio)? "" : this.convertirDate(this.filtro.fechacambio)
        this.tipocambiomastService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    convertirDate(date: Date): string{
        var diaActual = date.getDate().toString();
        var mesActual = (date.getMonth() + 1).toString();
        var anioActual = date.getFullYear().toString();
    
        if (diaActual.length === 1) {
            diaActual = '0' + diaActual;
        }
    
        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }
    
        return anioActual + mesActual + diaActual 
    }


    coreBusquedaRapida(filtro: string): void {}

    coreExportar(tipo: string): void {
        this.filtro.tipoexportar = tipo
        this.exportar()
    }


    coreMensaje(mensage: MensajeController): void {}

    coreFiltro(val: boolean): void {}

    coreAccion(accion: string): void {}

    coreNuevo(): void {
        console.log('coreNuevo');
        this.router.navigate([ConstanteComun.ruta_tipocambiomast_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunTipocambiomast){
        this.router.navigate([ConstanteComun.ruta_tipocambiomast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunTipocambiomast){
        this.router.navigate([ConstanteComun.ruta_tipocambiomast_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunTipocambiomast) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.tipocambiomastService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunTipocambiomast) {
        this.router.navigate([ConstanteComun.ruta_tipocambiomast_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.tipocambiomastService.exportarTipoCambio(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Tipo de Cambio Diario', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }


    obtenerTipoCambio(){
        this.bloquearPagina();
        this.tipocambiomastService.auxObtenerTipoCambioCreacionDefault().then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {                
            }            
            this.coreBuscar();
        });
    }

}
