
import { AccountMstComunService } from './../servicio/accountmst-comun.service';
import { ConstanteComun } from './../../ConstanteComun';
import { ParametroTransaccion } from './../../../../framework/modelo/ParametroTransaccion';
import { FiltroComunPrime } from './../dominio/filtro/FiltroComunPrime';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { DtoComunDwMaAccount } from './../dominio/dto/DtoComunDwMaAccount';
import { FiltroComunAccountmst } from './../dominio/filtro/FiltroComunAccountmst';
import { Table } from 'primeng/table';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { BaseComponent } from './../../../../framework/angular/component/BaseComponent';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { ParametrosmastComunService } from '../servicio/parametrosmast-comun.service';
import { MaPersonagrupoComunService } from '../servicio/mapersonagrupo-comun.service';
 

@Component({
    selector: 'app-selector-accountmst',
    templateUrl: './accountmst-selector.component.html'
})
export class AccountMstSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    verSelector: Boolean = false;
    filtro: FiltroComunAccountmst = new FiltroComunAccountmst();
    listado: DtoComunDwMaAccount[] = [];
    registrosPorPagina: number = 7;
    registroSeleccionado: DtoComunDwMaAccount = new DtoComunDwMaAccount();
    tag: string;    
    lstOrdenarpor: SelectItem[] = [];
    lstElemento: SelectItem[] = [];
    lstPrime: SelectItem[] = [];
    lstEstado: SelectItem[] = [];
    lstPlanContable: SelectItem[] = [];
    cantidad: number = 0;
    filtroPrime: FiltroComunPrime = new FiltroComunPrime();

    constructor(        
        private accountMstComunService: AccountMstComunService,
        private comunServive: MaPersonagrupoComunService,
        private personamastService: ParametrosmastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor,messageService,servicioComun);}

    ngOnInit() {
        this.cargarCombos()


        const p3 = this.listarElement()
        //const p4 = this.listarPrime()

        Promise.all([p3 ]).then((resp) => {
            //this.listarPrime()
        });

    }           
    
    cargarCombos(){        
        this.lstOrdenarpor.push({ label: 'Cuenta Contable', value: 'C' });
        this.lstOrdenarpor.push({ label: 'Descripción', value: 'D' });
        
        this.lstEstado.push({label: 'Activo', value: 'A'});        

        this.lstPrime.push({label: ConstanteAngular.COMBOTODOS, value: null});
        

        /*this.plancontableComunService.listar().then(resp =>{
            resp.forEach(element => {
                this.lstPlanContable.push({label: element.descripcion, value: element.codigo});
            });
        });*/
        
        var param: ParametroTransaccion = new ParametroTransaccion();
        param.aplicacioncodigo = "AC";
        param.parametroclave = "PRIMEDIGIT";
        param.companiacodigo = ConstanteComun.DEFECTO_COMPANIA;

        /*this.servicioComun.parametroobtener(param).then(resp => {
            this.primeComunService.listarActivosPorDigitsnumber(resp.numero).then( res =>{
                res.forEach(element2 => {
                    this.lstPrime.push({label: element2.nombre, value: element2.codigo});
                });
            });

        });*/

        
    }

    listarElement(): Promise<number> {
        this.lstElemento.push({ label: 'Todos', value: null });
        return this.comunServive.listarElement().then(res => {
          res.forEach(ele => {
            this.lstElemento.push({ label: ele.nombre, value: ele.codigo.trim() });
          });
          return 1;
        });
      }

    digito:number=0
    listarParametros(): Promise<number> {
        return this.personamastService.parametroobtener('999999','AC','PRIMEDIGIT').then(
            res=> {
              if(res){
                  if(!this.esNumeroVacio(res.numero)){
                    this.digito=res.numero
                    this.listarPrime()
                  }

              }
                return 1
            }
        )  
    }

    listarPrime(): Promise<number> {
        if(!this.esNumeroVacio(this.digito)){          
            return this.comunServive.listarPrime(this.digito).then(res => {
                console.log(res)
              res.forEach(ele => {
                this.lstPrime.push({ label: ele.nombre, value: ele.codigo.trim() });
              });
              return 1;
            });
        }

      }

    cargarPuesto(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }
        this.mensajeController.componenteDestino.bloquearPagina();        
        this.accountMstComunService.listarPaginado(this.filtro)
            .then(pg => {
                this.listado = pg;
                this.cantidad = this.listado.length;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });
    }

    listarDefecto() {        
        this.accountMstComunService.listarPaginado(this.filtro)
            .then(pg => {
                this.listado = pg;
                this.cantidad = this.listado.length;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.bloquearPagina()
            this.listarDefecto();
        }
    }


    coreBusquedaRapida(filtro: string){
    };

    coreBuscar(dt: Table){
        this.bloquearPagina()
            this.listarDefecto();
    }

    coreFiltro(flag: boolean){
    };

    coreSalir(){
        this.mensajeController.componenteDestino.desbloquearPagina();        
        this.verSelector = false;
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
    coreIniciarComponente(msj: MensajeController){
        this.mensajeController = msj;                
        this.filtro = new FiltroComunAccountmst();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.registroSeleccionado = new DtoComunDwMaAccount();
        this.filtro.estado='I'
        this.listarDefecto();
    }


}
