import { EmailTransaccion } from './../../../../framework/modelo/correo/EmailTransaccion';
import { DtoFlujoAdjunto } from "./DtoFlujoTransaccionRequest";

export class DtoFlujoSolicitud {
    constructor() {
        this.email = new EmailTransaccion();
    }
    proceso: string;
    flujo: number;
    transaccion: number;
    solicitante: string;
    referencia: string;
    fechaRegistro: Date;
    procesoFlujoDescripcion: string;
    nivelAprobar: number;
    ROWNUM_: number;
    usuarioAccion: string;
    usuariosubaccion: string;
    estado: string;
    estadoDescripcion: string;
    nivelActual: number;
    criterios: string;
    idSolicitante: number;
    componenteweb: string;
    areaNombre: string;
    monto: number;
    adjuntos: DtoFlujoAdjunto[];
    tiposadjuntovalidar: DtoFlujoAdjuntoValidar[];
    observaciones: string;
    nombrepersonareferencia: string;
    segmento: string;
    email: EmailTransaccion;
    conEmailPreparado: string;
    uuid: string;
}
export class DtoFlujoAdjuntoValidar {
    tipodocumento: string;
    tipodocumentodescripcion: string;
    requerido: string;
    firmaelectronica: string;
    firmaimagen: string;
    grupo: string;
    plantilla: string;
}