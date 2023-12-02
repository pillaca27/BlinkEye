 
import { SySeguridadconceptoService } from './../servicio/syseguridadconcepto.servicio';
import { DtoSySeguridadconcepto } from './../dominio/dto/DtoSySeguridadconcepto';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';
import { ConstanteComunSistema } from '@framework-comun/sy/ConstanteComunSistema';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { InputNumber } from 'primeng/inputnumber';
import { Calendar } from '@fullcalendar/core';
import { Dropdown } from 'primeng/dropdown';

import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';


import { SySeguridadgrupoService } from '../servicio/syseguridadgrupo.servicio';
import { DtoSySeguridadgrupo } from './../dominio/dto/DtoSySeguridadgrupo';

@Component({
    selector: 'app-syseguridadgrupo-mantenimiento',
    templateUrl: 'syseguridadgrupo-mantenimiento.component.html'
})
export class SySeguridadgrupoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myAplicacioncodigo', { static: false }) myAplicacioncodigo: ElementRef;
    @ViewChild('myGrupo', { static: false }) myGrupo: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoSySeguridadgrupo = new DtoSySeguridadgrupo();
    lstAplicaciones: SelectItem[] = [];
    lstTipoDetalle: SelectItem[] = [];
    grupo: string = '';
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private sySeguridadgrupoService: SySeguridadgrupoService,
        private sySeguridadconceptoService: SySeguridadconceptoService,
        private aplicacionesmastService: AplicacionesmastComunService,  
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
            ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados);
        const p2 = this.cargarAplicaciones();
        const p3 = this.cargarTipoDetalle();
        Promise.all([p1, p2, p3]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
               // this.myAplicacioncodigo.nativeElement.focus();

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSySeguridadgrupo);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.sySeguridadgrupoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.grupo = this.dto.grupo;
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myAplicacioncodigo });
        this.lstControlesRequeridos.push({ control: this.myGrupo });

    }

    coreGuardar(): void {
        /* this.cargarControlesRequeridos();
        var mensajes = this.validarControlesRequeridos(this.lstControlesRequeridos);
        if (mensajes.length > 0) {
            this.mostrarMensajeListado(mensajes);
            return;
        } */

        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.sySeguridadgrupoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.sySeguridadgrupoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComunSistema.ruta_syseguridadgrupo_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        console.log("coremensaje");
        console.log(mensage);

        if (mensage.componente == 'CENTROCOSTO' && mensage.parametros != 'MULTIPLE') {
            console.log(mensage.resultado.costcenter);
            this.cambiarConceptos(mensage.resultado.costcenter,mensage.resultado.localname);
            this.mostrarMensajeExito('Acción realizada correctamente.');
        }
        if (mensage.componente == 'CENTROCOSTO' && mensage.parametros == 'MULTIPLE') {
            mensage.resultado.forEach(dw => {
                console.log(dw.costcenter);
                this.cambiarConceptos(dw.costcenter,dw.localname);
            });
            this.mostrarMensajeExito('Acción realizada correctamente.');
        }
    }

    coreMostrarSelector(selector: string) {
        /* if (selector == ConstanteSistema.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        } */
    }

    cargarAplicaciones(): Promise<number> {
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.aplicacionesmastService.listarAplicacionPorUsuario().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }

    cargarTipoDetalle(): Promise<number> {
        this.lstTipoDetalle.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.sySeguridadgrupoService.listarTipoDetalle().then(res => {
            res.forEach(ele => {
                this.lstTipoDetalle.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;

        })
    }
    nuevoseguridadconcepto() {
        let nuevo = new DtoSySeguridadconcepto();
        nuevo.flgactualizar = 'NUEVO';
        nuevo.estado = 'A';
        nuevo.grupo = this.dto.grupo;
        nuevo.aplicacioncodigo = this.dto.aplicacioncodigo;
        nuevo.ultimousuario = this.getUsuarioActual().usuario;
        nuevo.ultimafechamodif = new Date();
        this.dto.seguridadconcepto.push(nuevo);
    }
    eliminarSeguridadConcepto(dto: DtoSySeguridadconcepto, index: number) {
        if(dto.flgactualizar == 'NUEVO'){
            this.dto.seguridadconcepto.splice(index, 1);
        }else{
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.messageService.clear();
                    this.sySeguridadconceptoService.eliminar(dto).then((res) => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res))
                        this.dto.seguridadconcepto.splice(index, 1);
                    });
                },
                key: "confirm",
            });
        }
    }

    cambiarConceptos(centrocosto:string, nombre: string){

        var res = this.dto.seguridadconcepto.find(e=>e.concepto.trim()==centrocosto.trim());

        if(res != null && res != undefined){
            res.descripcionlocal = nombre;
            res.ultimafechamodif = new Date();
            res.ultimousuario = this.getUsuarioActual().usuario;
            res.flgactualizar='S';
            res.color='#fdeda9';
        }else{
            res = new DtoSySeguridadconcepto();
            res.grupo = this.dto.grupo;
            res.aplicacioncodigo = this.dto.aplicacioncodigo;
            res.concepto = centrocosto;
            res.descripcionlocal = nombre;
            res.ultimafechamodif = new Date();
            res.ultimousuario = this.getUsuarioActual().usuario;
            res.estado = 'A';
            res.flgactualizar = 'NUEVO';
            res.color='#a9fda9';
            this.dto.seguridadconcepto.push(res);
        }      
    }


}
