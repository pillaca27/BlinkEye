import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunCompanyowner  extends DominioTransaccion {
    constructor(){
        super();
        this.accion=''
    }

    companyowner : string;
    description : string;
    englishdescription : string;
    percentage : number;
    company : string;
    owner : string;
    changerate : number;
    lastuser : string;
    lastdate : Date;

 
    accion:string
}