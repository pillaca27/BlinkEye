import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';
import { SelectItem, MessageService } from 'primeng/api';
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
import { DtoSeguridadconcepto } from '../../dominio/dto/DtoSeguridadconcepto';
import { SeguridadconceptoService } from '../../servicio/seguridadconcepto.servicio';
import { ConstanteSg } from '../../ConstanteSg';
import { SeguridadgrupoService } from '../../servicio/seguridadgrupo.servicio';
import { FiltroSeguridadgrupo } from '../../dominio/filtro/FiltroSeguridadgrupo';



@Component({
    selector: 'app-seguridadconcepto-mantenimiento',
    templateUrl: 'seguridadconcepto-mantenimiento.component.html'
})
export class SeguridadconceptoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    @ViewChild('myAplicacioncodigo', { static: false }) myAplicacioncodigo: ElementRef;
    @ViewChild('myGrupo', { static: false }) myGrupo: ElementRef;
    @ViewChild('myConcepto', { static: false }) myConcepto: ElementRef;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;


    lstControlesRequeridos: any[] = [];
    lstEstados: SelectItem[] = [];
    dto: DtoSeguridadconcepto = new DtoSeguridadconcepto();
    lstAplicaciones: SelectItem[] = [];
    lstGrupos: SelectItem[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private seguridadconceptoService: SeguridadconceptoService,
        private seguridadgrupoService: SeguridadgrupoService,
        private aplicacionesmastService: AplicacionesmastComunService,
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
            ConstanteSg.APLICACION, ConstanteSg.MISC_ESTADO_GENERICO, ConstanteSg.DEFECTO_COMPANIA,
            this.lstEstados);

        const p2 = this.cargarAplicaciones();
        /* const p3 = this.cargarGrupos(); */
        Promise.all([p1, p2/* , p3 */]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                //this.myAplicacioncodigo.nativeElement.focus();
                this.dto.visibleflag = 'S';
                this.dto.tipodedetalle = 'N';
                this.dto.tipodeobjeto = 'M';
                this.dto.tipodeacceso = 'N';
                this.dto.webflag = 'S';
                this.dto.webpage = '##';
                this.dto.imagen = 'fad fa-list';

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSeguridadconcepto);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.seguridadconceptoService.obtenerDto(tempdto).then(resp => {
                        this.dto = {...resp};
                        
                        if (!this.estaVacio(this.dto.aplicacioncodigo)) {
                            this.dto.grupo = null;
                            const p1 = this.cargarGrupos();
                            Promise.all([p1]).then(xx => {
                                console.log(this.dto.grupo)
                                console.log(resp.grupo)
                                this.dto.grupo = resp.grupo;
                                this.desbloquearPagina();
                            })
                        } else {
                            this.desbloquearPagina();
                        }
                    });
                }
            }
        });
    }
    cargarAplicaciones(): Promise<number> {
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }
    cargarGrupos(): Promise<number> {
        this.lstGrupos = [];
        this.lstGrupos.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        let filtro: FiltroSeguridadgrupo = new FiltroSeguridadgrupo();
        filtro.aplicacioncodigo = this.dto.aplicacioncodigo;
        return this.seguridadgrupoService.listardtoactivosPorAplicacion(filtro).then(res => {
            res.forEach(ele => {
                this.lstGrupos.push({ label: ele.descripcion, value: ele.grupo });
            });
            return 1;
        })
    }
    onChangeAplicacion() {
        this.bloquearPagina();
        this.dto.grupo = null;
        const p1 = this.cargarGrupos();
        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
        })
    }
    cargarControlesRequeridos() {
        this.lstControlesRequeridos = [];
        this.lstControlesRequeridos.push({ control: this.myAplicacioncodigo });
        this.lstControlesRequeridos.push({ control: this.myGrupo });
        this.lstControlesRequeridos.push({ control: this.myConcepto });
        this.lstControlesRequeridos.push({ control: this.myDescripcion });

    }

    coreGuardar(): void {
        this.cargarControlesRequeridos();
        var mensajes = this.validarControlesRequeridos(this.lstControlesRequeridos);
        if (mensajes.length > 0) {
            this.mostrarMensajeListado(mensajes);
            return;
        }

        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.seguridadconceptoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.seguridadconceptoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteSg.ruta_seguridadconcepto_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreAccion(accion: any): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == ConstanteSg.GENERICO_SELECTOR) {
            //
        }
    }

    coreSelectorMostrar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
            //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        }
    }

    coreSelectorLimpiar(selector: string) {
        if (selector == ConstanteSg.GENERICO_SELECTOR) {
            console.log(selector);
        }
    }

}
