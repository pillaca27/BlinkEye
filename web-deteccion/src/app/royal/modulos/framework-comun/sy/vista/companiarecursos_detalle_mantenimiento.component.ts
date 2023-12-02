import { CompanyownerComunService } from '@framework-comun/core/servicio/companyowner-comun.service';
import { UsuarioService } from '@framework-seguridad/servicio/spring/seguridad/usuario.service';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { ServicioComunService } from './../../servicioComun.service';
import { NoAuthorizationInterceptor } from './../../../../framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent, accionSolicitada } from './../../../../framework/angular/component/PrincipalBaseComponent';
import { MessageService, SelectItem } from 'primeng/api';
import { Component, OnInit, EventEmitter, Output, ViewChild, ElementRef } from '@angular/core';
import { CompaniaRecursosService, CONVERTIR_FOTO } from '../servicio/companiarecursos.service';
import { Companyownerrecurso } from '../dominio/dto/companyownerrecurso';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';

@Component({
    selector: 'app-companiadetallemantenimiento',
    templateUrl: './companiarecursos_detalle_mantenimiento.component.html'
})
export class CompaniaDetalleMantenimientoComponent extends PrincipalBaseComponent implements OnInit {

    verSelector: boolean = false;
    companyownerrecurso: Companyownerrecurso;
    nombreCompania: string;
    @Output() volveraBuscar = new EventEmitter();
    api: string;
    imageSrc: string;
    companias: SelectItem[] = [];
    puedeEditar: boolean = false;

    constructor(
        private companiaRecursosService: CompaniaRecursosService,
        // private empleadomastService: EmpleadomastServicio,
        private usuarioservice: UsuarioService,
        private companiaService: CompanyownerComunService,
        messageService: MessageService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComunService: ServicioComunService
    ) { super(noAuthorizationInterceptor, messageService, servicioComunService); }

    ngOnInit(): void {
        this.companyownerrecurso = new Companyownerrecurso();
        this.cargarCompanias();
    }

    private cargarCompanias(): void {
        this.companiaService.listarActivos()
             .then(comps => {
                 comps.forEach(comp => this.companias.push({ label: comp.nombre, value: comp.codigo.trim() }));
                 this.companias.push({ label: 'Default (999999)', value: '999999' });
                 this.desbloquearPagina();
             });
    }

    eliminarImagen() {
        this.imageSrc = '';
        this.companyownerrecurso.auxString = null;
    }

    cargarRecurso(event: any) {

        this.bloquearPagina();

        const files = event.files;

        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
           this.imageSrc = result.toString();
           this.companyownerrecurso.nombrerecurso = event.files[0].name;
           this.companyownerrecurso.auxString = result.toString();

            this.desbloquearPagina();
        };
    }

    verVentana(maMiscelaneoDetalle: DtoComunMaMiscelaneosdetalle) {
        this.verSelector = true;
        this.imageSrc = '';
        this.accionS = accionSolicitada.NUEVO;
        this.puedeEditar = true;
        this.companyownerrecurso = new Companyownerrecurso();
        this.usuarioservice.obtenerUsuarioActual().then(
            empleado => {
                this.companyownerrecurso.companyowner = empleado.companiaCodigo;
                this.companyownerrecurso.ultimousuario = empleado.usuario;
                this.nombreCompania = empleado.companiaNombre;
            });

        this.companyownerrecurso.tiporecurso = maMiscelaneoDetalle.codigoelemento;

    }

    editar(data: any) {        
        this.verSelector = true;
        this.imageSrc = '';
        if(data.auxString != null){
            this.imageSrc = CONVERTIR_FOTO(data.auxString);
        }        
        this.accionS = accionSolicitada.EDITAR;
        this.puedeEditar = false;
        // this.companyownerrecurso = data;

        this.companyownerrecurso.companyowner = data.companyowner;
        this.companyownerrecurso.periodo = data.periodo;
        this.companyownerrecurso.tiporecurso = data.tiporecurso;

        this.bloquearPagina();
        this.usuarioservice.obtenerUsuarioActual().then(
            empleado => {
                this.desbloquearPagina();
               // this.companyownerrecurso.companyowner = empleado.companiaId;
                this.companyownerrecurso.ultimousuario = empleado.usuario;
                this.nombreCompania = empleado.companiaNombre;
            });
    }

    guardar() {
        if (this.companyownerrecurso.periodo === null || this.companyownerrecurso.periodo === undefined) {
            this.messageService.clear();
            this.mostrarMensaje('Es obligatorio ingresar el periodo', 'error');
            return;
        }

        this.companyownerrecurso.auxString = this.imageSrc;

        if (this.accionS === accionSolicitada.NUEVO) {
            this.bloquearPagina();
            this.companiaRecursosService.registrar(this.companyownerrecurso)
                .then(tx => {
                    this.desbloquearPagina();
                    if(this.transaccionResultado(tx)){                        
                        this.messageService.clear();
                        this.mostrarMensaje('Se Registró el Recurso: ' + tx.tiporecurso, 'info');
                        this.volveraBuscar.emit(this.companyownerrecurso.tiporecurso);
                        this.verSelector = false;
                    }                    
                })
                .catch(error => {
                    this.desbloquearPagina();
                });
        } else {
            this.bloquearPagina();
            this.companiaRecursosService.actualizar(this.companyownerrecurso).then(
                resp => {
                    this.desbloquearPagina();    
                    if(this.transaccionResultado(resp)){  
                        this.messageService.clear();
                        this.mostrarMensaje('Se Actualizó el Recurso: ' + resp.tiporecurso, 'info');
                        this.volveraBuscar.emit(resp.tiporecurso);
                        this.verSelector = false;
                    }                                    
                }
            ).catch(error => {
                this.messageService.clear();
                this.desbloquearPagina();
                this.verSelector = false;
            });
        }

    }

}
