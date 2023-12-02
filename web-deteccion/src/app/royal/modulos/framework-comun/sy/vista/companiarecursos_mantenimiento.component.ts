import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { ConstanteAngular } from './../../../../framework/angular/ConstanteAngular';
import { TituloMantenimientoComponent } from './../../../../framework/angular/controles/titulos/titulo-mantenimiento.component';
import { BotonesMantenimientoComponent } from './../../../../framework/angular/controles/botones/botones-mantenimiento.component';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { SelectItem, MessageService } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompaniaRecursosService } from '../servicio/companiarecursos.service';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';

@Component({
    selector: 'app-companiarecursosmantenimiento',
    templateUrl: './companiarecursos_mantenimiento.component.html'
})
export class CompaniaRecursosMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(TituloMantenimientoComponent, { static: false }) tituloMantenimientoComponent: TituloMantenimientoComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    constructor(
        private route: ActivatedRoute,
        private rutaActiva: ActivatedRoute,
        private maMiscelaneoDetalleService: MaMiscelaneosdetalleComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        private router: Router, messageService: MessageService, servicioComunService: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComunService); }

    recurso: DtoComunMaMiscelaneosdetalle;
    estados: SelectItem[] = [];

    ngOnInit(): void {
        super.ngOnInit();
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.accion = this.ACCIONES.EDITAR;
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.recurso = new DtoComunMaMiscelaneosdetalle();
        this.cargarEstados();
        const dtoMaMiscelaneo = JSON.parse(this.rutaActiva.snapshot.params['dtoMaMiscelaneo']);
        const p1 = this.buscar(dtoMaMiscelaneo);

        Promise.all([p1]).then(resp => {
            this.desbloquearPagina();
        })
    }

    cargarEstados() {
        this.estados.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        this.estados.push({ label: 'Activo', value: 'A' });
        this.estados.push({ label: 'Inactivo', value: 'I' });
    }

    buscar(maMiscelaneosdetalle: DtoComunMaMiscelaneosdetalle): Promise<number> {
        this.bloquearPagina();
        return this.maMiscelaneoDetalleService.obtenerDtoPk(maMiscelaneosdetalle).then(
            resp => {
                this.recurso = resp;

                return 1;
            }
        );
    }


    coreGuardar() {

        if (this.recurso.estado == null) {
            this.mostrarMensajeError('El estado es requerido.');
            return;
        }

        this.bloquearPagina();
        this.maMiscelaneoDetalleService.actualizar(this.recurso).then(
            tx => {
                this.messageService.clear();
                this.mostrarMensaje('Se Actualizó la compañia : ' + tx.codigoelemento, 'info');
                this.coreSalir();
            }).catch(error => {
                this.desbloquearPagina();
            });
    }
    coreSalir() {
        this.router.navigate([ConstanteComun.ruta_companiarecursos_listado], { skipLocationChange: true });
    }
    coreExportar(tipo: string) { }
    coreMensaje(mensage: MensajeController) { }
    coreAccion(accion: string) { }


}

