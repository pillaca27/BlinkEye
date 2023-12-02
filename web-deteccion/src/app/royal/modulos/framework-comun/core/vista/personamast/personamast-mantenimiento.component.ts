import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { PersonamastComunService } from './../../servicio/personamast-comun.service';
import { UbicacionGeograficaSelectorComponent } from '@framework-comun/core/vista/ubicaciongeografica-selector.component';
import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { ProveedormastMantenimientoComponent } from './proveedormast/proveedormast-mantenimiento.component';
import { EmpleadomastMantenimientoComponent } from './empleadomast/empleadomast-mantenimiento.component';
import { InfoAdicionalMastMantenimientoComponent } from './info-adicional/info-adicional-mantenimiento.component';
import { DtoComunPersonamast } from '@framework-comun/core/dominio/dto/DtoComunPersonamast';
 


@Component({
    selector: 'app-personamast-mantenimiento',
    templateUrl: 'personamast-mantenimiento.component.html',
    styles: [`:host ::ng-deep .p-ai-center {
        align-items: center;
        margin-top: 10px;
    }
    :host ::ng-deep .p-button.p-button-icon-only {
        width: 2.357rem;
        padding: 0.2rem 0;
    }
    `]
})
export class PersonamastMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    @ViewChild(UbicacionGeograficaSelectorComponent, { static: false }) ubigeoSelector: UbicacionGeograficaSelectorComponent;
    @ViewChild(ProveedormastMantenimientoComponent, { static: false }) proveedorTab: ProveedormastMantenimientoComponent;
    @ViewChild(EmpleadomastMantenimientoComponent, { static: false }) empleadoTab: EmpleadomastMantenimientoComponent;
    @ViewChild(InfoAdicionalMastMantenimientoComponent, { static: false }) infoAdicional: InfoAdicionalMastMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstTipoPersona: SelectItem[] = []
    lstTipoDocumento: SelectItem[] = []
    lstTipoDocumentoj: SelectItem[] = []
    lstCivil: SelectItem[] = []
    lstSexo: SelectItem[] = []
    dto: DtoComunPersonamast = new DtoComunPersonamast();

    esProveedorC: boolean = false
    esClienteC: boolean = false
    esEmpleadoC: boolean = false
    esPersona: boolean = false
    direcciones: boolean = false
    index: number = 0;

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private personamastService: PersonamastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        let tempdto = this.route.snapshot.params['dto'] != null ? convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunPersonamast) : null;
        this.validarTipoMaestro(tempdto)

        const p1 = this.listarEstados()
        const p2 = this.tipoPersonas()
        const p3 = this.tipoDocumentos()
        const p4 = this.listaEstadoCivil()
        const p5 = this.listaSexo()

        Promise.all([p1, p2, p3, p4, p5]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO && this.esPersona) {
                this.dto.tipopersona = 'J'
                this.dto.estado = 'A'
                this.dto.usuarioActual = this.getUsuarioActual()
                this.dto.ingresousuario = this.getUsuarioActual().usuario
                this.dto.ingresofecharegistro = new Date()
                //this.dto.origen = this.getUsuarioActual().unidadNegocioAsignadaCodigo
                this.dto.ingresoaplicacioncodigo = ConstanteComun.DEFECTO_APLICACION_WH
                this.dto.usuarioActual = this.getUsuarioActual()
                this.dto.domicliadoPais = true;
                

            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR || !this.esPersona) {
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.personamastService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.dto.domicliadoPais = this.estaVacio(this.dto.nacionalidad) ? false : true
                        this.validarMestro(tempdto)

                    });
                }
            }
        });
    }

    validarTipoMaestro(tempdto: any) {
        if (tempdto != undefined && tempdto != null) {
            if (tempdto.esProveedorC) {
                this.esProveedorC = tempdto.esProveedorC
                this.index = 1
            }
            else if (tempdto.esClienteC) {
                this.esClienteC = tempdto.esClienteC
                this.index = 1
            }
            else if (tempdto.esEmpleadoC) {
                this.esEmpleadoC = tempdto.esEmpleadoC
                this.index = 1
            }
            else if (tempdto.esPersonaC) {
                this.esPersona = tempdto.esPersonaC
            }
        } else {
            this.esPersona = true
        }

    }

    validarMestro(tempdto: any) {

        if (!this.estaVacio(this.dto.documento)) {
            this.dto.documento = this.dto.documento.trim()
        }

        if (!this.estaVacio(this.dto.documentofiscal)) {
            this.dto.documentofiscal = this.dto.documentofiscal.trim()
        }
        if (!this.estaVacio(this.dto.documentoidentidad)) {
            this.dto.documentoidentidad = this.dto.documentoidentidad.trim()
        }

        if (!this.estaVacio(tempdto.departamento)) {
            this.ubigeo(tempdto)
        }

        if (tempdto.esProveedorC) {
            this.dto.esproveedor = "S"
            this.proveedorTab.iniciarComponenteProveedor(this.dto, this.objetoTitulo, this.accion)
        }
        else if (tempdto.esClienteC) {
            this.dto.escliente = "S"
            this.desbloquearPagina();
        }
        else if (tempdto.esEmpleadoC) {
            this.dto.esempleado = "S"
            this.empleadoTab.iniciarComponenteEmpleado(this.dto, this.objetoTitulo, this.accion)
        }
        else if (tempdto.esPersonaC) {
            this.esPersona = tempdto.esPersonaC
            this.desbloquearPagina();
        } else {
            this.desbloquearPagina();
        }


        this.dto.usuarioActual = this.getUsuarioActual()
    }

    onTabChange(event) {
        // TAB event.index 0 = Inf. General
        // TAB event.index 1 = Clientes
        // TAB event.index 2 = Proveedores
        // TAB event.index 3 = Empleados
        // TAB event.index 4 = Direcciones
        // TAB event.index 5 = Inf. Adicional

        this.index = event.index;

        if (!this.esPersona) {
            if (this.index == 2) {
                this.infoAdicional.iniciarComponenteInfoAdicional(this.dto, this.objetoTitulo, this.accion, this.esPersona)
            }
        } else {
            if (this.index == 1) {
                this.infoAdicional.iniciarComponenteInfoAdicional(this.dto, this.objetoTitulo, this.accion, this.esPersona)
            }
        }

    }

    coreGuardar(): void {
        if (this.validacionAdicional()) {
            return
        }
        this.dto.flagRuc = this.validarRuc(this.dto.documento)
        if (this.accion == this.ACCIONES.NUEVO && this.esPersona) {
            this.bloquearPagina();
            this.personamastService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR || (this.accion == this.ACCIONES.NUEVO && !this.esPersona)) {
            this.bloquearPagina();
            this.coreMaestros()
        }
        else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.coreEliminarMaestros()
                },
                key: "confirm",
            });
        }
    }

    coreMaestros() {
        if (this.esProveedorC) {
            this.proveedorTab.coreGuardar()
        } else if (this.esClienteC) {

        } else if (this.esEmpleadoC) {
            this.empleadoTab.coreGuardar()
        } else {
            this.personamastService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }
    }

    coreEliminarMaestros() {
        if (this.esProveedorC) {
            this.proveedorTab.coreEliminar()
        } else if (this.esClienteC) {

        } else if (this.esEmpleadoC) {
            this.empleadoTab.coreGuardar()
        }
    }

    validacionAdicional(): boolean {
        if (this.dto.domicliadoPais && this.accion != this.ACCIONES.ELIMINAR) {
            this.dto.nacionalidad = 'PERUANA'
        }
        if (this.esPersona && this.infoAdicional != null) {
            this.dto.dtoMaPersonapersonagrupo = this.infoAdicional.dto.dtoMaPersonapersonagrupo
            this.dto.dtoPersonacuentabancaria = this.infoAdicional.dto.dtoPersonacuentabancaria
            if (this.infoAdicional.validarGrupoPersonas()) {
                return true
            }
            if (this.infoAdicional.validarCuentasBancarias()) {
                return true
            }
        }
        return false
    }

    validarRuc(ruc: any) {
        if (this.dto.tipodocumento == 'R') {
            if(!this.estaVacio(this.dto.documento)){
                if (!(ruc >= 1e10 && ruc < 11e9
                    || ruc >= 15e9 && ruc < 18e9
                    || ruc >= 2e10 && ruc < 21e9))
                    return false;
    
                for (var suma = -(ruc % 10 < 2), i = 0; i < 11; i++, ruc = ruc / 10 | 0)
                    suma += (ruc % 10) * (i % 7 + (i / 7 | 0) + 1);
                return suma % 11 === 0;
            }
        }

        return true
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_personamast_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'UBIGEO') {
            this.dto.ubigeo = mensage.resultado.descripcion;
            this.dto.provincia = mensage.resultado.provincia
            this.dto.departamento = mensage.resultado.departamento
            this.dto.codigopostal = mensage.resultado.zonapostal
        }
    }

    coreAccion(accion: any): void { }

    listarEstados() {
        this.lstEstados.push({ label: 'Seleccione', value: null });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    tipoPersonas() {
        this.lstTipoPersona.push({ label: 'Seleccione', value: null });
        this.lstTipoPersona.push({ label: 'Jurídica', value: 'J' });
        this.lstTipoPersona.push({ label: 'Natural', value: 'N' });
        this.lstTipoPersona.push({ label: 'Natural con Negocio', value: 'B' });
    }

    tipoDocumentos() {
        this.lstTipoDocumento.push({ label: 'Seleccione', value: null });
        this.lstTipoDocumento.push({ label: 'RUC', value: 'R' });
        this.lstTipoDocumento.push({ label: 'NIT extranjero', value: 'E' });

        this.lstTipoDocumentoj.push({ label: 'Seleccione', value: null });
        this.lstTipoDocumentoj.push({ label: 'DNI', value: 'D' });
        this.lstTipoDocumentoj.push({ label: 'Libreta Militar', value: 'M' });
        this.lstTipoDocumentoj.push({ label: 'Carnet Extranjería', value: 'X' });
        this.lstTipoDocumentoj.push({ label: 'Pasaporte', value: 'P' });
        this.lstTipoDocumentoj.push({ label: 'Par. Nacimiento', value: 'N' });
        this.lstTipoDocumentoj.push({ label: 'Otros', value: 'O' });
    }

    listaEstadoCivil() {
        this.lstCivil.push({ label: 'Seleccione', value: null });
        this.lstCivil.push({ label: 'Soltero', value: 'S' });
        this.lstCivil.push({ label: 'Casado', value: 'C' });
        this.lstCivil.push({ label: 'Viudo', value: 'V' });
        this.lstCivil.push({ label: 'Divorciado', value: 'D' });
        this.lstCivil.push({ label: 'Conviviente', value: 'T' });
        this.lstCivil.push({ label: 'Otros', value: 'O' });
    }

    listaSexo() {
        this.lstSexo.push({ label: 'Seleccione', value: null });
        this.lstSexo.push({ label: 'Masculino', value: 'M' });
        this.lstSexo.push({ label: 'Femenino', value: 'F' });
    }


    validarPorTipoPersona() {

        if (this.dto.tipopersona == "J") {
            this.dto.apellidomaterno = ''
            this.dto.apellidopaterno = ''
            this.dto.nombres = ''
            this.dto.estadocivil = null
            this.dto.sexo = null
        }
    }

    tipoDocumentosChange() {
        if (this.dto.tipodocumento == "D") {
            this.dto.documento = ''
            this.dto.documentoidentidad = ''
        }
    }

    ubigeo(dto: any): Promise<number> {
        return this.personamastService.listarZonas(dto).then(res => {
            res.forEach(ele => {
                this.dto.ubigeo = ele.nombre
            });
            return 1;
        });
    }
    seleccionarUbigeo() {
        this.ubigeoSelector.coreIniciarComponente(new MensajeController(this, 'UBIGEO', ''))
    }

    limpiarUbigeo() {
        this.dto.ubigeo = null
        this.dto.provincia = null
        this.dto.departamento = null
        this.dto.codigopostal = null
    }

    nombreCompletoKey() {
        if (this.dto.apellidopaterno == null) {
            this.dto.apellidopaterno = ''
        }
        if (this.dto.apellidomaterno == null) {
            this.dto.apellidomaterno = ''
        }
        if (this.dto.nombres == null) {
            this.dto.nombres = ''
        }
        this.dto.nombrecompleto = this.dto.apellidopaterno
            + " " + this.dto.apellidomaterno
            + " , " + this.dto.nombres

        this.dto.busqueda = this.dto.apellidopaterno
            + " " + this.dto.apellidomaterno
            + " , " + this.dto.nombres
    }

    nombreCompletoKeyJD(){
        if(this.dto.tipopersona=='J'){
            this.dto.nombrecompleto = this.dto.busqueda
        }

    }

    documentoKey() {
        if (this.dto.tipodocumento == "R") {
            if (this.dto.documento == null) {
                this.dto.documento = ""
            }
            this.dto.documentofiscal = this.dto.documento
        }

        else if (this.dto.tipodocumento == "D") {
            if (this.dto.documento == null) {
                this.dto.documento = ""
            }
            this.dto.documentoidentidad = this.estaVacio(this.dto.documento) ? this.dto.documento : this.dto.documento.trim()

        }
    }

}
