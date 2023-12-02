import { convertDateStringsToDates } from 'src/app/royal/framework/angular/funciones/dateutils';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { OnInit } from '@angular/core';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { ParametroTransaccion } from '@framework/modelo/ParametroTransaccion';
import { BaseComponent } from './BaseComponent';
import { MenuItem, SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { EmpleadomastTransaccion } from '@framework/modelo/EmpleadomastTransaccion';
import { PersonamastTransaccion } from '@framework/modelo/PersonamastTransaccion';

export const enum accionSolicitada {
    NADA, NUEVO, EDITAR, VER, COPYINTERMEDIO, TRANSACCION
}

export class PrincipalBaseComponent extends BaseComponent implements OnInit {

    // DARIO: servicio local-inicio
    esAdministrador: string;
    servicioComunLocal: ServicioComunService;
    noAuthorizationInterceptorLocal: NoAuthorizationInterceptor;
    nombreParametroSession: string;
    // DARIO: servicio local-fin

    erroresList: DtoTabla[] = [];
    verTablaErrores: boolean = false;
    accionS: accionSolicitada;
    verVentanaEmergente: boolean = false;
    constructor(
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        this.servicioComunLocal = servicioComun;
        this.noAuthorizationInterceptorLocal = noAuthorizationInterceptor;
        this.esAdministrador = 'N';
    }

    // DARIO: falta implementar
    personaObtener(persona: number): Promise<PersonamastTransaccion> {
        return this.servicioComunLocal.PersonaPorDtoApi(persona).then(res => {
            // if(this.transaccionResultado(res)){
            return res;
            // }
        });
    }

    // DARIO: falta implementar
    empleadoObtener(companiasocio: string, persona: number): Promise<EmpleadomastTransaccion> {
        this.servicioComunLocal.EmpleadoPorDtoApi(companiasocio, persona).then(res => {
            // if(this.transaccionResultado(res)){
            return res;
            // }
        });
        return null;
    }

    // DARIO: falta implementar
    parametroObtener(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<ParametroTransaccion> {
        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;
        this.servicioComunLocal.parametroobtener(parametrotransaccion).then(res => {
            // if(this.transaccionResultado(res)){
            return res;
            // }
        });
        return null;
    }

    // DARIO: falta implementar
    parametroObtenerExplicacion(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<String> {

        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;

        return this.servicioComunLocal.parametroobtener(parametrotransaccion).then(res => {
            // if(this.transaccionResultado(res)){
            return res.explicacion;
            // }
        });
    }

    // DARIO: falta implementar
    parametroObtenerTexto(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<String> {

        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;

        return this.servicioComunLocal.parametroobtener(parametrotransaccion).then(res => {
            // if(this.transaccionResultado(res)){
            if (res.texto != null) {
                return res.texto.trim();
            }
            return res.texto;
            // }
        });

        //return null;                
    }

    // DARIO: falta implementar
    parametroObtenerNumero(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<number> {

        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;

        return this.servicioComunLocal.parametroobtener(parametrotransaccion).then(res => {
            // if(this.transaccionResultado(res)){
            return res.numero;
            // }
        });

        // return null;                
    }

    // DARIO: falta implementar
    parametroObtenerFecha(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<Date> {
        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;

        this.servicioComunLocal.parametroobtener(parametrotransaccion).then(res => {
            // if(this.transaccionResultado(res)){
            return res.fecha;
            // }
        });
        return null;
    }


    // DARIO: para el llenado de los combos especificar la etiqueta que tendra
    miscelaneosListar(tipo: number, aplicacionCodigo: string,
        codigoTabla: string,
        codigoCompania: string, lstDatos: SelectItem[]): Promise<number> {

        var label = '-- Seleccione --';

        if (tipo == this.comboEtiquetaTipo.LISTADO) {
            var label = '-- Todos --';
        }

        lstDatos.push({ label: label, value: null });
        return this.servicioComunLocal.listaractivos(codigoCompania, aplicacionCodigo, codigoTabla).then(res => {
            res.forEach(ele => {
                lstDatos.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    salirTablaErrores() {
        this.verTablaErrores = false;
        this.erroresList = [];
    }

    print() {
        window.print();
    }

    ngOnInit() {
        this.noAuthorizationInterceptorLocal.dataStream$().subscribe(errores => {
            this.messageService.clear();
            if (errores != undefined) {
                this.erroresList = [];

                if (errores.error.flagTabla) {
                    this.verTablaErrores = true;
                    var c = 1;
                    errores.error.errores.forEach(element => {
                        var er = new DtoTabla();
                        er.id = c;
                        er.descripcion = element.Mensaje;
                        this.erroresList.push(er);
                        c++;
                    });
                }
                else {
                    this.messageService.addAll(this.getMensajesUsuario(errores.error.errores));
                }
            }
        });
    }
}