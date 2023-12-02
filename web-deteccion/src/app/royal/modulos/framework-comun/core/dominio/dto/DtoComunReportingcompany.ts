import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunReportingcompany extends DominioTransaccion {
    constructor(){
        super();
        this.accion=''
        this.companiacodigoDescri=''
    }

    // pk
    companiacodigo : string;
    companyowner : string;
    percentage : number;
    accion:string
    companiacodigoDescri:string

    // columnas
    
}
