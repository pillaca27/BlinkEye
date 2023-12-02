import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class WfTransaccionComunicacionPk {
    transaccionId: number;
    comunicacionId: number;
}
export class WfTransaccionComunicacion extends DominioTransaccion {
    pk: WfTransaccionComunicacionPk;
    constructor() {
        super();
        this.pk = new WfTransaccionComunicacionPk();
        this.origen = "";
    }
    comunicacionPadreId: number;
    emisorId: number;
    receptorId: number;
    mensaje: string;
    tipoMensajeid: string;
    url: string;
    estado: string;
    creacionFecha: Date;
    creacionUsuario: string;
    modificacionFecha: Date;
    modificacionUsuario: string;
    auxContenido: string;
    origen: string;
}