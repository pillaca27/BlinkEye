import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroAuditoriaProcesos {
    paginacion: DominioPaginacion;

    compania: string;
    planilla: string;
    periodo: string;
    empleado: number;
    faltantes: string;
    func: string;

    auxCompaniaNombre: string;
    auxPlanillaNombre: string;
    auxPeriodoNombre: string;
    auxEmpleadoNombre: string;
    auxFuncNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}