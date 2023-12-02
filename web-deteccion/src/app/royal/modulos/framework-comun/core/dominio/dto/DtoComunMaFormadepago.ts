import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunMaFormadepago extends DominioTransaccion{
    formadepago : string;
    descripcion : string;
    descripcionextranjera : string;
    creditoflag : string;
    importacionflag : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    cuotacreditoflag : string;
}