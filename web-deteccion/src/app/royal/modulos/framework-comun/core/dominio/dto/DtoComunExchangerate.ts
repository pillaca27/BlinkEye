import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunExchangerate extends DominioTransaccion {
    period : string;
    rate : number;
    monthcloserate : number;
    monthbuyrate : number;
    monthsalerate : number;
    gainandlossrate : number;
    inflationfactor : number;
    inflationfactoracumulated : number;
    sbsrate : number;
    lastuser : string;
    lastdate : Date;
    status : string;
}