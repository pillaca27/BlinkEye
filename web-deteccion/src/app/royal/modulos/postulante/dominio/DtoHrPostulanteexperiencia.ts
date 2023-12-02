export class DtoHrPostulanteexperiencia {
    postulante: number;
    secuencia: number;
    fechadesde: Date;
    fechahasta: Date;
    ano: number;
    tipoexperiencia: string;
    empresa: string;
    giroempresa: string;
    tipoentidad: string;
    tipocontrato: string;
    motivocese: string;
    sueldo: number;
    moneda: string;
    areaexperiencia: string;
    cargo: string;
    funciones: string;
    puestosimilar: number;
    competencias: string;
    puntosmejora: string;
    observaciones: string;
    flagtrabaja: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxMotivoNombre: string;
    auxContratoNombre: string;
    auxPuestoNombre: string;

    auxflagtrabaja:boolean;
}