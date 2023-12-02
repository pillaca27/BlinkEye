import { CompanyownerComunService } from '../servicio/companyowner-comun.service';
import { DtlComunParametrosmast } from '../dominio/lista/DtlComunParametrosmast';
import { ParametrosmastComunService } from '../servicio/parametrosmast-comun.service';
import { AplicacionesmastComunService } from '../../sg/servicio/aplicacionesmast-comun.service';
import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { DtoComunParametrosmast } from '../dominio/dto/DtoComunParametrosmast';
import { ConstanteComun } from './../../ConstanteComun';
import { convertDateStringsToDates } from 'src/app/royal/framework/angular/funciones/dateutils';
import { TituloMantenimientoComponent } from '@framework/angular/controles/titulos/titulo-mantenimiento.component';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-parametros-mantenimiento',
    templateUrl: './parametros-mantenimiento.component.html'
})
export class ParametrosMantenimientoComponent  extends FormularioComponent implements OnInit, UIMantenimientoController {
 
    lstEstados: SelectItem[] = [];
    lstmonedacodigo: SelectItem[] = [];     
    lstAplicaciones: SelectItem[] = [];
    lstCompanias: SelectItem[] = [];
    lstValor: SelectItem[] = [];

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    
    dto: DtoComunParametrosmast = new DtoComunParametrosmast();
    accionnuevo: boolean = false;
    finanzas:boolean=false
    otraCompania:boolean=false

 constructor(        
        messageService: MessageService,        
        private router: Router,       
        private route: ActivatedRoute,
        private parametrosmastService:  ParametrosmastComunService,
        private confirmationService: ConfirmationService,
        private companyownerService: CompanyownerComunService,
        private aplicacionesmastService: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);        }

    ngOnInit() {

        this.bloquearPagina();

        this.formularioIniciar(this.route);        
        
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);         
        
        this.cargarTipoValor();

            const p1 = this.cargarCompanias();
           /*  const p2 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
                ConstanteComunSistema.APLICACION,ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,
                this.lstEstados); */
            const p2 =this.listarEstados()
            const p3 = this.cargarAplicaciones();
            Promise.all([p1, p2, p3]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){                                        
                this.dto.estado = 'A';
                this.dto.tipodedatoflag="T"
                this.accionnuevo = true;
                this.dto.financecomunflag="N"
                this.dto.companiacodigo="999999"
                this.dto.aplicacioncodigo=this.getUsuarioActual().aplicacionCodigo
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR ){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtlComunParametrosmast);        
                var param = new DtoComunParametrosmast();
                param.companiacodigo = tempdto.companiacodigo;
                param.aplicacioncodigo = tempdto.aplicacioncodigo;
                param.parametroclave = tempdto.parametroclave;
                param.uuid = tempdto.uuid;
                if(tempdto != undefined && tempdto != null){                    
                    this.bloquearPagina();
                    this.parametrosmastService.obtenerDto(param).then(resp=>{                       
                        resp.fecha = this.esFechaVacia(resp.fecha)? null : new Date(resp.fecha);
                        resp.ultimafechamodif = new Date(resp.ultimafechamodif);  
                        this.otraCompania= resp.companiacodigo.trim()=="999999"?true:false                      
                        this.dto = resp;
                        this.dto.companiacodigo=this.dto.companiacodigo.trim()
                        this.desbloquearPagina();
                    });
                }        
            }
        });
    }    

    coreGuardar(): void {
        this.tipoDeDato()
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();            
            this.parametrosmastService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();                    
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }                    
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();            
            this.parametrosmastService.actualizar(this.dto).then(res => {
                    this.desbloquearPagina();                    
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }                    
                }
            );
        }else if(this.accion == this.ACCIONES.ELIMINAR){
            this.confirmationService.confirm({
             header: 'Confirmación',icon: 'fa fa-question-circle',message: '¿Desea eliminar este registro?',
             accept: () => {
                 this.bloquearPagina();
                 this.messageService.clear();
                 this.parametrosmastService.eliminar(this.dto).then( res =>{
                     this.desbloquearPagina();                
                     if(this.transaccionResultado(res)){
                       this.coreSalir();
                     }
                 });
             },
             key: "confirm"
           });  
         }                       
        
    }
    coreExportar(tipo: string): void {        
    }
    coreSalir(): void {        
        this.router.navigate([ConstanteComun.ruta_parametros_listado], { skipLocationChange: true })
    }
    coreMensaje(mensage: MensajeController): void {      
    }
    
    coreAccion(accion: any):void{   
        console.log(accion);
    }        

    cargarAplicaciones(): Promise<number>{
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }

    
    cargarCompanias(): Promise<number>{
        this.lstCompanias.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.companyownerService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstCompanias.push({ label: ele.nombre, value: ele.codigo });
            });
            this.lstCompanias.push({ label: 'Default (999999)', value: '999999' });
            return 1;
        })
    }

    cargarTipoValor(){
        this.lstValor.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.lstValor.push({ label: 'Texto', value: 'T' });        
        this.lstValor.push({ label: 'Número', value: 'N' });        
        this.lstValor.push({ label: 'Fecha', value: 'F' });        
    }
    
    listarEstados() {
        this.lstEstados.push({ label: 'Seleccione', value: null });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    tipoDeDato(){
        if(this.dto.tipodedatoflag=="T"){
            this.dto.numero=null
            this.dto.fecha=null
        }else if(this.dto.tipodedatoflag=="N"){
            this.dto.texto=null
            this.dto.fecha=null
        }
        else if(this.dto.tipodedatoflag=="F"){
            this.dto.numero=null
            this.dto.texto=null
        }else{
            this.dto.numero=null
            this.dto.fecha=null
            this.dto.texto=null
        }
    }

    validarFinanzas(){
        if(this.finanzas){
            this.dto.financecomunflag="S"
        }else{
            this.dto.financecomunflag="N"
        }
    }

    validarCompania(){
        if(this.otraCompania){
            this.dto.companiacodigo="999999"
        }else{
            this.dto.companiacodigo=null
        }
    }
}


