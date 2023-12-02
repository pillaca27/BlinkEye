import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
 

export class DtoComunAcCostcenterafe extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    costcenter : string;
    afe : string;
    status : string;
    lastuser : string;
    lastdate : Date;
    accion:string
    descripcion:string

    // columnas
    
}
