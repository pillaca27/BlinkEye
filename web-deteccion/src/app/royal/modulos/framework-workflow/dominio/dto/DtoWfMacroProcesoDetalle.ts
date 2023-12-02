import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoWfMacroProcesoDetalle extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    macroProcesoId : string;
    procesoOrigenId : string;
    procesoDestinoId : string;
    orden : number;
    creacionUsuario : string;
    creacionFecha : Date;
    modificacionUsuario : string;
    modificacionFecha : Date;

    flgactualizar:string;
    // columnas
    
}
