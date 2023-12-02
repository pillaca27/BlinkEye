import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { TituloListadoComponent } from './../../../../framework/angular/controles/titulos/titulo-listado.component';
import { FiltroMiscelaneosDetalle } from './../dominio/dto/filtromiscelaneosdetalle';
import { ServicioComunService } from './../../servicioComun.service';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { SelectItem, ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CompaniaRecursosService } from '../servicio/companiarecursos.service';

import { trigger, animate, transition, style, state } from '@angular/animations';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-companiarecursoslistado',
    templateUrl: './companiarecursos_listado.component.html',
})
export class CompaniaRecursosListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;

    listaRecursos: DtoComunMaMiscelaneosdetalle[];
    filtro: DtoComunMaMiscelaneosdetalle;
    estados: SelectItem[] = [];

    constructor(
        private router: Router,        
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private maMiscelaneoDetalleService: MaMiscelaneosdetalleComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComunService: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComunService); }

    ngOnInit(): void {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this,  this.objetoBoton);

        this.filtro = new DtoComunMaMiscelaneosdetalle();
        this.coreBuscar();
        this.cargarEstados();
        var user = this.getUsuarioActual();
        console.log(user);
    }

    cargarEstados() {
        this.estados.push({ label: 'Seleccione', value: '' });
        this.estados.push({ label: 'Activo', value: 'A' });
        this.estados.push({ label: 'Inactivo', value: 'I' });
    }

    preBuscar(event?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar();
        }
    }        

    coreNuevo(){}
    coreBusquedaRapida(filtro: string){}
    coreBuscar(){        
        this.filtro.aplicacioncodigo = 'SY';
        this.filtro.codigotabla = 'TIPRECCOMP';
        this.filtro.compania = '999999';
        this.bloquearPagina();        
        this.maMiscelaneoDetalleService.listarDtoPorHeader(this.filtro.aplicacioncodigo,this.filtro.codigotabla,this.filtro.compania).then(
             resp => {
                this.listaRecursos = resp;
                this.desbloquearPagina();
             }
        );
    }
    coreFiltro(flag: boolean){}
    coreAnular(dto: any){}
    coreEliminar(dto: DtoComunMaMiscelaneosdetalle){

        this.confirmationService.confirm({
            message: '¿Desea Eliminar el registro?',
            accept: () => {
                this.bloquearPagina();                
                this.maMiscelaneoDetalleService
                     .eliminar(dto).then(res => {
                         this.desbloquearPagina();
                         this.messageService.clear();
                         this.mostrarMensaje(`Se Eliminó El Recurso` + dto.codigoelemento, 'info');
                         this.coreBuscar();
                     })
                     .catch(errors => {
                         this.messageService.clear();
                         this.desbloquearPagina();
                     });
            }
        });

    }
    coreEditar(dto: DtoComunMaMiscelaneosdetalle){
        this.router.navigate([ ConstanteComun.ruta_companiarecursos_mantenimiento, JSON.stringify(dto)], { skipLocationChange: true });
    }
    coreVer(dto: any){}
    coreExportar(tipo: string){}
    coreMensaje(mensage: MensajeController){}
    coreAccion(accion: string){}

    ngAfterViewInit() {
        
    }


    
}


