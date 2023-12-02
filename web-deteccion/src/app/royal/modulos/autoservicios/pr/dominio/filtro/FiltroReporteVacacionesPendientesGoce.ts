export class FiltroReporteVacacionesPendientesGoce {
    compania: string;
    tipoPlanilla: string;
    centroCosto: string;
    sucursal: string;
    unidadNegocio: string;
    auxCompaniaNombre: string;
    auxTipoPlanillaNombre: string;
    auxCentroCostoNombre: string;
    auxSucursalNombre: string;
    auxUnidadNegocioNombre: string;
    reporte: DtoReporteVacacionesPendientesGoce;
    constructor() {
        this.reporte = new DtoReporteVacacionesPendientesGoce();
    }
}
export class DtoReporteVacacionesPendientesGoce {
    anios: number[];
    datos: DtoReporteVacacionesPendientesGoceFila[];
    constructor() {
        this.anios = [];
        this.datos = [];
    }
}
export class DtoReporteVacacionesPendientesGoceFila {
    codigo: number;
    nombre: string;
    fechaIngreso: string;
    codmanual: string;
    pendientesPorAnio: number[];
    total: number;
    flagMostrarCodigoNombre: string;
    flagFilaTotales: string;
    constructor() {
        this.pendientesPorAnio = [];
    }
}