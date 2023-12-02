import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunZonapostal extends DominioTransaccion {

    constructor(){
        super();
        this.accion=''
    }
    
    departamento : string;
    provincia : string;
    codigopostal : string;
    descripcioncorta : string;
    descripcionlarga : string;
    cobrador : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    pais : string;

    
    accion:string
}