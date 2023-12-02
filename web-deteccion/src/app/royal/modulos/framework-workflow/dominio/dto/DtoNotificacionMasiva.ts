export class DtoNotificacionMasiva {
    mensaje: string;
    transacciones: DtoPersonaNotificar[];
    constructor() {
        this.transacciones = [];
    }
}

export class DtoPersonaNotificar {

    constructor() {
        this.auxlink = null;
    }

    transaccionId: number;
    personaId: number;
    auxlink: string;
}