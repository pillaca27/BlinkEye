import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunHrCentroestudiosCarrera {
    centro: number;
    descripcion: string;
    regimen: string;
    tipoinstitucion: string;
    paginacion: DominioPaginacion;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}