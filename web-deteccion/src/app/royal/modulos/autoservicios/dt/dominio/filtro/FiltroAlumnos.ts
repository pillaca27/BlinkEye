import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroAlumnos {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    idAlumno : number;
    nombre : string;
    apellido : string;
    email : string;
    
}
