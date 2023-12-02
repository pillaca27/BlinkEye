export class DtoHrPostulantecurso {
    postulante: number;
    secuencia: number;
    tipomaestro: string;
    curso: number;
    tipocurso: string;
    centroestudio: number;
    fechadesde;
    fechahasta;
    dias: number;
    horas: number;
    anosvigencia: number;
    nota: number;
    flagauspicioempresa: string;
    flagcapacitacion: string;
    capacitacion: string;
    capacitacioncompania: string;
    nivelgeneral: string;
    idiomanivellectura: string;
    idiomaniveloral: string;
    idiomanivelescritura: string;
    observaciones: string;
    rutadocumento: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxCursoNombre: string;
    auxTipoNombre: string;
    auxCentroNombre: string;
    auxArchivoContenido: string;
    auxFlagauspicioempresa: boolean;
}