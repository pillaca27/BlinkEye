export class FiltroReporteVacacionesResumen {
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
    reporte: any[] = [];

    constructor() {
        this.reporte = [];
    }
}