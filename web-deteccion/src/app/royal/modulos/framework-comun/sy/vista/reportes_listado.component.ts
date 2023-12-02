import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
import { TituloListadoComponent } from './../../../../framework/angular/controles/titulos/titulo-listado.component';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { SelectItem, ConfirmationService, MessageService, LazyLoadEvent } from 'primeng/api';
import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { AplicacionesmastComunService } from '@framework-comun/seguridad/servicio/aplicacionesmast-comun.service';

@Component({
    selector: 'app-reporteslistado',
    templateUrl: './reportes_listado.component.html',
})
export class ReportesListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    filtro: FiltroComunSyReporte = new FiltroComunSyReporte();
    lstTopicos: SelectItem[] = [];
    lstAplicacion: SelectItem[] = [];

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private syReporte: SyReporteComunService,
        private aplicaciones: AplicacionesmastComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComunService: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComunService); }

    ngOnInit(): void {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.coreBuscar();
        var user = this.getUsuarioActual();
        console.log(user);

        this.lstAplicacion.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.aplicaciones.listarActivos().then(
            res => {
                res.forEach(r => {
                    this.lstAplicacion.push({ label: r.nombre, value: r.codigo.trim() });
                });
                this.filtro.aplicacioncodigo = "WH";
                this.cambiarAplicacion();
                this.coreBuscar();
            }
        );
    }

    cambiarAplicacion() {
        this.lstTopicos = [];
        this.lstTopicos.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.syReporte.obtenerTopicos(this.filtro.aplicacioncodigo).then(
            res => {
                res.forEach(r => {
                    this.lstTopicos.push({ label: r.descripcion, value: r.codigo.trim() });
                });
            }
        );
    }

    preBuscar(event?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar();
        }
    }

    coreNuevo() { }
    coreBusquedaRapida(filtro: string) { }
    coreBuscar() {
        this.cargar({ first: 0, rows: 20 });
    }

    cargar(event: LazyLoadEvent) {

        if (!this.blocked) {
            this.bloquearPagina();
        }

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        sessionStorage.setItem(this.nombreParametroSession, JSON.stringify(this.filtro));
        this.syReporte.listarpaginadoReportes(this.filtro)
            .then(pg => {
                var lista = pg.paginacionListaResultado;
                this.filtro.paginacion = pg;
                this.desbloquearPagina();
            });
    }


    coreFiltro(flag: boolean) { }
    coreAnular(dto: any) { }
    coreEliminar() {
    }
    coreEditar() {
    }
    coreVer(dto: any) { }
    coreExportar(tipo: string) { }
    coreMensaje() { }
    coreAccion(accion: string) { }

    ngAfterViewInit() {
        this.myDescripcion.nativeElement.focus();
    }

    onRowDblclick(dto) {
       console.log(dto);
        if(!this.estaVacio(dto.ventanaobjeto)){
            this.router.navigate([dto.ventanaobjeto, JSON.stringify(dto), this.ACCIONES.VER, 'PEDIDO'], { skipLocationChange: true })
        }        
    }


}


