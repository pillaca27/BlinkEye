export class DtoHrPostulantedocumento {
    postulante: number;
    secuencia: number;
    documento: string;
    descripcion: string;
    fecha: Date;
    fechavencimiento: Date;
    flagpresento: string;
    observaciones: string;
    rutadocumento: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxNombreTipoDocumento: string;
    auxPresentoNombre: string;
    auxContenido: string;
    auxFlagPresento:boolean;
}