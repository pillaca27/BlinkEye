import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroConsultaCts {
    periodoId: string;
    paginacion: DominioPaginacion;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}