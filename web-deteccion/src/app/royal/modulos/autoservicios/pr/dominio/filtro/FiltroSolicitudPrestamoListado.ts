import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSolicitudPrestamoListado {
    paginacion: DominioPaginacion;
    compania: string;
    unidadNegocio: string;
    empleado: number;
    tipoPlanilla: string;
    tipoPrestamo: string;
    fechaSolicitudDesde: Date;
    fechaSolicitudHasta: Date;
    fechaAprobacionDesde: Date;
    fechaAprobacionHasta: Date;
    estado: string;

    auxCompaniaNombre: string;
    auxUnidadNegocioNombre: string;
    auxEmpleadoNombre: string;
    auxTipoPlanillaNombre: string;
    auxTipoPrestamoNombre: string;
    auxEstadoNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}