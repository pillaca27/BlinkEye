import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
import { DtoComunServicioximpuesto } from './DtoComunServicioximpuesto';
 
export class DtoComunTiposervicio extends DominioTransaccion {

    constructor(){
        super();
        this.lstDetalle=[]
    }

    tiposervicio : string;
    descripcion : string;
    regimenfiscal : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    clasificacionfiscal : string;
    estado : string;
    descripcioningles : string;

    
    timestamp : object;
    
    lstDetalle:DtoComunServicioximpuesto[]
}