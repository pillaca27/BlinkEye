import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';


export class DtoComunAcSucursalgrupo extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    sucursalgrupo: string;
    sucursalgrupomayor: string;
    descripcionlocal: string;
    descripcioningles: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    uuid: string;

    // columnas
    sucursalgrupomayornombre: string;
}
