import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { SelectItem, MessageService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';

import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';


import { MaUnidadnegocioComunService } from '@framework-comun/core/servicio/maunidadnegocio-comun.service';
import { SeguridadUsuarioLogin } from '@framework/modelo/seguridad/SeguridadUsuarioLogin';
//import { LoginProveedorService } from 'src/app/royal/modulos/proveedor/login-proveedor/servicio/loginProveedor.service';

import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { EmpleadomastComunService } from '@framework-comun/core/servicio/empleadomast-comun.service';
import { DtoComunEmpleadomast } from '@framework-comun/core/dominio/dto/DtoComunEmpleadomast';
import { DtoComunPersonamast } from '@framework-comun/core/dominio/dto/DtoComunPersonamast';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';

@Component({
  selector: 'app-empleadomast-mantenimiento',
  templateUrl: 'empleadomast-mantenimiento.component.html',
  styles: [`:host ::ng-deep .p-button.p-button-icon-only {
        width: 2.357rem;
        padding: 0.2rem 0;
    }
    `]
})
export class EmpleadomastMantenimientoComponent extends FormularioComponent implements OnInit {

  @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
  @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
  @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;

  lstEstados: SelectItem[] = [];
  lstCompanias: SelectItem[] = []
  lstUnidadNegocio: SelectItem[] = []
  lstResponsable: SelectItem[] = []
  lstDeparamentos: SelectItem[] = []
  dto: DtoComunEmpleadomast = new DtoComunEmpleadomast();

  constructor(
    messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute,
    private empleadomastService: EmpleadomastComunService,
    private comunServive: MaPersonagrupoComunService,
    private companiasService: LoginService,
    private unidadNegocioService: MaUnidadnegocioComunService,
    servicioComun: ServicioComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor
  ) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngOnInit() {
    this.lstUnidadNegocio.push({ label: 'Todos', value: null })
    this.lstDeparamentos.push({ label: 'Todos', value: null })
  }

  iniciarComponenteEmpleado(dto: any, objetoTitulo: any, accion: any) {
    this.bloquearPagina();
    this.objetoTitulo = objetoTitulo
    this.accion = accion
    let tempdtoPer = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunPersonamast);
    let dtoTemp = new DtoComunEmpleadomast();
    dtoTemp.empleado = tempdtoPer.persona
    //dtoTemp.companiasocio = dto.companiasocio;
    const p1 = this.listarCompanias()
    const p2 = this.listarUnidadNegocio()
    const p3 = this.listarResponsables()
    const p4 = this.listarDepartamentos()


    Promise.all([p1, p2, p3, p4]).then(resp => {
      this.desbloquearPagina();
      if (tempdtoPer.esempleado == "N") {
        this.dto.empleado = tempdtoPer.persona
        this.dto.flageducacioncompletaiep = 'S'
        this.dto.estado = 'A'
        this.dto.estadoempleado = '0'
        this.dto.companiasocio = this.getUsuarioActual().companiaCodigo
        this.dto.unidadnegocioasignada = 'CUSC'
        this.dto.deptoorganizacion = '013'
      } else {
        var tempdto = dtoTemp
        if (tempdto != undefined && tempdto != null) {
          this.bloquearPagina();
          this.empleadomastService.obtenerDto(tempdto).then(resp => {
            this.dto = resp;
            this.desbloquearPagina();
          });
        }
      }
    });
  }

  coreGuardar(): void {
    if (this.accion == this.ACCIONES.NUEVO) {
      this.bloquearPagina();
      this.empleadomastService.registrar(this.dto).then(res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          this.coreSalir();
        }
      }
      );
    } else if (this.accion == this.ACCIONES.EDITAR) {
      this.bloquearPagina();
      this.empleadomastService.actualizar(this.dto).then(res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          this.coreSalir();
        }
      }
      );
    } else if (this.accion == this.ACCIONES.ELIMINAR) {
      this.bloquearPagina();
      this.empleadomastService.eliminar(this.dto).then(res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          this.coreSalir();
        }
      }
      );
    }
  }

  coreSalir(): void {
    this.router.navigate([ConstanteComun.ruta_personamast_listado], { skipLocationChange: true })
  }

  listarCompanias(): Promise<number> {
    let usuario = new SeguridadUsuarioLogin()
    usuario.usuario = this.getUsuarioActual().usuario;
    return this.companiasService.listarcompaniasporusuario(usuario).then(res => {
      res.forEach(ele => {
        this.lstCompanias.push({ label: ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarUnidadNegocio(): Promise<number> {
    return this.unidadNegocioService.listarActivos().then(res => {
      res.forEach(ele => {
        this.lstUnidadNegocio.push({ label: ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }
  listarResponsables(): Promise<number> {
    return this.comunServive.listarResponsables().then(res => {
      res.forEach(ele => {
        this.lstResponsable.push({ label: ele.codigo.trim() + ' ' + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }
  listarDepartamentos(): Promise<number> {
    return this.comunServive.listarDepartamentos().then(res => {
      res.forEach(ele => {
        console.log(ele)
        this.lstDeparamentos.push({ label: ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  mostrarSelectorCentroCosto() {
    this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTO', ''));
  }

  limpiarCostos() {
    this.dto.centrocostos = ''
    this.dto.centrocostosDescri = ''
  }

  coreExportar(tipo: string): void { }

  coreMensaje(mensage: MensajeController): void {
    if (mensage.componente == 'CENTROCOSTO') {
      this.dto.centrocostos = mensage.resultado.costcenter.trim()
      this.dto.centrocostosDescri = mensage.resultado.localname
    }
  }

  coreAccion(accion: any): void { }

}
