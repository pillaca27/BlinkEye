import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunServicioximpuesto extends DominioTransaccion{

    constructor(){
        super();
        this.accion=""
    }
    
    tiposervicio : string;
    impuesto : string;
    ultimousuario : string;
    ultimafechamodif : Date;


  
    timestamp : object;
    accion:string
    tipoimpuesto:string

}