import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunSyAplicacionreportetopico extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    aplicacioncodigo: string;
    topico: string;
    descripcionlocal: string;
    descripcioningles: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    uuid: string;

    // columnas
    aplicacioncodigonombre: string;
}
