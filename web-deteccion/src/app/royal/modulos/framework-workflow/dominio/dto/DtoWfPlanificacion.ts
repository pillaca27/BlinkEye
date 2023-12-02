import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoWfPlanificacion extends DominioTransaccion {
    transaccion: number;
    lstEtapas: DtoWfPlanificacionEtapa[] = [];
    config: DtoWfPlanificacionConfiguracion;
    constructor() {
        super();
        this.lstEtapas = [];
        this.config = new DtoWfPlanificacionConfiguracion();
    }
}
export class DtoWfPlanificacionConfiguracion {
    puedeVer: string;
    puedeEditar: string;
    puedeActualizar: string;
}
export class DtoWfPlanificacionEtapa {
    nivel: number;
    nombre: string;
    inicio: Date;
    fin: Date;
    planificacionid: number;
}