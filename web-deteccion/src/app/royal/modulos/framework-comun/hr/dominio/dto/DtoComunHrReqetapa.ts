import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
 

export class DtoComunHrReqetapa extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    companyowner : string;
    etapa : number;
    descripcion : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;

    // columnas
    
}
