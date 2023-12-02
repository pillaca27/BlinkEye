import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoMicrosuenos extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    idMicrosueno : number;
    idAlumno : number;
    idClase : number;
    fechaHora : Date;
    duracion : number;

    // columnas
    
}
