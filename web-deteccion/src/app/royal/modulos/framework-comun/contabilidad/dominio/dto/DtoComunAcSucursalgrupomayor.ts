import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunAcSucursalgrupomayor extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    sucursalgrupomayor : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    uuid : string;

    // columnas
    
}
