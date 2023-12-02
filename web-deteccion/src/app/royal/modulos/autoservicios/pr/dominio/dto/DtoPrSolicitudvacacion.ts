import { DtoHistorialVacacionPeriodo } from "./DtoHistorialVacacionPeriodo";
import { DtoVacacionesDiasInformacion } from "./DtoVacacionesDiasInformacion";
import { DtoVacacionesHistorico } from './DtoVacacionesHistorico';

export class DtoPrSolicitudvacacion {
    numerosolicitud: number;
    fecharegistro: Date;
    empleado: number;
    fechainicio: Date;
    fechafin: Date;
    dias: number;
    estado: string;
    comentario: string;
    ultimafechaactualizacion: Date;
    ultimousuario: string;
    companiasocio: string;
    uuid: string;
    transaccionId;
    transaccionUUID: string;
    diasutiles: number;
    utilizacion: string;
    periodo: number;

    //Auxiliares
    auxEmpleadoNombre: string;
    auxDiasPendientesPeriodoActual: number;
    auxDiasPendientesPeriodoAnterior: number;
    axuEmpleadoFechaIngreso: Date;
    axuEmpleadoCargoNombre: string;
    auxEstadoNombre: string;

    historial: DtoHistorialVacacionPeriodo[];
    periodos: DtoHistorialVacacionPeriodo[];
    historico: DtoVacacionesHistorico[];

    diasInfo: DtoVacacionesDiasInformacion;
    auxDiasDisponibles: number;
    auxDiasUtiles: number;

    constructor() {
        this.historial = [];
        this.diasInfo = new DtoVacacionesDiasInformacion();
        this.historico = [];
        this.periodos = [];
    }
}