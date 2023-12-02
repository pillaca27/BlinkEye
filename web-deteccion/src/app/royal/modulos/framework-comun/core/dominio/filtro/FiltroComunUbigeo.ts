import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class FiltroComunUbigeo{

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    pais: string;
    departamento: string;
    provincia: string;
    zonapostal: string;
}