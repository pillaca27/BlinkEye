import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcCostcenteraccount  extends DominioTransaccion  {
    constructor(){
        super();
        this.accion=''
    }
    
    costcenter : string;
    account : string;
    status : string; 
    lastuser : string;
    lastdate : Date;
    descripcion:string
    accion:string

    

   
 
}