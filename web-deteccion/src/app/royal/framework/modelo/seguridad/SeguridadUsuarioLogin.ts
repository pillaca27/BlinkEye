import { DominioTransaccion } from '../generico/DominioTransaccion';

export class SeguridadUsuarioLogin extends DominioTransaccion{
    sid: string;
    tipoUsuarioId: string;
    aplicacionCodigo: string;
    aplicacionNombre: string;
    companiaCodigo: string;
    companiaNombre: string;
    usuario: string;
    clave: string;
    token: string;
    direccionIp: string;
}