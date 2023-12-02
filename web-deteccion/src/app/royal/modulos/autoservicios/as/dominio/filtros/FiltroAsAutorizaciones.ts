import { DominioPaginacion } from "../../../../../framework/modelo/generico/DominioPaginacion";

export class FiltroAsAutorizaciones{
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    fechadesde: Date;
    locacion: string;
    fechaHasta: Date;
    flagfiltroperiodo: string;
    sid: number;
    isAdmin: string;
    periodo: string;
    conceptoacceso: string;
    estado: string;
    empleado: number;
}