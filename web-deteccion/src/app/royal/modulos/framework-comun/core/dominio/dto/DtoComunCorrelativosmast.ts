import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunCorrelativosmast extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    companiacodigo : string;
    tipocomprobante : string;
    serie : string;
    descripcion : string;
    correlativonumero : number;
    correlativodesde : number;
    correlativohasta : number;
    almacencodigo : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    feflag : string;
    fetipocomprobanteref : string;
    feenvio : string;

    // columnas
    
}
