import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunSyUnidadreplicacion extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    unidadreplicacion : string;
    descripcionlocal : string;
    descripcionextranjera : string;
    rangopersonainicio : number;
    rangopersonafin : number;
    personaactual : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;

    // columnas
    
}
