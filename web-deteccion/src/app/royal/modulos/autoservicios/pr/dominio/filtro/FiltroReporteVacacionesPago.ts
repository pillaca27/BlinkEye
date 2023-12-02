import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class FiltroReporteVacacionesPago extends DominioTransaccion {
    compania: string;
    tipoPlanilla: string;
    periodo: Date;
    anio: number;
    mes: number;
    centroCosto: string;
    sucursal: string;
    unidadNegocio: string;
    auxCompaniaNombre: string;
    auxTipoPlanillaNombre: string;
    auxCentroCostoNombre: string;
    auxSucursalNombre: string;
    auxUnidadNegocioNombre: string;
    reporteBase64: string;
}