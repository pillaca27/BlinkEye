export class WfTransaccionPk {
    transaccionid: number;
    auxiliar: string;
}
export class WfTransaccion {
    pk: WfTransaccionPk;
    constructor() {
        this.pk = new WfTransaccionPk();
    }
    procesoid: string;

    areaRevisoraId: string;
    companiaSocioId: string;
    centroCostosId: string;
    sucursalId: string;
    proyectoId: string;
    monedaId: string;
    monto: number;

    versionid: number;
    flujoid: number;
    nivelid: number;
    criterios: string;
    solicitanteid: number;
    fecharegistro: Date;
    referencia: string;
    estado: string;
    creacionusuario: string;
    modificacionusuario: string;
    creacionfecha: Date;
    modificacionfecha: Date;
    transaccionOrigenId: number;
    segmentoEnviado: string;
    segmentoPendiente: string;
    segmentoAprobado: string;
}