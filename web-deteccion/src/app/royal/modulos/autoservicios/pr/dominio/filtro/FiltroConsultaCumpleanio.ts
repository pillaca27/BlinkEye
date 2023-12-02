import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroConsultaCumpleanio {
    empleado: number;
    companiasocio: string;
    mes: number;
    dia: number;
    paginacion: DominioPaginacion;
    auxEmpleadoNombre: string;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

}