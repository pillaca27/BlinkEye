import { DtoComunAcCostcentervendor } from '@framework-comun/contabilidad/dominio/dto/DtoComunAcCostcentervendor';
import { DtoComunAcCostcenterdestvalid } from './DtoComunAcCostcenterdestvalid';
 
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
import { DtoComunAcCostcenteraccount } from './DtoComunAcCostcenteraccount';
import { DtoComunAcCostcenterafe } from './DtoComunAcCostcenterafe';

export class DtoComunAcCostcentermst extends DominioTransaccion{
    constructor(){
        super();
        this.check01=false
        this.check02=false
        this.check03=false
        this.check04=false
        this.check05=false
        this.lstDetalle1=[]
        this.lstDetalle2=[]
        this.lstDetalle3=[]
        this.lstDetalle4=[]
    }
    costcenter : string;
    localname : string;
    englishname : string;
    costcenterclasification : string;
    costcentergroup : string;
    costcentersubgroup : string;
    costcenterrelation : string;
    responsible : string;
    expensefinanceflag : string;
    expenseadministrativeflag : string;
    expensesalesflag : string;
    expenseproductionflag : string;
    costcenternext : string;
    vendor : number;
    amountinvoices : number;
    amountrequisitions : number;
    amountadvances : number;
    amountothers01 : number;
    amountothers02 : number;
    amountothers03 : number;
    vendorsignfile : string;
    account : string;
    status : string;
    lastuser : string;
    lastdate : Date;
    incomeflag : string;
    accountinflation : string;
    expensefixedflag : string;
    expensedirectflag : string;
    sucursal : string;
    internalnumber : string;

 


    // pk
    costcenternextDescri:string
    vendorDescri:string
    amountinvoicesDescri:string
    accountDescri : string;
    costproductionlevel : string;
    accountdestination : string;
    accountdestinationDescri : string;
    costcentertype : string;
    racionperfil : string;
    racionperfilmantenimiento : string;
    address : string;
    multicompanyflag : string;
    cpautomaticinvoflag : string;
    cpautomaticrequflag : string;
    check01:boolean
    check02:boolean
    check03:boolean
    check04:boolean
    check05:boolean
    grupo:string
    subgrupo:string
    auxParametro1:string
    auxParametro2:string
    lstDetalle1:DtoComunAcCostcenteraccount[]
    lstDetalle2:DtoComunAcCostcentervendor[]
    lstDetalle3:DtoComunAcCostcenterdestvalid[]
    lstDetalle4:DtoComunAcCostcenterafe[]
    // columnas
    
}