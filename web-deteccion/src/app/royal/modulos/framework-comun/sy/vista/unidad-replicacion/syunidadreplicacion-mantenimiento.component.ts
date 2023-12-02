import { ConstanteComun } from '@framework-comun/ConstanteComun';
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
import { DtoComunSyUnidadreplicacion } from '@framework-comun/sy/dominio/dto/DtoComunSyUnidadreplicacion';
import { SyunidadreplicacionComunService } from '@framework-comun/sy/servicio/syunidadreplicacion-comun.service';
 


@Component({
    selector: 'app-syunidadreplicacion-mantenimiento',
    templateUrl: 'syunidadreplicacion-mantenimiento.component.html'
})
export class SyUnidadreplicacionMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    dto: DtoComunSyUnidadreplicacion = new DtoComunSyUnidadreplicacion();
    accionnuevo: boolean = false
    blockSpecial: RegExp =  /^[a-zA-Z0-9]*$/;
    blockSpecialNumero: RegExp =  /^[0-9]*$/;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private syUnidadreplicacionService: SyunidadreplicacionComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
         ) {       super(noAuthorizationInterceptor, messageService,servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        const p1 = this.listarEstados()

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
             this.dto.estado='A'
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunSyUnidadreplicacion);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.syUnidadreplicacionService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.accionnuevo = true
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

    coreGuardar(): void {
        if(this.validarGuardado()){
            return
        }
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.syUnidadreplicacionService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.syUnidadreplicacionService.actualizar(this.dto).then(res => {
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
                    this.syUnidadreplicacionService.eliminar(this.dto).then(res => {
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


  validarGuardado(){
      let resultado=false
      if(!this.esNumeroVacio(this.dto.rangopersonainicio) || !this.esNumeroVacio(this.dto.rangopersonafin) || !this.esNumeroVacio(this.dto.personaactual)){
        if(this.dto.rangopersonainicio > this.dto.rangopersonafin){
            this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El Rango Persona Inicio no debe ser mayor que el El Rango Persona Fin." });
            resultado = true
          }
          if(!this.esNumeroVacio(this.dto.rangopersonainicio) && !this.esNumeroVacio(this.dto.rangopersonafin) && !this.esNumeroVacio(this.dto.personaactual)){
            if( (+this.dto.personaactual  >= +this.dto.rangopersonainicio) && (+this.dto.personaactual <= +this.dto.rangopersonafin)){
                      
            }else{
              this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La Persona actual debe ser >= Persona Inicio y <= Persona Fin." });
              resultado = true
            }
          }

      }

      return resultado
  }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_syunidadreplicacion_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

}
