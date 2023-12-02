import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunEmpleado {
    empleado: number;
    nombreempleado: string;
    centrocosto: string;
    centrocostodescripcion;
    desde: Date;
    hasta: Date;
    compania: string;
	tipoplanilla: string;
	
    paginacion: DominioPaginacion;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}