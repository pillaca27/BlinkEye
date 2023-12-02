import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoClases extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    idClase : number;
    nombre : string;
    fechaHoraInicio : Date;
    fechaHoraFin : Date;
    idDocente : number;

    // columnas
    
}
