import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { DtoHrEspecialidad } from '../dominio/DtoHrEspecialidad';
import { FiltroHrEspecialidad } from '../dominio/FiltroHrEspecialidad';
import { EspecialidadService } from '../servicio/especialidad.service';

@Component({
  selector: 'app-especialidad-listado',
  templateUrl: './especialidad-listado.component.html',
  styleUrls: ['./especialidad-listado.component.scss']
})
export class EspecialidadListadoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    private especialidadesService: EspecialidadService,
    private confirmationService: ConfirmationService,
    messageService: MessageService
  ) { 
    super(messageService)
  }

  filtro: FiltroHrEspecialidad = new FiltroHrEspecialidad();
  especialidad: DtoHrEspecialidad[] =  [];
  verDesdeelInicio = false;
  lstEstados: SelectItem[] = [];

  ngOnInit(): void {
    this.verDesdeelInicio = false;

    console.log('INICIO DE ESPECIALIDADES' + this.verDesdeelInicio);

      this.lstEstados = [];
      this.lstEstados.push({ label: '-- Todos --', value: '' });
      this.lstEstados.push({ label: 'Preparación', value: 'P' });
      this.lstEstados.push({ label: 'Aprobado', value: 'A' });
      this.lstEstados.push({ label: 'Anulado', value: 'N' });

  }

  buscar() {
    this.verDesdeelInicio = true;
    console.log('INICIO DE BUSCAR ESPECIALIDADES' + this.verDesdeelInicio);
    this.cargarEvent({ first: 0, rows: 20 });
  }

  nuevo() {
    this.router.navigate(['portal/erp/especialidades-mantenimiento'], { skipLocationChange: true })
  }

  editar(dto: any) {
    this.router.navigate(['portal/erp/especialidades-mantenimiento', JSON.stringify(dto), this.ACCIONES.EDITAR], { skipLocationChange: true })
  }

  anular(dto: any) {
    this.confirmationService.confirm({
      header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea Anular este registro ? ",
      accept: () => {
        this.bloquearPagina();
        this.especialidadesService.eliminar(dto).then((res) => {
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

    this.bloquearPagina();
    this.especialidadesService.listar(this.filtro)
      .then(response => {
        this.especialidad = response;
        this.desbloquearPagina();
      });
  }

}
