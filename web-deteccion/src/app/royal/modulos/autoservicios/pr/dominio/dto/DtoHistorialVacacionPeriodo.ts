export class DtoHistorialVacacionPeriodo {
    empleado: number;
    compania: string;
    periodo: number;
    rangoAnios: string;
    mesAniversario: string;
    diasDerecho: number;
    diasPendientePeriodoAnterior: number;
    diasTotalUtilizado: number;
    diasPendientes: number;
    diasPendientesReales: number;
    nroSolicitud: number;
}
export class DtoHistorialVacacionPeriodoDetalle {
    desde: Date;
    hasta: Date;
    dias: number;
    diasutiles: number;
	utilizacion: string;
}