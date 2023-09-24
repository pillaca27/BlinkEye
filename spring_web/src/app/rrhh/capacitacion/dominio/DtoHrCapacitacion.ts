import { DominioTransaccion } from "src/app/util/DominioTransaccion";
import { DtoHrEmpleadoCapacitacion } from "./DtoHrEmpleadoCapacitacion";

export class DtoHrCapacitacion extends DominioTransaccion {
    capacitacion: string;
    companyOwner: string;
    curso: number;
    descripcion: string;
    estado: string;
    fechaSolicitud: Date;
    fechaDesde: Date;
    fechaHasta: Date;
    empleadoSolicitante: number;
    ultimoUsuario: string;
    ultimaFechaModif: Date;
    archivo : string;
    archivodatastring:string;
    auxSolicitanteNombre: string;
    auxCursoNombre: string;
    auxEstadoNombre: string;
    auxCantidadParticipantes: number;
    lstParticipantes: DtoHrEmpleadoCapacitacion[] = [];
}