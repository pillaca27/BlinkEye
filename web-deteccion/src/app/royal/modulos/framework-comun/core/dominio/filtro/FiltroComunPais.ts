import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
export class FiltroComunPais {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    pais: string;
    estado: string;
    nombre: string;


     
    descripcioncorta : string;
    descripcionlarga : string;
    descripcionextranjera : string;
 
    ultimousuario : string;
    ultimafechamodif : Date;
    
    nacionalidad : string;
    codigortps : string;
}