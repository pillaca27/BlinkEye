import { DtoComunCompanyownerrecurso } from './../dominio/dto/dtocompanyownerrecurso';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CompaniaDetalleMantenimientoComponent } from './companiarecursos_detalle_mantenimiento.component';
import { ActivatedRoute } from '@angular/router';
import { CompaniaRecursosService } from '../servicio/companiarecursos.service';
import { Companyownerrecurso, CompanyownerrecursoPk } from '../dominio/dto/companyownerrecurso';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';

@Component({
    selector: 'app-companiarecursosdetalle',
    templateUrl: './companiarecursos_detalle.component.html'
})
export class CompaniaDetalleComponent extends PrincipalBaseComponent implements OnInit {
    constructor(
        private rutaActiva: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private companiaRecursosService: CompaniaRecursosService,
        private usuarioService: UsuarioService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComunService: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComunService);
    }
    listaRecurso: Companyownerrecurso[] = [];
    dtotabla: DtoComunMaMiscelaneosdetalle;

    @ViewChild(CompaniaDetalleMantenimientoComponent, {static:false})
    companiaDetalleMantenimientoComponent: CompaniaDetalleMantenimientoComponent;


    ngOnInit(): void {
        
        this.dtotabla = JSON.parse(this.rutaActiva.snapshot.params['dtoMaMiscelaneo']);
        this.usuarioService.obtenerUsuarioActual().then(
            empleado => {
                this.buscarRecursos(this.dtotabla.codigoelemento);
            });

    }

    volveraBuscar(companyownerrecurso: any) {
        this.buscarRecursos(companyownerrecurso);
    }

    buscarRecursos(codigo: string) {
        this.bloquearPagina();
        this.companiaRecursosService.obtenerListaRecursosDetalle(codigo).then(
            resp => {
                this.listaRecurso = resp;
                this.desbloquearPagina();
            }
        );
    }

    agregarRecurso() {
        this.companiaDetalleMantenimientoComponent.verVentana(this.dtotabla);
    }

    editar(data: any) {
        this.bloquearPagina();
        this.companiaRecursosService.obtenerPorId(data.companyowner, data.tiporecurso, data.periodo).then(
            resp =>{
                resp.auxString = data.auxString;
                this.desbloquearPagina();
                this.companiaDetalleMantenimientoComponent.editar(resp)
            }                
        );
    }

    eliminar(data: any) {
        var bean: DtoComunCompanyownerrecurso = new DtoComunCompanyownerrecurso();
        bean.companyowner = data.companyowner;
        bean.periodo = data.periodo;
        bean.tiporecurso = data.tiporecurso;

        this.confirmationService.confirm({
            message: '¿Desea Eliminar el registro?',
            accept: () => {
                this.bloquearPagina();
                this.companiaRecursosService.eliminar(bean).then(res => {
                    this.messageService.clear();
                    this.mostrarMensaje(`Se Eliminó El Recurso ${data.tiporecurso}`, 'info');
                    this.buscarRecursos(data.tiporecurso);
                })
                    .catch(errors => {
                        this.messageService.clear();
                        this.desbloquearPagina();
                    });
            }
        });
    }

}


