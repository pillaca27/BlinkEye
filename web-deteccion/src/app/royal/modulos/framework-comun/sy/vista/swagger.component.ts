import { ActivatedRoute } from '@angular/router';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService, SelectItem } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoWsApi } from '../dominio/dto/DtoWsApi';
import { DtoWsApipath } from '../dominio/dto/DtoWsApipath';
import { DtoFiltroApi } from '../dominio/dto/DtoFiltroApi';
import { Table } from 'primeng/table';
import { DtoWsDefinicion } from '../dominio/dto/DtoWsDefinicion';
import { DtoWsDefinicionpropiedad } from '../dominio/dto/DtoWsDefinicionpropiedad';
import { DomSanitizer } from '@angular/platform-browser';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { SyApiServicio } from '../servicio/syapi.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Component({
    templateUrl: './swagger.component.html'
})
export class SwaggerComponent extends FormularioComponent implements OnInit {

    filtro: DtoFiltroApi = new DtoFiltroApi();
    rutaApi: string = "";//http://localhost:4200/assets/api-docs.json;
    lstApis: SelectItem[] = [];
    verModalCargarAPI: boolean = false;
    lstApiDefiniciones: DtoWsDefinicion[] = [];
    apiBean: DtoWsApi = new DtoWsApi();

    constructor(
        private confirmationService: ConfirmationService,
        private servicio: SyApiServicio,
        private domSanitizer: DomSanitizer,
        private cdref: ChangeDetectorRef,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {
        super.ngOnInit();
        this.listarApis();
    }

    nuevaApi() {
        this.rutaApi = "";
        this.verModalCargarAPI = true;
    }

    buscarRuta() {
        this.cargarEvent({ first: 0, rows: 20 })
    }

    cargarEvent(event: LazyLoadEvent) {
        if (this.formularioOninit == true) {
            this.formularioOninit = false;
            return;
        }
        this.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistrosPorPagina = 20;
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.servicio.listarrutaspaginado(this.filtro).then(
            res => {
                this.filtro.paginacion = res;
                this.desbloquearPagina();
            }
        );
    }

    listarApis() {
        this.bloquearPagina();
        this.filtro.api = null;
        this.lstApis = [];
        this.lstApis.push({ value: null, label: ConstanteAngular.COMBOSELECCIONE });
        this.servicio.listarApis().then(
            res => {
                this.desbloquearPagina();
                res.forEach(
                    r => {
                        this.lstApis.push({ value: r.id, label: r.nombre });
                    }
                );
            }
        );

    }

    cargarApi() {

        if (this.estaVacio(this.rutaApi)) {
            this.mostrarMensajeAdvertencia("Ingresar la dirección");
            return;
        }

        this.bloquearPagina();
        this.servicio.get(this.rutaApi).then(
            resp => {
                this.desbloquearPagina();
                this.apiBean = new DtoWsApi();
                if (resp != null) {
                    this.generarBeanApi(resp);
                }
                else {
                    this.mostrarMensajeError("Error");
                }
            }
        );
    }


    procesarDefinicion(definicionActual: string, jRaw: any) {

        console.log(definicionActual)

        var defBean = new DtoWsDefinicion();
        defBean.nombre = definicionActual;

        var propsRaw = jRaw.definitions[definicionActual].properties;

        //Tipo character
        if (propsRaw == undefined) {
            return;
        }

        var propsNameRaw = Object.keys(propsRaw);

        propsNameRaw.forEach(
            pName => {
                var propBean = new DtoWsDefinicionpropiedad();
                propBean.tipodato = propsRaw[pName].type;
                propBean.nombre = pName;

                if (propBean.tipodato == 'array' || propBean.tipodato == undefined) {

                    if (propBean.tipodato == undefined) {
                        propBean.tipodato = "object";
                    }

                    var search = "";

                    if (propsRaw[pName].items != undefined) {
                        search = propsRaw[pName].items.$ref as string;
                    }
                    else {
                        search = propsRaw[pName].$ref as string;
                    }

                    if (search != undefined) {
                        search = search.substring(14, search.length);
                        propBean.definicion = this.lstApiDefiniciones.find(x => x.nombre == search);

                        if (propBean.definicion == undefined || propBean.definicion == null) {
                            //Evitando la recursividad
                            if (definicionActual == search) {
                                console.log("socorro recursion");
                            }
                            else {
                                this.procesarDefinicion(search, jRaw);
                                propBean.definicion = this.lstApiDefiniciones.find(x => x.nombre == search);
                                if (propBean.definicion == undefined || propBean.definicion == null) {
                                    console.log("error al agregar definition : " + propBean.nombre + "-" + propBean.tipodato);
                                }
                            }
                        }

                        if (propBean.definicion != null || propBean.definicion != undefined) {
                            propBean.idDefinicion = propBean.definicion.idDefinicion;
                        }
                    }
                    else {
                        propBean.tipodato = propBean.tipodato + '-' + propsRaw[pName].items.format;
                    }

                }
                defBean.propiedades.push(propBean);
            }
        );

        var duplicado = this.lstApiDefiniciones.find(x => x.nombre == defBean.nombre);

        if (duplicado == null || duplicado == undefined) {
            defBean.idDefinicion = this.iDefinicion;
            this.lstApiDefiniciones.push(defBean);
            this.iDefinicion++;
        }
    }

    iDefinicion: number;

    generarBeanApi(jRaw: any) {
        this.validaciones = [];
        this.iDefinicion = 1;

        var bean = new DtoWsApi();
        bean.host = jRaw.host;
        bean.nombre = jRaw.basePath;

        this.lstApiDefiniciones = [];

        var definitionsRaw = Object.keys(jRaw.definitions);

        definitionsRaw.forEach(
            def => {
                this.procesarDefinicion(def, jRaw);
            }
        );

        var pathsRaw = Object.keys(jRaw.paths);

        pathsRaw.forEach(
            pRaw => {
                var pathRaw = jRaw.paths[pRaw];

                var pathBean = new DtoWsApipath();
                pathBean.metodo = Object.keys(pathRaw)[0];
                pathBean.nombre = pRaw;

                var metodoRaw = pathRaw[pathBean.metodo];

                pathBean.notas = metodoRaw.description;

                pathBean.notas = this.estaVacio(pathBean.notas) ? "" : pathBean.notas;

                if (this.estaVacio(pathBean.notas)) {
                    var ex = new DtoTabla();
                    ex.nombre = pRaw;
                    this.validaciones.push(ex);
                }

                if (pathBean.metodo == 'get' || pathBean.metodo == 'delete') {
                    //TODO cuando sea get y delete
                }
                else {
                    var parametros = metodoRaw.parameters as any[];
                    if (parametros != null && parametros != undefined) {
                        var parametro = parametros[0];

                        if (parametro.schema == undefined) {
                            //Si no tiene eschema es porq eu es un dato primitivo
                        }
                        else {
                            var nombreDefinicionRequest = null;

                            if (parametro.schema.type == 'array') {
                                pathBean.tipodatoRequest = 'array';
                                nombreDefinicionRequest = parametro.schema.items.$ref as string;
                            }
                            else {
                                pathBean.tipodatoRequest = "Object";
                                nombreDefinicionRequest = (parametro.schema.$ref as string);
                            }

                            nombreDefinicionRequest = nombreDefinicionRequest.substring(14, nombreDefinicionRequest.length);

                            pathBean.definicionRequest = this.lstApiDefiniciones.find(x => x.nombre == nombreDefinicionRequest);
                            //TODO, SWAGGER NO MAPEA	private ConstanteDatos.TIPO_IMAGEN tipoImagen; EN CompanyownerrecursoTransaccion
                            if (pathBean.definicionRequest != undefined) {
                                pathBean.idDefinicionRequest = pathBean.definicionRequest.idDefinicion;
                            }
                        }
                    }
                }

                if (metodoRaw.responses[200].schema != undefined) {
                    var nombreDefinicionResponse = metodoRaw.responses["200"].schema.$ref;

                    if (nombreDefinicionResponse == undefined) {
                        var tipoResponse = metodoRaw.responses["200"].schema.type;

                        if (tipoResponse == 'array') {
                            pathBean.tipodatoResponse = "array";
                            nombreDefinicionResponse = metodoRaw.responses["200"].schema.items.$ref;

                            if (nombreDefinicionResponse == undefined) {
                                //Cuando retorna un arreglo de byets, solo ponermos es un arreglo
                            }
                            else {
                                nombreDefinicionResponse = nombreDefinicionResponse.substring(14, nombreDefinicionResponse.length);
                                pathBean.definicionResponse = this.lstApiDefiniciones.find(x => x.nombre == nombreDefinicionResponse);
                                pathBean.idDefinicionResponse = pathBean.definicionResponse.idDefinicion;
                            }
                        }
                        else {
                            pathBean.tipodatoResponse = metodoRaw.responses["200"].schema.type;
                            pathBean.definicionResponse = null;
                        }
                    }
                    else {
                        nombreDefinicionResponse = nombreDefinicionResponse.substring(14, nombreDefinicionResponse.length);

                        pathBean.tipodatoResponse = "Object";
                        pathBean.definicionResponse = this.lstApiDefiniciones.find(x => x.nombre == nombreDefinicionResponse);
                        pathBean.idDefinicionResponse = pathBean.definicionResponse.idDefinicion;
                    }
                }
                else {
                    pathBean.tipodatoResponse = "void";
                    pathBean.definicionResponse = null;
                }

                bean.paths.push(pathBean);
            }
        );

        //enviar a la bd

        //limpiar definiciones en paths, ya se tiene el id

        bean.paths.forEach(
            p => {
                p.definicionRequest = null;
                p.definicionResponse = null;
            }
        );
        bean.definiciones = this.lstApiDefiniciones;

        bean.definiciones.forEach(
            def => {
                def.propiedades.forEach(
                    p => {
                        p.definicion = null;
                    }
                );
            }
        );

        if (this.validaciones.length > 0) {
            this.mostrarMensajeAdvertencia("Lo siguientes servicios no cuentan con detalle");
            this.verValidaciones = true;
            this.verModalCargarAPI = false;
            this.beanTemporal = bean;
            return;
        }

        this.bloquearPagina();
        this.servicio.registrar(bean).then(
            res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.verModalCargarAPI = false;
                    this.listarApis();
                }
            }
        );
    }

    guardarSinConfigs() {
        this.confirmationService.confirm({
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: 'Desea continuar con la carga?',
            accept: () => {
                this.bloquearPagina();
                this.servicio.registrar(this.beanTemporal).then(
                    res => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res)) {
                            this.verModalCargarAPI = false;
                            this.listarApis();
                        }
                    }
                );
            },
            reject: () => {
                this.verValidaciones = false;
            }
        });
    }

    beanTemporal: any;

    limpiar(table: Table) {
        table.clear();
    }

    pathVisualizando: DtoWsApipath = new DtoWsApipath();
    notasPath: any;

    verPath(path: DtoWsApipath) {
        this.bloquearPagina();
        this.servicio.obtenerDetallePath(path).then(
            res => {
                this.desbloquearPagina()
                var pathVisualizando = new DtoWsApipath();
                pathVisualizando.nombre = res.nombre;
                pathVisualizando.metodo = res.metodo;
                pathVisualizando.tipodatoRequest = res.tipodatoRequest;
                pathVisualizando.definicionRequest = res.definicionRequest;
                pathVisualizando.tipodatoResponse = res.tipodatoResponse;
                pathVisualizando.definicionResponse = res.definicionResponse;
                pathVisualizando.notas = res.notas;
                this.notasPath = this.domSanitizer.bypassSecurityTrustHtml(pathVisualizando.notas);
                this.pathVisualizando = pathVisualizando;
            }
        );
    }

    darVistoBueno(path: DtoWsApipath) {
        this.bloquearPagina();
        this.servicio.vistobueno(path).then(
            res => {
                var temp = [...this.filtro.paginacion.paginacionListaResultado];
                var i = temp.indexOf(path);
                temp[i].vb = temp[i].vb == null || temp[i].vb == 'N' ? 'S' : "N";
                this.filtro.paginacion.paginacionListaResultado = temp;
                this.desbloquearPagina();
            }
        );
    }

    verValidaciones: boolean;
    validaciones: DtoTabla[] = [];

}