import { MaMiscelaneosdetalleComunService } from './../../../core/servicio/mamiscelaneosdetalle-comun.service';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { SyDocumentoanexosMotorService } from './../servicio/sydocumentoanexosmotor.service';
import { DtoComunSyDocumentoanexos } from './../dominio/DtoComunSyDocumentoanexos';
import { DtoComunSyDocumentoCabecera } from './../dominio/DtoComunSyDocumentoCabecera';
import { TituloListadoComponent } from '@framework/angular/controles/titulos/titulo-listado.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { BaseComponentLista } from '@framework/angular/component/BaseComponentLista';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { ConstanteComunSistema } from '@framework-comun/sistema/ConstanteComunSistema';
import { DtoRequerimiento } from '@erp-logistica/requerimientos/dominio/dto/DtoRequerimiento';
import { DtoTablaTransaccion } from '@erp-logistica/requerimientos/dominio/dto/DtoTablaTransaccion';


@Component({
    selector: 'app-adjuntossimple',
    templateUrl: './adjuntossimple.component.html',
    styles: [
        `
        :host ::ng-deep .p-datatable-scrollable-body {
            padding-right: 17px;
          }
        `
    ]
})
export class AdjuntosSimpleComponent extends BaseComponentLista implements OnInit, UIMantenimientoController {

    persona: string = 'Seleccionar persona';
    proveedor: string = 'Seleccionar proveedor';
    cuenta: string = 'Seleccionar Cuenta';
    juicio: string = 'Seleccionar Juicio';
    proyecto: string = null;
    referencia: string = 'Seleccionar Referencia';
    @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;


    dtoCabecera: DtoComunSyDocumentoCabecera = new DtoComunSyDocumentoCabecera();
    dtoCabeceraTemp: DtoComunSyDocumentoCabecera = new DtoComunSyDocumentoCabecera();
    lstdto: DtoComunSyDocumentoanexos[] = [];
    dto: DtoComunSyDocumentoanexos = new DtoComunSyDocumentoanexos();
    seleccionado: DtoComunSyDocumentoanexos = new DtoComunSyDocumentoanexos();

    verSelectorMantenimiento: boolean = false;
    nuevo: boolean = false;

    listaPlantillaDto: DtoTablaTransaccion[] = [];
    documentosPermitidos: string[] = [];
    lstDocumentosSeleccionado: DtoComunSyDocumentoanexos[] = [];
    documentosSeleccionado: DtoComunSyDocumentoanexos = new DtoComunSyDocumentoanexos();
    maxFileSize: number;

    constructor(
        messageService: MessageService,
        servicioComun: ServicioComunService,
        private adjuntosComunService: SyDocumentoanexosMotorService,
        private confirmationService: ConfirmationService,
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        private router: Router,
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }
    lstextensiones: any[] = [];
    acceptextensions: string = '';
    ngOnInit() {
        const p2 = this.maMiscelaneosdetalleComunService.listarTablaPorHeaderActivos('SY', 'EXTADJPER', '999999').then(res => res.forEach(r => { this.documentosPermitidos.push(r.codigo.trim()) }));
        const p1 = this.miscelaneosListar(
            this.comboEtiquetaTipo.LISTADO,
            ConstanteComunSistema.APLICACION,
            ConstanteComunSistema.EXTENSIONES_DOS, // DOCUMENTOS
            ConstanteComunSistema.DEFECTO_COMPANIA,
            this.lstextensiones
        );


        var pFSize = this.parametroObtenerNumero('999999', 'HR', 'MXSIFOLIO').then(
            res => {
                if (res == null || res == undefined) {
                    this.maxFileSize = 1000000;
                }
                else {
                    this.maxFileSize = res;
                }
                return 1;
            }
        );

        Promise.all([p1, p2, pFSize]).then(res => {
            console.log("extensiones: ", this.lstextensiones);
            this.lstextensiones.forEach(element => {
                if (element.value != null) {
                    var d = new DtoTabla();
                    d.codigo = element.value;
                    this.acceptextensions += '.' + element.value + ', '
                    if (this.esListaVacia(this.dtoCabecera.listaExtensiones)) {
                        this.dtoCabecera.listaExtensiones = [];
                    }
                    this.dtoCabecera.listaExtensiones.push(d);
                }


            })
            console.log("text extens", this.acceptextensions)

        })
    }


    coreIniciarComponente(dto: DtoComunSyDocumentoCabecera, msj: MensajeController) {
        this.mensajeController = msj;
        this.dtoCabecera = new DtoComunSyDocumentoCabecera();
        this.dtoCabecera = dto;
        this.dtoCabeceraTemp = dto;

        if (this.esListaVacia(this.dtoCabecera.listaExtensiones)) {
            var d = new DtoTabla();
            d.codigo = 'PDF'
            this.dtoCabecera.listaExtensiones.push(d);
        }
        if (this.estaVacio(this.dtoCabecera.flgVer)) {
            this.dtoCabecera.flgVer = 'N';
        }

        this.llenarlista();

        this.buscar();
    }

    coreIniciarComponenteConPlantillaDel(dto: DtoComunSyDocumentoCabecera, listaPlantilla: DtoTablaTransaccion[]) {

        //const p1 = this.eliminartemporalMasivo(this.lstdto);
        //Promise.all([p1]).then(
        //f => {
        this.coreIniciarComponenteConPlantillaPdf(dto, listaPlantilla);
        //  });

        this.llenarlista();

    }

    llenarlista() {
        if (this.esListaVacia(this.dtoCabecera.listaExtensiones)) {
            var a = new DtoTabla(); a.codigo = 'DOC'
            var b = new DtoTabla(); b.codigo = 'DOCX'
            var c = new DtoTabla(); c.codigo = 'JPEG'
            var d = new DtoTabla(); d.codigo = 'JPG'
            var e = new DtoTabla(); e.codigo = 'PNG'
            var f = new DtoTabla(); f.codigo = 'PDF'
            var g = new DtoTabla(); g.codigo = 'PPT'
            var h = new DtoTabla(); h.codigo = 'XLS'
            var i = new DtoTabla(); i.codigo = 'XLSX'

            this.dtoCabecera.listaExtensiones.push(a);
            this.dtoCabecera.listaExtensiones.push(b);
            this.dtoCabecera.listaExtensiones.push(c);
            this.dtoCabecera.listaExtensiones.push(d);
            this.dtoCabecera.listaExtensiones.push(e);
            this.dtoCabecera.listaExtensiones.push(f);
            this.dtoCabecera.listaExtensiones.push(g);
            this.dtoCabecera.listaExtensiones.push(h);
            this.dtoCabecera.listaExtensiones.push(i);
        }

    }

    coreIniciarComponenteConPlantilla(dto: DtoComunSyDocumentoCabecera, listaPlantilla: DtoTablaTransaccion[]) {
        this.dtoCabecera = new DtoComunSyDocumentoCabecera();
        this.dtoCabecera = dto;
        if (this.estaVacio(this.dtoCabecera.flgVer)) {
            this.dtoCabecera.flgVer = 'N';
        }
        this.bloquearPagina();
        this.adjuntosComunService.listarporcabecera(this.dtoCabecera).then(resp => {
            this.desbloquearPagina();
            this.lstdto = resp;

            this.listaPlantillaDto = listaPlantilla;

            listaPlantilla.forEach(element => {
                var encontrado = this.lstdto.find(x => x.procesoTipoDocumentoId === element.tipoproceso);
                if (encontrado === undefined) {
                    var b = new DtoComunSyDocumentoanexos();
                    b.descripcion = element.descripcionlocal;
                    b.fecha = new Date();
                    b.auxFlgNuevo = 'S';
                    b.secuencia = element.secuencia;
                    b.procesoTipoDocumentoId = element.tipoproceso;
                    b.auxFlgAdjuntoObligatorio = element.obligatorioflag;
                    b.validarporrango = element.validarporrango;
                    b.montorango1inicio = element.montorango1inicio;
                    b.montorango1fin = element.montorango1fin;
                    // b.auxRutaPlantilla = element.descripcion;
                    this.lstdto.push(b);
                }
            });
        });

        this.llenarlista();
    }

    coreGuardar(): void {
        if (this.estaVacio(this.dto.archivo)) {
            this.mostrarMensajeAdvertencia('Debe ingresar un archivo.');
            return;
        }

        if (this.estaVacio(this.dto.descripcion)) {
            this.mostrarMensajeAdvertencia('Debe ingresar el titulo.');
            return;
        }

        this.bloquearPagina();
        this.adjuntosComunService.validarAdjunto(this.dto).then(val => {
            this.desbloquearPagina();
            if (this.transaccionResultado(val)) {

                if (val.auxConfirmar == 'S') {

                    this.confirmationService.confirm({
                        header: 'Confirmación',
                        icon: 'fa fa-question-circle',
                        message: val.auxConfirmarMensaje,
                        key: "adjuntos",
                        accept: () => {
                            this.guardarFinal();
                        },
                        reject: () => {
                            var fecha = new Date()
                            var dia = fecha.getDate();
                            var mes = fecha.getMonth();
                            var anio = fecha.getFullYear();
                            var hora = fecha.getHours();
                            var min = fecha.getMinutes();
                            var sec = fecha.getSeconds();
                            this.dto.archivo = '(' + dia + mes + anio + hora + min + sec + ')' + this.dto.archivo;
                            this.guardarFinal();
                        }
                    });

                } else {
                    this.guardarFinal();
                }

            }
        });

    }

    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
    }
    coreAccion(accion: string): void {
    }

    coreMensaje(mensage: MensajeController) {
    }

    ejemplo() {
        // this.router.navigate([ConstanteComun.ruta_logistica_mantenimiento], { skipLocationChange: true })
    }

    buscar() {
        this.bloquearPagina();
        this.adjuntosComunService.listarporcabecera(this.dtoCabecera).then(resp => {
            this.desbloquearPagina();
            this.lstdto = resp;
            console.log(this.lstdto)
        });
    }


    elminiarAdjunto(row: DtoComunSyDocumentoanexos) {
        row.auxFlgEliminar = 'S';

        this.bloquearPagina();
        this.adjuntosComunService.eliminartemporal(row).then(resp => {
            this.desbloquearPagina();
            row = resp;
        });
        this.verSelectorMantenimiento = false;
    }

    eliminartemporalMasivo(rows: DtoComunSyDocumentoanexos[]): Promise<number> {
        rows.forEach(row => {
            row.auxFlgEliminar = 'S';
            row.archivostring = '';
        });

        this.bloquearPagina();
        return this.adjuntosComunService.eliminartemporalMasivo(rows).then(resp => {
            this.desbloquearPagina();
            rows = resp;
            this.lstdto = resp;
            return 1;
        });
    }

    agregar() {
        this.verSelectorMantenimiento = true;
        this.nuevo = true;
        this.dto = new DtoComunSyDocumentoanexos();
        this.dto.fecha = new Date();
        this.dto.auxflgTituloEditable = 'S';
        this.dto.auxFlgAdjuntoObligatorio = 'N';
    }

    editarAd(row: DtoComunSyDocumentoanexos) {
        this.seleccionado = convertDateStringsToDates(row);
        this.dto = this.cloneAd(row);
        this.nuevo = false;
        this.verSelectorMantenimiento = true;
    }

    cloneAd(c: DtoComunSyDocumentoanexos): DtoComunSyDocumentoanexos {
        let bean = new DtoComunSyDocumentoanexos();
        for (let prop in c) {
            bean[prop] = c[prop];
        }
        return bean;
    }

    subiendoDto: DtoComunSyDocumentoanexos = null;
    resultado: string;

    subirArchivo(dto: DtoComunSyDocumentoanexos, fs: any) {
        fs.click();
        this.subiendoDto = dto;
    }

    cargarArchivo(event: any) {
        console.log('ADJUNTO')
        this.bloquearPagina();
        const files = event.files;
        if (files.length !== 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > this.maxFileSize) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + Math.trunc((this.maxFileSize / 1000000)) + 'mb');
            this.desbloquearPagina();
            return null;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var archivorepetido: boolean = false;

        this.lstdto.forEach(row => {
            if (row.auxFlgEliminar != 'S') {
                if (!this.estaVacio(row.archivo) && !this.estaVacio(files[0].name)) {
                    if (row.archivo.trim() == files[0].name) {
                        if (this.subiendoDto.archivo != row.archivo.trim()) {
                            this.mostrarMensajeAdvertencia('El archivo ya se encuentra en el listado.');
                            this.desbloquearPagina();
                            archivorepetido = true;
                        }

                        if (this.subiendoDto.archivo == row.archivo.trim()) {

                            this.confirmationService.confirm({
                                header: 'Confirmación',
                                icon: 'fa fa-question-circle',
                                message: 'Se reemplazará el archivo ' + this.trimNoNulo(this.subiendoDto.archivo) + '¿Desea continuar?',
                                key: "adjuntos",
                                accept: () => {
                                    //CONTINUA EL FLUJO
                                },
                                reject: () => {
                                    // this.mostrarMensajeAdvertencia('El archivo ya se encuentra en el listado.');
                                    this.desbloquearPagina();
                                    archivorepetido = true;
                                }
                            });


                        }


                    }
                }
            }
        });

        if (archivorepetido) {
            return null;
        }


        /*         var auxTipoSustento = this.extraerExtension(files[0].name);
                if (auxTipoSustento != null) {
                    var tipoSustento = auxTipoSustento.toUpperCase();
        
                    if (this.esListaVacia(this.dtoCabecera.listaExtensiones)) {
                       this.dtoCabecera.listaExtensiones = [];
                       var d = new DtoTabla();
                       d.codigo = 'PDF'
                       this.dtoCabecera.listaExtensiones.push(d);
                    }
        
                    var encontrado = this.dtoCabecera.listaExtensiones.find(x => x.codigo === tipoSustento);
                    if (encontrado === undefined) {
                        this.mostrarMensajeError('La extensión no está permitida.');
                        this.desbloquearPagina();
                        return;
                    }
                } */


        const reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            const result = reader.result as string;
            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }


            this.dto.archivo = files[0].name;
            this.dto.archivostring = result.split('base64,')[1];

            this.desbloquearPagina();
        };
    }

    bajarArchivo(archivoSeleccionado: DtoComunSyDocumentoanexos) {


        this.adjuntosComunService.descargar(
            archivoSeleccionado
        ).subscribe(
            res => {

                const link = window.URL.createObjectURL(res);
                const a = document.createElement('a');
                const type = res.type.split('/');
                document.body.appendChild(a);
                a.setAttribute('style', 'display: none');
                a.href = link;
                a.download = this.reemplazarRuta(archivoSeleccionado.archivo.trim(), ',', '_');
                a.click();
                window.URL.revokeObjectURL(link);
                a.remove();
            }, error => {
                throw error;
            }, () => {
            }
        );

    }

    salirAd() {
        this.verSelectorMantenimiento = false;
    }


    procesarAdjuntos(): Promise<DtoComunSyDocumentoCabecera> {
        this.dtoCabecera.listaDocumentos = this.lstdto;
        this.bloquearPagina();
        return this.adjuntosComunService.procesaradjuntos(this.dtoCabecera).then(resp => {
            this.desbloquearPagina();
            if (this.transaccionResultado(resp)) {
                this.dtoCabecera = resp;
                return this.dtoCabecera;
            } else {
                return null;
            }
        });
    }

    procesarAdjuntosEnviarId(id: string, accion: string, enviadoWorflow: string): Promise<DtoComunSyDocumentoCabecera> {
        this.dtoCabecera.numerodocumento = id;
        this.dtoCabecera.listaDocumentos = this.lstdto;
        this.dtoCabecera.linea = 0;
        this.dtoCabecera.auxAccion = accion;
        this.dtoCabecera.flgEnviadoWorflow = enviadoWorflow;
        this.bloquearPagina();
        return this.adjuntosComunService.procesaradjuntos(this.dtoCabecera).then(resp => {
            this.desbloquearPagina();
            this.dtoCabecera = resp;
            return this.dtoCabecera;
        });
    }


    validarAdjuntosEnviarId(dtoRequerimiento: DtoRequerimiento): Promise<DtoComunSyDocumentoCabecera> {


        this.dtoCabecera.numerodocumento = dtoRequerimiento.dw_1.requisicionnumero;
        this.dtoCabecera.listaDocumentos = this.lstdto;
        this.dtoCabecera.numerocontrato = dtoRequerimiento.dw_1.proveedordocumentoreferencia;

        this.dtoCabecera.monedadocumento = dtoRequerimiento.dw_1.monedacodigo;
        this.dtoCabecera.sistemacontratacion = dtoRequerimiento.dw_1.sistemacontratacion;
        this.dtoCabecera.preciototal = dtoRequerimiento.dw_1.preciototal;

        //Puesto por Jose 
        this.dtoCabecera.linea = 0;

        this.dtoCabecera.listaDocumentos.forEach(element => {
            if (element.auxFlgEliminar == 'S') {
                element.archivo = '';
            }
        });

        this.bloquearPagina();
        return this.adjuntosComunService.validarProcesaradjuntos(this.dtoCabecera).then(resp => {
            this.desbloquearPagina();
            this.dtoCabecera = resp;
            this.lstdto = resp.listaDocumentos;
            return this.dtoCabecera;
        });
    }


    descargarPlantilla(archivoSeleccionado: DtoComunSyDocumentoanexos) {
        this.adjuntosComunService.descargarplantilla(
            archivoSeleccionado
        ).subscribe(
            res => {
                const link = window.URL.createObjectURL(res);
                const a = document.createElement('a');
                const type = res.type.split('/');
                document.body.appendChild(a);
                a.setAttribute('style', 'display: none');
                a.href = link;
                a.download = 'plantilla';
                a.click();
                window.URL.revokeObjectURL(link);
                a.remove();
            }, error => {
                throw error;
            }, () => {
            }
        );
    }


    coreIniciarComponenteConPlantillaPdf(dto: DtoComunSyDocumentoCabecera, listaPlantilla: DtoTablaTransaccion[]) {
        this.dtoCabecera = new DtoComunSyDocumentoCabecera();
        this.dtoCabecera = dto;
        if (this.esListaVacia(this.dtoCabecera.listaExtensiones)) {
            var f = new DtoTabla(); f.codigo = 'PDF';
            this.dtoCabecera.listaExtensiones.push(f);
        }
        if (this.estaVacio(this.dtoCabecera.flgVer)) {
            this.dtoCabecera.flgVer = 'N';
        }

        this.llenarlista();

        if (dto.flgCambioModalidad == 'S') {
            listaPlantilla.forEach(element => {
                var b = new DtoComunSyDocumentoanexos();
                b.descripcion = element.descripcionlocal;
                b.secuencia = element.secuencia;
                b.fecha = new Date();
                b.auxFlgNuevo = 'S';
                b.procesoTipoDocumentoId = element.tipoproceso;
                b.auxFlgAdjuntoObligatorio = element.obligatorioflag;
                b.validarporrango = element.validarporrango;
                b.montorango1inicio = element.montorango1inicio;
                b.montorango1fin = element.montorango1fin;
                //b.auxRutaPlantilla = element.descripcion;

                if (!this.esListaVacia(this.lstdto)) {
                    var encontrado = 0;
                    this.lstdto.forEach(obj => {
                        if (this.trimNoNulo(obj.descripcion) == this.trimNoNulo(b.descripcion)) {
                            encontrado = 1;
                        }
                    })
                    if (encontrado != 1) {
                        this.lstdto.push(b);
                    }
                } else {
                    this.lstdto.push(b);
                }

            });
        } else {
            this.bloquearPagina();
            this.adjuntosComunService.listarporcabecera(this.dtoCabecera).then(resp => {
                this.desbloquearPagina();
                this.lstdto = resp;
                this.listaPlantillaDto = listaPlantilla;
                listaPlantilla.forEach(element => {
                    var encontrado = this.lstdto.find(x => x.procesoTipoDocumentoId === element.tipoproceso || this.trimNoNulo(x.descripcion) == this.trimNoNulo(element.descripcionlocal));
                    if (encontrado === undefined) {
                        var b = new DtoComunSyDocumentoanexos();
                        b.descripcion = element.descripcionlocal;
                        b.secuencia = element.secuencia;
                        b.fecha = new Date();
                        b.auxFlgNuevo = 'S';
                        b.procesoTipoDocumentoId = element.tipoproceso;
                        b.auxFlgAdjuntoObligatorio = element.obligatorioflag;
                        b.validarporrango = element.validarporrango;
                        b.montorango1inicio = element.montorango1inicio;
                        b.montorango1fin = element.montorango1fin;
                        // b.auxRutaPlantilla = element.descripcion;
                        this.lstdto.push(b);
                    }
                });
            });
        }
    }


    guardarFinal(): void {

        let lst = [...this.lstdto];
        if (this.nuevo) {
            this.dto.auxFlgNuevo = 'S';
            this.bloquearPagina();
            this.adjuntosComunService.registrartemporal(this.dto).then(resp => {
                this.desbloquearPagina();
                this.dto = resp;
                lst.push(this.dto);
                this.lstdto = lst;
                this.dto = new DtoComunSyDocumentoanexos();
                this.verSelectorMantenimiento = false;
                if (this.dtoCabecera.procesarautomatico == 'S') {
                    this.procesarAutomatico();
                }
            });
        }
        else {
            this.dto.auxFlgEditar = 'S';
            this.bloquearPagina();
            this.adjuntosComunService.actualizarTemporal(this.dto).then(resp => {
                this.desbloquearPagina();
                this.dto = resp;
                lst[this.lstdto.indexOf(this.seleccionado)] = this.dto;
                this.lstdto = lst;
                this.dto = new DtoComunSyDocumentoanexos();
                this.verSelectorMantenimiento = false;
                if (this.dtoCabecera.procesarautomatico == 'S') {
                    this.procesarAutomatico();
                }
            });

        }

    }


    devolverSeleccionados() {
        return this.lstDocumentosSeleccionado;
    }


    procesarAutomatico() {
        this.procesarAdjuntos().then(r => {
            this.desbloquearPagina();
            console.log('RES')
            if (this.transaccionResultado(r)) {
                this.dtoCabecera = this.dtoCabeceraTemp;
                this.buscar();
            }
        });
    }


}

