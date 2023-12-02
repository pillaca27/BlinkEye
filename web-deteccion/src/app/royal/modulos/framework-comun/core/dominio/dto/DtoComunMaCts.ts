import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunMaCts extends DominioTransaccion {
    numerocts : number;
    regimen : string;
    descripcion : string;
    fechainicio : Date;
    fechafin : Date;
    fechapago : Date;
    estado : string;
    estadocts : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    periodoplanilla : string;
}