import { DtoHrPostulantedireccion } from "./DtoHrPostulantedireccion";

export class DtoHrPostulantedependiente {
    postulante: number;
    secuencia: number;
    tipodependiente: string;
    apellidopaterno: string;
    apellidomaterno: string;
    nombres: string;
    nombrecompleto: string;
    fechanacimiento: Date;
    tipodocumentoidentidad: string;
    documentoidentidad: string;
    sexo: string;
    paisemisor: string;
    codigopostal: string;
    telefono: string;
    correoelectronico: string;
    tiposangre: string;
    flagcentroasistencia: string;
    tipovinculo: string;
    tipodocumentopaternidad: string;
    nrodocumentopaternidad: string;
    mesconcepcion: string;
    fechaalta: Date;
    motivobaja: string;
    fechabaja: Date;
    flagsmf: string;
    flagbeneficio: string;
    flagviveconempleado: string;
    ocupacion: string;
    gradoeducacion: string;
    centroeducacion: string;
    tieneescolaridad: string;
    tipoescolaridad: string;
    anoseducacion: number;
    tipocarnetasistenciasocial: string;
    carnetasistenciasocial: string;
    flagtrabaja: string;
    flagtrabajadentro: string;
    empresa: string;
    puesto: string;
    direccionempresa: string;
    tiemposervicio: number;
    sueldo: number;
    comentarios: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxParentestoNombre: string;

    lstDireccion: DtoHrPostulantedireccion[];

    auxFlagSmf:boolean;
    auxFlagViveConEmpleado:boolean;
    auxFlagBeneficio:boolean;
    auxFlagEscolaridad:boolean;
    auxFlagTrabaja:boolean;
    auxFlagTrabajaDentro:boolean;
    
    constructor() {
        this.lstDireccion = [];
    }
}