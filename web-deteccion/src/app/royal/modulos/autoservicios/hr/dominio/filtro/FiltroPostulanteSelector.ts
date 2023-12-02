import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroPostulanteSelector {
    paginacion: DominioPaginacion;
    postulante: number;
    nombre: string;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}