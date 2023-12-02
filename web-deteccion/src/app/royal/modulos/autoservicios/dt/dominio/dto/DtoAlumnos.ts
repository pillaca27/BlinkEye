import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoAlumnos extends DominioTransaccion {
    constructor(){
        super();
    }

    idAlumno : number;
    nombre : string;
    apellido : string;
    email : string;
    
}
