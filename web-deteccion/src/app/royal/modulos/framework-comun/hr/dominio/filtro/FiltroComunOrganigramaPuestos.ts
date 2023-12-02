import { DominioPaginacion } from "../../../../../framework/modelo/generico/DominioPaginacion";

export class FiltroComunOrganigramaPuestos {
    companiaSocio: string;
    unidadNegocio: string;
    unidadOriginal: number;
    puestoOriginal: number;
    anio: number;
    secuencia: number;
    evento: string;
    codigo: number;
    nombre: string;
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
}