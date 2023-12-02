import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunMonedamast extends DominioTransaccion {
    monedacodigo : string;
    descripcioncorta : string;
    sigla : string;
    descripcionextranjera : string;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    codigofiscal : string;
}