import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSolicitudVacacionListado {
    paginacion: DominioPaginacion;
    empleado: number;
    fechaSolicitudDesde: Date;
    fechaSolicitudHasta: Date;
    estado: string;

    auxEmpleadoNombre: string;
    auxEstadoNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}