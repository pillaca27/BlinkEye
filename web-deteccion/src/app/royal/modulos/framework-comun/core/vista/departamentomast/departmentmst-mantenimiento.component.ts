import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { DtoComunDepartmentmst } from '@framework-comun/core/dominio/dto/DtoComunDepartmentmst';
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
import { DepartmentmstComunService } from '@framework-comun/core/servicio/departmentmst-comun.service';
 


@Component({
    selector: 'app-departmentmst-mantenimiento',
    templateUrl: 'departmentmst-mantenimiento.component.html'
})
export class DepartmentmstMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    dto: DtoComunDepartmentmst = new DtoComunDepartmentmst();
    accionnuevo:boolean=false
    blockSpecialNumero: RegExp = /^[0-9]*$/;
    blockSpecial: RegExp = /^[a-zA-Z-0-9]*$/;
    
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private departmentmstService: DepartmentmstComunService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

/*         const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteComunSistema.APLICACION,ConstanteComunSistema.MISC_ESTADO_GENERICO,ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados); */

        const p1 =this.listarEstados()

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.dto.status='A'

            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunDepartmentmst);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.departmentmstService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.validarChecksIniciar()
                        this.accionnuevo=true
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

 validarChecksIniciar(){
     if(this.dto.accountavailableflag=="Y"){
         this.dto.contabilidadcheck=true
     }
     if(this.dto.warehouseavailableflag=="Y"){
        this.dto.logisticacheck=true
    }  
 }

    coreGuardar(): void {
        this.validarChecksGuardar()
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.departmentmstService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.departmentmstService.actualizar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }
        else if(this.accion == this.ACCIONES.ELIMINAR){
            this.confirmationService.confirm({
                header: "Confirmación",icon: "fa fa-question-circle",message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.departmentmstService.eliminar(this.dto).then(res => {
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

    validarChecksGuardar(){
        this.dto.accountavailableflag=this.dto.contabilidadcheck? "Y" :"N"
        this.dto.warehouseavailableflag = this.dto.logisticacheck?"Y":"N"
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_departmentmst_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

}
