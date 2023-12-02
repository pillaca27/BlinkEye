import { AplicacionesmastComunService } from '../../sg/servicio/aplicacionesmast-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { ParametrosmastComunService } from '../servicio/parametrosmast-comun.service';
import { DtlComunParametrosmast } from '../dominio/lista/DtlComunParametrosmast';
import { Table } from 'primeng/table';
import { ConstanteComun } from './../../ConstanteComun';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { TipocambiomastComunService } from '../servicio/tipocambiomast-comun.service';
import { TituloListadoComponent } from '@framework/angular/controles/titulos/titulo-listado.component';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { DtoComunTipocambiomast } from '../dominio/dto/DtoComunTipocambiomast';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { FiltroComunParametrosmast } from '../dominio/filtro/FiltroComunParametrosmast';

@Component({
    selector: 'app-parametros-listado',
    templateUrl: './parametros-listado.component.html'
}) 
export class ParametrosListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
 
    @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    lstAplicaciones: SelectItem[] = [];
    
    filtro: FiltroComunParametrosmast = new FiltroComunParametrosmast();    

    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

 constructor(        
        messageService: MessageService,        
        private router: Router,        
        private route: ActivatedRoute,
        private parametrosmastService:  ParametrosmastComunService,
        private confirmationService: ConfirmationService,
        private aplicacionesmastService: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {     super(noAuthorizationInterceptor, messageService, servicioComun);          }

    ngOnInit() {
        this.bloquearPagina();     
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this,  this.objetoBoton);
         
        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO,ConstanteComunSistema.APLICACION,ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,this.lstEstados);
        const p2 = this.cargarAplicaciones();
        Promise.all([p1, p2]).then(resp => {            

            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            if(this.estaVacio(this.filtro.aplicacioncodigo)){
                this.filtro.aplicacioncodigo="WH"
            }
           
            this.formularioOninit = false;
            this.desbloquearPagina();
            this.coreBuscar();

        });                
    }

    coreNuevo(): void {
        this.router.navigate([ConstanteComun.ruta_parametros_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }
    coreBusquedaRapida(filtro: string): void {
    }
    coreBuscar(): void {                
        // this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.cargarEvent({first: 0})        
    }    
    
    coreExportar(tipo: string): void {
        this.filtro.tipoexportar = tipo
        this.exportar()
    }
    
    coreMensaje(mensage: MensajeController): void {      
    }
    coreFiltro(val: boolean):void{        
    }        
    coreAccion(accion: string):void{   
        console.log(accion);     
    }        


    cargarAplicaciones(): Promise<number>{
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }

    cargarEvent(event: LazyLoadEvent) {    

        if(this.formularioOninit == true){
            return;
        }
            
            this.filtro.paginacion.paginacionRegistroInicio = event.first;
            sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));            
            this.bloquearPagina();
            this.parametrosmastService.listarPaginado(this.filtro).then(
                res => {
                    this.filtro.paginacion = res;
                    console.log(this.filtro.paginacion);
                    this.desbloquearPagina();
                }
            );

    }
     
    coreEditar(dto: DtlComunParametrosmast){
        this.router.navigate([ConstanteComun.ruta_parametros_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }
    coreVer(dto: DtlComunParametrosmast){
        this.router.navigate([ConstanteComun.ruta_parametros_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtlComunParametrosmast){

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Desea anular este registro?',
            accept: () => {
                this.bloquearPagina();
                this.parametrosmastService.anulardtolistado(dto).then( res =>{
                    this.desbloquearPagina();                
                    if(this.transaccionResultado(res)){
                        this.coreBuscar();
                    }
                });
        },
        key: "confirm"
    });

    }

    coreEliminar(dto: DtlComunParametrosmast){
        this.router.navigate([ConstanteComun.ruta_parametros_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }    

    defaultBuscarPaginacion(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar();
        }
    }


    ngAfterViewInit() {
        this.myDescripcion.nativeElement.focus();
    }

    
    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.parametrosmastService.exportarParametros(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Párametros ', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}

