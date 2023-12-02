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
 
import { TipoProveedorSelectorComponent } from '@framework-comun/core/vista/tipo-proveedor/tipo-proveedor-selector.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { DtoComunBanco } from '@framework-comun/core/dominio/dto/DtoComunBanco';
import { BancoComunService } from '@framework-comun/core/servicio/banco-comun.service';
import { ConstanteComun } from '@framework-comun/ConstanteComun';


@Component({
    selector: 'app-banco-mantenimiento',
    templateUrl: 'banco-mantenimiento.component.html'
})
export class BancoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    
    lstEstados: SelectItem[] = [];
    lstConciliacion:SelectItem[]=[]
    dto: DtoComunBanco = new DtoComunBanco();
    accionnuevo:boolean=false
    blockSpecial: RegExp = /^[a-zA-Z-0-9]*$/;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private bancoService: BancoComunService,
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
        const p2 = this.listarConcialiacion()

        Promise.all([p1,p2]).then(resp => {
            this.desbloquearPagina();
            if(this.accion == this.ACCIONES.NUEVO){
                this.dto.estado='A'
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunBanco);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.bancoService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.accionnuevo=true
                        this.validarCheck()
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

 validarCheck(){
    if(!this.estaVacio(this.dto.persona)){
        this.dto.persona= this.dto.banconumero + " - " + this.dto.persona
    }
    if(this.dto.conciliacionautomaticaflag=="S"){
        this.dto.concilio=true
    }
    if(this.dto.formatopropioflag=="S"){
       this.dto.cheque=true
   }  
 }

 validarChecksGuardar(){
    this.dto.conciliacionautomaticaflag=this.dto.concilio? "S" :"N"
    this.dto.formatopropioflag = this.dto.cheque?"S":"N"
}

    coreGuardar(): void {
        this.validarChecksGuardar()
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.bancoService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.bancoService.actualizar(this.dto).then(res => {
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
                    this.bancoService.eliminar(this.dto).then(res => {
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
        this.router.navigate([ConstanteComun.ruta_banco_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'TIPOPROVEEDOR') {
            this.dto.persona=mensage.resultado.persona + " - " + mensage.resultado.nombrecompleto
            this.dto.banconumero=mensage.resultado.persona
            //this.dto.banconumero = mensage.resultado.nombrecompleto;
        }
    }

    coreAccion(accion: any):void{}

    
    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarConcialiacion(){
        this.lstConciliacion.push({ label: 'Todos', value: null });
        this.lstConciliacion.push({ label: 'Diario', value: 'D' });
        this.lstConciliacion.push({ label: 'Mensual', value: 'M' });
    }

    mostrarPersona(){
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPROVEEDOR', 'PRCLOT'));
    }

    limpiar(){
        this.dto.banconumero=null
        this.dto.persona=''
    }
}
