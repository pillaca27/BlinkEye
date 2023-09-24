import { DominioPaginacion } from './../../../../app/util/DominioPaginacion';

export class FiltroHrCapacitacion {
    capacitacion: string;
    companyOwner: string;
    fechaInicio: Date;
    fechaFin: Date;
    estado: string;

    paginacion: DominioPaginacion=new DominioPaginacion();
}