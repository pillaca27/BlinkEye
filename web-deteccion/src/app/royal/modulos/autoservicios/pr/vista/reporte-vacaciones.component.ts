import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { DatePipe } from '@angular/common';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';

import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { FiltroReporteVacacionesPago } from '../dominio/filtro/FiltroReporteVacacionesPago';
import { FiltroReporteVacacionesUtilizacion } from '../dominio/filtro/FiltroReporteVacacionesUtilizacion';
import { FiltroReporteVacacionesResumen } from '../dominio/filtro/FiltroReporteVacacionesResumen';
import { FiltroReporteVacacionesPendientesGoce } from '../dominio/filtro/FiltroReporteVacacionesPendientesGoce';
import { CompaniaSelectorComponent } from '@framework-comun/core/vista/compania-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { SucursalselectorComponent } from '@framework-comun/contabilidad/vista/sucursalselector.component';
import { UnidadnegocioSelectorComponent } from '@framework-comun/core/vista/unidadnegocio-selector.component';
import { PrReporteVacacionServicio } from '../servicio/PrReporteVacacionServicio';

@Component({
  templateUrl: './reporte-vacaciones.component.html'
})
export class ReporteVacacionesComponent extends FormularioComponent implements OnInit, UIListadoController {

  @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
  @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;
  @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
  @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: SucursalselectorComponent;
  @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;

  filtroPago: FiltroReporteVacacionesPago = new FiltroReporteVacacionesPago();
  filtroUtilizacion: FiltroReporteVacacionesUtilizacion = new FiltroReporteVacacionesUtilizacion();
  filtroResumen: FiltroReporteVacacionesResumen = new FiltroReporteVacacionesResumen();
  filtroPendientes: FiltroReporteVacacionesPendientesGoce = new FiltroReporteVacacionesPendientesGoce();

  registroSeleccionadoResumen: any;
  registroSeleccionadoPendientesGoce: any;

  @ViewChild('vPago', { static: false }) vPago;
  @ViewChild('vUtilizacion', { static: false }) vUtilizacion;

  constructor(
    private prReporteVacacionServicio: PrReporteVacacionServicio,
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
  coreAccion(accion: string): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    this.bloquearPagina();
    this.prReporteVacacionServicio.validarPermisos().then(
      res => {
        if (res.length > 0) {
          res.forEach(
            r => {
              this.mostrarMensajeAdvertenciaNoClear(r.codigo);
            }
          );
        }
      }
    );
    this.prReporteVacacionServicio.obtenerFiltros().then(
      res => {
        this.filtroPago = res.filtroPago;
        this.filtroPendientes = res.filtroPendientes;
        this.filtroResumen = res.filtroResumen;
        this.filtroUtilizacion = res.filtroUtilizacion;
        if (this.filtroPago.periodo != null) {
          this.buscar1();
        }
        if (this.filtroUtilizacion.desde != null && this.filtroUtilizacion.hasta != null && this.filtroUtilizacion.desde < this.filtroUtilizacion.hasta) {
          this.buscar2();
        }
        this.buscar3();
        this.buscar4();
      }
    );
  }
  coreMensaje(mensage: MensajeController): void {
    if (mensage.componente == 'COMPANIA1') {
      this.filtroPago.compania = mensage.resultado.companyowner;
      this.filtroPago.auxCompaniaNombre = mensage.resultado.description;
    }
    else if (mensage.componente == 'TIPOPLANILLA1') {
      this.filtroPago.tipoPlanilla = mensage.resultado.codigo;
      this.filtroPago.auxTipoPlanillaNombre = mensage.resultado.descripcion;
    }
    else if (mensage.componente == 'CENTROCOSTOS1') {
      this.filtroPago.centroCosto = mensage.resultado.costcenter;
      this.filtroPago.auxCentroCostoNombre = mensage.resultado.localname;
    }
    else if (mensage.componente == 'SUCURSAL1') {
      this.filtroPago.sucursal = mensage.resultado.sucursal;
      this.filtroPago.auxSucursalNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'UNIDADNEGOCIO1') {
      this.filtroPago.unidadNegocio = mensage.resultado.unidadnegocio;
      this.filtroPago.auxUnidadNegocioNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'COMPANIA2') {
      this.filtroUtilizacion.compania = mensage.resultado.companyowner;
      this.filtroUtilizacion.auxCompaniaNombre = mensage.resultado.description;
    }
    else if (mensage.componente == 'TIPOPLANILLA2') {
      this.filtroUtilizacion.tipoPlanilla = mensage.resultado.codigo;
      this.filtroUtilizacion.auxTipoPlanillaNombre = mensage.resultado.descripcion;
    }
    else if (mensage.componente == 'CENTROCOSTOS2') {
      this.filtroUtilizacion.centroCosto = mensage.resultado.costcenter;
      this.filtroUtilizacion.auxCentroCostoNombre = mensage.resultado.localname;
    }
    else if (mensage.componente == 'SUCURSAL2') {
      this.filtroUtilizacion.sucursal = mensage.resultado.sucursal;
      this.filtroUtilizacion.auxSucursalNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'UNIDADNEGOCIO2') {
      this.filtroUtilizacion.unidadNegocio = mensage.resultado.unidadnegocio;
      this.filtroUtilizacion.auxUnidadNegocioNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'COMPANIA3') {
      this.filtroResumen.compania = mensage.resultado.companyowner;
      this.filtroResumen.auxCompaniaNombre = mensage.resultado.description;
    }
    else if (mensage.componente == 'TIPOPLANILLA3') {
      this.filtroResumen.tipoPlanilla = mensage.resultado.codigo;
      this.filtroResumen.auxTipoPlanillaNombre = mensage.resultado.descripcion;
    }
    else if (mensage.componente == 'CENTROCOSTOS3') {
      this.filtroResumen.centroCosto = mensage.resultado.costcenter;
      this.filtroResumen.auxCentroCostoNombre = mensage.resultado.localname;
    }
    else if (mensage.componente == 'SUCURSAL3') {
      this.filtroResumen.sucursal = mensage.resultado.sucursal;
      this.filtroResumen.auxSucursalNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'UNIDADNEGOCIO3') {
      this.filtroResumen.unidadNegocio = mensage.resultado.unidadnegocio;
      this.filtroResumen.auxUnidadNegocioNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'COMPANIA4') {
      this.filtroPendientes.compania = mensage.resultado.companyowner;
      this.filtroPendientes.auxCompaniaNombre = mensage.resultado.description;
    }
    else if (mensage.componente == 'TIPOPLANILLA4') {
      this.filtroPendientes.tipoPlanilla = mensage.resultado.codigo;
      this.filtroPendientes.auxTipoPlanillaNombre = mensage.resultado.descripcion;
    }
    else if (mensage.componente == 'CENTROCOSTOS4') {
      this.filtroPendientes.centroCosto = mensage.resultado.costcenter;
      this.filtroPendientes.auxCentroCostoNombre = mensage.resultado.localname;
    }
    else if (mensage.componente == 'SUCURSAL4') {
      this.filtroPendientes.sucursal = mensage.resultado.sucursal;
      this.filtroPendientes.auxSucursalNombre = mensage.resultado.descripcionlocal;
    }
    else if (mensage.componente == 'UNIDADNEGOCIO4') {
      this.filtroPendientes.unidadNegocio = mensage.resultado.unidadnegocio;
      this.filtroPendientes.auxUnidadNegocioNombre = mensage.resultado.descripcionlocal;
    }
  }

  buscarCompania1() {
    this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA1', null));
  }
  buscarTipoPlanilla1() {
    this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA1', null));
  }
  buscarCentroCosto1() {
    this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS1', null));
  }
  buscarSucursal1() {
    this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL1', null));
  }
  buscarUnidadNegocio1() {
    this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO1', null));
  }
  buscar1() {
    if (this.filtroPago.periodo == null) {
      this.mostrarMensajeAdvertencia('Seleccionar Año / Mes');
      return;
    }
    this.bloquearPagina();
    this.filtroPago.anio = this.filtroPago.periodo.getFullYear();
    this.filtroPago.mes = this.filtroPago.periodo.getMonth() + 1;
    this.prReporteVacacionServicio.reportePago(this.filtroPago).then(res => {
      this.desbloquearPagina();
      this.vPago.pdfSrc = res.reporteBase64;
      this.vPago.refresh();
    });
  }
  buscarCompania2() {
    this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA2', null));
  }
  buscarTipoPlanilla2() {
    this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA2', null));
  }
  buscarCentroCosto2() {
    this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS2', null));
  }
  buscarSucursal2() {
    this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL2', null));
  }
  buscarUnidadNegocio2() {
    this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO2', null));
  }
  buscar2() {
    if (this.filtroUtilizacion.desde == null) {
      this.mostrarMensajeAdvertencia('Seleccionar el rango de fechas');
      return;
    }
    if (this.filtroUtilizacion.hasta == null) {
      this.mostrarMensajeAdvertencia('Seleccionar el rango de fechas');
      return;
    }
    if (this.filtroUtilizacion.desde >= this.filtroUtilizacion.hasta) {
      this.mostrarMensajeAdvertencia('La fecha hasta debe ser mayor a la fecha desde');
      return;
    }
    this.bloquearPagina();
    this.prReporteVacacionServicio.reporteUtilizacion(this.filtroUtilizacion).then(res => {
      this.desbloquearPagina();
      this.vUtilizacion.pdfSrc = res.reporteBase64;
      this.vUtilizacion.refresh();
    });
  }
  utilizacionCalcularPeriodo() {
    if (this.filtroUtilizacion.desde != null) {
      var anio = this.filtroUtilizacion.desde.getFullYear();
      var mes = this.filtroUtilizacion.desde.getMonth() + 1;
      return anio + '-' + (mes < 10 ? '0' + mes : mes.toString());
    }
    return '';
  }
  buscarCompania3() {
    this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA3', null));
  }
  buscarTipoPlanilla3() {
    this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA3', null));
  }
  buscarCentroCosto3() {
    this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS3', null));
  }
  buscarSucursal3() {
    this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL3', null));
  }
  buscarUnidadNegocio3() {
    this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO3', null));
  }
  buscar3() {
    this.registroSeleccionadoResumen = null;
    this.bloquearPagina();
    this.prReporteVacacionServicio.reporteResumen(this.filtroResumen).then(res => {
      this.desbloquearPagina();
      this.filtroResumen.reporte = res.reporte;
    });
  }
  buscarCompania4() {
    this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA4', null));
  }
  buscarTipoPlanilla4() {
    this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA4', null));
  }
  buscarCentroCosto4() {
    this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS4', null));
  }
  buscarSucursal4() {
    this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL4', null));
  }
  buscarUnidadNegocio4() {
    this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO4', null));
  }
  buscar4() {
    this.registroSeleccionadoPendientesGoce = null;
    this.bloquearPagina();
    this.prReporteVacacionServicio.reportePendientes(this.filtroPendientes).then(res => {
      this.desbloquearPagina();
      this.filtroPendientes.reporte = res.reporte;
    });
  }
}
