import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroPersonalReclutamientoSelector {
    paginacion: DominioPaginacion;
    compania: string;
    tipoPlanilla: string;
    empleado: number;
    nombre: string; 

    auxCompaniaNombre: string;
    auxTipoPlanillaNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}