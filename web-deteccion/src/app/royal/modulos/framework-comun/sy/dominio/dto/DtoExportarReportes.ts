import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoExportarReportes extends DominioTransaccion {
    archivoBase64: string;
    archivoNombre: string;
}