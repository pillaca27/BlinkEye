import { Subscriber } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';

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

export class BaseComponent {
    mensajeResultado: string;
    constructor(
        protected messageService: MessageService,
        ) {
    }

    accion: accionSolicitada;
    blocked: boolean = false;

    transaccionResultado(dto: any): boolean {
        this.mensajeResultado = 'Acción realizada con éxito.';
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
                this.mostrarMensajeExito('Acción realizada con éxito.');
                return true;
            }
        }
        return false;
    }

   bloquearPagina(): void {
        if (!this.blocked) {
            this.blocked = true;
        }
    }

    desbloquearPagina(): void {
        if (this.blocked) { this.blocked = false; }
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
    esFechaVacia(fecha: Date): boolean {
        if (fecha == null) {
            return true;
        }
        if (fecha == undefined) {
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
        if (numero === '') {
            return true;
        }
        return false;
    }

    mostrarMensajeExito(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'success');
    }

    mostrarMensaje(mensaje: string, tipo: string): void {
        this.messageService.clear();
        this.messageService.add({ severity: tipo, summary: 'Mensaje', detail: mensaje });
    }

    get ACCIONES() {
        return {
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

    mostrarMensajeAdvertencia(mensaje: string): void {
        this.mostrarMensaje(mensaje, 'warn');
    }

}
