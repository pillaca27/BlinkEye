import { BotonesMantenimientoComponent } from './../../../../framework/angular/controles/botones/botones-mantenimiento.component';
import { ConstanteComun } from './../../ConstanteComun';
import { AplicacionesmastComunService } from '../../sg/servicio/aplicacionesmast-comun.service';
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { MessageService, SelectItem } from 'primeng/api';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
// import { SyAprobacionnivelesServicio } from '../servicio/SyAprobacionnivelesServicio';

@Component({
    selector: 'app-syreportemantenimiento',
    templateUrl: './syreporte-mantenimiento.component.html'
})
export class SyReporteMantenimientoComponent  extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private servicio: SyReporteComunService,
        servicioComun: ServicioComunService,
        private aplicaciones: AplicacionesmastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    syReporte: DtoComunSyReporte = new DtoComunSyReporte();
    lstAplicacion: SelectItem[] = [];
    lstEstado: SelectItem[] = [];
    lstTipoReporte: SelectItem[] = [];
    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();

    ngOnInit() {

        this.block.emit();
        this.formularioIniciar(this.route);                
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion,  this.objetoBoton);

        const p1 = this.aplicaciones.listarAplicacionPorUsuario().then(
            res => {
                this.lstAplicacion.push({ label: ' Seleccionar ', value: null });
                res.forEach(r => this.lstAplicacion.push({ label: r.nombre, value: r.codigo.trim() }));
                this.lstEstado = [];
                this.lstEstado.push({ label: 'Activo', value: 'A' });
                this.lstEstado.push({ label: 'Inactivo', value: 'I' });

                this.lstTipoReporte = [];
                this.lstTipoReporte.push({ label: ' Seleccionar ', value: null });
                this.lstTipoReporte.push({ label: 'HTML', value: 'HTML' });
                this.lstTipoReporte.push({ label: 'JASPER', value: 'JASPE' });
                this.lstTipoReporte.push({ label: 'POWER', value: 'POWER' });
                this.lstTipoReporte.push({ label: 'CORREO', value: 'CORRE' });

                // this.lstTipoReporte.push({ label: 'Fuente de datos Excel Drive', value: 'GDEXC' });
                // this.lstTipoReporte.push({ label: 'Informe Google', value: 'INGOO' });

                const ver = this.route.snapshot.params['ver'];
                const reporteCodigo = this.route.snapshot.params['reporteCodigo'];
                const aplicacioncodigo = this.route.snapshot.params['aplicacionCodigo'];

                if (aplicacioncodigo) {
                    this.editar();
                } else {
                    this.nuevo();
                }
            }
        );
    }

    editar() {

        this.accion = this.ACCIONES.EDITAR;
        const pk: DtoComunSyReporte = new DtoComunSyReporte();
        pk.uuid = this.route.snapshot.params['uuid'];
        console.log(pk);
        this.servicio.obtenerDto(pk).then(reg => {
            console.log(reg);
            this.syReporte = reg;
            this.unBlock.emit();
        });
    }
    nuevo() {
        this.accion = this.ACCIONES.NUEVO;
        this.syReporte = new DtoComunSyReporte();
        this.syReporte.aplicacioncodigo = null;
        this.syReporte.estado = 'A';
        this.syReporte.tiporeporte = 'HTML';
        this.unBlock.emit();
    }

    salir() {
        this.router.navigate([ConstanteComun.ruta_syreporte_listado]);
    }

    guardar() {
        // if (!this.validar()) {
        //     return;
        // }

        this.bloquearPagina();
        if (this.accion === this.ACCIONES.NUEVO) {
            this.servicio.registrar(this.syReporte).then(
                r => {
                    this.desbloquearPagina();
                    if(this.transaccionResultado(r)){
                        this.router.navigate([ConstanteComun.ruta_syreporte_listado]);
                        this.mostrarMensajeExito(this.getMensajeGrabado(this.syReporte.reportecodigo));
                    }                    
                }
            );
        } else {
            this.servicio.actualizar(this.syReporte).then(
                r => {
                    this.desbloquearPagina();
                    if(this.transaccionResultado(r)){
                        this.router.navigate([ConstanteComun.ruta_syreporte_listado]);
                        this.mostrarMensajeExito(this.getMensajeActualizado(this.syReporte.reportecodigo));
                    }
                }
            );
        }
    }


    validar(): Boolean {
        let valida: Boolean = true;

        this.messageService.clear();

        if (this.syReporte.aplicacioncodigo == null) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La aplicación es requerida' });
            valida = false;
        }
        if (this.estaVacio(this.syReporte.descripcionlocal)) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El nombre es requerido' });
            valida = false;
        }
        if (this.estaVacio(this.syReporte.reportecodigo)) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El codigo del reporte es requerido' });
            valida = false;
        }

        if (this.estaVacio(this.syReporte.tiporeporte)) {
            this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Tipo de Reporte es requerido' });
            valida = false;
        }
        else {
            /*
            if (this.syReporte.tiporeporte == 'GDEXC') {
                if (this.estaVacio(this.syReporte.hojaid)) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El ID Hoja Google es requerido' });
                    valida = false;
                }
                if (this.estaVacio(this.syReporte.auxsentencia)) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'La sentencia es requerida' });
                    valida = false;
                }
            }*/
            if (this.syReporte.tiporeporte == 'INGOO') {
                /*if (this.estaVacio(this.syReporte.urlInformeGoogle)) {
                    this.messageService.add({ severity: 'error', summary: 'Error', detail: 'El Url Informe Google es requerido' });
                    valida = false;
                }*/
            }
        }

        return valida;
    }


    coreGuardar(){this.guardar()}
    coreSalir(){this.salir()}
    coreExportar(tipo: string){}
    coreMensaje(mensage: MensajeController){}
    coreAccion(accion: string){}

}
