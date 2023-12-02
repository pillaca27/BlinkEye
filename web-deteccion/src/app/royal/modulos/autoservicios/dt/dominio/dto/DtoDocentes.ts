import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";


export class DtoDocentes extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    idDocente : number;
    nombre : string;
    apellido : string;
    email : string;

    // columnas
    
}
