import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { MensajeController } from './../../../../framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { FormularioComponent } from './../../../../framework/angular/component/FormularioComponent';
import { ConstanteComun } from './../../ConstanteComun';
 
import { SyReporteComunService } from '../servicio/syreporte-comun.service';
import { SyReportearchivoComunService } from '../servicio/syreportearchivo-comun.service';
import { ServicioComunService } from './../../servicioComun.service';
import { ConfirmationService, MessageService, SelectItem, LazyLoadEvent } from 'primeng/api';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { Component, OnInit, ViewChild, ChangeDetectorRef, ElementRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { FiltroComunSyLogWeb } from '../dominio/filtro/FiltroComunSyLogWeb';
import { Table } from 'primeng/table';
import { SyLogWebServicio } from '../servicio/sylogweb.service';
import { DtoComunSyLogWeb } from '../dominio/dto/DtoComunSyLogWeb';
import { ErrorServicio } from '../servicio/error.service';
import { Errordto } from '../dominio/dto/Errordto';

@Component({
    templateUrl: './error-listado.component.html',
})
export class ErrorListadoComponent extends FormularioComponent implements OnInit, UIListadoController {
    constructor(
        private confirmationService: ConfirmationService,
        private servicio: ErrorServicio,
        private route: ActivatedRoute,
        private cdref: ChangeDetectorRef,
        private router: Router,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    lstEstado: SelectItem[] = [];
    filtro: FiltroComunSyLogWeb = new FiltroComunSyLogWeb();
    lstParaModificar: Errordto[] = [];
    //error: Error = new Error();
    displayCambioEstado: boolean;
    @ViewChild(Table, { static: false }) dt: Table;

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.lstEstado.push({ label: '--Todos--', value: null })
        this.lstEstado.push({ label: 'Activo', value: 'ACT' })
        this.lstEstado.push({ label: 'Inactivo', value: 'INA' })
        this.lstEstado.push({ label: 'Informativo', value: 'INF' })
        this.lstEstado.push({ label: 'Eliminado', value: 'ELI' })
        this.filtro.desde = new Date();
        this.filtro.hasta = new Date();
        this.buscar(this.dt);
    }
    buscar1() {
        this.buscar(this.dt);
    }
    buscar(dt: any) {
        this.cargar(dt);
    }

    eliminarPorFecha() {
       
    }

    cargar(event: LazyLoadEvent) {

        if (this.formularioOninit) {
            this.formularioOninit = false;
            return;
        }

        this.bloquearPagina();

        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;

        this.servicio.listarpaginado(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.desbloquearPagina();
            });
    }

    help() {
        this.router.navigate(['spring/help-page'], { skipLocationChange: true });
    }


    coreNuevo() {

    }
    coreBusquedaRapida(filtro: string) {

    }
    coreBuscar(tabla: LazyLoadEvent) {
        this.buscar({ first: 0 })
    }
    coreFiltro(flag: boolean) {

    }
    coreAnular(dto: any) {

    }
    coreEliminar(dto: DtoComunSyLogWeb) {

    }

    trace: string;
    verModalTrace: boolean = false;

    verTrace(data: any) {
        this.trace = data.descripcionError;
        this.verModalTrace = true;
    }

    coreEditar(dto: any) {
    }
    coreVer(dto: any) {
    }
    coreExportar(tipo: string) {

    }
    coreMensaje(mensage: MensajeController) {

    }
    coreAccion(accion: string) {

    }

    defaultBuscarPaginacion(event, dt: Table) {
        if (event.keyCode === 13) {
            this.bloquearPagina();
            this.buscar(dt);
        }
    }

    ngAfterViewInit() {

    }

    limpiarLista(estado) {
        this.bloquearPagina();
        var e1=new Errordto();
        e1.estado = estado;
        this.servicio.eliminarRegistros(e1)
          .then(resp => {
            if (resp === null) {
              this.buscar(this.dt);
              this.mostrarMensajeExito("Se elimino correctamente la informacion")
            }
            this.desbloquearPagina();
          })
    }
    cambiarEstado() {
        if (!this.validarSeleccionados()) {
          return;
        }
        this.displayCambioEstado = true;
      }
      validarSeleccionados() { 
        let lstdto: Errordto[] = [];
        lstdto = this.filtro.paginacion.paginacionListaResultado
        //console.log("filtro ", this.filtro.seleccionar)
        let contador = 0;
        lstdto.forEach(element => {
          if (element.flgInactivar) {
            this.lstParaModificar.push(element)
          }
        });
        if (this.esListaVacia(this.lstParaModificar)) {
          this.mostrarMensajeAdvertencia("Debe Seleccionar Por Lo Menos Un Registro")
          return false;
        } else {
          return true;
        }
      }    
}
