import { DtoSySeguridadconcepto } from './DtoSySeguridadconcepto';
import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoSySeguridadgrupo extends DominioTransaccion {
    constructor() {
        super();
        this.seguridadconcepto = [];
    }

    // pk
    aplicacioncodigo: string;
    grupo: string;
    descripcionlocal: string;
    descripcioningles: string;
    tipodetalle: string;
    ordenpresentacion: number;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    // columnas
    seguridadconcepto: DtoSySeguridadconcepto[];
}
