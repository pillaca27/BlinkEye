export class DtoConsultaBoletaCabecera {
    companiaSocioId: string;
    empleadoId: number;

    periodoId: string;
    periodoNombre: string;

    tipoProcesoId: string;
    tipoProcesoNombre: string;

    tipoPlanillaId: string;
    tipoPlanillaNombre: string;

    totalIngresos: number;
    totalEgresos: number;
    totalNeto: number;
    totalPatronales: number;
}

export class DtoConsultaBoletaDetalle {
    conceptoTipoIn: string;
    conceptoTipoDe: string;
    conceptoTipoPa: string;

    conceptoIdIn: string;
    conceptoNombreIn: string;
    conceptoCantidadIn: number;
    conceptoMontoIn: number;

    conceptoIdDe: string;
    conceptoNombreDe: string;
    conceptoCantidadDe: number;
    conceptoMontoDe: number;

    conceptoIdPa: string;
    conceptoNombrePa: string;
    conceptoCantidadPa: number;
    conceptoMontoPa: number;

}