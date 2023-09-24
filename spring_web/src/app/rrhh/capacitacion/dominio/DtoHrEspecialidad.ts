import { DominioTransaccion } from "src/app/util/DominioTransaccion";

export class DtoHrEspecialidad extends DominioTransaccion {
    especialidad: number;
    descripcion: string;
    estado: string;
    ultimoUsuario: string;
    ultimaFechaModif: Date;
    unidadReplicacion: string;
}