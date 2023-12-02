import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunDepartmentmst extends DominioTransaccion {

    constructor(){
        super();
        this.contabilidadcheck=false
        this.logisticacheck=false
    }

    
    department : string;
    description : string;
    accountavailableflag : string;
    warehouseavailableflag : string;
    application : string;
    status : string;
    lastuser : string;
    lastdate : Date;
    codigosbs : string;
    codigosbsSup : string;

 
    contabilidadcheck?:boolean
    logisticacheck?:boolean
}