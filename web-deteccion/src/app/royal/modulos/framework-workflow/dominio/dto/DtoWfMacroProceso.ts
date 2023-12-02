import { DtoWfMacroProcesoDetalle } from './DtoWfMacroProcesoDetalle';
import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoWfMacroProceso extends DominioTransaccion {
    constructor() {
        super();
        this.detalle = [];
    }

    // pk
    macroProcesoId: string;
    nombre: string;
    estado: string;
    creacionUsuario: string;
    creacionFecha: Date;
    modificacionUsuario: string;
    modificacionFecha: Date;

    // columnas
    detalle: DtoWfMacroProcesoDetalle[]
}
