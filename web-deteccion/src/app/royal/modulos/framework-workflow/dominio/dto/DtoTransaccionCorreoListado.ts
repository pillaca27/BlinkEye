export class DtoTransaccionCorreoListado{


    flujoId: number;
    nivelId: number;
    tipoAprobadorId: string;
    tipoAprobadorNombre: string;
    accion: string;
    estadoId: string;
    estadoNombre: string;
    correoPersona: string;
    correoJefe: string;
    correoSolicitante: string;
    correoPersonaReferencia: string;
    correoTransaccion: string;
    correoOtros: string;
    aprobadores: string;
    aprobadoresCorreos: string;

    tienePlantillaAprobar:string;
    tienePlantillaRechazar:string;
    tienePlantillaDevolver:string;
}
