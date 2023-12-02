import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';


export class DtoSyProcesomst extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    aplicacioncodigo: string;
    procesocodigo: string;
    descripcion: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    descripcioncorta: string;
    // columnas

}
