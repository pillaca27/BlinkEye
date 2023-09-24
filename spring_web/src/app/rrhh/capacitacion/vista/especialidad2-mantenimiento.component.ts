import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { convertDateStringsToDates } from 'src/app/util/dateutils';
import { DtoHrEspecialidad2 } from '../dominio/DtoHrEspecialidad2';
import { Especialidad2Service } from '../servicio/especialidad2.service';
import { PersonaComunSelectorComponent } from './personacomunselector.component';

@Component({
  selector: 'app-especialidad2-mantenimiento',
  templateUrl: './especialidad2-mantenimiento.component.html'
})
export class Especialidad2MantenimientoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    messageService: MessageService,
    private confirmacionService: ConfirmationService,
    private route: ActivatedRoute,
    private especialidad2Service: Especialidad2Service
  ) { 
    super(messageService)
  }

  @ViewChild(PersonaComunSelectorComponent, { static: false })
  personaComunSelectorComponent: PersonaComunSelectorComponent;

  lstEstados: SelectItem[] = [];
  editable = false;
  dtoHrEspecialidad2: DtoHrEspecialidad2 = new DtoHrEspecialidad2();
  empleadoTipo:  string;

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
        this.dtoHrEspecialidad2 = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
        this.editar();
      }

  }

  nuevo() {
    this.dtoHrEspecialidad2.estado = 'P';
    this.desbloquearPagina();
  }

  editar() {
    this.especialidad2Service.obtenerDto(this.dtoHrEspecialidad2).then(
      resp => {
        this.dtoHrEspecialidad2 = resp;
        this.desbloquearPagina();
      }
    );
  }

  verSolicitante() {
    this.empleadoTipo = 'solicitante';
    this.personaComunSelectorComponent.coreIniciarComponente();
  }

  limpiarSolicitante() {
    this.dtoHrEspecialidad2.auxSolicitanteNombre = null;
    this.dtoHrEspecialidad2.empleadoSolicitante = null;
  }

  guardar() {
    this.bloquearPagina();
    if (this.accion === this.ACCIONES.EDITAR) {
      this.especialidad2Service.actualizar(this.dtoHrEspecialidad2).then(
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
      this.especialidad2Service.registrar(this.dtoHrEspecialidad2).then(
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

  salir() {
    this.router.navigate(['portal/erp/especialidad2'], { skipLocationChange: true })
  }

  cargarEmpleado(empleado: any) {
      this.dtoHrEspecialidad2.auxSolicitanteNombre = empleado.data.nombrecompleto;
      this.dtoHrEspecialidad2.empleadoSolicitante = empleado.data.empleado;
  }

}
