import { DtlComunObligaciones } from './../lista/DtlComunObligaciones';
import { DtoComunWhContratodetalle } from './DtoComunWhContratodetalle';
import { DtoComunWhContratovencimiento } from './DtoComunWhContratovencimiento';
import { DtoComunWhContratorequisicion } from './DtoComunWhContratorequisicion';
import { DtoComunWhContratopoliza } from './DtoComunWhContratopoliza';
import { DtoComunWhContratomodificacion } from './DtoComunWhContratomodificacion';
import { DtoComunSyDocumentoanexos } from './../../../adjuntos/adjuntos-simple/dominio/DtoComunSyDocumentoanexos';
 
import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
import { DtlComunWhRequisiciones } from '../lista/DtlComunWhRequisiciones';
 
 

export class DtoComunWhContrato extends DominioTransaccion {
    constructor(){
        super();
        this.lstDetalleContratos=[]
        this.montototal=0
        this.montonoafecto=0
        this.montopagado=0
        this.montodisponible=0
        this.montoimpuestos=0
        this.montoimponible=0
        this.enviadoflag='N'
        this.enviadoflag_checked=false
        this.accion=''
        this.fechacartafianza01=null
        this.fechacartafianza02=null
        this.fechatopegeneracion=null
        this.fecharenovacion=null
        this.fechacierre=null
        this.fecharevision=null
        this.dw_detail=[]
        this.lstContratoRequisicion=[]
        this.lstContratoVencimientos=[]
        this.lstDocumentos=[]
        this.lstContratosRelacionados=[]
        this.lstRequerimientosOrigen=[]
        this.lstObligaciones=[]
        this.lstContratoMarco=[]
        this.lstAdendas=[]
        this.lstContratoModificaciones=[]
        this.lstContratoMarcoEjecutado=[]
        this.accionAdenda=false
    }

    // pk
    companiasocio : string;
    numerocontrato : string;
    documentointerno : string;
    unidadnegocio : string;
    numerocontratorelacionado : string;
    proveedor : number;
    descripcion : string;
    formadepago : string;
    fechadocumento : Date;
    fechavalidezdesde : Date;
    fechavalidezhasta : Date;
    fechacierre : Date;
    fechapreparacion : Date;
    fechaaprobacion : Date;
    fechatopegeneracion : Date;
    fechacartafianza01 : Date;
    fechacartafianza02 : Date;
    fecharenovacion : Date;
    flujodecaja : string;
    monedadocumento : string;
    montoimponible : number;
    montoimpuestos : number;
    montototal : number;
    montopagado : number;
    preparadapor : number;
    aprobadapor : number;
    comentarios : string;
    razonrechazo : string;
    clasificacion : string;
    tipoprogramacion : string;
    condicionesperiodicidadflag : string;
    condicionesperiodicidadnumero : number;
    almacencodigo : string;
    contratoresponsable : string;
    contratotipo : string;
    proveedorpaginaweb : string;
    gastosadicionalesflag : string;
    estado : string;
    estado_descri:string
    ultimousuario : string;
    ultimafechamodif : Date;
    proveedorcontacto : string;
    contratoventaflag : string;
    contratomontozeroflag : string;
    responsablecodigo : string;
    revisadapor : number;
    fecharevision : Date;
    montonoafecto : number;
    montototalcontratopadre : number;
    enviadoflag : string;
    enviadoflag_checked:boolean
    reportarppflag : string;
    noreportableflag : string;
    vercontratovencidoflag : string;
    formatoadministrador : string;
    formatosupervisor : string;
    descripcionformapago : string;
    descripcionplazocontrato : string;
    antecedentes : string;
    documentoscontractuales : string;
    modalidadcontratacion : string;
    formatoresponsable : string;
    tipoproceso : string;
    tipoadj : string;
    numeroproceso : string;
    montogarantia : number;
    montogarantiamoneda : string;
    estadointerno : string;
    busqueda:string
    montodisponible:number
    tiposervicio:string
    accion:string
    w_po_mensaje:string
    unidadcodigo_detail:string
    companiasocio_descri:string
    //auditoria
    preparada:string
    revisada:string
    aprobada:string

    //aux
    fecha_desde:string
    fecha_hasta:string
    tipocambio:number

    correo:string

    // columnas
    lstDetalleContratos:DtoComunWhContratodetalle[]
    dw_detail:DtoComunWhContratodetalle[]
    lstContratoRequisicion:DtoComunWhContratorequisicion[]
    lstContratoVencimientos:DtoComunWhContratovencimiento[]
    lstDocumentos:DtoComunSyDocumentoanexos[]

    //documentosrelacionados
    lstContratosRelacionados:DtoComunWhContrato[]
    lstRequerimientosOrigen:DtoComunWhContratorequisicion[]
    lstObligaciones:DtlComunObligaciones[]
    lstContratoMarco:DtlComunWhRequisiciones[]
    lstContratoMarcoEjecutado:DtlComunWhRequisiciones[]

    //adendas
    lstAdendas:DtoComunWhContratopoliza[]
    lstContratoModificaciones:DtoComunWhContratomodificacion[]

    //aux
    montototal_adenda:number
    fechavalidezdesde_adenda:Date
    fechavalidezhasta_adenda:Date
    accionAdenda:boolean
    tipoadenda_descri:string
}
