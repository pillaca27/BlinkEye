import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
export class DtoComunHrUnidadoperativa extends DominioTransaccion{
    unidadoperativa : string;
    descripcion : string;
    responsable : number;
    unidadoperativaSuperior : string;
    responsablecontratos : number;
    responsablecompania : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
}