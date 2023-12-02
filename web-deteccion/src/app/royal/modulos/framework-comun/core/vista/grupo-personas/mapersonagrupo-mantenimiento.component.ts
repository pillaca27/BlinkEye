import { ConstanteComun } from '@framework-comun/ConstanteComun';
 
import { SelectItem, MessageService } from 'primeng/api';
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
import { DtoComunMaPersonagrupo } from '@framework-comun/core/dominio/dto/DtoComunMaPersonagrupo';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
 

@Component({
    selector: 'app-mapersonagrupo-mantenimiento',
    templateUrl: 'mapersonagrupo-mantenimiento.component.html'
})
export class MaPersonagrupoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    dto: DtoComunMaPersonagrupo = new DtoComunMaPersonagrupo();
    accionnuevo:boolean=false

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private maPersonagrupoService: MaPersonagrupoComunService,
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
                this.dto.estado = 'A';
                this.accionnuevo = true;
                this.dto.usuarioActual=this.getUsuarioActual()
            }else if(this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER){
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunMaPersonagrupo);
                if(tempdto != undefined && tempdto != null){
                    this.bloquearPagina();
                    this.maPersonagrupoService.obtenerDto(tempdto).then(resp=>{
                        this.dto = resp;
                        this.dto.usuarioActual=this.getUsuarioActual()
                        this.desbloquearPagina();
                    });
                }
            }
        });
 }

    coreGuardar(): void {
        if(this.accion == this.ACCIONES.NUEVO){
            this.bloquearPagina();
            this.maPersonagrupoService.registrar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }else if(this.accion == this.ACCIONES.EDITAR){
            this.bloquearPagina();
            this.maPersonagrupoService.actualizar(this.dto).then(res => {
                    this.desbloquearPagina();
                        if(this.transaccionResultado(res)){
                            this.coreSalir();
                        }
                }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_mapersonagrupo_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void {}

    coreMensaje(mensage: MensajeController): void {}

    coreAccion(accion: any):void{}

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }
}
