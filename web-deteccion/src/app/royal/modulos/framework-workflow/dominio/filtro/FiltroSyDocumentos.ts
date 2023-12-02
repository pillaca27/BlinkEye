import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroSyDocumentos {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

    paginacion: DominioPaginacion;
    aplicacion: string;
    proceso: string;
    codigo: string;
    nombre: string;
}