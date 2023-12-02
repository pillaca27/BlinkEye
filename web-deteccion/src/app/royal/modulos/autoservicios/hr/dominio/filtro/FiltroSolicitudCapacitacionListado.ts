import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSolicitudCapacitacionListado {
    paginacion: DominioPaginacion;
    compania: string;
    unidadNegocio: string;
    sucursal: string;
    centroCosto: string;
    fechaSolicitudDesde: Date;
    fechaSolicitudHasta: Date;
    estado: string;
    capacitacion: string;
    fechaDesde: Date;
    fechaHasta: Date;
    curso: number;
    cursoTipo: string;
    tipoCapacitacion: string;
    centroCapacitacion: number;
    anioPlan: number;
    empleado: number;
    codigoCargo: number;
    unidadOperativa: string;
    tipoPlanilla: string;
    estadoEmpleado: string;

    auxCompaniaNombre: string;
    auxEmpleadoNombre: string;
    auxUnidadnegocioNombre: string;
    auxTipocapacitacionNombre: string;
    auxCodigoCargoNombre: string;
    auxSucursalNombre: string;
    auxCursoNombre: string;
    auxUnidadOperativaNombre: string;
    auxCentroCostoNombre: string;
    auxCursoTipoNombre: string
    auxCentroCapacitacionNombre: string;
    auxEstadoEmpleadoNombre: string;
    auxEstadoNombre: string;
    auxTipoPlanillaNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}