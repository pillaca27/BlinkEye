import { FiltroTabla } from './FiltroTabla';
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroTransaccion extends FiltroTabla {
    constructor() {
        super();
        this.paginacion = new DominioPaginacion();
    }
    transaccionId: number;
    desde: Date;
    hasta: Date;
    proceso: string;
    aplicacion: string;
    referencia: string;
    paginacion: DominioPaginacion;
    compania: string;
    area: string;
    montodesde: number;
    montohasta: number;
    estadoProceso: string;
    idPersonaSolicitante2: number;
    nombreSolicitante: string;
    solicitante: number;
}