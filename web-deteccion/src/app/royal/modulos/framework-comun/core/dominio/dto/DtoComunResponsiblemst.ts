import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunResponsiblemst extends DominioTransaccion {
    responsible : string;
    nextresponsible : string;
    localname : string;
    englishname : string;
    vendor : number;
    responsabilitylevel : number;
    status : string;
    lastuser : string;
    lastdate : Date;
    authorizedamount : number;
    amountinvoices : number;
    amountrequisitions : number;
    amountadvances : number;
    amountothers01 : number;
    amountothers02 : number;
    amountothers03 : number;
    
}