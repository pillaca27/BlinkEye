import { PaisComunService } from './../../servicio/pais-comun.service';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';

import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { FiltroComunPais } from '@framework-comun/core/dominio/filtro/FiltroComunPais';
import { DtoComunPais } from '@framework-comun/core/dominio/dto/DtoComunPais';

@Component({
    selector: 'app-pais-listado',
    templateUrl: './pais-listado.component.html',
})
export class PaisListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myNombre', { static: false }) myNombre: ElementRef;

    lstEstados: SelectItem[] = [];
    filtro: FiltroComunPais = new FiltroComunPais();
    dto: DtoComunPais = new DtoComunPais();
    displayagregarPais: boolean = false;

    accionform: string = "";
    habilitarcampo: boolean = false;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private paisService: PaisComunService,
        private usuarioService: UsuarioService,
        private confirmationService: ConfirmationService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        const p1 = this.comboestados();

        Promise.all([p1]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
            this.usuarioService.obtenerUsuarioActual().then(
                res => {
                    console.log("datos usuario:", res);

                    this.desbloquearPagina();
                    this.dataTableComponent.reset();
                }
            )
            this.desbloquearPagina();

        });
    }

    defaultBuscar(event) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.coreBuscar();
        }
    }

    coreBuscar(): void {
        this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.grillaCargarDatos({ first: this.dataTableComponent.first });
    }

    grillaCargarDatos(event: LazyLoadEvent) {
        if (this.formularioOninit == true) return;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.paisService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.dto = new DtoComunPais();
        this.habilitarcampo = false;
        this.displayagregarPais = true;
        this.accionform = "NUEVO";

        this.dto.estado = 'A';
        //this.router.navigate([ConstanteCore.ruta_pais_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoComunPais) {
        console.log("dto editar:", dto);
        this.accionform = "EDITAR";
        this.habilitarcampo = false;
        this.dto = dto;
        this.displayagregarPais = true;
        // this.router.navigate([ConstanteCore.ruta_pais_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoComunPais) {
        this.accionform = "VER";
        this.dto = dto;
        this.habilitarcampo = true;
        this.displayagregarPais = true;
        //   this.router.navigate([ConstanteCore.ruta_pais_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoComunPais) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.paisService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoComunPais) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.paisService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    ngAfterViewInit() {
        this.myNombre.nativeElement.focus();
    }

    coreSeleccionar(dto: any) {
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSalir() {
        //   this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroComunPais();
        // this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        // if (mensage.componente == ConstanteCore.GENERICO_SELECTOR) {
        //     //
        // }
    }

    coreMostrarSelector(selector: string) {
        // if (selector == ConstanteCore.GENERICO_SELECTOR) {
        //     console.log(selector);
        //     //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
        // }
    }

    guardarmodalpais() {

        if (this.estaVacio(this.dto.pais)) {
            this.mostrarMensajeError('Ingrese codigo del Pais.');
            return;
        }

        if (this.estaVacio(this.dto.descripcioncorta)) {
            this.mostrarMensajeError('Ingrese descripción.');
            return;
        }

        if (this.accionform == "NUEVO") {
            this.bloquearPagina();
            this.paisService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.displayagregarPais = false;
                    this.dataTableComponent.reset();
                    //this.coreSalir();
                }
            }
            );
        } else if (this.accionform == "EDITAR") {
            this.bloquearPagina();
            this.paisService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.displayagregarPais = false;
                    this.dataTableComponent.reset();
                }
            }
            );
        }


    }

    salirmodalpais() {
        this.displayagregarPais = false;
    }

    comboestados() {
        this.lstEstados.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' })
    }


}
