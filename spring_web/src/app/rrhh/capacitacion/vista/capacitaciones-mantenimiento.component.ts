import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DtoHrCapacitacion } from '../dominio/DtoHrCapacitacion';
import { DtoHrEmpleadoCapacitacion } from '../dominio/DtoHrEmpleadoCapacitacion';
import { BaseComponent } from 'src/app/util/BaseComponent';
import { ActivatedRoute, Router } from '@angular/router';
import { convertDateStringsToDates } from 'src/app/util/dateutils';
import { CapacitacionService } from '../servicio/capacitacion.service';
import { PersonaComunSelectorComponent } from './personacomunselector.component';
import { CursoComunSelectorComponent } from './cursocomunselector.component';

@Component({
  selector: 'app-capacitaciones-mantenimiento',
  templateUrl: './capacitaciones-mantenimiento.component.html'
})
export class CapacitacionesMantenimientoComponent extends BaseComponent implements OnInit {

  constructor(
    private router: Router,
    messageService: MessageService,
    private confirmationService: ConfirmationService,
    private route: ActivatedRoute,
    private capacitacionesService: CapacitacionService,
  ) {
    super(messageService)
  }

  @ViewChild(PersonaComunSelectorComponent, { static: false })
  personaComunSelectorComponent: PersonaComunSelectorComponent;

  @ViewChild(CursoComunSelectorComponent, { static: false })
  cursoComunSelectorComponent: CursoComunSelectorComponent;

  lstCompanias: SelectItem[] = [];
  lstEstados: SelectItem[] = [];
  editable = false;
  dtoHrCapacitacion: DtoHrCapacitacion = new DtoHrCapacitacion();
  empladoTipo: string;


  ngOnInit(): void {
    this.bloquearPagina();
    this.lstEstados = [];
    this.lstEstados.push({ label: 'Preparación', value: 'P' });
    this.lstEstados.push({ label: 'Aprobado', value: 'A' });
    this.lstEstados.push({ label: 'Anulado', value: 'N' });

    const p1 = this.cargarCompanias();

    Promise.all([p1]).then(res => {

      this.accion = this.route.snapshot.params.accion;

      if (this.accion !== undefined) {
        this.accion = Number(this.accion);
      }

      if (this.accion === undefined) {
        this.accion = this.ACCIONES.NUEVO;
        this.nuevo();

      } else if (this.accion === this.ACCIONES.EDITAR) {
        this.dtoHrCapacitacion = convertDateStringsToDates(JSON.parse(this.route.snapshot.params.dto));
        this.editar();
      }
    })

  }

  cargarCompanias(): Promise<number> {
    this.lstCompanias = [];
    this.lstCompanias.push({ label: '-- Seleccione --', value: '' })
    return this.capacitacionesService.listarcompaniasporusuario().then(resp => {
      resp.forEach(obj => this.lstCompanias.push({ label: obj.nombre, value: obj.codigo.trim() }));
      return 1;
    });
  }

  nuevo() {
    this.dtoHrCapacitacion.fechaSolicitud = new Date();
    this.dtoHrCapacitacion.estado = 'PR';
    this.desbloquearPagina();
  }

  editar() {
    this.capacitacionesService.obtenerDto(this.dtoHrCapacitacion).then(
      resp => {
        this.dtoHrCapacitacion = resp;
        this.desbloquearPagina();
      }
    );
  }

  verCurso() {
    this.cursoComunSelectorComponent.coreIniciarComponente();
  }

  limpiarCurso() {
    this.dtoHrCapacitacion.auxCursoNombre = null;
    this.dtoHrCapacitacion.curso = null;
  }

  verSolicitante() {
    this.empladoTipo = 'solicitante';
    this.personaComunSelectorComponent.coreIniciarComponente();
  }

  limpiarSolicitante() {
    this.dtoHrCapacitacion.auxSolicitanteNombre = null;
    this.dtoHrCapacitacion.empleadoSolicitante = null;
  }

  guardar() {
    this.bloquearPagina();
    if (this.accion === this.ACCIONES.EDITAR) {
      this.capacitacionesService.actualizar(this.dtoHrCapacitacion).then(
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
            this.mostrarMensajeExito('La Capacitación se Actualizó correctamente');
            this.salir();
          }

        }
      );

    } else {
      this.capacitacionesService.registrar(this.dtoHrCapacitacion).then(
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
            this.mostrarMensajeExito('La Capacitación se registro correctamente');
            this.salir();
          }

        }
      );
    }
  }

  salir() {
    this.router.navigate(['portal/erp/capacitaciones'], { skipLocationChange: true })
  }

  agregarparticipante() {
    this.empladoTipo = 'participante';
    this.personaComunSelectorComponent.coreIniciarComponente();
  }

  eliminarParticipante(dto: DtoHrEmpleadoCapacitacion) {
    this.confirmationService.confirm({
      header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea Eliminar este registro ? ",
      accept: () => {
        dto.auxFlgEliminar = 'S';
      },
    });
  }

  cargarCurso(curso: any) {
    this.dtoHrCapacitacion.auxCursoNombre = curso.data.descripcion;
    this.dtoHrCapacitacion.curso = curso.data.id;
  }

  resultado: string;

  subirArchivo(dto: any, fs: any) {
    fs.click();
    //this.subiendoDto = dto;
}

  cargarArchivo(event: any) {

    this.bloquearPagina();
        const files = event.files;
        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        const filePath = files[0].name;

        const reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            const result = reader.result as string;
            this.dtoHrCapacitacion.archivo = filePath;
            this.dtoHrCapacitacion.archivodatastring = result.split('base64,')[1];
            this.desbloquearPagina();
        };

   /* this.bloquearPagina();

    var files = event.files;

    if (files.length !== 1) {
        this.desbloquearPagina();
        return;
    }
    if (files[0].size > 3000000) {
        this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 3 + 'mb');
        this.desbloquearPagina();
        return null;
    }

    // if (files[0].type != 'text/html') {
    //     this.mostrarMensajeAdvertencia('Solo se permiten archivos de extensión .html');
    //     this.desbloquearPagina();
    //     return null;
    // }

    var reader = new FileReader();
    reader.onloadend = (evt) => {
                    
        console.log(event);
        console.log(evt);
        console.log(reader);

        this.resultado = reader.result as string;

        var lines = this.resultado.split('\n');
        var cadena = '';
        for (var line = 0; line < lines.length; line++) {
            cadena = cadena + (lines[line]); //Archivo
        }            

        this.dtoHrCapacitacion.archivo = event.files[0].name;
        this.desbloquearPagina();
    }
    
    reader.readAsText(files[0]);
    */
}

  cargarEmpleado(empleado: any) {
    if (this.empladoTipo == 'participante') {
      let participante: DtoHrEmpleadoCapacitacion = new DtoHrEmpleadoCapacitacion();
      participante.empleado = empleado.data.empleado;
      participante.auxEmpleadoNombre = empleado.data.nombrecompleto;
      participante.ultimaFechaModif = new Date();
      participante.ultimoUsuario = 'MISESF';
      participante.auxFlgNuevo = 'S';

      this.dtoHrCapacitacion.lstParticipantes.push(participante);

    } else {
      this.dtoHrCapacitacion.auxSolicitanteNombre = empleado.data.nombrecompleto;
      this.dtoHrCapacitacion.empleadoSolicitante = empleado.data.empleado;
    }
  }

}
