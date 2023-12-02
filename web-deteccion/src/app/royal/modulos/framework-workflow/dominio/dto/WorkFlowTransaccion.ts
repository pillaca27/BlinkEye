import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class WorkFlowTransaccion extends DominioTransaccion {
    transaccionOrigenId: number;
    procesoId: string;
    transaccionId: number;
    empleadosolicitanteId: number;
    documentoReferencia: string;
    tipoDocumento: string;
    companiasocioId: string;
    centrocostoId: string;
    sucursalId: string;
    proyectoId: string;
    moneda: string;
    areaRevisoraId: string;
    asArea: string;
    unidadOperativa: string;
    monto: number;
    personaReferenciaid: number;
    metadatos: any[];
    listaAdjuntos: any[];
    listaAprobador: any[];
    motivoReclutamiento: string;
    segmentoEnviado: string;
}