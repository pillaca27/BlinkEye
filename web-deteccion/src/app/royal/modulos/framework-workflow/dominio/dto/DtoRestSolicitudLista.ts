import { DtoSolicitudes } from './DtoSolicitudes';

export class DtoRestSolicitudLista {

    constructor() {
        this.listaSolicitudes = [];
    }

    accion: string;
    listaSolicitudes: DtoSolicitudes[];
}
