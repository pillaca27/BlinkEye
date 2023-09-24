import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { convertDateStringsToDates } from 'src/app/util/dateutils';
import { DtoHrEspecialidad } from '../dominio/DtoHrEspecialidad';
import { EspecialidadService } from '../servicio/especialidad.service';

@Component({
  selector: 'app-especialidades-mantenimiento',
  templateUrl: './especialidades-mantenimiento.component.html',
  styleUrls: ['./especialidades-mantenimiento.component.scss']
})
export class EspecialidadesMantenimientoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    messageService: MessageService,
    private confirmationService: ConfirmationService,
    private route: ActivatedRoute,
    private especialidadService: EspecialidadService,
  ) { 
    super(messageService)
  }

  lstEstados: SelectItem[] = [];
  editable = false;
  dtoHrEspecialidad: DtoHrEspecialidad = new DtoHrEspecialidad();
  

  ngOnInit(): void {
    this.bloquearPagina();
    this.lstEstados = [];
    this.lstEstados.push({ label: 'Preparación', value: 'P' });
    this.lstEstados.push({ label: 'Aprobado', value: 'A' });
    this.lstEstados.push({ label: 'Anulado', value: 'N' });
    
    this.accion = this.route.snapshot.params.accion;

      if (this.accion !== undefined) {
        this.accion = Number(this.accion);
      }

      if (this.accion === undefined) {
        this.accion = this.ACCIONES.NUEVO;
        this.nuevo();

      } else if (this.accion === this.ACCIONES.EDITAR) {
        this.dtoHrEspecialidad = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
        this.editar();
      }
  }

  nuevo() {
    this.dtoHrEspecialidad.estado = 'P';
    this.desbloquearPagina();
  }

  editar() {
    this.especialidadService.obtenerDto(this.dtoHrEspecialidad).then(
      resp => {
        this.dtoHrEspecialidad = resp;
        this.desbloquearPagina();
      }
    );
  }

  salir() {
    this.router.navigate(['/portal/erp/especialidad'], { skipLocationChange: true })
  }

  guardar() {
    this.bloquearPagina();
    if (this.accion === this.ACCIONES.EDITAR) {
      this.especialidadService.actualizar(this.dtoHrEspecialidad).then(
        resp => {
          this.desbloquearPagina();
          if (!this.esListaVacia(resp.transaccionListaMensajes)) {
            for (let i = 0, len = resp.transaccionListaMensajes.length; i < len; i++) {
              this.messageService.add({
                severity: 'error', summary: 'Error',
                detail: resp.transaccionListaMensajes[i].mensaje
              });
            }
          } else {
            this.mostrarMensajeExito('La Especialidad se Actualizó correctamente');
            this.salir();
          }

        }
      );

    } else {
      this.especialidadService.registrar(this.dtoHrEspecialidad).then(
        resp => {
          this.desbloquearPagina();
          if (!this.esListaVacia(resp.transaccionListaMensajes)) {
            for (let i = 0, len = resp.transaccionListaMensajes.length; i < len; i++) {
              this.messageService.add({
                severity: 'error', summary: 'Error',
                detail: resp.transaccionListaMensajes[i].mensaje
              });
            }
          } else {
            this.mostrarMensajeExito('La Especialidad se registro correctamente');
            this.salir();
          }

        }
      );
    }
  }

}
