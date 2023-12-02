import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunMaPersonacuentabancaria extends DominioTransaccion {
    constructor(){
        super();
        this.cuentabanco=''
    }

    // pk
    persona : number;
    secuencia : number;
    monedacodigo : string;
    tipocuenta : string;
    bancocodigo : string;
    cuentabancarianumero : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    diferidomodalidad : string;
    acciones:string
    cuentabanco?:string

    // columnas
    
}
