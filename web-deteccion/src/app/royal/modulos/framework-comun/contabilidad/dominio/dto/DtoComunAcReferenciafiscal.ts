import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcReferenciafiscal extends DominioTransaccion {
    ano : string;
    tiporeferenciafiscal : string;
    nivel : string;
    referenciafiscal : string;
    descripcionlocal : string;
    descripcioningles : string;
    referenciafiscalrelacionada : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    referenciafiscalanterior : string;
    unidadmedida : string;
    tipogasto : string;
}