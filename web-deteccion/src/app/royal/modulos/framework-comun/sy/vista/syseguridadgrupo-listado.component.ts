import { AplicacionesmastComunService } from '@framework-comun/seguridad/servicio/aplicacionesmast-comun.service';
import { ConstanteComunSistema } from '@framework-comun/sistema/ConstanteComunSistema';
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

import { SySeguridadgrupoService } from './../servicio/syseguridadgrupo.servicio';
import { DtoSySeguridadgrupo } from './../dominio/dto/DtoSySeguridadgrupo';
import { FiltroSySeguridadgrupo } from './../dominio/filtro/FiltroSySeguridadgrupo';

@Component({
    selector: 'app-syseguridadgrupo-listado',
    templateUrl: './syseguridadgrupo-listado.component.html',
})
export class SySeguridadgrupoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dataTableComponent: Table;
    @ViewChild('myDescripcionlocal', { static: false }) myDescripcionlocal: ElementRef;

    lstEstados: SelectItem[] = [];
    filtro: FiltroSySeguridadgrupo = new FiltroSySeguridadgrupo();
    lstAplicaciones: SelectItem[] = [];
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private sySeguridadgrupoService: SySeguridadgrupoService,
        private confirmationService: ConfirmationService,
        private aplicacionesmastService: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        
        const p1 = this.miscelaneosListar(
            this.comboEtiquetaTipo.LISTADO,
            ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.MISC_ESTADO_GENERICO,
            ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstEstados
        );
        const p2 = this.cargarAplicaciones();
        Promise.all([p1, p2]).then((resp) => {
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.formularioOninit = false;
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
        this.sySeguridadgrupoService.listarpaginado(this.filtro).then((res) => {
            this.filtro.paginacion = res;
            this.desbloquearPagina();
        });
    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void { }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        this.router.navigate([ConstanteComunSistema.ruta_syseguridadgrupo_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: DtoSySeguridadgrupo) {
        this.router.navigate([ConstanteComunSistema.ruta_syseguridadgrupo_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreVer(dto: DtoSySeguridadgrupo) {
        this.router.navigate([ConstanteComunSistema.ruta_syseguridadgrupo_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSySeguridadgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.sySeguridadgrupoService.anular(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: DtoSySeguridadgrupo) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                this.bloquearPagina();
                this.messageService.clear();
                this.sySeguridadgrupoService.eliminar(dto).then((res) => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res))
                        this.coreBuscar();
                });
            },
            key: "confirm",
        });
    }

    ngAfterViewInit() {
        this.myDescripcionlocal.nativeElement.focus();
    }

    coreSeleccionar(dto: any) {
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    coreSalir() {
        //  this.verVentanaEmergente = false;
    };

    coreIniciarComponente(msj: MensajeController) {
        this.mensajeController = msj;
        this.filtro = new FiltroSySeguridadgrupo();
        //  this.verVentanaEmergente=true;
    }

    coreMensaje(mensage: MensajeController): void {
        /*   if (mensage.componente == ConstanteSistema.GENERICO_SELECTOR) {
              //
          } */
    }

    coreMostrarSelector(selector: string) {
        /*  if (selector == ConstanteSistema.GENERICO_SELECTOR) {
             console.log(selector);
             //this.XXXXXXSelectorComponent.coreIniciarComponente(new MensajeController(this, selector, ''));
         } */
    }
    
    cargarAplicaciones(): Promise<number> {
        this.lstAplicaciones.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.aplicacionesmastService.listarAplicacionPorUsuario().then(res => {
            res.forEach(ele => {
                this.lstAplicaciones.push({ label: ele.nombre, value: ele.codigo });
            });
            return 1;
        })
    }


    coreEditarConceptos(dto: any){
        this.router.navigate(['portal/seguridad/conceptos'], { skipLocationChange: true })
    }

}
