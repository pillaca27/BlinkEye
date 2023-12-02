import { WfTransaccionDocumentosXUsuarioComponent } from './transaccion-documentosxusuario.component';
import { WorkFlowTransaccion } from './../dominio/dto/WorkFlowTransaccion';
import { WfTransaccionPlanificacionComponent } from './transaccion-planificacion.component';
import { WfTransaccionBotonesComponent } from './transaccion-botones.component';
import { WfTransaccionSeguimientoComponent } from './transaccion-seguimiento.component';
import { WfTransaccionPk } from './../dominio/dto/WfTransaccion';
import { FormularioComponent } from './../../../framework/angular/component/FormularioComponent';
import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { Component, OnInit, ViewChild, Input, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { WfTransaccionServicio } from '../servicio/wftransaccion.service';
import { DtoFlujoAdjunto, DtoFlujoTransaccionRequest } from '../dominio/dto/DtoFlujoTransaccionRequest';
import { DtoFlujoTransaccionResponse } from '../dominio/dto/DtoFlujoTransaccionResponse';
import { WfProcesoServicio } from '@framework-workflow/servicio/wfproceso.service';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DtoWfTransaccionAdjunto } from '../dominio/dto/DtoWfTransaccionAdjunto';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { WorkFlowAprobadorTransaccion } from '../dominio/dto/WorkFlowAprobadorTransaccion';
import { DtoAprobacionAccion } from '../dominio/dto/DtoAprobacionAcciones';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
//import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';

@Component({
    templateUrl: './transaccion-mantenimiento.component.html'
})
export class WfTransaccionMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;
    @ViewChild(WfTransaccionPlanificacionComponent, { static: false }) wfTransaccionPlanificacionComponent: WfTransaccionPlanificacionComponent;
    @ViewChild(WfTransaccionDocumentosXUsuarioComponent, { static: false }) wfTransaccionDocumentosXUsuarioComponent: WfTransaccionDocumentosXUsuarioComponent;

    bean: DtoFlujoTransaccionRequest = new DtoFlujoTransaccionRequest();

    puedeEditar: Boolean = false;

    lstProcesos: SelectItem[] = [];
    lstTipoDocumento: SelectItem[] = [];
    lstEsHeader: SelectItem[] = [];
    resumen: DtoWfTransaccionAdjunto = new DtoWfTransaccionAdjunto(this.domSanitizer);
    documentosPermitidos: string[] = [];

    constructor(
        //private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private domSanitizer: DomSanitizer,
        private procesosService: WfProcesoServicio,
        private router: Router,
        private route: ActivatedRoute,
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    lstHTML: DtoWfTransaccionAdjunto[] = [];

    ngOnInit() {
        super.ngOnInit();

        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
       
        this.servicioComun.listaractivos('999999', 'SY', 'EXTADJPER')
        .then(respuesta => {
          respuesta.forEach(obj => this.documentosPermitidos.push(obj.codigo.trim() )); return 1;
        });
       
        this.lstEsHeader.push({ label: "Si", value: 'S' });
        this.lstEsHeader.push({ label: "No", value: 'N' });

        this.lstProcesos.push({ label: " -- Seleccione -- ", value: null });
        const accionExterna = this.route.snapshot.params['accion'] as number;
        this.accion = accionExterna;

        this.bloquearPagina();

        var p1 = this.procesosService.listarProcesosSinVersion(new DtoTabla()).then(
            x => {
                x.forEach(row => {
                    this.lstProcesos.push({ label: row.descripcion, value: row.proceso });
                });
            }
        )

        var p2 = this.wfTransaccionServicio.sytipodocumento().then(
            docs => {
                docs = docs.sort(this.compare);
                this.lstTipoDocumento.push({ value: null, label: ' -- Seleccione --' });
                docs.forEach(y => {
                    this.lstTipoDocumento.push({ value: y.codigo.trim(), label: y.descripcion });
                });
            }
        );

        Promise.all([p1, p2]).then(
            res => {
                this.iniciarFormulario();
            }
        );
    }

    compare(a: DtoTabla, b: DtoTabla) {
        if (a.descripcion < b.descripcion) {
            return -1;
        }
        if (a.descripcion > b.descripcion) {
            return 1;
        }
        return 0;
    }

    iniciarFormulario() {
        var UUID = (this.route.snapshot.params['pk'] as string);

        if (this.ACCIONES.NUEVO == this.accion) {
            this.bean.companiaid = '01000000';
            this.puedeEditar = true;
            this.desbloquearPagina();
            //test
            //var request = new WorkFlowTransaccion();
            //request.procesoId = "T1";
            //request.companiasocioId = "01000000";
            //this.wfTransaccionPlanificacionComponent.proyectarPlanificacion(request);
        }
        else {
            var promises = [];

            var p1 = this.wfTransaccionSeguimientoComponent.iniciarComponente(UUID);
            promises.push(p1);

            this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

            var p3 = this.wfTransaccionServicio.obtenerPorId(UUID).then(
                res => {
                    if (res != null) {
                        this.bean = new DtoFlujoTransaccionRequest();
                        this.bean.proceso = res.procesoid;
                        this.bean.referencia = res.referencia;
                        this.bean.criterios = res.criterios;
                        this.bean.origenTransaccion = res.transaccionOrigenId;
                        this.bean.companiaid = res.companiaSocioId;
                        this.bean.centrocostosid = res.centroCostosId;
                        this.bean.sucursalid = res.sucursalId;
                        this.bean.proyectoid = res.proyectoId;
                        this.bean.monedaid = res.monedaId;
                        this.bean.monto = res.monto;
                        this.bean.areaid = res.areaRevisoraId;
                    }
                }
            );

            var botonesAdicionales: DtoAprobacionAccion[] = [];

            this.wfTransaccionBotonesComponent.iniciarComponente(UUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false, botonesAdicionales);
            //this.wfTransaccionPlanificacionComponent.iniciarComponente(UUID);

            promises.push(p3);

            var p4 = this.wfTransaccionServicio.obtenerCabecera(UUID).then(
                x => {
                    //obtener resumen
                    var htmlresumen = x.filter(y => y.archivonombre.endsWith(".html") && y.tipodocumento == '81');
                    var docs = x.filter(y => y.archivonombre.endsWith(".pdf"));
                    if (htmlresumen.length > 0) {
                        var resumen = new DtoWfTransaccionAdjunto(this.domSanitizer);
                        resumen.tamanio = 100;
                        this.contenidoFormulario = this.domSanitizer.bypassSecurityTrustHtml(htmlresumen[0].archivostring);
                        console.log(this.contenidoFormulario);
                        resumen.tipo = "HTML";
                        this.resumen = resumen;
                    }
                    docs.forEach(
                        row => {
                            var html = new DtoWfTransaccionAdjunto(this.domSanitizer);
                            html.tamanio = 100;
                            html.inner = "data:application/pdf;base64," + row.archivostring + "#zoom=FitH";
                            html.tipo = "PDF";
                            var temp = [...this.lstHTML];
                            temp.push(html);
                            this.lstHTML = temp;
                        }
                    );
                }
            );

            promises.push(p4);

            Promise.all(promises).then(
                res => {
                    this.desbloquearPagina();
                }
            );
        }
    }

    salir() {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.router.navigate(['/spring/wf/aprobacion-listado']);
        }
        else if (this.accion == this.ACCIONES.VER) {
            this.router.navigate(['/spring/wf/transaccion-listado-administrador']);
        }
        else {
            this.router.navigate([ConstanteWorkflow.ruta_wftransaccion_listado]);
        }
    }

    verModalAprobadores: boolean = false;

    guardar(): void {


        if (this.bean.proceso == 'RC') {
            this.verModalAprobadores = true;
            return;
        }

        var criterios = '';

        if (!this.estaVacio(this.bean.companiaid)) {
            criterios = criterios + '"COMP":"' + this.bean.companiaid + '", ';
        }
        if (!this.estaVacio(this.bean.sucursalid)) {
            criterios = criterios + '"SUCU":"' + this.bean.sucursalid + '", ';
        }
        if (this.bean.monto != null) {
            criterios = criterios + '"MONT":"' + this.bean.monto + '", ';
        }
        if (!this.estaVacio(this.bean.monedaid)) {
            criterios = criterios + '"MOND":"' + this.bean.monedaid + '", ';
        }
        if (!this.estaVacio(this.bean.areaid)) {
            criterios = criterios + '"AREV":"' + this.bean.areaid + '", ';
        }
        if (!this.estaVacio(this.bean.proyectoid)) {
            criterios = criterios + '"AFEM":"' + this.bean.proyectoid + '", ';
        }

        if (this.estaVacio(criterios)) {
            criterios = '{}';
        }
        else {
            criterios = criterios.substring(0, criterios.length - 2);
            criterios = "{" + criterios + "}";
        }

        console.log(criterios);

        this.bean.criterios = criterios;

        if (!this.validar()) {
            return;
        }

        this.bloquearPagina();
        this.wfTransaccionServicio.transaccionIniciar(this.bean).then(
            res => {
                var response = res as DtoFlujoTransaccionResponse;
                this.desbloquearPagina();

                if (response != null) {
                    if (response.resultado == '0') {
                        this.mostrarMensajeAdvertencia(response.mensaje);
                        return;
                    }
                    this.mostrarMensajeExito(this.getMensajeGrabado(response.transaccion));
                    this.salir();
                }

            }
        );
    }

    empleadoBuscadorA: DtoTabla = new DtoTabla();
    sugerenciasEmpleadoA: any[];

    filtroSugerenciaEmpleadoA(event) {
        let busqueda = event.query;
        this.wfTransaccionServicio.filtrarEmpleados(busqueda).then(empleados => {
            this.sugerenciasEmpleadoA = empleados
        });
    }

    aceptarAprobadores() {

        if (this.empleadoBuscadorA == null) {
            this.mostrarMensajeAdvertencia("Seleccionar un empleado");
            return;
        }
        if (this.empleadoBuscadorA.id == null) {
            this.mostrarMensajeAdvertencia("Seleccionar un empleado");
            return;
        }

        var criterios = '';

        if (!this.estaVacio(this.bean.companiaid)) {
            criterios = criterios + '"COMP":"' + this.bean.companiaid + '", ';
        }
        if (!this.estaVacio(this.bean.sucursalid)) {
            criterios = criterios + '"SUCU":"' + this.bean.sucursalid + '", ';
        }
        if (this.bean.monto != null) {
            criterios = criterios + '"MONT":"' + this.bean.monto + '", ';
        }
        if (!this.estaVacio(this.bean.monedaid)) {
            criterios = criterios + '"MOND":"' + this.bean.monedaid + '", ';
        }
        if (!this.estaVacio(this.bean.areaid)) {
            criterios = criterios + '"AREV":"' + this.bean.areaid + '", ';
        }
        if (!this.estaVacio(this.bean.proyectoid)) {
            criterios = criterios + '"AFEM":"' + this.bean.proyectoid + '", ';
        }

        if (this.estaVacio(criterios)) {
            criterios = '{}';
        }
        else {
            criterios = criterios.substring(0, criterios.length - 2);
            criterios = "{" + criterios + "}";
        }

        console.log(criterios);

        this.bean.criterios = criterios;

        this.bean.listaAprobador = [];
        var aprobadorInicial = new WorkFlowAprobadorTransaccion();
        aprobadorInicial.nivelId = 1;
        aprobadorInicial.personaId = this.empleadoBuscadorA.id;
        this.bean.listaAprobador.push(aprobadorInicial);

        this.verModalAprobadores = false;
        this.empleadoBuscadorA = new DtoTabla();
        this.sugerenciasEmpleadoA = [];

        this.bloquearPagina();
        this.wfTransaccionServicio.transaccionIniciar(this.bean).then(
            res => {
                var response = res as DtoFlujoTransaccionResponse;
                this.desbloquearPagina();

                if (response != null) {
                    if (response.resultado == '0') {
                        this.mostrarMensajeAdvertencia(response.mensaje);
                        return;
                    }
                    this.mostrarMensajeExito(this.getMensajeGrabado(response.transaccion));
                    this.salir();
                }

            }
        );
    }


    eliminarDocumento() {

    }


    validar(): boolean {
        this.messageService.clear();
        let valida = true;
        if (this.bean.proceso == null) {
            this.mostrarMensajeAdvertencia("Seleccionar el proceso");
            valida = false;
        }
        if (this.bean.referencia == null) {
            this.mostrarMensajeAdvertencia("Ingresar la referencia");
            valida = false;
        }

        if (this.estaVacio(this.bean.criterios)) {
            this.mostrarMensajeAdvertencia("Ingresar los criterios");
            valida = false;
        }
        return valida;
    }

    verModalTipoDocumento = false;
    tipoDocumento: string;

    cargarFolioPre() {
        this.tipoDocumento = "";
        this.verModalTipoDocumento = true;
    }



    cargarFolio(event: any) {

        this.verModalTipoDocumento = false;

        this.bloquearPagina();

        var empDoc = new DtoFlujoAdjunto();
        empDoc.secuencia = this.generarSecuencia();

        var files = event.files;

        if (files.length != 1) {
            this.desbloquearPagina();
            return;
        }
        if (files[0].size > 3000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 3 + 'mb');
            this.desbloquearPagina();
            return null;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
            empDoc.fecha = new Date();
            empDoc.archivonombre = event.files[0].name;
            empDoc.archivostring = result.toString();

            if (!this.contenidoFileValidoPorTipos(result.toString().split(',')[1], this.documentosPermitidos)) {
                this.desbloquearPagina();
                return;
            }

            empDoc.flagVer = "N";
            empDoc.firmaImagen = "N";
            empDoc.firmaElectronica = "N";
            empDoc.cantidadfirmas = 0;
            empDoc.tipodocumento = this.tipoDocumento;

            var tmp = [...this.bean.adjuntos];

            tmp.push(empDoc);

            this.bean.adjuntos = tmp;

            this.desbloquearPagina();
        };

    }

    generarSecuencia() {
        if (this.bean.adjuntos.length == 0) {
            return 1;
        }

        var max = this.bean.adjuntos[0].secuencia;

        this.bean.adjuntos.forEach(
            x => {
                if (x.secuencia > max) {
                    max = x.secuencia;
                }
            }
        );

        return max + 1;
    }
    cancelarTipoDoc() {
        this.verModalTipoDocumento = false;
    }

    descargarDocumento(secuencia: number) {

        const doc = this.bean.adjuntos.find(x => x.secuencia == secuencia);

        if (doc === null) {
            return;
        }

        if (doc.archivostring == null && doc.archivostring == undefined) {
            this.mostrarMensajeAdvertencia('No se encontró el documento');
            return;
        }

        const a = document.createElement('a');
        if (!doc.archivostring.startsWith("data:")) {
            a.href = 'data:application/octet-stream;base64,' + doc.archivostring;
        } else {
            a.href = doc.archivostring;
        }

        a.setAttribute('download', this.reemplazarRuta(doc.archivonombre, ',', '_'));
        const b = document.createEvent('MouseEvents');
        b.initEvent('click', false, true);
        a.dispatchEvent(b);
    }


    coreSalir() {
        this.salir();
    }
    coreExportar(tipo: string): void { }
    coreMensaje(mensage: MensajeController): void { }
    coreAccion(accion: any): void { }
    coreGuardar() {
        this.guardar();
    }

    contenidoFormulario: SafeHtml = null;

}
