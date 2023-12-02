import { DtoComunServicioximpuesto } from './../../dominio/dto/DtoComunServicioximpuesto';
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
import { DtoComunTiposervicio } from '@framework-comun/core/dominio/dto/DtoComunTiposervicio';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { TiposervicioComunService } from '@framework-comun/core/servicio/tiposervicio-comun.service';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
 
 

@Component({
    selector: 'app-tiposervicio-mantenimiento',
    templateUrl: 'tiposervicio-mantenimiento.component.html'
})
export class TiposervicioMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstRegimen:SelectItem[]=[]
    lstImpuestos:SelectItem[]=[]
    lstImpuestos2:SelectItem[]=[]
    dto: DtoComunTiposervicio = new DtoComunTiposervicio();
    accionnuevo:boolean=false
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private comunServive: MaPersonagrupoComunService,
        private tiposervicioService: TiposervicioComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        const p1 = this.listarEstados()
        const p2= this.listarRegimenFiscal()
        const p3= this.listarImpuestos()
        const p4 = this.listarImpuestos2()

        Promise.all([p1, p2, p3, p4]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.dto.estado='A'
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunTiposervicio);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.tiposervicioService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.accionnuevo=true
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

 resultado:boolean=false
    coreGuardar(): void {
        this.validacionDetalle(this.dto.lstDetalle)
        if(this.resultado && this.accion != this.ACCIONES.ELIMINAR){
            return
        }
        
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.tiposervicioService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.tiposervicioService.actualizar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.ELIMINAR){
            this.confirmationService.confirm({
                header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.tiposervicioService.eliminar(this.dto).then(res => {
                            this.desbloquearPagina();
                                if(this.transaccionResultado(res)){
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
        this.router.navigate([ConstanteComun.ruta_tiposervicio_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarRegimenFiscal(): Promise<number> {
        this.lstRegimen.push({ label: 'Todos', value: null });
        return this.comunServive.listarRegimenFiscal().then(res => {
            res.forEach(ele => {
                this.lstRegimen.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarImpuestos():Promise<number>{
        this.lstImpuestos.push({ label: 'Todos', value: null });
        return this.comunServive.listarImpuesto().then(res => {            
            res.forEach(ele => {
                if(ele.estadoId == "I" || ele.estadoId=="R"){
                    this.lstImpuestos.push({ label: ele.codigo.trim()+"  "+  ele.nombre, value: ele.codigo.trim() });
                   
                }  
            });
            return 1;
        });
      }

      listarImpuestos2():Promise<number>{
        return this.comunServive.listarImpuesto().then(res => {            
            res.forEach(ele => {
                this.lstImpuestos2.push({ label: ele.codigo.trim()+"  "+  ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
      }

      cambioRegimen(){
          if(!this.estaVacio(this.dto.regimenfiscal)){
           
            this.lstImpuestos=[]
            this.lstImpuestos.push({ label: 'Todos', value: null });
            let dto=this.dto.regimenfiscal

            if(dto == "M"){
                this.bloquearPagina()
                return this.comunServive.listarImpuesto().then(res => {
                    res.forEach(ele => {
                            if(ele.estadoId == "I" || ele.estadoId=="R"){
                                this.lstImpuestos.push({ label: ele.nombre, value: ele.codigo.trim() });
                            }                      
                    });
                    this.desbloquearPagina()
                    return 1;
                });
            }else if(dto=="N" && this.accion == this.ACCIONES.NUEVO){
                this.dto.lstDetalle=[]
                return 1
            }
            
            else{
                this.bloquearPagina()
                return this.comunServive.listarImpuesto().then(res => {
                    res.forEach(ele => {
                                this.lstImpuestos.push({ label: ele.nombre, value: ele.codigo.trim() });                                            
                    });
                    this.desbloquearPagina()
                    return 1;
                });
               
            }

          }

      }

      changueImpuesto(dto:any){
          let data=  this.lstImpuestos.filter( x => x.value==dto.impuesto)
          if(!this.esListaVacia(data)){
            dto.tipoimpuesto=data[0].value 
          }

      }

    agregarDetalle(){
        let detalle = new DtoComunServicioximpuesto()
        detalle.accion='N'
        detalle.tiposervicio=this.dto.tiposervicio
        this.dto.lstDetalle.push(detalle)

    }

    elminiarDetalle(dto:any,index:number){
        this.confirmationService.confirm({
            header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
            accept: () => {
                if(dto.accion == "N"){
                    this.dto.lstDetalle.splice(index, 1);
                }else if(dto.accion == "A"){
                    dto.accion="E"
                  }
            },
            key: "confirm",
        });

      }
    
    validacionDetalle(nodo:any):boolean{
        this.resultado=false
        if(!this.esListaVacia(nodo)){
            let array= nodo.filter(x => x.accion != "E")
            let data = array.map(x => x.impuesto)
            const result = data.reduce((acc,item)=>{
              if(this.estaVacio(item)){
                this.resultado=true
              }
              if(!acc.includes(item)){
                acc.push(item);
              }
              return acc;
            },[])

            if(this.resultado){
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El impuesto no debe estar vació." });
                return this.resultado=true
            }else{

                if(array.length != result.length){
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El impuesto no debe estar duplicado." });
                return this.resultado=true
                }
            }

        }else{
            return this.resultado=false
        }
    }

}
