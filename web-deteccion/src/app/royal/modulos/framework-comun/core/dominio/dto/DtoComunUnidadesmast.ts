import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunUnidadesmast extends DominioTransaccion{
    unidadcodigo : string;
    descripcioncorta : string;
    descripcionextranjera : string;
    unidadtipo : string;
    numerodecimales : number;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    codigofiscal : string; 
}