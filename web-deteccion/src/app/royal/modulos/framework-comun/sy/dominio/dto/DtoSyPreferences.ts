import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoSyPreferences extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    usuario : string;
    preference : string;
    aplicacioncodigo : string;
    tipovalor : string;
    valorstring : string;
    valornumero : number;
    valorfecha : Date;
    ultimousuario : string;
    ultimafechamodif : Date;

    // columnas
    descripcioncorta:string;
}
