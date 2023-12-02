import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroConsultaAniversario {
    empleado: number;
    companiasocio: string;
    mes: number;
    dia: number;
    signo: string;
    anios: number;
    paginacion: DominioPaginacion;
    auxEmpleadoNombre: string;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

}