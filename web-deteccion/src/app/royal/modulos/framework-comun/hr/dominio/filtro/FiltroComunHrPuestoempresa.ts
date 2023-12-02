import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunHrPuestoempresa {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    codigopuesto: number;
    descripcion: string;
    estado: string;
    tipopuesto: string;

    companyowner: string;
    unidadnegocio: string;
    anio: number;
    secuencia: number;
    evento: string;
}