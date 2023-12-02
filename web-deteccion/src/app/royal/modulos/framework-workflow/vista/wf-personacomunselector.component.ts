import { WfPersonamastComunService } from './../servicio/wfpersonamast-comun.service';
import { FiltroComunPersonamastclis001Wf } from './../dominio/filtro/FiltroComunPersonamastclis001Wf';
import { DominioPaginacion } from './../../../framework/modelo/generico/DominioPaginacion';
import { BaseComponent } from './../../../framework/angular/component/BaseComponent';
import { ServicioComunService } from './../../framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
// import { CentroCostosComunSelectorComponent } from './../../contabilidad/vista/centrocostoscomunselector.component';
// import { AcCostcenterComunService } from './../../contabilidad/servicio/accostcenter-comun.service';
import { Component, OnInit, EventEmitter, Output, ChangeDetectorRef, ViewChild, ElementRef } from '@angular/core';
import { SelectItem, LazyLoadEvent, MessageService } from 'primeng/api';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Table } from 'primeng/table';

@Component({
    selector: 'app-wf-personacomun-selector',
    templateUrl: './wf-personacomunselector.component.html',
    styles:[
        `
        .tablaHeaderIzquierda2 {
            text-align: left !important;
            with:200px;
          }`
    ]
})
export class WfPersonaComunSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    // @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;

    verSelector = false;
    lstEstados: SelectItem[] = [];
    filtro: FiltroComunPersonamastclis001Wf = new FiltroComunPersonamastclis001Wf();
    paginacion: DominioPaginacion = new DominioPaginacion();
    titulo: string;           
    todos: boolean = true;
    verCentroCosto = false;

    flagVerSeleccionar: boolean = false;

    constructor(
        private personaService: WfPersonamastComunService,
        private cdref: ChangeDetectorRef,
        // private centrocostoServ: AcCostcenterComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,        
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        
        this.verSelector = false;
        this.titulo = '';
        this.listarEstados();
        this.filtro.estado = 'A';
    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.mensajeController.componenteDestino.bloquearPagina();
            this.coreBuscar(dt);
        }
    }    

    cargarEvent(event: LazyLoadEvent) {
        // TIPO(PREMOT) : Proveedor / Empleado / Otro
        // TIPO(EMOT) : Empleado / Otro
        // TIPO(PRCLOT) : Proveedor / Cliente / Otro        
        // TIPO(PR) : Proveedor
        if (!this.verSelector) {
            return;
        }

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;        
        this.mensajeController.componenteDestino.bloquearPagina();

        if (this.mensajeController.tipo === 'EMOT') {
            this.personaService.clis001(this.filtro).then(
                res => {
                    if(!this.esListaVacia(res.paginacionListaResultado)){
                        res.paginacionListaResultado.forEach(element => {
                            if (element.escliente === 'S') {
                                element.escliente = true;
                            } else { element.escliente = false; }
                            if (element.esempleado === 'S') {
                                element.esempleado = true;
                            } else { element.esempleado = false; }
                            if (element.esotro === 'S') {
                                element.esotro = true;
                            } else { element.esotro = false; }
                            if (element.esproveedor === 'S') {
                                element.esproveedor = true;
                            } else { element.esproveedor = false; }
                        });
                    }                    

                    this.filtro.paginacion = res;
                    this.mensajeController.componenteDestino.desbloquearPagina();
                    this.desbloquearPagina();
                    this.verSelector = true;
                }
            );        
        } else if (this.mensajeController.tipo === 'PRCLOT') {
            this.personaService.listarclienteproveedorotro(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.escliente === 'S') {
                            element.escliente = true;
                        } else { element.escliente = false; }
                        if (element.esempleado === 'S') {
                            element.esempleado = true;
                        } else { element.esempleado = false; }
                        if (element.esotro === 'S') {
                            element.esotro = true;
                        } else { element.esotro = false; }
                        if (element.esproveedor === 'S') {
                            element.esproveedor = true;
                        } else { element.esproveedor = false; }
                    });

                    this.filtro.paginacion = res;
                    console.log(this.filtro.paginacion);
                    this.mensajeController.componenteDestino.desbloquearPagina();
                    this.desbloquearPagina();
                }
            );
        } else if (this.mensajeController.tipo === 'PR') {
            this.personaService.listarproveedor(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.escliente === 'S') {
                            element.escliente = true;
                        } else { element.escliente = false; }
                        if (element.esempleado === 'S') {
                            element.esempleado = true;
                        } else { element.esempleado = false; }
                        if (element.esotro === 'S') {
                            element.esotro = true;
                        } else { element.esotro = false; }
                        if (element.esproveedor === 'S') {
                            element.esproveedor = true;
                        } else { element.esproveedor = false; }
                    });

                    this.filtro.paginacion = res;
                    console.log(this.filtro.paginacion);
                    this.mensajeController.componenteDestino.desbloquearPagina();
                    this.desbloquearPagina();
                }
            );
        }else if(this.mensajeController.tipo === 'PREMOT'){
            this.personaService.listarclienteproveedorotro(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.escliente === 'S') {
                            element.escliente = true;
                        } else { element.escliente = false; }
                        if (element.esempleado === 'S') {
                            element.esempleado = true;
                        } else { element.esempleado = false; }
                        if (element.esotro === 'S') {
                            element.esotro = true;
                        } else { element.esotro = false; }
                        if (element.esproveedor === 'S') {
                            element.esproveedor = true;
                        } else { element.esproveedor = false; }
                    });

                    this.filtro.paginacion = res;
                    console.log(this.filtro.paginacion);
                    this.mensajeController.componenteDestino.desbloquearPagina();
                    this.desbloquearPagina();
                }
            );
        }else if(this.mensajeController.tipo === 'GESTOR'){
            this.personaService.listargestor(this.filtro).then(
                res => {
                    res.paginacionListaResultado.forEach(element => {
                        if (element.escliente === 'S') {
                            element.escliente = true;
                        } else { element.escliente = false; }
                        if (element.esempleado === 'S') {
                            element.esempleado = true;
                        } else { element.esempleado = false; }
                        if (element.esotro === 'S') {
                            element.esotro = true;
                        } else { element.esotro = false; }
                        if (element.esproveedor === 'S') {
                            element.esproveedor = true;
                        } else { element.esproveedor = false; }
                    });

                    this.filtro.paginacion = res;
                    console.log(this.filtro.paginacion);
                    this.mensajeController.componenteDestino.desbloquearPagina();
                    this.desbloquearPagina();
                }
            );
        }


    }

    listarEstados() {
        this.lstEstados.push({ label: 'Todos', value: null });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });        
        this.lstEstados.push({ label: 'Pendiente', value: 'P' });
    } 

    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(dt: Table){
        dt.reset();
    }

    coreFiltro(flag: boolean){
    };

    coreSalir(){
        this.mensajeController.componenteDestino.desbloquearPagina();        
        this.verSelector = false;
    };

    coreSeleccionar(dto: any){
        if (dto === null) {
            this.mostrarMensajeInfo('Debe seleccionar un registro');
            return;
        }
        this.mensajeController.resultado = dto;        
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    seleccionProveedores: FiltroComunPersonamastclis001Wf[] = [];
    seleccionProv: FiltroComunPersonamastclis001Wf = new FiltroComunPersonamastclis001Wf();

    coreSeleccionarMultiple(){
        if (this.esListaVacia(this.seleccionProveedores)) {
            this.mostrarMensajeInfo('Debe seleccionar un registro');
            return;
        }
        this.mensajeController.resultado = this.seleccionProveedores;      
        this.mensajeController.parametros = 'MULTIPLE';  
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreExportar(){        
    }
    coreMensaje(mensage: MensajeController) {        

        if (mensage.componente == 'CENTROCOSTO') {
            this.filtro.centrocostoNombre = mensage.resultado.localname;
            this.filtro.centrocosto = mensage.resultado.costcenter;
        }
    }
    coreAccion(){        
    }
    coreIniciarComponente(msj: MensajeController){
        this.mensajeController = msj;                
        this.verSelector = true;
        if (this.mensajeController.tipo === 'PREMOT') {
            this.titulo = 'PROVEEDOR / EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'EMOT') {
            this.titulo = 'EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'PRCLOT') {
            this.titulo = 'PROVEEDOR / CLIENTE / OTRO';
        } else if (this.mensajeController.tipo === 'PR') {
            this.titulo = 'PROVEEDOR';
        }else if (this.mensajeController.tipo === 'GESTOR') {
            this.titulo = 'GESTOR';
        }
        this.filtro = new FiltroComunPersonamastclis001Wf();
        this.filtro.estado = 'A';    
        this.verCentroCosto = false; 
    }

    coreIniciarComponenteExtra(msj: MensajeController, centro: string){
        this.mensajeController = msj;                
        this.verSelector = true;
        if (this.mensajeController.tipo === 'PREMOT') {
            this.titulo = 'PROVEEDOR / EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'EMOT') {
            this.titulo = 'EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'PRCLOT') {
            this.titulo = 'PROVEEDOR / CLIENTE / OTRO';
        } else if (this.mensajeController.tipo === 'PR') {
            this.titulo = 'PROVEEDOR';
        }
        this.filtro = new FiltroComunPersonamastclis001Wf();
        this.filtro.estado = 'A'; 
        this.verCentroCosto = true;
        this.filtro.centrocosto = centro; 
        this.filtro.centrocostoNombre = '';

        // this.centrocostoServ.obtenerDto(centro).then(resp=>{
        //     this.filtro.centrocostoNombre = resp.localname;
        // });



    }


    coreIniciarComponenteProv(msj: MensajeController){
        this.mensajeController = msj;                
        this.todos = false;
        this.verSelector = true;
        if (this.mensajeController.tipo === 'PREMOT') {
            this.titulo = 'PROVEEDOR / EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'EMOT') {
            this.titulo = 'EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'PRCLOT') {
            this.titulo = 'PROVEEDOR / CLIENTE / OTRO';
        } else if (this.mensajeController.tipo === 'PR') {
            this.titulo = 'PROVEEDOR';
        }
        this.filtro = new FiltroComunPersonamastclis001Wf();
        this.filtro.estado = 'A'; 
        this.verCentroCosto = false;     
    }

    

    coreIniciarComponenteMultiple(msj: MensajeController){
        this.seleccionProveedores = [];
        this.flagVerSeleccionar = true;
        this.mensajeController = msj;                
        this.verSelector = true;
        if (this.mensajeController.tipo === 'PREMOT') {
            this.titulo = 'PROVEEDOR / EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'EMOT') {
            this.titulo = 'EMPLEADO / OTRO';
        } else if (this.mensajeController.tipo === 'PRCLOT') {
            this.titulo = 'PROVEEDOR / CLIENTE / OTRO';
        } else if (this.mensajeController.tipo === 'PR') {
            this.titulo = 'PROVEEDOR';
        }else if (this.mensajeController.tipo === 'GESTOR') {
            this.titulo = 'GESTOR';
        }
        this.filtro = new FiltroComunPersonamastclis001Wf();
        this.filtro.estado = 'A';
        this.verCentroCosto = false;      
    }


    mostrarSelectorCentroCosto() {
        // this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTO', ''));
    }

    limpiarCentro(){
        this.filtro.centrocosto = null;
        this.filtro.centrocostoNombre = '';
    }

}
