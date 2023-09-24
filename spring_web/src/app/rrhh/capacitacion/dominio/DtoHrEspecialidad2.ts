import { DominioTransaccion } from "src/app/util/DominioTransaccion";

export class DtoHrEspecialidad2 extends DominioTransaccion {
    especialidad: number;
    descripcion: string;
    estado: string;
    ultimoUsuario: string;
    ultimaFechaModif: Date;
    unidadReplicacion: string;
    empleadoSolicitante: number;
    auxSolicitanteNombre: string;
    auxEstadoNombre: string;
}