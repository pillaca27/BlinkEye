import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { DominioPaginacion } from 'src/app/util/DominioPaginacion';
import { DtoHrEspecialidad2 } from '../dominio/DtoHrEspecialidad2';
import { FiltroHrEspecialidad2 } from '../dominio/FiltroHrEspecialidad2';
import { Especialidad2Service } from '../servicio/especialidad2.service';
import { PersonaComunSelectorComponent } from './personacomunselector.component';

@Component({
  selector: 'app-especialidad2-listado',
  templateUrl: './especialidad2-listado.component.html'
})
export class Especialidad2ListadoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    private especialidad2Service: Especialidad2Service,
    private confirmationService: ConfirmationService,
    messageService: MessageService
  ) { 
    super(messageService)
  }

  @ViewChild(PersonaComunSelectorComponent, { static: false })
  personaComunSelectorComponent: PersonaComunSelectorComponent;

  filtro: FiltroHrEspecialidad2 = new FiltroHrEspecialidad2();
  dtoHrEspecialidad2: DtoHrEspecialidad2 = new DtoHrEspecialidad2();
  paginacion: DominioPaginacion = new DominioPaginacion();
  editable = false;
  verDesdeelInicio = false;
  lstEstados: SelectItem[] = [];

  ngOnInit(): void {
    this.verDesdeelInicio = false;
    console.log('INICIO' + this.verDesdeelInicio);

    this.lstEstados = [];
    this.lstEstados.push({ label: '-- Todos --', value: '' });
    this.lstEstados.push({ label: 'Preparación', value: 'P' });
    this.lstEstados.push({ label: 'Aprobado', value: 'A' });
    this.lstEstados.push({ label: 'Anulado', value: 'N' });
  }

  buscar() {
    this.verDesdeelInicio = true;
    console.log('INICIO' + this.verDesdeelInicio);
    this.cargarEvent({ first: 0, rows: 20 });
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
    this.especialidad2Service.listarPaginacion(this.filtro)
      .then(pg => {
        this.paginacion = pg;
        this.desbloquearPagina();
      });
  }

  verSolicitante() {
    this.personaComunSelectorComponent.coreIniciarComponente();
  }
  
  limpiarSolicitante() {
    this.dtoHrEspecialidad2.auxSolicitanteNombre = null;
    this.filtro.empleadoSolicitante = null;
  }

  nuevo() {
    this.router.navigate(['portal/erp/especialidad2-mantenimiento'], { skipLocationChange: true })
  }

  editar(dto: any) {
    this.router.navigate(['portal/erp/especialidad2-mantenimiento', JSON.stringify(dto), this.ACCIONES.EDITAR], { skipLocationChange: true })
  }

  anular(dto: any) {
    this.confirmationService.confirm({
      header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea Anular este registro ? ",
      accept: () => {
        this.bloquearPagina();
        this.especialidad2Service.eliminar(dto).then((res) => {
          this.desbloquearPagina();
          if (this.transaccionResultado(res))
            this.buscar();
        });
      },
    });
  }

  cargarEmpleado(empleado: any) {
    this.dtoHrEspecialidad2.auxSolicitanteNombre = empleado.data.nombrecompleto;
    this.filtro.empleadoSolicitante = empleado.data.empleado;
}

}
