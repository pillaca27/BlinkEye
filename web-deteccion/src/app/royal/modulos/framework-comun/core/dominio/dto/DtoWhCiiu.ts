import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoWhCiiu   extends DominioTransaccion{

    // pk
    ciiu : string;
    descripcion : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    ROWNUM_: number;
    // columnas
    
}
