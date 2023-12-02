import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
 
import { AplicacionesmastComunService } from '../../sg/servicio/aplicacionesmast-comun.service';
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { SyReportearchivoComunService } from '../servicio/syreportearchivo-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { ConfirmationService, MessageService, SelectItem, LazyLoadEvent } from 'primeng/api';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { Component, OnInit, ViewChild, ChangeDetectorRef, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { DtlComunSyReporte } from '../dominio/lista/DtlComunSyReporte';
import { Table } from 'primeng/table';
import { ConstanteComunSistema } from '../ConstanteComunSistema';

@Component({
    selector: 'app-syreportelistado',
    templateUrl: './syreporte-listado.component.html',
})
export class SyReporteListadoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(Table, { static: false }) dataTableComponent: Table;

    constructor(
        private aplicaciones: AplicacionesmastComunService,
        private confirmationService: ConfirmationService,
        private route: ActivatedRoute,
        private cdref: ChangeDetectorRef,
        private syReporteService: SyReportearchivoComunService,
        private syReporte: SyReporteComunService,
        private router: Router,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }
    lstTipoReporte: SelectItem[] = [];

    filtro: FiltroComunSyReporte = new FiltroComunSyReporte();
    lstAplicacion: SelectItem[] = [];
    lstEstado: SelectItem[] = [];
    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild('myDescripcion', { static: false }) myDescripcion: ElementRef;

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        //this.nombreParametroSession = "syreportelistado";
        this.bloquearPagina();

        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);

        this.lstTipoReporte.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstTipoReporte.push({ label: 'HTML', value: 'HTML' });
        this.lstTipoReporte.push({ label: 'JASPER', value: 'JASPE' });
        this.lstTipoReporte.push({ label: 'POWER', value: 'POWER' });
        this.lstTipoReporte.push({ label: 'CORREO', value: 'CORRE' });

        this.lstEstado.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        this.lstEstado.push({ label: 'Activo', value: 'A' });
        this.lstEstado.push({ label: 'Inactivo', value: 'I' });



    
       
        const p1 = this.cargarLstAplicaciones();
        Promise.all([p1]).then(res=>{
            console.log( this.lstAplicacion)
            this.filtro = this.formularioFiltrosRestaurar(this.filtro);
            this.filtro.estado = 'A';
            this.filtro.tiporeporte = 'HTML';
            //this.filtro.aplicacioncodigo = 'WH';

            this.filtro.aplicacioncodigo = this.getUsuarioActual().aplicacionCodigo; // "SN";
            this.formularioOninit = false;
            

           
            //this.filtro = this.formularioFiltrosRestaurarNombre(this.filtro, this.nombreParametroSession);
           // this.buscar(this.dt);
           this.coreBuscar();
        })
    }

    cargarLstAplicaciones():Promise<number>{
        this.lstAplicacion.push({ label: ConstanteAngular.COMBOTODOS, value: null });
        return this.aplicaciones.listarActivos().then(
            res => {
                res.forEach(r => {
                    this.lstAplicacion.push({ label: r.nombre, value: r.codigo.trim() });
                });
              
                return 1;
            }
        );
    }

    cargando = true;

 /*    buscar(dt: any) {
        this.cargar(dt);
    } */
    


    grillaCargarDatos(event: LazyLoadEvent) {

/*         if (this.cargando) {
            this.cargando = false;
            return;
        }

        if (!this.blocked) {
            this.bloquearPagina();
        }


 */

        if (this.formularioOninit == true) return;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;

        //this.filtro.paginacion.paginacionRegistroInicio = event.first;
        //this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        sessionStorage.setItem(ConstanteAngular.FILTROSESION, JSON.stringify(this.filtro));

        this.bloquearPagina();
        this.syReporte.listarPaginado(this.filtro)
            .then(pg => {
              //  var lista = pg.paginacionListaResultado as DtoComunSyReporte[];
                /*lista.forEach(
                    x => {
                        x.reporteCodigoCorte = x.reporteCodigo.substring(1, 3);
                    }
                );*/

                this.filtro.paginacion = pg;

                
                this.desbloquearPagina();
            });
    }

    help() {
        this.router.navigate(['spring/help-page'], { skipLocationChange: true });
    }

    inactivar(dto: DtoComunSyReporte) {

        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaInactivar(),
            accept: () => {

                this.bloquearPagina();
                const pk: DtoComunSyReporte = new DtoComunSyReporte();
                pk.aplicacioncodigo = dto.aplicacioncodigo;
                pk.reportecodigo = dto.reportecodigo;

                this.syReporte.obtenerDto(pk).then(reg => {
                    const pl = reg;
                    pl.estado = 'I';
                    this.syReporte.actualizar(pl).then(r => {
                        this.dt.reset();
                        this.messageService.clear();
                        this.messageService.add({
                            severity: 'info', summary: 'Información',
                            detail: this.getMensajeActualizado(dto.reportecodigo)
                        });
                    });
                });
            }
        });
    }
    ejecutar(dto: DtoComunSyReporte) {
        this.bloquearPagina();
        var pk = new DtoComunSyReporte();
        pk.aplicacioncodigo = dto.aplicacioncodigo;
        pk.reportecodigo = dto.reportecodigo;
    }

    coreNuevo() {
        this.router.navigate([ConstanteComunSistema.ruta_syreporte_mantenimiento, this.ACCIONES.NUEVO], { skipLocationChange: true });
    }
    coreBusquedaRapida(filtro: string) {

    }
    coreBuscar(/* tabla: LazyLoadEvent */) {
       /*  console.log('test');
        this.cargar({ first: 0 }) */

        this.dataTableComponent.first = this.filtro.paginacion.paginacionRegistroInicio;
        this.grillaCargarDatos({ first: this.dataTableComponent.first });
    }
    coreFiltro(flag: boolean) {

    }
    coreAnular(dto: DtlComunSyReporte) {

    }
    coreEliminar(dto: DtlComunSyReporte) {

    }
    coreEditar(dto: DtlComunSyReporte) {
        dto.tiporeporte = '';
        this.router.navigate([ConstanteComunSistema.ruta_syreporte_mantenimiento, this.ACCIONES.EDITAR, dto.aplicacioncodigo, dto.reportecodigo, dto.tiporeporte, dto.uuid], { skipLocationChange: true });
    }
    coreVer(dto: any) {
    }

    coreExportar(tipo: string): void {
        this.filtro.tipoexportar = tipo
        this.exportar()
    }

    coreMensaje(mensage: MensajeController) {

    }
    coreAccion(accion: string) {

    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            //this.buscar(dt);
            this.coreBuscar();
        }
    }

    ngAfterViewInit() {
        this.myDescripcion.nativeElement.focus();
    }

    exportar() {
        if (this.esListaVacia(this.filtro.paginacion.paginacionListaResultado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.syReporte.exportarReportes(this.filtro)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado de Reportes', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }

}
