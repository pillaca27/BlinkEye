import { DtoComunMaPersonacuentabancaria } from './../../../dominio/dto/DtoComunMaPersonacuentabancaria';
 
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';

import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
 
import { ServicioComunService } from '@framework-comun/servicioComun.service';
  
 
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
 
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { DtoComunPersonamast } from '@framework-comun/core/dominio/dto/DtoComunPersonamast';
import { PersonamastComunService } from '@framework-comun/core/servicio/personamast-comun.service';
import { DtoComunMaPersonapersonagrupo } from '@framework-comun/core/dominio/dto/DtoComunMaPersonapersonagrupo';

@Component({
  selector: 'app-info-adicional-mantenimiento',
  templateUrl: 'info-adicional-mantenimiento.component.html',
  styles: [`:host ::ng-deep .p-button.p-button-icon-only {
        width: 2.357rem;
        padding: 0.2rem 0;
    }
    :host ::ng-deep .p-datatable-scrollable-body {
      padding-right: 0px;
  }
  .tablaHeaderCodigoSmall {
    text-align: center !important;
    width: 30px;
  }
  .tablaColumnaCodigoSmall {
    text-align: center !important;
    width: 30px;
  }
  .tablaHeaderCodigoSmall2 {
    text-align: center !important;
    width: 40px;
  }
  .tablaColumnaCodigoSmall2 {
    text-align: center !important;
    width: 40px;
  }
  .tablaColumnaCodigoGrande2 {
    text-align: center !important;
    width: 140px;
  }
  .tablaHeaderCodigoGrande2{
    text-align: center !important;
    width: 140px;
  }
    `]
})
export class InfoAdicionalMastMantenimientoComponent extends FormularioComponent implements OnInit {

  @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
  @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
  @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;

  lstGrupoPersona: SelectItem[] = [];
  lstBancos: SelectItem[] = []
  lstMonedas: SelectItem[] = []
  lstTipoCuenta: SelectItem[] = []
  lstEstadoGrupo: SelectItem[] = []
  lstEstadoBanco: SelectItem[] = []
  dto: DtoComunPersonamast = new DtoComunPersonamast();
  parametroBanco:boolean=false
  esPersona:boolean=false

  constructor(
    messageService: MessageService,
    private comunServive: MaPersonagrupoComunService,
    private personamastService: PersonamastComunService,
    servicioComun: ServicioComunService,
    private confirmationService: ConfirmationService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor
  ) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngOnInit() {
    this.lstGrupoPersona.push({ label: 'Todos', value: null });
    this.lstBancos.push({ label: 'Todos', value: null });
    const p1 = this.listarGrupoPersonas()
    const p2 = this.listarEstadosBancos()
    const p3 = this.listarEstadosGrupo()
    const p4 = this.listarMonedas()
    const p5 = this.listarTipoCuenta()
    const p6 = this.listarBancos()
    const p7 = this.listarParametrosCuentaBanco()

  }

  iniciarComponenteInfoAdicional(dto: any, objetoTitulo: any, accion: any,persona:boolean) {
    this.objetoTitulo = objetoTitulo
    this.accion = accion
    this.dto = dto
    this.esPersona=persona
    if (this.esListaVacia(this.dto.dtoMaPersonapersonagrupo)) {
      this.dto.dtoMaPersonapersonagrupo = []
    }
    if (this.esListaVacia(this.dto.dtoPersonacuentabancaria)) {
      this.dto.dtoPersonacuentabancaria = []
    }
  }

 
  listarGrupoPersonas(): Promise<number> {
    return this.comunServive.listargrupopersonas().then(res => {
      res.forEach(ele => {
        this.lstGrupoPersona.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarBancos(): Promise<number> {
    return this.comunServive.listarBancos().then(res => {
      res.forEach(ele => {
        this.lstBancos.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarMonedas(): Promise<number> {
    return this.comunServive.listarMonedas().then(res => {
      res.forEach(ele => {
        this.lstMonedas.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarParametrosCuentaBanco(): Promise<number> {
    return this.personamastService.parametroobtener('999999','SY','VENDORCTAM').then(
        res=> {
            if(res){
              if(res.texto.trim() =="S"){
                this.parametroBanco=true
              }
            }
            return 1
        }
    )  
}

  listarTipoCuenta(){
    this.lstTipoCuenta.push({ label: 'Cta Corriente', value: 'C' });
    this.lstTipoCuenta.push({ label: 'Ahorros', value: 'A' });
    this.lstTipoCuenta.push({ label: 'InterBancaria', value: 'I' });
  }

  listarEstadosBancos() {
    this.lstEstadoBanco.push({ label: 'Inactivo', value: 'I' });
    this.lstEstadoBanco.push({ label: 'Activo', value: 'A' });
}

listarEstadosGrupo() {
  this.lstEstadoGrupo.push({ label: 'Inactivo', value: 'I' });
  this.lstEstadoGrupo.push({ label: 'Activo', value: 'A' });
}

  agregarDetalle(){
    let bean = new DtoComunMaPersonapersonagrupo()
    bean.personagrupo=null
    bean.acciones='NUEVO'
    bean.persona=this.dto.persona
    this.dto.dtoMaPersonapersonagrupo.push(bean)

  }

  agregarDetalleBanco(){
    let bean = new DtoComunMaPersonacuentabancaria()
    bean.secuencia=this.secuencialBancos()
    bean.estado='A'
    bean.monedacodigo='LO'
    bean.tipocuenta='C'
    bean.acciones='NUEVO'
    bean.persona=this.dto.persona
    this.dto.dtoPersonacuentabancaria.push(bean)
  }

  coreEliminar(dto:any,index:number) {
    this.confirmationService.confirm({
      header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
      accept: () => {
        if(dto.acciones=="NUEVO"){
          this.dto.dtoMaPersonapersonagrupo.splice(index, 1);
        }else{
          dto.acciones="DELETE"
        }
      },
      key: "confirm",
  });

      
  }

  coreEliminarBanco(dto:any,index:number){
    this.confirmationService.confirm({
      header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
      accept: () => {
        if(dto.acciones=="NUEVO"){
          this.dto.dtoPersonacuentabancaria.splice(index, 1);
        }else{
          dto.acciones="DELETE"
        }
      },
      key: "confirm",
  });

  }


  secuencialBancos():number{
    if(this.esListaVacia(this.dto.dtoPersonacuentabancaria)){
      return 1
    }else{
      let resultado =  Math.max(...this.dto.dtoPersonacuentabancaria.map(x => x.secuencia)) + 1
      return resultado
    }
  }


  validarGrupoPersonas():boolean{
    let resultado=false
    if(!this.esListaVacia(this.dto.dtoMaPersonapersonagrupo)){
      
      let arrayVacio=this.dto.dtoMaPersonapersonagrupo.filter(x => x.acciones != "DELETE" && ! this.estaVacio(x.personagrupo))
      if(!this.esListaVacia(arrayVacio)){
        let array= this.dto.dtoMaPersonapersonagrupo.filter(x => x.acciones != "DELETE")
        let data = array.map(x => x.personagrupo)
        const result = data.reduce((acc,item)=>{
          if(!acc.includes(item)){
            acc.push(item);
          }
          return acc;
        },[])
  
        if(result.length != array.length){
          this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El tipo de persona no debe estar duplicado." });
          return resultado =true
        }
      }

        let array= this.dto.dtoMaPersonapersonagrupo.filter(x => x.acciones != "DELETE")
        let data = array.map(x => x.personagrupo)
        if(data.filter(x => this.estaVacio(x)).length > 0){
          this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El tipo de persona no debe estar vació." });
          return resultado =true
        }
       
      
    }

  }

  validarCuentasBancarias(){
    let resultados=false
    if(!this.esListaVacia(this.dto.dtoPersonacuentabancaria)){
      
     
      let array= this.dto.dtoPersonacuentabancaria.filter(x => x.acciones != "DELETE")
      let cuentaBancaria= this.agregarCuentaBanco(array).map(x => x.cuentabanco)

      if(!this.esListaVacia(cuentaBancaria)){
        const result = cuentaBancaria.reduce((acc,item)=>{
          if(!acc.includes(item)){
            acc.push(item);
          }
          return acc;
        },[])

        if(result.length != cuentaBancaria.length){
          this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El código de banco y moneda no debe estar duplicado." });
          return resultados= true
        }else{
          resultados=false
        }
      }

     
      
        

        array.forEach(cuentas => {
          if(this.estaVacio(cuentas.bancocodigo) ){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El banco no debe estar vació. Sec. "+ cuentas.secuencia});
            resultados=true
          }
          if(this.estaVacio(cuentas.monedacodigo) ){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La moneda no debe estar vació. Sec. "+ cuentas.secuencia});
            resultados=true
          }
          if(this.estaVacio(cuentas.cuentabancarianumero) ){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El número de cuenta no debe estar vació. Sec. "+ cuentas.secuencia});
            resultados=true
          }else{
            if(cuentas.tipocuenta == "I"){
              if(cuentas.cuentabancarianumero.length != 20){
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El número de cuenta interbancaria debe tener 20 caracteres. Sec. "+ cuentas.secuencia});
                resultados=true
              }
            }

          }
          if(this.estaVacio(cuentas.tipocuenta) ){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El tipo de cuenta no debe estar vació. Sec. "+ cuentas.secuencia});
            resultados=true            
          }
          if(this.estaVacio(cuentas.estado) ){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El estado no debe estar vació. Sec. "+ cuentas.secuencia});
            resultados=true
          }
          
        });
        return resultados
       
      
    }

  }


  agregarCuentaBanco(nodo):any[]{
    return nodo.reduce((acc,item)=>{
        if(!this.estaVacio(item.cuentabancarianumero) && !this.estaVacio(item.monedacodigo)){
          item.cuentabanco=item.cuentabancarianumero.concat(item.monedacodigo)
          acc.push(item);
        }
       
      
      return acc;
    },[])
  }


  coreExportar(tipo: string): void { }

  coreMensaje(mensage: MensajeController): void {
  }

  coreAccion(accion: any): void { }

}
