import { DominioTransaccion } from "../../../../../framework/modelo/generico/DominioTransaccion";

 

export class DtoComunWhContratomodificacion extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    companiasocio : string;
    numerocontrato : string;
    secuencia : number;
    descripcion : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    accion:string

    // columnas
    
}
