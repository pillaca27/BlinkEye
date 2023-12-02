import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunAcCostcentermst {
  constructor() {
    this.paginacion = new DominioPaginacion();
    this.principales = 'N';
  }
  paginacion: DominioPaginacion;

  costcenter: string;
  localname: string;

  idCompania: string;
  idUsuario: string;
  idAplicacion: string;
  idPersona: number;
  valor1: string;
  status: string;

  principales: string;

  englishname: string;
  costcenterclasification: string;
  costcentergroup: string;
  costcentersubgroup: string;
  costcenterrelation: string;
  responsible: string;
  expensefinanceflag: string;
  expenseadministrativeflag: string;
  expensesalesflag: string;
  expenseproductionflag: string;
  costcenternext: string;
  vendor: number;
  amountinvoices: number;
  amountrequisitions: number;
  amountadvances: number;
  amountothers01: number;
  amountothers02: number;
  amountothers03: number;
  vendorsignfile: string;
  account: string;

  lastuser: string;
  lastdate: Date;
  costproductionlevel: string;
  accountdestination: string;
  costcentertype: string;
  incomeflag: string;
  accountinflation: string;
  expensedirectflag: string;
  expensefixedflag: string;
  sucursal: string;
  internalnumber: string;
  racionperfil: string;
  racionperfilmantenimiento: string;
  address: string;
  multicompanyflag: string;
  cpautomaticinvoflag: string;
  cpautomaticrequflag: string;
  tipoexportar: string
  
}