import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { PersonamastComunService } from './../../servicio/personamast-comun.service';
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
import { DtoComunMaUnidadnegocio } from '@framework-comun/core/dominio/dto/DtoComunMaUnidadnegocio';
import { MaUnidadnegocioComunService } from '@framework-comun/core/servicio/maunidadnegocio-comun.service';
 

@Component({
    selector: 'app-maunidadnegocio-mantenimiento',
    templateUrl: 'maunidadnegocio-mantenimiento.component.html'
})
export class MaUnidadnegocioMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstCompania:SelectItem[]=[]
    dto: DtoComunMaUnidadnegocio = new DtoComunMaUnidadnegocio();
    accionnuevo: boolean = false
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;
    blockSpecialNumero: RegExp =  /^[0-9]*$/;

    parametroCompania:boolean=false

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private personamastService: PersonamastComunService,
        private confirmationService: ConfirmationService,
        private maUnidadnegocioService: MaUnidadnegocioComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        const p1 = this.listarEstados()
        const p2 = this.listarParametrosCuentaBanco()

        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.dto.estado='A'
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                 || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunMaUnidadnegocio);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.maUnidadnegocioService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.accionnuevo = true
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

    coreGuardar(): void {
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.maUnidadnegocioService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.maUnidadnegocioService.actualizar(this.dto).then(res => {
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
                    this.maUnidadnegocioService.eliminar(this.dto).then(res => {
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
        this.router.navigate([ConstanteComun.ruta_maunidadnegocio_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarParametrosCuentaBanco(): Promise<number> {
        return this.personamastService.parametroobtener('999999','AC','UNINEGCOMP').then(
            res=> {
                if(res){
                    if(!this.estaVacio(res.texto)){
                        if(res.texto.trim() =="S"){
                            this.parametroCompania=true
                          }
                    }

                }
                return 1
            }
        )  
    }
}
