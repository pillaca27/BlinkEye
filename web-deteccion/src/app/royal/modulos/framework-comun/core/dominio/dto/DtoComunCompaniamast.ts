import { DtoComunCompanyowner } from './DtoComunCompanyowner';
import { DtoComunReportingcompany } from './DtoComunReportingcompany';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunCompaniamast extends DominioTransaccion{

    constructor(){
        super();
        this.check1=false
        this.check2=false
        this.check3=false
        this.check4=false
        this.check5=false
        this.detalle1=[]
        this.detalle2=[]
    }


    companiacodigo : string;
    descripcioncorta : string;
    descripcionlarga : string;
    direccioncomun : string;
    direccionadicional : string;
    fechafundacion : Date;
    telefono1 : string;
    telefono2 : string;
    telefono3 : string;
    fax1 : string;
    fax2 : string;
    documentofiscal : string;
    propietariopordefecto : string;
    descripcionextranjera : string;
    monedapordefecto : string;
    tipocompania : string;
    factorrvalidacion : string;
    afectoigvflag : string;
    creditofiscalfactorflag : string;
    cuentaprovisionsbsflag : string;
    logofile : string;
    persona : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    representantelegal : string;
    paginaweb : string;
    afectoretencionigvflag : string;
    grupo : string;
    certificadoinscripcion : string;
    detraccioncuentabancaria : string;
    representantelegaldocumento : string;
    regimenlaboralrtps : string;
    representantelegalcontrato : number;
    numeroresolucion : string;
    numerodesignacion : string;
    codigorepresentantelegal : number;
    codigoanterior : string;
    plancontable : string;
    pliego : string;
    fechavigenciapolizafin : Date;
    fechavigenciapolizainicio : Date;
    fechasuscripcionpoliza : Date;
    numeropoliza : string;
    unidadejecutora : string;
    servwebusuario : string;
    servwebclave : string;
    email : string;








 
    codestablesunat : string;
    personaDescri:string
    detalle1:DtoComunCompanyowner[]
    detalle2:DtoComunReportingcompany[]
    check1:boolean
    check2:boolean
    check3:boolean
    check4:boolean
    check5:boolean
}