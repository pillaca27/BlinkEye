import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ConstantePostulante } from "../ConstantePostulante";
import { accionSolicitada } from '@framework/angular/component/BaseComponent';
import { DtoHrPostulante } from "../dominio/DtoHrPostulante";
import { HrPostulanteService } from "../servicio/hrpostulante.service";
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { PostulanteUbicacionGeograficaSelectorComponent } from './postulante-ubicaciongeografica-selector.component';
import { SeguridadUsuarioLogin } from '@framework/modelo/seguridad/SeguridadUsuarioLogin';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';

@Component({
    templateUrl: './postulante-datospersonales.component.html',
    selector: 'postulante-datospersonales'
})
export class PostulanteDatosPersonalesComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    fotoMaxSize: number = 0;
    fotoMaxSizeMb: number = 0;

    constructor(
        private loginService: LoginService,
        private hrPostulanteService: HrPostulanteService,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); this.bean.auxFotoString = this.fotoTemp; }

    componentePadre: any;

    coreGuardar(): void {

        if (!this.valido()) {
            return;
        }
        this.bean.nombrecompleto = this.bean.apellidopaterno + ' ' + this.bean.apellidomaterno + ', ' + this.bean.nombres;
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            if (this.moduloOrigen == ConstantePostulante.ORIGEN_MODULO_GTH) {
                this.hrPostulanteService.registrarDatosPersonales(this.bean).then(res => {
                    if (this.transaccionResultadoSimple(res)) {
                        this.mostrarMensajeExito(this.getMensajeGrabadoSinCodigo());
                        this.componentePadre.cambiarModoEdicion(res.postulante);
                    }
                    else {
                        this.desbloquearPagina();
                    }
                });
            }
            else {
                this.hrPostulanteService.registrarDatosPersonalesPublico(this.bean).then(res => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoSimple(res)) {
                        this.mostrarMensajeExito(this.getMensajeGrabadoSinCodigo());
                        this.coreSalir();
                    }
                });
            }
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.hrPostulanteService.actualizarDatosPersonales(this.bean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
                    //this.coreSalir();
                }
            });
        }
    }
    valido(): boolean {
        var valida = true;

        var ahora = new Date();
        ahora.setHours(0, 0, 0);
        var anio100 = ahora.getFullYear() - 100;

        this.messageService.clear();

        if (this.estaVacio(this.bean.tipodocumento)) {
            this.mostrarMensajeAdvertenciaNoClear("El Tipo documento es requerido");
            valida = false;
        }
        if (this.estaVacio(this.bean.documento)) {
            this.mostrarMensajeAdvertenciaNoClear("El Número de documento es requerido");
            valida = false;
        }
        if (!this.estaVacio(this.bean.tipodocumento) && !this.estaVacio(this.bean.documento)) {
            if (this.bean.tipodocumento === 'D' || this.bean.tipodocumento === 'E') {
                if (this.bean.documento.length !== 8) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe tener 8 caracteres');
                    valida = false;
                }
                const dniNumerico = Number(this.bean.documento);
                if (isNaN(dniNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valida = false;
                }
            }
            if (this.bean.tipodocumento === 'P') {
                if (this.bean.documento.length > 12) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 12 caracteres');
                    valida = false;
                }
                const pasptNumerico = Number(this.bean.documento);
                if (isNaN(pasptNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valida = false;
                }
            }
            if (this.bean.tipodocumento === 'N') {
                if (this.bean.documento.length > 15) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 15 caracteres');
                    valida = false;
                }
            }
            if (this.bean.tipodocumento === 'X') {
                if (this.bean.documento.length > 12) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento solo permite 12 caracteres');
                    valida = false;
                }
            }
            if (this.bean.tipodocumento === 'R') {
                if (this.bean.documento.length !== 11) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe tener 11 caracteres');
                    valida = false;
                }
                const rucNumerico = Number(this.bean.documento);
                if (isNaN(rucNumerico)) {
                    this.mostrarMensajeAdvertenciaNoClear('El documento debe ingresar solo números');
                    valida = false;
                }
            }
        }
        if (!this.estaVacio(this.bean.documentofiscal)) {
            if (this.bean.documentofiscal.length !== 11) {
                this.mostrarMensajeAdvertenciaNoClear('El documento fiscal debe tener 11 caracteres');
                valida = false;
            }
            const rucNumerico = Number(this.bean.documentofiscal);
            if (isNaN(rucNumerico)) {
                this.mostrarMensajeAdvertenciaNoClear('El documento fiscal debe ingresar solo números');
                valida = false;
            }
        }
        if (this.estaVacio(this.bean.apellidopaterno)) {
            this.mostrarMensajeAdvertenciaNoClear("El Apellido paterno es requerido");
            valida = false;
        }
        if (this.estaVacio(this.bean.apellidomaterno)) {
            this.mostrarMensajeAdvertenciaNoClear("El Apellido materno es requerido");
            valida = false;
        }
        if (this.estaVacio(this.bean.nombres)) {
            this.mostrarMensajeAdvertenciaNoClear("Los Nombres son requeridos");
            valida = false;
        }
        if (this.estaVacio(this.bean.correoelectronico)) {
            this.mostrarMensajeAdvertenciaNoClear("El Correo es requerido");
            valida = false;
        }
        else {
            //validar formato
            const correoelectronico = this.esCorreoValido(this.bean.correoelectronico);
            if (!correoelectronico) {
                this.mostrarMensajeAdvertenciaNoClear('El Correo Electrónico ingresado no es correcto');
                valida = false;
            }
            else {
                if ((this.bean.correoelectronico.match(new RegExp("@", "g")) || []).length > 1) {
                    this.mostrarMensajeAdvertenciaNoClear('Sólo debe ingresar un E-mail.');
                    valida = false;
                }
            }
        }
        if (this.bean.fechanacimiento == null) {
            this.mostrarMensajeAdvertenciaNoClear("La Fecha de nacimiento es requerida");
            valida = false;
        }
        else {
            //no puede ser a la fecha actual
            var nac = new Date(this.bean.fechanacimiento);
            nac.setHours(0, 0, 0);
            if (nac >= ahora) {
                this.mostrarMensajeAdvertenciaNoClear("La Fecha de nacimiento no debe ser mayor a la fecha actual");
                valida = false;
            }
            //no puede ser mayor a 100 anios atras
            if (nac.getFullYear() <= anio100) {
                this.mostrarMensajeAdvertenciaNoClear("Fecha de nacimiento no válida");
                valida = false;
            }
        }
        if (this.estaVacio(this.bean.pais)) {
            this.mostrarMensajeAdvertenciaNoClear("El País del domicilio local es requerido");
            valida = false;
        }
        if (this.estaVacio(this.bean.direccion)) {
            this.mostrarMensajeAdvertenciaNoClear("La Dirección del domicilio local es requerido");
            valida = false;
        }
        if (this.estaVacio(this.bean.auxUbigeoActual)) {
            this.mostrarMensajeAdvertenciaNoClear("El Dpto-Prov-Dist del domicilio local es requerido");
            valida = false;
        }
        if (!this.estaVacio(this.bean.codigoldn)) {
            if (this.estaVacio(this.bean.telefono)) {
                this.mostrarMensajeAdvertenciaNoClear("El Teléfono es requerido");
                valida = false;
            }
        }
        if (!this.estaVacio(this.bean.telefono)) {
            if (this.estaVacio(this.bean.codigoldn)) {
                this.mostrarMensajeAdvertenciaNoClear("El Código de Larga Distancia Nacional(LDN) es requerido");
                valida = false;
            }
        }
        if (this.bean.fechaestadocivil != null) {
            //no puede ser a la fecha actual
            var est = new Date(this.bean.fechaestadocivil);
            est.setHours(0, 0, 0);
            if (est >= ahora) {
                this.mostrarMensajeAdvertenciaNoClear("La Fecha Estado Civil no debe ser mayor a la fecha actual");
                valida = false;
            }
            //no puede ser mayor a 100 anios atras
            if (est.getFullYear() <= anio100) {
                this.mostrarMensajeAdvertenciaNoClear("Fecha Estado Civil no válida");
                valida = false;
            }
        }
        if (this.bean.licenciaconducirfecha != null) {
            //no puede ser a la fecha actual
            var lic = new Date(this.bean.licenciaconducirfecha);
            lic.setHours(0, 0, 0);
            //no puede ser mayor a 100 anios atras
            if (lic.getFullYear() <= anio100) {
                this.mostrarMensajeAdvertenciaNoClear("Fecha de Expedición no válida");
                valida = false;
            }
        }
        if (this.estaVacio(this.bean.clave)) {
            this.mostrarMensajeAdvertenciaNoClear("La Clave es requerida");
            valida = false;
        }
        else if (this.bean.clave != this.bean.claveConfirmacion) {
            this.mostrarMensajeAdvertenciaNoClear("Las Claves no coinciden");
            valida = false;
        }
        return valida;
    }
    coreSalir(): void {
        if (this.moduloOrigen == ConstantePostulante.ORIGEN_MODULO_GTH) {
            this.router.navigate([this.rutaPantallaSalir]);
        }
        else if (this.moduloOrigen == ConstantePostulante.ORIGEN_MODULO_POSTULANTE_NUEVO) {
            this.bloquearPagina();
            this.mostrarMensajeInfo('Ingresando al Sistema');
            sessionStorage.setItem('registrar-postulante', '')
            //hacer login
            var usuario: SeguridadUsuarioLogin = new SeguridadUsuarioLogin();
            usuario.tipoUsuarioId = "POST";
            usuario.aplicacionCodigo = 'PE';
            usuario.companiaCodigo = this.bean.tipodocumento;
            usuario.usuario = this.bean.documento;
            usuario.clave = this.bean.clave;
            this.loginService.ingresar(usuario).then(
                obj => {
                    usuario.token = obj.token;
                    if (obj != null) {
                        //guardar token
                        sessionStorage.setItem('access_token', obj.token);
                        sessionStorage.setItem('login', 'postulante-login');
                        //ir dashboard
                        this.router.navigate(['spring/postulante/datos-personales']);
                        //Refrescar
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    } else {
                        this.desbloquearPagina();
                    }
                }
            );
        }
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'UBIGEO-NACIMIENTO') {
            this.bean.auxUbigeoNacimiento = mensage.resultado.descripcion;
            this.bean.departamentonacimiento = mensage.resultado.departamento;
            this.bean.provincianacimiento = mensage.resultado.provincia;
            this.bean.distritonacimiento = mensage.resultado.zonapostal;
        }
        else if (mensage.componente == 'UBIGEO-ACTUAL') {
            this.bean.auxUbigeoActual = mensage.resultado.descripcion;
            this.bean.departamento = mensage.resultado.departamento;
            this.bean.provincia = mensage.resultado.provincia;
            this.bean.distrito = mensage.resultado.zonapostal;
        }
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }

    moduloOrigen: string;
    rutaPantallaSalir: string;
    bean: DtoHrPostulante = new DtoHrPostulante();

    lstTipoDocumento: SelectItem[] = [];
    lstCodigoLDN: SelectItem[] = [];
    lstGrupoSanguineo: SelectItem[] = [];
    lstEstadoCivil: SelectItem[] = [];
    lstTipoVivienda: SelectItem[] = [];
    lstParentesco: SelectItem[] = [];
    lstCategoriaLicencia: SelectItem[] = [];
    lstTipoVisa: SelectItem[] = [];
    lstPais: SelectItem[] = [];
    lstNacionalidad: SelectItem[] = [];

    @ViewChild(PostulanteUbicacionGeograficaSelectorComponent, { static: false }) postulanteUbicacionGeograficaSelectorComponent: PostulanteUbicacionGeograficaSelectorComponent;

    objTemp: any = {
        c1: '',
        c2: '',
        c3: '',
        c4: '',
        c5: '',
        c6: '',
        c7: '',
        c8: ''
    };

    //Temporal
    fotoTemp: string = 'assets/imagenes/usrDefault.jpg';

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.route.data.subscribe(x => this.moduloOrigen = x.moduloOrigen);
        if (ConstantePostulante.ORIGEN_MODULO_POSTULANTE == this.moduloOrigen) {
            this.accion = this.ACCIONES.EDITAR;
            this.bean.postulante = 0;
            //Obtener data por usuario actual, caso desde modulo de postulante
            this.bloquearPagina();
            Promise.all(this.cargarCombos()).then(res => {
                Promise.all([this.refrescarVista()]).then(res => {
                    this.desbloquearPagina();
                });
            });
        }
        else if (ConstantePostulante.ORIGEN_MODULO_POSTULANTE_NUEVO == this.moduloOrigen) {
            this.accion = this.ACCIONES.NUEVO;
            this.bean = new DtoHrPostulante();
            this.bean.auxFotoString = this.fotoTemp;
            this.bloquearPagina();
            Promise.all(this.cargarCombos()).then(res => {
                this.desbloquearPagina();
            });
        }
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
    }

    cargarCombos(): Promise<number>[] {
        this.lstTipoDocumento.push({ value: null, label: " -- Seleccione --" });
        this.lstCodigoLDN.push({ value: null, label: " -- Seleccione --" });
        this.lstGrupoSanguineo.push({ value: null, label: " -- Seleccione --" });
        this.lstEstadoCivil.push({ value: null, label: " -- Seleccione --" });
        this.lstTipoVivienda.push({ value: null, label: " -- Seleccione --" });
        this.lstParentesco.push({ value: null, label: " -- Seleccione --" });
        this.lstCategoriaLicencia.push({ value: null, label: " -- Seleccione --" });
        this.lstTipoVisa.push({ value: null, label: " -- Seleccione --" });
        this.lstPais.push({ value: null, label: " -- Seleccione --" });
        this.lstNacionalidad.push({ value: null, label: " -- Seleccione --" });
        var p = [];
        p.push(this.hrPostulanteService.lstTipoDocumento().then(res => {
            res.forEach(r => {
                this.lstTipoDocumento.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstCodigoLDN().then(res => {
            res.forEach(r => {
                this.lstCodigoLDN.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstGrupoSanguineo().then(res => {
            res.forEach(r => {
                this.lstGrupoSanguineo.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstEstadoCivil().then(res => {
            res.forEach(r => {
                this.lstEstadoCivil.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstTipoVivienda().then(res => {
            res.forEach(r => {
                this.lstTipoVivienda.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstParentesco().then(res => {
            res.forEach(r => {
                this.lstParentesco.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstTipoVisa().then(res => {
            res.forEach(r => {
                this.lstTipoVisa.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstCategoriaLicencia().then(res => {
            res.forEach(r => {
                this.lstCategoriaLicencia.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstPaisNacionalidad().then(res => {
            res.forEach(r => {
                this.lstPais.push({ value: r.codigo, label: r.descripcion });
            });
        }));
        p.push(this.hrPostulanteService.lstPaisNacionalidad().then(res => {
            res.forEach(r => {
                this.lstNacionalidad.push({ value: r.codigo, label: r.nombre });
            });
        }));

        //TAMANIO MAXIMO DE FOTO
        p.push(this.hrPostulanteService.informacionAdicionalPostulante().then(res => {
            this.fotoMaxSize = res.maxFileSize;
            this.fotoMaxSizeMb = Math.round(this.fotoMaxSize / 1000000);
            return 1;
        }));

        return p;
    }

    iniciarComponenteNuevo(rutaPantallaSalir: string, padre) {
        this.componentePadre = padre;
        this.rutaPantallaSalir = rutaPantallaSalir;
        this.accion = this.ACCIONES.NUEVO;
        this.moduloOrigen = ConstantePostulante.ORIGEN_MODULO_GTH;
        this.bean = new DtoHrPostulante();
        this.bean.auxFotoString = this.fotoTemp;
        //Obtener data por parametro, caso desde modulo GTH
        return Promise.all(this.cargarCombos()).then(res => {
            this.desbloquearPagina();
            return 1;
        });
    }

    iniciarComponente(postulanteId: number, accion: accionSolicitada, rutaPantallaSalir: string): Promise<number> {
        this.rutaPantallaSalir = rutaPantallaSalir;
        this.accion = accion;
        this.moduloOrigen = ConstantePostulante.ORIGEN_MODULO_GTH;
        this.bean = new DtoHrPostulante();
        this.bean.postulante = postulanteId;
        //Obtener data por parametro, caso desde modulo GTH
        return Promise.all(this.cargarCombos()).then(res => {
            return Promise.all([this.refrescarVista()]).then(res => {
                this.desbloquearPagina();
                return 1;
            });
        });
    }

    refrescarVista(): Promise<number> {
        return this.hrPostulanteService.datosPersonales(this.bean).then(res => {
            res = this.limpiarTrims(res);
            if (res.auxFotoString == null) {
                res.auxFotoString = this.fotoTemp;
            }
            this.bean = res;
            return 1;
        });
    }

    limpiarTrims(res: DtoHrPostulante) {
        res.apellidopaterno = this.trim(res.apellidopaterno);
        res.apellidomaterno = this.trim(res.apellidomaterno);
        res.nombres = this.trim(res.nombres);
        res.nombrecompleto = this.trim(res.nombrecompleto);
        res.apellidocasada = this.trim(res.apellidocasada);
        res.sexo = this.trim(res.sexo);
        res.correoelectronico = this.trim(res.correoelectronico);
        res.paisnacimiento = this.trim(res.paisnacimiento);
        res.nacionalidad = this.trim(res.nacionalidad);
        res.departamentonacimiento = this.trim(res.departamentonacimiento);
        res.provincianacimiento = this.trim(res.provincianacimiento);
        res.distritonacimiento = this.trim(res.distritonacimiento);
        res.pais = this.trim(res.pais);
        res.direccion = this.trim(res.direccion);
        res.departamento = this.trim(res.departamento);
        res.provincia = this.trim(res.provincia);
        res.distrito = this.trim(res.distrito);
        res.direccionreferencia = this.trim(res.direccionreferencia);
        res.codigoldn = this.trim(res.codigoldn);
        res.telefono = this.trim(res.telefono);
        res.celular = this.trim(res.celular);
        res.fax = this.trim(res.fax);
        res.direccionextranjera = this.trim(res.direccionextranjera);
        res.paisextranjero = this.trim(res.paisextranjero);
        res.telefonoextranjero = this.trim(res.telefonoextranjero);
        res.celularextranjero = this.trim(res.celularextranjero);
        res.tipodocumento = this.trim(res.tipodocumento);
        res.documento = this.trim(res.documento);
        res.documentofiscal = this.trim(res.documentofiscal);
        res.impedimentos = this.trim(res.impedimentos);
        res.gruposanguineo = this.trim(res.gruposanguineo);
        res.flagdonante = this.trim(res.flagdonante);
        res.flagdiscapacidad = this.trim(res.flagdiscapacidad);
        res.codigodiscapacidad = this.trim(res.codigodiscapacidad);
        res.estadocivil = this.trim(res.estadocivil);
        res.situaciondomicilio = this.trim(res.situaciondomicilio);
        res.flagtrabajofuera = this.trim(res.flagtrabajofuera);
        res.observaciones = this.trim(res.observaciones);
        res.nombreemergencia = this.trim(res.nombreemergencia);
        res.direccionemergencia = this.trim(res.direccionemergencia);
        res.telefonoemergencia = this.trim(res.telefonoemergencia);
        res.celularemergencia = this.trim(res.celularemergencia);
        res.parentescoemergencia = this.trim(res.parentescoemergencia);
        res.nombreemergencia1 = this.trim(res.nombreemergencia1);
        res.direccionemergencia1 = this.trim(res.direccionemergencia1);
        res.telefonoemergencia1 = this.trim(res.telefonoemergencia1);
        res.celularemergencia1 = this.trim(res.celularemergencia1);
        res.parentescoemergencia1 = this.trim(res.parentescoemergencia1);
        res.tipobrevete = this.trim(res.tipobrevete);
        res.brevete = this.trim(res.brevete);
        res.pasaporte = this.trim(res.pasaporte);
        res.carnetextranjeria = this.trim(res.carnetextranjeria);
        res.tipovisa = this.trim(res.tipovisa);
        res.requerimiento = this.trim(res.requerimiento);
        res.companyowner = this.trim(res.companyowner);
        res.estado = this.trim(res.estado);
        res.ultimousuario = this.trim(res.ultimousuario);
        res.clave = this.trim(res.clave);
        res.claveConfirmacion = this.trim(res.claveConfirmacion);
        return res;
    }

    nuevo() {
    }

    editar(dto: any) {
    }

    ver(dto: any) {
    }

    eliminar(dto: any): void {

    }

    buscarUbigeoNacimiento() {
        if (this.estaVacio(this.bean.paisnacimiento)) {
            this.mostrarMensajeAdvertencia('Debe especificar el País de Nacimiento');
            return;
        }
        this.postulanteUbicacionGeograficaSelectorComponent.iniciarComponente(new MensajeController(this, 'UBIGEO-NACIMIENTO', ''), this.bean.paisnacimiento)
    }
    buscarUbigeoActual() {
        if (this.estaVacio(this.bean.pais)) {
            this.mostrarMensajeAdvertencia('Debe especificar el País');
            return;
        }
        this.postulanteUbicacionGeograficaSelectorComponent.iniciarComponente(new MensajeController(this, 'UBIGEO-ACTUAL', ''), this.bean.pais)
    }

    cargarFoto(event: any) {
        this.bloquearPagina();
        var files = event.files;
        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > this.fotoMaxSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + this.fotoMaxSizeMb + 'mb');
            this.desbloquearPagina();
            return null;
        }
        var filePath = files[0].name;
        var allowedExtensions = /(.jpg|.jpeg|.png|.gif)$/i;
        if (!allowedExtensions.exec(filePath)) {
            this.mostrarMensajeAdvertencia('Solo puede subir imágenes con estas extensiones .jpeg/.jpg/.png/.gif');
            files[0].value = '';
            this.desbloquearPagina();
            return;
        }
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result as string;
            this.bean.auxFotoString = result;
            this.desbloquearPagina();
        };
    }

}
