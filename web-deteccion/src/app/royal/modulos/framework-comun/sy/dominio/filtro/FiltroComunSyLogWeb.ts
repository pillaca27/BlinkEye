import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunSyLogWeb {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    fecha: Date;
    desde: Date;
    hasta: Date;
    estado: string;
    tipo: string;
    aplicacion: string;
    paginacion: DominioPaginacion;
}