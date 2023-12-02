import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcCostcenterdestination extends DominioTransaccion {
    costcenterdestination : string;
    localname : string;
    englishname : string;
    status : string;
    lastuser : string;
    lastdate : Date;
}