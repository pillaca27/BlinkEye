import { DtoTransaccionCorreoListado } from "./DtoTransaccionCorreoListado";

export class DtoTransaccionCorreoListadoHeader {
    lst: DtoTransaccionCorreoListado[];
    reportePorNiveles: string;
    aplicacion: string;
    constructor() {
        this.lst = [];
    }
}
