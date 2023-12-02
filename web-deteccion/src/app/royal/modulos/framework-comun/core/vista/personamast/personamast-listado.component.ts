import { ConstanteComun } from '@framework-comun/ConstanteComun';
 
import { PersonamastComunService } from '@framework-comun/core/servicio/personamast-comun.service';
import { FiltroComunPersonamast } from './../../dominio/filtro/FiltroComunPersonamast';
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
import { DtoComunPersonamast } from '@framework-comun/core/dominio/dto/DtoComunPersonamast';
 

@Component({
    selector: 'app-personamast-listado',
    templateUrl: './personamast-listado.component.html',
    styles: [`.tablaHeaderDocumento2 {
        text-align: center !important;
        width: 120px;
      }
      .tablaColumnaDocumento2{
        text-align: center !important;
        width: 120px;
      }
      .tablaHeaderIzquierda2{
        text-align: left !important;
        width: 280px;
    }
    .tablaColumnaIzquierda2{
        text-align: left !important;
        width: 280px;
    }
    `]
})
export class PersonamastListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    lstEstados: SelectItem[] = [];
    lstPersonas:SelectItem[]=[]
    lstMaestros:SelectItem[]=[]
    filtro: FiltroComunPersonamast = new FiltroComunPersonamast();

    checked: boolean = false;
    blockSpecial: RegExp = /^[0-9]*$/;
    
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private personamastService: PersonamastComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR,this,this.objetoBoton);
        

        const p1= this.listarEstados()
        const p2 =this.listarPersonasTipo()
        const p3= this.maestros()
       
        Promise.all([p1, p2, p3]).then((resp) => {       
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.filtro.estado='A'
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
        this.personamastService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    guardarFiltro(){
        sessionStorage.setItem(ConstanteAngular.FILTROSESION,JSON.stringify(this.filtro));
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
        this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunPersonamast){
        if(this.filtro.maestroactualizar=="PRO"){
            dto.esProveedorC=true
            if(dto.esproveedor=='S'){
                this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
            }else{
                this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.NUEVO, JSON.stringify(dto)], { skipLocationChange: true });
            }
        }
        else if(this.filtro.maestroactualizar=="EMP"){
            dto.esEmpleadoC=true
            if(dto.esempleado    =='S'){
                this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
            }else{
                this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.NUEVO, JSON.stringify(dto)], { skipLocationChange: true });
            }
        }
        else if(this.filtro.maestroactualizar=="CLI"){
            return
        }
        else if(this.filtro.maestroactualizar=="PER"){
            dto.esPersonaC=true
            this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
        }
        else{
            this.mostrarMensajeInfo('Debe seleccionar un maestro a actualizar.');
            return
        }

       
    }

    coreVer(dto: DtoComunPersonamast){
        if(this.filtro.maestroactualizar=="PRO"){
            dto.esProveedorC=true
        }
        else if(this.filtro.maestroactualizar=="EMP"){
            dto.esEmpleadoC=true
        }
        else if(this.filtro.maestroactualizar=="CLI"){
            return
        }
        else if(this.filtro.maestroactualizar=="PER"){
            dto.esPersonaC=true
        }
        else{
            this.mostrarMensajeInfo('Debe seleccionar un maestro a actualizar.');
            return
        }
        this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunPersonamast) {
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea anular este registro ? ",
            accept: () => {
            this.bloquearPagina();
            this.personamastService.anular(dto).then((res) => {
            this.desbloquearPagina();
            if (this.transaccionResultado(res))
                this.coreBuscar();
            });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunPersonamast) {
        if(this.filtro.maestroactualizar=="PRO"){
            dto.esProveedorC=true
        }
        else if(this.filtro.maestroactualizar=="EMP"){
            dto.esEmpleadoC=true
        }
        else if(this.filtro.maestroactualizar=="CLI"){
            return
        }
        else if(this.filtro.maestroactualizar=="PER"){
            return
        }
        else{
            this.mostrarMensajeInfo('Debe seleccionar un maestro a actualizar.');
            return
        }
        this.router.navigate([ConstanteComun.ruta_personamast_mantenimiento, this.ACCIONES.ELIMINAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    listarEstados() {
        this.lstEstados.push({ label: 'Seleccione', value: null });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarPersonasTipo() {
        this.lstPersonas.push({ label: 'Seleccione', value: null });
        this.lstPersonas.push({ label: 'Proveedores', value: 'S' });
        this.lstPersonas.push({ label: 'Empleados', value: 'E' });
        this.lstPersonas.push({ label: 'Clientes', value: 'C' });
    }

    maestros() {
        this.lstMaestros.push({ label: 'Seleccione', value: null });
        this.lstMaestros.push({ label: 'Personas', value: 'PER' });
        this.lstMaestros.push({ label: 'Proveedores', value: 'PRO' });
        this.lstMaestros.push({ label: 'Empleados', value: 'EMP' });
        this.lstMaestros.push({ label: 'Clientes', value: 'CLI' });
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.personamastService.exportarPersonas(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Personas', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }
}
