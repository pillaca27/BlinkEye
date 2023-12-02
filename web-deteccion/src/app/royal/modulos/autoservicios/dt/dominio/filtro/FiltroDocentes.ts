import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroDocentes {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    idDocente : number;
    nombre : string;
    apellido : string;
    email : string;
    
}
