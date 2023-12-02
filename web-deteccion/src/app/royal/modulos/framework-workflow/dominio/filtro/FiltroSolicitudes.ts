import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroSolicitudes {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    Tipo: number;

    companiaSocio: string;
    aplicacion: string;
    proceso: string;
    numeroProceso: number;
    procesoInterno: number;
    unidadReplicacion: string;
    estado: string;
    documento: string;
    fechaDesde: Date;
    fechaHasta: Date;
    idPersonaSolicitante: number;
    idPersonaSolicitante2: number;
    idPersonaActual: number;
    nombreSolicitante: string;
    nivel: number;
    plan: number;
    paginacion: DominioPaginacion;
    fechaSolicitud: Date;
    concepto: string;
    aprobar1: string;
    aprobar2: string;
    referencia: string;
    area: string;
    montodesde: number;
    montohasta: number;
    estadoProceso: string;

}
