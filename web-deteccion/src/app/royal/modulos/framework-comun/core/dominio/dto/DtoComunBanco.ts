import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunBanco extends DominioTransaccion {
    constructor(){
        super();
        this.cheque=false
        this.concilio=false
        this.persona=''
    }

    // pk
    banco : string;
    descripcioncorta : string;
    descripcionlarga : string;
    banconumero : number;
    personacontacto : string;
    conciliacionautomaticaflag : string;
    formatopropioflag : string;
    formatodatawindow : string;
    estado : string;
    ultimafechamodif : Date;
    timestamp : object;
    ultimousuario : string;
    conciliacionformatoflag : string;
    codigointerfaseafp : string;
    disponibleedflag : string;
    tasaefectivaanual : number;
    codigofiscal : string;
    cheque:boolean
    concilio:boolean
    persona:string

    // columnas
    
}
