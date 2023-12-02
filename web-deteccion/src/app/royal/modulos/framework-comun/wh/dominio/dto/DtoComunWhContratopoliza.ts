import { DominioTransaccion } from "../../../../../framework/modelo/generico/DominioTransaccion";

 

export class DtoComunWhContratopoliza extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    companiasocio : string;
    numerocontrato : string;
    secuencia : number;
    polizatipo : string;
    polizanumero : string;
    fechadesde : Date;
    fechahasta : Date;
    aseguradora : number;
    porcentajecontrato : number;
    montopoliza : number;
    clausulasespecialesflag : string;
    recibospagoflag : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    personacorreo : number;
    comentario : string;
    accion:string
    tipoadenda_descri:string

    // columnas
    
}
