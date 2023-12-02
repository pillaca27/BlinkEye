import { DtoFlujoSolicitud } from './DtoFlujoSolicitud';

export class DtoFlujoEjecutar {
    accion: string;
    subaccion: string;
    listaSolicitudes: DtoFlujoSolicitud[];
}