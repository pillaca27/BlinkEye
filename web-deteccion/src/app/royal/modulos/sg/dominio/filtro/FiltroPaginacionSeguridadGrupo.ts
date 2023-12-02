import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion'; 

export class FiltroPaginacionSeguridadGrupo {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    codigoAplicacion : string
    tipoexportar:string;
}