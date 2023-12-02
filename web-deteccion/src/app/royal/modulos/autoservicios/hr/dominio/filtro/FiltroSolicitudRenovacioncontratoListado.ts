import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSolicitudRenovacioncontratoListado {

    paginacion: DominioPaginacion;

    empleado: number;
    estadoContrato: string;
    estadoSolicitud: string;
    tipoContrato: string;
    tipoPlanilla: string;
    inicioContrato: Date;
    finContrato: Date;

    auxEmpleadoNombre: string;
    auxEstadoContratoNombre: string;
    auxEstadoSolicitudNombre: string;
    auxTipoContratoNombre: string;
    auxTipoPlanillaNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}