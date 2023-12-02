import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { DatePipe } from '@angular/common';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';

import { FiltroConsultaBoleta } from "../dominio/filtro/FiltroConsultaBoleta";
import { ActivatedRoute } from '@angular/router';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PrPlanillaempleadoServicio } from '../servicio/PrPlanillaempleadoServicio';
import { DtoConsultaBoletaCabecera } from '../dominio/dto/DtoConsultaBoletaCabecera';

@Component({
  templateUrl: './consulta.boletas.component.html'
})
export class ConsultaBoletasComponent extends FormularioComponent implements OnInit, UIListadoController {

  filtro: FiltroConsultaBoleta = new FiltroConsultaBoleta();

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

    if (this.estaVacio(this.filtro.periodoId)) {
      this.mostrarMensajeAdvertencia('Seleccione un periodo');
      return;
    }

    this.bloquearPagina();
    this.prPlanillaempleadoServicio.consultaBoletasCabecera(this.filtro)
      .then(pg => {
        this.lstPlanilla = pg;
        this.desbloquearPagina();
      });

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

  lstPeriodos: SelectItem[] = [];
  lstProceso: SelectItem[] = [];
  lstTipoPlanilla: SelectItem[] = [];

  lstPlanilla: DtoConsultaBoletaCabecera[] = [];

  lstBoletaIn: any[] = [];
  lstBoletaDe: any[] = [];
  lstBoletaPa: any[] = [];

  periodosRaw: any[];

  ngOnInit() {
    super.ngOnInit();
    this.formularioIniciar(this.route);
    this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.consultaBoletasDatosInicio().then(res => {
      this.filtro = res;
      this.cargarPeriodos();
    });

  }

  cargarPeriodos() {
    this.lstPeriodos.push({ value: null, label: ' -- Seleccione --' });
    this.filtro.periodoId = null;
    this.prPlanillaempleadoServicio.listarPeriodoParaBoleta()
      .then(td => {
        this.periodosRaw = td;
        td.forEach(obj => this.lstPeriodos.push({ label: obj.nombre + (obj.estadoId == 'S' ? '' : ''), value: obj.codigo.trim() }));
        this.desbloquearPagina();
      });
  }

  /*cargarTipoPlanilla() {
    this.lstTipoPlanilla.push({ label: ' Todos ', value: '' });
    this.prPlanillaempleadoServicio.listarPlanillasActivas()
      .then(td => {
        td.forEach(obj => this.lstTipoPlanilla.push({ label: obj.descripcion, value: obj.codigo }));
      });
  }*/

  /**/

  /*asignarTipoPlanilla() {
    this.prPlanillaempleadoServicio.obtenerTipoPlanilla(this.filtro.idPeriodo)
      .then(td => {
        this.filtro.idTipoPlanilla = td.tipoplanilla;
        this.cargarProcesosPorPeriodo();
      });
  }*/


  /*
    cargarProcesosPorPeriodo() {
      this.lstProceso = [];
      this.filtro.idTipoProceso = null;
      this.prPlanillaempleadoServicio.listarProcesadosPorPeriodo(this.filtro)
        .then(td => {
          td.forEach(obj => this.lstProceso.push({ label: obj.descripcion, value: obj.codigo }));
          this.desbloquearPagina();
          if (!this.esListaVacia(td)) {
            if (this.estaVacio(this.tipoProceso)) {
              this.filtro.idTipoProceso = td[0].codigo;
            } else {
              this.filtro.idTipoProceso = this.tipoProceso;
              this.tipoProceso = null;
            }
          }
        });
    }
  */

  refrescarPeriodos() {
    this.periodosRaw = [];
    this.lstPeriodos = [];
    var auxPeriodo = this.filtro.periodoId;
    this.filtro.periodoId = null;
    this.prPlanillaempleadoServicio.listarPeriodoParaBoleta()
      .then(td => {
        this.periodosRaw = td;
        td.forEach(obj => this.lstPeriodos.push({ label: obj.nombre + (obj.estadoId == 'S' ? /*' | Conforme'*/'' : ''), value: obj.codigo.trim() }));
        this.filtro.periodoId = auxPeriodo;
      });
  }

  imprimir(bean: DtoConsultaBoletaCabecera) {
    var periodoVer = this.periodosRaw.indexOf(this.periodosRaw.find(x => x.codigo == bean.periodoId));
    if (periodoVer == this.periodosRaw.length - 1) {
      //Si el periodo a ver es el mas antiguo, dejarlo ver
    }
    else {
      //validar si el anterior fue visto
      var periodoAnterior = this.periodosRaw[periodoVer + 1];
      if (periodoAnterior.estado != 'S') {
        //this.mostrarMensajeAdvertencia("Aún tiene pendiente IMPRIMIR documentos anteriores. Por favor verificar.");
        //return;
      }
    }
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.imprimirBoleta(bean)
      .then(respuesta => {
        this.desbloquearPagina();
        if (respuesta.codigo == 'OK') {
          this.refrescarPeriodos();
          //var a = document.createElement("a");
          //a.href = "data:text/html;base64," + respuesta.nombre;
          //a.download = respuesta.descripcion;
          //a.click();
          var win = window.open();
          win.document.body.innerHTML = atob(respuesta.nombre);
        }
        else {
          this.mostrarMensajeAdvertencia(respuesta.descripcion);
        }
      });
  }

  cargarDetalle(bean: DtoConsultaBoletaCabecera) {
    var periodoVer = this.periodosRaw.indexOf(this.periodosRaw.find(x => x.codigo == bean.periodoId));
    if (periodoVer == this.periodosRaw.length - 1) {
    }
    else {
      var periodoAnterior = this.periodosRaw[periodoVer + 1];
      if (periodoAnterior.estado != 'S') {
        //this.mostrarMensajeAdvertencia("Aún tiene pendiente IMPRIMIR documentos anteriores. Por favor verificar.");
        //return;
      }
    }
    this.bloquearPagina();
    this.prPlanillaempleadoServicio.consultaBoletasDetalle(bean)
      .then(pg => {
        this.desbloquearPagina();
        this.lstBoletaIn = pg.filter(x => x.conceptoTipoIn === 'IN');
        this.lstBoletaDe = pg.filter(x => x.conceptoTipoDe === 'DE');
        this.lstBoletaPa = pg.filter(x => x.conceptoTipoPa === 'PA');
      });
  }


  limpiarResultados() {
    this.lstPlanilla = [];
    this.lstBoletaIn = [];
    this.lstBoletaDe = [];
    this.lstBoletaPa = [];
  }


}
