import { DtoSyTipodocumento } from './../dominio/dto/DtoSyTipodocumento';
import { ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { ConfirmationService, MessageService, SelectItem, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { FiltroComunSyTipoDocumento } from '../dominio/filtro/FiltroComunSyTipoDocumento';
import { DtlComunSyReporte } from '../dominio/lista/DtlComunSyReporte';
import { SyTipoDocumentoService } from '../servicio/SyTipodocumento.service';

@Component({
    selector: 'SyTipodocumento-app',
    templateUrl: 'SyTipodocumento-listado.component.html'
})

export class SyTipodocumentoListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
    constructor(
        private confirmationService: ConfirmationService,
        private route: ActivatedRoute,
        private cdref: ChangeDetectorRef,
        private syTipoDocumentoService: SyTipoDocumentoService,
        private router: Router,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }
    filtro: FiltroComunSyTipoDocumento = new FiltroComunSyTipoDocumento();
    lstEstado: SelectItem[] = [];
    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        this.nombreParametroSession = "sytipodocumentolistado";
        this.bloquearPagina();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.lstEstado.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstEstado.push({ label: 'Activo', value: 'A' });
        this.lstEstado.push({ label: 'Inactivo', value: 'I' });

        this.filtro.estado = 'A';

        this.filtro = this.formularioFiltrosRestaurar(this.filtro);

        this.formularioOninit = false;

        this.buscar(this.dt);
    }

    cargando = true;

    buscar(dt: any) {
        this.cargar(dt);
    }


    cargar(event: LazyLoadEvent) {
        if (this.formularioOninit == true) {
            this.desbloquearPagina();
            return;
        }

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));
        this.bloquearPagina();
        this.syTipoDocumentoService.listarpaginado(this.filtro).then(
            res => {
                this.filtro.paginacion = res;
                console.log(this.filtro.paginacion);
                this.desbloquearPagina();
            }
        );
    }

    coreNuevo() {
        this.router.navigate([ConstanteComun.ruta_sytipodocumento_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }
    coreBusquedaRapida(filtro: string) {

    }
    coreBuscar(tabla: LazyLoadEvent) {
        console.log('test');
        this.buscar({ first: 0 })
    }
    coreFiltro(flag: boolean) {

    }
    coreEliminar(dto: DtoSyTipodocumento) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Desea Eliminar este registro?',
            accept: () => {
                this.bloquearPagina();
                this.syTipoDocumentoService.eliminar(dto).then(res => {
                    this.desbloquearPagina();
                    this.dt.reset();
                });
            },
            key: "confirm"
        });
    }

    coreEditar(dto: DtoSyTipodocumento) {
        this.router.navigate([ConstanteComun.ruta_sytipodocumento_mantenimiento, this.ACCIONES.EDITAR, JSON.stringify(dto)], { skipLocationChange: true });
    }
    coreVer(dto: DtoSyTipodocumento) {
        this.router.navigate([ConstanteComun.ruta_sytipodocumento_mantenimiento, this.ACCIONES.VER, JSON.stringify(dto)], { skipLocationChange: true });
    }

    coreAnular(dto: DtoSyTipodocumento) {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Desea anular este registro?',
            accept: () => {
                this.bloquearPagina();
                this.syTipoDocumentoService.anular(dto).then(res => {
                    this.desbloquearPagina();
                    if (this.transaccionResultado(res)) {
                        this.dt.reset();
                    }
                });
            },
            key: "confirm"
        });

    }

    coreExportar(tipo: string): void {
    }

    coreMensaje(mensage: MensajeController) {

    }
    coreAccion(accion: string) {

    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.buscar(dt);
        }
    }

    ngAfterViewInit() {
        this.myDescripcion.nativeElement.focus();
    }

}