import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class DtoFiltroApi {
    api: number;
    ruta: string;

    paginacion: DominioPaginacion;

    ROWNUM_: number;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}