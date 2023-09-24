import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { DominioPaginacion } from 'src/app/util/DominioPaginacion';
import { FiltroHrCapacitacion } from '../dominio/FiltroHrCapacitacion';
import { FiltroReporteUsuarios } from '../dominio/FiltroReporteUsuarios';
import { CapacitacionService } from '../servicio/capacitacion.service';

@Component({
  selector: 'app-capacitacion-listado',
  templateUrl: './capacitacion-listado.component.html',
  styleUrls: ['./capacitacion-listado.component.scss']
})
export class CapacitacionListadoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    private capacitacionesService: CapacitacionService,
    private confirmationService: ConfirmationService,
    messageService: MessageService
  ) {
    super(messageService)
  }

  filtro: FiltroHrCapacitacion = new FiltroHrCapacitacion();
  paginacion: DominioPaginacion = new DominioPaginacion();
  verDesdeelInicio = false;
  lstCompanias: SelectItem[] = [];
  lstEstados: SelectItem[] = [];

  ngOnInit(): void {
    this.verDesdeelInicio = false;

    console.log('INICIO' + this.verDesdeelInicio);

    this.lstEstados = [];
    this.lstEstados.push({ label: '-- Todos --', value: '' });
    this.lstEstados.push({ label: 'Preparación', value: 'P' });
    this.lstEstados.push({ label: 'Aprobado', value: 'A' });
    this.lstEstados.push({ label: 'Anulado', value: 'N' });
    this.cargarCompanias();
  }

  cargarCompanias(): Promise<number> {
    this.lstCompanias = [];
    this.lstCompanias.push({ label: '-- Seleccione --', value: '' })
    return this.capacitacionesService.listarcompaniasporusuario().then(resp => {
      resp.forEach(obj => this.lstCompanias.push({ label: obj.nombre, value: obj.codigo.trim() }));

      return 1;
    });
  }

  buscar() {
    this.verDesdeelInicio = true;
    console.log('INICIO' + this.verDesdeelInicio);
    this.cargarEvent({ first: 0, rows: 20 });
  }

  nuevo() {
    this.router.navigate(['portal/erp/capacitaciones-mantenimiento'], { skipLocationChange: true })
  }

  editar(dto: any) {
    this.router.navigate(['portal/erp/capacitaciones-mantenimiento', JSON.stringify(dto), this.ACCIONES.EDITAR], { skipLocationChange: true })
  }

  anular(dto: any) {
    this.confirmationService.confirm({
      header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea Anular este registro ? ",
      accept: () => {
        this.bloquearPagina();
        this.capacitacionesService.anular(dto).then((res) => {
          this.desbloquearPagina();
          if (this.transaccionResultado(res))
            this.buscar();
        });
      },
    });
  }

  cargarEvent(event: LazyLoadEvent) {

    if (!this.verDesdeelInicio) {
      return;
    }

    this.filtro.paginacion.paginacionRegistrosPorPagina = 20;
    this.filtro.paginacion.paginacionListaResultado = [];
    this.filtro.paginacion.paginacionRegistroInicio = event.first;
    this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

    this.bloquearPagina();
    this.capacitacionesService.listarPaginacion(this.filtro)
      .then(pg => {
        this.paginacion = pg;
        this.desbloquearPagina();
      });
  }


  usuarios() {
    this.bloquearPagina();
    let filtro: FiltroReporteUsuarios = new FiltroReporteUsuarios();
    //filtro.centrocosto
    this.capacitacionesService.generarReporteUsuarios(filtro
    )
      .subscribe(
        res => {
          this.desbloquearPagina();
          const link = window.URL.createObjectURL(res);
          const a = document.createElement('a');
          const type = res.type.split('/');
          document.body.appendChild(a);
          a.setAttribute('style', 'display: none');
          a.href = link;

          a.download = 'Listado-Usuarios' + '.' + type[1];

          a.click();
          window.URL.revokeObjectURL(link);
          a.remove();
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }
  trama() {
    this.blocked = true;
    let filtro: FiltroReporteUsuarios = new FiltroReporteUsuarios();
    filtro.centrocostoNombre='Prueba';
    this.capacitacionesService.generarTramaUsuarios(filtro);
    this.blocked = false;
  }
  html() {
    this.blocked = true;
    let filtro: FiltroReporteUsuarios = new FiltroReporteUsuarios();
    filtro.centrocostoNombre='Prueba';
    this.capacitacionesService.generarHtmlUsuarios(filtro);
    this.blocked = false;
  }
  excel() {
    this.bloquearPagina();
    let filtro: FiltroReporteUsuarios = new FiltroReporteUsuarios();
    this.capacitacionesService.generarUsuariosExcel(filtro)
      .subscribe(
        res => {
          this.desbloquearPagina();
          this.obtenerDescarga(res, 'Listado_de_Usuarios', this.modificarFormatoFechaExport(new Date))
        }, error => {
          throw error;
        }, () => {
          console.log('Completed file download.');
        }

      );
  }

}
