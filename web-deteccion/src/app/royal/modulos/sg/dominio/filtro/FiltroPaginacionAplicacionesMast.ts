import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

 
export class FiltroPaginacionAplicacionesMast {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    estado : string
}