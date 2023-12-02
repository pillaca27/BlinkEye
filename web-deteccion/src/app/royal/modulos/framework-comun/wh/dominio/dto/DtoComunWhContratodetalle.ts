 
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
import { DtoComunWhContratovencimiento } from './DtoComunWhContratovencimiento';

export class DtoComunWhContratodetalle extends DominioTransaccion {
    constructor(){
        super();
        this.igvexoneradoflag_aux=false
        this.igvexoneradoflag='N'
        this.lstVencimientos=[]
        this.cantidadTotal=0
        this.cantidadTotalGe=0
    }

    // pk
    companiasocio : string;
    numerocontrato : string;
    secuencia : number;
    descripcion : string;
    cantidad : number;
    preciounitario : number;
    montototal : number;
    item : string;
    commodity : string;
    unidadcodigo : string;
    cuentacontable : string;
    centrocosto : string;
    centrocosto_descri:string
    centrocostodestino : string;
    proyecto : string;
    proyecto_descri:string
    sucursal : string;
    camporeferencia : string;
    destinocompaniasocio : string;
    comentario : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    igvexoneradoflag : string;
    igvexoneradoflag_aux : boolean
    referenciafiscal01 : string;
    referenciafiscal02 : string;
    referenciafiscal03 : string;
    addfuturoflag : string;
    yecAfe : string;
    adendanumero : number;
    adendaflag : string;
    adendaestado : string;
    adendarazonrechazo : string;
    adendarequisicion : string;
    futuroflag : string;
    accion:string
    lstVencimientos:DtoComunWhContratovencimiento[]

    cantidadTotal:number
    cantidadTotalGe:number

    tipoitem:string
    controlcalidadflag:string

    // columnas
    
}
