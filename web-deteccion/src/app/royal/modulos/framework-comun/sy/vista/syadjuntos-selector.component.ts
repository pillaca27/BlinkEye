import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService, ConfirmationService } from 'primeng/api';
import { SyAdjuntoComunService } from '../servicio/syadjunto-comun.service';
import { SyAdjunto } from '../dominio/dto/SyAdjunto';
import { PersonamastService } from '../../../requerimiento/personamast/servicio/personamast.servicio';

@Component({
    selector: 'app-syadjuntos-selector',
    templateUrl: './syadjuntos-selector.component.html'
})
export class SyAdjuntosSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;    
    verSelectorMantenimiento: Boolean = false;    
    registrosPorPagina: number = 7;
    registroSeleccionado: any;
    listado: SyAdjunto[] = [];        
    cantidad: number = 0;    

    filtro: SyAdjunto = new SyAdjunto();

    bean: SyAdjunto = new SyAdjunto();
    accionMan: string = 'N';

    accionVentana: any;
            
    nuevoSyAdjunto = false;
    selectedSyAdjunto: SyAdjunto = new SyAdjunto();

    ruta: string;

    puedeEditar: boolean= true;
    parametro:string=''


    constructor(        
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService,
        private personamastService: PersonamastService,
        private confirmationService: ConfirmationService,
        private servicio: SyAdjuntoComunService        
        ) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

    ngOnInit() {

    }            

    listarParametros(): Promise<number> {
        return this.personamastService.parametroobtener('999999', 'SY', 'DIRFILE').then(
            res => {
                if (res) {
                    if (!this.estaVacio(res.texto)) {
                        this.parametro = res.texto.trim()
                    }
                }
                return 1
            }
        )
    }
    buscar() {
        debugger;
        this.mensajeController.componenteDestino.bloquearPagina();
        this.servicio.listar(this.filtro)
            .then(pg => {
                this.listado = pg;
                this.cantidad = this.listado.length;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;

                this.mensajeController.resultado = this.cantidad;
                this.mensajeController.componente= 'CANTIDADADJUNTOS';
                this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);

            });
    }


    obtenerCantidad() {                
        this.servicio.listar(this.filtro)
            .then(pg => {
                this.listado = pg;
                this.cantidad = this.listado.length;
                this.mensajeController.resultado = this.cantidad;
                this.mensajeController.componente= 'CANTIDADADJUNTOS';
                this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);

            });
    }    

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }

    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(dt: Table){
        this.buscar();
    }

    coreFiltro(flag: boolean){
    };

    coreSalir(){
        this.mensajeController.componenteDestino.desbloquearPagina();        
        this.verSelector = false;
        
        this.mensajeController.componente= 'CERRARADJUNTOS';
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);

    };

    coreSeleccionar(dto: any){
        this.mensajeController.resultado = dto;        
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreExportar(){        
    }
    coreMensaje(){    
    }
    coreAccion(){        
    }

    iniciarFiltros(tabla: string, codigo: string, accionVentana: any, ruta: string){
        this.filtro = new SyAdjunto();
        this.filtro.pk.nombretabla = tabla;
        this.filtro.pk.clavetabla = codigo;
        this.accionVentana = accionVentana;
        this.ruta = ruta;
    }

    coreIniciarComponente(msj: MensajeController){
        this.mensajeController = msj;                                
        this.mensajeController.componenteDestino.bloquearPagina();
        this.puedeEditar = true;
        if(this.accionVentana == this.ACCIONES.VER){
            this.puedeEditar = false;
        }
        // if(this.accionVentana == this.ACCIONES.EDITAR){
            this.verSelector = true;
            this.buscar();
        // }        
    }    

    coreIniciarComponenteWH(msj: MensajeController){
        this.mensajeController = msj;                                
        this.mensajeController.componenteDestino.bloquearPagina();
        this.puedeEditar = true;
        this.accionVentana = this.ACCIONES.NUEVO
        this.filtro = new SyAdjunto();
        this.filtro.pk.nombretabla = "WH";
        this.filtro.pk.clavetabla = "OC";
        this.ruta = this.parametro;
        // if(this.accionVentana == this.ACCIONES.EDITAR){
            this.verSelector = true;
            this.buscar();
        // }        
    }  

    coreObtenerCantidadAdjuntos(msj: MensajeController){        
        this.mensajeController = msj;                   
        this.mensajeController.componente= 'CANTIDADADJUNTOS';
        this.obtenerCantidad();
    }
    
    coreNuevo(){
        this.accionMan = 'N';
        this.nuevoSyAdjunto = true;        
        this.bean = new SyAdjunto();
        this.verSelectorMantenimiento = true;  
    }

    coreEditar(row: SyAdjunto){        
        this.accionMan = 'A';
        this.bean = new SyAdjunto();
        this.bean = convertDateStringsToDates(row);

        if(this.accionVentana == this.ACCIONES.NUEVO){
            this.editarAd(row);
        }
        else{
            this.verSelectorMantenimiento = true;
        }

    }

    coreGuardar(){


        if(this.estaVacio(this.bean.comentario)){
            this.mostrarMensajeError('Debe ingresar una descripción');
            return;
        }

        if(this.estaVacio(this.bean.archivo)){
            this.mostrarMensajeError('Debe adjuntar un archivo');
            return;
        }

        if(this.accionVentana == this.ACCIONES.EDITAR || this.accionVentana == this.ACCIONES.NUEVO){
            this.bean.ruta = this.ruta;
            if(this.accionMan == 'N'){
                this.bean.pk.nombretabla = this.filtro.pk.nombretabla;
                this.bean.pk.clavetabla = this.filtro.pk.clavetabla;
                this.bloquearPagina();
                this.servicio.registrar(this.bean).then(resp=>{
                    this.mostrarMensajeExito('Registrado con éxito');
                    this.verSelectorMantenimiento = false;
                    this.desbloquearPagina();
                    this.buscar();
                });
            }else if(this.accionMan == 'A'){
                this.bean.pk.nombretabla = this.filtro.pk.nombretabla;
                this.bean.pk.clavetabla = this.filtro.pk.clavetabla;
                this.bloquearPagina();
                this.servicio.actualizar(this.bean).then(resp=>{
                    this.mostrarMensajeExito('Actualizado con éxito');
                    this.verSelectorMantenimiento = false;
                    this.desbloquearPagina();
                    this.buscar();
                });
            }
        }
        
    }



    eliminar(row: SyAdjunto){
        

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Desea eliminar este registro?',
            accept: () => {
                this.servicio.eliminar(row).then(resp=>{
                    this.mostrarMensajeExito('Eliminado con éxito');
                    this.desbloquearPagina();
                    this.buscar();
                });
            },
            key: "confirmad",
            reject: () => {
                this.desbloquearPagina();
            }
        });


    }



    editarAd(row: SyAdjunto){
        this.selectedSyAdjunto =  convertDateStringsToDates(row);
        this.nuevoSyAdjunto = false;        
        this.bean = this.cloneAd(row);
        this.verSelectorMantenimiento = true;
    }    

    verAd(row: SyAdjunto){
        this.selectedSyAdjunto = convertDateStringsToDates(row);
        this.nuevoSyAdjunto = false;        
        this.bean = this.cloneAd(row);
        this.verSelectorMantenimiento = true;
    }

    salirAd(){
        this.bean = new SyAdjunto();
        this.verSelectorMantenimiento = false;
    }        

    guardarAd() {
        let lstProveedorServicio = [...this.listado];
        if (this.nuevoSyAdjunto)
            lstProveedorServicio.push(this.bean);
        else
            lstProveedorServicio[this.listado.indexOf(this.selectedSyAdjunto)] = this.bean;

        this.listado = lstProveedorServicio;
        this.bean = new SyAdjunto();
        this.verSelectorMantenimiento = false;
    }

    eliminarAd(row: SyAdjunto) {
        this.selectedSyAdjunto = row;
        let index = this.listado.indexOf(this.selectedSyAdjunto);
        this.listado = this.listado.filter((val, i) => i != index);
        this.bean = new SyAdjunto();
        this.verSelectorMantenimiento = false;
    }    

    cloneAd(c: SyAdjunto): SyAdjunto {
        let bean = new SyAdjunto();
        for (let prop in c) {
            bean[prop] = c[prop];
        }
        return bean;
    }


    subiendoDto: SyAdjunto = null;
    resultado: string;

    subirArchivo(dto: SyAdjunto, fs: any) {
        fs.click();
        this.subiendoDto = dto;
    }
    
    cargarArchivo(event: any) {
        this.bloquearPagina();
        const files = event.files;
        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        const filePath = files[0].name;

        const reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            const result = reader.result as string;
            this.bean.archivo = filePath;
            this.bean.archivoadjuntostring = result.split('base64,')[1];            
            this.desbloquearPagina();
        };
    }    

    coreAgregarDocumento(msj: MensajeController, descripcion: string, nombreArchivo: string, archivobase64: string ){

        this.bean = new SyAdjunto();
        this.bean.comentario = descripcion;
        this.bean.archivo = nombreArchivo;
        this.bean.archivoadjuntostring = archivobase64;

        this.bean.ruta = this.ruta;
            
        this.bean.pk.nombretabla = this.filtro.pk.nombretabla;
        this.bean.pk.clavetabla = this.filtro.pk.clavetabla;
                
                this.bloquearPagina();
                this.servicio.registrar(this.bean).then(resp=>{
                    this.mostrarMensajeExito('Documento Registrado con éxito');
                    this.verSelectorMantenimiento = false;
                    this.desbloquearPagina();
                    this.coreSalir();
                });


    }

    bajarArchivo(archivoSeleccionado: SyAdjunto) {
        this.bloquearPagina();
       
            this.servicio.downloadFile(
                archivoSeleccionado.pk.clavetabla,
                archivoSeleccionado.pk.nombretabla,
                archivoSeleccionado.pk.secuencia
            ).subscribe(
                res => {
                    this.desbloquearPagina();
                    const link = window.URL.createObjectURL(res);
                    const a = document.createElement('a');
                    const type = res.type.split('/');
                    document.body.appendChild(a);
                    a.setAttribute('style', 'display: none');
                    a.href = link;
                   // a.download = archivoSeleccionado.archivo + '.' + this.obtenerMimeType(type[1]);
                    a.download = this.reemplazarRuta(archivoSeleccionado.archivo.trim(), ',', '_')+ '.' + this.obtenerMimeType(type[1]);

                    a.click();
                    window.URL.revokeObjectURL(link);
                    a.remove();
                }, error => {
                    throw error;
                }, () => {
                }
            );
        
    }



}
