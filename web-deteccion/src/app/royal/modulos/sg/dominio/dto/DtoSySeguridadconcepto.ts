import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
export class DtoSySeguridadconcepto extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    aplicacioncodigo: string;
    grupo: string;
    concepto: string;
    ordenpresentacion: number;
    descripcionlocal: string;
    descripcioningles: string;
    codigointerno: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    // columnas
    flgactualizar: string;

    color:string;
}
