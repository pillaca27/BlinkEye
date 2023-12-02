import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunPlancontable extends DominioTransaccion {
    plancontable : string;
    descripcionlocal : string;
    digitaccount : number;
    digitprime : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
}