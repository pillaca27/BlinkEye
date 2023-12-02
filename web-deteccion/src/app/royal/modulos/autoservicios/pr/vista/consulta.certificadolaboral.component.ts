import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PrPlanillaempleadoServicio } from '../servicio/PrPlanillaempleadoServicio';
import { Table } from 'primeng/table';
import { FiltroCertificadoLaboral } from '../dominio/filtro/FiltroCertificadoLaboral';

@Component({
  templateUrl: './consulta.certificadolaboral.component.html'
})
export class ConsultaCertificadoLaboralComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(Table, { static: false }) dt: Table;
  @ViewChild('pdfViewerReporte', { static: false }) pdfViewerReporte;

  filtro: FiltroCertificadoLaboral = new FiltroCertificadoLaboral();
  nombreDoc: string = '';

  constructor(
    private prPlanillaempleadoServicio: PrPlanillaempleadoServicio,
    private route: ActivatedRoute,
    servicioComun: ServicioComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor,
    messageService: MessageService
  ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

  coreNuevo(): void {
    throw new Error('Method not implemented.');
  }
  coreBusquedaRapida(filtro: string): void {
    throw new Error('Method not implemented.');
  }
  coreBuscar(): void {
    throw new Error('Method not implemented.');
  }
  coreFiltro(flag: boolean): void {
    throw new Error('Method not implemented.');
  }
  coreAnular(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEliminar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreEditar(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreVer(dto: any): void {
    throw new Error('Method not implemented.');
  }
  coreExportar(tipo: string): void {
    throw new Error('Method not implemented.');
  }
  coreMensaje(mensage: MensajeController): void {
    throw new Error('Method not implemented.');
  }
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }
  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.consultaCertificadoLaboral().then(res => {
      this.desbloquearPagina();
      this.filtro = res;
    });
  }

  imprimir() {
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.obtenerCertifCorrelativo(this.filtro).then(res => {
      this.filtro.nroCertificado = res.codigo;
      this.nombreDoc = 'CertifLaboral-HR-' + res.codigo + '-' +  this.filtro.anio;
      this.prPlanillaempleadoServicio.imprimirCertificadoLaboral(this.filtro)
        .subscribe(
          (data: Blob) => {
            const link = window.URL.createObjectURL(data);
            this.pdfViewerReporte.pdfSrc = link;
            this.pdfViewerReporte.refresh();
            this.desbloquearPagina();
          }
      );
    });
  }

}
