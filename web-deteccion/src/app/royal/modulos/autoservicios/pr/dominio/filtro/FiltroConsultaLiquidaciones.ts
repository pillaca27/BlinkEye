import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroConsultaLiquidaciones {
    empleado: number;
    companiasocio: string;
    desde: Date;
    hasta: Date;
    paginacion: DominioPaginacion;
    auxEmpleadoNombre: string;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

}