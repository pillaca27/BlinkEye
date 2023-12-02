import { DominioTransaccion } from './../../../../framework/modelo/generico/DominioTransaccion';


export class DtoSeguridadgrupo extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    aplicacioncodigo: string;
    grupo: string;
    descripcion: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    orden: number;
    webgrupo: string;
    webgruposecuencia: string;
    imagen: string;
    uuid: string;
    grupopadre: string;

    // columnas
    aplicacioncodigonombre: string;
}
