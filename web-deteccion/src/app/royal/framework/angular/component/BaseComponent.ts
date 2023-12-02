import { DecimalesDto } from './../dominio/DecimalesDto';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { convertDateStringsToDates } from 'src/app/royal/framework/angular/funciones/dateutils';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MessageService, SelectItem, Message } from 'primeng/api';
import { Subscriber } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { AuditoriaDto } from '@framework/angular/dominio/AuditoriaDto';
import { ObjetoTitulo } from '@framework/angular/dominio/ObjetoTitulo';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { SeguridadMenuItem } from 'src/app/royal/framework/modelo/seguridad/SeguridadMenuItem';
import { ObjetoBoton } from '@framework/angular/dominio/ObjetoBoton';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { SeguridadUsuarioActual } from '@framework/modelo/seguridad/SeguridadUsuarioActual';

// ARMAS: acciones de ventanas
export const enum accionSolicitada {
    //COPYINTERMEDIO, TRANSACCION, ADMITIR, DESESTIMAR, DENEGAR,  WORKFLOW_SOLICIUD, WORKFLOW_TRANSACCION, GUARDAR, SALIR,
    NADA,
    LISTAR,
    NUEVO,
    EDITAR,
    VER,
    ELIMINAR,
    ANULAR,
    WORKFLOW,
    APROBAR,
    COPIAR,
    REVISAR,
    CERRAR,
    INVITAR,
    COTIZAR,
    IMPRIMIR,
    GENERAR,
    EDITARMOD,
    OTRA_ACCION
}

//TIPOS DE AUDITORIA
export enum tiposAuditoria {
    BAS2C = "BAS2C",
}

//TIPOS BOTONES LISTADO
export enum tiposBotonesListado {
    BAS1B = "BAS1B",
    BAS2B = "BAS2B",
    BAS2D = "BAS2D",
    BAS2F = "BAS2F",
    BAS1N = "BAS1N",
    BAS2N = "BAS2N",
    BASOC = "BASOC",
    BASCT = "BASCT",
    BASCI = "BASCI",
    BASCH = "BASCH",
    BASGO = "BASGO",
    BASCO = "BASCO",
    BAS0B = "BAS0B",
    BAS1R = "BAS1R",
    BAS3B = "BAS3B",
    BASGC = "BASGC",
    BASAN = "BASAN",
    BOSIN = "BOSIN",
    BOEXP = "BOEXP",
    BOBUS = "BOBUS"
}

//TIPOS BOTONES MANTENIMIENTO
export enum tiposBotonesMantenimiento {
    BAS2B = "BAS2B",
    CH2B = "CH2B",
    BAS21 = "BAS21",
    BAS1M = "BAS1M",
}

// DARIO: para el llenado de los combos especificar la etiqueta que tendra
export const enum comboEtiquetaTipo {
    LISTADO, MANTENIMIENTO
}

// ARMAS: Titulo Listado Tipo
export const enum tituloListadoTipo {
    ESTANDAR
}

// ARMAS: Titulo Mantenimiento Tipo
export const enum tituloMantenimientoTipo {
    ESTANDAR
}

export function DestroySubscribers() {
    return function (target: any) {
        // decorating the function ngOnDestroy
        target.prototype.ngOnDestroy = ngOnDestroyDecorator(target.prototype.ngOnDestroy);
        // decorator function
        function ngOnDestroyDecorator(f) {
            return function () {
                // saving the result of ngOnDestroy performance to the variable superData
                const superData = f ? f.apply(this, arguments) : null;
                // unsubscribing
                for (const subscriberKey in this.subscribers) {
                    const subscriber = this.subscribers[subscriberKey];
                    if (subscriber instanceof Subscriber) {
                        subscriber.unsubscribe();
                    }
                }
                // returning the result of ngOnDestroy performance
                return superData;
            };
        }
        // returning the decorated class
        return target;
    };
}

@DestroySubscribers()
export class BaseComponent {

    // DARIO: Accion del formulario
    accion: accionSolicitada;

    //ARMAS: numero de pagina del listado
    numeropagina: number;

    //ARMAS: si el formulario no se inicia = true
    formularioOninit: boolean = true;

    //ARMAS: Control de mensajes entre componente
    mensajeController: MensajeController;

    blocked: boolean = false;
    es: any;//Idioma del p-calendar (Español)    
    languaje: string;//Idioma de la web
    proxy: string;//proxy de los servicios
    mensajeResultado: string;//Mensaje final de las transacciones

    urlAyuda: string = '';//Url del documento de ayuda

    KeyAlfanumerico: RegExp = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ\d\-_\s]+$/;//Validar sin carecteres especiales

    locale: string = "en-US";

    menuSeguridad: SeguridadMenuItem = new SeguridadMenuItem();
    objetoTitulo: ObjetoTitulo = new ObjetoTitulo();
    objetoBoton: ObjetoBoton[] = [];
    rowselect: any;

    decimalesn: DecimalesDto = new DecimalesDto();
    constructor(
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        protected messageService: MessageService,
        public servicioComun: ServicioComunService) {
        this.init();
    }

    //Inicializar
    init() {
        this.es = {
            firstDayOfWeek: 1,
            dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
            dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb'],
            dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],


            today: 'Hoy',
            clear: 'Limpiar',
            dateFormat: 'dd/mm/yy',
            weekHeader: 'Sem'


        };
        window.onbeforeunload = null;
        this.languaje = localStorage.getItem('lang');
    }

    flagABoolean(flag: string): boolean {
        if (flag === 'S') {
            return true;
        } else { return false; }
    }
    flagAInteger(flag: string): boolean {
        if (flag === '1') {
            return true;
        } else { return false; }
    }
    booleanAFlag(bool: boolean): string {
        if (bool) {
            return 'S';
        } else { return 'N'; }
    }
    integerAFlag(bool: boolean): string {
        if (bool) {
            return '1';
        } else { return '0'; }
    }
    getMensajePreguntaInactivar(): string {
        return 'Desea inactivar este registro?';
    }
    getMensajePreguntaDesbloqeuar(): string {
        return 'Desea desbloquear este registro?';
    }
    getMensajePreguntaProvInstitucionInactivar(nombre: string, titulo: string): string {
        return titulo + "  " + nombre + "?"
    }
    getMensajePreguntaCompletado(): string {
        return 'Desea Completar este registro?';
    }
    getMensajePreguntaActivar(): string {
        return 'Desea Activar este registro?';
    }
    getMensajePreguntaPlanificar(): string {
        return 'Desea Planificar este registro?';
    }
    getMensajesUsuario(mensajes: DominioMensajeUsuario[]): Message[] {
        const messages: Message[] = [];
        mensajes.forEach(
            mensaje => {
                let severidad = '';
                switch (mensaje.tipoMensaje) {
                    case '0': { severidad = 'warn'; break; }
                    case '1': { severidad = 'error'; break; }
                    case '2': { severidad = 'success'; break; }
                    case '3': { severidad = 'info'; break; }
                    default: { severidad = 'info'; break; }
                }
                if (mensaje.mensaje === undefined && mensaje.mensaje !== undefined) {
                    mensaje.mensaje = mensaje.mensaje;
                }
                messages.push({ severity: severidad, summary: 'Mensaje', detail: mensaje.mensaje });
            }
        );
        return messages;
    }

    //Copiar objeto
    copiar<T>(instance: T): T {
        const copy = new (instance.constructor as { new(): T })();
        Object.assign(copy, instance);
        return copy;
    }

    toDate(date: Date): Date {
        if (date == null) {
            return null;
        }
        return new Date(date);
    }

    //Clonar objeto
    clone(obj): any {
        let copy;

        // Handle the 3 simple types, and null or undefined
        if (null == obj || 'object' !== typeof obj) { return obj; }

        // Handle Date
        if (obj instanceof Date) {
            copy = new Date();
            copy.setTime(obj.getTime());
            return copy;
        }

        // Handle Array
        if (obj instanceof Array) {
            copy = [];
            for (let i = 0, len = obj.length; i < len; i++) {
                copy[i] = this.clone(obj[i]);
            }
            return copy;
        }

        // Handle Object
        if (obj instanceof Object) {
            copy = {};
            for (const attr in obj) {
                if (obj.hasOwnProperty(attr)) { copy[attr] = this.clone(obj[attr]); }
            }
            return copy;
        }

        throw new Error('Unable to copy obj! Its type isn t supported.');
    }


    //Obtener tipo documento adjunto
    obtenerMimeType(extension: string): string {
        let mimeType: string;

        switch (extension) {
            case 'msword':
                mimeType = 'doc';
                break;
            case 'vnd.openxmlformats-officedocument.wordprocessingml.document':
                mimeType = 'docx';
                break;
            case 'jpeg':
                mimeType = 'jpg';
                break;
            case 'png':
                mimeType = 'png';
                break;
            case 'json':
                mimeType = 'json';
                break;
            case 'pdf':
                mimeType = 'pdf';
                break;
            case 'vnd.ms-powerpoint':
                mimeType = 'ppt';
                break;
            case 'vnd.ms-excel':
                mimeType = 'xls';
                break;
            case 'vnd.openxmlformats-officedocument.spreadsheetml.sheet':
                mimeType = 'xlsx';
                break;
            case 'plain':
                mimeType = 'txt';
                break;
            default:
                mimeType = extension;
                break;
        }

        return mimeType;
    }

    obtenerDescarga(res, titulo, fecha) {
        const link = window.URL.createObjectURL(res);
        const a = document.createElement('a');
        const type = res.type.split('/');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = link;
        if (type[1] === 'vnd.ms-excel') {
            a.download = titulo + " " + fecha + '.xls';
        } else {
            a.download = titulo + " " + fecha + '.' + type[1];
        }
        a.click();
        window.URL.revokeObjectURL(link);
        a.remove();
    }

    obtenerFechaInicio() {
        let date = new Date();
        let firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        return firstDay;
    }
    obtenerFechaFin() {
        let date = new Date();
        let lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        return lastDay;
    }

    obtenerFechasFinWh(date: Date): string {
        var diaActual = date.getDate().toString();
        var mesActual = (date.getMonth() + 1).toString();
        var anioActual = date.getFullYear().toString();
        var horas = '23:59'

        if (diaActual.length === 1) {
            diaActual = '0' + diaActual;
        }

        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }

        return anioActual + "-" + mesActual + '-' + diaActual + ' ' + horas
    }

    esFechaVacia(fecha: Date): boolean {
        if (fecha == null) {
            return true;
        }
        if (fecha == undefined) {
            return true;
        }

        return false;
    }
    esListaVacia(lista: any): boolean {
        if (lista == null) {
            return true;
        }
        if (lista == undefined) {
            return true;
        }
        if (lista.length == 0) {
            return true;
        }
        return false;
    }
    estaVacio(cadena: string): boolean {
        if (cadena == null) {
            return true;
        }
        if (cadena == undefined) {
            return true;
        }
        if (cadena.trim() === '') {
            return true;
        }
        return false;
    }
    esObjetoVacio(objeto: any): boolean {
        if (objeto == null) {
            return true;
        }
        if (objeto == undefined) {
            return true;
        }
        if (Object.keys(objeto).length === 0) {
            return true;
        }

        return false;
    }
    esNumeroVacio(numero): boolean {
        if (numero == null) {
            return true;
        }
        if (numero === undefined) {
            return true;
        }
        return false;
    }
    esNumeroVacioOrCero(numero): boolean {
        if (numero == null) {
            return true;
        }
        if (numero === undefined) {
            return true;
        }
        if (numero === 0) {
            return true;
        }
        return false;
    }
    esfechaInicioMayor(inicio: Date, fin: Date): boolean {
        if (inicio > fin) {
            return true;
        } else {
            return false
        }
    }
    esNumeroVacioCero(numero): number {
        if (numero == null) {
            return 0;
        }
        if (numero === undefined) {
            return 0;
        } else {
            return numero
        }
    }

    sinEspacios(valor: string) {
        if (!this.estaVacio(valor)) {
            valor = valor.trim();
        }
        return valor;
    }

    obtenerLabelPorCombo(lista: SelectItem[], val: any): string {
        if (val == null) {
            return '';
        }
        if (Number.isNaN(val)) {
            if (val.trim() === '') {
                return '';
            }
        }
        let label: string = '';
        lista.forEach(item => {
            if (item.value === val) {
                label = item.label;
            }
        });
        return label;
    }
    defaultBuscarPaginacion(tb: any, event) {
        if (event.keyCode === 13) {
            tb.reset();
        }
    }
    mostrarMensajeAdvertenciaNoClear(mensaje: string): void {
        this.mostrarMensajeNoClear(mensaje, 'warn');
    }
    mostrarMensajeAdvertencia(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'warn');
    }
    mostrarMensajeAdvertenciaFechas(): void {
        this.mostrarMensaje("La fecha fin no debe ser menor a la fecha de inicio.", 'warn');
    }
    mostrarMensajevalidarexportarGridPrincipal(): void {
        this.mostrarMensaje("No se encontro información para exportar.", 'warn');
    }
    mostrarMensaje(mensaje: string, tipo: string): void {
        this.messageService.clear();
        this.messageService.add({ severity: tipo, summary: 'Mensaje', detail: mensaje, life: 25000 });
    }
    mostrarMensajeNoClear(mensaje: string, tipo: string): void {
        this.messageService.add({ severity: tipo, summary: 'Mensaje', detail: mensaje, life: 25000 });
    }
    mostrarMensajeListado(mensajes: DominioMensajeUsuario[]): void {
        mensajes.forEach(element => {
            this.messageService.add({ severity: element.tipoMensaje, summary: element.titulo, detail: element.mensaje });
        });
    }
    mostrarMensajeExito(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'success');
    }
    mostrarMensajeInfo(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'info');
    }
    getMensajeGrabado(value: any): string {
        return 'El Registro Nro. ' + value + ' se guardó con éxito';
    }
    getMensajeGrabadoObs(value: any): string {
        return 'Se ha generado el informe de Observaciones N° ' + value;
    }
    getMensajeActualizado(value: any): string {
        return 'La Actualización Nro. ' + value + ' se guardó con éxito';
    }
    getMensajeEliminado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue eliminado';
    }
    getMensajeInactivado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue inactivado';
    }
    getMensajeInactivadoSinCodigo(): string {
        return 'El Registro fue inactivado';
    }
    getMensajeGrabadoSinCodigo(): string {
        return 'El Registro se guardó con éxito';
    }
    getMensajeEliminadoSinCodigo(): string {
        return 'El Registro se eliminó con éxito';
    }
    getMensajeActualizadoSinCodigo(): string {
        return 'La Actualización se guardó con éxito';
    }
    getMensajePreguntaAnular(): string {
        return 'Desea Anular este registro?';
    }
    getMensajePreguntaCerrar(): string {
        return 'Desea Cerrar este registro?';
    }
    getMensajePreguntaCulminar(): string {
        return 'Desea Terminar este registro?';
    }
    getMensajePreguntaCopiar(): string {
        return 'Desea Copiar este registro?';
    }
    getMensajePreguntaEliminar(): string {
        return 'Desea Eliminar este registro?';
    }
    getMensajePreguntaEliminarPoi(): string {
        return 'Desea Eliminar este registro? Si se elimina el registro se eliminaran todos los registros asociados';
    }
    getMensajeAnulado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue Modificado';
    }
    getMensajeAnulame(value: any): string {
        return 'El Registro Nro. ' + value + ' fue Anulado';
    }
    getMensajeCerrado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue cerrado';
    }
    getMensajeejecucion(value: any): string {
        return 'El Registro Nro. ' + value + ' fue revertido En Ejecución';
    }
    getMensajeTerminado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue Terminado';
    }
    getMensajeCopiado(value: any): string {
        return 'El Registro Nro. ' + value + ' fue copiado';
    }
    getMensajePreguntaAprobar(): string {
        return '¿Desea aprobar este registro?';
    }
    getMensajeAprobado(value: any): string {
        return 'El registro Nro. ' + value + ' se aprobó con éxito';
    }
    getMensajeValidaRepetido(adj: string, proceso: string, nombre: string, linea: string): string {
        return '' + adj + ' ' + proceso + ' ' + nombre + ' ya fue ingresado, duplicidad en la línea : ' + linea;
    }
    getMensajeRegistroUsuario(value: any): string {
        return 'El usuario con codigo ' + value + ' se registró con éxito';
    }
    getMensajeRegistroUsuarioDesbloqeuar(value: any): string {
        return 'El usuario con codigo ' + value + ' fue desbloquado con éxito';
    }
    getMensajeRegistroCotizacion(value: any): string {
        return 'La cotización con codigo ' + value + ' se registró con éxito';
    }
    getMensajeRegistroRequisito(value: any): string {
        return 'La Solicitud con código ' + value + ' se registró con éxito';
    }
    getMensajeActualiacionCotizacion(value: any): string {
        return 'La cotización con codigo ' + value + ' se actualizó con éxito';
    }
    getMensajeActualiacionPedido(value: any): string {
        return 'El pedido con codigo ' + value + ' se actualizó con éxito';
    }
    getMensajeRegistroPedido(value: any): string {
        return 'El pedido con codigo ' + value + ' se registró con éxito';
    }
    getmensajeordenatencioncreate(value: any): string {
        return 'La Orden de atención con codigo ' + value + ' se registró con éxito';
    }
    getMensajeActualiacionExpediente(value: any): string {
        return 'El expediente con codigo ' + value + ' se actualizó con éxito';
    }
    getMensajeActualiacionNeg(value: any): string {
        return 'La Negociación con codigo ' + value + ' se actualizó con éxito';
    }
    getgenerateContaro(value: any): string {
        return 'La negociación ha sido aprobada. Se generó contrato : ' + value + '';
    }
    getMensajeActualiacionExpedienteEvaluado(value: any): string {
        return 'El expediente público   ' + value + '  ha sido evaluado';
    }
    getMensajeActualiacionExpedienteAprobado(value: any): string {
        return 'El expediente ha sido aprobado. Se generó  la negociación :    ' + value;
    }
    getMensajeRegistroUsuarioPerfil(value: any, tipo: string): string {
        return 'El ' + tipo + ' ' + 'con codigo ' + value + ' se registró con éxito';
    }
    getMensajeActualizarUsuario(value: any): string {
        return 'El registro con codigo ' + value + ' se actualizó con éxito';
    }
    getMensajeEliminarUsuario(): string {
        return 'El registro se elimino correctamente';
    }
    getMensajeNoExisteCorreoPersona(): string {
        return 'La persona no cuenta con algun correo registrado.';
    }
    getMensajeInvalidoCorreoPersona(): string {
        return 'El correo registrado en esta persona no es valida.';
    }

    trim(cadena: string): string {
        if (this.estaVacio(cadena)) {
            return null;
        }
        return cadena.trim();
    }

    trimNoNulo(cadena: string): string {
        if (this.estaVacio(cadena)) {
            return "";
        }
        return cadena.trim();
    }

    getMensajeDocMorososContinuar(): string {
        return 'El Cliente tiene Documentos Morosos, ¿Continuar?';

    }
    getMensajePreguntaDesaprobar(): string {
        return '¿Desea desaprobar este registro?';
    }
    mostrarMensajeError(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'error');
    }

    // DARIO:Etiquetas
    getAccionNombre() {
        if (this.accion) {
            switch (this.accion) {
                case accionSolicitada.LISTAR: return 'Listar';
                case accionSolicitada.NUEVO: return 'Nuevo';
                case accionSolicitada.EDITAR: return 'Edición';
                case accionSolicitada.VER: return 'Vista Previa';
                case accionSolicitada.ELIMINAR: return 'Eliminar';
                case accionSolicitada.ANULAR: return 'Anular';
                case accionSolicitada.WORKFLOW: return 'Work Flow';
                case accionSolicitada.OTRA_ACCION: return 'Otra Acción';
            }
        }
        return '';
    }

    borrarUTCHoras(fecha: Date): Date {
        if (fecha == null) { return null; }
        fecha.setUTCMilliseconds(fecha.getMilliseconds());
        fecha.setUTCMinutes(fecha.getMinutes());
        fecha.setUTCHours(fecha.getHours());
        fecha.setUTCDate(fecha.getDate());
        return fecha;
    }

    obtenerFechaFormatedDate(date: string) {
        var dataSplit = date.split('-');

        var diaActual = dataSplit[2]
        var mesActual = dataSplit[1]
        var anioActual = dataSplit[0]
        if (diaActual.length === 1) {
            diaActual = '0' + diaActual;
        }
        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }
        let date_actual = new Date(anioActual + '-' + mesActual + '-' + diaActual)
        date_actual.setDate(date_actual.getDate() + 1)
        return date_actual
    }

    modificarFormatoFecha(fecha: Date) {
        var diaActual = fecha.getDate().toString();
        var mesActual = (fecha.getMonth() + 1).toString();
        var anioActual = fecha.getFullYear().toString();
        if (diaActual.length === 1) {
            diaActual = '0' + diaActual;
        }
        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }
        return diaActual + '/' + mesActual + '/' + anioActual;
    }

    modificarFormatoFechaExport(fecha: Date) {
        var diaActual = fecha.getDate().toString();
        var mesActual = (fecha.getMonth() + 1).toString();
        var anioActual = fecha.getFullYear().toString();
        if (diaActual.length === 1) {
            diaActual = '0' + diaActual;
        }
        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }
        return diaActual + '-' + mesActual + '-' + anioActual;
    }

    obtenerPeriodoActual() {
        var fechaActual = new Date();
        return fechaActual.getFullYear();
    }

    esCorreoValido(email: string) {
        let mailValido = false;
        var EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if (email.match(EMAIL_REGEX)) {
            mailValido = true;
        }
        return mailValido;
    }

    esCorreoValidoTest(email: string) {
        let mailValido = false;
        var EMAIL_REGEX = /^[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9_.+-\d\-_\s]+@[a-zA-ZñÑáéíóúÁÉÍÓÚ0-9_+-]+\.[a-zA-Z0-9-.]+$/;
        if (email.match(EMAIL_REGEX)) {
            mailValido = true;
        }
        return mailValido;
    }

    esCorreoValidoPersona(email: string) {
        let mailValido = false;
        if (email == null || email == '') {
            mailValido = true
        } else {
            var EMAIL_REGEX = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
            if (email.match(EMAIL_REGEX)) {
                mailValido = true;
            }
        }
        return mailValido;
    }

    obtenerPeriodoMesActual(hoy: Date) {
        var periodoMes = '0';
        var mesActual = (hoy.getMonth() + 1).toString();
        var anioActual = hoy.getFullYear().toString();

        if (mesActual.length === 1) {
            mesActual = '0' + mesActual;
        }

        periodoMes = anioActual + mesActual;

        return +periodoMes;
    }

    validarIngresoMontoDecimal(monto: number): Boolean {
        var unit = true;
        if (!isNaN(monto)) {
            var auxMonto = monto.toString().endsWith('.') ? monto.toString() + '00' : monto.toString();
            monto = +auxMonto;
        }
        var regexp = new RegExp("^[0-9]+([\.][0-9]{1,2})?$");

        unit = regexp.test(monto.toString());
        return unit;
    }

    //ARMAS: obtener la página de ayuda de acuerdo al documento
    obtenerPaginaAyuda(menu: SeguridadMenuItem): string {
        console.log(menu);
        console.log(menu.urlAyuda);
        this.urlAyuda = window.location.origin + '/assets/' + menu.urlAyuda;
        console.log(this.urlAyuda);
        return this.urlAyuda;
    }

    //Seleccionar registro de una tabla
    setRowSelect(any) {
        this.rowselect = any;
    }

    // ARMAS: asignación de tipo de acciones en mantenimiento con titulo
    tituloMantenimientoAsignarConTitulo(tipo: number, componente: UIMantenimientoController, letra: any, titulo: string, objetoBoton: ObjetoBoton[]) {
        this.tituloMantenimientoAsignar(tipo, componente, letra, objetoBoton);
        this.objetoTitulo.menuSeguridad.label = titulo;

        var items = [
            { label: this.objetoTitulo.menuSeguridad.grupo },
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var itemsMovil = [
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var icon = { icon: this.objetoTitulo.menuSeguridad.icon };

        this.servicioComun.setBreadIcon(icon)
        this.servicioComun.setBread(items, itemsMovil);
        this.servicioComun.setItems(this.objetoTitulo);
    }

    // ARMAS: asignación de tipo de acciones en mantenimiento
    tituloMantenimientoAsignar(tipo: number, componente: UIMantenimientoController, acciongen: any, objetoBoton: ObjetoBoton[]) {
        const ss = sessionStorage.getItem(ConstanteAngular.MENUSEGURIDAD);
        if (ss !== undefined && ss != null) {
            const seguridad = JSON.parse(ss) as SeguridadMenuItem;
            this.menuSeguridad = convertDateStringsToDates(seguridad);
            this.objetoTitulo.menuSeguridad = this.menuSeguridad;
        }
        this.objetoTitulo.componente = componente;
        this.objetoTitulo.tipo = tipo;
        console.log(acciongen, 'Accion');
        if (acciongen == "1") {
            this.objetoTitulo.accion = 'Listar';
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        } else if (acciongen == "2") {
            this.objetoTitulo.accion = 'Nuevo';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        } else if (acciongen == "3") {
            this.objetoTitulo.accion = 'Editar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "4") {
            this.objetoTitulo.accion = 'Ver';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "5") {
            this.objetoTitulo.accion = 'Eliminar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "6") {
            this.objetoTitulo.accion = 'Anular';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        }
        else if (acciongen == "7") {
            this.objetoTitulo.accion = 'Workflow';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "8") {
            this.objetoTitulo.accion = 'Aprobar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "9") {
            this.objetoTitulo.accion = 'Copiar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        } else if (acciongen == "10") {
            this.objetoTitulo.accion = 'Revisar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        } else if (acciongen == "11") {
            this.objetoTitulo.accion = 'Cerrar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        }
        else if (acciongen == "12") {
            this.objetoTitulo.accion = 'Invitar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        }
        else if (acciongen == "13") {
            this.objetoTitulo.accion = 'Cotizar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        } else if (acciongen == "14") {
            this.objetoTitulo.accion = 'Imprimir';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = false;
        }
        else if (acciongen == "15") {
            this.objetoTitulo.accion = 'Generar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = true;
            this.objetoTitulo.puedeEditarPk = true;
        }
        else if (acciongen == "16") {
            this.objetoTitulo.accion = 'Editar';
            this.objetoTitulo.codigoAccion = acciongen;
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        }
        else {
            this.objetoTitulo.accion = '';
            this.objetoTitulo.puedeEditar = false;
            this.objetoTitulo.puedeEditarPk = false;
        }

        this.objetoTitulo.objetoBoton = objetoBoton;
        console.log(this.objetoTitulo);

        var items = [
            { label: this.objetoTitulo.menuSeguridad.grupo },
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];


        var itemsMovil = [
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var icon = { icon: this.objetoTitulo.menuSeguridad.icon };

        this.servicioComun.setBreadIcon(icon)
        this.servicioComun.setBread(items, itemsMovil);
        this.servicioComun.setItems(this.objetoTitulo);
    }

    // ARMAS: gestion de los errores y mensajes
    transaccionResultadoMensaje(dto: any, mensaje: string): boolean {
        this.mensajeResultado = mensaje;
        if (dto != null) {
            console.log(dto);
            if (dto.transaccionEstado == 'ER' || dto.transaccionEstado == 'VA') {
                if (dto.transaccionListaMensajes.length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje });
                    });
                }
                return false;
            } else if (dto.transaccionEstado == 'OK') {
                this.mostrarMensajeExito(this.mensajeResultado);
                return true;
            }
        }
        return false;
    }

    transaccionResultadoSoloErrores(dto: any): boolean {
        this.mensajeResultado = 'Acción realizada con éxito.';
        if (dto != null) {
            console.log(dto);
            if (dto.transaccionEstado == 'ER' || dto.transaccionEstado == 'VA' || dto.transaccionEstado == 'ERROR') {
                if (dto.transaccionListaMensajes.length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje });
                    });
                }
                return false;
            } else if (dto.transaccionEstado == 'OK') {
                //this.mostrarMensajeExito('Acción realizada con éxito.');
                return true;
            }
        }
        return false;
    }
    // ARMAS: gestion de los errores y mensajes
    transaccionResultado(dto: any): boolean {
        this.mensajeResultado = 'Acción realizada con éxito.';
        if (dto != null) {
            console.log(dto);
            if (dto.transaccionEstado == 'ER' || dto.transaccionEstado == 'VA' || dto.transaccionEstado == 'ERROR') {
                if (dto.transaccionListaMensajes.length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje });
                    });
                }
                return false;
            } else if (dto.transaccionEstado == 'OK') {
                this.mostrarMensajeExito('Acción realizada con éxito.');
                return true;
            }
        }
        return false;
    }

    // ARMAS: gestion de los errores y mensajes sin mensaje de confirmacion
    transaccionResultadoSimple(dto: any): boolean {
        this.mensajeResultado = 'Acción realizada con éxito.';
        if (dto != null) {
            console.log(dto);
            if (dto.transaccionEstado == 'ER' || dto.transaccionEstado == 'VA' || dto.transaccionEstado == 'ERROR') {
                if (dto.transaccionListaMensajes.length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: element.mensaje, life: 25000 });
                    });
                }
                return false;
            } else if (dto.transaccionEstado == 'OK') {
                if ((dto.transaccionListaMensajes as DominioMensajeUsuario[]).filter(x => x.tipoMensaje.toString() === 'INFORMACION').length > 0) {
                    dto.transaccionListaMensajes.forEach(element => {
                        this.messageService.add({ severity: 'info', summary: 'Mensaje', detail: element.mensaje, life: 25000 });
                    });
                }
                return true;
            }
        }
        return false;
    }


    // DARIO: inicializar 
    formularioIniciar(route: any) {
        // capturar las acciones
        const accionExterna = route.snapshot.params['accion'] as number;
        this.accion = accionExterna;

        // capturar los menus
        if (route.snapshot.params['menu'] != null && route.snapshot.params['menu'] != undefined) {
            this.menuSeguridad = JSON.parse(route.snapshot.params['menu']) as SeguridadMenuItem;
            sessionStorage.setItem('menu-seguridad', JSON.stringify(this.menuSeguridad));
        } else {
            const ss = sessionStorage.getItem('menu-seguridad');
            if (ss !== undefined && ss != null) {
                const seguridad = JSON.parse(ss) as SeguridadMenuItem;
                this.menuSeguridad = seguridad;
                console.log(this.menuSeguridad);
            }
        }
    }

    // DARIO: para el llenado de los combos especificar la etiqueta que tendra
    get comboEtiquetaTipo() {
        return {
            LISTADO: comboEtiquetaTipo.LISTADO,
            MANTENIMIENTO: comboEtiquetaTipo.MANTENIMIENTO,
        }
    }

    // ARMAS : Titulo asignar titulo Listado
    tituloAnularWhAsignarConTitulo(tipo: number, componente: any, titulo: string, objetoBoton: ObjetoBoton[]) {
        this.tituloListadoAsignar(tipo, componente, objetoBoton);
        console.log(tipo);
        this.objetoTitulo.menuSeguridad.label = titulo;
        this.objetoTitulo.accion = 'OC/CO';

        var items = [
            { label: this.objetoTitulo.menuSeguridad.grupo },
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];


        var itemsMovil = [
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var icon = { icon: this.objetoTitulo.menuSeguridad.icon };

        this.servicioComun.setBreadIcon(icon)
        this.servicioComun.setBread(items, itemsMovil);
        this.servicioComun.setItems(this.objetoTitulo);
    }

    tituloListadoAsignarConTitulo(tipo: number, componente: any, titulo: string, objetoBoton: ObjetoBoton[]) {
        this.tituloListadoAsignar(tipo, componente, objetoBoton);
        console.log(tipo);
        this.objetoTitulo.menuSeguridad.label = titulo;
        this.objetoTitulo.accion = 'Listado';

        var items = [
            { label: this.objetoTitulo.menuSeguridad.grupo },
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];


        var itemsMovil = [
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var icon = { icon: this.objetoTitulo.menuSeguridad.icon };

        this.servicioComun.setBreadIcon(icon)
        this.servicioComun.setBread(items, itemsMovil);
        this.servicioComun.setItems(this.objetoTitulo);
    }

    // ARMAS : Titulo asignar titulo Listado
    tituloListadoAsignar(tipo: number, componente: any, objetoBoton: ObjetoBoton[]) {
        const ss = sessionStorage.getItem(ConstanteAngular.MENUSEGURIDAD);
        if (ss !== undefined && ss != null) {
            const seguridad = JSON.parse(ss) as SeguridadMenuItem;
            this.menuSeguridad = convertDateStringsToDates(seguridad);
            this.objetoTitulo.menuSeguridad = this.menuSeguridad;
        }
        this.objetoTitulo.componente = componente;
        this.objetoTitulo.tipo = tipo;

        objetoBoton.forEach(element => {
            if(element.clase != undefined && element.clase != null && element.clase != ''){
              element.clase = 'p-button-rounded p-button-raised '+element.clase;
            }else{
                element.clase = 'p-button-rounded p-button-raised botonNormal';
            }
        });

        this.objetoTitulo.objetoBoton = objetoBoton;
        this.objetoTitulo.accion = 'Listado';

        var items = [
            { label: this.objetoTitulo.menuSeguridad.grupo },
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var itemsMovil = [
            { label: this.objetoTitulo.menuSeguridad.label },
            { label: this.objetoTitulo.accion },
        ];

        var icon = { icon: this.objetoTitulo.menuSeguridad.icon };

        this.servicioComun.setBreadIcon(icon)
        this.servicioComun.setBread(items, itemsMovil);
        this.servicioComun.setItems(this.objetoTitulo);
    }

    // ARMAS: Bloqueo de la pagina
    bloquearPagina(): void {

        if (!this.blocked) {
            this.blocked = true;
        }

        this.servicioComun.setBloqueo(this.blocked);
    }

    // ARMAS: desbloquear pagina
    desbloquearPagina(): void {
        if (this.blocked) { this.blocked = false; }
        this.servicioComun.setBloqueo(this.blocked);
    }

    // ARMAS: Tipo de titulo de listado
    get tituloListadoTipo() {
        return {
            ESTANDAR: tituloListadoTipo.ESTANDAR
        }
    }

    // ARMAS: Tipo de titulo de mantenimiento
    get tituloMantenimientoTipo() {
        return {
            ESTANDAR: tituloMantenimientoTipo.ESTANDAR
        }
    }

    // ARMAS:Acciones de procesos
    get ACCIONES() {
        return {
            /*COPYINTERMEDIO: accionSolicitada.COPYINTERMEDIO,
            ADMITIR: accionSolicitada.ADMITIR,
            DESESTIMAR: accionSolicitada.DESESTIMAR,
            DENEGAR: accionSolicitada.DENEGAR,            
            WORKFLOW_SOLICIUD: accionSolicitada.WORKFLOW_SOLICIUD,            
            GUARDAR: accionSolicitada.GUARDAR,
            SALIR: accionSolicitada.SALIR,*/
            NADA: accionSolicitada.NADA,
            LISTAR: accionSolicitada.LISTAR,
            NUEVO: accionSolicitada.NUEVO,
            EDITAR: accionSolicitada.EDITAR,
            VER: accionSolicitada.VER,
            ELIMINAR: accionSolicitada.ELIMINAR,
            ANULAR: accionSolicitada.ANULAR,
            CERRAR: accionSolicitada.CERRAR,
            WORKFLOW: accionSolicitada.WORKFLOW,
            APROBAR: accionSolicitada.APROBAR,
            COPIAR: accionSolicitada.COPIAR,
            REVISAR: accionSolicitada.REVISAR,
            INVITAR: accionSolicitada.INVITAR,
            COTIZAR: accionSolicitada.COTIZAR,
            IMPRIMIR: accionSolicitada.IMPRIMIR,
            GENERAR: accionSolicitada.GENERAR,
            EDITARMOD: accionSolicitada.EDITARMOD,
            OTRA_ACCION: accionSolicitada.OTRA_ACCION
        };
    }


    //TIPOS DE AUDITORIA
    get TIPOSAUDITORIA() {
        return {
            BAS2C: tiposAuditoria.BAS2C
        }
    }

    //TIPOS DE BOTONES LISTADO
    get TIPOSBOTONES_LISTADO() {
        return {
            BAS1B: tiposBotonesListado.BAS1B,
            BAS2B: tiposBotonesListado.BAS2B,
            BAS2D: tiposBotonesListado.BAS2D,
            BAS2F: tiposBotonesListado.BAS2F,
            BAS1N: tiposBotonesListado.BAS1N,
            BAS2N: tiposBotonesListado.BAS2N,
            BASOC: tiposBotonesListado.BASOC,
            BASCT: tiposBotonesListado.BASCT,
            BASCI: tiposBotonesListado.BASCI,
            BASCH: tiposBotonesListado.BASCH,
            BASGO: tiposBotonesListado.BASGO,
            BASCO: tiposBotonesListado.BASCO,
            BAS0B: tiposBotonesListado.BAS0B,
            BAS1R: tiposBotonesListado.BAS1R,
            BAS3B: tiposBotonesListado.BAS3B,
            BASGC: tiposBotonesListado.BASGC,
            BASAN: tiposBotonesListado.BASAN,

            BOSIN: tiposBotonesListado.BOSIN,
            BOEXP: tiposBotonesListado.BOEXP,
            BOBUS: tiposBotonesListado.BOBUS
        }
    }

    //TIPOS DE BOTONES MANTENIMIENTO
    get TIPOSBOTONES_MANTENIMIENTO() {
        return {
            BAS2B: tiposBotonesMantenimiento.BAS2B,
            CH2B: tiposBotonesMantenimiento.CH2B,
            BAS21: tiposBotonesMantenimiento.BAS21,
            BAS1M: tiposBotonesMantenimiento.BAS1M
        }
    }

    //ARMAS: Restaurar filtros de pantallas
    formularioFiltrosRestaurar(filtro: any) {
        const ss = sessionStorage.getItem(ConstanteAngular.FILTROSESION);
        if (ss !== undefined && ss !== null && ss !== 'null') {
            const prefiltro = JSON.parse(ss);
            filtro = convertDateStringsToDates(prefiltro);
            return filtro;
        } else { return filtro; }
    }
    formularioFiltrosRestaurarNombre(filtro: any, nombreSession: string) {
        const ss = sessionStorage.getItem(nombreSession);
        if (ss !== undefined && ss !== null) {
            const prefiltro = JSON.parse(ss);
            filtro = convertDateStringsToDates(prefiltro);
            return filtro;
        } else { return filtro; }
    }


    getUsuarioActual() {
        const ss = sessionStorage.getItem('usuarioActual');
        if (ss !== undefined && ss != null) {
            const usuario = JSON.parse(ss) as SeguridadUsuarioActual;
            return usuario;
        }
        return new SeguridadUsuarioActual();
    }


    extraerExtension(archivo: string) {
        var res = "";
        if (archivo == null)
            return null;
        var i = archivo.lastIndexOf('.');
        if (i > 0) {
            res = archivo.substring(i + 1);
        }
        return res;
    }

    contenidoFileValidoPorTipos(base64: string, tiposPermitidos: string[]): boolean {
        var valida = false;
        var hex = this.base64ToHex(base64);
        tiposPermitidos.forEach(
            r => {
                if (this.contenidoFileValido(hex, r)) {
                    valida = true;
                }
            }
        );
        if (!valida) {
            this.mostrarMensajeAdvertencia("Solo se permiten archivos: " + tiposPermitidos);
        }
        return valida;
    }

    contenidoFileValido(hex: string, tipo: string): boolean {
        var magicNumber = '';
        switch (tipo) {
            case "PDF":
                magicNumber = "25504446";
                break;
            case "BMP":
                magicNumber = "424D";
                break;
            case "JPG":
                magicNumber = "FFD8";
                break;
            case "PNG":
                magicNumber = "89504E47";
                break;
            case "DOC":
                magicNumber = "D0CF11E0A1B11AE1";
                break;
            case "XLS":
                magicNumber = "D0CF11E0A1B11AE1";
                break;
            case "PPT":
                magicNumber = "D0CF11E0A1B11AE1";
                break;
            case "DOCX":
                magicNumber = "504B0304";
                break;
            case "XLSX":
                magicNumber = "504B0304";
                break;
            case "PPTX":
                magicNumber = "504B0304";
                break;
            default:
                break;
        }
        if (hex.startsWith(magicNumber)) {
            return true;
        }
        return false;
    }

    base64ToHex(str) {
        const raw = atob(str);
        let result = '';
        for (let i = 0; i < raw.length; i++) {
            const hex = raw.charCodeAt(i).toString(16);
            result += (hex.length === 2 ? hex : '0' + hex);
        }
        return result.toUpperCase();
    }


    tieneAcceso(aplicacioncodigo: string, grupo: string, concepto: string): Promise<boolean> {
        return this.servicioComun.tieneAcceso(aplicacioncodigo, grupo, concepto).then(
            permiso => {
                return this.flagABoolean(permiso.codigo);
            }
        );
    }

    reemplazarRuta(rutaarchivo: string, caracteraEncontrar: string, caracteraReemplazar: string): string {
        if (!this.estaVacio(rutaarchivo)) {
            const result = this.replaceAll(rutaarchivo, caracteraEncontrar, caracteraReemplazar);
            return result;
        }
    }

    replaceAll(str, find, replace) {
        return str.replace(new RegExp(find, 'g'), replace);
    }

    f_nombrearchivo(par_rutaadjunto: string, par_criterio: string) {
        return par_rutaadjunto;
    }
    f_date(par_date: Date) {
        if (par_date == null) {
            return '';
        }
        return this.formatDate(par_date);
    }

    f_datetime(par_date: Date) {
        if (par_date == null) {
            return '';
        }
        return this.formatDatetime(par_date);
    }

    padTo2Digits(num) {
        return num.toString().padStart(2, '0');
    }

    formatDatetime(date: Date) {
        return [
            this.padTo2Digits(date.getDate()),
            this.padTo2Digits(date.getMonth() + 1),
            date.getFullYear(),
        ].join('/') + ' ' +

            [
                this.padTo2Digits(date.getHours()),
                this.padTo2Digits(date.getMinutes()),
                this.padTo2Digits(date.getSeconds()),
            ].join(':');
    }

    formatDate(date: Date) {
        return [
            this.padTo2Digits(date.getDate()),
            this.padTo2Digits(date.getMonth() + 1),
            date.getFullYear(),
        ].join('/');
    }

    diaDeSemanaLetras(fecha: Date) {
        if (fecha == null || fecha == undefined) {
            return '';
        }
        var dia = fecha.getDay().toString();
        switch (dia) {
            case '0': return 'DOMINGO';
            case '1': return 'LUNES';
            case '2': return 'MARTES';
            case '3': return 'MIERCOLES';
            case '4': return 'JUEVES';
            case '5': return 'VIERNES';
            case '6': return 'SABADO';
            default:
                '';
        }
    }
}
