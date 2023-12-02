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
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { AcSucursalComunService } from '@framework-comun/contabilidad/servicio/acsucursal-comun.service';
import { DtoComunAcSucursal } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcSucursal';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
 


@Component({
    selector: 'app-acsucursal-mantenimiento',
    templateUrl: 'acsucursal-mantenimiento.component.html'
})
export class AcSucursalMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstGrupoSucursal:SelectItem[]=[]
    dto: DtoComunAcSucursal = new DtoComunAcSucursal();
    accionnuevo: boolean = false
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private comunServive: MaPersonagrupoComunService,
        private confirmationService: ConfirmationService,
        private acSucursalService: AcSucursalComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.lstGrupoSucursal.push({label:"Todos",value:null})
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        const p1 = this.listarEstados()
        const p2 = this.listarGrupoSucursal()
        
        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.dto.estado='A'
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunAcSucursal);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.acSucursalService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.accionnuevo=true
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

    coreGuardar(): void {
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.acSucursalService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.acSucursalService.actualizar(this.dto).then(res => {
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
                    this.acSucursalService.eliminar(this.dto).then(res => {
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
        this.router.navigate([ConstanteComun.ruta_acsucursal_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    
    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarGrupoSucursal(): Promise<number> {
        return this.comunServive.listarGrupoSucursal().then(res => {
            res.forEach(ele => {
                this.lstGrupoSucursal.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }
}
