import { DtoSyTipodocumentoproceso } from './../dominio/dto/DtoSyTipodocumentoproceso';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { DtoSyTipodocumento } from './../dominio/dto/DtoSyTipodocumento';
import { SyTipoDocumentoService } from '../servicio/SyTipodocumento.service';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';


@Component({
    selector: 'SyTipodocumento-mant-app',
    templateUrl: 'SyTipodocumento-mantenimiento.component.html'
})

export class SyTipodocumentoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {
    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private servicio: SyTipoDocumentoService,
        private confirmationService: ConfirmationService,
        private aplicacionesmastService: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    dto: DtoSyTipodocumento = new DtoSyTipodocumento();
    dtoDetalle: DtoSyTipodocumentoproceso = new DtoSyTipodocumentoproceso();
    dtoDetalleFiltro: DtoSyTipodocumentoproceso = new DtoSyTipodocumentoproceso();
    lstAplicacion: SelectItem[] = [];
    lstEstado: SelectItem[] = [];
    lstProcesos: SelectItem[] = [];
    lstProcesosA: SelectItem[] = [];
    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();

    detalleAccion: string = '';
    lstDetalle: DtoSyTipodocumentoproceso[] = [];

    accionnuevo: boolean = false;
    verDetalle: boolean = false;


    ngOnInit() {

        this.bloquearPagina();

        this.formularioIniciar(this.route);

        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.lstEstado.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.lstEstado.push({ label: 'Activo', value: 'A' });
        this.lstEstado.push({ label: 'Inactivo', value: 'I' });

        const p1 = this.cargarAplicaciones();
        this.lstProcesosA = [];
        this.lstProcesosA.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        const p2 = this.servicio.listarProcesos(null).then(res => {
            this.desbloquearPagina();
            res.forEach(ele => {
                this.lstProcesosA.push({ label: ele.descripcion, value: ele.codigo });
            });
        })

        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A';
                this.accionnuevo = true;
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER) {

                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoSyTipodocumento);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.servicio.obtenerdtoporid(tempdto).then(resp => {
                        resp.ultimafechamodif = new Date(resp.ultimafechamodif);
                        this.dto = resp;
                        this.dtoDetalleFiltro.tipodocumentoid = this.dto.tipodocumentoid;

                        this.servicio.listarDtoPorHeader(this.dto.tipodocumentoid).then(det => {
                            this.lstDetalle = det;
                            this.desbloquearPagina();
                        });
                    });
                }
            }
        });
    }

    validar(): boolean {
        let retorno = false;

        if (this.estaVacio(this.dto.tipodocumentoid)) {
            this.mostrarMensajeAdvertencia('Debe ingresar el código.');
            retorno = true;
        }

        if (this.estaVacio(this.dto.descripcionlocal)) {
            this.mostrarMensajeAdvertencia('Debe ingresar el nombre.');
            retorno = true;
        }

        return retorno;
    }

    coreGuardar(): void {

        if (this.validar()) {
            return;
        }

        let accion = '';
        if (this.accion == this.ACCIONES.NUEVO) {
            accion = 'INSERTAR';

        } else {
            accion = 'ACTUALIZAR';
        }

        this.servicio.validar(this.dto, accion).then(
            resp => {
                if (!this.esListaVacia(resp)) {
                    for (let i = 0, len = resp.length; i < len; i++) {
                        this.messageService.add({
                            severity: 'error', summary: 'Error',
                            detail: resp[i].mensaje
                        });
                    }
                } else {

                    if (this.accion == this.ACCIONES.NUEVO) {
                        this.bloquearPagina();
                        this.dto.detalle = this.lstDetalle;
                        this.servicio.registrar(this.dto).then(res => {
                            this.desbloquearPagina();
                            if (this.transaccionResultado(res)) {
                                this.coreSalir();
                            }
                        }
                        );
                    } else if (this.accion == this.ACCIONES.EDITAR) {
                        this.bloquearPagina();
                        this.dto.detalle = this.lstDetalle;
                        this.servicio.actualizar(this.dto).then(res => {
                            this.desbloquearPagina();
                            if (this.transaccionResultado(res)) {
                                this.coreSalir();
                            }
                        }
                        );
                    }
                }
            }
        )


    }
    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_sytipodocumento_listado], { skipLocationChange: true })
    }
    coreMensaje(mensage: MensajeController): void {
    }

    coreAccion(accion: any): void {
        console.log(accion);
    }

    cargarAplicaciones(): Promise<number> {
        this.lstAplicacion.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.aplicacionesmastService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstAplicacion.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }

    cargarProceso(): Promise<number> {
        this.lstProcesos = [];
        this.lstProcesos.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.bloquearPagina();
        return this.servicio.listarProcesos(this.dtoDetalle.aplicacioncodigo).then(res => {
            this.desbloquearPagina();
            res.forEach(ele => {
                this.lstProcesos.push({ label: ele.descripcion, value: ele.codigo });
            });
            return 1;
        })
    }



    agregarDetalle() {
        this.dtoDetalle = new DtoSyTipodocumentoproceso();
        this.detalleAccion = 'N';
        this.dtoDetalle.ultimafechamodif = new Date();
        this.verDetalle = true;
    }

    elminiarDetalle(row: any) {

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Desea eliminar este registro?',
            accept: () => {
                this.dtoDetalle = row;
                this.dtoDetalle.auxAccion = 'E';
                const lst = [...this.lstDetalle];
                const aux = this.lstDetalle.find(obj => obj.aplicacioncodigo == this.dtoDetalle.aplicacioncodigo && obj.procesocodigo == this.dtoDetalle.procesocodigo);
                const index = this.lstDetalle.indexOf(aux);
                lst[index] = this.dtoDetalle;
                this.lstDetalle = lst;
            },
            key: "confirm"
        });
    }

    accionverDetalle(row: any) {
        this.dtoDetalle = row;
        this.detalleAccion = 'V';
        this.verDetalle = true;
    }

    editarDetalle(row: any) {
        this.dtoDetalle = row;
        this.detalleAccion = 'A';
        this.verDetalle = true;
    }

    validarDetalle(): boolean {
        let retorno = false;

        if (this.estaVacio(this.dtoDetalle.procesocodigo)) {
            this.mostrarMensajeAdvertencia('Debe ingresar el proceso.');
            retorno = true;
        }

        if (this.estaVacio(this.dtoDetalle.aplicacioncodigo)) {
            this.mostrarMensajeAdvertencia('Debe seleccionar la aplicación.');
            retorno = true;
        }

        var existe = this.lstDetalle.filter(x => x.auxAccion != 'E').find(obj => obj.aplicacioncodigo == this.dtoDetalle.aplicacioncodigo && obj.procesocodigo == this.dtoDetalle.procesocodigo);
        if (existe != null) {
            this.mostrarMensajeAdvertencia('Ya existe un registro con el mismo código.');
            retorno = true;
        }

        return retorno;
    }

    guardarDetalle() {
        this.dtoDetalle.auxAccion = this.detalleAccion;

        if (this.dtoDetalle.auxAccion === 'N') {

            if (this.validarDetalle()) {
                return;
            }

            const temp = [...this.lstDetalle];
            temp.push(this.dtoDetalle);
            this.lstDetalle = temp;
        } else if (this.dtoDetalle.auxAccion === 'A') {
            const lst = [...this.lstDetalle];
            const aux = this.lstDetalle.find(obj => obj.tipodocumentoid === this.dtoDetalle.tipodocumentoid && obj.aplicacioncodigo == this.dtoDetalle.aplicacioncodigo && obj.procesocodigo == this.dtoDetalle.procesocodigo);
            const index = this.lstDetalle.indexOf(aux);
            lst[index] = this.dtoDetalle;
            this.lstDetalle = lst;
        }
        this.verDetalle = false;

    }

    salirDetalle() {
        //this.dtoDetalle = new DtoComunMaMiscelaneosdetalle();
        this.verDetalle = false;
    }

    onchangeAplicacion() {
        this.cargarProceso();
    }


}
