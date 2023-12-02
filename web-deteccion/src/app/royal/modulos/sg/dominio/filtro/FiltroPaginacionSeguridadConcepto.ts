import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
 
export class FiltroPaginacionSeguridadConcepto {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    codigoAplicacion : string
    nombreapp: string
    grupo : string
    tipoexportar:string;
 
     
}