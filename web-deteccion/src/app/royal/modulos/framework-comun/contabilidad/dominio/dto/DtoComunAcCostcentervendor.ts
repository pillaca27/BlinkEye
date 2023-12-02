import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
 

export class DtoComunAcCostcentervendor extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    costcenter : string;
    vendor : number;
    status : string;
    lastuser : string;
    lastdate : Date;
    accion:string
    descripcion:string

    // columnas
    
}
