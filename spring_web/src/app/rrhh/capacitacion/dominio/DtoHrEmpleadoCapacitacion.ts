import { DominioTransaccion } from "src/app/util/DominioTransaccion";

export class DtoHrEmpleadoCapacitacion extends DominioTransaccion {
    capacitacion: string;
    empleado: number;
    companyOwner: string;
    ultimoUsuario: string;
    ultimaFechaModif: Date;

    auxEmpleadoNombre: string;
}