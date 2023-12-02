import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from './../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from './../../framework-comun/servicioComun.service';
import { DtoTransaccionCorreoListado } from './../dominio/dto/DtoTransaccionCorreoListado';
import { DtoTransaccionCorreoFiltros } from './../dominio/dto/DtoTransaccionCorreoFiltros';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DomSanitizer } from '@angular/platform-browser';
import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { Component, OnInit, ViewChild, Input, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';


@Component({
    selector: 'app-transaccion-listado-correos',
    templateUrl: './transaccion-listado-correos.component.html'
})
export class WfTransaccionListadoCorreoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {


    dtoListado: DtoTransaccionCorreoListado[] = [];
    dtoFiltros: DtoTransaccionCorreoFiltros = new DtoTransaccionCorreoFiltros();
    conPlantillaPorNivel: boolean = false;

    constructor(
        private domSanitizer: DomSanitizer,
        private router: Router,
        private route: ActivatedRoute,
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }


    //@ViewChild(SyReporteDetalleMantenimientoComponent, { static: false }) syReporteDetalleMantenimientoComponent: SyReporteDetalleMantenimientoComponent;

    ngOnInit() {
        super.ngOnInit();
        console.log('TL')
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);


        const dtoFiltro = JSON.parse(this.route.snapshot.params['dto']) as DtoTransaccionCorreoFiltros;
        this.dtoFiltros = dtoFiltro;

        this.wfTransaccionServicio.listarProcesoCorreos(this.dtoFiltros).then(resp => {
            this.dtoListado = resp.lst;
            this.conPlantillaPorNivel = resp.reportePorNiveles == 'S' ? true : false;
            this.aplicacion = resp.aplicacion;
            this.desbloquearPagina();
        });


    }

    coreIniciarComponente(tipo_consulta: string, transaccionUUID: string) {

        this.dtoFiltros = new DtoTransaccionCorreoFiltros();
        this.dtoFiltros.tipo_consulta = tipo_consulta;
        this.dtoFiltros.transaccionUUID = transaccionUUID;
        //this.dtoFiltros.proceso_id = proceso_id;

        this.wfTransaccionServicio.listarProcesoCorreos(this.dtoFiltros).then(resp => {
            this.dtoListado = resp.lst;
            this.conPlantillaPorNivel = resp.reportePorNiveles == 'S' ? true : false;
            this.aplicacion = resp.aplicacion;
            this.desbloquearPagina();
        });

    }


    coreGuardar() { }
    coreSalir() { }

    coreExportar(tipo: string): void {
        this.dtoFiltros.tipoExportar = tipo
        this.exportar()
    }

    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }


    llamarReporte(dto: any) {
        /*dto.reportecodigo = this.dtoFiltros.proceso_id;
        dto.aplicacioncodigo = 'WH';
        dto.companiasocio = '999999';
        dto.periodo = '999999';
        dto.transaccion_id = this.dtoFiltros.transaccion_id;
        this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = true;        
        this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');*/
    }

    guardar() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'GUARDA';
        this.llamarReporte(dto);*/
    }

    aprobar() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'APROBA';
        this.llamarReporte(dto);*/
    }

    devolver() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'DEVOLV';
        this.llamarReporte(dto);*/
    }

    rechazar() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'RECHAZ';
        this.llamarReporte(dto);*/
    }

    seguimiento() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'SEGUIM';
        this.llamarReporte(dto);*/
    }

    alerta() {
        /*var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        dto.version = 'ALERTA';
        this.llamarReporte(dto);*/
    }

    clear(table: Table) {
        table.clear();
    }



    exportar() {
        if (this.esListaVacia(this.dtoListado)) {
            this.mostrarMensajevalidarexportarGridPrincipal();
            return
        }
        this.bloquearPagina();
        this.wfTransaccionServicio.exportarProcesoCorreos(this.dtoFiltros)
            .subscribe(
                res => {
                    this.desbloquearPagina();
                    this.obtenerDescarga(res, 'Listado Correos', this.modificarFormatoFechaExport(new Date))
                }, error => {
                    throw error;
                }, () => {
                    console.log('Completed file download.');
                }

            );
    }

    iconoConPlantilla: string = 'fad fa-file';
    iconoSinPlantilla: string = 'fad fa-question';

    iconoPlantilla(nivel, accion) {
        var flgEvaluar = '';

        if (accion == 'A')
            flgEvaluar = nivel.tienePlantillaAprobar;
        else if (accion == 'R')
            flgEvaluar = nivel.tienePlantillaRechazar;
        else if (accion == 'D')
            flgEvaluar = nivel.tienePlantillaDevolver;
        else if (accion == 'S')
            flgEvaluar = nivel.tienePlantillaSeguimiento;

        if (flgEvaluar == 'S')
            return this.iconoConPlantilla;

        return this.iconoSinPlantilla;
    }

    nivelActualizando: number;
    accionPlantilla: string;
    aplicacion: string;

    verPlantillaAprobar(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'A';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'APROBA';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }
    verPlantillaRechazar(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'R';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'RECHAZ';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }
    verPlantillaDevolver(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'D';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'DEVOLV';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }

    verPlantillaSeguimiento(nivel) {
        // console.log('cambio')
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'S';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'SEGUIM';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.editar(dto, 'HTML');
    }

    armarPeriodo(flujo, nivel) {
        var p = 'F';
        if (flujo > 9) {
            p = p + flujo;
        }
        else {
            p = p + '0' + flujo;
        }

        p = p + '-';

        if (nivel > 9) {
            p = p + nivel;
        }
        else {
            p = p + '0' + nivel;
        }
        return p;
    }

    confirmarPlantilla(event) {
        var temp = [...this.dtoListado];
        var index = temp.indexOf(temp.find(x => x.nivelId == this.nivelActualizando));
        if (this.accionPlantilla == 'A') {
            temp[index].tienePlantillaAprobar = 'S';
        }
        else if (this.accionPlantilla == 'R') {
            temp[index].tienePlantillaRechazar = 'S';
        }
        else if (this.accionPlantilla == 'D') {
            temp[index].tienePlantillaDevolver = 'S';
        }
        else if (this.accionPlantilla == 'S') {
            temp[index].tienePlantillaDevolver = 'S';
        }
        this.dtoListado = temp;
    }

    verPlantillaEjecucionAprobar(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'A';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'APROBA';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.verEjecucion(dto, 'HTML');
    }
    verPlantillaEjecucionRechazar(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'R';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'RECHAZ';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.verEjecucion(dto, 'HTML');
    }
    verPlantillaEjecucionDevolver(nivel: DtoTransaccionCorreoListado) {
        // this.nivelActualizando = nivel.nivelId;
        // this.accionPlantilla = 'D';
        // var dto: DtoComunSyReportearchivo = new DtoComunSyReportearchivo();
        // dto.aplicacioncodigo = this.aplicacion;
        // dto.reportecodigo = this.dtoFiltros.proceso_id;
        // dto.version = 'DEVOLV';
        // dto.companiasocio = '999999';
        // dto.periodo = this.armarPeriodo(nivel.flujoId, this.nivelActualizando);
        // dto.transaccion_id = this.dtoFiltros.transaccion_id;
        // this.syReporteDetalleMantenimientoComponent.verAsuntoGeneral = false;
        // this.syReporteDetalleMantenimientoComponent.verEjecucion(dto, 'HTML');
    }

    


}