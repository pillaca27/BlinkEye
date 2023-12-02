import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcAfecompany extends DominioTransaccion {
    afe : string;
    companyowner : string;
    status : string;
    lastuser : string;
    lastdate : Date;
}