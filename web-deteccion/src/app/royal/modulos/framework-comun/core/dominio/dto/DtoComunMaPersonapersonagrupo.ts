import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
 
 

export class DtoComunMaPersonapersonagrupo extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    persona : number;
    personagrupo : string;
    placavehiculo : string;
    personarelacionada : number;
    acciones:string

    // columnas
    
}
