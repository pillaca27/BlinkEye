import { WorkFlowAprobadorTransaccion } from "./WorkFlowAprobadorTransaccion";
export class DtoFlujoTransaccionRequest {
    proceso: string;
    criterios: string;
    referencia: string;
    solicitante: number;
    origenTransaccion: number;

    companiaid: string;
    centrocostosid: string;
    sucursalid: string;
    proyectoid: string;
    monedaid: string;
    areaid: string;
    monto: number;

    adjuntos: DtoFlujoAdjunto[] = [];
    listaAprobador: WorkFlowAprobadorTransaccion[] = [];

    constructor() {
        this.adjuntos = [];
        this.listaAprobador = [];
    }
}
export class DtoFlujoAdjunto {

    constructor() {
        this.auxSeleccionado = 'N';
    }

    tCompania: string;
    tProceso: string;
    tReferencia: string;


    proceso: number;
    flujo: number;
    transaccion: number;
    transaccionUUID: string;
    secuencia: number;
    cantidadfirmas: number;
    tipodocumento: string;
    rutaadjunto: string;
    flagVer: string;
    archivostring: string;
    archivonombre: string;
    firmaElectronica: string;
    firmaImagen: string;
    fecha: Date;
    usuario: string;

    x: number;
    y: number;
    w: number;
    h: number;

    base64Image: string;
    puedeEditar: string;

    grupo: string;
    plantilla: string;
    auxRequerido: string;
    nivel: number;
    flgVerDocumentoGrupo: string;
    flgVerDocumentoNuevo: string;

    auxSeleccionado: string;
    estadoTransaccion: string;

}